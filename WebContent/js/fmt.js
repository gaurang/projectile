	function callURL(filePath){
		filePath +='.jpg';
	 $.ajax({url:'cert/'+filePath,type:'HEAD',
		 error:function(){
		 window.open('cert/'+filePath,'_newtab');
	 },
	 success: function(){
		 	window.open('cert/'+filePath,'_newtab');
	 		}
	 	});
	 } 

	function getDNALink(cellvalue, options, rowObject){
		return '<a href="javascript:void(0);" onclick="javascript:window.open(\'packetDetails.html?pktId='+options.rowId+'\',\'_newtab\', \'menubar=1,resizable=1,width=600,height=400\');" title="Click on packet number to View more details">'+cellvalue+'</a>';
	}
	function getCertLink(cellvalue, options, rowObject){
		if(cellvalue == null)
			return '';
		if(rowObject[rowObject.length-1] == 'null')
			return cellvalue;
		 else
			return '<a href=\"javascript:callURL(\''+rowObject[0]+'\');\">'+cellvalue+'</a>';
	}
	
	
	function getDGCLink(cellvalue, options, rowObject){
	//	if(rowObject[rowObject.length-1] == 'null'){
			return cellvalue;
		/*} else
			return '<a href=\"cert/'+rowObject[0] +'.jpg'+'\" target=\"_blank\">'+cellvalue+'</a>';*/
	}
	function getFavIcon(cellvalue, options, rowObject){
		   
	 if(cellvalue != '-')	
		return '<img src="images/Favorite.png" alt="Favorites"/>';
	 else
		return '-';
	}
	
	function doReportFormSubmitTo(target){

		var myForm = document.createElement("form"); 
		document.body.appendChild(myForm);
		
		myForm.setAttribute('name',''); 
		myForm.method = "POST"; 
		myForm.action = target; 
		
		//if period exists
		
		
		myForm.submit();
	}


	function createHiddenInput( name , value ) 
	{ 
		var hidden = document.createElement("input"); 
		hidden.setAttribute('type', 'hidden'); 
		hidden.setAttribute('value', value ); 
		hidden.setAttribute('name', name ); 
		hidden.setAttribute('id', name ); 
		
		return hidden; 
	} 
	function adjustHeight(grid, maxHeight){
	    var height = grid.height();

	    if (height>maxHeight)height = maxHeight;
	    else height = 'auto';
  	    
            grid.setGridHeight(height);
           
              
	}
	function getRate(cellvalue, options, rowObject){
		if(cellvalue == null || isNaN(cellvalue)){
			return '0';
		}else{
			return parseFloat(cellvalue).toFixed(2);
		}
	}
	function getRateLocal(cellvalue, options, rowObject){
		var exRate = $('#exRate').val();
		if(cellvalue == null || isNaN(cellvalue)){
			return '0';
		}else{
			return parseFloat(cellvalue * exRate).toFixed(2);
		}
	}
	function getFNC(cellvalue, options, rowObject){
			return '';
	}

	function getRap(cellvalue, options, rowObject){
		if(cellvalue == null || isNaN(cellvalue)){
			return '-';
		}else{
			return parseFloat(cellvalue).toFixed(2);
		}
	}
	
	function priceCheck(value, colname) {
		if(value == ''){
			return [false,"Please enter value for Price"];
		}else if(value < 5 && value> 10000000 ){
			return [false,"Price is either too low or too high"];
		}else
			return [true,""];
	}
	function priceCheckRap(value, colname) {
		 if(value > 100 && value < -100 ){
				return [false,"Rap discount is out of range"];
			}else
				return [true,""];
	}
	
	function getPartyAcc(cellvalue, options, rowObject){
		return selfParty[cellvalue];
	}

	