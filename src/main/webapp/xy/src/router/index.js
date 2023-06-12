// 该文件用于创建整个应用的路由器
import vueRouter from 'vue-router'
// 引入组件
import Regist from '../pages/Regist'
import Login from '../pages/Login'
import About from '../pages/About'
import Index from '../pages/Index'
import ShoppingBag from '../pages/ShoppingBag'
import GoodsIndex from '../components/GoodsIndex'
import UserDetail from '../components/UserDetail'
import GoodsDetail from '../components/GoodsDetail'
import Goods from '../components/Goods'
import Screener from '../components/Screener'
import UserInformation from '../components/UserInformation'
import UserWallet from '../components/UserWallet'
import ExchangeRecord from '../components/ExchangeRecord'
import RecordScreener from '../components/RecordScreener'
import UserOrder from '../components/UserOrder'
import UploadGoods from "@/pages/UploadGoods";

// 创建一个路由器
const router = new vueRouter({
	mode:'hash',
	routes:[
		{
			path:'/',
			component:About,
			meta:{
				title:'欢迎使用校易'
			}
		},
		{
			name:'login',
			path:'/login',
			component:Login,
			meta:{
				isAuth:false,
				title:'登录'
			}
		},
		{
			name:'regist',
			path:'/regist',
			component:Regist,
			meta:{
				isAuth:false,
				title:'注册'}
		},
		{
			name:'about',
			path:'/about',
			component:About,
			meta:{
				isAuth:false,
				title:'关于我们'}
		},
		{
			name:'index',
			path:'/index',
			component:Index,
			meta:{
				isAuth:false,
				title:'校易首页'
			},
			children:[
				{
					path:'goodsIndex',
					component:GoodsIndex,
					meta:{
						isAuth:false,
						title:'商品广场'
					},
					children:[
						{
							path:'goods',
							component:Goods,
							meta:{
								isAuth:false,
								title:'商品广场'
							}
						},
						{
							name:'screener',
							path:'screener',
							component:Screener,
							props($route){
								return{
									keyword:$route.query.keyword
								}
							},
							meta:{
								isAuth:false,
								title:'商品筛选'
							}
						}
					]
				},
				{ 
					name:'goodsDetail',
					path:'goodsDetail/:id',
					component:GoodsDetail,
					meta:{
						isAuth:false,
						title:'商品详情'},
						props:true
				},
				{
					path:'userDetail',
					component:UserDetail,
					meta:{
						isAuth:true,
						title:'个人中心'
					},
					children:[
						{
							path:'userInformation',
							component:UserInformation,
							meta:{
								isAuth:true,
								title:'个人中心'
							}
						},
						{
							path:'userWallet',
							component:UserWallet,
							meta:{
								isAuth:true,
								title:'个人中心'
							}
						},
						{
							path:'exchangeRecord',
							component:ExchangeRecord,
							meta:{
								isAuth:true,
								title:'个人中心'
							},
							
						},
						{
								name:'recordScreener',
								path:'recordScreener',
								component:RecordScreener,
								meta:{
									title:'交易记录筛选'
								}
						}
						
					]
				},
				{
					path:'userOrder',
					component:UserOrder,
					meta:{
						title:'我的订单'
					}
				},
				{
					path:'uploadGoods',
					component:UploadGoods,
					meta:{
						title: '商品发布'
					}
				}
			]
		},
		{
			path:"/ShoppingBag",
			component:ShoppingBag,
			meta:{
				title:'购物车'
			}
		}
			
			
	],
	
})
// 暴露路由器
export default router

// 全局前置路由守卫
router.beforeEach((to,from,next)=>{
	if(to.path==='/index/goodsIndex'){
		next('/index/goodsIndex/goods')
	}else if(to.path==='/index' && from.path==='/index/goodsIndex/goods'){
		next('/index/goodsIndex/goods')
	}else{
		next()
	}
})
// 全局后置路由守卫
router.afterEach((to,from)=>{
	document.title = to.meta.title || '校易网络二手交易平台'

})