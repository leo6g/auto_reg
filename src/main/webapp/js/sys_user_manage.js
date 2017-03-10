 $(function(){
		var params = {};
			
		$("#pushMess").datagrid(
				{
					idField : "pushId",
					pagination : true,
					singleSelect : true,
					rownumbers : true,
					checkOnSelect: true,
					selectOnCheck: true,
				//	border:false,
					url : "${contextPath}/front/sh/imgTxtMess!queryAllMess?uid=002",
					queryParams: params,
					title : "推送消息列表",
					columns : [[
							{
								field : 'pushId',
								align : 'center',
								checkbox:true,
								width : 50
							},
							{
								field : 'pushTitle',
								title : '推送消息备注',
								align : 'center',
								width : 300
							},
							{
								field : 'messageType',
								title : '消息类型',
								align : 'center',
								width : 180,
								formatter : function(value, rows, index) {
									if(rows.messageType =='0'){
										return '文本消息';
									}else{
										return '图文消息';
									}
								}
							},
							{
								field : 'sendStatus',
								title : '发送状态',
								align : 'center',
								width : 100,
								formatter : function(value, rows, index) {
									if(rows.sendStatus =='0'){
										return '未发送';
									}else{
										return '已发送';
									}
								}
							},
							{
								field : 'sendScope',
								title : '发送范围',
								align : 'center',
								width : 100,
								formatter : function(value, rows, index) {
									if(rows.sendScope =='1'){
										return '全部';
									}else{
										return '部分';
									}
								}
							},
							{
								field : 'createTime',
								title : '创建时间',
								align : 'center',
								width : 180,
								formatter:function(value,rows,index){
									if(value !=null && value !=""){
										return value.substr(0,value.lastIndexOf('.'));
									}
								}
							},
							{
								field : 'createUser',
								title : '创建人',
								align : 'center',
								width : 100
							}/*,
							{
								field : 'province',
								title : '发送对象',
								align : 'center',
								width : 300
							}
							,
							{
								field : 'adc',
								title : '操作',
								align : 'center',
								width : 200,
								formatter : function(value, rows, index) {
									return "<a href='themeTwoEdit.html?tid="+rows.tid+"'>社区首页</a>";
								}
							}*/]]
				            
				});
		//图文消息列表
		$("#pushImgMess").datagrid(
				{
					idField : "articleId",
					singleSelect : true,
					rownumbers : "true",
					//checkOnSelect: "true",
					//selectOnCheck: true,
				//	border:false,
					url : "${contextPath}/front/sh/imgTxtMess!queryImgMessById?uid=004",
					queryParams: {pushId:''},
					title : "",
					columns : [[
							{
								field : 'picTitle',
								title : '标题',
								align : 'center',
								width : 200,
								formatter : function(value, rows, index) {
									return "<a target='_blank' href='viewDetails.html?articleId="+rows.articleId+"'>"+value+"</a>";
								}
							},
							{
								field : 'author',
								title : '作者',
								align : 'center',
								width : 100
							},
							{
								field : 'imgUrl',
								title : '封面',
								align : 'center',
								width : 100,
								formatter : function(value, rows, index) {
									if(value!=undefined){
										return '<div onmouseout="hideImg()" onmouseover="showImg(\''+value+'\',this);">'+value+'</div>';
				        		  }
								}
							},
							{
								field : 'describetion',
								title : '描述',
								align : 'center',
								width : 200
							},
							{
								field : 'showImg',
								title : '封面是否在正文中显示',
								align : 'center',
								width : 100,
								formatter : function(value, rows, index) {
									if(value =='1'){
										return '是';
									}else{
										return '否';
									}
								}
							},
							{
								field : 'picConn',
								title : '原文链接',
								align : 'center',
								width : 100,
								formatter : function(value, rows, index) {
									return "<a target='_blank' href='"+value+"'>"+value+"</a>";
								}
							}
							/*,
							{
								field : 'adc',
								title : '操作',
								align : 'center',
								width : 200,
								formatter : function(value, rows, index) {
									return "<a href='themeTwoEdit.html?tid="+rows.tid+"'>社区首页</a>";
								}
							}*/]]
				            
				});
		
		pushPortsUse();
		previewPortsUse();
	 });
	 
    //搜索
	 function searchData(){
		 var startTime =  $('#start').datebox('getValue'); 
		var endTime =  $('#end_time').datebox('getValue');
		if(!Util.isEmpty(startTime) && !Util.isEmpty(endTime)){	
			if(compareTime(startTime,endTime)){
					$.messager.alert("提示","结束日期必须大于开始日期");
					return;
				}
    	 }
			var params ={startTime:startTime,endTime:endTime};
			$('#pushMess').datagrid('reload', params);
	 }
	//重置
	 function resetSearch(){
		 $('#start').datebox('setValue','');
		 $('#end_time').datebox('setValue','');
		}
	//比较时间
		function compareTime(start,end){
			  var startDateTemp = start.split(" ");   
		      var endDateTemp = end.split(" ");   
		      var arrStartDate = startDateTemp[0].split("-");   
		      var arrEndDate = endDateTemp[0].split("-");   
		      var arrStartTime = startDateTemp[1].split(":");   
		      var arrEndTime = endDateTemp[1].split(":");  
		      var allStartDate = new Date(arrStartDate[0],arrStartDate[1],arrStartDate[2],arrStartTime[0],arrStartTime[1],arrStartTime[2]);   
		      var allEndDate = new Date(arrEndDate[0],arrEndDate[1],arrEndDate[2],arrEndTime[0],arrEndTime[1],arrEndTime[2]);
	      	return allStartDate.getTime() > allEndDate.getTime();
		}
	//接口次数使用情况
	function pushPortsUse(){
		//显示群发次数：		    
	    Util.ajax.postJson('${contextPath}/front/sh/portsTimes!pushImgTxtNum?uid=005','',function(json){
			if(json.returnCode=="2"){							
				$(".sendAllPorts_useNum").html("今日群发使用次数已超限,");						
			}else if(json.returnCode=="1"){
				$(".sendAllPorts_useNum").html("今日群发还有"+json.returnMessage+"次可用,");		
			}
		});
	   
	}
	function previewPortsUse(){
		 //显示预览次数：		    
	    Util.ajax.postJson('${contextPath}/front/sh/portsTimes!previewImgTxtNum?uid=004','',function(json){
			if(json.returnCode=="2"){							
				$(".mbPreviewPorts_useNum").html("今日发送手机预览使用次数已超限.");						
			}else if(json.returnCode=="1"){
				$(".mbPreviewPorts_useNum").html("今日发送手机预览还有"+json.returnMessage+"次可用.");		
			}
		});
	}
	//群发窗口
	function showAddDialog() {
			$("#dlg").dialog("open").dialog('setTitle', '请选择群发对象');
			$("#fm01").form("clear");
		}
	//群发
	function pushMess(){
		var mediaId;
		var row = $('#pushMess').datagrid('getSelected');			
			mediaId = row.mediaids;
		var newsType = row.messageType;
		var pushId = row.pushId;
		var isToAll = 0;
		var groupIds = "";
		var province = "";
		var hd_checker =$("#pushScope").combogrid("grid").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]");
        var hd = hd_checker.is(":checked"); 
        $("#pushMessButton").linkbutton('disable');
        //判断是否是全选/取消全选   
        if(hd == true){
			isToAll = 1;
			$('#dlg').dialog('close');
			var confirmParam = {
					modal:true,
					content:"确定要选择发送全部对象吗？",
					okVal:"确定",
					okCallback:function(){
						Util.ajax.posttoken('${contextPath}/front/sh/imgTxtMess!pushMess?uid=003',
								{
								pushId : pushId,
								mediaId : mediaId, 
								groupIds : groupIds,
								province : province,
								newsType : newsType,
								isToAll : isToAll
								},function(data){
								$("#pushMess").datagrid('reload');
								pushPortsUse();
								Util.dialog.tips(data.returnMessage);
								$("#pushMessButton").linkbutton('enable');
							});
					}
			}
			 Util.dialog.confirm(confirmParam);
		}else{
			groupIds = $('#pushScope').combogrid('getValues');
			province = $('#pushScope').combogrid('getText');
			if(Util.isEmpty(groupIds)){
				$(".objspan_tip").css("display","block");
				return;
			}
			groupIds = groupIds.toString();
			Util.ajax.posttoken('${contextPath}/front/sh/imgTxtMess!pushMess?uid=003',
				{
				pushId : pushId,
				mediaId : mediaId, 
				groupIds : groupIds,
				province : province,
				newsType : newsType,
				isToAll : isToAll
				},function(data){
				$('#dlg').dialog('close');
				$("#pushMess").datagrid('reload');
				Util.dialog.tips(data.returnMessage);
				$("#pushMessButton").linkbutton('enable');
			});
		}	
			
							
	}
	//群发预览
	function previewMess(){
		var mediaId;
		var row = $('#pushMess').datagrid('getSelected');	
		if(row){ 
			var cond='<div class="phonetips_dialog" style="width:200px;height:100px;margin-top:35px;"><span>请输入移动手机号：</span><input id="dialog_phone" type="tel" /><input type="button" class="okDiv" style="margin-top:10px;" value="确定"/><input type="button" class="cancelDiv" style="margin-left:50px;margin-top:10px;" value="取消"/></div>';
			var params = {
					id : "1454055001089",
					modal:true,
					content:cond
			}
			Util.dialog.openDiv(params);
			 $(".okDiv").on('click',function(){
				 phone = $("#dialog_phone").val();
				 regPatt = /^1(3[4-9]|47|5[0124789]|78|8[23478])\d{8}$/;
				 if(phone == ""){
					 Util.dialog.tips("请输入中国移动手机号");
				 }else if(!regPatt.test(phone)){
					 Util.dialog.tips("中国移动手机号不正确");
				 }else{
					 Util.dialog.close("1454055001089");		
						mediaId = row.mediaids;				
					
						Util.ajax.posttoken('${contextPath}/front/sh/imgTxtMess!previewOfPushMess?uid=008',
							{
								mediaId : mediaId,
								phone:phone
							},function(json){
								if(json.returnCode=="1"){
									Util.dialog.tips(json.returnMessage);		
									previewPortsUse();
								}else{
									Util.dialog.tips(json.returnMessage);
								}
						});
												 
				 }
			 });
			 $(".cancelDiv").on('click',function(){
				 Util.dialog.close("1454055001089"); 
			 });
		}else{
			Util.dialog.tips("请选择一条数据");
		}
					
	}
	//查看窗口
	function showDialog() {
		var row = $('#pushMess').datagrid('getSelected');
		if(row){
			$('#pushImgMess').datagrid('reload', {
				pushId : row.pushId
			});
			$("#lookdlg").dialog("open").dialog('setTitle', '图文消息列表');
		}else{
			Util.dialog.tips("请选择一条数据");
		}
		
    }
	function editImgTxt(ele){
		var row = $('#pushMess').datagrid('getSelected');
		if(row){				
			location.href='${contextPath}/imgTxt/editImgTxt.html?pushId='+row.pushId+'&mediaIds='+row.mediaids+'&sendState='+row.sendStatus;
		}else{
			Util.dialog.tips("请选择一条数据");
		}
	}
	function showImg(url,e){
		var top = getTop(e);
		var left = getLeft(e);
		var contentHeight = $("body").get(0).offsetHeight;
		$("#showImgDiv").find("img").attr("src","${contextPath}/"+url);
		if(top>(contentHeight/2)){
			$("#showImgDiv").css({"top":top-240,"left":left+e.clientWidth,"display":"block"});
		}else{
			$("#showImgDiv").css({"top":top,"left":left+e.clientWidth,"display":"block"});
		}
		Util.stopBubble();
	}
	function hideImg(){
		$("#showImgDiv").css("display","none");
	}
	function getTop(e){ 			
		var offset=$("#lookdlg").offset().top + 10; 
		//if(e.offsetParent!=null) offset +=getTop(e.offsetParent); 
		return offset; 
	} 
		//获取元素的横坐标 
	function getLeft(e){ 
		var offset=e.offsetLeft; 
		if(e.offsetParent!=null) offset +=getLeft(e.offsetParent); 
		return offset; 
	}