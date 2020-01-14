package com.wind2esg.capacitor.alipay;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import java.util.Map;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;

@NativePlugin()
public class Alipay extends Plugin {
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            PluginCall savedCall = getSavedCall();
            if(savedCall == null){
                Log.e("capacitor-alipay", "savedCall null");
                return;
            }
            JSObject json = new JSObject();
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    json.put("payResult", resultInfo);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        json.put("result", 0);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        json.put("result", -1);                    
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    json.put("payResult", authResult);
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        json.put("result", 0);
                    } else {
                        // 其他状态值则为授权失败
                        json.put("result", -1);  
                    }
                    break;
                }
                default:
                    break;
            }
            savedCall.resolve(json);
        };
    };

    @PluginMethod()
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.success(ret);
    }

    @PluginMethod()
    public void pay(PluginCall call){
        final String orderInfo = call.getString("orderInfo");
        saveCall(call);
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @PluginMethod()
    public void auth(PluginCall call){
        final String authInfo = call.getString("authInfo");
        saveCall(call);
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(authInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
