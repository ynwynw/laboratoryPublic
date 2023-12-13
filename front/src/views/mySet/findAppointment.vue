<template>
  <div class="app-container">


    <el-table
      :data="list"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column prop="labId" label="实验室id" width="120">
      </el-table-column>
      <el-table-column
        prop="labName"
        label="实验室名称"
        width="100"
      ></el-table-column>
      <el-table-column
        prop="userId"
        label="申请人id"
        width="100"
      ></el-table-column>
      <el-table-column prop="userName" label="申请人姓名" width="150">
      </el-table-column>
      <el-table-column prop="count" label="申请人数" width="150">
      </el-table-column>
      <el-table-column prop="description" label="实验内容" width="150">
      </el-table-column>
      <el-table-column prop="appointDate" label="申请日期" width="150">
      </el-table-column>

      <el-table-column prop="time" label="申请时间" width="150">
      </el-table-column>
      <el-table-column prop="status" label="审核情况" width="150">
      </el-table-column>


      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <!-- <router-link :to="'/my/progress/' + scope.row.uid">
               <el-button type="primary" icon="el-icon-info">查看进度</el-button>
          </router-link> -->
          <el-button @click="removeAppointment(scope.row.uid)" type="danger">取消预约</el-button>

          <el-popover
            placement="right"
            width="600"
            trigger="click"
          >
            <el-button slot="reference" @click="getProgess(scope.row.uid)">查看进度</el-button>

            <el-table :data="progess">
              <el-table-column width="150" property="checkFirst" label="user"></el-table-column>
              <el-table-column width="100" property="checkSecond" label="teacher"></el-table-column>
              <el-table-column width="300" property="checkThird" label="admin"></el-table-column>
            </el-table>

          </el-popover>
        </template>
      </el-table-column>


    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total,prev, pager, next,jumper"
      @current-change="getList"
    />
  </div>
</template>


<script>
import mySet from '@/api/mySet'

export default {
  data() {
    return {
      current: 1, //当前页
      limit: 50, // 每页显示记录数
      list: [], //每页数据集合
      total: 0,
      progess: [{
        checkFirst: '1',
        checkSecond: '2',
        checkThird: '3'
      }]
    };
  },
  created() {
    this.getList()
  },
  methods: {
    getList(page = 1) {
      //1.获取待审核预约列表
      this.current = page;
      mySet.getMyAppointmentList(this.current, this.limit) //调用api中写的方法
        .then((response) => {
          //response接口返回的数据

          this.list = response.data.records; //给数组赋值

          for (var i = 0; i < this.list.length; i++) {
            if (this.list[i].appointTime == 1)
              this.list[i].time = '8:00-10:00'
            if (this.list[i].appointTime == 2)
              this.list[i].time = '10:00-12:00'
            if (this.list[i].appointTime == 3)
              this.list[i].time = '14:00-16:00'
            if (this.list[i].appointTime == 3)
              this.list[i].time = '16:00-18:00'
          }


          this.total = response.data.total;
          console.log(response);
        })
        .catch((error) => {
          //请求失败
          console.log(error);
        });
    },

    removeAppointment(uid) {
      this.$confirm("确定取消预约, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        //确定执行then方法
        //调用接口
        mySet.removeAppointment(uid)
          .then((response) => {
            //提示
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            //刷新页面
            this.getList(1);
          })
          .catch(() => {
            //请求失败
            this.$confirm("已通过的预约不可取消", "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning",
            })

          });

      });
    },
    getProgess(uid) {

      mySet.searchProgess(uid)
        .then((response) => {
          this.progess[0].checkFirst = response.data.checkFirst
          this.progess[0].checkSecond = response.data.checkSecond
          this.progess[0].checkThird = response.data.checkThird


          //response接口返回的数据
          //  var a=response.data.checkFirst;
          //  var b=response.data.checkSecond;
          //  var c=response.data.checkThird;
          //  var status=response.data.status;

          //  if(a==0&&b==0&&c==0){
          //      this.progess=1
          //  }
          //  else if(a==1&&b==0&&c==0){
          //      this.progess=2
          //   }
          //   else if(a==1&&b==1&&c==0){
          //      this.progess=3
          //   }
          //   else if(a==1&&b==1&&c==1){
          //      this.progess=4
          //      this.progess=5;
          //   }


        })
        .catch((error) => {
          //请求失败
          alert('789')
          console.log(error);
        });


    }

  },
};
</script>



