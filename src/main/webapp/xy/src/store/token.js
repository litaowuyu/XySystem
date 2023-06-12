export default{
	namespaced:true,
	actions:{},
	mutations:{
		// 登录时将用户token，用户id,用户登陆时间存储在本地
		LOGINSIGN(state,user){
			state.userId = user.userId;
			state.Authorization = user.Authorization;
			state.signTime = user.signTime;
			
			window.localStorage.setItem('userId',state.userId)
			window.localStorage.setItem('Authorization',state.Authorization)
			window.localStorage.setItem('signTime',state.signTime)
		},
		// 用户退出登录时将用户信息从本地删除
		EXIT(state){
			window.localStorage.removeItem('userId')
			window.localStorage.removeItem('Authorization')
			window.localStorage.removeItem('signTime')
			state.userId = null
			state.Authorization= null
			state.signTime = null
		}
	},
	state:{
		signTime:window.localStorage.getItem('signTime'),
		Authorization:window.localStorage.getItem('Authorization'),
		userId:window.localStorage.getItem('userId')
	},
	getters:{
		
	}
}