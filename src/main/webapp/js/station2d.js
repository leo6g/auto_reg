$(document).ready(function(){
	
//列表分页每页显示多少条记录
var global_limit = 10 ;
	//初始化电性能检测工位列表信息
	initStation2dListInfo();
	//查询按钮
	$("#querySubmit").click(function(){
		initStation2dListInfo();
	});
	//初始化电性能检测工位列表信息
	function initStation2dListInfo(currentPage, limit){
		if(typeof currentPage == "undefined"){
			currentPage = 1;
		}
		if(typeof limit == "undefined"){
			limit = global_limit;
		}
		var url = contextPath + "/station2d/getList";
		var params = $("#queryForm").serialize();
		params = params + "&pageNumber="+currentPage+"&limit="+limit;
		//异步请求电性能检测工位列表数据
		Util.ajax.postJson(url, params, function(data, flag){
			var source = $("#station2d-list-template").html();
			var templet = Handlebars.compile(source);
			if(data.returnCode=="1"){
				//渲染列表数据
				var htmlStr = templet(data.beans);
				$("#station2dListTable").html(htmlStr);
				//初始化分页数据(当前页码，总数，回调查询函数)
				initPaginator(currentPage, data.bean.count, initStation2dListInfo);
			}
		});
	}
});



