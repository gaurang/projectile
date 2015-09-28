package com.basync.b2b.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;



public class JQGridFormatterUtil {

	private static final Logger logger = Logger
			.getLogger(JQGridFormatterUtil.class);

	public static String getJSON(int currentPageNo,
			int totalRecords, Set<Object> objectsToBeAdded,
			List<String> orderedPropertyNames) {

		Integer pages = 0;

		if(totalRecords % 50 >0){
			pages = (totalRecords/50)+1;
		}
		else{
			pages = (totalRecords/50);
		}

		JQGridContainer container = new JQGridContainer();
		container.setPage(currentPageNo);
		container.setTotal(pages);
		container.setRecords(totalRecords);
		List<JQGridRow> rows = new ArrayList<JQGridRow>();
		if (!objectsToBeAdded.isEmpty()) {
			for (Object obj : objectsToBeAdded) {
				JQGridRow row = new JQGridRow();
				row.setId(new Integer(getPropertvalue(obj, "id")));
				List<String> cells = new ArrayList<String>();
				for (String propertyName : orderedPropertyNames) {
					cells.add(getPropertvalue(obj, propertyName));
				}
				row.setCell(cells);
				rows.add(row);
			}
		}
		container.setRows(rows);
		return JSONUtil.convertToJSON(container);

	}

	private static String getPropertvalue(Object bean, String propName) {
		String val = null;
		try {
			val = ObjectUtils.toString(PropertyUtils.getProperty(bean, propName));
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		} catch (NoSuchMethodException e) {
			logger.error(e);
		}
		return val;
	}

}
