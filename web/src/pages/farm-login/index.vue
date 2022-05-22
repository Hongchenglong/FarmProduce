<template>
  <div class="farm-login-tab">
    <!-- <div style="margin: 20px" />   style="max-width: 460px"-->
    <p class="farm-home-title">智慧农业系统</p>
    <el-form class="farm-login-form" label-position="top" label-width="450px" :model="loginInfo">
      <el-form-item label="用户名">
        <el-input class="einput" v-model="loginInfo.account" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input class="einput" v-model="loginInfo.password" show-password placeholder="请输入密码" />
        <!-- <span v-if="error.pwd" class="err-msg">{{error.pwd}}</span> -->
      </el-form-item>
      <!-- <el-form-item label="验证码">
                <el-input v-model="loginInfo.type" placeholder="请输入验证码" />
            </el-form-item> -->
      <el-button class="" type="success" style="width:450px" @click="farmLogin">登录</el-button>
    </el-form>
  </div>

</template>

<script  setup>
import {useRouter} from 'vue-router'
import {reactive} from 'vue'
import service from '@/axios'
import {ElMessage} from 'element-plus'

const router = useRouter()
const loginInfo = reactive({
  account: '',
  password: ''
})
function farmLogin() {
  service.post('/oauth/login', loginInfo).then(res => {
    localStorage.setItem('token', res)
    console.log(localStorage.getItem('token'));
    service.get('/user/info').then(res => {
      localStorage.setItem('userInfo', JSON.stringify(res))
      router.push('/farm-manage')
    })
  }).catch(error => {
    loginInfo.account = ''
    loginInfo.password = ''
    ElMessage({
      type: 'error',
      message: '账号或密码错误',
    })
  })
}

</script>

<style lang="less">
.farm-login-tab {
  // width: 100vw;
  background-image: url('http://oss.lqjai.cn/traceap/img/222.jpg');
  background-size: cover;
  //居中
  height: 100vh;
  display: flex;
  flex-direction: column; //并列元素
  align-items: center;
  justify-content: center;
}

.farm-home-title {
  color: #fff; //rgb(15, 174, 68);
  font-size: 50px;
  text-align: center;
  margin-bottom: 30px;
}

.farm-login-form {
  width: 492px;
  padding: 40px 20px;
  background: #fff;
  opacity: 90%;
  border: 1px solid #999;
  border-radius: 5px;
}

.einput {
  border: 1px solid #999;
  border-radius: 5px;
}
</style>