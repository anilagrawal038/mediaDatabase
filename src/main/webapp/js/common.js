/**
 * 
 */
var domObj, dojoObj, domFormObj, queryObj, requestObj, xhrObj, readyObj, onObj, domAttrObj;
setTimeout(function() {

	var dojoConfig = {
		async : true
	};

	require.config({
		packages : [ {
			name : 'dojo',
			location : '../js/dojo'
		// ,main : "dojo"
		} ],

	});

	require([ 'dojo', 'dojo/dom', "dojo/dom-form", 'dojo/query',
			'dojo/request', 'dojo/ready', "dojo/request/xhr", 'dojo/on',
			'dojo/dom-attr', 'dojo/domReady!' ], function(dojo, dom, domForm,
			query, request, ready, xhr, on, domAttr) {
		dojoObj = dojo;
		domObj = dom;
		domFormObj = domForm;
		queryObj = query;
		requestObj = request;
		xhrObj = xhr;
		readyObj = ready;
		onObj = on;
		domAttrObj = domAttr;
		try {
			onReady();
		} catch (exception) {
			console.log(exception);
		}

	});
}, 1000);