import request from '@/utils/request'

// 查询补考成绩列表
export function listMakeup(query) {
  return request({
    url: '/education/makeup/list',
    method: 'get',
    params: query
  })
}

// 查询补考成绩详细
export function getMakeup(makeupId) {
  return request({
    url: '/education/makeup/' + makeupId,
    method: 'get'
  })
}

// 新增补考成绩
export function addMakeup(data) {
  return request({
    url: '/education/makeup',
    method: 'post',
    data: data
  })
}

// 修改补考成绩
export function updateMakeup(data) {
  return request({
    url: '/education/makeup',
    method: 'put',
    data: data
  })
}

// 删除补考成绩
export function delMakeup(makeupId) {
  return request({
    url: '/education/makeup/' + makeupId,
    method: 'delete'
  })
}
