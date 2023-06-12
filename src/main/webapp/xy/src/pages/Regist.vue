<template>
  <div class="regist">
    <div id="header">
      <img src="../assets/logo.png"/>
      <a style="margin-left: 10px;font-size:30pt; font-style: italic;color: dodgerblue; ">校易新用户注册</a>
    </div>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-position="left" label-width="75px"
             class="demo-ruleForm">
      <el-form-item label="学校:" prop="schoolName">
        <el-input type="text" v-model="ruleForm.schoolName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称:" prop="petName">
        <el-input type="text" v-model="ruleForm.petName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="手机:" prop="account">
        <el-input type="text" v-model="ruleForm.account" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码:" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码:" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
import axios from 'axios'
import {serverIp} from '../assets/config.js'

export default {
  name: 'Regist',
  data() {
    var reg = /^[\u0391-\uFFE5A-Za-z]+$/;
    var checkPetName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('昵称不能为空'));
      }
      setTimeout(() => {
        if (!reg.test(value)) {
          callback(new Error('昵称只能包含中文和英文'));
        } else {
          callback()
        }
      }, 1000);
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    var checkAccount = (rule, value, callback) => {
      if (!value) {
        callback(new Error("手机号不能为空"))
      } else {
        if (value.trim().length !== 11) {
          callback((new Error("请检查手机号是否正确")))
        } else {
          callback()
        }
      }
    };
    var checkSchoolName = (rule, value, callback) => {
      if (!value) {
        callback(new Error("学校名不能为空"))
      } else {
        callback()
      }
    };
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        petName: '',
        account: '',
        schoolName: ''
      },
      rules: {
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ],
        petName: [
          {validator: checkPetName, trigger: 'blur'}
        ],
        account: [
          {validator: checkAccount, trigger: 'blur'},
        ],
        schoolName: [
          {validator: checkSchoolName, trigger: 'blur'},
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post(serverIp + '/user/register', this.ruleForm).then(
              response => {
                if (response.data.code === 1) {
                  alert("注册成功")
                } else {
                  alert("该手机号已被注册！")
                }
              })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }

}
</script>

<style scoped>
.regist {
  display: block;
  margin-top: 0px;
  width: 100%;
  height: 721px;
  background-color: floralwhite;
}

.demo-ruleForm {
  margin: 0px auto;
  width: 400px;
  height: 500px;
  padding-top: 100px;
  text-align: center;
}

#header {
  display: block;
  width: 100%;
  height: 100px;
  border-bottom: 2px solid #808080;
  background-color: antiquewhite;
}

#header img {
  width: 10%;
  margin-left: 30%;
}
</style>