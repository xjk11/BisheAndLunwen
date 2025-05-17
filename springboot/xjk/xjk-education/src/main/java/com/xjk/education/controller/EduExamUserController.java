package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduAvg;
import com.xjk.education.domain.vo.EduExamUserVo;
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
import com.xjk.education.domain.EduExamUser;
import com.xjk.education.service.IEduExamUserService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeStartTime;
/**
 * 个人考试信息查询Controller
 *
 * @author xjk
 * @date 2025-02-07
 */
@RestController
@RequestMapping("/education/userExam")
public class EduExamUserController extends BaseController
{
    @Autowired
    private IEduExamUserService eduExamUserService;

    /**
     * 查询个人考试信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduExamUserVo eduExamUserVo)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
//        List<EduExamUser> list = eduExamUserService.selectEduExamUserList(eduExamUser);
        if(userid == 1) {
            //管理员
            List<EduExamUserVo> list = eduExamUserService.selectEduExamUserVoList(eduExamUserVo);
            return getDataTable(list);
        }else {
            eduExamUserVo.setUserId(userid);
            List<EduExamUserVo> list = eduExamUserService.selectEduExamUserVoList(eduExamUserVo);
            return getDataTable(list);
        }
    }

    /**
     * 导出个人考试信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:export')")
    @Log(title = "个人考试信息查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduExamUserVo eduExamUserVo)
    {
        List<EduExamUserVo> resultList = new ArrayList<>();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        if(userid == 1) {
            //管理员
            resultList = eduExamUserService.selectEduExamUserVoList(eduExamUserVo);
        }else {
            eduExamUserVo.setUserId(userid);
            resultList = eduExamUserService.selectEduExamUserVoList(eduExamUserVo);
        }

        for (EduExamUserVo vo : resultList){
            if (vo.getStartTime() != null){
                vo.setStartTime(changeStartTime(vo.getStartTime()));
            }
        }

        ExcelUtil<EduExamUserVo> util = new ExcelUtil<EduExamUserVo>(EduExamUserVo.class);
        util.exportExcel(response, resultList, "个人考试信息查询数据");
    }

    /**
     * 获取个人考试信息查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:query')")
    @GetMapping(value = "/{userExamId}")
    public AjaxResult getInfo(@PathVariable("userExamId") Long userExamId)
    {
        return success(eduExamUserService.selectEduExamUserByUserExamId(userExamId));
    }

    /**
     * 新增个人考试信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:add')")
    @Log(title = "个人考试信息查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduExamUser eduExamUser)
    {
        return toAjax(eduExamUserService.insertEduExamUser(eduExamUser));
    }

    /**
     * 修改个人考试信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:edit')")
    @Log(title = "个人考试信息查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduExamUser eduExamUser)
    {
        return toAjax(eduExamUserService.updateEduExamUser(eduExamUser));
    }

    /**
     * 删除考试信息用户信息关联
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:remove')")
    @Log(title = "考试信息用户信息关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userExamIds}")
    public AjaxResult remove(@PathVariable Long[] userExamIds)
    {
        return toAjax(eduExamUserService.deleteEduExamUserByUserExamIds(userExamIds));
    }

    /**
     * 导入个人考试信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:add')")
    @Log(title = "个人考试信息查询信息导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduExamUser> util = new ExcelUtil<EduExamUser>(EduExamUser.class);
            List<EduExamUser> list = util.importExcel(file.getInputStream());
            for (EduExamUser eduExamUser : list) {
                eduExamUserService.insertEduExamUser(eduExamUser);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败,文件格式错误");
        }
        return success();
    }

    /**
     * 导出个人考试信息查询导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:userExam:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduExamUser> util = new ExcelUtil<EduExamUser>(EduExamUser.class);
        util.importTemplateExcel(response, "导入模型");
    }
}
