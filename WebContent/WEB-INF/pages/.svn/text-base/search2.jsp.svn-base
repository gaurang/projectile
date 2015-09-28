<%@include file="/WEB-INF/pages/include/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H.Riddhesh & Co.</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/mainstyle.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jquery-multiselect/themes/ui-lightness/jquery.ui.all.css" />
 
    <link rel="stylesheet" type="text/css" href="css/ui.dropdownchecklist.css" />
    
    <script type="text/javascript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-dropdown/ui.core-min.js"></script>
    <script type="text/javascript" src="js/jquery-dropdown/ui.dropdownchecklist-min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.corners.js"></script>
 	<script type="text/javascript" src="js/jquery/script.js"></script>
 	<script type="text/javascript" src="js/jquery/jquery.jacg.js"></script>
 	<script type="text/javascript" src="js/web.js"></script>
 
 	
	<script type="text/javascript">
	$(document).ready(function(){
			$("#sz").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 100 });  
		//	$("#<c:out value='${prpList.prp}_to'/>").dropdownchecklist({ firstItemChecksAll: true, maxDropHeight: 100 });  
	//		$('.acc-content').jacg({'radius': '1em', 'start': '#ffffff', 'end': '#2E6E9E'});

			//$('#shpAll').bind("click", chkAll('shpAll', 'sh'));
	});
	
	
	</script>
</head>
<body>


	<jsp:include page="include/header.jsp"></jsp:include>
<div id="content">

	<br/>
	<div class="searchBox" align="center">
	<form action="stockSearch.html" method="post" onsubmit="fnSubmit(this);" name="eForm">
		<div class="tableSubwrap">  
			<div class="inlineStrip">
			<br/>
			<br/>
			
			<label for="shpAll">Select All :</label>
			<br/>
			<br/>
				<input type="checkbox" name="shpAll" id="shpAll" value"-1" onclick="chkAll('shpAll', 'sh');"/>
			</div>
			<div class="inlineStrip">
				<img alt="Round" src="images/RO.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_10" value="10" checked="checked"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Pear" src="images/PR.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_20" value="20"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Emerald" src="images/EM.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_30" value="30"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Oval" src="images/OV.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_40" value="40"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Marquise" src="images/MQ.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_50" value="50"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Princess" src="images/PC.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_60" value="60"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Round" src="images/HT.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_70" value="70"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Asscher" src="images/AS.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_80" value="80"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Cushion" src="images/CU.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_90" value="90"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Cushion B" src="images/CB.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_100" value="100"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Radient" src="images/RD.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_110" value="110"/>
			 </div>
			 <div class="inlineStrip">
				<img alt="Sq. Radient" src="images/SR.gif"/>
				<br/>
				<input type="checkbox" name="sh" id="sh_120" value="120"/>
			 </div>
		</div>
		<br/><br/>
		
		<ul class="acc" id="acc">
		  <li>	
			<h3>Quick Search</h3> 
			<div class="acc-section">
			 <div class="acc-content">
				 <div class="inlineStrip">	
				<label class="prpLabel"> Size :</label>
				<select class="select" id="szQ" name="szQ">
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
				<select class="select" id="colQ" name="colQ">
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
						<c:forEach var="prpLov" items='${PRP_LOV["lab"]}'>
							<option value='${prpLov.id}'>${prpLov.description}</option>
						</c:forEach>
				</select>
				</div>
				<br/>
				<br/>
				<div>  
					<input type="button"  value="Search"  id="search" onclick="javascript:fnSubmit('QS');"/>
				</div>
			</div>
			</div>
		</li>
		<li>  
		<h3>Advance Search</h3>
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
							<option value='${s.id}'>${s.id} - ${s.description}</option>
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
				 </c:if>--%>
		 	</div>
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
				
				<tr>
					<c:choose>
						<c:when test="${prpList.dataType == 1}">
							<c:set value='${prpList.prp}' var="prp"></c:set>
							<td>${prpList.webDesc} </td>
							<td>
								<select name='${prpList.prp}_from' id='${prpList.prp}_from' ${selectCss}>
									<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
										<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
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
				<tr>
					<c:choose>
						<c:when test="${prpList.dataType == 1}">
							<c:set value='${prpList.prp}' var="prp"></c:set>
							<td>${prpList.webDesc} </td>
							<td>
								<select name='${prpList.prp}_from' id='${prpList.prp}_from' ${selectCss}>
									<option value="-1">---All---</option>
									<c:forEach var="prpLov" items='${PRP_LOV[prpList.prp]}'>
										<option value='${prpLov.id}' <c:if test="${SEARCH_PRP_MAP[prpList.prp].prpFromNum == prpLov.id}">selected</c:if>>${prpLov.description}</option>
									</c:forEach>
								</select>
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
			<td colspan="2">
			Search Ref. Stock No(comma separated) <input type="text" name="pktNo" id="pktNo" size="40" onblur="javascript:chkPktNum(this);">
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
	<jsp:include page="include/footer.jsp"></jsp:include>
<script type="text/javascript" src="js/jquery/script.js"></script>

<script type="text/javascript">

var parentAccordion=new TINY.accordion.slider("parentAccordion");
parentAccordion.init("acc","h3",0,1);

//var nestedAccordion=new TINY.accordion.slider("nestedAccordion");
//nestedAccordion.init("nested","h3",1,-1,"acc-selected");

</script>
	
</body>
</html>