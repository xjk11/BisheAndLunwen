import request from '@/utils/request'

// 查询等级考试成绩列表
export function listPlacement(query) {
  return request({
    url: '/education/placement/list',
    method: 'get',
    params: query
  })
}

// 查询等级考试成绩详细
export function getPlacement(gradeId) {
  return request({
    url: '/education/placement/' + gradeId,
    method: 'get'
  })
}

// 新增等级考试成绩
export function addPlacement(data) {
  return request({
    url: '/education/placement',
    method: 'post',
    data: data
  })
}

// 修改等级考试成绩
export function updatePlacement(data) {
  return request({
    url: '/education/placement',
    method: 'put',
    data: data
  })
}

// 删除等级考试成绩
export function delPlacement(gradeId) {
  return request({
    url: '/education/placement/' + gradeId,
    method: 'delete'
  })
}
