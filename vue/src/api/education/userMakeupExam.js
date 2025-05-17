import request from '@/utils/request'

// 查询个人补考信息查询列表
export function listUserMakeupExam(query) {
  return request({
    url: '/education/userMakeupExam/list',
    method: 'get',
    params: query
  })
}

// 查询个人补考信息查询详细
export function getUserMakeupExam(userMakeupexamId) {
  return request({
    url: '/education/userMakeupExam/' + userMakeupexamId,
    method: 'get'
  })
}

// 新增个人补考信息查询
export function addUserMakeupExam(data) {
  return request({
    url: '/education/userMakeupExam',
    method: 'post',
    data: data
  })
}

// 修改个人补考信息查询
export function updateUserMakeupExam(data) {
  return request({
    url: '/education/userMakeupExam',
    method: 'put',
    data: data
  })
}

// 删除补考信息用户信息关联
export function delUserMakeupExam(userMakeupexamId) {
  return request({
    url: '/education/userMakeupExam/' + userMakeupexamId,
    method: 'delete'
  })
}
