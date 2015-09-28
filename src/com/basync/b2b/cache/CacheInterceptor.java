
/**
 * Copyright @ 2010  Basync. 
 * All rights reserved. 
 */
package com.basync.b2b.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 
 * An AOP interceptor which caches the results of a method invocation.  
 * The arguments that are passed to the method determine if the method is actually 
 * invoked or if a cached object from a previous invocation of the method is returned.
 * 

 * @see #getCacheKey(Object, Object[])
 * @see #invoke(MethodInvocation)
 * @see org.springframework.aop.interceptor.cache.EHCacheInterceptor
 * @see org.springframework.aop.interceptor.cache.MemoryCacheInterceptor
 * 
 */
@SuppressWarnings("unchecked")
public abstract class CacheInterceptor implements MethodInterceptor,
		InitializingBean, ApplicationListener {

	private static Log log = LogFactory.getLog(CacheInterceptor.class);

	/**
	 * <p>When true, default classes like <code>String</code>, <code>Number</code> 
	 * and <code>Boolean</code> are allowed in the cache key.
	 * 
	 * <p>The list of default identifiers is specified by {@link #DEFAULT_IDENTIFIERS}.
	 * 
	 * <p>The {@link #DEFAULT_IDENTIFIERS_METHOD} method of these classes is used as 
	 * their identifier (by default this is <code>toString</code>).
	 * 
	 * <p><code>true</code> by default.
	 */
	private boolean useDefaultIdentifiers = true;

	/**
	 * <p>If you want to specify additional classes that are allowed to be added 
	 * to the cache key, add them here.
	 * <p>The key should be a class name, the value of the property should be a public 
	 * no-argument method in the class which returns a <code>String</code>.
	 * <p>Example
	 * <pre>
	 * <props>
	 *     <prop key="com.company.Person">getId</prop>
	 *     <prop key="com.company.SearchResult">getKeywords</prop>
	 * </props>
	 * </pre>
	 */
	private Properties identifiers;

	/**
	 * The identifier method that is used for the default identifiers.
	 */
	private String defaultIdentifiersMethod = DEFAULT_IDENTIFIERS_METHOD;

	/**
	 * The default identifier method that is used for the default identifiers.
	 */
	private static final String DEFAULT_IDENTIFIERS_METHOD = "toString";

	/**
	 * The discriminator used in the cache key to separate different arguments.
	 * "-" by default.
	 */
	private String argumentDiscriminator = DEFAULT_ARGUMENT_DISCRIMINATOR;

	/**
	 * The default object discriminator.
	 */
    protected static final String DEFAULT_OBJECT_DISCRIMINATOR = "@";
    
	/**
	 * The default argument discriminator.
	 */
    protected static final String DEFAULT_ARGUMENT_DISCRIMINATOR = "-";

	/**
	 * A variable used by this class that provides a <code>java.lang.Class</code> to 
	 * method mapping.
	 */
	private Map identifiersClasses;

	/**
	 * The list of default identifiers.
	 */
	private static final Class[] DEFAULT_IDENTIFIERS = new Class[] {
			String.class, Number.class, Boolean.class };

	/**
	 * Initializes the identifiersClasses variable which provides a <code>java.lang.Class</code> to 
	 * method mapping.
	 * 
	 * @throws ClassNotFoundException when a class in <code>identifiers</code> could not be found
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
    public void afterPropertiesSet() throws Exception {

		this.identifiersClasses = new HashMap();

		if (isUseDefaultIdentifiers()) {
			for (int i = 0; i < DEFAULT_IDENTIFIERS.length; i++) {
				this.identifiersClasses.put(DEFAULT_IDENTIFIERS[i], getDefaultIdentifiersMethod());
			}
		}

		if (getIdentifiers() != null) {

			Iterator iter = getIdentifiers().keySet().iterator();
			while (iter.hasNext()) {
				String className = (String) iter.next();
				String method = getIdentifiers().getProperty(className);
				this.identifiersClasses.put(Class.forName(className), method);
			}

		}
	}

	/**
	 * <p>Surrounds the method specified by <code>invocation</code>. Returns the result of a normal  
	 * invocation to the method when there is no result for this method in the cache and puts that 
	 * object in the cache.
	 * 
	 * <p>When there is a cached result found for this method, the method is not invoked and the cached 
	 * object is returned.
	 * 
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String cacheName = getCacheName(invocation);
		String key = getCacheKey(invocation.getThis(), invocation.getArguments());
        key = cacheName + key;
		Serializable result = getFromCache(cacheName, key);

		if (result == null) {
			log.debug("Invoking method Cache key: " + key);
			result = (Serializable) invocation.proceed();
			putInCache(cacheName, key, result);
		} else {
			log.debug("Returning cached data for key: " + key);
		}

		return result;
	}

	/**
	 * Return the Object that corresponds to an invocation of <code>invocation</code>, under 
	 * the key <code>key</code>.
	 * If the Object does not exist in the cache, return null.
	 * 
	 * @param cacheName the name of the cache that is used
	 * @param key the key under which the object should be found in the cache
	 * @return the Object if it exists in the cache, <code>null</code> otherwise
	 * @throws CacheInterceptorException when the Object could not be fetched from the cache
	 */
	protected abstract Serializable getFromCache(String cacheName, String key)
			throws CacheInterceptorException;

	/**
	 * Store the Object that corresponds to an invocation of <code>invocation</code> under 
	 * the key <code>key</code>.
	 * 
	 * @param cacheName the name of the cache that is used
	 * @param key the key under which the object must be put in the cache
	 * @param result the object that must be put in the cache
	 * @throws CacheInterceptorException when the Object could not be put in the cache
	 */
	protected abstract void putInCache(String cacheName, String key, Serializable result)
			throws CacheInterceptorException;

	/**
	 * <p>Returns a cache name suitable for the specified method invocation. This is: 
	 * "name of the class" + "@" + "name of the method".
	 * <p>This can be used by subclasses to use different caches for different methods.
	 * 
	 * @param invocation the invocation that we want to cache
	 * @return a cache name suitable for the specified method invocation
	 */
	protected String getCacheName(MethodInvocation invocation) {
		return invocation.getMethod().getDeclaringClass().getName() + DEFAULT_OBJECT_DISCRIMINATOR
				+ invocation.getMethod().getName();
	}

	/**
	 * <p>Returns a cache key that can be used to identify an invocation of a method. The 
	 * key is based on the arguments that are passed on to the method.  Which arguments 
	 * are used to discriminate different calls to the same method depends on 
	 * {@link #isUseDefaultIdentifiers()} and {@link #getIdentifiers()}.
	 * 
	 * <p>If {@link #isUseDefaultIdentifiers()} is true, then the classes listed under 
	 * {@link #DEFAULT_IDENTIFIERS} will be included in the key.  Their value will be 
	 * determined by {@link #DEFAULT_IDENTIFIERS_METHOD}.
	 * 
	 * <p>In reality this means that classes like String, Number (=Integer, Float, ...) and Boolean 
	 * will be used to discriminate between calls to the same method. A call to the cached method
	 * <code>getSearchResults(String keywords)</code> for example will have a different key if 
	 * the keywords parameter is different.
	 * 
	 * <p>If you want to cache a method and discriminate calls by non-standard arguments, you need 
	 * to specify those with the <code>identifiers</code> property. For example, suppose you have a method 
	 * like <code>getSearchResults(Query)</code> and want to return the same results if the 
	 * <code>Query</code> object has the same value for <code>getKeywords</code>, then you need to add 
	 * the name of the <code>Query</code> class to the <code>identifiers</code> property.
	 * 
	 * @param target the object on which the method will be invoked
	 * @param arguments the arguments of the method that will be cached
	 * @return a String which can be used to uniquely identify the <code>arguments</code> array
	 * @throws CacheInterceptorException
	 */
	protected String getCacheKey(Object target, Object[] arguments)
			throws CacheInterceptorException {

		StringBuffer result = new StringBuffer();

		/*
		 * Add the identity hash code for the object
		 */
		//result.append(ObjectUtils.getIdentityHexString(target));

		if (arguments != null) {
			result.append(DEFAULT_OBJECT_DISCRIMINATOR);
			for (int i = 0; i < arguments.length; i++) {
				// A separator between the keys for each argument
				if (i > 0) {
					result.append(DEFAULT_ARGUMENT_DISCRIMINATOR);
				}
				result.append(getKey(arguments[i]));
			}
		}

		return result.toString();
	}

    protected String getCacheKey(String className, String methodName, Object[] arguments){
        StringBuffer result = new StringBuffer();

        result.append(className);
        result.append(DEFAULT_OBJECT_DISCRIMINATOR);
        result.append(methodName);

        if (arguments != null) {
            result.append(DEFAULT_OBJECT_DISCRIMINATOR);
            for (int i = 0; i < arguments.length; i++) {
                // A separator between the keys for each argument
                if (i > 0) {
                    result.append(DEFAULT_ARGUMENT_DISCRIMINATOR);
                }
                result.append(getKey(arguments[i]));
            }
        }

        return result.toString();
    }
    
	/**
	 * <p>Returns a key suitable for the object.  This is the toString() of the Object 
	 * that is returned by an invocation of the method specified as the value for a class 
	 * in <code>allowedClassNames</code>.  The returned Object must be a String, Number 
	 * or Boolean. 
	 * <p>
	 * If <code>object</code> is null, the empty String <code>""</code> will be returned.
	 *  
	 * @param object
	 * @return a key for the object
	 * @throws CacheInterceptorException
	 */
	protected String getKey(Object object) throws CacheInterceptorException {

		if (object == null) {
			return "";
		}

		StringBuffer result = new StringBuffer();

		/*
		 * Will be true if at least one class has been found that matches.
		 */
		boolean matchFound = false;

		if (getIdentifiersClasses() != null) {
			Iterator iter = getIdentifiersClasses().entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Class clazz = (Class) entry.getKey();

				if (clazz.isAssignableFrom(object.getClass())) {
					String method = (String) entry.getValue();
					Object key = null;
					try {
						key = MethodUtils.invokeMethod(object, method, null);
					} catch (Exception exc) {
						throw new CacheInterceptorException("Could not invoke the method '"
								+ method
								+ "' "
								+ "in class '"
								+ clazz.getName() + "' without parameters", exc);
					}
					if (!(key instanceof String || key instanceof Number || key instanceof Boolean)) {
						throw new CacheInterceptorException("The method '"
								+ method
								+ "' in class '"
								+ clazz.getName()
								+ "' does not return a String, Number or Boolean");
					}
					result.append(key);
					matchFound = true;

				}
			}
		}

		/*
		 * If no class or interface could be found, then the argument isn't retained 
		 * in the cache key.
		 */
		if (!matchFound) {
			try {
				Object key = MethodUtils.invokeMethod(object, getDefaultIdentifiersMethod(), null);
				result.append(key);
				log.debug("No class or interface could be found that is allowed "
						+ "for class "
						+ object.getClass().getName()
						+ " return key:" + key.toString());
			} catch (Exception exc) {
				throw new CacheInterceptorException("Could not invoke the method '"
						+ getDefaultIdentifiersMethod()
						+ "' "
						+ "in class '"
						+ object.getClass().getName() + "' without parameters", exc);
			}
		}

		return result.toString();
	}

	/**
	 * Listens for a {@link CacheRefreshNeededEvent} and flushes the caches 
	 * when this event is published.
	 * 
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof CacheRefreshNeededEvent) {
			clearCaches();
		}
	}

	/**
	 * Clear all caches.
	 */
    public abstract void clearCaches();
    
    /**
     * Flushes all items that belong to the specified group.
     *
     * @param group The name of the group to flush
     */
    public abstract void clearCaches(String group);

    
    /**
     * Flushe the items by className@methodName
     * @param IClass
     * @param methodName
     */
    public abstract void clearCaches(Class IClass, String methodName);
    
    /**
     * Flushe the items by className@methodName@argument1
     * @param IClass
     * @param methodName
     * @param userid
     */
    public abstract void clearCaches(Class IClass, String methodName, Object argument1);

    /**
     * Flushe the item specified.
     * @param IClass
     * @param methodName
     * @param arguments
     */
    public abstract void clearCaches(Class IClass, String methodName, Object[] arguments);

	/**
	 * @return Returns the identifiers.
	 */
	public Properties getIdentifiers() {
		return this.identifiers;
	}

	/**
	 * @param identifiers The identifiers to set.
	 */
	public void setIdentifiers(Properties identifiers) {
		this.identifiers = identifiers;
	}

	/**
	 * @return Returns the useDefaultIdentifiers.
	 */
	public boolean isUseDefaultIdentifiers() {
		return this.useDefaultIdentifiers;
	}

	/**
	 * @param useDefaultIdentifiers The useDefaultIdentifiers to set.
	 */
	public void setUseDefaultIdentifiers(boolean useDefaultIdentifiers) {
		this.useDefaultIdentifiers = useDefaultIdentifiers;
	}

	/**
	 * @return Returns the identifiersClasses.
	 */
	protected Map getIdentifiersClasses() {
		return this.identifiersClasses;
	}

	/**
	 * @return Returns the argumentDiscriminator.
	 */
	public String getArgumentDiscriminator() {
		return this.argumentDiscriminator;
	}

	/**
	 * @param argumentDiscriminator The argumentDiscriminator to set.
	 */
	public void setArgumentDiscriminator(String argumentDiscriminator) {
		this.argumentDiscriminator = argumentDiscriminator;
	}

	/**
	 * @return Returns the defaultIdentifiersMethod.
	 */
	public String getDefaultIdentifiersMethod() {
		return this.defaultIdentifiersMethod;
	}

	/**
	 * @param defaultIdentifiersMethod The defaultIdentifiersMethod to set.
	 */
	public void setDefaultIdentifiersMethod(String defaultIdentifiersMethod) {
		this.defaultIdentifiersMethod = defaultIdentifiersMethod;
	}
}
