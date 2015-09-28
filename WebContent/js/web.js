function fnSubmit(elem){
	/*$("input[@name='shp[]']:checked").each(function() {
		alert(this.name + ' ||  '+ this.id+ ' and  '+ this.value);
		$("#sh option[value='"+ $(this).val()+"']").attr('selected', 'selected');
	});*/
	if($("input:checkbox[name=partyAccId]:checked").length==0){
		$('#locAll').attr("checked",true);
		chkAll('locAll', 'partyAccId');
	}
	
	if(elem == 'SS')
	{
		var sDesc = prompt("Please enter description for saving search criteria.","");
		if(sDesc == null)
			return false;
		$('#sDesc').val(sDesc);
		
	}
	$('#searchType').val(elem);
	document.eForm.submit();
}

function chkAll(frmFld, fld){
	if($('#'+frmFld).attr("checked") ==true){
		$('input[name='+fld+']').attr("checked", true);
	}else{
		$('input[name='+fld+']').attr("checked", false);
	}	
	
}
function chkNumVal(elem , val, min, max){
	if(isNaN(val)){
		alert('Value is not numeric, please enter numeric');
		elem.value = min;
		elem.focus();
	}else{
		if(parseInt(val)< min || parseInt(val) > max ){
			alert('Value should be between "'+min+'" and "'+max+'"' );
			if(this.id.indexOf("from")>-1)
				elem.value = min;
			else
				elem.value = max;
		}
	}
}
function chkPktNum(fld){
	 var text = fld.value;
    //fld.value = text.replace(/[^\d\s]/,"");
	 fld.value  = stripNonNumeric(text);
}

 function stripNonNumeric( str )
 {
   str += '';
   var rgx = /^\d|\,|-$/;
   var out = '';
   for( var i = 0; i < str.length; i++ )
   {
	   if(i==0 || i== str.length-1 )
		   if(str.charAt(i) == ',')
			   continue;
     if( rgx.test( str.charAt(i) ) ){
       if( !( ( str.charAt(i) == '.' && out.indexOf( '.' ) != -1 ) ||
              ( str.charAt(i) == '-' && out.length != 0 ) ) ){
         out += str.charAt(i);
       }
     }
   }
   return out;
 }
 


