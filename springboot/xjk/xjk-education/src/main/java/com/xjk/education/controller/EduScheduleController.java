package com.xjk.education.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.core.domain.entity.SysUser;
import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduCourse;
import com.xjk.education.domain.EduTeaStu;
import com.xjk.education.domain.vo.EduCourseScheduleVo;
import com.xjk.education.domain.vo.EduTeaStuExportVo;
import com.xjk.education.service.IEduCourseService;
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
import com.xjk.education.domain.EduSchedule;
import com.xjk.education.service.IEduScheduleService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeClassTime;
import static com.xjk.education.util.ExportUtil.changeStartTime;

/**
 * 个人课程Controller
 *
 * @author xjk
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/education/schedule")
public class EduScheduleController extends BaseController
{
    @Autowired
    private IEduScheduleService eduScheduleService;

    @Autowired
    private IEduCourseService eduCourseService;

    /**
     * 查询个人课程列表
     */
//    @PreAuthorize("@ss.hasPermi('education:schedule:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(EduSchedule eduSchedule)
//    {
//        startPage();
//        List<EduSchedule> list = eduScheduleService.selectEduScheduleList(eduSchedule);
//        return getDataTable(list);
//    }

    @PreAuthorize("@ss.hasPermi('education:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCourse eduCourse)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        SysUser currentUser = SecurityUtils.getLoginUser().getUser();
        System.out.println("当前用户角色信息备注：" + currentUser.getRemark());

        if(userid == 1) {
            //如果是管理员
            List<EduCourseScheduleVo> list = eduCourseService.selectEduCourseScheduleList(eduCourse);
            return getDataTable(list);
        }else{
            eduCourse.setUserId(userid);
            List<EduCourseScheduleVo> list = eduCourseService.selectEduCourseScheduleList(eduCourse);
            return getDataTable(list);
        }
    }

    /**
     * 导出个人课程列表
     */
    @PreAuthorize("@ss.hasPermi('education:schedule:export')")
    @Log(title = "个人课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response,EduCourse eduCourse)
    {
        List<EduCourseScheduleVo> resultList = eduCourseService.selectEduCourseScheduleList(eduCourse);
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        if(userid == 1) {
            //如果是管理员
            resultList = eduCourseService.selectEduCourseScheduleList(eduCourse);
        }else{
            eduCourse.setUserId(userid);
            resultList = eduCourseService.selectEduCourseScheduleList(eduCourse);
        }

        for (EduCourseScheduleVo vo : resultList){
            if (vo.getStartTime() != null) {
                vo.setStartTime(changeStartTime(vo.getStartTime()));
            }
            if (vo.getLessonTime() != null){
                vo.setLessonTime(changeClassTime(vo.getLessonTime()));
            }
        }

        ExcelUtil<EduCourseScheduleVo> util = new ExcelUtil<EduCourseScheduleVo>(EduCourseScheduleVo.class);
        util.exportExcel(response, resultList, "个人课程数据");
    }

    /**
     * 获取个人课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:schedule:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return success(eduScheduleService.selectEduScheduleByScheduleId(scheduleId));
    }

    /**
     * 新增个人课程
     */
    @PreAuthorize("@ss.hasPermi('education:schedule:add')")
    @Log(title = "个人课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduSchedule eduSchedule)
    {
        return toAjax(eduScheduleService.insertEduSchedule(eduSchedule));
    }

    /**
     * 修改个人课程
     */
    @PreAuthorize("@ss.hasPermi('education:schedule:edit')")
    @Log(title = "个人课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduSchedule eduSchedule)
    {
        return toAjax(eduScheduleService.updateEduSchedule(eduSchedule));
    }

    /**
     * 删除个人课程
     */
    @PreAuthorize("@ss.hasPermi('education:schedule:remove')")
    @Log(title = "个人课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds)
    {
        return toAjax(eduScheduleService.deleteEduScheduleByScheduleIds(scheduleIds));
    }

    @PreAuthorize("@ss.hasPermi('education:schedule:add')")
    @Log(title = "个人课程导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduSchedule> util = new ExcelUtil<EduSchedule>(EduSchedule.class);
            List<EduSchedule> list = util.importExcel(file.getInputStream());
            for (EduSchedule eduSchedule : list) {
                eduScheduleService.insertEduSchedule(eduSchedule);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败，文件格式有误");
        }
        return success();
    }

    /**
     * 导出导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:schedule:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduSchedule> util = new ExcelUtil<EduSchedule>(EduSchedule.class);
        util.importTemplateExcel(response, "数据模板");
    }
}
