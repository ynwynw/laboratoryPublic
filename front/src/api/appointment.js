import request from '@/utils/request'

export default {
 //添加预约
 addAppointment(AppInfo) {
    return request({
    url:`/admin/appointment/addAppointment`,
    method: 'post',
    data: AppInfo
   })
  },
  //显示待审核预约列表
  getAppointmentList(current, limit) {
    return request({
      url: `/admin/appointment/findpage/${current}/${limit}`,
      method: 'post'
    })
  },
  //显示以审核预约列表
  getAppointmentListOld(current, limit) {
    return request({
      url: `/admin/appointment/findpageOld/${current}/${limit}`,
      method: 'post'
    })
  },


  //预约审核
  checkAgree(uid) {
    return request({
      url: `/admin/appointment/agreeAppointment/${uid}`,
      method: 'post',
      
    })
  },
  checkRefuse(uid) {
    return request({
      url: `/admin/appointment/refuseAppointment/${uid}`,
      method: 'post',
     
    })
  },
}