<template>

   <div>

          <!-- <font color="black" face="宋体" size="5">实验室地址：{{ this.LabInfo.address }}</font>
          <br>
          <font color="black" face="宋体" size="5">实验室容量：{{ this.LabInfo.capacity }}</font>
           <br>
          <font color="black" face="宋体" size="5">实验室功能：{{ this.LabInfo.description }}</font>
            -->


 <div>
   <el-row>
  <el-col :span="18"><div class="grid-content bg-purple">
    <span>
      <font color="black" face="宋体" size="5">
      <h2>实验室名称：{{ this.LabInfo.labName }}</h2>
      </font>
      </span>
    </div>
    </el-col>
  <el-col :span="6"><div class="grid-content bg-purple-light">
    <img src="../image/01.jpg" class="image" style="width: 300px; height: 150px">
    </div></el-col>
</el-row>



    <el-divider></el-divider>
    <span><h2 >
       <font color="black" face="宋体" size="5">
      实验室容量：{{ this.LabInfo.capacity }}  </font></h2>

        </span>
    <el-divider></el-divider>

    <span><h2><font color="black" face="宋体" size="5">实验室功能：{{ this.LabInfo.description }} </font></h2></span>


     <el-divider></el-divider>

  </div>





<!--  实验室空余时间查询 -->

        <div class="block">
        <span class="demonstration">
          <font color="black" face="宋体" size="4">
          实验室空余时间查询:</font>
          </span>
          <el-date-picker
          v-model="timeSearch.nowdate"
          type="date"
           placeholder="选择日期">
         </el-date-picker>


        <el-popover
          placement="right"
          width="400"
          trigger="click">
          <el-table :data="findFreeTime">
            <el-table-column width="150" property="date" label="时间段"></el-table-column>
            <el-table-column width="100" property="labName" label="实验室"></el-table-column>
            <el-table-column width="100" property="isOccupy" label="剩余容量"></el-table-column>

          </el-table>
          <el-button slot="reference" @click="searchFreeTime">查询</el-button>
        </el-popover>

       </div>
          <el-divider></el-divider>
   <!-- 实验室设备查看 -->
    <div>
        设备信息 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <el-button type="primary" @click="table = true">查询</el-button>
        <el-drawer
          title="实验室设备信息!"
          :visible.sync="table"
          direction="rtl"
          size="50%">
          <el-table :data="equipmentInfo">
              <el-table-column property="equName" label="设备名称" width="150"></el-table-column>
              <el-table-column property="types" label="设备型号" width="200"></el-table-column>
               <el-table-column property="func" label="设备功能"></el-table-column>
              <el-table-column property="description" label="设备描述"></el-table-column>


              <el-table-column property="status" label="是否可用"></el-table-column>
            </el-table>
        </el-drawer>

        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
      实验室课表
    <router-link :to="'/lab/scheduled/'+id">
          <el-button type="primary"  >查询</el-button>
    </router-link>
    </div>




  </div>
</template>

<script>
import lab from "@/api/labset";
import moment from 'moment';

export default {
  data() {
    return {
      table :false,
      id:'',
      equipmentInfo:[], //接受设备信息
      LabInfo: {} ,//条件封装对象
      freeTime:{}, //获取返回值
      timeSearch:{},  //传回后端的对象
      findFreeTime:[{
        date:'8:00-10:00',
        labName:'',
        isOccupy:0},
        {
        date:'10:00-12:00',
        labName:'',
        isOccupy:0},
        {
        date:'14:00-16:00',
        labName:'',
        isOccupy:0},
        {
        date:'16:00-18:00',
        labName:'',
        isOccupy:0},
      ],
      labuseUpdate:{}

    };
  },
  created() {
    const uid = this.$route.params.uid;
    this.getLab(uid)
    this.getEquipmentInfo(uid)
     this.id=uid
  },
  methods: {

    //根据id查询,数据回显
    getLab(uid) {
        lab.getLabInfo(uid).then((response) => {
        this.LabInfo = response.data;

      });
    },

    //查询空余时间
    searchFreeTime(){
       this.timeSearch.uid=this.$route.params.uid
       let startDate = moment(this.timeSearch.nowdate).format("YYYY-MM-DD");
       this.timeSearch.nowdate=startDate
      lab.labFreeTime(this.timeSearch)
      .then((response) => {

        this.freeTime= response.data;  //返回数据

       for(var i=0;i<4;i++)
        this.findFreeTime[i].labName=this.timeSearch.nowdate

        this.findFreeTime[0].isOccupy=this.freeTime.firstTime
        this.findFreeTime[1].isOccupy=this.freeTime.secondTime
        this.findFreeTime[2].isOccupy=this.freeTime.thirdTime
        this.findFreeTime[3].isOccupy=this.freeTime.fourthTime


      });
    },
    getEquipmentInfo(uid){

      lab.getEquipmentTimeInfo(uid).then((response) => {
        this.equipmentInfo = response.data;
        for(var i=0;i<this.equipmentInfo.length;i++){
          if(this.equipmentInfo[i].status===0)
         this.equipmentInfo[i].status='可用'
         else
         this.equipmentInfo[i].status='禁用'
        }


      });
    },
    //实验室使用情况更新
    labUseUpdate(){
      var date=Date()
      let today = moment(date).format("YYYY-MM-DD");

       this.labuseUpdate.uid=this.$route.params.uid
       this.labuseUpdate.today=today
       lab.labuseUpdate(this.labuseUpdate)
       .then((response) => {

      alert(this.labuseUpdate.today)
       })
    }
  }
};
</script>
<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }

  .clearfix:after {
      clear: both
  }
</style>
<style>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
</style>
