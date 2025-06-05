

<template class="all">

<div class="all">



  <div class="menu">
    <div class="logo">
    <img src="../other/1.png" width="100%" height="100%">
    </div>

    <div  class="title">民权标注平台</div>
    <div class="menu1">

  <el-row class="tac">
    <el-col>
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

    <div class="usertype" @click="tomy">用户</div>
  </div>
<div v-if="flag==-1" class="main1">
  <div>
    <img src="../other/main.jpg" style="width:400px;height:400px;margin-left: 5vh;margin-top: 5vh" >
    </div>
  </div>
  <div class="main1" v-if="flag==0">
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
<!--  <iframe :src="imgfile" width="100%" height="500px"></iframe>-->

</div>


  </div>
  <div class="main2" v-if="flag==1">
<!--    {{da.id}}-->
<el-tabs>
    <el-tab-pane label="已上传的数据">
      <div>
        <el-table :data="tableData" stripe height="100%" style="width:151.5vh">
          <el-table-column prop="id" label="项目编号" width="250vh" />
          <el-table-column prop="name" label="名称" width="250vh" />
          <el-table-column prop="done" label="状态" width="250vh" />
          <el-table-column width="250vh" label="删除">
            <template #default="scope">
              <el-button @click="delet(scope.row)">删除</el-button>
            </template>
          </el-table-column>
          <el-table-column width="250vh" label="预览">
            <template #default="scope">
              <el-button @click="lookpdf(scope.row)">预览PDF</el-button>
            </template>
          </el-table-column>
          <el-table-column width="230vh" label="查看">
            <template #default="scope">
              <el-button @click="updata(scope.row)" v-if="scope.row.done=='已审核' ">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[13]"
          :page-size="13"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </el-tab-pane>
  </el-tabs>

  </div>
<div class="look" v-if="flag==2">
  <el-tabs  >
    <el-tab-pane :label="tit" >
      <div>
        <el-table :data="tableData1" stripe height="100%" style="width:151.5vh">
          <el-table-column prop="time" label="时间" width="210vh" />
          <el-table-column prop="school" label="学校" width="210vh" />
          <el-table-column prop="profession" label="专业" width="210vh" />
          <el-table-column prop="course" label="课程" width="200vh" />
          <el-table-column prop="semester" label="学期" width="200vh" />
          <el-table-column prop="stuname" label="学生姓名" width="200vh" />
          <el-table-column prop="grade" label="成绩" width="200vh" />
        </el-table>
        <el-pagination
          @size-change="handleSizeChange1"
          @current-change="handleCurrentChange1"
          :current-page="currentPage1"
          :page-sizes="[13]"
          :page-size="13"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total1">
        </el-pagination>
      </div>
    </el-tab-pane>

  </el-tabs>
  <div>
    <el-button  type="primary" @click="exports" style="margin-left: 2vh;width: 8vh;height: 4vh;margin-top: 5vh">数据导出</el-button>
  </div>
</div>

  <el-dialog  v-model="dialogVisible" width="60%">
    <iframe :src="nowfile" width="100%" height="500px"></iframe>
  </el-dialog>



</div>



</template>

<script lang="ts" setup>



import { ref } from 'vue';
import axios from 'axios';
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import jwt from 'jsonwebtoken';
import * as XLSX from "xlsx";
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const nowfile=ref(null)
const route1=ref("")
const route2=ref("")
const flag=ref(-1)
const pdffile=ref(null)
const imgfile=ref(null)
const dataname=ref("")
const dialogVisible = ref(false)
const tit=ref("")
const da=ref([])
const da1=ref([])

function pushdata(){
  // console.log(pdffile.value)
  // console.log(imgfile.value)

  let formData = new FormData();
      formData.append("name", dataname.value);
      formData.append("file", pdffile.value);
      // console.log(formData)

  axios.post("javaapi/customer/upload", formData,{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
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
  // imgfile.value=pdffile.value
  const reader=new FileReader();
  reader.onload=(event)=>{
    imgfile.value=event.target.result;
    console.log(imgfile.value)
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

  axios.get("javaapi/customer/getdata", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data["code"])




      console.log(da.value)
    if(response.data["code"]===0){
      da.value=response.data["data"]
      da.value.forEach((item, index) => {
        item.id = index + 1;
        if(item.done=='0') item.done='未审核'
        else item.done='已审核'
      });
          refresh()
    }else{
      alert("查看失败！")
    }

  })

}

function updata(row:any){
  route1.value="结果查看"
  route2.value=row.name
  flag.value=2
  tit.value=row.name

  axios.post("javaapi/customer/checkresult", {"name":row.name},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data["code"])
    if(response.data["code"]===0){
      da1.value=response.data["data"]
      refresh()
    }else{
      alert("查看失败！")
    }

  })
}
function delet(row:any){



  ElMessageBox.confirm(
    '是否确定删除？',
    'Warning',
    {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    }
  )
    .then(() => {
      ElMessage({
        type: 'success',
        message: 'Delete completed',
      })

        // const token = localStorage.getItem("Authorization");
        // console.log("Token:", token);
      axios.post("javaapi/customer/delete", {"name":row.name},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
        console.log(response.data["code"])
        if(response.data["code"]===0){
          alert("删除成功！")
          axios.get("javaapi/customer/getdata", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
           console.log(response.data["code"])
           if(response.data["code"]===0){
             da.value=response.data["data"]
             // da.value=response.data["data"]
              da.value.forEach((item, index) => {
               item.id = index + 1;
               if(item.done=='0') item.done='未审核'
                else item.done='已审核'
              });
             refresh()
            }else{
               alert("查看失败！")
           }
           })
        }else{
          alert("删除失败！")
        }


      })

    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: 'Delete canceled',
      })
    })

}

const currentPage = ref(1);
    const pageSize = ref(13);
    const total = ref(da.value.length);
const  pageSize1 = ref(13);
    const tableData = ref([]);
const tableData1 = ref([]);
const currentPage1 = ref(1);
const total1 = ref(da1.value.length);


    const handleSizeChange = (newSize) => {
      pageSize.value = newSize;
      updateTableData();
    };

    const handleCurrentChange = (newPage) => {
      currentPage.value = newPage;
      updateTableData();
    };

    const updateTableData = () => {
      const start = (currentPage.value - 1) * pageSize.value;
      const end = start + pageSize.value;
      tableData.value = da.value.slice(start, end);
    };
    updateTableData();

    const handleSizeChange1 = (newSize) => {
      pageSize1.value = newSize;
      updateTableData1();
    };

    const handleCurrentChange1 = (newPage) => {
      currentPage1.value = newPage;
      updateTableData1();
    };
    const updateTableData1 = () => {
      const start = (currentPage1.value - 1) * pageSize1.value;
      const end = start + pageSize1.value;
      tableData1.value = da1.value.slice(start, end);
    };


 updateTableData1();

function refresh(){
  total1.value = da1.value.length
  total.value = da.value.length

  updateTableData();
  updateTableData1();
}


 function lookpdf(row:any) {
  dialogVisible.value= true;
  console.log(11111)
  // console.log(row.file)
  nowfile.value='data:application/pdf;base64,'+row.file;
  console.log(nowfile.value)

 }
 function exports(){
const data = da1.value.map(item => ({
        时间: item.time,
        学校: item.school,
        专业: item.profession,
        课程: item.course,
        学期: item.semester,
        学生姓名: item.stuname,
        成绩: item.grade
      }));

      const worksheet = XLSX.utils.json_to_sheet(data);
      const workbook = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');

      XLSX.writeFile(workbook, 'data.xlsx');
      alert("导出成功！")

  }
  function tomy(){
    const url = new URL(window.location.origin + '/my');
      url.searchParams.append('op', "用户");
      window.location.href = url.toString();
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
  cursor: pointer;
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