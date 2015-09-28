<%@ page isELIgnored="false" %>
<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<link href="css/contentslider.css" rel="stylesheet" type="text/css">
<c:if test="${param.jquery != 1}">
<script src="js/jquery/jquery.js" type="text/javascript"></script>
</c:if>
<script src="js/contentslider.js" type="text/javascript"></script>
<style>
<!--
#mask {
background-color:grey;
display:none;
left:0;
position:absolute;
top:0;
z-index:9000;
}
#boxes .window {
border:5px solid #736F6E;
display:none;
height:250px;
left:0;
padding:20px;
position:absolute;
top:0;
width:440px;
z-index:9999;
}
#boxes #dialog {
background-color:#3d555e;
color: #ffffff;
height:230px;
padding:10px;
width:375px;
}
.close {
float:right;
}

-->
</style>
<script type="text/javascript">
$(function() {
	$('.window .close').click(function (e) {
		//Cancel the link behavior
		e.preventDefault();
		$('#mask').hide();
		$('.window').hide();
	});		
	
});

function fnsubmit(){

	var valid = true;
	if($('#email').val() =='' || $('#login').val() =='' || $('#question').val() ==''|| $('#answer').val() ==''){
		alert( 'Enter all details');
		return; 
	}
}
function fnValid(theForm){
	if(document.getElementById('username').value == '' || document.getElementById('password').value ==''){
		alert( 'Please enter username/password');
		return false;
	}else{
		return true;
	}
}
function modalMsg(id) {
	//Get the screen height and width
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();

	//Set heigth and width to mask to fill up the whole screen
	$('#mask').css({'width':maskWidth,'height':maskHeight});
	
	//transition effect		
	$('#mask').fadeIn(1000);	
	$('#mask').fadeTo("slow",0.8);	

	//Get the window height and width
	var winH = $(window).height();
	var winW = $(window).width();
          
	//Set the popup window to center
	var scrolledY = document.body.scrollTop || document.documentElement.scrollTop || self.pageYOffset;
	
	$(id).css('top',  scrolledY+winH/2-$(id).height()/2);
	$(id).css('left', winW/2-$(id).width()/2);

	//transition effect
	$(id).fadeIn(2000); 
}

</script>
<div id="boxes">
	<div id="dialog" class="window">
		<div id="Pagetitle" class="ui-widget-header"><h1 > Forgot Password <img src="images/Close-16.png" class="close" alt="close" /></h1></div>
		<table>
		<tr>
			<td>
				<label for="login" class="alignTop">User Name : </label>
			</td>
			<td>
				<input name="login" id="login" class="inputText" style="width: 178px;"/>
			</td>
		</tr>
		<!--<tr>
			<td>
				<label for="company" class="alignTop">Company Name :</label>
			</td>
			<td>
				<input name="company" id="company" class="inputText" style="width: 178px;"/>
			</td>
		</tr>
		--><tr>
			<td>
				<label for="email" class="alignTop">Email:</label>
			</td>
			<td>
				<input name="email" id="email" class="inputText" style="width: 178px;"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="question" class="alignTop">Security Question :</label>
			</td>
			<td>
      	  		<select name="question" id="question" style="width: 178px;">
         			<option value="-1" >--select--</option>
         			<option value="1" >What was your childhood nickname ? </option>
         			<option value="2" >What is your spouse's maiden name ?</option>
         			<option value="3" >What is your pet's name ?</option>
         			<option value="4" >Who was your childhood hero ?</option>
         	  	</select>
         	  	</td>
     	  				</tr>
     	  				<tr>
			<td>
				<label for="answer" class="alignTop">Answer:</label>
			</td>
			<td>
				<input type="text" name="answer" id="answer" style="width: 178px;"/>
			</td>
		</tr>
     	  				
  	
		</table>
		<div align="center"> <input type="button" name="submit" id="submit" value="submit" onclick="javascript:fnsubmit();"/> </div>
	</div>
</div>
<div id="mask"></div>
<div class="header">
	 <c:choose>
         <c:when test="${USER_SESSION.userId >0 }">
        	<div align="right"> 
        	 	<br/>
        	 	<br/>
        	 	Welcome, <br/><br/>
        	 	${USER_SESSION.compnayName} 
        	 	<br/><br/>
        	 	<a href="webUserSearch.html">Search Stock</a>  | <a href="logOut.html">Log out</a>
        	 	<br/><br/>
        	</div> 	
        	 	
         </c:when>
	<c:otherwise>  
	       <form name="form1" method="post" action="logIn.html"
	onsubmit="return fnValid(this.form);" >
		  <p align="right"> &nbsp;<c:out value="${INVALID}"/></p>
		  <p align="right">User Id : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <input name="username" type="text" id="username" size="18">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         </p>
		  <p align="right">Password : &nbsp;
		    <input name="password" type="password" id="password" size="18">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  </p>
		  <p align="right">
		  	<a href="javascript:void(0);" onclick="javascript:modalMsg('#dialog');"><img src="images/forgotpassword.png" alt="forgot password" width="97" height="29" border="0"></a>
		  	<input type="image" src="images/loginbtn.png" alt="login" width="40" height="28"/>
		  	<a href="registration.html"><img src="images/register.png" alt="register" width="56" height="29" border="0"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	       </form>
	 </c:otherwise>
	 </c:choose>
	 
		  <p align="center"><img src="images/menu.png" width="832" height="48" usemap="#Map" border="0">
		   	    <map name="Map">
	              <area shape="rect" coords="12,8,103,39" href="index.html" target="_self">
	              <area shape="rect" coords="158,8,270,39" href="about.jsp" target="_self">
	              <area shape="rect" coords="318,8,430,39" href="portfolio.jsp" target="_self">
	              <area shape="rect" coords="483,8,638,38" href="diamond_edu.jsp" target="_self">
	              <area shape="rect" coords="654,8,777,37" href="contact.jsp" target="_self">
	            </map>
	      </p>
</div>
<div class="banner" align="center">
	<div id="slider2" class="sliderwrapper" align="center">
          <div class="contentdiv"><img src="photos/b1.jpg" alt="image" /></div>
		       <div class="contentdiv"><img src="photos/b2.jpg" alt="image" /></div>
		  <div class="contentdiv"><img src="photos/b3.jpg" alt="image" /></div>
          
          <div class="contentdiv"><img src="photos/b4.jpg" alt="image" /></div>
	    </div>
	    <div id="paginate-slider2" class="pagination"></div>
<script type="text/javascript">
                    
                    featuredcontentslider.init({
                    id: "slider2",  //id of main slider DIV
                    contentsource: ["inline", ""],  //Valid values: ["inline", ""] or ["ajax", "path_to_file"]
                    toc: "markup",  //Valid values: "#increment", "markup", ["label1", "label2", etc]
                    nextprev: ["Previous", "Next"],  //labels for "prev" and "next" links. Set to "" to hide.
                    revealtype: "click", //Behavior of pagination links to reveal the slides: "click" or "mouseover"
                    enablefade: [true, 0.2],  //[true/false, fadedegree]
                    autorotate: [true, 5000],  //[true/false, pausetime]
                    onChange: function(previndex, curindex){  //event handler fired whenever script changes slide
                    //previndex holds index of last slide viewed b4 current (1=1st slide, 2nd=2nd etc)
                    //curindex holds index of currently shown slide (1=1st slide, 2nd=2nd etc)
                    }
                    })
            
           			 </script>
</div>