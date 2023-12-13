<template>
  <div class="app-container">
    <el-table
      :data="list"
      style="width: 100%"
      stripe="true">
      <el-table-column align="center" prop="title" label="公告标题" width="300"></el-table-column>
      <el-table-column align="center" prop="resume" label="简述" width="500">
      </el-table-column>
      <el-table-column align="center" prop="publisher" label="发布人" width="100">
      </el-table-column>
      <el-table-column align="center" prop="pTime" label="发布时间" width="300"></el-table-column>

      <el-table-column label="详情" width="100">
        <template slot-scope="scope">
          <router-link :to="'/notice/details/' + scope.row.nid">
          <el-button type="info" size="mini">详情</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import noticeApi from '@/api/notice';

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
      noticeApi.noticeList()
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


