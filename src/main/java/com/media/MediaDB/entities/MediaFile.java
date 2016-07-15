package com.media.MediaDB.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import com.media.MediaDB.constants.MediaCodec;
import com.media.MediaDB.constants.MediaFormat;

@Entity
public class MediaFile implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private Long id;

	@Override
	public String toString() {
		return "MediaFile [id=" + id + ", location=" + location + ", name=" + name + ", size=" + size + ", duration="
				+ duration + ", format=" + format + ", codecs=" + codecs + ", frameRate=" + frameRate + ", resolution="
				+ resolution + ", bitrate=" + bitrate + ", dateCreated=" + dateCreated + ", dateModified="
				+ dateModified + "]";
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

	private String location;

	private String name;

	private long size;

	private int duration;

	@Enumerated(EnumType.STRING)
	@Validate("required")
	private MediaFormat format;

	@Enumerated(EnumType.STRING)
	@Validate("required")
	private MediaCodec codecs;

	@Validate("required")
	private int frameRate;

	private String resolution;

	private int bitrate;

	private Date dateCreated;

	private Date dateModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
