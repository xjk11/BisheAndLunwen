package com.xjk.education.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.education.domain.EduStudents;
import com.xjk.education.domain.vo.EduTeaStuExportVo;
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
import com.xjk.education.domain.EduTeaStu;
import com.xjk.education.service.IEduTeaStuService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 师生关系管理Controller
 *
 * @author xjk
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/education/teacherStudentRelationship")
public class EduTeaStuController extends BaseController
{
    @Autowired
    private IEduTeaStuService eduTeaStuService;

    /**
     * 查询师生关系管理列表
     */
    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduTeaStu eduTeaStu)
    {
        startPage();
//        List<EduTeaStu> list = eduTeaStuService.selectEduTeaStuList(eduTeaStu);
        List<EduTeaStu> list = eduTeaStuService.selectEduTeaStuWithNames(eduTeaStu);
        return getDataTable(list);
    }

    /**
     * 导出师生关系管理列表
     */
    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:export')")
    @Log(title = "师生关系管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTeaStu eduTeaStu)
    {
        List<EduTeaStu> list = eduTeaStuService.selectEduTeaStuWithNames(eduTeaStu);
        ExcelUtil<EduTeaStu> util = new ExcelUtil<EduTeaStu>(EduTeaStu.class);
        util.exportExcel(response, list, "师生关系管理数据");
    }

    /**
     * 获取师生关系管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:query')")
    @GetMapping(value = "/{teaStuId}")
    public AjaxResult getInfo(@PathVariable("teaStuId") Long teaStuId)
    {
        return success(eduTeaStuService.selectEduTeaStuByTeaStuId(teaStuId));
    }

    /**
     * 新增师生关系管理
     */
    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:add')")
    @Log(title = "师生关系管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTeaStu eduTeaStu)
    {
        return toAjax(eduTeaStuService.insertEduTeaStu(eduTeaStu));
    }

    /**
     * 修改师生关系管理
     */
    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:edit')")
    @Log(title = "师生关系管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTeaStu eduTeaStu)
    {
        return toAjax(eduTeaStuService.updateEduTeaStu(eduTeaStu));
    }

    /**
     * 删除师生关系管理
     */
    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:remove')")
    @Log(title = "师生关系管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{teaStuIds}")
    public AjaxResult remove(@PathVariable Long[] teaStuIds)
    {
        return toAjax(eduTeaStuService.deleteEduTeaStuByTeaStuIds(teaStuIds));
    }

    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:add')")
    @Log(title = "学生教师关系导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduTeaStu> util = new ExcelUtil<EduTeaStu>(EduTeaStu.class);
            List<EduTeaStu> list = util.importExcel(file.getInputStream());
            for (EduTeaStu eduTeaStu : list) {
                eduTeaStuService.insertEduTeaStu(eduTeaStu);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败，文件格式有误");
        }
        return success();
    }

    /**
     * 导出学生成绩导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:teacherStudentRelationship:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduTeaStuExportVo> util = new ExcelUtil<EduTeaStuExportVo>(EduTeaStuExportVo.class);
        util.importTemplateExcel(response, "数据模板");
    }
}
