package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduAvg;
import com.xjk.education.domain.EduMakeup;
import com.xjk.education.domain.vo.EduPlacementVo;
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
import com.xjk.education.domain.EduPlacement;
import com.xjk.education.service.IEduPlacementService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeExamName;
import static com.xjk.education.util.ExportUtil.changeStartTime;

/**
 * 等级考试成绩Controller
 *
 * @author xjk
 * @date 2025-02-03
 */
@RestController
@RequestMapping("/education/placement")
public class EduPlacementController extends BaseController
{
    @Autowired
    private IEduPlacementService eduPlacementService;

    /**
     * 查询等级考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:placement:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduPlacement eduPlacement)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
//        List<EduPlacement> list = eduPlacementService.selectEduPlacementList(eduPlacement);
        if(userid == 1) {
            List<EduPlacementVo> list = eduPlacementService.selectEduUserPlacementList(eduPlacement);
            return getDataTable(list);
        }else {
            eduPlacement.setUserId(userid);
            List<EduPlacementVo> list = eduPlacementService.selectEduUserPlacementList(eduPlacement);
            return getDataTable(list);
        }
    }

    /**
     * 导出等级考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:placement:export')")
    @Log(title = "等级考试成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduPlacement eduPlacement)
    {

        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

        List<EduPlacementVo> resultList = new ArrayList<>();

        if(userid == 1) {
            resultList = eduPlacementService.selectEduUserPlacementList(eduPlacement);
        }else {
            eduPlacement.setUserId(userid);
            resultList = eduPlacementService.selectEduUserPlacementList(eduPlacement);
        }

        // 转换分数为描述
        for (EduPlacementVo vo : resultList) {
            if (vo.getExamName() != null) {
                vo.setExamName(changeExamName(vo.getExamName()));
            }
            if (vo.getStartTime()!=null){
                vo.setStartTime(changeStartTime(vo.getStartTime()));
            }
        }

        ExcelUtil<EduPlacementVo> util = new ExcelUtil<EduPlacementVo>(EduPlacementVo.class);
        util.exportExcel(response, resultList, "等级考试成绩数据");
    }

    /**
     * 获取等级考试成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:placement:query')")
    @GetMapping(value = "/{gradeId}")
    public AjaxResult getInfo(@PathVariable("gradeId") Long gradeId)
    {
        return success(eduPlacementService.selectEduPlacementByGradeId(gradeId));
    }

    /**
     * 新增等级考试成绩
     */
    @PreAuthorize("@ss.hasPermi('education:placement:add')")
    @Log(title = "等级考试成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPlacement eduPlacement)
    {
        return toAjax(eduPlacementService.insertEduPlacement(eduPlacement));
    }

    /**
     * 修改等级考试成绩
     */
    @PreAuthorize("@ss.hasPermi('education:placement:edit')")
    @Log(title = "等级考试成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPlacement eduPlacement)
    {
        return toAjax(eduPlacementService.updateEduPlacement(eduPlacement));
    }

    /**
     * 删除等级考试成绩
     */
    @PreAuthorize("@ss.hasPermi('education:placement:remove')")
    @Log(title = "等级考试成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{gradeIds}")
    public AjaxResult remove(@PathVariable Long[] gradeIds)
    {
        return toAjax(eduPlacementService.deleteEduPlacementByGradeIds(gradeIds));
    }

    @PreAuthorize("@ss.hasPermi('education:placement:add')")
    @Log(title = "等级考试成绩导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduPlacement> util = new ExcelUtil<EduPlacement>(EduPlacement.class);
            List<EduPlacement> list = util.importExcel(file.getInputStream());
            for (EduPlacement eduPlacement : list) {
                eduPlacementService.insertEduPlacement(eduPlacement);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败，文件格式有误");
        }
        return success();
//        return toAjax(eduPlacementService.importInsertEduPlacement(list));
    }

    /**
     * 导出学生成绩导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:placement:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduPlacement> util = new ExcelUtil<EduPlacement>(EduPlacement.class);
        util.importTemplateExcel(response, "学生成绩数据");
    }
}
