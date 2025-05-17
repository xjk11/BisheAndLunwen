import request from '@/utils/request'

// 查询学生信息查询列表
export function listSelectStudent(query) {
  return request({
    url: '/education/selectStudent/list',
    method: 'get',
    params: query
  })
}

// 查询学生信息查询详细
export function getSelectStudent(testPage) {
  return request({
    url: '/education/selectStudent/' + testPage,
    method: 'get'
  })
}

// 新增学生信息查询
export function addSelectStudent(data) {
  return request({
    url: '/education/selectStudent',
    method: 'post',
    data: data
  })
}

// 修改学生信息查询
export function updateSelectStudent(data) {
  return request({
    url: '/education/selectStudent',
    method: 'put',
    data: data
  })
}

// 删除学生信息查询
export function delSelectStudent(testPage) {
  return request({
    url: '/education/selectStudent/' + testPage,
    method: 'delete'
  })
}
