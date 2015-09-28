(function(a){a.widget("ui.dropdownchecklist",{version:function(){alert("DropDownCheckList v1.4")},_appendDropContainer:function(b){var c=a("<div/>");c.addClass("ui-dropdownchecklist ui-dropdownchecklist-dropcontainer-wrapper");c.addClass("ui-widget");c.attr("id",b.attr("id")+"-ddw");c.css({position:"absolute",left:"-33000px",top:"-33000px"});var d=a("<div/>");d.addClass("ui-dropdownchecklist-dropcontainer ui-widget-content");d.css("overflow-y","auto");c.append(d);c.insertAfter(b);c.isOpen=false;return c},_isDropDownKeyShortcut:function(b,c){return b.altKey&&a.ui.keyCode.DOWN==c},_isDropDownCloseKey:function(b,c){return a.ui.keyCode.ESCAPE==c||a.ui.keyCode.ENTER==c},_keyFocusChange:function(b,c,d){var e=a(":focusable");var f=e.index(b);if(f>=0){f+=c;if(d){var g=this.dropWrapper.find("input:not([disabled])");var h=e.index(g.get(0));var i=e.index(g.get(g.length-1));if(f<h){f=i}else if(f>i){f=h}}e.get(f).focus()}},_handleKeyboard:function(b){var c=this;var d=b.keyCode||b.which;if(!c.dropWrapper.isOpen&&c._isDropDownKeyShortcut(b,d)){b.stopImmediatePropagation();c._toggleDropContainer(true)}else if(c.dropWrapper.isOpen&&c._isDropDownCloseKey(b,d)){b.stopImmediatePropagation();c._toggleDropContainer(false);c.controlSelector.focus()}else if(c.dropWrapper.isOpen&&b.target.type=="checkbox"&&(d==a.ui.keyCode.DOWN||d==a.ui.keyCode.UP)){b.stopImmediatePropagation();c._keyFocusChange(b.target,d==a.ui.keyCode.DOWN?1:-1,true)}else if(c.dropWrapper.isOpen&&d==a.ui.keyCode.TAB){}},_handleFocus:function(b,c,d){var e=this;if(d&&!e.dropWrapper.isOpen){b.stopImmediatePropagation();if(c){e.controlSelector.addClass("ui-state-hover");if(a.ui.dropdownchecklist.gLastOpened!=null){a.ui.dropdownchecklist.gLastOpened._toggleDropContainer(false)}}else{e.controlSelector.removeClass("ui-state-hover")}}else if(!d&&!c){if(b!=null){b.stopImmediatePropagation()}e.controlSelector.removeClass("ui-state-hover");e._toggleDropContainer(false)}},_cancelBlur:function(a){var b=this;if(b.blurringItem!=null){clearTimeout(b.blurringItem);b.blurringItem=null}},_appendControl:function(){var b=this,c=this.sourceSelect,d=this.options;var e=a("<span/>");e.addClass("ui-dropdownchecklist ui-dropdownchecklist-selector-wrapper ui-widget");e.css({display:"inline-block",cursor:"default",overflow:"hidden"});var f=c.attr("id");if(f==null||f==""){f="ddcl-"+a.ui.dropdownchecklist.gIDCounter++}else{f="ddcl-"+f}e.attr("id",f);var g=a("<span/>");g.addClass("ui-dropdownchecklist-selector ui-state-default");g.css({display:"inline-block",overflow:"hidden","white-space":"nowrap"});var h=c.attr("tabIndex");if(h==null){h=0}else{h=parseInt(h);if(h<0){h=0}}g.attr("tabIndex",h);g.keyup(function(a){b._handleKeyboard(a)});g.focus(function(a){b._handleFocus(a,true,true)});g.blur(function(a){b._handleFocus(a,false,true)});e.append(g);if(d.icon!=null){var i=d.icon.placement==null?"left":d.icon.placement;var j=a("<div/>");j.addClass("ui-icon");j.addClass(d.icon.toOpen!=null?d.icon.toOpen:"ui-icon-triangle-1-e");j.css({"float":i});g.append(j)}var k=a("<span/>");k.addClass("ui-dropdownchecklist-text");k.css({display:"inline-block","white-space":"nowrap",overflow:"hidden"});g.append(k);e.hover(function(){if(!b.disabled){g.addClass("ui-state-hover")}},function(){if(!b.disabled){g.removeClass("ui-state-hover")}});e.click(function(a){if(!b.disabled){a.stopImmediatePropagation();b._toggleDropContainer(!b.dropWrapper.isOpen)}});e.insertAfter(c);a(window).resize(function(){if(!b.disabled&&b.dropWrapper.isOpen){b._toggleDropContainer(true)}});return e},_createDropItem:function(b,c,d,e,f,g,h,i){var j=this,k=this.options,l=this.sourceSelect,m=this.controlWrapper;var n=a("<div/>");n.addClass("ui-dropdownchecklist-item");n.css({"white-space":"nowrap"});var o=g?' checked="checked"':"";var p=h?' class="inactive"':' class="active"';var q=m.attr("id");var r=q+"-i"+b;var s;if(j.isMultiple){s=a('<input disabled type="checkbox" id="'+r+'"'+o+p+' tabindex="'+c+'" />')}else{s=a('<input disabled type="radio" id="'+r+'" name="'+q+'"'+o+p+' tabindex="'+c+'" />')}s=s.attr("index",b).val(d);n.append(s);var t=a("<label for="+r+"/>");t.addClass("ui-dropdownchecklist-text");if(f!=null)t.attr("style",f);t.css({cursor:"default"});t.html(e);if(i){n.addClass("ui-dropdownchecklist-indent")}n.addClass("ui-state-default");if(h){n.addClass("ui-state-disabled")}t.click(function(a){a.stopImmediatePropagation()});n.append(t);n.hover(function(b){var c=a(this);if(!c.hasClass("ui-state-disabled")){c.addClass("ui-state-hover")}},function(b){var c=a(this);c.removeClass("ui-state-hover")});s.click(function(b){var c=a(this);b.stopImmediatePropagation();if(c.hasClass("active")){var d=j.options.onItemClick;if(a.isFunction(d))try{d.call(j,c,l.get(0))}catch(e){c.prop("checked",!c.prop("checked"));j._syncSelected(c);return}j._syncSelected(c);j.sourceSelect.trigger("change","ddcl_internal");if(!j.isMultiple&&k.closeRadioOnClick){j._toggleDropContainer(false)}}});n.click(function(b){var c=a(this);b.stopImmediatePropagation();if(!c.hasClass("ui-state-disabled")){var d=c.find("input");var e=d.prop("checked");d.prop("checked",!e);var f=j.options.onItemClick;if(a.isFunction(f))try{f.call(j,d,l.get(0))}catch(g){d.prop("checked",e);j._syncSelected(d);return}j._syncSelected(d);j.sourceSelect.trigger("change","ddcl_internal");if(!e&&!j.isMultiple&&k.closeRadioOnClick){j._toggleDropContainer(false)}}else{c.focus();j._cancelBlur()}});n.focus(function(b){var c=a(this);b.stopImmediatePropagation()});n.keyup(function(a){j._handleKeyboard(a)});return n},_createGroupItem:function(b,c){var d=this;var e=a("<div />");e.addClass("ui-dropdownchecklist-group ui-widget-header");if(c){e.addClass("ui-state-disabled")}e.css({"white-space":"nowrap"});var f=a("<span/>");f.addClass("ui-dropdownchecklist-text");f.css({cursor:"default"});f.text(b);e.append(f);e.click(function(b){var c=a(this);b.stopImmediatePropagation();c.focus();d._cancelBlur()});e.focus(function(b){var c=a(this);b.stopImmediatePropagation()});return e},_createCloseItem:function(b){var c=this;var d=a("<div />");d.addClass("ui-state-default ui-dropdownchecklist-close ui-dropdownchecklist-item");d.css({"white-space":"nowrap","text-align":"right"});var e=a("<span/>");e.addClass("ui-dropdownchecklist-text");e.css({cursor:"default"});e.html(b);d.append(e);d.click(function(b){var d=a(this);b.stopImmediatePropagation();d.focus();c._toggleDropContainer(false)});d.hover(function(b){a(this).addClass("ui-state-hover")},function(b){a(this).removeClass("ui-state-hover")});d.focus(function(b){var c=a(this);b.stopImmediatePropagation()});return d},_appendItems:function(){var b=this,c=this.options,d=this.sourceSelect,e=this.dropWrapper;var f=e.find(".ui-dropdownchecklist-dropcontainer");d.children().each(function(c){var d=a(this);if(d.is("option")){b._appendOption(d,f,c,false,false)}else if(d.is("optgroup")){var e=d.prop("disabled");var g=d.attr("label");if(g!=""){var h=b._createGroupItem(g,e);f.append(h)}b._appendOptions(d,f,c,true,e)}});if(c.explicitClose!=null){var g=b._createCloseItem(c.explicitClose);f.append(g)}var h=f.outerWidth();var i=f.outerHeight();return{width:h,height:i}},_appendOptions:function(b,c,d,e,f){var g=this;b.children("option").each(function(b){var h=a(this);var i=d+"."+b;g._appendOption(h,c,i,e,f)})},_appendOption:function(a,b,c,d,e){var f=this;var g=a.html();if(g!=null&&g!=""){var h=a.val();var i=a.attr("style");var j=a.prop("selected");var k=e||a.prop("disabled");var l=f.controlSelector.attr("tabindex");var m=f._createDropItem(c,l,h,g,i,j,k,d);b.append(m)}},_syncSelected:function(b){var c=this,d=this.options,e=this.sourceSelect,f=this.dropWrapper;var g=e.get(0).options;var h=f.find("input.active");if(d.firstItemChecksAll=="exclusive"){if(b==null&&a(g[0]).prop("selected")){h.prop("checked",false);a(h[0]).prop("checked",true)}else if(b!=null&&b.attr("index")==0){var i=b.prop("checked");h.prop("checked",false);a(h[0]).prop("checked",i)}else{var j=true;var k=null;h.each(function(b){if(b>0){var c=a(this).prop("checked");if(!c){j=false}}else{k=a(this)}});if(k!=null){if(j){h.prop("checked",false)}k.prop("checked",j)}}}else if(d.firstItemChecksAll){if(b==null&&a(g[0]).prop("selected")){h.prop("checked",true)}else if(b!=null&&b.attr("index")==0){h.prop("checked",b.prop("checked"))}else{var j=true;var k=null;h.each(function(b){if(b>0){var c=a(this).prop("checked");if(!c){j=false}}else{k=a(this)}});if(k!=null){k.prop("checked",j)}}}var l=0;h=f.find("input");h.each(function(b){var c=a(g[b+l]);var d=c.html();if(d==null||d==""){l+=1;c=a(g[b+l])}c.prop("selected",a(this).prop("checked"))});c._updateControlText();if(b!=null){b.focus()}},_sourceSelectChangeHandler:function(a){var b=this,c=this.dropWrapper;c.find("input").val(b.sourceSelect.val());b._updateControlText()},_updateControlText:function(){var a=this,b=this.sourceSelect,c=this.options,d=this.controlWrapper;var e=b.find("option:first");var f=b.find("option");var g=a._formatText(f,c.firstItemChecksAll,e);var h=d.find(".ui-dropdownchecklist-text");h.html(g);h.attr("title",h.text())},_formatText:function(b,c,d){var e;if(a.isFunction(this.options.textFormatFunction)){try{e=this.options.textFormatFunction(b)}catch(f){alert("textFormatFunction failed: "+f)}}else if(c&&d!=null&&d.prop("selected")){e=d.html()}else{e="";b.each(function(){if(a(this).prop("selected")){if(e!=""){e+=", "}var b=a(this).attr("style");var c=a("<span/>");c.html(a(this).html());if(b==null){e+=c.html()}else{c.attr("style",b);e+=a("<span/>").append(c).html()}}});if(e==""){e=this.options.emptyText!=null?this.options.emptyText:" "}}return e},_toggleDropContainer:function(b){var c=this;var d=function(b){if(b!=null&&b.dropWrapper.isOpen){b.dropWrapper.isOpen=false;a.ui.dropdownchecklist.gLastOpened=null;var c=b.options;b.dropWrapper.css({top:"-33000px",left:"-33000px"});var e=b.controlSelector;e.removeClass("ui-state-active");e.removeClass("ui-state-hover");var f=b.controlWrapper.find(".ui-icon");if(f.length>0){f.removeClass(c.icon.toClose!=null?c.icon.toClose:"ui-icon-triangle-1-s");f.addClass(c.icon.toOpen!=null?c.icon.toOpen:"ui-icon-triangle-1-e")}a(document).unbind("click",d);b.dropWrapper.find("input.active").prop("disabled",true);if(a.isFunction(c.onComplete)){try{c.onComplete.call(b,b.sourceSelect.get(0))}catch(g){alert("callback failed: "+g)}}}};var e=function(b){if(!b.dropWrapper.isOpen){b.dropWrapper.isOpen=true;a.ui.dropdownchecklist.gLastOpened=b;var c=b.options;if(c.positionHow==null||c.positionHow=="absolute"){b.dropWrapper.css({position:"absolute",top:b.controlWrapper.position().top+b.controlWrapper.outerHeight()+"px",left:b.controlWrapper.position().left+"px"})}else if(c.positionHow=="relative"){b.dropWrapper.css({position:"relative",top:"0px",left:"0px"})}var e=0;if(c.zIndex==null){var f=b.controlWrapper.parents().map(function(){var b=a(this).css("z-index");return isNaN(b)?0:b}).get();var g=Math.max.apply(Math,f);if(g>=0)e=g+1}else{e=parseInt(c.zIndex)}if(e>0){b.dropWrapper.css({"z-index":e})}var h=b.controlSelector;h.addClass("ui-state-active");h.removeClass("ui-state-hover");var i=b.controlWrapper.find(".ui-icon");if(i.length>0){i.removeClass(c.icon.toOpen!=null?c.icon.toOpen:"ui-icon-triangle-1-e");i.addClass(c.icon.toClose!=null?c.icon.toClose:"ui-icon-triangle-1-s")}a(document).bind("click",function(a){d(b)});var j=b.dropWrapper.find("input.active");j.prop("disabled",false);var k=j.get(0);if(k!=null){k.focus()}}};if(b){d(a.ui.dropdownchecklist.gLastOpened);e(c)}else{d(c)}},_setSize:function(b){var c=this.options,d=this.dropWrapper,e=this.controlWrapper;var f=b.width;if(c.width!=null){f=parseInt(c.width)}else if(c.minWidth!=null){var g=parseInt(c.minWidth);if(f<g){f=g}}var h=this.controlSelector;h.css({width:f+"px"});var i=h.find(".ui-dropdownchecklist-text");var j=h.find(".ui-icon");if(j!=null){f-=j.outerWidth()+4;i.css({width:f+"px"})}f=e.outerWidth();var k=c.maxDropHeight!=null?parseInt(c.maxDropHeight):-1;var l=k>0&&b.height>k?k:b.height;var m=b.width<f?f:b.width;if(m<=2)m="auto";if(l<=2){l=k}a(d).css({height:l+"px",width:m+"px"});d.find(".ui-dropdownchecklist-dropcontainer").css({height:l+"px"})},_init:function(){var b=this,c=this.options;if(a.ui.dropdownchecklist.gIDCounter==null){a.ui.dropdownchecklist.gIDCounter=1}b.blurringItem=null;var d=b.element;b.initialDisplay=d.css("display");d.css("display","none");b.initialMultiple=d.prop("multiple");b.isMultiple=b.initialMultiple;if(c.forceMultiple!=null){b.isMultiple=c.forceMultiple}d.prop("multiple",true);b.sourceSelect=d;var e=b._appendControl();b.controlWrapper=e;b.controlSelector=e.find(".ui-dropdownchecklist-selector");var f=b._appendDropContainer(e);b.dropWrapper=f;var g=b._appendItems();b._updateControlText(e,f,d);b._setSize(g);if(c.firstItemChecksAll){b._syncSelected(null)}if(c.bgiframe&&typeof b.dropWrapper.bgiframe=="function"){b.dropWrapper.bgiframe()}b.sourceSelect.change(function(a,c){if(c!="ddcl_internal"){b._sourceSelectChangeHandler(a)}})},_refreshOption:function(a,b,c){var d=a.parent();if(b){a.prop("disabled",true);a.removeClass("active");a.addClass("inactive");d.addClass("ui-state-disabled")}else{a.prop("disabled",false);a.removeClass("inactive");a.addClass("active");d.removeClass("ui-state-disabled")}a.prop("checked",c)},_refreshGroup:function(a,b){if(b){a.addClass("ui-state-disabled")}else{a.removeClass("ui-state-disabled")}},close:function(){this._toggleDropContainer(false)},refresh:function(){var b=this,c=this.sourceSelect,d=this.dropWrapper;var e=d.find("input");var f=d.find(".ui-dropdownchecklist-group");var g=0;var h=0;c.children().each(function(c){var d=a(this);var i=d.prop("disabled");if(d.is("option")){var j=d.prop("selected");var k=a(e[h]);b._refreshOption(k,i,j);h+=1}else if(d.is("optgroup")){var l=d.attr("label");if(l!=""){var m=a(f[g]);b._refreshGroup(m,i);g+=1}d.children("option").each(function(){var c=a(this);var d=i||c.prop("disabled");var f=c.prop("selected");var g=a(e[h]);b._refreshOption(g,d,f);h+=1})}});b._syncSelected(null)},enable:function(){this.controlSelector.removeClass("ui-state-disabled");this.disabled=false},disable:function(){this.controlSelector.addClass("ui-state-disabled");this.disabled=true},destroy:function(){a.Widget.prototype.destroy.apply(this,arguments);this.sourceSelect.css("display",this.initialDisplay);this.sourceSelect.prop("multiple",this.initialMultiple);this.controlWrapper.unbind().remove();this.dropWrapper.remove()}});a.extend(a.ui.dropdownchecklist,{defaults:{width:null,maxDropHeight:null,firstItemChecksAll:false,closeRadioOnClick:false,minWidth:50,positionHow:"absolute",bgiframe:false,explicitClose:null}})})(jQuery)