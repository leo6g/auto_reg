/**
 * <!-- JiaThis Button BEGIN -->
 */
var jiathis_config = {
	siteNum : 5,
	sm : "qzone,weixin,tsina,tqq,fav",
	url : "http://120.92.149.186/front/index#",
	summary : "群发软件  加人软件  微信群发  微信加人",
	title : "正版群发软件 0元 注册 ##",
	boldNum : 2,
	pic : "http://120.92.149.186/images/logo.png",
	showClose : true,
	shortUrl : false,
	hideMore : false
}
var pageMark = Util.browser.getParameter("page");
if (pageMark.indexOf("how_to_use") != -1) {
	$("#how_to_use").addClass("mnuSel");
} else {
	$("#" + pageMark).addClass("mnuSel");
}
