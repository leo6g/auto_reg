$(document).ready(function(){
	
//列表分页每页显示多少条记录
var global_limit = 10 ;
	//初始化首工位列表信息
	initStation0sListInfo();
	
	
	//新增首工位信息时，validate验证，验证通过后调用保存方法 
	$("#addStation0sForm").validate({
        submitHandler:function(form){
        	addStation0s();
        }    
    });
	
	//新增首工位信息
	$("#saveStation0sBtn").click(function(){
		var form = $("#addStation0sForm");
		form.submit();
	});
	
	//选中首工位信息
	$("#station0sListTable").delegate("tr","click",function(){
		var linenum = $(this).find("td:eq(1)").html();
		var wpnum = $(this).find("td:eq(2)").html();
		var creattime = $(this).find("td:eq(3)").html();
		var id = $(this).find("td:eq(4)").html();
		var batchnum = $(this).find("td:eq(5)").html();
		var stationnum = $(this).find("td:eq(6)").html();
		var port = $(this).find("td:eq(7)").html();
		var station = $(this).find("td:eq(8)").html();
		var result = $(this).find("td:eq(9)").html();
		var barcode = $(this).find("td:eq(10)").html();
		
		$("#edit-linenum").val(linenum);
		$("#edit-wpnum").val(wpnum);
		$("#edit-creattime").val(creattime);
		$("#edit-id").val(id);
		$("#edit-batchnum").val(batchnum);
		$("#edit-stationnum").val(stationnum);
		$("#edit-port").val(port);
		$("#edit-station").val(station);
		$("#edit-result").val(result);
		$("#edit-barcode").val(barcode);
		
		$(this).css("background","#DFEBF2").siblings().css("background","#FFFFFF");
		return false;
	});
	
	
	//修改首工位信息时，jqvalidate验证，验证通过后调用保存方法 
	$("#editStation0sForm").validate({
        submitHandler:function(form){
        	editStation0s();
        }    
    });
	
	//保存编辑的首工位信息
	$("#edit-saveStation0sBtn").click(function(){
		var form = $("#editStation0sForm");
		form.submit();
	});
	//编辑首工位信息
	$("#editStation0sBtn").click(function(){
		var id = $("#edit-id").val();
		if(id==""){
			alert("请选择一条信息!",$.scojs_message.TYPE_ERROR);
			return false;
		}
	});
	
	//删除首工位信息
	$("#delStation0sBtn").click(function(){
		//logicDelStation0s();//逻辑删除
		delStation0s()
	});
	
	//初始化首工位列表信息
	function initStation0sListInfo(currentPage, limit){
		if(typeof currentPage == "undefined"){
			currentPage = 1;
		}
		if(typeof limit == "undefined"){
			limit = global_limit;
		}
		var url = contextPath + "/getList";
		var params = "pageNumber="+currentPage+"&limit="+limit;
		//异步请求首工位列表数据
		Util.ajax.postJson(url, params, function(data, flag){
			var source = $("#station0s-list-template").html();
			var templet = Handlebars.compile(source);
			if(flag && data.returnCode=="0"){
				//渲染列表数据
				var htmlStr = templet(data.beans);
				$("#station0sListTable").html(htmlStr);
				//初始化分页数据(当前页码，总数，回调查询函数)
				initPaginator(currentPage, data.bean.count, initStation0sListInfo);
			}
		});
	}
	
	
	//新增首工位信息
	function addStation0s(){
		var url = contextPath + "/insertStation0s";
		var params = $("#addStation0sForm").serialize();
		//异步请求新增首工位信息
		Util.ajax.postJson(url, params, function(data, flag){
			if(flag){
				if(data.returnCode=="0"){
					//$("#alert-info-content").html("首工位新增成功!");
					//$("#alert-info").modal("show");
					alert("首工位新增成功!");
					$("#myModal").modal('hide');
					//重新请求列表数据
					initStation0sListInfo();
					//清空新增首工位的弹窗信息
					$("#addStation0sForm input").val("");
					$("#descn").val("");
				}
			}else{
				if(data.returnCode=="-1"){
					alert("首工位编码已经存在，请修改!");
				}else{
					alert(data.returnMessage);
				}
			}
		});
	}
	
	
	//修改首工位信息
	function editStation0s(){
		
		
		var url = contextPath + "/updateStation0s";
		var params = $("#editStation0sForm").serialize();
		//异步请求修改首工位信息
		Util.ajax.postJson(url, params, function(data, flag){
			if(flag){
				if(data.returnCode=="0"){
					alert("首工位信息修改成功!");
					//编辑首工位信息清空
					$("#editStation0sForm input").val("");
					$("#edit-descn").val("");
					//重新请求列表数据
					initStation0sListInfo();
					$("#myModal1").modal('hide');
				}
			}else{
				if(data.returnCode=="-1"){
					alert("首工位编码已经存在，请修改!");
				}else{
					alert(data.returnMessage);
				}
			}
		});
	}
	//删除首工位
	function delStation0s(){
		var id = $("#edit-id").val();
		if(id==""){
			alert("请选择一条信息!",$.scojs_message.TYPE_ERROR);
			return false;
		}
		confirm("确定删除吗？",function(){
			var url = contextPath + "/deleteStation0s";
			var params = $("#editStation0sForm").serialize();
			//异步请求逻辑删除首工位信息
			Util.ajax.postJson(url, params, function(data, flag){
				if(flag){
					if(data.returnCode=="0"){
						alert("首工位删除成功!");
						//编辑首工位信息清空
						$("#editStation0sForm input").val("");
						$("#edit-descn").val("");
						//重新请求列表数据
						initStation0sListInfo();
					}
				}else{
					alert(data.returnMessage);
				}
			});
		});
	}
	
	//逻辑删除首工位
	function logicDelStation0s(){
		var id = $("#edit-id").val();
		if(id==""){
			alert("请选择一条信息!",$.scojs_message.TYPE_ERROR);
			return false;
		}
		confirm("确定删除吗？",function(){
			var url = contextPath + "/logicDeleteStation0s";
			var params = $("#editStation0sForm").serialize();
			//异步请求逻辑删除首工位信息
			Util.ajax.postJson(url, params, function(data, flag){
				if(flag){
					if(data.returnCode=="0"){
						alert("首工位删除成功!");
						//编辑首工位信息清空
						$("#editStation0sForm input").val("");
						$("#edit-descn").val("");
						//重新请求列表数据
						initStation0sListInfo();
					}
				}else{
					alert(data.returnMessage);
				}
			});
		});
	}

});



