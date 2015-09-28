package com.basync.b2b.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * <p>A concrete implementation of {@link org.springframework.aop.interceptor.cache.CacheInterceptor}
 * which uses <a href="http://www.opensymphony.com/oscache/">OSCache</a>.</p>
 * 
 * <p>The definitions of the caches should be defined in an oscache.properties file or some 
 * other initialization method allowed by OSCache.</p>
 * 
 */
public class OSCacheInterceptor extends ConfigurableCacheInterceptor {

    private static Log log = LogFactory.getLog(OSCacheInterceptor.class);

    private Map caches = new HashMap();
    
    /**
     * @see com.basync.b2b.cache.ConfigurableCacheInterceptor#getFromCache(java.lang.String, java.lang.String, int)
     */
    protected Serializable getFromCache(String cacheName, String key, int refreshPeriod) {
        try{
            return (Serializable)getCache(cacheName).getFromCache(key, refreshPeriod);
        } catch (NeedsRefreshException exc) {
            return null;
        }
    }

    /**
     * @see com.sinocode.godzillow.cache.CacheInterceptor#putInCache(java.lang.String, java.lang.String, java.io.Serializable)
     */
    protected void putInCache(String cacheName, String key, Serializable result, String[] groups) {
        getCache(cacheName).putInCache(key, result, groups);
    }
    
    /**
     * Returns the OSCache instance that should be used for the specified method 
     * invocation.
     * 
     * @param cacheName the name of the cache
     * @return an OSCache cache isntance
     */
    @SuppressWarnings("unchecked")
    protected GeneralCacheAdministrator getCache(String cacheName) {
        cacheName = "common";
        GeneralCacheAdministrator cache = (GeneralCacheAdministrator) getCaches().get(cacheName); 
        if (cache == null) {
            cache = new GeneralCacheAdministrator();
            getCaches().put(cacheName,cache);
        }
        return cache;
    }
    
    /**
     * Flush the entire cache immediately.
     */
    public void clearCaches() {
        log.debug("Clearing OSCaches");
        Iterator iter = getCaches().values().iterator();
        while (iter.hasNext()) {
            GeneralCacheAdministrator cache = (GeneralCacheAdministrator) iter.next();
            cache.flushAll();
            if (log.isDebugEnabled()) {
                log.debug("OSCache '" + cache + "' cleared");
            }
        }
        log.debug("OSCaches cleared");
    }

    /**
     * @return the Map with the caches
     */
    protected Map getCaches() {
        return this.caches;
    }
    
    /**
     * Flushes all items that belong to the specified group.
     *
     * @param group The name of the group to flush
     */
    public void clearCaches(String group) {
        Iterator iter = getCaches().values().iterator();
        while (iter.hasNext()) {
            GeneralCacheAdministrator cache = (GeneralCacheAdministrator) iter.next();
            cache.flushGroup(group);
            
            log.info("Clearing OSCaches by group:" + group);
        }
    }

    /**
     * Flushe the items by className@methodName
     * @param IClass
     * @param methodName
     * @param userid
     */
    public void clearCaches(Class IClass, String methodName){
        clearCaches(IClass.getName() + DEFAULT_OBJECT_DISCRIMINATOR + methodName);
    }
    
    /**
     * Flushe the items by className@methodName@argument1
     * @param IClass
     * @param methodName
     * @param argument1
     */
    public void clearCaches(Class IClass, String methodName, Object argument1){
        clearCaches(IClass.getName() + DEFAULT_OBJECT_DISCRIMINATOR + methodName + DEFAULT_OBJECT_DISCRIMINATOR + getKey(argument1));
    }

    /**
     * Flushe the item specified.
     * @param IClass
     * @param methodName
     * @param arguments
     */
    public void clearCaches(Class IClass, String methodName, Object[] arguments) {
        String key= getCacheKey(IClass.getName(), methodName, arguments);
        Iterator iter = getCaches().values().iterator();
        while (iter.hasNext()) {
            GeneralCacheAdministrator cache = (GeneralCacheAdministrator) iter.next();
            cache.flushEntry(key);
            
            log.info("Clearing OSCaches by key:" + key);
        }
    }
}