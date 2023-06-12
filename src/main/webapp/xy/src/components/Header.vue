<template>
  <div id="header">
    <p v-if="Authorization===null" id="plaseLogin" @click="login">请登录</p>
    <div v-if="Authorization!==null" id="userinfo">
      <div id="userinfo_avatar">
        <el-row class="demo-avatar demo-basic">
          <el-col :span="12">
            <div class="demo-basic--circle">
              <div class="block">
                <el-avatar v-if="user!==null&&user.userAvatar!==null" :size="40"
                           :src="require(`../assets/avatar/${user.userAvatar}.jpg`)"></el-avatar>
                <el-avatar v-if="user!==null&&user.userAvatar===null" :size="40"
                           src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <div id="userinfo_info">
        <p>{{ user.petName }}</p>
        <p>{{ user.userSchool }}</p>
      </div>
    </div>
    <div v-if="Authorization!==null" id="exit">
      <img @click="exit" src="../assets/exit.png"/>
    </div>
    <div id="topright">
      <a @click="uploadGoods">出售物品</a>
      <a @click="userOrder">我的订单</a>
      <a @click="userDetail">个人中心</a>
      <a @click="about">关于我们</a>
    </div>
  </div>
</template>

<script>
import {mapState, mapMutations} from 'vuex'

export default {
  name: 'Header',
  methods: {
    ...mapMutations('user', {loadingUser: 'LOADINGUSER', userExit: 'USEREXIT'}),
    ...mapMutations('token', {exitLogin: 'EXIT'}),
    ...mapMutations('shoppingBagList',['LOADINGAHOPPINGBAG']),
    uploadGoods(){
      this.$router.push('/index/uploadGoods')
    },
    userOrder(){
      this.$router.push('/index/userOrder')
    },
    userDetail() {
      this.$router.push('/index/userDetail')
    },
    login() {
      this.$router.push('/login')
    },
    exit() {
      this.exitLogin();
      this.userExit()
    },
    about() {
      this.$router.push('/about')
    }
  },
  data() {
    return {}

  },
  computed: {
    ...mapState('user', ['user']),
    ...mapState('token', ['Authorization', 'userId'])

  },
  mounted() {
    this.loadingUser(this.userId)
    this.LOADINGAHOPPINGBAG(this.userId)
  },
}
</script>

<style scoped>
#header {
  margin: 0px;
  width: 100%;
  display: inline-block;
  background-color: lightblue;
  cursor: default;

}

#userinfo {
  float: left;
  padding: 3px;
  display: flex;
  align-items: center;
  margin-left: 10%;
}

#userinfo_avatar {
  margin-top: 3%;
  margin-right: 3%;
}

#userinfo_info {
  display: inline-block;
}

#userinfo_info p {
  white-space: nowrap;
  font-size: 11pt;
  margin: 0px 0px;
}

#exit {
  float: left;
  margin-top: 0.8%;
  margin-left: 0.5%;
}

#plaseLogin {
  display: inline;
  color: crimson;
  cursor: default;
}

#plaseLogin:hover {
  margin-top: 8px;
  float: left;
  width: 50px;

}

#topright {
  margin-top: 1%;
  margin-right: 10%;
  float: right;
}

#topright a {
  font-size: 11pt;
  margin-right: 8px;
  cursor: default;
}

#topright a:hover {
  color: blue;
}
</style>