import axios from "axios"
import {serverIp} from '../assets/config.js'

export default{
	namespaced:true,
	actions:{},
	mutations:{
		// 用户登录时从服务器获取用户的信息
		LOADINGUSER(state,userId){
			axios.post(serverIp + '/user/information',{
				userId:userId
			}).then(
			response=>{
				state.user=response.data.data
				window.sessionStorage.setItem("user",JSON.stringify(response.data.data))
			})
			return Promise.resolve();
		},
		USEREXIT(state){
			state.user=null
		}
	},
	state:{
		user: JSON.parse(window.sessionStorage.getItem("user"))
	},
	getters:{
		
	}
}