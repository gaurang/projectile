function errorFunc(userId){
	if(userId == null || userId == '' || userId  < 0){
        alert('You have logged out of system kindly login again .system will take you to login section');
   		window.location = 'indexCrm.html';
	}else{
		alert('Some error occured in system kindly contact system admin');
	}
}
function jsonSuccess(json){
	if(json!=null && json !=""){
		if(json.toString().indexOf("ok") == -1 ){
			errorMsgPr(json[1]);
		}else{
			errorMsgPr(json[1]);
		}
	}
}
function errorMsgPr(jsonMsg){
	$('#errorMsg').html(jsonMsg);
  	setTimeout (function() {clearMsg();} ,20000);  
}
function clearMsg(){
		$('#errorMsg').html('');
}

function chkNumVal(elem , val, min, max){
	if(isNaN(val)){
		alert('Value is not numeric, please enter numeric');
		elem.value = min;
		elem.focus();
	}else{
		if(parseInt(val)< min || parseInt(val) > max ){
			alert('Value should be between "'+min+'" and "'+max+'"' );
			if(elem.id.indexOf("from")>-1)
				elem.value = min;
			else
				elem.value = max;
		}
	}
	checkMinMx(elem);
}
function checkMinMx(elem , val){
	var elemid = elem.id;
	var prp = elemid.substring(elemid,elemid.indexOf("_"));
	if($('#'+prp+'_from').val() != '' && $('#'+prp+'_to').val() != ''){
		if( parseFloat($('#'+prp+'_from').val()) > parseFloat( $('#'+prp+'_to').val())){
			var a = $('#'+prp+'_from').val();
			$('#'+prp+'_from').val($('#'+prp+'_to').val());
			$('#'+prp+'_to').val(a);
		}
	}
}

$.fn.clearForm = function() {
	alert("called");
  // iterate each matching form
  return this.each(function() {
    // iterate the elements within the form
    $(':input', this).each(function() {
      var type = this.type, tag = this.tagName.toLowerCase();
      if (type == 'text' || type == 'password' || tag == 'textarea')
        this.value = '';
      else if (type == 'checkbox' || type == 'radio')
        this.checked = false;
      else if (tag == 'select'){
    	  if(this.attr("multiple")){
    		  $(this).val(null);
    		  $(this).dropdownchecklist("refresh");
    	  }else{
    		  this.selectedIndex = -1;
    	  }
      }
    });
  });
};

function clearForm(form) {
  // iterate over all of the inputs for the form
  // element that was passed in
  $(':input', form).each(function() {
    var type = this.type;
    var tag = this.tagName.toLowerCase(); // normalize case
    // it's ok to reset the value attr of text inputs,
    // password inputs, and textareas
    if (type == 'text' || type == 'password' || tag == 'textarea')
      this.value = "";
    // checkboxes and radios need to have their checked state cleared
    // but should *not* have their 'value' changed
    else if (type == 'checkbox' || type == 'radio')
      this.checked = false;
    // select elements need to have their 'selectedIndex' property set to -1
    // (this works for both single and multiple select elements)
    else if (tag == 'select'){
	      if($(this).attr("multiple")){
	   		  $(this).val(null);
	   		  $(this).dropdownchecklist("refresh");
	   	  }else{
	   		 $(this).val(-1);
	   	  }
    }
  });
};
function checknumber(value){
	var x = value;
	var anum=/(^\d+$)|(^\d+\.\d+$)/;
	
	if (anum.test(x))
		testresult=true;
	else{
		alert("Please input a valid number!");
		testresult=false;
	}
	return (testresult);
}

function checkInt(value){
	var x = value;
	if(isNaN(x)){
		alert("Please input a valid number!");
		testresult=false;
	}else{
		testresult=true;
	}
		
	return (testresult);
}
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
	var scrolledY = document.body.scrollTop || document.documentElement.scrollTop || self.pageYOffset;
	
	$(id).css('top',  scrolledY+winH/2-$(id).height()/2);
	$(id).css('left', winW/2-$(id).width()/2);

	//transition effect
	$(id).fadeIn(2000); 
}
function calDate(fld,objId){
	if(!isNaN(fld.value)){
		var now = $('#memoDate').DatePickerGetDate(false);
		var val = fld.value;
		if(val == ''){
			val = 0;
		}
		now.setDate(now.getDate()+parseInt(val));
		$('#'+objId).DatePickerSetDate(now,true);
		var obj = $('#'+objId).DatePickerPopDate();
	}
}
function getExRate(from, to, fld){
	$.getJSON('getExRate.html?from='+from+'&to='+to, function(json) {
		if(json!= null && json.error != ''){
			$(fld).val(parseFloat(json).toFixed(2));
		}else{
			alert('Could not load Ex rate please put mannually');
		}
	});
}
function checkCompany(companyName){
	$.ajax({type:'POST',
			url:'checkCompanyName.html',
			dataType: 'json',
			data: {"companyName":companyName},
			success: function(json){
				if(json!=null && json !=""){
					if(json>1){
						var html="Company Name: "+companyName+" is Already Exist";
						$("#generalDetails #error").html(html);
						$("#companyName").val("");
					}
				}
				else{
					$("#generalDetails #error").html('');
					
				}
				return false;
			},
			error: function(xmlHttpRequest, textStatus, errorThrown) {
			}
	});
}

function validateMobileNo(mobNo)
{
    var y = mobNo;
   if(isNaN(y)||y.indexOf(" ")!=-1)
   {
	   alert("Enter numeric value");
	  // $('#ownerMobNo').val('');
	   setFoucus("ownerMobNo");
	   return false;
   }
   $.ajax({type:'POST',
		url:'checkMobileNo.html',
		dataType: 'json',
		data: {"ownerMobNo":mobNo},
		success: function(json){
			if(json!=null && json !=""){
				if(json>1){
					var html="Mobile No: "+mobNo+" is Already Exist";
					$("#generalDetails #error").html(html);
					$("#ownerMobNo").val("");
				}
			}
			else{
				$("#generalDetails #error").html('');
			}
			return false;
		},
		error: function(xmlHttpRequest, textStatus, errorThrown) {
		}
   });
   return false;
}
function setFoucus(name)
{
	 window.setTimeout(function ()
	   {
			$("#"+name+"").focus();
	   }, 0);
	 return true;
}


function getAngadiaBal(id,  fld){
	$.getJSON('getClosingBalAngadiaAJAX.html?angadiaId='+id, function(json) {
		if(json!= null && json.error != ''){
			var txtObj = $('#'+$(fld).attr("id")+' :selected').text();
			if(txtObj.indexOf('-')>0){
				txt = txtObj.substring(txtObj.indexOf('-'));
			}
			$('#angadiaBal').html(json);
			 $('#angadiaCurr').val('INR');
			 checkCurrParty();
		}else{
			alert('Could not load Angadia Balance please put mannually');
		}
	});
	
}
function getExcel(){
	window.location.href='getExcel.html?fileId='+$("#fileId1").val();
}
function checkCurrParty(){
	$('#table_amount').empty();
	$('#table_amount').append('Amount(' + $('#currency').val() + ')');
	if($('#currency').val() != 'USD'){
		$('#toUSDFromLOCAL').show();
	}else{
		$('#toUSDFromLOCAL').hide();
	}
	$('#toUSDFromLOCAL .from').html('1 USD = ');
	$('#toUSDFromLOCAL .to').html($('#currency').val());
	var toCurr = 'USD';
	if($('#angadiaRow').is(":visible")){
		toCurr = $('#angadiaCurr').val();
	}else{
		toCurr = $('#bankCurr').val();
	}
	$('#fromUSDtoLOCAL .from').html('1 USD = ');
	$('#fromUSDtoLOCAL .to').html(toCurr);
	if(toCurr == $('#currency').val() || toCurr == 'USD'){
		$('#fromUSDtoLOCAL').hide();
	}else	{
		$('#fromUSDtoLOCAL').show();
		getExRate('USD', toCurr, '#exRate2');
	}
}
