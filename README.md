# capacitor-alipay
[![Build Status](https://travis-ci.org/Wind2esg/capacitor-alipay.svg?branch=master)](https://travis-ci.org/Wind2esg/capacitor-alipay)
[![Build Status](https://img.shields.io/npm/l/capacitor-alipay)](https://www.npmjs.com/package/capacitor-alipay)
[![Build Status](https://img.shields.io/npm/v/capacitor-alipay)](https://www.npmjs.com/package/capacitor-alipay)
[![Build Status](https://img.shields.io/npm/dm/capacitor-alipay)](https://www.npmjs.com/package/capacitor-alipay)

## Usage
Install  

`npm i -S capacitor-alipay`    
`npx cap update`

### Android
Edit native code.  

+ `npx cap open android`  
  
+ Find module `app`, open `<your package>/MainActivity.java`, add `add(alipay.class);`.
+ Edit project's `build.gradle`
```
allprojects {
    repositories {
        flatDir {
            dirs project(':capacitor-alipay').file('libs')
        }
        ...
    }
    ...
}
```
+ As official guided, you have to store your private key at your server and offer api for `orderInfo` and `authInfo`.

编辑原生代码。
+ `npx cap open android` 打开 IDE。  
  
+ 找到 `app` 模块，找到你包名下的 `MainActivity.java`，添加 `add(alipay.class);`。  
+ 修改项目的`build.gradle`，如上(见英文部分)
+ 如官方所说，必须将私钥等信息存储在服务器，通过 api 提供 `orderInfo` 和 `authInfo`。

For more infomation, plz refer to [alipay official wiki](https://docs.open.alipay.com/204/105296/)  

更多详情 [官方文档](https://docs.open.alipay.com/204/105296/)  

### IOS  
TBD  

## API
+ `pay`
  + param: `{orderInfo: string}`
  + return: `{payResult: string, result: string}`
+ `auth`
  + param: `{orderInfo: string}`
  + return: `{payResult: string, result: string}`

>  `orderInfo` and `authInfo` is string return from your api.  
>  `code`, 0 for success, -1 for failure.  
>  `result`, detail result

> For more infomation, plz refer to [alipay official wiki](https://docs.open.alipay.com/204/105296/)  

## Related link
[capacitor 支付宝插件](https://github.com/Wind2esg/capacitor-alipay)  
[capacitor-alipay](https://github.com/Wind2esg/capacitor-alipay)  

