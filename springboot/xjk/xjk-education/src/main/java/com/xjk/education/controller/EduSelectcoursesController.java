package com.xjk.education.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduAvg;
import com.xjk.education.domain.EduUserselectcourses;
import com.xjk.education.domain.vo.EduSelectcourseVo;
import com.xjk.education.service.IEduUserselectcoursesService;
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
import com.xjk.education.domain.EduSelectcourses;
import com.xjk.education.service.IEduSelectcoursesService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeStartTime;

/**
 * 学生选课Controller
 *
 * @author xjk
 * @date 2025-03-04
 */
@RestController
@RequestMapping("/education/selectcourses")
public class EduSelectcoursesController extends BaseController
{
    @Autowired
    private IEduSelectcoursesService eduSelectcoursesService;

    @Autowired
    private IEduUserselectcoursesService eduUserselectcoursesService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final String COURSE_CAPACITY_KEY = "course_capacity:";
    private static final String USER_COURSE_KEY = "user_course:";

    private ReentrantLock lock = new ReentrantLock();

    /**
     * 查询学生选课列表
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduSelectcourses eduSelectcourses)
    {
        startPage();
//        List<EduSelectcourses> list = eduSelectcoursesService.selectEduSelectcoursesList(eduSelectcourses);

        //        eduSelectcourses.setStartTime("6"); //可以通过设置限定学年来开启当年选课
        List<EduSelectcourseVo> list = eduSelectcoursesService.selectEduSelectcoursesVoList(eduSelectcourses);
        return getDataTable(list);
    }

    /**
     * 学生选课
     */
//    @PreAuthorize("@ss.hasPermi('education:selectcourses:list')")
//    @Log(title = "学生选课", businessType = BusinessType.INSERT)
//    @PostMapping("/select")
//    @Transactional // 添加事务注解
//    public AjaxResult selectCourse(@RequestBody EduSelectcourses eduSelectcourses) {
//        Long userId = SecurityUtils.getUserId(); // 获取当前登录用户ID
//        Long selectId = eduSelectcourses.getSelectId(); // 获取当前选择的选课ID
//
//        lock.lock();
//        try {
//            // 检查用户是否已经选过该课程
//            if (eduSelectcoursesService.isCourseSelectedByUser(userId, selectId)>0) {
//                return error("您已经选过该课程，不能重复选课");
//            }
//
//            // 检查课程剩余量是否大于0
//            int remainingCapacity = eduSelectcoursesService.getCourseRemainingCapacity(selectId);
//            if (remainingCapacity <= 0) {
//                return error("该课程已满，不能选课");
//            }
//            // 执行选课操作
//            EduUserselectcourses eduUserselectcourses = new EduUserselectcourses();
//            eduUserselectcourses.setUserId(userId);
//            eduUserselectcourses.setSelectId(selectId);
//            // 更新选课信息
//            int result = eduUserselectcoursesService.insertEduUserselectcourses(eduUserselectcourses);
//            // 如果选课成功，更新课程剩余量
//            if (result > 0) {
//                // 更新课程剩余量
//                eduSelectcourses.setRemainingSeats(eduSelectcourses.getRemainingSeats() - 1);
//                // 更新课程剩余量-1
//                eduSelectcoursesService.updateEduSelectcourses(eduSelectcourses);
//                return success("选课成功");
//            } else {
//                return error("选课更新失败");
//            }
//        }finally {
//            lock.unlock();
//        }
//    }


    /**
     * 学生选课
     */
    @PostMapping("/select")
    @Transactional // 添加事务注解，确保选课操作的原子性
    public AjaxResult selectCourse(@RequestBody EduSelectcourses eduSelectcourses) {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        // 获取当前选择的选课ID
        Long selectId = eduSelectcourses.getSelectId();
        // 构建用户课程缓存键的前缀
        String userCourseKeyPrefix = USER_COURSE_KEY + userId + ":";

        // 检查Redis中是否存在该用户的选课记录缓存
        if (!redisTemplate.hasKey(userCourseKeyPrefix + "*")) {
            // 创建一个EduUserselectcourses对象，设置用户ID
            EduUserselectcourses eduUserselectcourseRedis = new EduUserselectcourses();
            eduUserselectcourseRedis.setUserId(userId);
            // 查询该用户的所有选课记录
            List<EduUserselectcourses> userSelectedCourses = eduUserselectcoursesService.selectEduUserselectcoursesList(eduUserselectcourseRedis);

            // 遍历用户的所有选课记录，将每门课程的选课状态缓存到Redis中，有效期为1小时
            for (EduUserselectcourses course : userSelectedCourses) {
                Long selectIdRedis = course.getSelectId();
                String userCourseKey = USER_COURSE_KEY + userId + ":" + selectIdRedis;
                redisTemplate.opsForValue().set(userCourseKey, "1", 1, TimeUnit.HOURS);
            }
        }

        // 获取锁，确保同一时间只有一个线程可以执行选课操作
        lock.lock();
        try {
            // 构建用户课程缓存键
            String userCourseKey = USER_COURSE_KEY + userId + ":" + selectId;
            // 检查Redis中是否存在该用户的选课记录缓存
            if (redisTemplate.hasKey(userCourseKey)) {
                return error("您已经选过该课程，不能重复选课");
            }

            // 构建课程容量缓存键
            String courseCapacityKey = COURSE_CAPACITY_KEY + selectId;
            // 从Redis中获取课程剩余容量
            Integer remainingCapacity = (Integer) redisTemplate.opsForValue().get(courseCapacityKey);
            // 如果Redis中没有课程剩余容量信息，则从数据库中查询并缓存到Redis中，有效期为1小时
            if (remainingCapacity == null) {
                remainingCapacity = eduSelectcoursesService.getCourseRemainingCapacity(selectId);
                redisTemplate.opsForValue().set(courseCapacityKey, remainingCapacity, 1, TimeUnit.HOURS);
            }

            // 检查课程剩余容量是否大于0
            if (remainingCapacity <= 0) {
                return error("该课程已满，不能选课");
            }

            // 创建一个EduUserselectcourses对象，设置用户ID和选课ID
            EduUserselectcourses eduUserselectcourses = new EduUserselectcourses();
            eduUserselectcourses.setUserId(userId);
            eduUserselectcourses.setSelectId(selectId);
            // 执行选课操作，将选课记录插入数据库
            int result = eduUserselectcoursesService.insertEduUserselectcourses(eduUserselectcourses);

            // 如果选课成功，更新课程剩余容量缓存和数据库记录
            if (result > 0) {
                redisTemplate.opsForValue().decrement(courseCapacityKey); // 课程剩余容量减1
                redisTemplate.opsForValue().set(userCourseKey, "1", 1, TimeUnit.HOURS); // 将选课状态缓存到Redis中，有效期为1小时
                // 更新课程剩余量
                eduSelectcourses.setRemainingSeats(eduSelectcourses.getRemainingSeats() - 1);
                // 更新课程剩余量-1
                eduSelectcoursesService.updateEduSelectcourses(eduSelectcourses);
                return success("选课成功");
            } else {
                return error("选课更新失败");
            }
        } finally {
            // 释放锁
            lock.unlock();
        }
    }






    /**
     * 导出学生选课列表
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:export')")
    @Log(title = "学生选课", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduSelectcourses eduSelectcourses)
    {
        List<EduSelectcourseVo> list = eduSelectcoursesService.selectEduSelectcoursesVoList(eduSelectcourses);
        for (EduSelectcourseVo eduSelectcourseVo : list){
            if (eduSelectcourseVo.getStartTime()!=null){
                eduSelectcourseVo.setStartTime(changeStartTime(eduSelectcourseVo.getStartTime()));
            }
        }

        ExcelUtil<EduSelectcourseVo> util = new ExcelUtil<EduSelectcourseVo>(EduSelectcourseVo.class);
        util.exportExcel(response, list, "学生选课数据");
    }

    /**
     * 获取学生选课详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:query')")
    @GetMapping(value = "/{selectId}")
    public AjaxResult getInfo(@PathVariable("selectId") Long selectId)
    {
        return success(eduSelectcoursesService.selectEduSelectcoursesBySelectId(selectId));
    }

    /**
     * 新增学生选课
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:add')")
    @Log(title = "学生选课", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduSelectcourses eduSelectcourses)
    {
        return toAjax(eduSelectcoursesService.insertEduSelectcourses(eduSelectcourses));
    }

    /**
     * 修改学生选课
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:edit')")
    @Log(title = "学生选课修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduSelectcourses eduSelectcourses)
    {
        return toAjax(eduSelectcoursesService.updateEduSelectcourses(eduSelectcourses));
    }

    /**
     * 删除学生选课
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:remove')")
    @Log(title = "学生选课", businessType = BusinessType.DELETE)
	@DeleteMapping("/{selectIds}")
    public AjaxResult remove(@PathVariable Long[] selectIds)
    {
        return toAjax(eduSelectcoursesService.deleteEduSelectcoursesBySelectIds(selectIds));
    }

    /**
     * 导入学生选课信息
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:add')")
    @Log(title = "学生选课信息导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduSelectcourses> util = new ExcelUtil<EduSelectcourses>(EduSelectcourses.class);
            List<EduSelectcourses> list = util.importExcel(file.getInputStream());
            for (EduSelectcourses eduSelectcourses : list) {
                eduSelectcoursesService.insertEduSelectcourses(eduSelectcourses);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败，文件格式有误");
        }
        return success();
    }

    /**
     * 导出学生选课信息导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:selectcourses:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduSelectcourses> util = new ExcelUtil<EduSelectcourses>(EduSelectcourses.class);
        util.importTemplateExcel(response, "导入模型");
    }
}
