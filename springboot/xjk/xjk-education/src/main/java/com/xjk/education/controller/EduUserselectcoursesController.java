package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduSelectcourses;
import com.xjk.education.domain.vo.EduSelectcourseVo;
import com.xjk.education.service.IEduSelectcoursesService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xjk.common.annotation.Log;
import com.xjk.common.core.controller.BaseController;
import com.xjk.common.core.domain.AjaxResult;
import com.xjk.common.enums.BusinessType;
import com.xjk.education.domain.EduUserselectcourses;
import com.xjk.education.service.IEduUserselectcoursesService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeStartTime;

/**
 * 个人选课信息查询Controller
 *
 * @author xjk
 * @date 2025-03-04
 */
@RestController
@RequestMapping("/education/userselectcourses")
public class EduUserselectcoursesController extends BaseController
{
    @Autowired
    private IEduUserselectcoursesService eduUserselectcoursesService;

    @Autowired
    private IEduSelectcoursesService eduSelectcoursesService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final String COURSE_CAPACITY_KEY = "course_capacity:";
    private static final String USER_COURSE_KEY = "user_course:";

    /**
     * 查询个人选课信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:list')")
    @GetMapping("/list")
//    public TableDataInfo list(EduUserselectcourses eduUserselectcourses)
    public TableDataInfo list(EduSelectcourses eduUserselectcourses)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
//        List<EduUserselectcourses> list = eduUserselectcoursesService.selectEduUserselectcoursesList(eduUserselectcourses);
        if(userid == 1) {
            //管理员
            List<EduSelectcourseVo> list = eduUserselectcoursesService.selectEduUserSelectcoursesVoList(eduUserselectcourses);
            return getDataTable(list);
        }else {
            eduUserselectcourses.setUserId(userid);
            List<EduSelectcourseVo> list = eduUserselectcoursesService.selectEduUserSelectcoursesVoList(eduUserselectcourses);
            return getDataTable(list);
       }
    }

    /**
     * 导出个人选课信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:export')")
    @Log(title = "个人选课信息查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduSelectcourses eduUserselectcourses)
    {
        List<EduSelectcourseVo> resultList = new ArrayList<>();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        if(userid == 1) {
            //管理员
            resultList = eduUserselectcoursesService.selectEduUserSelectcoursesVoList(eduUserselectcourses);
        }else {
            eduUserselectcourses.setUserId(userid);
            resultList = eduUserselectcoursesService.selectEduUserSelectcoursesVoList(eduUserselectcourses);
        }

        for(EduSelectcourseVo eduSelectcourseVo : resultList){
            if(eduSelectcourseVo.getStartTime()!=null){
                eduSelectcourseVo.setStartTime(changeStartTime(eduSelectcourseVo.getStartTime()));
            }
        }

        ExcelUtil<EduSelectcourseVo> util = new ExcelUtil<EduSelectcourseVo>(EduSelectcourseVo.class);
        util.exportExcel(response, resultList, "个人选课信息查询数据");
    }

    /**
     * 获取个人选课信息查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:query')")
    @GetMapping(value = "/{userSelectId}")
    public AjaxResult getInfo(@PathVariable("userSelectId") Long userSelectId)
    {
        return success(eduUserselectcoursesService.selectEduUserselectcoursesByUserSelectId(userSelectId));
    }

    /**
     * 新增个人选课信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:add')")
    @Log(title = "个人选课信息查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduUserselectcourses eduUserselectcourses)
    {
        return toAjax(eduUserselectcoursesService.insertEduUserselectcourses(eduUserselectcourses));
    }

    /**
     * 修改个人选课信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:edit')")
    @Log(title = "个人选课信息查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduUserselectcourses eduUserselectcourses)
    {
        return toAjax(eduUserselectcoursesService.updateEduUserselectcourses(eduUserselectcourses));
    }

    /**
     * 删除个人选课信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:delete')")
    @Log(title = "个人选课信息查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userSelectIds}")
    public AjaxResult remove(@PathVariable Long[] userSelectIds)
    {
        return toAjax(eduUserselectcoursesService.deleteEduUserselectcoursesByUserSelectIds(userSelectIds));
    }

    /**
     * 取消选课
     */
//    @PreAuthorize("@ss.hasPermi('education:userselectcourses:remove')")
//    @Log(title = "个人选课信息查询（退选处）", businessType = BusinessType.DELETE)
//    @DeleteMapping("/cancel/{userSelectId}")
//    @Transactional // 添加事务注解
//    public synchronized AjaxResult cancelCourse(@PathVariable("userSelectId") Long userSelectId) {
//        // 获取用户选课记录
//        EduUserselectcourses eduUserselectcourses = eduUserselectcoursesService.selectEduUserselectcoursesByUserSelectId(userSelectId);
//        // 获取选课ID
//        Long selectId = eduUserselectcourses.getSelectId();
//
//        // 删除用户选课记录
//        eduUserselectcoursesService.deleteEduUserselectcoursesByUserSelectId(userSelectId);
//
//        // 更新课程剩余容量
//        EduSelectcourses eduSelectcourses = eduSelectcoursesService.selectEduSelectcoursesBySelectId(selectId);
//        // 课程剩余容量+1
//        eduSelectcourses.setRemainingSeats(eduSelectcourses.getRemainingSeats() + 1);
//        eduSelectcoursesService.updateEduSelectcourses(eduSelectcourses);
//        return success("取消选课成功");
//    }
    @DeleteMapping("/cancel/{userSelectId}")
    @Transactional // 添加事务注解
    public synchronized AjaxResult cancelCourse(@PathVariable("userSelectId") Long userSelectId) {
        // 获取用户选课记录
        EduUserselectcourses eduUserselectcourses = eduUserselectcoursesService.selectEduUserselectcoursesByUserSelectId(userSelectId);
        // 获取选课ID
        Long selectId = eduUserselectcourses.getSelectId();
        // 获取用户ID
        Long userId = eduUserselectcourses.getUserId();

        // 删除用户选课记录
        eduUserselectcoursesService.deleteEduUserselectcoursesByUserSelectId(userSelectId);

        // 更新课程剩余容量
        EduSelectcourses eduSelectcourses = eduSelectcoursesService.selectEduSelectcoursesBySelectId(selectId);
        // 课程剩余容量+1
        eduSelectcourses.setRemainingSeats(eduSelectcourses.getRemainingSeats() + 1);
        eduSelectcoursesService.updateEduSelectcourses(eduSelectcourses);

        // 清除Redis中的课程剩余容量缓存
        String courseCapacityKey = COURSE_CAPACITY_KEY + selectId;
        redisTemplate.delete(courseCapacityKey);

        // 清除Redis中的用户选课状态缓存
        String userCourseKey = USER_COURSE_KEY + userId + ":" + selectId;
        redisTemplate.delete(userCourseKey);

        return success("取消选课成功");
    }


    /**
     * 导入学生个人选课信息
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:add')")
    @Log(title = "学生选课信息导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduUserselectcourses> util = new ExcelUtil<EduUserselectcourses>(EduUserselectcourses.class);
            List<EduUserselectcourses> list = util.importExcel(file.getInputStream());
            for (EduUserselectcourses eduUserselectcourses : list) {
                eduUserselectcoursesService.insertEduUserselectcourses(eduUserselectcourses);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败,文件格式有误");
        }
        return success();
    }

    /**
     * 导出学生选课信息导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:userselectcourses:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduUserselectcourses> util = new ExcelUtil<EduUserselectcourses>(EduUserselectcourses.class);
        util.importTemplateExcel(response, "导入模型");
    }
}
