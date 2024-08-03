<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on"
             label-position="left">

      <div class="title-container">
        <h3 class="title">LF 权限管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="请输入用户名"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password"/>
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="请输入密码"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
          </span>
        </el-form-item>
      </el-tooltip>

      <div class="captcha-container">
        <el-form-item prop="code" class="code-item">
          <span class="svg-container">
            <svg-icon icon-class="安全A-1"/>
          </span>
          <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            class="code-input"
            @keyup.enter.native="handleLogin"
          />
        </el-form-item>
        <div class="login-code">
          <img :src="codeUrl" @click="getCaptcha">
        </div>
      </div>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
                 @click.native.prevent="handleLogin">登录
      </el-button>

    </el-form>
  </div>
</template>

<script>
import { getCaptcha } from '@/api/captcha'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (value.length === 0) {
        callback(new Error('用户名不能为空！'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length === 0) {
        callback(new Error('密码不能为空！'))
      } else {
        callback()
      }
    }
    return {
      codeUrl: '',
      loginForm: {
        username: 'admin',
        password: '123456',
        code: '',
        uuid: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },
  created() {
    this.getCaptcha()
  },
  watch: {
    $route: {
      handler: function (route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  methods: {
    getCaptcha() {
      getCaptcha().then(res => {
        this.codeUrl = res.data.base64Image
        this.loginForm.uuid = res.data.uuid
      })
    },
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true

          this.$store.dispatch('user/login', this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
              this.loading = false
            })
            .catch(() => {
              this.loading = false
              this.getCaptcha()
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    }
  }
}
</script>

<style lang="scss">
$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background: url('../../assets/Background_images/f21ea1b7d8264ff6b2e77e6b3ae6d3f7.jpg') no-repeat center center fixed;
  background-size: cover;
  overflow: hidden;

  .login-form {
    border-radius: 6px;
    background: #ffffff;
    width: 385px;
    padding: 25px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    display: flex;
    flex-direction: column;
    align-items: center;

    .el-form-item {
      width: 100%;
      margin-bottom: 20px;
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;

      .el-input {
        height: 38px;
        width: 85%;
        display: inline-block;

        input {
          height: 38px;
          color: #000; /* 使输入框中的字体变成黑色 */
          background: transparent;
          caret-color: #000;
          border: 0;
          -webkit-appearance: none;
          border-radius: 0;
          padding: 12px 5px 12px 15px;

          &:-webkit-autofill {
            box-shadow: 0 0 0px 1000px $bg inset !important;
            -webkit-text-fill-color: $cursor !important;
          }
        }
      }

      .svg-container {
        margin-left: 10px;
        vertical-align: middle;
        width: 15px;
        display: inline-block;
      }
    }

    .captcha-container {
      display: flex;
      align-items: center;
      width: 100%;

      .code-item {
        flex: 1;
      }

      .login-code {
        flex: 0 0 43%;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: -20px;
        margin-left: -10px;
        margin-right: -12px;
        img {
          cursor: pointer;
        }
      }
    }

    .title-container {
      text-align: center;
      margin-bottom: 20px;

      .title {
        font-size: 26px;
        color: #707070; /* 标题颜色 */
        margin: 0;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 50%;
      transform: translateY(-50%);
      font-size: 16px;
      color: #707070;
      cursor: pointer;
      user-select: none;
    }

    .login-button {
      width: 100%;
      margin-top: 30px;
      margin-bottom: 30px;
    }
  }
}

@media only screen and (max-width: 470px) {
  .login-container {
    .login-form {
      width: 100%;
      padding: 15px;
    }
  }
}
</style>
