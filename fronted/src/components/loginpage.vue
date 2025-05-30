<template>
<form>
   <input type="text" name="username" placeholder="Username" v-model="name">
 <input type="password" name="password" placeholder="Password" v-model="password" ><br>
 <button type="submit" class="but" @click="login">Login</button>
</form>
</template>


<script setup>
import { ref } from 'vue';
import axios from 'axios';
const name = ref('')
const password = ref('')

function login(){
  console.log(1)
  axios.post("javaapi/api/login",{"name":name.value,"password":password.value}).then((response)=>{
    console.log(response.data)

    if(response.data=="success"){
      alert("Login successful")
    }else{
       alert("Login failed")
    }
  })
}
</script>

<style scoped>

</style>