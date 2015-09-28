
javascript:window.history.forward(1);
var selection = new Array();
var pktRates = new Object();
var pktRaps = new Object();
var pktCts = new Object();
var pktPcs = new Object();
var pktCtsEdit = new Object();
var pktPcsEdit = new Object();

var createMemoGrid ;
var createStockGrid;
var selfParty = new Object();

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
function makeExcel(){
	var s =  selection;
	if($("#email").val() !=''){
		alert('An email will be sent to this email id ');		
	}
	
	if(s.length == 0 ){
		s =  jQuery("#memoResult").jqGrid('getGridParam','selarrrow');
	}
	var str ='';
	for ( var i = 0; i < s.length; i++) {
		str +='&sVal='+s[i];
	}
	var curr = "";
	if($("#accType_L").checked)
		curr = "L";
	if($('#exportformat').val() > 2){
		
	}
	str +='&currency='+curr+'&factor='+$("#exRate").val()+'&'+$('#searchForm').serialize();
	window.open('makeExcelByMapping.html?1=1'+str,'_newtab');
}
function submitOrder(){
	var s =  selection;
	if(s.length == 0 ){
		s =  jQuery("#memoResult").jqGrid('getGridParam','selarrrow');
	}
	if(s.length == 0 ){
		alert('Please select packets');
		return;
	}
	if($('#orderType').val() == ''){
		alert('Please select Order Type ');
		return;
	}
	if(($('#orderType').val() == 'MI' || $('#orderType').val() == 'SL') && $('#partyAccId').val()=='' ){
		alert('Please select Buyer to create Memo');
		return;
	}else if(($('#orderType').val() == 'LI' )&&  $('#vendor').val()==''){
		alert('Please select Vendor');
		return;
	}
	else if (($('#orderType').val() == 'CI' ) && $('#lab').val()==''){
		alert('Please select lab');
		return;		
	}
	
	/**if( $('#comments').val()==''){
		alert('Please Enter comments ');
		return;
	}*/
	if($('#memoDate').val()=='' ||  ($('#dueDate').val()==''&& $('#orderType').val() == 'SL')){
		alert('Please Enter Memo-Date and Due-date ');
		return;
	}
	var msg ='';
	for(var i = 0 ;i<selection.length;i++ ){
		if(jQuery("#memoResult").jqGrid('getCell',selection[i],'status')!='AVLB'
					&& jQuery("#memoResult").jqGrid('getCell',selection[i],'pcs' == 1)){
			alert('Stones are already on memo Please take return');
			return;
		}
		if(isNaN(pktRates['rate'+selection[i]]) || pktRates['rate'+selection[i]]==0){
			alert('Packet Rate is either non-numeric or 0 for '+jQuery("#memoResult").jqGrid('getCell',selection[i],'pktCode'));
			return;
		}
		//PCS CTS validation
		/*if(containsElement(pktCtsEdit,'cts'+selection[i])){
			if(!containsElement(pktPcsEdit,'cts'+selection[i])){
				msg += 'Cannot issue all cts from parcel'+jQuery("#memoResult").jqGrid('getCell',selection[i],'pktCode'+'\n');
				continue;
			}			
		} else if(pktPcsEdit['pcs'+selection[i]]){
			if(!containsElement(pktPcsEdit,'pcs'+selection[i])){
				msg += 'Cannot issue all pcs from parcel'+jQuery("#memoResult").jqGrid('getCell',selection[i],'pktCode'+'\n');
				continue;
			}
		}*/
	}
	if(msg ==''){
		$.ajax({type:'POST',
			url:'generateMemo.html?'+$('#searchForm').serialize(),
			dataType: 'json',
			data: { selectedPkts : s, rates: JSON.stringify(pktRates), raps:JSON.stringify(pktRaps), pktCts: JSON.stringify(pktCtsEdit), pktPcs: JSON.stringify(pktPcs)},
			success: function(json){
				if(json!=null && json !=""){
						errorMsgPr(json[1]);
						if(!isNaN(json[2]) &&  parseInt(json[2]) > 0){
							$("#memoResult").trigger("reloadGrid");
						 	window.open('memoPrintPDF.html?orderId='+json[2],'_newtab');
						}
				}
			},
			error: function(xmlHttpRequest, textStatus, errorThrown) {
				errorFunc();
			}
		});
	}else{
		alert(msg);
	}
	clrSel();
}

function transferPkts(){
	var s =  selection;
	if(s.length == 0 ){
		s =  jQuery("#memoResult").jqGrid('getGridParam','selarrrow');
	}
	if($('#orderType').val() == 'MA' &&  $('#self').val()==''){
			alert('Please select Self Party to Issue');
			return;
	}
	$.ajax({type:'POST',
		url:'transferPkts.html',
		dataType: 'json',
		data: { selectedPkts : s,partyAccId:$('#self').val()},
		success: function(json){
			if(json!=null && json !=""){
				errorMsgPr(json[1]);
				$("#memoResult").trigger("reloadGrid");
			}
		},
		error: function(xmlHttpRequest, textStatus, errorThrown) {
			errorFunc();
		}
	});
	clrSel();
}
function memoReturn(){
	var s =  selection;
	if(s.length == 0 ){
		s =  jQuery("#memoResult").jqGrid('getGridParam','selarrrow');
	}
	if($('#memoNos').val()==''  ){
		alert('Memo Number is Compulsory');
		return;
	}else if(s.length == 0   ){
		var input = confirm('This will return all packets in memo , \n Do you want to continue ?');
		if(!input){
			return;
		}
	}
	$.ajax({type:'POST',
		url:'memoReturn.html',
		dataType: 'json',
		data: { selectedPkts : s,memoNos:$('#memoNos').val()},
		success: function(json){
			jsonSuccess(json);
			$("#memoResult").trigger("reloadGrid");
		},
		error: function(xmlHttpRequest, textStatus, errorThrown) {
			errorFunc();
		}
	});
	clrSel();
}

function filterSelection(){
   var s =  selection;
   $('#memoResult').setGridParam({url:'memoLoadGrid.html?'+$('#searchForm').serialize(),postData:{ selectedPkts : s, cart:'1', rates:JSON.stringify(pktRates), raps:JSON.stringify(pktRaps), pktCts: JSON.stringify(pktCts), pktPcs: JSON.stringify(pktPcs)}, datatype:'json'}).trigger("reloadGrid"); 
}

 
 function changeOrderType(){
	 if($('#orderType').val() == 'LI'){
		 $('#vendorParty').show();
		 $('#selfParty').hide();
		 $('#SentToLab').hide();
		 $('#trfStock').hide();
		 $('#createMemo').show();		 
	 }else if($('#orderType').val() == 'CI'){
		 $('#vendorParty').hide();
		 $('#selfParty').hide();
		 $('#SentToLab').show();
		 $('#trfStock').hide();
		 $('#createMemo').show();
	}else if($('#orderType').val() == 'MA'){
		 $('#vendorParty').hide();
		 $('#selfParty').show();
		 $('#SentToLab').hide();
		 $('#trfStock').show();
		 $('#createMemo').hide();
	}else {
		 $('#vendorParty').hide();
		 $('#selfParty').hide();
		 $('#SentToLab').hide();
		 $('#trfStock').hide();
		 $('#createMemo').show();

	 }
	 
 }
 
 function searchoption(fld){
	 if(fld.checked){
			$('memoSpan').html("Rough Nos");
			$('#memoReturn').hide();
	 }else{
			$('#memoSpan').html("Memo Nos."); 
			$('#memoReturn').show();
	 }
 }
 function chFncy(fld){
	 if(fld.checked){
			$('#FNC_row').show();
			$('#FNCI_row').show();
			$('#FNCO_row').show();
			$("#C").dropdownchecklist("disable");
			$("#C").val(null);
			$("#C").dropdownchecklist("refresh");
	 }else{
		    $('#FNC_row').hide();
			$('#FNCI_row').hide();
			$('#FNCO_row').hide();
			$("#C").dropdownchecklist("enable");
	 }
	 
 }
 function clrSel(){
	 selection = new Array();
	 pktRates = new Object();
	 pktRaps = new Object();
	 $('#selCount').html(selection.length);
	 $('.cartLk').hide();
	 $('.cbox').attr("checked", false);
	 $('#buyerName').val('');
	 $('#partyAccId').val('');
	 $('#term').val('-1');
	 $('#brokerName').val('');
	 $('#brokerId').val('');
	 $('#dueDate').val('');
	 $('#exRate').val('');
	 $('#accType_L').attr("checked", true);
	 $('#accType_E').attr("checked", true);
	 
 }
 function onSelectFun(id, status){
	if(jQuery("#memoResult").jqGrid('getCell',id,'status') !='AVLB'){
		var pktCode = jQuery("#memoResult").jqGrid('getCell',id,'pktCode');
		//alert('The selected packet - "'+pktCode+'" is on Memo');
	}
	if(status){
		if(!containsElement(selection, id))
		selection.push(id);
	}else{
		removeByElement(selection, id);
	}
	$('#selCount').html(selection.length);
	if(selection.length >0){
		$('.cartLk').show();
	}
	else{
		$('.cartLk').hide();
	}
 }
 
var oldRap = 0;
var oldRate =0;
var oldPcs = 0;
var oldCts =0;
var lstPkt = '';

function createGrid(link, col_names, col_models){
	jQuery("#memoResult").jqGrid({
		sortable: true, 
		url: link ,
		 datatype: 'local',
		 postData :{currency:$('#currency').val(), factor:$('#factor').val()}, 
		 colNames:col_names,
		 colModel:col_models, 
			rowNum:50, 
			rowList:[50,75,100], 
			pager: '#pmemoResult',
			sortname: 'SZ_so',
			sortorder: "desc", 
			cellEdit: true, 
			width: "900", 
			afterInsertRow: function(rowid, aData, rowData){ 
				if(containsElement(selection, rowid)){
					var rate = pktRates['rate'+rowid];
					var rap = pktRaps['rap'+rowid];
				 	jQuery("#memoResult").setSelection(rowid);
					//jQuery("#memoResult").jqGrid('setRowData',rowid,{total: Math.round(rate*parseFloat(cts)),RATE:rate,RAP: rap});
					if(rowData.pcs != 1){//Only for parcel
						var cts = pktCts['cts'+rowid];
						var pcs = pktPcs['pcs'+rowid];
						var acts = jQuery("#memoResult").jqGrid('getCell',rowid,'ACTS');
						var rej = (100- (cts/acts)*100).toFixed(2);
						jQuery("#memoResult").jqGrid('setRowData',rowid,{CTS:cts, pcs: pcs,REJ:rej});
					}
					pktRates['rate'+rowid] =  jQuery("#memoResult").jqGrid('getCell',rowid,'RATE');
				 	pktRaps['rap'+rowid] =  jQuery("#memoResult").jqGrid('getCell',rowid,'RAP');
					
				}else{
				 	pktRates['rate'+rowid] =  jQuery("#memoResult").jqGrid('getCell',rowid,'RATE');
				 	pktRaps['rap'+rowid] =  jQuery("#memoResult").jqGrid('getCell',rowid,'RAP');
				 	if(rowData.pcs != 1){//Only for parcel
					 	pktCts['cts'+rowid] =  jQuery("#memoResult").jqGrid('getCell',rowid,'CTS');
					 	pktPcs['pcs'+rowid] =  jQuery("#memoResult").jqGrid('getCell',rowid,'pcs');
					 	pktCtsEdit['cts'+rowid] = pktCts['cts'+rowid];
					 	pktPcsEdit['pcs'+rowid] = pktPcs['pcs'+rowid];
				 	}
				}
			 	if(rowData.statusId==2){
			 		jQuery("#memoResult").setCell(rowid,'pktCode','',{'background-color':'grey'},'');
				}
			 	if(jQuery("#memoResult").jqGrid('getCell',rowid,'pcs') == 1){
					jQuery("#memoResult").jqGrid('setCell',rowid,'CTS','','not-editable-cell');
	        	}
			 	jQuery("#memoResult").jqGrid('setCell',rowid,'pcs','','not-editable-cell'); 

			 	var exRate = $('#exRate').val();
			 	var rate = jQuery("#memoResult").jqGrid('getCell',rowid,'RATE');
			 	jQuery("#memoResult").jqGrid('setRowData',rowid, {RATE_LOCAL:Math.round(rate*exRate), total_LOCAL:Math.round(rate*parseFloat(cts)*exRate)});
			}, 
			onSelectRow: function(id, status){ 
					onSelectFun(id,status);
			} ,
			 loadComplete: function() {
				 if($('#srchPair').attr("checked")){
					var rowData = $("#memoResult").getRowData();
					var rids = $('#newapi').jqGrid('getDataIDs');
			        var pairCss = 'matchPair';
			        var lstPkt ='';
			        for (var i = 0; i < rowData.length; i++) {
			        	var rowid= rids[i];
			        	if(rowData[i].pktCode != lstPkt){
							if(pairCss == 'matchPair')			        		
			        			pairCss = '';
							else
								pairCss = 'matchPair';
			        	}
			        	jQuery('#memoResult').jqGrid('setRowData',rowid, false, pairCss);
			        	lstPkt = rowData[i].pairStock;
			        }
				 }
				 $('#cb_memoResult').change(function(){
				  	  var status =false;
					  if(this.checked)
						  status = true;
					  var grid = $("#memoResult");
					  var ids = grid.getDataIDs();
					  for (var i=0, il=ids.length; i < il; i++ ) {
						 onSelectFun(ids[i],status);
					  }
				 });
			    },
			shrinkToFit : "false", 
			height: "330",
			caption:'Stock &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="errorMsg" >Total Packets under selection <span id="selCount">0</span>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="filterSelection();" id="cartLk" class="cartLk" style="display:none;">Show All</a>&nbsp;&nbsp; <a href="javascript:void(0);" onclick="clrSel();" id="clr" class="cartLk" style="display:none;">Clear All</a></span>' ,
			multiselect:"true",
			footerrow : true,
			userDataOnFooter : true,
			altRows : true,
			 jsonReader:{
	            root: "rows",
	            page: "page",
	            total: "total",
	            records: "records",
	            cell:"cell",
	            certId:"certId",
	            id: "id",
	            userdata: "userdata"
			} ,
			cellsubmit : 'clientArray',
			beforeEditCell : function(rowid,name,val,iRow,iCol) { 
					oldRap = jQuery("#memoResult").jqGrid('getCell',rowid,'RAP');
					oldRate = jQuery("#memoResult").jqGrid('getCell',rowid,'RATE'); 
			} ,
			afterSaveCell : function(rowid,name,val,iRow,iCol) { 
				
				val = parseFloat(val);
				var cts = jQuery("#memoResult").jqGrid('getCell',rowid,'CTS');
				var pcs = jQuery("#memoResult").jqGrid('getCell',rowid,'pcs');
				
				var rapPrice =  oldRate/(1+(oldRap/100));
				var exrate = $('#exRate').val();
				var rap;
				var rate;
				var total;
				var totalLocal;
				var rateLocal;
				if(name == 'RAP') {
					rate = jQuery("#memoResult").jqGrid('getCell',rowid,'RATE');
					rate = (((val/100)+1)*rapPrice).toFixed(2);
					rateLocal = parseFloat(rate * exrate).toFixed(2);
					totalLocal =  (parseFloat(rate)*parseFloat(cts)).toFixed(2);
					total = (parseFloat(rateLocal)*parseFloat(cts)).toFixed(2);
					jQuery("#memoResult").jqGrid('setRowData',rowid,{total:total , RATE:rate,RATE_LOCAL:rateLocal,total_LOCAL:totalLocal });
				} 
				if(name == 'RATE') {
					rap = jQuery("#memoResult").jqGrid('getCell',rowid,'RAP');
					rap = (-1*(1-(val/rapPrice))*100).toFixed(2); 
					total = (parseFloat(val)*parseFloat(cts)).toFixed(2);
					rateLocal = parseFloat(val * exrate).toFixed(2);
					totalLocal =  (parseFloat(rateLocal)*parseFloat(cts)).toFixed(2);
					jQuery("#memoResult").jqGrid('setRowData',rowid,{total: Math.round(val*parseFloat(cts)), RAP:rap,RATE_LOCAL:rateLocal,total_LOCAL:totalLocal});
				}
				if(name == 'RATE_LOCAL') {
					
					rate = (parseFloat(val)/exrate).toFixed(2) ; //Math.round(parseFloat(val)/exrate);
					rap = jQuery("#memoResult").jqGrid('getCell',rowid,'RAP');
					rap = (-1*(1-(rate/rapPrice))*100).toFixed(2); 
					total = (parseFloat(rate)*parseFloat(cts)).toFixed(2);
					totalLocal =  (parseFloat(val)*parseFloat(cts)).toFixed(2);
					jQuery("#memoResult").jqGrid('setRowData',rowid,{total:total , RATE:rate,total_LOCAL:totalLocal, RAP:rap });
				}
				var newRate =  jQuery("#memoResult").jqGrid('getCell',rowid,'RATE');
				var newRap =  jQuery("#memoResult").jqGrid('getCell',rowid,'RAP');
				pktRates['rate'+rowid] = newRate;
				pktRaps['rap'+rowid] = newRap;
				/*	if(name == 'pcs'){ 
					if(pktPcsEdit['pcs'+rowid] >= pktPcs['pcs'+rowid]) {
						pktPcsEdit['pcs'+rowid] = val;
					}else{
						jQuery("#memoResult").restoreCell(iRow,iCol);
						if(pktPcsEdit['pcs'+rowid] >= pktPcs['pcs'+rowid])
						alert('CTS cannot be less then '+pktCts['cts'+rowid]);
					}	
				}*/
				if(name == 'CTS'){
					//if(val <= pktCts['cts'+rowid]) {
						pktCtsEdit['cts'+rowid] = val;
						if(pcs != 1){
							var acts = jQuery("#memoResult").jqGrid('getCell',rowid,'ACTS');
							var rej = (100- (cts/acts)*100).toFixed(2);
							if(rej < 0 || acts < 0)
								rej = 0;
							jQuery("#memoResult").jqGrid('setRowData',rowid,{total: Math.round(newRate*parseFloat(val)), REJ:rej });
						}
					/*}else{
						if(pcs ==1 ){
							jQuery("#memoResult").restoreCell(iRow,iCol);
							if(val > pktCts['cts'+rowid])
								alert('CTS cannot be less then '+pktCts['cts'+rowid]);
						}
					}*/
				}
				if(name == 'REJ'){
					if(val <= 0 || val > 100) {
						alert('REJ is between 0 to 100 ');
					}
					else{
						var acts = jQuery("#memoResult").jqGrid('getCell',rowid,'ACTS');
						var cts = (acts - ((val/100)*acts)).toFixed(2);
						jQuery("#memoResult").jqGrid('setRowData',rowid,{total: Math.round(newRate*parseFloat(cts)), CTS:cts });
					}
				}
				// $('#memoResult').trigger("reloadGrid");
			} 
			
	}); 
	jQuery("#memoResult").jqGrid('navGrid','#pmemoResult',{edit:false,add:false,del:false,search:false});
}

function changeAutoComplete(fld,fld2) {
		if($("#"+fld).val() == ''){
			$("#"+fld2).val('');
	}
}		
function autoCompleteByr(){
	$("#buyerName").autocomplete('reloadPartyAJAX.html?pType=BYR',
			{
			minChars: 0,
			autoFill: false,
			matchContains: false,
			selectFirst: false,
			dataType: 'json',
			max:100,
			parse: function(json) {	
				var parsed = []; 
				if(json!=null && json.length != 0){
					 $(json).each(function() {
						 parsed[parsed.length] = {
						 data: {id:this.id,description:this.companyName+'/'+this.branchCode+'/'+this.termCode+'/'+this.accType,accType:this.accType,brokerPartyId:this.brokerPartyId,termId:this.termId},
						 value: this.companyName+'/'+this.branchCode+'/'+this.termCode+'/'+this.accType,
						 result: this.companyName+'/'+this.branchCode+'/'+this.termCode+'/'+this.accType
					 	};
					 });
				}
			   return parsed; 
			},
			formatItem: function(row, i, max, searchTerm) {	
				return row.description;
			},
			formatMatch: function(row, i, max) {
				return row.description;
			},
			formatResult: function(row, i, max) {
					return row.description;
			}	
	});
	
	$("#buyerName").result(function(event, data, formatted) {
		$("#partyAccId").val(!data ? "-1" : data.id);
		$("#accType_"+data.accType).attr("checked",true);
		$("#term").val(data.termId);
		$.each(brokerData,function(index, obj) { 
			if(obj.id == data.brokerPartyId){
				$("#brokerName").val(obj.description);
				$("#brokerId").val(obj.id);
			}
		});
		submitSearch(document.getElementById('btnSearch2'));
	});
	$('#buyerName').change(function(row, i, max) {
		changeAutoComplete('buyerName','partyAccId');
	});

}
function autoCompleteBkr(brokerData){
	$("#brokerName").autocomplete('reloadPartyAJAX.html?pType=BKR',
			{
			minChars: 0,
			autoFill: true,
			matchContains: false,
			selectFirst: true,
			max: 100,
			dataType: 'json',
			parse: function(json) {	
				var parsed = []; 
				if(json!=null && json.length != 0){
					 $(json).each(function() {
						 parsed[parsed.length] = {
						 data: this,
						 value: this.description,
						 result:  this.description
					 	};
					 });
				}
			   return parsed; 
			},
			formatItem: function(row, i, max, searchTerm) {	
				return row.description;
			},
			formatMatch: function(row, i, max) {
				return row.description;
			},
			formatResult: function(row, i, max) {
					return row.description;
			}	
	});
	$("#brokerName").result(function(event, data, formatted) {
		$("#brokerId").val(!data ? "-1" : data.id);
	});
	$('#brokerName').change(function(row, i, max) {
		changeAutoComplete('brokerName','brokerId');
	});
}

	



function deposit(){
	if($('#partyAccId').val() == ''){
			alert('Please select Party');
			return;
		}
	 addTab('Deposit','deposit.html?partyAccId='+$('#partyAccId').val());
}
function partyRep(){
	if($('#partyAccId').val() == ''){
		alert('Please select Party');
		return;
	}
	addTab('Report','partyLedger.html?partyAccId='+$('#partyAccId').val());
}
