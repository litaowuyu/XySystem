<template>
	<div id="userInfo">
		<el-row class="demo-avatar demo-basic">
		  <el-col :span="12">
		    <div class="demo-basic--circle">
		      <div class="block" v-if="user.userAvatar!==null">
				 <el-avatar :size="70" :src="require(`../assets/avatar/${user.userAvatar}.jpg`)"></el-avatar>
			  </div>
			  <div class="block" v-if="user.userAvatar===null">
			  			<el-avatar :size="70" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
			  </div>
		    </div>
		  </el-col> 
		</el-row>
		<template v-if="user!==null">
			<p>{{user.petName}}</p>
			<p>{{user.userSchool}}</p>
			<p id="update" style="border: 1px solid #545454; width: 80px; margin: auto auto;">编辑资料</p>
		</template>	
		<template v-if="user===null">
			<p @click="login" style="color: crimson;cursor: default;text-decoration: underline;">请登录</p>
		</template>
	</div>
	
</template>

<script>
	import {mapState} from 'vuex';
	
	export default{
		name:'UserInformation',
		
		data(){
		      return {
				  
		      }
		    
		     },
		computed:{
			...mapState('user',['user']),
			
		},
		methods:{
			login(){
				this.$router.push('/login')
			}
		},
	}
	
</script>

<style scoped>
	#userInfo{
		float: left;
		background-color: white;
		border-radius: 3px;
		text-align: center;
		width: 200px;
		height: 200px;
		padding: 20px;
	}
	#update:hover{
		cursor: default;
	}
	.demo-avatar{
		margin-left: 68px;
		margin-top: 10px;
	}
</style>