<template>
  <div class="app-container">
   
    <el-table
      :data="list"
       border
       stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      
      <el-table-column prop="week" label="日期" width="180">
      </el-table-column>
      <el-table-column prop="first" label="第一节" width="180">
      </el-table-column>

      <el-table-column prop="second" label="第二节" width="180"> </el-table-column>

      <el-table-column prop="third" label="第三节" width="180">
      </el-table-column>

       <el-table-column prop="fourth" label="第四节" width="180">
      </el-table-column>
       
    

      <!-- <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
           <router-link :to="'/lab/edit/' +  scope.row.week+id">
               <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
          </router-link>
        </template>
      </el-table-column> -->

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
        id:'',
      current: 1, //当前页
      limit: 8, //每页显示记录数
      searchObj: {}, //条件封装对象
      list: [], //每页数据集合
      total: 0,   
  };

  },
  created(){
     const uid = this.$route.params.uid;
     this.id=uid
      this.getList(uid)
  },
  methods: {
      getList(uid){
          lab.labScheduled(uid) .then((response) => {
         this.list  = response.data.records; //给数组赋值
         var templist = response.data.records; //给数组赋值

          for(var i=0;i<templist.length;i++){
              if(templist[i].first==0)
              this.list[i].first='有课'

              if(templist[i].second==0)
              this.list[i].second='有课'
               if(templist[i].third==0)
              this.list[i].third='有课'
               if(templist[i].fourth==0)
              this.list[i].fourth='有课'
             
                
          }

          



          this.total = response.data.total;
          console.log(response);
        })
        .catch((error) => {
          
          console.log(error);
        });
      }
  
  }
 
}


</script>