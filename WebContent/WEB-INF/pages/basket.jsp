<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>

<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/custom-theme/jquery-ui-1.7.3.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jqgrid/css/ui.jqgrid.css" />

<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script src="js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="js/fmt.js" type="text/javascript"></script>


<script type="text/javascript">
function addToFav(){
	var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	$.getJSON('addFavorite.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
			}
		}
	});
}
function addToCart(){
	var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	$.getJSON('addToCart.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
			}
		}
	});
}
function removeToFav(){
	var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	$.getJSON('removeFavorite.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
			}
		}
	});
}
function submitOrder(form){
	var valid= true;
	
	if($('#contact').val() == ''){
			alert('Enter Contact Person');
			valid = false;
	}

	if(valid){
		form.submit();
	}
	<%--var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	$.ajax({
	    type: "POST",
	    url: "submitOrder.html",
	    data: "rows=" + $("#grid").getGridParam("selarrrow"),
	    success: function(){
	        alert("submitted.");
	    }
	});--%>
		
}
jQuery(document).ready(function(){
jQuery("#newapi").jqGrid({
	<c:choose>
		<c:when test="${TableHeader!=null}">url:'${gridLink}' ,</c:when>
		<c:otherwise> url:'stockLoadGrid.html?q=2',</c:otherwise>
	</c:choose>
	 datatype: "json", 
	 colNames:[${headers}],
	 colModel:[
	      	 ${colModel}
	      	 ], 
		pager: '#pnewapi',
		sortname: 'cts',
		viewrecords: true, 
		sortorder: "desc", 
		width: "918", 
		height: "360",
		shrinkToFit : "false", 
		<c:choose>
			<c:when test="${TableHeader!=null}">caption:"${TableHeader}" ,</c:when>
			<c:otherwise> caption:"Search Result" ,</c:otherwise>
		</c:choose>
		multiselect:"true",
		footerrow : true,
		userDataOnFooter : true,
		afterInsertRow: function(rowid, aData, rowData){ 
		 	adjustHeight($("#newapi"),360);
		}, 
		
}); 
jQuery("#newapi").jqGrid('navGrid','#pnewapi',{edit:false,add:false,del:false,search:false});
	
	//select all the a tag with name equal to modal
	//if close button is clicked
	$('.window .close').click(function (e) {
		//Cancel the link behavior
		e.preventDefault();
		$('#mask').hide();
		$('.window').hide();
	});		
	
});

function modalMsg(id) {
	var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	
	if(s.length == 0 ){
		alert('Please select packets');
		return false;
	}
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
	$(id).css('top',  winH/2-$(id).height()/2);
	$(id).css('left', winW/2-$(id).width()/2);

	//transition effect
	$(id).fadeIn(2000); 
}

</script>
</head>
<body >


<table  align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15" ></td>
    <td><table  align="center" border="0" cellpadding="0" 
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
<jsp:include page="include/header.jsp"></jsp:include>
</td>
                  </tr>
                  <tr>
                    <td >
    <!-- Main COntent here -->

<form action="submitOrder.html" method="POST" name="basket">
<div id="boxes">
	<div id="dialog" class="window">
		<div id="Pagetitle" class="ui-widget-header"><h1 > Order Confirmation <img src="images/Close-16.png" class="close" alt="close" /></h1></div>
		<br/>
		<table>
		<tr>
			<td width="50%">
				<input type="radio" name="orderType" id="moreInfo" checked="checked" value="Request">
				<label for="request">More Details</label>
			</td>
			<td>
				<input type="radio" name="orderType" id="confirm" value="Confirm">
				<label for="confirm">Confirm Order</label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="coments" class="alignTop">Comments : </label>
			</td>
			<td>
				<textarea rows="3" cols="20" name="coments" id="comensts"></textarea>
			</td>
		</tr>
		<tr>
			<td>
				<label for="contact" class="alignTop">Contact Person :</label>
			</td>
			<td>
				<input name="contact" id="contact" class="inputText1" style="width: 178px;">
			</td>
		</tr>
		</table>
		<div align="center"> <input type="button" name="buy" id="buy" value="Submit" onclick="javascript:submitOrder(this.form);"> </div>
		<div align="left" style="font-size:12px;">Note - All orders will be confirmed only via E-mail. You can track status under "My Confirm Orders" to be added</div>
	</div>
</div>
<div id="mask"></div>
  <div id="content" align="center">
	
<table id="newapi" ></table> 
<div id="pnewapi"></div>

<table align="center">
<tr>
<c:choose>
	<c:when test="${TableHeader eq 'My Favorites'}">
	<td class="button" >	
		<input type="button" name="basket" id="basket" value="Add to Cart">
	</td>	
	</c:when>
	<c:when test="${TableHeader eq 'My Cart'}">
	<td class="button" >
		<input type="button" name="order" id="order" value="Order" onclick="javascript:modalMsg('#dialog');"/>
	</td>
	<td class="button" >	
		<input type="button" onclick="javascript:window.open('makeExcel.html?stage=B');" value="Excel"/>
	</td>
	<td class="button" >	
		<input type="button" onclick="window.location='myCart.html?clr=1'" value="Clear Cart"/>
	</td>
	</c:when>
	<c:otherwise>
	<td class="button" >	
		<input type="button" name="basket" id="basket" value="Add to Cart" onclick="javascript:addToCart();">
	</td>
	</c:otherwise>
</c:choose>
</tr>
</table>
</div>
</form>
 <!-- Data ends -->
    
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
background="images/shadow_right.png"><img 
src="images/shadow_right.png" width="15" 
height="1"></td>
  </tr>
</tbody></table>
<map name="Map2" id="Map2"><area shape="rect" coords="710,209,893,246" 
href="index.html"><area 
shape="rect" coords="710,209,893,246" 
href="index.html">
</map>
<input id="gwProxy" type="hidden"><!--Session data--><input 
onclick="jsCall();" id="jsProxy" type="hidden"><div id="refHTML"></div>
	
</body>
</html>