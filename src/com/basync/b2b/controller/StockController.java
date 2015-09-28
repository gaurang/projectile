package com.basync.b2b.controller;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.PartyAccData;
import com.basync.b2b.data.OrderMasterData;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.SearchPrpData;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.service.IPrpService;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;

public class StockController extends AbstractController implements IExceptionHandler {

	protected IPrpService prpService;
	
	protected ICommonService commonService;
	
	protected IStockService stockService;
	
	private MailSenderPooled mailSender;

	
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
	
	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {

		return new ModelAndView(new RedirectView("logout.html"));
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView stockLoadGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PrpData> prpLst = (List<PrpData>)request.getSession().getAttribute(Constants.PRP_DATA);
		
		String currency =  RequestUtils.getParam(request, "currency", "");
		Double factor =  Double.parseDouble(RequestUtils.getParam(request, "factor", "0"));
		Integer srchPair = RequestUtils.getParam(request, "srchPair", -1);//If search by root pkt nos
		
		logger.debug(currency  +" =currency");
		HttpSession session = request.getSession();
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		Double term = RequestUtils.getUserSession(request).getTerm();
		
		String json = null;
		int p =RequestUtils.getParam(request, "page", 1);
		int pageSize =RequestUtils.getParam(request, "rows", 50);
		String sIdx =RequestUtils.getParam(request, "sidx", "SZ_so");
		String sorD =RequestUtils.getParam(request, "sord", "desc");
		
		String[] pktCodes = request.getParameterValues("selectedPkts");
		if(sIdx.equals("SZ_so")){
			sIdx = " SH_so, C_so ,PU_so, SZ_so desc";
			sorD = "";
		}
		if(srchPair ==1){
			sorD = "SZ_so desc, pairNo ";
			sIdx = "";
		}
		List<String> selectPKts = new Vector<String>(); 
		if(session.getAttribute(Constants.SELECTED_PKTS)!=null){
			selectPKts  = (Vector)request.getSession().getAttribute(Constants.SELECTED_PKTS);
		}
		if(pktCodes!=null)
			selectPKts.addAll(Arrays.asList(pktCodes));
		
		session.setAttribute(Constants.SELECTED_PKTS, selectPKts);
		
		String whereClause = (String)request.getSession().getAttribute(Constants.SEARCH_CLAUSE);
		JQGridContainer container  =  this.stockService.getStockData(term, prpLst,whereClause,userId, p, pageSize, sIdx, sorD, currency, factor);
			
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
	public ModelAndView stockSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PrpData> prpRsltLst = (List<PrpData>)request.getSession().getAttribute(Constants.PRP_DATA);
		List<PrpData> prpLst = prpService.getSearchPrpList();//TODO
		Map<String, List<QueryDescription>> prpLov = (Map<String, List<QueryDescription>>)request.getAttribute(Constants.PRP_LOV);
		//searchType
		String searchType = RequestUtils.getParam(request, "searchType", Constants.SEARCH_REGULAR );
		
		String partyAccIdArr[] = request.getParameterValues("partyAccId") ;
		String partyAccId ="";
		if(partyAccIdArr!=null)
			partyAccId = StringUtils.toString(partyAccIdArr, ", ");
		//Integer partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		Integer srchPair = RequestUtils.getParam(request, "srchPair", -1);//If search by root pkt nos
		
		/*
		HttpSession session = request.getSession();
		List<PartyAccData> selfPartyList = (List<PartyAccData>)session.getAttribute("SELF_LIST");
		if(selfPartyList!=null){
			for (PartyAccData partyAccData : selfPartyList) {
				if(partyAccData.getId()== partyAccId){
					request.setAttribute("LOCATION", partyAccData.getCompanyName()+"/"+partyAccData.getBranchCode());
				}
			}
		}*/
		
		
   		//Integer userId =  request.getSession().getAttribute("USER_SESSION")prpLst.getValue();
		logger.debug("###################"+searchType);
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		
		boolean saveSearch = false;
		String sDesc = RequestUtils.getParam(request, "sDesc", "");
		if(searchType.equals(Constants.SEARCH_AND_SAVE)){
			saveSearch = true;
		}	
		
		String currency =  RequestUtils.getParam(request, "currency", "");
 		Double factor =  Double.parseDouble(RequestUtils.getParam(request, "factor", "0"));
 		
 		if(RequestUtils.isEmpty(currency)){
 		
			List<SearchPrpData> searchPrpList = new ArrayList<SearchPrpData>();
			String pktNos=RequestUtils.getParam(request, "pktNo", "");
			
			if(!StringUtils.isEmpty(partyAccId)){
				SearchPrpData spd = new SearchPrpData();
				spd.setPrpId(0);	
				spd.setPrp("partyAccId");	
				spd.setPrpIn(String.valueOf(partyAccId));
				searchPrpList.add(spd);
			}
			
			
			if(!searchType.equals(Constants.SEARCH_QUICK)){
				if (StringUtils.isEmpty(pktNos)) {
			 		for(int i= 0; i< prpLst.size();i++){
						SearchPrpData searchPrpData = new SearchPrpData();
						PrpData pd = new PrpData();
						pd = prpLst.get(i);
						
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
						
						if(pd.getPrp().equals("SH") || pd.getPrp().equals("SZ")||pd.getPrp().equals("LAB") ){
							String prpInArr[] = request.getParameterValues(pd.getPrp().toLowerCase()) ;
							if(prpInArr !=null && prpInArr.length >0){
								String labStr = StringUtils.toString(prpInArr, ", ");
								if(labStr.contains("9999")){
									frVal = null;
									toVal = null;
								}else{
									searchPrpData.setPrpIn(labStr);
								}
							}
							logger.debug("\n $$$$$$$ ==========> SH and SIZE "+request.getParameterValues("sh") +" " +pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
							
						}
						if(pd.getPrp().equals("FNC")){
							String prpInArr[] = request.getParameterValues(pd.getPrp().toLowerCase()) ;
							if(prpInArr !=null && prpInArr.length >0){
								String labStr = pd.getPrp()+" like '%"+ StringUtils.toString(prpInArr, "%' or "+pd.getPrp()+" like '%") +"'";
								searchPrpData.setPrpIn(labStr);
							}
						}
						if((frVal ==null || toVal ==null || frVal != -1f || toVal != -1f)  || !StringUtils.isEmpty(searchPrpData.getPrpIn())){
							if(pd.getDisplayDataType()==1)
								toVal=frVal;
							searchPrpData.setPrpId(pd.getId());	
							searchPrpData.setPrp(pd.getPrp());	
							searchPrpData.setPrpFromNum(frVal);
							searchPrpData.setPrpToNum(toVal);
							searchPrpList.add(searchPrpData);
						}
						
						logger.debug(" $$$$$$$ ==========> "+pd.getPrp() +"  "+frVal+" "+toVal+"  "+searchPrpData.getPrpIn() );
						//QueryDescription qd = prpLov.get(pd.getPrp()).get(index);
						/*if(pd.getDataType()== Constants.CHARACTER){
						}else{
						}*/		
					}
				}
			}else{
				String[] prpArr={"sh", "sz", "pu", "c", "lab"};
				int[] prpIds = {1, 2, 3, 5, 40};
				for (int i = 0; i < prpArr.length; i++) {
					String prp = prpArr[i];
					
					SearchPrpData searchPrpData = new SearchPrpData();
					
					Float frVal = RequestUtils.getParam(request, prp+"Q", -1f);
					Float toVal = frVal;
					if(prp.equalsIgnoreCase("SH") || prp.equalsIgnoreCase("LAB")){
						String prpInArr[] = request.getParameterValues(prp.toLowerCase()) ;
						if(prpInArr !=null && prpInArr.length >0)
							searchPrpData.setPrpIn(StringUtils.toString(prpInArr, ", "));
						
					}
					if((frVal != -1f || toVal != -1f)  || !StringUtils.isEmpty(searchPrpData.getPrpIn())){
						searchPrpData.setPrpId(prpIds[i]);	
						searchPrpData.setPrp(prp);	
						searchPrpData.setPrpFromNum(frVal);
						searchPrpData.setPrpToNum(toVal);
						searchPrpList.add(searchPrpData);
						logger.debug(Constants.SEARCH_QUICK+"  from Value ===> "+frVal +"     to val"+toVal);
					}
				}
			}	
	 		int searchId = getPrpService().insertSearchPrp(searchPrpList, userId, searchType, saveSearch, sDesc);
			
	 		request.getSession().setAttribute("SEARCH_ID", searchId);
	 		
	 		if(StringUtils.isEmpty(pktNos))		{
	 			String whereClause = this.getPrpService().getSearchCriteria(searchPrpList);
	 			if(srchPair ==1 ){
	 				whereClause += " and s.pairStock is not null ";
	 			}
	 			request.getSession().setAttribute(Constants.SEARCH_CLAUSE, whereClause);
			}
	 		else{
	 			request.getSession().setAttribute(Constants.SEARCH_CLAUSE, " and s.pktCode in ('"+ pktNos.replaceAll(",", "','")+"')");
	 		}
	 		
 		}
 		String [] colDataArr;
 		
 		/*if(RequestUtils.isEmpty(currency))
 			colDataArr = this.getStockService().getJQGridColModel(prpRsltLst, Constants.SEARCH_RESULT);
 		else
 			colDataArr = this.getStockService().getJQGridColModel(prpRsltLst,currency,factor);
 		
		request.setAttribute("headers", colDataArr[0]);
		request.setAttribute("colModel", colDataArr[1]);*/
 		request.setAttribute("prpList", prpRsltLst);
		return new ModelAndView("searchResult");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView addFavorite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		//UserSession userSession = (UserSession)session.getAttribute(Constants.USER_SESSION);
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		List<String> selectPkts = new Vector<String>(); 
		if(request.getSession().getAttribute(Constants.SELECTED_PKTS)!=null){
			selectPkts  = (Vector)request.getSession().getAttribute(Constants.SELECTED_PKTS);
		}
		//String selPkts 
	//	String selection = RequestUtils.getParam(request, "sVal", "");
		String[] array = new String[0];
		if(request.getParameterValues("sVal")!=null){
			array = request.getParameterValues("sVal");
		}	
		logger.debug("#############"+array.length);
		selectPkts.addAll(Arrays.asList(array));
	//	int z[] = this.getStockService().addToFavorite(selectPkts, userSession.getUserId());
		
		int z[] = this.getStockService().addToFavorite(selectPkts, userId);

		String success="", json="";
		if(selectPkts.size()>0 && z.length >0 ){
			success = "ok";
		}else if(selectPkts.size()==0){
			success = "Please Select Pkts";
		}else if(selectPkts.size() > 0 && z.length == 0 ){
			success = "Pkts are already in Favorites";
		}else{
			success ="no";
		}
		String[] data = new String[1];
		data[0]=success;
		json = JSONUtil.convertToJSON(data);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
	//	return new ModelAndView(new RedirectView("myFavorite.html"));
		return null;
	}
	
	public ModelAndView removeFavorite(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			HttpSession session = request.getSession();
			//UserSession userSession = (UserSession)session.getAttribute(Constants.USER_SESSION);
			Integer userId = RequestUtils.getUserSession(request).getUserId();
			
			List<String> selectPkts = new Vector<String>(); 
			if(request.getSession().getAttribute(Constants.SELECTED_PKTS)!=null){
				selectPkts  = (Vector)session.getAttribute(Constants.SELECTED_PKTS);
			}
			//String selPkts 
		//	String selection = RequestUtils.getParam(request, "sVal", "");
			String[] array = new String[0];
			if(request.getParameterValues("sVal")!=null){
				array = request.getParameterValues("sVal");
			}	
			logger.debug("#############"+array.length);
			selectPkts.addAll(Arrays.asList(array));
		//	int z[] = this.getStockService().addToFavorite(selectPkts, userSession.getUserId());
			
			int z[] = this.getStockService().removeFavorite(selectPkts, userId);
	
			String success="", json="";
			if(selectPkts.size()>0 && z.length >0 ){
				success = "ok";
			}else if(selectPkts.size()==0){
				success = "Please Select Pkts";
			}else if(selectPkts.size() > 0 && z.length == 0 ){
				success = "Pkts are already in Favorites";
			}else{
				success ="no";
			}
			String[] data = new String[1];
			data[0]=success;
			json = JSONUtil.convertToJSON(data);
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(json);
			response.getWriter().flush();
		//	return new ModelAndView(new RedirectView("myFavorite.html"));
			return null;
		}
	
	@SuppressWarnings("unchecked")	
	public ModelAndView myFavorite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<PrpData> prpLst= (List<PrpData>) request.getSession().getAttribute("PRP_DATA");

		String [] colDataArr = this.getStockService().getJQGridColModel(prpLst, Constants.MY_FAVIRATES);
		request.setAttribute("headers", colDataArr[0]);
		request.setAttribute("colModel", colDataArr[1]);
		
		request.setAttribute("TableHeader", "My Favorites");
		request.setAttribute("gridLink", "myFavoriteGrid.html?q=2");
		return new ModelAndView("searchResult");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView myFavoriteGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PrpData> prpLst = (List<PrpData>)request.getSession().getAttribute(Constants.PRP_DATA);

		HttpSession session = request.getSession();
		Integer userId = RequestUtils.getUserSession(request).getUserId();   
		Double term = RequestUtils.getUserSession(request).getTerm();
		
		String xml = null;
		int p =RequestUtils.getParam(request, "page", 1);
		int pageSize =RequestUtils.getParam(request, "rows", 10);
		String sIdx =RequestUtils.getParam(request, "sidx", "");
		String sorD =RequestUtils.getParam(request, "sord", "");
		int pages = 1; 
		
		String[] pktCodes = request.getParameterValues("selectedPkts");
		
		List<String> selectPKts = new Vector<String>(); 
		if(session.getAttribute(Constants.SELECTED_PKTS)!=null){
			selectPKts  = (Vector)request.getSession().getAttribute(Constants.SELECTED_PKTS);
		}
		//selectPKts.addAll(Arrays.asList(pktCodes));
		session.setAttribute(Constants.SELECTED_PKTS, selectPKts);
		
		JQGridContainer container  =  this.stockService.getFavorites(term, prpLst, userId, p, pageSize, sIdx, sorD);
		
		xml = JSONUtil.convertToJSON(container);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(xml);
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
	@SuppressWarnings("unchecked")
	public ModelAndView addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		//UserSession userSession = (UserSession)session.getAttribute(Constants.USER_SESSION);
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		List<String> selectPkts = new Vector<String>(); 
		Set<String> selPkt = new HashSet<String>();

		if(request.getSession().getAttribute(Constants.SELECTED_PKTS)!=null){
			selectPkts  = (Vector)request.getSession().getAttribute(Constants.SELECTED_PKTS);
		}
		//String selPkts 
	//	String selection = RequestUtils.getParam(request, "sVal", "");
		String[] array = new String[0];
		if(request.getParameterValues("sVal[]")!=null){
			array = request.getParameterValues("sVal[]");
		}	
		logger.debug("#############"+array.length);
		selPkt.addAll(Arrays.asList(array));
		selectPkts.addAll(selPkt);
		//	int z[] = this.getStockService().addToFavorite(selectPkts, userSession.getUserId());
		
		//int z[] = this.getStockService().addToFavorite(selectPkts, userId);
		session.setAttribute(Constants.SESSION_BASKET, selectPkts);
		
		String success="ok", json="";
		String[] data = new String[1];
		data[0]=success;
		json = JSONUtil.convertToJSON(data);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
	//	return new ModelAndView(new RedirectView("myFavorite.html"));
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView myCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		List<PrpData> prpLst= (List<PrpData>) request.getSession().getAttribute("PRP_DATA");
        int clearFlg = RequestUtils.getParam(request, "clr", -1);
		if(clearFlg == 1){
			session.removeAttribute(Constants.SESSION_BASKET);
			session.removeAttribute(Constants.SELECTED_PKTS);
		}
		
		
		String [] colDataArr = this.getStockService().getJQGridColModel(prpLst, Constants.SEARCH_RESULT);
		request.setAttribute("headers", colDataArr[0]);
		request.setAttribute("colModel", colDataArr[1]);
		
		request.setAttribute("TableHeader", "My Cart");
		request.setAttribute("gridLink", "myCartGrid.html?q=2");
		
		return new ModelAndView("basket");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView myCartGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PrpData> prpLst = (List<PrpData>)request.getSession().getAttribute(Constants.PRP_DATA);

		HttpSession session = request.getSession();
		Integer userId = RequestUtils.getUserSession(request).getUserId();   
		Double term = RequestUtils.getUserSession(request).getTerm();   
		
		String xml = null;
		int p =RequestUtils.getParam(request, "page", 1);
		int pageSize = -1;
		String sIdx =RequestUtils.getParam(request, "sidx", "");
		String sorD =RequestUtils.getParam(request, "sord", "");
		
		String whereClause	=  "";	
		List<String> pktCodes = new Vector<String>();
		
		if(session.getAttribute(Constants.SESSION_BASKET) != null)
			pktCodes = (List<String>)session.getAttribute(Constants.SESSION_BASKET);
		
		if(pktCodes.size()>0){
			String[] strings =  new String[pktCodes.size()];
			whereClause += " and s.id in ("+ StringUtils.toString((String[])pktCodes.toArray(strings), ",")+") ";
		}else{
			whereClause = " and 1 = 2";
		}
			
		
		JQGridContainer container  =  this.stockService.getStockData(term,prpLst,whereClause, userId, p, pageSize, sIdx, sorD,"",0D);
		xml = JSONUtil.convertToJSON(container);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(xml);
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
	public ModelAndView submitOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		@SuppressWarnings("unused")
		List<PrpData> prpLst= (List<PrpData>) request.getSession().getAttribute("PRP_DATA");
		HttpSession session = request.getSession();
		Integer userId = RequestUtils.getUserSession(request).getUserId();  
		
		String[] array = new String[0];
		List<String> selectPkts = new Vector<String>(); 
		String subject = "HRC : Diamond Request";
		
		selectPkts = (List<String> )session.getAttribute(Constants.SESSION_BASKET);
		OrderMasterData omd = new OrderMasterData();
		omd.setOrderType(RequestUtils.getParam(request, "orderType", ""));
		omd.setContactPerson(RequestUtils.getParam(request, "contactPerson", ""));
		omd.setComments(RequestUtils.getParam(request, "comments", ""));
		omd.setUserId(userId);
		omd.setStatus(Constants.STATUS_PENDING);
		omd.setUserName(RequestUtils.getUserSession(request).getUserName());
		omd.setCompanyName(RequestUtils.getUserSession(request).getCompnayName());
		//omd.setDgc(dgc);
		
		List<PacketDetails> pktList = new Vector<PacketDetails>();
		for (int i = 0; i < selectPkts.size(); i++) {
			logger.debug("#####  ro num =" +i +" pkt "+selectPkts.get(i));
			if(RequestUtils.getParam(request, "jqg_newapi_"+selectPkts.get(i), "off").equals("on")){
				PacketDetails pktDtl = new PacketDetails();
				pktDtl.setPktId(Integer.parseInt(selectPkts.get(i)));
				
				if(!pktList.contains(pktDtl))
					pktList.add(pktDtl);
			}
		} 
		omd.setPacketList(pktList);
		int orderId =0;
		if(pktList.size() >0)
			orderId = this.getStockService().insertOrder(omd, 1.0);
		omd.setOrderDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		omd.setId(orderId);
		this.getStockService().updateStoneStatus(pktList, 1 );
		
		String email =this.getCommonService().getPropertiesByName("b2b.sales.email"); 
		this.getMailSender().sendMail(email, subject , request, orderId);

		omd.setPacketList(this.getStockService().getPktDetailsByOrderId(Constants.STATUS_TYPE_SL, Integer.toString(orderId), Constants.STATUS_APPROVED));
		
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
		logger.debug("##### invoice.jsp ");
		return new ModelAndView("memo/invoice");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView makeExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		HttpSession session = request.getSession();
		List<PrpData> prpLst = (List<PrpData>)session.getAttribute(Constants.PRP_DATA);
		int userId = RequestUtils.getUserSession(request).getUserId(); 
		Double term = RequestUtils.getUserSession(request).getTerm();
		
		String stage = RequestUtils.getParam(request, "stage", "S");
		OutputStream out = null;
		String[] array = null;
		if(request.getParameterValues("sVal")!=null){
			array = request.getParameterValues("sVal");
		}	
		String currency =  RequestUtils.getParam(request, "currency", "");
 		Double factor =  Double.parseDouble(RequestUtils.getParam(request, "factor", "0"));
		
		try{
		response.setContentType("application/vnd.ms-excel");
		response.setHeader
		     ("Content-Disposition", "attachment; filename=hrc.xls");
		   WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
		   WritableSheet s = w.createSheet("hrc-Stock List", 0);
		 
		   List<String> hdrs= new ArrayList<String>();
		   //hdrs.add("Pkt No.");
		  		   
		   for (int i = 0; i < prpLst.size(); i++) {
			   hdrs.add(prpLst.get(i).getWebhdr());
				if(prpLst.get(i).getPrp().equalsIgnoreCase("RATE") && !RequestUtils.isEmpty(currency)){
					hdrs.add("Rate("+prpLst.get(i).getWebhdr()+")");
				}
		   }
		  // hdrs.add("Total");
		   
		   String whereClause = "";
		   JQGridContainer container = new JQGridContainer();
		   if(array !=null && array.length >0){
			   //for selected currencies
			   whereClause += " and s.id in ("+ StringUtils.toString(array, ",")+") ";
			   container = getStockService().getStockData(term,prpLst, whereClause, userId, 1, -1, "cts", "desc", currency, factor);
		 
		   }else{
			    if(stage.equals("F")){
				   container = getStockService().getFavorites(term, prpLst, userId, 1, -1,  "cts", "desc");
			    }else if(stage.equals("B")){
				   List<String> pktCodes = new Vector<String>();
					if(session.getAttribute(Constants.SESSION_BASKET) != null)
						pktCodes = (List<String>)session.getAttribute(Constants.SESSION_BASKET);
					if(pktCodes.size()>0){
						String[] strings =  new String[pktCodes.size()];
						whereClause += " and s.id in ("+ StringUtils.toString((String[])pktCodes.toArray(strings), ",")+") ";
					}
					container  =  getStockService().getStockData(term,prpLst,whereClause, userId, 1, -1, "cts", "desc", currency, factor);
				   
			   } else{
				   whereClause = (String)session.getAttribute(Constants.SEARCH_CLAUSE);
				   container = getStockService().getStockData(term,prpLst, whereClause, userId, 1, -1, "cts", "desc", currency, factor);
			   }
		   }
		   CommonUtil.fillXLWeb(s, container, hdrs);
		   
		   
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
	
	public ModelAndView myAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {	
	
		request.setAttribute("status", RequestUtils.getParam(request, "status", -1));
		request.setAttribute("orderType",RequestUtils.getParam(request, "orderType",""));
		request.setAttribute("pktStatus", RequestUtils.getParam(request, "pktStatus",-1));
		return new ModelAndView("account");
	}
	
	public ModelAndView myAccountGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		           
		int userId = RequestUtils.getUserSession(request).getUserId();
		int status = RequestUtils.getParam(request, "status", 1); 
		String orderType = RequestUtils.getParam(request, "orderType","Confirm");
		
		SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy");
		int p =RequestUtils.getParam(request, "page", 1);
		int pageSize = RequestUtils.getParam(request, "pageSize", 50);
		String srtIndex =RequestUtils.getParam(request, "sidx", "");
		String srtType =RequestUtils.getParam(request, "sord", "");
		String fromDate = RequestUtils.getParam(request, "fromDate", "");
		
		int pktStatus = RequestUtils.getParam(request, "pktStatus", -1);
		Date fromDateTime = null;
		if(!StringUtils.isEmpty(fromDate))
			fromDateTime = smf.parse(fromDate);
		
		PageList pageList = this.getStockService().getOrderMasterData(userId, status, pktStatus, orderType, fromDateTime, srtIndex, srtType, p, pageSize);
		String json = JSONUtil.convertToJSON(pageList);
		
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	
	public ModelAndView reqShip(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
			
		String[] array = null;
		if(request.getParameterValues("sVal")!=null){
			array = request.getParameterValues("sVal");
		}	
		logger.debug("#############"+array.length);
		
		
		StringBuilder sb = new StringBuilder("<div><b>Company</b> >> "+ RequestUtils.getUserSession(request).getCompnayName() +" >> <b>User </b>")
				.append(RequestUtils.getUserSession(request).getUserName()+" <div/>");
		sb.append("<div >Packet No.</div");
		
		for (int i = 0; i < array.length; i++) {
			sb.append("<div >"+array[i]+"</div>");
		}
		String email=this.getCommonService().getPropertiesByName("b2b.sales.email");
		String cc=this.getCommonService().getPropertiesByName("b2b.owner.email");
		
		this.getMailSender().general_send_mail(email, cc, sb.toString(), "Request for Shipment");
		
		String success="ok", json="";
		String[] data = new String[1];
		data[0]=success;
		
		json = JSONUtil.convertToJSON(data);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		
		return null;
	}
	
	public ModelAndView invoiceReprint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int userId = RequestUtils.getUserSession(request).getUserId();
		int orderId = RequestUtils.getParam(request, "orderId", -1);
		List<PrpData> prpRsltLst = (List<PrpData>)request.getSession().getAttribute(Constants.PRP_DATA);
		
		String orderType = RequestUtils.getParam(request, "OrderType", Constants.ORDER_TYPE_CONFRIM);
		
		
		//	request.setAttribute("msg","No such order present in system");
		//	return new ModelAndView("invoice");
		String whereClause="";
		if(orderType.equals(Constants.ORDER_TYPE_REQUEST)){
			if(orderId ==-1){	
				whereClause+= " and s.Id in (select pktId from tb_orderdetail od INNER join tb_ordermaster om on om.ID =od.orderId and om.ID in ("+orderId+")) " ;
			}else{
				whereClause+= " and s.Id in (select pktId from tb_orderdetail od INNER join tb_ordermaster om on om.ID =od.orderId and om.ID and " +
						" DATEDIFF(curdate(), om.orderDate)  < 30 and om.orderType = '"+Constants.ORDER_TYPE_REQUEST+"') " ;
			}
			request.getSession().setAttribute(Constants.SEARCH_CLAUSE, whereClause);
			
			request.setAttribute("OrderList",this.getStockService().getOrdersList(userId, orderType, -1,90));
			
			String [] colDataArr = this.getStockService().getJQGridColModel(prpRsltLst, Constants.SEARCH_RESULT);
			request.setAttribute("headers", colDataArr[0]);
			request.setAttribute("colModel", colDataArr[1]);
			request.setAttribute("TableHeader", "My Request ("+ (orderId==0?"ALL":orderId) +")");
			request.setAttribute("gridLink", "stockLoadGrid.html?q=2");
			return new ModelAndView("searchResult");
		}
			
		 
		OrderMasterData omd = this.getStockService().getOrderMasterDetail(userId, orderId);
		
		omd.setId(orderId);
		omd.setCompanyName(RequestUtils.getUserSession(request).getCompnayName());
		omd.setPacketList(this.getStockService().getPktDetailsByOrderId(Constants.STATUS_TYPE_SL, Integer.toString(orderId),Constants.STATUS_APPROVED));
		Double totalCts=0D,total=0D; 
		for (int i = 0; i < omd.getPacketList().size(); i++) {
			PacketDetails pd = new PacketDetails();
			pd =omd.getPacketList().get(i);
			total+=pd.getRate();
			totalCts += pd.getCts();
		}
		NumberFormat nf = new DecimalFormat("#0.00");
		omd.setTotalCts(Double.valueOf(nf.format(totalCts)));
		omd.setTotalAmount(Double.valueOf(nf.format(total)));
		
		
		request.setAttribute("orderMasterData",omd);
		
		return new ModelAndView("invoice");
	}
	
	public ModelAndView orderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int userId = RequestUtils.getUserSession(request).getUserId();
		
		String orderType = RequestUtils.getParam(request, "OrderType", Constants.ORDER_TYPE_CONFRIM);
		
		request.setAttribute("OrderList",this.getStockService().getOrdersList(userId, orderType, -1,90));
		request.setAttribute("OrderType", orderType);
		return new ModelAndView("orderList");
	}
	
	public ModelAndView packetDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pktId = RequestUtils.getParam(request, "pktId", -1);
		PacketDetails pd = this.getStockService().getPacketDetails(pktId);
		
		request.setAttribute("pd", pd);
		return new ModelAndView("pktDetails");
	}
	
	
}