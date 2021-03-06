package com.media.MediaDB.co;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.media.MediaDB.constants.MediaFormat;

public class MediaSearchCO {

	private MediaFormat mediaFormats;

	private String name;

	private long maxSize;

	private long minSize;

	private Date createdAfterDate;

	private Date createdBeforeDate;

	private int batchSize;

	private int pageCount;

	public MediaFormat getMediaFormats() {
		return mediaFormats;
	}

	public void setMediaFormats(MediaFormat mediaFormats) {
		this.mediaFormats = mediaFormats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public long getMinSize() {
		return minSize;
	}

	public void setMinSize(long minSize) {
		this.minSize = minSize;
	}

	public Date getCreatedAfterDate() {
		return createdAfterDate;
	}

	public void setCreatedAfterDate(Date createdAfterDate) {
		this.createdAfterDate = createdAfterDate;
	}

	public Date getCreatedBeforeDate() {
		return createdBeforeDate;
	}

	public void setCreatedBeforeDate(Date createdBeforeDate) {
		this.createdBeforeDate = createdBeforeDate;
	}

	public int getBatchSize() {
		if (batchSize == 0) {
			batchSize = 10;
		}
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "MediaSearchCO [mediaFormats=" + mediaFormats + ", name=" + name + ", maxSize=" + maxSize + ", minSize="
				+ minSize + ", createdAfterDate=" + createdAfterDate + ", createdBeforeDate=" + createdBeforeDate
				+ ", batchSize=" + batchSize + ", pageCount=" + pageCount + "]";
	}
}
