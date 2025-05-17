<template>
    <el-form :model="queryParams" ref="queryRef" :inline="true" label-width="68px" style="margin: 20px; margin-bottom: 0px;">
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
    <div class='class-table'>
        <div class='table-wrapper'>
            <div class='tabel-container'>
                <table>
                    <thead>
                        <tr>
                            <th>时间</th>
                            <th v-for='(week, index) in weeks' :key='week'> {{ weekNames[index] }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for='(item, index) in classTableData' :key='item.id'>
                            <td>
                                <p class="period">{{ item.period }}</p>
                                <p>{{ item.classesTime }}</p>
                            </td>

                            <td v-for='(week, index) in weeks' :key='week'>
                                <div v-if="item[week]">
                                    <p>{{ item[week].courseName }}</p>
                                    <p>{{ item[week].teacherName }}</p>
                                    <p>{{ item[week].classroom }}</p>
                                </div>
                                <p v-else>-</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue';
import { listSchedule } from "@/api/education/schedule";

export default {
    setup() {
        const weeks = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];
        const weekNames = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
        const classTableData = ref([]);
        const responseData = reactive({
            total: 0,
            rows: []
        });

        const queryParams = reactive({
            startTime: '' // 定义查询参数，初始值为空
        });

        const { proxy } = getCurrentInstance();
        const { course_duration } = proxy.useDict('course_duration');

        const getList = () => {
            listSchedule(queryParams).then(response => {
                responseData.total = response.total;
                responseData.rows = response.rows;
                processRows();
            }).catch(error => {
                console.error('获取课程列表失败:', error);
            });
        };

        const processRows = () => {
            const periods = [
                { id: 1, period: '上午1-2节', classesTime: '08:10-10:00' },
                { id: 2, period: '上午3-4节', classesTime: '10:20-11:50' },
                { id: 3, period: '下午1-2节', classesTime: '14:00-15:40' },
                { id: 4, period: '下午3-4节', classesTime: '16:00-17:50' },
                { id: 5, period: '晚上1-2节', classesTime: '18:40-20:20' }
            ];

            const weekMap = {
                '周一': 'monday',
                '周二': 'tuesday',
                '周三': 'wednesday',
                '周四': 'thursday',
                '周五': 'friday',
                '周六': 'saturday',
                '周日': 'sunday'
            };

            const data = periods.map(period => {
                const item = {
                    id: period.id,
                    period: period.period,
                    classesTime: period.classesTime,
                    monday: null,
                    tuesday: null,
                    wednesday: null,
                    thursday: null,
                    friday: null,
                    saturday: null,
                    sunday: null
                };

                responseData.rows.forEach(row => {
                    if (row.lessonTime === period.id.toString()) {
                        const weekKey = weekMap[row.dayOfWeek];
                        item[weekKey] = {
                            courseName: row.courseName,
                            teacherName: row.teacherName,
                            classroom: row.classroom
                        };
                    }
                });

                return item;
            });

            classTableData.value = data;
        };

        const handleQuery = () => {
            getList();
        };

        const resetQuery = () => {
            queryParams.startTime = '';
            getList();
        };

        onMounted(() => {
            getList();
        });

        return {
            weeks,
            weekNames,
            classTableData,
            queryParams,
            course_duration,
            handleQuery,
            resetQuery
        };
    }
};
</script>

<style lang='scss' scoped>
.class-table {
    .table-wrapper {
        overflow: auto;
    }
    .tabel-container {
        margin: 7px;

        table {
            table-layout: fixed;

            thead {
                background-color: #67a1ff;
                th {
                    height: 20px;
                    color: #fff;
                    line-height: 17px;
                    font-weight: normal;
                }
            }
            tbody {
                background-color: #eaf2ff;
                td {
                    color: #677998;
                    line-height: 12px;
                }
            }
            th,
            td {
                height: 140px;
                width: 160px;
                padding: 12px 2px;
                font-size: 15px;
                text-align: center;
            }

            tr td:first-child {
                color: #333;
                p {
                    margin: 10px;
                    font-size: 15px;
                }
                .period {
                    margin: 10px;
                    font-size: 15px;
                }
            }
        }
    }
}
</style>