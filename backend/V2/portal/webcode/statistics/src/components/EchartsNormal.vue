<template>
    <div class="chartImg" :id="id"></div>
</template>

<script>
import { random } from "../plugins/utils";
import echarts from "echarts";

export default {
  data() {
    return {
      id: random(),
      chart: null
    };
  },
  props: {
    option: Object
  },
  watch: {
    option: function() {
      this.setOption();
    },
  },
  mounted() {
    this.$nextTick(()=>{
      let dom = document.getElementById(this.id)
      if(dom){
        this.chart = echarts.init(dom);
        window.onresize = this.chart.resize
        this.chart.on("click", params => {
          this.onChartClick(params);
        });
        //點擊shadow事件(整塊柱子的區域)
        this.chart.getZr().on('click',params=>{
        var pointInPixel= [params.offsetX, params.offsetY];
        if (this.chart.containPixel('grid',pointInPixel)) {
            var xIndex=this.chart.convertFromPixel({seriesIndex:0},
                  [params.offsetX, params.offsetY])[0];
                   // 使用getOption() 获取图表的option
            let op = this.chart.getOption()

            // 获取当前点击位置要的数据
            // var xData = op.series[0].data[xIndex]
            // var allData = op.series
            // let xData = []
            // let yName = ['用戶數','訪問量','數據量','活躍用戶']
            // allData.forEach((item,index)=>{
            //   if(item.data.length){
            //     let obj = {data:item.data[xIndex],index:index,name:yName[index]}
            //     xData.push(obj)
            //   }
            // })
            // console.log("xData",xData)

            // let theDate = op.xAxis[0].data[xIndex]
            // console.log("op",op)
            // console.log("選中的時間爲",theDate)
            this.shadowClick(op,xIndex)
            /*事件处理代码书写位置*/
    }
    });
        this.setOption();
      }
    })
    
  },
  methods: {
    setOption: function() {
      // 合并是在之前的 option 基础上更新，不合并是直接替换
      let config = { notMerge: true };
      this.chart&&this.chart.setOption(this.option, config);
    },
    onChartClick: function(params) {
      this.$emit("click", params);
    },
    shadowClick:function(op,xIndex){
      this.$emit("shadowClick",op,xIndex)
    }
    
  }
};
</script>

<style lang="css" scoped>
.chartImg {
  width: 100%;
  height: 100%;
}
</style>
