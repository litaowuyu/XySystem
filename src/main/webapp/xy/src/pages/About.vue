<template>
	<div id="about">
		<div>
			<img src="../assets/logo.png"/>
			<a>欢迎使用校易二手交易平台！
			</a>
			<el-row class="go">
			<el-button @click="goLogin"  style="background-color: mediumblue;" type="primary" icon="el-icon-right" circle></el-button>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default{
		name:'About',
		methods:{
			goLogin(){
				this.$router.push({
					path:'/index'
				})
				
			}
			
		}
	}
</script>

<style scoped>
	#about{
		background: url("../assets/background.png");
		width: 100%;
		height: 100%;
		position: fixed;
		background-size: 100% 100%;
	}
	img{
		margin-top: 80px;
		width: 30%;
		height: 30%;
		position: relative;
		left: 35%;
	}
	a{
		display: block;
		text-align: center;
		font-size: 50px;
		font-family:华康方圆体W7 ;
		color: blue;
		margin: 60px auto;
	}
	.go{
		position: relative;
		left: 50%;
	}
	
</style>