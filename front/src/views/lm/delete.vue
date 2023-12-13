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
      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini"
                     icon="el-icon-info" @click="removeDataById(scope.row.uuid)">删除材料
          </el-button>

        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>

import lrApi from '@/api/lm'
import courseApi from "@/api/course";

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
    },
    removeDataById(uuid) {
      this.$confirm("是否删除该材料?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        lrApi.deleteM(uuid)
          .then((response) => {
            this.$message({
              type: "success",
              message: "删除成功!",
            })

            this.getList();
          });
      });
      }
    }

}
</script>


