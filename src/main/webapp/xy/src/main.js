import Vue from 'vue'
import App from './App.vue'
// 引入vue-router
import vueRouter from 'vue-router'
// 引入配置的路由器
import router from './router'
Vue.use(vueRouter)
// 引入vuex的store
import store from './store/index.js'
import VueRouter from "vue-router";
// 按需引入element-ui
import { Button,Row,Form,Link,Input,FormItem,Container,Header,Col,Avatar,
         Select,Option,Image,Checkbox,CheckboxGroup,Descriptions,DescriptionsItem,Tag,Drawer,Message,MessageBox} from 'element-ui';
Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm
// 应用element-ui
Vue.use(Button)
Vue.use(Row)
Vue.use(Form)
Vue.use(Link)
Vue.use(Input)
Vue.use(FormItem)
Vue.use(Container)
Vue.use(Header)
Vue.use(Col)
Vue.use(Avatar)
Vue.use(Select)
Vue.use(Option)
Vue.use(Image)
Vue.use(Checkbox)
Vue.use(CheckboxGroup)
Vue.use(Descriptions)
Vue.use(DescriptionsItem)
Vue.use(Tag)
Vue.use(Drawer)
Vue.config.productionTip = false

new Vue({
  beforeCreate() {
    Vue.prototype.$bus = this //安装全局事件总线(GlobalEventBus)
  },
  render: h => h(App),
  store,
  router,
}).$mount('#app')

let originPush = VueRouter.prototype.push;
VueRouter.prototype.push = function(location,resolve,reject){
  if(resolve && reject){
    originPush.call(this,location,resolve,resolve)
  }else{
    originPush.call(this,location,()=>{},()=>{})
  }
}


