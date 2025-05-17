<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="成绩记录ID" prop="makeupId">
        <el-input
          v-model="queryParams.makeupId"
          placeholder="请输入成绩记录ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生名"
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
      <el-form-item label="考试日期" style="width: 308px">
        <el-date-picker
          v-model="daterangeExamDate"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="考试成绩" prop="examScore">
        <el-input
          v-model="queryParams.examScore"
          placeholder="请输入考试成绩"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="补考成绩" prop="makeupScore">
        <el-select v-model="queryParams.makeupScore" placeholder="请选择补考成绩" clearable>
          <el-option
            v-for="dict in total_score"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['education:makeup:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['education:makeup:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['education:makeup:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['education:makeup:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Upload"
          @click="handleImport"
          v-hasPermi="['education:makeup:add']"
        >导入</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="makeupList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" width="90" align="center" prop="userId" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="课程名称" align="center" prop="courseName" />
      <el-table-column label="教师姓名" align="center" prop="teacherName" />
      <el-table-column label="考试日期" align="center" prop="examDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.examDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考试成绩" width="90" align="center" prop="examScore" />
      <el-table-column label="补考成绩" align="center" prop="makeupScore">
        <template #default="scope">
          <dict-tag :options="total_score" :value="scope.row.makeupScore"/>
        </template>
      </el-table-column>
      <el-table-column label="开课学年" align="center" prop="startTime">
        <template #default="scope">
          <dict-tag :options="course_duration" :value="scope.row.startTime"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['education:makeup:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['education:makeup:remove']">删除</el-button>
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

    <!-- 添加或修改补考成绩对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="makeupRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生ID" />
        </el-form-item>
        <el-form-item label="教师ID" prop="teacherId" v-if="isAdmin">
          <el-input v-model="form.teacherId" placeholder="请输入教师ID" />
        </el-form-item>
        <el-form-item label="课程ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程ID" />
        </el-form-item>
        <el-form-item label="考试日期" prop="examDate">
          <el-date-picker clearable
            v-model="form.examDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择考试日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="考试成绩" prop="examScore">
          <el-input v-model="form.examScore" placeholder="请输入考试成绩" />
        </el-form-item>
        <el-form-item label="补考成绩" prop="makeupScore">
          <el-select v-model="form.makeupScore" placeholder="请选择补考成绩">
            <el-option
              v-for="dict in total_score"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开课学年" prop="startTime">
          <el-select v-model="form.startTime" placeholder="请选择开课学年">
            <el-option
              v-for="dict in course_duration"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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

<script setup name="Makeup">
import { listMakeup, getMakeup, delMakeup, addMakeup, updateMakeup } from "@/api/education/makeup";
import { getToken } from "@/utils/auth";
import useUserStore from '@/store/modules/user'  // 修改这里
import { computed } from 'vue'

const { proxy } = getCurrentInstance();
const { total_score, course_duration } = proxy.useDict('total_score', 'course_duration');

const userStore = useUserStore()
const userId = computed(() => userStore.id)
const isAdmin = computed(() => userId.value === 1)

const makeupList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const daterangeExamDate = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    makeupId: null,
    studentId: null,
    courseId: null,
    examDate: null,
    examScore: null,
    makeupScore: null,
    startTime: null
  },
  rules: {
    studentId: [
      { required: true, message: "学生ID不能为空", trigger: "blur" }
    ],
    teacherId: [
      { required: true, message: "教师ID不能为空", trigger: "blur" }
    ],
    courseId: [
      { required: true, message: "课程ID不能为空", trigger: "blur" }
    ],
    examDate: [
      { required: true, message: "考试日期不能为空", trigger: "blur" }
    ],
    examScore: [
      { required: true, message: "考试成绩不能为空", trigger: "blur" }
    ],
    makeupScore: [
      { required: true, message: "补考成绩不能为空", trigger: "change" }
    ],
    startTime: [
      { required: true, message: "开课学年不能为空", trigger: "change" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询补考成绩列表 */
function getList() {
  loading.value = true;
  queryParams.value.params = {};
  if (null != daterangeExamDate && '' != daterangeExamDate) {
    queryParams.value.params["beginExamDate"] = daterangeExamDate.value[0];
    queryParams.value.params["endExamDate"] = daterangeExamDate.value[1];
  }
  listMakeup(queryParams.value).then(response => {
    makeupList.value = response.rows;
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
    makeupId: null,
    studentId: null,
    courseId: null,
    examDate: null,
    examScore: null,
    makeupScore: null,
    startTime: null
  };
  proxy.resetForm("makeupRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeExamDate.value = [];
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.makeupId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加补考成绩";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _makeupId = row.makeupId || ids.value
  getMakeup(_makeupId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改补考成绩";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["makeupRef"].validate(valid => {
    if (valid) {
      if (form.value.makeupId != null) {
        updateMakeup(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMakeup(form.value).then(response => {
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
  const _makeupIds = row.makeupId || ids.value;
  proxy.$modal.confirm('是否确认删除补考成绩编号为"' + _makeupIds + '"的数据项？').then(function() {
    return delMakeup(_makeupIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('education/makeup/export', {
    ...queryParams.value
  }, `makeup_${new Date().getTime()}.xlsx`)
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
const uploadExcelUrl = ref(import.meta.env.VITE_APP_BASE_API + "/education/makeup/import");
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
  proxy.download('education/makeup/exportModel', {
  }, `makeup_${new Date().getTime()}.xlsx`)
}

getList();
</script>
