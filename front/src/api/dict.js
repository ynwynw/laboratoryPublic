import request from '@/utils/request'

export default {
  dictList(id) {
    return request({
      url: `/admin/dict/findChildData/${id}`,
      method: 'get',
      data: id
    })
  },
  exportData(){
    return request({
      url: `/admin/dict/exportData`,
      method: 'get',
     
    })
  }
}