package com.basync.b2b.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.basync.b2b.jobs.StockDownload;

public class ConnectionUtil {
	Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	private String connectionUrl;
	private String driverClass;
	private String userName;
	private String password;
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Connection getConnection(){
		Connection conn =null;
		try {
			System.out.println("Connection "+driverClass);
			Class.forName(driverClass);
			conn = DriverManager.getConnection(connectionUrl,userName,password);
		} catch (Exception e) {
			logger.error("Connection timeout",e);
		}
		return conn;
		
	}

}
