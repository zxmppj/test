<template>
  <el-form :model="loginForm" status-icon :rules="loginRules" ref="loginForm">
    <el-form-item prop="username">
      <el-input type="text" class="loginUsername" v-model="loginForm.username" auto-complete="on" placeholder="请输入用户名" clearable></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码" clearable></el-input>
    </el-form-item>
    <el-form-item prop="remember">
      <el-checkbox v-model="loginForm.remember">保持登录状态</el-checkbox>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" class="loginBtn" @click="submitForm('loginForm')">登录</el-button>
     <!-- <el-button type="primary" class="loginBtn" @click="toSubmitForm('loginForm')">注册</el-button>-->
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    data() {
      let validateEmail = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入用户名'));
        } else if (value.length < 1 || value.length > 16) {
          callback(new Error('用户名格式不正确'));
        } else {
          callback();
        }
      };
      let validatePassword = (rule, value, callback) => {
        const regular = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$).{8,32}$/);
        if (value === '') {
          callback(new Error('请输入密码'));
        } else if (!regular.test(value)) {
          callback(new Error('密码格式不正确'));
        } else {
          callback();
        }
      };
      return {
        loginForm: {
          username: '',
          password: '',
          remember: false
        },
        loginRules: {
          username: [
            {validator: validateEmail, trigger: 'blur'}
          ],
          password: [
            {validator: validatePassword, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$api.post('/user/login', {
              "username": this.loginForm.username,
              "password": this.loginForm.password
            }, res => {
              const data = res.data;
              if (data === 'usernameOrPasswordError') {
                this.$message.error('用户名或密码错误');
              } else {
                this.$message.success('登录成功');
                this.$api.removeToken();
                if (this.loginForm.remember) {
                  localStorage.setItem('token', data);
                } else {
                  sessionStorage.setItem('token', data);
                }
                this.$api.setToken(data);
                const redirect = this.$route.query.redirect;
                if (redirect !== undefined) {
                  this.$router.push(redirect);
                } else {
                  this.$router.push({name:'content',params:{id:"1234"}});
                }
              }
            });
          } else {
            return false;
          }
        });
      },
      toSubmitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$api.post('/user/tologin', {
              "username": this.loginForm.username,
              "password": this.loginForm.password
            }, res => {
              const data = res.data;
              if (data === 'usernameOrPasswordError') {
                this.$message.error('用户名或密码错误');
              } else {
                this.$message.success('注册成功');
                this.$api.removeToken();
                if (this.loginForm.remember) {
                  localStorage.setItem('token', data);
                } else {
                  sessionStorage.setItem('token', data);
                }
                this.$api.setToken(data);
                const redirect = this.$route.query.redirect;
                if (redirect !== undefined) {
                  this.$router.push(redirect);
                } else {
                  this.$router.push({name:'content',params:{id:"1234"}});
                }
              }
            });
          } else {
            return false;
          }
        });
      }
    }
  };
</script>
