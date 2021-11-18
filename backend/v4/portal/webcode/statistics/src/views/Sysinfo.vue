<template>
  <div>
    <div class="top">
      <div class="top-main">
        <el-select v-model="factoryno" placeholder="廠區" clearable>
          <el-option v-for="item in factorynos" :key="item.id" :label="item.label" :value="item.id"></el-option>
        </el-select>
        <el-select v-model="punit" placeholder="部門" clearable>
          <el-option v-for="item in punits" :key="item.id" :label="item.label" :value="item.id"></el-option>
        </el-select>
        <!-- <div class="top-info">{{sysName}}刷新於{{time}}</div> -->
      </div>
 
    </div>
    <div class="main mt-1">
      <div class="main1 ">
          <div class="name">{{sysName}}</div>
            <div class="inner mt-4-reduce">
          <div class="row" style="margin-left:8%">
            <div class="left">
              <el-radio-group v-model="dateType">
              <el-radio label="week">近七日</el-radio>
              <el-radio label="month">近一月</el-radio>
              <el-radio label="year">近一年</el-radio>
              <el-radio :disabled="ifDisabled" :label="dateString">
                <el-date-picker
                v-model="dateArr"
                type="datetimerange"
                range-separator="~"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                class="mr-1"
                style="padding:0px;"
                format="yyyy-MM-dd "
                value-format="yyyy-MM-dd "
                :default-time="['00:00:00', '23:59:59']"
                @change="dateValueChange"
                :clearable="false"
                >
                </el-date-picker>
              </el-radio>
            </el-radio-group>
            <el-select v-model="mobile" placeholder="所有終端" class="zd" @change="changeMobile">
              <el-option v-for="item in mobiles" :key="item.id" :label="item.label" :value="item.value"></el-option>
            </el-select>
            </div>
           
          </div>
          <div class="content">
            <Echart :option="getBarOption" @shadowClick="shadowClick" />
          </div>

          <!-- <div class="center">
          <div class="small-block" style="background-color:#3A7EFF"></div><div class="mr-1 inline">用戶數</div>
          <div class="small-block" style="background-color:#1DCA51"></div><div class="mr-1 inline">訪問量(K)</div>
          <div class="small-block" style="background-color:#FFB400"></div><div class="mr-1 inline">數據量(W)</div>
          <div class="small-block" style="background-color:#FF7C1C"></div><div class="mr-1 inline">活躍用戶</div>
        </div> -->
        </div>
    
      </div>
      <div class="main2" :class="{'mb5':ifShow===true,'':ifShow===false}">
        <div style="background:white;height:35px" :class="{'pd1':ifShow===true,'pd2':ifShow===false}">
          <el-input v-model="username" :ref="username" class="zd2 mr-1" placeholder="请输入姓名/工號" @keyup.native="entername"></el-input>
          <div style="cursor:pointer;position:relative;left:155px;height:40px;width:30px;display:inline-block;top:-19px" :class="{'mt1-2':ifShow===true}" @click="findInfo()">
            <i class="el-icon-search" style="padding:9px"></i>  
          </div>
          <div class="inline info-right" v-if="ifShow===true" :class="{'mt1':ifShow===true}">
            <span class="mr-1">工号：{{empId}}</span>
            <span class="mr-1">姓名：{{empName}}</span>
            <span class="mr-1">部门名称：{{deptName}}</span>
            <span class="mr-1">厂区：{{humFactory}}</span>
          </div>
          
      </div>
      <div class="main-2-content" v-if="allVipAccessNum.length" :class="{'h1':allVipAccessNum.length,'h2':!allVipAccessNum.length}">
        <Echart :option="getLineOpion" />
      </div>
      <div class="main-2-content" v-else :class="{'h1':allVipAccessNum.length,'h2':!allVipAccessNum.length}">
        <span class="noContent">暫無數據</span>
      </div>

      </div>
    </div>
   
   <Dialog :mobile="mobile" :dialogVisible="dialogVisible" :theDate="theDate" :systemId="systemId" :dateType="dateType" @close="close"/>
   <userDialog :mobile="mobile" :dialogVisible='allUserDialog' @close="closeUserD" @save="saveUser" :tableData="userData" :sendempId="empId"/>
  </div>
</template>
<script>
import Echart from "../components/EchartsNormal";
import {barOption2,lineOption,parseDate2} from "../plugins/utils";
import axios from "axios"
import {queryStatisticData,queryVipAccessNum,queryEmpInfo} from "../api/sysinfo"
import Dialog from "./dialog/SysDialog.vue"
import userDialog from "./dialog/SysUserDialog.vue"
export default {
  components:{
    Echart,
    Dialog,
    userDialog
  },
  data(){
    return{
      factorynos:[
        {id:'a1',label:'CD'},
        {id:'a2',label:'SZLH'}
      ],
      punits:[
        {id:'b1',label:'MacI'},
        {id:'b2',label:'MacIII'}
      ],
      factoryno:'',
      punit:'',
      time:'2020年05月19日20点07分52秒',
      sysName:'品質系統',
      topData:[
        {id:'c1',label:'用戶數',number:200,img:require('../assets/images/per-small.png')},
        {id:'c1-2',label:'活躍用戶',number:200,img:require('../assets/images/per-small.png')},
        // {id:'c2',label:'訪問量',number:73005,img:require('../assets/images/visit.png')},
        // {id:'c3',label:'數據量',number:267358,img:require('../assets/images/data.png')},
        // {id:'a4',label:'PC',number:195,img:require('../assets/images/pc.png')},
        // {id:'a5',label:'無線',number:103,img:require('../assets/images/mobile.png')}
      ],
      mobiles:[
        {id:'c1',label:'所有終端',value:'all'},
        {id:'c2',label:'PC',value:'pc'},
        {id:'c3',label:'APP',value:'mobile'}
      ],
      mobile:"all",
      radio: 1,
      getBarOption:{},
      chartDataList:[],
      dateType:"week",
      dateArr:[new Date('2020-06-29'),new Date('2020-07-06')],
      sendItem:{},
      ifDisabled:true,
      getLineOpion:{},
      ManagerId:'',//主管id
      allManager:[],
      dialogVisible:false,
      theDate:'',
      systemId:'',
      username:'',
      ifShow:false,
      empId:'',
      empName:'',
      sex:'',
      postNo:'',
      deptNo:'',
      deptName:'',
      humFactory:'',
      factory:'',
      allUserDialog:false,
      userData:[],
      allVipAccessNum:[],
      dateString:"",
      today:"",
      targetDay:"",
      
    }
  },
  watch:{
    dateType:function(){
      this.today = parseDate2(new Date())
      this.targetDay = ""
      if(this.dateType==="week"||this.dateType==="month"||this.dateType==="year"){
        this.ifDisabled = true;
        this.dateArr = []
        if(this.dateType === "week"){
          this.getWeekInfo()
        }
        if(this.dateType === "month"){
          this.getMonthInfo()
        }
        if(this.dateType === "year"){
          this.getYearInfo()
        }
        this.initEcharts()
      }
      this.dateString = this.dateArr.join()
    },
   dateString:function(){
   },
    empId:function(){
      this.initLineEcharts()
    }
  },
  
  mounted(){
    this.init();
  },
  methods:{
    init(){
      this.username = JSON.parse(localStorage.getItem("user")).empId
      if(this.username&&this.username!=''){
        this.findInfo()
      }
      // this.sendParams = this.$store.state.pathparams || JSON.parse(localStorage.getItem("messageStore"))
      this.sendItem = this.$store.state.pathparams.paramsItem||JSON.parse(localStorage.getItem("messageStore")).paramsItem;
      this.sysName = this.sendItem.name;
      this.$store.commit("setParamsItem",this.$store.state.pathparams.paramsItem||JSON.parse(localStorage.getItem("messageStore")).paramsItem);
      this.systemId = this.$store.state.pathparams.paramsItem.id
     
      this.initEcharts();
      if(this.empId!=""){
        this.initLineEcharts()
      }else{
        // console.log("暫無數據")
      }
      
    },
    getWeekInfo(){
      this.targetDay = parseDate2(new Date() - 1000*3600*24*7)
      this.dateArr.push(new Date(this.targetDay),new Date(this.today))
    },
    getMonthInfo(){
      let d = new Date()
      d.setMonth(new Date().getMonth() -1);
      // console.log("d",d)
      this.targetDay = parseDate2(d)
      this.dateArr.push(new Date(this.targetDay),new Date(this.today))
    },
    getYearInfo(){
      let d = new Date()
      d.setFullYear(d.getFullYear()-1)
      this.targetDay = parseDate2(d)
      this.dateArr.push(new Date(this.targetDay),new Date(this.today))
    },
    entername(event){
      if(event.keyCode === 13){
        this.findInfo()
        event.target.blur()
      }
    },
    findInfo(){
        queryEmpInfo.params.param = this.username
        axios(queryEmpInfo).then(data=>{
          if(data.length===1){
            this.ifShow = true
            this.empId = data[0].empId
            this.empName = data[0].empName
            this.sex = data[0].sex
            this.postNo = data[0].postNo
            this.deptNo = data[0].deptNo
            this.deptName = data[0].deptName
            this.humFactory = data[0].humFactory
            this.factory = data[0].factory
          }
          else if(data.length===0){
            this.openComfirm('对不起,并未找到该用户的相关信息')
          }else{
            //多個數據顯示彈出框讓用戶選擇
            this.allUserDialog = true
            this.userData = data
          }
        }).catch(error=>{
          this.openComfirm(error)
        })
    },
    saveUser(user){
      this.ifShow = true
      this.empId = user.empId
      this.empName = user.empName
      this.sex = user.sex
      this.postNo = user.postNo
      this.deptNo = user.deptNo
      this.deptName = user.deptName
      this.humFactory = user.humFactory
      this.factory = user.factory
    },
    //提示信息窗口
    openComfirm(title){
      this.$alert(title, {
      confirmButtonText: '确定',
      });
    },
    initLineEcharts(){
      let params = {}
      params.systemId = this.$store.state.pathparams.paramsItem.id;
      params.empId = this.empId
      params.deviceType = this.mobile
      queryVipAccessNum.data = params;
      this.allVipAccessNum = []
      axios(queryVipAccessNum).then(data=>{
        this.allVipAccessNum = data
        this.getLineOpion = lineOption(this.allVipAccessNum)
      })

    },
    initEcharts(){
      let params = {};
      params.dateType = this.dateType
      params.deviceType = this.mobile;
      params.systemId = this.$store.state.pathparams.paramsItem.id;
      queryStatisticData.data = params
      axios(queryStatisticData).then(data=>{
        // console.log(123,data)
        this.chartDataList = data.chartDataList;
        this.topData[0].number = data.todayUserSum
        this.topData[1].number = data.todayActiveUserSum
        let xData = [];
        let yData = [];
        let activeUserSums = [];
        let visitSums = [];
        let dataSums = [];
        let userSums = [];
        let accessSums = []
        let deviceSums = []
        let activeDeviceSums = []
        let userSum2 =[]
        this.chartDataList.forEach(item=>{
          xData.push(item.theDate);
          activeUserSums.push(item.activeUserSum);
          userSums.push(item.userSum-item.activeUserSum)
          accessSums.push(item.accessSum)
          deviceSums.push(item.deviceSum-item.activeDeviceSum)
          activeDeviceSums.push(item.activeDeviceSum)
          userSum2.push(item.userSum)
        })
        yData.push({data:userSums})
        yData.push({data:visitSums})
        yData.push({data:dataSums})
        yData.push({data:activeUserSums})
        yData.push({data:deviceSums})
        yData.push({data:activeDeviceSums})
        yData.push({data:userSum2})
        this.getBarOption = barOption2(xData,yData,accessSums)

      }).catch(err=>{
        this.openComfirm(err)
      })
    },
    dateValueChange(){
      this.dateType = this.dateArr.join()
      this.dateString = this.dateArr.join()
      this.ifDisabled = false;
      this.initEcharts()
    },
    changeMobile(){
      this.initEcharts()
    },

    shadowClick(op,xIndex){
      this.theDate = op.xAxis[0].data[xIndex]
      this.dialogVisible = true
    },
    close(){
      this.dialogVisible = false
    },
    closeUserD(){
      this.allUserDialog = false
    }
  }
}
</script>
<style scoped>
  .main{
    height: calc(100vh - 270px);
    /* height: 100%; */
    /* height: 100vh; */
    display: flex;
    width: 62.5vw;
    left: 17.5%;
    position: relative;
    flex-flow: row wrap;
    min-height: 650px

  }
  .main-2{
    position: relative;
    width: 62.5%;
    left: 17.5%;
    height: 300px;
    border: 1px solid #eaeff4;
   
  }
  .main-2-content{
    width: 100%;
    position: relative;    
    background: white;
  }
  .h1{
     height: calc(100% - 30px);
  }
  .h2{
     height: calc(100% - 40px);
  }
  .top{
    height:40px;
    background:rgba(58,126,255,1);
    width: 100%;
    position: relative;
    flex-flow: row wrap;
    
  }
  .top-main{
    display: flex;
    width: 62.5%;
    left: 17.5%;
    position: relative;
    flex-flow: row wrap;
  }
  .top>>>.el-input__inner{
    background-color:#3a7eff;
    border:none;
    width: 100px;
    color: #FFFFFF;
  }
  .left>>>.el-input__inner{
    border: none;
  }
  .left>>>.el-select .el-input .el-select__caret{
    height: 40px;
    line-height: 40px
  }
  .top-info{
    line-height: 40px;
    height: 40px;
    color: #FFFFFF;
    width: calc(100% - 200px);
    justify-content: center;
    display: flex;
    top:120px; 
    position: relative;

  }
  .main1{
    background: #FFFFFF;
    height: 60%;
    width: 100%;
    min-height: 400px
  }
  .mt-1{
    margin-top: 10px
  }
  .mr-1{
    margin-right: 1rem
  }
  .inline{
    display: inline-block;
  }
  .main2{
    background: #FFFFFF;
    height: 36%;
    width: 100%;
    
  }
  .line{
    width:100%;
    height:1px;
    background:rgba(219,223,229,1);
    opacity:0.6;
  }
  .inner{
    width: 100%;
    height: 100%;
  }
  .name{
    font-size:20px;
    font-weight:400;
    color:rgba(56,62,73,1);
    margin: 0.5% 0 0.5% 0;
    text-align: center
  }
  .main3{
    display: flex;
    flex-flow: row wrap;
  }
  .number{
    font-size: 30px
  }
  .block{
    min-width: 70px;
    width: 16.6%;
    text-align: center

  }
  .block:first-child{
    width: 16.6%;
    text-align: left;
  }
  .block:last-child{
    width: 15%;
    /* text-align: right; */
  }
  .img-label{
    display: inline-block;
    margin-left:8px; 
  }
  .mt-4-reduce{
    height: 100%
  }
  .right{
    float:right;
    height: 40px;
    line-height: 40px
  }
  .left{
    float: left;
  }
  .small-block{
    width: 12px;
    height: 12px;
    display: inline-block;
    margin-right: 5px
  }
  .content{
    height: calc(100% - 100px);
    width: 100%;
    top: 40px;
    position: relative;
  }
  .el-radio{
    margin-right:1rem  
  }
  .center{
    text-align: center;
    position: relative;
    top: 40px
  }
  .left>>>.el-date-editor .el-range__icon{
    display: none !important
  }
 el-radio>>>.el-date-editor .el-range-input{
   width: 29% !important
 }
 el-radio>>>.el-date-editor--datetimerange.el-input, .el-date-editor--datetimerange{
   width: 220px
 }
 .zd>>>.el-input__inner{
   width: 110px;
   min-width: 110px;
 }
 .zd2{
   position: relative;
   top:10px;
   left: 40px;
 }
 .zd2>>>.el-input__inner{
   width: 150px;
   min-width: 150px;
   height: 25px

 }
 .el-radio:last-child{
   margin-right: 0px
 }
 .left>>>.el-date-editor .el-range-separator{
   padding: 5px 0;
 }
 .noContent{
   font-size: 20px;
   position: relative;
   left: 45%;
   top: 40%
 }
 .info-right{
   position: relative;
   left: 200px;
   width: calc(100% - 230px);
   color: #606266;
   font-size: 14px;
   top:-19px;
   height: 30px;
   
 }
 .mt1{
   margin-top: -10px
 }
 .mt1-2{
   top: -20px
 }
 .pd1{
   padding: 0px 0px 0px 40px
 }
 .pd2{
   padding: 0px 0px 0px 40px
 }
 .mb5{
   margin-bottom: 5px
 }
</style>