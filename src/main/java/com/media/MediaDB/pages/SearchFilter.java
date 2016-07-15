package com.media.MediaDB.pages;

import java.util.Date;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.slf4j.Logger;

import com.media.MediaDB.constants.MediaFormat;

public class SearchFilter {

	@Inject
	private Logger logger;

	@ActivationRequestParameter(value = "message")
	private String message;

	@Property
	private MediaFormat mediaFormats;

	@Property
	private String name;

	@Property
	private long maxSize;

	@Property
	private long minSize;

	@Property
	private Date createdAfterDate;

	@Property
	private Date createdBeforeDate;

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	@InjectPage("SearchMediaFile")
	SearchMediaFile searchMediaFile;

	public String getMessage() {
		return message;
	}

	public Link set(String message) {
		this.message = message;
		return pageRenderLinkSource.createPageRenderLink(this.getClass());
	}

	public Object onSuccess() {
		searchMediaFile.setName(name);
		searchMediaFile.setMediaFormats(mediaFormats);
		searchMediaFile.setMaxSize(maxSize);
		searchMediaFile.setMinSize(minSize);
		searchMediaFile.setCreatedAfterDate(createdAfterDate);
		searchMediaFile.setCreatedBeforeDate(createdBeforeDate);
		return searchMediaFile;
	}

}
