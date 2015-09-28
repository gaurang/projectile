<%@include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co. </title>
<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jquery-multiselect/themes/ui-lightness/jquery.ui.all.css" />
 
    <link rel="stylesheet" type="text/css" href="css/ui.dropdownchecklist.css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
     <script type="text/javascript" src="js/stuHover.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-dropdown/ui.core-min.js"></script>
    <script type="text/javascript" src="js/jquery-dropdown/ui.dropdownchecklist-min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.corners.js"></script>
 	<script type="text/javascript" src="js/jquery/script.js"></script>
 	<script type="text/javascript" src="js/web.js"></script>
 
 	
	<script type="text/javascript">
	$(document).ready(function(){
			$("#sz").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 100 });  
			$("#lab").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 150 });  
		//	$("#<c:out value='${prpList.prp}_to'/>").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 100 });  
	//		$('.acc-content').jacg({'radius': '1em', 'start': '#ffffff', 'end': '#2E6E9E'});

			//$('#shpAll').bind("click", chkAll('shpAll', 'sh'));

			$('select[name*="_from"]').bind("change", function(){
				var elemName = this.name.replace("_from","_to");
				if($('#'+elemName)!=null && $('#'+elemName).val() ==-1){
					$('#'+elemName).val(this.value);
				}else{
					prpOrder(this.id,elemName);
				}
				
			});

			$('select[name*="_to"]').bind("change", function(){
				var elemName = this.name.replace("_to","_from");
				prpOrder(elemName, this.id);
			});

			$('input[name*="_from"]').bind("change", function(){
				var elemName = this.name.replace("_from","_to");
				if($('#'+elemName)!=null && $('#'+elemName).val() == '')
					$('#'+elemName).val(this.value);
				else{
					prpOrder(this.id,elemName);
				}
			});

			$('input[name*="_from"]').bind("change", function(){
				var elemName = this.name.replace("_to","_from");
				prpOrder(elemName, this.id);
			});

			$('#fancyTable').hide();
			
			$('#fancy').bind("click", function(){

				if($(this).attr("checked")){
					$('#fancyTable').show();
					$('#C_from').attr("disabled", true);
					$('#C_to').attr("disabled", true);
				}else{
					$('#fancyTable').hide();
					$('#C_from').attr("disabled", false);
					$('#C_to').attr("disabled", false);
				}
			});

			$('#acc h3').click(function() {
				var img = jQuery("img", this);
				if(img.attr('src') == 'images/plus.png'){
					img.attr('src', 'images/minus.png');
				}else{
					img.attr('src', 'images/plus.png');
				}
			});
			
			var shpIn = '${SEARCH_PRP_MAP["SH"].prpIn}'.split(",");
			$.each(shpIn, function(index, value) { 
				$('#sh_'+value.replace(" ","")).attr("checked",true);
			});
			var labIn = '${SEARCH_PRP_MAP["LAB"].prpIn}'.split(",");
			$.each(labIn, function(index, value) { 
				$('#lab_'+value.replace(" ","")).attr("checked",true);
			});
			var partyAccIdIn = '${SEARCH_PRP_MAP["partyAccId"].prpIn}'.split(",");
			//$('#partyAccId').val(partyAccId);
			$.each(partyAccIdIn, function(index, value) { 
				$('#partyAccId_'+value.replace(" ","")).attr("checked",true);
			});
			
			
	});
	function prpOrder(from, to){
		if($('#'+from).val() > $('#'+to).val()  ){
			var swap = $('#'+to).val() ;
			$('#'+to).val($('#'+from).val());
			$('#'+from).val(swap);
		}
	}
	</script>
</head>
<body>


<table width="900px" align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15" 
background="images/shadow_left.png"><img 
src="images/shadow_left.png" width="15" 
height="1"></td>
    <td><table  align="center" border="0" cellpadding="0" 
cellspacing="0">
      <tbody><tr>
        <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="2">
            <tbody><tr>
              <td class="mainStyle"><table width="880" border="0" 
cellpadding="0" cellspacing="0" >
                  <tbody><tr>
                    <td valign="bottom" height="131"  >
<jsp:include page="include/header.jsp"></jsp:include>
</td>
                  </tr>
                  <tr>
                    <td id="content">
<div >

	<br/>
	<div class="searchBox" align="center">
	<form action="stockSearch.html" method="post" onsubmit="fnSubmit(this);" name="eForm" id="search">
		<div class="tableSubwrap" align="left">  
		<table width="90%">
		  <tr>	
			<td align="center" width="11%">
			<br/>
			<br/>
			<label for="shpAll">Select All :</label>
			<br/>
			<br/>
				<input type="checkbox" name="shpAll" id="shpAll" value"-1" onclick="chkAll('shpAll', 'sh');"/>
			</td>
			<td align="center">
				<img alt="Round" src="images/RO.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_10" value="10" checked="checked"/>
			 </td>
			 <td align="center">
				<img alt="Heart" src="images/HT.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_20" value="20"/>
			 </td>
			 <td align="center">
				<img alt="Pear" src="images/PR.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_30" value="30"/>
			 </td>

             <td align="center">
				<img alt="Princess" src="images/PC.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_40" value="40"/>
			 </td>	
  			 <td align="center">
				<img alt="Marquise" src="images/MQ.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_50" value="50"/>
			 </td>
			 <td align="center">
				<img alt="Cushion" src="images/CU.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_60" value="60"/>
			 </td>
			 <td align="center">
				<img alt="Cushion B" src="images/CB.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_70" value="70"/>
			 </td>
             <td align="center">
				<img alt="Oval" src="images/OV.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_80" value="80"/>
			 </td>

			 <td align="center">
				<img alt="Radient" src="images/RD.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_90" value="90"/>
			 </td>
			 <td align="center">
				<img alt="Sq. Radient" src="images/SR.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_100" value="100"/>
			 </td>
			 <td align="center">
				<img alt="Asscher" src="images/AS.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_110" value="110"/>
			 </td>
             <td align="center">
				<img alt="Emerald" src="images/EM.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_120" value="120"/>
			 </td>
		   </tr> 
		 </table>
		 <br/>
		<label for="locAll" style="margin-left: 5px;">Stock Location</label>
		 <table width="90%">
		  <tr>	
			<td align="center" width="11%">
			<label for="locAll">Select All :</label>
				<input type="checkbox" name="locAll" id="locAll" value"-1" onclick="chkAll('locAll', 'partyAccId');" checked="checked"/>
			</td>
			<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
			<td align="center">
				<label for="partyAccId_${s.id}">${s.branchCode}</label>
				<input type="checkbox" name="partyAccId" id="partyAccId_${s.id}" value="${s.id}" checked="checked"/>
			 </td>
			</c:forEach>
		 </table> 
		</div>
		<br/><br/>
		
		<ul class="acc" id="acc">
		  <li>	
			<h3><img alt="" src="images/plus.png" >Quick Search</h3> 
			<div class="acc-section">
			 <div class="acc-content">
				  <div class="inlineStrip">	
				<label class="prpLabel"> Size :</label>
				<select class="select" id="szQ" name="szQ" >
					<option value="-1">---All---</option>
						<c:forEach var="prpLov" items='${PRP_LOV["SZ"]}'>
							<option value='${prpLov.id}' >${prpLov.description}</option>
						</c:forEach>
				</select>
				</div>	
				<div class="inlineStrip">
				<label class="prpLabel">Purity : </label>
				<select class="select" id="puQ" name="puQ">
					<option value="-1">---All---</option>
						<c:forEach var="prpLov" items='${PRP_LOV["PU"]}'>
							<option value='${prpLov.id}'>${prpLov.description}</option>
						</c:forEach>
				</select>
				</div>
				<div class="inlineStrip">
				<label class="prpLabel">Color : </label>
				<select class="select" id="cQ" name="cQ">
					<option value="-1">---All---</option>
						<c:forEach var="prpLov" items='${PRP_LOV["C"]}'>
							<option value='${prpLov.id}' >${prpLov.description}</option>
						</c:forEach>
				</select>
				</div>
				<div class="inlineStrip">
				<label class="prpLabel">Lab : </label>
				<select class="select" id="labQ" name="labQ">
					<option value="-1">---All---</option>
						<c:forEach var="prpLov" items='${PRP_LOV["LAB"]}'>
							<option value='${prpLov.id}'>${prpLov.description}</option>
						</c:forEach>
				</select>
				</div>
				<br/>
				<br/>
				<div class="button" style="width: 50px;" align="center">  
					<input type="button"  value="Search"  id="search" onclick="javascript:fnSubmit('QS');"/>
				</div>
			</div>
			</div>
		</li>
		<li>  
		<h3><img alt="" src="images/minus.png" >  Advance Search</h3>
		<div class="acc-section">
		 <div class="acc-content">	
		 	<div align="left" style="width:88%;padding-bottom: 15px;">
			 	<div class="vMiddleALign">
			 		<label for="sz">Size :</label>
			 		<select name="sz" id="sz" class="select" multiple='multiple' style="width: 500px;">
						<option value="-1">---All---</option>
						<c:forEach var="prpLov" items='${PRP_LOV["SZ"]}'>
							<option value='${prpLov.id}' <c:if test="${fn:contains(SEARCH_PRP_MAP[prpList.prp].prpIn,prpLov.id)}">selected</c:if>>${prpLov.description}</option>
						</c:forEach>
					 </select>
				 </div>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <c:if test="${SMART_SEARCH != null}">
				 <div class="vMiddleALign" >
				 <label for="smartSearchId">Saved Search :</label>
			 		<select name='smartSearchId' id='smartSearchId' class="select" onchange="document.location='webUserSearch.html?smartSearchId='+this.value;">
						<option value="-1">---All---</option>
						<c:forEach var="s" items='${SMART_SEARCH}'>
							<option value='${s.id}'>-${s.description}</option>
						</c:forEach>
					 </select>
				 </div>
				 </c:if>
			  	<%--<c:if test="${OrderList != null}">
				 <div class="vMiddleALign" >
				 <label for="smartSearchId">Requested :</label>
			 		<select name='orderId' id='orderId' class="select" >
						<option value="-1">---All---</option>
						<c:forEach var="s" items='${OrderList}'>
							<option value='${s.id}'>${s.id} - ${s.description}</option>
						</c:forEach>
					 </select>
				 </div>
				 </c:if>--%><!--
				 
				 <span id="selfPartyStock" >
						<label for="partyAccId" class="ttl_names">Location</label>
				 		<select id="partyAccId" name="partyAccId" style="width: 130px;">
							<c:forEach items="${SELF_LIST}" var="s" varStatus="cnt">
							 	<option value="${s.id}" <c:if test="${cnt.index==0}"> selected</c:if>>${s.companyName}/${s.branchCode}</option>
							</c:forEach>
						</select>
				</span>
		 	--></div>
		 	<table>
		 	<tr>
		 	<td>
		 	
			<table class="bluetable">
				<thead>
					<tr>
					<th>Property</th>
					<th>From</th>	
					<th>To</th>	
					</tr>
					
				</thead>
				<tbody>
				
				<c:forEach var="prpList" items="${PRP_LIST}" varStatus="list" begin="1">
				<c:if test="${list.count % 2 != 0 }">
				
				<c:set var="selectCss" value="class='select'"></c:set>
				<c:if test="${prpList.prp == 'LAB'}">
				<c:set var="selectCss" value="multiple='multiple' class='select'"></c:set>
				</c:if>
				<tr>
					<c:choose>
						<c:when test="${prpList.dataType == 1}">
							<c:set value='${prpList.prp}' var="prp"></c:set>
							<td>${prpList.webDesc} </td>
							<td>
									<c:choose>
										<c:when test="${prpList.prp == 'LAB'}">
											<select name='lab' id='lab' ${selectCss}>
											<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select name='${prpList.prp}_from' id='${prpList.prp}_from' ${selectCss}>
												<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
											
										 </c:otherwise>
									</c:choose>
							</td>
							<td>
								<c:if test="${prpList.displayDataType !=1}">
								<select name='${prpList.prp}_to' id='${prpList.prp}_to' ${selectCss} >
								<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
										<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpToNum == prpLov.id}">selected</c:if>> ${prpLov.description}</option>
									</c:forEach>
								</select>
								</c:if>
							</td>
							
						</c:when>
						<c:otherwise>
							<td>${prpList.webhdr}</td>
							<td><input type="text" name='${prpList.prp}_from' id='${prpList.prp}_from' class="inputText" <c:if test='${SEARCH_PRP_MAP[prpList.prp].prpFromNum > 0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpFromNum}"</c:if>
							onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
							<td><input type="text" name='${prpList.prp}_to' id='${prpList.prp}_to' class="inputText" <c:if test='${SEARCH_PRP_MAP[prpList.prp].prpToNum >0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpToNum}"</c:if>
							onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:if>
				</c:forEach>
				</tbody>
			</table>
			</td>
		 	<td>
			<table class="bluetable">
				<thead>
					<tr>
					<th>Property</th>
					<th>From</th>	
					<th >To</th>	
					
					</tr>
					
				</thead>
				<tbody>
				<c:forEach var="prpList" items="${PRP_LIST}" varStatus="list" begin="1">
				<c:if test="${list.count % 2 == 0}">
				<c:set var="selectCss" value="class='select'"></c:set>
				<c:if test="${prpList.prp == 'LAB'}">
				<c:set var="selectCss" value="multiple='multiple' class='select'"></c:set>
				</c:if>
				
				<tr>
					<c:choose>
						<c:when test="${prpList.dataType == 1}">
							<c:set value='${prpList.prp}' var="prp"></c:set>
							<td>${prpList.webDesc} </td>
							<td>
									<c:choose>
										<c:when test="${prpList.prp == 'LAB'}">
											<select name='lab' id='lab' ${selectCss}>
												<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select name='${prpList.prp}_from' id='${prpList.prp}_from' ${selectCss}>
												<option value="-1">---All---</option>
												<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
													<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
												</c:forEach>
											</select>
											
										 </c:otherwise>
									</c:choose>
									
							</td>
							<td>
							<c:if test="${prpList.displayDataType !=1}">
								<select name='${prpList.prp}_to' id='${prpList.prp}_to' ${selectCss}>
								<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
											<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpToNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
							</c:if>	
							</td>
							
						</c:when>
						<c:otherwise>
							<td>${prpList.webhdr}</td>
							<td><input type="text" name='${prpList.prp}_from' id='${prpList.prp}_from' class="inputText" 
							<c:if test='${SEARCH_PRP_MAP[prpList.prp].prpToNum >0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpFromNum}"</c:if> onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
							<td><input type="text" name='${prpList.prp}_to' id='${prpList.prp}_to' class="inputText" 
							<c:if test='${SEARCH_PRP_MAP[prpList.prp].prpToNum >0}'>value="${SEARCH_PRP_MAP[prpList.prp].prpToNum}"</c:if> onblur="javascript:chkNumVal(this,this.value,'${prpList.minValue} }','${prpList.maxValue}');"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:if>
				</c:forEach>
				</tbody>
			</table>
			</td>
			</tr>
			<tr>
			<td colspan="2" style="padding: 4px!important;">
			 <input type="checkbox" id="fancy" value="1" name="fancy"/> Fancy Color stones
			</td>
			</tr>
			<tr id="fancyTable">
			<td> 
			 <table class="bluetable"  >
					<tr>
							<td>Intensity </td>
							<td>
								<select name='FNCI_from' id='FNCI_from' ${selectCss}>
									<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV["FNCI"]}'>
										<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP['FNCI'].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
							</td>
							<td>
							<c:if test="${prpList.displayDataType !=1}">
								<select name='FNCI_to' id='FNCI_to' ${selectCss}>
								<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV["FNCI"]}'>
											<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP['FNCI'].prpToNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
							</c:if>	
							</td>
				</tr>
				<tr>			
							<td>Color </td>
							<td>
								<select name='FNC_from' id='FNC_from' ${selectCss}>
									<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV["FNC"]}'>
										<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP['FNC'].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
							</td>
							<td>
							<c:if test="${prpList.displayDataType !=1}">
								<select name='FNC_to' id='FNC_to' ${selectCss}>
								<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV["FNC"]}'>
											<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP['FNC'].prpToNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
							</c:if>	
							</td>
					</tr>
			</table>
			</td>
			<td>	
			<table class="bluetable" id="fancy">
					<tr>
							<td>Overtone </td>
							<td>
								<select name='FNCO_from' id='FNCO_from' ${selectCss}>
									<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV["FNCO"]}'>
										<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP['FNCO'].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
							</td>
							<td>
							<c:if test="${prpList.displayDataType !=1}">
								<select name='FNCO_to' id='FNCO_to' ${selectCss}>
								<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV["FNCO"]}'>
											<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP['FNCO'].prpToNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
							</c:if>	
							</td>
					</tr>
			</table>	
			</td>
			</tr>
			<tr>
			<td colspan="2">
			Search Ref. Stock No(comma separated) <input type="text" name="pktNo" id="pktNo" size="40" >
			</td>
			</tr>
			</table>
				<div>  
				<table>
				 <tr>
				 	<td class="button">	
						<input type="button"  value="Save & Search" id="save" onclick="javascript:fnSubmit('SS');"/> 
					</td>
					<td class="button">
						<input type="button"  value="Search"  id="search" onclick="javascript:fnSubmit('SR');"/>
				 	</td>
				 	<td class="button">
				 		<input type="hidden" id="srchPair" name="srchPair" value="-1">
						<input type="button"  value="Search Pair"  id="searchPair" onclick="$('#srchPair').val(1);javascript:fnSubmit('SR');"/>
				 	</td>
				 	<td class="button">
						<input type="button"  value="Reset"  id="Reset" onclick="javascript:$('form').clearForm();"/>
				 	</td>
				 </tr>
				</table>	
					<input type="hidden" name="searchType" id="searchType"/>
					<input type="hidden" name="sDesc" id="sDesc"/>
				</div>
			</div>
		 </div>
		</li>
	</ul>
	</form>
</div>
</div>
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
<script type="text/javascript" src="js/jquery/script.js"></script>

<script type="text/javascript">

var parentAccordion=new TINY.accordion.slider("parentAccordion");
parentAccordion.init("acc","h3",0,1);

//var nestedAccordion=new TINY.accordion.slider("nestedAccordion");
//nestedAccordion.init("nested","h3",1,-1,"acc-selected");

</script>
	
</body>
</html>