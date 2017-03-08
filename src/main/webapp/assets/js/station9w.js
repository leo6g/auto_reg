$(document).ready(function(){
	
//列表分页每页显示多少条记录
var global_limit = 10 ;
	//初始化尾工位列表信息
	initStation9wListInfo();
	
	
	//新增尾工位信息时，validate验证，验证通过后调用保存方法 
	$("#addStation9wForm").validate({
        submitHandler:function(form){
        	addStation9w();
        }    
    });
	
	//新增尾工位信息
	$("#saveStation9wBtn").click(function(){
		var form = $("#addStation9wForm");
		form.submit();
	});
	
	//选中尾工位信息
	$("#station9wListTable").delegate("tr","click",function(){
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
	
	
	//修改尾工位信息时，jqvalidate验证，验证通过后调用保存方法 
	$("#editStation9wForm").validate({
        submitHandler:function(form){
        	editStation9w();
        }    
    });
	
	//保存编辑的尾工位信息
	$("#edit-saveStation9wBtn").click(function(){
		var form = $("#editStation9wForm");
		form.submit();
	});
	//编辑尾工位信息
	$("#editStation9wBtn").click(function(){
		var id = $("#edit-id").val();
		if(id==""){
			alert("请选择一条信息!",$.scojs_message.TYPE_ERROR);
			return false;
		}
	});
	
	//删除尾工位信息
	$("#delStation9wBtn").click(function(){
		//logicDelStation9w();//逻辑删除
		delStation9w()
	});
	
	//初始化尾工位列表信息
	function initStation9wListInfo(currentPage, limit){
		if(typeof currentPage == "undefined"){
			currentPage = 1;
		}
		if(typeof limit == "undefined"){
			limit = global_limit;
		}
		var url = contextPath + "/getList";
		var params = "pageNumber="+currentPage+"&limit="+limit;
		//异步请求尾工位列表数据
		Util.ajax.postJson(url, params, function(data, flag){
			var source = $("#station9w-list-template").html();
			var templet = Handlebars.compile(source);
			if(flag && data.returnCode=="0"){
				//渲染列表数据
				var htmlStr = templet(data.beans);
				$("#station9wListTable").html(htmlStr);
				//初始化分页数据(当前页码，总数，回调查询函数)
				initPaginator(currentPage, data.bean.count, initStation9wListInfo);
			}
		});
	}
	
	
	//新增尾工位信息
	function addStation9w(){
		var url = contextPath + "/insertStation9w";
		var params = $("#addStation9wForm").serialize();
		//异步请求新增尾工位信息
		Util.ajax.postJson(url, params, function(data, flag){
			if(flag){
				if(data.returnCode=="0"){
					//$("#alert-info-content").html("尾工位新增成功!");
					//$("#alert-info").modal("show");
					alert("尾工位新增成功!");
					$("#myModal").modal('hide');
					//重新请求列表数据
					initStation9wListInfo();
					//清空新增尾工位的弹窗信息
					$("#addStation9wForm input").val("");
					$("#descn").val("");
				}
			}else{
				if(data.returnCode=="-1"){
					alert("尾工位编码已经存在，请修改!");
				}else{
					alert(data.returnMessage);
				}
			}
		});
	}
	
	
	//修改尾工位信息
	function editStation9w(){
		
		
		var url = contextPath + "/updateStation9w";
		var params = $("#editStation9wForm").serialize();
		//异步请求修改尾工位信息
		Util.ajax.postJson(url, params, function(data, flag){
			if(flag){
				if(data.returnCode=="0"){
					alert("尾工位信息修改成功!");
					//编辑尾工位信息清空
					$("#editStation9wForm input").val("");
					$("#edit-descn").val("");
					//重新请求列表数据
					initStation9wListInfo();
					$("#myModal1").modal('hide');
				}
			}else{
				if(data.returnCode=="-1"){
					alert("尾工位编码已经存在，请修改!");
				}else{
					alert(data.returnMessage);
				}
			}
		});
	}
	//删除尾工位
	function delStation9w(){
		var id = $("#edit-id").val();
		if(id==""){
			alert("请选择一条信息!",$.scojs_message.TYPE_ERROR);
			return false;
		}
		confirm("确定删除吗？",function(){
			var url = contextPath + "/deleteStation9w";
			var params = $("#editStation9wForm").serialize();
			//异步请求逻辑删除尾工位信息
			Util.ajax.postJson(url, params, function(data, flag){
				if(flag){
					if(data.returnCode=="0"){
						alert("尾工位删除成功!");
						//编辑尾工位信息清空
						$("#editStation9wForm input").val("");
						$("#edit-descn").val("");
						//重新请求列表数据
						initStation9wListInfo();
					}
				}else{
					alert(data.returnMessage);
				}
			});
		});
	}
	
	//逻辑删除尾工位
	function logicDelStation9w(){
		var id = $("#edit-id").val();
		if(id==""){
			alert("请选择一条信息!",$.scojs_message.TYPE_ERROR);
			return false;
		}
		confirm("确定删除吗？",function(){
			var url = contextPath + "/logicDeleteStation9w";
			var params = $("#editStation9wForm").serialize();
			//异步请求逻辑删除尾工位信息
			Util.ajax.postJson(url, params, function(data, flag){
				if(flag){
					if(data.returnCode=="0"){
						alert("尾工位删除成功!");
						//编辑尾工位信息清空
						$("#editStation9wForm input").val("");
						$("#edit-descn").val("");
						//重新请求列表数据
						initStation9wListInfo();
					}
				}else{
					alert(data.returnMessage);
				}
			});
		});
	}

});



