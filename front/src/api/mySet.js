import request from '@/utils/request'

export default {
  getMyAppointmentList(current, limit) {
    return request({
      url: `/admin/appointment/getAppointmentInfo/${current}/${limit}`,
      method: 'post',
    })
  },
  removeAppointment(uid) {            //删除预约信息
    return request({
      url: `/admin/appointment/deleteAppointment/${uid}`,
      method: 'post',
    })
  },
  searchProgess(uid) {               //查看预约进度
    return request({
      url: `/admin/appointment/searchProgess/${uid}`,
      method: 'post',
    })
  },
  //上传修改信息
  submit(ruleForm) {
    return request({
      url: `/admin/users/user/editInfo`,
      method: 'post',
      data: ruleForm
    })

  }
}
