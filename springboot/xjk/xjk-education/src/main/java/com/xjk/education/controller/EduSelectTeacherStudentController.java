package com.xjk.education.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xjk.common.utils.SecurityUtils;
import com.xjk.education.domain.EduStudents;
import com.xjk.education.domain.EduTeachers;
import com.xjk.education.domain.vo.EduStudentSelectTeacherVo;
import com.xjk.education.domain.vo.EduTeacherSelectStudentVo;
import com.xjk.education.service.IEduStudentsService;
import com.xjk.education.service.IEduTeachersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.xjk.common.utils.poi.ExcelUtil;
import com.xjk.common.core.page.TableDataInfo;

import static com.xjk.education.util.ExportUtil.changeDept;
import static com.xjk.education.util.ExportUtil.changeSex;

/**
 * 教师信息查询Controller
 *
 * @author xjk
 * @date 2025-02-05
 */
@RestController
public class EduSelectTeacherStudentController extends BaseController
{
    @Autowired
    private IEduStudentsService eduStudentsService;

    /**
     * 学生查询教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('education:selectTeacher:list')")
    @GetMapping("/education/selectTeacher/list")
    public TableDataInfo studentSelectTeacherslist(EduTeachers eduTeachers)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        if(userid == 1) {
            List<EduStudentSelectTeacherVo> list = eduStudentsService.studentSelectTeachers(eduTeachers);
            return getDataTable(list);
        }else {
            eduTeachers.setUserId(userid);
            List<EduStudentSelectTeacherVo> list = eduStudentsService.studentSelectTeachers(eduTeachers);
            return getDataTable(list);
        }
    }

    @Autowired
    private IEduTeachersService eduTeachersService;

    /**
     * 教师查询学生信息列表
     */
    @PreAuthorize("@ss.hasPermi('education:selectStudent:list')")
    @GetMapping("/education/selectStudent/list")
    public TableDataInfo teacherSelectStudentslist(EduStudents eduStudents)
    {
        startPage();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        if(userid == 1) {
            //如果是管理员
            List<EduTeacherSelectStudentVo> list = eduTeachersService.teacherSelectStudent(eduStudents);
            return getDataTable(list);
        }else {
            eduStudents.setUserId(userid);
            List<EduTeacherSelectStudentVo> list = eduTeachersService.teacherSelectStudent(eduStudents);
            return getDataTable(list);
        }
    }


    /**
     * 学生导出个人教师信息列表
     */

    @PreAuthorize("@ss.hasPermi('education:student:export')")
    @Log(title = "个人教师信息", businessType = BusinessType.EXPORT)
    @PostMapping("/education/selectTeacher/export")
    public void selectTeacherExport(HttpServletResponse response, EduTeachers eduTeachers)
    {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        List<EduStudentSelectTeacherVo> resultList = new ArrayList<>();

        if(userid == 1) {
            resultList = eduStudentsService.studentSelectTeachers(eduTeachers);
        }else {
            eduTeachers.setUserId(userid);
            resultList = eduStudentsService.studentSelectTeachers(eduTeachers);
        }

        for (EduStudentSelectTeacherVo vo : resultList) {
            if (vo.getTeacherGender() != null) {
                vo.setTeacherGender(changeSex(vo.getTeacherGender()));
            }
            if (vo.getDeptId() != null) {
                vo.setDeptId(changeDept(vo.getDeptId()));
            }
        }

        ExcelUtil<EduStudentSelectTeacherVo> util = new ExcelUtil<EduStudentSelectTeacherVo>(EduStudentSelectTeacherVo.class);
        util.exportExcel(response, resultList, "个人教师信息数据");
    }

    /**
     * 教师导出个人学生信息列表
     */

    @PreAuthorize("@ss.hasPermi('education:teacher:export')")
    @Log(title = "个人学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/education/selectStudent/export")
    public void selectStudentExport(HttpServletResponse response, EduStudents eduStudents)
    {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        List<EduTeacherSelectStudentVo> resultList = new ArrayList<>();

        if(userid == 1) {
            //如果是管理员
            resultList = eduTeachersService.teacherSelectStudent(eduStudents);
        }else {
            eduStudents.setUserId(userid);
            resultList = eduTeachersService.teacherSelectStudent(eduStudents);
        }

        for (EduTeacherSelectStudentVo vo : resultList) {
            if (vo.getStudentGender() != null) {
                vo.setStudentGender(changeSex(vo.getStudentGender()));
            }
            if (vo.getDeptId() != null) {
                vo.setDeptId(changeDept(vo.getDeptId()));
            }
        }

        ExcelUtil<EduTeacherSelectStudentVo> util = new ExcelUtil<EduTeacherSelectStudentVo>(EduTeacherSelectStudentVo.class);
        util.exportExcel(response, resultList, "个人教师信息数据");
    }


}
