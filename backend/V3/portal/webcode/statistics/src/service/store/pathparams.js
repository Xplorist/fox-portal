export default{
  state:{
    paramsItem:'',   //點擊dashbord時的一個item
    paramsListSystem:''
  },
  mutations:{
    setParamsItem:function(state,paramsInfo){
      // console.log("setEquItem",paramsInfo)
      state.paramsItem = paramsInfo;
    },
    setListSystem:function(state,paramsInfo){
      // console.log("paramsListSystem",paramsInfo)
      state.paramsListSystem = paramsInfo;
    }
  }
};
