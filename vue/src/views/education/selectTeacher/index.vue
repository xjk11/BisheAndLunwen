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
      <el-form-item label="教师姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入教师姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入电话"
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
      <el-form-item label="职称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入职称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属院系" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择所属院系" clearable>
          <el-option
            v-for="dict in edu_faculties"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="入职时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeHireDate"
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
          v-hasPermi="['education:selectTeacher:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['education:selectTeacher:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['education:selectTeacher:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['education:selectTeacher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="selectTeacherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生用户id" align="center" prop="studentUserId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="教师名" align="center" prop="teacherName" />
      <el-table-column label="电话号码" align="center" prop="teacherPhonenumber" />
      <el-table-column label="性别" align="center" prop="teacherGender">
        <template #default="scope">
          <dict-tag :options="sex" :value="scope.row.teacherGender"/>
        </template>
      </el-table-column>
      <el-table-column label="职称" align="center" prop="teacherTitle" />
      <el-table-column label="所属院系" align="center" prop="deptId">
        <template #default="scope">
          <dict-tag :options="edu_faculties" :value="scope.row.deptId"/>
        </template>
      </el-table-column>
      <el-table-column label="入职时间" align="center" prop="teacherHireDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.teacherHireDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['education:selectTeacher:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['education:selectTeacher:remove']">删除</el-button>
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

    <!-- 添加或修改教师信息查询对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="selectTeacherRef" :model="form" :rules="rules" label-width="80px">
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

<script setup name="SelectTeacher">
import { listSelectTeacher, getSelectTeacher, delSelectTeacher, addSelectTeacher, updateSelectTeacher } from "@/api/education/selectTeacher";

const { proxy } = getCurrentInstance();
const { sex, edu_faculties } = proxy.useDict('sex', 'edu_faculties');

const selectTeacherList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const daterangeHireDate = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    studentId: null,
    teacherId: null
  },
  rules: {
    studentId: [
      { required: true, message: "学生id不能为空", trigger: "blur" }
    ],
    teacherId: [
      { required: true, message: "教师id不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询教师信息查询列表 */
function getList() {
  loading.value = true;
  queryParams.value.params = {};
  if (null != daterangeHireDate && '' != daterangeHireDate) {
    queryParams.value.params["beginHireDate"] = daterangeHireDate.value[0];
    queryParams.value.params["endHireDate"] = daterangeHireDate.value[1];
  }
  listSelectTeacher(queryParams.value).then(response => {
    selectTeacherList.value = response.rows;
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
  proxy.resetForm("selectTeacherRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeHireDate.value = [];
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
  title.value = "添加教师信息查询";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _testPage = row.testPage || ids.value
  getSelectTeacher(_testPage).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改教师信息查询";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["selectTeacherRef"].validate(valid => {
    if (valid) {
      if (form.value.testPage != null) {
        updateSelectTeacher(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSelectTeacher(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除教师信息查询编号为"' + _testPages + '"的数据项？').then(function() {
    return delSelectTeacher(_testPages);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('education/selectTeacher/export', {
    ...queryParams.value
  }, `selectTeacher_${new Date().getTime()}.xlsx`)
}

getList();
</script>
