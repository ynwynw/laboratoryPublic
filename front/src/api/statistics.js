import request from '@/utils/request'

export default {
    //1.生成统一数据
  createDate(day) {
    return request({
      url: `/admin/appointment/countAppointment/`+day,
      method: 'post', 
    })
  }
  ,
  getDataSta(searchObj) {
    return request({
      url: `/admin/appointment/showData/`,
      method: 'post', 
      data:searchObj
    })
  }
}