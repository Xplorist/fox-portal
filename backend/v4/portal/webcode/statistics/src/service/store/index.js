import Vue from "vue";
import Vuex from "vuex";

import pathparams from "./pathparams"
import mutations from "./mutations"
import actions from "./actions"
Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: mutations,
  actions: actions,
  modules: {
    pathparams: pathparams,
  }
});
