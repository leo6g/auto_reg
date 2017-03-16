	 $(function(){
		 var params = {};
		$("#weiuser_tb").datagrid(
				{
					idField : "id",
					pagination : true,
					singleSelect : true,
					rownumbers : true,
					checkOnSelect: false,
					fitColumns:true,
					selectOnCheck: false,
					queryParams: params,
					url : contextPath+"/admin/wei_user/getList",
					title : "微用户列表",
					columns : [[
							{
								field : 'id',
								align : 'center',
								checkbox:true,
								width : 0
							},
							{
								field : 'openid',
								title : '标识',
								align : 'center',
								width : 180
							},
							{
								field : 'wallet',
								title : '余额',
								align : 'center',
							},
							{
								field : 'followStatus',
								title : '关注状态',
								align : 'center',
								formatter : function(value, rows, index) {
									if(rows.followStatus =='1'){
										return '关注';
									}else{
										return '取关';
									}
								}
							},
							{
								field : 'signDate',
								title : '签到时间',
								align : 'center',
								width : 180,
								formatter : function(value, rows, index) {
									if(rows.signDate){
										return rows.signDate;
									}else{
										return '--';
									}
								}
							},
							{
								field : 'followDate',
								title : '关注时间',
								align : 'center',
								width : 180,
								formatter:function(value,rows,index){
									if(rows.followDate){
										return rows.followDate;
									}else{
										return '--';
									}
								}
							},
							{
								field : 'unfollowDate',
								title : '取关时间',
								align : 'center',
								width : 180,
								formatter:function(value,rows,index){
									if(rows.unfollowDate){
										return rows.unfollowDate;
									}else{
										return '--';
									}
								}
							},{
								field : 'adc',
								title : '操作',
								align : 'center',
								formatter : function(value, rows, index) {
									return "<a style='text-decoration:none;' href=\"javascript:showDialog(\'"+rows.id+"\')\">充值</a>"
								}
							}]]
				});
		
		
	 });
	 function search_data(){
		 var openid = $("#openid").val()==''?undefined:$("#openid").val();
		 var followStatus = $('#followStatus').combobox('getValue')==''?undefined:$('#followStatus').combobox('getValue');
		 var params ={"openid":openid,"followStatus":followStatus};
		 $("#weiuser_tb").datagrid("reload",params);
	 }
	function showDialog(id){
		$('#charge_dlg').dialog('open');
		$("#id").val(id);
	}
	function charge(){
		 $('#charge_form').form('submit', {    
			    url:contextPath+"/admin/wei_user/userCharge",    
			    success:function(data){
			        var obj = $.parseJSON(data);
			        Util.dialog.tips(obj.returnMessage);
			        $('#charge_dlg').dialog('close');
			        $("#weiuser_tb").datagrid("reload");
			    }    
			}); 
	}
