export default {
  IS_DEV: process.env.NODE_ENV === 'development',
  // @ts-ignore 根路徑
  BASE_URL: baseURI,
  // @ts-ignore 日期格式
  DATE_FORMAT: dateFormat,
  // @ts-ignore 時間格式
  TIME_FORMAT: timeFormat,
  // @ts-ignore 日期時間格式
  DATE_TIME_FORMAT: datetimeFormat,
};
