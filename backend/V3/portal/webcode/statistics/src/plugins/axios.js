import axios from "axios";
import config from './config'

axios.defaults.baseURL = config.apiUri;
axios.defaults.headers.post["Content-Type"] = config.postContentType;

/**
 * @desc 设置异常
 * @param {Object} response
 */
const setErr = response => {
  response.data.data = response.config;
  return reject(response.data);
};
/**
 * @desc 处理正确返回值
 * @param {Object} response
 * @returns {Object} 如果返回值正常，则返回数据内容，否则抛出异常
 */
const resolve = response => {
  let data = response.data;
  let code = parseInt(data.code);
  let isOk = code === 1 || code === "1";
  if (isOk) return data.data;
  if (!isOk && data.msg) return setErr(response);
  return data;
};
/**
 * @desc 异常处理
 */
const reject = error => {
  config.model === "development" && window.console.log(error);
  if (error.msg) return Promise.reject(error.msg);
  return Promise.reject("网络异常!");
};
axios.interceptors.response.use(resolve, reject);

export default axios;
