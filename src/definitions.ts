declare module "@capacitor/core" {
  interface PluginRegistry {
    Alipay: AlipayPlugin;
  }
}

export interface AlipayPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
  pay(orderInfo: {orderInfo: string}): Promise<{result: number; payResult: string}>;
  auth(authInfo: {authInfo: string}): Promise<{result: number; payResult: string}>;
}
