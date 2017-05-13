$(function(){
	$("#resultDiv").hide();
	$("#resultDiv1").hide();
	$("#regBut").click(function(){
		if(!validate()){
			return;
		};
		$("#regBut").attr("disabled","disabled");
		var url = contextPath + "/front/regMachine";
		var ticketCode = $("#ticket_code").val();
		var machineCode = $("#machine_code").val();
		var softType = $("#softType").val();
		var regOrigin = $("#origin_type").val();
		var params = {"machineCode":machineCode,"ticketCode":ticketCode,"regOrigin":regOrigin,"softType":softType};
		Util.ajax.postJson(url, params, function(data,flag){
			if(data.returnCode=="1"){
				$("#machine").html(data.option);
				$("#reg").html(data.returnMessage);
				$("resultDiv1").attr({style:"display:none"});
				$("#resultDiv").removeAttr("style");
			}else{
				$("#mess").html(data.returnMessage);
				$("#resultDiv1").removeAttr("style");
			}
			$("#regBut").removeAttr("disabled");
		});
	})
})

/**
 * 验证元素
 * @param ele 触发元素*/
function validate(){
	var errorMes;
	var ticketCode=$.trim($("#ticket_code").val());
	if(ticketCode==''){
		errorMes="请输入券码";
		$("#ticket_code").attr({placeholder:errorMes});
	}
	var machineCode=$.trim($("#machine_code").val());
	if(machineCode==''){
		errorMes="请输入机器码"
		$("#machine_code").attr({placeholder:errorMes});
	}
	if(errorMes){
		return false;
	}
	return true;
}