import request from '@/utils/request'

// 查询课程管理列表
export function listCourse(query) {
  return request({
    url: '/education/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程管理详细
export function getCourse(courseId) {
  return request({
    url: '/education/course/' + courseId,
    method: 'get'
  })
}

// 新增课程管理
export function addCourse(data) {
  return request({
    url: '/education/course',
    method: 'post',
    data: data
  })
}

// 修改课程管理
export function updateCourse(data) {
  return request({
    url: '/education/course',
    method: 'put',
    data: data
  })
}

// 删除课程管理
export function delCourse(courseId) {
  return request({
    url: '/education/course/' + courseId,
    method: 'delete'
  })
}
