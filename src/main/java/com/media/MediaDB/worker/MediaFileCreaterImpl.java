package com.media.MediaDB.worker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.Transaction;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.ServiceId;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.hibernate.Session;

import com.media.MediaDB.constants.MediaCodec;
import com.media.MediaDB.constants.MediaFormat;
import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.entities.MediaFile;
import com.media.MediaDB.exp.InvalidCSVHeaderException;
import com.media.MediaDB.pages.AddMediaFile;

@ServiceId("MediaFileCreater")
public class MediaFileCreaterImpl implements MediaFileCreater {

	@Inject
	private Session session;

	@Inject
	GenericDAO<MediaFile> genricDAO;

	private String tempPath = System.getProperty("java.io.tmpdir");

	private final int CSV_COL_COUNT = 9;

	public void createMediaFile(AddMediaFile mediaFileData) {
		MediaFile mediaFile = new MediaFile();
		mediaFile.setName(mediaFileData.getName());
		mediaFile.setLocation(mediaFileData.getLocation());
		mediaFile.setSize(mediaFileData.getSize());
		mediaFile.setFormat(mediaFileData.getMediaFormat());
		mediaFile.setCodecs(mediaFileData.getMediaCodec());
		mediaFile.setDuration(mediaFileData.getDuration());
		mediaFile.setResolution(mediaFileData.getResolution());
		mediaFile.setBitrate(mediaFileData.getBitrate());
		mediaFile.setFrameRate(mediaFileData.getFrameRate());
		Date date = new Date();
		mediaFile.setDateCreated(date);
		mediaFile.setDateModified(date);
	}

	public void storeMediaFiles(UploadedFile uploadedFile) throws InvalidCSVHeaderException {
		File file = new File(tempPath + "/" + uploadedFile.getFileName());
		uploadedFile.write(file);
		processCSV(uploadedFile.getStream(), uploadedFile.getFileName());
	}

	public void processCSV(InputStream inputStream, String fileName) throws InvalidCSVHeaderException {
		String processedFileName = tempPath + "/" + fileName.split("\\.")[0] + "_status.csv";
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			bufferedWriter = new BufferedWriter(new FileWriter(processedFileName, true));
			String currentLine = bufferedReader.readLine();
			if (currentLine != null) {
				processCSVItem(currentLine, bufferedWriter, true);
			}
			while ((currentLine = bufferedReader.readLine()) != null) {
				processCSVItem(currentLine, bufferedWriter, false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void processCSVItem(String currentLine, BufferedWriter bufferedWriter, boolean isHeader)
			throws InvalidCSVHeaderException {
		String[] data = currentLine.split("\\,");
		if (data.length != CSV_COL_COUNT) {
			if (isHeader) {
				throw new InvalidCSVHeaderException();
			} else {
				try {
					bufferedWriter.write(currentLine+",*****Column Count Mismatch*****");
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		try {
			if (isHeader) {
				bufferedWriter.write(currentLine + ",uploadStaus");
			} else {
				MediaFile mediaFile = null;
				try {
					mediaFile = pupulateMediaFileFromCSVData(data);
					Serializable id = genricDAO.save(mediaFile);
					bufferedWriter.write(currentLine + "," + id.toString());
				} catch (IllegalArgumentException exp) {
					bufferedWriter.write(currentLine + ",Invalid Data");
				}
			}
			bufferedWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	MediaFile pupulateMediaFileFromCSVData(String[] data) {
		MediaFile mediaFile = new MediaFile();
		mediaFile.setName(data[0]);
		mediaFile.setLocation(data[1]);
		mediaFile.setFormat(MediaFormat.valueOf(data[2]));
		mediaFile.setCodecs(MediaCodec.valueOf(data[3]));
		mediaFile.setBitrate(Integer.parseInt(data[4]));
		mediaFile.setResolution(data[5]);
		mediaFile.setFrameRate(Integer.parseInt(data[6]));
		mediaFile.setSize(Long.parseLong(data[7]));
		mediaFile.setDuration(Integer.parseInt(data[8]));
		Date date = new Date();
		mediaFile.setDateCreated(date);
		mediaFile.setDateModified(date);
		return mediaFile;
	}

}
