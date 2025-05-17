<template>
  <div>
    <header>
      <h1>欢迎来到教务管理系统</h1>
      <div class="showTime">当前时间：{{ currentTime }}</div>
    </header>

    <section class="mainbox">
      <div class="column">
        <div class="panel bar">
          <h2>柱形图-就业行业</h2>
          <div class="chart" ref="barChart"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel line">
          <h2>折线图-人员变化 <a href="javascript:;">2020</a><a href="javascript:;">2021</a></h2>
          <div class="chart" ref="foldingLineChart"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel pie">
          <h2>饼形图-教职工年龄分布</h2>
          <div class="chart" ref="pieChart"></div>
          <div class="panel-footer"></div>
        </div>
      </div>
      <div class="column">
        <div class="no">
          <div class="no_hd">
            <ul>
              <li>33132</li>
              <li>15345</li>
            </ul>
          </div>
          <div class="no_bd">
            <ul>
              <li>就业需求人数</li>
              <li>学校供应人数</li>
            </ul>
          </div>
        </div>
        <div class="map">
          <div class="map1"></div>
          <div class="map2"></div>
          <div class="map3"></div>
          <div class="chart" ref="mapChart">地图模块</div>
        </div>
      </div>
      <div class="column">
        <div class="panel bar2">
          <h2>柱形图-就业行业</h2>
          <div class="chart" ref="barChart2"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel line2">
          <h2>折线图-人员流动</h2>
          <div class="chart" ref="foldingLineChart2"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel pie2">
          <h2>饼形图-人员地区分布</h2>
          <div class="chart" ref="pieChart2" ></div>
          <div class="panel-footer"></div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import * as echarts from 'echarts';
import 'echarts-gl'; // 导入 echarts-gl
import './js/flexible.js';

import china from './json/china.json'

const currentTime = ref('');

const updateTime = () => {
  const dt = new Date();
  const y = dt.getFullYear();
  const mt = dt.getMonth() + 1;
  const day = dt.getDate();
  const h = dt.getHours();
  const m = dt.getMinutes();
  const s = dt.getSeconds();
  currentTime.value = `${y}年${mt}月${day}日${h}时${m}分${s}秒`;
};

const barChart = ref(null);
const barChart2 = ref(null);
const foldingLineChart = ref(null);
const foldingLineChart2 = ref(null);
const pieChart = ref(null);
const pieChart2 = ref(null);
const mapChart = ref(null); // 添加地图模块的 ref


onMounted(() => {
  setInterval(updateTime, 1000);
  updateTime(); // 初始化显示时间

  // 实例化对象
  const myChart = echarts.init(barChart.value);
  // 指定配置项和数据
  const option = {
    color: ["#3398DB"],
    // 修改图表大小
    grid: {
      left: "0%",
      top: "10px",
      right: "0%",
      bottom: "4%",
      containLabel: true
    },
    xAxis: {
      type: "category",
      data: [
        "旅游行业",
        "教育培训",
        "游戏行业",
        "医疗行业",
        "电商行业",
        // "社交行业",
        "金融行业"
      ],
      axisTick: {
        show: false
      },
      // 修改刻度标签 相关样式
      axisLabel: {
        color: "rgba(255,255,255,.6) ",
        fontSize: "8"
      },
      // 不显示x坐标轴的样式
      axisLine: {
        show: false
      },
    },
    yAxis: {
      type: 'value',
      axisLabel: {
        color: "rgba(255,255,255,.6)",
        fontSize: "12",
        // 增加标签的间距
        margin: 20,
      },
      axisLine: {
        lineStyle: {
          color: "rgba(255,255,255,.1)",
          width: 2
        }
      },
      // y 轴分隔线样式
      splitLine: {
        lineStyle: {
          color: "rgba(255,255,255,.1)"
        }
      },
      // 设置 y 轴刻度间隔
      interval: 300
    },
    series: [
      {
        name: "直接访问",
        type: "bar",
        barWidth: "35%",
        data: [200, 300, 500, 900, 1100, 600],
        itemStyle: {
          // 修改柱子圆角
          barBorderRadius: 5
        }
      }
    ]
  };
  // 把配置项给示例对象
  myChart.setOption(option);
  // 让表随着屏幕变化
  window.addEventListener("resize", function() {
    myChart.resize();
  });

   // 第二个柱状图
  const myChart2 = echarts.init(barChart2.value);
  const myColor = ["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
  const option2 = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      top: "0%",
      left: "22%",
      bottom: "10%"
    },
    xAxis: {
      show: false
    },
    yAxis: [
      {
        type: "category",
        inverse: true,
        data: ["前端", "后端", "电商", "游戏", "公务员"],
        axisLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        axisLabel: {
          color: "#fff"
        }
      },
      {
        data: [702, 350, 610, 793, 664],
        inverse: true,
        axisLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        axisLabel: {
          color: "#fff"
        }
      }
    ],
    series: [
      {
        name: "条",
        type: "bar",
        data: [70, 34, 60, 78, 69],
        yAxisIndex: 0,
        itemStyle: {
          barBorderRadius: 20,
          color: function(params) {
            return myColor[params.dataIndex];
          }
        },
        barCategoryGap: 50,
        barWidth: 10,
        label: {
          show: true,
          position: 'inside',
          formatter: '{c}%'
        }
      },
      {
        name: "框",
        type: "bar",
        barCategoryGap: 50,
        barWidth: 15,
        yAxisIndex: 1,
        itemStyle: {
          color: "none",
          borderColor: "#00c1de",
          borderWidth: 3,
          barBorderRadius: 15
        },
        data: [100, 100, 100, 100, 100],
      }
    ]
  };
  myChart2.setOption(option2);
  window.addEventListener("resize", function() {
    myChart2.resize();
  });

  // 折线图1
  const yearData = [
    {
      year: '2020',
      data: [
        [24, 40, 101, 134, 90, 230, 210, 230, 120, 230, 210, 120],
        [40, 64, 191, 324, 290, 330, 310, 213, 180, 200, 180, 79]
      ]
    },
    {
      year: '2021',
      data: [
        [123, 175, 112, 197, 121, 67, 98, 21, 43, 64, 76, 38],
        [143, 131, 165, 123, 178, 21, 82, 64, 43, 60, 19, 34]
      ]
    }
  ];

  const myFoldingLineChart = echarts.init(foldingLineChart.value);
  const optionFoldingLineChart = {
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      textStyle: {
        color: '#4c9bfd'
      },
      right: '10%'
    },
    grid: {
      top: '20%',
      left: '3%',
      right: '4%',
      bottom: '3%',
      show: true,
      borderColor: '#012f4a',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
      axisTick: {
        show: false
      },
      axisLabel: {
        color: '#4c9bfd',
        fontSize: 10
      },
      axisLine: {
        show: false
      },
      boundaryGap: false
    },
    yAxis: {
      type: 'value',
      axisTick: {
        show: false
      },
      axisLabel: {
        color: '#4c9bfd'
      },
      splitLine: {
        lineStyle: {
          color: '#012f4a'
        }
      }
    },
    series: [
      {
        color: ['#00f2f1', '#ed3f35'],
        name: '新增学生',
        smooth: true,
        type: 'line',
        data: yearData[0].data[0],
      },
      {
        name: '新增教师',
        type: 'line',
        smooth: true,
        data: yearData[0].data[1],
      },
    ]
  };
  myFoldingLineChart.setOption(optionFoldingLineChart);
  window.addEventListener("resize", function() {
    myFoldingLineChart.resize();
  });

  // 点击切换数据
  const handleYearClick = (event) => {
    const index = event.target.getAttribute('data-index');
    const obj = yearData[index];
    optionFoldingLineChart.series[0].data = obj.data[0];
    optionFoldingLineChart.series[1].data = obj.data[1];
    myFoldingLineChart.setOption(optionFoldingLineChart);
  };

  const links = document.querySelectorAll('.line h2 a');
  links.forEach((link, index) => {
    link.setAttribute('data-index', index);
    link.addEventListener('click', handleYearClick);
  });
  
  //折线图2
  //初始化对象
  const myFoldingLineChart2=echarts.init(document.querySelector('.line2 .chart'))
  //初始化设置
  var FoldingLineChart2option = {
    tooltip: {
      trigger: 'axis',
    },
    legend: {
      top: "0%",
      textStyle: {
        color: "rgba(255,255,255,.5)",
        fontSize: "12"
      }
    },
    toolbox: {
      feature: {
        saveAsImage: {}
      }
    },
    grid: {
      left: "10",
      top: "30",
      right: "10",
      bottom: "10",
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        boundaryGap: false,
        // x轴更换数据
        data: [ "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","26","28","29","30"],

        // 文本颜色为rgba(255,255,255,.6)  文字大小为 12
        axisLabel: {
          textStyle: {
            color: "rgba(255,255,255,.6)",
            fontSize: 12
          }
        },
        // x轴线的颜色为   rgba(255,255,255,.2)
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.2)"
          }
        },
      }
    ],
    yAxis: [
      {
        type: 'value',
        axisTick: { show: false },
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        },
        axisLabel: {
          textStyle: {
            color: "rgba(255,255,255,.6)",
            fontSize: 12
          }
        },
	      // 修改分割线的颜色
        splitLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        }
      }
    ],
    series: [
      {
        name: '教职工',
        type: 'line',
        smooth:true,
         // 填充区域设置
         areaStyle: {
            // 渐变色，只需要复制即可
            color: new echarts.graphic.LinearGradient(
            0,
            0,
            0,
            1,
            [
              {
                offset: 0,
                color: "rgba(1, 132, 213, 0.9)"   // 渐变色的起始颜色
              },
              {
                offset: 0.8,
                color: "rgba(1, 132, 213, 0.9)"   // 渐变线的结束颜色
              }
            ],
            false
          ),
          shadowColor: "rgba(0, 0, 0, 0.1)"
        },
        lineStyle:{
          color:"#0184d5",
          width:3,
        },
        emphasis: {
          focus: 'series'
        },
        // 设置拐点 小圆点
        symbol: "circle",
        // 拐点大小
        symbolSize: 8,
        // 设置拐点颜色以及边框
       itemStyle: {
            color: "#0184d5",
            borderColor: "rgba(221, 220, 107, .1)",
            borderWidth: 8
        },
        // 开始不显示拐点， 鼠标经过显示
        showSymbol: false,
        // series  第一个对象data数据
        data: [ 30, 40, 30, 40,30, 40, 30,60,20, 40, 30, 40, 30, 40,30, 40, 30,60,20, 40, 30, 40, 30, 40,30, 40, 20,60,50, 40],
      },
      {
        name: '学生',
        type: 'line',
        smooth:true,
        areaStyle: {
          normal: {
            color: new echarts.graphic.LinearGradient(
              0,
              0,
              0,
              1,
              [
                {
                  offset: 0,
                  color: "rgba(0, 216, 135, 0.4)"
                },
                {
                  offset: 0.8,
                  color: "rgba(0, 216, 135, 0.1)"
                }
              ],
              false
            ),
            shadowColor: "rgba(0, 0, 0, 0.1)"
          }
        },
        // 设置拐点 小圆点
        symbol: "circle",
        // 拐点大小
        symbolSize: 5,
        // 设置拐点颜色以及边框
         itemStyle: {
            color: "#00d887",
            borderColor: "rgba(221, 220, 107, .1)",
            borderWidth: 12
        },
        // 开始不显示拐点， 鼠标经过显示
        showSymbol: false,
        emphasis: {
          focus: 'series'
        },
        // series  第二个对象data数据
        data: [ 130, 10, 20, 40,30, 40, 80,60,20, 40, 90, 40,20, 140,30, 40, 130,20,20, 40, 80, 70, 30, 40,30, 120, 20,99,50, 20],

      },
    ]
  };
  
  //装填对象
  myFoldingLineChart2.setOption(FoldingLineChart2option);
  //让表随着屏幕变化
  window.addEventListener("resize", function() {
    myFoldingLineChart2.resize();
  });

  //饼形图1
  // 1. 实例化对象
  var myPieChart = echarts.init(document.querySelector(".pie .chart"));
  // 2.指定配置
  var PieChartoption = {
    color: ["#065aab", "#066eab", "#0682ab", "#0696ab", "#06a0ab"],
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b}: {c} ({d}%)"
    },

    legend: {
      bottom: "0%",
      // 修改小图标的大小
      itemWidth: 10,
      itemHeight: 10,
      // 修改图例组件的文字为 12px
      textStyle: {
        color: "rgba(255,255,255,.5)",
        fontSize: "12"
      }
    },
    series: [
      {
        name: "年龄分布",
        type: "pie",
        // 这个radius可以修改饼形图的大小
        // radius 第一个值是内圆的半径 第二个值是外圆的半径
        radius: ["40%", "60%"],
        center: ["50%", "45%"],
        avoidLabelOverlap: false,
        // 图形上的文字
        label: {
          show: false,
          position: "center"
        },
        // 链接文字和图形的线是否显示
        labelLine: {
          show: false
        },
        data: [
          { value: 1, name: "20岁以下" },
          { value: 4, name: "20-29岁" },
          { value: 2, name: "30-39岁" },
          { value: 2, name: "40-49岁" },
          { value: 1, name: "50岁以上" }
        ]
      }
    ]
  };

  // 3. 把配置给实例对象
  myPieChart.setOption(PieChartoption);
  // 4. 让图表跟随屏幕自动的去适应
  window.addEventListener("resize", function() {
    myPieChart.resize();
  });

  const myPieChart2 = echarts.init(document.querySelector(".pie2 .chart"));
  const PieChart2option = {
    color: ['#006cff', '#60cda0', '#ed8884', '#ff9f7f', '#0096ff', '#9fe6b8', '#32c5e9', '#1d9dff'],
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
      bottom:0,
      itemWidth: 6,
      itemHeight: 6,
      textStyle: {
        color: "rgba(255,255,255,.5)"
      }      
    },
    series: [
      {
        name: '地区分布',
        type: 'pie',
        radius: ['10%', '70%'],
        center: ['50%', '50%'],
        roseType: 'radiu',
        itemStyle: {
          borderRadius: 5
        },
        //图形的文字标签
        label: {
          fontSize: 10
        },
        labelLine: {
          length: 6,
          length2: 8,
        },
        data: [
          { value: 20, name: '云南' },
          { value: 26, name: '北京' },
          { value: 24, name: '山东' },
          { value: 25, name: '河北' },
          { value: 20, name: '江苏' },
          { value: 25, name: '浙江' },
          { value: 30, name: '四川' },
          { value: 42, name: '湖北' }
        ]
      }
    ]
  };
  myPieChart2.setOption(PieChart2option);
  //让表随着屏幕变化
  window.addEventListener("resize", function() {
    myPieChart2.resize();
  });

  //地图模块
  const myMapChart = echarts.init(document.querySelector(".map .chart"));

  //注册地图
  echarts.registerMap('中国', china)

  const MapChartoption = {
    series: [{
        tooltip: {
            trigger: 'item',
        },
        name: '中国数据',
        type: 'map',
        map: '中国', // 自定义扩展图表类型
        showLegendSymbol: true, // 存在legend时显示
        label: { // 文字
            show: true,
            color: '#fff',
            fontSize: 10
        },
        itemStyle: { // 地图样式
            areaColor: '#282C34',  //区域颜色
            borderColor: '#ffffff',  //边框颜色
            borderWidth: 1
        },
        emphasis: { // 鼠标移入时显示的默认样式
            itemStyle: {
                areaColor: '#4adcf0',
                borderColor: '#404a59',
                borderWidth: 1
            },
            label: { // 文字
                show: true,
                color: '#0D5EFF',
                fontSize: 12,
                fontWeight: 600
            },
        },
        data: [],
    }],
  };

  myMapChart.setOption(MapChartoption);

  });
</script>

<style scoped>
@import './css/index.css';
</style>