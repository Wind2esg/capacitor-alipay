import { WebPlugin } from '@capacitor/core';
import { AlipayPlugin } from './definitions';
export declare class AlipayWeb extends WebPlugin implements AlipayPlugin {
    constructor();
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    pay(orderInfo: string): Promise<{
        result: number;
        payResult: string;
    }>;
}
declare const Alipay: AlipayWeb;
export { Alipay };
