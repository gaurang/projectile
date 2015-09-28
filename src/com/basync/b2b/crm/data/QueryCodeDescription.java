package com.basync.b2b.crm.data;

import java.io.Serializable;
import java.math.BigDecimal;

import com.basync.b2b.data.QueryDescription;

public class QueryCodeDescription implements Serializable {

		public QueryCodeDescription() {
			super();
			// TODO Auto-generated constructor stub
		}
		public QueryCodeDescription(String id, String description) {
				super();
				this.id = id;
				this.description = description;
		}
		private static final long serialVersionUID = 3814686946860149227L;
		
		private String id; 
		private String description ;
		private String accountName;
		private BigDecimal exRate;
		private String accountCode;
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		
		/**
		 * 
		 * @return accountName
		 */
		public String getAccountName() {
			return accountName;
		}
		
		/**
		 * 
		 * @param accountName
		 */
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		
		/**
		 * 
		 * @return exRate
		 */
		public BigDecimal getExRate() {
			return exRate;
		}
		/**
		 * 
		 * @param exRate
		 */
		public void setExRate(BigDecimal exRate) {
			this.exRate = exRate;
		}
		
		/**
		 * 
		 * @return accountCode
		 */
		public String getAccountCode() {
			return accountCode;
		}
		/**
		 * 
		 * @param accountCode
		 */
		public void setAccountCode(String accountCode) {
			this.accountCode = accountCode;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this.id.equals(((QueryCodeDescription)obj).getId()))
				return true;
			else
				return false;
		}

}
