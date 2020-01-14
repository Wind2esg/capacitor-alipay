import { WebPlugin } from '@capacitor/core';
import { AlipayPlugin } from './definitions';
export declare class AlipayWeb extends WebPlugin implements AlipayPlugin {
    constructor();
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    pay(orderInfo: {
        orderInfo: string;
    }): Promise<{
        result: number;
        payResult: string;
    }>;
    auth(authInfo: {
        authInfo: string;
    }): Promise<{
        result: number;
        payResult: string;
    }>;
}
declare const Alipay: AlipayWeb;
export { Alipay };
