<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>看家狗风险报告</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="/report/css/index.css" rel="stylesheet"/>
		<script src="/report/js/echarts.min.js" type="text/javascript"></script>
		<script src="/report/js/jquery-3.2.1.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="container">
			<div class="titile">
				<h1>看家狗风控状态监控报告</h1>
				<p id ="dataTime">2017-10-28</p>
			</div>
			<div class="whiteBox">
				<div class="brief">
					<div class="briefTitle">
						<img src="/report/img/icon_gaishu.png"/>
						<span>总体概述</span>
					</div>
					<div class="briefContent">
						<div class="left">
							<div class="">
								<div id="briefPie" style="height:18rem;max-width: 100%;position: relative;">
									<div class="noData hide" >
									<img src="/report/img/nodata.png"/>
									<p>暂无数据</p>
								</div>
								</div>
							</div>
						</div>
						<div class="right">
							<p class="rightTitle">风险客户<span style="color:#e74c3c;padding-left: 6px;">top10</span></p>
							<img src="/report/img/baseboard.png" class="rightBg"/>
							<div class="suggest">
								<div class="noData hide tableNoData">
									<img src="/report/img/nodata.png"/>
									<p>暂无数据</p>
								</div>
								<table border="" cellspacing="" cellpadding="" class="hide">
									<thead><tr><th>排名</th><th>姓名</th><th>手机号</th><th>身份证</th></tr></thead>
									<tbody id="tbody-result">
										
									</tbody>
								</table>
							</div>

						</div>

					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
				<div class="whiteBox" style="margin-bottom: 20px;">
				<div class="brief">
					<div class="briefTitle">
						<img src="/report/img/icon_fengxianzhibiao.png"/>
						<span>风险/预警指标</span>
					</div>
					<div class="content">
						<div class="riskIndex">
							<div class="contentTitle">
								<p style="color: #60b8f7;">多重借贷命中情况</p>
								<img src="/report/img/tick_blue.png"/>
							</div>
							<div id="riskIndexLine" class="indexData">
								<div class="noData hide">
									<img src="/report/img/nodata.png"/>
									<p>暂无数据</p>
								</div>
							</div>
							<div class="contentTitle">
								<p style="color: #e74c3c;">黑名单命中情况</p>
								<img src="/report/img/tick_red.png"/>
							</div>
							<div id="blackIndexBar" class="indexData">
								<div class="noData hide">
									<img src="/report/img/nodata.png"/>
									<p>暂无数据</p>
								</div>
							</div>
						</div>
					</div>
					<div class="" style="clear: both;"></div>
				</div>
			</div>
		</div>
		
	</body>
	<script type="text/javascript">
	
		var  datas =${detailData} 
		
		var herfs = window.location.href
		var dataTime =  herfs.substr(herfs.length-10)

		$("#dataTime").text(dataTime)
		//表格数据
		var tbody=$("#tbody-result");
      	var dataTable= datas.topCustData
      	//var dataTable= []
      	if(dataTable.length !=0){
     	$(".suggest").find("table").removeClass("hide")	
      	var str = "";
      	 
	        for (i in dataTable) {  
	         	var custName = dataTable[i].custName.trim();
	            var custTel = dataTable[i].custTel.replace( dataTable[i].custTel.substr(3, 4), "****");
	            var custCertNo = dataTable[i].custCertNo.replace( dataTable[i].custCertNo.substr(6, 8), "****");
	            str += "<tr>" 
	            if(i<=2)
	            	str += "<td><img src='/report/img/medal_0"+(parseInt(i)+1)+".png' class='medalIcon'/></td><td>"+custName+"</td><td>"+custTel+"</td><td>"+custCertNo+"</td>"
	            else
	               str+="<td>"+(parseInt(i)+1)+"</td><td>"+custName+"</td><td>"+custTel+"</td><td>"+custCertNo+"</td>"
	            
	            str +="</tr>";  
	       }
         	tbody.append(str);
      	}else{
      		$(".suggest").find(".noData").removeClass("hide")	
      	}
    
     
      
 
		//概述
		var riskData = datas.riskData
		//var riskData = {riskCnt:12,safeCnt:30,warnCnt:50}
		if(riskData !=null && Object.keys(riskData).length != 0) {
			briefPie(riskData)
		}else{
			$("#briefPie").find(".noData").removeClass("hide")
		}
	
		
		//多重借贷命中情况
			var multLoanAmt = datas.multLoanData
			//var multLoanAmt = []
			if(multLoanAmt.length != 0){
				riskIndexLine(multLoanAmt)
			}else{
			 	$("#riskIndexLine").find(".noData").removeClass("hide")
			}
		
	
		
		//黑名单命中情况
			var blackHitArray = datas.blackHitData
			//var blackHitArray = []
			if(blackHitArray.length !=0){
				blackIndexBar(blackHitArray)
			}else{
			$("#blackIndexBar").find(".noData").removeClass("hide")
			}
		
		
		
		
		//概述
		
	function briefPie(riskData){
	
		var riskBag = riskData.riskCnt;//风险包
		var safeBag = riskData.safeCnt;//安全包
		var warnBag = riskData.warnCnt;//预警包
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
		            	name:'风险人数'+riskBag+"人",
						label: {
			                normal: {
			                    formatter: '{b}\n\n 比例为{d}%  ',
			                    rich: {
			                    }
			                }
           				 },
		                itemStyle: {
							normal: {
								color: '#e74c3c',
							}
		            	}},{
			            	value: safeBag, 
			            	name:'安全人数'+safeBag+"人",
			            	label: {
			                normal: {
			                    formatter: '{b}\n\n 比例为{d}%  ',
			                    rich: {
			                    }
			                }
           				 },
			            	itemStyle: {
							normal: {
								color: '#2ecc71',
							}
		                }},{
		                	value: warnBag, 
		                	name:'预警人数'+warnBag+"人",
		                	label: {
			                normal: {
			                    formatter: '{b}\n\n 比例为{d}%  ',
			                    rich: {
			                    }
			                }
           				 },
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
		}
	
		
		
		
		
		function  riskIndexLine(multLoanAmts){
			var riskIndexName =[]
			var riskIndexData =[]
			for(var i in multLoanAmts){
			  riskIndexName.push(multLoanAmts[i].indicatorName)
			  riskIndexData.push(multLoanAmts[i].indcValue)
			}
			
		
		//命中风险指标名称
		var riskIndexLine = echarts.init(document.getElementById('riskIndexLine'), 'report');
		riskIndexLineOption = {
			tooltip : {
		        trigger: 'axis'
		   },
		    legend: {
		        data:['多重借贷命中指标'],
		      	y: 'bottom',
		      	textStyle:{
		        	color: '#6b7074'
		        },
		      	x: 'center'
		    },
		    grid: {
		        left: '5.5%',
		        right: '5%',
		        bottom: '10%',
		        containLabel: true
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
		        name: '(人数)',
		      	axisLine:{
		      	lineStyle:{
		        	color:'#d9ddda'
		        }}}
		    ],
		    series : [{
		    	data: riskIndexData, 
		    	name:'多重借贷命中指标',
		    	type:'line',
		        itemStyle: {
					normal: {
						areaStyle: {type: 'default',color: 'rgba(96,184,247,0.4)'},
		        		borderColor: '#60b8f7',
		        		color: '#60b8f7'
					}
		    	}}]
		};
		riskIndexLine.setOption(riskIndexLineOption);
		}
	
	

		function  blackIndexBar(blackHitArray){
			var blackIndexName =[]
			var blackIndexData =[]
			for(var i in blackHitArray){
			  blackIndexName.push(blackHitArray[i].indicatorName)
			  blackIndexData.push(blackHitArray[i].indcValue)
			}
			
				//命中黑名单指标情况
		var blackIndexBar = echarts.init(document.getElementById('blackIndexBar'), 'report');
		blackIndexBarOption = {
			color: ['#e74c3c','#f3b760'],
			 tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
			legend: {
		        data:['黑名单命中指标'],
		        textStyle:{
		        	color: '#6b7074'
		        },
		      	y: 'bottom'
		    },
		    grid: {
		        left: '5%',
		        right: '5%',
		        bottom: '10%',
		        containLabel: true
		    },
		    xAxis : [{
		        type : 'category',
		      	show: false,
		        data : blackIndexName
		    }],
		    yAxis : [{
		        type : 'value',
		        name: '(人数)',
		      	axisLine:{
		      	lineStyle:{
		        	color:'#d9ddda'
		        }}}
		    ],
		    series: [{
		    	data: blackIndexData, 
		    	name:'黑名单命中指标',
		    	stack: '指标',
		    	type: 'bar'
			}]
		};
		blackIndexBar.setOption(blackIndexBarOption);
			
		}	
		
	

	
		
		
			
	
	
		//隐藏预警指标
		$(".warningIndex").addClass("hide");
		$(function(){
			//点击切换“风险指标”和“预警指标”
			$(".btn").click(function(){
				$(".btn").removeClass("active-btn");
				if($(".riskIndex").hasClass("hide")){
					$(".riskIndex").removeClass("hide");
					$(".warningIndex").addClass("hide");
				}else{
					$(".warningIndex").removeClass("hide");
					$(".riskIndex").addClass("hide");
				}
				$(this).addClass("active-btn");
			})
		})
		
		
		
		
		
	</script>