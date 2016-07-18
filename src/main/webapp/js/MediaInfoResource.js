/**
 * 
 */

var formId = "searchMediaFilterForm";
var media;
var responseJson;

function onReady() {

	searchMedia();

	onObj(domObj.byId("paginationDiv"), "a.paginationLink:click",
			initializePagination);

	onObj(domObj.byId("searchMediaFilterForm"),
			".formField:change", initializeFormFields);


	onObj(domObj.byId("searchMediaFilterForm"), ".formBtn:click",
			function() {
				domObj.byId('pageCount').value = 0;
				searchMedia();
			});
};

function initializeFormFields() {
	domObj.byId('pageCount').value = 0;
}

function initializePagination(e) {
	var pageIndex = domAttrObj.get(this, 'index');
	console.log("Pagination page with index :" + pageIndex + " clicked");
	domObj.byId('pageCount').value = pageIndex;
	domObj.byId('batchSize').value = responseJson.batchSize;
	searchMedia();
}

function updateMedia() {
	var formJson = dojoObj.formToJson(formId);
	console.log("Form submitted with data : " + JSON.stringify(formJson));
	media.name = "updatedWowFile";
	requestObj.put("rest/mediaInfo/8", {
		data : JSON.stringify(media),
		headers : {
			"Content-Type" : "application/json"
		},
		handleAs : "json",
	}).then(
			function(text) {
				console.log("The file's content of updateMedia() is: "
						+ JSON.stringify(text));
			});
}

function getMedia() {
	var formJson = dojoObj.formToJson(formId);
	console.log("Form submitted with data : " + JSON.stringify(formJson));
	requestObj.get("rest/mediaInfo/8", {
		data : JSON.stringify(formJson),
		headers : {
			"Content-Type" : "application/json"
		},
		handleAs : "json",
	}).then(
			function(text) {
				media = text;
				console.log("The file's content of getMedia() is: "
						+ JSON.stringify(text));
			});
}

function searchMedia() {

	var formJson = dojoObj.formToJson(formId);
	formJson = removeBlankParametrsFromJson(formJson);
	console.log("Form submitted with data : " + JSON.stringify(formJson));

	xhrObj("rest/mediaInfo", {
		handleAs : "json",
		method : 'OPTIONS',
		data : JSON.stringify(formJson),
		headers : {
			'Content-Type' : 'application/json'
		}
	}).then(
			function(data) {
				responseJson = data;
				console.log("The file's content of searchMediaNew() is: "
						+ JSON.stringify(data));
				paintSearchResult();
				createPagination();
			},
			function(err) {
				console.log("The file's err of searchMediaNew() is: "
						+ JSON.stringify(err));
			},
			function(evt) {
				console.log("The file's evt of searchMediaNew() is: "
						+ JSON.stringify(evt));
			});

}

function paintSearchResult() {
	var divElement = domObj.byId("searchResultDataDiv");
	var mediaFiles = responseJson.mediaFiles;

	var divData = "<table border='2' width='100%'>" + "	<tr>"
			+ "		<th>Name</th>" + "		<th>Location</th>" + "		<th>Size</th>"
			+ "		<th>Duration</th>" + "		<th>Format</th>" + "		<th>Codecs</th>"
			+ "		<th>Framerate</th>" + "		<th>Resolution</th>"
			+ "		<th>Bitrate</th>" + "		<th>Date Created</th>"
			+ "		<th>Update</th>" + "		<th>Delete</th>" + "	</tr>";

	for (var i = 0; i < mediaFiles.length; i++) {
		var mediaFile = mediaFiles[i];
		divData += "<tr>";
		divData += "<td>" + "<a href='mediaFileInfo/" + mediaFile.id + "'>"
				+ mediaFile.name + "</a>" + "</td>";
		divData += "<td>" + mediaFile.location + "</td>";
		divData += "<td>" + mediaFile.size + "</td>";
		divData += "<td>" + mediaFile.duration + "</td>";
		divData += "<td>" + mediaFile.format + "</td>";
		divData += "<td>" + mediaFile.codecs + "</td>";
		divData += "<td>" + mediaFile.frameRate + "</td>";
		divData += "<td>" + mediaFile.resolution + "</td>";
		divData += "<td>" + mediaFile.bitrate + "</td>";
		divData += "<td>" + mediaFile.dateCreated + "</td>";
		divData += "<td>" + "<a href='updateMediaFile/" + mediaFile.id
				+ "'>Update</a>" + "</td>";
		divData += "<td>" + "<a href='deleteMediaFile/" + mediaFile.id
				+ "'>Delete</a>" + "</td>";
		divData += "</tr>";
	}
	divData += "</table>";

	divElement.innerHTML = divData;

	// Update form fields on the basis of current response
	// domObj.byId('pageCount').value = (responseJson.currentPage + 1);
	// domObj.byId('batchSize').value = responseJson.batchSize;

}

function createPagination() {
	var divElement = domObj.byId("paginationDiv");
	var totalPages = Math
			.ceil(responseJson.totalCount / responseJson.batchSize);
	var divContent = "";
	var currentPageIndex = responseJson.currentPage;
	divContent += "<a class='paginationLink' href='#' index="
			+ (currentPageIndex - 1)
			+ "  "
			+ ((currentPageIndex <= 0) ? "style='pointer-events: none;cursor: default;'"
					: "") + ">Previous</a>&nbsp;&nbsp;";
	for (var counter = 1; counter <= totalPages; counter++) {
		divContent += "<a class='paginationLink' href='#' index="
				+ (counter - 1) + "  style='color:"
				+ ((currentPageIndex + 1) == counter ? 'red' : 'black') + "'>"
				+ counter + "</a>&nbsp;";
	}
	divContent += "&nbsp;<a class='paginationLink' href='#' index="
			+ (currentPageIndex + 1)
			+ "  "
			+ ((currentPageIndex >= (totalPages - 1)) ? "style='pointer-events: none;cursor: default;'"
					: "") + ">Next</a>";
	divElement.innerHTML = divContent;
}

function removeBlankParametrsFromJson(jsonObj) {
	jsonObj = JSON.parse(jsonObj);
	for ( var key in jsonObj) {
		var val = jsonObj[key];
		if (val.length < 1) {
			delete jsonObj[key];
		}
	}
	return jsonObj;
}
