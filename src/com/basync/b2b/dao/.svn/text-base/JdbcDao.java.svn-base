package com.basync.b2b.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.exception.DaoException;
import com.basync.b2b.exception.MySQLErrorCodesTranslator;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.PageList;


public class JdbcDao implements IJdbcDao {
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
	 * create a custom translator and set the DataSource for the default
	 * translation lookup
	 * 
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 * 
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

			pageList.setPageAmount((count/pageSize)+1);
			if(count % pageSize == 0){
				pageList.setPageAmount(pageList.getPageAmount()-1);
			}
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
			logger.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}

		return pageList;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#getGridList(java.lang.String, java.lang.String, int, int, java.lang.Object[], org.springframework.jdbc.core.RowMapperResultSetExtractor)
	 */
	public JQGridContainer getGridList(String sql, String countSql,
			int pageNo, int pageSize, Object[] params,
			RowMapperResultSetExtractor extractor) throws DaoException {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		JQGridContainer container = new JQGridContainer();

		try {

			int count = this.getJdbcTemplate().queryForInt(countSql, params);
			if(pageSize ==-1){
				pageSize = count;
			}
			
			container.setRecords(count);
			double d = (double)count/pageSize;
			logger.debug("$$$$$$$$$$$$$$" + d +"  "+(d+""));
			if(d-(int)d >0 )
				container.setTotal((int)d +1);
			else
				container.setTotal((int)d );
			
			int pages =(int) Math.ceil((double) container.getRecords() /pageSize);
			logger.debug("$$$$$$$$$$$$$$ pages " + pages +"  "+(pageNo+""));
			if(pageNo > 1 &&  pageNo > pages)
				pageNo = pages;
			container.setPage(pageNo);
			List results = null;
			if(pageSize == -1){
				results = (List) this.getJdbcTemplate().query(sql, params, extractor);
			}else{
				int start = (pageNo - 1) * pageSize;
				results = (List) this.getJdbcTemplate().query(
					sql + " limit " + start + " ," + pageSize, params,
					extractor);
			}

			container.setRows(results);

		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			logger.error("Please notice limit <m, n>");
			logger.error(container);
			logger.error(e.getMessage(), e);
			throw new DaoException(e.getMessage(), e);
		}

		return container;
	}

	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForObject(java.lang.String, java.lang.Object[], org.springframework.jdbc.core.RowMapper)
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
		Object c = null;
		try {
			c = this.getJdbcTemplate().queryForObject(sql, params, rowmapper);
			return c; 
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e; 
		} 
	}

	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForInt(java.lang.String, java.lang.Object[])
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


	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForObject(java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	@SuppressWarnings("finally")
	public Object queryForObject(String sql, Object[] params, Class c) throws Exception {
		Object o = null;
		try {
			o = this.getJdbcTemplate().queryForObject(sql, params, c);
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
	 * @see com.basync.b2b.dao.IJdbcDao#update(java.lang.String, java.lang.Object[])
	 */
	public int update(String sql, Object[] params) throws Exception {

		int result = -1; 
		try {
			result = this.getJdbcTemplate().update(sql, params); 
			return result;
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e;
		} 
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#query(java.lang.String, java.lang.Object[], org.springframework.jdbc.core.RowMapperResultSetExtractor)
	 */
	public List query(String sql, Object[] params, RowMapperResultSetExtractor rowMapperResultSetExtractor) throws Exception {
		List list = new  ArrayList();
		try {
			list = (List) this.getJdbcTemplate().query(sql, params, rowMapperResultSetExtractor);
			return list; 
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			throw e;
		}
	}
	
	

	/* (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#queryForMap(java.lang.String, java.lang.Object[])
	 */
	public Map<String, Object> queryForMap(String sql, Object[] params)
			throws Exception {
		Map<String, Object> m = null;
		try {
			m = this.getJdbcTemplate().queryForMap(sql, params);
		} catch (EmptyResultDataAccessException empty) {
			recordErrorSql(sql, params, empty);
		} catch (Exception e){
			recordErrorSql(sql, params, e);
			throw e;
		}
		return m;
	}

	/**
	 * For devleoper check sql!
	 * @param sql
	 * @param params
	 */
	private void recordErrorSql(String sql, Object[] params, Throwable e){		
		logger.error("ERROR SQL START: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if(e instanceof EmptyResultDataAccessException){
			logger.error("ERROR MESSAGE: " + e.getMessage());
		}else{
			logger.error("ERROR MESSAGE: " + e.getMessage(), e);
		}
		logger.error("ERROR SQL:\t"+sql);
		
		if(sql==null || params==null){
			return ;
		}
		String [] p = sql.split("[?]");
		
		for(int i=0; i<params.length; i++)
		{
			if(params[i] instanceof Date) {
				logger.error("ERROR SQL PARAMS:\tparams["+i+"] : ("+format.format(params[i])+")\t");
			}else {
				logger.error("ERROR SQL PARAMS:\tparams["+i+"] : ("+params[i]+")\t");
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
			logger.error("ERROR SQL : MAYBE A BAD SQL\t");
			
		}
		
		logger.error("ERROR SQL WITH PARAMS:\t"+sb.toString()+"\t");
	
		logger.error("ERROR SQL END: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.dao.IJdbcDao#batchUpdate(java.lang.String, java.util.List)
	 */
	public int[] batchUpdate(String sql, List<Object[]> paramList)
			throws Exception {
		
		int[] result ; 
		try {
			final List<Object[]> paramsList = new ArrayList<Object[]>(paramList);
			//BatchPreparedStatementSetter 
			
			result = this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps, int rec) throws SQLException {
					Object o[]=(Object[])paramsList.get(rec);
					for(int i=0;i< o.length;i++ ){
						ps.setObject(i+1, o[i]);
					}
					
				}
				
				public int getBatchSize() {					
					return paramsList.size();
				}
			});
			return result;
		} catch (Exception e) {
			recordErrorSql(sql, null, e);
			logger.error("ERROR SQL IN BATCH UPDATE");
			throw e;
		} 
	}
	
	public int execute(String sql, Object[] params) throws Exception {
		int result ; 
		try {
			return result= this.getJdbcTemplate().update(sql,params); 
		} catch (Exception e) {
			recordErrorSql(sql, params, e);
			e.printStackTrace();
			throw e;
		}
	}
}
