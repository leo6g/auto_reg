// JScript 文件

function ResumeError() { return true; } 
window.onerror = ResumeError;

function $(_sId){
    return document.getElementById(_sId);
}

function $n(_sId){
    return document.getElementsByName(_sId);
}

//****************************************//
//  Form操作  //
function ShowForm(_ID){
    if($(_ID).style.display==""){
        $(_ID).style.display="none";
        ShowSelect();
    }else{
        $(_ID).style.display="";
        HiddeSelect();
    }
    
}
function CloseForm(_ID){
    $(_ID).style.display="none";
    ShowSelect();
} 
//****************************************//


//******************************获得控件的坐标位置*************************//
function GetTagX(object){
 try{
  var obectLeft = object.offsetLeft; 
  var otag=object;
  while (otag.tagName!="BODY"){obectLeft+=otag.offsetLeft;otag=otag.offsetParent;}
  return obectLeft+9;
 }//try
 catch(err){
  return 100;
 }//catch
}

function GetTagY(object){
  try{
    var objectTop  = object.offsetTop;
    var otag=object;
    while (otag.tagName!="BODY"){objectTop+=otag.offsetTop;otag=otag.offsetParent;};
    return objectTop+object.offsetHeight+14;
  }//try
  catch(err)
  {
    return 100;
  }//catch
}
//====================================结束=================================//

function Enter_KeyUP(Event){
        var _key;
        document.onkeyup = function(e){
            if (e == null) { // ie
                _key = event.keyCode;
            } else { // firefox
                _key = e.which;
            }
            
            if(_key == 13){
                Event();
            }
        }
    }

  function Data_GetItemValue(Data,SubItemName,RootItemSplit,SubItemSplit){
     var arrRootItems = Data.split(RootItemSplit);//=============分割成数组
        //'//alert(arrRootItems.length);
     for (var i=0;i<=arrRootItems.length-1;i++) {
          var arrSubItems=arrRootItems[i].split(SubItemSplit);
        //'//alert(arrSubItems.length);
          if (arrSubItems.length==2) {
              var paraName=arrSubItems[0];
              var paraData=arrSubItems[1];
        //'//alert(paraName.toUpperCase());
              if(paraName.toUpperCase() == SubItemName.toUpperCase()) {
                 return paraData;
              }
          }
     }

     return "";  
  }

    function replaceAll(text,replacement,target){
       if(text==null || text=="") return text;//如果text无内容,返回text
       if(replacement==null || replacement=="") return text;//如果replacement无内容,返回text
       if(target==null) target="";//如果target无内容,设置为空串
       var returnString="";//定义返回值变量,并初始化
       var index=text.indexOf(replacement);//定义查找replacement的索引下标,并进行第一次查找
       while(index!=-1) {//直至未找到replacement,要么进行下面的处理
            returnString+=text.substring(0,index)+target;//如果找到的replacement前有字符,累加到返回值中,并加上target
            text=text.substring(index+replacement.length);//取掉找到的replacement及前边的字符
            index=text.indexOf(replacement);//进行查询,准备下一次处理
       }
       if(text!="") returnString+=text;//如果找到的最后一个replacement后有字符,累加到返回值中 
       return returnString;//返回 
   }	
   
	//检查是否是英文数字加-(首尾不能是-)
    function IsEnglishNum(String)
    { 
        var Letters = "abcdefghijklmnopqrstuvwxyz0123456789-";
        var i;
        var c;
        if(String.charAt(0)=='-')return false;
        if(String.charAt(String.length-1)=='-')return false;
        for(i=0;i<String.length;i++ )
        {
            c=String.charAt(i);
    	    if(Letters.indexOf(c)<0)
    	    return false;
        }
        return true;
    }

    //更改字体大小
    var status0='';
    var curfontsize=10;
    var curlineheight=18;
    function fontZoomA(){
        if(curfontsize>8){
        document.getElementById('fontzoom').style.fontSize=(--curfontsize)+'pt';
        	document.getElementById('fontzoom').style.lineHeight=(--curlineheight)+'pt';
    }
    }
    function fontZoomB(){
        if(curfontsize<64){
        document.getElementById('fontzoom').style.fontSize=(++curfontsize)+'pt';
        	document.getElementById('fontzoom').style.lineHeight=(++curlineheight)+'pt';
    }
    }
    function fontZoom(size)
    {
        document.getElementById('fontzoom').style.fontSize=size+'px';
    }
    //双击鼠标滚动屏幕的代码
    var currentpos,timer;
    function initialize()
    {
        timer=setInterval ("scrollwindow ()",30);
    }
    function sc()
    {
        clearInterval(timer);
    }
    function scrollwindow()
    {
        currentpos=document.body.scrollTop;
        window.scroll(0,++currentpos);
        if (currentpos !=document.body.scrollTop)
        sc();
    }
    //document.onmousedown=sc
    //document.ondblclick=initialize

    //生成1-9随机数
    function GetRndNo(length){return Math.floor(Math.random()*length)+1;}

    //生成不重复的字符串
    function GetRndChar(){
             var myDate=new Date;
             var theYear=myDate.getYear();
             var theMonth=myDate.getMonth();
             var theDay=myDate.getDay();
             var theHour=myDate.getHours();
             var theMinute=myDate.getMinutes();
             var theSecond=myDate.getSeconds();
             var theMilliseconds=myDate.getMilliseconds();
             return theYear+""+theMonth+""+theDay+""+theHour+""+theMinute+""+theSecond+""+theMilliseconds+""+GetRndNo(999);
    }

				function CheckUserLogin()
				{
				    if (document.getElementById("UserName").value==""){
					    Alert("对不起！用户名不能为空！",document.getElementById("UserName"));
						return false;
					}
					if (document.getElementById("PassWord").value==""){
					    Alert("对不起！密码不能为空！",document.getElementById("PassWord"))
						return false;
					}
					return true;
				}

    function ShowFlash(Path,width,height){
        document.write("		      <object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" width=\""+width+"\" height=\""+height+"\">\n")
        document.write("                 <param name=\"movie\" value=\""+Path+"\">\n")
        document.write("                 <param name=\"quality\" value=\"high\">\n")
        document.write("  			     <param name=\"wmode\" value=\"transparent\">\n")
        document.write("                 <embed src=\""+Path+"\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\""+width+"\" height=\""+height+"\"></embed>\n")
        document.write("		      </object>\n")
	}
	
	//检查是否是英文数字加-(首尾不能是-)
    function Ajax_Form_EnglishNum(String)
    { 
        var Letters = "abcdefghijklmnopqrstuvwxyz0123456789-";
        var i;
        var c;
        if(String.charAt(0)=='-')return false;
        if(String.charAt(String.length-1)=='-')return false;
        for(i=0;i<String.length;i++ )
        {
            c=String.charAt(i);
    	    if(Letters.indexOf(c)<0)
    	    return false;
        }
        return true;
    }
    
	function Ajax_CheckURL(URL) {
	    if(URL==""){return true;}
		if(URL.indexOf("/") == -1 || URL.indexOf(".") == -1) {
			return false;
		}
		return true;
	}
    
	//检查是否是数字
    function Ajax_Form_CheckNUM(NUM)
    {
    	var i,j,strTemp;
    	strTemp="0123456789.";
    	if (NUM.length== 0)
    		return true;
    	for (i=0;i<NUM.length;i++)
    	{
    		j=strTemp.indexOf(NUM.charAt(i));	
    		if (j==-1)
    		{
    		    //说明有字符不是数字
    			return false;
    		}
    	}
    	//说明是数字
    	return true;
    }
	//检查EMail
	function CheckEMail(strSource){
        var Letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-@.";
        var i;
        var c;
        if(strSource.charAt(0)=='-')return false;
        if(strSource.charAt(strSource.length-1)=='-')return false;
        for(i=0;i<strSource.length;i++ )
        {
            c=strSource.charAt(i);
    	    if(Letters.indexOf(c)<0)
    	    return false;
        }
		
	    if(strSource.length==0) {return true;}
		if(strSource.indexOf("@") == -1 || strSource.indexOf(".") == -1 || strSource.indexOf(" ") >0) {
			return false;
		}
		return true;
	}
	//检查电话号码
    function Ajax_Form_CheckTEL(TEL)
    {
    	var i,j,strTemp;
    	strTemp="0123456789-()# ";
    	for (i=0;i<TEL.length;i++)
    	{
    		j=strTemp.indexOf(TEL.charAt(i));	
    		if (j==-1)
    		{
    		//说明有字符不合法
    			return false;
    		}
    	}
    	//说明合法
    	return true;
    }
    
    function isCharsInBag(inputchar)
    { 
      var bagchar="><,[>{}?/+=|\\'\":;~!#$%()`@"; 
      var ii,cc;
      for (ii = 0; ii < inputchar.length; ii++)
      { 
        cc = inputchar.charAt(ii);//字符串inputchar中的字符
        if (bagchar.indexOf(cc) > -1)
        {
          return true;
        }
      }
      return false;
    }    
    
    function isBugTag(inputchar)
    { 
      var bagchar="><[>{}?/+=|\\'\":;~!#$%()`@"; 
      var ii,cc;
      for (ii = 0; ii < inputchar.length; ii++)
      { 
        cc = inputchar.charAt(ii);//字符串inputchar中的字符
        if (bagchar.indexOf(cc) > -1)
        {
          return true;
        }
      }
      return false;
    } 
    
    function Element_CheckedAll(object,action){
        var obj=object.getElementsByTagName('input');
        for(var i=0;i<obj.length;i++){
            if(obj[i].type=="checkbox"){obj[i].checked=action;} 
        }
    }

    //num表示要四舍五入的数,v表示要保留的小数位数。
    function decimal(num,v)
    {
        var vv = Math.pow(10,v);
        return Math.round(num*vv)/vv;
    }


   
   function ShowObj(_ID){
       $(_ID).style.display="";
   }
   
   function HiddenObj(_ID){
       $(_ID).style.display="none";
   }
   
   function HiddeSelect(){
       try{$("SeatType").style.display="none";}catch(e){}
       try{$("PlaneCompany").style.display="none";}catch(e){}
       try{$("TravelType").style.display="none";}catch(e){}
	   try{$("ManNumber").style.display="none";}catch(e){}
   }
   
   function ShowSelect(){
       try{$("SeatType").style.display="";}catch(e){}
       try{$("PlaneCompany").style.display="";}catch(e){}
       try{$("TravelType").style.display="";}catch(e){}
	   try{$("ManNumber").style.display="";}catch(e){}
   }
   
   
   
    function ChangeAreaSel(AreaValue) {
        if ($("Area").selectedIndex==0){
            $("sArea").style.display="none";
            return false;
        }else{
            $("sArea").style.display="";
        }
    	var i;
    	$("sArea").length=0;
    	$("sArea").options[0]=new Option("--请选择--","");
        //$("sArea").value=AreaValue;
    	for(i=0;i<SubCatCity.length;i++) {
    	    if (SubCatCity[i][1]==AreaValue) {
    		    $("sArea").options[$("sArea").length]=new Option(SubCatCity[i][0],SubCatCity[i][2]);            
            }
    	}//for
    	$("sArea").index=0;
            //$("sArea").value="";
    }  
    
    
    function ChangeAreaSel_Sub(AreaValue,SubValue) {
        if ($("Area").selectedIndex==0){
            $("sArea").style.display="none";
            return false;
        }else{
            $("sArea").style.display="";
        }
    	var i;
    	var SelIndex=0;
    	$("sArea").length=0;
    	$("sArea").options[0]=new Option("--请选择--","");
        //$("sArea").value=AreaValue;
    	for(i=0;i<SubCatCity.length;i++) {
    	    if (SubCatCity[i][1]==AreaValue) {
    		    $("sArea").options[$("sArea").length]=new Option(SubCatCity[i][0],SubCatCity[i][2]);   
    		    if (SubValue==SubCatCity[i][0]){SelIndex=$("sArea").length-1;}         
            }
    	}//for
    	$("sArea").selectedIndex=SelIndex;
            //$("sArea").value="";
    }     
    
    function ChangeAreaSelv(AreaValue) {
    	var i;
    	$("sArea").length=0;
    	$("sArea").options[0]=new Option("--请选择--","");
    	for(i=0;i<SubCatCity.length;i++) {
    	    if (SubCatCity[i][1]==AreaValue) {
    		    $("sArea").options[$("sArea").length]=new Option(SubCatCity[i][0],SubCatCity[i][2]);            
            }
    	}//for
    	$("sArea").index=0;
    }  
    
    function CheckedAll(object,action) {
      try{
       var CheckedCount=object.length;
       if (!CheckedCount){
          object.checked=action;
       }
       else{
          for(i=0;i<CheckedCount;i++){
              object[i].checked=action;
          }
       }
      }//
      catch(err){}
    }
function AddFavorite(sURL, sTitle)
{
     try
     {
         window.external.addFavorite(sURL, sTitle);
     }
     catch (e)
     {
         try
         {
             window.sidebar.addPanel(sTitle, sURL, "");
         }
         catch (e)
         {
             alert("加入收藏失败，请手动添加。");
         }
     }
}

function CopySome(obj){
    if(document.all){
        window.clipboardData.setData("Text",obj); 
    }
    alert("内容已复制到剪贴板了");
}