import request from '@/utils/request'

// 查询成绩信息列表
export function listGrades(query) {
  return request({
    url: '/education/grades/list',
    method: 'get',
    params: query
  })
}

// 查询成绩信息详细
export function getGrades(studentId) {
  return request({
    url: '/education/grades/' + studentId,
    method: 'get'
  })
}

// 新增成绩信息
export function addGrades(data) {
  return request({
    url: '/education/grades',
    method: 'post',
    data: data
  })
}

// 修改成绩信息
export function updateGrades(data) {
  return request({
    url: '/education/grades',
    method: 'put',
    data: data
  })
}

// 删除成绩信息
export function delGrades(studentId) {
  return request({
    url: '/education/grades/' + studentId,
    method: 'delete'
  })
}
