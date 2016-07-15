package com.media.MediaDB.pages;

import java.util.List;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.entities.MediaFile;

public class ListMediaFiles {
	@Inject
	private GenericDAO<MediaFile> mediaFileDAO;

	@Property
	private MediaFile mediaFile;

	public List<MediaFile> getMediaFiles() {
		return mediaFileDAO.getAll(MediaFile.class);
	}

	@ActivationRequestParameter(value = "message")
	private String message;

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	public Link set(String message) {
		this.message = message;
		return pageRenderLinkSource.createPageRenderLink(this.getClass());
	}

	public String getMessage() {
		return message;
	}

}
