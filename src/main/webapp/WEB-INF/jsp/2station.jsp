<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href= "<%=request.getContextPath()%>/assets/jsp/images/styles.css">
<script src="<%=request.getContextPath()%>/assets/lib/jquery/jquery.min.js"></script>
<script type="text/javascript">
	var conpath = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/jump2.js"></script>
<jsp:include page="common.jsp" />
   <SCRIPT LANGUAGE="JavaScript" >
	var flag=true;
    function f_test(){
        var u77=document.getElementById('u77');
         if(flag) 
        u77.style.display="block"; 
    else 
        u77.style.display="none"; 
   		 flag=!flag; 
    }
  
  </SCRIPT>
</head>

<body>

	<div>
	<div>
	<div class="rightpart">
		<div id="u50" class="text">
			<p>
				<span style="color:#333333;">工位号&gt;</span><span
					style="color:#3399FF;">2电性能检测工位</span>
			</p>
		</div>

		
		<input  name="submit" type="image" onclick="f_test()"  align="right" width="40"  height="40"
			src="<%=request.getContextPath()%>/assets/jsp/images/0首工位/u75.png" />
	
		<div >
			<input  type="submit" class="sub1"  value="开始" />
			<input  type="submit" class="sub2" value="开始" />
		</div>

		<div class="data"  style="margin-top: 20px;">
			
				<div style="width:450px;margin:10px;margin-left:20px; padding:0 0 0 10px;" >
				数据反馈
					</div>
					
					<div  style="margin-left:40px;margin-top:30px;;height:50px;">
						<div>
							<p>
								<span>条码:12345 返回结果:OK</span><span>&nbsp; </span><span>2016.11.1&nbsp;&nbsp;
									21:44</span>
							</p>
						</div>
					

					<!-- Unnamed (Shape) -->
					
						<div>
							<p>
								<span>条码:123456 返回结果:Er04</span><span>&nbsp;</span><span>
									2016.11.1&nbsp;&nbsp; 21：45</span>
							</p>
						</div>
					
					<!-- Unnamed (Shape) -->
					
						<!-- Unnamed () -->
						<div id="u70" class="text">
							<p>
								<span>条码:123456 返回结果:Er04</span><span>&nbsp;</span><span>
									2016.11.1&nbsp;&nbsp; 21：45</span>
							</p>
						</div>
					

					<!-- Unnamed (Shape) -->
					
						<!-- Unnamed () -->
						<div id="u72" class="text">
							<p>
								<span>条码:12345 返回结果:OK</span><span>&nbsp; </span><span>2016.11.1&nbsp;&nbsp;
									21:44</span>
							</p>
						</div>
					

					<!-- Unnamed (Shape) -->
					
						<!-- Unnamed () -->
						<div id="u74" class="text">
							<p>
								<span>条码:123456 返回结果:Er04</span><span>&nbsp;</span><span>
									2016.11.1&nbsp;&nbsp; 21：45</span>
							</p>
						
					</div>
					</div>
				</div>
			</div>
		

		<div  style="float: right;margin-right: 400px;">
			<img id="u10_img" width="100" class="img " src="<%=request.getContextPath()%>/assets/jsp/images/0首工位/u10.png" />
			<img id="u12_img" width="100" class="img " src="<%=request.getContextPath()%>/assets/jsp/images/0首工位/u12.png" />
		


		<div >
		<div   id="u15" class="text" style=" margin-left:22px; width:100px;float: left;" >
			<p>
				<span style="color:#333333;">成功</span><span style="color:#0099FF;">5</span><span
					style="color:#333333;">条</span>
			</p>
		</div>


		<div id="u17" class="text" style="width: 100px;">
			<p>
				<span style="color:#333333;">失败</span><span style="color:#FFCC00;">5</span><span
					style="color:#333333;">条</span>
			</p>

		</div>
		</div>
	</div>
	</div>

	<div class="div2">

		<img id="u8_img"  width="150" class="img " src="<%=request.getContextPath()%>/assets/jsp/images/0首工位/u8.png" />
			<div style="position:absolute;z-index: 21;left: 36px;top: 89px;font-size:12pt;color: #29abe2;font-weight:bold;"><%=session.getAttribute("station") %></div>
				<div style="position:absolute;z-index: 21;left: 86px;top: 90px;font-size:12pt;color: #29abe2;font-weight:bold;"><%=session.getAttribute("userName") %></div>
				<div style="position:absolute;z-index: 21;left: 19px;top: 111px;font-size:10pt;color: #29abe2;font-weight:bold;">操作员&nbsp;&nbsp;&nbsp;|</div>
				<div style="position:absolute;z-index: 21;left: 84px;top: 112px;font-size:10pt;color: #29abe2;font-weight:bold; cursor:hand"data-toggle="modal" data-target="#myModal">修改密码</div>
		<div class="ins1">工位号</div>
		<ul>
			<ins id="station0s" style="width: 90px;"><a href="#">0首工位</ins>
			<ins style="width: 90px;"></ins>
			<li id="station1m" style="width: 106px; "><a href="#">1密封性检测工位</a></li>
			<ins style="width: 90px;"></ins>
			<li id="station2d" style="width: 106px; "><a href="#">2电性能检测工位</a></li>
			<ins style="width: 90px;"></ins>
			<li id="station9w" style="width: 90px; "><a href="#">9尾工位</a></li>
			
		</ul>
		<div class="ins1">数据查询</div>
		<ul>
			<li id="testingProcedures" style="width: 106px; "><a href="#">检测流程查询</a></li>
			<ins style="width: 90px;"></ins>
			<li id="sealingTest" style="width: 106px; "><a href="#">密封性能测试查询</a></li>
			<ins style="width: 90px;"></ins>
			<li id="electrical" style="width: 106px; "><a href="#">电性能测试查询</a></li>
		</ul>
	</div>
	</div>


	<!-- 设置界面 (Dynamic Panel) -->

			 <div  id="u77" style="display:none;  width:749px; height:248px; margin-left:750px; margin-top:-520px;  background-image: url('<%=request.getContextPath()%>/assets/jsp/images/0首工位/u78.png')" >
		<div style="width:450px;margin:10px;padding:0 0 0 10px;" >
			2电性能检测工位参数设置
		</div>
		<div style="margin-left:80px;margin-top:35px;;height:50px;">
			<div id="u87" style="width:200px;height:50px;float: left;">
				<span>工位序号</span><span>:</span>
				<select id="u85_input">
					<option selected="" value="01">
						01
					</option>
					<option value="02">
						02
					</option>
				</select>
			</div>
			<div id="u84" class="check"
				style="width:200px; height:50px; float: left;">
				<p>
					<span>车间号</span><span>:</span>
					<select>
						<option selected="" value="4">
							4
						</option>
						<option value="5">
							5
						</option>
						<option value="6">
							6
						</option>
						<option value="7">
							7
						</option>
						<option value="8">
							8
						</option>
					</select>
				</p>
			</div>

			<div id="u90" class="check"
				style="width: 140px;height:50px; float: left;">
				<p>
					<span>线别</span><span>:</span>
					<select id="u88_input">
						<option selected="" value="4">
							4
						</option>
						<option value="5">
							5
						</option>
						<option value="6">
							6
						</option>
						<option value="7">
							7
						</option>
						<option value="8">
							8
						</option>
					</select>
				</p>
			</div>
			
		</div>
		<div style="margin-left: 80px;">
		<div id="u96" class="check"
				style="width: 200px; height:50px; float: left;">
				<p>
					<span>端口号</span><span>:</span>
					<select id="check">
						<option selected="" value="01">
							01
						</option>
						<option value="02">
							02
						</option>
					</select>
				</p>
			</div>
			<div id="u93" class="check"
				style="width:200px;height:50px; float: left;">
				<p>
					<span>批次号</span><span>:</span>
					<select id="u91_input">
						<option selected="" value="4">
							4
						</option>
						<option value="5">
							5
						</option>
						<option value="6">
							6
						</option>
						<option value="7">
							7
						</option>
						<option value="8">
							8
						</option>
					</select>
				</p>
			</div>
			<div id="u99" class="check"
				style="width: 140px;height:50px;float: left;">
				<p>
					<span>串口</span><span>:</span>
					<select id="u97_input">
						<option selected="" value="COM1">
							COM1
						</option>
						<option value="COM2">
							COM2
						</option>
						<option value="COM3">
							COM3
						</option>
						<option value="COM4">
							COM4
						</option>
						<option value="COM5">
							COM5
						</option>
					</select>
				</p>
			</div>
		 </div>	
		<div style="width:500px;height:50px;float: left; margin-top:20px; margin-left:120px;">
			<div style="width: 300px;float: left;" >
				<input type="submit"  class="sub1" value="保存">
		   </div>
		<div style="width: 80px">
				<input type="submit" class="sub2" value="清空">
			</div>
     </div>
	</div>
	<div class="modal fade" id="myModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <form id="addStation0sForm">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="info-item-content">
                    <table class="wide-table table">
                        <tr>
							<td style="border: none;">
								原密码：
								<div class="input-wrap">
									<input type="password" id="oldPass" name="oldPass" value="" class="fluid-input" />
								</div>
							</td>
						</tr>
						<tr>
							<td style="border: none;">
								新密码：
								<div class="input-wrap">
									<input type="password" id="newPass" name="newPass" value="" class="fluid-input" />
								</div>
							</td>
						</tr>
						 <tr>
							<td style="border: none;">
								确认密码：
								<div class="input-wrap">
									<input type="password" id="confirmPass" name="confirmPass" value="" class="fluid-input" />
								</div>
							</td>
						</tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <span type="button" class="btn" id="savePass">确认</span>
                <span type="button" class="btn"  data-dismiss="modal">取消</span>
            </div>
        </div>
    </div>
  </form>
</div>
<script type="text/javascript">
	$("#savePass").click(function(){
		if($("#oldPass").val()==""){
			alert("请输入原密码");
			return;
		}
		if($("#newPass").val()==""){
			alert("请输入新密码");
			return;
				}
		if($("#confirmPass").val()==""){
			alert("请确认密码");
			return;
		}
		if($("#newPass").val()!=$("#confirmPass").val()){
			alert("两次输入不一致 请检查");
			return;
		}
		var url = "<%=request.getContextPath()%>/login/changePass";
		var params = $("#addStation0sForm").serialize();
		Util.ajax.postJson(url, params, function(data,flag){
				if(data.returnCode=="1"){
					alert(data.returnMessage);
					$("#myModal").modal('hide');
				}else{
					alert(data.returnMessage);
				}
		});
		
	})
</script>
</body>
</html>
