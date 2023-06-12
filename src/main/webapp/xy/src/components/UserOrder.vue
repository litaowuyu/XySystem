<template>
  <div>
        <div id="navigation">
          <p @click="all">全部订单</p>
          <p @click="completed">已完成</p>
          <p @click="incomplete">未完成</p>
        </div>
    <div v-for="(order,index) in orders" :key="index" style="margin-bottom: 20px">
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
            交易状态
          </template>
          {{order.newOrderState}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            交易时间
          </template>
          {{ order.exchangeTime }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            操作
          </template>
          <a class="sbtn" @click="complete(order.orderId)" v-show="order.newOrderState==='未完成'">完成&nbsp;&nbsp;&nbsp;</a>
          <a class="sbtn" @click="deleteOrder(index)">删除&nbsp;&nbsp;&nbsp;</a>
          <a class="sbtn" @click="contactSeller(order)">联系卖家</a>
        </el-descriptions-item>
     </el-descriptions>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {serverIp} from "@/assets/config";
import {mapState} from "vuex";


export default {
  name: 'UserOrder',
  data() {
    return {
      orders: [],
      new_Orders:[]
    }
  },
  methods: {
    all(){
       this.orders = this.new_Orders
    },
    completed(){
      this.orders = this.new_Orders.filter(order => order.orderState === 1);
    },
    incomplete(){
      this.orders = this.new_Orders.filter(order => order.orderState === 0);
    },
    getOrderState(){
      for(var i = 0;i<this.orders.length;i++){
        if(this.orders[i].orderState===1){
          this.$set(this.orders[i],"newOrderState","已完成")
        }else {
          this.$set(this.orders[i],"newOrderState","未完成")
        }
      }
      for(var j = 0;j<this.new_Orders.length;j++){
        if(this.new_Orders[j].orderState===1){
          this.$set(this.new_Orders[j],"newOrderState","已完成")
        }else {
          this.$set(this.new_Orders[j],"newOrderState","未完成")
        }
      }
    },
    getOrders(){
        axios.post(serverIp+'/user/getOrders',{
          userId: this.user.userId
        }).then(
            response=>{
              this.orders = response.data.data
              this.new_Orders = response.data.data
              this.getOrderState()
            }
        )
    },
    contactSeller(order) {
      // 实现联系卖家的逻辑

      },
    complete(orderId){
      axios.post(serverIp+'/goods/complete',{
        orderId:orderId
      }).then(
          response=>{
            if(response.data.code===1){
              alert("完成订单！")
              window.location.reload()
            }else {
              alert("请重试！")
            }
          }
      )
    },
    deleteOrder(index) {
      // 实现删除订单的逻辑
      axios.post(serverIp+'/order/deleteOrder',{
        orderId:this.orders[index].orderId
      }).then(
          response=>{
            if(response.data.code===1){
              alert("删除成功！")
            }else{
              alert("请重试！")
            }
          }
      )
    },
  },
  computed:{
    ...mapState("user",['user']),

  },
  mounted() {
    this.getOrders()
  }
}
</script>

<style scoped>
#navigation{
  height: 30px;
  border-bottom: grey solid 2px;
  padding: 5px;
  display: flex;
}
#navigation p{
  font-size: 15px;
  font-weight: bold;
  margin-right: 10px;
}
#navigation p:hover{
  cursor: default;
  color: dodgerblue;
}
.sbtn:hover{
  cursor: default;
  color: dodgerblue;
}

</style>