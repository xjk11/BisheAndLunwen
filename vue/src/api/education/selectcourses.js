import request from '@/utils/request'

// 查询学生选课列表
export function listSelectcourses(query) {
  return request({
    url: '/education/selectcourses/list',
    method: 'get',
    params: query
  })
}

// 查询学生选课详细
export function getSelectcourses(selectId) {
  return request({
    url: '/education/selectcourses/' + selectId,
    method: 'get'
  })
}

// 新增学生选课
export function addSelectcourses(data) {
  return request({
    url: '/education/selectcourses',
    method: 'post',
    data: data
  })
}

// 学生选课
export function studentSelectcourses(data) {
  return request({
    url: '/education/selectcourses/select',
    method: 'post',
    data: data
  })
}

// 修改学生选课
export function updateSelectcourses(data) {
  return request({
    url: '/education/selectcourses',
    method: 'put',
    data: data
  })
}

// 删除学生选课
export function delSelectcourses(selectId) {
  return request({
    url: '/education/selectcourses/' + selectId,
    method: 'delete'
  })
}
