<template>
  <div class="container">
    <h2>上传商品信息</h2>
    <form @submit.prevent="submitForm" >
      <div class="form-group">
        <label>商品描述:</label>
        <textarea id="description" v-model="description" required>
        </textarea>
      </div>
      <div class="form-group">
        <label for="price">商品价格:</label>
        <input type="number" step="0.01" id="price" v-model="price" required>
      </div>
      <div class="form-group">
        <label for="image">商品图片:</label>
        <input type="file" id="image" @change="handleImageUpload" required>
      </div>
      <button type="submit">上传</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import {serverIp} from "@/assets/config";
import {mapState} from "vuex";
import qs from "qs";

export default {
  name:'UploadGoods',
  data() {
    return {
        description: '',
        price: null,
        image: null,
    }
  },
  computed:{
    ...mapState('user',['user'])
  },
  methods: {
    handleImageUpload(event) {
      this.image = event.target.files[0];
    },
    submitForm() {
      if (!this.description || !this.price || !this.image) {
        alert('请填写完整商品信息');
        return;
      }
      const formData = new FormData();
      formData.append('goodsDescription', this.description);
      formData.append('price', Number(this.price));
      formData.append('image', this.image);
      formData.append('userId', this.user.userId);
      // 这里可以进行上传商品信息的操作，比如使用axios发送POST请求
      axios.post(serverIp+'/goods/uploadGoods',formData,{
        headers: {
          'Content-Type': 'multipart/form-data'
        }}).then(
          response=>{
            if(response.data.code===1){
              alert('商品信息已上传')
            }else {
              alert("请稍后重试！")
            }
          }
      )

    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: #fff;
  padding: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin: 10px 0;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

textarea,
input[type="number"],
input[type="file"] {
  border: 1px solid #ccc;
  padding: 5px;
  border-radius: 5px;
}

button {
  background-color: #1e90ff;
  color: #fff;
  border: none;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #007acc;
}

</style>