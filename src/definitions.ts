declare module "@capacitor/core" {
  interface PluginRegistry {
    Alipay: AlipayPlugin;
  }
}

export interface AlipayPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
}
