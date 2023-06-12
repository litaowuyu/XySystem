<template>
  <div id="BImage">
    <div id="logo">
      <img class="img" src="../assets/logo.png"/>
    </div>
    <div id="loginForm">
      账号：
      <el-input style="width: 200px;" class="account" type="text" placeholder="请输入您的账号" v-model="account"/>
      <br/>
      密码：
      <el-input style="width: 200px;" class="password" type="password" placeholder="请输入您的密码" v-model="password"
                show-password/>
      <el-row class="go">
        <el-button size="medium" @click="login" type="primary">登录</el-button>
        <el-button size="medium" @click="clear" type="primary">重置</el-button>
      </el-row>
      <el-link style="margin-top: 40px;color: black;" @click="goRegist" type="primary">没有账号？马上去注册</el-link>
    </div>
  </div>
</template>

<script>
import {mapMutations, mapState} from 'vuex'
import {serverIp} from '../assets/config.js'
import axios from 'axios'

export default {
  name: 'Login',
  data() {
    return {
      account: '',
      password: ''
    }
  },
  methods: {
    ...mapMutations('token', {loginSing: 'LOGINSIGN'}),
    ...mapMutations('user', {loadingUser: 'LOADINGUSER'}),
    ...mapMutations('shoppingBagList',['LOADINGAHOPPINGBAG']),
    login() {
      if (this.account === "" || this.password === "") {
        alert("请正确输入账户和密码！")
      } else {
        axios.post(serverIp + '/user/login', {
          userAccount: this.account, password: this.password
        }).then(
            response => {
              if (response.data.code === 1) {
                this.loginSing({
                  userId: response.data.userId,
                  Authorization: response.data.token,
                  signTime: new Date().getTime()
                })
                this.loadingUser(response.data.userId)
                this.LOADINGAHOPPINGBAG(response.data.userId)
                this.$router.push({
                  path: '/index'
                })
              } else if (response.data.code === 0) {
                alert("用户名或密码错误！")
              } else {
                alert("用户不存在！")
              }

            },
            error => {
              alert('服务器繁忙，请稍后再试^_^')
            }
        )
      }

    },
    clear() {
      this.account = ''
      this.password = ''
    },
    goRegist() {
      this.$router.push({
        path: '/regist'
      })
    }
  }
}
</script>

<style scoped>
#loginForm {
  margin-right: 250px;
  margin-top: 180px;
  padding-top: 60px;
  width: 400px;
  height: 300px;
  background-color: azure;
  text-align: center;
  float: right;
  border-radius: 4px;
}

#loginForm input {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border: 0.5px solid rgba(0, 0, 0, .12);
  width: 200px;
  height: 25px;
  margin-bottom: 15px;
  border-radius: 3px;
  outline: none;

}

#BImage {
  background: url("../assets/background.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}

#logo {
  margin-top: 150px;
  margin-left: 200px;
  width: 35%;
  float: left;
}

.img {
  width: 100%;
}

.account {
  margin-top: 0px;
}

.password {
  margin-top: 20px;
}

.go {
  margin-top: 30px;
}
</style>