<template>
  <div>
    <div id="goodDisplay">
      <div id="good" v-for="(good,index) in screenerList" :key="index" @click="goodDetail(good. goodsId)">
        <div class="demo-image">
          <el-image
              style="width: 150px; height: 150px"
              :src="require(`../assets/images/${good.goodsImg}`)"
              fit=cover>
          </el-image>
        </div>
        <a id="introduce">{{ good.goodsIntroduce }}</a>
        <p id="price">￥{{ good.price }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'Screener',
  props: ['keyword'],
  data() {
    return {
      url: 'https://s1.ax1x.com/2023/02/26/pp9ZQTe.jpg',
    }
  },
  methods: {
    goodDetail(id) {
      this.$router.push({
        name: 'goodsDetail',
        params: {
          id: id
        }
      })
    }
  },
  computed: {
    ...mapState('goods', ['goods']),
    screenerList() {
      let keyword = this.keyword;
      let goods = this.goods;
      var reg = new RegExp(keyword);
      var arr = [];
      for (var i = 0; i < goods.length; i++) {
        if (reg.test(goods[i].goodsIntroduce)) {
          arr.push(goods[i]);
        }
      }
      return arr;
    }
  },

}
</script>

<style scoped>
#goodDisplay {
  margin-top: 0.8%;
  margin-left: 9%;
}

#good {
  padding-top: 1%;
  margin-bottom: 10px;
  margin-right: 2%;
  float: left;
  text-align: center;
  width: 16.7%;
  height: 250px;
  border-radius: 4px;
  background-color: white;
}

#introduce {
  height: 40px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  width: 200px;
  height: 40px;
  margin: 5px 12px 0px;
  overflow: hidden;
  text-overflow: ellipsis;
}

#price {
  color: crimson;
  font-weight: 700;
  font-size: 13pt;
  margin: 13px auto 0px;

}

#good p {

}
</style>