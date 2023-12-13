<template>
  <div class="app-container">
    <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
      <el-form-item label="公告名称">
        <el-input placeholder="公告" style="width: 300px" v-model="sizeForm.title"></el-input>
      </el-form-item>
      <el-form-item label="公告简述">
        <el-input placeholder="公告简述" style="width: 800px" type="textarea" :autosize="{ minRows: 2, maxRows: 4}"
                  v-model="sizeForm.resume"></el-input>
      </el-form-item>
      <el-form-item label="公告内容">
        <el-input placeholder="请输入公告内容" style="width: 800px" type="textarea" :autosize="{ minRows: 5, maxRows: 10}"
                  v-model="sizeForm.content"></el-input>
      </el-form-item>
      <el-form-item label="发布人">
        <el-input placeholder="请输入发布人，默认admin" style="width: 200px" v-model="sizeForm.publisher"></el-input>
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
import noticeApi from "@/api/notice"

export default {
  data() {
    return {
      sizeForm: {
        title: '',
        resume: '',
        content: '',
        publisher: ''
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
     this.sizeForm.publisher=this.name
      noticeApi.addNotice(this.sizeForm)
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
