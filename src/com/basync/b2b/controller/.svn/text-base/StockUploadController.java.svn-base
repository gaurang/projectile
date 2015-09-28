
package com.basync.b2b.controller;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import au.com.bytecode.opencsv.CSVReader;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.wrapper.IssueWraperService;
import com.basync.b2b.dao.StockUploadDAOImpl;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.StockMasterDO;
import com.basync.b2b.dataobjects.StockPRPDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

public class StockUploadController extends AbstractController{
	protected IStockService stockService;
	
	String mailMsg="";
	String tablePrint="";
	int errorcnt =0;
	HashMap<String, String> hMap;
	
	public IStockService getStockService() {
		return stockService;
	}

	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}
	
	private StockUploadDAOImpl stockUploadDAO;
	private MailSenderPooled mailSender;
	private IGenericService genericService;
	protected IMemoService memoService;
	
	public void setStockUploadDAO(StockUploadDAOImpl stockUploadDAO) {
		this.stockUploadDAO = stockUploadDAO;
	}
	/**
	 * @return the mailSender
	 */
	public MailSenderPooled getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(MailSenderPooled mailSender) {
		this.mailSender = mailSender;
	}
	
	/**
	 * @return the genericService
	 */
	public IGenericService getGenericService() {
		return genericService;
	}
	/**
	 * @param genericService the genericService to set
	 */
	public void setGenericService(IGenericService genericService) {
		this.genericService = genericService;
	}
	
	public IMemoService getMemoService() {
		return memoService;
	}

	public void setMemoService(IMemoService memoService) {
		this.memoService = memoService;
	}

	/**
	 * Upload From XL file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView uploadStockFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//UserSession userSession = RequestUtils.getUserSession(request);
		
	//	Method[] arrMethod;
	//	Method[] arrMethod1;
		int fileId = RequestUtils.getParam(request, "fileId1", -1); 
		
		String fileName="G:\\downloads\\Book1.xls";	
		if(request.getSession().getAttribute("FILEPATH")!=null)
		fileName = (String)request.getSession().getAttribute("FILEPATH");
		logger.debug("##################"+fileName +"server file  "+request.getSession().getAttribute("FILEPATH"));
		
		mailMsg += "<br/>"+ fileName +"<br/>";

		//TODO default

		List<String> doPropertyList = stockUploadDAO.getColumns(fileId);
		hMap = stockUploadDAO.getPrpValMap();
		Cell[] cell;
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		Sheet sheet = workbook.getSheet(0);
		logger.debug("##################"+sheet.getRows());
		//logger.debug("##################"+workbook.getSheet(1).getRows());
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//For Purchase TODO
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		String billNo =  RequestUtils.getParam(request, "billNo", ""); 
		String comments =  RequestUtils.getParam(request, "comments", ""); 
		String date =  RequestUtils.getParam(request, "date", ""); 
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		Double tax = Double.parseDouble(RequestUtils.getParam(request, "tax", "0"));
		double expenses = RequestUtils.getParam(request, "expenses", 0);
		double exRate = RequestUtils.getParam(request, "exRate", 0);
		String currency =   RequestUtils.getParam(request, "currency", "USD");
		String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
		String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
		
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());
		if(StringUtils.isEmpty(dueDate))
			dueDate  = sdfView.format(new Date());
		
		PurchaseMaster pm = new PurchaseMaster();
		pm.setBillNo(billNo);
		pm.setComments(comments);
		pm.setVendorId(vendorId);
		pm.setUserId(userId);
		pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
		pm.setTax(tax);
		
		pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
		pm.setExRate(exRate);
		pm.setExpenses(expenses);
		pm.setCurrency(currency);
		pm.setPaymentTerm(paymentTerm);
		
		List<StockPRPDO> stockPRPList = new ArrayList<StockPRPDO>();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		
		try {
			tablePrint ="<table class='preview'><tr><th>Xl row</th>";
			//Validation starts
			boolean fileFormat = true;
			//kri(change null)
			List<FileMap> fileMapList = this.getGenericService()
			.getFileMapping(null, fileId, 0);
			for (int i=0;i< doPropertyList.size();i++) {
				int hdrRow = 0;
				cell = sheet.getRow(hdrRow);
				
				if(i >= cell.length){
					break;
				}
				String fileHdr = cell[i].getContents()==null?"":cell[i].getContents();
				fileHdr = fileHdr.trim();
				String dbHdr  = fileMapList.get(i).getExcelColumnName()==null?"":fileMapList.get(i).getExcelColumnName();
				dbHdr = dbHdr.trim();
				logger.debug(fileHdr+" ^^^^^^^^^^^^^^^^^^^^^"+dbHdr);
				if(!fileHdr.equalsIgnoreCase(dbHdr)){
					fileFormat = false;
					logger.debug(fileHdr+" ^^^^^^^^^^^^^^^^^^^^^");
					mailMsg ="Headers not matching kindly check file format. 1st line has to be header  "+fileHdr;
					break;
				}
				tablePrint +="<th>"+fileHdr+"</th>";
			}
			tablePrint +="</tr>";
			if(!fileFormat)	{
				String json = JSONUtil.convertToJSON(mailMsg);
				request.setAttribute("uploadMsg", mailMsg);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().write(json);
				response.getWriter().flush();
				return null;
			}	
			//validation ends
			
			String property;
			
			int pkIdx  = doPropertyList.indexOf("pktCode");
			if(pkIdx ==-1 ){
				mailMsg = "No column for Packet ID or stone ID. Please correct file format. ";
				String json = JSONUtil.convertToJSON(mailMsg);
				request.setAttribute("uploadMsg", mailMsg);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().write(json);
				response.getWriter().flush();
				return null;
				//Property file
			}
			
			rows:
			for(int j=1;j<sheet.getRows();j++){
						
				Object stockMasterDO = new StockMasterDO();
				Object stockPRPDO = new StockPRPDO();
				cell = sheet.getRow(j);
				logger.debug("################## Row number "+j +" cell Lenth "+cell.length +" LIST "+doPropertyList.size());
				if(cell.length == 0)
					continue;
				String pktIdAuto = CommonUtil.getPropertiesByName("b2b.pktcode.auto");
				if((StringUtils.isEmpty(pktIdAuto) || !pktIdAuto.equalsIgnoreCase("Y"))){
					if(StringUtils.isEmpty(cell[pkIdx].getContents()))
						continue;
					else if(cell[pkIdx].getContents().trim().contains(" "))
						continue;
				} 
				
				String pktId =cell[pkIdx].getContents();
				for (int i=0;i< doPropertyList.size();i++) {
					property = doPropertyList.get(i);	
					if(StringUtils.isEmpty(property))
						continue;
					if(property.equals("SH")||property.equals("CTS")||property.equals("PU")){
						if(!checkValidPktRow(property,cell[i].getContents(),pktId)){
							continue rows;
						}
					}	
					if(i<cell.length){
						boolean valid =setObjectPrp(property,cell[i].getContents(),pktId, stockMasterDO,stockPRPDO,false);
						if(!valid){
							continue rows;
						}
					} 
				}
				if(((StockMasterDO)stockMasterDO).getVendorId() != null && vendorId !=0){
					((StockMasterDO)stockMasterDO).setVendorId(vendorId);
				}
				if(checkValidPktDtl((StockMasterDO)stockMasterDO, (StockPRPDO)stockPRPDO, pktId, j,doPropertyList, cell)){
					stockMasterList.add((StockMasterDO)stockMasterDO);
					stockPRPList.add((StockPRPDO)stockPRPDO);
				}
			
			}
		logger.debug("uploadStock------------------size "+stockMasterList.size());
		tablePrint +="</table>";
		
		//int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession ,pm, fileId);
		
/*		if(errorcnt>0)
			mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
		else
			mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows inserted/updated";*/
		
//		mailSender.general_send_mail(mailMsg, "ADMIN : Stock File Uploaded  ");
		
		request.setAttribute("uploadMsg", tablePrint);
		HttpSession session = request.getSession(true);
		session.setAttribute("stockMasterList", stockMasterList);
		session.setAttribute("stockPRPList", stockPRPList);
		session.setAttribute("fileId", fileId);
		
		session.setAttribute("purchaseMaster", pm);
		
		request.setAttribute("url", "uploadStock.html");
		
		} catch (Exception e) {
		//	this.getMailSender().general_send_mail(fileName+" Upload problem <br/>"+e.toString(), "ERROR IN UPLOADING FILE");
			mailMsg+="<br/>Error in uploading File";
			mailMsg +=e.getMessage();
			e.printStackTrace();
			//throw new Exception();
		}
		
		String json = JSONUtil.convertToJSON(tablePrint);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
		//return new ModelAndView("issue/stockEntryChk");
		
		//return new ModelAndView("Success");
	}
	
	public ModelAndView uploadStk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserSession userSession = RequestUtils.getUserSession(request);
		HttpSession session = request.getSession(true);
		
		PurchaseMaster pm = (PurchaseMaster)session.getAttribute("purchaseMaster");
		List<StockPRPDO> stockPRPList = (List<StockPRPDO>)session.getAttribute("stockPRPList");
		List<StockMasterDO> stockMasterList = (List<StockMasterDO>)session.getAttribute("stockMasterList");
		int fileId = (Integer)session.getAttribute("fileId");
		try{
		
		int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession ,pm, fileId);
		
		if(errorcnt>0)
			mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
		else
			mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows affected "+stockUploadDAO.getUpdateCnt();
		
		
		session.removeAttribute("purchaseMaster");
		session.removeAttribute("stockMasterList");
		session.removeAttribute("stockPRPList");
		session.removeAttribute("fileId");
		
		request.setAttribute("url", "uploadStock.html");
		
		} catch (Exception e) {
			mailMsg+="<br/>Error in uploading File";
			mailMsg +=e.getMessage();
			e.printStackTrace();
			//throw new Exception();
		}
		
		String json = JSONUtil.convertToJSON(mailMsg);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
		
	}
	
	/**
	 * Stock process mannual upload
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
		
	/**
	 * Get sort Values
	 * @param hm
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getSortVal(HashMap hm,String key, String value){
		
		key = key.replaceAll(" ", "-");
		//logger.debug(key);
		if(key.startsWith("shFr") ||key.startsWith("shTo") ) {
			key = "SH"+key.substring(key.indexOf("_"));
		}else if(key.startsWith("cFr") ||key.startsWith("cTo") ) {
			key = "COL"+key.substring(key.indexOf("_"));
		}else if(key.startsWith("puFr") ||key.startsWith("puTo") ) {
			key = "PU"+key.substring(key.indexOf("_"));
		}
		if(hm.containsKey(key.trim().toUpperCase())) {
			logger.debug(key+"                  zzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+hm.get(key.trim().toUpperCase()).toString());
			return hm.get(key.trim().toUpperCase()).toString();
		}else
			return value;
	}
	
	/**
	 * Set values in Stock Object
	 * @param f
	 * @param object
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	private boolean setField(Field f,Object object,String cell)throws Exception {
		boolean setField =false;
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
						cell = cell.replaceAll("%", "");
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
						logger.debug("#### ----------in set FIELD = "+setField);
						f.set(object, CommonUtil.getStringValue(cell
								));
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.debug(e.toString());
					throw new Exception();
					
				}
				return setField;
		}

	private Object getField(String property,Object object, Object object2,Cell[] cell,int i)throws Exception {
		Object objVal=null,objValSort=null ;	
			try {
					
				boolean set = true;
				try {
						Field f = StockMasterDO.class.getDeclaredField(property);
						f.setAccessible(true);
						objVal = f.get(object);		
						logTable(objVal);
					}catch (NoSuchFieldException e) {
						logger.debug("\nNO SUCH FIELD SO MOVE ON");
						set =false;
					}
				if(!set){
					Field f = StockPRPDO.class.getDeclaredField(property);
					f.setAccessible(true);
					objVal = f.get(object2);	
					
					if(!property.equals("CTS")){
						try {
							Field f2 = StockPRPDO.class.getDeclaredField(property+"_so");
							f2.setAccessible(true);
							objValSort = f2.get(object2);
							if(objValSort == null && (objVal!=null && !StringUtils.isEmpty(objVal.toString()))){
								logTableError(objVal);
							}else{
								logTable(objVal);
							}
						}catch (NoSuchFieldException e) {
							logger.debug("\nNO SUCH FIELD SO MOVE ON");
							logTable(objVal);
						}
					}else{
						logTable(objVal);
					}
				}
			}
			catch (Exception e) {
					// TODO: handle exception
					logger.debug(e.toString());
					if(i < cell.length &&  cell[i]!=null)
						logTable(cell[i].getContents());
					else{
						logTable(null);
					}
			}
			return objVal;
		}
	
	private Object getField(String property,Object object, Object object2,String[] cell,int i)throws Exception {
		Object objVal=null,objValSort=null ;	
			try {
					
				boolean set = true;
				try {
						Field f = StockMasterDO.class.getDeclaredField(property);
						f.setAccessible(true);
						objVal = f.get(object);		
						logTable(objVal);
					}catch (NoSuchFieldException e) {
						logger.debug("\nNO SUCH FIELD SO MOVE ON");
						set =false;
					}
				if(!set){
					Field f = StockPRPDO.class.getDeclaredField(property);
					f.setAccessible(true);
					objVal = f.get(object2);	
					
					if(!property.equals("CTS")){
						Field f2 = StockPRPDO.class.getDeclaredField(property+"_so");
						f2.setAccessible(true);
						objValSort = f2.get(object2);
						if(objValSort == null && (objVal!=null && !StringUtils.isEmpty(objVal.toString()))){
							logTableError(objVal);
						}else{
							logTable(objVal);
						}
					}else{
						logTable(objVal);
					}
				}
			}
			catch (Exception e) {
					// TODO: handle exception
					logger.debug(e.toString());
					if(cell[i]!=null)
						logTable(cell[i]);
					else{
						logTable(null);
					}
			}
			return objVal;
		}
	/**
	 * log Msg
	 * @param id
	 * @param prp
	 * @param val
	 */
	public void logMsg(String id, String prp,String val){
		
		mailMsg += "<br/>";
		mailMsg += "Pkt Code  :  "+id +"  |  Field  :  "+prp +" | value  :  "+val;
	//	tablePrint += "<td style='color:red'>"+val+"</td>";
		errorcnt++;
	}
	public void logTable(Object val){
		if(val!=null)
			tablePrint += "<td>"+val+"</td>";
		else{
			tablePrint += "<td></td>";
		}
	}
	public void logTableGrey(Object val){
		if(val!=null)
			tablePrint += "<td >"+val+"</td>";
		else{
			tablePrint += "<td></td>";
		}
	}
	public void logTableError(Object val){
		if(val!=null)
			tablePrint += "<td style='color:red'>"+val+"</td>";
		else{
			tablePrint += "<td></td>";
		}
	}

	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {
		// TODO Auto-generated method stub
		return uploadStock(request, response);

	}
	
	/**
	 * Upload Stock from CSV
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView uploadStockFileCSV(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession userSession = RequestUtils.getUserSession(request);
	//	Method[] arrMethod;
	//	Method[] arrMethod1;
		//File Name

		int fileId = RequestUtils.getParam(request, "fileId1", -1); 
		
		String fileType = (String)request.getAttribute("fileType");
		char sepString =  ',';
		if(fileType.equals(Constants.FILE_TYPE_TSV))
			sepString = Constants.FILE_TYPE_TSV_SEPERATOR;
		
		List<String> doPropertyList = stockUploadDAO.getColumns(fileId);
		
		String fileName="G:\\downloads\\Book1.csv";	
		if(request.getSession().getAttribute("FILEPATH")!=null)
			fileName = (String)request.getSession().getAttribute("FILEPATH");
		
		logger.debug("##################"+fileName +"server file  "+request.getSession().getAttribute("FILEPATH"));
		
		mailMsg += "<br/>"+ fileName +"<br/>";
		
		List<StockPRPDO> stockPRPList = new ArrayList<StockPRPDO>();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//For Purchase TODO
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		String billNo =  RequestUtils.getParam(request, "billNo", ""); 
		String comments =  RequestUtils.getParam(request, "comments", ""); 
		String date =  RequestUtils.getParam(request, "date", ""); 
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		Double tax = Double.parseDouble(RequestUtils.getParam(request, "tax", "0"));
		double expenses = RequestUtils.getParam(request, "expenses", 0);
		double exRate = RequestUtils.getParam(request, "exRate", 0);
		String currency =   RequestUtils.getParam(request, "currency", "USD");
		String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
		String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
		
		
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());
		if(StringUtils.isEmpty(dueDate))
			dueDate  = sdfView.format(new Date());

		PurchaseMaster pm = new PurchaseMaster();
		pm.setBillNo(billNo);
		pm.setComments(comments);
		pm.setVendorId(vendorId);
		pm.setUserId(userId);
		pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
		pm.setTax(tax);
		
		pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
		pm.setExRate(exRate);
		pm.setExpenses(expenses);
		pm.setCurrency(currency);
		pm.setPaymentTerm(paymentTerm);
		try {
			//Read CSV
			CSVReader reader = new CSVReader(new FileReader(fileName), sepString, '"', 0);
			
			List rows = reader.readAll();
			
			String[] cell;
			//TODO for default file from syspref  
			hMap = stockUploadDAO.getPrpValMap();
			tablePrint ="<table class='preview'><tr><th>Xl row</th>";
			//Validation starts
			boolean fileFormat = true;
			List<FileMap> fileMapList = this.getGenericService()
			.getFileMapping(null, fileId, 0);//kri_change to null
			
			for (int i=0;i< doPropertyList.size();i++) {
				int hdrRow = 0;
				cell = (String[])rows.get(hdrRow);
				logger.debug(" Object -->"+i+"  "+ doPropertyList.get(i));
				String fileHdr = cell[i]==null?"":cell[i];
				fileHdr = fileHdr.trim();
				String dbHdr  = fileMapList.get(i).getExcelColumnName()==null?"":fileMapList.get(i).getExcelColumnName();
				dbHdr = dbHdr.trim();
				if(!fileHdr.equalsIgnoreCase(dbHdr)){
					fileFormat = false;
					mailMsg ="Headers not matching kindly check file format. 1st line has to be header";
					break;
				}
				tablePrint +="<th>"+fileHdr+"</th>";
			}
			tablePrint +="</tr>";
			if(!fileFormat)	{
				String json = JSONUtil.convertToJSON(mailMsg);
				request.setAttribute("uploadMsg", mailMsg);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().write(json);
				response.getWriter().flush();
				return null;
			}	
			
			
			String property;
			
			int pkIdx  = doPropertyList.indexOf("pktCode");
			if(pkIdx ==-1 ){
				mailMsg = "No column for Packet ID or stone ID. Please correct file format. ";
				String json = JSONUtil.convertToJSON(mailMsg);
				request.setAttribute("uploadMsg", mailMsg);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().write(json);
				response.getWriter().flush();
				return null;
				//Property file
			}
			//validation ends
			
			
			boolean setField = false;
			
			//Workbook workbook = Workbook.getWorkbook(new File(fileName));
			//Sheet sheet = workbook.getSheet(0);
		
			rows:
			for(int j=1;j<rows.size();j++){
				Object stockMasterDO = new StockMasterDO();
				Object stockPRPDO = new StockPRPDO();
				cell = (String[])rows.get(j);
				
				if(cell.length != doPropertyList.size() )
					continue;
				
				String pktIdAuto = CommonUtil.getPropertiesByName("b2b.pktcode.auto");
				if((StringUtils.isEmpty(pktIdAuto) || !pktIdAuto.equalsIgnoreCase("Y"))){
					if(StringUtils.isEmpty(cell[pkIdx]))
						continue;
					if(!NumberUtils.isNumber(cell[pkIdx]))
						continue;
				}
				String pktId =cell[pkIdx];
				for (int propertyIndex=0;propertyIndex< doPropertyList.size();propertyIndex++) {
					setField = false;
					property = doPropertyList.get(propertyIndex);	
					if(StringUtils.isEmpty(property))
						continue;
					
					if(property.equals("SH")||property.equals("CTS")||property.equals("PU")){
						if(!checkValidPktRow(property,cell[propertyIndex],pktId)){
							continue rows;
						}
					}	
					boolean valid = setObjectPrp(property,cell[propertyIndex],pktId, stockMasterDO,stockPRPDO,false);
					
					if(!valid)
						continue rows;
				}	
				
				
				if(checkValidPktDtl((StockMasterDO)stockMasterDO, (StockPRPDO)stockPRPDO, pktId, j,doPropertyList, cell)){
					stockMasterList.add((StockMasterDO)stockMasterDO);
					stockPRPList.add((StockPRPDO)stockPRPDO);
				}
			
			}
			
		tablePrint +="</table>";
	/*		
		int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession, pm, fileId);
		
		if(errorcnt>0)
			mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
		else
			mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows inserted/updated";
		
		mailSender.general_send_mail(mailMsg, "ADMIN : Stock File Uploaded  ");
		*/
		request.setAttribute("uploadMsg", tablePrint);
		HttpSession session = request.getSession(true);
		session.setAttribute("stockMasterList", stockMasterList);
		session.setAttribute("stockPRPList", stockPRPList);
		session.setAttribute("fileId", fileId);
		
		session.setAttribute("purchaseMaster", pm);
		
		request.setAttribute("url", "uploadStock.html");
		
		} catch (Exception e) {
		//	this.getMailSender().general_send_mail(fileName+" Upload problem <br/>"+e.toString(), "ERROR IN UPLOADING FILE");
			mailMsg+="<br/>Error in uploading File";
			mailMsg +=e.getMessage();
			e.printStackTrace();
			//throw new Exception();
		}
		
		String json = JSONUtil.convertToJSON(tablePrint);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	private boolean checkValidPktRow(String property,String cellContent,String pktId){
		if(StringUtils.isEmpty(cellContent)){
			//logMsg(pktId, property, "No Basic properties for the row hence skipped");
			return false;
		}else if (property.equals("CTS")){
			if(!NumberUtils.isNumber(cellContent)){
				//logMsg(pktId, property, "CTS is non numeric hence skipped. ");
				return false;
			}
		}
		
		return true;
	}
	private boolean checkValidPktDtlMix(StockMasterDO sm, StockPRPDO sp,String pktId,int xlRowNum, List<String> doPropertyList, Cell[] cell ) throws Exception{
		boolean valid = true;
		
		/*if(sp.getSH_so()==null &&  sp.getPU_so()==null && sp.getCTS()==null){
			valid =false;
			return valid ;
		}*/
		if(sp.getCTS()!= null && !NumberUtils.isNumber(sp.getCTS().toString())){
			valid =false;
			return valid ;
		}
		tablePrint+="<tr><td>"+xlRowNum+"</td>";
		//for
		for (int i=0;i< doPropertyList.size();i++) {
			String prp = doPropertyList.get(i);
			Object o = getField(prp,sm, sp,cell,i );
			/*if(o == null){
				tablePrint+="<td>"+cell[i].getContents()+"</td>";
			}*/
		}
		tablePrint+="</tr>";
		return valid; 
	}
	private boolean checkValidPktDtl(StockMasterDO sm, StockPRPDO sp,String pktId,int xlRowNum, List<String> doPropertyList, Cell[] cell ) throws Exception{
		boolean valid = true;
		
		if(sp.getSH_so()==null &&  sp.getPU_so()==null && sp.getCTS()==null){
			valid =false;
			return valid ;
		}
		if(sp.getCTS()!= null && !NumberUtils.isNumber(sp.getCTS().toString())){
			valid =false;
			return valid ;
		}
		tablePrint+="<tr><td>"+xlRowNum+"</td>";
		//for
		for (int i=0;i< doPropertyList.size();i++) {
			String prp = doPropertyList.get(i);
			Object o = getField(prp,sm, sp,cell,i );
			/*if(o == null){
				tablePrint+="<td>"+cell[i].getContents()+"</td>";
			}*/
		}
		tablePrint+="</tr>";
		return valid; 
	}
	private boolean checkValidPktDtl(StockMasterDO sm, StockPRPDO sp,String pktId,int xlRowNum, List<String> doPropertyList, String[] cell ) throws Exception{
		boolean valid = true;
		
		if(sp.getSH_so()==null &&  sp.getPU_so()==null && sp.getCTS()==null){
			valid =false;
			return valid ;
		}
		if(sp.getCTS()!= null && !NumberUtils.isNumber(sp.getCTS().toString())){
			valid =false;
			return valid ;
		}
		tablePrint+="<tr><td>"+xlRowNum+"</td>";
		//for
		for (int i=0;i< doPropertyList.size();i++) {
			String prp = doPropertyList.get(i);
			Object o = getField(prp,sm, sp,cell,i );
			/*if(o == null){
				tablePrint+="<td>"+cell[i].getContents()+"</td>";
			}*/
		}
		tablePrint+="</tr>";
		return valid; 
	}
	/**
	 * Common method for setting object
	 * @param property
	 * @param cellContent
	 * @param pktId
	 * @param stockMasterDO
	 * @param stockPRPDO
	 */
	private boolean setObjectPrp(String property,String cellContent,String pktId,Object stockMasterDO,Object stockPRPDO, boolean isSortId ){
		boolean valid = true;
		boolean setField = false;
		logger.debug("#### ----------prp = "+property + " || "+cellContent);
		
		cellContent =  StringUtils.getNormaliseString(cellContent);
		
		try{
				Field f = StockMasterDO.class.getDeclaredField(property);
				setField = setField(f, stockMasterDO,cellContent);
		//		logger.debug("#### ----------prp in MASTER = "+property +" and "+cellContent);
				
				
		 }catch (NoSuchFieldException e) {
				logger.debug("\nNO SUCH FIELD SO MOVE ON");
		 }
		 catch (ArrayIndexOutOfBoundsException e) {
				logMsg(pktId, property, "No value for property");
		 }
		 catch (Exception e) {
				logMsg(pktId, property, cellContent);
				setField= true;
		 }
		if (!setField) {
			
			
			String[] prpArr =null;
			if(StringUtils.isContainsSpecialChar(property))
				prpArr = StringUtils.splitSpecialChar(property);
				
			if(property.equalsIgnoreCase("cts")){
			//	logger.debug("########### Cell val CTS "+(cellContent));
				
				if(StringUtils.isEmpty(cellContent) || !NumberUtils.isNumber(cellContent)){
					return false;
				}
				String sz =stockUploadDAO.getSizes(CommonUtil.getDoubleValue(cellContent));
				((StockPRPDO)stockPRPDO).setSZ(sz);
				((StockPRPDO)stockPRPDO).setSZ_so(Double.valueOf(getSortVal(hMap, "SZ"+"_"+sz, sz)));
			}
			if(prpArr == null){	
				//logger.debug("$$$$$$$$$$$"+property);
				try{
					Field fp = StockPRPDO.class.getDeclaredField(property);
					if(isSortId){
						setField(fp, stockPRPDO, getSortVal(hMap, property+"_"+cellContent.trim(), cellContent.trim()));
					}else{
						setField(fp, stockPRPDO, cellContent);
					}
					Field fpso = null;
					fpso = StockPRPDO.class.getDeclaredField(property+"_so");
					if(isSortId){
						setField(fpso, stockPRPDO, cellContent);
					}else{
						setField(fpso, stockPRPDO,  getSortVal(hMap, property+"_"+cellContent.trim(), cellContent.trim()));
					}
					
					
				}
				catch (NoSuchFieldException e) {
					logger.debug("NO SUCH FIELD SO MOVE ON");
				}
				catch (ArrayIndexOutOfBoundsException e) {
					logMsg(pktId, property, "No value for property");
				}
				catch (Exception e) {
					if(!property.equals("FNCO") && !property.equals("FNC")){
						//TODO GENERIC
						logMsg(pktId, property, cellContent);
					}
				}
			}else{
				for (int i = 0; i < prpArr.length; i++) {
					String prp =prpArr[i];
					String[] cellValues =   StringUtils.splitSpecialChar(cellContent);
					if(cellValues.length < prpArr.length)
						break;
					
					try{
						Field fp = StockPRPDO.class.getDeclaredField(prp);
						if(isSortId){
							setField(fp, stockPRPDO, getSortVal(hMap, prp+"_"+cellValues[i].trim(), cellValues[i].trim()));
						}else{
							setField(fp, stockPRPDO, cellValues[i]);
						}
						
						Field fpso = null;

						fpso = StockPRPDO.class.getDeclaredField(prp+"_so");
						
						if(isSortId){
							setField(fpso, stockPRPDO, cellValues[i]);
						}else{
							setField(fpso, stockPRPDO, getSortVal(hMap, prp+"_"+cellValues[i].trim(), cellValues[i].trim()));
						}
						
					}
					catch (NoSuchFieldException e) {
						logger.debug("NO SUCH FIELD SO MOVE ON");
					}
					catch (ArrayIndexOutOfBoundsException e) {
						logMsg(pktId, property, "No value for property");
					}
					catch (Exception e) {
						logMsg(pktId, prp, cellValues[i]);
					}
				}
				
			}	
		}
		return valid;
	}
	
	public ModelAndView uploadStockFileMixed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//UserSession userSession = RequestUtils.getUserSession(request);
		
	//	Method[] arrMethod;
	//	Method[] arrMethod1;
		int fileId = RequestUtils.getParam(request, "fileId1", -1); 
		
		String fileName="G:\\downloads\\Book1.xls";	
		if(request.getSession().getAttribute("FILEPATH")!=null)
		fileName = (String)request.getSession().getAttribute("FILEPATH");
		logger.debug("##################"+fileName +"server file  "+request.getSession().getAttribute("FILEPATH"));
		
		mailMsg += "<br/>"+ fileName +"<br/>";

		//TODO default

		List<String> doPropertyList = stockUploadDAO.getColumns(fileId);
		hMap = stockUploadDAO.getPrpValMap();
		Cell[] cell;
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		Sheet sheet = workbook.getSheet(0);
		logger.debug("################## Excel Sheet Rows == "+sheet.getRows());
		//logger.debug("##################"+workbook.getSheet(1).getRows());
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//For Purchase TODO
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		String billNo =  RequestUtils.getParam(request, "billNo", ""); 
		String comments =  RequestUtils.getParam(request, "comments", ""); 
		String date =  RequestUtils.getParam(request, "date", ""); 
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		Double tax = Double.parseDouble(RequestUtils.getParam(request, "tax", "0"));
		double expenses = RequestUtils.getParam(request, "expenses", 0);
		double exRate = RequestUtils.getParam(request, "exRate", 0);
		String currency =   RequestUtils.getParam(request, "currency", "USD");
		String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
		String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
		
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());
		if(StringUtils.isEmpty(dueDate))
			dueDate  = sdfView.format(new Date());
		
		PurchaseMaster pm = new PurchaseMaster();
		pm.setBillNo(billNo);
		pm.setComments(comments);
		pm.setVendorId(vendorId);
		pm.setUserId(userId);
		//pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
		pm.setTax(tax);
		
		//pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
		pm.setExRate(exRate);
		pm.setExpenses(expenses);
		pm.setCurrency(currency);
		pm.setPaymentTerm(paymentTerm);
		
		List<StockPRPDO> stockPRPList = new ArrayList<StockPRPDO>();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		
		try {
			tablePrint ="<table class='preview'><tr><th>Xl row</th>";
			//Validation starts
			boolean fileFormat = true;
			//kri(change null)
			List<FileMap> fileMapList = this.getGenericService()
			.getFileMapping(null, fileId, 0);
			for (int i=0;i< doPropertyList.size();i++) {
				int hdrRow = 0;
				cell = sheet.getRow(hdrRow);
				
				if(i >= cell.length){
					break;
				}
				String fileHdr = cell[i].getContents()==null?"":cell[i].getContents();
				fileHdr = fileHdr.trim();
				String dbHdr  = fileMapList.get(i).getExcelColumnName()==null?"":fileMapList.get(i).getExcelColumnName();
				dbHdr = dbHdr.trim();
				logger.debug(fileHdr+" ^^^^^^^^^^^^^^^^^^^^^"+dbHdr);
				if(!fileHdr.equalsIgnoreCase(dbHdr)){
					fileFormat = false;
					logger.debug(fileHdr+" ^^^^^^^^^^^^^^^^^^^^^");
					mailMsg ="Headers not matching kindly check file format. 1st line has to be header  "+fileHdr;
					break;
				}
				tablePrint +="<th>"+fileHdr+"</th>";
			}
			tablePrint +="</tr>";
			if(!fileFormat)	{
				String json = JSONUtil.convertToJSON(mailMsg);
				request.setAttribute("uploadMsg", mailMsg);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().write(json);
				response.getWriter().flush();
				return null;
			}	
			//validation ends
			
			String property;
			
			int pkIdx  = doPropertyList.indexOf("pktCode");
			if(pkIdx ==-1 ){
				mailMsg = "No column for Packet ID or stone ID. Please correct file format. ";
				String json = JSONUtil.convertToJSON(mailMsg);
				request.setAttribute("uploadMsg", mailMsg);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().write(json);
				response.getWriter().flush();
				return null;
				//Property file
			}
			
			rows:
			for(int j=1;j<sheet.getRows();j++){
				StockMasterDO stockMasterDO = new StockMasterDO();
				stockMasterDO.setPcs(-1.0);
				Object stockPRPDO = new StockPRPDO();
				cell = sheet.getRow(j);
				logger.debug("################## Row number "+j +" cell Lenth "+cell.length +" LIST "+doPropertyList.size());
				if(cell.length == 0)
					continue;
				String pktIdAuto = CommonUtil.getPropertiesByName("b2b.pktcode.auto");
				if((StringUtils.isEmpty(pktIdAuto) || !pktIdAuto.equalsIgnoreCase("Y"))){
					if(StringUtils.isEmpty(cell[pkIdx].getContents()))
						continue;
					else if(cell[pkIdx].getContents().trim().contains(" "))
						continue;
				} 
				
				String pktId =cell[pkIdx].getContents();
				for (int i=0;i< doPropertyList.size();i++) {
					property = doPropertyList.get(i);	
					if(StringUtils.isEmpty(property))
						continue;
					if(property.equals("CTS")){
						if(!checkValidPktRow(property,cell[i].getContents(),pktId)){
							continue rows;
						}
					}	
					if(i<cell.length){
						boolean valid =setObjectPrp(property,cell[i].getContents(),pktId, stockMasterDO,stockPRPDO,false);
						if(!valid){
							continue rows;
						}
					} 
				}
				if(((StockMasterDO)stockMasterDO).getVendorId() != null && vendorId !=0){
					((StockMasterDO)stockMasterDO).setVendorId(vendorId);
				}
				if(checkValidPktDtlMix((StockMasterDO)stockMasterDO, (StockPRPDO)stockPRPDO, pktId, j,doPropertyList, cell)){
					stockMasterList.add((StockMasterDO)stockMasterDO);
					stockPRPList.add((StockPRPDO)stockPRPDO);
				}
			
			}
		logger.debug("uploadStock------------------size "+stockMasterList.size());
		tablePrint +="</table>";
		
		//int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession ,pm, fileId);
		
/*		if(errorcnt>0)
			mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
		else
			mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows inserted/updated";*/
		
//		mailSender.general_send_mail(mailMsg, "ADMIN : Stock File Uploaded  ");
		
		request.setAttribute("uploadMsg", tablePrint);
		HttpSession session = request.getSession(true);
		session.setAttribute("stockMasterList", stockMasterList);
		session.setAttribute("stockPRPList", stockPRPList);
		session.setAttribute("fileId", fileId);
		
		session.setAttribute("purchaseMaster", pm);
		
		request.setAttribute("url", "uploadStock.html");
		
		} catch (Exception e) {
		//	this.getMailSender().general_send_mail(fileName+" Upload problem <br/>"+e.toString(), "ERROR IN UPLOADING FILE");
			mailMsg+="<br/>Error in uploading File";
			mailMsg +=e.getMessage();
			e.printStackTrace();
			//throw new Exception();
		}
		
		String json = JSONUtil.convertToJSON(tablePrint);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
		//return new ModelAndView("issue/packetUpload");
		
		//return new ModelAndView("Success");
	}
	
	/**
	 * To Stock uploading page
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView uploadStock(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		UserSession userSession = RequestUtils.getUserSession(request);
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		String date =  RequestUtils.getParam(request, "date", ""); 
		request.setAttribute("CURR_DATE",sdf.format(new Date()));
		String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());
		if(StringUtils.isEmpty(dueDate))
			dueDate  = sdfView.format(new Date());
		PurchaseMaster pm = (PurchaseMaster)session.getAttribute("purchaseMaster");
		List<StockPRPDO> stockPRPList = (List<StockPRPDO>)session.getAttribute("stockPRPList");
		List<StockMasterDO> stockMasterList = (List<StockMasterDO>)session.getAttribute("stockMasterList");
		int fileId = (Integer)session.getAttribute("fileId");
		List<StockMasterDO> stockPurchaseMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPurchasePrpList = new ArrayList<StockPRPDO>(); 
	
		StockMasterDO stockMasterDo = null;
		StockPRPDO stockPrpDo = null;
 		
		try {
		
		long iStatus = 1;
		for (int i = 0; i < stockMasterList.size(); i++) {
			stockMasterDo = stockMasterList.get(i);
			stockPrpDo =  stockPRPList.get(i);
			stockMasterDo.setPcs(-1.0);
			Double cts = stockPrpDo.getCTS();
			Double avgCts = stockPrpDo.getAVGCTS();
			Double ppc = stockPrpDo.getPPC();
			if((ppc == null || ppc < 0) && (avgCts != null && avgCts > 0) && cts > 0) {
				ppc = cts/avgCts; 
				stockPrpDo.setPPC(ppc);
			}else if((avgCts == null || avgCts < 0) && (ppc != null && ppc > 0) && cts > 0) {
				avgCts = cts/ppc;
				stockPrpDo.setAVGCTS(avgCts);
			}
			stockMasterDo.setStatus(iStatus);
		}
			int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession ,pm, fileId);
			
			if(errorcnt>0)
				mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
			else
				mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows affected "+stockUploadDAO.getUpdateCnt();
			
			
			session.removeAttribute("purchaseMaster");
			session.removeAttribute("stockMasterList");
			session.removeAttribute("stockPRPList");
			session.removeAttribute("fileId");
			request.setAttribute("url", "uploadStock.html");
		}catch (Exception e) {
				mailMsg+="<br/>Error in uploading File";
				mailMsg +=e.getMessage();
				e.printStackTrace();
				//throw new Exception();
			}
			
			String json = JSONUtil.convertToJSON(mailMsg);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			return null;
		}
	
	public ModelAndView uploadStockMixedManual(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession userSession = RequestUtils.getUserSession(request);
		List<StockPRPDO> stockPRPList = new ArrayList<StockPRPDO>();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//For Purchase TODO
		PurchaseMaster pm = new PurchaseMaster();

		
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		if(vendorId > 0){
			logger.debug("$$$$$$$$$$$ "+vendorId);
			String billNo =  RequestUtils.getParam(request, "billNo", ""); 
			String comments =  RequestUtils.getParam(request, "comments", ""); 
			String date =  RequestUtils.getParam(request, "date", ""); 
			String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
	
			Integer userId = RequestUtils.getUserSession(request).getUserId();
			Double tax = Double.parseDouble(RequestUtils.getParam(request, "tax", "0"));
			double expenses = RequestUtils.getParam(request, "expenses", 0);
			double exRate = RequestUtils.getParam(request, "exRate", 0);
			String currency =   RequestUtils.getParam(request, "currency", "USD");
			String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
			
			if(StringUtils.isEmpty(date))
				date  = sdfView.format(new Date());
			if(StringUtils.isEmpty(dueDate))
				dueDate  = sdfView.format(new Date());
			
			pm.setBillNo(billNo);
			pm.setComments(comments);
			pm.setVendorId(vendorId);
			pm.setUserId(userId);
			pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
			pm.setTax(tax);
			
			pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
			pm.setExRate(exRate);
			pm.setExpenses(expenses);
			pm.setCurrency(currency);
			pm.setPaymentTerm(paymentTerm);
		}
		try {
			
			//TODO default
			int fileId = RequestUtils.getParam(request, "fileId2", -1); 
			List<String> doPropertyList = stockUploadDAO.getColumns(fileId);
			hMap = stockUploadDAO.getValPrpMap();//from sort and value

			Integer pktCount = RequestUtils.getParam(request, "pktCount", 0);
			String property;
			
			int pkIdx  = doPropertyList.indexOf("pktCode");
			rows:
			
			for(int j=0;j<pktCount;j++){
				Object stockMasterDO = new StockMasterDO();
				Object stockPRPDO = new StockPRPDO();

				String editPktId = RequestUtils.getParam(request, "editPktCode_"+j, "");//only for editing pkt
				String pktId = RequestUtils.getParam(request, "pktCode_"+j, "");
				
				if(!StringUtils.isEmpty(editPktId)){
					((StockMasterDO)stockMasterDO).setEditPktCode(editPktId);
				}
				String pktIdAuto = CommonUtil.getPropertiesByName("b2b.pktcode.auto");
				if((StringUtils.isEmpty(pktIdAuto) || !pktIdAuto.equalsIgnoreCase("Y"))){
					if(StringUtils.isEmpty(pktId))
						continue;
					else if(pktId.trim().contains(" "))
						continue;
				} 
				
				for (int propertyIndex=0;propertyIndex< doPropertyList.size();propertyIndex++) {
					property = doPropertyList.get(propertyIndex);
					logger.debug("value of propertyindex == " + propertyIndex);
					if(StringUtils.isEmpty(property))
						continue;
					String prpValue= RequestUtils.getParam(request, property+"_"+j, "");
					/*if(property.equals("SH")||property.equals("CTS")||property.equals("PU")){
						if(!checkValidPktRow(property,prpValue,pktId)){
							continue rows;
						}
					}	*/
					boolean valid = setObjectPrp(property,prpValue,pktId, stockMasterDO,stockPRPDO,true);
					if(!valid)
						continue rows;
				}
				if(((StockPRPDO)stockPRPDO).getLAB() == null){
					String lab = RequestUtils.getParam(request, "labVal_"+j, "");
					if(!StringUtils.isEmpty(lab)){
						((StockPRPDO)stockPRPDO).setLAB(lab);
					}
				}
				Integer grpId = RequestUtils.getParam(request, "grpId_"+j, 1);
				((StockPRPDO)stockPRPDO).setGRPID(grpId.longValue());
				
				if(((StockMasterDO)stockMasterDO).getVendorId() != null && vendorId !=0){
					((StockMasterDO)stockMasterDO).setVendorId(vendorId);
				}
				StockMasterDO oStockMasterDO = (StockMasterDO)stockMasterDO;
				StockPRPDO oStockPRPDO = (StockPRPDO)stockPRPDO;
				long val = 1;
				oStockMasterDO.setStatus(val);
				oStockMasterDO.setPcs(-1.0);
				Double cts = oStockPRPDO.getCTS();
				Double avgCts = oStockPRPDO.getAVGCTS();
				Double ppc = oStockPRPDO.getPPC();
				if((ppc == null || ppc < 0) && (avgCts != null && avgCts > 0) && cts > 0) {
					ppc = cts/avgCts; 
					oStockPRPDO.setPPC(ppc);
				}else if((avgCts == null || avgCts < 0) && (ppc != null && ppc > 0) && cts > 0) {
					avgCts = cts/ppc;
					oStockPRPDO.setAVGCTS(avgCts);
				}
				stockMasterList.add(oStockMasterDO );
				stockPRPList.add(oStockPRPDO);
			
			}
		logger.debug("uploadStock------------------size "+stockMasterList.size());
		int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession, pm, fileId);
		
		if(errorcnt>0)
			mailMsg+="<br/>Error occured!! "+countEx +" rows inserted/updated";
		else
			mailMsg+="<br/> Packet Uploaded Successfully "+countEx +" rows inserted/updated";
		
		//mailSender.general_send_mail(mailMsg, "ADMIN : Stock Data Uploaded  ");
		request.setAttribute("mailMsg", mailMsg);
		
		//request.setAttribute("url", "stockEntry.html");
		
		
		} catch (Exception e) {
		//	this.getMailSender().general_send_mail("Mannual Upload problem <br/>"+e.toString(), "ERROR IN UPLOADING DATA");
			mailMsg +=e.getMessage();
			e.printStackTrace();
		}
		request.getRequestDispatcher("packetUpload.html").forward(request, response);
        return null;
	}
	
		public ModelAndView uploadStockManual(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession userSession = RequestUtils.getUserSession(request);
		List<StockPRPDO> stockPRPList = new ArrayList<StockPRPDO>();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//For Purchase TODO
		PurchaseMaster pm = new PurchaseMaster();

		
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		if(vendorId > 0){
			logger.debug("$$$$$$$$$$$ "+vendorId);
			String billNo =  RequestUtils.getParam(request, "billNo", ""); 
			String comments =  RequestUtils.getParam(request, "comments", ""); 
			String date =  RequestUtils.getParam(request, "date", ""); 
			String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
	
			Integer userId = RequestUtils.getUserSession(request).getUserId();
			Double tax = Double.parseDouble(RequestUtils.getParam(request, "tax", "0"));
			double expenses = RequestUtils.getParam(request, "expenses", 0);
			double exRate = RequestUtils.getParam(request, "exRate", 0);
			String currency =   RequestUtils.getParam(request, "currency", "USD");
			String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
			
			if(StringUtils.isEmpty(date))
				date  = sdfView.format(new Date());
			if(StringUtils.isEmpty(dueDate))
				dueDate  = sdfView.format(new Date());
			
			pm.setBillNo(billNo);
			pm.setComments(comments);
			pm.setVendorId(vendorId);
			pm.setUserId(userId);
			pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
			pm.setTax(tax);
			
			pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
			pm.setExRate(exRate);
			pm.setExpenses(expenses);
			pm.setCurrency(currency);
			pm.setPaymentTerm(paymentTerm);
		}
		try {
			
			//TODO default
			int fileId = RequestUtils.getParam(request, "fileId2", -1); 
			List<String> doPropertyList = stockUploadDAO.getColumns(fileId);
			hMap = stockUploadDAO.getValPrpMap();//from sort and value

			Integer pktCount = RequestUtils.getParam(request, "pktCount", 0);
			String property;
			int checkBoxChecked = RequestUtils.getParam(request, "editPktCode", 0);
			if(checkBoxChecked==1){
				request.getRequestDispatcher("editPacketCode.html").forward(request, response);
			}
			int pkIdx  = doPropertyList.indexOf("pktCode");
			rows:
			for(int j=0;j<pktCount;j++){
				Object stockMasterDO = new StockMasterDO();
				Object stockPRPDO = new StockPRPDO();

				String editPktId = RequestUtils.getParam(request, "editPktCode_"+j, "");//only for editing pkt
				String pktId = RequestUtils.getParam(request, "pktCode_"+j, "");
				
				if(!StringUtils.isEmpty(editPktId)){
					((StockMasterDO)stockMasterDO).setEditPktCode(editPktId);
				}
				String pktIdAuto = CommonUtil.getPropertiesByName("b2b.pktcode.auto");
				if((StringUtils.isEmpty(pktIdAuto) || !pktIdAuto.equalsIgnoreCase("Y"))){
					if(StringUtils.isEmpty(pktId))
						continue;
					else if(pktId.trim().contains(" "))
						continue;
				} 
				
				for (int propertyIndex=0;propertyIndex< doPropertyList.size();propertyIndex++) {
					property = doPropertyList.get(propertyIndex);
					
					if(StringUtils.isEmpty(property))
						continue;
					String prpValue= RequestUtils.getParam(request, property+"_"+j, "");
					if(property.equals("SH")||property.equals("CTS")||property.equals("PU")){
						if(!checkValidPktRow(property,prpValue,pktId)){
							continue rows;
						}
					}	
					boolean valid = setObjectPrp(property,prpValue,pktId, stockMasterDO,stockPRPDO,true);
					if(!valid)
						continue rows;
				}
				if(((StockPRPDO)stockPRPDO).getLAB() == null){
					String lab = RequestUtils.getParam(request, "labVal_"+j, "");
					if(!StringUtils.isEmpty(lab)){
						((StockPRPDO)stockPRPDO).setLAB(lab);
					}
				}
				Integer grpId = RequestUtils.getParam(request, "grpId_"+j, 1);
				((StockPRPDO)stockPRPDO).setGRPID(grpId.longValue());
				
				if(((StockMasterDO)stockMasterDO).getVendorId() != null && vendorId !=0){
					((StockMasterDO)stockMasterDO).setVendorId(vendorId);
				}
				stockMasterList.add((StockMasterDO)stockMasterDO);
				stockPRPList.add((StockPRPDO)stockPRPDO);
			
			}
		logger.debug("uploadStock------------------size "+stockMasterList.size());
		int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession, pm, fileId);
		
		if(errorcnt>0)
		{
			mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
		}else{
			if(countEx>0){
				mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows inserted/updated";
			}else{
				mailMsg+="<br/> Please Fill the Minumum Required detailed for Upload";
			}
		}
		
		//mailSender.general_send_mail(mailMsg, "ADMIN : Stock Data Uploaded  ");
		request.setAttribute("uploadMsg", mailMsg);
		
		request.setAttribute("url", "stockEntry.html");
		
		
		} catch (Exception e) {
		//	this.getMailSender().general_send_mail("Mannual Upload problem <br/>"+e.toString(), "ERROR IN UPLOADING DATA");
			mailMsg +=e.getMessage();
			e.printStackTrace();
			//throw new Exception();
		}
		String json = JSONUtil.convertToJSON(mailMsg);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
	
		return null;
		//return new ModelAndView(new RedirectView("stockEntry.html"));
	}
/*
	public ModelAndView uploadStockManual(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession userSession = RequestUtils.getUserSession(request);
		List<StockPRPDO> stockPRPList = new ArrayList<StockPRPDO>();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//For Purchase TODO
		PurchaseMaster pm = new PurchaseMaster();

		
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		if(vendorId > 0){
			logger.debug("$$$$$$$$$$$ "+vendorId);
			String billNo =  RequestUtils.getParam(request, "billNo", ""); 
			String comments =  RequestUtils.getParam(request, "comments", ""); 
			String date =  RequestUtils.getParam(request, "date", ""); 
			String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
	
			Integer userId = RequestUtils.getUserSession(request).getUserId();
			Double tax = Double.parseDouble(RequestUtils.getParam(request, "tax", "0"));
			double expenses = RequestUtils.getParam(request, "expenses", 0);
			double exRate = RequestUtils.getParam(request, "exRate", 0);
			String currency =   RequestUtils.getParam(request, "currency", "USD");
			String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
			
			if(StringUtils.isEmpty(date))
				date  = sdfView.format(new Date());
			if(StringUtils.isEmpty(dueDate))
				dueDate  = sdfView.format(new Date());
			
			pm.setBillNo(billNo);
			pm.setComments(comments);
			pm.setVendorId(vendorId);
			pm.setUserId(userId);
			pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
			pm.setTax(tax);
			
			pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
			pm.setExRate(exRate);
			pm.setExpenses(expenses);
			pm.setCurrency(currency);
			pm.setPaymentTerm(paymentTerm);
		}
		try {
			
			//TODO default
			int fileId = RequestUtils.getParam(request, "fileId2", -1); 
			List<String> doPropertyList = stockUploadDAO.getColumns(fileId);
			hMap = stockUploadDAO.getValPrpMap();//from sort and value

			Integer pktCount = RequestUtils.getParam(request, "pktCount", 0);
			String property;
			
			int pkIdx  = doPropertyList.indexOf("pktCode");
			rows:
			for(int j=0;j<pktCount;j++){
				Object stockMasterDO = new StockMasterDO();
				Object stockPRPDO = new StockPRPDO();

				String editPktId = RequestUtils.getParam(request, "editPktCode_"+j, "");//only for editing pkt
				String pktId = RequestUtils.getParam(request, "pktCode_"+j, "");
				
				if(!StringUtils.isEmpty(editPktId)){
					((StockMasterDO)stockMasterDO).setEditPktCode(editPktId);
				}
				String pktIdAuto = CommonUtil.getPropertiesByName("b2b.pktcode.auto");
				if((StringUtils.isEmpty(pktIdAuto) || !pktIdAuto.equalsIgnoreCase("Y"))){
					if(StringUtils.isEmpty(pktId))
						continue;
					else if(pktId.trim().contains(" "))
						continue;
				} 
				
				for (int propertyIndex=0;propertyIndex< doPropertyList.size();propertyIndex++) {
					property = doPropertyList.get(propertyIndex);
					
					if(StringUtils.isEmpty(property))
						continue;
					String prpValue= RequestUtils.getParam(request, property+"_"+j, "");
					if(property.equals("SH")||property.equals("CTS")||property.equals("PU")){
						if(!checkValidPktRow(property,prpValue,pktId)){
							continue rows;
						}
					}	
					boolean valid = setObjectPrp(property,prpValue,pktId, stockMasterDO,stockPRPDO,true);
					if(!valid)
						continue rows;
				}
				if(((StockPRPDO)stockPRPDO).getLAB() == null){
					String lab = RequestUtils.getParam(request, "labVal_"+j, "");
					if(!StringUtils.isEmpty(lab)){
						((StockPRPDO)stockPRPDO).setLAB(lab);
					}
				}
				Integer grpId = RequestUtils.getParam(request, "grpId_"+j, 1);
				((StockPRPDO)stockPRPDO).setGRPID(grpId.longValue());
				
				if(((StockMasterDO)stockMasterDO).getVendorId() != null && vendorId !=0){
					((StockMasterDO)stockMasterDO).setVendorId(vendorId);
				}
				Double ppc = 0d;
				StockMasterDO oStockMasterDO = (StockMasterDO)stockMasterDO;
				StockPRPDO oStockPRPDO = (StockPRPDO)stockPRPDO;
				long val = 1;
				oStockMasterDO.setStatus(val);
				oStockMasterDO.setPcs(-1.0);
				Double cts = oStockPRPDO.getCTS();
				Double avgCts = oStockPRPDO.getAVGCTS();
				ppc = oStockPRPDO.getPPC();
				if((ppc == null || ppc < 0) && (avgCts != null && avgCts > 0) && cts > 0) {
					ppc = cts/avgCts; 
					oStockPRPDO.setPPC(ppc);
				}else if((avgCts == null || avgCts < 0) && (ppc != null && ppc > 0) && cts > 0) {
					avgCts = cts/ppc;
					oStockPRPDO.setAVGCTS(avgCts);
				}
				stockMasterList.add(oStockMasterDO );
				stockPRPList.add((StockPRPDO)stockPRPDO);
			
			}
		logger.debug("uploadStock------------------size "+stockMasterList.size());
		int countEx =stockUploadDAO.uploadStock(stockMasterList,stockPRPList,userSession, pm, fileId);
		
		if(errorcnt>0)
			mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
		else
			mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows inserted/updated";
		
		//mailSender.general_send_mail(mailMsg, "ADMIN : Stock Data Uploaded  ");
		request.setAttribute("uploadMsg", mailMsg);
		
		request.setAttribute("url", "stockEntry.html");
		
		
		} catch (Exception e) {
		//	this.getMailSender().general_send_mail("Mannual Upload problem <br/>"+e.toString(), "ERROR IN UPLOADING DATA");
			mailMsg +=e.getMessage();
			e.printStackTrace();
			//throw new Exception();
		}
		String json = JSONUtil.convertToJSON(mailMsg);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
	
		return null;
		//return new ModelAndView(new RedirectView("stockEntry.html"));
	}*/
		
	public ModelAndView editPacketCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		int counter=0;
		String mailMsg="";
		Integer pktCount = RequestUtils.getParam(request, "pktCount", 0);
		for(int j=0;j<pktCount;j++){
			String editPktId = RequestUtils.getParam(request, "editPktCode_"+j, "");//only for editing pkt
			String pktId = RequestUtils.getParam(request, "pktCode_"+j, "");
			if(!(editPktId.isEmpty()) && !(pktId.isEmpty())){
				Integer value= this.stockUploadDAO.updatePacketId(pktId,editPktId);
				if(value>0){
					counter++;
				}
			} 
		}
		if(counter > 0){
			mailMsg+="<br/> Successfully "+counter +" rows updated ";
		}else{
			mailMsg+="<br/> Enter Packet is not available for Update. Please try another packet to Update";
		}
		String json = JSONUtil.convertToJSON(mailMsg);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null; 
	}
}
