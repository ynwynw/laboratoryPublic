<template>
  <div class="app-container">
    <el-table
      :data="list"
      style="width: 100%"
      stripe="true">
      <el-table-column align="center" prop="lname" label="实验室名称" width="200"></el-table-column>
      <el-table-column align="center" prop="mname" label="材料名称" width="200">
      </el-table-column>
      <el-table-column align="center" prop="minfo" label="材料简介" width="500">
      </el-table-column>
      <el-table-column align="center" prop="mcount" label="材料数量" width="100"></el-table-column>

    </el-table>

  </div>
</template>

<script>

import lrApi from '@/api/lm'
export default {
  data() {
    return {
      list: []
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      lrApi.lmList()
        .then((response) => {
          this.list = response.data;
          console.log(response)
          console.log('list' + this.list);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
}
</script>


