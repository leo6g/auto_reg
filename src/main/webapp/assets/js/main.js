$(function(){
	$("#regBut").click(function(){
		var url = contextPath + "/font/regMachine";
		var machineCode = $("#machineCode").val();
		var params = {"machineCode":machineCode};
		Util.ajax.postJson(url, params, function(data,flag){
				if(data.returnCode=="1"){
					alert(data.returnMessage);
				}else{
					alert(data.returnMessage);
				}
		});
	})
})