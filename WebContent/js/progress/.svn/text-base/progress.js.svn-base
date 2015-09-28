// JScript File
(function ($) {

    $.showprogress = function(msgValue)
    {
        /*$.hideprogress();*/
        $("BODY").append('<div id="processing_overlay">');
            $("BODY").append(
		      '<div id="processing_container">' +
		        /*'<div id="processing_title">This is title</div>' +*/
		        '<div id="processing_content">' +
    		            '<img src="js/progress/img/loading.gif" style="width:25px;height:25px;" alt=""/>' +
    		            "<br>"+msgValue+"" +
			    '</div>' +
		      '</div> </div>');
		 
		var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
		
		$("#processing_container").css({
			position: pos,
			zIndex: 99999,
			padding: 0,
			margin: 0
		});
		
		$("#processing_container").css({
			minWidth: $("#processing_container").outerWidth(),
			maxWidth: $("#processing_container").outerWidth()
		});
		  
		var top = (($(window).height() / 2) - ($("#processing_container").outerHeight() / 2)) + (-75);
		var left = (($(window).width() / 2) - ($("#processing_container").outerWidth() / 2)) + 0;
		if( top < 0 ) top = 0;
		if( left < 0 ) left = 0;
		
		// IE6 fix
		if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
		
		$("#processing_container").css({
			top: top + 'px',
			left: left + 'px'
		});
		$("#processing_overlay").height( $(document).height() );
    },
    $.hideprogress = function()
    {
        $("#processing_container").remove();
        $("#processing_overlay").remove();
    },
    $.showmsg = function(msgEle,msgText,msgClass,msgIcon,msgHideIcon,autoHide){
        var tblMsg;
        
        tblMsg = '<table width="100%" cellpadding="1" cellspacing="0" border="0" class="' + msgClass + '"><tr><td style="width:30px;" align="center" valign="middle">' + msgIcon + '</td><td>' + msgText + '</td><td style="width:30px;" align="center" valign="middle"><a href="javascript:void(0);" onclick="$(\'#' + msgEle + '\').toggle(400);">' + msgHideIcon + '</a></td></tr></table>';
        
        $("#" + msgEle).html(tblMsg);
        $("#" + msgEle).show();
        if(autoHide)
        {
            setTimeout(function(){
                $('#' + msgEle).fadeOut('normal')},10000    
	        );
        }
    }
})(jQuery);

