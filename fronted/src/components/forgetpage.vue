<template >
  <body class="body">

  <div class="maincs">

  <p class="title">忘记密码</p>

<!--    <div class="username">-->
<!--    <p>账号：</p>-->
  <input type="text" name="username" placeholder="邮箱（需带@.com）" v-model="email" style="width: 300px;display: inline-block;margin-left: 45px;margin-top: 10px">
    <button style="font-size:12px;width:80px;float:left;display: inline-block;margin-top:-50px;margin-left: 370px" @click="getcode">发送验证码</button>
<!--</div>-->
<input type="text" name="username" placeholder="验证码" v-model="codee">
<input type="password" name="password" placeholder="密码" v-model="password" >
      <input type="password" name="password" placeholder="再输一遍密码" v-model="password1" ><br>
 <div class="p1">
   <p @click="jumplogin">登录</p>
 </div>
    <div class="p2">
      <p @click="jumpregister">注册</p>
    </div>
    <br>
<div class="but">
    <button type="submit"  @click="login" style="margin-top: -15px">重置密码</button>
  </div>


    </div>

  </body>
</template>


<script setup>
import { ref } from 'vue';
import axios from 'axios';
const email = ref('')
const codee = ref('')
const password1 = ref('')
const password = ref('')
function getcode(){
  axios.post("javaapi/user/find",{"op":0,"email":email.value}).then((response)=>{
    // console.log(response.data["code"])

    if(response.data["code"]===0){
      alert("成功发送验证码！")
    }else{
       alert("发送验证码失败！")
    }
  })


}

function jumpregister(){
  window.location.href = "/register"
}
function jumplogin(){
  window.location.href = "/login"
}
function login(){
  // console.log(1)
  if(password.value===password1.value) {
    axios.post("javaapi/user/find", {"op": 1, "email": email.value,"code":codee.value,"password":password.value}).
    then((response) => {
      // console.log(response.data["code"])

      if (response.data["code"] === 0) {
        alert("密码重置成功！")
        window.location.href = "/login"

      } else {
        alert("密码重置失败！")
      }
    })
  }else{
    alert("两次密码不一致！")
  }
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
  margin-top: 10px;
  border-radius: 10px;
  border: 0px solid #4a4a4a;
  padding-left: 10px;
}
.p1{
  color:blue;
  cursor: pointer;
  margin-top: -20px;
  height: 40px;
  width: 100px;
  float: left;
margin-left: 60px;
  font-size: 13px;
  //border: 3px solid #4a4a4a;
}
.p2{
  margin-top: -20px;
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

.p4{
  margin-top: -50px;
  padding-top: 5px;
  color: #212180;
  cursor: pointer;
  margin-left: 380px;
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