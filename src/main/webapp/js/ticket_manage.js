	 $(function(){
		 var params = {};
		$("#ticket_tb").datagrid(
				{
					idField : "id",
					pagination : true,
					singleSelect : true,
					rownumbers : true,
					checkOnSelect: false,
					fitColumns:true,
					selectOnCheck: false,
					queryParams: params,
					url : contextPath+"/reg_ticket/getList",
					title : "券码列表",
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
								width : 180
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
								field : 'priceValue',
								title : '价值',
								align : 'center',
								width : 50,
								formatter : function(value, rows, index) {
									if(rows.priceValue =='0'){
										return '--';
									}else{
										return rows.priceValue;
									}
								}
							},
							{
								field : 'createUser',
								title : '创建人',
								align : 'center',
								width : 80
							},
							{
								field : 'available',
								title : '是否有效',
								align : 'center',
								width : 80,
								formatter:function(value,rows,index){
									if(rows.available=='1'){
										return '是';
									}else{
										return '否';
									}
								}
							},
							{
								field : 'consumeTime',
								title : '消费时间',
								align : 'center',
								width : 180,
								formatter : function(value, rows, index) {
									if(rows.consumeTime){
										return rows.consumeTime;
									}else{
										return '--';
									}
								}
							},
							{
								field : 'usedTime',
								title : '售出时间',
								align : 'center',
								width : 180,
								formatter:function(value,rows,index){
									if(rows.usedTime){
										return rows.usedTime;
									}else{
										return '--';
									}
								}
							},
							{
								field : 'createTime',
								title : '生成时间',
								align : 'center',
								width : 180,
								formatter:function(value,rows,index){
									if(rows.createTime){
										return rows.createTime;
									}else{
										return '--';
									}
								}
							},
							{
								field : 'adc',
								title : '操作',
								align : 'center',
								width : 100,
								formatter : function(value, rows, index) {
									return "<a style='text-decoration:none;' href=\"javascript:active(\'"+rows.id+"\',\'0\');$(\'#ticket_tb\').datagrid(\'reload\');\">失效||</a>"+
									"<a style='text-decoration:none;' href=\"javascript:active(\'"+rows.id+"\',\'1\');$(\'#ticket_tb\').datagrid(\'reload\');\">激活</a>";
								}
							}]]
				});
		
		
	 });
	 function search_data(){
		 var ticketCode = $("#ticketCode").val()==''?undefined:$("#ticketCode").val();
		 var ticketType = $('#ticketType').combobox('getValue')==''?undefined:$('#ticketType').combobox('getValue');
		 var available = $('#available').combobox('getValue')==''?undefined:$('#available').combobox('getValue');
		 var params ={"ticketCode":ticketCode,"ticketType":ticketType,"available":available};
		 $("#ticket_tb").datagrid("reload",params);
	 }
	 function active(id,status){
		 var params ={"id":id,"available":status};
		 var url = contextPath+"/reg_ticket/updateRegTicket";
			Util.ajax.postJson(url, params, function(data, flag){
					Util.dialog.tips(data.returnMessage);
			});
	 }
	 function add_ticket(){
		 $('#add_form').form('submit', {    
			    url:contextPath+"/reg_ticket/insertRegTicket",    
			    success:function(data){    
			        var obj = $.parseJSON(data);
			        Util.dialog.tips(obj.returnMessage);
			        $('#add_dlg').dialog('close');
			        $("#ticket_tb").datagrid("reload");
			    }    
			});  
	 }
