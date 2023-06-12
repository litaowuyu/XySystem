<template>
  <div id="userWallet">
    <div style="display: inline-block;float: left;">
      <el-input placeholder="请输入您要搜索的内容" v-model="input" class="input-with-select">
        <el-button @click="search" slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </div>
    <ShoppingCart/>
    <div id="wallet">
      <a style="display: block; float:left; margin-right: 350px;font-size: 20pt;">我的钱包</a>
      <a @click="drawer = true" style="display: block; cursor:default;  font-size: 15pt;">充值</a>
<!--      <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">-->
<!--        充值-->
<!--      </el-button>-->
      <el-drawer
          title="充值"
          :visible.sync="drawer"
          direction="rtl"
          :before-close="handleClose">
        <el-input style="margin-left: 20px" placeholder="请输入您要充值的金额" v-model="charge" class="input-with-select">
          <el-button @click="recharge" slot="append">充值 </el-button>
        </el-input>
      </el-drawer>
      <p>余额：{{user.balance}}</p>
      <p>累计消费：{{total}}</p>
    </div>
  </div>
</template>

<script>
import ShoppingCart from './ShoppingCart'
import {mapState} from "vuex";
import axios from "axios";
import {serverIp} from "@/assets/config";

export default {
  name: 'UserWallet',
  components: {ShoppingCart},
  data(){
    return{
       total:'',
       input:'',
       charge: '',
       drawer: false
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    search() {

    },
    recharge(){
      axios.post(serverIp+'/user/recharge',{
           recharge:  Number(this.charge),
           userId: this.user.userId
      }).then(
          response=>{
            if(response.data.code===1){
              alert("充值成功！")
              window.location.reload()
            }
          }
      )
    },
    getTotal(){
      axios.post(serverIp+'/user/getOrders',{
        userId: this.user.userId
      }).then(
          response=>{
            let t = 0
            for(var i =0;i<response.data.data.length;i++) {
              if (response.data.data[i].orderState === 1) {
                t = t + response.data.data[i].price
              }
            }
            this.total = t
          }
      )
    },
  },
  computed:{
    ...mapState('user',['user'])
  },
  mounted() {
    this.getTotal()
  }
}
</script>

<style scoped>
#userWallet {
  float: left;
  margin-left: 30px;
  width: 580px;
  height: 220px;
  padding: 10px 10px;
}

.input-with-select {

  width: 400px;
  background-color: #fff;
}

#wallet {
  margin-top: 50px;
  width: 550px;
  height: 150px;
  background-color: white;
  border-radius: 3px;
  padding: 15px;
}

#wallet p {
  margin-right: 30px;
  float: left;
  font-weight: 700;
  font-size: 15pt;
  margin-top: 50px;
}

</style>