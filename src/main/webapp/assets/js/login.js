$(function(){
	$("#loginUser").bind("click",function(){
		var url = contextPath + "/login/checkUser";
		var params = $("#loginForm").serialize();
		Util.ajax.postJson(url, params, function(data,flag){
				if(data.returnCode=="1"){
					location.href = contextPath + "/station0s";
				}else{
					alert(data.returnMessage);
				}
		});
	})
	 document.onkeydown = function(e) {
		e = e || window.event;
		if (e.keyCode == 13) {
			$("#loginUser").click();
		}
	}
})