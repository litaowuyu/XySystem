<template>
  <div id="detail">
    <div class="demo-image">
      <el-image
          style="width: 350px; height: 350px;display: block"
          :src="require(`../assets/images/${good.goodsImg}`)"
          fit=cover>
      </el-image>
    </div>
    <div id="description">
      <a id="introduce">{{ good.goodsIntroduce }}</a>
      <div class="other">
        <a>上架时间：{{good.uploadTime}}</a>
        <a>发布者:{{sellUserName}}</a>
      </div>
      <a id="price">价格:{{ good.price }}</a>
      <a id="oldPrice">原价：0</a>
      <div id="btn">
        <button>联系卖家</button>
        <button @click="add">加入购物车</button>
        <button @click="buy(good)">立即购买</button>
      </div>

    </div>
  </div>
</template>

<script>
import {mapMutations, mapState} from 'vuex'
import axios from "axios";
import {serverIp} from "@/assets/config";

export default {
  name: 'GoodsDetail',
  props: ['id'],
  data() {
    return {
          sellUserName:''
    }
  },
  methods: {
    ...mapMutations('shoppingBagList',['LOADINGAHOPPINGBAG']),
    buy(good){
      if(good.exchangeTime !== null && good.exchangeTime !== ""){
        alert("此商品已售出！")
        return 0
      }
      axios.post(serverIp+'/user/buy',{
        goodsId:good.goodsId,
        userId:this.user.userId
      }).then(
          response=>{
            if(response.data.code===1){
              alert("购买成功！")
            }
          }
      )
    },
    add() {
        for (var i=0;i< this.shoppingBagList.length;i++){
          if(this.shoppingBagList[i].goodsId===this.id){
            alert("您的购物车中已存在此商品！")
            return 0;
          }
       }
        axios.post(serverIp+'/user/addShoppingBag',{
          goodsId: this.id,
          userId: this.user.userId
        }).then(
            response=>{
              if(response.data.code===1){
                alert("添加成功！")
                console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~")
                this.LOADINGAHOPPINGBAG()
              }else {
                alert("添加失败！")
              }
            }
        )
    },
    getSellUserName(){
      axios.post(serverIp+'/user/getUserByUserAccount',{
        userAccount: this.good.sellUser
      }).then(
          response=>{
            this.sellUserName =  response.data.data.petName
          }
      )
      return 1;
    }
  },
  computed: {
    ...mapState('goods', ['goods']),
    ...mapState('user',['user']),
    ...mapState('shoppingBagList',['shoppingBagList']),
    good() {
      for (var i = 0; i < this.goods.length; i++) {
        if (this.goods[i].goodsId === this.id) {
          let good = this.goods[i]
          return good
        }
      }
      return 1
    }
  },
mounted() {
    this.getSellUserName()
}
}
</script>

<style scoped>
#detail {
  width: 60%;
  height: 350px;
  margin: 0px auto;
  padding: 15px;

}

.demo-image {
  float: left;
  margin-right: 20px;
}

#description {
  float: left;
  width: 50%;

}

#description a {
  display: block;
}

#introduce {
  font-size: 16pt;
}

.other {
  font-weight: bold;
  margin-top: 7px;
}

.other a {
  margin-right: 20px;
  margin-top: 7px;
}

#price {
  margin-top: 7px;
  font-size: 20pt;
  color: red;
}

#oldPrice {
  margin-top: 7px;
  font-size: 15pt;
}

#btn {
  margin: 7px auto;
}

#btn button {
  width: 80px;
  height: 30px;
  background-color: cornflowerblue;
  border: 0px;
  border-radius: 4px;
  margin-right: 30px;
}
</style>