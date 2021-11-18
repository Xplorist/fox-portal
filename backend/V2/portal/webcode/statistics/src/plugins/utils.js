
export const random=()=>{
  return Math.random().toString(32).slice(2,10)
}
// 2020年05月19日20点07分52秒
export const parseDateTime=dateStr=>{
  return parseDate(dateStr)+parseTime(dateStr);
}
export const padStart=(value,length=2,str="0") => {
  return value.toString().padStart(length,str);
}

export const parseTime=dateStr=>{
  if (!dateStr) return "";
  let date=new Date(dateStr);
  let hour=date.getHours();
  let minute=date.getMinutes();
  let second=date.getSeconds();
  return `${padStart(hour)}點${padStart(minute)}分${padStart(second)}秒`;
};

export const parseDate=(dateStr)=>{
  if (!dateStr) return "";
  let date=new Date(dateStr);
  let year=date.getFullYear();
  let month=date.getMonth()+1;
  let day=date.getDate();
  return `${year}年${padStart(month)}月${padStart(day)}日`;
};
export const parseDate2=(dateStr)=>{
  if (!dateStr) return "";
  let date=new Date(dateStr);
  let year=date.getFullYear();
  let month=date.getMonth()+1;
  let day=date.getDate();
  return `${year}-${padStart(month)}-${padStart(day)}`;
};

export const barOption=(xData,yData)=>{
  let xAxis={type:'category',data:xData,axisLine:{lineStyle:{color:'#DBDFE5'}},axisLabel:{textStyle:{color:'#383E49'}},axisTick:{show:false}}
  let yAxis={type:'value',axisTick:{show:false},axisLine:{show:false},}
  let series=yData
  yData.forEach(item=>{
    item.type='bar';
    item.label = {show:true,position:'top',color:'#383E49'}
  })
  //x:左邊距  x2:右邊距  y:上邊距  y2:下邊距
  let grid={x:80,y:30,x2:100,y2:30}
  let yName = ['用戶數','活躍用戶']
  let option={xAxis:xAxis,yAxis:yAxis,series:series,color:[],grid:grid,
    tooltip: {
      trigger: 'axis',
      axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    },
      formatter:params=>{
        let all =`${params[0].name}<br>`
        for(let i=0;i<params.length;i++){
          all += `${params[i].marker}${yName[i]}:${params[i].data}<br>`
        }
         return all;
      }
  },}
  
  option.color=['#3A7EFF','#1DCA51','#FFB400','#FF7C1C']
  return option;
}

export const barOption2=(xData,yData,accessSums)=>{

let option = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter:params=>{
          let all =`${params[0].name}<br>`
          for(let i=0;i<params.length;i++){
              let activenum = params.find(item=>item.seriesName==="活躍用戶").value
              let activeDevice = params.find(item=>item.seriesName==='活躍設備數').value
              if(params[i].seriesName==='用戶數'){
                  all += `${params[i].marker}${params[i].seriesName}:${params[i].data+activenum}<br>`
              }
              else if(params[i].seriesName==='總設備數'){
                all += `${params[i].marker}${params[i].seriesName}:${params[i].data+activeDevice}<br>`
              }
              else{
                  all += `${params[i].marker}${params[i].seriesName}:${params[i].data}<br>`      
              }
            
          }
           return all;
        }
    },
    legend: {
      // itemWidth:15,  
      // itemHeight:15,  
      data:['用戶數','活躍用戶','總設備數','活躍設備數','訪問量'],
      y:'bottom',
      x:'center'
  },
    xAxis:  {
        type: 'category',
        data:xData
    },
    yAxis: [
      
      {
        type: 'value',
        name: '用戶數/設備數量',
        axisLabel: {
            formatter: '{value}'
        },
        axisTick:{show:false},
        splitLine: {
          show: false
      }
    },
      {
        type: 'value',
        name: '訪問量',
        zlevel:1,
        axisLabel: {
            formatter: '{value} '
        },
        axisTick:{show:false},
        splitLine: {
          show: false
      }
    }
    ],
    series: [
      
      {
        name: '活躍用戶',
        stack:'用戶',
        type: 'bar',
        zlevel:1,
        barMaxWidth: '25',
        label: {
            normal: {
                show: xData.length>15?false:true,
                position: 'inside',
                fontSize:'12',
                fontWeight:'bold'
            },
            emphasis: {
              show: true,
              position:"inside"
          }
        },
        itemStyle : {
          normal : {
            color:'#ff9080',
            lineStyle:{
              color:'#ff9080'
            },
            label:{show:true,formatter: function(p) {
              return p.value > 0 ? (p.value) : '';
          }}
          }
        },
        data: yData[3].data
    },
    {
      name: '用戶數',
      type: 'bar',
      stack:'用戶',
      // zlevel:2,
      //barGap: '-100%',//添加此配置项即为重叠效果
      barMaxWidth: '25',
      label: {
          normal: {
              show: true,
              position: 'top',
              fontSize:'12',
              fontWeight:'bold'
          }
      },
      itemStyle : {
        normal : {
          color:'#00bfb7',
          lineStyle:{
            color:'#00bfb7'
          },
          label:{show:true,formatter:(params)=>{
            if(params.value>0){
              return params.value+yData[3].data[params.dataIndex]
            }else{
              return ''
            }
            
          }}
        }
      },
      data: yData[0].data
  },


  {
    name: '活躍設備數',
    stack:'設備',
    type: 'bar',
    zlevel:1,
    barMaxWidth: '25',
    label: {
        normal: {
            show: xData.length>15?false:true,
            position: 'inside',
            fontSize:'12',
            fontWeight:'bold'
        },
        emphasis: {
          show: true,
         position:"inside"
      }
    },
    itemStyle : {
      normal : {
        color:'#c23531',
        lineStyle:{
          color:'#c23531'
        },
        label:{show:true,formatter: function(p) {
          return p.value > 0 ? (p.value) : '';
      }}
      }
    },
    data: yData[5].data
},
{
  name: '總設備數',
  type: 'bar',
  stack:'設備',
  // zlevel:2,
  //barGap: '-100%',//添加此配置项即为重叠效果
  barMaxWidth: '25',
  label: {
      normal: {
          show: true,
          position: 'top',
          fontSize:'12',
          fontWeight:'bold'
      }
  },
  itemStyle : {
    normal : {
      color:'#FF7C1C',
      lineStyle:{
        color:'#FF7C1C'
      },
      label:{show:true,formatter:(params)=>{
        if(params.value>0){
          return params.value+yData[5].data[params.dataIndex]
        }else{
          return ''
        }
        
      }}
    }
  },
  data: yData[4].data
},
        {
          itemStyle : {
            normal : {
              color:'#3a7eff ',
              lineStyle:{
                color:'#3a7eff '
              },
              label:{show:true,position:'top'}
            }
          },
          symbolSize: 7,
          symbol:'circle',
          name: '訪問量',
          type: 'line',
          yAxisIndex: 1,
          zlevel:1,
          data: accessSums
      }
    ]
};



 
return option
}

export const lineOption = (data)=>{
  let times = [];
  let yDatas = [];
  data.forEach(item=>{
    times.push(item.theDate);
    yDatas.push(item.accessNum);
  })
  let grid={x:80,y:30,x2:100,y2:40}
    let option = {
      xAxis: {
          type: 'category',
          data: times,
          axisTick:{show:false}
      },
      legend: {
        show: true,
        data:['訪問量'],
        y:'bottom'
      
    },
      tooltip: {
        trigger: 'axis',
        formatter:params=>{
          return `${params[0].name}<br>${params[0].marker}訪問量:${params[0].data}`
        }
    },
      yAxis: {
          type: 'value',
          axisTick:{show:false},
          axisLine:{show:false}
      },
      grid:grid,
      series: [{
          name:'訪問量',
          symbolSize: 7,
          symbol:'circle',
          data: yDatas,
          type: 'line',
          itemStyle : {
            normal : {
              color:'#3a7eff',
              lineStyle:{
                color:'#3a7eff'
              },
              label:{show:true}
            }
          }
      }]
  };
  return option

}