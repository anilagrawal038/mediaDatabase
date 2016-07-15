package com.media.MediaDB.pages;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.slf4j.Logger;

public class Index {
	@Inject
	private Logger logger;
	
	@ActivationRequestParameter(value = "message")
	private String message;

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;
	
	@Inject
    private AlertManager alertManager;

	public String getMessage(){
		return message;
	}

	public Link set(String message) {
		this.message = message;
		return pageRenderLinkSource.createPageRenderLink(this.getClass());
	}

	public String getHelloMessage() {
		logger.info("getHelloMessage method called ************************");
		return "Hello!!!!!!!!!!!!";

	}
	
	public void onActionFromUpdateMediaFile() {
		alertManager.info("Please search or list down media files and update any particular media file");
//		 alertManager.alert(Duration.SINGLE , Severity.INFO , "Please search or list down media files and update any particular media file");
		logger.info("onActionFromUpdateMediaFile method called ************************");
	}
	
	public void onActionFromDeleteMediaFile() {
		alertManager.info("Please search or list down media files and delete any particular media file");
		logger.info("onActionFromUpdateMediaFile method called ************************");
	}

	public void onActionFromClick() {
		logger.info("onActionFromClick method called ************************");
	}
}
