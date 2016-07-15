package com.media.MediaDB.dao;

import java.util.List;

public interface GenericDAO<T> {

	public <T> T save(final T o);

	public void delete(final T o);

	public <T> T get(final Class<T> type, final long id);

	public <T> T merge(final T o);

	public <T> void saveOrUpdate(final T o);

	public <T> List<T> getAll(final Class<T> type);

}
