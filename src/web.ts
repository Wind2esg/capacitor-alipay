import { WebPlugin } from '@capacitor/core';
import { AlipayPlugin } from './definitions';

export class AlipayWeb extends WebPlugin implements AlipayPlugin {
  constructor() {
    super({
      name: 'Alipay',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }

  async pay(orderInfo: {orderInfo: string}): Promise<{result: number, payResult: string}> {
    console.log(orderInfo);
    //send request to api to get qrcode, scan to pay
    return new Promise(()=>({result: 0, payResult: "web"}))
  }

  async auth(authInfo: {authInfo: string}): Promise<{result: number, payResult: string}> {
    console.log(authInfo);
    //send request to api to get qrcode, scan to pay
    return new Promise(()=>({result: 0, payResult: "web"}))
  }
}

const Alipay = new AlipayWeb();

export { Alipay };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(Alipay);
