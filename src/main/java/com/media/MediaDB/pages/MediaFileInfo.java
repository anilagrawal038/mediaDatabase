package com.media.MediaDB.pages;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.entities.MediaFile;

public class MediaFileInfo {

	@Inject
	private GenericDAO<MediaFile> genricDAO;

	@Property
	private MediaFile mediaFile;
	
	@ActivationRequestParameter(value = "message")
	private String message;

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	public String getMessage(){
		return message;
	}

	public Link set(String message) {
		this.message = message;
		return pageRenderLinkSource.createPageRenderLink(this.getClass());
	}

	void onActivate(long mediaFileId) {
		mediaFile = genricDAO.get(MediaFile.class, mediaFileId);
	}
}
