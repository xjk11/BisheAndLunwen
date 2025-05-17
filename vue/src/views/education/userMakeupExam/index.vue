<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="考试记录ID" prop="examId">
        <el-input
          v-model="queryParams.examId"
          placeholder="请输入考试记录ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="课程ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课程ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程名" prop="courseName">
        <el-input
          v-model="queryParams.courseName"
          placeholder="请输入课程名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开课学年" prop="startTime">
        <el-select v-model="queryParams.startTime" placeholder="请选择开课学年" clearable>
          <el-option
            v-for="dict in course_duration"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="考试时间" prop="examTime">
        <el-input
          v-model="queryParams.examTime"
          placeholder="请输入考试时间"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试教室" prop="examClassroom">
        <el-input
          v-model="queryParams.examClassroom"
          placeholder="请输入考试教室"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['education:userMakeupExam:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['education:userMakeupExam:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['education:userMakeupExam:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['education:userMakeupExam:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Upload"
          @click="handleImport"
          v-hasPermi="['education:userMakeupExam:add']"
        >导入</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userMakeupExamList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程ID" align="center" prop="courseId" />
      <el-table-column label="课程名" align="center" prop="courseName" />
      <el-table-column label="开课学年" align="center" prop="startTime">
        <template #default="scope">
          <dict-tag :options="course_duration" :value="scope.row.startTime"/>
        </template>
      </el-table-column>
      <el-table-column label="考试时间" align="center" prop="examTime" />
      <el-table-column label="考试地点" align="center" prop="examClassroom" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户姓名" align="center" prop="userName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['education:userMakeupExam:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['education:userMakeupExam:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改个人补考信息查询对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="userMakeupExamRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="记录ID" prop="examId">
          <el-input v-model="form.examId" placeholder="请输入考试记录ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

     <!-- 数据导入对话框 -->
     <el-dialog title="数据导入" v-model="excelOpen" width="400px" append-to-body>
      <el-upload
        ref="uploadRef"
        class="upload-demo"
        :action="uploadExcelUrl"
        :headers="headers"
        :before-upload="handleBeforeUpload"
        :on-success="handleUploadSuccess"
        :on-error="handleUploadError"
        :auto-upload="false"
      >
        <template #trigger>
          <el-button type="primary" style="margin: 10px;">选择文件</el-button>
        </template>

        <el-button class="ml-3" type="success" @click="submitUpload">
          上传到系统
        </el-button>

        <template #tip>
          <div class="el-upload__tip">
            上传文件支持，xls/xlsx格式，文件大小不超过10M
            <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importExcelModel">下载上传模板</el-link>
          </div>
        </template>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script setup name="UserMakeupExam">
import { listUserMakeupExam, getUserMakeupExam, delUserMakeupExam, addUserMakeupExam, updateUserMakeupExam } from "@/api/education/userMakeupExam";
import { getToken } from "@/utils/auth";

const { proxy } = getCurrentInstance();
const { course_duration } = proxy.useDict('course_duration');

const userMakeupExamList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    examId: null,
    userId: null
  },
  rules: {
    examId: [
      { required: true, message: "考试记录ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "用户ID不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询个人补考信息查询列表 */
function getList() {
  loading.value = true;
  listUserMakeupExam(queryParams.value).then(response => {
    userMakeupExamList.value = response.rows;
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
    userMakeupexamId: null,
    examId: null,
    userId: null
  };
  proxy.resetForm("userMakeupExamRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.userMakeupexamId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加个人补考信息查询";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _userMakeupexamId = row.userMakeupexamId || ids.value
  getUserMakeupExam(_userMakeupexamId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改补考信息用户信息关联";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["userMakeupExamRef"].validate(valid => {
    if (valid) {
      if (form.value.userMakeupexamId != null) {
        updateUserMakeupExam(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addUserMakeupExam(form.value).then(response => {
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
  const _userMakeupexamIds = row.userMakeupexamId || ids.value;
  proxy.$modal.confirm('是否确认删除补考信息用户信息关联编号为"' + _userMakeupexamIds + '"的数据项？').then(function() {
    return delUserMakeupExam(_userMakeupexamIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('education/userMakeupExam/export', {
    ...queryParams.value
  }, `userMakeupExam_${new Date().getTime()}.xlsx`)
}

// 导入按钮操作

const excelOpen = ref(false);
function handleImport() {
  excelOpen.value = true;
}

// 上传excel文件
// 上传前文件属性的校验
const props = defineProps({
  modelValue: [String, Object, Array],
  // 大小限制(MB)
  fileSize: {
    type: Number,
    default: 10,
  },
  // 文件类型, 例如["xls", "xlsx"]
  fileType: {
    type: Array,
    default: () => ["xls", "xlsx"],
  },
});

// 上传地址
const uploadExcelUrl = ref(import.meta.env.VITE_APP_BASE_API + "/education/userMakeupExam/import");
// 上传请求头
const headers = ref({ Authorization: "Bearer " + getToken() });

const uploadRef=ref({});
// 点击上传
function submitUpload() {
  uploadRef.value.submit();
}

// 上传前loading加载
function handleBeforeUpload(file) {
  let isExcel = false;
  if (props.fileType.length) {
    let fileExtension = "";
    if (file.name.lastIndexOf(".") > -1) {
      fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
    }
    isExcel = props.fileType.some(type => {
      if (file.type.indexOf(type) > -1) return true;
      if (fileExtension && fileExtension.indexOf(type) > -1) return true;
      return false;
    });
  }
  if (!isExcel) {
    proxy.$modal.msgError(
      `文件格式不正确, 请上传${props.fileType.join("/")}格式文件!`
    );
    return false;
  }
  if (props.fileSize) {
    const isLt = file.size / 1024 / 1024 < props.fileSize;
    if (!isLt) {
      proxy.$modal.msgError(`上传excel大小不能超过 ${props.fileSize} MB!`);
      return false;
    }
  }
  proxy.$modal.loading("正在上传excel，请稍候...");
}
// 上传成功回调
function handleUploadSuccess(res) {
  if (res.code === 200) {
    proxy.$modal.msgSuccess("上传excel文件成功");
    excelOpen.value = false;
    getList();
  } else {
    proxy.$modal.msgError(res.msg);
  }
  uploadRef.value.clearFiles();
  proxy.$modal.closeLoading();
}

// 上传失败
function handleUploadError() {
  proxy.$modal.msgError("上传excel失败");
  uploadRef.value.clearFiles();
  proxy.$modal.closeLoading();
}

// 下载文件模板
function importExcelModel() {
  proxy.download('education/userMakeupExam/exportModel', {
  }, `userMakeupExam_${new Date().getTime()}.xlsx`)
}

getList();
</script>
