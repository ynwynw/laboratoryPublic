<template>
  <div class="app-container">
   <div>
    <span >实验室名称：{{labInfo.labName}}</span>
    <el-divider></el-divider>
    <span>实验室id:{{labInfo.labId}}</span>
     <el-divider></el-divider>
     <span>实验地点:{{labInfo.address}}</span>
     <el-divider></el-divider>
     <span>
       申请人数&nbsp;&nbsp;&nbsp;<el-input-number v-model="AppointInfo.count" @change="handleChange" :min="1" :max="30" label="申请人数(最多30人)"></el-input-number>
     </span>
     <!-- <span>
        申请设备 
        <el-select v-model="AppointInfo.equipment" filterable placeholder="请选择">
       <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
        </el-option>
        </el-select>
       </span>
     -->
      <el-divider></el-divider>
      实验内容
      <br><br>
      <el-input
        type="textarea"
       :autosize="{ minRows: 4, maxRows: 6}"
       placeholder="请输入内容"
        v-model="AppointInfo.description">
        </el-input>
 <br><br>预约日期
       <router-link :to="'/lab/details/' + this.id">
          <el-tooltip class="item" effect="dark" content="点击查看实验室使用时间详情" placement="right">
          <el-button type="info" size="mini" icon="el-icon-info" circle></el-button>
          </el-tooltip>
       </router-link>
 
 <br><br>
      <div class="block">
       <span class="demonstration"></span>
          <el-date-picker
            v-model="AppointInfo.appointDate"
            type="date"
            placeholder="选择日期"
            >
         </el-date-picker>

         <el-select v-model="AppointInfo.appointTime" placeholder="请选择时间">
            <el-option
            v-for="item in times"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled">
           </el-option>
          </el-select> 
       
      
        
        <el-button type="primary" plain @click="dialogTableVisible = true ; getEquipmentInfo()">选择设备</el-button>

    <el-dialog title="设备列表" :visible.sync="dialogTableVisible">
      
     <span>  <el-button type="primary" @click="getEquipmentInfo">刷新设备列表</el-button> </span>

       <el-table :data="equipmentList">
      <el-table-column property="equName" label="设备名" width="150"></el-table-column>
      <el-table-column property="func" label="设备功能" width="200"></el-table-column>
      <el-table-column property="types" label="设备型号"></el-table-column>
      
       <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">

          <el-button type="danger" size="mini" 
           icon="el-icon-info" @click="useThisEquipment(scope.row.equId,scope.row.equName); ">使用</el-button>

              
        </template>
      </el-table-column>

      </el-table>
    </el-dialog>
        
     </div>
     <br><br>
   
     
   
      <el-button type="primary" @click="submitAppointment">提交</el-button>

      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

      <router-link :to="'/lab/list'">
        <el-button type="primary" @click="clear">取消</el-button>
       </router-link>
  </div>
     

  </div>
</template>

<script>
import moment from 'moment';
import labAppointment from '@/api/labset';
export default {
  data() {
    return {
 equipmentList: [ 

 ],
     dialogTableVisible: false,


      equipmentInfo:[],
      equipmentVo:{},
      labInfo:{},
      useThisEqu:{},
      AppointInfo:{
         equipmentIdList:'',
          equipmentNameList:'',
          equIdList:[],
        //  equNameList:[]
      },
      times: [{       //默认时间选择
          value: 1,
          label: '8:00-10:00',
          disabled:false
        }, 
        {
          value: 2,
          label: '10:00-12:00',
          disabled:false
        },
        {
           value: 3,
          label: '14:00-16:00',
          disabled:false
        },
        {
           value: 4,
          label: '16:00-18:00',
          disabled:false
        }
        ],
        id:'',
        i:0
    };
  },
  created() {
    const uid = this.$route.params.uid;
    this.id=uid
    this.getLab(uid);
    //this.getEquipmentInfo(uid)
  },
  methods: {
    //根据id查询,数据回显
    getLab(uid) {
      labAppointment.getLabInfo(uid).then((response) => {
        this.labInfo = response.data;
        const uid=this.labInfo.data.uid
      })
    },

   submitAppointment(){
    this.AppointInfo.labId=this.labInfo.labId
    this.AppointInfo.labName=this.labInfo.labName  //实验室id，实验室名，申请人数，实验内容，日期，时间段 

     let startDate = moment(this.AppointInfo.appointDate).format("YYYY-MM-DD");
      this.AppointInfo.appointDate=startDate

  
     labAppointment.addAppointment(this.AppointInfo)
      .then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "提交成功!",
        })
      })
      .catch((error) => {
          //请求失败
          this.$confirm('该时间已经没有足够容量了', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
          })
        })

      
   },
   
    getEquipmentInfo(){
     //预约时间，预约时间段，实验室uid  获取设备信息

      this.equipmentVo.uid=this.$route.params.uid;
      let startDate = moment(this.AppointInfo.appointDate).format("YYYY-MM-DD");
      this.equipmentVo.date=startDate
      this.equipmentVo.time=this.AppointInfo.appointTime
       let time=this.AppointInfo.appointTime

     labAppointment.getEquipmentInfo(this.equipmentVo).then((response) => {
        this.equipmentList=response.data
      
      })

     
    },
    //使用某设备，更新设备状态  x先存储，后更新
    useThisEquipment(equId,equName){
     this.useThisEqu.equId=equId     //设备id  设备名称
   

     this.AppointInfo.equipmentIdList=this.AppointInfo.equipmentIdList+equId+','
     this.AppointInfo.equipmentNameList=this.AppointInfo.equipmentNameList+equName+';'
     
     this.AppointInfo.equIdList[this.i]=equId
    
     this.i=this.i+1
     
     //alert(this.AppointInfo.equipmentIdList)
     let startDate = moment(this.AppointInfo.appointDate).format("YYYY-MM-DD");
     this.useThisEqu.date=startDate
     this.useThisEqu.time=this.AppointInfo.appointTime

     labAppointment.equUseUpdate(this.useThisEqu)
     .then((response) => {
        this.$message({
            type: "success",
            message: "选择成功!",
          });
      })
     
    }


   

  },
};
</script>
