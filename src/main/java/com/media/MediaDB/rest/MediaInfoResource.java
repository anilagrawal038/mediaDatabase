package com.media.MediaDB.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

import com.media.MediaDB.co.MediaFileCO;
import com.media.MediaDB.co.MediaSearchCO;
import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.dto.MediaSearchDTO;
import com.media.MediaDB.entities.MediaFile;
import com.media.MediaDB.worker.CommonMediaService;

import java.util.List;

@Path("mediaInfo")
public class MediaInfoResource {

	@Inject
	private GenericDAO<MediaFile> genricDAO;

	@Inject
	CommonMediaService commonMediaService;

	@Inject
	private Logger logger;

	@GET
	@Path("/{mediaId}")
	@Produces("application/json")
	public MediaFile getMediaInfo(@PathParam("mediaId") long mediaId) {
		logger.info("Input MediaID => " + mediaId);
		MediaFile mediaFile = genricDAO.get(MediaFile.class, mediaId);
		logger.info("Output MediaInfo => " + mediaFile);
		return mediaFile;
	}

	@OPTIONS
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public MediaSearchDTO searchMedia(MediaSearchCO searchCO) {
		logger.info("Input searchCO => " + searchCO);
		MediaSearchDTO data = commonMediaService.searchMediaFiles(searchCO);
		logger.info("Output MediaInfo => " + data);
		return data;
	}

	@PUT
	@Path("/{mediaId}")
	@Consumes("application/json")
	@Produces("application/json")
	public MediaFile updateMediaInfo(@PathParam("mediaId") long mediaId, MediaFileCO mediaFileCO) {
		logger.info("Input MediaInfo => " + mediaFileCO.toString());
		MediaFile mediaFile = genricDAO.get(MediaFile.class, mediaId);
		logger.info("Original MediaInfo => " + mediaFile);
		mediaFile = mediaFileCO.populateMediaFile(mediaFile);
		genricDAO.saveOrUpdate(mediaFile);
		// update(mediaFile);
		logger.info("Output MediaInfo => " + mediaFile);
		return mediaFile;
	}

}
