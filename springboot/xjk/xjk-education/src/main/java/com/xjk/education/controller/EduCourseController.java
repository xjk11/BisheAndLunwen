package com.xjk.education.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.education.domain.EduSchedule;
import com.xjk.education.domain.vo.EduCourseScheduleVo;
import com.xjk.education.util.ExportUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.xjk.common.annotation.Log;
import com.xjk.common.core.controller.BaseController;
import com.xjk.common.core.domain.AjaxResult;
import com.xjk.common.enums.BusinessType;
import com.xjk.education.domain.EduCourse;
import com.xjk.education.service.IEduCourseService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeDept;

/**
 * 课程管理Controller
 *
 * @author xjk
 * @date 2025-01-23
 */
@RestController
@RequestMapping("/education/course")
public class EduCourseController extends BaseController
{
    @Autowired
    private IEduCourseService eduCourseService;

    /**
     * 查询课程管理列表
     */
    @PreAuthorize("@ss.hasPermi('education:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCourse eduCourse)
    {
        startPage();
        List<EduCourse> list = eduCourseService.selectEduCourseList(eduCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程管理列表
     */
    @PreAuthorize("@ss.hasPermi('education:course:export')")
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCourse eduCourse)
    {
        List<EduCourse> list = eduCourseService.selectEduCourseList(eduCourse);
        for (EduCourse vo : list){
            vo.setDeptId(ExportUtil.changeDept(vo.getDeptId()));
        }

        ExcelUtil<EduCourse> util = new ExcelUtil<EduCourse>(EduCourse.class);
        util.exportExcel(response, list, "课程管理数据");
    }

    /**
     * 获取课程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:course:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Long courseId)
    {
        return success(eduCourseService.selectEduCourseByCourseId(courseId));
    }

    /**
     * 新增课程管理
     */
    @PreAuthorize("@ss.hasPermi('education:course:add')")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCourse eduCourse)
    {
        return toAjax(eduCourseService.insertEduCourse(eduCourse));
    }

    /**
     * 修改课程管理
     */
    @PreAuthorize("@ss.hasPermi('education:course:edit')")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCourse eduCourse)
    {
        return toAjax(eduCourseService.updateEduCourse(eduCourse));
    }

    /**
     * 删除课程管理
     */
    @PreAuthorize("@ss.hasPermi('education:course:remove')")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Long[] courseIds)
    {
        return toAjax(eduCourseService.deleteEduCourseByCourseIds(courseIds));
    }

    @PreAuthorize("@ss.hasPermi('education:course:add')")
    @Log(title = "课程导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduCourse> util = new ExcelUtil<EduCourse>(EduCourse.class);
            List<EduCourse> list = util.importExcel(file.getInputStream());
            for (EduCourse eduCourse : list) {
                eduCourseService.insertEduCourse(eduCourse);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败,文件格式错误");
        }
        return success();
    }

    /**
     * 导出导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:course:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduCourse> util = new ExcelUtil<EduCourse>(EduCourse.class);
        util.importTemplateExcel(response, "数据模板");
    }
}
