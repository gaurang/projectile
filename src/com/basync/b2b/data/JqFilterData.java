package com.basync.b2b.data;

import java.util.HashMap;
import java.util.List;

public class JqFilterData {
	
	
	private String groupOp;
	List<HashMap<String, String>> rules;
	
	
	/**
	 * @return the groupOp
	 */
	public String getGroupOp() {
		return groupOp;
	}
	/**
	 * @param groupOp the groupOp to set
	 */
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	/**
	 * @return the rules
	 */
	public List<HashMap<String, String>> getRules() {
		return rules;
	}
	/**
	 * @param rules the rules to set
	 */
	public void setRules(List<HashMap<String, String>> rules) {
		this.rules = rules;
	}
	
	

}
