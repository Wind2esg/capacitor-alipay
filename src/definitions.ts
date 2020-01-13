declare module "@capacitor/core" {
  interface PluginRegistry {
    Alipay: AlipayPlugin;
  }
}

export interface AlipayPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
  pay(orderInfo: string): Promise<{result: number; payResult: string}>;
}
