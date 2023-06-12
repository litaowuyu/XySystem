<template>
	<div id="goodsIndex">
		<GoodsIndexHeader/>
    <div>
      <router-view :key="$route.fullPath"></router-view>
    </div>
	</div>
</template>

<script>
	import GoodsIndexHeader from './GoodsIndexHeader.vue'
	export default{
		name:'GoodsIndex',
		components:{GoodsIndexHeader},
		mounted() {
			this.$router.push('/index/goodsIndex/goods')	
		}
	}
</script>

<style scoped>
	#goodsIndex{
    margin: 0px;
  }
</style>