

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
            <span style="font-size: 1.7vh">项目管理</span>
<!--              </div>-->
          </template>
            <el-menu-item index="1-1" @click="select" style="font-size: 1.5vh">项目分配</el-menu-item>
            <el-menu-item index="1-2" @click="usermanage" style="font-size: 1.5vh">人员管理</el-menu-item>
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
          项目管理
        </el-breadcrumb-item>
        <el-breadcrumb-item v-if="route1" style="font-size: 1.6vh;margin-top: 0.40vh">{{route1}}</el-breadcrumb-item
        >
        <el-breadcrumb-item v-if="route2" style="font-size: 1.5vh;margin-top: 0.55vh">{{route2}}</el-breadcrumb-item>
      </el-breadcrumb>




  </div>
    <div class="person" >
      <img src="../other/1.png" width="100%" height="100%" style="border-radius: 2.5vh" >
    </div>

    <div class="usertype"  @click="tomy">项目经理</div>
  </div>
<div v-if="flag==-1" class="main1">
  <div>
    <img src="../other/main.jpg" style="width:400px;height:400px;margin-left: 5vh;margin-top: 5vh" >
    </div>
  </div>
  <div class="main1" v-if="flag==0">
<el-tabs>

      <div>
        <el-table :data="tableData" stripe height="100%" style="width:151.5vh">
          <el-table-column prop="name" label="名称" width="170vh" />
          <el-table-column prop="email" label="上传的用户" width="170vh" />
          <el-table-column prop="worker" label="标注员" width="170vh" />
          <el-table-column prop="progress" label="进度" width="170vh" />
          <el-table-column prop="done" label="状态" width="170vh" />
          <el-table-column width="140vh" label="删除">
            <template #default="scope">
              <el-button @click="delet(scope.row)">删除</el-button>
            </template>
          </el-table-column>
          <el-table-column width="140vh" label="预览">
            <template #default="scope">
              <el-button @click="lookpdf(scope.row)">预览PDF</el-button>
            </template>
          </el-table-column>
          <el-table-column width="140vh" label="分配标注员">
            <template #default="scope">
              <el-button @click="selectnm(scope.row)" >选择</el-button>
            </template>
          </el-table-column>
          <el-table-column width="140vh" label="查看与管理">
            <template #default="scope">
              <el-button @click="manage(scope.row)"  v-if="scope.row.progress=='已完成'">查看与管理</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[15]"
          :page-size="15"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>

  </el-tabs>
  </div>




<!--人员管理-->
  <div class="main2" v-if="flag==1">

    <el-tabs>

      <div>
        <el-table :data="tableData2" stripe height="100%" style="width:151.5vh">
          <el-table-column prop="id" label="编号" width="300vh" />
          <el-table-column prop="username" label="标注员姓名" width="300vh" />
          <el-table-column prop="email" label="标注员邮箱" width="300vh" />
          <el-table-column width="250vh" label="删除">
            <template #default="scope">
              <el-button @click="delet2(scope.row)">删除</el-button>
            </template>
          </el-table-column>

        </el-table>
        <el-pagination
          @size-change="handleSizeChange2"
          @current-change="handleCurrentChange2"
          :current-page="currentPage2"
          :page-sizes="[12]"
          :page-size="12"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total2">
        </el-pagination>
      </div>

  </el-tabs>



  </div>




<!--查看-->
<div class="look" v-if="flag==2">
<div class="check">
  <div>
<el-tabs  >
    <el-tab-pane :label="'项目'+nowitem+'的标注结果：标注员:'+nowworker">
      <div>
        <el-table :data="tableData1" stripe height="100%" style="width:151.5vh">
          <el-table-column prop="time" label="时间" width="210vh" />
          <el-table-column prop="school" label="学校" width="210vh" />
          <el-table-column prop="profession" label="专业" width="210vh" />
          <el-table-column prop="course" label="课程" width="210vh" />
          <el-table-column prop="semester" label="学期" width="210vh" />
          <el-table-column prop="stuname" label="学生姓名" width="210vh" />
          <el-table-column prop="grade" label="成绩" width="210vh" />
        </el-table>
        <el-pagination
          @size-change="handleSizeChange1"
          @current-change="handleCurrentChange1"
          :current-page="currentPage1"
          :page-sizes="[10]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total1">
        </el-pagination>
      </div>
    </el-tab-pane>

  </el-tabs>
  </div>
  <div >
    <p style="font-size: 3vh;padding-left: 2vh;color:red">{{nowdone}}！</p>
  <el-button v-if="nowdone=='未审核'" type="primary" @click="checkdata" style="margin-left: 2vh;width: 8vh;height: 4vh">审核通过</el-button>
    <el-button v-if="nowdone=='已审核'" type="primary" @click="exports" style="margin-left: 2vh;width: 8vh;height: 4vh">数据导出</el-button>
  </div>
</div>

</div>







   <el-dialog  v-model="dialogVisible" width="30%">
    <iframe :src="nowfile" width="100%" height="500px"></iframe>
  </el-dialog>

  <el-dialog
    v-model="dialog1"
    title="选择标注员"
    width="500"
    align-center
  >
 <select v-model="now">
      <option value="" disabled>请选择标注员</option>
      <option v-for="value in values" :key="value.username" :value="value.username">
        {{ value.username }}
      </option>
    </select>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialog1 = false">取消</el-button>
        <el-button type="primary" @click="reset">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>

  <el-dialog
    v-model="dialog2"
    title="你确定选择该标注员吗？"
    width="500"
    align-center
  >
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialog2 = false">取消</el-button>
        <el-button type="primary" @click="updata1">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
<el-dialog
    v-model="dialog3"
    title="是否确定审核通过？"
    width="500"
    align-center
  >
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialog3 = false">取消</el-button>
        <el-button type="primary" @click="updata2">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>




</div>



</template>

<script lang="ts" setup>
import { ref } from 'vue';
import axios from 'axios';
import { ElMessage, ElMessageBox } from 'element-plus'
import * as XLSX from 'xlsx';
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import jwt from 'jsonwebtoken';
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const now=ref("")
const values=ref([{"name":1},{"name":2}])
const nowfile=ref(null)
const route1=ref("")
const route2=ref("")
const flag=ref(-1)
const nowworker=ref("")
const nowitem=ref("")
const nowdone=ref("")
const dialogVisible = ref(false)
const dialog1=ref(false)
const person=ref([{"name":11,"email":123,}])
const da=ref([])
const da1=ref([])
const dialog2=ref(false)
const dialog3=ref(false)
const dialog4=ref(false)
const workername=ref("")
const workeremail=ref("")
const workerpassword=ref("")
function select(){
  route1.value="项目分配"
  route2.value=""
  flag.value=0
  axios.get("javaapi/manager/getdata", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data["code"])
      console.log(da.value)
    if(response.data["code"]===0){
      da.value=response.data["data"]

      da.value.forEach((item, index) => {
        if(item.done=='0') item.done='未审核'
        else item.done='已审核'
        if(item.progress=='0') item.progress='未完成'
        else item.progress='已完成'
      });
      refresh()

    }else{
      alert("查看失败！")
    }

  })
}
function usermanage(){
  route1.value="人员管理"
  route2.value=""
  flag.value=1
  person.value.forEach((item, index) => {
        item.id=index+1
      })
  refresh()
  axios.get("javaapi/manager/getworker", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data["code"])
    if(response.data["code"]===0){
      person.value=response.data["data"]
      person.value.forEach((item, index) => {
        item.id=index+1
      })
      refresh()
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
      console.log(da.value)
        // const token = localStorage.getItem("Authorization");
        // console.log("Token:", token);
      axios.post("javaapi/manager/delete", {"name":row.name,"email":row.email},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
        console.log(response.data["code"])
        if(response.data["code"]===0){
          alert("删除成功！")
          axios.get("javaapi/manager/getdata", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
           console.log(response.data["code"])
           if(response.data["code"]===0){
             da.value=response.data["data"]
             da.value.forEach((item, index) => {
        if(item.done=='0') item.done='未审核'
        else item.done='已审核'
        if(item.progress=='0') item.progress='未完成'
        else item.progress='已完成'
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

const nowname=ref("")
const nowcustomer=ref("")

function refresh(){
  updateTableData();
  updateTableData1();
  updateTableData2();
  total.value=da.value.length
  total1.value=da1.value.length
  total2.value=person.value.length
}

const currentPage = ref(1);
    const pageSize = ref(15);
    const total = ref(da.value.length);

    const tableData = ref([]);


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
const currentPage1 = ref(1);
    const pageSize1 = ref(10);
    const total1 = ref(da1.value.length);

    const tableData1 = ref([]);


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
const currentPage2 = ref(1);
    const pageSize2 = ref(10);
    const total2 = ref(person.value.length);

    const tableData2 = ref([]);


    const handleSizeChange2 = (newSize) => {
      pageSize2.value = newSize;
      updateTableData2();
    };
    const handleCurrentChange2 = (newPage) => {
      currentPage2.value = newPage;
      updateTableData2();
    };
    const updateTableData2 = () => {
      const start = (currentPage2.value - 1) * pageSize2.value;
      const end = start + pageSize2.value;
      tableData2.value = person.value.slice(start, end);
    };
updateTableData2();




 function lookpdf(row:any) {
  dialogVisible.value= true;
  nowfile.value='data:application/pdf;base64,'+row.file;

  console.log(nowfile.value)
 }
function manage(row:any){
  flag.value=2
  route1.value="项目分配"
  route2.value=row.name
  nowworker.value=row.worker
  nowitem.value=row.name
  nowdone.value=row.done
  nowcustomer.value=row.email
  console.log(nowitem.value)
  console.log(row.email)
axios.post("javaapi/manager/checkresult", {"name":row.name,"email":row.email},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
  console.log(response.data)
  if(response.data["code"]===0){
    da1.value=response.data["data"]
    refresh()
  }else{
    alert("查看失败！")
  }
})

}

function selectnm(row:any){
   dialog1.value=true
    nowname.value=row.name
    nowcustomer.value=row.email
    axios.get("javaapi/manager/getworker", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
      console.log(response.data["code"])
      if(response.data["code"]===0){
        values.value=response.data["data"]
        values.value.forEach((item, index) => {
          item.id=index+1
        })
      }else{
        alert("标注员获取失败！")
      }

    })
}
function reset(){
   dialog2.value=true
  console.log(nowname.value)
  console.log(nowcustomer.value)
  console.log(now.value)

}
function updata1(){
   dialog2.value=false
  dialog1.value=false
  axios.post("javaapi/manager/selectworker", {"name":nowname.value,"email":nowcustomer.value,"worker":now.value},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data["code"])
    if(response.data["code"]===0){
      alert("修改成功！")
      axios.get("javaapi/manager/getdata", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
        console.log(response.data["code"])
      console.log(da.value)
    if(response.data["code"]===0){
      da.value=response.data["data"]
      da.value.forEach((item, index) => {
        if(item.done=='0') item.done='未审核'
        else item.done='已审核'
        if(item.progress=='0') item.progress='未完成'
        else item.progress='已完成'
      });
        refresh()

    }else{
      alert("查看失败！")
    }
      })
    }else{
      alert("修改失败！")
    }
  })
  }
  function checkdata(){
   dialog3.value=true

  }
  function updata2(){
   dialog3.value=false
    console.log(nowitem.value)
    console.log(nowcustomer.value)
    axios.post("javaapi/manager/updatedone", {"name":nowitem.value,"email":nowcustomer.value,"done":1},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
      console.log(response.data)

      if(response.data["code"]===0){
        alert("审核成功！")
          nowdone.value="已审核"
              axios.get("javaapi/manager/getdata", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
          console.log(response.data)
           console.log(da.value)
         if(response.data["code"]===0){
            da.value=response.data["data"]
           da.value.forEach((item, index) => {
        if(item.done=='0') item.done='未审核'
        else item.done='已审核'
        if(item.progress=='0') item.progress='未完成'
        else item.progress='已完成'
      });
              refresh()
          }else{
           alert("查看失败！")
          }
          })
    }else{
      alert("修改失败！")
    }
  })
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


  function delet2(row:any){
   axios.post("javaapi/manager/deleteworker", {"email":row.email},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
     console.log(response.data["code"])
    if(response.data["code"]===0){
      alert("删除成功！")
      axios.get("javaapi/manager/getworker", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
        console.log(response.data["code"])
        if(response.data["code"]===0){
          person.value=response.data["data"]
          person.value.forEach((item, index) => {
            item.id=index+1
          })
          refresh()
        }else{
          alert("标注员获取失败！")
        }

      })
    }else{
      alert("删除失败")
   }})
  }
function tomy(){
   const url = new URL(window.location.origin + '/my');
      url.searchParams.append('op', "项目经理");
      window.location.href = url.toString();
}


</script>










<style>

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
  width: 10vh;
  height: 3vh;
  margin-top: -5.6vh;
  margin-left: 132vh;
  cursor: pointer;
  //border: 2px solid blue;
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
.check{
  width: 149vh;
  height: 85vh;
  //border: 2px solid blue;
  margin-left: 26vh;
  margin-top: 1vh;
  background-color: white;
}
.sub{
  width: 149vh;
  height: 30vh;
  //border: 2px solid blue;
  margin-left: 26vh;
  margin-top: 1vh;
  background-color: white;
}
</style>