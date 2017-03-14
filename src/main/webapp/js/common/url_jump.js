/**
 * 跳转手机页面
 */
var url = window.location.href;
var mobile_index = url.indexOf("/mobile");
if ((navigator.userAgent.match(/(iPhone|iPod|Android|ios|iPad)/i))) {
	if(mobile_index==-1){
		window.location=contextPath+"/front/mobile"+url.substring(url.indexOf("/front")+6);
	}
}else{
	if(mobile_index>-1){
		window.location=contextPath+"/front"+url.substring(mobile_index+7);
	}
}
