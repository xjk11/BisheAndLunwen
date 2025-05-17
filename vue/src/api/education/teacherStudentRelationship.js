import request from '@/utils/request'

// 查询师生关系管理列表
export function listTeacherStudentRelationship(query) {
  return request({
    url: '/education/teacherStudentRelationship/list',
    method: 'get',
    params: query
  })
}

// 查询师生关系管理详细
export function getTeacherStudentRelationship(teaStuId) {
  return request({
    url: '/education/teacherStudentRelationship/' + teaStuId,
    method: 'get'
  })
}

// 新增师生关系管理
export function addTeacherStudentRelationship(data) {
  return request({
    url: '/education/teacherStudentRelationship',
    method: 'post',
    data: data
  })
}

// 修改师生关系管理
export function updateTeacherStudentRelationship(data) {
  return request({
    url: '/education/teacherStudentRelationship',
    method: 'put',
    data: data
  })
}

// 删除师生关系管理
export function delTeacherStudentRelationship(teaStuId) {
  return request({
    url: '/education/teacherStudentRelationship/' + teaStuId,
    method: 'delete'
  })
}
