import request from '@/utils/request'

export default {
  courseList(current, limit,searchObj) {
    return request({
      url: `/admin/member/course/findpage/${current}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  addCourse(course) {
    return request({
      url: '/admin/member/course/addCourse',
      method: 'post',
      data: course
    })
  },
  deleteCourse(id) {
    return request({
      url: `/admin/member/course/deleteCourse/${id}`,
      method: 'get'
    })
  },
  addSC(data) {
    return request({
      url: '/admin/member/course/addSC',
      method: 'post',
      data: data
    })
  },
  SCList(id) {
    return request({
      url: `/admin/member/sc/findMySC/${id}`,
      method: 'get'
    })
  },
  deleteSC(uuid) {
    return request({
      url: `/admin/member/sc/deleteMySC/${uuid}`,
      method: 'get'
    })
  },

}
