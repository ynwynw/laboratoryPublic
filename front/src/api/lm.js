import request from '@/utils/request'

export default {
  lmList() {
    return request({
      url: `/admin/member/lr/findList`,
      method: 'get'

    })
  },
  addM(course) {
    return request({
      url: '/admin/member/lr/addM',
      method: 'post',
      data: course
    })
  },
  deleteM(id) {
    return request({
      url: `/admin/member/lr/deleteM/${id}`,
      method: 'get'
    })
  },
  addR(data) {
    return request({
      url: '/admin/member/lr/addR',
      method: 'post',
      data: data
    })
  },
  rpList() {
    return request({
      url: `/admin/member/lr/findAllR`,
      method: 'get'
    })
  },
  deleteR(uuid) {
    return request({
      url: `/admin/member/lr/deleteR/${uuid}`,
      method: 'get'
    })
  },

}
