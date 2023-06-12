<template>
	<div id="indexBack">
		<Header/>
		<div id="goods">
			<router-view></router-view>
		</div>
		<hr/>
		<Footer/>
	</div>
</template>

<script>
	import Header from '../components/Header.vue'
	import Footer from '../components/Footer.vue'

	export default{
		name:'Index',
		components:{
			Header,Footer
		},
		mounted() {
			this.$router.push('/index/goodsIndex')
		},
		
	}
</script>

<style scoped>
	#goods{
		width:100%;
    margin-top: -4px;
		display: inline-block;
	}
	#indexBack{
		background-color: #F0F8FF;
	}
</style>