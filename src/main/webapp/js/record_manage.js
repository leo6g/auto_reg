	 $(function(){
		 var params = {};
		$("#record_tb").datagrid(
				{
					idField : "id",
					pagination : true,
					singleSelect : true,
					rownumbers : true,
					checkOnSelect: false,
					fitColumns:true,
					selectOnCheck: false,
					queryParams: params,
					url : contextPath+"/admin/reg_record/getList",
					title : "记录列表",
					columns : [[
							{
								field : 'id',
								align : 'center',
								checkbox:true,
								width : 0
							},
							{
								field : 'ticketCode',
								title : '券码',
								align : 'center',
								width : 100
							},
							{
								field : 'machineCode',
								title : '机器码',
								align : 'center',
								width : 120
							},
							{
								field : 'regStatus',
								title : '状态',
								align : 'center',
								width : 50,
								formatter:function(value,rows,index){
									if(rows.regStatus=='1'){
										return '成功';
									}else{
										return '失败';
									}
								}
							},
							{
								field : 'regOrigin',
								title : '渠道',
								align : 'center',
								width : 50,
								formatter : function(value, rows, index) {
									if(rows.regOrigin =='1'){
										return 'PC';
									}else{
										return "Mobile";
									}
								}
							},
							{
								field : 'ticketType',
								title : '券种',
								align : 'center',
								width : 50,
								formatter : function(value, rows, index) {
									if(rows.ticketType =='1'){
										return '普通';
									}else{
										return '现金';
									}
								}
							},
							{
								field : 'regCode',
								title : '注册码',
								align : 'center',
								width : 80,
							},
							{
								field : 'regTime',
								title : '注册时间',
								align : 'center',
								width : 100,
								formatter:function(value,rows,index){
									if(rows.regTime){
										return rows.regTime;
									}else{
										return '--';
									}
								}
							}
						]]
				});
		
		
	 });
	 function search_data(){
		 var ticketCode = $("#ticketCode").val()==''?undefined:$("#ticketCode").val();
		 var machineCode = $("#machineCode").val()==''?undefined:$("#machineCode").val();
		 var regOrigin = $('#regOrigin').combobox('getValue')==''?undefined:$('#regOrigin').combobox('getValue');
		 var regStatus = $('#regStatus').combobox('getValue')==''?undefined:$('#regStatus').combobox('getValue');
		 var params ={"ticketCode":ticketCode,"regOrigin":regOrigin,"regStatus":regStatus,"machineCode":machineCode};
		 $("#record_tb").datagrid("reload",params);
	 }
