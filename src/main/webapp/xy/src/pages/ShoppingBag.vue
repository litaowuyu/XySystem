<template>
  <div id="shoppingBag">
    <div id="one">
      <p id="total">购物车（总计{{ displayDatas.length }}）</p>
      <p id="count">已选{{ countAll }}</p>
      <el-button style="height: 40px" @click="balancing">结算</el-button>
    </div>
    <div id="header">
      <div id="title1">
        <input @change="checkedAll(checkAll)" type="checkbox" v-model="checkAll" name="checkAll">
        <label>全选</label>
      </div>
      <p id="title2">商品信息</p>
      <p id="title3">状态信息</p>
      <p id="title4">当前售价</p>
    </div>
    <div v-for="(displayData,index) in displayDatas" :key="index">
      <input @change="pushChecked(displayData,displayData.goodsId)" type="checkbox" :value=displayData
             :name=displayData.goodsId>
      <label>发布者: {{ displayData.userName }}</label>
      <div id="displayData">
        <div id="img">
          <el-image
              style="width: 120px; height: 120px"
              :src="require(`../assets/images/${displayData.goodsImg}`)"
              fit=cover>
          </el-image>
        </div>
        <div id="other">
          <p id="introduce">{{ displayData.goodsIntroduce }}</p>
          <div id="statement">
            <p>发布时间: {{ displayData.uploadTime }}</p>
            <p>当前状态: {{ displayData.statement }}</p>
          </div>
          <p id="price">{{ displayData.price }}</p>
        </div>
        <p  id="delete" @click="deleteBag(displayData.goodsId)" >删除</p>
      </div>
    </div>
  </div>
</template>
<script>
import {mapMutations, mapState} from "vuex";
import axios from "axios";
import {serverIp} from "../assets/config.js"


export default {
  name: "ShoppingBag",
  data() {
    return {
      checkAll: false,
      checks: [],
      sellUsers: [],
      displayDatas: [],
      goodsList: []
    };
  },

  computed: {
    ...mapState('shoppingBagList', ['shoppingBagList']),
    ...mapState('goods', ['goods']),
    ...mapState('user',['user']),
    countAll() {
      var countAll = 0
      for (var i = 0; i < this.checks.length; i++) {
        countAll += this.checks[i].price
      }
      return countAll;
    },
  },
  watch: {
    deep: true,
    checks() {
      if (this.checks.length !== this.displayDatas.length) {
        this.checkAll = false
        document.getElementsByName('checkAll')[0].checked = false
      } else {
        this.checkAll = true
        document.getElementsByName('checkAll')[0].checked = true
      }
    }
  },
  methods: {
    ...mapMutations('shoppingBagList',['LOADINGAHOPPINGBAG']),
    deleteBag(id){
      axios.post(serverIp+'/user/deleteBag',{
        goodsId:id
      }).then(
          response => {
            if (response.data.code === 1) {
              alert("已删除！")
              window.location.reload()
            }
          }
      )
    },
    balancing() {
      for(var i =0;i<this.checks.length;i++){
        if(this.checks[i].statement==="已售出"){
          alert("您所选的商品包含已售出商品，请重新选择！")
        }else {
          axios.post(serverIp + '/user/balancing', {
            checks:JSON.stringify(this.checks),
            userId:this.user.userId
          }).then(
              response => {
                if (response.data.code === 1) {
                  alert("结算成功！")
                } else if(response.data.code === 0) {
                  alert("您的余额不足！")
                }
              }
          )
        }
      }
    },
    pushChecked(displayData, goodsId) {
      let temp = document.getElementsByName(goodsId)
      if (temp[0].checked === false) {
        this.checks.splice(this.checks.indexOf(displayData), 1)
      } else {
        this.checks.push(displayData)
      }
    },
    checkedAll(checkAll) {
      if (checkAll === true) {
        for (let i = 0; i < this.displayDatas.length; i++) {
          let temp = document.getElementsByName(this.displayDatas[i].goodsId)
          if (temp[0].checked === false) {
            document.getElementsByName(this.displayDatas[i].goodsId)[0].checked = true
            this.checks.push(this.displayDatas[i])
          }
        }
      } else {
        for (let i = 0; i < this.displayDatas.length; i++) {
          let temp = document.getElementsByName(this.displayDatas[i].goodsId)
          if (temp[0].checked === true) {
            document.getElementsByName(this.displayDatas[i].goodsId)[0].checked = false
            this.checks.splice(this.checks.indexOf(this.displayDatas[i]), 1)
          }
        }
      }
    },
    getStatement() {
      console.log(this.displayDatas)
      for (var i = 0; i < this.displayDatas.length; i++) {
        if (this.displayDatas[i].exchangeTime === null || this.displayDatas[i].exchangeTime === '') {
          this.$set(this.displayDatas[i], 'statement', '未售出')
        } else {
          this.$set(this.displayDatas[i], 'statement', '已售出')
        }
      }
    },
    getUser() {
      var userAccount = []
      for (var i = 0; i < this.goodsList.length; i++) {
        userAccount.push(this.goodsList[i].sellUser)
      }
      var newUserAccount = []
      for (var k = 0; k < userAccount.length; k++) {
        if (newUserAccount.indexOf(userAccount[k]) === -1) {
          newUserAccount.push(userAccount[k])
        }
      }
      console.log(newUserAccount)
      axios.post(serverIp + '/user/getUserInformationByUserAccount', {
        userAccount: newUserAccount
      }).then(
          response => {
            this.sellUsers = response.data.data
            console.log(response.data.data)
            this.getSellUserName()
          },
      )
    },
    getSellUserName() {
      var displayDatas = []
      for (var i = 0; i < this.sellUsers.length; i++) {
        for (var j = 0; j < this.goodsList.length; j++) {
          if (this.sellUsers[i].userAccount === this.goodsList[j].sellUser) {
            this.$set(this.goodsList[j], 'userName', this.sellUsers[i].petName)
            displayDatas.push(this.goodsList[j])
          }
        }
      }
      this.displayDatas = displayDatas
      this.getStatement()
    },
    shoppingGoods() {
      var goodsList = []
      console.log(this.goods.length,this.shoppingBagList.length)
      for (var i = 0; i < this.shoppingBagList.length; i++) {
        for (var j = 0; j < this.goods.length; j++) {
          if (this.shoppingBagList[i].goodsId === this.goods[j].goodsId) {

            goodsList.push(this.goods[j]);
            break;
          }
        }
      }
      this.goodsList = goodsList;
    },
  },
  mounted() {
    this.LOADINGAHOPPINGBAG(this.user.userId)
    setTimeout(()=> {
      this.shoppingGoods()
      this.getUser()
    }, 1000)



  }
};
</script>

<style scoped>
#shoppingBag {
  width: 70%;
  margin: 1% 20% 0% 20%;
}

#displayData {
  width: 900px;
  height: 150px;
  border: 1px solid #808080;
}

#one {
  display: flex;
  align-items: center;
}

#total {

}

#count {
  margin-left: 640px;
  margin-right: 20px;
}

#header {
  display: flex;
}

#header p {
  margin-top: 0px;
  margin-bottom: 0px;
}

#title2 {
  margin-left: 120px;
}

#title3 {
  margin-left: 245px;
}

#title4 {
  margin-left: 215px;
}

#img {
  float: left;
  margin: 15px 20px 0px 30px;
}

#introduce {
  width: 300px;
  height: auto;
  margin-right: 10px;
  float: left;
}

#statement {
  float: left;
  margin-right: 50px;
}

#other {
  float: left;

}

#price {
  float: left;
}
#delete{
  float: right;
  margin-right: 10px;
  cursor: default;

}
#delete:hover{
  color: red;
}
</style>