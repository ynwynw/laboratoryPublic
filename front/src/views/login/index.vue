<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">实验室预约管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="Password"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon
            :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
          />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width: 48%; margin-bottom: 30px"
        @click.native.prevent="handleLogin"
        >登录
      </el-button>
      <el-button
        type="primary"
        style="width: 48%; margin-bottom: 30px"
        @click="dialogFormVisible = true"
        >注册</el-button
      >
      <div style="text-align: center">
        <el-radio v-model="loginForm.role" label="user">学生 </el-radio>
        <el-radio v-model="loginForm.role" label="teacher">教师 </el-radio>
        <el-radio v-model="loginForm.role" label="admin">管理员 </el-radio>
      </div>

      <el-dialog title="" :visible.sync="dialogFormVisible">
        <div class="register">
          <h1>注册</h1>
          <el-form ref="registerForm" :model="user" label-width="100px">
            <el-form-item label="姓名">
              <el-input
                v-model="user.userName"
                placeholder="Enter your username"
              />
            </el-form-item>
            <el-form-item label="账号">
              <el-input
                v-model="user.userId"
                placeholder="Enter your username"
              />
            </el-form-item>
            <el-form-item label="密码">
              <el-input
                v-model="user.password"
                type="password"
                placeholder="Enter your password"
              />
            </el-form-item>
            <el-form-item label="电话">
              <el-input
                v-model="user.phone"
                placeholder="Enter your phone number"
              />
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="user.email" placeholder="Enter your email" />
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="register()">注册</el-button>
              <el-button @click="dialogFormVisible = false">返回</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>

      <div class="tips">
        <span style="margin-right: 20px" />
        <span />
      </div>
    </el-form>
  </div>
</template>

<script>
import { validUsername } from "@/utils/validate";
import axios from "axios";

export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("请输入正确的用户名"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("密码不小于6位"));
      } else {
        callback();
      }
    };
    return {
      user: {
        userName: "",
        userId: "",
        password: "",
        phone: "",
        email: "",
        role: "user",
      },
      dialogFormVisible: false,
      loginForm: {
        username: "20171506001",
        password: "123456",
        role: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", validator: validateUsername },
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword },
        ],
      },
      loading: false,
      passwordType: "password",
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  methods: {
    register() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          axios
            .post("http://localhost:8200/admin/member/stuRegister", this.user)
            .then((response) => {
              console.log(response.data);
              this.$message.success("注册成功");
              // handle response
            })
            .catch((error) => {
              console.log(error);
              // handle error
            });
        } else {
          console.log("Validation failed");
        }
      });
    },
    resetForm() {
      this.$refs.registerForm.resetFields();
    },
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("user/login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" });

              this.loading = false;
            })
            .catch(() => {
              this.loading = false;

              this.$message({
                type: "error",
                message: "用户名或密码输入有误!",
              });
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #232222;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      //color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
.register {
  width: 500px;
  margin: 0 auto;
  padding: 50px 0;
}

$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
