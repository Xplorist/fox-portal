import development from "./development.js";
import production from "./production";

const config = {
  apiVersion: "1.1",
  model: process.env.NODE_ENV,
  routeModel: "history",
  postContentType: "application/json",
};

const configs = { development, production };


const setConfig = obj => {
  for (const key of Object.keys(obj)) config[key] = obj[key];
};

if (configs[config.model]) setConfig(configs[config.model]);

export default config;
