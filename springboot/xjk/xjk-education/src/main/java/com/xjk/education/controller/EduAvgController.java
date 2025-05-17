package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduCourse;
import com.xjk.education.domain.EduScores;
import com.xjk.education.domain.EduTeachers;
import com.xjk.education.domain.vo.EduAvgVo;
import com.xjk.education.domain.vo.EduScoresDetailVo;
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
import com.xjk.education.domain.EduAvg;
import com.xjk.education.service.IEduAvgService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 班级平均成绩Controller
 *
 * @author xjk
 * @date 2025-02-03
 */
@RestController
@RequestMapping("/education/avg")
public class EduAvgController extends BaseController
{
    @Autowired
    private IEduAvgService eduAvgService;

    /**
     * 查询班级平均成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:avg:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduAvg eduAvg)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

//        List<EduAvg> list = eduAvgService.selectEduAvgList(eduAvg);
        if(userid == 1){
            List<EduAvgVo> list = eduAvgService.selectAverageScoreByClassCourseTeacher(eduAvg);
            return getDataTable(list);
        }else {
            eduAvg.setTeacherUserId(userid);
            List<EduAvgVo> list = eduAvgService.selectAverageScoreByClassCourseTeacher(eduAvg);
            return getDataTable(list);
        }
    }

    /**
     * 导出班级平均成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:avg:export')")
    @Log(title = "班级平均成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAvg eduAvg)
    {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

        List<EduAvgVo> resultList = new ArrayList<>();

        if(userid == 1){
            resultList = eduAvgService.selectAverageScoreByClassCourseTeacher(eduAvg);
        }else {
            eduAvg.setTeacherUserId(userid);
            resultList = eduAvgService.selectAverageScoreByClassCourseTeacher(eduAvg);
        }
        ExcelUtil<EduAvgVo> util = new ExcelUtil<EduAvgVo>(EduAvgVo.class);
        util.exportExcel(response, resultList, "班级平均成绩数据");
//        List<EduAvg> list = eduAvgService.selectEduAvgList(eduAvg);
//        ExcelUtil<EduAvg> util = new ExcelUtil<EduAvg>(EduAvg.class);
//        util.exportExcel(response, list, "班级平均成绩数据");
    }

    /**
     * 获取班级平均成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:avg:query')")
    @GetMapping(value = "/{avgId}")
    public AjaxResult getInfo(@PathVariable("avgId") Long avgId)
    {
        return success(eduAvgService.selectEduAvgByAvgId(avgId));
    }

    /**
     * 新增班级平均成绩
     */
    @PreAuthorize("@ss.hasPermi('education:avg:add')")
    @Log(title = "班级平均成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAvg eduAvg)
    {
        return toAjax(eduAvgService.insertEduAvg(eduAvg));
    }

    /**
     * 修改班级平均成绩
     */
    @PreAuthorize("@ss.hasPermi('education:avg:edit')")
    @Log(title = "班级平均成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAvg eduAvg)
    {
        return toAjax(eduAvgService.updateEduAvg(eduAvg));
    }

    /**
     * 删除班级平均成绩
     */
    @PreAuthorize("@ss.hasPermi('education:avg:remove')")
    @Log(title = "班级平均成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{avgIds}")
    public AjaxResult remove(@PathVariable Long[] avgIds)
    {
        return toAjax(eduAvgService.deleteEduAvgByAvgIds(avgIds));
    }

    /**
     * 导入班级平均成绩
     */
    @PreAuthorize("@ss.hasPermi('education:avg:add')")
    @Log(title = "教师信息导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduAvg> util = new ExcelUtil<EduAvg>(EduAvg.class);
            List<EduAvg> list = util.importExcel(file.getInputStream());
            for (EduAvg eduAvg : list) {
                eduAvgService.insertEduAvg(eduAvg);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败,文件格式错误");
        }
        return success();
    }

    /**
     * 导出班级平均信息导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:avg:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduAvg> util = new ExcelUtil<EduAvg>(EduAvg.class);
        util.importTemplateExcel(response, "导入模型");
    }
}
