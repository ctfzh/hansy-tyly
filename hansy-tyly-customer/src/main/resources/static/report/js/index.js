/**********************************概述******************************/
var riskBag = 335;//风险包
var safeBag = 444;//安全包
var warnBag = 321;//预警包
/****************************风险指标*********************************/
//命中风险指标情况
var riskIndexName = ['周一','周二','周三','周四','周五','周六','周日','周二','周三','周四','周五','周六','周日','周二','周三','周四','周五','周六','周日','周二','周三','周四','周五','周六','周日'];
var riskIndexData = [120, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230, 210];
//命中黑名单指标情况
var blackIndexName = ['总费用','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数']
var blackIndexData = [2900, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300]
/****************************预警风险指标*********************************/
//命中风险指标情况
var warnRiskIndexName = ['周一','周二','周三','周四','周五','周六','周日'];
var warnRiskIndexData = [120, 132, 101, 134, 90, 230, 210];
//命中黑名单指标情况
var warnBlackIndexName = ['房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数','房租','水电费','交通费','伙食费','日用品数']
var warnBlackIndexData = [ 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300, 1200, 300, 200, 900, 300]


//概述
var briefPie = echarts.init(document.getElementById('briefPie'), 'report');
briefPieOption = {
	tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    series : [
        {
            name: '总体概述',
            type: 'pie',
            radius : '80%',
            center: ['50%', '50%'],
            data:[{
            	value: riskBag, 
            	name:'风险包',
                itemStyle: {
					normal: {
						color: '#e74c3c',
					}
            	}},{
	            	value: safeBag, 
	            	name:'安全包',
	            	itemStyle: {
					normal: {
						color: '#2ecc71',
					}
                }},{
                	value: warnBag, 
                	name:'预警包',
                	itemStyle: {
					normal: {
						color: '#f3b760',
					}
                }}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
briefPie.setOption(briefPieOption);

//命中风险指标名称
var riskIndexLine = echarts.init(document.getElementById('riskIndexLine'), 'report');
riskIndexLineOption = {
	    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['命中风险指标情况'],
      	y: 'bottom',
      	textStyle:{
        	color: '#6b7074'
        },
      	x: 'center'
    },
    calculable : true,
    xAxis : [{
        type : 'category',
        boundaryGap : false,
      	show: false,
        data : riskIndexName
    }],
    yAxis : [{
        type : 'value',
        name: '(次数)',
      	axisLine:{
      	lineStyle:{
        	color:'#d9ddda'
        }}}
    ],
    series : [{
        name:'命中风险指标情况',
        type:'line',
        stack: '名称',
       	smooth: false,
        symbolSize: '10',
        itemStyle: {
        	normal:{
        		areaStyle: {type: 'default',color: 'rgba(96,184,247,0.4)'},
        		borderColor: '#60b8f7',
        		color: '#60b8f7'
        	}                
        },
        lineStyle: {
            normal: {
                color: "#60b8f7"   // 线条颜色
            }
        },
        data:riskIndexData
    }]
};
riskIndexLine.setOption(riskIndexLineOption);

//命中黑名单指标情况
var blackIndexBar = echarts.init(document.getElementById('blackIndexBar'), 'report');
blackIndexBarOption = {
	color: ['#e74c3c'],
	tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
	legend: {
        data:['命中黑名单指标情况'],
        textStyle:{
        	color: '#6b7074'
        },
      	y: 'bottom'
    },
    xAxis : [{
        type : 'category',
      	show: false,
        data : blackIndexName
    }],
    yAxis : [{
        type : 'value',
        name: '(次数)',
      	axisLine:{
      	lineStyle:{
        	color:'#d9ddda'
        }}}
    ],
    series: [{
    	name: '命中黑名单指标情况',
        type: 'bar',
        stack: '名称',
        label: {
            normal: {
                show: false,
            }
        },
        itemStyle:{
        	normal:{
        		barBorderRadius: 6
        	}
        },
        data:blackIndexData
    }]
};
blackIndexBar.setOption(blackIndexBarOption);

/**************************************预警*********************************************/
//命中风险指标名称
var warnRiskIndexLine = echarts.init(document.getElementById('warnRiskIndexLine'), 'report');
warnRiskIndexLineOption = {
	    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['命中风险指标情况'],
        textStyle:{
        	color: '#6b7074'
        },
      	y: 'bottom',
      	x: 'center'
    },
    calculable : true,
    xAxis : [{
        type : 'category',
        boundaryGap : false,
      	show: false,
        data : warnRiskIndexName
    }],
    yAxis : [{
        type : 'value',
        name: '(次数)',
      	axisLine:{
      	lineStyle:{
        	color:'#d9ddda'
        }}}
    ],
    series : [{
        name:'命中风险指标情况',
        type:'line',
        stack: '名称',
       	smooth: false,
        symbolSize: '10',
        borderColor: '#60b8f7',
        itemStyle: {
        	normal:{
        		areaStyle: {type: 'default',color: 'rgba(96,184,247,0.4)'},
        		borderColor: '#60b8f7',
        		color: '#60b8f7'
        	}                
        },
        lineStyle: {
            normal: {
                color: "#60b8f7"   // 线条颜色
            }
        },
        data:warnRiskIndexData
    }]
};
warnRiskIndexLine.setOption(warnRiskIndexLineOption);

//命中黑名单指标情况
var warnBlackIndexBar = echarts.init(document.getElementById('warnBlackIndexBar'), 'report');
warnBlackIndexBarOption = {
	color: ['#e74c3c'],
	tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
	legend: {
        data:['命中黑名单指标情况'],
        textStyle:{
        	color: '#6b7074'
        },
      	y: 'bottom'
    },
    xAxis : [{
        type : 'category',
      	show: false,
        data : warnBlackIndexName
    }],
    yAxis : [{
        type : 'value',
        name: '(次数)',
      	axisLine:{
      	lineStyle:{
        	color:'#d9ddda'
        }}}
    ],
    series: [{
    	name: '命中黑名单指标情况',
        type: 'bar',
        stack: '名称',
        label: {
            normal: {
                show: false,
            }
        },
        itemStyle:{
        	normal:{
        		barBorderRadius: 6
        	}
        },
        data:warnBlackIndexData
    }]
};
warnBlackIndexBar.setOption(warnBlackIndexBarOption);