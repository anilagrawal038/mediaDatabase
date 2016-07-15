package com.media.MediaDB.worker;

import org.apache.tapestry5.upload.services.UploadedFile;

import com.media.MediaDB.entities.MediaFile;
import com.media.MediaDB.exp.InvalidCSVHeaderException;
import com.media.MediaDB.pages.AddMediaFile;

public interface MediaFileCreater {

	public void createMediaFile(AddMediaFile mediaFileData);

	public void storeMediaFiles(UploadedFile uploadedFile)  throws InvalidCSVHeaderException;
}
