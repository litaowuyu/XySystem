// 该文件用于创建vuex中最为核心的store

import Vue from 'vue'
// 引入vuex
import Vuex from 'vuex'
// 引入对应文件
import token from './token.js'
import goodsAbout from './goodsAbout.js'
import userAbout from './userAbout.js'
import shoppingBagAbout from './shoppingBagAbout.js'
// 使用vuex插件
Vue.use(Vuex)

// 创建并暴露store
export default new Vuex.Store({
	modules:{
		token:token,
		goods:goodsAbout,
		user:userAbout,
		shoppingBagList:shoppingBagAbout
	}
}) 