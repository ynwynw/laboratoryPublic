import request from '@/utils/request'

export default {
  gerEquList(current, limit, searchObj) {
    return request({
      url: `/admin/equipment/findpage/${current}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
   //删除医院
   deleteEquipment(uid) {
    return request({
      url: `/admin/equipment/${uid}`,
      method: 'delete'
    })
  },
  //批量删除
  batchEquipment(idList) {
    return request({
      url: `/admin/equipment/batchRemove`,
      method: 'delete',
      data: idList
    })
  },
  //锁定与解锁
  lockEquipment(id, status) {
    return request({
      url: `/admin/equipment/updateStatus/${id}/${status}`,
      method: 'put'
    })
  },
  //添加设备设置
  saveEquipment(equipment) {
    return request({
      url:`/admin/equipment/addEquipment`,
      method: 'post',
      data: equipment
    })
  },
  //更改设备设置
  updateEquipment(equipment) {
    return request({
      url: `/admin/equipment/updateEquipment`,
      method: 'post',
      data: equipment
    })
  },
  //设备id查询
  getEquipment(uid) {
    return request({
      url: `/admin/equipment/getEquipment/${uid}`,
      method: 'get'
    })


  }
}