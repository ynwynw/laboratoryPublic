import request from '@/utils/request'

export default {
  noticeList() {
    return request({
      url: '/admin/member/notice/findAllNotice',
      method: 'get'
    })
  },
  addNotice(notice) {
    return request({
      url: '/admin/member/notice/addNotice',
      method: 'post',
      data: notice
    })
  },
  getNotice(id) {
    return request({
      url: `/admin/member/notice/getNotice/${id}`,
      method: 'get'
    })
  }
}
