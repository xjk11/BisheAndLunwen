import request from '@/utils/request'

// 查询班级平均成绩列表
export function listAvg(query) {
  return request({
    url: '/education/avg/list',
    method: 'get',
    params: query
  })
}

// 查询班级平均成绩详细
export function getAvg(avgId) {
  return request({
    url: '/education/avg/' + avgId,
    method: 'get'
  })
}

// 新增班级平均成绩
export function addAvg(data) {
  return request({
    url: '/education/avg',
    method: 'post',
    data: data
  })
}

// 修改班级平均成绩
export function updateAvg(data) {
  return request({
    url: '/education/avg',
    method: 'put',
    data: data
  })
}

// 删除班级平均成绩
export function delAvg(avgId) {
  return request({
    url: '/education/avg/' + avgId,
    method: 'delete'
  })
}
