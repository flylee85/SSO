<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>SSO 统一认证中心</title>

    <#-- css   -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui@2.13.1/lib/theme-chalk/index.css">
    <link href="/static/css/login.css" rel="stylesheet"/>

    <#-- js   -->
    <script src="https://unpkg.com/vue@2.6.11/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui@2.13.1/lib/index.js"></script>
    <script src="https://unpkg.com/axios@0.19.2/dist/axios.js"></script>
    <script src="https://unpkg.com/js-cookie@2.2.1/src/js.cookie.js"></script>

</head>
<body>
<div id="app">
    <div class="login-container">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on"
                 label-position="left">

            <div class="title-container">
                <h3 class="title">SSO 统一认证中心</h3>
            </div>

            <el-form-item prop="username">
                <div class="svg-container">
                    <img class="svg-class" src="/static/svg/user.svg" alt="user-icon"/>
                </div>
                <el-input
                        ref="username"
                        v-model="loginForm.username"
                        placeholder="Username"
                        name="username"
                        type="text"
                        tabindex="1"
                        autocomplete="on"
                />
            </el-form-item>

            <el-tooltip v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
                <el-form-item prop="password">
                    <span class="svg-container">
                       <img class="svg-class" src="/static/svg/password.svg" alt="password-icon"/>
                    </span>
                    <span class="show-pwd" @click="showPwd">
                        <img v-if="passwordType === 'password'" class="svg-class-2"
                             src="/static/svg/eye.svg"
                             alt="eye-icon"/>
                        <img v-else class="svg-class" src="/static/svg/eye-open.svg" alt="eye-open-icon"/>
                    </span>
                    <el-input
                            :key="passwordType"
                            ref="password"
                            v-model="loginForm.password"
                            :type="passwordType"
                            placeholder="Password"
                            name="password"
                            tabindex="2"
                            autocomplete="on"
                            @keyup.native="checkCapslock"
                            @blur="capsTooltip = false"
                            @keyup.enter.native="handleLogin"
                    />
                </el-form-item>
            </el-tooltip>

            <el-button
                    :loading="loading"
                    type="primary"
                    style="width:100%;margin-bottom:30px;"
                    @click.native.prevent="handleLogin">Login
            </el-button>

            <div style="position:relative">
                <div class="tips">
                    <span>Username : admin</span>
                    <span>Password : 123456</span>
                </div>
            </div>
        </el-form>
    </div>
</div>
</body>

<script type="text/javascript">
  var app = new Vue({
    el: '#app',
    data() {
      const validatePassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('The password can not be less than 6 digits'))
        } else {
          callback()
        }
      }
      return {
        loginForm: {
          username: 'admin',
          password: '123456'
        },
        loginRules: {
          username: [{required: true, trigger: 'blur'}],
          password: [{required: true, trigger: 'blur', validator: validatePassword}]
        },
        passwordType: 'password',
        capsTooltip: false,
        loading: false
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
      checkCapslock(e) {
        const {key} = e
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
        const params = window.location.search.substring(1);
        if (!params) {
          alert('回调地址获取失败，请返回原页面重试')
          return;
        }
        const urlPair = params.split("&").filter(pair => pair.indexOf("callbackUrl=") > -1);
        if (!urlPair || !urlPair.length) {
          alert('回调地址获取失败，请返回原页面重试')
          return;
        }
        const callbackUrl = urlPair[0].split("=")[1];
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            axios({
              method: 'post',
              url: '/user/login',
              data: {
                callbackUrl,
                name: this.loginForm.username,
                pwd: this.loginForm.password
              }
            }).then((res) => {
              this.loading = false
              const {data, msg} = res.data;
              if (data && data['access-token']) {
                Cookies.set('access-token', data['access-token'])
                window.location = callbackUrl + "?" + data['access-token'];
              } else {
                Message({message: msg || 'Error', type: 'error', duration: 5 * 1000})
              }
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      }
    }
  });
</script>

</html>