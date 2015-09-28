<%@page import="com.sun.xml.bind.v2.schemagen.xmlschema.Import"%>
<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ page import="java.util.*,com.basync.b2b.data.PrpData" %>

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
<script type="text/javascript" src="js/jquery/jquery-ui-1.7.2.custom.min.js"></script>
<script src="js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="js/fmt.js" type="text/javascript"></script>

<script type="text/javascript">
var selection = new Array();

function containsElement(arrayName,arrayElement)
{
   for(var i=0; i<arrayName.length;i++ )
    { 
       if(arrayName[i]==arrayElement)
    	return true;       
    } 
   return false;
 }
function removeByElement(arrayName,arrayElement)
{
   for(var i=0; i<arrayName.length;i++ )
    { 
       if(arrayName[i]==arrayElement)
           arrayName.splice(i,1); 
     } 
 }
function addToFav(){
	//var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	var s =  selection;
       if(s.length == 0 ){
		s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	}
	if(s.length == 0 ){
		alert('Please select packets');
	}
	$.getJSON('addFavorite.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.toString().indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
				$("#maxBox").html('Your selections have been added to Favorites, click <a href="myFavorite.html">here </a> to view Favorites.');
				$("#maxBox").show();
			}
		}
	});
}
function addToCart(){
	//var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	var s =  selection;
if(s.length == 0 ){
		s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	}
	if(s.length == 0 ){
		alert('Please select packets');
	}
	$.getJSON('addToCart.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.toString().indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
				$("#maxBox").html('<a href="myCart.html" style="color:#FFFFFF;">Your selections have been added to cart, click here  to view Cart.</a>');
				$("#maxBox").show();
			}
		}
	});
}
function removeToFav(){
	//var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	var s =  selection;
if(s.length == 0 ){
		s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	}
	if(s.length == 0 ){
		alert('Please select packets');
	}
	$.getJSON('removeFavorite.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.toString().indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
				$("#maxBox").html('Your selections have been Removed from Favorites.');
				$("#maxBox").show();
			}
		}
	});
}
function makeExcel(){
	//var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	var s =  selection;
if(s.length == 0 ){
		s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	}
	var str ='';
	for ( var i = 0; i < s.length; i++) {
		str +='&sVal='+s[i];
	}
	str +='&currency='+$("#currency").val()+'&factor='+$("#factor").val();
	window.open('makeExcel.html?1=1'+str);
}
function submitOrder(){
	//var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	var s =  selection;
if(s.length == 0 ){
		s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	}
	if(s.length == 0 ){
		alert('Please select packets');
	}
	$.getJSON('addToCart.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.toString().indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
				
			}
		}
	});
}

jQuery(document).ready(function(){

<% 
List<PrpData> prpLst = (List<PrpData>)request.getAttribute("prpList");
StringBuilder headers = new StringBuilder();
StringBuilder colModel = new StringBuilder();

for(int i =0;i< prpLst.size();i++){
	PrpData pd= prpLst.get(i);
	if(i > 0){
		colModel.append(", ");
		headers.append(", ");
	}
	headers.append("'").append(pd.getWebhdr()+"'");
	if(pd.getPrp().equalsIgnoreCase("lab")){
		colModel.append(" {name:'lab',index:'lab', width:"+ pd.getWidth()+", formatter:getCertLink}");
	}else if(pd.getPrp().equalsIgnoreCase("rate")){
		colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getRate }");
		if(request.getParameter("currency")!=null && !request.getParameter("currency").equals("")){
			String currency = request.getParameter("currency");
			headers.append(", '").append("Rate("+currency+")'");
			colModel.append(", {name:'rate',index:'rate', width:70,sortable:false, formatter:getRate }");
		}
	}
	else if(pd.getPrp().equalsIgnoreCase("rap")){
		colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getRap}");
	}
	else if(pd.getPrp().equalsIgnoreCase("pktCode")){
		colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getDNALink}");
	}
	else if(pd.getPrp().equalsIgnoreCase("total")){
		colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+", formatter:getRate}");
	}
	else if(pd.getPrp().equalsIgnoreCase("C")){
		if(request.getParameter("fancy")!=null && !request.getParameter("fancy").equals("")){
			colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:200}");
		}else{
			colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+"}");
		}
	}
	else{
		colModel.append(" {name:'"+ pd.getPrp()+"',index:'"+ pd.getPrp()+"', width:"+ pd.getWidth()+"}");
		//	colModel.append(", {name:'total',index:'total', width:70, formatter:getRate }");
	}
}
headers.append(", 'pairStock'");
colModel.append(", {name:'pairStock',index:'pairStock',  hidden: true  }");
request.setAttribute("colModel",colModel);
request.setAttribute("headers",headers);
%>

var colModels = [${colModel}];
var colHdrs =  [${headers}];
jQuery("#newapi").jqGrid({
	sortable: true, 
	<c:choose>
		<c:when test="${TableHeader!=null}">url:'${gridLink}' ,</c:when>
		<c:otherwise> url:'stockLoadGrid.html?q=2',</c:otherwise>
	</c:choose>
	 datatype: "json",
	 postData :{currency:$('#currency').val(), factor:$('#factor').val(),srchPair:$('#srchPair').val(),fancy:$('#fancy').val()}, 
	 colNames:colHdrs,
	 colModel:
			  colModels
	      	 , 
		rowNum:50, 
		rowList:[50,75,100], 
		pager: '#pnewapi',
		sortname: 'SZ_so',
		viewrecords: true, 
		sortorder: "desc", 
		//width: (0.87*screen.width), 
		width: "918", 
		afterInsertRow: function(rowid, aData, rowData){ 
			if(containsElement(selection, rowid))
			 	jQuery("#newapi").setSelection(rowid);
		 	
		 	if(rowData[rowData.length-2]==2){
		 		jQuery("#newapi").setCell(rowid,'pktCode','',{'background-color':'#cccccc'},'');
			}
		 	adjustHeight($("#newapi"),360);
		}, 
		onSelectRow: function(id, status){ 
			if(status)
				selection.push(id);
			else
				removeByElement(selection, id);
		} ,
		loadComplete: function() {
		     if (jQuery("#newapi").getGridParam("records")==0) {
		          jQuery("#newapi").html("Currently, there are no stones in the requested criteria, you can mail your requirement at <a href='mailto:info@hrc.com'>info@hrc.com</a>" );
		     }
		     if($('#srchPair').val() == 1){
					var rowData = $("#newapi").getRowData();
					var rids = $('#newapi').jqGrid('getDataIDs');
			        var pairCss = 'matchPair';
			        var lstPkt ='';
			        for (var i = 0; i < rowData.length; i++) {
			        	var rowid=rids[i];
			        	if(rowData[i].pktCode != lstPkt){
							if(pairCss == 'matchPair')			        		
			        			pairCss = '';
							else
								pairCss = 'matchPair';
			        	}
			        	jQuery('#newapi').jqGrid('setRowData', rowid, false, pairCss);
			        	lstPkt = rowData[i].pairStock;
			        }
				 }
		    if($('#fancy').val() == 1){
		   		var rowDataObj = $("#newapi").getRowData();
		   		var rids = $('#newapi').jqGrid('getDataIDs');
		        for (var i = 0; i < rowDataObj.length; i++) {
		        	var rowid=rids[i];
		        	if(rowDataObj[i].C == ''){
		        		jQuery("#newapi").jqGrid('setCell',rowid,'C',rowDataObj[i].FNC +' '+rowDataObj[i].FNCI +' '+rowDataObj[i].FNCO);
		        	}
		        }
		    }
		    jQuery("#newapi").hideCol('FNC');
		    jQuery("#newapi").hideCol('FNCI');
		    jQuery("#newapi").hideCol('FNCO');
		},
		height: "360",
		shrinkToFit : "false", 
		<c:choose>
			<c:when test="${TableHeader!=null}">caption:"${TableHeader}" ,</c:when>
			<c:otherwise> caption:"Search Result" ,</c:otherwise>
		</c:choose>
		multiselect:"true",
		footerrow : true,
		userDataOnFooter : true, 
		 jsonReader:{
            root: "rows",
            page: "page",
            total: "total",
            records: "records",
            cell:"cell",
            certId:"certId",
            id: "id",
            userdata: "userdata"
                
		} 
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

function fnSubmit(fld){
	window.location='invoiceReprint.html?OrderType=Request&orderId='+fld.value ;
}	
$("#currencyForm").submit(function() {
	
});


</script>
</head>
<body>


<table width="900px" align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15" ></td>
    <td><table  align="center" border="0" cellpadding="0" 
cellspacing="0">
      <tbody><tr>
        <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="2">
            <tbody><tr>
              <td class="mainStyle"><table width="100%" border="0" 
cellpadding="0" cellspacing="0" >
                  <tbody><tr>
                    <td valign="bottom" 
height="131">
<jsp:include page="include/header.jsp"></jsp:include>
</td>
                  </tr>
                  <tr>
                    <td>
    <!-- Main COntent here -->
<div id="content" align="center" >
<div id="boxes">
	<div id="dialog" class="window">
		<h1> Order Confirmation <img src="images/Close-16.png" class="close" alt="close" /></h1>
		<div>
			<span class="leftColumn"><input type="radio" name="orderType" id="confirm"><label for="confirm">Confirm Order</label></span>
			<span class="rightcolumn"><input type="radio" name="orderType" id="request"><label for="request">More Details</label></span>
		</div>
		<div>
			<span class="leftColumn"><label for="coments" class="alignTop">Coments : </label></span>
			<textarea rows="3" cols="20" name="coments" id="comensts"></textarea>
		</div>
		<div>
			<span class="leftColumn"><label for="contact" class="alignTop">Contact Person :</label></span> 
			<input name="contact" id="contect" class="inputText">
		</div>
		<div align="center"> <input type="button" name="submit" id="submit" value="buy"> </div>
	</div>
</div>
<div id="mask"></div>
	
<c:if test="${param.orderId !=null}">
<form action="invoiceReprint.html" name="orderNo" id="orderNo">
<div align="left"><b>Select Request</b> : 
			<select id="orderId" name="orderId" style="width:150px;" onchange="javascript:fnSubmit(this)">
				<option value="0">---All---</option>
				<c:forEach items="${OrderList}" var="o" >
					<option value="${o.id}" <c:if test="${param.orderId == o.id}">selected</c:if>>${o.id} - ${o.description}</option>
				</c:forEach>
			</select>

</div>
</form>
<br/>
</c:if>
	<div class="style8">
	<form name="currencyForm" id="currencyForm" action="stockSearch.html">
	
		Currency Convert : 
				<select id="currency" name="currency" style="width: 150px;">
					<option value="" <c:if test="${param.currency  == null || param.currency == 'USD'}">selected</c:if>>USD - Unites states Dollars</option>
					<option value="INR" <c:if test="${param.currency == 'INR' }">selected</c:if>>INR - Indian Rupees</option>
					<option value="GBP" <c:if test="${param.currency == 'GBP'}">selected</c:if>>GBP - British Pounds</option>
					<option value="EUR"<c:if test="${param.currency == 'EUR'}">selected</c:if>>EUR - Europian Euros</option>
					<option value="AUD"<c:if test="${param.currency == 'AUD'}">selected</c:if>>AUD - Australia Dollars</option>
					<option value="HKD"<c:if test="${param.currency == 'HKD'}">selected</c:if>>HKD - Hong Kong Dollars</option>
					<option value="ILS"<c:if test="${param.currency == 'ILS'}">selected</c:if>>ILS - Israeli Shelkel</option>
					<option value="CAD"<c:if test="${param.currency == 'CAD'}">selected</c:if>>CAD - Canadian Dollars</option>
					<option value="AED"<c:if test="${param.currency == 'AED'}">selected</c:if>>AED - Aram emirates Dirham</option>
					<option value="OTH"<c:if test="${param.currency == 'OTH'}">selected</c:if>>Others</option>
				</select>
				Trade Factor :
				<input id="factor" name="factor" value="${param.factor}" type="text" size="5"/> <input type="submit" id="convert" value="convert" >
	<input type="hidden" id="srchPair" name="srchPair" value="${param.srchPair}">
	<input type="hidden" id="fancy" name="fancy" value="${param.fancy}">
	</form>			
	</div>
	<div id="maxBox" style="display: none;color: white;font-weight: bold;"><a href="myCart.html" >Your selections have been added to cart, clickhere to view Cart.</a> </div>
	<table id="newapi" ></table> 
	<div id="pnewapi"></div>
	
				<c:choose>
					<c:when test="${TableHeader eq 'My Favorites'}">
					<table align="center">
					  <tr>
						<td class="button">
						<input type="button" name="basket" id="basket" value="Add to Cart" onclick="javascript:addToCart();">
						</td>
						<td class="button">
							 	<input type="button" onclick="javascript:window.open('makeExcel.html?stage=F');" value="Excel"/>
						</td>
					  </tr>
					</table>
					</c:when>
					<c:when test="${TableHeader eq 'My Cart'}">
					<table align="center">
					 <tr>
						<td class="button" >
							<input type="button" name="basket" id="basket" value="Add to Cart" onclick="javascript:addToCart();">
						</td>
						<td class="button">
							<input type="button" onclick="javascript:window.open('makeExcel.html');" value="Excel"/>
						</td>
					 </tr>
					</table>
					</c:when>
					<c:otherwise>
						<table align="center">
							<tr>
							<td class="button">
								<input type="button" name="basket" id="basket" value="Add to Cart" onclick="javascript:addToCart();">
							 </td>
							 <td class="button">
							 	<input type="button" onclick="javascript:makeExcel();" value="Excel"/>
							 </td>
							 
							 <td class="button">
							 	<input type="button" onclick="window.location='webUserSearch.html'" value="Modify"/>
							 </td>
						  </tr>
						</table>
					</c:otherwise>
				</c:choose>
	
	</div>
    <!-- Data ends -->
    
                    </td>
                  </tr>
                  <tr>
	                  <td>
	                  	<jsp:include page="include/footer.jsp"></jsp:include>
	                  </td>
                      
                  </tr>
              </tbody></table></td>
            </tr>
        </tbody></table></td>
      </tr>
    </tbody></table>
      </td>
    <td valign="top" width="15" ></td>
  </tr>
</tbody></table>
              
</body></html>