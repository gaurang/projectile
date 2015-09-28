/*
 * jQuery MultiSelect Plugin 0.4.1
 * Copyright (c) 2010 Eric Hynds
 *
 * http://www.erichynds.com/jquery/jquery-multiselect-plugin-with-themeroller-support/
 * Inspired by Cory S.N. LaViska's implementation, A Beautiful Site (http://abeautifulsite.net/) 2009
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
*/
(function($){
	
	$.fn.extend({
		multiSelect: function(opts){
			opts = $.extend({}, $.fn.multiSelect.defaults, opts);

			return this.each(function(){
				new MultiSelect(this, opts);
			});
		}
	});
	
	var MultiSelect = function(select,o){
		var $select = $original = $(select), $options, $labels, html = [], optgroups = [];
		
		html.push('<a class="ui-multiselect ui-widget ui-state-default ui-corner-all"><input readonly="readonly" type="text" class="ui-state-default" value="'+o.noneSelected+'" /><span class="ui-icon ui-icon-triangle-1-s"></span></a>');
		html.push('<div class="ui-multiselect-options' + (o.shadow ? ' ui-multiselect-shadow' : '') + ' ui-widget ui-widget-content ui-corner-bl ui-corner-br ui-corner-tr">');
	
		if(o.showHeader){
			html.push('<div class="ui-widget-header ui-helper-clearfix ui-corner-all ui-multiselect-header">');
			html.push('<ul class="ui-helper-reset">');
			html.push('<li><a class="ui-multiselect-all" href=""><span class="ui-icon ui-icon-check"></span>' + o.checkAllText + '</a></li>');
			html.push('<li><a class="ui-multiselect-none" href=""><span class="ui-icon ui-icon-closethick"></span>' + o.unCheckAllText + '</a></li>');
			html.push('<li class="ui-multiselect-close"><a href="" class="ui-multiselect-close ui-icon ui-icon-circle-close"></a></li>');
			html.push('</ul>');
			html.push('</div>');
		};
		
		// build options
		html.push('<ul class="ui-multiselect-checkboxes ui-helper-reset">');
		$select.find('option').each(function(){
			var $this = $(this), title = $this.html(), value = $this.val(), len = value.length, $parent = $this.parent(), hasOptGroup = $parent.is('optgroup'), isDisabled = $this.is(':disabled'), labelClasses = ['ui-corner-all'], liClasses = [];
			
			if(hasOptGroup){
				var label = $parent.attr('label');
				
				if($.inArray(label, optgroups) === -1){
					html.push('<li class="ui-multiselect-optgroup-label"><a href="#">' + label + '</a></li>');
					optgroups.push(label);
				};
			};
			
			if(len > 0){
				if(isDisabled){
					labelClasses.push('ui-state-disabled');
					liClasses.push('ui-multiselect-disabled');
				};
				
				html.push('<li class="' + liClasses.join(' ') + '">');
				html.push('<label class="' + labelClasses.join(' ') + '"><input type="checkbox" name="' + $select.attr('name') + '" value="' + value + '" title="' + title + '"');
				if($this.is(':selected')){
					html.push(' checked="checked"');
				};
				if(isDisabled){
					html.push(' disabled="disabled"');
				};
				html.push(' />' + title + '</label></li>');
			};
		});
		html.push('</ul></div>');
		
		// cache queries
		$select  = $select.after( html.join('') ).next('a.ui-multiselect');
		$options = $select.next('div.ui-multiselect-options');
		$header  = $options.find('div.ui-multiselect-header');
		$labels  = $options.find('label').not('.ui-state-disabled');
		
		// calculate widths
		var iconWidth = $select.find('span.ui-icon').outerWidth(), inputWidth = $original.outerWidth(), totalWidth = inputWidth+iconWidth;
		if( /\d/.test(o.minWidth) && totalWidth < o.minWidth){
			inputWidth = o.minWidth-iconWidth;
			totalWidth = o.minWidth;
		};
		
		// set widths
		$select.width(totalWidth + 'px').find('input').width(inputWidth + 'px');
		
		// build header links
		if(o.showHeader){
			$header.find('a').click(function(e){
				var $target = $(e.target);
			
				// close link
				if($target.hasClass('ui-multiselect-close')){
					$options.trigger('close');
			
				// check all / uncheck all
				} else {
					var checkAll = $target.hasClass('ui-multiselect-all');
					$options.trigger('toggleChecked', [(checkAll ? true : false)]);
					o[ checkAll ? 'onCheckAll' : 'onUncheckAll']['call'](this);
				};
			
				e.preventDefault();
			});
		};
		
		// the select box events
		$select.bind({
			click: function(){
				$options.trigger('toggle');
			},
			keypress: function(e){
				switch(e.keyCode){
					case 27: // esc
					case 38: // up
						$options.trigger('close');
						break;
					case 40: // down
					case 0: // space
						$options.trigger('toggle');
						break;
				};
			},
			mouseenter: function(){
				$(this).addClass('ui-state-hover');
			},
			mouseleave: function(){
				$(this).removeClass('ui-state-hover');
			},
			focus: function(){
				$(this).addClass('ui-state-focus');
			},
			blur: function(){
				$(this).removeClass('ui-state-focus');
			}
		});
		
		// bind custom events to the options div
		$options.bind({
			'close': function(e, others){
				others = others || false;
			
				// hides all other options but the one clicked
				if(others === true){
					$('div.ui-multiselect-options')
					.filter(':visible')
					.fadeOut(o.fadeSpeed)
					.prev('a.ui-multiselect')
					.removeClass('ui-state-active')
					.trigger('mouseout');
					
				// hides the clicked options
				} else {
					$select.removeClass('ui-state-active').trigger('mouseout');
					$options.fadeOut(o.fadeSpeed);
				};
			},
			'open': function(e){
				var offset = $select.position(), $container = $options.find('ul:last'), timer, listHeight = 0, top, width;
				
				// calling select is active
				$select.addClass('ui-state-active');
				
				// hide all other options
				$options.trigger('close', [true]);
				
				// calculate positioning
				if(o.position === 'middle'){
					top = ( offset.top+($select.height()/2)-($options.outerHeight()/2) );
				} else if(o.position === 'top'){
					top = (offset.top-$options.outerHeight());
				} else {
					top = (offset.top+$select.outerHeight());
				};
				
				// calculate the width of the options menu
				width = $select.width()-parseInt($options.css('padding-left'))-parseInt($options.css('padding-right'));
				
				// select the first option
				$labels.filter('label:first').trigger('mouseenter').trigger('focus');
				
				// show the options div + position it
				$options.css({ 
					position: 'absolute',
					top: top+'px',
					left: offset.left+'px',
					width: width+'px'
				}).show();
				
				// set the scroll of the checkbox container
				$container.scrollTop(0);
				
				// set the height of the checkbox container
				if(o.maxHeight){
					$container.css('height', o.maxHeight );
				};
				
				o.onOpen.call($options[0]);
			},
			'toggle': function(){
				$options.trigger( $(this).is(':hidden') ? 'open' : 'close' );
			},
			'traverse': function(e, start, keycode){
				var $start = $(start), 
					moveToLast = (keycode === 38 || keycode === 37) ? true : false,
					
					// select the first li that isn't an optgroup label / disabled
					$next = $start.parent()[moveToLast ? 'prevAll' : 'nextAll']('li:not(.ui-multiselect-disabled, .ui-multiselect-optgroup-label)')[ moveToLast ? 'last' : 'first']();

				// if at the first/last element
				if(!$next.length){
					var $container = $options.find("ul:last");
					
					// move to the first/last
					$options.find('label')[ moveToLast ? 'last' : 'first' ]().trigger('mouseover');
					
					// set scroll position
					$container.scrollTop( moveToLast ? $container.height() : 0 );
					
				} else {
					$next.find('label').trigger('mouseenter');
				};
			},
			'toggleChecked': function(e, flag, group){
				var $inputs = (group && group.length) ? group : $labels.find('input');
				$inputs.not(':disabled').attr('checked', (flag ? 'checked' : '')); 
				updateSelected();
			}
		})
		.find('li.ui-multiselect-optgroup-label a')
		.click(function(e){
			// optgroup label toggle support
			var $checkboxes = $(this).parent().nextUntil('li.ui-multiselect-optgroup-label').find('input'),
				total = $checkboxes.length,
				checked = $checkboxes.filter(':checked').length;
			
			$options.trigger('toggleChecked', [ (checked === total) ? false : true, $checkboxes]);
			o.onOptgroupToggle.call(this, $checkboxes.get() );
			e.preventDefault();
		});
		
		// labels/checkbox events
		$labels.bind({
			mouseenter: function(){
				$labels.removeClass('ui-state-hover');
				$(this).addClass('ui-state-hover').find('input').focus();
			},
			click: function(e,caller){
				// if the label was clicked, trigger the click event on the checkbox.  IE6 fix
				e.preventDefault();
				$(this).find('input').trigger('click', [true]); 
			},
			keyup: function(e){
				switch(e.keyCode){
					case 27: // esc
						$options.trigger('close');
						break;
			
					case 38: // up
					case 40: // down
					case 37: // left
					case 39: // right
						$options.trigger('traverse', [this, e.keyCode]);
						break;
				
					case 13: // enter
						e.preventDefault();
						$(this).click();
						break;
				};
			}
		})
		.find('input')
		.bind('click', function(e, label){
			var $this = $(this);
			label = label || false;
			
			// stop this click from bubbling up to the label
			e.stopPropagation();

			// if the click originated from the label, stop the click event and manually toggle the checked state
			if(label){
				e.preventDefault();
				$this.attr('checked', $this.is(':checked') ? '' : 'checked');
			};
		
			o.onCheck.call( $this[0] );
			updateSelected();
		});

		// apply bgiframe if available
		if($.fn.bgiframe){
			$options.bgiframe();
		};
		
		// remove the original input element
		$original.remove();

		// update the number of selected elements when the page initially loads, and use that as the defaultValue.  necessary for form resets when options are pre-selected.
		$select.find("input")[0].defaultValue = updateSelected();
		
		function updateSelected(){
			var $inputs = $labels.find('input'),
				$checked = $inputs.filter(':checked'),
				value = '',
				numChecked = $checked.length;
			
			if(numChecked === 0){
				value = o.noneSelected;
			} else {
			
				// if selectedList is enabled, and the number of checked items is less/equal to the max specified
				if(o.selectedList && $checked.length <= o.selectedList){
					$checked.each(function(){
						var text = $(this).parent().text();
						value = (value.length) ? (value += ', ' + text) : text;
					});
				} else {
					value = o.selectedText.replace('#', numChecked).replace('#', $inputs.length);
				};
			};
			
			$select.find('input').val(value).attr('title', value);
			return value;
		};
	};
	
	// close each select when clicking on any other element/anywhere else on the page
	$(document).bind("click", function(e){
		var $target = $(e.target);

		if(!$target.closest('div.ui-multiselect-options').length && !$target.parent().hasClass('ui-multiselect')){
			$('div.ui-multiselect-options').trigger('close', [true]);
		};
	});
	
	// default options
	$.fn.multiSelect.defaults = {
		showHeader: true,
		maxHeight: 175, /* max height of the checkbox container (scroll) in pixels */
		minWidth: 100, /* min width of the entire widget in pixels. setting to 'auto' will disable */
		checkAllText: 'Check all',
		unCheckAllText: 'Uncheck all',
		noneSelected: 'Select options',
		selectedList: false, /* to enable, enter a number (gt 0) denoting the maximum number of list items to display before switching to selectedText  */
		selectedText: '# selected',
		position: 'bottom', /* top|middle|bottom */
		shadow: false,
		fadeSpeed: 200,
		onCheck: function(){}, /* when an individual checkbox is clicked */
		onOpen: function(){}, /* when the select menu is opened */
		onCheckAll: function(){}, /* when the check all link is clicked */
		onUncheckAll: function(){}, /* when the uncheck all link is clicked */
		onOptgroupToggle: function(){} /* when the optgroup heading is clicked */
	};

})(jQuery);

