<template>
  <div class="app-container">
    <el-row>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="密码" prop="pass">
                  <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                  <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                  <el-input v-model.number="ruleForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                  <el-input v-model="ruleForm.email"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="submitInfo('ruleForm')">提交</el-button>
                  <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
              </el-form>
        </div>
      </el-col>


      <el-col :span="16"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>



       
  </div>
 </template>

<script>
import myInfo from '@/api/mySet' 
  export  default {
    data() {
      var phone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } 
          else {
            var pattern = /0?(13|14|15|17|18|19)[0-9]{9}/
            if(!pattern.test(value))
            {
               callback(new Error('手机号格式有误'))
            }
             else {
              callback();
            }
          }
        }, 1000);
      };
      
      var validatePass = (rule, value, callback) => {
        var pattern=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/
        if (value === '') {
          callback(new Error('请输入密码'));
        }
       else if(!pattern.test(value))
       {
          callback(new Error('密码必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10位'));
       }
         else {

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
      var emailCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入邮箱地址'));
        } 
        else {
          var pattern = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/
          
            if(!pattern.test(value))
            {
               callback(new Error('邮箱格式有误'))
            }
             else {
              callback();
            }
          
        }
      };

      return {
        ruleForm: {
          pass: '',
          checkPass: '',
          phone: '',
          email:''
        },

        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          phone: [
            { validator: phone, trigger: 'blur' }
          ],
          email: [
            { validator: emailCheck, trigger: 'blur' }
          ]
        }
      };
    },

    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },

     //提交修改信息
     submitInfo(formName){
       //1.传回修改信息
       //2.调用接口
     
       this.$refs[formName].validate((valid) => {
          if (valid) {
            myInfo.submit(this.ruleForm)
         .then(response=>{
                this.$message({
                type: "success",
                 message: "成功!",
                 })
              
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
       

     }

    }
  }
</script>