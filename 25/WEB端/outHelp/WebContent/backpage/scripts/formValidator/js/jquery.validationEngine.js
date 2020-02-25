/*
 * Inline Form Validation Engine 1.6.4, jQuery plugin
 * 
 * Copyright(c) 2009, Cedric Dugas
 * http://www.position-relative.net
 *	
 * Form validation engine allowing custom regex rules to be added.
 * Thanks to Francois Duquette and Teddy Limousin 
 * and everyone helping me find bugs on the forum
 * Licenced under the MIT Licence
 */
 
 /*
  * bluespring 修改1.6.4.1
  * 提供选项定制是否关闭提示框箭头 showArray
  * 提供选项定制提示框是否在鼠标放上去的时刻出现还是一直出现 showOnMouseOver
  * 提供选项定制input 效验错误样式
  */
 
(function($) {
	
	$.fn.validationEngine = function(settings) {
		
	if($.validationEngineLanguage){				// IS THERE A LANGUAGE LOCALISATION ?
		allRules = $.validationEngineLanguage.allRules;
	}else{
		$.validationEngine.debug("Validation engine rules are not loaded check your external file");
	}
 	settings = jQuery.extend({
		allrules:allRules,
		validationEventTriggers:"focusout",					
		inlineValidation: true,	
		returnIsValid:false,
		liveEvent:true,
		unbindEngine:true,
		ajaxSubmit: false,
		scroll:true,
		promptPosition: "topRight",	// OPENNING BOX POSITION, IMPLEMENTED: topLeft, topRight, bottomLeft, centerRight, bottomRight
		success : false,
		beforeSuccess :  function() {},
		failure : function() {},
		
		showArray:true, //bluespring修改 添加是否显示提示箭头，默认不显示
		showOnMouseOver:true, //bluespring修改 提示信息是否在鼠标移上去时才显示，默认为是
		errorClass:'error-field' //bluepsirng修改 提供效验出错时的field样式定制,必须在showOnMouseOver为true时才有用
		
	}, settings);	
	$.validationEngine.settings = settings;
	$.validationEngine.ajaxValidArray = new Array();	// ARRAY FOR AJAX: VALIDATION MEMORY 
	
	if(settings.inlineValidation == true){ 		// Validating Inline ?
		if(!settings.returnIsValid){					// NEEDED FOR THE SETTING returnIsValid
			allowReturnIsvalid = false;
			if(settings.liveEvent){						// LIVE event, vast performance improvement over BIND
				$(this).find("[class*=validate][type!=checkbox]").live(settings.validationEventTriggers, function(caller){ _inlinEvent(this);})
				$(this).find("[class*=validate][type=checkbox]").live("click", function(caller){ _inlinEvent(this); })
			}else{
				$(this).find("[class*=validate]").not("[type=checkbox]").bind(settings.validationEventTriggers, function(caller){ _inlinEvent(this); })
				$(this).find("[class*=validate][type=checkbox]").bind("click", function(caller){ _inlinEvent(this); })
			}
			firstvalid = false;
		}
			function _inlinEvent(caller){
				$.validationEngine.settings = settings;
				if($.validationEngine.intercept == false || !$.validationEngine.intercept){		// STOP INLINE VALIDATION THIS TIME ONLY
					$.validationEngine.onSubmitValid=false;
					$.validationEngine.loadValidation(caller); 
				}else{
					$.validationEngine.intercept = false;
				}
			}
	}
	if (settings.returnIsValid){		// Do validation and return true or false, it bypass everything;
		if ($.validationEngine.submitValidation(this,settings)){
			return false;
		}else{
			return true;
		}
	}
	$(this).bind("submit", function(caller){   // ON FORM SUBMIT, CONTROL AJAX FUNCTION IF SPECIFIED ON DOCUMENT READY
		$.validationEngine.onSubmitValid = true;
		$.validationEngine.settings = settings;
		if($.validationEngine.submitValidation(this,settings) == false){
			if($.validationEngine.submitForm(this,settings) == true) {return false;}
		}else{
			settings.failure && settings.failure(); 
			return false;
		}		
	})
	//bluespring修改 当设置为鼠标over才显示提示框模式时，去掉点击删除功能
	
	if(!$.validationEngine.settings.showOnMouseOver)$(".formError").live("click",function(){	 // REMOVE BOX ON CLICK
		$(this).fadeOut(150,function(){
			$(this).remove();
		}) 
	})
};	
$.validationEngine = {
	defaultSetting : function(caller) {		// NOT GENERALLY USED, NEEDED FOR THE API, DO NOT TOUCH
		if($.validationEngineLanguage){				
			allRules = $.validationEngineLanguage.allRules;
		}else{
			$.validationEngine.debug("Validation engine rules are not loaded check your external file");
		}	
		settings = {
			allrules:allRules,
			validationEventTriggers:"blur",					
			inlineValidation: true,	
			returnIsValid:false,
			scroll:true,
			unbindEngine:true,
			ajaxSubmit: false,
			promptPosition: "topRight",	// OPENNING BOX POSITION, IMPLEMENTED: topLeft, topRight, bottomLeft, centerRight, bottomRight
			success : false,
			failure : function() {}
		}	
		$.validationEngine.settings = settings;
	},
	loadValidation : function(caller) {		// GET VALIDATIONS TO BE EXECUTED
		if(!$.validationEngine.settings){
			$.validationEngine.defaultSetting()
		}
		rulesParsing = $(caller).attr('class');
		rulesRegExp = /\[(.*)\]/;
		getRules = rulesRegExp.exec(rulesParsing);
		str = getRules[1];
		pattern = /\[|,|\]/;
		result= str.split(pattern);	
		var validateCalll = $.validationEngine.validateCall(caller,result)
		return validateCalll;
	},
	validateCall : function(caller,rules) {	// EXECUTE VALIDATION REQUIRED BY THE USER FOR THIS FIELD
		var promptText =""	
		
		if(!$(caller).attr("id")) { $.validationEngine.debug("This field have no ID attribut( name & class displayed): "+$(caller).attr("name")+" "+$(caller).attr("class")) }

		caller = caller;
		ajaxValidate = false;
		var callerName = $(caller).attr("name");
		$.validationEngine.isError = false;
		//修改   将默认打开的提示箭头设置为选项值
		$.validationEngine.showTriangle = $.validationEngine.settings.showArray;
		callerType = $(caller).attr("type");

		for (i=0; i<rules.length;i++){
			switch (rules[i]){
			case "optional": 
				if(!$(caller).val()){
					$.validationEngine.closePrompt(caller);
					return $.validationEngine.isError;
				}
			break;
			case "required": 
				_required(caller,rules);
			break;
			case "custom": 
				 _customRegex(caller,rules,i);
			break;
			case "exemptString": 
				 _exemptString(caller,rules,i);
			break;
			case "ajax": 
				if(!$.validationEngine.onSubmitValid){
					_ajax(caller,rules,i);	
				};
			break;
			case "length": 
				 _length(caller,rules,i);
			break;
			case "lengthSize": 
				 _lengthSize(caller,rules,i);
			break;
			case "lengthMax": 
				 _lengthMax(caller,rules,i);
			break;
			case "idCard": 
				 _idCard(caller,rules,i);
			break;
			//bluespring添加 效验取值区间
			case "region": 
				 _region(caller,rules,i);
			break;
			case "maxCheckbox": 
				_maxCheckbox(caller,rules,i);
			 	groupname = $(caller).attr("name");
			 	caller = $("input[name='"+groupname+"']");
			break;
			case "minCheckbox": 
				_minCheckbox(caller,rules,i);
				groupname = $(caller).attr("name");
			 	caller = $("input[name='"+groupname+"']");
			break;
			case "confirm": 
				 _confirm(caller,rules,i);
			break;
			case "funcCall": 
		     	_funcCall(caller,rules,i);
			break;
			default :;
			};
		};
		radioHack();
		if ($.validationEngine.isError == true){
			linkTofield = $.validationEngine.linkTofield(caller);
			
			($("div."+linkTofield).size() ==0) ? $.validationEngine.buildPrompt(caller,promptText,"error")	: $.validationEngine.updatePromptText(caller,promptText);
		}else{ $.validationEngine.closePrompt(caller);}			
		/* UNFORTUNATE RADIO AND CHECKBOX GROUP HACKS */
		/* As my validation is looping input with id's we need a hack for my validation to understand to group these inputs */
		function radioHack(){
	      if($("input[name='"+callerName+"']").size()> 1 && (callerType == "radio" || callerType == "checkbox")) {        // Hack for radio/checkbox group button, the validation go the first radio/checkbox of the group
	          caller = $("input[name='"+callerName+"'][type!=hidden]:first");     
	          $.validationEngine.showTriangle = false;
	      }      
	    }
		/* VALIDATION FUNCTIONS */
		function _required(caller,rules){   // VALIDATE BLANK FIELD
			callerType = $(caller).attr("type");
			if (callerType == "text" || callerType == "password" || callerType == "textarea"){
								
				if(!$(caller).val()){
					$.validationEngine.isError = true;
					promptText += $.validationEngine.settings.allrules[rules[i]].alertText+"<br />";
				}	
			}	
			if (callerType == "radio" || callerType == "checkbox" ){
				callerName = $(caller).attr("name");
		
				if($("input[name='"+callerName+"']:checked").size() == 0) {
					$.validationEngine.isError = true;
					if($("input[name='"+callerName+"']").size() ==1) {
						promptText += $.validationEngine.settings.allrules[rules[i]].alertTextCheckboxe+"<br />"; 
					}else{
						 promptText += $.validationEngine.settings.allrules[rules[i]].alertTextCheckboxMultiple+"<br />";
					}	
				}
			}	
			if (callerType == "select-one") { // added by paul@kinetek.net for select boxes, Thank you		
				if(!$(caller).val()) {
					$.validationEngine.isError = true;
					promptText += $.validationEngine.settings.allrules[rules[i]].alertText+"<br />";
				}
			}
			if (callerType == "select-multiple") { // added by paul@kinetek.net for select boxes, Thank you	
				if(!$(caller).find("option:selected").val()) {
					$.validationEngine.isError = true;
					promptText += $.validationEngine.settings.allrules[rules[i]].alertText+"<br />";
				}
			}
		}
		function _customRegex(caller,rules,position){		 // VALIDATE REGEX RULES
			customRule = rules[position+1];
			pattern = eval($.validationEngine.settings.allrules[customRule].regex);
			
			if(!pattern.test($(caller).attr('value'))){
				$.validationEngine.isError = true;
				promptText += $.validationEngine.settings.allrules[customRule].alertText+"<br />";
			}
		}
		function _exemptString(caller,rules,position){		 // VALIDATE REGEX RULES
			customString = rules[position+1];
			if(customString == $(caller).attr('value')){
				$.validationEngine.isError = true;
				promptText += $.validationEngine.settings.allrules['required'].alertText+"<br />";
			}
		}
		
		function _funcCall(caller,rules,position){  		// VALIDATE CUSTOM FUNCTIONS OUTSIDE OF THE ENGINE SCOPE
			customRule = rules[position+1];
			funce = $.validationEngine.settings.allrules[customRule].nname;
			
			var fn = window[funce];
			if (typeof(fn) === 'function'){
				var fn_result = fn();
				$.validationEngine.isError = fn_result;
				promptText += $.validationEngine.settings.allrules[customRule].alertText+"<br />";
			}
		}
		function _ajax(caller,rules,position){				 // VALIDATE AJAX RULES
			
			customAjaxRule = rules[position+1];
			postfile = $.validationEngine.settings.allrules[customAjaxRule].file;
			fieldValue = $(caller).val();
			ajaxCaller = caller;
			fieldId = $(caller).attr("id");
			ajaxValidate = true;
			ajaxisError = $.validationEngine.isError;
			
			if(!$.validationEngine.settings.allrules[customAjaxRule].extraData){
				extraData = $.validationEngine.settings.allrules[customAjaxRule].extraData;
			}else{
				extraData = "";
			}
			/* AJAX VALIDATION HAS ITS OWN UPDATE AND BUILD UNLIKE OTHER RULES */	
			if(!ajaxisError){
				$.ajax({
				   	type: "POST",
				   	url: postfile,
				   	async: true,
				   	data: "validateValue="+fieldValue+"&validateId="+fieldId+"&validateError="+customAjaxRule+extraData,
				   	beforeSend: function(){		// BUILD A LOADING PROMPT IF LOAD TEXT EXIST		   			
				   		if($.validationEngine.settings.allrules[customAjaxRule].alertTextLoad){
				   		
				   			if(!$("div."+fieldId+"formError")[0]){				   				
	 			 				return $.validationEngine.buildPrompt(ajaxCaller,$.validationEngine.settings.allrules[customAjaxRule].alertTextLoad,"load");
	 			 			}else{
	 			 				$.validationEngine.updatePromptText(ajaxCaller,$.validationEngine.settings.allrules[customAjaxRule].alertTextLoad,"load");
	 			 			}
			   			}
			  	 	},
			  	 	error: function(data,transport){ $.validationEngine.debug("error in the ajax: "+data.status+" "+transport) },
					success: function(data){					// GET SUCCESS DATA RETURN JSON
						data = eval( "("+data+")");				// GET JSON DATA FROM PHP AND PARSE IT
						ajaxisError = data.jsonValidateReturn[2];
						customAjaxRule = data.jsonValidateReturn[1];
						ajaxCaller = $("#"+data.jsonValidateReturn[0])[0];
						fieldId = ajaxCaller;
						ajaxErrorLength = $.validationEngine.ajaxValidArray.length;
						existInarray = false;
						
			 			 if(ajaxisError == "false"){			// DATA FALSE UPDATE PROMPT WITH ERROR;
			 			 	
			 			 	_checkInArray(false)				// Check if ajax validation alreay used on this field
			 			 	
			 			 	if(!existInarray){		 			// Add ajax error to stop submit		 		
				 			 	$.validationEngine.ajaxValidArray[ajaxErrorLength] =  new Array(2);
				 			 	$.validationEngine.ajaxValidArray[ajaxErrorLength][0] = fieldId;
				 			 	$.validationEngine.ajaxValidArray[ajaxErrorLength][1] = false;
				 			 	existInarray = false;
			 			 	}
				
			 			 	$.validationEngine.ajaxValid = false;
							promptText += $.validationEngine.settings.allrules[customAjaxRule].alertText+"<br />";
							$.validationEngine.updatePromptText(ajaxCaller,promptText,"",true);				
						 }else{	 
						 	_checkInArray(true);
						 	$.validationEngine.ajaxValid = true; 			
						 	if(!customAjaxRule)	{$.validationEngine.debug("wrong ajax response, are you on a server or in xampp? if not delete de ajax[ajaxUser] validating rule from your form ")}		   
	 			 			if($.validationEngine.settings.allrules[customAjaxRule].alertTextOk){	// NO OK TEXT MEAN CLOSE PROMPT	 			
	 			 				 				$.validationEngine.updatePromptText(ajaxCaller,$.validationEngine.settings.allrules[customAjaxRule].alertTextOk,"pass",true);
 			 				}else{
				 			 	ajaxValidate = false;		 	
				 			 	$.validationEngine.closePrompt(ajaxCaller);
 			 				}		
			 			 }
			 			function  _checkInArray(validate){
			 				for(x=0;x<ajaxErrorLength;x++){
			 			 		if($.validationEngine.ajaxValidArray[x][0] == fieldId){
			 			 			$.validationEngine.ajaxValidArray[x][1] = validate;
			 			 			existInarray = true;
			 			 		
			 			 		}
			 			 	}
			 			}
			 		}				
				});
			}
		}
		function _confirm(caller,rules,position){		 // VALIDATE FIELD MATCH
			confirmField = rules[position+1];
			
			if($(caller).attr('value') != $("#"+confirmField).attr('value')){
				$.validationEngine.isError = true;
				promptText += $.validationEngine.settings.allrules["confirm"].alertText+"<br />";
			}
		}
		function _length(caller,rules,position){    	  // VALIDATE LENGTH
		
			startLength = eval(rules[position+1]);
			endLength = eval(rules[position+2]);
			feildLength = $(caller).attr('value').length;

			if(feildLength<startLength || feildLength>endLength){
				$.validationEngine.isError = true;
				promptText += $.validationEngine.settings.allrules["length"].alertText+startLength+$.validationEngine.settings.allrules["length"].alertText2+endLength+$.validationEngine.settings.allrules["length"].alertText3+"<br />"
			}
		}
		function _lengthSize(caller,rules,position){    	  // VALIDATE LENGTH
			startLength = eval(rules[position+1]);
			feildLength = $(caller).attr('value').length;
			if(feildLength!=startLength){
				$.validationEngine.isError = true;
				promptText += $.validationEngine.settings.allrules["lengthSize"].alertText+startLength+"<br />"
			}
		}
		
		function _lengthMax(caller,rules,position){    	  // VALIDATE LENGTH
			
			startLength = eval(rules[position+1]);
			endLength = eval(rules[position+2]);
			feildLength = $(caller).attr('value').length+7;

			if(feildLength<startLength || feildLength>endLength){
				$.validationEngine.isError = true;
				promptText += $.validationEngine.settings.allrules["lengthMax"].alertText+startLength+$.validationEngine.settings.allrules["length"].alertText2+endLength+$.validationEngine.settings.allrules["length"].alertText3+"<br />"
			}else{
				
				var id = $(caller).attr('id');
				
				$("#"+id+"Span").html(feildLength+"/"+Math.ceil(feildLength/65));
			}
		}
		
		function _idCard(caller,rules,position){  
			if($("#certType").val()!="身份证"){
				
			}else{
				var inputValue =  $(caller).attr('value');
				var testFlage = isIdCardNo(inputValue.toLowerCase());
				if(!testFlage){
					$.validationEngine.isError = true;
					promptText += $.validationEngine.settings.allrules["idCard"].alertText+"<br />"
				}
				
			}
			
			/**
			if(18 != inputValue.length){
			}else{
				var number = inputValue.toLowerCase();
				var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
				var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
				if(re == null || a.indexOf(re[1]) < 0){
				}else{
					if(re[2].length == 9){
						number = number.substr(0, 6) + '19' + number.substr(6);
						d = ['19' + re[4], re[5], re[6]].join('-');				
					}else{
						d = [re[9], re[10], re[11]].join('-');
					}
					var r = d.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
					var d= new Date(r[1], r[3]-1, r[4]);
					var isDateFlage = d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4];
					if(!isDateFlage){			
					}else{
						for(var i = 0;i < 17; i++) sum += number.charAt(i) * w[i];
						testFlage=(re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11))
					}
				}
			}**/
			
		}
		/** 
		 * 身份证号码验证 
		 * 
		 */  
		function isIdCardNo(num) {  
		 
		var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);  
		 var parityBit=new Array("1","0","x","9","8","7","6","5","4","3","2");  
		 var varArray = new Array();  
		 var intValue;  
		 var lngProduct = 0;  
		 var intCheckDigit;  
		 var intStrLen = num.length;  
		 var idNumber = num;  
		   // initialize  
		     if ((intStrLen != 15) && (intStrLen != 18)) {  
		         return false;  
		     }  
		     // check and set value  
		     for(i=0;i<intStrLen;i++) {  
		         varArray[i] = idNumber.charAt(i);  
		         if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {  
		             return false;  
		         } else if (i < 17) {  
		             varArray[i] = varArray[i] * factorArr[i];  
		         }  
		     }  
		       
		     if (intStrLen == 18) {  
		         //check date  
		         var date8 = idNumber.substring(6,14);  
		         if (isDate8(date8) == false) {  
		            return false;  
		         }  
		         // calculate the sum of the products  
		         for(i=0;i<17;i++) {  
		             lngProduct = lngProduct + varArray[i];  
		         }  
		         // calculate the check digit  
		         intCheckDigit = parityBit[lngProduct % 11];  
		         // check last digit  
		         if (varArray[17] != intCheckDigit) {  
		             return false;  
		         }  
		     }  
		     else{        //length is 15  
		         //check date  
		         var date6 = idNumber.substring(6,12);  
		         if (isDate6(date6) == false) {  
		  
		             return false;  
		         }  
		     }  
		     return true;  
		       
		 }  
		 /** 
		 * 判断是否为“YYYYMM”式的时期 
		 * 
		 */  
		function isDate6(sDate) {  
		   if(!/^[0-9]{6}$/.test(sDate)) {  
		      return false;  
		   }  
		   var year, month, day;  
		   year = sDate.substring(0, 4);  
		   month = sDate.substring(4, 6);  
		   if (year < 1700 || year > 2500) return false  
		   if (month < 1 || month > 12) return false  
		   return true  
		}  
		/** 
		 * 判断是否为“YYYYMMDD”式的时期 
		 * 
		 */  
		function isDate8(sDate) {  
		   if(!/^[0-9]{8}$/.test(sDate)) {  
		      return false;  
		   }  
		   var year, month, day;  
		   year = sDate.substring(0, 4);  
		   month = sDate.substring(4, 6);  
		   day = sDate.substring(6, 8);  
		   var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]  
		   if (year < 1700 || year > 2500) return false  
		   if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;  
		   if (month < 1 || month > 12) return false  
		   if (day < 1 || day > iaMonthDays[month - 1]) return false  
		   return true  
		}  

		
		//bluespring添加 效验取值区间
		function _region(caller,rules,position){
			var min = eval(rules[position+1]);
			var max = eval(rules[position+2]);
			var value;
			try{
				value = parseFloat($(caller).attr('value'))
			}catch(e){
				promptText += $.validationEngine.settings.allrules["region"].alertText+min+$.validationEngine.settings.allrules["region"].alertText2+max+$.validationEngine.settings.allrules["region"].alertText3+"<br />";
				$.validationEngine.isError = true;
				return;
			}
			
			if(value<min||value>max){
				promptText += $.validationEngine.settings.allrules["region"].alertText+min+$.validationEngine.settings.allrules["region"].alertText2+max+$.validationEngine.settings.allrules["region"].alertText3+"<br />";
				$.validationEngine.isError = true;
			}
			
		}
		
		function _maxCheckbox(caller,rules,position){  	  // VALIDATE CHECKBOX NUMBER
		
			nbCheck = eval(rules[position+1]);
			groupname = $(caller).attr("name");
			groupSize = $("input[name='"+groupname+"']:checked").size();
			if(groupSize > nbCheck){	
				$.validationEngine.showTriangle = false;
				$.validationEngine.isError = true;
				promptText += $.validationEngine.settings.allrules["maxCheckbox"].alertText+"<br />";
			}
		}
		function _minCheckbox(caller,rules,position){  	  // VALIDATE CHECKBOX NUMBER
		
			nbCheck = eval(rules[position+1]);
			groupname = $(caller).attr("name");
			groupSize = $("input[name='"+groupname+"']:checked").size();
			if(groupSize < nbCheck){	
			
				$.validationEngine.isError = true;
				$.validationEngine.showTriangle = false;
				promptText += $.validationEngine.settings.allrules["minCheckbox"].alertText+" "+nbCheck+" "+$.validationEngine.settings.allrules["minCheckbox"].alertText2+"<br />";
			}
		}
		return($.validationEngine.isError) ? $.validationEngine.isError : false;
	},
	submitForm : function(caller){
		if($.validationEngine.settings.ajaxSubmit){		
			if($.validationEngine.settings.ajaxSubmitExtraData){
				extraData = $.validationEngine.settings.ajaxSubmitExtraData;
			}else{
				extraData = "";
			}
			$.ajax({
			   	type: "POST",
			   	url: $.validationEngine.settings.ajaxSubmitFile,
			   	async: true,
			   	data: $(caller).serialize()+"&"+extraData,
			   	error: function(data,transport){ $.validationEngine.debug("error in the ajax: "+data.status+" "+transport) },
			   	success: function(data){
			   		if(data == "true"){			// EVERYTING IS FINE, SHOW SUCCESS MESSAGE
			   			$(caller).css("opacity",1)
			   			$(caller).animate({opacity: 0, height: 0}, function(){
			   				$(caller).css("display","none");
			   				$(caller).before("<div class='ajaxSubmit'>"+$.validationEngine.settings.ajaxSubmitMessage+"</div>");
			   				$.validationEngine.closePrompt(".formError",true); 	
			   				$(".ajaxSubmit").show("slow");
			   				if ($.validationEngine.settings.success){	// AJAX SUCCESS, STOP THE LOCATION UPDATE
								$.validationEngine.settings.success && $.validationEngine.settings.success(); 
								return false;
							}
			   			})
		   			}else{						// HOUSTON WE GOT A PROBLEM (SOMETING IS NOT VALIDATING)
			   			data = eval( "("+data+")");	
			   			if(!data.jsonValidateReturn){
			   				 $.validationEngine.debug("you are not going into the success fonction and jsonValidateReturn return nothing");
			   			}
			   			errorNumber = data.jsonValidateReturn.length	
			   			for(index=0; index<errorNumber; index++){	
			   				fieldId = data.jsonValidateReturn[index][0];
			   				promptError = data.jsonValidateReturn[index][1];
			   				type = data.jsonValidateReturn[index][2];
			   				$.validationEngine.buildPrompt(fieldId,promptError,type);
		   				}
	   				}
   				}
			})	
			return true;
		}
		// LOOK FOR BEFORE SUCCESS METHOD		
			if(!$.validationEngine.settings.beforeSuccess()){
				if ($.validationEngine.settings.success){	// AJAX SUCCESS, STOP THE LOCATION UPDATE
					if($.validationEngine.settings.unbindEngine){ $(caller).unbind("submit") }
					$.validationEngine.settings.success && $.validationEngine.settings.success(); 
					return true;
				}
			}else{
				return true;
			} 
		return false;
	},
	//bluespring修改 当鼠标over 及out时的提示显示方法
	showTip:function(event){
		event.data.stop();
		event.data.fadeTo('normal',1);
	},
	hideTip:function(event){
		event.data.stop(); 
		event.data.fadeTo('normal',0,function(){$(this).hide()});
	},
	buildPrompt : function(caller,promptText,type,ajaxed) {			// ERROR PROMPT CREATION AND DISPLAY WHEN AN ERROR OCCUR
		if(!$.validationEngine.settings){
			$.validationEngine.defaultSetting()
		}
		deleteItself = "." + $(caller).attr("id") + "formError"
	
		if($(deleteItself)[0]){
			$(deleteItself).get(0).validateField.unbind('mouseover',$.validationEngine.showTip).unbind('mouseout',$.validationEngine.hideTip);
			$(deleteItself).stop();
			$(deleteItself).remove();
		}
		var divFormError = document.createElement('div');
		var formErrorContent = document.createElement('div');
		linkTofield = $.validationEngine.linkTofield(caller)
		$(divFormError).addClass("formError")
		
		if(type == "pass"){ $(divFormError).addClass("greenPopup") }
		if(type == "load"){ $(divFormError).addClass("blackPopup") }
		if(ajaxed){ $(divFormError).addClass("ajaxed") }
		
		$(divFormError).addClass(linkTofield);
		$(formErrorContent).addClass("formErrorContent");
		
		$("body").append(divFormError);
		$(divFormError).append(formErrorContent);
			
		if($.validationEngine.showTriangle != false){		// NO TRIANGLE ON MAX CHECKBOX AND RADIO
			var arrow = document.createElement('div');
			$(arrow).addClass("formErrorArrow");
			$(divFormError).append(arrow);
			if($.validationEngine.settings.promptPosition == "bottomLeft" || $.validationEngine.settings.promptPosition == "bottomRight"){
			$(arrow).addClass("formErrorArrowBottom")
			$(arrow).html('<div class="line1"><!-- --></div><div class="line2"><!-- --></div><div class="line3"><!-- --></div><div class="line4"><!-- --></div><div class="line5"><!-- --></div><div class="line6"><!-- --></div><div class="line7"><!-- --></div><div class="line8"><!-- --></div><div class="line9"><!-- --></div><div class="line10"><!-- --></div>');
		}
			if($.validationEngine.settings.promptPosition == "topLeft" || $.validationEngine.settings.promptPosition == "topRight"){
				$(divFormError).append(arrow);
				$(arrow).html('<div class="line10"><!-- --></div><div class="line9"><!-- --></div><div class="line8"><!-- --></div><div class="line7"><!-- --></div><div class="line6"><!-- --></div><div class="line5"><!-- --></div><div class="line4"><!-- --></div><div class="line3"><!-- --></div><div class="line2"><!-- --></div><div class="line1"><!-- --></div>');
			}
		}
		$(formErrorContent).html(promptText)
	
		callerTopPosition = $(caller).offset().top;
		callerleftPosition = $(caller).offset().left;
		callerWidth =  $(caller).width();
		inputHeight = $(divFormError).height();
	
		/* POSITIONNING */
		if($.validationEngine.settings.promptPosition == "topRight"){callerleftPosition +=  callerWidth -30; callerTopPosition += -inputHeight -10; }
		if($.validationEngine.settings.promptPosition == "topLeft"){ callerTopPosition += -inputHeight -10; }
		
		if($.validationEngine.settings.promptPosition == "centerRight"){ callerleftPosition +=  callerWidth +13; }
		
		if($.validationEngine.settings.promptPosition == "bottomLeft"){
			callerHeight =  $(caller).height();
			callerleftPosition = callerleftPosition;
			callerTopPosition = callerTopPosition + callerHeight + 15;
		}
		if($.validationEngine.settings.promptPosition == "bottomRight"){
			callerHeight =  $(caller).height();
			callerleftPosition +=  callerWidth -30;
			callerTopPosition +=  callerHeight + 15;
		}
		$(divFormError).css({
			top:callerTopPosition,
			left:callerleftPosition,
			opacity:$.validationEngine.settings.showOnMouseOver?1:0,
			display:$.validationEngine.settings.showOnMouseOver?'none':''
		})
		//bluespring修改  鼠标over时才出现提示框,添加效验错误class
		if($.validationEngine.settings.showOnMouseOver){
			if($(caller).is(':checkbox,:radio')){
				$(divFormError).get(0).validateField=$(caller).parent();
			}else{
				$(divFormError).get(0).validateField=$(caller);
			}
			$(divFormError).get(0).validateField.addClass($.validationEngine.settings.errorClass);
			$(divFormError).get(0).validateField.bind('mouseover',$(divFormError),$.validationEngine.showTip).bind('mouseout',$(divFormError),$.validationEngine.hideTip);	
		}else{
			return $(divFormError).animate({"opacity":0.87},function(){ return true;});	
		}
		
	},
	updatePromptText : function(caller,promptText,type,ajaxed) {	// UPDATE TEXT ERROR IF AN ERROR IS ALREADY DISPLAYED
		linkTofield = $.validationEngine.linkTofield(caller);
		var updateThisPrompt =  "."+linkTofield;
		
		if(type == "pass") { $(updateThisPrompt).addClass("greenPopup") }else{ $(updateThisPrompt).removeClass("greenPopup")};
		if(type == "load") { $(updateThisPrompt).addClass("blackPopup") }else{ $(updateThisPrompt).removeClass("blackPopup")};
		if(ajaxed) { $(updateThisPrompt).addClass("ajaxed") }else{ $(updateThisPrompt).removeClass("ajaxed")};
	
		$(updateThisPrompt).find(".formErrorContent").html(promptText);
		callerTopPosition  = $(caller).offset().top;
		inputHeight = $(updateThisPrompt).height();
		
		if($.validationEngine.settings.promptPosition == "bottomLeft" || $.validationEngine.settings.promptPosition == "bottomRight"){
			callerHeight =  $(caller).height();
			callerTopPosition =  callerTopPosition + callerHeight + 15;
		}
		if($.validationEngine.settings.promptPosition == "centerRight"){  callerleftPosition +=  callerWidth +13;}
		if($.validationEngine.settings.promptPosition == "topLeft" || $.validationEngine.settings.promptPosition == "topRight"){
			callerTopPosition = callerTopPosition  -inputHeight -10;
		}
		$(updateThisPrompt).animate({ top:callerTopPosition });
	},
	linkTofield : function(caller){
		linkTofield = $(caller).attr("id") + "formError";
		linkTofield = linkTofield.replace(/\[/g,""); 
		linkTofield = linkTofield.replace(/\]/g,"");
		return linkTofield;
	},
	closePrompt : function(caller,outside) {						// CLOSE PROMPT WHEN ERROR CORRECTED
		if(!$.validationEngine.settings){
			$.validationEngine.defaultSetting()
		}
		if(outside){
			//bluespring修改 
			if($.validationEngine.settings.showOnMouseOver){
				$(caller).get(0).validateField.removeClass($.validationEngine.settings.errorClass).unbind('mouseover',$.validationEngine.showTip).unbind('mouseout',$.validationEngine.hideTip);
				$(caller).remove();
			}else{
				$(caller).fadeTo("fast",0,function(){
					$(caller).remove();
				});
			}
			return false;
		}
		if(typeof(ajaxValidate)=='undefined'){ajaxValidate = false}
		if(!ajaxValidate){
			linkTofield = $.validationEngine.linkTofield(caller);
			closingPrompt = "."+linkTofield;
			
			//bluespring修改 
			if($.validationEngine.settings.showOnMouseOver){
				if($(closingPrompt).get(0))$(closingPrompt).get(0).validateField.removeClass($.validationEngine.settings.errorClass).unbind('mouseover',$.validationEngine.showTip).unbind('mouseout',$.validationEngine.hideTip);
				$(closingPrompt).remove();
			}else{
				$(closingPrompt).fadeTo("fast",0,function(){
					$(closingPrompt).remove();
				});
			}
			
		}
	},
	debug : function(error) {
		if(!$("#debugMode")[0]){
			$("body").append("<div id='debugMode'><div class='debugError'><strong>This is a debug mode, you got a problem with your form, it will try to help you, refresh when you think you nailed down the problem</strong></div></div>");
		}
		$(".debugError").append("<div class='debugerror'>"+error+"</div>");
	},			
	submitValidation : function(caller) {					// FORM SUBMIT VALIDATION LOOPING INLINE VALIDATION
		var stopForm = false;
		$.validationEngine.ajaxValid = true;
		$(caller).find(".formError").remove();
		var toValidateSize = $(caller).find("[class*=validate]").size();
		
		$(caller).find("[class*=validate]").each(function(){
			linkTofield = $.validationEngine.linkTofield(this);
			
			if(!$("."+linkTofield).hasClass("ajaxed")){	// DO NOT UPDATE ALREADY AJAXED FIELDS (only happen if no normal errors, don't worry)
				var validationPass = $.validationEngine.loadValidation(this);
				return(validationPass) ? stopForm = true : "";					
			};
		});
		ajaxErrorLength = $.validationEngine.ajaxValidArray.length;		// LOOK IF SOME AJAX IS NOT VALIDATE
		for(x=0;x<ajaxErrorLength;x++){
	 		if($.validationEngine.ajaxValidArray[x][1] == false){
	 			$.validationEngine.ajaxValid = false;
 			}
 		}
		if(stopForm || !$.validationEngine.ajaxValid){		// GET IF THERE IS AN ERROR OR NOT FROM THIS VALIDATION FUNCTIONS
			if($.validationEngine.settings.scroll){
				destination = $(".formError:not('.greenPopup'):first").offset().top;
				$(".formError:not('.greenPopup')").each(function(){
					testDestination = $(this).offset().top;
					if(destination>testDestination){
						destination = $(this).offset().top;
					}
				})
				$("html:not(:animated),body:not(:animated)").animate({ scrollTop: destination}, 1100);
			}
			return true;
		}else{
			return false;
		}
	}
}
})(jQuery);