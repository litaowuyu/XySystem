import axios from "axios";
import {serverIp} from '../assets/config.js'

export default {
    namespaced: true,
    actions: {},
    mutations: {
        LOADINGAHOPPINGBAG(state,userId) {
            axios.post(serverIp + '/ShoppingBag', {
                userId:userId
            }).then(
                response => {
                    state.shoppingBagList = response.data.data
                    window.sessionStorage.setItem("shoppingBagList",JSON.stringify(response.data.data))
                }
            )
            return Promise.resolve();
        }
    },
    state: {
        shoppingBagList: JSON.parse(window.sessionStorage.getItem("shoppingBagList"))
    }
    ,
    getters: {},
}