package com.xjk.education.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.education.domain.EduMakeup;
import com.xjk.education.domain.vo.EduPlacementVo;
import com.xjk.education.domain.vo.EduStudentImportVo;
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
import com.xjk.education.domain.EduStudents;
import com.xjk.education.service.IEduStudentsService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeDept;
import static com.xjk.education.util.ExportUtil.changeSex;

/**
 * 学生信息Controller
 *
 * @author xjk
 * @date 2025-01-22
 */
@RestController
@RequestMapping("/education/student")
public class EduStudentsController extends BaseController
{
    @Autowired
    private IEduStudentsService eduStudentsService;

    /**
     * 查询学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('education:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduStudents eduStudents)
    {
        startPage();
        List<EduStudents> list = eduStudentsService.selectEduStudentsList(eduStudents);
        return getDataTable(list);
    }


    /**
     * 导出学生信息列表
     */

    @PreAuthorize("@ss.hasPermi('education:student:export')")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduStudents eduStudents)
    {
        List<EduStudents> list = eduStudentsService.selectEduStudentsList(eduStudents);

        for (EduStudents vo : list) {
            if (vo.getGender() != null) {
                vo.setGender(changeSex(vo.getGender()));
            }
            if (vo.getDeptId() != null) {
                vo.setDeptId(changeDept(vo.getDeptId()));
            }
        }

        ExcelUtil<EduStudents> util = new ExcelUtil<EduStudents>(EduStudents.class);
        util.exportExcel(response, list, "学生信息数据");
    }

    /**
     * 获取学生信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return success(eduStudentsService.selectEduStudentsByStudentId(studentId));
    }

    /**
     * 新增学生信息
     */
    @PreAuthorize("@ss.hasPermi('education:student:add')")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduStudents eduStudents)
    {
        return toAjax(eduStudentsService.insertEduStudents(eduStudents));
    }

    /**
     * 修改学生信息
     */
    @PreAuthorize("@ss.hasPermi('education:student:edit')")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduStudents eduStudents)
    {
        return toAjax(eduStudentsService.updateEduStudents(eduStudents));
    }

    /**
     * 删除学生信息
     */
    @PreAuthorize("@ss.hasPermi('education:student:remove')")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(eduStudentsService.deleteEduStudentsByStudentIds(studentIds));
    }

    @PreAuthorize("@ss.hasPermi('education:student:add')")
    @Log(title = "学生导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduStudents> util = new ExcelUtil<EduStudents>(EduStudents.class);
            List<EduStudents> list = util.importExcel(file.getInputStream());
            for (EduStudents eduStudents : list) {
                eduStudentsService.insertEduStudents(eduStudents);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败，文件格式有误");
        }
        return success();
    }

    /**
     * 导出学生成绩导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:student:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduStudentImportVo> util = new ExcelUtil<EduStudentImportVo>(EduStudentImportVo.class);
        util.importTemplateExcel(response, "数据模板");
    }
}
