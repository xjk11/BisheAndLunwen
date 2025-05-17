package com.xjk.education.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.education.domain.EduAvg;
import com.xjk.education.domain.EduPlacement;
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
import com.xjk.education.domain.EduTeachers;
import com.xjk.education.service.IEduTeachersService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeSex;
import static com.xjk.education.util.ExportUtil.changeDept;

/**
 * 教师信息Controller
 *
 * @author xjk
 * @date 2025-01-22
 */
@RestController
@RequestMapping("/education/teacher")
public class EduTeachersController extends BaseController
{
    @Autowired
    private IEduTeachersService eduTeachersService;

    /**
     * 查询教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('education:teacher:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduTeachers eduTeachers)
    {
        startPage();
        List<EduTeachers> list = eduTeachersService.selectEduTeachersList(eduTeachers);
        return getDataTable(list);
    }

    /**
     * 导出教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('education:teacher:export')")
    @Log(title = "教师信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTeachers eduTeachers)
    {
        List<EduTeachers> list = eduTeachersService.selectEduTeachersList(eduTeachers);
        for (EduTeachers eduTeacher : list) {
            if (eduTeacher.getGender() != null) {
                eduTeacher.setGender(changeSex(eduTeacher.getGender()));
            }
            if (eduTeacher.getDeptId() != null){
                eduTeacher.setDeptId(changeDept(eduTeacher.getDeptId()));
            }
        }
        ExcelUtil<EduTeachers> util = new ExcelUtil<EduTeachers>(EduTeachers.class);
        util.exportExcel(response, list, "教师信息数据");
    }

    /**
     * 获取教师信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:teacher:query')")
    @GetMapping(value = "/{teacherId}")
    public AjaxResult getInfo(@PathVariable("teacherId") Long teacherId)
    {
        return success(eduTeachersService.selectEduTeachersByTeacherId(teacherId));
    }

    /**
     * 新增教师信息
     */
    @PreAuthorize("@ss.hasPermi('education:teacher:add')")
    @Log(title = "教师信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTeachers eduTeachers)
    {
        return toAjax(eduTeachersService.insertEduTeachers(eduTeachers));
    }

    /**
     * 修改教师信息
     */
    @PreAuthorize("@ss.hasPermi('education:teacher:edit')")
    @Log(title = "教师信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTeachers eduTeachers)
    {
        return toAjax(eduTeachersService.updateEduTeachers(eduTeachers));
    }

    /**
     * 删除教师信息
     */
    @PreAuthorize("@ss.hasPermi('education:teacher:remove')")
    @Log(title = "教师信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teacherIds}")
    public AjaxResult remove(@PathVariable Long[] teacherIds)
    {
        return toAjax(eduTeachersService.deleteEduTeachersByTeacherIds(teacherIds));
    }

    @PreAuthorize("@ss.hasPermi('education:teacher:add')")
    @Log(title = "教师信息导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduTeachers> util = new ExcelUtil<EduTeachers>(EduTeachers.class);
            List<EduTeachers> list = util.importExcel(file.getInputStream());
            for (EduTeachers eduTeachers : list) {
                eduTeachersService.insertEduTeachers(eduTeachers);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败，文件格式有误");
        }
        return success();
//        return toAjax(eduTeachersService.importInsertEduTeachers(list));
    }

    /**
     * 导出教师信息导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:teacher:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduTeachers> util = new ExcelUtil<EduTeachers>(EduTeachers.class);
        util.importTemplateExcel(response, "教师数据");
    }
}
