package com.media.MediaDB.pages;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import com.media.MediaDB.co.MediaFileCO;
import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.entities.MediaFile;

public class DeleteMediaFile {

	@Inject
	private GenericDAO<MediaFile> genricDAO;

	@Property
	MediaFileCO mediaFile = new MediaFileCO();

	@Property
	private MediaFile currentMediaFile;

	@ActivationRequestParameter(value = "message")
	private String message;

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	public String getMessage() {
		return message;
	}

	public Link set(String message) {
		this.message = message;
		return pageRenderLinkSource.createPageRenderLink(this.getClass());
	}

	void onActivate(long mediaFileId) {
		currentMediaFile = genricDAO.get(MediaFile.class, mediaFileId);
		mediaFile.populateFields(currentMediaFile);
	}

	@InjectPage
	ListMediaFiles listMediafiles;

	@CommitAfter
	Object onSuccess() {
		currentMediaFile = genricDAO.get(MediaFile.class, mediaFile.getId());
		genricDAO.delete(currentMediaFile);
		return listMediafiles.set("Media File Deleted Successfully");

	}

}
