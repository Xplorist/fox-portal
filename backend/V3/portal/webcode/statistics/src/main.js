import Vue from 'vue';
import App from './App.vue';
import router from './router';
import echarts from "echarts";
import ElementUI from 'element-ui'
import  "./plugins/axios"
import './styles.scss'
import store from "./service/store"
Vue.use(ElementUI)

Vue.config.productionTip = false;

let instance = null;
Vue.prototype.$echarts = echarts;

new Vue({ router, store, render: h => h(App) }).$mount("#app");
function render(props = {}) {
  const { container } = props;
  instance = new Vue({
    router,
    store,
    render: (h) => h(App),
  }).$mount(container ? container.querySelector('#app') : '#app');
}

if (!window.__POWERED_BY_QIANKUN__) {
  render();
}

export async function bootstrap() {}

export async function mount(props) {
  render(props);
}

export async function unmount() {
  instance.$destroy();
  instance = null;
}
