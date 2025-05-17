package com.xjk.education.util;

public class ExportUtil {
    public static String changeSex(String Sex) {
        switch (Sex) {
            case "0":
                return "男";
            case "1":
                return "女";
            default:
                return "未知";
        }
    }

    public static String changeDept(String Dept) {
        switch (Dept) {
            case "200":
                return "信息与通信学院";
            case "201":
                return "外语学院";
            case "202":
                return "数学学院";
            default:
                return "未知";
        }
    }

    public static String changeScore(String score) {
        switch (score) {
            case "1":
                return "优秀";
            case "2":
                return "良好";
            case "3":
                return "中等";
            case "4":
                return "及格";
            case "5":
                return "不及格";
            default:
                return "未知";
        }
    }


    public static String changeStartTime(String startTime) {
        switch (startTime) {
            case "1":
                return "2023-2024上半年";
            case "2":
                return "2023-2024下半年";
            case "3":
                return "2024-2025上半年";
            case "4":
                return "2024-2025下半年";
            case "5":
                return "2025-2026上半年";
            default:
                return "未知";
        }
    }


    public static String changeExamName(String examName) {
        switch (examName) {
            case "1":
                return "英语四级考试";
            case "2":
                return "英语六级考试";
            default:
                return "未知";
        }
    }

    public static String changeClassTime(String classTime) {
        switch (classTime) {
            case "1":
                return "上午1-2节";
            case "2":
                return "上午3-4节";
            case "3":
                return "下午1-2节";
            case "4":
                return "下午3-4节";
            case "5":
                return "晚上1-2节";
            default:
                return "未知";
        }
    }
}
