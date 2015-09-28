package com.basync.b2b.jobs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import au.com.bytecode.opencsv.CSVReader;

import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.dao.StockUploadDAOImpl;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.StockMasterDO;
import com.basync.b2b.dataobjects.StockPRPDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.impl.CommonServiceImpl;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.ConnectionUtil;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;

public class StockDownload  extends TimerTask {
	protected Logger logger = Logger.getLogger(getClass());

	String mailMsg="";
	int errorcnt =0;
	private StockUploadDAOImpl stockUploadDAO;
	private MailSenderPooled mailSender;
	int totSIze =0;
	
	
	
	public static void main(String[] args) throws Exception{
		Logger logger = Logger.getLogger(StockDownload.class);
		StockDownload stockDownlaod = new StockDownload();
	    stockDownlaod.getRapnetFile();
	}
	
	public void getRapnetFile()	{
		Logger logger = Logger.getLogger(StockDownload.class);
		logger.info("In rap File Download " +(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		try {
			CommonServiceImpl	csImpl = new CommonServiceImpl();
			String filePath = csImpl.getPropertiesByName("b2b.upload.path")+"stockdata.csv";
			
			logger.info("Before read file------------------size "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
			String url = csImpl.getPropertiesByName("b2b.rapnet.url");
			String authUrl = csImpl.getPropertiesByName("b2b.rapnet.auth.url");
			String username = csImpl.getPropertiesByName("b2b.rapnet.username");
			String password = csImpl.getPropertiesByName("b2b.rapnet.password");
			
			logger.info("before Auth------------------size "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
			String authenticationTicket = getAuthenticationTicket(username, password, authUrl);
			logger.info("authenticationTicket  "+authenticationTicket);
			// Execute the POST method and get File
			getData(new URL(url), authenticationTicket, filePath);
			      
			uploadStockFile(filePath);
			      

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadStockFile(String fileName) throws Exception {
 		
		mailMsg += "<br/>"+ fileName +"<br/>";
		
		ConnectionUtil dataSource = new ConnectionUtil();
		CommonServiceImpl cmpl = new CommonServiceImpl("/appConfig.properties");
		
		dataSource.setConnectionUrl(cmpl.getPropertiesByName("jdbc.url"));
		dataSource.setDriverClass(cmpl.getPropertiesByName("jdbc.driverClassName"));
		dataSource.setUserName(cmpl.getPropertiesByName("jdbc.username"));
		dataSource.setPassword(cmpl.getPropertiesByName("jdbc.password"));
		
		logger.debug("###############"+cmpl.getPropertiesByName("jdbc.username"));
		mailSender = new MailSenderPooled();
		stockUploadDAO = new StockUploadDAOImpl();
		stockUploadDAO.setDataSource(dataSource);
		PurchaseMaster pm = new PurchaseMaster();
		pm.setVendorId(0);
		
		List<StockPRPDO> stockPRPList = new ArrayList<StockPRPDO>();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		try {
			List<String> doPropertyList = stockUploadDAO.getColumns(2);
			HashMap<String, String> hMap = stockUploadDAO.getPrpValMap();
			logger.debug("##################$$$"+fileName);
			String[] cell;
			File file = new File(fileName);
			logger.debug("##################$$$ FILE PRESENT => "+file.exists());
			CSVReader reader = new CSVReader(new FileReader(fileName));
			
			logger.info("##################$$$ FILE Reqder => "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
			
				
				List<String[]> myEntries = new ArrayList<String[]>();
				
				myEntries = reader.readAll();
				
				logger.info("##################$$$ FILE PRESENT AFTER=> "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
			//	logger.debug("##################"+myEntries.size());
				String property;
				boolean setField = false;
			//	arrMethod = new StockMasterDO().getClass().getMethods();
			//	arrMethod1 = new StockPRPDO().getClass().getMethods();
				int pkIdx  = doPropertyList.indexOf("pktCode");
				UserSession userSession = new UserSession();
				for(int j=1;j<myEntries.size();j++){
					//logger.debug("INNNNNNNNNNNNNNNNNNNNNNNN");
					logger.debug("INNNNNNNNNNNNNNNNNNNNNNNN  "+pkIdx);
					Object stockMasterDO = new StockMasterDO();
					Object stockPRPDO = new StockPRPDO();
					cell = (String[])myEntries.get(j);
					
					if(StringUtils.isEmpty(cell[pkIdx]))
						continue;
					//if(!NumberUtils.isNumber(cell[pkIdx]))
					//	continue;
					String pktId =cell[pkIdx];
					logger.debug("INNNNNNNNNNNNNNNNNNNNNNNN");
					
					
					for (int propertyIndex=0;propertyIndex< doPropertyList.size();propertyIndex++) {
						setField = false;
						property = doPropertyList.get(propertyIndex);	
						if(StringUtils.isEmpty(property))
							continue;
						//logger.debug("#### ----------prp = "+property);
						 try{
								Field f = StockMasterDO.class.getDeclaredField(property);
								setField = setField(f, stockMasterDO, cell[propertyIndex]);
							//	logger.debug("#### ----------prp in MASTER = "+property +" and "+cell[propertyIndex] );
						 }catch (NoSuchFieldException e) {
								logger.error("\nNO SUCH FIELD SO MOVE ON");
						}
						 catch (Exception e) {
								logMsg(pktId, property, cell[propertyIndex]);
								setField= true;
						 }
						if (!setField) {
							logger.debug("$$$$$$$$$$$  pktId: "+ pktId +"  Prp:"+property);
							
							String[] prpArr =null;
							if(StringUtils.isContainsSpecialChar(property))
								prpArr = StringUtils.splitSpecialChar(property);
								
							if(property.equalsIgnoreCase("cts")){
								//logger.debug("########### Cell val CTS "+(cell[propertyIndex]));
								String sz =stockUploadDAO.getSizes(CommonUtil.getDoubleValue(cell[propertyIndex]));
								((StockPRPDO)stockPRPDO).setSZ(sz);
								((StockPRPDO)stockPRPDO).setSZ_so(Double.valueOf(getSortVal(hMap, "SZ"+"_"+sz, sz)));
							}
							if(prpArr == null){
								
								
								try{
									Field fp = StockPRPDO.class.getDeclaredField(property);
									setField(fp, stockPRPDO, cell[propertyIndex]);
									Field fpso = null;
									fpso = StockPRPDO.class.getDeclaredField(property+"_so");
									setField(fpso, stockPRPDO,  getSortVal(hMap, property+"_"+cell[propertyIndex], cell[propertyIndex]));
								}
								catch (NoSuchFieldException e) {
									logger.error("NO SUCH FIELD SO MOVE ON");
								}catch (Exception e) {
									logMsg(pktId, property, cell[propertyIndex]);
								}
							}else{
								for (int i = 0; i < prpArr.length; i++) {
									logger.debug("$$$$$$$$$$$  pktId: "+ pktId +"  PrpArr: "+prpArr.length);
									String prp =prpArr[i];
									String[] cellValues =   StringUtils.splitSpecialChar(cell[propertyIndex]);
									try{
										Field fp = StockPRPDO.class.getDeclaredField(prp);
										setField(fp, stockPRPDO, cellValues[i]);
										Field fpso = null;
										
										fpso = StockPRPDO.class.getDeclaredField(prp+"_so");
										setField(fpso, stockPRPDO, getSortVal(hMap, prp+"_"+cellValues[i], cellValues[i]));
									}
									catch (NoSuchFieldException e) {
										logger.error("NO SUCH FIELD SO MOVE ON");
									}
									catch (Exception e) {
										logMsg(pktId, prp, cellValues[i]);
									}
								}
								
							}	
						}
					}	
					logger.info("uploadStock------------------size "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
					stockMasterList.add((StockMasterDO)stockMasterDO);
					stockPRPList.add((StockPRPDO)stockPRPDO);
					if(stockMasterList.size() > 300){
						int countEx = stockUploadDAO.uploadStock(stockMasterList,stockPRPList, userSession,pm,0);
						logger.info("uploadStock------------------size "+stockMasterList.size());
						totSIze += stockMasterList.size();
						stockMasterList = new ArrayList<StockMasterDO>();
						stockPRPList = new ArrayList<StockPRPDO>();
						logger.info("uploadStock------------------size Clear "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
					}
				
			}
		int countEx = stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession,pm,0);
		logger.info("uploadStock------------------size "+stockMasterList.size());
		totSIze += stockMasterList.size();
		stockUploadDAO.deleteSold();	
		if(errorcnt>0)
			mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload";
		else
			mailMsg+="<br/> File Uploaded Successfully";
		String email ="admin@shawnintl.com";
		
		mailSender.setMailSender(new JavaMailSenderImpl());
		
		mailSender.general_send_mail(email, mailMsg, "SHAWN ADMIN : Stock File Uploaded  ");
		
		} catch (Exception e) {
			String email ="admin@shawnintl.com";
			e.printStackTrace();
			mailSender.general_send_mail(email,fileName+" Upload problem <br/>"+e.toString(), "ERROR IN UPLOADING FILE");
			throw new Exception();
		}
	}	

	private String getSortVal(HashMap hm,String key, String value){
	//	logger.debug("################ >SHAPE  "+key);
		if(NumberUtils.isNumber(value)){
			return value;
		}
		if(hm.containsKey(key.trim()))
			return hm.get(key.trim()).toString();
		else 
			return "0";	
	}
	private boolean setField(Field f,Object object,String cell)throws Exception {
		boolean setField =false;
		//logger.debug("################ >type"+f.getType());
				try {
					f.setAccessible(true);
					if (f.getType().isInstance(CommonUtil
							.getDate("01/01/1900"))) {
						setField = true;
						f.set(object, CommonUtil.getDate(cell
								));
					} else if (f.getType()
							.isInstance(new Long(0))) {
						setField = true;
						f.set(object, CommonUtil.getLongValue(cell
								));
					} else if (f.getType()
							.isInstance(CommonUtil.getTimestamp("01/01/1900"))) {
						setField = true;
						f.set(object, CommonUtil.getTimestamp(cell
								));
					} else if (f.getType()
							.isInstance(new Short((short) 0))) {
						setField = true;
						f.set(object, CommonUtil.getShortValue(cell
								));
					} else if (f.getType()
							.isInstance(new Double(0))) {
						setField = true;
						f.set(object, CommonUtil.getDoubleValue(cell
								));
					} else if (f.getType()
							.isInstance(new Float(0))) {
						setField = true;
						f.set(object, CommonUtil.getFloatValue(cell
								));
					} else if (f.getType()
							.isInstance(new Integer(0))) {
						setField = true;
						f.set(object, CommonUtil.getIntegerValue(cell
								));
					} else if (f.getType()
							.isInstance(new String())) {
						setField = true;
						//logger.debug("#### ----------in set FIELD = "+setField);
						f.set(object, CommonUtil.getStringValue(cell
								));
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.info(e.toString());
					throw new Exception();
					
				}
				return setField;
		}
	
	public void logMsg(String id, String prp,String val){
		
		mailMsg += "<br/>";
		mailMsg += "Pkt Code  :  "+id +"  |  Field  :  "+prp +" | value  :  "+val;
		errorcnt++;
	}


	@Override
	public void run() {
		
		Logger logger = Logger.getLogger(StockDownload.class);
		logger.info("Staring process for Stock Download.....");
		StockDownload sd= new StockDownload();
		sd.getRapnetFile();
	}
	
	

	/**
	* Gets the data
	*
	* @param url
	*
	eg

	*
	http://technet.rapaport.com/HTTP/RapLink/download.aspx?CaratSizeFrom

	*
	=0.30&CaratSizeTo=5

	* @param ticket
	*
	authentication ticket

	* @throws IOException
	*/
	private void getData(final URL url, final String ticket, String filePath) throws IOException {
		final String data = URLEncoder.encode("ticket", "UTF-8").concat("=").concat(URLEncoder.encode(ticket, "UTF-8"));
		final URLConnection connection = url.openConnection();
	
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept-Encoding", "gzip");
	
		final OutputStream outputStream = connection.getOutputStream();
		final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
	
		outputStreamWriter.write(data);
		outputStreamWriter.flush();
	
		final InputStream replyStream = connection.getInputStream();
		final InputStreamReader inputStreamReader;
	
		// The following is to check if the server sending the response supports
		// Gzip
		final String contentEncodingField = connection.getHeaderField("Content-Encoding");
		if (contentEncodingField != null && contentEncodingField.equalsIgnoreCase("gzip")) {
		// read the gzipped format
		final GZIPInputStream gzipInputStream = new GZIPInputStream(replyStream);
		inputStreamReader = new InputStreamReader(gzipInputStream);
		} else {
		inputStreamReader = new InputStreamReader(replyStream);
		}
	
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		OutputStreamWriter out= new OutputStreamWriter(new FileOutputStream(filePath));
		String line;
		logger.info("BEFORE READING DATA------------------size Clear "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		while ((line = reader.readLine()) != null) {
			//logger.debug(line);
			out.write(line +"\n ");
		}
		logger.info("AFTER READING DATA-----------------size Clear "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		outputStreamWriter.close();
		
		logger.info("AFTER CLOSE OUT------------------size Clear "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		reader.close();
	}
	
	
	/**
	* Get the ticket
	*
	* @param username
	* @param password
	* @return
	* @throws IOException
	*/
	private String getAuthenticationTicket(final String username, final String password,final String urlStr) throws IOException {
	final URL url = new URL(urlStr);
	final String data = URLEncoder.encode("username", "UTF-8").concat("=")
	.concat(URLEncoder.encode(username, "UTF-8")).concat("&")
	.concat(URLEncoder.encode("password", "UTF-8")).concat("=")
	.concat(URLEncoder.encode(password, "UTF-8"));

	
	logger.info("getAuthenticationTicketssss------------------size "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
	final URLConnection connection = url.openConnection();
	connection.setDoOutput(true);
	final OutputStream outputStream = connection.getOutputStream();
	final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
	outputStreamWriter.write(data);
	outputStreamWriter.flush();

	// Get the response
	final BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

	final StringBuffer stringBuffer = new StringBuffer();
	String line;
	while ((line = rd.readLine()) != null) {
	stringBuffer.append(line);
	}
	outputStreamWriter.close();
	rd.close();

	return stringBuffer.toString();
	}
}
