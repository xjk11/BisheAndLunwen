package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduExamUser;
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
import com.xjk.education.domain.EduMakeupexamUser;
import com.xjk.education.service.IEduMakeupexamUserService;
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.xjk.education.util.ExportUtil.changeStartTime;

/**
 * 个人补考信息查询Controller
 *
 * @author xjk
 * @date 2025-02-07
 */
@RestController
@RequestMapping("/education/userMakeupExam")
public class EduMakeupexamUserController extends BaseController
{
    @Autowired
    private IEduMakeupexamUserService eduMakeupexamUserService;

    /**
     * 查询个人补考信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduExamUserVo eduExamUserVo)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
//        List<EduExamUser> list = eduExamUserService.selectEduExamUserList(eduExamUser);
        if(userid == 1) {
            //管理员
            List<EduExamUserVo> list = eduMakeupexamUserService.selectEduMakeupExamUserVoList(eduExamUserVo);
            return getDataTable(list);
        }else {
            eduExamUserVo.setUserId(userid);
            List<EduExamUserVo> list = eduMakeupexamUserService.selectEduMakeupExamUserVoList(eduExamUserVo);
            return getDataTable(list);
        }
    }

    /**
     * 导出个人补考信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:export')")
    @Log(title = "个人补考信息查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduExamUserVo eduExamUserVo)
    {
        List<EduExamUserVo> resultList = new ArrayList<>();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

        if(userid == 1) {
            //管理员
            resultList = eduMakeupexamUserService.selectEduMakeupExamUserVoList(eduExamUserVo);
        }else {
            eduExamUserVo.setUserId(userid);
            resultList = eduMakeupexamUserService.selectEduMakeupExamUserVoList(eduExamUserVo);
        }

        for (EduExamUserVo vo : resultList) {
            if(vo.getStartTime() != null){
                vo.setStartTime(changeStartTime(vo.getStartTime()));
            }
        }

        ExcelUtil<EduExamUserVo> util = new ExcelUtil<EduExamUserVo>(EduExamUserVo.class);
        util.exportExcel(response, resultList, "个人补考信息查询数据");
    }

    /**
     * 获取补考信息用户信息关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:query')")
    @GetMapping(value = "/{userMakeupexamId}")
    public AjaxResult getInfo(@PathVariable("userMakeupexamId") Long userMakeupexamId)
    {
        return success(eduMakeupexamUserService.selectEduMakeupexamUserByUserMakeupexamId(userMakeupexamId));
    }

    /**
     * 新增个人补考信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:add')")
    @Log(title = "个人补考信息查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduMakeupexamUser eduMakeupexamUser)
    {
        return toAjax(eduMakeupexamUserService.insertEduMakeupexamUser(eduMakeupexamUser));
    }

    /**
     * 修改个人补考信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:edit')")
    @Log(title = "个人补考信息查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduMakeupexamUser eduMakeupexamUser)
    {
        return toAjax(eduMakeupexamUserService.updateEduMakeupexamUser(eduMakeupexamUser));
    }

    /**
     * 删除补考信息用户信息关联
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:remove')")
    @Log(title = "补考信息用户信息关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userMakeupexamIds}")
    public AjaxResult remove(@PathVariable Long[] userMakeupexamIds)
    {
        return toAjax(eduMakeupexamUserService.deleteEduMakeupexamUserByUserMakeupexamIds(userMakeupexamIds));
    }

    /**
     * 导入个人补考信息查询
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:add')")
    @Log(title = "个人补考信息查询信息导入", businessType = BusinessType.IMPORT)
    @Transactional
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception{
        try {
            ExcelUtil<EduMakeupexamUser> util = new ExcelUtil<EduMakeupexamUser>(EduMakeupexamUser.class);
            List<EduMakeupexamUser> list = util.importExcel(file.getInputStream());
            for (EduMakeupexamUser eduMakeupexamUser : list) {
                eduMakeupexamUserService.insertEduMakeupexamUser(eduMakeupexamUser);
            }
        }catch (Exception e){
            return AjaxResult.error("导入失败,文件格式错误");
        }
        return success();
    }

    /**
     * 导出个人补考信息查询导入模型
     */
    @PreAuthorize("@ss.hasPermi('education:userMakeupExam:add')")
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response)
    {
        ExcelUtil<EduMakeupexamUser> util = new ExcelUtil<EduMakeupexamUser>(EduMakeupexamUser.class);
        util.importTemplateExcel(response, "导入模型");
    }
}
