package com.basync.b2b.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.basync.b2b.dataobjects.RegistrationDO;
import com.basync.b2b.dataobjects.RegistrationViewDO;
import com.basync.b2b.util.ConnectionUtil;
import com.basync.b2b.util.StringUtils;

public class RegistrationDAOImpl {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	private ConnectionUtil dataSource =null;

	public void setDataSource(ConnectionUtil dataSource) {
		this.dataSource = dataSource;
	}

	public void registerUser(RegistrationDO registrationDO) throws Exception {
		Connection conn =dataSource.getConnection();
		ResultSet rs = null;
		try {
			String sql=  " Insert into tb_registration " +
					 "(loginName,"+
					 "companyName,"+
					 "brokerName,"+
					 "businessType,"+
					 "memberOfAssociation,"+
					 "cEmail,"+
					 "alternateEmail,"+
					 "webSiteName,"+
					 "comments,"+
					 "cName,"+
					 "cMobile,"+
					 "tradeCompanyName,"+
					 "contactPerson,"+
					 "phoneNo1,"+
					 "trMobile1,"+
					 "tradeCompanyName1,"+
					 "contactPerson1,"+
					 "phoneNo2,"+
					 "trMobile2,"+
					 "designation,"+
					 "address,"+
					 "city,"+
					 "state,"+
					 "country,"+
					 "pinCode,"+
					 "telphone1,"+
					 "telphone2,"+
					 "fax,"+
					 "shipName,"+
					 "shipDesignation,"+
					 "shipAddress,"+
					 "shipCity,"+
					 "shipState,"+
					 "shipCountry,"+
					 "shipPinCode,"+
					 "shipTelphone1,"+
					 "shipTelphone2,"+
					 "shipFax,"+
					 "shipMobile,"+
					 "emailUpdates," +
					 "password, " +
					 "question," +
					 "answer ) " + 
					 "values " ;
			
			 String[] elemArr = {"loginName", "companyName",
			 "brokerName", "businessType",
			 "memberOfAssociation", "cEmail",
			 "alternateEmail", "webSiteName",
			 "comments", "cName",
			 "cMobile", "tradeCompanyName",
			 "contactPerson",
			 "phoneNo1",
			 "trMobile1",
			 "tradeCompanyName1",
			 "contactPerson1",
			 "phoneNo2",
			 "trMobile2",
			 "designation",
			 "address",
			 "city",
			 "state",
			 "country",
			 "pinCode",
			 "telphone1",
			 "telphone2",
			 "fax",
			 "shipName",
			 "shipDesignation",
			 "shipAddress",
			 "shipCity",
			 "shipState",
			 "shipCountry",
			 "shipPinCode",
			 "shipTelphone1",
			 "shipTelphone2",
			 "shipFax", "shipMobile","emailUpdates", "password", "question", "answer" };
			 sql+="(";
			 for (int j = 0; j < elemArr.length; j++) {
				 if(j ==0)
					 sql += getQueryVal(elemArr[j],registrationDO,"");
				 else
					sql += getQueryVal(elemArr[j],registrationDO,",");
				}
			sql+=")"; 
			logger.debug("Registration qry " + sql );
			PreparedStatement pst = conn.prepareStatement(sql);
			
			int rowIns= pst.executeUpdate();
			
			sql = "select LAST_INSERT_ID()";
			pst =conn.prepareStatement(sql);
			rs= pst.executeQuery();
			int id= 0;
			if(rs.next())
				id=rs.getInt(1);
			
			sql = "insert into tb_usertable (userName, password, email, deleteflag, companyName, status,roleID, termId,registrationId) values (" +
					"?," +
					"md5(?),"+
					"?,"+
					"0  ,"+
					"?,"+
					"0,"+
					"1,"+
					"1,"+
					""+ id+""+
					")";
			pst =conn.prepareStatement(sql);
			pst.setString(1,registrationDO.getLoginName());
			pst.setString(2,registrationDO.getPassword());
			pst.setString(3,registrationDO.getcEmail());
			pst.setString(4,registrationDO.getCompanyName());
			
			int z= pst.executeUpdate();
			logger.debug("Registration created for " + rowIns + " person");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public RegistrationDO updateUser(RegistrationDO registrationDO){
		Connection conn = dataSource.getConnection();
		try {
			 String[] elemArr = { "companyName",
					 "brokerName", "businessType",
					 "memberOfAssociation", "cEmail",
					 "alternateEmail", "webSiteName",
					 "comments", "cName",
					 "cMobile", "tradeCompanyName",
					 "contactPerson",
					 "phoneNo1",
					 "trMobile1",
					 "tradeCompanyName1",
					 "contactPerson1",
					 "phoneNo2",
					 "trMobile2",
					 "designation",
					 "address",
					 "city",
					 "state",
					 "country",
					 "pinCode",
					 "telphone1",
					 "telphone2",
					 "fax",
					 "shipName",
					 "shipDesignation",
					 "shipAddress",
					 "shipCity",
					 "shipState",
					 "shipCountry",
					 "shipPinCode",
					 "shipTelphone1",
					 "shipTelphone2",
					 "shipFax", "shipMobile","emailUpdates" };
			
			 String sql = "update tb_registration set ";
				 for (int j = 0; j < elemArr.length; j++) {
					 if(j ==0)
						sql += elemArr[j]+"="+ getQueryVal(elemArr[j],registrationDO,"");
					 else
						sql +=", "+ elemArr[j]+"="+getQueryVal(elemArr[j],registrationDO,"");
					}
			sql+=  " where id = "
				 + registrationDO.getID();
			PreparedStatement pst = conn
					.prepareStatement(sql);
			
			int i = pst.executeUpdate();
			logger.debug("$$$$$$$$$$$$$$ update Profile"+i);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error in update Profile");
		}
		return registrationDO;
	}
	public List<RegistrationViewDO>getUserView() {
		Connection conn =dataSource.getConnection();
		ResultSet rs = null;
		List<RegistrationViewDO> list  = new ArrayList<RegistrationViewDO>();
		try {
			RegistrationViewDO registrationViewDO =null;
			PreparedStatement pst = conn.prepareStatement("select * from registrationView");
			rs = pst.executeQuery();
			while(rs.next()){
				//set remaining fields here
				registrationViewDO =new RegistrationViewDO();
				registrationViewDO.setCName(rs.getString("Cname"));
				list.add(registrationViewDO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	public RegistrationDO getUserDetails(int userId) {
		Connection conn =dataSource.getConnection();
		ResultSet rs = null;
		RegistrationDO registrationDO =null;
		try {
			PreparedStatement pst = conn.prepareStatement("select * from tb_registration where ID = (select registrationId from tb_usertable where ID ="+userId+")");
			rs = pst.executeQuery();
			if(rs.next()){
				 String[] elemArr = {"ID","loginName", "companyName",
						 "brokerName", "businessType",
						 "memberOfAssociation", "cEmail",
						 "alternateEmail", "webSiteName",
						 "comments", "cName",
						 "cMobile", "tradeCompanyName",
						 "contactPerson",
						 "phoneNo1",
						 "trMobile1",
						 "tradeCompanyName1",
						 "contactPerson1",
						 "phoneNo2",
						 "trMobile2",
						 "designation",
						 "address",
						 "city",
						 "state",
						 "country",
						 "pinCode",
						 "telphone1",
						 "telphone2",
						 "fax",
						 "shipName",
						 "shipDesignation",
						 "shipAddress",
						 "shipCity",
						 "shipState",
						 "shipCountry",
						 "shipPinCode",
						 "shipTelphone1",
						 "shipTelphone2",
						 "shipFax", "shipMobile","emailUpdates" };
				//set remaining fields here
				registrationDO =new RegistrationDO();
				for (int i = 0; i < elemArr.length; i++) {
					Field f = RegistrationDO.class.getDeclaredField(elemArr[i]);
					f.setAccessible(true);
					f.set(registrationDO, rs.getString(elemArr[i]));
				}
				
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return registrationDO;
	}
	public String getQueryVal(String elem,RegistrationDO rd, String saperator) throws Exception{
			
			Field f = RegistrationDO.class.getDeclaredField(elem);
			
			f.setAccessible(true);
			Object val = f.get(rd); 
			if(val ==null || StringUtils.isEmpty(val.toString()))
				return saperator+"  null";
			else if(NumberUtils.isNumber(val.toString()))
				return saperator+" "+val.toString() ;
			else{
				String valStr = val.toString();
				if(valStr.contains("'")){
					valStr = valStr.replaceAll("'", "''");
				}
				return saperator+" '"+valStr+"'";
			}
	}
	public String getObjVal(String elem,RegistrationDO rd, String saperator) throws Exception{
		
		Field f = RegistrationDO.class.getDeclaredField(elem);
		
		f.setAccessible(true);
		Object val = f.get(rd); 
		logger.debug("$$$$$$$$"+val);
		if(val ==null || StringUtils.isEmpty(val.toString()))
			return saperator+"  null";
		else 
			return saperator+" "+val.toString() ;
}
}
