package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.vo.EduScoresDetailVo;
import com.xjk.education.domain.vo.EduTeaScoreImportModelVo;
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
import com.xjk.education.domain.EduScores;
import com.xjk.education.service.IEduScoresService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeScore;
import static com.xjk.education.util.ExportUtil.changeStartTime;
import static com.xjk.education.util.ExportUtil.changeDept;

/**
 * 学生成绩Controller
 *
 * @author xjk
 * @date 2025-02-02
 */
@RestController
@RequestMapping("/education/scores")
public class EduScoresController extends BaseController
{
    @Autowired
    private IEduScoresService eduScoresService;

    @Autowired
    private IEduTeachersService eduTeachersService;

    /**
     * 查询学生成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:scores:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduScores eduScores) {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        List<EduScoresDetailVo> resultList = new ArrayList<>();

        if (userid == 1) {
            // 如果是管理员
            resultList = eduScoresService.getEduScoresDetails(eduScores);
        } else {
            // 学生用户
            eduScores.setStuUserId(userid);
            eduScores.setTeaUserId(null);
            startPage();
            List<EduScoresDetailVo> stuList = eduScoresService.getEduScoresDetails(eduScores);
            if (!stuList.isEmpty()){
                return getDataTable(stuList);
            }

            // 教师用户
            eduScores.setStuUserId(null);
            eduScores.setTeaUserId(userid);
            startPage();
            List<EduScoresDetailVo> teaList = eduScoresService.getEduScoresDetails(eduScores);
            if (!teaList.isEmpty()) {
                return getDataTable(teaList);
            }
        }
        return getDataTable(resultList);
    }

    /**
     * 导出学生成绩列表
     */
    @PreAuthorize("@ss.hasPermi('education:scores:export')")
    @Log(title = "学生成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduScores eduScores)
    {
//        List<EduScores> list = eduScoresService.selectEduScoresList(eduScores);
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        List<EduScoresDetailVo> resultList = new ArrayList<>();

        if (userid == 1) {
            // 如果是管理员
            resultList = eduScoresService.getEduScoresDetails(eduScores);
        } else {
            // 先根据stuUserId查询
            eduScores.setStuUserId(userid);
            eduScores.setTeaUserId(null);
            List<EduScoresDetailVo> stuList = eduScoresService.getEduScoresDetails(eduScores);
            resultList.addAll(stuList);

            // 再根据teaUserId查询
            eduScores.setStuUserId(null);
            eduScores.setTeaUserId(userid);
            List<EduScoresDetailVo> teaList = eduScoresService.getEduScoresDetails(eduScores);
            resultList.addAll(teaList);
        }

        // 转换分数为描述
        for (EduScoresDetailVo vo : resultList) {
            if (vo.getTotalScore() != null) {
                vo.setTotalScore(changeScore(vo.getTotalScore()));
            }
            if (vo.getAcademicYear()!=null){
                vo.setAcademicYear(changeStartTime(vo.getAcademicYear()));
            }
            if (vo.getDepartmentId()!=null){
                vo.setDepartmentId(changeDept(vo.getDepartmentId()));
            }
        }

        ExcelUtil<EduScoresDetailVo> util = new ExcelUtil<EduScoresDetailVo>(EduScoresDetailVo.class);
        util.exportExcel(response, resultList, "学生成绩数据");
    }


    /**
     * 获取学生成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:scores:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return success(eduScoresService.selectEduScoresByScoreId(scoreId));
    }

    /**
     * 新增学生成绩
     */
    @PreAuthorize("@ss.hasPermi('education:scores:add')")
    @Log(title = "学生成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduScores eduScores)
    {
        Long userId = SecurityUtils.getUserId();
        if(userId != 1){
            //不是管理员则是教师用户，教师用户需要设置自己的教师ID
            Long teacherId = eduTeachersService.selectTeacherIdByUserId(userId);
            eduScores.setTeacherId(teacherId);
        }
        return toAjax(eduScoresService.insertEduScores(eduScores));
    }

    /**
     * 修改学生成绩
     */
    @PreAuthorize("@ss.hasPermi('education:scores:edit')")
    @Log(title = "学生成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduScores eduScores)
    {
        Long userId = SecurityUtils.getUserId();
        if(userId != 1){
            //不是管理员则是教师用户，教师用户需要设置自己的教师ID
            Long teacherId = eduTeachersService.selectTeacherIdByUserId(userId);
            eduScores.setTeacherId(teacherId);
        }
        return toAjax(eduScoresService.updateEduScores(eduScores));
    }

    /**
     * 删除学生成绩
     */
    @PreAuthorize("@ss.hasPermi('education:scores:remove')")
    @Log(title = "学生成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(eduScoresService.deleteEduScoresByScoreIds(scoreIds));
    }

    @PreAuthorize("@ss.hasPermi('education:scores:add')")
    @Log(title = "学生成绩导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            Long userId = SecurityUtils.getUserId();
            Long teacherId = eduTeachersService.selectTeacherIdByUserId(userId);
//        System.out.println(teacherId);
            if (userId == 1) {
                // 管理员
                ExcelUtil<EduScores> util = new ExcelUtil<EduScores>(EduScores.class);
                List<EduScores> list = util.importExcel(file.getInputStream());
                for (EduScores EduScores : list) {
                    eduScoresService.insertEduScores(EduScores);
                }
            } else {
                ExcelUtil<EduScores> util = new ExcelUtil<EduScores>(EduScores.class);
                List<EduScores> list = util.importExcel(file.getInputStream());
                for (EduScores EduScores : list) {
                    EduScores.setTeacherId(teacherId);
                    eduScoresService.insertEduScores(EduScores);
                }
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败，文件格式有误");
        }
        return success();
//        return toAjax(eduScoresService.importInsertEduScores(eduScoresList));
    }

    /**
     * 导出学生成绩导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:scores:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        Long userId = SecurityUtils.getUserId();
        if (userId == 1) {
            ExcelUtil<EduScores> util = new ExcelUtil<EduScores>(EduScores.class);
            util.importTemplateExcel(response, "学生成绩数据");
        }else {
            ExcelUtil<EduTeaScoreImportModelVo> util = new ExcelUtil<EduTeaScoreImportModelVo>(EduTeaScoreImportModelVo.class);
            util.importTemplateExcel(response, "学生成绩数据");
        }
    }
}
