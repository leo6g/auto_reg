		/*获取upload btn:点击选择文件的按钮;img:放置上传文件路径的hidden的ID;pic:预览上传图片的img的ID*/
		/*如有特殊需求-请在此JS添加新的方法 勿改此方法*/
        function getUpload(btn, img, pic) {
			var setting = {
				flash_url : contextPath+"/assets/swfupload/1.0.0/2.5/swfupload.swf",
				upload_url : contextPath+"/system/upload/doUpload",
				post_params : {},
				use_query_string : false,
				file_size_limit : "5 MB",
				file_types : "*.jpg;*.png;*.gif;*.jpeg;*.bmp",
				file_types_description : "All Files",
				file_upload_limit : 0,
				file_queue_limit : 0,
				debug : false,
				// Button settings
				button_width : '60',
				button_height : "20",
				button_text : "<span class=\"fontStyle\">选择图片</span>",
				button_text_style : ".fontStyle{font-size: 12px;color:#A9A9A9;}", 
				button_placeholder_id : btn,
				button_text_left_padding : 0,
				button_text_top_padding : 6,
				button_cursor : SWFUpload.CURSOR.HAND,
				file_queued_handler : function(file) {
				},
				file_dialog_complete_handler : function(numFilesSelected,
						numFilesQueued) {
					////console.log(1);
					this.startUpload();
				},
				upload_start_handler : function(file) {
					//console.log(file)
					return true;
				},
				upload_error_handler : function(file, errorCode, message) {
					try {
					} catch (ex) {
						this.debug(ex);
					}
				},
				upload_success_handler : function uploadSuccess(file,
						serverData) {
					var data = eval("(" + serverData + ")");
					$("#" + img).val(data.bean.realPath);
					var url = contextPath +"/"+ data.bean.realPath;
					$("#" + pic).attr("src", url);
				}
			};
			return new SWFUpload(setting);
		}