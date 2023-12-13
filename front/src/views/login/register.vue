<template>
  <div class="register">
    <h1>Register</h1>
    <el-form ref="registerForm" :model="user" label-width="100px">
      <el-form-item label="Username">
        <el-input v-model="user.userName" placeholder="Enter your username"></el-input>
      </el-form-item>
      <el-form-item label="Password">
        <el-input v-model="user.password" type="password" placeholder="Enter your password"></el-input>
      </el-form-item>
      <el-form-item label="Phone">
        <el-input v-model="user.phone" placeholder="Enter your phone number"></el-input>
      </el-form-item>
      <el-form-item label="Email">
        <el-input v-model="user.email" placeholder="Enter your email"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="register">Register</el-button>
        <el-button @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Register',
  data() {
    return {
      user: {
        userName: '',
        password: '',
        phone: '',
        email: ''
      }
    };
  },
  methods: {
    register() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          axios.post('/api/stuRegister', this.user)
            .then(response => {
              console.log(response.data);
              // handle response
            })
            .catch(error => {
              console.log(error);
              // handle error
            });
        } else {
          console.log('Validation failed');
        }
      });
    },
    resetForm() {
      this.$refs.registerForm.resetFields();
    }
  }
};
</script>

<style scoped>
.register {
  width: 500px;
  margin: 0 auto;
  padding: 50px 0;
}
</style>
