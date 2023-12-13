import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/member/login',
    method: 'post',
    data:data
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/member/getMemberInfo',
    method: 'get',
    params: { token }
    //data:token

  })
}

export function logout() {
  return request({
    url: '/admin/member/logout',
    method: 'post'
  })
}
