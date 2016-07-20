/**
 * 
 */
var domObj, dojoObj, domFormObj, queryObj, requestObj, xhrObj, readyObj, onObj, domAttrObj, parserObj, validateObj, arrayUtilObj;
setTimeout(function() {

	var dojoConfig = {
		async : true
	};

	require.config({
		packages : [ {
			name : 'dojo',
			location : '../js/dojo'
		// ,main : "dojo"
		}, {
			name : 'dijit',
			location : '../js/dijit'
		}, {
			name : 'dojox',
			location : '../js/dojox'
		} ],

	});

	require([ 'dojo/parser', 'dojo', 'dojo/dom', "dojo/dom-form", 'dojo/query',
			'dojo/request', 'dojo/ready', "dojo/request/xhr", 'dojo/on',
			'dojo/dom-attr', 'dojox/validate', 'dojo/_base/array', "dojo/date",
			"dijit/form/DateTextBox", "dojox/validate/web",
			"dojox/validate/us", "dojox/validate/check", "dijit/form/Form",
			"dijit/form/NumberTextBox", 'dojo/domReady!' ], function(parser,
			dojo, dom, domForm, query, request, ready, xhr, on, domAttr,
			validate, array) {
		dojoObj = dojo;
		domObj = dom;
		domFormObj = domForm;
		queryObj = query;
		requestObj = request;
		xhrObj = xhr;
		readyObj = ready;
		onObj = on;
		domAttrObj = domAttr;
		parserObj = parser;
		validateObj = validate;
		arrayUtilObj = array;
		try {
			onReady();
		} catch (exception) {
			console.log(exception);
		}

	});
}, 1000);

function testRegexString(regex, str) {
	// var re = new RegExp("((^.{2,5}$)|(^[.]*$))");
	var re = new RegExp(regex);
	return re.test(str);
}