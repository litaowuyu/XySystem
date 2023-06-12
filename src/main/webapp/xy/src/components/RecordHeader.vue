<template>
  <div id="record">
    <div id="recordHeader">
      <a style="font-size: 15pt;">我的商品</a>
      <p @click="boughtList"  style="font-size: 14pt;">已购买</p>
      <p style="font-size: 14pt;">已发布</p>
    </div>
    <div v-for="(order,index) in list" :key="index" style="margin-bottom: 20px">
      <el-descriptions  class="margin-top" :column="3"  border>
        <el-descriptions-item>
          <template slot="label">
            发布者
          </template>
          {{order.sellUser}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            商品图
          </template>
          <div class="demo-image">
            <el-image
                style="width: 80px; height: 80px"
                :src="require(`../assets/images/${order.goodsImg}`)"
                fit=cover>
            </el-image>
          </div>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            商品名
          </template>
          {{order.goodsIntroduce}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            商品价格
          </template>
          {{order.price}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            交易时间
          </template>
          {{ order.exchangeTime }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import axios from "axios";
import {serverIp} from "@/assets/config";

export default {
  name:'RecordHeader',
  data(){
    return{
       list:[]
    }
  },
  methods:{
    boughtList(){
        axios.post(serverIp+'/user/getOrders',{
          userId: this.user.userId
        }).then(
            response=>{
              let l = []
              for(var i= 0;i<response.data.data.length;i++){
                if(response.data.data[i].orderState===1){
                  l.push(response.data.data[i])
                }
              }
              this.list= l
            }
        )

    },
    soldList(){

    }
  },
  computed:{
    ...mapState('user',['user'])
  },
  mounted() {
    this.boughtList()
  }
}
</script>

<style scoped>
#recordHeader a {
  display: block;
  margin-right: 30px;
  float: left;
}

#recordHeader p {
  float: left;
  margin: 0px 20px;
}

#recordHeader p:hover {
  cursor: default;
  color: dodgerblue;
}
#record{
  width:840px;
  display: inline-block;
  background-color:white;
  margin-top:40px;
  padding: 10px;
}
</style>