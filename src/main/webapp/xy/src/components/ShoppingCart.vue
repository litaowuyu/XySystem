<template>
  <div id="shopping" @click="shoppingBag" >
    <img id="icon"  src="../assets/shopping.png"/>
    <a id="gwc" >
      购物车
    </a>
    <i id="count">
      {{this.shoppingBagList.length}}</i>
  </div>
</template>

<script>

import {mapMutations, mapState} from "vuex";

export default {
  name:'ShoppingCart',
  data(){
    return{

    }
  },
  methods:{
    shoppingBag(){
      this.$router.push('/ShoppingBag')
    },
    ...mapMutations('shoppingBagList',['LOADINGAHOPPINGBAG'])
  },
  computed:{
    ...mapState('shoppingBagList',['shoppingBagList']),
  }
}
</script>

<style scoped>
#shopping {
  margin-left: 20px;
  border-radius: 2px;
  width: 150px;
  height: 30px;
  float: left;
  display: flex;
  align-items: center;
  background-color: white;
  padding: 5px;

}

#icon {
  display: block;
  margin-left: 15px;
}

#gwc {
  margin-top: 5px;
  margin-left: 10px;
  white-space: nowrap;
  display: block;
  cursor: default;
}

#count {
  text-align: center;
  display: block;
  background-color: dodgerblue;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  margin-top: 6px;
  margin-left: 10px;
}
</style>