$(function(){
	$("#regBut").click(function(){
		if(!validate()){
			return;
		};
		var url = contextPath + "/reg_record/regMachine";
		var ticketCode = $("#ticket_code").val();
		var machineCode = $("#machine_code").val();
		var regOrigin = $("#origin_type").val();
		var params = {"machineCode":machineCode,"ticketCode":ticketCode,"regOrigin":regOrigin};
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
		});
	})
})

/**
 * 验证元素
 * @param ele 触发元素*/
function validate(){
	$("#errTip1").hide();
	$("#errTip2").hide();
	var errorMes;
	var ticketCode=$.trim($("#ticket_code").val());
	if(ticketCode==''){
		errorMes="请输入券码";
		$("#errTip1 span").html(errorMes);
		$("#errTip1").show();
	}
	var machineCode=$.trim($("#machine_code").val());
	if(machineCode==''){
		errorMes="请输入机器码"
		$("#errTip2 span").html(errorMes);
		$("#errTip2").show();
	}
	if(errorMes){
		return false;
	}
	return true;
}
