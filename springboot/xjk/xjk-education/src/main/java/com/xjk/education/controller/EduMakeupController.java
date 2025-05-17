package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduScores;
import com.xjk.education.domain.vo.EduAvgVo;
import com.xjk.education.domain.vo.EduMakeupTeacherImportVo;
import com.xjk.education.domain.vo.EduScoresDetailVo;
import com.xjk.education.domain.vo.EduStuMakeupVo;
import com.xjk.education.service.IEduTeachersService;
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
import com.xjk.education.domain.EduMakeup;
import com.xjk.education.service.IEduMakeupService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeScore;
import static com.xjk.education.util.ExportUtil.changeStartTime;

/**
 * 补考成绩Controller
 *
 * @author xjk
 * @date 2025-02-03
 */
@RestController
@RequestMapping("/education/makeup")
public class EduMakeupController extends BaseController
{
    @Autowired
    private IEduMakeupService eduMakeupService;

    @Autowired
    private IEduTeachersService eduTeachersService;

    /**
     * 查询补考成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:makeup:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduMakeup eduMakeup)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
//        List<EduMakeup> list = eduMakeupService.selectEduMakeupList(eduMakeup);
        List<EduStuMakeupVo> list = new ArrayList<>();
        if(userid == 1){
            //管理员
            list = eduMakeupService.selectStuEduMakeupList(eduMakeup);
        }else {
            eduMakeup.setUserId(userid);
            startPage();
            List<EduStuMakeupVo> stuList = eduMakeupService.selectStuEduMakeupList(eduMakeup);
            if (!stuList.isEmpty()){
                return getDataTable(stuList);
            }
            startPage();
            List<EduStuMakeupVo> teaList = eduMakeupService.selectTeaEduMakeupList(eduMakeup);
            if(!teaList.isEmpty()){
                return getDataTable(teaList);
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出补考成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:makeup:export')")
    @Log(title = "补考成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduMakeup eduMakeup)
    {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

        List<EduStuMakeupVo> resultList = new ArrayList<>();

        if(userid == 1){
            resultList = eduMakeupService.selectStuEduMakeupList(eduMakeup);
        }else {
            eduMakeup.setUserId(userid);
            resultList = eduMakeupService.selectStuEduMakeupList(eduMakeup);
        }

        // 转换分数为描述
        for (EduStuMakeupVo vo : resultList) {
            if (vo.getMakeupScore() != null) {
                vo.setMakeupScore(changeScore(vo.getMakeupScore()));
            }
        }

        // 转换开课学年为描述
        for (EduStuMakeupVo vo : resultList) {
            if (vo.getStartTime() != null) {
                vo.setStartTime(changeStartTime(vo.getStartTime()));
            }
        }

        ExcelUtil<EduStuMakeupVo> util = new ExcelUtil<EduStuMakeupVo>(EduStuMakeupVo.class);
        util.exportExcel(response, resultList, "补考成绩数据");

//        List<EduMakeup> list = eduMakeupService.selectEduMakeupList(eduMakeup);
//        ExcelUtil<EduMakeup> util = new ExcelUtil<EduMakeup>(EduMakeup.class);
//        util.exportExcel(response, list, "补考成绩数据");
    }

    /**
     * 获取补考成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:makeup:query')")
    @GetMapping(value = "/{makeupId}")
    public AjaxResult getInfo(@PathVariable("makeupId") Long makeupId)
    {
        return success(eduMakeupService.selectEduMakeupByMakeupId(makeupId));
    }

    /**
     * 新增补考成绩
     */
    @PreAuthorize("@ss.hasPermi('education:makeup:add')")
    @Log(title = "补考成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduMakeup eduMakeup)
    {
        Long userId = SecurityUtils.getUserId();
        if(userId != 1){
            //不是管理员则是教师用户，教师用户需要设置自己的教师ID
            Long teacherId = eduTeachersService.selectTeacherIdByUserId(userId);
            eduMakeup.setTeacherId(teacherId);
        }
        return toAjax(eduMakeupService.insertEduMakeup(eduMakeup));
    }

    /**
     * 修改补考成绩
     */
    @PreAuthorize("@ss.hasPermi('education:makeup:edit')")
    @Log(title = "补考成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduMakeup eduMakeup)
    {
        Long userId = SecurityUtils.getUserId();
        if(userId != 1){
            //不是管理员则是教师用户，教师用户需要设置自己的教师ID
            Long teacherId = eduTeachersService.selectTeacherIdByUserId(userId);
            eduMakeup.setTeacherId(teacherId);
        }
        return toAjax(eduMakeupService.updateEduMakeup(eduMakeup));
    }

    /**
     * 删除补考成绩
     */
    @PreAuthorize("@ss.hasPermi('education:makeup:remove')")
    @Log(title = "补考成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{makeupIds}")
    public AjaxResult remove(@PathVariable Long[] makeupIds)
    {
        return toAjax(eduMakeupService.deleteEduMakeupByMakeupIds(makeupIds));
    }

    @PreAuthorize("@ss.hasPermi('education:makeup:add')")
    @Log(title = "补考成绩导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            Long userId = SecurityUtils.getUserId();
            Long teacherId = eduTeachersService.selectTeacherIdByUserId(userId);
            if (userId == 1) {
                //管理员
                ExcelUtil<EduMakeup> util = new ExcelUtil<EduMakeup>(EduMakeup.class);
                List<EduMakeup> list = util.importExcel(file.getInputStream());
                for (EduMakeup makeup : list) {
                    eduMakeupService.insertEduMakeup(makeup);
                }
            } else {
                //教师用户
                ExcelUtil<EduMakeup> util = new ExcelUtil<EduMakeup>(EduMakeup.class);
                List<EduMakeup> list = util.importExcel(file.getInputStream());
                for (EduMakeup makeup : list) {
                    makeup.setTeacherId(teacherId);
                    eduMakeupService.insertEduMakeup(makeup);
                }
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败,文件格式错误");
        }
//        return toAjax(eduMakeupService.importInsertEduMakeup(list));
        return success();
    }

    /**
     * 导出学生成绩导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:makeup:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        Long userId = SecurityUtils.getUserId();
        if(userId == 1) {
            ExcelUtil<EduMakeup> util = new ExcelUtil<EduMakeup>(EduMakeup.class);
            util.importTemplateExcel(response, "导出数据模型");
        }else {
            ExcelUtil<EduMakeupTeacherImportVo> util = new ExcelUtil<EduMakeupTeacherImportVo>(EduMakeupTeacherImportVo.class);
            util.importTemplateExcel(response, "导出数据模型");
        }
    }
}
