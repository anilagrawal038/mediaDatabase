package com.media.MediaDB.worker;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.ServiceId;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.media.MediaDB.co.MediaSearchCO;
import com.media.MediaDB.dto.MediaSearchDTO;
import com.media.MediaDB.entities.MediaFile;

@ServiceId("CommonMediaService")
public class CommonMediaServiceImpl implements CommonMediaService {

	@Inject
	Session session;

	public MediaSearchDTO searchMediaFiles(MediaSearchCO searchCO) {
		MediaSearchDTO mediaSearchDTO = new MediaSearchDTO();
		Criteria criteria = session.createCriteria(MediaFile.class);
		Criteria countCriteria = session.createCriteria(MediaFile.class);
		if (searchCO.getName() != null) {
			criteria.add(Restrictions.like("name", searchCO.getName() + "%"));
			countCriteria.add(Restrictions.like("name", searchCO.getName() + "%"));
		}
		if (searchCO.getMediaFormats() != null) {
			criteria.add(Restrictions.eq("format", searchCO.getMediaFormats()));
			countCriteria.add(Restrictions.eq("format", searchCO.getMediaFormats()));
		}
		if (searchCO.getMinSize() > 1) {
			criteria.add(Restrictions.gt("size", searchCO.getMinSize()));
			countCriteria.add(Restrictions.gt("size", searchCO.getMinSize()));

		}
		if (searchCO.getMaxSize() > 1) {
			criteria.add(Restrictions.lt("size", searchCO.getMaxSize()));
			countCriteria.add(Restrictions.lt("size", searchCO.getMaxSize()));
		}
		if (searchCO.getCreatedAfterDate() != null) {
			criteria.add(Restrictions.gt("dateCreated", searchCO.getCreatedAfterDate()));
			countCriteria.add(Restrictions.gt("dateCreated", searchCO.getCreatedAfterDate()));
		}
		if (searchCO.getCreatedBeforeDate() != null) {
			criteria.add(Restrictions.lt("dateCreated", searchCO.getCreatedBeforeDate()));
			countCriteria.add(Restrictions.lt("dateCreated", searchCO.getCreatedBeforeDate()));
		}

		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult(searchCO.getPageCount() * searchCO.getBatchSize());
		criteria.setMaxResults(searchCO.getBatchSize());
		mediaSearchDTO.setMediaFiles(criteria.list());
		mediaSearchDTO.setBatchSize(searchCO.getBatchSize());
		mediaSearchDTO.setCurrentPage(searchCO.getPageCount());

		countCriteria.setProjection(Projections.rowCount());
		mediaSearchDTO.setTotalCount((Long) countCriteria.uniqueResult());
		return mediaSearchDTO;
	}
}
