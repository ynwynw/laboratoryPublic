import request from '@/utils/request'

export default {
  gerUserList(current, limit, searchObj) {
    return request({
      url: `/admin/users/user/findpage/${current}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  //删除医院
  deleteUser(userId) {
    return request({
      url: `/admin/users/user/${userId}`,
      method: 'delete'
    })
  },
  //批量删除
  batchHospSet(idList) {
    return request({
      url: `/admin/users/user/batchRemove`,
      method: 'delete',
      data: idList
    })
  },
  //锁定与解锁
  lockHospSet(id, status) {
    return request({
      url: `/admin/users/user/updateStatus/${id}/${status}`,
      method: 'put'
    })
  },
  //添加医院设置
  saveUser(userSet) {
    return request({
      url:`/admin/users/user/addUser`,
      method: 'post',
      data: userSet
    })
  },
  //修改医院设置
  updateUser(userSet) {
    return request({
      url: `/admin/users/user/updateUsers`,
      method: 'post',
      data: userSet
    })
  },
  //院设置id查询
  getUser(uid) {
    return request({
      url: `/admin/users/user/getUsers/${uid}`,
      method: 'get'
    })


  }
}