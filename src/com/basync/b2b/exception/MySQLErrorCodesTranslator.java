/**
 * Copyright ® 2007 Vividlime Co. Ltd. 
 * All rights reserved. 
 */
package com.basync.b2b.exception;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {

	/**
	 * Handles specified MYSQL exception
	 * 
	 */
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlex) {
    	
    	
    	logger.error("--------------------EXCEPTION SQL START---------------------------");
    	logger.error("Exception with sql errorCode :	"+sqlex.getErrorCode());
    	logger.error("Exception with sql message :	"+sqlex.getMessage());
    	logger.error("TASK :	"+task);
    	logger.error("SQL  :	"+sql);
    	logger.error("--------------------EXCEPTION SQL END-----------------------------");
        if (sqlex.getErrorCode() == -12345) {
        	
        	
            return new DeadlockLoserDataAccessException(task, sqlex);
        }
        else {
        	throw new  DaoException(sqlex);
        }
    }
}