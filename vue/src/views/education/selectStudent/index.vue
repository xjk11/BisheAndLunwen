<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select v-model="queryParams.gender" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input
          v-model="queryParams.major"
          placeholder="请输入专业"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学院" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择学院" clearable>
          <el-option
            v-for="dict in edu_faculties"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="班级" prop="className">
        <el-input
          v-model="queryParams.className"
          placeholder="请输入班级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入学时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeEnrollmentDate"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['education:selectStudent:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['education:selectStudent:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['education:selectStudent:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['education:selectStudent:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="selectStudentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教师用户id" align="center" prop="teacherUserId" />
      <el-table-column label="教师姓名" width="130" align="center" prop="teacherName" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="性别" width="70" align="center" prop="studentGender">
        <template #default="scope">
          <dict-tag :options="sex" :value="scope.row.studentGender"/>
        </template>
      </el-table-column>
      <el-table-column label="学生专业" align="center" prop="studentMajor" :show-overflow-tooltip="true" />
      <el-table-column label="所属院系" align="center" prop="deptId">
        <template #default="scope">
          <dict-tag :options="edu_faculties" :value="scope.row.deptId"/>
        </template>
      </el-table-column>
      <el-table-column label="学生班级" align="center" prop="studentClass" />
      <el-table-column label="入学时间" align="center" prop="studentEnrollmentDate" />
      <el-table-column label="学生电话" align="center" prop="studentPhonenumber" />
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['education:selectStudent:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['education:selectStudent:remove']">删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改学生信息查询对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="selectStudentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生id" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生id" />
        </el-form-item>
        <el-form-item label="教师id" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入教师id" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="SelectStudent">
import { listSelectStudent, getSelectStudent, delSelectStudent, addSelectStudent, updateSelectStudent } from "@/api/education/selectStudent";

const { proxy } = getCurrentInstance();
const { sex, edu_faculties } = proxy.useDict('sex', 'edu_faculties');

const selectStudentList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const daterangeEnrollmentDate = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询学生信息查询列表 */
function getList() {
  loading.value = true;
  queryParams.value.params = {};
  if (null != daterangeEnrollmentDate && '' != daterangeEnrollmentDate) {
    queryParams.value.params["beginEnrollmentDate"] = daterangeEnrollmentDate.value[0];
    queryParams.value.params["endEnrollmentDate"] = daterangeEnrollmentDate.value[1];
  }
  listSelectStudent(queryParams.value).then(response => {
    selectStudentList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    testPage: null
  };
  proxy.resetForm("selectStudentRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeEnrollmentDate.value = [];
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.testPage);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加学生信息查询";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _testPage = row.testPage || ids.value
  getSelectStudent(_testPage).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改学生信息查询";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["selectStudentRef"].validate(valid => {
    if (valid) {
      if (form.value.testPage != null) {
        updateSelectStudent(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSelectStudent(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _testPages = row.testPage || ids.value;
  proxy.$modal.confirm('是否确认删除学生信息查询编号为"' + _testPages + '"的数据项？').then(function() {
    return delSelectStudent(_testPages);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('education/selectStudent/export', {
    ...queryParams.value
  }, `selectStudent_${new Date().getTime()}.xlsx`)
}

getList();
</script>
