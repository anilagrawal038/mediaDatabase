package com.media.MediaDB.pages;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageAttached;
import org.apache.tapestry5.annotations.PageDetached;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.PageReset;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

import com.media.MediaDB.constants.MediaFormat;
import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.entities.MediaFile;

public class SearchMediaFile {

	@Inject
	private Logger logger;

	@Inject
	private GenericDAO<MediaFile> mediaFileDAO;

	@Property
	private MediaFile mediaFile;

	@ActivationRequestParameter(value = "message")
	private String message;

	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	public String getMessage() {
		return message;
	}

	@Persist(PersistenceConstants.FLASH)
	private MediaFormat mediaFormats;

	@Persist(PersistenceConstants.FLASH)
	private String name;

	@Persist(PersistenceConstants.FLASH)
	private long maxSize;

	@Persist(PersistenceConstants.FLASH)
	private long minSize;

	@Persist(PersistenceConstants.FLASH)
	private Date createdAfterDate;

	@Persist(PersistenceConstants.FLASH)
	private Date createdBeforeDate;

	@Inject
	Session session;

	private Criteria criteria;

	public Link set(String message) {
		this.message = message;
		return pageRenderLinkSource.createPageRenderLink(this.getClass());
	}

	public List<MediaFile> getMediaFiles() {
		criteria = session.createCriteria(MediaFile.class);
		if (name != null) {
			criteria.add(Restrictions.like("name", name + "%"));
		}
		if (mediaFormats != null) {
			criteria.add(Restrictions.eq("format", mediaFormats));
		}
		if (minSize > 1) {
			criteria.add(Restrictions.gt("size", minSize));
		}
		if (maxSize > 1) {
			criteria.add(Restrictions.lt("size", maxSize));
		}
		if (createdAfterDate != null) {
			criteria.add(Restrictions.gt("dateCreated", createdAfterDate));
		}
		if (createdBeforeDate != null) {
			criteria.add(Restrictions.lt("dateCreated", createdBeforeDate));
		}
		criteria.setMaxResults(20);
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}

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

	@PageAttached
	public void pageAttached() {
		logger.info("**************************@PageAttached*****************************");
		logger.info("**************************name=>" + name + "*****************************");
		logger.info("**************************mediaFormats=>" + mediaFormats + "*****************************");
		logger.info("**************************maxSize=>" + maxSize + "*****************************");
		logger.info("**************************minSize=>" + minSize + "*****************************");
		logger.info(
				"**************************createdAfterDate=>" + createdAfterDate + "*****************************");
		logger.info(
				"**************************createdBeforeDate=>" + createdBeforeDate + "*****************************");
	}

	@PageReset
	public void pageReset() {
		logger.info("**************************@PageReset*****************************");
		logger.info("**************************name=>" + name + "*****************************");
		logger.info("**************************mediaFormats=>" + mediaFormats + "*****************************");
		logger.info("**************************maxSize=>" + maxSize + "*****************************");
		logger.info("**************************minSize=>" + minSize + "*****************************");
		logger.info(
				"**************************createdAfterDate=>" + createdAfterDate + "*****************************");
		logger.info(
				"**************************createdBeforeDate=>" + createdBeforeDate + "*****************************");
	}

	@PageDetached
	public void pageDetached() {
		logger.info("**************************@PageDetached*****************************");
		logger.info("**************************name=>" + name + "*****************************");
		logger.info("**************************mediaFormats=>" + mediaFormats + "*****************************");
		logger.info("**************************maxSize=>" + maxSize + "*****************************");
		logger.info("**************************minSize=>" + minSize + "*****************************");
		logger.info(
				"**************************createdAfterDate=>" + createdAfterDate + "*****************************");
		logger.info(
				"**************************createdBeforeDate=>" + createdBeforeDate + "*****************************");
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
}
