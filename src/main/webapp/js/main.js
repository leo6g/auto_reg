$(function(){
	$("#resultDiv").hide();
	$("#resultDiv1").hide();
	$("#regBut").click(function(){
		var url = contextPath + "/reg_record/regMachine";
		var ticketCode = $("#ticket_code").val();
		var machineCode = $("#machine_code").val();
		var regOrigin = $("#origin_type").val();
		var params = {"machineCode":machineCode,"ticketCode":ticketCode,"regOrigin":regOrigin};
		Util.ajax.postJson(url, params, function(data,flag){
				if(data.returnCode=="1"){
					$("#machine").html(data.option);
					$("#reg").html(data.returnMessage);
					$("#resultDiv").show();
				}else{
					$("#mess").html(data.returnMessage);
					$("#resultDiv1").show();
				}
		});
	})
})