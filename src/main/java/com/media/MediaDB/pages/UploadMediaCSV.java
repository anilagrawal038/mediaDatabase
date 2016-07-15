package com.media.MediaDB.pages;

import java.io.File;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.upload.services.UploadedFile;

import com.media.MediaDB.exp.InvalidCSVHeaderException;
import com.media.MediaDB.worker.MediaFileCreater;

public class UploadMediaCSV {
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

	@Property
	private UploadedFile file;

	@Inject
	MediaFileCreater mediaFileCreater;

	@InjectPage
	ListMediaFiles listMediafiles;

	@CommitAfter
	public Object onSuccess() {
		Link link = null;
		try {
			mediaFileCreater.storeMediaFiles(file);
			link = listMediafiles.set("Media File CSV uploaded successfully");
		} catch (InvalidCSVHeaderException exp) {
			link = set("Media File CSV header is invalid");
		}
		return link;
	}

	Object onUploadException(FileUploadException ex) {
		message = "Upload exception: " + ex.getMessage();

		return this;
	}

}
