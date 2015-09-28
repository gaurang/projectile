package com.basync.b2b.crm.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.math.NumberUtils;
import org.jgroups.debug.Debugger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.controller.AbstractController;
import com.basync.b2b.controller.IExceptionHandler;
import com.basync.b2b.crm.data.CurrencyResult;
import com.basync.b2b.crm.data.DemandMaster;
import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.FileTypes;
import com.basync.b2b.crm.data.InvoiceMaster;
import com.basync.b2b.crm.data.PartyAccData;
import com.basync.b2b.crm.data.PartyAddMaster;
import com.basync.b2b.crm.data.PartyMasterData;
import com.basync.b2b.crm.data.PartyShipAdd;
import com.basync.b2b.crm.data.PurchaseDetails;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.UserActivity;
import com.basync.b2b.crm.data.Dashboard;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.b2b.dao.RegistrationDAOImpl;
import com.basync.b2b.data.OrderMasterData;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.SearchPrpData;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.RegistrationDO;
import com.basync.b2b.dataobjects.TermMaster;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.service.IPrpService;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.service.IUserService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.NumberToWord;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CrmController extends AbstractController implements IExceptionHandler {
	protected IPrpService prpService;
	
	protected ICommonService commonService;
	
	protected IMemoService memoService;
	
	private MailSenderPooled mailSender;
	
	private IGenericService genericService;
	
	private IPartyService partyService;
	
	private IStockService stockService;
	
	private IPriceService priceService;

	private IUserService userService;
	
	private RegistrationDAOImpl registrationDao;
	
	
	
	/**
	 * @return the registrationDao
	 */
	public RegistrationDAOImpl getRegistrationDao() {
		return registrationDao;
	}


	/**
	 * @param registrationDao the registrationDao to set
	 */
	public void setRegistrationDao(RegistrationDAOImpl registrationDao) {
		this.registrationDao = registrationDao;
	}


	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {

		return new ModelAndView(new RedirectView("logout.html"));
	}

 
	/**
	 * @return the prpService
	 */
	public IPrpService getPrpService() {
		return prpService;
	}


	/**
	 * @param prpService the prpService to set
	 */
	public void setPrpService(IPrpService prpService) {
		this.prpService = prpService;
	}


	/**
	 * @return the commonService
	 */
	public ICommonService getCommonService() {
		return commonService;
	}


	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}


	

	/**
	 * @return the memoService
	 */
	public IMemoService getMemoService() {
		return memoService;
	}


	/**
	 * @param memoService the memoService to set
	 */
	public void setMemoService(IMemoService memoService) {
		this.memoService = memoService;
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
	
	
	/**
	 * @return the partyService
	 */
	public IPartyService getPartyService() {
		return partyService;
	}


	/**
	 * @param partyService the partyService to set
	 */
	public void setPartyService(IPartyService partyService) {
		this.partyService = partyService;
	}



	/**
	 * @return the stockService
	 */
	public IStockService getStockService() {
		return stockService;
	}


	/**
	 * @param stockService the stockService to set
	 */
	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}


	/**
	 * @return the priceService
	 */
	public IPriceService getPriceService() {
		return priceService;
	}


	/**
	 * @param priceService the priceService to set
	 */
	public void setPriceService(IPriceService priceService) {
		this.priceService = priceService;
	}


	
	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}


	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView partyList(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return new ModelAndView("party/partyMaster");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView partyListGrid(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PageList pageList=null;
		int pageNo = RequestUtils.getParam(request, "page", 1);
		int pageSize = RequestUtils.getParam(request, "rows", -1);
		String typeOfParty = RequestUtils.getParam(request, "typeOfParty", "");
		String companyName = RequestUtils.getParam(request, "companyName", "");
		String brokerName = RequestUtils.getParam(request, "brokerName", "");
		String businessType = RequestUtils.getParam(request, "businessType", "");
		int activeflag = RequestUtils.getParam(request, "activeflag", 1);
		String sIdx =RequestUtils.getParam(request, "sidx", "companyName");
		String sorD =RequestUtils.getParam(request, "sord", "desc");		
		if(sIdx!=null)
		pageList =  this.getPartyService().getAllPartyList(activeflag, typeOfParty, companyName, brokerName, businessType, pageNo, pageSize,sIdx);
		
		pageList.setPageAmount((int) Math.ceil((double) pageList.getRecordSize() / pageList.getPageSize()));
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;	
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView exportFilesList(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	//	int pageNo = RequestUtils.getParam(request, "pageNo", 1);
	//	int pageSize = RequestUtils.getParam(request, "rows", 25);
		//String exportFList=RequestUtils.getParam(request,"exportformat", null);//kri changed
		String pktType = RequestUtils.getParam(request, "pktType", "single");
		request.setAttribute("FILES_LIST", this.getGenericService().getExportFiles(null, pktType));//kri 
		request.setAttribute("pktType", pktType);
		return new ModelAndView("crm/exportFiles");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView exportFileMap(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	//	int pageNo = RequestUtils.getParam(request, "pageNo", 1);
	//	int pageSize = RequestUtils.getParam(request, "rows", 25);
		int fileId = RequestUtils.getParam(request, "fileId", -1);
		
		List<FileMap> fileMapList = this.getGenericService().getExportFileMapping(fileId);
		int fileColCount = 0;
		for (FileMap fileMap : fileMapList) {
			if(fileMap.getFileId() > 0)
				fileColCount++;
		}
		request.setAttribute("fileColCount", fileColCount);
		request.setAttribute("FILES_MAP", fileMapList);
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV()); 
		
		return new ModelAndView("crm/fileMap");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateFileMap(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	//	int pageNo = RequestUtils.getParam(request, "pageNo", 1);
	//	int pageSize = RequestUtils.getParam(request, "rows", 25);
		int fileId = RequestUtils.getParam(request, "fileId", -1);
		int fileColCount = RequestUtils.getParam(request, "fileColCount", -1);
		String pktType = "single";
		if(request.getParameter("mixed") != null)  pktType = "mixed";
		
		if(fileId == -1 ){
			FileTypes fileTypes = new FileTypes();
			fileTypes.setFileName(RequestUtils.getParam(request, "fileName", ""));
			fileTypes.setProcessType(RequestUtils.getParam(request, "processType", ""));
			fileTypes.setType(RequestUtils.getParam(request, "type", ""));
			fileTypes.setCompanyName(RequestUtils.getParam(request, "company", ""));
			fileTypes.setRapFormat(RequestUtils.getParam(request, "rapFormat", 0));
			fileTypes.setLab(RequestUtils.getParam(request, "LAB", ""));
			fileTypes.setPktType(pktType);
			fileId =this.getGenericService().insertFileFormat(fileTypes);
		}
		List<FileMap> fileMapList= new ArrayList<FileMap>();
		for(int i =0;i <fileColCount;i++){
			FileMap fm = new FileMap();
			fm.setColumnName(RequestUtils.getParam(request, "columnName_"+i, null));
			fm.setExcelColumnName(RequestUtils.getParam(request, "excelColName_"+i, null));
			fm.setColIndex(i);
			fileMapList.add(fm);
		}
		
		request.setAttribute("FILES_MAP", this.getGenericService().updateExportFileMapping(fileMapList ,fileId));
		return new ModelAndView(new RedirectView("exportFileMap.html?fileId="+fileId));
	}
	

	/**
	 * To stock page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stock(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		List<PrpData> prpRsltLst =  this.getCommonService().getResultPrpList(Integer.toString(3));
		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_MEMO, 
				Constants.MODULE_VALID_FLAG);
		UserSession us = RequestUtils.getUserSession(request);
 		String [] colDataArr;
 		
 		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
 		
 		colDataArr = this.getMemoService().getJQGridColModel(prpRsltLst,0, us);
 		
		request.setAttribute("headers", colDataArr[0]);
		request.setAttribute("colModel", colDataArr[1]);
		
		colDataArr = this.getMemoService().getJQGridColModel(prpRsltLst,2, us);
		request.setAttribute("headersMemo", colDataArr[0]);
		request.setAttribute("colModelMemo", colDataArr[1]);
	
		
		request.setAttribute("gridLink", "memoLoadGrid.html?q=1");
		
		//TODO in Common service half done
		//request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null));
		request.setAttribute("BROKER_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BROKER));
		request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF, null));
		request.setAttribute("TERMS_LIST", this.getCommonService().getTermsList());
		request.setAttribute("EXP_FILE_LIST", this.getGenericService().getExportFiles("OUT"));
		request.setAttribute("FILE_LIST", this.getGenericService().getExportFiles(null));
		
		
		request.setAttribute("PRP_RESULT_LIST",prpRsltLst);
		
		request.setAttribute("PRP_LIST", prpList);
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		
		
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		return new ModelAndView("memo/stock");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView memoLoadGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<PrpData> prpRsltLst =  this.getCommonService().getResultPrpList(Integer.toString(3));
		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_MEMO, 
				Constants.MODULE_VALID_FLAG);

		List<String> selectPKts = new Vector<String>(); 
		UserSession us = RequestUtils.getUserSession(request); 
		String accType =  RequestUtils.getParam(request, "accType", "");
		if(accType.equals("E"))
			accType="";
		
		//Double factor = 0d;
		Double factor =  Double.parseDouble(RequestUtils.getParam(request, "exRate", "0"));
		
		logger.debug(accType  +" =currency");
		HttpSession session = request.getSession();
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		
		Integer termId = RequestUtils.getParam(request, "term", 1);
		
		Integer cashDisc =  -1;
		if(termId == -1){
			termId = 1;
			cashDisc = RequestUtils.getParam(request, "cashDisc", -1);
		}
		Double termFactor = null ;
		TermMaster tm;
		if(session.getAttribute(Constants.SESSION_TERM)==null || termId != (Integer)session.getAttribute(Constants.SESSION_TERM_ID)){
			tm = this.getPartyService().getTermsById(termId.longValue()) ;
			termFactor = tm.getFactor();
			session.setAttribute(Constants.SESSION_TERM_ID, termId);
			session.setAttribute(Constants.SESSION_TERM, tm);

		}else{
			tm =(TermMaster)session.getAttribute(Constants.SESSION_TERM);
			if(tm.getFactor()!=null)
				termFactor = tm.getFactor();
		}
		if(tm.getRap()!=null && tm.getRap() != 0){
			cashDisc = tm.getRap().intValue();
		}
		
		String json = null;
		int p =RequestUtils.getParam(request, "page", 1);
		int pageSize =RequestUtils.getParam(request, "rows", 50);
		String sIdx =RequestUtils.getParam(request, "sidx", "SZ_so");
		String sorD =RequestUtils.getParam(request, "sord", "desc");
		if(sIdx.equals("SZ_so")){
			sIdx = " isnull(SH_so), SH_so, SZ_so, isnull(C_so),C_so ,PU_so ";
			sorD = "";
		}
		
		String[] pktIds = request.getParameterValues("selectedPkts[]");
		int cart =RequestUtils.getParam(request, "cart", 0);
		
		String pktNos=RequestUtils.getParam(request, "pktNos", "");
		String memoNos=RequestUtils.getParam(request, "memoNos", "");
		
		Integer addCriteria = RequestUtils.getParam(request, "addCriteria", 0);
		String fromSrch = RequestUtils.getParam(request, "fromSrch", "");
		
		Integer partyAccId = RequestUtils.getParam(request, "partyStock", -1);//Most important PartyAccount Id
		if(partyAccId == -1){
			partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		}
		
		Integer rootPktSrch = RequestUtils.getParam(request, "rootPktSrch", -1);//If search by root pkt nos
		Integer srchPair = RequestUtils.getParam(request, "srchPair", -1);//If search by pair pkt nos
		//Integer cashDisc = RequestUtils.getParam(request, "cashDisc", -1);
		
		//Sorting overridden for Pair
		if(srchPair == 1){
			sIdx = "SZ_so desc, pairNo ";
			sorD = "";
		}
		
		JQGridContainer container ;
		String whereClause = "";
		List<SearchPrpData> searchPrpList = new ArrayList<SearchPrpData>();
		//If pkt or memo is given
		if(session.getAttribute(Constants.SELECTED_PKTS)!=null){
			selectPKts  = (Vector)request.getSession().getAttribute(Constants.SELECTED_PKTS);
		}
		session.setAttribute(Constants.SELECTED_PKTS, selectPKts);

		if(pktIds!=null && pktIds.length >0 && cart ==1 ){
			selectPKts.addAll(Arrays.asList(pktIds));
			whereClause = " and s.id in ("+ StringUtils.toString(pktIds, ", ")+")";

			
		}else if (!fromSrch.equals("pktNos")&& !fromSrch.equals("memoNos")) {
			/**
			 * Changed logic on 8-1-2011
			 * StringUtils.isEmpty(pktNos) && StringUtils.isEmpty(memoNos)
			*/
	 		for(int i= 0; i< prpList.size();i++){
				SearchPrpData searchPrpData = new SearchPrpData();
				PrpData pd = new PrpData();
				pd = prpList.get(i);
				
				Float frVal =RequestUtils.getParam(request, pd.getPrp()+"_from", -1f);
				Float toVal =RequestUtils.getParam(request, pd.getPrp()+"_to", -1f);
				
				if(pd.getPrp().equalsIgnoreCase("C")){
					if(RequestUtils.getParam(request, "fancy", -1)==1){
						frVal = null;
						toVal = null;
					}else{
						frVal = 0f;
						toVal = 9999f;
					}
				}
				if(pd.getPrp().equals("LAB")){
					if(frVal == 100000f){
						frVal = null;
						toVal = null;
					}
				}
				
				if(pd.getFieldDisplayType().equalsIgnoreCase(Constants.FIELD_TYPE_MULTI_SELECT) ){
					String prpInArr[] = request.getParameterValues(pd.getPrp()) ;
					if(prpInArr !=null && prpInArr.length >0){
						String labStr = StringUtils.toString(prpInArr, ", ");
						if(labStr.equalsIgnoreCase("100000")){
							frVal = null;
							toVal = null;
						}else{
							searchPrpData.setPrpIn(labStr);
						}
					}
					logger.debug("\n $$$$$$$ ==========> SH and SIZE "+request.getParameterValues("SH") +" " +pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
					
				}
				if((frVal ==null || toVal ==null || frVal != -1f || toVal != -1f)  || !StringUtils.isEmpty(searchPrpData.getPrpIn())){
					if(pd.getFieldDisplayType().equalsIgnoreCase(Constants.FIELD_TYPE_SELECT))
						toVal=frVal;
					searchPrpData.setPrpId(pd.getId());	
					searchPrpData.setPrp(pd.getPrp());	
					searchPrpData.setPrpFromNum(frVal);
					searchPrpData.setPrpToNum(toVal);
					searchPrpData.setDataType(pd.getDataType());
					searchPrpList.add(searchPrpData);
					
				}
				
				logger.debug(" $$$$$$$ ==========> "+pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
				//QueryDescription qd = prpLov.get(pd.getPrp()).get(index);
				/*if(pd.getDataType()== Constants.CHARACTER){
				}else{
				}*/		
			}
	 		if(srchPair ==1 ){
	 			whereClause = " and s.pairStock is not null ";
	 		}
	 		whereClause += " and s.status in (1,2) "; //For regular search only one status allowed 
		}
		
		if(fromSrch.equals("memoNos")){
			if(rootPktSrch ==1){
				whereClause = " and s.rootPkt in ("+ memoNos.replaceAll(",","','")+")";
				container  =  this.getMemoService().getMemoStock(termFactor, prpRsltLst, whereClause,userId, p, pageSize, sIdx, sorD, accType, factor, cashDisc, false, us);
				
			}else{
				whereClause = " and om.id in ("+ memoNos+")";
				container  =  this.getMemoService().getMemoStockByMemoId(termFactor, prpRsltLst, whereClause,userId, p, pageSize, sIdx, sorD, accType, factor, false,us);
			}

		}else{
			if(fromSrch.equals("pktNos")){
				whereClause  = " and s.pktCode in ('"+ pktNos.replaceAll(",","','")+"')";
			}else if(cart ==0){
				whereClause  += this.getPrpService().getSearchCriteria(searchPrpList);
			}
			if(addCriteria ==1){
				List<String> whereClauseList ;
				if(session.getAttribute(Constants.SESSION_CRITERIA)!= null);
					whereClauseList = (List<String>) session.getAttribute(Constants.SESSION_CRITERIA);
				if(whereClauseList==null){
					whereClauseList = new ArrayList<String>();
				}	
				if(!fromSrch.equals("pktNos"))
					whereClause += " and partyAccId = "+ partyAccId;
				whereClauseList.add(whereClause);
				session.setAttribute(Constants.SESSION_CRITERIA, whereClauseList);
				container  =  this.getMemoService().getMemoStock(termFactor, prpRsltLst, whereClauseList,userId, p, pageSize, sIdx, sorD, accType, factor, cashDisc, false,us);
			}
			else{
				whereClause += " and partyAccId = "+ partyAccId;
				container  =  this.getMemoService().getMemoStock(termFactor, prpRsltLst, whereClause,userId, p, pageSize, sIdx, sorD, accType, factor, cashDisc, false,us);
				session.removeAttribute(Constants.SESSION_CRITERIA);
				session.setAttribute(Constants.SEARCH_CLAUSE, whereClause);
			}
			
		}
		
		json = JSONUtil.convertToJSON(container);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView makeExcelByMapping(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		HttpSession session = request.getSession();
		UserSession us = RequestUtils.getUserSession(request); 
	
		int userId = us.getUserId(); 
		Integer parcel = RequestUtils.getParam(request, "isParcel", -1);
		boolean isParcel = false;
		if(parcel == 1)
			isParcel = true;
		
		Integer rapNetDic = RequestUtils.getParam(request, "rapNetDic", -1);
		
		boolean rapDiscCol = false;
		if(rapNetDic == 1)
			rapDiscCol = true;
		
		OutputStream out = null;
		String[] array = null;
		if(request.getParameterValues("sVal")!=null){
			array = request.getParameterValues("sVal");
		}	
		
		String currency =  RequestUtils.getParam(request, "accType", "E");
 		Double factor = 0d;
 			if(currency.equals("L"))
 				factor = Double.parseDouble(RequestUtils.getParam(request, "exRate", "0"));
 		
		int fileId = RequestUtils.getParam(request, "exportformat", 1);
		
		int flatFile = RequestUtils.getParam(request, "flatFile", -1);
		if(flatFile == 1 ){
			fileId = RequestUtils.getParam(request, "flatFileId", 1);
		}
		
		String memoNos=RequestUtils.getParam(request, "memoNos", "");
		Integer termId = RequestUtils.getParam(request, "term", -1);
		
		Integer cashDisc =  -1;
		if(termId == -1){
			termId = 1;
			cashDisc = RequestUtils.getParam(request, "cashDisc", -1);
		}
		
		Double termFactor = null ;
		TermMaster tm;
		tm = this.getPartyService().getTermsById(termId.longValue()) ;
		termFactor = tm.getFactor();

		if(tm.getRap()!=null && tm.getRap() != 0){
			cashDisc = tm.getRap().intValue();
		}
		
		Integer partyAccId = RequestUtils.getParam(request, "partyStock", -1);//Most important PartyAccount Id
		if(partyAccId == -1){
			partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		}
		String sIdx =RequestUtils.getParam(request, "sidx", "SZ_so");
		String sorD =RequestUtils.getParam(request, "sord", "desc");
		if(sIdx.equals("SZ_so")){
			sIdx = " isnull(SH_so), SH_so, SZ_so desc, isnull(C_so),C_so ,PU_so, cts desc";
			sorD = "";
		}
		Integer srchPair = RequestUtils.getParam(request, "srchPair", -1);//If search by pair pkt nos
		
		//Sorting overridden for Pair
		if(srchPair == 1){
			sIdx = "SZ_so desc, pairNo ";
			sorD = "";
		}
		String email = RequestUtils.getParam(request, "email", "");
		try{
		File file = new File(commonService.getPropertiesByName("b2b.download.filepath")+commonService.getPropertiesByName("b2b.download.filename")+".xls");
			
		FileOutputStream fos = new FileOutputStream(file);
			
		response.setContentType("application/vnd.ms-excel");
		response.setHeader
		     ("Content-Disposition", "attachment; filename="+commonService.getPropertiesByName("b2b.download.filename")+".xls");
		 WritableWorkbook w;
		 if(StringUtils.isEmpty(email)){
			  w = Workbook.createWorkbook(response.getOutputStream()); 
		   }else{
		    w = Workbook.createWorkbook(fos);
		   }
		 WritableSheet s = w.createSheet("Stock List", 0);
		 	
		   List<FileMap> fileMapList = this.getGenericService().getFileMapping(null,fileId,-1);//kri change to null
		  	
		   //TermMaster tm = this.getPartyService().getTermsById(termId.longValue());
		    int fnc =RequestUtils.getParam(request, "fancy", -1);
			for (FileMap fileMap : fileMapList) {
				if(fileMap.getColumnName()!= null &&( fileMap.getColumnName().equalsIgnoreCase("RAP"))){
					String term = tm.getTermName();
					if(cashDisc!= -1 && cashDisc != null){
						term = "Asking price";
					}
					fileMap.setExcelColumnName((fileMap.getExcelColumnName()!=null?fileMap.getExcelColumnName():fileMap.getColumnName()) +"("+term +")");
				}
				
			}
		   
		   
		   String whereClause = "";
		   JQGridContainer container = new JQGridContainer();
		   List<String> whereClauseList = null; 
		   
		   String prpString =  this.getMemoService().getPrpStrMap(fileMapList, termFactor, factor, cashDisc, rapDiscCol, us);
		   //sys pref for individual pairs
		   String pairView = this.getGenericService().getSysPref(Constants.pairs_xl_view);
		   int pairViewInt = 0;
		   if(pairView !=null)
			   pairViewInt = Integer.parseInt(pairView);
		   if(session.getAttribute(Constants.SESSION_CRITERIA)!= null){
				whereClauseList = (List<String>) session.getAttribute(Constants.SESSION_CRITERIA);
				container = getMemoService().getQueryResult(termFactor,prpString, whereClauseList, userId, 1, -1, sIdx, sorD, false,isParcel);
		   
		   }else if(array !=null && array.length >0){
			   //for selected currencies
			   whereClause += " and s.id in ("+ StringUtils.toString(array, ",")+") ";
				   whereClause += " and partyAccId = "+ partyAccId;
			   container = getMemoService().getQueryResult(termFactor,prpString, whereClause, userId, 1, -1, sIdx, sorD,false, isParcel);
		   
		   }else if(!StringUtils.isEmpty(memoNos)){
			   if(session.getAttribute(Constants.SEARCH_CLAUSE)!=null)
				   whereClause = (String)session.getAttribute(Constants.SEARCH_CLAUSE);
				   whereClause += " and partyAccId = "+ partyAccId;
			   container = getMemoService().getQueryResult(termFactor, prpString, whereClause, userId, 1, -1, sIdx, sorD, true,isParcel );
		  
		   }else{
			   if(session.getAttribute(Constants.SEARCH_CLAUSE)!=null)
				   whereClause = (String)session.getAttribute(Constants.SEARCH_CLAUSE);
				   whereClause += " and partyAccId = "+ partyAccId;
			   String pairClause = "" ;
			   if(srchPair != 1 && pairViewInt == 1 && flatFile != 1){
				   pairClause += " and s.pairStock is null ";
			   }
			   container = getMemoService().getQueryResult(termFactor, prpString, whereClause+pairClause, userId, 1, -1, sIdx, sorD, false,isParcel);
		   }
		   
		   if(flatFile == 1){
			   CommonUtil.fillXLRegular(s, container, fileMapList, false);
		   }else{
			   if(srchPair != 1){
			   int rows = CommonUtil.fillXLSimplified(s, container, fileMapList,true, fnc);
				   if(pairViewInt == 1){
					   sIdx = "SZ_so desc, pairNo ";
					   sorD = "";
					   String pairClause = "" ;
					   pairClause += " and s.pairStock is not null ";
					   container = getMemoService().getQueryResult(termFactor, prpString, whereClause+pairClause, userId, 1, -1, sIdx, sorD, false,isParcel);
					   if(container.getRows().size() > 0)
						   rows = CommonUtil.fillXLPairSimplified(s, container, fileMapList,rows, fnc);
					   
					   if(container.getTotal() > 0 ){
						  Map userData =  this.getMemoService().getTotals(termFactor, whereClause, userId, false,isParcel);
						  CommonUtil.fillUserData(s, userData, fileMapList, rows);
					   }
				   }
			   }else{
				   CommonUtil.fillXLPairSimplified(s, container, fileMapList,1, fnc);
			   }
		   }
		   if(!StringUtils.isEmpty(email)){
			   List<File> attacthList = new ArrayList<File>();
			   attacthList.add(file);
			   String content = "Dear Sir,<br/> please Find attached stock file. <br/><br/> Regards,<br/>Team H.Riddhesh & Co.";
			   this.getMailSender().setAdminemail(this.getCommonService().getPropertiesByName("b2b.admin.email"));
			   this.getMailSender().general_send_mail(email,"",content, this.getCommonService().getPropertiesByName("b2b.download.companyCode")+" Diamonds: stock list",attacthList);
		   }
		   
		   w.write();
		   w.close();
		  } 
		  catch (Exception e){
		   throw new ServletException("Exception in Excel Sample Servlet", e);
		  } 
		  finally{
		   if (out != null)
		    out.close();
		  }
		   return null;
	}
	
	
	/**
	 * To stock page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockMemoColAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		UserSession us = RequestUtils.getUserSession(request);
		List<PrpData> prpRsltLst =  this.getCommonService().getResultPrpList(Integer.toString(3));
		
 		String [] colDataArr;
 		
 		colDataArr = this.getMemoService().getJQGridColModel(prpRsltLst,2, us);
		String json = JSONUtil.convertToJSON(colDataArr);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;

	}
	/**
	 * Edit Pricing Not To Be used
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockMemoPriceEditAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		
		Double rate = Double.parseDouble(RequestUtils.getParam(request, "rate", "0"));
		Double rap = Double.parseDouble(RequestUtils.getParam(request, "rap", "0"));
		Integer pktID = RequestUtils.getParam(request, "id", 0);
		
		int result= 0;
		if(rate > 0)
			result = getPriceService().updateStockPrice(rate, null, pktID,partyAccId, userId);
		else
			result = getPriceService().updateStockPrice(null, rap, pktID,partyAccId, userId);
		
		String[] responseArr =new String[2];
		if(result ==1){
			responseArr[0] ="true";
			responseArr[1] ="";
		}if(result == 0){
			responseArr[0] ="false";
			responseArr[1] ="You may not have rights to Change price/or data doesn't exists";
		}else{
			responseArr[0] ="false";
			responseArr[1] ="Error in updating price ";
		}
		
		String json = JSONUtil.convertToJSON(responseArr);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;

	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView generateMemo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer userId = RequestUtils.getUserSession(request).getUserId();  
		String subject = this.getCommonService().getPropertiesByName("b2b.download.companyCode")+" : Diamond Request";
		String currency =  RequestUtils.getParam(request, "accType", "E");
 		Double factor = 0d;
 		//	if(currency.equals("L"))
 		factor = Double.parseDouble(RequestUtils.getParam(request, "exRate", "0"));
		
		String[] pktIds = request.getParameterValues("selectedPkts[]");
		Integer termId = RequestUtils.getParam(request, "term", 1);
		if(termId ==-1){
			termId = 0;
		}
		Integer status = RequestUtils.getParam(request, "status", 4);
		String orderType =RequestUtils.getParam(request, "orderType", "");
		
		int salesMixInt = RequestUtils.getParam(request, "salesMix", 0);
		boolean salesMix = false;
		if(salesMixInt == 1)
			salesMix = true;
		String[] responscArr = new String[3];//For error messages
		
		
		OrderMasterData omd = new OrderMasterData();
		//Incase of edit order 
		
		String memoNos =  RequestUtils.getParam(request, "memoNos", "");
		if(StringUtils.isEmpty(memoNos) && RequestUtils.getParam(request, "orderType", "").equals(Constants.ORDER_TYPE_CONFRIM)){
			String[] memoListArr = memoNos.split(",");
			for(int i=0 ; i < memoListArr.length ;i++){
				omd = new OrderMasterData();
				omd.setId(Integer.parseInt(memoListArr[i].trim()));
				omd.setOrderType(RequestUtils.getParam(request, "orderType", Constants.ORDER_TYPE_CONFRIM));
				getStockService().editMemoType(omd);
				
			}
		}
		if(orderType.equals("MI")){
			status = Constants.STATUS_TYPE_MI;
			orderType =Constants.ORDER_TYPE_REQUEST;
		}else if(orderType.equals("SL")){
			status = Constants.STATUS_TYPE_SL;
			orderType =Constants.ORDER_TYPE_CONFRIM;
		}else if(orderType.equals("LI")){
			status = Constants.STATUS_TYPE_LI;
			orderType =Constants.ORDER_TYPE_LI;
		}else if(orderType.equals("CI")){
			status = Constants.STATUS_TYPE_CI;
			orderType =Constants.ORDER_TYPE_CI;
		}else if(orderType.equals("IS")){
			status = Constants.STATUS_TYPE_IS;
			orderType =Constants.ORDER_TYPE_IS;
		}else if(orderType.equals("MA")){
			status = Constants.STATUS_TYPE_MA;
			orderType =Constants.ORDER_TYPE_TRF;
						//HERE in transfer
		}
		else if(orderType.equals("MX")){
			status = Constants.STATUS_TYPE_MX;
		}
		
		if(status == Constants.STATUS_TYPE_LI ){
			omd.setPartyAccId(RequestUtils.getParam(request, "vendor",-1));
			omd.setRefPartyId(RequestUtils.getParam(request, "partyAccId", -1));
		}else if(status == Constants.STATUS_TYPE_MA){
			omd.setPartyAccId(RequestUtils.getParam(request, "self",-1));
			omd.setRefPartyId(RequestUtils.getUserSession(request).getPartyAccId());
		}else if(status == Constants.STATUS_TYPE_CI){
			omd.setLab(RequestUtils.getParam(request, "lab",""));
			omd.setPartyAccId(RequestUtils.getUserSession(request).getPartyAccId());
			omd.setRefPartyId(RequestUtils.getUserSession(request).getPartyAccId());
		}else{
			omd.setRefPartyId(RequestUtils.getParam(request, "vendor", -1));
			omd.setPartyAccId(RequestUtils.getParam(request, "partyAccId", -1));
		}
		omd.setOrderType(orderType);
		omd.setContactPerson(RequestUtils.getParam(request, "contactPerson", ""));
		omd.setComments(RequestUtils.getParam(request, "comments", ""));
		omd.setUserId(userId);
		omd.setStatus(Constants.STATUS_APPROVED);
		omd.setUserName(RequestUtils.getUserSession(request).getUserName());
		omd.setCompanyName(RequestUtils.getUserSession(request).getCompnayName());
		omd.setBrokerId(RequestUtils.getParam(request, "brokerId", -1));
		omd.setBrokrage(Double.valueOf(RequestUtils.getParam(request, "brokerage", "0")));
		omd.setMemo(1);
		omd.setTermId(termId);
		omd.setOrderDate(RequestUtils.getParam(request, "memoDate", ""));
		omd.setDueDate(RequestUtils.getParam(request, "dueDate", ""));
		omd.setExrate(factor);
		omd.setAccType(currency);
		
		//omd.setDgc(dgc);
		
		List<PacketDetails> pktList = new Vector<PacketDetails>();
		Map<String, String> rapsMap = new HashMap<String, String>(); 
		Map<String, String> ratesMap = new HashMap<String, String>(); 
		Map<String, String> pcsMap = new HashMap<String, String>(); 
		Map<String, String> ctsMap = new HashMap<String, String>(); 
		
		String rates = request.getParameter("rates");
		String rap = request.getParameter("raps");
		String pktPcs = request.getParameter("pktPcs");
		String pktCts = request.getParameter("pktCts");
		
		Gson gson = new Gson();
		ratesMap = gson.fromJson(rates, new TypeToken<Map<String,
				String>>() {}.getType());
		
		rapsMap = gson.fromJson(rap, new TypeToken<Map<String,
				String>>() {}.getType());
		pcsMap = gson.fromJson(pktPcs, new TypeToken<Map<String,
				String>>() {}.getType());
		ctsMap = gson.fromJson(pktCts, new TypeToken<Map<String,
				String>>() {}.getType());
		Double totalCts=0D,total=0D; 
		
		List<String> invalidPktRate = new ArrayList<String>();
		for (int i = 0; i < pktIds.length; i++) {
			logger.debug("#####  ro num =" +i +" pkt "+pktIds[i]);
				PacketDetails pktDtl = new PacketDetails();
				pktDtl.setPktId(Integer.parseInt(pktIds[i]));
				
				if(!pktList.contains(pktDtl)){	
					if(NumberUtils.isNumber(ratesMap.get("rate"+pktIds[i])) && Double.parseDouble(ratesMap.get("rate"+pktIds[i])) > 0){
						pktDtl.setRate(Double.parseDouble(ratesMap.get("rate"+pktIds[i])));
						pktDtl.setRap(Double.parseDouble(NumberUtils.isNumber(rapsMap.get("rap"+pktIds[i]))?rapsMap.get("rap"+pktIds[i]):"0"));
						if(pcsMap.containsKey("pcs"+pktIds[i]) && ctsMap.containsKey("cts"+pktIds[i])){
							pktDtl.setCts(Double.parseDouble(ctsMap.get("cts"+pktIds[i])));
							String pcs =  NumberUtils.isNumber(pcsMap.get("pcs"+pktIds[i]))== true?pcsMap.get("pcs"+pktIds[i]).toString():"0";
							pktDtl.setPcs(Double.parseDouble(pcs));
						}
						pktList.add(pktDtl);
					}
					else {
						invalidPktRate.add(pktDtl.getPktId().toString());
					}
				//	total+= pktDtl.getRate()*pktDtl.getCts();
				//	totalCts += pktDtl.getCts();
				}
		} 
		int orderId =0;
		//transferred from transferPkts
		
		UserSession userSession = RequestUtils.getUserSession(request);
		if(invalidPktRate.size() == 0){
			int success = 0;
			omd.setDiscount(Double.valueOf(RequestUtils.getParam(request, "discount", "0")));
			omd.setTax(Double.valueOf(RequestUtils.getParam(request, "tax", "0")));
			omd.setExpences(Double.valueOf(RequestUtils.getParam(request, "expences", "0")));
			omd.setShipCharges(Double.valueOf(RequestUtils.getParam(request, "shipCharges", "0")));			
			omd.setPacketList(pktList);			
			if(pktList.size() >0)
				orderId = this.getStockService().insertOrderMemo(omd, 1.0, status,salesMix);
				
			//?? changed
			if(status == Constants.STATUS_TYPE_MA){
				success = this.getMemoService().trnPkts(pktIds, omd.getPartyAccId(), userSession);
			}
			omd.setPacketList(pktList);
			
						//omd.setOrderDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			omd.setId(orderId);			
			//New process generate invoice on generating memo only if type is confirm
			
			if(omd.getOrderType().equals(Constants.ORDER_TYPE_CONFRIM)){
				InvoiceMaster im = new InvoiceMaster();
				
				Integer consigneeId =  RequestUtils.getParam(request, "consigneeId", -1); 
				String consigneeName = RequestUtils.getParam(request, "consigneeName", "");
				if(StringUtils.isEmpty(consigneeName))
					im.setConsigneePartyId(consigneeId);
				
				im.setConsigneeName(consigneeName);
				im.setExpRefNo(RequestUtils.getParam(request, "expRefNo", "")); 
				im.setOthRefNo(RequestUtils.getParam(request, "othRefNo", "")); 
				im.setDestination(RequestUtils.getParam(request, "destination", "")); 
				im.setPreCarrierPartyId(RequestUtils.getParam(request, "preCarrierPartyId", -1)); 
				im.setPlaceOfPreCarrier(RequestUtils.getParam(request, "placeOfPreCarrier", "N.A.")); 
				im.setVesselFlight(RequestUtils.getParam(request, "vesselFlight","By Air")); 
				im.setPortOfLoad(RequestUtils.getParam(request, "portOfLoad", "Mumbai")); 
				im.setPortOfDischarge(RequestUtils.getParam(request, "portOfDischarge","")); 
				this.getMemoService().insertMemoToInvoice(im, orderId, userId);
			}	
			//New process generate invoice on generating memo
			
		//	String email =this.getCommonService().getPropertiesByName("b2b.sales.email"); 
		}else{
			orderId = -1;
		}
		//this.getMailSender().sendMail(email, subject , request, orderId);
		/*
		omd.setPacketList(this.getStockService().getPktDetailsByOrderId(status, Integer.toString(orderId)));
		
		Double totalCts=0D,total=0D; 
		for (int i = 0; i < omd.getPacketList().size(); i++) {
			PacketDetails pd = new PacketDetails();
			pd =omd.getPacketList().get(i);
			total+=pd.getRate();
			totalCts += pd.getCts();
		}
		omd.setTotalCts(totalCts);
		omd.setTotalAmount(total);
		
		
		request.setAttribute("orderMasterData",omd);
		logger.debug("##### invoice.jsp ");*/
		
		
		if(orderId > 0){
			responscArr[0]="ok";
			responscArr[1]="Memo is Generated succesfully";
			responscArr[2]=String.valueOf(orderId);
		}else if(orderId == -1){
			responscArr[0]="fail";
			responscArr[1]=  "Kindly check prices of selected Packets. It should be numeric as well as more then 0";
			responscArr[2]= String.valueOf(orderId);
		}else{
			responscArr[0] = "fail";
			responscArr[1] = "Some error in generating memo";
			responscArr[2] = String.valueOf(orderId);
		}
		String json = JSONUtil.convertToJSON(responscArr);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
        
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoPrintPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		int orderId = RequestUtils.getParam(request, "orderId", -1);
		int invId = RequestUtils.getParam(request, "invId", -1);
		//String inputFile = "memoPrintLP.html?orderId="+orderId;
		String inputFile = "memoPrint.html?orderId="+orderId;
		int copy = Integer.parseInt(this.getGenericService().getSysPref("copy.perpage"));
		String filename = "memo-"+orderId;
		if(copy > 1){
			inputFile += "&copy="+copy;
		}
		if(orderId == -1){
			inputFile = "invPrint.html?invId="+invId;
			filename = "inv-"+invId;
		}
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="+filename+".pdf");
		
        ITextRenderer renderer = new ITextRenderer();
       URL uri =   	new URL("http://" + request.getServerName() + ":" + request.getServerPort() + 
		        	request.getContextPath() + "/"+inputFile);
HttpURLConnection ucon = 
            (HttpURLConnection)uri.openConnection();

		ucon.setRequestProperty("Cookie", "userId="+RequestUtils.getCookie(request, "userId")
				.getValue()+";userKey="+RequestUtils.getCookie(request, "userKey")
				.getValue()+";JSESSIONID=" + session.getId());
        ucon.connect();
        if (ucon.getResponseCode() == 500) {
            System.out.println(" ERROR IN SENDING INVOICEMAIL");
        }
        InputStream in = ucon.getInputStream();
        
        InputStreamReader isr = new InputStreamReader(in);
        Reader inReader = new BufferedReader(isr);
        StringBuffer buf = new StringBuffer();
        int ch;
        while ((ch = inReader.read()) > -1) {
            buf.append((char)ch);
        }
        inReader.close();
        String dtls = buf.toString();
        
        logger.debug(dtls);
        renderer.setDocumentFromString(dtls);
        renderer.layout();
        renderer.createPDF(response.getOutputStream());
        
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int orderId = RequestUtils.getParam(request, "orderId", -1);
		
		OrderMasterData omd = this.getStockService().getOrderMasterDetail( orderId);
		omd.setId(orderId);
		//PartyMasterData pm = this.getPartyService().findByPartyMasterById(omd.getPartyAccId());
		
		omd.setPacketList(this.getStockService().getPktDetailsByOrderId(-1, Integer.toString(orderId),Constants.STATUS_APPROVED));
		Double totalCts=0D,total=0D; 
		for (int i = 0; i < omd.getPacketList().size(); i++) {
			PacketDetails pd = new PacketDetails();
			pd =omd.getPacketList().get(i);
			total+= (pd.getRate()*pd.getCts());
			totalCts += pd.getCts();
		}
		NumberFormat nf = new DecimalFormat("#0.00");
		omd.setTotalAmount(Double.valueOf(nf.format(total)));
		double addDiscount=omd.getDiscount();
		double temp=(addDiscount/100)*total;
		total=total-temp;
		omd.setTotalCts(Double.valueOf(nf.format(totalCts)));
		omd.setFinalAmount(Double.valueOf(nf.format(total)));
		omd.setDiscount(Double.valueOf(nf.format(addDiscount)));
		
		Map otherDetails = this.getMemoService().getOrderAddDetails(omd) ;
		
		int totalMemoPkt = omd.getPacketList().size();
		int PKT_PER_MEMO = 10;
		int memoPrint = totalMemoPkt/10;
		if(totalMemoPkt % 10 > 0)
			memoPrint ++;
		
		request.setAttribute("memoCount",memoPrint);
		request.setAttribute("otherDetails",otherDetails);
		request.setAttribute("orderMasterData",omd);
		
		
	return new ModelAndView("memo/memoPrint");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String memoNos = RequestUtils.getParam(request, "memoNos", "");
		String[] memoNosArr = memoNos.split(",");
		String[] pktIds = request.getParameterValues("selectedPkts[]");
		Integer userId = RequestUtils.getUserSession(request).getUserId();  
		
		int success =0;
		String[] responscArr = new String[2];
		
		
		List<QueryCodeDescription> appPktList = new ArrayList<QueryCodeDescription>();
		if(pktIds!= null && pktIds.length > 0 && !StringUtils.isEmpty(memoNos)){
			success = this.getMemoService().memoReturnByPktNos(pktIds, memoNosArr, userId);
		}else if(!StringUtils.isEmpty(memoNos)){
			success = this.getMemoService().memoReturnFull(memoNosArr, userId);
		}
		else{
			success =-1;
		}

		appPktList = this.getMemoService().getPktMemo(pktIds, memoNosArr);
		String appMsg = "";
		if(appPktList.size()>0){
			 appMsg = "Following packets are in another memo, Please return from respective memo. ";
			for (int i = 0; i < appPktList.size(); i++) {
				QueryCodeDescription qd =new QueryCodeDescription();
				qd = appPktList.get(i);
				appMsg += "Pkt No.: "+qd.getId() +" on Memo No.: "+ qd.getDescription() +" | ";
			}
		}
		if(success > 0){
			responscArr[0]="ok";
			responscArr[1]="Memo is Returned succesfully. "+appMsg;
			
		}else if(success == 0){
			responscArr[0]="fail";
			responscArr[1]="No packets on the memo. "+ appMsg;
		}else if(success == -1){
			responscArr[0]="fail";
			responscArr[1]="Please enter Memo number";
		}
		String json = JSONUtil.convertToJSON(responscArr);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoListReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] pktIds = request.getParameterValues("selectedPkts[]");
		String sMemoType = RequestUtils.getParam(request, "memotype", "");
		UserSession us  = RequestUtils.getUserSession(request);
		int success =0;
		if((pktIds!= null && pktIds.length > 0) && !StringUtils.isEmpty(sMemoType)) {
			if(sMemoType.equals(Constants.ORDER_TYPE_REQUEST) || sMemoType.equals(Constants.ORDER_TYPE_CONFRIM) ){
				success = this.getMemoService().memoReturnFull(pktIds, us.getUserId());
			}
			if(sMemoType.equals(Constants.ORDER_TYPE_TRF) ) {
				success = this.getMemoService().memoReturnTrf(pktIds, us.getUserId(), us.getPartyAccId());
			}			
			/*if(sMemoType.equals(Constants.ORDER_TYPE_CI) ) {
				return NewstockEntry(request, response);
			}*/			
		}
		return null;
	}
		
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF,partyAccid));
		request.setAttribute("MEMOSTATUS_LIST", this.getCommonService().getMemoStatusList());
		request.setAttribute("MEMOTYPE_LIST", this.getCommonService().getMemoTypeList());
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, partyAccid));
		request.setAttribute("CARRIER_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_CARIEER));
		request.setAttribute("BROKER_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BROKER));
		request.setAttribute("WEB", RequestUtils.getParam(request, "web", -1));
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("CURR_DATE", sdf.format(new Date()));		
		return new ModelAndView("memo/pendingMemo");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoReportGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		//Integer status =  RequestUtils.getParam(request, "status", -1);
		String memoDate = RequestUtils.getParam(request, "memoDate", "");
		Integer partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		Integer partyAddId = RequestUtils.getParam(request, "partyAddId", -1);
		Integer partyId = RequestUtils.getParam(request, "partyId", -1);
		String orderType = RequestUtils.getParam(request, "orderType", "");
		
		String jsonStr = RequestUtils.getParam(request, "filters", "");
		String jsonQry = ""; 
		if(!StringUtils.isEmpty(jsonStr))
			jsonQry = JSONUtil.getQueryString(jsonStr);
		
		
		Integer page = RequestUtils.getParam(request, "page", -1);
		Integer pageSize = RequestUtils.getParam(request, "rows", -1);
		String srtIndex = RequestUtils.getParam(request, "srtIndex", "om.id");
		String srtType = RequestUtils.getParam(request, "srtType", "desc");
		int web =RequestUtils.getParam(request, "web", -1);
		
		Integer selfPartyAccId = RequestUtils.getParam(request, "selfPartyAccId", -1);;
		if(selfPartyAccId == -1)
			selfPartyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		Integer iMemoStatus = RequestUtils.getParam(request, "memoStatus", 2);;
		String accType = RequestUtils.getParam(request, "LC/EX", "");
		String sMemoType = RequestUtils.getParam(request, "memoType", "Confirm");
		//TODO for roles based login
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		int overDue = RequestUtils.getParam(request, "overDue", -1);
		int exRate = RequestUtils.getParam(request, "exRate", -1);
		userId = -1;
		PageList pageList = null;
		//code for "show Overdue memos" in the next 5 lines
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		Date dueDate = new Date();	
		String sDueDate = null;
		if(overDue > 0) {
			sDueDate = sdf.format(dueDate);
		}
		
		String sToDate = RequestUtils.getParam(request, "toDate", "");
		String sFromDate = RequestUtils.getParam(request, "fromDate", "");
		
		String sCompanyName = RequestUtils.getParam(request, "companyName", "");
		Integer memoNo = RequestUtils.getParam(request, "memoNo", 0);
		if(web != 1)
			pageList= this.getMemoService().getMemoList(memoDate, partyAccId,partyAddId,partyId,orderType, page, pageSize,
					srtIndex, srtType, userId,selfPartyAccId, iMemoStatus, sMemoType, accType, sDueDate, sToDate, sFromDate, exRate, sCompanyName, memoNo);
		else // not in use as there are no selection menus on the web memo page  
			//pageList= this.getMemoService().getMemoListWeb(jsonQry, page, pageSize, srtIndex, srtType);
			pageList= this.getMemoService().getMemoListWeb(memoDate, partyAddId,partyId,orderType, page, pageSize,
					srtIndex, srtType, userId, iMemoStatus, sMemoType, accType, sDueDate, sToDate, sFromDate, exRate, sCompanyName, memoNo);
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoSubGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer id = RequestUtils.getParam(request, "id", -1); 
		List<PacketDetails> list= this.getStockService().getPktDetailsByOrderId(-1, Integer.toString(id), -1);
		PageList pageList =new PageList();
		pageList.setResults(list);
		pageList.setRecordSize(list.size());
		pageList.setPageNo(1);
		pageList.setPageSize(list.size());
		pageList.setPageAmount(1);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView memoToInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer memoId = RequestUtils.getParam(request, "memoId", -1); 
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		InvoiceMaster im = new InvoiceMaster();
		
		Integer invId =  RequestUtils.getParam(request, "invId", -1); 
		
		//check for generated invId to update or insert accordingly -- important
		if(invId > -1){
			im = this.getMemoService().getInvoiceMaster(invId);
		}else{
			//generally wont come
			invId = this.getMemoService().getInvIdFromMemo(memoId);
			if(invId >0){
				im = this.getMemoService().getInvoiceMaster(invId);
			}
		}
		String accType =  RequestUtils.getParam(request, "accType", "E");
		int id = 0;
		if(accType.equalsIgnoreCase("L")){
				
			im.setOthRefNo(RequestUtils.getParam(request, "othRefNo2", "")); 
			im.setCstVat(RequestUtils.getParam(request, "cstVat", ""));
			im.setPan(RequestUtils.getParam(request, "PAN", ""));
					
		}else{
				Integer consigneeId =  RequestUtils.getParam(request, "consigneeId", -1); 
				String consigneeName = RequestUtils.getParam(request, "consigneeName", "");
				if(StringUtils.isEmpty(consigneeName))
					im.setConsigneePartyId(consigneeId);
				
				im.setConsigneeName(consigneeName);
				im.setExpRefNo(RequestUtils.getParam(request, "expRefNo", "")); 
				im.setOthRefNo(RequestUtils.getParam(request, "othRefNo", "")); 
				im.setDestination(RequestUtils.getParam(request, "destination", "")); 
				im.setPreCarrierPartyId(RequestUtils.getParam(request, "preCarrierPartyId", -1)); 
				im.setPlaceOfPreCarrier(RequestUtils.getParam(request, "placeOfPreCarrier", "")); 
				im.setVesselFlight(RequestUtils.getParam(request, "vesselFlight","")); 
				im.setPortOfLoad(RequestUtils.getParam(request, "portOfLoad", "")); 
				im.setPortOfDischarge(RequestUtils.getParam(request, "portOfDischarge","")); 
				im.setStatus("2");
		}	
		if(invId > -1){
			this.getMemoService().updateInvoiceMasterData(im);
			id = invId;
		}else{
			id = this.getMemoService().insertMemoToInvoice(im,memoId, userId);
		}
		String[] responscArr = new String[3];
		
		if(id > 0){
			responscArr[0] = "ok";
			responscArr[1] = "Invoice is Generated from Memo";
			responscArr[2] = String.valueOf(id);
		}else{
			responscArr[0] = "fail";
			responscArr[1] = "Some error in Generating Invoice";
			responscArr[2] = "0";
		}
		String json = JSONUtil.convertToJSON(responscArr);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		SimpleDateFormat sdfmsql =new SimpleDateFormat(Constants.DATE_FORMAT);
		SimpleDateFormat sdf2 =new SimpleDateFormat("yyyy");
		
		
		int invId = RequestUtils.getParam(request, "invId", -1);
		
		InvoiceMaster invMaster = this.getMemoService().getInvoiceMaster(invId);
		float exRate = this.getMemoService().getExRateFromOrderId(invMaster.getMemoOrderId());
		invMaster.setExRate(exRate);
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+invMaster.getInvoiceDate());
		Date invdate = sdfmsql.parse(invMaster.getInvoiceDate()) ;
		invMaster.setInvoiceDate(sdf.format(invdate));
		//Invoice Code generation 
		String currYr = sdf2.format(invdate);
		String nxtYr = (Integer.parseInt(currYr.substring(2))+1)+"";
		if(invMaster.getInvType()== null || invMaster.getInvType().equals("L"))
			invMaster.setInvoiceCode("LCL/"+invMaster.getId()+"/"+currYr+"-"+nxtYr);
		else
			invMaster.setInvoiceCode("EXP/"+invMaster.getId()+"/"+currYr+"-"+nxtYr);
		
		List invDetailsList = this.getMemoService().getPktDetailsByInvId(String.valueOf(invId));
		
		Map otherDetails = this.getMemoService().getInvoiceAddDetails(invMaster) ;
		
		String finalAmount = "";
		Double tempDiscount=0d;
		
		
		if(invMaster.getFinalAmount()!=null){
			DecimalFormat df = new DecimalFormat("#.##");
			Double amount = invMaster.getFinalAmount();
			Double discount=invMaster.getDiscount();
			if(invMaster.getFinalAmount().toString().indexOf(".")>0)
				//for USD
				if(invMaster.getInvType() == null || invMaster.getInvType().equals("E")) {
					tempDiscount=(discount/100)*amount;
					double tempamount=amount-tempDiscount;
					amount= Double.valueOf(df.format(tempamount));
					finalAmount = NumberToWord.convert(amount.longValue());
					finalAmount += " and "+ NumberToWord.convert(Long.parseLong(amount.toString().substring((amount.toString().indexOf(".")+1)))) +" cents only";
				}
				//for INR
				else if(invMaster.getInvType() == null || invMaster.getInvType().equals("L")) {
					amount *= exRate;
					tempDiscount=(discount/100)*amount;
					double tempamount=amount-tempDiscount;
					amount= Double.valueOf(df.format(tempamount));
					finalAmount = NumberToWord.convert((amount).longValue());
					//int ar=amount.toString().indexOf(".")+1;
					//System.out.println("Final Amount: "+finalAmount+"");
					//finalAmount += " and "+ NumberToWord.convert(amount.toString().indexOf(".")+1) +" paise only";
				}
				invMaster.setTotalAmount(amount);
			}
		else{
			finalAmount = "zero";
		}
		otherDetails.put("finalAmtStr", finalAmount);
		otherDetails.put("discontValue", tempDiscount);
		
		request.setAttribute("invAddData",otherDetails);
		request.setAttribute("inDetails",invDetailsList);
		request.setAttribute("im",invMaster);
		/*TODO  Internationalisation for the Currencies has to be done in the future. Right now number to word works only
		 * for USD and INR. Local currency options in projectile have to be used too.
		 */
		
		if(invMaster.getInvType() == null || invMaster.getInvType().equals("L"))
			return new ModelAndView("memo/localInvoice");
		else return new ModelAndView("memo/invoice");
		
	
	}
	
	/**
	 * AJAX json data of invoice
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invDataAJAX(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int memoId = RequestUtils.getParam(request, "id", -1);
		
		InvoiceMaster invMaster = this.getMemoService().getInvoiceMasterByMemoId(memoId);
		//List invDetailsList = this.getMemoService().getPktDetailsByInvId(String.valueOf(invId));
		//Map otherDetails = this.getMemoService().getInvoiceAddDetails(invMaster) ;
		
		String json = JSONUtil.convertToJSON(invMaster);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
	return null;
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPartyInv(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		String accType = RequestUtils.getParam(request, "accType", "E");
		String exRate = RequestUtils.getParam(request, "exRate", "");
		Integer checkBox = RequestUtils.getParam(request, "checkBox", 0);
		String currency = RequestUtils.getParam(request, "currency", "");
		List<QueryDescription> invList = null;
		if(!StringUtils.isEmpty(currency) && currency.equals(RequestUtils.getUserSession(request).getCurrency())) {
			if(checkBox == 1)  {
				invList = this.getMemoService().getInvoiceList(partyAccId, accType, exRate);	
			}else invList = this.getMemoService().getInvoiceList(partyAccId, accType);
		}
		else invList = this.getMemoService().getInvoiceList(partyAccId, accType, exRate);			
		String json = JSONUtil.convertToJSON(invList);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF, partyAccid));
		return new ModelAndView("memo/invoiceReport");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invReportGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		/*String invType =  RequestUtils.getParam(request, "status", "");
		String invDate = RequestUtils.getParam(request, "invoiceDate", "");
		Integer partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		Integer partyAddId = RequestUtils.getParam(request, "partyAddId", -1);
		Integer partyId = RequestUtils.getParam(request, "partyId", -1);
		String invStatus = RequestUtils.getParam(request, "invStatus", "");*/
		
		String jsonStr = RequestUtils.getParam(request, "filters", "");
		String jsonQry = ""; 
		if(!StringUtils.isEmpty(jsonStr))
			jsonQry = JSONUtil.getQueryString(jsonStr);
		
		Integer page = RequestUtils.getParam(request, "page", -1);
		Integer pageSize = RequestUtils.getParam(request, "rows", -1);
		String srtIndex = RequestUtils.getParam(request, "srtIndex", "");
		String srtType = RequestUtils.getParam(request, "srtType", "");
		
		Integer selfPartyAccId = RequestUtils.getParam(request, "selfPartyAccId", -1);;
		if(selfPartyAccId == -1)
			selfPartyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		
		//TODO for roles based login
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		userId = -1;
		
		PageList pageList= this.getMemoService().getInvoiceList(jsonQry, page, pageSize, srtIndex, srtType, userId, selfPartyAccId);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invSubGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer id = RequestUtils.getParam(request, "id", -1); 
		List<PacketDetails> list= this.getMemoService().getPktDetailsByInvId(Integer.toString(id));
		PageList pageList =new PageList();
		pageList.setResults(list);
		pageList.setRecordSize(list.size());
		pageList.setPageNo(1);
		pageList.setPageSize(list.size());
		pageList.setPageAmount(1);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView transferPkts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String[] pktIds = request.getParameterValues("selectedPkts[]");
		Integer partyAccId =  RequestUtils.getParam(request, "partyAccId", -1); 
		
		UserSession userSession = RequestUtils.getUserSession(request);
		
		int success =0;
		String[] responscArr = new String[2];

		if(pktIds!= null && pktIds.length > 0 ){
			success = this.getMemoService().trnPkts(pktIds, partyAccId, userSession);
		}

		if(success > 0){
			responscArr[0]="ok";
			responscArr[1]="Packets Has be transfered to specified location";
		}else if(success == 0){
			responscArr[0]="fail";
			responscArr[1]="Packets are not authorised to transfer. Packets are not at your location";
		}else if(success == -1){
			responscArr[0]="fail";
			responscArr[1]="Some problem in transfer";
		}
		String json = JSONUtil.convertToJSON(responscArr);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView paymentReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF, partyAccid));
		
		return new ModelAndView("memo/paymentReport");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView paymentReportGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String jsonStr = RequestUtils.getParam(request, "filters", "");
		String jsonQry = ""; 
		if(!StringUtils.isEmpty(jsonStr))
			jsonQry = JSONUtil.getQueryString(jsonStr);
		
		Integer page = RequestUtils.getParam(request, "page", -1);
		Integer pageSize = RequestUtils.getParam(request, "rows", -1);
		String srtIndex = RequestUtils.getParam(request, "srtIndex", "");
		String srtType = RequestUtils.getParam(request, "srtType", "");
		
		Integer selfPartyAccId = RequestUtils.getParam(request, "selfPartyAccId", -1);
		int paymentId = RequestUtils.getParam(request, "paymentId", -1);
		String partyName = RequestUtils.getParam(request, "partyName", "");
		String mode = RequestUtils.getParam(request, "mode", "");
		String frDate = RequestUtils.getParam(request, "frDate", "");
		String toDate = RequestUtils.getParam(request, "toDate", "");
		if(selfPartyAccId == -1)
			selfPartyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		
		//TODO for roles based login
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		userId = -1;
		
		PageList pageList= this.getMemoService().getPaymentList(jsonQry, page, pageSize, srtIndex, srtType, userId, selfPartyAccId, paymentId, partyName, mode, frDate, toDate);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView paymentSubGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer paymentId = RequestUtils.getParam(request, "id", -1); 
		
		List<QueryDescription> list= this.getMemoService().getPaymentDetails(paymentId);
		PageList pageList =new PageList();
		pageList.setResults(list);
		pageList.setRecordSize(list.size());
		pageList.setPageNo(1);
		pageList.setPageSize(list.size());
		pageList.setPageAmount(1);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("STATUS", this.getMemoService().getStatus(1));
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		if(RequestUtils.getParam(request, "rep", "").equals("sell")){
			request.setAttribute("status" ,"4,9,10");
		}
		
		return new ModelAndView("memo/stockReport");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockReportGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String jsonStr = RequestUtils.getParam(request, "filters", "");
		String jsonQry = ""; 
		if(!StringUtils.isEmpty(jsonStr))
			jsonQry = JSONUtil.getQueryString(jsonStr);
		
		Integer page = RequestUtils.getParam(request, "page", -1);
		Integer pageSize = RequestUtils.getParam(request, "rows", -1);
		String srtIndex = RequestUtils.getParam(request, "srtIndex", "");
		String srtType = RequestUtils.getParam(request, "srtType", "");
		
		Integer selfPartyAccId = RequestUtils.getParam(request, "selfPartyAccId", -1);;
		if(selfPartyAccId == -1)
			selfPartyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		
		String[] statusArr = request.getParameterValues("status");
		if(statusArr ==null || statusArr.length ==0){
			jsonQry += " and s.status in (1) ";
			
		}else{
			jsonQry += " and s.status in ("+StringUtils.toString(statusArr, ",")+") ";
		}
		//TODO for roles based login
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		userId = -1;
		
		PageList pageList= this.getMemoService().getStockReportData(jsonQry, page, pageSize, srtIndex, srtType, userId, selfPartyAccId);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	/**
	 * Edit Pricing Bulk
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockPriceBulkEditAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		
		Double rapChange = Double.parseDouble(RequestUtils.getParam(request, "rapChange", "0"));
		Integer pktID = RequestUtils.getParam(request, "id", 0);
		
		String[] sh = request.getParameterValues("sh"); 
		String[] c = request.getParameterValues("c"); 
		String[] pu = request.getParameterValues("pu"); 
		Double ctsFr = Double.parseDouble(RequestUtils.getParam(request, "cts_from", "0"));
		Double ctsTo = Double.parseDouble(RequestUtils.getParam(request, "cts_to", "0"));
		
		String whereClause = "";
		
		if(sh!=null && sh.length>0){
			whereClause += " and sp.SH_so in("+StringUtils.toString(sh, ",")+") ";
		}
		if(c!=null && c.length>0){
			whereClause += " and sp.C_so in("+StringUtils.toString(c, ",")+") ";
		}
		if(pu!=null && pu.length>0){
			whereClause += " and sp.PU_so in("+StringUtils.toString(pu, ",")+") ";
		}
		if(ctsFr>0 && ctsTo>0){
			whereClause += " and sp.CTS >= "+ ctsFr +" and sp.CTS <= "+ctsTo+" ";
		}
		
		
		int result= 0;
		if(rapChange != 0 && rapChange < 10 && rapChange >-10)
			result = getPriceService().updateStockPriceBulk(whereClause, rapChange, partyAccId, userId);
		
		String[] responseArr =new String[2];
		if(result > 1){
			responseArr[0] = "true";
			responseArr[1] = "Successfully Updated rates for criteria";
		}if(result == 0){
			responseArr[0] ="false";
			responseArr[1] ="You may not have rights to Change price/or data doesn't exists";
		}
		else{
			responseArr[0] ="false";
			responseArr[1] ="Error in updating price ";
		}
		
		String json = JSONUtil.convertToJSON(responseArr);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;

	}
	
	
	/**
	 * Edit Email List AUTO COMPLETE
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView partyEmailListAC(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String qry = RequestUtils.getParam(request, "q", "");
		
		List emlList = this.getPartyService().getPartyEmailList(qry);
		String json = JSONUtil.convertToJSON(emlList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;

	}
	/**
	 * Edit Email List AUTO COMPLETE
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteParty(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Integer partyId = RequestUtils.getParam(request, "partyId", -1);
		Integer partyAddId = RequestUtils.getParam(request, "partyAddId", -1);
		if(partyId >0)
			this.getPartyService().deleteParty(partyId);
		else{
			this.getPartyService().deletePartyAdd(partyAddId);
		}
		//return new ModelAndView(new RedirectView("partyList.html"));
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write("1");
		response.getWriter().flush();

		return null;
	}
	
	/**
	 * Edit Memo Ex Rate
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateMemoExRate(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		OrderMasterData omd = new OrderMasterData();
		omd.setId(RequestUtils.getParam(request, "id", -1));
		omd.setExrate(Double.parseDouble(RequestUtils.getParam(request, "exrate", "0")));
		
		getStockService().editMemoExRate(omd);
		
		//return new ModelAndView(new RedirectView("partyList.html"));
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write("1");
		response.getWriter().flush();

		return null;
	}
	
	/**
	 * Edit Memo Ex Rate
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateMemoType(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		OrderMasterData omd = new OrderMasterData();
		omd.setId(RequestUtils.getParam(request, "orderId", -1));
		omd.setOrderType(RequestUtils.getParam(request, "orderType", Constants.ORDER_TYPE_CONFRIM));
		
		//update memo status
		this.getStockService().editMemoType(omd);
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		
		
		//insert invoice
		InvoiceMaster im = new InvoiceMaster();
		
		Integer consigneeId =  RequestUtils.getParam(request, "consigneeId", -1); 
		String consigneeName = RequestUtils.getParam(request, "consigneeName", "");
		if(StringUtils.isEmpty(consigneeName))
			im.setConsigneePartyId(consigneeId);
		
		im.setConsigneeName(consigneeName);
		im.setExpRefNo(RequestUtils.getParam(request, "expRefNo", "")); 
		im.setOthRefNo(RequestUtils.getParam(request, "othRefNo", "")); 
		im.setDestination(RequestUtils.getParam(request, "destination", "")); 
		im.setPreCarrierPartyId(RequestUtils.getParam(request, "preCarrierPartyId", -1)); 
		im.setPlaceOfPreCarrier(RequestUtils.getParam(request, "placeOfPreCarrier", "N.A.")); 
		im.setVesselFlight(RequestUtils.getParam(request, "vesselFlight","By Air")); 
		im.setPortOfLoad(RequestUtils.getParam(request, "portOfLoad", "Mumbai")); 
		im.setPortOfDischarge(RequestUtils.getParam(request, "portOfDischarge","")); 
		
		//When order is confirmed check and insert order in invoice 
		this.getMemoService().insertMemoToInvoice(im, omd.getId(), userId);
		
		//return new ModelAndView(new RedirectView("partyList.html"));
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write("1");
		response.getWriter().flush();

		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView partyOSReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("party/partyOsRep");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView partyOSReportGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String jsonStr = RequestUtils.getParam(request, "filters", "");
		String jsonQry = ""; 
		if(!StringUtils.isEmpty(jsonStr))
			jsonQry = JSONUtil.getQueryString(jsonStr);
		
		Integer page = RequestUtils.getParam(request, "page", -1);
		Integer pageSize = RequestUtils.getParam(request, "rows", -1);
		String srtIndex = RequestUtils.getParam(request, "srtIndex", "");
		String srtType = RequestUtils.getParam(request, "srtType", "");
		
		//TODO for roles based login
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		userId = -1;
		
		PageList pageList= this.getMemoService().getPartyOS(jsonQry, page, pageSize, srtIndex, srtType, userId);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView purchaseReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("issue/purchaseReport");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView purchaseReportGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String jsonStr = RequestUtils.getParam(request, "filters", "");
		String jsonQry = ""; 
		if(!StringUtils.isEmpty(jsonStr))
			jsonQry = JSONUtil.getQueryString(jsonStr);
		
		Integer page = RequestUtils.getParam(request, "page", -1);
		Integer pageSize = RequestUtils.getParam(request, "rows", -1);
		String srtIndex = RequestUtils.getParam(request, "srtIndex", "");
		String srtType = RequestUtils.getParam(request, "srtType", "");
		
		
		Integer selfPartyAccId = RequestUtils.getParam(request, "selfPartyAccId", -1);;
		if(selfPartyAccId == -1)
			selfPartyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		
		//TODO for roles based login
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		userId = -1;
		
		PageList pageList= this.getMemoService().getPaurchaseRep(jsonQry, page, pageSize, srtIndex, srtType, userId);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView purchaseReportSubGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer purchaseId = RequestUtils.getParam(request, "id", -1); 
		
		List<PurchaseDetails> list= this.getMemoService().getPaurchaseDetails("", purchaseId);
		PageList pageList =new PageList();
		pageList.setResults(list);
		pageList.setRecordSize(list.size());
		pageList.setPageNo(1);
		pageList.setPageSize(list.size());
		pageList.setPageAmount(1);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView termList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("crm/termList");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView termListGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<TermMaster> list= this.getPartyService().getTerms();
		PageList pageList =new PageList();
		pageList.setResults(list);
		pageList.setRecordSize(list.size());
		pageList.setPageNo(1);
		pageList.setPageSize(list.size());
		pageList.setPageAmount(1);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView termEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long id = Long.parseLong(RequestUtils.getParam(request, "id", "-1")); 
		if(id >-1){
			TermMaster tm = this.getPartyService().getTermsById(id);
			request.setAttribute("tm", tm);
		}
		return new ModelAndView("crm/termEdit");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView termEditSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long id = Long.parseLong(RequestUtils.getParam(request, "id", "-1")); 
		TermMaster tm = null ;
		if(id >-1){
			tm = this.getPartyService().getTermsById(id);
			request.setAttribute("tm", tm);
		}else{
			tm = new TermMaster();
		}
		tm.setTermName(RequestUtils.getParam(request, "termName", ""));
		tm.setTermCode(RequestUtils.getParam(request, "termCode", ""));
		tm.setTermDesc(RequestUtils.getParam(request, "termDesc", ""));
		
		tm.setComm1(Double.parseDouble(RequestUtils.getParam(request, "comm1", "0")));
		tm.setComm2(Double.parseDouble(RequestUtils.getParam(request, "comm2", "0")));
		tm.setComm3(Double.parseDouble(RequestUtils.getParam(request, "comm3", "0")));
		tm.setBrokComm1(Double.parseDouble(RequestUtils.getParam(request, "brokComm1", "0")));
		tm.setBrokComm2(Double.parseDouble(RequestUtils.getParam(request, "brokComm2", "0")));
		tm.setCreditDays(Double.parseDouble(RequestUtils.getParam(request, "creditDays", "0")));
		tm.setInterest(Double.parseDouble(RequestUtils.getParam(request, "interest", "0")));
		tm.setInterestDay(Double.parseDouble(RequestUtils.getParam(request, "creditDays", "0")));
		tm.setFactor(Double.parseDouble(RequestUtils.getParam(request, "factor", "0")));
		tm.setRap(Double.parseDouble(RequestUtils.getParam(request, "rap", "0")));
		if(id >-1){
			this.getPartyService().editTerm(tm);
		}else{
			this.getPartyService().addTerm(tm);
		}
		
		return new ModelAndView(new RedirectView("termList.html"));
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView termDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long id = Long.parseLong(RequestUtils.getParam(request, "id", "-1")); 
		if(id >-1){
			TermMaster tm = this.getPartyService().getTermsById(id);
			request.setAttribute("tm", tm);
		}
		return new ModelAndView("crm/termsEdit");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView changeCRMPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ModelAndView("crm/changePwd");
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateCRMPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oldPwd =RequestUtils.getParam(request, "oldPwd", "");
		String newPwd =RequestUtils.getParam(request, "newPwd", "");
		String rePwd =RequestUtils.getParam(request, "rePwd", "");
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		
		int i = 0;
		if(rePwd.equals(newPwd)){
			i = this.getGenericService().updatePassword(oldPwd, newPwd, userId);
		}
		
		request.setAttribute("RESULT", i);
		
		return new ModelAndView("crm/changePwd");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView userManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ModelAndView("crm/userManager");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView userListGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<UserSession> list= this.getGenericService().getUsersList();
		PageList pageList = new PageList();
		pageList.setResults(list);
		pageList.setRecordSize(list.size());
		pageList.setPageNo(1);
		pageList.setPageSize(list.size());
		pageList.setPageAmount(1);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView userManagerEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("USER_ROLES", this.getGenericService().getUserRoles());
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF,RequestUtils.getUserSession(request).getPartyAccId()));
		
		Long id = Long.parseLong(RequestUtils.getParam(request, "id", "-1")); 
		UserSession userSession = new UserSession();
		if(id >-1){
			userSession = this.getGenericService().getUserDetails(id);
		}
		request.setAttribute("USER_DETAILS", userSession);
		request.setAttribute("USER_ACTIVITY", this.getGenericService().getRoleActivity(userSession.getRoleId()));
		
		return new ModelAndView("crm/userEdit");
	}
	
	/**
	 * Check CRM UserName of user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView checkUserId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String userName = RequestUtils.getParam(request, "userName", "");
		String success="ok", json="";
		
		if(getGenericService().checkCRMUserName(userName)>0){
			success="fail";
		}
		String[] data = new String[1];
		data[0]=success;
		
		json = JSONUtil.convertToJSON(data);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView userManagerEditSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Long id = Long.parseLong(RequestUtils.getParam(request, "id", "-1")); 
		
		UserSession userSession = new UserSession();
		userSession.setUserId(RequestUtils.getParam(request, "id", -1));
		userSession.setUserName(RequestUtils.getParam(request, "userName", ""));
		userSession.setRoleId(RequestUtils.getParam(request, "roleId", -1));
		userSession.setPartyAccId(RequestUtils.getParam(request, "partyAccId", -1));
		String password = RequestUtils.getParam(request, "password", "");
		int i = 0;
		
		int userId = RequestUtils.getUserSession(request).getUserId();
		if(id >-1){
			i = this.getGenericService().updateCRMUser(userSession, password, userId);
		}else{
			i =  this.getGenericService().insertCRMUser(userSession, password, userId);
		}
		if(id  <= -1){
			return new ModelAndView(new RedirectView("userManager.html"));
		}else{
			return new ModelAndView(new RedirectView("userManagerEdit.html?id="+id));
		}
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView roleManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("ROLE_LIST", this.getGenericService().getUserRoles());
		int roleId = RequestUtils.getParam(request, "roleId", -1); 
		if(roleId > -1)
			request.setAttribute("ROLE_ACTIVITY", this.getGenericService().getRoleActivity(roleId));
		return new ModelAndView("crm/roleManage");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView roleActivitySubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int roleId = RequestUtils.getParam(request, "roleId", -1); 
		if(roleId > -1){
			List<UserActivity> uaList = this.getGenericService().getRoleActivity(roleId);
			
			for (UserActivity userActivity : uaList) {
				int act = RequestUtils.getParam(request,"act_"+userActivity.getActivityId() , 0);
				userActivity.setAccessFlag(act); 
				userActivity.setRoleId(roleId);
				userActivity.setUserId(0);
			}
			int i[] = this.getGenericService().updateRoleActivity(uaList);
			request.setAttribute("ROLE_ACTIVITY",uaList);
		}
		return new ModelAndView(new RedirectView("roleManager.html?roleId="+roleId));
	}
	
	
	public ModelAndView demandForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_DEMAND, 
				Constants.MODULE_VALID_FLAG);
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, RequestUtils.getUserSession(request).getPartyAccId()));
		
		request.setAttribute("prpList", prpList);
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		
		return new ModelAndView("party/demand");
	}
	
	public ModelAndView demandSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<PrpData> prpRsltLst =  this.getCommonService().getResultPrpList(Integer.toString(3));
		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_DEMAND, 
				Constants.MODULE_VALID_FLAG);

		UserSession us = RequestUtils.getUserSession(request);
		DemandMaster dm = new DemandMaster();
		
		dm.setDmdType(RequestUtils.getParam(request,"dmdType" , "REC"));
		dm.setPartyAccId(RequestUtils.getParam(request,"partyAccId" , -1));
		
		dm.setPartyAccId(RequestUtils.getParam(request,"partyAccId" , -1));
		dm.setFromDate(RequestUtils.getParam(request,"fromDate" , null));
		dm.setTodate(RequestUtils.getParam(request,"toDate" , null));
		
		dm.setAutoMail(RequestUtils.getParam(request,"autoMail" , 0));
		dm.setAutoMemo(RequestUtils.getParam(request,"autoMemo" , 0));
		dm.setNoPrice(RequestUtils.getParam(request,"noPrice" , 0));
		dm.setDemandName(RequestUtils.getParam(request,"demandName" , ""));
		
		List<SearchPrpData> searchPrpList = new ArrayList<SearchPrpData>();
		
		for(int i= 0; i< prpList.size();i++){
			SearchPrpData searchPrpData = new SearchPrpData();
			PrpData pd = new PrpData();
			pd = prpList.get(i);
			
			Float frVal =RequestUtils.getParam(request, pd.getPrp()+"_from", -1f);
			Float toVal =RequestUtils.getParam(request, pd.getPrp()+"_to", -1f);
			
			if(pd.getPrp().equalsIgnoreCase("C")){
				if(RequestUtils.getParam(request, "fancy", -1)==1){
					frVal = null;
					toVal = null;
				}
			}
			if(pd.getPrp().equals("LAB")){
				if(frVal == 9999f){
					frVal = null;
					toVal = null;
				}
			}
			
			if(pd.getFieldDisplayType().equalsIgnoreCase(Constants.FIELD_TYPE_MULTI_SELECT) ){
				String prpInArr[] = request.getParameterValues(pd.getPrp()) ;
				if(prpInArr !=null && prpInArr.length >0){
					String labStr = StringUtils.toString(prpInArr, ", ");
					if(labStr.contains("9999")){
						frVal = null;
						toVal = null;
					}else{
						searchPrpData.setPrpIn(labStr);
					}
				}
				logger.debug("\n $$$$$$$ ==========> SH and SIZE "+request.getParameterValues("SH") +" " +pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
				
			}
			if((frVal ==null || toVal ==null || frVal != -1f || toVal != -1f)  || !StringUtils.isEmpty(searchPrpData.getPrpIn())){
				if(pd.getFieldDisplayType().equalsIgnoreCase(Constants.FIELD_TYPE_SELECT))
					toVal=frVal;
				searchPrpData.setPrpId(pd.getId());	
				searchPrpData.setPrp(pd.getPrp());	
				searchPrpData.setPrpFromNum(frVal);
				searchPrpData.setPrpToNum(toVal);
				searchPrpList.add(searchPrpData);
			}
			
			logger.debug(" $$$$$$$ ==========> "+pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
			
			
		}
		dm.setSearchPrpList(searchPrpList);
		this.getPartyService().addPartyDemand(dm, us);
		
		/**
		 * TODO for AJAX
		 */
		
		return new ModelAndView("party/demandList");
	}
	
	public ModelAndView demandList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, RequestUtils.getUserSession(request).getPartyAccId()));
		return new ModelAndView("party/demandList");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView demandListGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer partyAccId =  RequestUtils.getParam(request, "partyAccId", -1);
		Integer page = RequestUtils.getParam(request, "page", 1);
		Integer pageSize = RequestUtils.getParam(request, "rows", 20);
		String srtIndex = RequestUtils.getParam(request, "sidx", "");
		String srtType = RequestUtils.getParam(request, "sord", "");
		PageList pageList = new PageList();
		
		pageList= this.getPartyService().getDemandListByParty(partyAccId, page, pageSize, srtIndex, srtType);
		
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView demandDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, RequestUtils.getUserSession(request).getPartyAccId()));
		request.setAttribute("DEMAND_DETAILS", this.getPartyService().getPartyAcc(1,1,null,null, RequestUtils.getUserSession(request).getPartyAccId()));
//TODO
		return new ModelAndView("party/demand");
	}
	
	public ModelAndView comming(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("comming");
	}
	
	
	/**
	 * ALl users list in CP
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView webUsers(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		int pageNo= RequestUtils.getParam(request, "page", 1);
		int pageSize= RequestUtils.getParam(request, "pageSize", -1);
		
		request.setAttribute("pageList",this.getUserService().getUsers(pageSize, pageNo, -1, ""));
		return new ModelAndView("party/webUsers");
	}
	
	/**
	 * User edit CP 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView webUserEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int userId = RequestUtils.getParam(request, "userId", -1);
		UserSession uSession =  this.userService.getUserDetails(userId);
		
		request.setAttribute("user", uSession);
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, RequestUtils.getUserSession(request).getPartyAccId()));
		
		request.setAttribute("byrList", this.getUserService().getBuyersQD());
		request.setAttribute("termsList", this.getCommonService().getTermsList());
		return new ModelAndView("party/webUserEdit");
	}
	
	/**
	 * Updation of user details by admin
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView webUserSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
			String uName = RequestUtils.getParam(request, "uNm", "");
			UserSession uSession =  new UserSession();
			
			uSession.setTermId(RequestUtils.getParam(request, "trm", -1));
			uSession.setPassword(RequestUtils.getParam(request, "pwd", ""));
			uSession.setStatus(RequestUtils.getParam(request, "status", 0));
			uSession.setPartyAccId(RequestUtils.getParam(request, "partyAccId", -1));
			uSession.setUserName(uName);
			uSession.setUserId(RequestUtils.getParam(request, "userId", -1));
			
			int webUpdate = RequestUtils.getParam(request, "webUpdate", -1);
			
			if(uSession.getPartyAccId() ==-1 ){
				//Create new party from registration id
				RegistrationDO reDo = this.registrationDao.getUserDetails(uSession.getUserId());
				PartyMasterData pmd = new PartyMasterData();
				pmd.setCompanyName(reDo.getCompanyName());
				pmd.setBusinessType(reDo.getBusinessType());
				pmd.setEmail(reDo.getcEmail());
				pmd.setMembersOfAccociation(reDo.getMemberOfAssociation());
				
				if(reDo.getTelphone1()!=null)
				pmd.setPhoneNo1(Long.parseLong(reDo.getTelphone1()));
				if(reDo.getTelphone2()!=null)
				pmd.setPhoneNo2(Long.parseLong(reDo.getTelphone2()));
				pmd.setTypeOfParty(Constants.PARTY_TYPE_BUYER);
				pmd.setComments(reDo.getComments());
				if(reDo.getFax()!=null)
				pmd.setFax(Long.parseLong(reDo.getFax()));
				
				pmd.setTradeRefComp1(reDo.getTradeCompanyName());
				pmd.setTradeRefComp2(reDo.getTradeCompanyName1());
				pmd.setTradeRefContact1(reDo.getContactPerson());
				pmd.setTradeRefContact2(reDo.getContactPerson1());
				if(reDo.getTrMobile1()!=null)
				pmd.setTradeRefMobile1(Long.parseLong(reDo.getTrMobile1()));
				if(reDo.getTrMobile2()!=null)
				pmd.setTradeRefMobile2(Long.parseLong(reDo.getTrMobile2()));
				if(reDo.getPhoneNo1()!=null)
				pmd.setTradeRefPhone1(Long.parseLong(reDo.getPhoneNo1()));
				if(reDo.getPhoneNo2()!=null)
				pmd.setTradeRefPhone2(Long.parseLong(reDo.getPhoneNo2()));
				pmd.setActiveFlag(1);
				
				//Address master
				PartyAddMaster pam = new PartyAddMaster();
				pam.setAddress1(reDo.getAddress());
				pam.setCity(reDo.getCity());
				pam.setState(reDo.getState());
				pam.setCountry(reDo.getCountry());
				pam.setPin(reDo.getPinCode());
				pam.setBranchCode(reDo.getCity());
				pam.setContactPerson(reDo.getCName());
				
				PartyShipAdd psa = new PartyShipAdd();
				psa.setAddress1(reDo.getShipAddress());
				psa.setCity(reDo.getShipCity());
				psa.setState(reDo.getShipState());
				psa.setCountry(reDo.getShipCountry());
				psa.setPin(reDo.getShipPinCode());
				psa.setContactPerson(reDo.getShipName());
				pam.setPartyShipAdd(psa);//set ship add master
				psa.setPartyAddMaster(pam);//Set add master
				PartyAccData pad =new PartyAccData();
				pad.setTermId(uSession.getTermId());
				pad.setAccType("E");
				List<PartyAccData> padList = new ArrayList<PartyAccData>();
				padList.add(pad);
				pam.setPartyAccs(padList);
				
				List<PartyAddMaster> pamList = new ArrayList<PartyAddMaster>();
				pamList.add(pam);
				pmd.setPartyAddMasters(pamList);
				
				this.partyService.insertPartyMaster(pmd);
				
				uSession.setPartyAccId(pmd.getPartyAddMasters().get(0).getPartyAccs().get(0).getId());
				
			}else if(webUpdate == 1){
				RegistrationDO reDo = this.registrationDao.getUserDetails(uSession.getUserId());

				Integer partyId = this.getPartyService().getPartyIDByPartyAccId(uSession.getPartyAccId());
				Integer partyAddId = this.getPartyService().getPartyAddIdByPartyAccId(uSession.getPartyAccId());
				PartyMasterData partyMasterData= this.getPartyService().findByPartyMasterById(partyId);
				
				//Edit new party from registration id
				PartyMasterData pmd = new PartyMasterData();
				pmd.setId(partyId);
				pmd.setCompanyName(reDo.getCompanyName());
				pmd.setBusinessType(reDo.getBusinessType());
				pmd.setEmail(reDo.getcEmail());
				pmd.setMembersOfAccociation(reDo.getMemberOfAssociation());
				
				if(reDo.getTelphone1()!=null)
				pmd.setPhoneNo1(Long.parseLong(reDo.getTelphone1()));
				if(reDo.getTelphone2()!=null)
				pmd.setPhoneNo2(Long.parseLong(reDo.getTelphone2()));
				pmd.setTypeOfParty(partyMasterData.getTypeOfParty());
				pmd.setComments(reDo.getComments());
				if(reDo.getFax()!=null)
				pmd.setFax(Long.parseLong(reDo.getFax()));
				
				pmd.setTradeRefComp1(reDo.getTradeCompanyName());
				pmd.setTradeRefComp2(reDo.getTradeCompanyName1());
				pmd.setTradeRefContact1(reDo.getContactPerson());
				pmd.setTradeRefContact2(reDo.getContactPerson1());
				if(reDo.getTrMobile1()!=null)
				pmd.setTradeRefMobile1(Long.parseLong(reDo.getTrMobile1()));
				if(reDo.getTrMobile2()!=null)
				pmd.setTradeRefMobile2(Long.parseLong(reDo.getTrMobile2()));
				if(reDo.getPhoneNo1()!=null)
				pmd.setTradeRefPhone1(Long.parseLong(reDo.getPhoneNo1()));
				if(reDo.getPhoneNo2()!=null)
				pmd.setTradeRefPhone2(Long.parseLong(reDo.getPhoneNo2()));
				pmd.setActiveFlag(partyMasterData.getActiveFlag());
				
				//Address master
				PartyAddMaster pam = new PartyAddMaster();
				pam.setId(partyAddId);
				pam.setAddress1(reDo.getAddress());
				pam.setCity(reDo.getCity());
				pam.setState(reDo.getState());
				pam.setCountry(reDo.getCountry());
				pam.setPin(reDo.getPinCode());
				pam.setBranchCode(reDo.getCity());
				pam.setContactPerson(reDo.getCName());
				
				PartyShipAdd psa = new PartyShipAdd();
				psa.setPartyAddId(partyAddId);
				psa.setAddress1(reDo.getShipAddress());
				psa.setCity(reDo.getShipCity());
				psa.setState(reDo.getShipState());
				psa.setCountry(reDo.getShipCountry());
				psa.setPin(reDo.getShipPinCode());
				psa.setContactPerson(reDo.getShipName());
				pam.setPartyShipAdd(psa);//set ship add master
				psa.setPartyAddMaster(pam);//Set add master
				PartyAccData pad =new PartyAccData();
				pad.setId(uSession.getPartyAccId());
				pad.setTermId(uSession.getTermId());
				pad.setAccType("E");
				List<PartyAccData> padList = new ArrayList<PartyAccData>();
				padList.add(pad);
				pam.setPartyAccs(padList);
				
				List<PartyAddMaster> pamList = new ArrayList<PartyAddMaster>();
				pamList.add(pam);
				pmd.setPartyAddMasters(pamList);
				
				this.getPartyService().update(pmd);
			}
			
			int i =0;
			if(RequestUtils.getParam(request, "edit", -1) == 1)
			{
				i = this.getUserService().updateUser(uSession);
				logger.debug("OK Updated");
			}else{
				//TODO
			}		
			if(i>0)
				request.setAttribute("msg", "Successfully Updated");
			return new ModelAndView(new RedirectView("webUsers.html"));
	}
	
	public ModelAndView webRegistrationView(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int userId = RequestUtils.getUserSession(request).getUserId();
		if(RequestUtils.getUserSession(request).getRoleId()!=1){
			userId = RequestUtils.getParam(request, "uID", userId);
			logger.debug("####################"+userId);
		}
		
		RegistrationDO rd = registrationDao.getUserDetails(userId);
		if(rd ==null)
			request.setAttribute("registrationDO", new RegistrationDO());
		else
			request.setAttribute("registrationDO", rd);
		return new ModelAndView("party/webRegistration");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView approveMemo(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		OrderMasterData omd = new OrderMasterData();
		omd.setId(RequestUtils.getParam(request, "orderId", -1));
		omd.setStatus(2);
		int  i = getStockService().approveWebMemo(omd);
		
		
		//return new ModelAndView(new RedirectView("partyList.html"));
		response.setContentType("text/plain; charset=UTF-8");
		if(i ==0){
			response.getWriter().write("0");	
		}else{
			response.getWriter().write("1");
		}
		response.getWriter().flush();

		return null;
	}
	
	
	public ModelAndView editMemoDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		OrderMasterData omd = new OrderMasterData();
		omd.setId(RequestUtils.getParam(request, "orderId", -1));
		omd.setBrokerId(RequestUtils.getParam(request, "brokerId", -1));
		omd.setBrokrage(Double.parseDouble(RequestUtils.getParam(request, "brokrage", "0")));
		int  i = getStockService().editMemoDetails(omd);
		
		
		//return new ModelAndView(new RedirectView("partyList.html"));
		response.setContentType("text/plain; charset=UTF-8");
		if(i ==0){
			response.getWriter().write("0");	
		}else{
			response.getWriter().write("1");
		}
		response.getWriter().flush();

		return null;
	}
	
	//StockEditing_k
	public ModelAndView memoEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//request.setAttribute("pktDtl", this.getCommonService().getPrpLOV());
		int orderId = RequestUtils.getParam(request, "orderId", -1);
		//SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		OrderMasterData omd = this.getStockService().getOrderMasterDetail( orderId);
		omd.setId(orderId);
		//PartyMasterData pm = this.getPartyService().findByPartyMasterById(omd.getPartyAccId());
		
		omd.setPacketList(this.getStockService().getPktDetailsByOrderId(-1, Integer.toString(orderId),Constants.STATUS_APPROVED));
		Double totalCts=0D,total=0D; 
		for (int i = 0; i < omd.getPacketList().size(); i++) {
			PacketDetails pd = new PacketDetails();
			pd =omd.getPacketList().get(i);
			total+= (pd.getRate()*pd.getCts());
			totalCts += pd.getCts();
		}
		NumberFormat nf = new DecimalFormat("#0.00");
		omd.setTotalCts(Double.valueOf(nf.format(totalCts)));
		omd.setTotalAmount(Double.valueOf(nf.format(total)));
	
	   
		
		
		Map otherDetails = this.getMemoService().getOrderAddDetails(omd) ;
		
		int totalMemoPkt = omd.getPacketList().size();
		int PKT_PER_MEMO = 10;
		int memoPrint = totalMemoPkt/10;
		if(totalMemoPkt % 10 > 0)
			memoPrint ++;
		
		
		
		//request.setAttribute("CURR_DATE", sdf.format(new Date()));
		
		request.setAttribute("BROKER_LIST", this.getPartyService().getPartyBrokerParties(1, omd.getBrokerId()));
		request.setAttribute("memoCount",memoPrint);
		request.setAttribute("otherDetailts",otherDetails);
		request.setAttribute("orderMasterData",omd);
		request.setAttribute("TERMS_LIST", this.getCommonService().getTermsList());
		request.setAttribute("TERMS_MASTER", this.getPartyService().getTerms());
		return new ModelAndView("memo/memoEdit");
	}
	
	public ModelAndView saveMemoEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//request.setAttribute("pktDtl", this.getCommonService().getPrpLOV());
		//TODOD
		
		
		int orderId = RequestUtils.getParam(request, "orderId", -1);
		Integer userId = RequestUtils.getUserSession(request).getUserId();  
		//String pktCode = RequestUtils.getParam(request, "pktCode", "");
		String currency =  RequestUtils.getParam(request, "accType", "E");
 		Double factor = 0d;
 		//	if(currency.equals("L"))
 		factor = Double.parseDouble(RequestUtils.getParam(request, "exRate", "0"));
		
		String[] pktIds = request.getParameterValues("selectedPkts");
		Integer termId = RequestUtils.getParam(request, "term", 1);
		if(termId ==-1){
			termId = 1;
		}
		Integer status = RequestUtils.getParam(request, "status", 4);
		String[] responscArr = new String[3];//For error messages
		OrderMasterData omd = new OrderMasterData();
		//Incase of edit order 
		omd.setId(orderId);
		
		omd.setContactPerson(RequestUtils.getParam(request, "contactPerson", ""));
		omd.setComments(RequestUtils.getParam(request, "comments", ""));
		omd.setBrokerId(RequestUtils.getParam(request, "brokerId", -1));
		omd.setBrokrage(Double.valueOf(RequestUtils.getParam(request, "brokerage", "0")));
		omd.setTermId(termId);
		omd.setOrderDate(RequestUtils.getParam(request, "memoDate", ""));
		omd.setDueDate(RequestUtils.getParam(request, "dueDate", ""));
		omd.setExrate(factor);
		omd.setAccType(currency);
		omd.setUserId(userId);
		Double totalCts=0D,total=0D; 
		List<PacketDetails> pktList = new Vector<PacketDetails>();
		for (int i = 0; i < pktIds.length; i++) {
			logger.debug("#####  ro num =" +i +" pkt "+pktIds[i]);
				PacketDetails pktDtl = new PacketDetails();
				pktDtl.setPktId(Integer.parseInt(pktIds[i]));
				pktDtl.setRate(Double.parseDouble(RequestUtils.getParam(request,"rate_"+pktIds[i],"0" )));
				pktDtl.setRap(Double.parseDouble(RequestUtils.getParam(request,"disc_"+pktIds[i],"0" )));
				pktDtl.setCts(Double.parseDouble(RequestUtils.getParam(request,"cts_"+pktIds[i],"0" )));
				double actualTotCts = Double.parseDouble(RequestUtils.getParam(request,"totalCts_"+pktIds[i],"0" ));
				double aCts = Double.parseDouble(RequestUtils.getParam(request,"acts_"+pktIds[i],"0" ));
				if(actualTotCts > 0){
					pktDtl.setRejCts(actualTotCts - pktDtl.getCts());
					pktDtl.setFinalCts(aCts - pktDtl.getCts());
					logger.debug(actualTotCts+" %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5 "+ aCts);
				}
				
			/*	//for_totalAmmount_k
				pktDtl.setTotalRate(Double.parseDouble(RequestUtils.getParam(request,"total_"+pktIds[i],"0")));
				*/
				pktList.add(pktDtl);
				
			} 
			omd.setPacketList(pktList);
			
			if(pktList.size() >0)
				this.getMemoService().updateOrderMemo(omd);
			
			return new ModelAndView( new RedirectView("memoEdit.html?orderId="+orderId+"&submit=1" ));
		
	}
	
	
	
	//memoPrintLP_PDF  kri 2/dec
	public ModelAndView memoPrintLPPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session = request.getSession();

	int orderId = RequestUtils.getParam(request, "orderId", -1);
	int invId = RequestUtils.getParam(request, "invId", -1);
	
	String inputFile = "memoPrintLP.html?orderId="+orderId;
	int copy = Integer.parseInt(this.getGenericService().getSysPref("copy.perpage"));
	String filename = "memo-"+orderId;
	if(copy > 1){
		inputFile += "&copy="+copy;
	}
	if(orderId == -1){
		inputFile = "invPrint.html?invId="+invId;
		filename = "inv-"+invId;
	}
	response.setContentType("application/pdf");
	response.setHeader
	     ("Content-Disposition", "attachment; filename="+filename+".pdf");
	
    ITextRenderer renderer = new ITextRenderer();
   URL uri =   	new URL("http://" + request.getServerName() + ":" + request.getServerPort() + 
	        	request.getContextPath() + "/"+inputFile);
HttpURLConnection ucon = 
        (HttpURLConnection)uri.openConnection();

	ucon.setRequestProperty("Cookie", "userId="+RequestUtils.getCookie(request, "userId")
			.getValue()+";userKey="+RequestUtils.getCookie(request, "userKey")
			.getValue()+";JSESSIONID=" + session.getId());
    ucon.connect();
    if (ucon.getResponseCode() == 500) {
        System.out.println(" ERROR IN SENDING INVOICEMAIL");
    }
    InputStream in = ucon.getInputStream();
    
    InputStreamReader isr = new InputStreamReader(in);
    Reader inReader = new BufferedReader(isr);
    StringBuffer buf = new StringBuffer();
    int ch;
    while ((ch = inReader.read()) > -1) {
        buf.append((char)ch);
    }
    inReader.close();
    String dtls = buf.toString();
    
    logger.debug(dtls);
    renderer.setDocumentFromString(dtls);
    renderer.layout();
    renderer.createPDF(response.getOutputStream());
    
	return null;
}
//memoPrintLP kri 2/dec
public ModelAndView memoPrintLP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int orderId = RequestUtils.getParam(request, "orderId", -1);
		
		OrderMasterData omd = this.getStockService().getOrderMasterDetail( orderId);
		omd.setId(orderId);
		//PartyMasterData pm = this.getPartyService().findByPartyMasterById(omd.getPartyAccId());
		
		omd.setPacketList(this.getStockService().getPktDetailsByOrderId(-1, Integer.toString(orderId),Constants.STATUS_APPROVED));
		Double totalCts=0D,total=0D; 
		for (int i = 0; i < omd.getPacketList().size(); i++) {
			PacketDetails pd = new PacketDetails();
			pd =omd.getPacketList().get(i);
			total+= (pd.getRate()*pd.getCts());
			totalCts += pd.getCts();
		}
		NumberFormat nf = new DecimalFormat("#0.00");
		omd.setTotalCts(Double.valueOf(nf.format(totalCts)));
		omd.setTotalAmount(Double.valueOf(nf.format(total)));
		
		
		
		Map otherDetails = this.getMemoService().getOrderAddDetails(omd) ;
		
		int totalMemoPkt = omd.getPacketList().size();
		int PKT_PER_MEMO = 10;
		int memoPrint = totalMemoPkt/10;
		if(totalMemoPkt % 10 > 0)
			memoPrint ++;
		
		request.setAttribute("memoCount",memoPrint);
		request.setAttribute("otherDetails",otherDetails);
		request.setAttribute("orderMasterData",omd);
		
		
	return new ModelAndView("memo/memoPrintLP");
	}
	
/**
 * To stock page
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
public ModelAndView stockMix(HttpServletRequest request,
		HttpServletResponse response) throws Exception{
	
	List<PrpData> prpRsltLst =  this.getCommonService().getResultPrpList(Integer.toString(4));
	List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MEMO_MX, 
			Constants.MODULE_VALID_FLAG);
	UserSession us = RequestUtils.getUserSession(request);
		String [] colDataArr;
		
		colDataArr = this.getMemoService().getJQGridColModel(prpRsltLst,0, us);
		
	request.setAttribute("headers", colDataArr[0]);
	request.setAttribute("colModel", colDataArr[1]);
	
	colDataArr = this.getMemoService().getJQGridColModel(prpRsltLst,2, us);
	request.setAttribute("headersMemo", colDataArr[0]);
	request.setAttribute("colModelMemo", colDataArr[1]);

	
	request.setAttribute("gridLink", "memoLoadGridMix.html?q=1");
	
	//TODO in Common service half done
	//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
	request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, RequestUtils.getUserSession(request).getPartyAccId()));
	request.setAttribute("BROKER_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BROKER));
	request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
	//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
	request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1,1,null,Constants.PARTY_TYPE_SELF, RequestUtils.getUserSession(request).getPartyAccId()));
	request.setAttribute("TERMS_LIST", this.getCommonService().getTermsList());
	request.setAttribute("EXP_FILE_LIST", this.getGenericService().getExportFiles("OUT"));
	request.setAttribute("FILE_LIST", this.getGenericService().getExportFiles(null));
	
	
	request.setAttribute("PRP_RESULT_LIST",prpRsltLst);
	
	request.setAttribute("PRP_LIST", prpList);
	
	request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
	
	
	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
	
	request.setAttribute("CURR_DATE", sdf.format(new Date()));
	return new ModelAndView("memo/stockMix");
}

/**
 * 
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
@SuppressWarnings("unchecked")
public ModelAndView memoLoadGridMix(HttpServletRequest request, HttpServletResponse response) throws Exception {

	List<PrpData> prpRsltLst =  this.getCommonService().getResultPrpList(Integer.toString(4));
	List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MEMO_MX, 
			Constants.MODULE_VALID_FLAG);

	List<String> selectPKts = new Vector<String>(); 
	UserSession us= RequestUtils.getUserSession(request); 
	String accType =  RequestUtils.getParam(request, "accType", "");
	if(accType.equals("E"))
		accType="";
	
	//Double factor = 0d;
	Double factor =  Double.parseDouble(RequestUtils.getParam(request, "exRate", "0"));
	
	logger.debug(accType  +" =currency");
	HttpSession session = request.getSession();
	Integer userId = RequestUtils.getUserSession(request).getUserId();
	
	Integer termId = RequestUtils.getParam(request, "term", 1);
	
	Integer cashDisc =  -1;
	if(termId == -1){
		termId = 1;
		cashDisc = RequestUtils.getParam(request, "cashDisc", -1);
	}
	Double termFactor = null ;
	if(session.getAttribute(Constants.SESSION_TERM_ID)==null || termId != (Integer)session.getAttribute(Constants.SESSION_TERM_ID)){
		 termFactor = this.getPartyService().getTermsFactor(termId);
		 session.setAttribute(Constants.SESSION_TERM_ID, termId);
		 session.setAttribute(Constants.SESSION_TERM_FACTOR, termFactor);

	}else{
		termFactor = (Double) session.getAttribute(Constants.SESSION_TERM_FACTOR);
	}
	
	String json = null;
	int p =RequestUtils.getParam(request, "page", 1);
	int pageSize =RequestUtils.getParam(request, "rows", 50);
	String sIdx =RequestUtils.getParam(request, "sidx", "SZ_so");
	String sorD =RequestUtils.getParam(request, "sord", "desc");
	if(sIdx.equals("SZ_so")){
		sIdx = " isnull(shFr_so), shFr_so, isnull(cFr_so),cFr_so ,puFr_so ";
		sorD = "";
	}
	
	String[] pktIds = request.getParameterValues("selectedPkts[]");
	int cart =RequestUtils.getParam(request, "cart", 0);
	
	String pktNos=RequestUtils.getParam(request, "pktNos", "");
	String memoNos=RequestUtils.getParam(request, "memoNos", "");
	
	Integer addCriteria = RequestUtils.getParam(request, "addCriteria", 0);
	String fromSrch = RequestUtils.getParam(request, "fromSrch", "");
	
	Integer partyAccId = RequestUtils.getParam(request, "partyStock", -1);//Most important PartyAccount Id
	if(partyAccId == -1){
		partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
	}
	
	Integer rootPktSrch = RequestUtils.getParam(request, "rootPktSrch", -1);//If search by root pkt nos
	Integer srchPair = RequestUtils.getParam(request, "srchPair", -1);//If search by pair pkt nos
	//Integer cashDisc = RequestUtils.getParam(request, "cashDisc", -1);
	
	//Sorting overridden for Pair
	if(srchPair == 1){
		sIdx = "SZ_so desc, pairNo ";
		sorD = "";
	}
	
	JQGridContainer container ;
	String whereClause = "";
	List<SearchPrpData> searchPrpList = new ArrayList<SearchPrpData>();
	//If pkt or memo is given
	if(session.getAttribute(Constants.SELECTED_PKTS)!=null){
		selectPKts  = (Vector)request.getSession().getAttribute(Constants.SELECTED_PKTS);
	}
	session.setAttribute(Constants.SELECTED_PKTS, selectPKts);

	if(pktIds!=null && pktIds.length >0 && cart ==1 ){
		selectPKts.addAll(Arrays.asList(pktIds));
		whereClause = " and s.id in ("+ StringUtils.toString(pktIds, ", ")+")";

		
	}else if (!fromSrch.equals("pktNos")&& !fromSrch.equals("memoNos")) {
		/**
		 * Changed logic on 8-1-2011
		 * StringUtils.isEmpty(pktNos) && StringUtils.isEmpty(memoNos)
		*/
 		for(int i= 0; i< prpList.size();i++){
			SearchPrpData searchPrpData = new SearchPrpData();
			PrpData pd = new PrpData();
			pd = prpList.get(i);
			
			Float frVal =RequestUtils.getParam(request, pd.getPrp()+"_from", -1f);
			Float toVal =RequestUtils.getParam(request, pd.getPrp()+"_to", -1f);
			
			if(pd.getPrp().equalsIgnoreCase("C")){
				if(RequestUtils.getParam(request, "fancy", -1)==1){
					frVal = null;
					toVal = null;
				}else{
					frVal = 0f;
					toVal = 9999f;
				}
			}
			if(pd.getPrp().equals("LAB")){
				if(frVal == 9999f){
					frVal = null;
					toVal = null;
				}
			}
			
			if(pd.getFieldDisplayType().equalsIgnoreCase(Constants.FIELD_TYPE_MULTI_SELECT) ){
				String prpInArr[] = request.getParameterValues(pd.getPrp()) ;
				if(prpInArr !=null && prpInArr.length >0){
					String labStr = StringUtils.toString(prpInArr, ", ");
					if(labStr.contains("9999")){
						frVal = null;
						toVal = null;
					}else{
						searchPrpData.setPrpIn(labStr);
					}
				}
				logger.debug("\n $$$$$$$ ==========> SH and SIZE "+request.getParameterValues("SH") +" " +pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
				
			}
			if((frVal ==null || toVal ==null || frVal != -1f || toVal != -1f)  || !StringUtils.isEmpty(searchPrpData.getPrpIn())){
				if(pd.getFieldDisplayType().equalsIgnoreCase(Constants.FIELD_TYPE_SELECT))
					toVal=frVal;
				searchPrpData.setPrpId(pd.getId());	
				searchPrpData.setPrp(pd.getPrp());	
				searchPrpData.setPrpFromNum(frVal);
				searchPrpData.setPrpToNum(toVal);
				searchPrpData.setDataType(pd.getDataType());
				searchPrpList.add(searchPrpData);
				
			}
			
			logger.debug(" $$$$$$$ ==========> "+pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
			//QueryDescription qd = prpLov.get(pd.getPrp()).get(index);
			/*if(pd.getDataType()== Constants.CHARACTER){
			}else{
			}*/		
		}
 		if(srchPair ==1 ){
 			whereClause = " and s.pairStock is not null ";
 		}
 		whereClause += " and s.status in (1,2) "; //For regular search only one status allowed 
	}
	
	if(fromSrch.equals("memoNos")){
		if(rootPktSrch ==1){
			whereClause = " and s.rootPkt in ("+ memoNos.replaceAll(",","','")+")";
			container  =  this.getMemoService().getMemoStock(termFactor, prpRsltLst, whereClause,userId, p, pageSize, sIdx, sorD, accType, factor, cashDisc, true, us);
			
		}else{
			whereClause = " and om.id in ("+ memoNos+")";
			container  =  this.getMemoService().getMemoStockByMemoId(termFactor, prpRsltLst, whereClause,userId, p, pageSize, sIdx, sorD, accType, factor, true,us);
		}

	}else{
		if(fromSrch.equals("pktNos")){
			whereClause  = " and s.pktCode in ('"+ pktNos.replaceAll(",","','")+"')";
		}else if(cart ==0){
			whereClause  += this.getPrpService().getSearchCriteriaMix(searchPrpList);
		}
		logger.debug(whereClause);
		if(addCriteria ==1){
			List<String> whereClauseList ;
			if(session.getAttribute(Constants.SESSION_CRITERIA)!= null);
				whereClauseList = (List<String>) session.getAttribute(Constants.SESSION_CRITERIA);
			if(whereClauseList==null){
				whereClauseList = new ArrayList<String>();
			}	
			if(!fromSrch.equals("pktNos"))
				whereClause += " and partyAccId = "+ partyAccId;
			whereClauseList.add(whereClause);
			session.setAttribute(Constants.SESSION_CRITERIA, whereClauseList);
			container  =  this.getMemoService().getMemoStock(termFactor, prpRsltLst, whereClauseList,userId, p, pageSize, sIdx, sorD, accType, factor, cashDisc, true,us);
		}
		else{
			whereClause += " and partyAccId = "+ partyAccId;
			container  =  this.getMemoService().getMemoStock(termFactor, prpRsltLst, whereClause,userId, p, pageSize, sIdx, sorD, accType, factor, cashDisc, true,us);
			session.removeAttribute(Constants.SESSION_CRITERIA);
			session.setAttribute(Constants.SEARCH_CLAUSE, whereClause);
		}
		
	}
	
	json = JSONUtil.convertToJSON(container);
	
	response.setContentType("text/plain; charset=UTF-8");
	response.getWriter().write(json);
	response.getWriter().flush();
	
	return null;
}
//Krishna
	public ModelAndView localInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("SELF_LIST",this.getPartyService().getPartyAcc(1, 1, null,	Constants.PARTY_TYPE_SELF, RequestUtils.getUserSession(request).getPartyAccId()));
		return new ModelAndView("memo/localInvoice");

	}

	public ModelAndView reloadPartyAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pType = RequestUtils.getParam(request, "pType", "");
		String json = "";
		
		String q = RequestUtils.getParam(request, "q", "");
		if(pType.equals(Constants.PARTY_TYPE_BROKER)){
			List<QueryDescription> pList = this.getPartyService().getPartyByTypeAuto(1, Constants.PARTY_TYPE_BROKER, q);
			json =  JSONUtil.convertToJSON(pList);
		}else if(pType.equals(Constants.PARTY_TYPE_VENDOR)){
			List<QueryDescription> pList = this.getPartyService().getPartyByTypeAuto(1, Constants.PARTY_TYPE_VENDOR,q);
			json =  JSONUtil.convertToJSON(pList);
		}else{
			List<PartyAccData> pList = this.getPartyService().getPartyAccAuto(1,1,null,null,q);
			json =  JSONUtil.convertToJSON(pList);
		}
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}

	public ModelAndView getExRate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String from = RequestUtils.getParam(request, "from", "USD");
		String to = RequestUtils.getParam(request, "to", "INR");
		String json = "";
		
		String amount =  RequestUtils.getExRateJson(from, to);
		response.setContentType("text/plain; charset=UTF-8");

		response.getWriter().write(amount);
		response.getWriter().flush();
		return null;
	}
	


	
	public ModelAndView checkCompanyName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = "";
		String companyName = RequestUtils.getParam(request, "companyName", "");
		int count=this.getMemoService().checkCompName(companyName);
		json =  JSONUtil.convertToJSON(count);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	
	public ModelAndView checkMobileNo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = "";
		String companyName = RequestUtils.getParam(request, "ownerMobNo", "");
		int count=this.getMemoService().checkMobNo(companyName);
		json =  JSONUtil.convertToJSON(count);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}

	public ModelAndView stockCertUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		
		int fileId = RequestUtils.getParam(request, "fileId", -1);

		List<FileMap> fileMappingList = this.getGenericService()
				.getFileMapping(null, fileId, 0);
		request.setAttribute("fileMappingList", fileMappingList);
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));

		request.setAttribute("fileList", this.getGenericService() .getExportFiles("IN"));
		request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		// genericService.backUpHistory("tb_rapPrices");
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		//request.setAttribute("rapFormat", stockUploadDAO.getFileRapFormat(fileId));
		
		return new ModelAndView("issue/stockCertUpload");
	}
	
	public ModelAndView packetCert(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PageList pageList = this.getMemoService().getPacketDetailCert();
		request.setAttribute("pageData",pageList);
		return new ModelAndView("memo/packetCert");
	}
	
	public ModelAndView packetCertAllLab(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PageList pageList = this.getMemoService().getPacketDetailCert(1);
		request.setAttribute("pageData",pageList);
		return new ModelAndView("memo/packetCert");
	}
	
	public ModelAndView packetCertSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String [] pktIds = request.getParameterValues("select");
		List<String>  orderIDs = new ArrayList<String>() ;
		for (int i = 0; i < pktIds.length; i++) {
			String orderId =  request.getParameter("memoId_"+pktIds[i]);
			if(!orderIDs.contains(orderId)) {
				orderIDs.add(orderId);
			}
		}		
		Integer userId = RequestUtils.getUserSession(request).getUserId(); 
		Integer status = 0;
		Integer success = 0;
		if((pktIds.length > 0) && (orderIDs.size() > 0))
			success = this.getMemoService().memoReturnByPktNos(pktIds, orderIDs.toArray(new String[orderIDs.size()]), userId, status);
		//request.getAttribute("pageData",pageList);
		//return null;
		return new ModelAndView(new RedirectView("pendingStock.html"));
	}


	public ModelAndView dashboard(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer iPartyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		String sDate = sdf.format(new Date());
		List<Dashboard> lst = new ArrayList<Dashboard>();		
		lst = this.getMemoService().getDashboardQueries();
		Dashboard oDs = new Dashboard();
		String type = "";
		String sql = "";
		Integer params = 0;
		Map<String, String> CompMap = new HashMap<String, String>();
		CompMap.put("$PartyAccId$", iPartyAccId.toString());
		CompMap.put("$DueDate$", sDate);
		Set<String> keys = new HashSet<String>();	
		keys = CompMap.keySet();
		List<Object> Param = new ArrayList<Object>();
		for(int i=0; i<lst.size(); i++) {
			oDs = lst.get(i);
			type = oDs.getType();
			params = oDs.getParams();
			sql = oDs.getQuery();
			Iterator<String> itr = keys.iterator();
			if(params > 0) {
				for(int j =0; j < keys.size(); j++) {					
					if(itr.hasNext()) {
						String key = itr.next();
						sql = RequestUtils.replace(sql, key , CompMap.get(key));									
					}											 
				}
			} 
			if(type.equals("Integer")) {				
				Param.add( this.getMemoService().getdashboardDataInt(sql));
				//request.setAttribute("PARAM_" + i, Param);
			} 
			else if(type.equals("QueryCode")) {			
				List<QueryDescription> qs = new ArrayList<QueryDescription>();
				qs = this.getMemoService().getdashboardDataQueryCode(sql);
				Param.add(qs);
				//request.setAttribute("PARAM_" + i, Param);
			}
			//if(!Param.isEmpty()) Param.clear(); 
		}		
		request.setAttribute("PARAM", Param);
		request.setAttribute("DASHDATA", lst);
		return new ModelAndView("dashboard/dashboard");	
	}
	
	public ModelAndView getExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		 int fileId = RequestUtils.getParam(request, "fileId", -1);
		 try {
			 
			 List<FileMap> fileMapList = this.getGenericService().getFileMapping(null,fileId,-1);//kri change to null
				//List<FileMap> fileMapList = this.getGenericService().getExportFileMapping(fileId);
				response.setContentType("application/vnd.ms-excel");
				response.setHeader
				     ("Content-Disposition", "attachment; filename="+commonService.getPropertiesByName("b2b.download.filename")+".xls");
				 WritableWorkbook w;
				 w = Workbook.createWorkbook(response.getOutputStream()); 
				 WritableSheet s = w.createSheet("Stock List", 0);
				
				 CommonUtil.fillBlankData(s, null, fileMapList, 0);
				
				 w.write();
				 w.close();
		  } 
		  catch (Exception e){
			  throw new ServletException("Exception in Excel Sample Servlet", e);
		  } 
		 
		   return null;
	}

	
}
	
