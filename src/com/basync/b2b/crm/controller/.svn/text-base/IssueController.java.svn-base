 package com.basync.b2b.crm.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.xalan.lib.Redirect;
import org.jgroups.protocols.TOTAL;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.controller.AbstractController;
import com.basync.b2b.controller.IExceptionHandler;
import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.ParcelMaster;
import com.basync.b2b.crm.data.PurchaseDetails;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.b2b.crm.wrapper.IssueWraperService;
import com.basync.b2b.dao.FileUploadListener;
import com.basync.b2b.dao.StockUploadDAOImpl;
import com.basync.b2b.data.FileDetails;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.PriceMasterPrpDO;
import com.basync.b2b.dataobjects.StockMasterDO;
import com.basync.b2b.dataobjects.StockPRPDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.service.IPrpService;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;
import com.ctc.wstx.util.StringUtil;

public class IssueController extends AbstractController implements
		IExceptionHandler {

	protected ICommonService commonService;

	protected IStockService stockService;

	private MailSenderPooled mailSender;

	private IGenericService genericService;

	private IPartyService partyService;
	
	private IPriceService priceService;

	private StockUploadDAOImpl stockUploadDAO;
	
	private IMemoService memoService;
	
	private IPrpService prpService;
	

	public ICommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}

	public IStockService getStockService() {
		return stockService;
	}

	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}

	public MailSenderPooled getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSenderPooled mailSender) {
		this.mailSender = mailSender;
	}

	
	/**
	 * @return the stockUploadDAO
	 */
	public StockUploadDAOImpl getStockUploadDAO() {
		return stockUploadDAO;
	}

	/**
	 * @param stockUploadDAO
	 *            the stockUploadDAO to set
	 */
	public void setStockUploadDAO(StockUploadDAOImpl stockUploadDAO) {
		this.stockUploadDAO = stockUploadDAO;
	}

	/**
	 * @return the genericService
	 */
	public IGenericService getGenericService() {
		return genericService;
	}

	/**
	 * @param genericService
	 *            the genericService to set
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
	 * @param partyService
	 *            the partyService to set
	 */
	public void setPartyService(IPartyService partyService) {
		this.partyService = partyService;
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

	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {

		return new ModelAndView(new RedirectView("logout.html"));
	}

	public IPrpService getPrpService() {
		return prpService;
	}

	public void setPrpService(IPrpService prpService) {
		this.prpService = prpService;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockEntry(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		int fileId = RequestUtils.getParam(request, "fileId", -1);
		List<FileMap> fileMappingList = this.getGenericService()
			.getFileMapping(null, fileId, 0);
		request.setAttribute("fileMappingList", fileMappingList);
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		request.setAttribute("rapFormat", stockUploadDAO.getFileRapFormat(fileId));
		Integer pktId  = RequestUtils.getParam(request, "pktId", -1);
		if(pktId > 0){
			Integer grpId  = RequestUtils.getParam(request, "grpId", 1);
			
			List<String> doPropertyList = this.stockUploadDAO.getColumns(fileId);
			Map<String, Object> pktDetails = this.getStockService()
					.findStockByPktId(pktId, grpId,  doPropertyList);
			request.setAttribute("pktDetails",pktDetails);
			request.setAttribute("editPacket",1);
		}else{
			//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
			request.setAttribute("PARTY_LIST",
					this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));
			request.setAttribute("fileList", this.getGenericService()
					.getExportFiles("IN"));
			request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
			request.setAttribute("CURR_DATE", sdf.format(new Date()));
			// genericService.backUpHistory("tb_rapPrices");
			request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		}
		request.setAttribute("PAGE_VALUE", RequestUtils.getParam(request, "pageValue", ""));
		return new ModelAndView("issue/stockEntry");
	}	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockSearchAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserSession us = RequestUtils.getUserSession(request);
		int fileId = RequestUtils.getParam(request, "fileId", -1); 
		List<String> doPropertyList = this.stockUploadDAO.getColumns(fileId);
		
		Integer pktId = RequestUtils.getParam(request, "pktId", -1);
		Map<String, Object> pktDetails ; 
		if(pktId > 0){
			Integer grpId  = RequestUtils.getParam(request, "grpId", 1);
			 pktDetails = this.getStockService()
			 .findStockByPktId(pktId, grpId,  doPropertyList);
			
		}else{
			
			String pktCode = RequestUtils.getParam(request, "pktCode", "");
			pktDetails = this.getStockService()
			.findStockByPktCode(pktCode, doPropertyList);
		}
		String json = "";
		if(pktDetails!=null && pktDetails.size()>0){
			if(Integer.parseInt(pktDetails.get("status").toString()) != 0 && Integer.parseInt(pktDetails.get("status").toString()) != 1 && Integer.parseInt(pktDetails.get("status").toString()) != 7){
				json = JSONUtil.convertToJSON("Stone is sold or in process");
			}
			else if(Integer.parseInt(pktDetails.get("partyAccId").toString()) != us.getPartyAccId()){
				json = JSONUtil.convertToJSON("Stone is not at your location");
			}else{
				json = JSONUtil.convertToJSON(pktDetails);
			}
		}
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
	public ModelAndView stockRapPriceAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String c = RequestUtils.getParam(request, "c", "");
		String pu = RequestUtils.getParam(request, "pu", "");
		Double cts = Double.parseDouble(RequestUtils.getParam(request, "cts", "0"));
		String sh = RequestUtils.getParam(request, "sh", "");
		
		HashMap<String, String> hMap = stockUploadDAO.getValPrpMap();
		c = hMap.get("C_"+c);
		int comparsionResult = c.compareTo("M");
		if (comparsionResult > 0){
			c = "M";
		}
		pu = RequestUtils.getParam(request, "pu", "");
		pu = hMap.get("PU_"+pu);
		
		if (pu == "FL"){
			pu = "IF";
		}
		
		if(sh.equals("10"))
			sh = "ROUND";
		else
			sh = "PEAR";
		
		Double rapPrice = this.getPriceService().getLocalRap(sh, cts, c, pu);

		String json = JSONUtil.convertToJSON(rapPrice);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}

	public ModelAndView clearStock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Integer status = RequestUtils.getParam(request, "status", 1);
		Integer partyAccId = RequestUtils.getUserSession(request)
				.getPartyAccId();

		Integer i = this.getStockService().clearStock(status, partyAccId);

		return new ModelAndView(new RedirectView("stockEntry.html"));
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deletePkt(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		Integer i = this.getStockService().deletePkt(pktCode);
		String msg = "Pkt Deleted Successfully";
		if(i != 1){
			msg = "error in execution please try again";
		}
		String json = JSONUtil.convertToJSON(msg);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	public ModelAndView labForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_LAB, Constants.MODULE_VALID_FLAG);
		request.setAttribute("prpList", prpList);
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());

		return new ModelAndView("issue/othLab");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView labLoad(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<String> doPropertyList = new ArrayList<String>();
		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_LAB, Constants.MODULE_VALID_FLAG);
		
		for (int i = 0; i < prpList.size(); i++) {
			doPropertyList.add(prpList.get(i).getPrp());
		}
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		List<Map<String, Object>> pktDetails = this.getStockService()
				.findStockByPktCodeList(pktCode, doPropertyList);
		
		request.setAttribute("pktCode", pktCode);
		request.setAttribute("pktDetails", pktDetails);
		request.setAttribute("prpList", prpList);
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());

		return new ModelAndView("issue/othLab");
	}
	
	public ModelAndView labSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		int grpId = RequestUtils.getParam(request, "grpId", 9999);
		int defaultLab = RequestUtils.getParam(request, "defaultLab"+grpId, -1);
		
		List<String> doPropertyList = new ArrayList<String>();
		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_LAB, Constants.MODULE_VALID_FLAG);
		
		
		Map<String, Object> pktDetailsMap = new HashMap<String,Object>();
		HashMap<String, String> hMap = stockUploadDAO.getValPrpMap();
		String grp = "_"+grpId;
		if(grpId == 9999)
		{
			grp="";
		}
		for (int i = 0; i < prpList.size(); i++) {
			String prp = prpList.get(i).getPrp();
			doPropertyList.add(prp);
			String sort = RequestUtils.getParam(request, prp+grp, "");
			pktDetailsMap.put(prp+"_so", sort);
			if(hMap.containsKey(prp+"_"+sort)){
				pktDetailsMap.put(prp, hMap.get(prp+"_"+sort));
			}else{
				pktDetailsMap.put(prp, sort);
			}
			if(!prp.equalsIgnoreCase("CTS"))
				doPropertyList.add(prp+"_so");
		}
		
		if(grpId == 9999){
			stockUploadDAO.insertLab(doPropertyList,pktDetailsMap, pktCode, defaultLab);
		}else{
			stockUploadDAO.editLab(doPropertyList, pktDetailsMap, pktCode, grpId, defaultLab);
		}
				
		return new ModelAndView(new RedirectView("labLoad.html?pktCode="+pktCode));
	}
	
	public ModelAndView labLoadRep(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<String> doPropertyList = new ArrayList<String>();
		List<PrpData> prpList = this.getCommonService().getModulePrpList(Constants.MODULE_LAB, Constants.MODULE_VALID_FLAG);
		
		for (int i = 0; i < prpList.size(); i++) {
			doPropertyList.add(prpList.get(i).getPrp());
		}
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		List<Map<String, Object>> pktDetails = this.getStockService()
				.findStockByPktCodeList(pktCode, doPropertyList);
		
		request.setAttribute("pktCode", pktCode);
		request.setAttribute("pktDetails", pktDetails);
		request.setAttribute("prpList", prpList);

		return new ModelAndView("issue/othLabRep");
	}
	
	public ModelAndView pendingStock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		int grpId = RequestUtils.getParam(request, "grpId", -1);
		int status = RequestUtils.getParam(request, "status", 0);
		int priceNull = RequestUtils.getParam(request, "priceNull", 0);
		
		String[] sh = request.getParameterValues("sh"); 
		String[] c = request.getParameterValues("c"); 
		String[] pu = request.getParameterValues("pu"); 
		Double ctsFr = Double.parseDouble(RequestUtils.getParam(request, "cts_from", "0"));
		Double ctsTo = Double.parseDouble(RequestUtils.getParam(request, "cts_to", "0"));
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		//String whereClause = " and sp.SH_so is not null and sp.c_so is not null and sp.pu_so is not null and sp.cts is not null ";
		
		String whereClause = " and sp.cts is not null and sp.cts > 0 ";
		
		if(sh!=null && sh.length>0){
			whereClause += " and sp.SH in('"+StringUtils.toString(sh, "','")+"') ";
		}
		if(c!=null && c.length>0){
			whereClause += " and sp.C in('"+StringUtils.toString(c, "','")+"') ";
		}
		if(pu!=null && pu.length>0){
			whereClause += " and sp.PU in('"+StringUtils.toString(pu, "','")+"') ";
		}
		if(ctsFr>0 && ctsTo>0){
			whereClause += " and sp.CTS >= "+ ctsFr +" and sp.CTS <= "+ctsTo+" ";
		}
		if(!StringUtils.isEmpty(pktCode)){
			whereClause += " and s.pktCode like '%"+ pktCode +"%' ";
		}
		
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		request.setAttribute("pendingStock", this.getStockService().getPendingStock(status, grpId, priceNull, partyAccId, whereClause));
		request.setAttribute("from", "pending");
		return new ModelAndView("issue/pendingStock");
	}
	
	public ModelAndView invalidStock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		int grpId = RequestUtils.getParam(request, "grpId", -1);
		int status = RequestUtils.getParam(request, "status", -1);
		int priceNull = RequestUtils.getParam(request, "priceNull", 0);
		
		String[] sh = request.getParameterValues("sh"); 
		String[] c = request.getParameterValues("c"); 
		String[] pu = request.getParameterValues("pu"); 
		Double ctsFr = Double.parseDouble(RequestUtils.getParam(request, "cts_from", "0"));
		Double ctsTo = Double.parseDouble(RequestUtils.getParam(request, "cts_to", "0"));
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		
		String whereClause = "";
		
		if(sh!=null && sh.length>0){
			whereClause += " and sp.SH in('"+StringUtils.toString(sh, "','")+"') ";
		}
		if(c!=null && c.length>0){
			whereClause += " and sp.C in('"+StringUtils.toString(c, "','")+"') ";
		}
		if(pu!=null && pu.length>0){
			whereClause += " and sp.PU in('"+StringUtils.toString(pu, "','")+"') ";
		}
		if(ctsFr>0 && ctsTo>0){
			whereClause += " and sp.CTS >= "+ ctsFr +" and sp.CTS <= "+ctsTo+" ";
		}
		if(!StringUtils.isEmpty(pktCode)){
			whereClause += " and s.pktCode like '%"+ pktCode +"%' ";
		}
		
		whereClause+= " and (sp.SH_so is null or sp.pu_so is null or sp.cts is null or sp.cts = 0 or s.rate is null or s.rate = 0 ) ";
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		request.setAttribute("from", "invalid");
		request.setAttribute("pendingStock", this.getStockService().getPendingStock(status, grpId, priceNull, partyAccId, whereClause));

		return new ModelAndView("issue/pendingStock");
	}
	
	public ModelAndView available(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		int grpId = RequestUtils.getParam(request, "grpId", -1);
		int status = RequestUtils.getParam(request, "status", 1);
		int priceNull = RequestUtils.getParam(request, "priceNull", 0);
		
		String[] sh = request.getParameterValues("sh"); 
		String[] c = request.getParameterValues("c"); 
		String[] pu = request.getParameterValues("pu"); 
		Double ctsFr = Double.parseDouble(RequestUtils.getParam(request, "cts_from", "0"));
		Double ctsTo = Double.parseDouble(RequestUtils.getParam(request, "cts_to", "0"));
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		
		String whereClause = "";
		
		if(sh!=null && sh.length>0){
			whereClause += " and sp.SH in('"+StringUtils.toString(sh, "','")+"') ";
		}
		if(c!=null && c.length>0){
			whereClause += " and sp.C in('"+StringUtils.toString(c, "','")+"') ";
		}
		if(pu!=null && pu.length>0){
			whereClause += " and sp.PU in('"+StringUtils.toString(pu, "','")+"') ";
		}
		if(ctsFr>0 && ctsTo>0){
			whereClause += " and sp.CTS >= "+ ctsFr +" and sp.CTS <= "+ctsTo+" ";
		}
		if(!StringUtils.isEmpty(pktCode)){
			whereClause += " and s.pktCode like '%"+ pktCode +"%' ";
		}

		whereClause +=" and sp.SH_so is not null and ((sp.c_so is null and sp.c is null) or (sp.c_so is not null and sp.c is not null)) and sp.pu_so is not null and sp.cts is not null and s.rate is not null and s.rate > 0 "; 
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		request.setAttribute("pendingStock", this.getStockService().getPendingStock(status, grpId, priceNull, partyAccId, whereClause));
		request.setAttribute("from", "available");
		return new ModelAndView("issue/pendingStock");
	}
	//onprocess
	public ModelAndView onProcess(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		int grpId = RequestUtils.getParam(request, "grpId", -1);
		int status = RequestUtils.getParam(request, "status", 7);
		int priceNull = RequestUtils.getParam(request, "priceNull", 0);
		
		String[] sh = request.getParameterValues("sh"); 
		String[] c = request.getParameterValues("c"); 
		String[] pu = request.getParameterValues("pu"); 
		Double ctsFr = Double.parseDouble(RequestUtils.getParam(request, "cts_from", "0"));
		Double ctsTo = Double.parseDouble(RequestUtils.getParam(request, "cts_to", "0"));
		
		String whereClause = "";
		
		if(sh!=null && sh.length>0){
			whereClause += " and sp.SH in('"+StringUtils.toString(sh, "','")+"') ";
		}
		if(c!=null && c.length>0){
			whereClause += " and sp.C in('"+StringUtils.toString(c, "','")+"') ";
		}
		if(pu!=null && pu.length>0){
			whereClause += " and sp.PU in('"+StringUtils.toString(pu, "','")+"') ";
		}
		if(ctsFr>0 && ctsTo>0){
			whereClause += " and sp.CTS >= "+ ctsFr +" and sp.CTS <= "+ctsTo+" ";
		}
		
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		request.setAttribute("pendingStock", this.getStockService().getPendingStock(status, grpId, priceNull, partyAccId, whereClause));
		request.setAttribute("from", "onProcess");
		return new ModelAndView("issue/pendingStock");
	}
	
	public ModelAndView similarStk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		double range = 0.10d;
		String lab = RequestUtils.getParam(request, "lab", "");
		request.setAttribute("stock", this.getStockService().getSimilarStock(-1, pktCode, range, partyAccId , lab));
		return new ModelAndView("issue/similar");
	}
	public ModelAndView similarPriceStk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		String lab = RequestUtils.getParam(request, "lab", "");
		request.setAttribute("stock", this.getStockService().getSimilarPriceStock(-1, pktCode, partyAccId, lab));
		return new ModelAndView("issue/similar");
	}
	
	public ModelAndView soldStk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		int grpId = RequestUtils.getParam(request, "grpId", -1);
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		String lab = RequestUtils.getParam(request, "lab", "");
		int priceNull = RequestUtils.getParam(request, "priceNull", 0);
		int days =60;
		request.setAttribute("stock", this.getStockService().getSoldPkt(-1, pktCode, partyAccId, days, lab));

		return new ModelAndView("issue/sold");
	}


	public ModelAndView transfer(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String[] pktCodes = request.getParameterValues("select");
		
		List<PacketDetails> pList = new ArrayList<PacketDetails>();
		if(pktCodes!=null){
			PacketDetails pd = new PacketDetails();
			for (int i = 0; i < pktCodes.length; i++) {
				String[] grpIds = request.getParameterValues("grpId_"+pktCodes[i]);
				for (int j = 0; j < grpIds.length; j++) {
					String grpId = grpIds[j];
					pd = new PacketDetails();
					pd.setPktCode(pktCodes[i]);	
					String selectedLab = RequestUtils.getParam(request, "default_"+pktCodes[i], ""); 
					String lab_so = RequestUtils.getParam(request, "lab_so_"+pktCodes[i]+"_"+grpId, "");
					logger.debug("000000000000000000000000000"+lab_so);
					if(!StringUtils.isEmpty(lab_so) && NumberUtils.isNumber(lab_so))
						pd.setLab_so(Double.parseDouble(lab_so));
					else{
						pd.setLab_so(null);
					}
					if(!StringUtils.isEmpty(selectedLab) && selectedLab.equals(grpId)){
						pd.setGrpId(1);
						logger.debug("111111111111111111111111111111111"+pd.getGrpId());
					}
					else{
						pd.setGrpId(pd.getLab_so()!=null?pd.getLab_so().intValue():null);
						logger.debug("2222222222222222222222222222"+pd.getGrpId());
					}
					
					logger.debug("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZz"+pd.getGrpId());
					pd.setLab(RequestUtils.getParam(request, "lab_"+pktCodes[i]+"_"+grpId, ""));
					pd.setRap(Double.parseDouble(RequestUtils.getParam(request, "rap_"+pktCodes[i]+"_"+grpId, "0")));
					pd.setRate(Double.parseDouble(RequestUtils.getParam(request, "rate_"+pktCodes[i]+"_"+grpId, "0")));
					pList.add(pd);
				}
			}
			HttpSession session = request.getSession(true);
			UserSession us = RequestUtils.getUserSession(request);
			List<QueryCodeDescription> qdList = this.getStockService().transferStock(pList,us);
			
			List<FileMap> fileMapList = this.getGenericService()
				.getFileMapping(Constants.RAPNET, -1, -1);//kri_change to null
			if(RequestUtils.getParam(request, "rapUpload", -1) == 1){
				for (int i = 0; i < pList.size(); i++) {
					pd = new PacketDetails();
					pd = pList.get(i);
					if(pd.getGrpId() == 1){
						QueryCodeDescription qcd = new QueryCodeDescription(pd.getPktCode(),"");
						if(qdList.contains(qcd)){
							int z = this.getStockService().uploadRap(pd.getPktCode(), fileMapList, pd.getLab());
							if(z == 0){
								QueryCodeDescription qd = new QueryCodeDescription();
								qd.setId(pd.getPktCode());
								qd.setDescription("Rapnet could not accept this pakcet using webservices please upload it mannualy.");
							}
						}
					}
				}
			}
			session.setAttribute("eMsg", qdList);
			/*return new ModelAndView(new  RedirectView("pendingStock.html"));
			request.getRequestDispatcher("packetUpload.html").forward(request, response);
	        return null;*/
		}
		/*return new ModelAndView(new  RedirectView("pendingStock.html"));*/
		request.getRequestDispatcher("pendingStock.html").forward(request, response);
        return null;
	}
	public ModelAndView emailToByrAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		PacketDetails	pd = new PacketDetails();
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		pd.setPktCode(pktCode);	
		pd.setLab(RequestUtils.getParam(request, "lab", ""));
		pd.setRate(Double.parseDouble(RequestUtils.getParam(request, "rate", "0")));
		pd.setRap(Double.parseDouble(RequestUtils.getParam(request, "rap_", "0")));
		pd.setGrpId(1);
		
		String msg = "";
		if(this.getStockService().validatePkt(pd)){
			List<String> emailList = this.getStockService().findpartyEmails(pd, partyAccId, 60);
			List<FileMap> fileMapList = this.getGenericService()
				.getFileMapping(null, -1, 0);//kri chnge to null
			Map<String,Object> stkDtl=this.getStockService().findStockForMailPktCode(pktCode, fileMapList, pd.getLab());
			
			String mailContent = getCollectionTomail(stkDtl, fileMapList);
			for (int i = 0; i < emailList.size(); i++) {
				if(!StringUtils.isEmpty(emailList.get(i))){
				this.mailSender.general_send_mail(emailList.get(i), mailContent, Constants.CLIENT_SIMILAR_MAIL_SUB);
				}
			}
			msg = "Mail send to buyers having similar request";
		}else{
			msg = "Invalid Packet details please correct it details. ";
		}
		String json = JSONUtil.convertToJSON(msg);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	
	
	public ModelAndView rapUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		PacketDetails	pd = new PacketDetails();
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		pd.setPktCode(pktCode);	
		pd.setLab(RequestUtils.getParam(request, "lab_"+pktCode, ""));
		pd.setRate(Double.parseDouble(RequestUtils.getParam(request, "rate_"+pktCode, "0")));
		pd.setRap(Double.parseDouble(RequestUtils.getParam(request, "rap_"+pktCode, "0")));
		pd.setGrpId(1);
		
		String msg = "";
		if(this.getStockService().validatePkt(pd)){
			List<String> emailList = this.getStockService().findpartyEmails(pd, partyAccId, 60);
			List<FileMap> fileMapList = this.getGenericService()
				.getFileMapping(null, -1, 0);//kri change to null
			Map<String,Object> stkDtl=this.getStockService().findStockForMailPktCode(pktCode, fileMapList, pd.getLab());
			
		}else{
			msg = "Invalid Packet details please correct it. ";
		}
		String json = JSONUtil.convertToJSON(msg);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	
	public String getCollectionTomail(Map<String,Object> stkDtl,List<FileMap> fileMapList) throws Exception{
		
		StringBuilder html =new StringBuilder();
		html.append("<div>"+Constants.CLIENT_SIMILAR_MAIL+"</div><table><tr bgcolor='grey'>");
		for (int i = 0; i < fileMapList.size(); i++) {
			String string= (fileMapList.get(i).getExcelColumnName()!=null?fileMapList.get(i).getExcelColumnName():fileMapList.get(i).getColumnName()); 
			html.append("<th>"+string+"</th>");
		}
		html.append("</tr><tr>");
		for (int i = 0; i < fileMapList.size(); i++) {
			String string= fileMapList.get(i).getColumnName();
			html.append("<td>"+(stkDtl.get(string)!=null?stkDtl.get(string):"")+"</td>");
		}
		CommonUtil.getInstance();
		html.append("</tr></table><br/>Regards,<br/> "+CommonUtil.getPropertiesByName("b2b.download.company"));
		return html.toString();
	}
		
	//stockEntryMix.....kri 28-11(for small diamond)
	
	public ModelAndView stockEntryMix(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		int fileId = RequestUtils.getParam(request, "fileId", -1);
		List<FileMap> fileMappingList = this.getGenericService()
			.getFileMapping(null, fileId, 0);
		request.setAttribute("fileMappingList", fileMappingList);
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		request.setAttribute("rapFormat", stockUploadDAO.getFileRapFormat(fileId));
		Integer pktId  = RequestUtils.getParam(request, "pktId", -1);
		
		if(pktId > 0){
			Integer grpId  = RequestUtils.getParam(request, "grpId", 1);
			List<String> doPropertyList = this.stockUploadDAO.getColumns(fileId);
			Map<String, Object> pktDetails = this.getStockService()
					.findStockByPktId(pktId, grpId,  doPropertyList);
			request.setAttribute("pktDetails",pktDetails);
			request.setAttribute("editPacket",1);
		}else{
			//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
			request.setAttribute("PARTY_LIST",
					this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));
			request.setAttribute("fileList", this.getGenericService()
					.getExportFiles("IN"));
			request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
			request.setAttribute("CURR_DATE", sdf.format(new Date()));
			// genericService.backUpHistory("tb_rapPrices");
			request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		}
		
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());	
		request.setAttribute("VENDOR_LIST", this.getCommonService().
													getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		
		request.setAttribute("parcelList", this.getStockService().getParcelMasterQD());	
		request.setAttribute("fileList", this.getGenericService().getExportFiles("IN", "mixed"));
		if(session.getAttribute("mailMsg")!=null){
			request.setAttribute("mailMsg", session.getAttribute("mailMsg"));
			session.removeAttribute("mailMsg");
		}
		
		request.setAttribute("CURR_DATE", sdf.format(new Date()));		
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		request.setAttribute("selectedDiv", request.getAttribute("selectedDiv"));
		request.setAttribute("notSelectedDiv1", request.getAttribute("notSelectedDiv1"));
		request.setAttribute("notSelectedDiv2", request.getAttribute("notSelectedDiv2"));
		return new ModelAndView("issue/stockEntryMix");
	}
	public ModelAndView stockEntryMix2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());	
		request.setAttribute("VENDOR_LIST", this.getCommonService().
													getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
			
		
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		
		return new ModelAndView("issue/stockEntry2");
	}
	
	public ModelAndView parcelDetailAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = RequestUtils.getParam(request, "id", "");
		
		ParcelMaster pm =  this.getStockService().getParcelMaster(id);
		
		String json = JSONUtil.convertToJSON(pm);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();

		return null;
	}
	//todo_krishna_8 and _9
	
	//todo_krishna_8 and _9
	public ModelAndView stockEntryMixBoxSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserSession userSession = RequestUtils.getUserSession(request);
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//request.setAttribute("CURR_DATE", sdf.format(new Date()));
		//For Purchase TODO
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		String billNo =  RequestUtils.getParam(request, "billNo", ""); 
		String comments =  RequestUtils.getParam(request, "comments", ""); 
		String date =  RequestUtils.getParam(request, "date", ""); 
		request.setAttribute("CURR_DATE",sdf.format(new Date()));
		double tax = RequestUtils.getParam(request, "tax", 0); 

		String dueDate =  RequestUtils.getParam(request, "dueDate", ""); 
		double expenses = RequestUtils.getParam(request, "expenses", 0);
		double exRate = RequestUtils.getParam(request, "exRate", 0);
		String currency =   RequestUtils.getParam(request, "currency", "USD");
		String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());
		if(StringUtils.isEmpty(dueDate))
			dueDate  = sdfView.format(new Date());
		//Integer userId = RequestUtils.getUserSession(request).getUserId();
		//Double tax = Double.parseDouble(RequestUtils.getParam(request, "tax", "0"));
		
		PurchaseMaster pm = new PurchaseMaster();
		pm.setBillNo(billNo);
		pm.setComments(comments);
		pm.setVendorId(vendorId);
		//stcPrpo.setUserId(userId);
		pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
		pm.setTax(tax);
		
		pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
		pm.setExRate(exRate);
		pm.setExpenses(expenses);
		pm.setCurrency(currency);
		pm.setPaymentTerm(paymentTerm);
		

		Map<String, String> hMap = stockUploadDAO.getValPrpMap();
		IssueWraperService iws = new IssueWraperService();
		
		List<Object>  list = iws.stockEntryWraper(request, response, 10, hMap);
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPrpList = new ArrayList<StockPRPDO>(); 
		stockMasterList = (List<StockMasterDO>) list.get(0);
		stockPrpList = (List<StockPRPDO>) list.get(1);
		
		
		List<StockMasterDO> stockPurchaseMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPurchasePrpList = new ArrayList<StockPRPDO>(); 
		String msg="";
		double amt =0;
		for (int i = 0; i < stockMasterList.size(); i++) {
			int z = this.getStockService().insertParcelStock(stockMasterList.get(i), stockPrpList.get(i), userSession,0);
			if(z == -1){
				 this.getStockService().updateParcelStock(stockMasterList.get(i), stockPrpList.get(i), userSession);
				 //msg+="<br/>Duplicate PktCode for "+stockMasterList.get(i).getPktCode();
				
			}else{
				stockPurchaseMasterList.add(stockMasterList.get(i));
				stockPurchasePrpList.add(stockPrpList.get(i));
				amt +=  stockPrpList.get(i).getCTS() * stockMasterList.get(i).getRate();
			}
		}
		pm.setAmount(amt);
		if(stockPurchaseMasterList.size() > 0)
		stockUploadDAO.insertPurchaseEntry(stockPurchaseMasterList,stockPurchasePrpList,pm,userSession);
		
		session.setAttribute("mailMsg", msg);
	return new ModelAndView(new RedirectView("stockEntryMix.html"));
	}
	//stockEntryMixAJAX_kri
	
	public ModelAndView stockEntryMixAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//int fileId = RequestUtils.getParam(request, "fileId", 0); 
	//	List<String> doPropertyList = this.stockUploadDAO.getColumns(fileId);
		
		String pktCode = RequestUtils.getParam(request, "pktCode", "");

		Map<String, Object> pkcodeDetails=this.getStockService().findMixPktCode(pktCode);
		String json = JSONUtil.convertToJSON(pkcodeDetails);
		
		//String json = JSONUtil.convertToJSON(dfdf);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	public ModelAndView parcelDistribute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		request.setAttribute("PARCEL_LIST", this.getStockService().getPurchaseParcel());
		request.setAttribute("PACKET_LIST", this.getStockService().getfixedpkts());
		return new ModelAndView("issue/parcelDistribute");
	}
	//for fixed packets
	public ModelAndView mixedPacketSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer iCurCount = RequestUtils.getParam(request, "curCount", -1); 
		Integer iParcelID = RequestUtils.getParam(request, "curID", -1); 
		Integer iPurchaseId = RequestUtils.getParam(request, "curPurchaseId", -1);
		//Double dRemTotalCts = Double.parseDouble(RequestUtils.getParam(request, "curRemCts", "-1"));
		String sCts = null;
		String sCodes = null;
		double dRemTotalCts = 0d;
		int z = 0;
		for(int i=0; i<=iCurCount; i++) {
			sCts = RequestUtils.getParam(request, "cts_"+(i+1), "");
			sCodes = RequestUtils.getParam(request, "codes_"+(i+1), "");
			if(StringUtils.isEmpty(sCodes) || StringUtils.isEmpty(sCts)) continue;
			else{
				if(!StringUtils.isEmpty(sCts) && NumberUtils.isNumber(sCts)) {
					dRemTotalCts += Double.parseDouble(sCts);
					//Update StockMaster and Stockprp
					//Make entry in parcel history
					
					z = this.getStockService().updateStockMaster(sCodes, sCts);
					if(z > 0) {
						this.getStockService().insertParcelHistory(iParcelID, sCts, sCodes, iPurchaseId);
					}
					}
				}
			}
		this.getStockService().updatePkts(iParcelID, dRemTotalCts);//Update parcelmaster...Set status as 1 if remtotalcts = 0
		request.setAttribute("PARCEL_LIST", this.getStockService().getPurchaseParcel());
		request.setAttribute("PACKET_LIST", this.getStockService().getfixedpkts());
		if(z > 0) request.setAttribute("mailMsg", "The changes have been saved.");
		return new ModelAndView("issue/parcelDistribute");
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView fixedPacketUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserSession userSession = RequestUtils.getUserSession(request);
		Map<String, String> hMap = stockUploadDAO.getValPrpMap();
		IssueWraperService iws = new IssueWraperService();
		
		List<Object>  list = iws.stockEntryWraper(request, response, 10, hMap);
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPrpList = new ArrayList<StockPRPDO>(); 
		stockMasterList = (List<StockMasterDO>) list.get(0);
		stockPrpList = (List<StockPRPDO>) list.get(1);
		
		String msg="";
		for (int i = 0; i < stockMasterList.size(); i++) {
			int z = this.getStockService().insertParcelStock(stockMasterList.get(i), stockPrpList.get(i), userSession,1);
			if(z == -1){
				//this.getStockService().updateParcelStock(stockMasterList.get(i), stockPrpList.get(i), userSession);
				 msg+="Duplicate PktCode for "+stockMasterList.get(i).getPktCode();				
			}
		}
		//if(stockPurchaseMasterList.size() > 0)
		//stockUploadDAO.insertPurchaseEntry(stockPurchaseMasterList,stockPurchasePrpList,pm,userSession);
		
		session.setAttribute("mailMsg", msg);
		request.setAttribute("PARCEL_LIST", this.getStockService().getPurchaseParcel());
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());	
		request.setAttribute("PACKET_LIST", this.getStockService().getfixedpkts());
		return new ModelAndView("issue/fixedPacket");
	}
	//stockSearchBarCodeReader
	public ModelAndView pktBarcod(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		return new ModelAndView("issue/pktBarcod");
	}
	
	//kri
	public ModelAndView pktBarcodePrint(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		request.setAttribute("pktDetails", this.getStockService().getBarCodePktPrint(pktCode));
		
		request.setAttribute("pktList", this.getStockService().getPktPrintLab(pktCode));
		return new ModelAndView("issue/pktBarcodePrint");
	}
	
	public ModelAndView getBarcode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		
		Code39Bean bean = new Code39Bean();
		final int dpi = 300;
		
		//Configure the barcode generator
		bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar 
		                                                 //width exactly one pixel
		bean.setWideFactor(2.5);
		bean.setModuleWidth(0.19);
		bean.setHeight(20);
		bean.setFontSize(0);
		bean.setFontName("Arial");
		//Open output file
		CommonUtil.getInstance();
		String filename= CommonUtil.getPropertiesByName("b2b.download.filepath")+"/"+pktCode +".png";
		File outputFile = new File(filename);
		//OutputStream out = new FileOutputStream(outputFile);
		ByteArrayOutputStream baos =
		    	new ByteArrayOutputStream();
		try {
		    //Set up the canvas provider for monochrome PNG output 
		    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
		    		baos, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	
		    //Generate the barcode
		    bean.generateBarcode(canvas, pktCode);
		    
		    //Signal end of generation
		    canvas.finish();
		    request.setAttribute("BARCODE", filename);
		    
		    response.getOutputStream().write(baos.toByteArray());
		    response.getOutputStream().flush();
		} finally {
			baos.close();
		}
		return null;
	}
	public ModelAndView getBarcodePDF(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		HttpSession session = request.getSession();
		response.setContentType("application/pdf");
        response.setHeader
             ("Content-Disposition", "attachment; filename="+pktCode+".pdf");
        String inputFile = "pktBarcodePrint.html?pktCode="+pktCode;
	ITextRenderer renderer = new ITextRenderer();
	URL uri =           new URL("http://" + request.getServerName() + ":" + request.getServerPort() + 
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
	 * Split stock for creating new packets
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//splitpackets
	
	public ModelAndView stockSplit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());	
		request.setAttribute("VENDOR_LIST", this.getCommonService().
													getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		request.setAttribute("parcelList", this.getStockService().getParcelMasterQD());	
		
		//k
		String pktNo = RequestUtils.getParam(request, "pktId", "");
		request.setAttribute("SPLIT_DATA_LIST",this.getStockService().findMixPktId(pktNo));	
		//logger.debug("%%%%%%%%%%%%%% krishnaaaaaaaaaaaaaaaaaaa "+stcMstr.getPktCode()+","+stcMstr.getPcs()+","+stcMstr.getRate()+","+stcMstr.getRootPkt()+","+stcMstr.getBaseRate());//+","+stcMstr.getTotalCts());

		//k
			return new ModelAndView("issue/stockSplit");
	}
	//split
	public ModelAndView stockEntrySplitMixSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		UserSession userSession = RequestUtils.getUserSession(request);
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//request.setAttribute("CURR_DATE", sdf.format(new Date()));
		
		Integer pktId = RequestUtils.getParam(request, "pktId", -1);
		//this count is comming from url
		String date =  RequestUtils.getParam(request, "date", ""); 
		request.setAttribute("CURR_DATE",sdf.format(new Date()));
		
		
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());

		Map<String, String> hMap = stockUploadDAO.getValPrpMap();
		IssueWraperService iws = new IssueWraperService();
		
		List<Object>  list = iws.stockEntryWraper(request, response, 10, hMap);
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPrpList = new ArrayList<StockPRPDO>(); 
		stockMasterList = (List<StockMasterDO>) list.get(0);
		stockPrpList = (List<StockPRPDO>) list.get(1);
		
		List<StockMasterDO> stockPurchaseMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPurchasePrpList = new ArrayList<StockPRPDO>(); 
		String msg="";
		double amt =0,totCts= 0;
		for (int i = 0; i < stockMasterList.size(); i++) {
			int z = this.getStockService().insertParcelStock(stockMasterList.get(i), stockPrpList.get(i), userSession, 0); //to verify
			
			if(z == -1){
				 //this.getStockService().updateParcelStock(stockMasterList.get(i), stockPrpList.get(i), userSession);
					msg+="<br/>Duplicate PktCode for "+stockMasterList.get(i).getPktCode();
				
			}else{
				stockPurchaseMasterList.add(stockMasterList.get(i));
				stockPurchasePrpList.add(stockPrpList.get(i));
				amt +=  stockPrpList.get(i).getCTS() * stockMasterList.get(i).getRate();
				totCts += stockPrpList.get(i).getCTS();
			}
		}
		
		this.stockService.updateSplitData(pktId, totCts);
		
		logger.debug("Size of param  "+ stockMasterList.size());	
		session.setAttribute("mailMsg", msg);
		request.setAttribute("success", 1);
		return new ModelAndView("issue/stockSplit");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView parcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());	
		String code = RequestUtils.getParam(request, "pId", "");
		if(!StringUtils.isEmpty(code)){
			ParcelMaster p =  this.getStockService().getParcelMaster(code);
			request.setAttribute("pMaster", p);
		}
	//	request.setAttribute("VENDOR_LIST", this.getCommonService().
	//												getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		return new ModelAndView("issue/parcel");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addParcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Integer paramCount = RequestUtils.getParam(request, "count", 0);
		List<ParcelMaster> pmList = new ArrayList<ParcelMaster>();
		HashMap<String, String> hMap =  stockUploadDAO.getValPrpMap();

		ParcelMaster pm ; 
		for(int p=1;p<=paramCount;p++)
		{
			logger.debug("         *******************************"+p);
			pm =  new ParcelMaster();
			pm.setCode(RequestUtils.getParam(request, "code_"+p, ""));
			if(StringUtils.isEmpty(pm.getCode())){
				continue;
			}
			if(!StringUtils.isEmpty(RequestUtils.getParam(request, "pktCode_"+p, "")))
				pm.setCode(RequestUtils.getParam(request, "pktCode_"+p, ""));
			
			pm.setParcelType(RequestUtils.getParam(request, "parcelType_"+p, ""));
			
			if(!StringUtils.isEmpty(RequestUtils.getParam(request, "rate_"+p, "")))
				pm.setRate(Double.parseDouble(RequestUtils.getParam(request, "rate_"+p, "0")));
			if(!StringUtils.isEmpty(RequestUtils.getParam(request, "baseRate_"+p, "")))
				pm.setBaseRate(Double.parseDouble(RequestUtils.getParam(request, "baseRate_"+p, "0")));
			if(!StringUtils.isEmpty(RequestUtils.getParam(request, "costRate_"+p, "")))
				pm.setCostRate(Double.parseDouble(RequestUtils.getParam(request, "costRate_"+p, "0")));
			
			if(!StringUtils.isEmpty(RequestUtils.getParam(request, "cts_"+p, ""))){
				pm.setCts(Double.parseDouble(RequestUtils.getParam(request, "cts_"+p, "0")));
			}
			if(!StringUtils.isEmpty(RequestUtils.getParam(request, "totCts_"+p, "")))
				pm.setTotalCts(Double.parseDouble(RequestUtils.getParam(request, "totCts_"+p, "0")));
			pm.setRootPkt(RequestUtils.getParam(request, "rootPkt_"+p, "0"));
			pm.setShFr_so(Double.parseDouble(RequestUtils.getParam(request, "shFr_"+p, "0")));
			pm.setShTo_so(Double.parseDouble(RequestUtils.getParam(request, "shTo_"+p, "0")));
			pm.setPuFr_so(Double.parseDouble(RequestUtils.getParam(request, "puFr_"+p, "0")));
			pm.setPuTo_so(Double.parseDouble(RequestUtils.getParam(request, "puTo_"+p, "0")));
			pm.setColFr_so(Double.parseDouble(RequestUtils.getParam(request, "cFr_"+p, "0")));
			pm.setColTo_so(Double.parseDouble(RequestUtils.getParam(request, "cTo_"+p, "0")));
			
			pm.setShFr(hMap.containsKey("SH_"+pm.getShFr_so().longValue())==true?hMap.get("SH_"+pm.getShFr_so().longValue()):"");
			pm.setShTo(hMap.containsKey("SH_"+pm.getShTo_so().longValue())==true?hMap.get("SH_"+pm.getShTo_so().longValue()):"");
			pm.setPuFr(hMap.containsKey("PU_"+pm.getPuFr_so().longValue())==true?hMap.get("PU_"+pm.getPuFr_so().longValue()):"");
			pm.setPuTo(hMap.containsKey("PU_"+pm.getPuTo_so().longValue())==true?hMap.get("PU_"+pm.getPuTo_so().longValue()):"");
			pm.setColFr(hMap.containsKey("COL_"+pm.getColFr_so().longValue())==true?hMap.get("COL_"+pm.getColFr_so().longValue()):"");
			pm.setColTo(hMap.containsKey("COL_"+pm.getColFr_so().longValue())==true?hMap.get("COL_"+pm.getColFr_so().longValue()):"");
			pmList.add(pm);
		}
		this.stockService.saveParcelMaster(pmList);
		return new ModelAndView( new RedirectView("parcelList.html"));
	}
	
	public ModelAndView parcelList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String whereClause = "where status <> 0 ";
		List<ParcelMaster> list =  this.getStockService().getParcelMasterList(whereClause);
		request.setAttribute("list", list);
		return new ModelAndView("issue/parcelList");
	}
	
	public ModelAndView mergeMix(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int fixedFlag =  RequestUtils.getParam(request, "fixedFlag", 0);
		List<PacketDetails> list =  this.getStockService().getMixStock(1,fixedFlag);
		request.setAttribute("list", list);
		return new ModelAndView("issue/mergeMix");
	}
	
	
	public ModelAndView mergeMixSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserSession userSession = RequestUtils.getUserSession(request);
		//List<PacketDetails> list =  this.getStockService().getMixStock(1);	
		String[] pktIds =  request.getParameterValues("cBox");
		int toPktId =  RequestUtils.getParam(request, "rBox",-1);
		//if(RequestUtils.getParam(request, "createNew", "0") > 0);
			List<Integer> pktList = new ArrayList<Integer>();
			Double rate = Double.parseDouble(RequestUtils.getParam(request, "rate","0"));
			String pktCode = RequestUtils.getParam(request, "pktCode","");
			for (int i = 0; i < pktIds.length; i++) {
				pktList.add(Integer.parseInt(pktIds[i]));	
				
			}
			if(!pktList.contains(toPktId))
				pktList.add(toPktId);

		this.stockService.mergePackets(pktList, rate, toPktId, pktCode, userSession);
		return new ModelAndView( new RedirectView("mergeMix.html"));
	}
		
	public ModelAndView split(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int fixedFlag =  RequestUtils.getParam(request, "fixedFlag", 0);
		List<PacketDetails> list =  this.getStockService().getMixStock(1, fixedFlag);
		request.setAttribute("list", list);
		return new ModelAndView("issue/split");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockMixList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int fixedFlag =  RequestUtils.getParam(request, "fixedFlag", 0);
		List<PacketDetails> list =  this.getStockService().getMixStock(1,fixedFlag);
		request.setAttribute("list", list);
		return new ModelAndView("issue/stockMixList");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView stockMixEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//request.setAttribute("edit", 2);
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
		request.setAttribute("parcelList", this.getStockService().getParcelMasterQD());	
		List<FileMap> fileMappingList = this.getGenericService().getFileMapping(null, -1, 0, "mixed","IN");
		request.setAttribute("fileMappingList", fileMappingList);
		String pktNo = RequestUtils.getParam(request, "pktId", "");
		request.setAttribute("fileId2", 26);	
		request.setAttribute("SPLIT_DATA_LIST",this.getStockService().findMixPktId(pktNo));	
		request.setAttribute("editPacket",1);
		return new ModelAndView("issue/stockMixEdit");
	}		
	//masterPrice
	public ModelAndView masterPrice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		List<PrpData> prpDataList=this.getPrpService().getModulePrpList("PRICE",1);
        request.setAttribute("prpDataList",prpDataList);		
      //TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST",
				this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));
        request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
	    request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());
	    return new ModelAndView("issue/masterPrice");
	}
	
	public ModelAndView addMasterPrice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		List<PrpData> prpDataList=this.getPrpService().getModulePrpList("PRICE",1);
		PriceMasterPrpDO  pMrpo=new PriceMasterPrpDO();
		List<PriceMasterPrpDO> stkPrcMPrpList = new ArrayList<PriceMasterPrpDO>(); 
		HashMap<String, String> hMap =  stockUploadDAO.getValPrpMap();
		
		//for row -->
		for (int i = 0; i <10; i++) {
			String mstPkId = RequestUtils.getParam(request,"masterStkPktId_"+i,null);
			pMrpo=new PriceMasterPrpDO();
			if(StringUtils.isEmpty(mstPkId)){continue;}
			pMrpo.setPMSTKID(mstPkId);
			//if no masterStockId then skip
			//for colmn
			for (Iterator iterator = prpDataList.iterator(); iterator.hasNext();) {
				PrpData prpData= (PrpData) iterator.next();
				Object prpValue = RequestUtils.getParam(request,prpData.getPrp()+"_"+i,null);
				logger.debug("ididididididididi"+pMrpo.getPMSTKID());
				logger.debug("aaaaaaaaaaaaaaaaa"+prpData.getPrp());
				//set values
					
			    //CommonUtil.setField(f, prpValue, prpData.getPrp()+"_so");
			  if(prpValue != null){
				if(prpData.getPrp().equalsIgnoreCase("RATE") || prpData.getPrp().equalsIgnoreCase("CTS")){
					Field f2 = PriceMasterPrpDO.class.getDeclaredField(prpData.getPrp());
					f2.setAccessible(true);
					f2.set(pMrpo, hMap.get(prpData.getPrp()+"_"+prpValue));
			    } else{
			    	
			    	Object d = Double.parseDouble(prpValue.toString());
			    	//logger.debug("kkkkkkkkkkkkkkkkkkk"+d);
			    	Field f = PriceMasterPrpDO.class.getDeclaredField(prpData.getPrp()+"_so");
			    	
			    	f.setAccessible(true);
			    	f.set(pMrpo, d);	
			    }
			  }
				if(prpValue != null && hMap.containsKey(prpData.getPrp()+"_"+prpValue)){
					Field f2 = PriceMasterPrpDO.class.getDeclaredField(prpData.getPrp());
					f2.setAccessible(true);
					f2.set(pMrpo, hMap.get(prpData.getPrp()+"_"+prpValue));
					//CommonUtil.setField(f2, hMap.get(prpData.getPrp()+"_"+prpValue), prpData.getPrp());
				}
			}
			stkPrcMPrpList.add(pMrpo);     	
			this.stockService.addPriceMasterData(stkPrcMPrpList);
						
		}   					
		String json = JSONUtil.convertToJSON(prpDataList);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	
	public ModelAndView parcelUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("VENDOR_LIST", this.getCommonService().
													getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		request.setAttribute("parcelList", this.getStockService().getParcelMasterQD());	
		request.setAttribute("CURR_DATE", sdf.format(new Date()));		
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		return new ModelAndView("issue/parcelUpload");
	}
	
	public ModelAndView parcelSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserSession userSession = RequestUtils.getUserSession(request);
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//request.setAttribute("CURR_DATE", sdf.format(new Date()));
		//For Purchase TODO
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		String billNo =  RequestUtils.getParam(request, "billNo", ""); 
		String comments =  RequestUtils.getParam(request, "comments", ""); 
		String date =  RequestUtils.getParam(request, "parcelDate", ""); 
		request.setAttribute("CURR_DATE",sdf.format(new Date()));
		double tax = RequestUtils.getParam(request, "tax", 0);
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String dueDate =  RequestUtils.getParam(request, "parcelDueDate", ""); 
		double expenses = RequestUtils.getParam(request, "expenses", 0);
		double exRate = RequestUtils.getParam(request, "exRate", 0);
		String currency =   RequestUtils.getParam(request, "currency", "USD");
		String localCurr = RequestUtils.getUserSession(request).getCurrency();
		String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());
		if(StringUtils.isEmpty(dueDate))
			dueDate  = sdfView.format(new Date());
		//String TotRate = ;
		Double totRate = Double.parseDouble(RequestUtils.getParam(request, "TotRate", "-1"));
		PurchaseMaster pm = new PurchaseMaster();
		pm.setBillNo(billNo);
		pm.setComments(comments);
		pm.setVendorId(vendorId);
		pm.setUserId(userSession.getUserId());
		pm.setPurchaseDate(sdf.format(sdfView.parse(date)));
		pm.setTax(tax);
		pm.setAmount(totRate);
		
		pm.setDueDate(sdf.format(sdfView.parse(dueDate)));
		pm.setExRate(exRate);
		pm.setExpenses(expenses);
		pm.setCurrency(currency);
		pm.setPaymentTerm(paymentTerm);
		
		//insert into parcelmaster
		Double cts = 0d;
		Integer status = 0;
		Integer deleteFlag = 0;
		String sCts = RequestUtils.getParam(request, "cts", "-1");
		String str = RequestUtils.getParam(request, "TotCts", "0");

		double totalCts = Double.parseDouble(str);
		if(!StringUtils.isEmpty(sCts))  cts = Double.parseDouble(sCts);
		String parcelCode = request.getParameter("ParcelNo");
		if(StringUtils.isEmpty(parcelCode))  parcelCode = "";
		ParcelMaster pc = new ParcelMaster();
		pc.setCode(parcelCode);
		pc.setCts(cts);
		pc.setTotalCts(totalCts);
		pc.setStatus(status);
		pc.setDeleteFlag(deleteFlag);
		pc.setPurchaseParcel(1);//PurchaseParcel=1 for Purchased Parcels
		Integer i = this.getStockService().insertParcelMaster(pc);
		
		if(i > 0) {
			PurchaseDetails pd = new PurchaseDetails();
			pd.setPktCode(parcelCode);
			pd.setParcelId(i.toString());
			pd.setWt(totalCts);
			pd.setTotalRate(totRate); //ERROR  to be solved T
			pd.setRate(totRate/pd.getWt());
			pd.setStatus(status);
			String accountCode = genericService.getSysPref(Constants.default_inventory_act);
			this.getStockService().insertPurchaseAndGL(pm, pd, accountCode, partyAccId, localCurr, currency);//Makes entry in tb_purchaseDetails, tb_purchasemaster, tb_gl_trans
		}		
		return new ModelAndView(new RedirectView("parcelDistribute.html"));
	}
	public ModelAndView packetUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		int fileId = RequestUtils.getParam(request, "fileId", -1);
		List<FileMap> fileMappingList = this.getGenericService().getFileMapping(null, fileId, 0, "mixed","IN");
		request.setAttribute("fileMappingList", fileMappingList);
		//request.setAttribute("rapFormat", stockUploadDAO.getFileRapFormat(fileId));
		Integer pktId  = RequestUtils.getParam(request, "pktId", -1);
		
		if(pktId > 0){
			Integer grpId  = RequestUtils.getParam(request, "grpId", 1);
			List<String> doPropertyList = this.stockUploadDAO.getColumns(fileId);
			Map<String, Object> pktDetails = this.getStockService()
					.findStockByPktId(pktId, grpId,  doPropertyList);
			request.setAttribute("pktDetails",pktDetails);
			request.setAttribute("editPacket",1);
		}else{
			//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
			request.setAttribute("PARTY_LIST",
					this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));
			request.setAttribute("fileList", this.getGenericService()
					.getExportFiles("IN","mixed"));
			request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
			request.setAttribute("CURR_DATE", sdf.format(new Date()));
			// genericService.backUpHistory("tb_rapPrices");
			request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		}
		
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());	
		//request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		//request.setAttribute("parcelList", this.getStockService().getParcelMasterQD());	
		//request.setAttribute("fileList", this.getGenericService().getExportFiles("IN", "mixed"));
		if(session.getAttribute("mailMsg")!=null){
			request.setAttribute("mailMsg", session.getAttribute("mailMsg"));
			session.removeAttribute("mailMsg");
		}
		String mode = request.getParameter("mode");
		if(StringUtils.isEmpty(mode)) mode = "manualUpload";
		request.setAttribute("mode", mode);
		request.setAttribute("CURR_DATE", sdf.format(new Date()));		
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		return new ModelAndView("issue/packetUpload");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView packetUploadSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserSession userSession = RequestUtils.getUserSession(request);
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		SimpleDateFormat sdfView =new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//request.setAttribute("CURR_DATE", sdf.format(new Date()));
		//For Purchase TODO
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0); 
		String billNo =  RequestUtils.getParam(request, "billNo", ""); 
		String comments =  RequestUtils.getParam(request, "comments", ""); 
		String date =  RequestUtils.getParam(request, "packetDate", ""); 
		request.setAttribute("CURR_DATE",sdf.format(new Date()));
		double tax = RequestUtils.getParam(request, "tax", 0); 

		String dueDate =  RequestUtils.getParam(request, "packetDueDate", ""); 
		double expenses = RequestUtils.getParam(request, "expenses", 0);
		double exRate = RequestUtils.getParam(request, "exRate", 0);
		String currency =   RequestUtils.getParam(request, "currency", "USD");
		String paymentTerm =   RequestUtils.getParam(request, "paymentTerm", "CHEQUE");
		if(StringUtils.isEmpty(date))
			date  = sdfView.format(new Date());
		if(StringUtils.isEmpty(dueDate))
			dueDate  = sdfView.format(new Date());
		
		PurchaseMaster pm = (PurchaseMaster)session.getAttribute("purchaseMaster");
		String mailMsg = "";
		List<StockPRPDO> stockPRPList = (List<StockPRPDO>)session.getAttribute("stockPRPList");
		List<StockMasterDO> stockMasterList = (List<StockMasterDO>)session.getAttribute("stockMasterList");
		int fileId = (Integer)session.getAttribute("fileId");
		List<StockMasterDO> stockPurchaseMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPurchasePrpList = new ArrayList<StockPRPDO>(); 
	
		StockMasterDO stockMasterDo = null;
		StockPRPDO stockPrpDo = null;
 		//get values for shape, color and clarity
		Double ppc = 0d;
		long iStatus = 1;
		for (int i = 0; i < stockMasterList.size(); i++) {
			stockMasterDo = stockMasterList.get(i);
			stockPrpDo =  stockPRPList.get(i);
			stockMasterDo.setPcs(-1.0);
			Double cts = stockPrpDo.getCTS();
			Double avgCts = stockPrpDo.getAVGCTS();
			ppc = stockPrpDo.getPPC();
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
			
			/*if(errorcnt>0)
				mailMsg+="<br/> File Uploaded Successfully with above error kindly correct and reupload "+countEx +" rows inserted/updated";
			else
				mailMsg+="<br/> File Uploaded Successfully "+countEx +" rows affected "+stockUploadDAO.getUpdateCnt();*/
			
			
			session.removeAttribute("purchaseMaster");
			session.removeAttribute("stockMasterList");
			session.removeAttribute("stockPRPList");
			session.removeAttribute("fileId");
			//request.setAttribute("url", "uploadStock.html");
			
			return new ModelAndView(new RedirectView("packetUpload.html"));
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView fixedPacket(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		int fileId = RequestUtils.getParam(request, "fileId", -1);
//	List<FileMap> fileMappingList = this.getGenericService()
//			.getFileMapping(null, fileId, 0);
//		request.setAttribute("fileMappingList", fileMappingList);
		Integer pktId  = RequestUtils.getParam(request, "pktId", -1);
		
		if(pktId > 0){
			Integer grpId  = RequestUtils.getParam(request, "grpId", 1);
			List<String> doPropertyList = this.stockUploadDAO.getColumns(fileId);
			Map<String, Object> pktDetails = this.getStockService()
					.findStockByPktId(pktId, grpId,  doPropertyList);
			request.setAttribute("pktDetails",pktDetails);
			request.setAttribute("editPacket",1);
		}else{
			//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
//			request.setAttribute("PARTY_LIST",
//					this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));
//			request.setAttribute("fileList", this.getGenericService()
//					.getExportFiles("IN"));
			// genericService.backUpHistory("tb_rapPrices");
		}
		
		request.setAttribute("PRP_LOV", this.getCommonService().getPrpLOV());	
		
		request.setAttribute("parcelList", this.getStockService().getParcelMasterQD());	
//		request.setAttribute("fileList", this.getGenericService().getExportFiles("IN", "mixed"));
		if(session.getAttribute("mailMsg")!=null){
			request.setAttribute("mailMsg", session.getAttribute("mailMsg"));
			session.removeAttribute("mailMsg");
		}
		return new ModelAndView("issue/fixedPacket");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ModelAndView costPrice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO  comments 
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.MONTH, -3);
		Date dt = c.getTime();
		List<PurchaseMaster> list = this.getMemoService().getPurchaseMaster(partyAccId, sdf.format(dt));
		request.setAttribute("list", list);
		request.setAttribute("listSize", list.size());
		request.setAttribute("currency",this.getMemoService().getCurrencyQD(-1));
		return new ModelAndView("issue/costPrice");
	}
	
	/**
	 * Gets Details for a selected purchaseId from tb_purchaseDetails
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView costPriceAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer purchaseId = RequestUtils.getParam(request, "purchaseId", -1);
		if(purchaseId > 0) { List<PurchaseDetails> lst = this.getMemoService().getPurchaseDetails(purchaseId);
			String json = JSONUtil.convertToJSON(lst);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 response.getWriter().flush();
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
	public ModelAndView costPriceSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String selectedPurchaseString = RequestUtils.getParam(request, "purchaseId", ""); //pipe separated purchase string for multi values 
		Integer purchaseId = 0; 
		Integer glTransNo = RequestUtils.getParam(request, "glTransNo", -1);
		String localCurr = RequestUtils.getUserSession(request).getCurrency();
		String currency = RequestUtils.getParam(request, "currency", "");
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String purchaseDate = RequestUtils.getParam(request, "purchaseDate", "");
		purchaseDate = purchaseDate.replaceAll("/", "-");
		String billNo = RequestUtils.getParam(request, "billNo", "");
		Integer frompartyAccId = 0;
		String exRate = request.getParameter("exRate");
		if(!StringUtils.isEmpty(selectedPurchaseString )) {
		//TODO comments
			String[] arr = StringUtils.splitToArray(selectedPurchaseString , "\\|");
			purchaseId = Integer.parseInt(arr[0]);
			frompartyAccId = Integer.parseInt(arr[3]);
		}
		Integer count = RequestUtils.getParam(request, "count", -1);
		List<PurchaseDetails> pdList = new ArrayList<PurchaseDetails>();
		Double purchaseTot = 0d;
		for(int i = 0; i < count; i++) {
			PurchaseDetails pd = new PurchaseDetails();
			Double dTotal = Double.parseDouble(RequestUtils.getParam(request, "total_" + i, "0"));
			purchaseTot += dTotal;
			pd.setRate(RequestUtils.getParam(request, "rate_" + i, 0d));
			pd.setPktCode( RequestUtils.getParam(request, "pktCode_" + i, ""));
			pd.setTotalRate(dTotal);
			pdList.add(pd);
		}
		 String accountCode = genericService.getSysPref(Constants.default_inventory_act);
		
		if(glTransNo == -1) {
			glTransNo = this.getMemoService().addPurchaseTrf(Constants.PARTY_TYPE_VENDOR, frompartyAccId, accountCode,purchaseDate,
					new BigDecimal(purchaseTot), "", new BigDecimal(exRate), partyAccId, billNo, Constants.STATUS_PERFORMED_INSERT, -1, localCurr, currency);
		}
		else {
			this.getMemoService().addPurchaseTrf(Constants.PARTY_TYPE_VENDOR, frompartyAccId, accountCode, purchaseDate, 
					new BigDecimal(purchaseTot), "", new BigDecimal(exRate), partyAccId, billNo, Constants.STATUS_PERFORMED_EDIT, glTransNo, localCurr, currency);
		}
		
		//update purchasemaster
		this.getMemoService().updatePurchaseDetails(pdList, glTransNo, purchaseTot, currency, purchaseId, partyAccId);
		return new ModelAndView(new RedirectView("costPrice.html"));
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAllFileList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Properties prop = new Properties();
		String path="";
		try {
				CommonUtil.getInstance();
				path=CommonUtil.getPropertiesByName("b2b.certUpload.path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		  File folder = new File(path);
		  String Common_URL="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"";
		  File[] listOfFiles = folder.listFiles();
		  List<FileDetails> fileDetails=new ArrayList<FileDetails>();
		  for (int i = 0; i < listOfFiles.length; i++) 
		  {
		   if (listOfFiles[i].isFile()) 
		   {
			   File fileSize=new File(path+listOfFiles[i].getName());
			   double bytes = Double.parseDouble(new DecimalFormat("#.##").format(fileSize.length()));
			   FileDetails file =new FileDetails();
			   file.setFilename(listOfFiles[i].getName());
			   file.setPath(listOfFiles[i].getPath());
			   file.setSize(bytes/1024);
			   file.setCommonUrl(Common_URL);
			   fileDetails.add(file);
		    }
		  }
		  
		 String json = JSONUtil.convertToJSON(fileDetails);
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
	public ModelAndView certUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("issue/certUpload");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getImageThumb(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServletContext sc = getServletContext();
		String destinationDir = "";
		if(sc.getAttribute("LOCAL_PATH")!=null){
			destinationDir = sc.getAttribute("LOCAL_PATH").toString();
			File dfile = new File(destinationDir);
			if(!dfile.exists() || !dfile.isDirectory()){
				request.getSession().setAttribute("locationInvalid", "1");
			}
		}
	    String filename  = destinationDir+"/"+RequestUtils.getParam(request, "path", "");
	    filename = filename.replaceAll("//", "/");
	    logger.debug("                /////////////  "+filename);
	    // Get the MIME type of the image
	    String mimeType = sc.getMimeType(filename);
	    if (mimeType == null) {
	        sc.log("Could not get MIME type of "+filename);
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        return null;
	    }

	    // Set content type
	    response.setContentType(mimeType);

	    // Set content size
	    File file = new File(filename);
	    response.setContentLength((int)file.length());

	    // Open the file and output streams
	    FileInputStream in = new FileInputStream(file);
	    OutputStream out = response.getOutputStream();

	    // Copy the contents of the file to the output stream
	    byte[] buf = new byte[1024];
	    int count = 0;
	    while ((count = in.read(buf)) >= 0) {
	        out.write(buf, 0, count);
	    }
	    in.close();
	    out.close();
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName= RequestUtils.getParam(request, "fileName", "null");
		Properties prop = new Properties();
		String path="";
		try {
				CommonUtil.getInstance();
				path=CommonUtil.getPropertiesByName("b2b.certUpload.path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		File directory = new File(path);
		File files[] = directory.listFiles();
		for(int index = 0; index < files.length; index++){
			if(files[index].getName().equals(fileName)){
				boolean wasDeleted = files[index].delete();
				
			}
		}
		return null;
	}
}

		
		
		
