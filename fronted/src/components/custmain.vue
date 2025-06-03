

<template class="all">

<div class="all">


  <div class="menu">
    <div class="logo">
    <img src="../other/1.png" width="100%" height="100%">
    </div>

    <div  class="title">民权标注平台</div>
    <div class="menu1">

  <el-row class="tac">
    <el-col >

      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-sub-menu index="1" style="background-color: #dae8f8;width: 22vh" >
          <template #title >
<!--            <div style="background-color: #adeaff">-->
            <el-icon><icon-menu /></el-icon>
            <span style="font-size: 1.7vh">数据管理</span>
<!--              </div>-->
          </template>
            <el-menu-item index="1-1" @click="dataclick" style="font-size: 1.5vh">数据上传</el-menu-item>
            <el-menu-item index="1-2" @click="datalook" style="font-size: 1.5vh">结果查看</el-menu-item>
        </el-sub-menu>


      </el-menu>
    </el-col>

  </el-row>






    </div>

  </div>


  <div class="lab">
    <div class="page">


      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: './page-header.html' }" style="font-size: 2vh">
          数据管理
        </el-breadcrumb-item>
        <el-breadcrumb-item v-if="route1" style="font-size: 1.6vh;margin-top: 0.40vh">{{route1}}</el-breadcrumb-item
        >
        <el-breadcrumb-item v-if="route2" style="font-size: 1.5vh;margin-top: 0.55vh">{{route2}}</el-breadcrumb-item>
      </el-breadcrumb>




  </div>
    <div class="person" >
      <img src="../other/1.png" width="100%" height="100%" style="border-radius: 2.5vh" >
    </div>

    <div class="usertype">用户</div>
  </div>

  <div class="main1" v-if="!flag">
<div class="push">
  <div class="text">
    <p>数据上传</p>
  </div>
  <div>
    <p style="padding-left: 6vh;font-size: 2vh;padding-top: 2vh;display: inline-block;" >数据名称:</p>
    <input type="text" name="username" placeholder="数据名称" style="width: 26vh;height: 3.5vh;margin-left: 1vh;margin-top: 1vh;border-radius: 0.5vh;border:1px solid #3a70ed;outline: none"  v-model="dataname">
    <p style="padding-left: 6vh;font-size: 2vh;padding-top: 2vh;display: inline-block;" >选择数据:</p>
    <input type="file" name="username" style="width: 15vh;height: 2.5vh;margin-left: 1vh;margin-top: 1vh" @change="getfile" >
    <br><button class="pushup" @click="pushdata">上传</button>
  </div>

</div>


  </div>
  <div class="main2" v-if="flag">
<!--    {{da.id}}-->
<el-tabs  >
    <el-tab-pane label="已上传的数据" >
      <div>
        <el-table :data="da" stripe height="100%" style="width:151.5vh">
          <el-table-column prop="id" label="项目编号" width="300vh" />
          <el-table-column prop="name" label="名称" width="300vh" />
          <el-table-column prop="done" label="状态" width="320vh" />
          <el-table-column width="300vh" label="删除">
            <template #default="scope">
              <el-button @click="delet(scope.row)">删除</el-button>
            </template>
          </el-table-column>
          <el-table-column width="320vh" label="查看">
            <template #default="scope">
              <el-button @click="updata(scope.row)">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-tab-pane>
  </el-tabs>

  </div>



  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import axios from 'axios';

import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}


const route1=ref("")
const route2=ref("")
const flag=ref(0)
const pdffile=ref(null)
const imgfile=ref(null)
const dataname=ref("")
const da=ref([
  { id: 1, lon: 1.1, lat: 1.1, name: "站点1", line: 1, done: "待审核" },
      { id: 2, lon: 1.1, lat: 1.1, name: "站点2", line: 1, done: "待审核" },
      { id: 3, lon: 1.1, lat: 1.1, name: "站点3", line: 1, done: "待审核" },
      { id: 4, lon: 1.1, lat: 1.1, name: "站点4", line: 1, done: "待审核" },
      { id: 5, lon: 1.1, lat: 1.1, name: "站点5", line: 1, done: "待审核" },
]

)

function pushdata(){
  console.log(pdffile.value)
  console.log(imgfile.value)
  axios.post("javaapi/customer/upload", {"name":dataname.value,"file":imgfile.value}).then((response) => {
console.log(response.data["code"])
    if(response.data["code"]===0){
      alert("上传成功！")
    }else{
      alert("上传失败！")
    }
  })
}
function getfile(event:any){
  pdffile.value=event.target.files[0]
  console.log(pdffile.value)
  const reader=new FileReader();
  reader.onload=(event)=>{
    imgfile.value=event.target.result;
  };
  reader.readAsDataURL(pdffile.value);
}


function dataclick(){
  route1.value="数据上传"
  route2.value=""
  flag.value=0

}
function datalook(){
  route1.value="结果查看"
  route2.value=""
  flag.value=1
}

function updata(row:any){

}
function delet(row:any){

}
</script>










<style>
.mainline{
  width: 1500px;
  height: 800px;
  margin-top: 20px;
  margin-left: 20px;
  //background-color: #8f3d3d;

}
.menu{
  float: left;
  width: 25vh;
  height: 100vh;
  border: 0.1px solid #d8deec;
  background-color: #ffffff;

}
.logo{
  float: left;
  width: 7vh;
  height: 7vh;
   //border: 0.1px solid black;
  margin-top: 0.8vh;
  margin-left: 0.8vh;
}
.title{
  font-size: 2.3vh;
  float: left;
  width:15vh;
  height: 4vh;
    //border: 0.1px solid black;
  margin-top: 3vh;
  margin-left: 0.6vh;
}
.menu1{
  width: 20vh;
  height: 80vh;
    //border: 0.1px solid black;
  margin-top: 16vh;
  margin-left: 2vh;
}
.lab{
  background-color: #ffffff;
  width: 152vh;
height: 10vh;
    border: 0.1px solid #d8deec;
  margin-left: 25vh;
}
.all{
  width: 177.7777vh;
  height: 100vh;
background-color: #f9f5f5;
}
.page{
  float: left;
  //border: 1px solid #000309;
 //font-size: 30px;
  margin-top: 3vh;
  margin-left: 2vh;
  width: 50vh;
  height: 6vh;
}
.person{
  //float: left;
  //border: 1px solid #000309;
  //background-image: url("../other/1.png");
  width: 5vh;
  height: 5vh;
  margin-top: 2vh;
  margin-left: 125vh;
  border-radius: 2.5vh;
}
.usertype{
  font-size: 2vh;
  float: left;
  width: 5vh;
  height: 5vh;
  margin-top: -5.6vh;
  margin-left: 132vh;
}
.main1{
  width: 152vh;
  height: 89.99vh;
  margin-left: 25vh;
  //margin-top: 0vh;
  //background-color: #ffffff;
  border: 0.1px solid #ffffff;
}
.main2{
  //color: #000309;
  width: 152vh;
  height: 89.99vh;
  margin-left: 25vh;
  //background-color: #ffffff;
  border: 0.1px solid #ffffff;
}
.push{
  width:48vh;
  height: 50vh;
  //border: 1px solid #000309;
  border-radius: 3vh;
  margin-top: 15vh;
  margin-left: 48vh;
  background-color: #ffffff;
}
.text{

  font-size: 3.2vh;
  text-align: center;
  padding-top: 2.5vh;
}
.pushup{
  font-size: 2vh;
  color:white;
  margin-left: 20vh;width: 12vh;height: 5vh;margin-top: 4vh;border-radius: 2vh;
  background-color: #00eaff;
  border: 0px;
  cursor: pointer;
  transition: 0.5s;
}
.pushup:hover{
  font-size: 2.2vh;
  background-color: #05c2ff;
}
</style>