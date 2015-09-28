<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/fadestrip2.png);
	background-repeat: repeat-x;
	background-attachment: fixed;
	background-attachment: fixed;
}
-->
</style>
<script type="text/javascript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n];
   for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>

<script language="javascript">	
			window.setTimeout("window.location='${url}'",3000);
</script>
</head>
<body >

<table width="900px" align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15" >
</td>
    <td><table width="918" align="center" border="0" cellpadding="0" 
cellspacing="0">
      <tbody><tr>
        <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="2">
            <tbody><tr>
              <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="0">
                  <tbody><tr>
                    <td valign="bottom" 
 height="131">
<jsp:include page="include/headers.jsp"></jsp:include>
</td>
                  </tr>
       <tr>
                    <td >
                    <div id="content">

	<div style="overflow:hidden;" id="update_div" align="center">
		<div class="outer">
                        <div class="tit"><img src="images/success.jpg" width="50" height="50" /></div>
			<font size="+2">
			<c:choose>
				<c:when test="${url eq 'registration.html'}">
					Thank you for Registering with us. <br/>
					We have received your mail, our executives will contact you soon.  
				</c:when>
				<c:when test="${url eq 'registrationView.html'}">
					Your profile has been Successfully updated.
				</c:when>
				<c:otherwise>Successfully updated</c:otherwise>
			</c:choose>
				
			</font><br />
			<font size="+1" style="line-height:44px;">${section} </font>
			<br/>
			
			<span style="word-wrap: break-word; line-height:30px;">
				This page will disappear within 3 seconds, or you can click 
				<a href="${url}" ><font color="red">here</font>
			</a>instead!</span>
		</div>
	  </div>
					

</div>
                    </td>
                  </tr>
                  <tr>
                  <td >
                  <jsp:include page="include/footer.jsp"></jsp:include>
                  </td>
                  </tr>
                  
                  
              </tbody></table></td>
            </tr>
        </tbody></table></td>
      </tr>
    </tbody></table>
      </td>
    <td valign="top" width="15" 
style="background-image: url('images/shadow_right.png');"><img 
src="images/shadow_right.png" width="15" 
height="1"/></td>
  </tr>
</tbody></table>
<map name="Map2" id="Map2"><area shape="rect" coords="710,209,893,246" 
href="index.html"/><area 
shape="rect" coords="710,209,893,246" 
href="index.html"/>
</map>
<input id="gwProxy" type="hidden"/><!--Session data--><input 
onclick="jsCall();" id="jsProxy" type="hidden"/><div id="refHTML"></div>

</body></html>