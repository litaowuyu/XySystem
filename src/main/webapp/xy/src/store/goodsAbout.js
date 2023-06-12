import axios from "axios"
import {serverIp} from '../assets/config.js'
export default{
	namespaced:true,
	actions:{},
	mutations:{
		LOADINGGOODS( state){
			axios.get(serverIp+'/goods/allGoods').then(
			response=>{
				state.goods = response.data.data
				window.localStorage.setItem('goods',JSON.stringify(response.data.data))
			})
		}

	},
	state:{
		goods: JSON.parse(window.localStorage.getItem('goods'))
	},
	getters:{}
}