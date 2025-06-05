<template >
  <body class="body">

  <div class="maincs">

  <p class="title" v-if="op==0">用户登录</p>
<p class="title" v-if="op==2">项目经理登录</p>
    <p class="title" v-if="op==1">标注员登录</p>
<!--    <div class="username">-->
<!--    <p>账号：</p>-->
  <input type="text" name="username" placeholder="邮箱（需带@.com）" v-model="name">
<!--</div>-->


      <input type="password" name="password" placeholder="密码" v-model="password" ><br>
 <div class="p1">
   <p @click="jumpforget">忘记密码</p>
 </div>
    <div class="p2">
      <p @click="jumpregister">注册</p>
    </div>
    <br>
<div class="but">
    <button type="submit"  @click="login">登录</button>
  </div>
<div class="p3" v-if="op==0||op==1">
   <p @click="login2">项目经理登录</p>
 </div>
    <div class="p3" v-if="op==2">
   <p @click="login0">用户登录</p>
 </div>
    <div class="p4" v-if="op==1">
      <p @click="login0" >用户登录</p>
    </div>
    <div class="p4" v-if="op==0||op==2">
      <p @click="login1" >标注员登录</p>
    </div>
<!--<p>{{op}}</p>-->
    </div>

  </body>
</template>


<script setup>
import { ref } from 'vue';
import axios from 'axios';
const name = ref('')
const password = ref('')
const op = ref(0)
function jumpregister(){
  window.location.href = "/register"
}
function jumpforget(){
  window.location.href = "/forget"
}
function login2(){
  op.value=2
}
function login1(){
  op.value=1
}
function login0(){
  op.value=0
}
function login(){
  // console.log(1)
  axios.post("javaapi/user/login",{"identity":op.value,"email":name.value,"password":password.value}).then((response)=>{
    // console.log(response.data)
    console.log(op.value)
    if(response.data["code"]===0){
      localStorage.setItem("Authorization", response.data.data);
      alert("Login successful")
      if(op.value==0)
        window.location.href = "/custmain"
      else if(op.value==2){
        window.location.href = "/manager"
      }else{
        window.location.href = "/workerpage"
      }
    }else{
       alert("Login failed")
    }
  })
}
</script>

<style>
.body{
  //margin-top: 0;
  width: 100%;
  height: 100vh;
  border: 3px solid #dce5f4;
  background-color: #dce5f4;
}
.maincs{

  border-radius: 20px 20px 20px 20px;
  margin: 0 auto;
  margin-top: 100px;
  width:500px;
  height: 600px;
  background-color: #f1f7fe;

}
.title{

  text-align: center;
  //margin-top: 100px;
  padding-top: 40px;
  font-size: 50px;
  color: #4a4a4a;
  //border: 3px solid #0d387e;
}

.maincs input{
   display: flex;
  //text-align: center;
  //align-items: center;
  justify-content: center;
  width: 400px;
  height: 60px;
  margin: 0 auto;
  margin-top: 80px;
  border-radius: 10px;
  border: 0px solid #4a4a4a;
  padding-left: 10px;
}
.p1{
  color:blue;
  cursor: pointer;
  //margin-top: 0px;
  height: 40px;
  width: 100px;
  float: left;
margin-left: 60px;
  font-size: 13px;
  //border: 3px solid #4a4a4a;
}
.p2{
  color:blue;
  cursor: pointer;
  margin-left: 230px;
  height: 40px;
  width: 100px;
float: left;
  font-size: 13px;
  //border: 3px solid #4a4a4a;
}
.but{
  //border: 3px solid #4a4a4a;
  margin-top: 60px;
}
.maincs button{
  //float: left;
  display: flex;

  align-items: center;
   justify-content: center;
   color: #5656f6;
  font-size: 20px;
  margin: 0 auto;
  //margin-bottom: 50px;
  width: 120px;
height: 50px;
  border-radius: 10px 10px 10px 10px;
  border: 0px;
  background-color: #adeaff;
  transition:0.5s;
}
.maincs button:hover{
 cursor: pointer;
  color: #037c74;
  font-size: 22px;
  background-color: #f3f0d1;
}
.p3{

  color: #212180;
  cursor: pointer;
  //margin-top: 0px;
  padding-top: 5px;
  height: 40px;
  //width: 100px;
  float: left;
margin-left: 30px;
  font-size: 15px;
  margin-bottom: 10px;
  //border: 3px solid #4a4a4a;
}
.p4{
  padding-top: 5px;
  color: #212180;
  cursor: pointer;
  margin-left: 280px;
  height: 40px;
  //width: 100px;
float: left;
  font-size: 15px;


}

.p3:hover{
  color: #4981fb;
  //font-size: 16px;

}
.p4:hover{
 color: #4981fb;

  //font-size: 19px;
}
.p1:hover{
  color: #4981fb;
  //font-size: 16px;

}
.p2:hover{
 color: #4981fb;

  //font-size: 19px;
}
</style>