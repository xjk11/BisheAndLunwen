import request from '@/utils/request'

// 查询个人选课信息查询列表
export function listUserselectcourses(query) {
  return request({
    url: '/education/userselectcourses/list',
    method: 'get',
    params: query
  })
}

// 查询个人选课信息查询详细
export function getUserselectcourses(userSelectId) {
  return request({
    url: '/education/userselectcourses/' + userSelectId,
    method: 'get'
  })
}

// 新增个人选课信息查询
export function addUserselectcourses(data) {
  return request({
    url: '/education/userselectcourses',
    method: 'post',
    data: data
  })
}

// 修改个人选课信息查询
export function updateUserselectcourses(data) {
  return request({
    url: '/education/userselectcourses',
    method: 'put',
    data: data
  })
}

// 删除个人选课信息查询
export function delUserselectcourses(userSelectId) {
  return request({
    url: '/education/userselectcourses/' + userSelectId,
    method: 'delete'
  })
}


export function cancelCourse(userSelectId) {
  return request({
    url: `/education/userselectcourses/cancel/${userSelectId}`,
    method: 'delete'
  });
}
