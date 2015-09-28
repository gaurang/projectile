/**
 * Copyright @ 2010 Basync.  
 * All rights reserved. 
 */
package com.basync.b2b.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.basync.b2b.cache.CacheInterceptor;



/**
 * This is a parent class of all controllers.
 * 
 * @author Gaurang
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractController extends MultiActionController
		implements IExceptionHandler {
	
	/** Logger for this class and subclasses */
	protected Logger logger = Logger.getLogger(getClass());
	
	public AbstractController(){
		super();
		this.setCacheSeconds(1);
	}
	
	protected CacheInterceptor cacheInterceptor;

	/**
	 * @return the cacheInterceptor
	 */
	protected CacheInterceptor getCacheInterceptor() {
		return cacheInterceptor;
	}

	/**
	 * @param cacheInterceptor the cacheInterceptor to set
	 */
	protected void setCacheInterceptor(CacheInterceptor cacheInterceptor) {
		this.cacheInterceptor = cacheInterceptor;
	}

	

}
