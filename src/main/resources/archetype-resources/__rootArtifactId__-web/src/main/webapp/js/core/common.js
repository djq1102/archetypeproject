/**
 * @name: 前端核心逻辑
 * @detail: 提供封装的常用调用接口和工具函数
 * @author: 李洪波
 * @create: 2012/5/15
 * @update: 2012/5/15
 */

/*******************************************************************************
 * 
 * Extend jQuery Global Function
 * 
 ******************************************************************************/
;(function($) {
	"use strict";
	$.extend({
		// convert text to Date
		textToDate : function(dateText) {
			dateText = dateText.split("-");
			var selDate = new Date();
			selDate.setFullYear(dateText[0], dateText[1] - 1, dateText[2]);
			return selDate;
		},
		// compare date
		compareDate : function(dateOne, dateTwo) {
			dateOne = (typeof (dateOne) === "string") ? $.textToDate(dateOne)
					: dateOne;
			dateTwo = (typeof (dateTwo) === "string") ? $.textToDate(dateTwo)
					: dateTwo;
			return dateOne > dateTwo;
		},
		// get current date
		getCurDate : function(dayCount) {
			var myDate = new Date();
			myDate.setDate(myDate.getDate() + (!!dayCount ? dayCount : 0));
			var year = myDate.getFullYear(); // 获取完整的年份(4位,1970-????)
			var month = myDate.getMonth() + 1 > 9 ? myDate.getMonth() + 1 : '0'+ (myDate.getMonth() + 1); // 获取当前月份(0-11,0代表1月)
			var date = myDate.getDate() > 9 ? myDate.getDate() : '0'+ myDate.getDate(); // 获取当前日(1-31)
			var myTime = year + "-" + month + "-" + date;
			return myTime;
		},
		// error handler for ajax call
		ajaxErrorHandler : function(xmlRequest) {
			alert("抱歉！数据请求失败！");
			// alert(xmlRequest.status + "\n\r" +
			// xmlRequest.statusText + "\n\r" +
			// xmlRequest.responseText);
		},
		// detect empty string
		isEmptyString : function(value) {
			value = $.trim(value);
			if (value === undefined || value === ''){
				return true;
			}
			return false;
		}
	});
})(jQuery);

/*******************************************************************************
 * 
 * Extend jQuery Object Function
 * 
 ******************************************************************************/
;(function($) {
	"use strict";
	$.fn.extend({
		// empty the file input
		emptyFile : function() {
			this.after(this.clone().val(""));
			this.remove();
			return this;
		},
		// reset the form
		resetForm : function() {
			this.eq(0).reset();
			return this;
		},
		// default tip effect
		defaultTip : function(options) {
			// default value
			options = $.extend({
				defaultClass : "tip-state",
				defaultText : "请输入内容"
			}, options);

			var dClass = options.defaultClass;
			var dText = options.defaultText;
			this.val(dText).addClass(dClass);
			this.focus(function() {
				if ($(this).val() === dText) {
					$(this).val("").removeClass(dClass);
				}
			}).blur(function() {
				if ($.trim($(this).val()) === "") {
					$(this).val(dText).addClass(dClass);
				}
			});

			return this;
		}
	});
})(jQuery);


/**
 * @name: jQuery Countdown Plugin
 * @author: Hongbo Li
 * @create: 2012/5/23
 * @update: 2012/5/23
 */
(function($){
	"use strict";

	$.countdown = function(seconds,endCallback) {
		var timer = setInterval(showinfo, 100);
		timers[this[0].id]=timer;
		var endtime = new Date();
		endtime.setSeconds(endtime.getSeconds() + opts.seconds);
		function showinfo() {
			var nowtime = new Date();
			var leftsecond = parseInt((endtime.getTime() - nowtime.getTime()) / 1000,10);
			var s = parseInt(leftsecond % 60,10);
			if (leftsecond <= 0) {
				endCallback();
				clearInterval(timer);
			}
		}
		return timer;
	};
	
	var timers={};
	var restSeconds={};
	$.fn.countdown = function(options) {
		var opts = $.extend({}, $.fn.countdown.defaults, options);
		var timer = setInterval(showinfo, 100);
		timers[this[0].id]=timer;
		var endtime = new Date();
		endtime.setSeconds(endtime.getSeconds() + opts.seconds);
		var me=this;
		function showinfo() {
			var nowtime = new Date();
			var leftsecond = parseInt((endtime.getTime() - nowtime.getTime()) / 1000,10);
			var s = parseInt(leftsecond % 60,10);
			var info=opts.html.replace(/\$s\$/g, s);
			me.html(info);
			if (leftsecond <= 0) {
				opts.endCallback();
				clearInterval(timer);
			}
			restSeconds[me[0].id]=leftsecond;
		}
		return this;
	};
	
	$.fn.isCountdowning = function(){
		var rs=restSeconds[this[0].id];
		return rs>0?true:false;
	};
	
	$.fn.removeCountdown = function(){
		var timer=timers[this[0].id];
		if(timer){
			clearInterval(timer);
		}
		return this;
	};
	
	$.fn.countdown.defaults = {
		html : "$s$",
		seconds : 10,
		endCallback : function(){}
	};
})(jQuery);
