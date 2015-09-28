<%@ include file="/WEB-INF/pages/include/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>H.Riddhesh &amp; Co. : </title>

<link href="css/dimond.css" rel="stylesheet" type="text/css"/>
<link href="css/mainstyle.css" rel="stylesheet" type="text/css" />

<script src="js/jquery/jquery.js" type="text/javascript"></script>
<script src="js/jquery/jquery.validate.js" type="text/javascript"></script>
   <%--<script src="js/jquery/jquery.maskedinput.js" type="text/javascript"></script>
  <script src="js/valid.js" type="text/javascript"></script> --%>
 <style type="text/css">

label { width: 10em; float: left; }
label.error { float: none; color: red; padding-left: .5em; vertical-align: top;display: list-item; }
p { clear: both; }
.submit { margin-left: 12em; }
em { font-weight: bold; padding-right: 1em; vertical-align: top; }


 </style>
 <script type="text/javascript">
 
  $(document).ready(function(){
	  $("#rform").validate();
  });

	function toggle(fld){
		if(fld.checked){
			$('#shiptbl').hide();
		}else{
			$('#shiptbl').show();
		}	
		shipAdd(fld.checked);
	  }

	function shipAdd(fld){

		 if(fld){
			 
			$("#shipName").val($("#cName").val());
			
			$("#shipDesignation").val($("#designation").val());
			$("#shipPinCode").val($("#pinCode").val());
			$("textarea[name='shipAddress']").val($("textarea[name='address']").val());
			$("#shipCity").val($("#city").val());
			$("#shipMobile").val($("#cMobile").val());

			$("#shipTelphone11").val($("#telphone11").val());
			$("#shipTelphone12").val($("#telphone12").val());
			$("#shipTelphone13").val($("#telphone13").val());
			
			$("#shipTelphone21").val($("#telphone21").val());
			$("#shipTelphone22").val($("#telphone22").val());
			$("#shipTelphone23").val($("#telphone23").val());
			
			$("#shipFax1").val($("#fax1").val());
			$("#shipFax1").val($("#fax1").val());
			$("#shipFax1").val($("#fax1").val());
		//	$("input[name='']").val($("input[name='Telphone2']").val());
		//	$("input[name='']").val($("input[name='fax']").val());


			$("#shipCountry").val($("#country").val());
			$("#shipState").val($("#state").val());
		}else{
		}
		
	}
		function updateTelephone(fldNM){
			$('#'+fldNM).val($('#'+fldNM+'1').val()+$('#'+fldNM+'2').val()+$('#'+fldNM+'3').val()) ;
		}	

		function checkUser(un){
			if(un.value.length  < 6  ){
				un.value = '';
				alert('Please enter minimum of 6 character for Username');
				return ;
			}
			$.getJSON('checkUserId.html', { user : un.value  }, function(json){
				if(json!=null && json !=""){
					if(json.toString().indexOf("ok") > -1 ){
						$('#userMsg').html('<label for=loginName>Available</label>');
					}else{
						un.value = '';
						$('#userMsg').html('<label class=error for=loginName>User name not available</label>');
					}
				}
			});
		}
</script>

</head>
<body >
<table width="900" align="center" border="0" cellpadding="0" 
cellspacing="0">
  <tbody><tr>
    <td valign="top" width="15"></td>
    <td><table align="center" border="0" cellpadding="0" 
cellspacing="0">
      <tbody><tr>
        <td ><table width="100%" border="0" 
cellpadding="0" cellspacing="2">
            <tbody><tr>
              <td ><table width="880" border="0" 
cellpadding="0" cellspacing="0">
                  <tbody><tr>
                    <td valign="bottom" 
 height="131">
	<c:if test="${registrationDO.ID > 0}">
		<div>
			<jsp:include page="include/header.jsp"></jsp:include>
		</div>	
	</c:if>
	<c:if test="${registrationDO.ID == null}">
		<div >
			<jsp:include page="../../header.jsp">
				<jsp:param value="1" name="jquery"/>
			</jsp:include>
		</div>
	</c:if>
</td>
                  </tr>
                  <tr>
                    <td >
    <!-- Main Content here -->
<div id="mainBody">
<form:form method="post" cssClass="cmxform" action="RegistrationController.html" commandName="registrationDO" name="rform" id="rform" >
 	<div class="error" style="display:none;">
      <img src="images/warning.gif" alt="Warning!" width="24" height="24" style="float:left; margin: -5px 10px 0px 0px; " />

      <span></span>.<br clear="all"/>
    </div>
	<c:if test="${registrationDO.ID > 0}">
		<form:input path="ID" cssStyle="display:none;"/>
	</c:if>
      <table width="85%" border="0" align="center" >
        <tr>
          <td colspan="2" class="heading">Company Details<span class="ttl_names">(<span class="astrix">*</span> Mandatory Fields)</span></td>
          <td ></td>
          <td align="left" class="ttl_names">&nbsp;</td>
        </tr>
        <tr>
          <td width="22%" class="ttl_names">Company Name<span class="astrix">*</span></td>
          <td width="28%">
           <form:input path="companyName" id="cname" cssClass="required input" size="25" maxlength="100"/>
          </td>
<td height="21"><span class="ttl_names">Member of any association</span></td>
          <td><form:input path="memberOfAssociation" cssClass="input" size="25" /></td>
         
        </tr>
        <tr>
          <td class="ttl_names">Your Broker (if any)</td>
          <td><form:input path="brokerName" cssClass="input" size="25"/></td>
          <td class="ttl_names">Telephone 1<span class="astrix">*</span></td>
          <td><div>
          <form:input cssClass="required number input" size="25" id="telphone1" path="telphone1"  />
          </div>
             </td>
        </tr>
        <tr>
          <td class="ttl_names">Business Type<span class="astrix">*</span></td>
          <td><label>
          <form:select path="businessType" cssClass="required list_box" id="select">
            <form:option value="">----Select Business Type----</form:option>
			<form:option value="Jewellery Manufacturer ">Jewellery Manufacturer </form:option>
			<form:option value="Jewellery Wholeseller">Jewellery Wholeseller</form:option>
			<form:option value="Jewellery Retailer">Jewellery Retailer</form:option>
			<form:option value="Jewellery Importer/Exporter">Jewellery Importer/Exporter</form:option>
			<form:option value="Diamond Manufacturer ">Diamond Manufacturer </form:option>
			<form:option value="Diamond Wholeseller">Diamond Wholeseller</form:option>
			<form:option value="Diamond Retailer">Diamond Retailer</form:option>
			<form:option value="Diamond Importer/Exporter">Diamond Importer/Exporter</form:option>
          </form:select>
          </label></td>
          <td class="ttl_names">Telephone 2</td>
          <td><div>
         <form:input path="telphone2" id="telphone23" cssClass="number input" size="25" /></div></td>
        </tr>
        <tr>
          <td class="ttl_names">Name<span class="astrix">*</span></td>
          <td><form:input path="cName" cssClass="required" size="25"/></td>
          <td class="ttl_names">Fax</td>
          <td>
          <form:input path="fax" id="fax3"  cssClass="number input" size="25" /></td>
        </tr>
        <tr>
          <td class="ttl_names">Designation<span class="astrix">*</span></td>
          <td><form:input path="designation" size="25" cssClass="required"/></td>
          <td class="ttl_names">Mobile<span class="astrix">*</span></td>
          <td>	<form:input path="cMobile" cssClass="required number"  size="25" /></td>
        </tr>
        <tr>
          <td rowspan="2" valign="top" class="ttl_names">Address<span class="astrix">*</span></td>
          <td rowspan="2" valign="top"><form:textarea path="address" cols="19" cssClass="required input_big" ></form:textarea></td>
          <td class="ttl_names">Email<span class="astrix">*</span></td>
          <td>	<form:input path="cEmail" cssClass="required email" size="25" /></td>
        </tr>
        <tr>
          <td class="ttl_names">Alternate Email </td>
          <td>
          	<form:input path="alternateEmail" cssClass="input" size="25" />
        </td>
        </tr>
        <tr>
          <td class="ttl_names">City<span class="astrix">*</span></td>
          <td>  <form:input path="city" cssClass="required input" size="25" /></td>
          <td class="ttl_names">Website </td>
          <td><form:input path="webSiteName"  cssClass="input" size="25" /></td>
        </tr>
        <tr>
          <td class="ttl_names">State<span class="astrix">*</span></td>
          <td><form:input path="state" size="25" cssClass="required input"/></td>
          <td class="ttl_names">Comments </td>
          <td rowspan="3" valign="top"><label>
            <form:textarea path="comments" cols="19" rows="3" cssClass="input_big"></form:textarea>
          </label></td>
        </tr>
        <tr>
          <td class="ttl_names">Country <span class="astrix">*</span></td>
          <td>
          
          <form:select path="country" cssClass="required list_box"  tabindex="9" id="ddlCcountry" >
				<option value="India" selected="selected">India</option>
				<option value="Afghanistan">Afghanistan</option>
				<option value="Albania">Albania</option>
				<option value="Algeria">Algeria</option>
				<option value="Andorra">Andorra</option>
				<option value="Anguilla">Anguilla</option>
				<option value="Antarctica">Antarctica</option>
				<option value="Armenia">Armenia</option>
				<option value="Aruba">Aruba</option>
				<option value="Australia">Australia</option>
				<option value="Austria">Austria</option>
				<option value="Azerbaijan">Azerbaijan</option>
				<option value="Bahamas">Bahamas</option>
				<option value="Bahrain">Bahrain</option>
				<option value="Bangladesh">Bangladesh</option>
				<option value="Barbados">Barbados</option>
				<option value="Belarus">Belarus</option>
				<option value="Belgium">Belgium</option>
				<option value="Belize">Belize</option>
				<option value="Benin">Benin</option>
				<option value="Bermuda">Bermuda</option>
				<option value="Bhutan">Bhutan</option>
				<option value="Bolivia">Bolivia</option>
				<option value="Bosnia">Bosnia</option>
				<option value="Bosnia">Bosnia</option>
				<option value="Botswana">Botswana</option>
				<option value="Bouvet">Bouvet</option>
				<option value="Brazil">Brazil</option>
				<option value="Bulgaria">Bulgaria</option>
				<option value="Burkina">Burkina</option>
				<option value="Burundi">Burundi</option>
				<option value="Cambodia">Cambodia</option>
				<option value="Cameroon">Cameroon</option>
				<option value="Canada">Canada</option>
				<option value="Cayman Islands">Cayman Islands</option>
				<option value="Chad">Chad</option>
				<option value="Chile">Chile</option>
				<option value="China">China</option>
				<option value="Christmas Island">Christmas Island</option>
				<option value="Colombia">Colombia</option>
				<option value="Comoros">Comoros</option>
				<option value="Congo">Congo</option>
				<option value="Costa Rica">Costa Rica</option>
				<option value="Croatia">Croatia</option>
				<option value="Cuba">Cuba</option>
				<option value="Cyprus">Cyprus</option>
				<option value="Denmark">Denmark</option>
				<option value="Djibouti">Djibouti</option>
				<option value="Dominica">Dominica</option>
				<option value="East Timor">East Timor</option>
				<option value="Ecuador">Ecuador</option>
				<option value="Egypt">Egypt</option>
				<option value="El Salvador">El Salvador</option>
				<option value="Equatorial Guinea">Equatorial Guinea</option>
				<option value="Eritrea">Eritrea</option>
				<option value="Estonia">Estonia</option>
				<option value="Ethiopia">Ethiopia</option>
				<option value="Falkland Islands">Falkland Islands</option>
				<option value="Faroe Islands">Faroe Islands</option>
				<option value="Finland">Finland</option>
				<option value="France">France</option>
				<option value="French Guiana">French Guiana</option>
				<option value="French Polynesia">French Polynesia</option>
				<option value="Gabon">Gabon</option>
				<option value="Gambia">Gambia</option>
				<option value="Georgia">Georgia</option>
				<option value="Germany">Germany</option>
				<option value="Ghana">Ghana</option>
				<option value="Gibraltar">Gibraltar</option>
				<option value="Greece">Greece</option>
				<option value="Grenada">Grenada</option>
				<option value="Guadaloupe">Guadaloupe</option>
				<option value="Guam">Guam</option>
				<option value="Guatemala">Guatemala</option>
				<option value="Guinea">Guinea</option>
				<option value="Guinea-Bissau">Guinea-Bissau</option>
				<option value="Guyana">Guyana</option>
				<option value="Haiti">Haiti</option>
				<option value="Honduras">Honduras</option>
				<option value="Hong Kong">Hong Kong</option>
				<option value="Hungary">Hungary</option>
				<option value="Iceland">Iceland</option>
				<option value="Indonesia">Indonesia</option>
                
				<option value="Iran">Iran</option>
				<option value="Iraq">Iraq</option>
				<option value="Ireland">Ireland</option>
				<option value="Israel">Israel</option>
				<option value="Italy">Italy</option>
				<option value="Jamaica">Jamaica</option>
				<option value="Japan">Japan</option>
				<option value="Jordan">Jordan</option>
				<option value="Kazakhstan">Kazakhstan</option>
				<option value="Kenya">Kenya</option>
				<option value="Kiribati">Kiribati</option>
				<option value="Korea">Korea</option>
				<option value="Kuwait">Kuwait</option>
				<option value="Kyrgyzstan">Kyrgyzstan</option>
				<option value="Laos">Laos</option>
				<option value="Latvia">Latvia</option>
				<option value="Lebanon">Lebanon</option>
				<option value="Lesotho">Lesotho</option>
				<option value="Liberia">Liberia</option>
				<option value="Libya">Libya</option>
				<option value="Liechtenstein">Liechtenstein</option>
				<option value="Lithuania">Lithuania</option>
				<option value="Luxembourg">Luxembourg</option>
				<option value="Macau">Macau</option>
				<option value="Macedonia">Macedonia</option>
				<option value="Madagascar">Madagascar</option>
				<option value="Malaysia">Malaysia</option>
				<option value="Maldives">Maldives</option>
				<option value="Mali">Mali</option>
				<option value="Malta">Malta</option>
				<option value="Marshall Islands">Marshall Islands</option>
				<option value="Martinique">Martinique</option>
				<option value="Mauritania">Mauritania</option>
				<option value="Mauritius">Mauritius</option>
				<option value="Mayotte">Mayotte</option>
				<option value="Mexico">Mexico</option>
				<option value="Micronesia">Micronesia</option>
				<option value="Moldova">Moldova</option>
				<option value="Mongolia">Mongolia</option>
				<option value="Montserrat">Montserrat</option>
				<option value="Morocco">Morocco</option>
				<option value="Mozambique">Mozambique</option>
				<option value="Myanmar">Myanmar</option>
				<option value="Namibia">Namibia</option>
				<option value="Nauru">Nauru</option>
				<option value="Nepal">Nepal</option>
				<option value="Netherlands">Netherlands</option>
				<option value="Netherlands Antilles">Netherlands Antilles</option>
				<option value="New Caledonia">New Caledonia</option>
				<option value="Nicaragua">Nicaragua</option>
				<option value="Nigeria">Nigeria</option>
				<option value="Norfolk Island">Norfolk Island</option>
				<option value="Norway">Norway</option>
				<option value="Oman">Oman</option>
				<option value="Pakistan">Pakistan</option>
				<option value="Palau">Palau</option>
				<option value="Panama">Panama</option>
				<option value="Paraguay">Paraguay</option>
				<option value="Philippines">Philippines</option>
				<option value="Pitcairn">Pitcairn</option>
				<option value="Poland">Poland</option>
				<option value="Portugal">Portugal</option>
				<option value="Puerto Rico">Puerto Rico</option>
				<option value="Qatar">Qatar</option>
				<option value="Romania">Romania</option>
				<option value="Russian Federation">Russian Federation</option>
				<option value="Rwanda">Rwanda</option>
				<option value="Saint Helena">Saint Helena</option>
				<option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
				<option value="Saint Lucia">Saint Lucia</option>
				<option value="San Marino">San Marino</option>
				<option value="Sao Tom">Sao Tom</option>
				<option value="Saudi Arabia">Saudi Arabia</option>
				<option value="Senegal">Senegal</option>
				<option value="Seychelles">Seychelles</option>
				<option value="Sierra Leone">Sierra Leone</option>
				<option value="Senegal">Senegal</option>
				<option value="Seychelles">Seychelles</option>
				<option value="Sierra Leone">Sierra Leone</option>
				<option value="Singapore">Singapore</option>
				<option value="Slovakia">Slovakia</option>
				<option value="Slovenia">Slovenia</option>
				<option value="Solomon Islands">Solomon Islands</option>
				<option value="Somalia">Somalia</option>
				<option value="South Africa">South Africa</option>
				<option value="Spain">Spain</option>
				<option value="Sri Lanka">Sri Lanka</option>
				<option value="Sudan">Sudan</option>
				<option value="Suriname">Suriname</option>
				<option value="Swaziland">Swaziland</option>
				<option value="Sweden">Sweden</option>
				<option value="Switzerland">Switzerland</option>
				<option value="Syria">Syria</option>
				<option value="Taiwan">Taiwan</option>
				<option value="Tajikistan">Tajikistan</option>
				<option value="Tanzania">Tanzania</option>
				<option value="Thailand">Thailand</option>
				<option value="Togo">Togo</option>
				<option value="Tokelau">Tokelau</option>
				<option value="Tonga">Tonga</option>
				<option value="Trinidad and Tobago">Trinidad and Tobago</option>
				<option value="Tunisia">Tunisia</option>
				<option value="Turkey">Turkey</option>
				<option value="Turkmenistan">Turkmenistan</option>
				<option value="Tuvalu">Tuvalu</option>
				<option value="Uganda">Uganda</option>
				<option value="Ukraine">Ukraine</option>
				<option value="United Arab Emirates">United Arab Emirates</option>
				<option value="United Kingdom">United Kingdom</option>
				<option value="United States">United States</option>
				<option value="Uruguay">Uruguay</option>
				<option value="Uzbekistan">Uzbekistan</option>
				<option value="Vanuatu">Vanuatu</option>
				<option value="Venezuela">Venezuela</option>
				<option value="Virgin Islands">Virgin Islands</option>
				<option value="Western Sahara ">Western Sahara </option>
				<option value="Yemen">Yemen</option>
				<option value="Yugoslavia">Yugoslavia</option>
				<option value="Zambia">Zambia</option>
				<option value="Zimbabwe">Zimbabwe</option>

			</form:select>
          
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td width="22%" class="ttl_names">Pin Code<span class="astrix">*</span></td>
          <td width="28%"><form:input path="pinCode" cssClass="required input" size="25"/></td>
          
          <td>&nbsp;</td>
        </tr>
         <!--	 <tr>
          <td height="21" colspan="4" class="line">.........................................................................................................................................................................................</td>
        </tr>
      <tr>
          <td colspan="3" valign="top" class="heading">Shipping Details<span class="ttl_names">( please tick if same as above )</span>   
          <input type="checkbox" name="shipAdd" id="shipAdd" value="1" onclick="toggle(this);"/></td>
          <td>&nbsp;</td>
        </tr>
        
        --></table>
        <!--
        
        <table width="100%" border="0" align="center" id="shiptbl"> 
        <tr>
          <td width="28%" class="ttl_names">Name<span class="astrix">*</span></td>
          <td width="24%"><label>
           <form:input path="shipName" cssClass="required input" size="25"/>
          </label></td>
          <td width="22%" class="ttl_names">Pin Code<span class="astrix">*</span></td>
          <td width="26%"><form:input path="shipPinCode" cssClass="required input" size="25"/></td>
        </tr>
        <tr>
         <td class="ttl_names">Designation<span class="astrix">*</span></td>
          <td><form:input path="shipDesignation" size="25" cssClass="required input"/></td>
          <td class="ttl_names">Telephone 1<span class="astrix">*</span></td>
          <td>
          <input class="number" size="2" id="shipTelphone11" name="shipTelphone11" onblur="updateTelephone('shipTelphone1');"/>
          <input class="number" size="2" id="shipTelphone12" name="shipTelphone12" onblur="updateTelephone('shipTelphone1');"/>
          <input class="required number" size="12" id="shipTelphone13" name="shipTelphone3" onblur="updateTelephone('shipTelphone1');"/></td>
        <form:hidden path="shipTelphone1" />	
        </tr>
        <tr>
          <td rowspan="2" valign="top" class="ttl_names">Address<span class="astrix">*</span></td>
          <td rowspan="2" valign="top"><form:textarea path="shipAddress" cols="19" cssClass="input_big" ></form:textarea></td>
          
          <td class="ttl_names">Telephone 2</td>
          <td><form:input path="shipTelphone2" id="shipTelphone21" cssClass="number" size="2" onblur="updateTelephone('shipTelphone2');"/>
           <form:input path="shipTelphone2" id="shipTelphone22" cssClass="number" size="2" onblur="updateTelephone('shipTelphone2');"/>
        	<form:input path="shipTelphone2" id="shipTelphone23" cssClass="number" size="12" onblur="updateTelephone('shipTelphone2');"/></td>
        </tr>
        <tr>
        
          <td class="ttl_names">Fax</td>
          <td><form:input path="shipFax" id="shipFax1"  cssClass="number"  size="2" onblur="updateTelephone('shipFax');"/>
            <form:input path="shipFax" id="shipFax2"  cssClass="number"  size="2" onblur="updateTelephone('shipFax');"/>
          <form:input path="shipFax" id="shipFax3"  cssClass="number" size="12" onblur="updateTelephone('shipFax');"/></td>
        </tr>
        <tr>
          <td class="ttl_names">City<span class="astrix">*</span></td>
          <td>  <form:input path="shipCity" cssClass="required " size="25" /></td>
          <td class="ttl_names">Mobile </td>
          <td><form:input path="shipMobile" cssClass="" size="25" /></td>
        </tr>
        <tr>
          <td rowspan="2" valign="top" class="ttl_names">State<span class="astrix">*</span></td>
          <td rowspan="2" valign="top"><form:textarea path="shipCity" cols="19" cssClass="required input_big" ></form:textarea></td>
          <td class="ttl_names" valign="top">Country<span class="astrix">*</span></td>
          <td valign="top">
			<form:select path="shipCountry" cssStyle="width: 176px;" tabindex="9" id="shipcountry" cssClass="required">
				<option value="India" selected="selected">India</option>
				
				<option value="Afghanistan">Afghanistan</option>
				<option value="Albania">Albania</option>
				<option value="Algeria">Algeria</option>
				<option value="Andorra">Andorra</option>
				<option value="Anguilla">Anguilla</option>
				<option value="Antarctica">Antarctica</option>
				<option value="Armenia">Armenia</option>
				<option value="Aruba">Aruba</option>
				<option value="Australia">Australia</option>
				<option value="Austria">Austria</option>
				<option value="Azerbaijan">Azerbaijan</option>
				<option value="Bahamas">Bahamas</option>
				<option value="Bahrain">Bahrain</option>
				<option value="Bangladesh">Bangladesh</option>
				<option value="Barbados">Barbados</option>
				<option value="Belarus">Belarus</option>
				<option value="Belgium">Belgium</option>
				<option value="Belize">Belize</option>
				<option value="Benin">Benin</option>
				<option value="Bermuda">Bermuda</option>
				<option value="Bhutan">Bhutan</option>
				<option value="Bolivia">Bolivia</option>
				<option value="Bosnia">Bosnia</option>
				<option value="Bosnia">Bosnia</option>
				<option value="Botswana">Botswana</option>
				<option value="Bouvet">Bouvet</option>
				<option value="Brazil">Brazil</option>
				<option value="Bulgaria">Bulgaria</option>
				<option value="Burkina">Burkina</option>
				<option value="Burundi">Burundi</option>
				<option value="Cambodia">Cambodia</option>
				<option value="Cameroon">Cameroon</option>
				<option value="Canada">Canada</option>
				<option value="Cayman Islands">Cayman Islands</option>
				<option value="Chad">Chad</option>
				<option value="Chile">Chile</option>
				<option value="China">China</option>
				<option value="Christmas Island">Christmas Island</option>
				<option value="Colombia">Colombia</option>
				<option value="Comoros">Comoros</option>
				<option value="Congo">Congo</option>
				<option value="Costa Rica">Costa Rica</option>
				<option value="Croatia">Croatia</option>
				<option value="Cuba">Cuba</option>
				<option value="Cyprus">Cyprus</option>
				<option value="Denmark">Denmark</option>
				<option value="Djibouti">Djibouti</option>
				<option value="Dominica">Dominica</option>
				<option value="East Timor">East Timor</option>
				<option value="Ecuador">Ecuador</option>
				<option value="Egypt">Egypt</option>
				<option value="El Salvador">El Salvador</option>
				<option value="Equatorial Guinea">Equatorial Guinea</option>
				<option value="Eritrea">Eritrea</option>
				<option value="Estonia">Estonia</option>
				<option value="Ethiopia">Ethiopia</option>
				<option value="Falkland Islands">Falkland Islands</option>
				<option value="Faroe Islands">Faroe Islands</option>
				<option value="Finland">Finland</option>
				<option value="France">France</option>
				<option value="French Guiana">French Guiana</option>
				<option value="French Polynesia">French Polynesia</option>
				<option value="Gabon">Gabon</option>
				<option value="Gambia">Gambia</option>
				<option value="Georgia">Georgia</option>
				<option value="Germany">Germany</option>
				<option value="Ghana">Ghana</option>
				<option value="Gibraltar">Gibraltar</option>
				<option value="Greece">Greece</option>
				<option value="Grenada">Grenada</option>
				<option value="Guadaloupe">Guadaloupe</option>
				<option value="Guam">Guam</option>
				<option value="Guatemala">Guatemala</option>
				<option value="Guinea">Guinea</option>
				<option value="Guinea-Bissau">Guinea-Bissau</option>
				<option value="Guyana">Guyana</option>
				<option value="Haiti">Haiti</option>
				<option value="Honduras">Honduras</option>
				<option value="Hong Kong">Hong Kong</option>
				<option value="Hungary">Hungary</option>
				<option value="Iceland">Iceland</option>
				<option value="Indonesia">Indonesia</option>
                                <option value="India">India</option>
				<option value="Iran">Iran</option>
				<option value="Iraq">Iraq</option>
				<option value="Ireland">Ireland</option>
				<option value="Israel">Israel</option>
				<option value="Italy">Italy</option>
				<option value="Jamaica">Jamaica</option>
				<option value="Japan">Japan</option>
				<option value="Jordan">Jordan</option>
				<option value="Kazakhstan">Kazakhstan</option>
				<option value="Kenya">Kenya</option>
				<option value="Kiribati">Kiribati</option>
				<option value="Korea">Korea</option>
				<option value="Kuwait">Kuwait</option>
				<option value="Kyrgyzstan">Kyrgyzstan</option>
				<option value="Laos">Laos</option>
				<option value="Latvia">Latvia</option>
				<option value="Lebanon">Lebanon</option>
				<option value="Lesotho">Lesotho</option>
				<option value="Liberia">Liberia</option>
				<option value="Libya">Libya</option>
				<option value="Liechtenstein">Liechtenstein</option>
				<option value="Lithuania">Lithuania</option>
				<option value="Luxembourg">Luxembourg</option>
				<option value="Macau">Macau</option>
				<option value="Macedonia">Macedonia</option>
				<option value="Madagascar">Madagascar</option>
				<option value="Malaysia">Malaysia</option>
				<option value="Maldives">Maldives</option>
				<option value="Mali">Mali</option>
				<option value="Malta">Malta</option>
				<option value="Marshall Islands">Marshall Islands</option>
				<option value="Martinique">Martinique</option>
				<option value="Mauritania">Mauritania</option>
				<option value="Mauritius">Mauritius</option>
				<option value="Mayotte">Mayotte</option>
				<option value="Mexico">Mexico</option>
				<option value="Micronesia">Micronesia</option>
				<option value="Moldova">Moldova</option>
				<option value="Mongolia">Mongolia</option>
				<option value="Montserrat">Montserrat</option>
				<option value="Morocco">Morocco</option>
				<option value="Mozambique">Mozambique</option>
				<option value="Myanmar">Myanmar</option>
				<option value="Namibia">Namibia</option>
				<option value="Nauru">Nauru</option>
				<option value="Nepal">Nepal</option>
				<option value="Netherlands">Netherlands</option>
				<option value="Netherlands Antilles">Netherlands Antilles</option>
				<option value="New Caledonia">New Caledonia</option>
				<option value="Nicaragua">Nicaragua</option>
				<option value="Nigeria">Nigeria</option>
				<option value="Norfolk Island">Norfolk Island</option>
				<option value="Norway">Norway</option>
				<option value="Oman">Oman</option>
				<option value="Pakistan">Pakistan</option>
				<option value="Palau">Palau</option>
				<option value="Panama">Panama</option>
				<option value="Paraguay">Paraguay</option>
				<option value="Philippines">Philippines</option>
				<option value="Pitcairn">Pitcairn</option>
				<option value="Poland">Poland</option>
				<option value="Portugal">Portugal</option>
				<option value="Puerto Rico">Puerto Rico</option>
				<option value="Qatar">Qatar</option>
				<option value="Romania">Romania</option>
				<option value="Russian Federation">Russian Federation</option>
				<option value="Rwanda">Rwanda</option>
				<option value="Saint Helena">Saint Helena</option>
				<option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
				<option value="Saint Lucia">Saint Lucia</option>
				<option value="San Marino">San Marino</option>
				<option value="Sao Tom">Sao Tom</option>
				<option value="Saudi Arabia">Saudi Arabia</option>
				<option value="Senegal">Senegal</option>
				<option value="Seychelles">Seychelles</option>
				<option value="Sierra Leone">Sierra Leone</option>
				<option value="Senegal">Senegal</option>
				<option value="Seychelles">Seychelles</option>
				<option value="Sierra Leone">Sierra Leone</option>
				<option value="Singapore">Singapore</option>
				<option value="Slovakia">Slovakia</option>
				<option value="Slovenia">Slovenia</option>
				<option value="Solomon Islands">Solomon Islands</option>
				<option value="Somalia">Somalia</option>
				<option value="South Africa">South Africa</option>
				<option value="Spain">Spain</option>
				<option value="Sri Lanka">Sri Lanka</option>
				<option value="Sudan">Sudan</option>
				<option value="Suriname">Suriname</option>
				<option value="Swaziland">Swaziland</option>
				<option value="Sweden">Sweden</option>
				<option value="Switzerland">Switzerland</option>
				<option value="Syria">Syria</option>
				<option value="Taiwan">Taiwan</option>
				<option value="Tajikistan">Tajikistan</option>
				<option value="Tanzania">Tanzania</option>
				<option value="Thailand">Thailand</option>
				<option value="Togo">Togo</option>
				<option value="Tokelau">Tokelau</option>
				<option value="Tonga">Tonga</option>
				<option value="Trinidad and Tobago">Trinidad and Tobago</option>
				<option value="Tunisia">Tunisia</option>
				<option value="Turkey">Turkey</option>
				<option value="Turkmenistan">Turkmenistan</option>
				<option value="Tuvalu">Tuvalu</option>
				<option value="Uganda">Uganda</option>
				<option value="Ukraine">Ukraine</option>
				<option value="United Arab Emirates">United Arab Emirates</option>
				<option value="United Kingdom">United Kingdom</option>
				<option value="United States">United States</option>
				<option value="Uruguay">Uruguay</option>
				<option value="Uzbekistan">Uzbekistan</option>
				<option value="Vanuatu">Vanuatu</option>
				<option value="Venezuela">Venezuela</option>
				<option value="Virgin Islands">Virgin Islands</option>
				<option value="Western Sahara ">Western Sahara </option>
				<option value="Yemen">Yemen</option>
				<option value="Yugoslavia">Yugoslavia</option>
				<option value="Zambia">Zambia</option>
				<option value="Zimbabwe">Zimbabwe</option>

			</form:select>

		</td>
        </tr>
        
      </table>
    -->
    <!--<div id="ref_detail">
      <table width="100%" border="0" align="center">
        <tr>
          <td colspan="4" class="heading">Trade References </td>
        </tr>
        <tr>
          <td width="22%" class="sub_heading">Reference 1 <span class="astrix"></span></td>
          <td width="28%">&nbsp;</td>
          <td width="22%" class="sub_heading">Reference 2          </td>
          <td width="28%">&nbsp;</td>
        </tr>
        <tr>
          <td class="ttl_names">Company Name<span class="astrix"></span></td>
          <td><form:input path="tradeCompanyName" cssClass="" size="25" /></td>
          <td class="ttl_names">Company Name</td>
          <td><form:input path="tradeCompanyName1" cssClass="" size="25" /></td>
        </tr>
        <tr>
          <td class="ttl_names">Contact Person<span class="astrix"></span></td>
          <td>	<form:input path="contactPerson" cssClass=""  size="25" /></td>
          <td class="ttl_names">Contact Person</td>
          <td><form:input path="contactPerson1" cssClass=""  size="25" /></td>
        </tr>
        <tr>
          <td class="ttl_names">Phone No.</td>
          <td valign="top"><form:input path="phoneNo1" id="phoneNo11" cssClass="input"  size="2" />
            <form:input path="phoneNo1" id="phoneNo12" cssClass="number"  size="2" />
           <form:input path="phoneNo1" id="phoneNo13" cssClass="number"  size="12" /></td>
          <td class="ttl_names">Phone No.</td>
          <td><form:input path="phoneNo2" id="phoneNo21" cssClass="number"  size="2" />
            <form:input path="phoneNo2" id="phoneNo22" cssClass="number"  size="2" />
           <form:input path="phoneNo2" id="phoneNo23" cssClass="number"  size="12" /></td>
        </tr>
        <tr>
          <td valign="top" class="ttl_names">Mobile </td>
          <td valign="top"><form:input path="trMobile1" cssClass="number" size="25" /></td>
          <td class="ttl_names">Mobile</td>
          <td><form:input path="trMobile2" cssClass="number" size="25" /></td>
        </tr>
        <tr>
          <td height="21" colspan="4" class="line">&nbsp;</td>
        </tr>
      </table>
    </div>
    --><div id="login_detail">
      <table width="85%" border="0" align="center">
    	<c:if test="${registrationDO.ID == null}">
        <tr>
          <td colspan="4" class="heading">Login Details </td>
        </tr>
        <tr>
          <td width="22%" class="ttl_names">Username<span class="astrix"><em>*</em></span></td>
          <td width="28%" id="userTag"><form:input path="loginName" cssClass="required" size="25"  onblur="checkUser(this);" /> <span id="userMsg"></span></td>
          <td width="22%" class="ttl_names">Confirm Password<span class="astrix">*</span></td>
          <td width="28%"><form:password path="confirmPassword" cssClass="required" size="25" /></td>
        </tr>
        <tr>
          <td class="ttl_names">Password <span class="astrix">*</span></td>
          <td><form:password path="password" cssClass="required input" size="25" /></td>
          <td class="ttl_names">Send me Email Updates</td>
          <td valign="middle" class="ttl_names">
            <form:radiobutton path="emailUpdates" value="1" cssStyle="width:auto!important;"/>
            Yes 
            <form:radiobutton path="emailUpdates" value="0" cssStyle="width:auto!important;"/>
            No</td>
        </tr>
       <c:if test="${registrationDO.ID > 0}">
        <tr>
          <td class="ttl_names">Sequrity Question <span class="astrix">*</span></td>
          <td><form:select path="question" cssClass="required list_box" >
          			<form:option value="" >--select--</form:option>
          			<form:option value="1" >What was your childhood nickname ? </form:option>
          			<form:option value="2" >What is your spouse's maiden name ?</form:option>
          			<form:option value="3" >What is your pet's name ?</form:option>
          			<form:option value="4" >Who was your childhood hero ?</form:option>
          	  </form:select>
          </td>
          <td class="ttl_names">Answer</td>
          <td >
            <form:input path="answer" cssClass="required input" size="25" />
           </td>
        </tr>
        </c:if>
        <tr>
          <td class="ttl_names">&nbsp;</td>
          <td>&nbsp;</td>
          <td class="ttl_names">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        </c:if>
        <tr>
          <td height="21" class="line">&nbsp;</td>
          <td height="21" align="right"><table width="70" border="0">
            <tr>
              <td width="97" height="27" class="button" ><input type="submit" value="Submit"/></td>
            </tr>
          </table></td>
          <td height="21"><table width="70" border="0">
            <tr>
              <td width="97" height="28" class="button"><input type="button" value="Reset" onclick="this.form.reset();"/></td>
            </tr>
          </table></td>
          <td height="21" class="line">&nbsp;
          
          </td>
        </tr>
      </table>
  </div>
</form:form>
<!-- Data ends -->
    <br/>
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
    <td valign="top" width="15" ></td>
  </tr>
</tbody></table>
<map name="Map2" id="Map2"><area shape="rect" coords="710,209,893,246" 
href="index.html"/><area 
shape="rect" coords="710,209,893,246" 
href="index.html"/>
</map>
<input id="gwProxy" type="hidden"/><!--Session data--><input 
onclick="jsCall();" id="jsProxy" type="hidden"/><div id="refHTML"></div>

</body>
</html>








