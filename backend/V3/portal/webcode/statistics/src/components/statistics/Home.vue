<template>
  <div>
    
    <div class="main">
      <div class="mt-2" style="width:100%;height:40px">
        <el-input v-model="inputVal" placeholder="請輸入系統名稱"></el-input>
        <el-button type="primary">搜索</el-button>
      </div>
      <div class="content mt-2">
        <div class="block mt-3" v-for="item in contents" :key="item.id" :style="{background:item.background}" @click="clickSys(item)" >
          <div class="img-info" >
            <img class="img-center" :src="item.picSrc">
            <div class="img-label" style="margin-bottom:30px">{{item.name}}</div> 
            <div>
              <!-- <img class="small" src="../../assets/images/per.png"> -->
              <!-- <span style="font-size:15px;margin-right:4px">{{item.number}}</span> -->
            </div>
              
          </div>
        </div>
        <div class="block mt-3 hidden" v-for="i in 5" :key="i"></div>
      </div>

    </div>
  </div>
</template>

<script>
import {listSystem} from "../../api/sysinfo";
import axios from "axios"
export default {
  data(){
    return{
      contents:[
        // {id:"1a",picSrc:require("../../assets/images/jl.png"),name:'金流系統',number:159,background:'#D9B344'},
        // {id:"2a",picSrc:require("../../assets/images/pz.png"),name:'品質系統',number:500,background:'#0FD83A'},
        // {id:"3a",picSrc:require("../../assets/images/mj.png"),name:'模具冶檢具訂單信息服務',number:90,background:'#0093FF'},
        // {id:"4a",picSrc:require("../../assets/images/gj.png"),name:'智能工具管理',number:168,background:'linear-gradient(0deg,rgba(246,184,16,1),rgba(52,181,221,1))'},
        // {id:"5a",picSrc:require("../../assets/images/per.png"),name:'人事系統',number:942,background:'#761BFF'},
        // {id:"6a",picSrc:require("../../assets/images/dm.png"),name:'點名系統',number:159,background:'#08DB94'},
        // {id:"7a",picSrc:require("../../assets/images/gg.png"),name:'公管系統',number:159,background:'linear-gradient(180deg,rgba(22,188,209,1),rgba(205,227,118,1))'},
        // // {id:"8a",picSrc:require("../../assets/images/jl.png"),name:'金流系統',number:159,background:'#D9B344'},
        // {id:"9a",picSrc:require("../../assets/images/pz.png"),name:'品質系統',number:159,background:'#0FD83A'},
        // {id:"10a",picSrc:require("../../assets/images/mj.png"),name:'模具冶檢具訂單信息服務',number:159,background:'#0093FF'},
      ],
      inputVal:"",
      contents2:[],
      show:false,
      colors:['#D9B344','#0FD83A','#0093FF','linear-gradient(0deg,rgba(246,184,16,1),rgba(52,181,221,1))','#761BFF','#08DB94','linear-gradient(180deg,rgba(22,188,209,1),rgba(205,227,118,1))','#D9B344','#0FD83A','#0093FF'],
      images:[
            require("../../assets/images/pz.png"),
            require("../../assets/images/jl.png"),
            require("../../assets/images/gg.png"),
            require("../../assets/images/mj.png"),
            require("../../assets/images/gj.png"),
            require("../../assets/images/per.png"),
            require("../../assets/images/dm.png")
      ]
    }
  },
  mounted(){
    this.init()
  },
  methods:{
    init(){
      this.getSysList()
    },
    getSysList(){
      axios(listSystem).then(data=>{
        console.log("listSystem",data)
        // this.contents = data
        data.forEach((item,index)=>{
          //validFlag爲1有效  
          if(item.validFlag){
            this.contents.push(item)
            if(this.colors.length<=1){
              item.background = this.colors[0]
            }
            //超出數據按照colors循環
            else if(data.length<=this.colors.length){
              item.background = this.colors[index]
            }else{
              let itemIndex = (index)%(this.colors.length);
              item.background = this.colors[itemIndex]
            }

            //假數據的圖片，後面數據有圖片了可注釋
          if(this.images.length<=1){
            item.picSrc = this.colors[0]
          }
          else if(data.length<=this.images.length){
            item.picSrc = this.images[index]
          }else{
            let itemIndex = (index)%(this.images.length);
            item.picSrc = this.images[itemIndex]
          }
          }
        })
      })
    },
    clickSys(item){
      // console.log("即將跳轉：",item,"頁面")
      this.$store.commit("setParamsItem", item);
      // this.$store.commit("setListSystem",this.listSystem);
      // this.$router.push('/sysinfo')
      // this.$router.push({
      //     name: 'Sysinfo',
      //     params: {
      //       id: id
      //     }
      //   })
      // this.$router.push(`/sysinfo/${id}`)
      this.$router.push({path: '/sysinfo'})},
      mouseContent(item){
        console.log("mouseItem",item)
        this.show = true
      },
  }
}
</script>
<style  scoped>
  .main{
    display: flex;
    /* border: 1px solid red; */
    width: 62.5%;
    left: 17.5%;
    position: relative;
    flex-flow: row wrap;
    
  }
  .mt-2{
    margin-top: 20px;
  }
  .main>>>.el-input__inner{
    border-radius:20px !important;
  }
  .top{
    display: flex;
    flex-flow: row nowrap;
  }
  .el-button{
    z-index:1;
    height: 40px !important;
    border-radius:0px 20px 20px 0px;
    float: right;
    bottom: 2.5rem;
    position: relative;
  }
  .block{
    height: 21rem;
    width: 14rem;
    border-radius: 2px;
    cursor: pointer;
  }
  .content{
    display: flex;
    flex-flow: row wrap;
    margin-right:auto;
    justify-content: space-between;
   
  }
  .mt-3{
    margin-top: 30px;
  }
  .hidden{
    visibility: hidden;
    height: 0px;
  }
  .img-center{
    width: 72px;
    height: 72px;
    margin: 50px 0px 36px 0px
   
  }
  .img-label{
    font-size: 18px;
    font-weight: 400;
   
  }
  .img-info{
    text-align: center;
     color: #FFFFFF
  }
  .small{
    height: 1rem;
    width: 1rem;
  }
</style>
