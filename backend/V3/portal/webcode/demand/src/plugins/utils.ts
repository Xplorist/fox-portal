/**
 * @description 生成一個長度爲8的隨機數
 */
export const random = (): string => Math.random().toString(32).slice(2, 10);
/**
 * @description 下载文件
 * @param {string} uri 文件地址
 * @param {string} name 文件名
 */
export const downloadFile = (uri: string, name: string) => {
  let a = document.createElement("a");
  a.href = uri;
  a.download = name;
  a.style.position = 'fixed';
  a.style.visibility = 'hidden';
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
};
