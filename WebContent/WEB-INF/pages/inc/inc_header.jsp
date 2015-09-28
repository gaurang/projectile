<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/include/include.jsp"%>
<!--[if lt IE 7]>
		<link rel="stylesheet" type="text/css" href="menu/includes/ie6.css" media="screen"/>
	<![endif]-->
<script type="text/javascript">
var id='';
var occlk='';
$(document).ready(function(){
	$("form").each(function(){
		occlk =  $(this).attr('onsubmit');
		$(this).attr('onsubmit','');
		if(occlk == undefined){
            return false;
		}
      });
	$('#ajaxMessage').html('');
	$('#ajaxMessage').hide();
	$('form').submit(function(e) {
		e.preventDefault();
        var $form = $(this);
        if(!$form.valid()) return false;
		id= $(this).attr('id');
		var occlk =  $('#'+id+' input[type="submit"]').attr('onClick');
		if(occlk || (occlk != undefined))
			return false;
	    var options = {
	    	dataType: 'html',
	    	beforeSubmit:  showRequest,
	        success: function(response) {
	        	$tab = $('#tt').tabs('getSelected');
	        	$tab.html(response);
	        	$('#ajaxMessage').show();
	        	$('.ajaxMsg').html('<p>Process completed successfully.</p>');//Changed to use CSS effectively TODO
	        },
	    	iframe: false,
	        error: function(){
	        	$('#ajaxMessage').html('<p>Error in submission, please try again.</p>');
	        	$('#ajaxMessage').show();
	        },
	        url: $(this).attr('action')
	    };
		    $(this).ajaxSubmit(options); 
		    return false;
		}); 
	
});
function showRequest(formData, jqForm, options) {
	/* var occlk =  $('#'+id+'').attr('onsubmit');
	//this line WHY DOING HERE i said do it in on load doc.ready  use logic properly 
	
	// not good way of writing code first check if it works as it is
	if(eval(occlk.substr(7))==true){
		return true;
	}else{
		return false;
	} */
	if((occlk != undefined)){
		if(eval(occlk.substr(7))==true){
	        return true;
	    }else{
	        return false;
	    }
	}
	/* if(validate()){
		return true;
	}else{
	    return false;
	} */
}
function getHelp(){
	<c:if test="${param.subPage == ''}">
		<c:set var="subpage" value="common" scope="page" />
	</c:if>
	<c:if test="${param.subPage != ''}">
		<c:set var="subpage" value="${param.subPage}" scope="page" />
	</c:if>
	var html = '<spring:message code="projectile.${param.page}.${subpage}"></spring:message>';
	$('#helpText').html(html);
		$("#helpText").dialog();
		$('#helpText').show();
}
</script>
<div>
	<div id="mainMenu">
	<span class="preload1"></span>
    <span class="preload2"></span>
    <div id="head_nav">
	<div id="tabs">
	          <c:if test="${USER_SESSION.userUpperActivityMap['1_sales'] > 0 }">
				<div class="<c:if test="${param.page == 'sales' }">btn_active</c:if><c:if test="${param.page != 'sales' }">btn_up</c:if>">
                    <a href="javascript:void(0);" onclick="func('stock.html');">Sales</a>
                </div>
			  </c:if>
			  <c:if test="${USER_SESSION.userUpperActivityMap['2_Purchase'] > 0 }">	
				<div class="<c:if test="${param.page == 'purchase' }">btn_active</c:if><c:if test="${param.page != 'purchase' }">btn_up</c:if>">
				     <a href="javascript:void(0);" onclick="func('stockEntry.html');">Purchase</a>
			    </div>
			  </c:if>
			  <c:if test="${USER_SESSION.userUpperActivityMap['3_Parties'] > 0 }">
				<div class="<c:if test="${param.page == 'party' }">btn_active</c:if><c:if test="${param.page != 'party' }">btn_up</c:if>">
					<a href="javascript:void(0);" onclick="func('partyList.html');">Parties</a>
				</div>
			  </c:if>
			  <c:if test="${USER_SESSION.userUpperActivityMap['4_Utility'] > 0 }">	
				<div class="<c:if test="${param.page == 'utility' }">btn_active</c:if><c:if test="${param.page != 'utility' }">btn_up</c:if>">
					<a href="javascript:void(0);" onclick="func('termList.html');">Utility</a>
				</div>
			  </c:if>	
			  <c:if test="${USER_SESSION.userUpperActivityMap['5_Account'] >0 }">
				<div class="<c:if test="${param.page == 'account' }">btn_active</c:if><c:if test="${param.page != 'account' }">btn_up</c:if>">
					<a href="javascript:void(0);" onclick="func('deposit.html');">Account</a>
				</div>
			  </c:if>
				<div class="btn_up">
					<a href="#" onclick="getHelp()">Help</a>
				</div>
				<div class="btn_up" onclick="window.location='logOutCrm.html';">Logout</div>
			</div>
			<div class="memberDetails">${USER_SESSION.userName}</div>
			<div id="logo">&nbsp;&nbsp; V 1.1.3
				Beta&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</div>
	</div>
	<div id="sub_nav">
		<div id="subTabs">
			<c:if test="${param.page == 'party' }">
				<c:if test="${USER_SESSION.userActivityMap['PartyList'] == 1 }">
					<div class="<c:if test="${param.subPage == 'party' }">sub_active</c:if><c:if test="${param.subPage != 'party' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('partyList.html');">Party List</a>
					</div>
			    </c:if>
			    <c:if test="${USER_SESSION.userActivityMap['AddNew_Party'] == 1 }">
					<div class="<c:if test="${param.subPage == 'addParty' }">sub_active</c:if><c:if test="${param.subPage != 'addParty' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('partyActionController.html');">Add New Party</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Web_Users'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'webUser' }">sub_active</c:if><c:if test="${param.subPage != 'webUser' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('webUsers.html');">Web Users</a>
					</div>
				</c:if>
			</c:if>
			<c:if test="${param.page == 'purchase' }">
				<c:if test="${USER_SESSION.userActivityMap['Upload_Edit(Big)'] == 1 }">
					<div class="<c:if test="${param.subPage == 'purchase' }">sub_active</c:if><c:if test="${param.subPage != 'purchase' }">sub_up</c:if>">
						<a href="javascript:void(0);" accesskey="U" onclick="func('stockEntry.html');"><u>U</u>pload/Edit
							(Big)</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Pending_Stock'] == 1 }">    
					<div class="<c:if test="${param.subPage == 'pending' }">sub_active</c:if><c:if test="${param.subPage != 'pending' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('pendingStock.html');">Pending Stock</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Invalid_Stock'] == 1 }">
					<div class="<c:if test="${param.subPage == 'invalid' }">sub_active</c:if><c:if test="${param.subPage != 'invalid' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('invalidStock.html');">Invalid Stock</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Available'] == 1 }">     
					<div class="<c:if test="${param.subPage == 'available' }">sub_active</c:if><c:if test="${param.subPage != 'available' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('available.html');">Available</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Mix_Group(Small)'] == 1 }">		    	
					<div class="<c:if test="${param.subPage == 'mixParcel' }">sub_active</c:if><c:if test="${param.subPage != 'mixParcel' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('parcelList.html');">Mix Groups(Small)</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Cost_Price'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'costPrice' }">sub_active</c:if><c:if test="${param.subPage != 'costPrice' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('costPrice.html');">Cost Price</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Small_Upload'] == 1 }">		 
					<div class="<c:if test="${param.subPage == 'stockEntryMix' }">sub_active</c:if><c:if test="${param.subPage != 'stockEntryMix' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('parcelUpload.html');">Small upload</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Small_Merge'] == 1 }">
					<div class="<c:if test="${param.subPage == 'merge' }">sub_active</c:if><c:if test="${param.subPage != 'merge' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('mergeMix.html');">Small Merge</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Small_Split'] == 1 }">
					<div class="<c:if test="${param.subPage == 'split' }">sub_active</c:if><c:if test="${param.subPage != 'split' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('split.html');">Small Split</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Lab'] == 1 }">		 
					<div class="<c:if test="${param.subPage == 'lab' }">sub_active</c:if><c:if test="${param.subPage != 'lab' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('labForm.html');">Lab</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['certUpload'] == 1 }">		
					 <div class="<c:if test="${param.subPage == 'certUpload' }">sub_active</c:if><c:if test="${param.subPage != 'certUpload' }">sub_up</c:if>">
					 	<a href="javascript:void(0);" onclick="func('certUpload.html');">Cert Upload</a>
		        	 </div>
		        </c:if>
			</c:if>
			<c:if test="${param.page == 'sales' }">
				<c:if test="${USER_SESSION.userActivityMap['memo'] == 1 }">
					<div class="<c:if test="${param.subPage == 'memo' }">sub_active</c:if><c:if test="${param.subPage != 'memo' }">sub_up</c:if>">
						<a href="javascript:void(0);" accesskey="M" onclick="func('stock.html');"><u>M</u>emo</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['sale_mix'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'mix' }">sub_active</c:if><c:if test="${param.subPage != 'mix' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('stockMix.html');">Sale Mix</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['memo_list'] == 1 }">
					<div class="<c:if test="${param.subPage == 'memoReport' }">sub_active</c:if><c:if test="${param.subPage != 'memoReport' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('memoReport.html');">Memo List</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['web_memo'] == 1 }">
					<div class="<c:if test="${param.subPage == 'webMemo' }">sub_active</c:if><c:if test="${param.subPage != 'webMemo' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('memoReport.html?web=1');">Web Memo</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['bar_code'] == 1 }">
					<div class="<c:if test="${param.subPage == 'barcode' }">sub_active</c:if><c:if test="${param.subPage != 'barcode' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('pktBarcod.html');">Barcode</a>
					</div>
				</c:if>
			</c:if>
			<c:if test="${param.page == 'utility' }">
				<c:if test="${USER_SESSION.userActivityMap['Terms'] == 1 }">
					<div class="<c:if test="${param.subPage == 'terms' }">sub_active</c:if><c:if test="${param.subPage != 'terms' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('termList.html');">Terms</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['File_Mappings'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'mapping' }">sub_active</c:if><c:if test="${param.subPage != 'mapping' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('exportFilesList.html');">File mappings</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['AddFile_Mappings'] == 1 }">
					<div class="<c:if test="${param.subPage == 'addmap' }">sub_active</c:if><c:if test="${param.subPage != 'addmap' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('exportFileMap.html');">Add File mappings</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['ChangePassword'] == 1 }">
					<div class="<c:if test="${param.subPage == 'pwd' }">sub_active</c:if><c:if test="${param.subPage != 'pwd' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('changeCRMPwd.html');">Change Password</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['UserManager'] == 1 }">
					<div class="<c:if test="${param.subPage == 'user' }">sub_active</c:if><c:if test="${param.subPage != 'user' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('userManager.html');">User Manager</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['RoleManager'] == 1 }">		
					<div class="<c:if test="${param.subPage == 'role' }">sub_active</c:if><c:if test="${param.subPage != 'role' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('roleManager.html');">Role Manager</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Currencies'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'curr' }">sub_active</c:if><c:if test="${param.subPage != 'curr' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('currency.html');">Currencies</a>
					</div>
				</c:if>
					<div class="<c:if test="${param.subPage == 'rapUserCreation' }">sub_active</c:if><c:if test="${param.subPage != 'rapUserCreation' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('rapNetCreation.html');">RapNet Creation</a>
					</div>
			</c:if>
			<c:if test="${param.page == 'account' }">
				<c:if test="${USER_SESSION.userActivityMap['Receivable'] == 1 }">
					<div class="<c:if test="${param.subPage == 'deposit' }">sub_active</c:if><c:if test="${param.subPage != 'deposit' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('deposit.html');">Receivable</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Payable'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'pay' }">sub_active</c:if><c:if test="${param.subPage != 'pay' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('pay.html');">Payable</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Journal_Entry'] == 1 }">
					<div class="<c:if test="${param.subPage == 'journal' }">sub_active</c:if><c:if test="${param.subPage != 'journal' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('journal.html');">Journal Entry</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['BankTransfer'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'bankTrf' }">sub_active</c:if><c:if test="${param.subPage != 'bankTrf' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('bankTrf.html');">Bank Transfer</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['BankAccount'] == 1 }">
					<div class="<c:if test="${param.subPage == 'bank' }">sub_active</c:if><c:if test="${param.subPage != 'bank' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('bankAcc.html');">Bank Account</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['GlAccount'] == 1 }">
					<div class="<c:if test="${param.subPage == 'glacc' }">sub_active</c:if><c:if test="${param.subPage != 'glacc' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('glAcc.html');">GL account</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['GlGroup'] == 1 }">
					<div class="<c:if test="${param.subPage == 'glgrp' }">sub_active</c:if><c:if test="${param.subPage != 'glgrp' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('glAccGrp.html');">GL Group</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['GlTypes'] == 1 }">
					<div class="<c:if test="${param.subPage == 'gltyp' }">sub_active</c:if><c:if test="${param.subPage != 'gltyp' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('glAccType.html');">GL Types</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userActivityMap['Angadia'] == 1 }">	
					<div class="<c:if test="${param.subPage == 'angadia' }">sub_active</c:if><c:if test="${param.subPage != 'angadia' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('angadia.html');">Angadia</a>
					</div>
				</c:if>
				<c:if test="${USER_SESSION.userUpperActivityMap['6_Reports']>0 }">
					<div class="<c:if test="${param.subPage == 'reports' }">sub_active</c:if><c:if test="${param.subPage != 'reports' }">sub_up</c:if>">
						<a href="javascript:void(0);" onclick="func('reports.html');">Reports</a>
					</div>
			    </c:if>
			</c:if>
		</div>
		<div style="display:none" id="webLink"></div>
		<div id="helpText"
			title="${fn:toUpperCase(param.page)} - ${fn:toUpperCase(param.subPage)} HELP"></div>
		</div>
		<br/>
		<div id="ajaxMessage" align="center" style="padding: 5px" class="ajaxMsg"></div>
		<br/>
</div>

