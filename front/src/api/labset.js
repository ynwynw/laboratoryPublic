import request from '@/utils/request'

export default {
    getLabList(current,limit,searchObj){
      return request({
      url: `/admin/lab/findpage/${current}/${limit}`,
      method: 'post',
       data: searchObj
    })
  }
    ,
    getLabInfo(uid){
      return request({
        url: `/admin/lab/getLab/${uid}`,
        method: 'get'
    })
  },
  addAppointment(AppointInfo) {
    return request({
    url:`/admin/appointment/addAppointment`,
    method: 'post',
    data: AppointInfo
   })
  },
  //查询空余时间
  labFreeTime(timeSearch){
    return request({
      url:`/admin/lab/labFreeTime`,
      method: 'post',
      data:timeSearch
     })
  },
  getEquipmentInfo(equipmentList){
    return request({
      url:`/admin/equipment/findEquTime/`,
      method: 'post',
     data:equipmentList
     })
  },
  getEquipmentTimeInfo(uid){
    return request({
      url:`/admin/lab/labEquip/${uid}`,
      method: 'post',
     
     })
  },
  
  //更新实验室一周的使用表
  labuseUpdate(labuseUpdate){
    return request({
      url:`/admin/timeUpdate/updateLabuse`,
      method: 'post',
     data:labuseUpdate
     })
  },
  equUseUpdate(equipmentList){
    return request({
      url:`/admin/equipment/useThisEqu/`,
      method: 'post',
      data:equipmentList
     })
  },
  labScheduled(uid){
    return request({
      url:`/admin/timeUpdate/searchLabSched/${uid}`,
      method:'get'
     })
  }


}