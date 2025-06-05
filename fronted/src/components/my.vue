<template class="all">
  <div class="all">
    <!-- 侧边栏菜单 -->
    <div class="menu">
      <div class="logo">
        <img src="../other/1.png" width="100%" height="100%" alt="Logo">
      </div>

      <div class="title">民权标注平台</div>
      <div class="menu1">
        <el-row class="tac">
          <el-col>
            <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
            >
              <el-sub-menu index="1" style="background-color: #dae8f8; width: 22vh">
                <template #title>
                  <el-icon><IconMenu /></el-icon>
                  <span style="font-size: 1.7vh">个人中心</span>
                </template>
                <el-menu-item index="1-1" @click="goToProfile" style="font-size: 1.5vh">个人信息</el-menu-item>
                <el-menu-item index="1-2" @click="logout" style="font-size: 1.5vh">退出登录</el-menu-item>
              </el-sub-menu>


            </el-menu>
          </el-col>
        </el-row>
      </div>
    </div>

    <!-- 顶部区域 -->
    <div class="lab">
      <div class="page">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item style="font-size: 2vh">个人中心</el-breadcrumb-item>
          <el-breadcrumb-item style="font-size: 1.6vh; margin-top: 0.40vh">个人信息</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="person">
        <img src="/src/other/1.png" width="100%" height="100%" style="border-radius: 2.5vh" alt="用户头像">
      </div>

      <div class="usertype">{{ userInfo.nickname }}</div>
    </div>

    <!-- 主内容区 -->
    <div class="main-content">
      <!-- 用户资料卡片 -->
      <div class="profile-card">
        <div class="profile-header">
          <div class="profile-avatar">
            <img src="../other/1.png" alt="用户头像" width="100" height="100" style="border-radius:2.5vh;">
          </div>
          <div class="profile-info">
            <h2 class="username">
              <el-input v-model="userInfo.realName" size="small" style="width: 150px;"></el-input>
            </h2>
            <div class="user-roles">
              <span v-for="role in userInfo.roles" :key="role" class="role-tag">{{ iden }}</span>
            </div>
          </div>
        </div>

        <div class="profile-body">
          <div class="section-title">实名认证信息</div>
          <div class="info-row">
            <span class="info-label">姓名:</span>
            <el-input v-model="userInfo.realName" size="small" style="width: 200px;" disabled></el-input>
          </div>
          <div class="info-row">
            <span class="info-label">身份证号:</span>
            <el-input v-model="userInfo.idCard" size="small" style="width: 300px;" disabled></el-input>
          </div>
          <div class="info-row">
            <span class="info-label">手机号:</span>
            <el-input v-model="userInfo.phone" size="small" style="width: 200px;" disabled></el-input>
          </div>
          <div class="info-row">
            <span class="info-label">邮箱:</span>
            <el-input v-model="userInfo.email" size="small" style="width: 300px;" disabled></el-input>
          </div>

          <div class="section-title">账号信息</div>
          <div class="info-row">
            <span class="info-label">昵称:</span>
            <el-input v-model="userInfo.nickname" size="small" style="width: 200px;"></el-input>
          </div>
          <div class="info-row">
            <span class="info-label">账号:</span>
            <el-input v-model="userInfo.account" size="small" style="width: 200px;"></el-input>
          </div>
          <div class="info-row">
            <span class="info-label">登录密码:</span>
            <span class="info-value">**********</span>
            <el-button size="mini" type="text" @click="changePassword">修改</el-button>
          </div>

        </div>

        <div class="profile-footer">
          <el-button type="primary" @click="saveProfile">保存修改</el-button>
          <el-button @click="cancelEdit" style="margin-left: 10px;">取消</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed, reactive, toRefs } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router';

const route = useRoute();
const iden = route.query.op;
// 获取路由实例
const router = useRouter()
// const iden= ref('')
// iden.value = $router.params.op
console.log(iden.value)
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'

// 原始用户数据
const originalUserInfo = reactive({
  avatar: '../other/1.png',
  realName: '张三',
  idCard: '110101199001011234',
  phone: '13800138000',
  email: 'example@domain.com',
  nickname: '张三',
  account: 'zhangsan',
  roles: ['项目经理'],
});

// 编辑状态下的用户数据
const userInfo = reactive({...originalUserInfo});

// 工具函数
const formatIdCard = (idCard: string) => {
  if (!idCard) return '';
  return idCard.replace(/(\d{6})(\d{8})(\d{4})/, '$1********$3');
};

const formatPhone = (phone: string) => {
  if (!phone) return '';
  return phone.replace(/(\d{3})(\d{4})(\d{4})/, '$1****$3');
};

// 菜单事件处理
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
};

const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
};

// 导航函数
const goToProfile = () => {
  // 个人信息页面
  router.push('/my')
};

const goToDataUpload = () => {
  // 数据上传页面
  router.push('/custmain');
};

const goToResultView = () => {
  // 结果查看页面
  console.log('');
};

// 操作函数
const logout = () => {
  router.push('/login');
};

// 保存修改
const saveProfile = () => {
  // 这里可以添加表单验证逻辑

  // 模拟API请求
  console.log('保存用户信息:', userInfo);
  router.push('/personalcenter')

  // 实际项目中应该调用API
  // axios.post('/api/updateUserInfo', userInfo)
  //   .then(response => {
  //     if (response.data.code === 200) {
  //       ElMessage.success('保存成功');
  //       // 更新原始数据
  //       Object.assign(originalUserInfo, userInfo);
  //     } else {
  //       ElMessage.error(response.data.message || '保存失败');
  //     }
  //   })
  //   .catch(error => {
  //     console.error('保存失败:', error);
  //     ElMessage.error('网络错误，请稍后重试');
  //   });
};

// 取消编辑，恢复原始数据
const cancelEdit = () => {
  Object.assign(userInfo, originalUserInfo);
  router.push('/personalcenter');
};

// 修改密码
const changePassword = () => {
  // 修改密码逻辑
  console.log('修改密码');
  // 这里可以打开修改密码的对话框
};
</script>

<style scoped>
.all {
  width: 177.7777vh;
  height: 100vh;
  background-color: #f9f5f5;
}

.menu {
  float: left;
  width: 25vh;
  height: 100vh;
  border: 0.1px solid #d8deec;
  background-color: #ffffff;
}

.logo {
  float: left;
  width: 7vh;
  height: 7vh;
  margin-top: 0.8vh;
  margin-left: 0.8vh;
}

.title {
  font-size: 2.3vh;
  float: left;
  width: 15vh;
  height: 4vh;
  margin-top: 3vh;
  margin-left: 0.6vh;
}

.menu1 {
  width: 20vh;
  height: 80vh;
  margin-top: 16vh;
  margin-left: 2vh;
}

.lab {
  background-color: #ffffff;
  width: 152vh;
  height: 10vh;
  border: 0.1px solid #d8deec;
  margin-left: 25vh;
}

.page {
  float: left;
  margin-top: 3vh;
  margin-left: 2vh;
  width: 50vh;
  height: 6vh;
}

.person {
  width: 5vh;
  height: 5vh;
  margin-top: 2vh;
  margin-left: 125vh;
  border-radius: 2.5vh;
}

.usertype {
  font-size: 2vh;
  float: left;
  width: 5vh;
  height: 5vh;
  margin-top: -5.6vh;
  margin-left: 132vh;
}

.main-content {
  width: 80vh;
  height: 80vh;
  margin-left: 60vh;
  border: 0.1px solid #ffffff;
  padding: 2vh;
  box-sizing: border-box;
}

.profile-card {
  background-color: #ffffff;
  border-radius: 2vh;
  padding: 5vh;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 4vh;
}

.profile-avatar {
  margin-right: 3vh;
}

.profile-avatar img {
  border-radius: 50%;
  border: 2px solid #eee;
}

.username {
  font-size: 2.8vh;
  margin-bottom: 1vh;
}

.user-roles {
  display: flex;
  flex-wrap: wrap;
}

.role-tag {
  background-color: #409eff;
  color: white;
  padding: 0.5vh 1.5vh;
  border-radius: 2vh;
  margin-right: 1vh;
  margin-bottom: 1vh;
  font-size: 1.4vh;
}

.section-title {
  font-size: 2vh;
  font-weight: bold;
  margin-top: 3vh;
  margin-bottom: 2vh;
  color: #333;
  border-bottom: 1px solid #eee;
  padding-bottom: 1vh;
}

.info-row {
  display: flex;
  align-items: center;
  margin-bottom: 2vh;
  font-size: 1.8vh;
}

.info-label {
  width: 15vh;
  color: #666;
}

.info-value {
  color: #333;
}

.profile-footer {
  margin-top: 4vh;
  text-align: right;
}
</style>