package com.basync.b2b.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.exception.DaoException;
import com.basync.b2b.exception.MySQLErrorCodesTranslator;
import com.basync.b2b.util.PageList;

public class JdbcSlaveDao implements IJdbcSlaveDao {

	private JdbcTemplate jdbcTemplate;

	protected Logger logger = Logger.getLogger(getClass());
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		MySQLErrorCodesTranslator tr = new MySQLErrorCodesTranslator();
		tr.setDataSource(this.getJdbcTemplate().getDataSource());
		this.getJdbcTemplate().setExceptionTranslator(tr);
	}

	/**
	 * 
	 * Create a PageList object that be used to display in jsp . Including
	 * pagination information.
	 * 
	 * @param pageNo
	 * @param sql
	 * @param countSql
	 * @param pageSize
	 * @param extractor
	 *            be used to envelop javabean for sql results.
	 */
	public PageList getPageList(String sql, String countSql, int pageNo,
			int pageSize, Object[] params, RowMapperResultSetExtractor extractor)
			throws DaoException {

		if (pageNo <= 0) {
			pageNo = 1;
		}
		PageList pageList = new PageList();

		try {

			int count = this.getJdbcTemplate().queryForInt(countSql, params);

			pageList.setPageAmount(count);
			pageList.setPageNo(pageNo);
			pageList.setPageSize(pageSize);
			pageList.setRecordSize(count);

			List results = null;
			if(pageSize == -1){
				results = (List) this.getJdbcTemplate().query(sql, params, extractor);
			}else{
				int start = (pageNo - 1) * pageSize;
				results = (List) this.getJdbcTemplate().query(
					sql + " limit " + start + " ," + pageSize, params,
					extractor);
			}

			pageList.setResults(results);

		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			logger.error("Please notice limit <m, n>");
			logger.error(pageList);
			throw new DaoException();
		}

		return pageList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.dao.IJdbcSlaveDao#queryForObject(java.lang.String,
	 *      java.lang.Object[], org.springframework.jdbc.core.RowMapper)
	 */
	@SuppressWarnings("finally")
	public Object queryForObject(String sql, Object[] params,
			RowMapper rowmapper) throws Exception {

		Object c = null;
		try {
			c = this.getJdbcTemplate().queryForObject(sql, params, rowmapper);
		} catch (EmptyResultDataAccessException e) {
			recordErrorSql(sql, params, e);
		} finally {
			return c;

		}

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForObjectThrowsExc(java.lang.String, java.lang.Object[], org.springframework.jdbc.core.RowMapper)
	 */
	public Object queryForObjectThrowsExc(String sql, Object[] params, RowMapper rowmapper) throws Exception {
		try {
			Object c = this.getJdbcTemplate().queryForObject(sql, params, rowmapper);
			return c; 
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e; 
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.dao.IJdbcSlaveDao#queryForInt(java.lang.String,
	 *      java.lang.Object[])
	 */
	@SuppressWarnings("finally")
	public int queryForInt(String sql, Object[] params) throws Exception {

		int id = -1;
		try {
			id = this.getJdbcTemplate().queryForInt(sql, params);
		} catch (EmptyResultDataAccessException e) {
			recordErrorSql(sql, params, e);
			id = -1;
		} finally {
			return id;
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForIntThrowsExc(java.lang.String, java.lang.Object[])
	 */
	public int queryForIntThrowsExc(String sql, Object[] params) throws Exception {

		try {
			int id = this.getJdbcTemplate().queryForInt(sql, params);
			return id; 
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForObject(java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	@SuppressWarnings("finally")
	public Object queryForObject(String sql, Object[] params, Class c) throws Exception {
		Object o = null;
		try {
			o = (Object) this.getJdbcTemplate().queryForObject(sql, params, c);
		} catch (EmptyResultDataAccessException e) {
			recordErrorSql(sql, params, e);
		} finally {
			return o;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForObjectThrowsExc(java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	public Object queryForObjectThrowsExc(String sql, Object[] params, Class c) throws Exception {
		
		try {
			Object o = this.getJdbcTemplate().queryForObject(sql, params, c);
			return o; 
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e; 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcSlaveDao#query(java.lang.String, java.lang.Object[], org.springframework.jdbc.core.RowMapperResultSetExtractor)
	 */
	public List query(String sql, Object[] params, RowMapperResultSetExtractor rowMapperResultSetExtractor) throws Exception {
		
		List list = new ArrayList();
		try {
			list = (List) this.getJdbcTemplate().query(sql, params, rowMapperResultSetExtractor);
			return list;
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e;
		} 
	}

	
	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForList(java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	public List queryForList(String sql, Object[] params, Class c) throws Exception {
		List list = new  ArrayList();
		try {
			list = (List) this.getJdbcTemplate().queryForList(sql, params, c);
			return list; 
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e;
		}
	}
	
		
	/* (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcSlaveDao#queryForMap(java.lang.String, java.lang.Object[])
	 */
	public Map<String, Object> queryForMap(String sql, Object[] params)
			throws Exception {
		Map<String, Object> m = null;
		try {
			m = this.getJdbcTemplate().queryForMap(sql, params);
		} catch (EmptyResultDataAccessException e) {
			recordErrorSql(sql, params, e);
		} 
		return m;
	}

	/**
	 * For devleoper check sql!
	 * @param sql
	 * @param params
	 */
	private void recordErrorSql(String sql, Object[] params, Throwable e){
		logger.debug("ERROR SQL START: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if(e instanceof EmptyResultDataAccessException){
			logger.error("ERROR MESSAGE: " + e.getMessage());
		}else{
			logger.error("ERROR MESSAGE: " + e.getMessage(), e);
		}
		logger.debug("ERROR SQL:\t"+sql);
		
		if(sql==null || params==null){
			return ;
		}
		String [] p = sql.split("[?]");
		
		for(int i=0; i<params.length; i++)
		{
			if(params[i] instanceof Date) {
				logger.debug("ERROR SQL PARAMS:\tparams["+i+"] : ("+format.format(params[i])+")\t");
			}else {
				logger.debug("ERROR SQL PARAMS:\tparams["+i+"] : ("+params[i]+")\t");
			}
		}
		

		StringBuilder sb = new StringBuilder();
	
		
		if(p.length == params.length+1){
			for(int i=0; i<p.length-1; i++){
				
				sb.append(p[i]);
				if(params[i] instanceof Date) {
					sb.append(" '"+format.format(params[i])+"'");
				}else {
					sb.append(" '"+params[i]+"'");
				}
			}
			sb.append(p[p.length-1]);
		}else if(p.length == params.length){
			for(int i=0; i<p.length; i++){
				
				sb.append(p[i]);
				if(params[i] instanceof Date) {
					sb.append(" '"+format.format(params[i])+"'");
				}else {
					sb.append(" '"+params[i]+"'");
				}
			}
			
		}else {
			logger.debug("ERROR SQL : MAYBE A BAD SQL\t");
			
		}
		
		logger.debug("ERROR SQL WITH PARAMS:\t"+sb.toString()+"\t");
	
		logger.debug("ERROR SQL END: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}
