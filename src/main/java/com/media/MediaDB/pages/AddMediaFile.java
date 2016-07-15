package com.media.MediaDB.pages;

import java.io.Serializable;
import java.util.Date;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.hibernate.Session;
import org.slf4j.Logger;

import com.media.MediaDB.constants.MediaCodec;
import com.media.MediaDB.constants.MediaFormat;
import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.entities.MediaFile;
import com.media.MediaDB.worker.MediaFileCreater;
import com.media.MediaDB.worker.MediaFileCreaterImpl;

public class AddMediaFile {

	@Inject
	private Logger logger;

	@Inject
	Request request;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public MediaFormat getMediaFormat() {
		return mediaFormat;
	}

	public void setMediaFormat(MediaFormat mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	public MediaCodec getMediaCodec() {
		return mediaCodec;
	}

	public void setMediaCodec(MediaCodec mediaCodec) {
		this.mediaCodec = mediaCodec;
	}

	public int getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getBitrate() {
		return bitrate;
	}

	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	private String name;
	@InjectComponent("name")
	private TextField nameField;

	private String location;
	@InjectComponent("location")
	private TextField locationField;

	private long size;
	@InjectComponent("size")
	private TextField sizeField;

	private int duration;
	@InjectComponent("duration")
	private TextField durationField;

	@Validate("required")
	private MediaFormat mediaFormat;

	@Validate("required")
	private MediaCodec mediaCodec;

	private int frameRate;
	@InjectComponent("frameRate")
	private TextField frameRateField;

	private String resolution;
	@InjectComponent("resolution")
	private TextField resolutionField;

	private int bitrate;
	@InjectComponent("bitrate")
	private TextField bitrateField;

	@Component
	private Form addMediaFileForm;

	@Inject
	private GenericDAO<MediaFile> genericDAO;

	@InjectPage
	ListMediaFiles listMediafiles;

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

	void onValidateFromAddMediaFileForm() {
		if (name == null || name.length() < 5) {
			addMediaFileForm.recordError(nameField, "Media file name length should be greater than 4");
		} else if (location == null || location.length() < 5) {
			addMediaFileForm.recordError(locationField, "Media file location length should be greater than 4");
		} else if (size < 1) {
			addMediaFileForm.recordError(sizeField, "Media file size should be greater than 0");
		} else if (duration < 1) {
			addMediaFileForm.recordError(durationField, "Media file duration should be greater than 0");
		} else if (frameRate < 1) {
			addMediaFileForm.recordError(frameRateField, "Media file framerate should be greater than 0");
		} else if (bitrate < 1) {
			addMediaFileForm.recordError(bitrateField, "Media file bitrate should be greater than 0");
		} else if (resolution == null || resolution.length() < 3) {
			addMediaFileForm.recordError(frameRateField, "Media file resolution is invalid");
		}
	}

	@CommitAfter
	Object onSuccess() {
		Serializable o = (Serializable) genericDAO.save(populateMediaFile());
		logger.info(o.toString() + "" + o.getClass());
		logger.info(genericDAO.get(MediaFile.class, (Long) o).toString());
		return listMediafiles.set("Media File added sucessfully");
	}

	public MediaFile populateMediaFile() {
		MediaFile mediaFile = new MediaFile();
		mediaFile.setName(getName());
		mediaFile.setLocation(getLocation());
		mediaFile.setSize(getSize());
		mediaFile.setFormat(getMediaFormat());
		mediaFile.setCodecs(getMediaCodec());
		mediaFile.setDuration(getDuration());
		mediaFile.setResolution(getResolution());
		mediaFile.setBitrate(getBitrate());
		mediaFile.setFrameRate(getFrameRate());
		Date date = new Date();
		mediaFile.setDateCreated(date);
		mediaFile.setDateModified(date);
		return mediaFile;
	}
}
