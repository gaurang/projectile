<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projectile:</title>
<link rel="stylesheet" type="text/css" href="css/crm/style.css" />
<link rel="stylesheet" type="text/css" href="css/ui.dropdownchecklist.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="ui/jquery.ui.core.css" />
<link rel="stylesheet" type="text/css" href="ui/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="ui/jquery.ui.dialog.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/custom-theme/jquery.ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jqgrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="js/colorbox/colorbox.css" />
<link rel="stylesheet" type="text/css" href="css/dynamic-tab/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/dynamic-tab/icon.css" />
<link rel="stylesheet" type="text/css" href="css/progress/loadingbox.css" />
<!-- jquery.js -->
<script src="js/certUpload/jquery.min.js"></script> 
<script src="js/jquery/jquery-ui-1.7.2.custom.min.js" type="text/javascript"></script>
<script src="js/dynamic-tab/jquery.easyui.min.js" type="text/javascript"></script>

<!-- jquery ui -->
<script src="ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="ui/jquery.ui.mouse.js" type="text/javascript"></script>
<script src="ui/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="ui/jquery.ui.position.js" type="text/javascript"></script>
<script src="ui/jquery.ui.resizable.js" type="text/javascript"></script>
<script src="ui/jquery.ui.dialog.js" type="text/javascript"></script>
<script src="ui/jquery.bgiframe-2.1.2.js" type="text/javascript"></script>
<script src="js/jquery-dropdown/ui.core-min.js" type="text/javascript"></script> 
<script src="js/jquery-dropdown/ui.dropdownchecklist-min.js"
	type="text/javascript"></script>
<script src="js/datepicker.js" type="text/javascript"></script>
<script src="js/colorbox/jquery.colorbox.js" type="text/javascript"></script>

<!-- jqgrid -->
<script src="js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>

<!-- forms and validation -->
<script src="js/jquery/jquery.form.js" type="text/javascript"></script>
<script src="js/jquery/jquery.autocomplete.js" type="text/javascript"></script>
<script src="js/jquery/jquery.validate.js" type="text/javascript"></script>


<!-- projectile specific-->
<script src="js/common.js" type="text/javascript"></script>
<script src="js/fmt.js" type="text/javascript"></script>
<script src="js/s.js" type="text/javascript"></script>
<script src="js/reports.js" type="text/javascript"></script>
<script src="js/progress/progress.js"type="text/javascript" ></script>
<script type="text/javascript">
	$(document).ready(function() {
		if('${webURL}' !=''){
			func('${webURL}');
		}else{
			func('stock.html');
		}
  });
	function func(add) {
		$('#tt').tabs('select','Main');
		if (add == '') add = 'stock.html';
		if(add.indexOf('?')>0){
			add += '&webLink=1';
		}else{
			add += '?webLink=1';
		}
		$.showprogress("loading..");
		$.ajax({
			url : add,
			type : "GET",
			dataType : 'html',
			success : function(response) {
				$.hideprogress();	
				$('#container').html(response);
			},
	   error: function(jqXHR, textStatus, errorThrown){
		     $.hideprogress();
			 alert("Requesting page containing following error:"+textStatus);
		  }
		  
		});
	}

	function commonTab(){
		
	}
   var index = 0;
   function addTab(title, url){
       index++;
       var content= '';
       $.showprogress("loading..");
       $.ajax({
           url : url,
           type : "GET",
           dataType : 'html',
           success : function(response) {
           $.hideprogress();
        	    content = response;
        	    $('#tt').tabs('add',{
        	           title:'' + title,
        	           content:'' + content,
        	           iconCls:'icon-save',
        	           closable:true,
        	           tools:[{
        	               iconCls:'icon-mini-refresh',
        	               handler:function(){
        	                   alert('refresh');
        	               }
        	           }]
        	       });
           }
       });
    }
   
</script>
</head>
<body>
	<!-- Main Content here -->
    <div id="tt" class="easyui-tabs" tools="#tab-tools" style="margin-top: -30px;">
        <div title="Main" tools="#p-tools">
			<div id="container">
			</div>
		</div>
    </div>
</body>
</html>