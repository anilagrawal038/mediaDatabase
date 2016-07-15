package com.media.MediaDB.pages;

import java.io.Serializable;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.slf4j.Logger;

import com.media.MediaDB.co.MediaFileCO;
import com.media.MediaDB.constants.MediaCodec;
import com.media.MediaDB.constants.MediaFormat;
import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.entities.MediaFile;

public class UpdateMediaFile {

	@Inject
	Request request;

	@Inject
	private Logger logger;

	@Inject
	private GenericDAO<MediaFile> genricDAO;

	@Property
	private MediaFile currentMediaFile;

	@Property
	MediaFileCO mediaFile = new MediaFileCO();

	@Property
	private MediaFormat mediaFormats;

	@Property
	private MediaCodec mediaCodecs;

	@InjectComponent("name")
	private TextField nameField;

	@InjectComponent("location")
	private TextField locationField;

	@InjectComponent("size")
	private TextField sizeField;

	@InjectComponent("duration")
	private TextField durationField;

	@InjectComponent("frameRate")
	private TextField frameRateField;

	@InjectComponent("resolution")
	private TextField resolutionField;

	@InjectComponent("bitrate")
	private TextField bitrateField;

	@Component
	private Form updateMediaFileForm;

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

	@InjectPage
	ListMediaFiles listMediafiles;

	void onValidateFromUpdateMediaFileForm() {
		if (mediaFile.getName() == null || mediaFile.getName().length() < 5) {
			updateMediaFileForm.recordError(nameField, "Media file name length should be greater than 4");
		} else if (mediaFile.getLocation() == null || mediaFile.getLocation().length() < 5) {
			updateMediaFileForm.recordError(locationField, "Media file location length should be greater than 4");
		} else if (mediaFile.getSize() < 1) {
			updateMediaFileForm.recordError(sizeField, "Media file size should be greater than 0");
		} else if (mediaFile.getDuration() < 1) {
			updateMediaFileForm.recordError(durationField, "Media file duration should be greater than 0");
		} else if (mediaFile.getFrameRate() < 1) {
			updateMediaFileForm.recordError(frameRateField, "Media file framerate should be greater than 0");
		} else if (mediaFile.getBitrate() < 1) {
			updateMediaFileForm.recordError(bitrateField, "Media file bitrate should be greater than 0");
		} else if (mediaFile.getResolution() == null || mediaFile.getResolution().length() < 3) {
			updateMediaFileForm.recordError(frameRateField, "Media file resolution is invalid");
		}
	}

	void onActivate(long mediaFileId) {
		currentMediaFile = genricDAO.get(MediaFile.class, mediaFileId);
		mediaFile.populateFields(currentMediaFile);
		// logger.info("*****************" + currentMediaFile.toString() +
		// "****************************");
		// logger.info("*****************" + mediaFile.toString() +
		// "****************************");
	}

	@CommitAfter
	Object onSuccess() {
		currentMediaFile = genricDAO.get(MediaFile.class, mediaFile.getId());
		currentMediaFile = mediaFile.populateMediaFile(currentMediaFile);
		// logger.info("*****************" + currentMediaFile.toString() +
		// "****************************");
		// logger.info("*****************" + mediaFile.toString() +
		// "****************************");
		genricDAO.saveOrUpdate(currentMediaFile);
		// logger.info("*****************Submitted
		// successfully****************************");
		// logger.info(request.getAttributeNames().toString());

		// mediaFile.setFormat(mediaFormats);
		// mediaFile.setCodecs(mediaCodecs);
		// genricDAO.saveOrUpdate(mediaFile);
		return listMediafiles.set("Media File Updated Successfully");

	}
}
