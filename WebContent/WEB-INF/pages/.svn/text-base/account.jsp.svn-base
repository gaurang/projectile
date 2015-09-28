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
<script type="text/javascript" src="js/jquery/jquery-ui-1.7.2.custom.min.js"></script>
<script src="js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="js/fmt.js" type="text/javascript"></script>
<script type="text/javascript">
function statusFormat( cellvalue, options, rowObject )
{
	if(cellvalue == 1)
		return 'Pending';
	else if(cellvalue == 2)
		return 'Approved';
	else if(cellvalue == 3)
		return 'Rejected';
}
function reqShip(){
	var s =  jQuery("#newapi").jqGrid('getGridParam','selarrrow');
	if(s.length == 0){
			alert('Kindly select Packets for request.');
		}
	else{
	$.getJSON('reqShip.html', { sVal : s  }, function(json){
		if(json!=null && json !=""){
			if(json.indexOf("ok") == -1 ){
				alert(json);
			}else{
				$("#newapi").trigger("reloadGrid");
				alert("Dear, sir /n  We have successfully recived Your request for Shipment.");
			}
		}
	});
	}
}
jQuery(document).ready(function(){
jQuery("#newapi").jqGrid({
	 url:'myAccountGrid.html?q=2',
	 datatype: "json", 
	 jsonReader:{
		  root: "results",
		  page: "pageNo",
		  total: "pageAmount" ,
		  records: "recordSize",
		  repeatitems: false,
		  id: "pktCode"
		  } ,
	 colNames:[
				'Order No.','Order Date', 'Packet No.', 'Rate','Cts','Shape','Purity'
				,'Color','Cut','Status'
		      ],
	 colModel:[
				{name:"orderId",index:"orderId",width:80},
				{name:"orderDate",index:"orderDate",width:120},
				{name:"pktCode", index:"pktCode",width:100},
				{name:"rate",index:"rate",width:60},
				{name:"cts",index:"cts",width:60},
				{name:"sh",index:"sh",width:60},
				{name:"pu",index:"pu",width:50},
				{name:"c",index:"c",width:50},
				{name:"ct",index:"ct",width:50},
				{name:"pktStatus",index:"pktStatus",width:50, formatter:statusFormat},
					
	          ], 
	    rowNum:50, 
	    rowList:[50,75,100], 
		pager: '#pnewapi',
		sortname: 'orderDate',
		shrinkToFit : "false", 
		viewrecords: true, 
		sortorder: "desc", 
		width: "918", 
		height: "360",

		caption:"My Confirm Orders" ,
		multiselect:"true",
		afterInsertRow: function(rowid, aData) {
				if(aData.pktStatus != 2)
			  		$("#jqg_"+rowid).attr("disabled",true);
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
</script>
</head>
<body>

<table width="918px" align="center" border="0" cellpadding="0" 
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
                    <td valign="bottom" height="131" >
<jsp:include page="include/header.jsp"></jsp:include>
</td>
                  </tr>
                  <tr>
                    <td >
					<div id="content" >
					<form method="POST" name="account">
							<br/>
							<table id="newapi" ></table> 
							<div id="pnewapi"></div>
							<table align="center">
								<tr>
									<td class="button">
										<input type="button" name="ship" value="Ship Goods" id="ship" onclick="javascript:reqShip();">
									 </td>
								</tr>
							</table>
					</form>
					</div>
 
                    </td>
                  </tr>
                  
                  <tr>
                     <td> <jsp:include page="include/footer.jsp"></jsp:include></td>
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