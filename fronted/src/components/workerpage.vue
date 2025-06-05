

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
            <span style="font-size: 1.7vh">数据标注</span>
<!--              </div>-->
          </template>
            <el-menu-item index="1-1" @click="dataclick" style="font-size: 1.5vh">数据标注</el-menu-item>
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
    <div class="usertype" @click="tomy">标注员</div>
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

          <el-table-column width="140vh" label="预览PDF">
            <template #default="scope">
              <el-button @click="lookpdf(scope.row)">预览PDF</el-button>
            </template>
          </el-table-column>
          <el-table-column width="140vh" label="标注">
            <template #default="scope">
              <el-button @click="markdata(scope.row)" v-if="scope.row.progress=='未提交'" >标注</el-button>
            </template>
          </el-table-column>
          <el-table-column width="140vh" label="提交">
            <template #default="scope">
              <el-button @click="sub(scope.row)" v-if="scope.row.progress=='未提交'">提交</el-button>
            </template>
          </el-table-column>

          <el-table-column width="140vh" label="查看已标注的结果">
            <template #default="scope">
              <el-button @click="lookdata(scope.row)" >查看结果</el-button>
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

<!--  标注-->
  <div class="main2" v-if="flag==1">
    <div class="file">
<iframe :src="nowfile" width="100%" height="800px"></iframe>

    </div>
    <div class="excel">
      <p style="margin-left: 1vh">项目名为：{{nowitem}}</p>
      <div class="editor">
        <div class="tool">
          <el-button @click="addRow">添加行</el-button>
          <el-button @click="deleteSelectedRows" :disabled="!selectedRows.length">删除选中行</el-button>
<!--          <el-button @click="addColumn">添加列</el-button>-->
        </div>
         <el-table
            ref="tableRef"
            :data="tableData0"
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
      <div>
        <el-button @click="saveData" style="margin-top: 1vh;margin-left: 3vh">保存并提交</el-button>
      </div>
  </div>


  </div>




<div class="look" v-if="flag==2" style="display: flex;">
  <div style="width: 50vh;height: 84vh;border: 1px solid #ccc;margin-left: 1vh">
    <iframe :src="nowfile" width="100%" height="800px"></iframe>
  </div>
  <div  style="width: 100vh;border: 1px solid #ccc;margin-left: 1vh">
  <el-tabs  >
    <el-tab-pane :label="'项目名称：'+tit" >
      <div>
        <el-table :data="tableData1" stripe height="100%" style="width:151.5vh">
          <el-table-column prop="time" label="时间" width="140vh" />
          <el-table-column prop="school" label="学校" width="140vh" />
          <el-table-column prop="profession" label="专业" width="140vh" />
          <el-table-column prop="course" label="课程" width="140vh" />
          <el-table-column prop="semester" label="学期" width="140vh" />
          <el-table-column prop="stuname" label="学生姓名" width="140vh" />
          <el-table-column prop="grade" label="成绩" width="140vh" />
          <el-table-column width="140vh" label="修改" v-if="nowprogress=='未提交'">
            <template #default="scope">
              <el-button @click="redata(scope.row)" >修改</el-button>
            </template>
          </el-table-column>
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

  <div style="float: left">
    <el-button  type="primary" @click="add" style="margin-left: 5vh;width: 8vh;height: 4vh;margin-top: 5vh" v-if="nowprogress=='未提交' ">添加新数据</el-button>
  </div>
  <div>
    <el-button  type="primary" @click="exports" style="margin-left: 2vh;width: 8vh;height: 4vh;margin-top: 5vh">数据导出</el-button>
  </div>
    </div>
</div>

  <el-dialog  v-model="dialogVisible" width="60%">
    <iframe :src="nowfile" width="100%" height="800px"></iframe>
  </el-dialog>

<el-dialog
    v-model="dialog"
    title="数据信息"
    width="500"
    align-center
  >
  <div >
  时间：<input  type="text" v-model="nowtime" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  学校：<input  type="text" v-model="nowschool" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  专业：<input  type="text" v-model="nowprofession" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  课程：<input  type="text" v-model="nowcourse" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  学期：<input  type="text" v-model="nowsemeste" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  学生姓名：<input  type="text" v-model="nowstuname" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  成绩：<input  type="text" v-model="nowgrade" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
    </div>
    <template #footer>
      <div style="display: flex;justify-content: center;">
        <el-button @click="deletedata">删除</el-button>
        <el-button type="primary" @click="updata1">
          修改
        </el-button>
      </div>
    </template>
  </el-dialog>

<el-dialog
    v-model="dialog1"
    title="是否确定对该数据进行修改"
    width="500"
    align-center
  >
    <template #footer>
      <div style="display: flex;justify-content: center;">
        <el-button @click="dialog1=false">取消</el-button>
        <el-button type="primary" @click="updata2">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
  <el-dialog
    v-model="dialog2"
    title="是否确定删除该数据"
    width="500"
    align-center
  >
    <template #footer>
      <div style="display: flex;justify-content: center;">
        <el-button @click="dialog2=false">取消</el-button>
        <el-button type="primary" @click="delete1">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>


  <el-dialog
    v-model="dialog3"
    title="数据信息"
    width="500"
    align-center
  >
  <div >
  时间：<input  type="text" v-model="nowtime" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  学校：<input  type="text" v-model="nowschool" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  专业：<input  type="text" v-model="nowprofession" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  课程：<input  type="text" v-model="nowcourse" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  学期：<input  type="text" v-model="nowsemeste" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  学生姓名：<input  type="text" v-model="nowstuname" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
  成绩：<input  type="text" v-model="nowgrade" style="width: 15vh;height: 3vh;margin-left: 1vh;margin-top: 1vh"><br>
    </div>
    <template #footer>
      <div style="display: flex;justify-content: center;">
        <el-button @click="dialog3=false">取消</el-button>
        <el-button type="primary" @click="adddata">
          添加
        </el-button>
      </div>
    </template>
  </el-dialog>

<el-dialog
    v-model="dialog4"
    title="是否确定提交该标注的数据？"
    width="500"
    align-center
  >
    <template #footer>
      <div style="display: flex;justify-content: center;">
        <el-button @click="dialog4=false">取消</el-button>
        <el-button type="primary" @click="subdate">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>

<el-dialog
    v-model="dialog5"
    title="是否确定提交该需标注的项目？提交后将不可修改！"
    width="500"
    align-center
  >
    <template #footer>
      <div style="display: flex;justify-content: center;">
        <el-button @click="dialog5=false">取消</el-button>
        <el-button type="primary" @click="subitem">
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

const dataname=ref("")
const dialogVisible = ref(false)
const tit=ref("")
const da=ref([{"name":11,"done":"已审核","worker":1,"file":null,"email":1111,"progress":"已提交"},{"name":11,"done":"未审核","worker":1,"file":null,"email":1111,"progress":"已提交"},{"name":11,"done":"未审核","worker":1,"file":null,"email":1111,"progress":"未提交"}])
const da1=ref([{"time":1,"school":11,"profession":11,"course":11,"semester":11,"stuname":11,"grade":11,"progress":"已提交"},{"time":1,"school":11,"profession":11,"course":11,"semester":11,"stuname":11,"grade":11,"progress":"已提交"},{"time":1,"school":11,"profession":11,"course":11}])
const nowitem=ref("")
const  nowcustomer=ref("")
const nowworker=ref("")
const nowdone=ref("")
const  nowprogress=ref("")
const dialog=ref(false)
const dialog1=ref(false)
const dialog2=ref(false)
const dialog3=ref(false)
const dialog4=ref(false)
const dialog5=ref(false)
const nowtime=ref("")
const nowschool=ref("")
const nowprofession=ref("")
const nowcourse=ref("")
const nowsemeste=ref("")
const nowstuname=ref("")
const nowgrade=ref("")
const lasttime=ref("")
const lastschool=ref("")
const lastprofession=ref("")
const lastcourse=ref("")
const lastsemeste=ref("")
const laststuname=ref("")
const lastgrade=ref("")
function pushdata(){
  // console.log(pdffile.value)
  // console.log(imgfile.value)

  let formData = new FormData();
      formData.append("name", dataname.value);
      formData.append("file", pdffile.value);
      // console.log(formData)

  axios.post("javaapi/customer/upload", formData,{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
console.log(response.data)
    if(response.data["code"]===0){
      alert("上传成功！")
    }else{
      alert("上传失败！")
    }
  })
}



function dataclick(){
  route1.value="数据标注"
  route2.value=""
  flag.value=0
  axios.get("javaapi/worker/getitem", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data)
      // console.log(da.value)
    if(response.data["code"]===0){
      da.value=response.data["data"]
      da.value.forEach((item, index) => {
        if(item.done=='0') item.done='未审核'
        else item.done='已审核'
        if(item.progress=='0') item.progress='未提交'
        else item.progress='已提交'
      });
          refresh()
    }else{
      alert("查看失败！")
    }

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
      url.searchParams.append('op', "标注员");
      window.location.href = url.toString();
  }
function markdata(row:any){
  flag.value=1
  route2.value=row.name
  nowitem.value=row.name
  nowcustomer.value=row.email
  nowworker.value=row.worker
  nowfile.value='data:application/pdf;base64,'+row.file;
}
function lookdata(row:any){
  nowfile.value='data:application/pdf;base64,'+row.file;
  flag.value=2
  route2.value=row.name
  nowitem.value=row.name
  nowcustomer.value=row.email
  nowworker.value=row.worker
  nowdone.value=row.done
  nowprogress.value=row.progress
  tit.value=row.name
  axios.post("javaapi/worker/checkresult", {"name":row.name,"email":row.email},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data)
    if(response.data["code"]===0){
      da1.value=response.data["data"]
      refresh()
     }else{
        alert("查看失败！")
    }
    })
}




function redata(row:any){
  console.log(row)
  dialog.value=true
  lasttime.value=row.time
  lastschool.value=row.school
  lastprofession.value=row.profession
  lastcourse.value=row.course
  lastsemeste.value=row.semester
  laststuname.value=row.stuname
  lastgrade.value=row.grade
  nowtime.value=row.time
  nowschool.value=row.school
  nowprofession.value=row.profession
  nowcourse.value=row.course
  nowsemeste.value=row.semester
  nowstuname.value=row.stuname
  nowgrade.value=row.grade
  console.log(nowstuname.value)
}

function deletedata(){
  dialog2.value=true
}
function updata1(){
  dialog1.value=true
}
function delete1(){
  dialog.value=false
  dialog2.value=false
  console.log(nowstuname.value)
  axios.post("javaapi/worker/delete", {"name":nowitem.value,"email":nowcustomer.value,"time":nowtime.value,"school":nowschool.value,"profession":nowprofession.value,"course":nowcourse.value,"semester":nowsemeste.value,"stuname":nowstuname.value,"grade":nowgrade.value},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data)
    if(response.data["code"]===0){
      alert("删除成功！")
      axios.post("javaapi/worker/checkresult", {"name":nowitem.value,"email":nowcustomer.value},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
   console.log(response.data)
    if(response.data["code"]===0){
      da1.value=response.data["data"]
      refresh()
     }else{
        alert("查看失败！")
    }
    })
     }else{
        alert("删除失败！")
    }
    })
}
function updata2(){
 dialog1.value=false
  dialog.value=false
  axios.post("javaapi/worker/update", {"name":nowitem.value,"email":nowcustomer.value,"lasttime":lasttime.value,"lastschool":lastschool.value,"lastprofession":lastprofession.value,"lastcourse":lastcourse.value,"lastsemester":lastsemeste.value,"laststuname":laststuname.value,"lastgrade":lastgrade.value,"time":nowtime.value,"school":nowschool.value,"profession":nowprofession.value,"course":nowcourse.value,"semester":nowsemeste.value,"stuname":nowstuname.value,"grade":nowgrade.value},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data)
    if (response.data["code"] === 0) {
      alert("修改成功！")
      axios.post("javaapi/worker/checkresult", {
        "name": nowitem.value,
        "email": nowcustomer.value
      }, {headers: {'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
        console.log(response.data)
        if (response.data["code"] === 0) {
          da1.value = response.data["data"]
          refresh()
        } else {
          alert("查看失败！")
        }
      })
    } else {
      alert("修改失败！")
    }
  })

}
function add(){
  dialog3.value=true
  nowtime.value=""
  nowschool.value=""
  nowprofession.value=""
  nowcourse.value=""
  nowsemeste.value=""
  nowstuname.value=""
  nowgrade.value=""
}
function adddata(){
dialog3.value=false
  axios.post("javaapi/worker/adddata", {"name":nowitem.value,"email":nowcustomer.value,"time":nowtime.value,"school":nowschool.value,"profession":nowprofession.value,"course":nowcourse.value,"semester":nowsemeste.value,"stuname":nowstuname.value,"grade":nowgrade.value},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
   console.log(response.data)
    if (response.data["code"] === 0) {
      alert("添加成功！")
      axios.post("javaapi/worker/checkresult", {"name":nowitem.value,"email":nowcustomer.value},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data)
    if(response.data["code"]===0){
      da1.value=response.data["data"]
      refresh()
     }else{
        alert("查看失败！")
    }
    })
    } else {
      alert("添加失败！")
    }
  })
}








// 表格列配置
const tableColumns = ref([

  {field:'time',label:'时间',type:'string'},
  {field:'school',label:'学校',type:'string'},
  {field:'profession',label:'专业',type:'string'},
  {field:'course',label:'科目',type:'string'},
  {field:'semester',label:'学期',type:'string'},
  {field:'stuname',label:' 学生名',type:'string'},
  {field:'grade',label:'成绩',type:'string'},
])

// 表格数据
const tableData0 = ref([
  {time:'2024.5.1',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'zmpyy',grade:'66'
  },
  {time:'2024.6.1',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'lzx',grade:'76'
  },
  {time:'2024.5.2',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'云起时',grade:'86'
  },
  {time:'2024.5.2',school:'中国计量大学',profession:'计算机科学与技术',course:'数据结构',
   semester:'4',stuname:'Tao',grade:'76'
  }
])
// 选中的行
const selectedRows = ref([]);

// 添加行方法
const addRow = () => {
  const newRow = {
    id: tableData0.value.length + 1,
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
  tableData0.value.push(newRow);
};

// 删除选中的行
const deleteSelectedRows = () => {
  if (!selectedRows.value.length) return;

  // 从后往前删除，避免索引问题
  selectedRows.value.sort((a, b) => b.id - a.id).forEach(row => {
    const index = tableData0.value.findIndex(item => item.id === row.id);
    if (index !== -1) {
      tableData0.value.splice(index, 1);
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
  tableData0.value.forEach(row => {
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
  console.log(`保存: 行=${rowIndex}, 字段=${field}, 值=${tableData0.value[rowIndex][field]}`);
};

const jsonData=ref(null)
function saveData(){
  dialog4.value=true
  console.log(tableData0.value)

}
function subdate(){
  tableData0.value.forEach((item, index) => {
    item.name=nowitem.value
    item.email=nowcustomer.value
  })
  jsonData .value= JSON.stringify(tableData0.value);
  console.log(tableData0.value)
  console.log(jsonData.value)
  dialog4.value=false
  axios.post("javaapi/worker/subdata", tableData0.value,{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data["code"])

    if(response.data["code"]===0){
      alert("该数据表已提交成功，可进行新的数据表填写！")
      tableData0.value=[]

    }else{
      alert("提交失败！")
    }
  })
}
function sub(row:any){
  nowitem.value=row.name
  nowcustomer.value=row.email
  nowworker.value=row.worker
  nowdone.value=row.done
  nowprogress.value=row.progress
  dialog5.value=true
}
function subitem(){
  dialog5.value=false
  axios.post("javaapi/worker/subitem",{"name":nowitem.value,"email":nowcustomer.value},{headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data["code"])
    if(response.data["code"]===0){
       axios.get("javaapi/worker/getitem", {headers:{'Authorization': localStorage.getItem("Authorization")}}).then((response) => {
    console.log(response.data)
      // console.log(da.value)
    if(response.data["code"]===0){
      da.value=response.data["data"]
      da.value.forEach((item, index) => {
        if(item.done=='0') item.done='未审核'
        else item.done='已审核'
        if(item.progress=='0') item.progress='未提交'
        else item.progress='已提交'
      });
          refresh()
    }else{
      alert("查看失败！")
    }

  })
      alert("提交成功！")

    }else{
      alert("提交失败！")
    }
  })

}
</script>










<style>
.excel{
   margin-top: 2vh;
    height: 84vh;
    width: 84vh;
    background-color: #ffffff;
  margin-left: 2vh;

}
.editor{
  margin-top: 1vh;
}
.file{
   margin-top: 2vh;
    height: 84vh;
    width: 65vh;
    background-color:#ffffff;


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
  width: 7vh;
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
  display: flex;
}

</style>