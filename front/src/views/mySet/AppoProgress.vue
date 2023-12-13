<template>
    <div>
      <br><br><br><br><br><br><br>




      <el-table
      :data="progess"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column width="150" property="checkFirst" label="user"></el-table-column>
            <el-table-column width="100" property="checkSecond" label="teacher"></el-table-column>
            <el-table-column width="300" property="checkThird" label="admin"></el-table-column>
      </el-table>

<br><br><br><br><br>






      <h2> 预约具体内容</h2>
    </div>
</template>

<script>
import  mySet  from '@/api/mySet'

export default {
  data() {
    return {
        list:[],
         progess:[{
        checkFirst:'1',
        checkSecond:'2',
        checkThird:'3'
      }]

    }
    },
  created() {
       const uid = this.$route.params.uid;
     this.getProgess(uid)
  },
  methods: {

      getProgess(uid){

          mySet.searchProgess(this.$route.params.uid)
          .then((response) => {
          //response接口返回的数据
           var a=response.data.checkFirst;
           var b=response.data.checkSecond;
           var c=response.data.checkThird;
           var status=response.data.status;

           if(a==0&&b==0&&c==0){
               this.progess=1
           }
           else if(a==1&&b==0&&c==0){
               this.progess=2
            }
            else if(a==1&&b==1&&c==0){
               this.progess=3
            }
            else if(a==1&&b==1&&c==1){
               this.progess=4
               this.progess=5;
            }




          console.log(response);

        })
        .catch((error) => {
          //请求失败

          console.log(error);
        });



      }
  }


}
</script>





