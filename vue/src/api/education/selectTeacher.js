import request from '@/utils/request'

// 查询教师信息查询列表
export function listSelectTeacher(query) {
  return request({
    url: '/education/selectTeacher/list',
    method: 'get',
    params: query
  })
}

// 查询教师信息查询详细
export function getSelectTeacher(testPage) {
  return request({
    url: '/education/selectTeacher/' + testPage,
    method: 'get'
  })
}

// 新增教师信息查询
export function addSelectTeacher(data) {
  return request({
    url: '/education/selectTeacher',
    method: 'post',
    data: data
  })
}

// 修改教师信息查询
export function updateSelectTeacher(data) {
  return request({
    url: '/education/selectTeacher',
    method: 'put',
    data: data
  })
}

// 删除教师信息查询
export function delSelectTeacher(testPage) {
  return request({
    url: '/education/selectTeacher/' + testPage,
    method: 'delete'
  })
}
