<template>
  <div>
    <el-dialog :title="title" :visible.sync="sysVisible" @close="close" center>
      <el-switch
        v-model="value2"
        :active-text="s1"
        :inactive-text="s2"
        @change="titleChange">
    </el-switch>
    <br/>
      <el-switch
  v-model="value1"
  :active-text="s3"
  :inactive-text="s4"
  @change="dataChange">
</el-switch>
    <el-table ref="table" v-loading="loading" :data="tableData" max-height="300" stripe 
       element-loading-text = "数据正在加载中"
       element-loading-spinner = "el-icon-loading"
       v-if="!value2"
       >
      <el-table-column label="序號" value="No" prop="No" width="50"></el-table-column>
      <el-table-column v-for="title in titles1" :prop="title.key" :label="title.label" :key="title.key" :width="title.width" align="center" show-overflow-tooltip></el-table-column>
    </el-table>

     <el-table ref="table" v-loading="loading" :data="tableData" max-height="300" stripe 
       element-loading-text = "数据正在加载中"
       element-loading-spinner = "el-icon-loading"
       v-if="value2"
       >
      <el-table-column label="序號" value="No" prop="No" width="50"></el-table-column>
      <el-table-column v-for="title in titles2" :prop="title.key" :label="title.label" :key="title.key" :width="title.width" align="center" show-overflow-tooltip></el-table-column>
    </el-table>
  </el-dialog>
  </div>
</template>
<script>
import axios from 'axios'
import {listActiveUser} from '../../api/sysinfo'
export default {
  props:{
    dialogVisible:Boolean,
    theDate:String,
    systemId:String,
    dateType:String,
    mobile:String
  },
  watch:{
    dialogVisible:function(){
      this.sysVisible = this.dialogVisible
      this.sysVisible&&this.init()
    },
   
  },
  data(){
    return{
      title:'',
      s1:'設備詳情',
      s2:'用戶詳情',
      s3:'',
      s4:'',
      loading:true,
      sysVisible:false,
      allData:{},
      tableData:[],
      dom:null,
      inactiveUserList:[],
      activeUserList:[],
      inactiveDeviceList:[],
      activeDeviceList:[],
      titles1:[
        {key:"empId",label:"工號",width:'100'},
        {key:"empName",label:"姓名",width:'80'},
        {key:"sex",label:"性別",width:'50'},
        {key:"postNo",label:"職位",width:'80'},
        {key:"deptNo",label:"部門代碼"},
        {key:"deptName",label:"部門名稱",width:'160'},
        {key:"factory",label:"廠區編號",width:'50'},
        {key:"humFactory",label:"廠區"},
      ],
      titles2:[
        {key:"deviceId",label:"設備id",width:'170'},
        {key:"deviceAppId",label:"設備AppId",width:'170'},
        {key:"win",label:"操作系統"},
        {key:"name",label:"最後登錄用戶",width:'160'},
        {key:"lastLoginSiteName",label:"最後登錄系統",width:"120"},
        // {key:"osType",label:"操作系統類型"},
        // {key:"osVersion",label:"操作系統版本"},
        // {key:"lastLoginUser.empId",label:"工號",width:'100'},
        // {key:"lastLoginUser.empName",label:"姓名",width:'80'},
        // {key:"lastLoginUser.sex",label:"性別",width:'50'},
        // {key:"lastLoginUser.postNo",label:"職位",width:'80'},
        // {key:"lastLoginUser.deptNo",label:"部門代碼"},
        // {key:"lastLoginUser.deptName",label:"部門名稱"},
        // {key:"lastLoginUser.factory",label:"廠區編號",width:'50'},
        // {key:"lastLoginUser.humFactory",label:"廠區"},
        {key:"lastLoginTime",label:"最後登錄的時間點",width:"180"},
        {key:"resolution",label:"設備分辨率",width:"120"},
      ],
      titles:[],
      value1:false,
      value2:false,
      inactiveUserNum:0,
      activeUserNum:0,
      inactiveDeviceNum:0,
      activeDeviceNum:0
    
    }
  },
  methods:{
    init(){
      this.title = "用戶詳情("+this.theDate+")"
      this.loading = true
      let params = {}
      params.theDate = this.theDate;
      params.systemId = this.systemId;
      params.deviceType = this.mobile
      params.dateType = this.dateType==="week"||this.dateType==="month"?"day":"month"
      listActiveUser.data = params
      axios(listActiveUser).then(data=>{
        this.loading = false
        this.allData = data
        this.inactiveUserList = data.inactiveUserList
        this.activeUserList = data.activeUserList
        this.inactiveUserNum = data.inactiveUserNum
        this.activeUserNum = data.activeUserNum
        this.inactiveDeviceNum = data.inactiveDeviceNum
        this.activeDeviceNum = data.activeDeviceNum
        this.inactiveDeviceList = data.inactiveDeviceList
        this.activeDeviceList = data.activeDeviceList
        if(!this.value2){
          this.s3 = '閒置用戶('+this.inactiveUserNum+")"
          this.s4 = '活躍用戶('+this.activeUserNum+")"
          this.titles = this.titles1
        }else{
          this.s3 = '閒置設備('+this.inactiveDeviceNum+")"
          this.s4 = '活躍設備('+this.activeDeviceNum+")"
          this.titles = this.titles2
        }
          this.dataChange()
        
      })
    },
    dataChange(){
      this.loading = true
      this.dom = this.$refs.table.bodyWrapper
      this.dom.scrollTop=0
      this.tableData = []
      if(!this.value2){
        if(this.value1){
        this.loading = false
        this.tableData = this.inactiveUserList.filter((item,index)=>index<50);
        //監聽滾動事件才將所有數據放出來
        this.dom.addEventListener('scroll', ()=>{
          if(!this.value2){
            if(this.value1){
            this.tableData = this.inactiveUserList;
            this.addNo(this.tableData)
          }else{
            this.tableData = this.activeUserList
          }
          this.titles = this.titles1
          }else{
            if(this.value1){
              this.tableData = this.inactiveDeviceList
            }else{
              this.tableData = this.activeDeviceList
            }
            this.tableData.forEach(item=>{
              item.win = item.osType+""+item.osVersion
              item.name  = item.lastLoginUser.empName+"("+item.lastLoginUser.empId+")"
            })
            this.titles = this.titles2
          }
          
        })
      }else{
        this.loading = false
        this.tableData = this.activeUserList
      }
      }else{
        this.loading = false
         if(this.value1){
          this.tableData = this.inactiveDeviceList
        }else{
          this.tableData = this.activeDeviceList
        }
         this.tableData.forEach(item=>{
              item.win = item.osType+""+item.osVersion
              item.name  = item.lastLoginUser.empName+"("+item.lastLoginUser.empId+")"
        })
        this.titles = this.titles2
      }
      this.addNo(this.tableData)
    },
    titleChange(){
      this.title = this.value2?this.s1+"("+this.theDate+")":this.s2+"("+this.theDate+")"
      if(!this.value2){
          this.s3 = '閒置用戶('+this.inactiveUserNum+")"
          this.s4 = '活躍用戶('+this.activeUserNum+")"
        }else{
          this.s3 = '閒置設備('+this.inactiveDeviceNum+")"
          this.s4 = '活躍設備('+this.activeDeviceNum+")"
      }
      this.dataChange()
    },
    addNo(data){
      for(let i=0;i<data.length;i++){
        data[i].No = i+1;
      }
    },
    close(){
      this.$emit("close",close)
    }
  }
}
</script>
<style >
.el-table__body-wrapper::-webkit-scrollbar{
     /*width: 0;宽度为0隐藏*/
    width: 2px;
  }
 .el-table__body-wrapper::-webkit-scrollbar-thumb{
    border-radius: 2px;
    height: 50px;
    background: #3a7eff;
  }
 .el-table__body-wrapper::-webkit-scrollbar-track{
    box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 2px;
    background: rgba(255,255,255,0);
  }
  .el-dialog{
    width: 70% !important
  }
</style>