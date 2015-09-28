/**
 * Writen by arvind Kushwaha on 10/07/2012
 * 
 * This JavaScript is used for display and hiding INR Values
 */

function displayINRValue(value){
	$(".displayInrValue").toggle();
	$("#showInr").remove();
	var html="";
	if(value=='Show INR'){
		   html +="<td colspan='2' align='center'><input type='button' onclick='displayINRValue(this.value);' name='displayInrValue' value='Hide INR'>";
	}
	else{
		html +="<td colspan='2' align='center'><input type='button' onclick='displayINRValue(this.value);' name='displayInrValue' value='Show INR'>";
	}
	$("#showINRValue").html(html);
}