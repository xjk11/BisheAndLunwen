import request from '@/utils/request'

// 查询个人课程列表
export function listSchedule(query) {
  return request({
    url: '/education/schedule/list',
    method: 'get',
    params: query
  })
}

// 查询个人课程详细
export function getSchedule(scheduleId) {
  return request({
    url: '/education/schedule/' + scheduleId,
    method: 'get'
  })
}

// 新增个人课程
export function addSchedule(data) {
  return request({
    url: '/education/schedule',
    method: 'post',
    data: data
  })
}

// 修改个人课程
export function updateSchedule(data) {
  return request({
    url: '/education/schedule',
    method: 'put',
    data: data
  })
}

// 删除个人课程
export function delSchedule(scheduleId) {
  return request({
    url: '/education/schedule/' + scheduleId,
    method: 'delete'
  })
}
