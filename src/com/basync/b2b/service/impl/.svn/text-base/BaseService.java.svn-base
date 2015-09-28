package com.basync.b2b.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.basync.b2b.cache.CacheInterceptor;
import com.basync.b2b.dao.JdbcDao;
import com.basync.b2b.dao.JdbcSlaveDao;

public class BaseService {

	protected Logger logger = Logger.getLogger(getClass());
	private CacheInterceptor cacheInterceptor;
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected DecimalFormat   decimaf   =   new   DecimalFormat("#######0.00");   
	private JdbcDao jdbcDao;
	private JdbcSlaveDao jdbcSlaveDao;


	
	/**
	 * @return the cacheInterceptor
	 */
	public CacheInterceptor getCacheInterceptor() {
		return cacheInterceptor;
	}

	/**
	 * @param cacheInterceptor the cacheInterceptor to set
	 */
	public void setCacheInterceptor(CacheInterceptor cacheInterceptor) {
		this.cacheInterceptor = cacheInterceptor;
	}

	/**
	 * @return the jdbcDao
	 */
	public JdbcDao getJdbcDao() {
		return jdbcDao;
	}

	/**
	 * @param jdbcDao
	 *            the jdbcDao to set
	 */
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	public void handleException(Throwable t) {
	}

	/**
	 * @return the jdbcSlaveDao
	 */
	public JdbcSlaveDao getJdbcSlaveDao() {
		return jdbcSlaveDao;
	}

	/**
	 * @param jdbcSlaveDao the jdbcSlaveDao to set
	 */
	public void setJdbcSlaveDao(JdbcSlaveDao jdbcSlaveDao) {
		this.jdbcSlaveDao = jdbcSlaveDao;
	}
	
}
