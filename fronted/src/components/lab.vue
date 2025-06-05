

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
        <el-sub-menu index="1" style="background-color: #dae8f8;width: 20vh" >
          <template #title >
<!--            <div style="background-color: #adeaff">-->
            <el-icon><icon-menu /></el-icon>
            <span style="font-size: 1.7vh">待处理文件</span>
<!--              </div>-->
          </template>
            <el-menu-item index="1-1" @click="dataclick" style="font-size: 1.5vh">文件1</el-menu-item>
            <el-menu-item index="1-2" @click="datalook" style="font-size: 1.5vh">文件2</el-menu-item>
            <el-menu-item index="1-2" @click="datalook" style="font-size: 1.5vh">文件3</el-menu-item>
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
          数据标注
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

    <div class="file">


    </div>
    <div class="excel">
      <div class="editor">
        <div class="tool">
          <el-button @click="addRow">添加行</el-button>
          <el-button @click="deleteSelectedRows" :disabled="!selectedRows.length">删除选中行</el-button>
          <el-button @click="addColumn">添加列</el-button>
        </div>
         <el-table
            ref="tableRef"
            :data="tableData"
            stripe
            border
            @selection-change="handleSelectionChange"
            style="width: 100%">

          <!-- 选择框列 -->
          <el-table-column type="selection" width="55"></el-table-column>

          <!-- 动态生成列 -->
          <el-table-column
            v-for="(column, index) in tableColumns"
            :key="index"
            :prop="column.field"
            :label="column.label"
            min-width="100">
            <template #default="scope">
              <el-input
                v-model="scope.row[column.field]"
                size="small"
                @keyup.enter.native="saveCell(scope.$index, column.field)"></el-input>
            </template>
          </el-table-column>
    </el-table>

      </div>


    </div>



  </div>
</div>
</template>

<script lang="ts" setup>

import axios from 'axios';


import {
  Menu as IconMenu,
} from '@element-plus/icons-vue'
import { ref,reactive} from 'vue';



const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}



// 表格列配置
const tableColumns = ref([
  {field:'id',label:'ID',type:'number'},
  {field:'time',label:'时间',type:'string'},
  {field:'school',label:'学校',type:'string'},
  {field:'profession',label:'专业',type:'string'},
  {field:'course',label:'科目',type:'string'},
  {field:'semester',label:'学期',type:'string'},
  {field:'stuname',label:' 学生名',type:'string'},
  {field:'grade',label:'成绩',type:'string'},
  {field: 'name', label: '项目名', type: 'string'},
  {field: 'email', label: '邮箱', type: 'string'}
])

// 表格数据
const tableData = ref([
  {id:1,time:'2024.5.1',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'zmpyy',grade:'66',name:'标注平台',email:'12345678@qq.com'
  },
  {id:2,time:'2024.6.1',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'lzx',grade:'76',name:'标注平台',email:'123456700@qq.com'
  },
  {id:3,time:'2024.5.2',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'云起时',grade:'86',name:'标注平台',email:'12345628@qq.com'
  },
  {id:4,time:'2024.5.2',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'Tao',grade:'76',name:'标注平台',email:'1234562890@qq.com'
  }
])
// 选中的行
const selectedRows = ref([]);

// 添加行方法
const addRow = () => {
  const newRow = {
    id: tableData.value.length + 1,
    time: '',
    school: '',
    profession: '',
    course: '',
    semester: '',
    stuname: '',
    grade: '',
    name: '',
    email: ''
  };
  tableData.value.push(newRow);
};

// 删除选中的行
const deleteSelectedRows = () => {
  if (!selectedRows.value.length) return;

  // 从后往前删除，避免索引问题
  selectedRows.value.sort((a, b) => b.id - a.id).forEach(row => {
    const index = tableData.value.findIndex(item => item.id === row.id);
    if (index !== -1) {
      tableData.value.splice(index, 1);
    }
  });
   // 清空选中状态
  selectedRows.value = [];
};
// 添加列
const addColumn = () => {
  const newField = `column${tableColumns.value.length + 1}`;
  tableColumns.value.push({
    field: newField,
    label: `列${tableColumns.value.length + 1}`,
    type: `${typeof tableColumns.value}`
  });

  // 为所有行添加新列
  tableData.value.forEach(row => {
    row[newField] = '';
  });
};

// 处理选择变化
const handleSelectionChange = (val) => {
  selectedRows.value = val;
};

// 保存单元格修改（可选）
const saveCell = (rowIndex, field) => {
  // 这里可以添加保存逻辑，如发送到服务器
  console.log(`保存: 行=${rowIndex}, 字段=${field}, 值=${tableData.value[rowIndex][field]}`);
};



const route1=ref("")
const route2=ref("")
const flag=ref(0)
const pdffile=ref(null)
const imgfile=ref(null)
const dataname=ref("")


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
  route1.value="文件1"
  route2.value=""
  flag.value=0

}
function datalook(){
  route1.value="文件1"
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
  /* //background-color: #8f3d3d; */

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
  border: 0.1px solid black;
  margin-top: 0.8vh;
  margin-left: 0.8vh;
}
.title{
  font-size: 2.3vh;
  float: left;
  width:15vh;
  height: 4vh;
  border: 0.1px solid black;
  margin-top: 3vh;
  margin-left: 0.6vh;
}
.menu1{
  width: 20vh;
  height: 80vh;
  border: 0.1px solid black;
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
  border: 1px solid #000309;
 font-size: 30px;
  margin-top: 3vh;
  margin-left: 2vh;
  width: 50vh;
  height: 6vh;
}
.person{
  /* float: left; */
  border: 1px solid #000309;
  background-image: url("../other/1.png");
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
  display: flex;
  gap: 5vh;
  width: 152vh;
  height: 89.99vh;
  margin-left: 25vh;
  margin-top: 0vh;
  background-color: rgba(0,0,0,.01);
  border: 0.1px solid #ffffff;
}
.excel{
   margin-top: 4vh;
    height: 84vh;
    width: 70vh;
    background-color: #ffffff;

}
.editor{
  margin-top: 5vh;
}
.file{
   margin-top: 4vh;
    height: 84vh;
    width: 70vh;
    background-color:#ffffff;


}
.main2{
  color: #000309;
  width: 152vh;
  height: 89.99vh;
  margin-left: 25vh;
  background-color: #ffffff;
  border: 0.1px solid #ffffff;
}
.push{
  width:48vh;
  height: 50vh;
  border: 1px solid #000309;
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