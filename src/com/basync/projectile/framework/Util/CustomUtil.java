package com.basync.projectile.framework.Util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomUtil {
	  public static boolean contains(Collection<?> coll, Object o) {
		  	if(coll !=null){
		  		return coll.contains(o);
		  	}else{
		  		return false;
		  	}
	  }	  	
	  
	  public static boolean contains(List<?> list, Object o) {
		  	if(list !=null){
		  		return list.contains(o);
		  	}else{
		  		return false;
		  	}
	  }
	  
	 public static boolean contains(Map<?, ?> map, Object o) {
		  	if(map !=null){
		  		return map.containsKey(o);
		  	}else{
		  		return false;
		  	}
	 }

	  
}
