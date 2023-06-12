<template>
  <div id="header">
    <div class="position" id="logo">
      <img src="../assets/logo.png"/>
    </div>
    <div class="position" id="search">
      <el-input placeholder="请输入您要搜索的内容" v-model="input" class="input-with-select">
        <el-button @click="search" slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </div>
    <div class="position" id="shoppingCart">
      <ShoppingCart/>
    </div>
  </div>
</template>

<script>
import ShoppingCart from './ShoppingCart'
export default {
  name: 'IndexHeader',
  components: {ShoppingCart},
  data() {
    return {
      input: ''
    }
  },
  methods: {
    search() {
      if (this.input !== '') {
        this.$router.push({
          path: "screener",
          query:{
            keyword: this.input
          }
        })

      } else {
        alert('请输入关键字进行搜索！')
      }
    }
  },
}
</script>

<style scoped>
#header {
  width: 100%;
  display: flex;
  align-items: center;
  background-color: aqua;
}

#logo {
  width: 200px;
  padding: 20px;
  margin-left: 20%;
}

#logo img {
  width: 80%;
}

.el-input {
  width: 400px;
}

.el-input-group__prepend {
  background-color: #fff;
}

#shoppingCart {
  margin-left: 1%;
}
</style>