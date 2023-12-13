<template>
  <div class="app-container">
    <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
      <el-form-item label="实验室名称">
        <el-input placeholder="请输入实验室名称" style="width: 200px" v-model="sizeForm.lname"></el-input>
      </el-form-item>
      <el-form-item label="材料名称">
        <el-input placeholder="请输入材料名称" style="width: 500px"
                  v-model="sizeForm.mname"></el-input>
      </el-form-item>
      <el-form-item label="数量">
        <el-input placeholder="请输入材料数量" style="width: 200px"
                  v-model="sizeForm.mcount"></el-input>
      </el-form-item>
      <el-form-item label="材料简介">
        <el-input placeholder="请输入材料简介"  type="textarea" :autosize="{ minRows: 2, maxRows: 4} " style="width: 500px"
                  v-model="sizeForm.minfo"></el-input>
      </el-form-item>

      <el-form-item size="large">
        <el-button type="primary" @click="onSubmit">立即发布</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import {mapGetters} from "vuex";
import lmApi from '@/api/lm'

export default {
  data() {
    return {
      sizeForm: {
        lname: '',
        mname: '',
        mcount: '',
        minfo: '',
      }
    };
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    onSubmit() {
      console.log('submit!')
      lmApi.addM(this.sizeForm)
        .then((response) => {
          this.$message({
            message: '发布成功',
            type: 'success'
          });
        })
        .catch((error) => {
          console.log(error);
          this.$message.error('发布失败');
        });
    }
  }
};
</script>
