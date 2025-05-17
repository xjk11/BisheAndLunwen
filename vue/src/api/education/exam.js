import request from '@/utils/request'

// 查询考试信息列表
export function listExam(query) {
  return request({
    url: '/education/exam/list',
    method: 'get',
    params: query
  })
}

// 查询考试信息详细
export function getExam(examId) {
  return request({
    url: '/education/exam/' + examId,
    method: 'get'
  })
}

// 新增考试信息
export function addExam(data) {
  return request({
    url: '/education/exam',
    method: 'post',
    data: data
  })
}

// 修改考试信息
export function updateExam(data) {
  return request({
    url: '/education/exam',
    method: 'put',
    data: data
  })
}

// 删除考试信息
export function delExam(examId) {
  return request({
    url: '/education/exam/' + examId,
    method: 'delete'
  })
}
