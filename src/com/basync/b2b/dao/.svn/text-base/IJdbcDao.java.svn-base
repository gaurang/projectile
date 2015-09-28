package com.basync.b2b.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.basync.b2b.exception.DaoException;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.PageList;


public interface IJdbcDao {
	

	/**
	 * Query sql for Object , 
	 * <p>If catch EmptyResultDataAccessException , retrun <code>null</code>; 
	 * 
	 * @param sql
	 * @param params
	 * @param rowmapper
	 * @return
	 * @throws Exception
	 */
	public Object queryForObject(String sql, Object [] params , RowMapper rowmapper) throws Exception;
	
	/**
	 * Query sql for Object , 
	 * <p>If catch any exception , throws this exception;
	 *   
	 * @param sql
	 * @param params
	 * @param rowmapper
	 * @return
	 * @throws Exception
	 */
	public Object queryForObjectThrowsExc(String sql, Object [] params , RowMapper rowmapper) throws Exception;
	
	/**
	 * Query sql for Object , 
	 * <p>If catch EmptyResultDataAccessException , retrun <code>null</code>; 
	 * 
	 * @param sql
	 * @param params
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public Object queryForObject(String sql, Object [] params , Class c) throws Exception;
	
	/**
	 * Query sql for Object , 
	 * <p>If catch any exception , throws this exception; 
	 * @param sql
	 * @param params
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public Object queryForObjectThrowsExc(String sql, Object [] params , Class c) throws Exception;
	
	/**
	 * Query sql for int,   if catch excption , return -1.
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int queryForInt(String sql, Object [] params) throws Exception;
	
	/**
	 * Query sql for int  if catch excption , throws this exception .
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int queryForIntThrowsExc(String sql, Object [] params) throws Exception;
	
	/**
	 * Query sql for list.
	 * 
	 * @param sql
	 * @param params
	 * @param rowMapperResultSetExtractor
	 * @return
	 * @throws Exception
	 */
	public List query(String sql, Object params[], RowMapperResultSetExtractor rowMapperResultSetExtractor ) throws Exception ;
	
	/**
	 * Query sql for list.
	 * @param sql
	 * @param params
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public List queryForList(String sql, Object params [], Class c) throws Exception ;
	
	/**
	 *  Used for paging
	 * 
	 * @param hsql
	 * @param countSql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @param extractor
	 * @return
	 * @throws DaoException
	 */
	
	public PageList getPageList(String hsql, String countSql, 
			int pageNo, int pageSize  , Object [] params, RowMapperResultSetExtractor extractor) throws DaoException ;

	
	public JQGridContainer getGridList(String hsql, String countSql, 
			int pageNo, int pageSize  , Object [] params, RowMapperResultSetExtractor extractor) throws DaoException ;

	
	/**
	 * Used for fetching column details for an select query without having a specific row mapper class. 
	 * @param sql
	 * @param params
	 * @return map having column name as key and column value as object 
	 * @throws Exception
	 */
	public Map<String,Object> queryForMap(String sql, Object[] params) throws Exception;
	
	/**
	 * Used for batch update of records i.e. same query with different set of values
	 * @param sql
	 * @param params
	 * @return integer array with true/false status of each sql
	 * @throws Exception
	 */
	public int[] batchUpdate(String sql,List<Object[]> paramList) throws Exception;

	
}
