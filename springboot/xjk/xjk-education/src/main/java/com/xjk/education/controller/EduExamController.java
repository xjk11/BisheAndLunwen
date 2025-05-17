package com.xjk.education.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.education.domain.EduAvg;
import com.xjk.education.domain.vo.EduExamVo;
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
import com.xjk.education.domain.EduExam;
import com.xjk.education.service.IEduExamService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeStartTime;

/**
 * 考试信息Controller
 *
 * @author xjk
 * @date 2025-02-07
 */
@RestController
@RequestMapping("/education/exam")
public class EduExamController extends BaseController
{
    @Autowired
    private IEduExamService eduExamService;

    /**
     * 查询考试信息列表
     */
    @PreAuthorize("@ss.hasPermi('education:exam:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduExam eduExam)
    {
        startPage();
//        List<EduExam> list = eduExamService.selectEduExamList(eduExam);
        List<EduExamVo> list = eduExamService.selectEduExamVoList(eduExam);
        return getDataTable(list);
    }

    /**
     * 导出考试信息列表
     */
    @PreAuthorize("@ss.hasPermi('education:exam:export')")
    @Log(title = "考试信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduExam eduExam)
    {
        List<EduExamVo> list = eduExamService.selectEduExamVoList(eduExam);
        for (EduExamVo eduExamVo : list){
            if(eduExamVo.getStartTime()!=null){
                eduExamVo.setStartTime(changeStartTime(eduExamVo.getStartTime()));
            }
        }
        ExcelUtil<EduExamVo> util = new ExcelUtil<EduExamVo>(EduExamVo.class);
        util.exportExcel(response, list, "考试信息数据");
    }

    /**
     * 获取考试信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:exam:query')")
    @GetMapping(value = "/{examId}")
    public AjaxResult getInfo(@PathVariable("examId") Long examId)
    {
        return success(eduExamService.selectEduExamByExamId(examId));
    }

    /**
     * 新增考试信息
     */
    @PreAuthorize("@ss.hasPermi('education:exam:add')")
    @Log(title = "考试信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduExam eduExam)
    {
        return toAjax(eduExamService.insertEduExam(eduExam));
    }

    /**
     * 修改考试信息
     */
    @PreAuthorize("@ss.hasPermi('education:exam:edit')")
    @Log(title = "考试信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduExam eduExam)
    {
        return toAjax(eduExamService.updateEduExam(eduExam));
    }

    /**
     * 删除考试信息
     */
    @PreAuthorize("@ss.hasPermi('education:exam:remove')")
    @Log(title = "考试信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{examIds}")
    public AjaxResult remove(@PathVariable Long[] examIds)
    {
        return toAjax(eduExamService.deleteEduExamByExamIds(examIds));
    }

    /**
     * 导入考试成绩
     */
    @PreAuthorize("@ss.hasPermi('education:exam:add')")
    @Log(title = "考试信息导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduExam> util = new ExcelUtil<EduExam>(EduExam.class);
            List<EduExam> list = util.importExcel(file.getInputStream());
            for (EduExam eduExam : list) {
                eduExamService.insertEduExam(eduExam);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败,文件格式错误");
        }
        return success();
    }

    /**
     * 导出班级平均信息导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:exam:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduExam> util = new ExcelUtil<EduExam>(EduExam.class);
        util.importTemplateExcel(response, "导入模型");
    }
}
