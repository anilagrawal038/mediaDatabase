package com.media.MediaDB.co;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.tapestry5.beaneditor.Validate;

import com.media.MediaDB.constants.MediaCodec;
import com.media.MediaDB.constants.MediaFormat;
import com.media.MediaDB.entities.MediaFile;

public class MediaFileCO {

	private long id;

	private String location;

	private String name;

	private long size;

	private int duration;

	private MediaFormat format;

	private MediaCodec codecs;

	private int frameRate;

	private String resolution;

	private int bitrate;

	private Date dateCreated;

	private Date dateModified;

	public void populateFields(MediaFile mediaFile) {
		setId(mediaFile.getId());
		setLocation(mediaFile.getLocation());
		setName(mediaFile.getName());
		setSize(mediaFile.getSize());
		setDuration(mediaFile.getDuration());
		setFormat(mediaFile.getFormat());
		setCodecs(mediaFile.getCodecs());
		setFrameRate(mediaFile.getFrameRate());
		setResolution(mediaFile.getResolution());
		setBitrate(mediaFile.getBitrate());
		setDateCreated(mediaFile.getDateCreated());
		setDateModified(mediaFile.getDateModified());
	}

	public MediaFile populateMediaFile(MediaFile mediaFile) {
		if (id == mediaFile.getId()) {
			mediaFile.setLocation(getLocation());
			mediaFile.setName(getName());
			mediaFile.setSize(getSize());
			mediaFile.setDuration(getDuration());
			mediaFile.setFormat(getFormat());
			mediaFile.setCodecs(getCodecs());
			mediaFile.setFrameRate(getFrameRate());
			mediaFile.setResolution(getResolution());
			mediaFile.setBitrate(getBitrate());
		}
		return mediaFile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public MediaFormat getFormat() {
		return format;
	}

	public void setFormat(MediaFormat format) {
		this.format = format;
	}

	public MediaCodec getCodecs() {
		return codecs;
	}

	public void setCodecs(MediaCodec codecs) {
		this.codecs = codecs;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public String toString() {
		return "MediaFileCO [id=" + id + ", location=" + location + ", name=" + name + ", size=" + size + ", duration="
				+ duration + ", format=" + format + ", codecs=" + codecs + ", frameRate=" + frameRate + ", resolution="
				+ resolution + ", bitrate=" + bitrate + ", dateCreated=" + dateCreated + ", dateModified="
				+ dateModified + "]";
	}


}
