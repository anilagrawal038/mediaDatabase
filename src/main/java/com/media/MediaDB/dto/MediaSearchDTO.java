package com.media.MediaDB.dto;

import java.util.List;

import com.media.MediaDB.entities.MediaFile;

public class MediaSearchDTO {

	private List<MediaFile> mediaFiles;

	private int currentPage;
	private int batchSize;
	private long totalCount;

	public List<MediaFile> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<MediaFile> mediaFiles) {
		this.mediaFiles = mediaFiles;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
