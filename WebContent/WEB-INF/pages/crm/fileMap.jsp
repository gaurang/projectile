<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projectile:</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
  var availableColList = new Array(); 
  var colList = new Array();
  var fileCol = new Array();
  
  var colListNew = new Array();
  var fileColNew = new Array();
  
  $(document).ready(function(){
	  var index = 0;
	  <c:forEach items="${FILES_MAP}" var="f" varStatus="status">
		  	if('${f.columnName}' != ''){
			   availableColList[index] = '${f.columnName}';
			   index++;
		  	}
		  	
		  if('${f.fileId}' > 0){
		  	colList[${status.index}] = '${f.columnName}';
		  	fileCol[${status.index}] = '${f.excelColumnName}';
		  }
      </c:forEach>
      colListNew = colList;
      fileColNew =  fileCol;
      updateMap(fileCol, colList);
  });
  
  	function updateMap(fileColLoc, colListLoc){
  		var count = $('#fileColCount').val();
		var html= '' ;
  		for(var z =0 ;z<count ;z++){
  			html += '<tr id="row_'+z+'">';
  			html += '<td>'+(z+1)+'</td>';
  			html += '<td><input type="checkbox" name="colIndex_'+z+'" id="colIndex_'+z+'" checked="checked" value="1" onclick="removeRow(\''+z+'\')"/> </td>'
			
  			html += '<td><select id="columnName_'+z+'" name="columnName_'+z+'">';
  			html += '<option value="">No Mapping</option>';	
	  			for(var a =0 ;a<availableColList.length ;a++){
	  				if(availableColList[a] == colListLoc[z]){
		  				html += '<option value='+availableColList[a]+' selected>'+availableColList[a]+'</option>';
	  				}else{
		  				html += '<option value='+availableColList[a]+'>'+availableColList[a]+'</option>';
	  					
	  				}
	  			}
  			html += '</select></td>';
  			var exlNm = '';
  			if(fileColLoc[z])
  				exlNm = fileColLoc[z];
  			html += '<td><input type="text" value="'+exlNm+'" id="excelColName_'+z+'" name="excelColName_'+z+'"/></td>';
  			html += '</tr>';
  		}
  		$('#columnMap').html(html);
  	}
  	function removeRow(fldId){
  		colListNew = jQuery.grep(colListNew, function(n, i){
						  		  return (i != fldId);
  						});

  		fileColNew = jQuery.grep(fileColNew, function(n, i){
	  		 				 return (i != fldId);
						 });
  		var count = $('#fileColCount').val();
  		$('#fileColCount').val(count-1);
  		updateMap(fileColNew, colListNew);
  	}
  	function validate(){
  		if($('#newFormat')){
  			if($('#fileName').val() == '' || $('#company').val() == ''){
  				alert('Please enter filename and company name ');
  				return false;
  			}
  		}
  	}
  </script>

</head>
<body>

	<jsp:include page="../inc/inc_header.jsp">
		<jsp:param name="page" value="utility" />
		<jsp:param name="subPage" value="addmap" />
	</jsp:include>
	<!-- Main Content here -->
	<div class="container">
		<div align="center" class="content">
			<input type="button" onclick="location.reload();" value="Refresh">
			<form action="updateFileMap.html" action="post"
				onsubmit="return validate();">
				<input type="hidden" id="fileId" name="fileId"
					value="${param.fileId}">
				<c:if test="${param.fileId == null}">
					<div class="ttl_names" id="newFormat">
						<label for="fileName">File Name</label><input type="text"
							name="fileName" id="fileName" /> <label for="type">File
							Type</label> <select name="type" id="type">
							<option value="EXCEL">Excel</option>
							<option value="CSV">CSV</option>
						</select> <label for="processType">File Process</label> <select
							name="processType" id="processType">
							<option value="IN">IN</option>
							<option value="OUT">OUT</option>
						</select> <label for="rapFormat">RapFormat</label> <select name="rapFormat"
							id="rapFormat">
							<option value="1">Out of 100 (ex. -25)</option>
							<option value="100">Out of 1000 (ex. -0.25 )</option>
						</select> <br /> <label for="company">Company</label><input type="text"
							name="company" id="company" /> <label for="LAB">LAB</label> <select
							name="LAB" id="LAB">
							<option value="">Select</option>
							<c:forEach items="${PRP_LOV['LAB']}" var="l" varStatus="cnt">
								<option value="${l.description}">${l.description}</option>
							</c:forEach>
						</select> <input type="checkbox" name="mixed" /> Mixed
					</div>
				</c:if>
				<label>Number of Columns</label> <input type="text"
					value="${fileColCount}" onchange="updateMap(fileCol, colList);"
					name="fileColCount" id="fileColCount" /> <input type="button"
					value="Go" onclick="updateMap();" />
				<table id="greenTable">
					<tr>
						<th>Select</th>
						<th>Col Index</th>
						<th>Column Name</th>
						<th>File Column Name</th>
					</tr>
					<tbody id="columnMap">
					</tbody>
				</table>
				<input type="submit" value="Save Changes" />
			</form>
		</div>
	</div>
</body>
</html>