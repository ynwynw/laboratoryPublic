<template>
  <div class="app-container">
    <!-- 条件查询 -->
    <!-- 条件查询 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.labName" placeholder="实验室名称" />
      </el-form-item>


      <el-select v-model="searchObj.func" filterable placeholder="功能">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
         </el-option>
      </el-select>


       <!-- <el-form-item>
        <el-input v-model="searchObj.func" placeholder="功能" />
      </el-form-item> -->
     
      <el-button type="primary" icon="el-icon-search" @click="getList()"
        >查询</el-button>
    </el-form>
    <!-- 工具条 -->
    

    <el-table
      :data="list"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      
      <el-table-column prop="labId" label="实验室id" width="80">
      </el-table-column>
      <el-table-column prop="labName" label="实验室名" width="100">
      </el-table-column>

      <el-table-column prop="address" label="地址" width="100"> </el-table-column>

      <el-table-column prop="capacity" label="容量" width="50">
      </el-table-column>

       <el-table-column prop="func" label="功能" width="200">
      </el-table-column>
       
      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 0 ? "可用" : "禁用" }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">

          <router-link :to="'/lab/details/' + scope.row.uid">
               <el-button type="primary" size="mini" icon="el-icon-info">详情</el-button>
          </router-link>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <router-link :to="'/lab/appointment/' + scope.row.uid">
               <el-button type="primary" size="mini" icon="el-icon-info">预约</el-button>
          </router-link>
          
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
import lab from '@/api/labset';
export default {
  data() {
    return {
      current: 1, //当前页
      limit: 8, //每页显示记录数
      searchObj: {}, //条件封装对象
      list: [], //每页数据集合
      total: 0,
      multipleSelection: [],
      options: [{
          value: '软件开发',
          label: '软件开发'
        }, {
          value: '计算机信息实践',
          label: '计算机信息实践'
        }, {
          value: '网络安全测试',
          label: '网络安全测试'
        }, {
          value: '网络攻防',
          label: '网络攻防'
        }, {
          value: '功能5',
          label: '功能5'
        }],
    };
  },
  created() {
    //一般调用method中的方法调用，当前vue中方法
    this.getList();
  },
  methods: {
    getList(page = 1) {
      //获取用户列表

      this.current = page;

      lab.getLabList(this.current, this.limit, this.searchObj)
        .then((response) => {
          //response接口返回的数据
          this.list = response.data.records; //给数组赋值
          this.total = response.data.total;
          console.log(response);
        })
        .catch((error) => {
          //请求失败
          console.log(error);
        });
    }
    
  }
};
</script>