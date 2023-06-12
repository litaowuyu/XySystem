<template>
    <div id="goodDisplay">
      <div id="good" v-for="(good,index) in goods" :key="index" @click="goodDetail(good. goodsId)">
        <div class="demo-image">
          <el-image
              style="width: 150px; height: 150px"
              :src="require(`../assets/images/${good.goodsImg}`)"
              fit=cover>
          </el-image>
        </div>
        <a id="introduce">{{ good.goodsIntroduce }}</a>
        <p id="price">￥{{ good.price }}</p>
        <p id></p>
      </div>
    </div>
</template>

<script>
import {mapMutations, mapState} from 'vuex'

export default {
  name: 'Goods',
  data() {
    return {

    }
  },
  computed: {
    ...mapState('goods', ['goods']),
  },
  methods: {
    goodDetail(id) {
      this.$router.push({
        name: 'goodsDetail',
        params: {
          id: id
        }
      })
    },
    ...mapMutations('goods', {loadingGoods: 'LOADINGGOODS'}),

  },

  mounted() {
    this.loadingGoods();
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