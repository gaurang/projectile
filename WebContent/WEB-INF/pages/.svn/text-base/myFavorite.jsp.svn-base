<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>

<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.8rc2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jqgrid/css/ui.jqgrid.css" />

<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script src="js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>

<script type="text/javascript">
function addToFav(){
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
</script>
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
	
<br/>
<table id="newapi" ></table> 
<div id="pnewapi"></div>

<script type="text/javascript">
jQuery("#newapi").jqGrid({
	 url:'myFavoriteGrid.html',
	 datatype: "json", 
	 colNames:[${headers}],
	 colModel:[
	      	 ${colModel}
	      	 ], 
		rowNum:2, 
		rowList:[10,20,30], 
		pager: '#pnewapi',
		sortname: 'cts',
		viewrecords: true, 
		sortorder: "desc", 
		width: (0.92*screen.width), 
		shrinkToFit : "false", 
		caption:"My Favorites" ,
		multiselect:"true",
}); 
jQuery("#list2").jqGrid('navGrid','#pnewapi',{edit:false,add:false,del:false});
</script>
<input type="button" name="fav" id="fav" value="Remove" onclick="javascript:addToFav();"/><input type="button" name="basket" id="basket" value="Add to Cart">
<jsp:include page="include/footer.jsp"></jsp:include>
	
</body>
</html>