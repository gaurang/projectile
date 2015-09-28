
package com.basync.b2b.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>An implementation of {@link org.springframework.aop.interceptor.cache.CacheInterceptor} 
 * which is configurable.  This implementation allows to specify a refresh (=expiration) time 
 * for each cache.</p>
 * 
 * <p>There is a default refresh period which can be set with {@link #defaultRefreshPeriod}.</p>
 * 
 * <p>A refresh period specific for a method can be set in the property 
 * {@link #refreshPeriods}}, where the key is the name of cache as defined under 
 * {@link org.springframework.aop.interceptor.cache.CacheInterceptor#getCacheName(MethodInvocation)} and 
 * the value is a String which represent the refresh period in seconds.</p>
 * 
 */
public abstract class ConfigurableCacheInterceptor extends CacheInterceptor {
    private static Log log = LogFactory.getLog(ConfigurableCacheInterceptor.class);

    private Map cacheGroups = new HashMap();
    private Properties refreshPeriods = new Properties();
    private int defaultRefreshPeriod = DEFAULT_REFRESH_PERIOD;
    
    public static final int DEFAULT_REFRESH_PERIOD = 60;
    
    /**
     * @see CacheInterceptor#getFromCache(String, String)
     */
    protected final Serializable getFromCache(String cacheName, String key) throws CacheInterceptorException {
        int refreshPeriod = getRefreshPeriod(cacheName);
        return getFromCache(cacheName, key, refreshPeriod);
    }
    
    /**
     * Same as {@link #getFromCache(String, String)}, but with a refresh period specified.
     * 
     * @param cacheName
     * @param key
     * @param refreshPeriod
     * @return the cached Object
     */
    protected abstract Serializable getFromCache(String cacheName, String key, int refreshPeriod);
    
    /**
     * @see org.springframework.aop.interceptor.cache.CacheInterceptor#putInCache(java.lang.String, java.lang.String, java.io.Serializable)
     */
    protected final void putInCache(String cacheName, String key, Serializable result) throws CacheInterceptorException {
        //int refreshPeriod = getRefreshPeriod(cacheName);
        String[] groups = getGroups(key);
        putInCache(cacheName, key, result, groups);
    }
    
    /**
     * Same as {@link #putInCache(String, String, Serializable)}, but with a refresh period specified.
     * 
     * @param cacheName
     * @param key
     * @param result
     * @param groups
     * @throws CacheInterceptorException
     */
    protected abstract void putInCache(String cacheName, String key, Serializable result, String[] groups) throws CacheInterceptorException;
    
    /**
     * @param cacheName
     * @return the refresh period for the cache
     */
    private int getRefreshPeriod(String cacheName) {
        String period = (String) this.refreshPeriods.get(cacheName);
        if (period == null) {
            if (log.isDebugEnabled()) {
                log.debug("No refresh period specified for cache '" + cacheName + "', " +
                    "using default of " + getDefaultRefreshPeriod());
            }
            return getDefaultRefreshPeriod();
        } else {
            try {
                return Integer.parseInt(period);
            } catch (NumberFormatException exc) {
                log.error("Refresh period for cache '" + cacheName + "' is not an integer, " +
                    "using default of " + getDefaultRefreshPeriod());
                return getDefaultRefreshPeriod();
            }
        }
    }
    
    private String[] getGroups(String key) {
        ArrayList<String> alist = new ArrayList<String>();
        
        int idx1 = key.indexOf(DEFAULT_OBJECT_DISCRIMINATOR);
        String className = key.substring(0, idx1);
        String cacheName;
        int idx2 = key.indexOf(DEFAULT_OBJECT_DISCRIMINATOR, idx1);
        if(idx2 > 0){
            cacheName = key.substring(0, idx2);
            int idx3 = key.indexOf(DEFAULT_ARGUMENT_DISCRIMINATOR, idx2);
            String argument1;
            if(idx3 > 0){
                argument1 = key.substring(0, idx3);
            }else{
                argument1 = key;
            }
            alist.add(argument1);
        }else{
            cacheName = key;
        }
        
        
        alist.add(className);
        alist.add(cacheName);
        
        Iterator it = cacheGroups.keySet().iterator();
        while (it.hasNext()) {
            String group = (String) it.next();
            List custom = (List) cacheGroups.get(group);
            if(custom == null) continue;
            if(custom.contains(className) || custom.contains(cacheName)){
                alist.add(group);
            }
        }
        
        return alist.toArray(new String[0]);
        /*
        String[] custom = (String[]) this.cacheGroups.get(cacheName);
        int len = custom.length;
        String[] groups = new String[len+2];
        System.arraycopy(custom, 0, groups, 0, len);
        
        groups[len] = className;
        groups[len+1] = cacheName;
        return groups;
        */
    }


    /**
     * @return the Properties with the refresh periods
     */
    public Properties getRefreshPeriods() {
        return this.refreshPeriods;
    }

    /**
     * @param refreshPeriods
     */
    public void setRefreshPeriods(Properties refreshPeriods) {
        this.refreshPeriods = refreshPeriods;
    }

    /**
     * @return the default refresh period
     */
    public int getDefaultRefreshPeriod() {
        return this.defaultRefreshPeriod;
    }

    /**
     * @param i
     */
    public void setDefaultRefreshPeriod(int i) {
        this.defaultRefreshPeriod = i;
    }

    /**
     * @return the cacheGroups
     */
    public Map getCacheGroups() {
        return cacheGroups;
    }

    /**
     * @param cacheGroups the cacheGroups to set
     */
    public void setCacheGroups(Map cacheGroups) {
        this.cacheGroups = cacheGroups;
    }


}