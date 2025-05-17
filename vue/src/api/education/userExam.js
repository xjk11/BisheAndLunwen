import request from '@/utils/request'

// 查询个人考试信息查询列表
export function listUserExam(query) {
  return request({
    url: '/education/userExam/list',
    method: 'get',
    params: query
  })
}

// 查询考试信息用户信息关联详细
export function getUserExam(userExamId) {
  return request({
    url: '/education/userExam/' + userExamId,
    method: 'get'
  })
}

// 新增个人考试信息查询
export function addUserExam(data) {
  return request({
    url: '/education/userExam',
    method: 'post',
    data: data
  })
}

// 修改个人考试信息查询
export function updateUserExam(data) {
  return request({
    url: '/education/userExam',
    method: 'put',
    data: data
  })
}

// 删除考试信息用户信息关联
export function delUserExam(userExamId) {
  return request({
    url: '/education/userExam/' + userExamId,
    method: 'delete'
  })
}
