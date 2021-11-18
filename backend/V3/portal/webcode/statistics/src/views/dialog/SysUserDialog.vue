<template>
  <div>
    <el-dialog title="用戶詳情" :visible.sync="sysVisible" @close="close" center @opened="opened">
      <span v-if="currentRow">
         序號：{{currentRow.No||'未選擇'}}
        <!-- <div v-for="(title) in titles" :key="'name'+title.key" class="mr-1 inline">
          <span>{{title.label}}:{{currentRow[title.key]||'null'}}</span>
        </div> -->
        <span class="mr-1">人員工號：{{currentRow.empId||'null'}}</span>
        <span class="mr-1">人員姓名：{{currentRow.empName||'null'}}</span>
        <span class="mr-1">性别：{{currentRow.sex||'null'}}</span>
        <span class="mr-1">职位：{{currentRow.postNo||'null'}}</span>
        <div style="margin-top:10px"></div>
        <span class="mr-1" >部门代码：{{currentRow.deptNo||'null'}}</span>
        <span class="mr-1">部门名称：{{currentRow.deptName||'null'}}</span>
        <span class="mr-1">厂区：{{currentRow.humFactory||'null'}}</span>
        <span class="mr-1">厂区编号：{{currentRow.factory||'null'}}</span>
      </span>
     
    <el-table :data="tableData" max-height="300" stripe  highlight-current-row
    @current-change="handleCurrentChange">
       <el-table-column label="" width="70">
          <template scope="scope">
            <el-radio :label="scope.$index+1" v-model="radio"></el-radio>
          </template>
        </el-table-column>
      <el-table-column v-for="title in titles" :prop="title.key" :label="title.label" :key="title.key" :width="title.width" align="center" show-overflow-tooltip></el-table-column>
    </el-table>
   <span slot="footer" class="dialog-footer">
    <el-button @click="sysVisible = false">取 消</el-button>
    <el-button type="primary" @click="save()">确 定</el-button>
  </span>
  </el-dialog>
  </div>
</template>
<script>
export default {
  props:{
    dialogVisible:Boolean,
    tableData:Array,
    sendempId:String,
    mobile:String
  },
  watch:{
    dialogVisible:function(){
      this.sysVisible = this.dialogVisible
      this.sysVisible&&this.init()
    },
    radio:function(){
      // console.log("radio",this.radio)
    },
    sendempId:function(){
   
    },
    tableData:function(){
      this.loading = true
    }
  },
  data(){
    return{
      sysVisible:false,
      allData:{},
      loading:false,
      titles:[
        // {key:"No",label:"序號",},
        {key:"empId",label:"人員工號",width:'100'},
        {key:"empName",label:"人員姓名",width:'80'},
        {key:"sex",label:"性別",width:'50'},
        {key:"postNo",label:"職位",width:'80'},
        {key:"deptNo",label:"部門代碼"},
        {key:"deptName",label:"部門名稱",width:'160'},
        {key:"factory",label:"廠區編號",width:'50'},
        {key:"humFactory",label:"廠區"},
      ],
      value1:false,
      currentRow:{},
      radio:'',
    }
  },
  methods:{
    opened(){
      let obj = this.tableData.find(item=>{
        return item.empId == this.sendempId
      })
      if(obj){
        this.radio = obj.No;
        this.currentRow = obj
      }
    },
    init(){
      this.addNo(this.tableData)
    },
    handleCurrentChange(val){
      this.currentRow = val;
      if(val){
        this.radio = val.No
      }
    },
    addNo(data){
      for(let i=0;i<data.length;i++){
        data[i].No = i+1;
      }
    },
    close(){
      this.$emit("close",close)
    },
    save(){
      this.$emit("save",this.currentRow)
      this.close()
    },
    getCurrentRow(index){
      console.log("index",index)
    }
  }
}
</script>
<style >
.el-table__body-wrapper::-webkit-scrollbar{
     /*width: 0;宽度为0隐藏*/
    width: 2px;
    height: 10px;
  }
 .el-table__body-wrapper::-webkit-scrollbar-thumb{
    border-radius: 2px;
    height: 20px;
    background: #3a7eff;
  }
 .el-table__body-wrapper::-webkit-scrollbar-track{
    box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 2px;
    background: rgba(255,255,255,0);
  }
  .mr-1{
    margin-right:10px; 
  }
  .el-table--scrollable-y .el-table__body-wrapper{
    /* overflow-x:hidden  */
   
  }
  .inline{display: inline-block}
</style>