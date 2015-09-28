
package com.basync.b2b.crm.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.asn1.ocsp.Request;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.controller.AbstractController;
import com.basync.b2b.controller.IExceptionHandler;
import com.basync.b2b.crm.data.AngadiaMaster;
import com.basync.b2b.crm.data.BankAccounts;
import com.basync.b2b.crm.data.Currency;
import com.basync.b2b.crm.data.GeneralIdNameStatusEtc;
import com.basync.b2b.crm.data.GlRepData;
import com.basync.b2b.crm.data.PacketHistory;
import com.basync.b2b.crm.data.PartyAccData;
import com.basync.b2b.crm.data.PaymentDetails;
import com.basync.b2b.crm.data.ProfitLossData;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.ThreeStrings;
import com.basync.b2b.crm.service.IGenericService;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.b2b.crm.service.IRepService;
import com.basync.b2b.dao.CustomerInfo;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.PartyOutStandingDO;
import com.basync.b2b.mail.MailSenderPooled;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.service.IPrpService;
import com.basync.b2b.util.DateUtil;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;
import com.basync.b2b.util.NumberUtils;;

public class AccController extends AbstractController implements IExceptionHandler {
	protected IPrpService prpService;
	
	protected ICommonService commonService;
	
	protected IMemoService memoService;
	
	private MailSenderPooled mailSender;
	
	private IGenericService genericService;
	
	private IPartyService partyService;
	
	private IPriceService priceService;

	private IRepService repService;

	
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
	 * @return the repService
	 */
	public IRepService getRepService() {
		return repService;
	}



	/**
	 * @param repService the repService to set
	 */
	public void setRepService(IRepService repService) {
		this.repService = repService;
	}



	/**
	 * 
	 */

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
	public ModelAndView angadia(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		request.setAttribute("glAcc",this.getMemoService().getGLAccData(-1));
		request.setAttribute("angadiaList",this.getMemoService().getAngadiaData(1));
		request.setAttribute("currency",this.getMemoService().getCurrencyQD(-1));
		return new ModelAndView("party/angadia");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addUpdateAngadia(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		AngadiaMaster am = new AngadiaMaster();
		UserSession us = RequestUtils.getUserSession(request);
		
		
		am.setAngadiaCoName(RequestUtils.getParam(request, "angadiaCoName", ""));
		am.setOpBalance(Double.parseDouble(RequestUtils.getParam(request, "opBalance", "0")));
		am.setCode(RequestUtils.getParam(request, "code", ""));
		am.setDsc(RequestUtils.getParam(request, "dsc", ""));
		am.setAccCode(RequestUtils.getParam(request, "accountCode", "0"));
		am.setCurrCode(RequestUtils.getParam(request, "currCode", us.getCurrency()));
		
		int  i = 0;
		if(RequestUtils.getParam(request, "angadiaId", -1) > 0 ){
			i = this.getMemoService().updateAngadia(am, us);
		}else{
			i = this.getMemoService().addNewAngadia(am, us);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
		
		return new ModelAndView(new RedirectView("angadia.html"));
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView glAccType(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("glAccTypes",this.getMemoService().getGLAccTypData(-1));
		return new ModelAndView("acc/glAccTyp");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addUpdateGlAccType(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		GeneralIdNameStatusEtc g = new GeneralIdNameStatusEtc();
		
		g.setName(RequestUtils.getParam(request, "name", ""));
		g.setStatus(RequestUtils.getParam(request, "status", 1));
		
		int  i = 0;
		if(RequestUtils.getParam(request, "glAccTypeId", -1) > 0 ){
			i = this.getMemoService().updateGLAccTyp(g);
		}else{
			i = this.getMemoService().addNewGLAccTyp(g);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
		
		return new ModelAndView(new RedirectView("glAccType.html"));
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView glAccGrp(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("glAccTypes",this.getMemoService().getGLAccTypData(1));
		request.setAttribute("glAccGrp",this.getMemoService().getGLAccGrpData(-1));
		return new ModelAndView("acc/glAccGrp");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addUpdateGlAccGrp(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		GeneralIdNameStatusEtc g = new GeneralIdNameStatusEtc();
		
		g.setName(RequestUtils.getParam(request, "name", ""));
		g.setStatus(RequestUtils.getParam(request, "status", 0));
		g.setTypeId(RequestUtils.getParam(request, "glAccTypeId", 0));
		
		int  i = 0;
		if(RequestUtils.getParam(request, "glAccGrpId", -1) > 0 ){
			i = this.getMemoService().updateGLAccGrp(g);
		}else{
			i = this.getMemoService().addNewGLAccGrp(g);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
		
		return new ModelAndView(new RedirectView("glAccGrp.html"));
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView glAcc(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("glAccGrp",this.getMemoService().getGLAccGrpData(1));
		request.setAttribute("glAcc",this.getMemoService().getGLAccData(-1));
		return new ModelAndView("acc/glAcc");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addUpdateGlAcc(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		GeneralIdNameStatusEtc g = new GeneralIdNameStatusEtc();
		
		g.setName(RequestUtils.getParam(request, "name", ""));
		g.setStatus(RequestUtils.getParam(request, "status", 0));
		g.setTypeId(RequestUtils.getParam(request, "glAccGrpId", 0));
		g.setCode(RequestUtils.getParam(request, "code", ""));
		
		int  i = 0;
		if(RequestUtils.getParam(request, "glAccId", -1) > 0 ){
			i = this.getMemoService().updateGLAcc(g);
		}else{
			i = this.getMemoService().addNewGLAcc(g);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
		
		return new ModelAndView(new RedirectView("glAcc.html"));
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView bankAcc(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("glAcc",this.getMemoService().getGLAccData(-1));
		request.setAttribute("currency",this.getMemoService().getCurrencyQD(-1));
		request.setAttribute("bankAccList",this.getMemoService().getBankAccList(1,-1));
		return new ModelAndView("acc/bankAcc");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addBankAcc(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		BankAccounts b = new BankAccounts();
		UserSession u = RequestUtils.getUserSession(request);
		b.setAccountCode(RequestUtils.getParam(request, "accountCode", ""));
		b.setAccountType(RequestUtils.getParam(request, "accountType", 0));
		b.setBankAccountName(RequestUtils.getParam(request, "bankAccountName", ""));
		b.setBankAccountNumber(RequestUtils.getParam(request, "bankAccountNumber", ""));
		b.setBankAddress(RequestUtils.getParam(request, "bankAddress", ""));
		b.setBankName(RequestUtils.getParam(request, "bankName", ""));
		b.setBankCurrCode(RequestUtils.getParam(request, "bankCurrCode", ""));
		b.setDfltCurrAct(RequestUtils.getParam(request, "dfltCurrAct", 0));
		b.setStatus(RequestUtils.getParam(request, "status", 1));
		b.setBranchName(RequestUtils.getParam(request, "branchName", ""));
		b.setPartyAccId(u.getPartyAccId());
		
		int  i = 0;
		if(RequestUtils.getParam(request, "bankAccId", -1) > 0 ){
			i = this.getMemoService().updateBankAcc(b);
		}else{
			i = this.getMemoService().addNewBankAcc(b);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
		
		return new ModelAndView(new RedirectView("bankAcc.html"));
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView currency(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		return new ModelAndView("acc/currList");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addCurr(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		Currency c = new Currency();

		c.setCurrAbrev(RequestUtils.getParam(request, "currAbrev", ""));
		c.setCurrency(RequestUtils.getParam(request, "currency", ""));
		c.setCurrSymbol(RequestUtils.getParam(request, "currSymbol", ""));
		c.setCountry(RequestUtils.getParam(request, "country", ""));
		c.setHundredsName(RequestUtils.getParam(request, "hundredsName", ""));
		c.setAutoUpdate(RequestUtils.getParam(request, "autoUpdate", 0));
		c.setStatus(RequestUtils.getParam(request, "status", 1));
		
		int  i = 0;
		if(RequestUtils.getParam(request, "currUpdate", 0) > 0 ){
			i = this.getMemoService().updateCurrency(c);
		}else{
			i = this.getMemoService().addNewCurrency(c);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
		
		return new ModelAndView(new RedirectView("currency.html"));
	}
	
	public ModelAndView deleteBankAccount(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		int id = RequestUtils.getParam(request, "bankAccId", 0);

		int  i = 0;
		if(id > -1 ){
			i = this.getMemoService().deleteBankAcc(id);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
		return new ModelAndView(new RedirectView("bankAcc.html"));
	}
	
	public ModelAndView deleteCurrency(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		String id = RequestUtils.getParam(request, "currAbrev", "");
		
		int  i = 0;
		if(!StringUtils.isEmpty(id)){
			i = this.getMemoService().deleteCurrency(id);
		}
		if( i > 0)
			request.setAttribute("msg", "Success");
	
	return new ModelAndView(new RedirectView("currency.html"));
	}
	
	public ModelAndView bankTrf(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		request.setAttribute("bankAcc", this.getMemoService().getBankAccList(1, partyAccId));
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		return new ModelAndView("acc/bankTrf");
	}
	
	/**
	 * AJAX
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getBalanceJson(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		int bankAccId = RequestUtils.getParam(request, "fromBankAccId", -1);
		Double balance = 0d;
		if(bankAccId  >-1){
			balance = this.getMemoService().getBankBalance(bankAccId);
		}
			String json = JSONUtil.convertToJSON(balance);
			
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			
			return null;
	}
	
	public ModelAndView bankTrfAction(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		//int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		int userId = RequestUtils.getUserSession(request).getUserId();
		
		String sFromBankAccId = RequestUtils.getParam(request, "fromBankAccId", "");
		String sToBankAccId = RequestUtils.getParam(request, "toBankAccId", "");
		Integer fromBankAccId = 0;
		Integer toBankAccId =0;
		if(!StringUtils.isEmpty(sFromBankAccId))  fromBankAccId = Integer.parseInt(sFromBankAccId.substring(0, sFromBankAccId.indexOf("|")));
		if(!StringUtils.isEmpty(sToBankAccId))  toBankAccId =  Integer.parseInt(sToBankAccId.substring(0, sToBankAccId.indexOf("|")));
		
		String ref = RequestUtils.getParam(request, "ref", "");
		String paymentDate = RequestUtils.getParam(request, "paymentDate", "");
		BigDecimal amount = new BigDecimal(RequestUtils.getParam(request, "amount", "0"));
		BigDecimal charges = new BigDecimal(RequestUtils.getParam(request, "charges", "0"));
		String dsc = RequestUtils.getParam(request, "dsc", "");
		BigDecimal exRate1 = new BigDecimal(RequestUtils.getParam(request, "exRate1", "1"));
		BigDecimal exRate2 = new BigDecimal(RequestUtils.getParam(request, "exRate2", "1"));
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String userCurrency = RequestUtils.getUserSession(request).getCurrency();
		this.getMemoService().addBankTrf(fromBankAccId, toBankAccId, ref, paymentDate, amount, charges, exRate1, exRate2, dsc, userId, partyAccId, userCurrency);
		
		return new ModelAndView(new RedirectView("bankTrf.html"));
	}
	public ModelAndView pay(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_BUYER, RequestUtils.getUserSession(request).getPartyAccId()));
		request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		request.setAttribute("BANK_ACC_LIST",this.getMemoService().getBankAccList(1,-1));
		request.setAttribute("ANGADIA_LIST",this.getMemoService().getAngadiaData(1));
		request.setAttribute("GL_ACC",this.getMemoService().getGLAccData(1));
		request.setAttribute("accCodeDef",this.getGenericService().getSysPref(Constants.default_cogs_act));
		request.setAttribute("accCodeParty",this.getGenericService().getSysPref(Constants.debtors_act));
		request.setAttribute("accCodeVendor",this.getGenericService().getSysPref(Constants.creditors_act));
		//request.setAttribute("accSales",this.getMemoService().getSysPref(Constants.default_sales_act));
		//request.setAttribute("SELF_PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF));
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		
	return new ModelAndView("acc/pay");
	}
	
	public ModelAndView paymentAction(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		
		Integer fromAccType = RequestUtils.getParam(request, "fromAccType", 0);
		Integer toPartyType = RequestUtils.getParam(request, "toPartyType", 0);
		
		String bankAccStr = RequestUtils.getParam(request, "fromBankAccId", "");
		Integer fromBankAccId = 0;
		if(!StringUtils.isEmpty(bankAccStr)) fromBankAccId = Integer.parseInt(bankAccStr.substring(0, bankAccStr.indexOf("|")));
		Integer fromAngadiaId = RequestUtils.getParam(request, "fromAngadiaId", -1);
		
		Integer toPartyAccId = RequestUtils.getParam(request, "toPartyAccId", -1);
		Integer vendorAccId = RequestUtils.getParam(request, "vendorAccId", -1);
		String toPartyName = RequestUtils.getParam(request, "toPartyName", "");
		Integer invoice = RequestUtils.getParam(request, "invPay", -1);
		//if(invoice.equals("-1"))
		// make direct payment...get the first unpaid invoice amount and make a payment to that invoice 
		
		String accountCode = RequestUtils.getParam(request, "accountCode", "");
		
		String paymentDate = RequestUtils.getParam(request, "paymentDate", "");
		BigDecimal amount = new BigDecimal(RequestUtils.getParam(request, "amount", "0"));
		
		BigDecimal actualAmount = Constants.BIGDECIMAL_ZERO;
		if(invoice == -1) actualAmount = amount;	
		else actualAmount = new BigDecimal(RequestUtils.getParam(request, "actualAmount", "0"));
		
		String mode = RequestUtils.getParam(request, "mode", "");

		String ref = RequestUtils.getParam(request, "ref", "");
		String dsc = RequestUtils.getParam(request, "dsc", "");
		BigDecimal exRate = new BigDecimal(RequestUtils.getParam(request, "exRate", "1"));
		String bank = RequestUtils.getParam(request, "bank", "");
		String bankAccNo = RequestUtils.getParam(request, "bankAccNo", "");
		String chequeDate = RequestUtils.getParam(request, "chequeDate", "");
		String chequeNo = RequestUtils.getParam(request, "chequeNo", "");
		
		PaymentDetails pd = new PaymentDetails();
		pd.setChequeNo(chequeNo);
		pd.setBank(bank);
		pd.setBankAccNo(bankAccNo);
		
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String userCurrency = RequestUtils.getUserSession(request).getCurrency();		
		this.getMemoService().addPaymentTrf(fromAccType,toPartyType,fromBankAccId, fromAngadiaId, toPartyAccId, vendorAccId, toPartyName,
												accountCode, paymentDate, amount, actualAmount, mode, ref, dsc, exRate, userId,  invoice, pd, chequeDate, partyAccId, userCurrency);
		
		return new ModelAndView(new RedirectView("pay.html"));
	}
	
	public ModelAndView deposit(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		List<PartyAccData> nw = this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_BUYER,RequestUtils.getUserSession(request).getPartyAccId());
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_BUYER,RequestUtils.getUserSession(request).getPartyAccId()));
		request.setAttribute("VENDOR_LIST", this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR));
		request.setAttribute("BANK_ACC_LIST",this.getMemoService().getBankAccList(1,-1));
		request.setAttribute("ANGADIA_LIST",this.getMemoService().getAngadiaData(1));
		request.setAttribute("GL_ACC",this.getMemoService().getGLAccData(1));
		//request.setAttribute("accCodeDef",this.getMemoService().getSysPref(Constants.default_cogs_act));
		//request.setAttribute("accCodeParty",this.getMemoService().getSysPref(Constants.debtors_act));
		//request.setAttribute("accCodeVendor",this.getMemoService().getSysPref(Constants.creditors_act));
		request.setAttribute("accSales",this.getGenericService().getSysPref(Constants.default_sales_act));
		//request.setAttribute("SELF_PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF));
		request.setAttribute("currencyList",this.getMemoService().getCurrencyList(-1));
		request.setAttribute("localCurr",RequestUtils.getUserSession(request).getCurrency());
		return new ModelAndView("acc/deposit");
	}
	public ModelAndView depositAction(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		int userId = RequestUtils.getUserSession(request).getUserId();
		
		int fromPartyType = RequestUtils.getParam(request, "fromPartyType", 0);
		int toAccType = RequestUtils.getParam(request, "toAccType", 0);
		
		int fromPartyAccId = RequestUtils.getParam(request, "fromPartyAccId", -1);
		int vendorAccId = RequestUtils.getParam(request, "vendorAccId", -1);
		String fromPartyName = RequestUtils.getParam(request, "fromPartyName", "");

		int toBankAccId = RequestUtils.getParam(request, "toBankAccId", -1);
		int toAngadiaId = RequestUtils.getParam(request, "toAngadiaId", -1);

		String paymentDate = RequestUtils.getParam(request, "paymentDate", "");
		String mode = RequestUtils.getParam(request, "mode", "");
		String ref = RequestUtils.getParam(request, "ref", "");

		String currency = RequestUtils.getParam(request, "currency", "USD");
		
		//double exRate = Double.parseDouble(RequestUtils.getParam(request, "exRate", "1"));
		BigDecimal exRate2 = new BigDecimal(RequestUtils.getParam(request, "exRate2", "1"));
		Integer checkBoxVal = RequestUtils.getParam(request, "currCheckVal", -1);
		
		
		
		
		//if(currency.equals(""))
		
		String paymentTerm = RequestUtils.getParam(request, "paymentTerm", "");

		String dscMain = RequestUtils.getParam(request, "dsc", "");
		List<PaymentDetails> pDtl = new ArrayList<PaymentDetails>();
		List<QueryDescription> invArrList = this.getMemoService().getInvoiceList(fromPartyAccId);
		
		BigDecimal totalAmount=new BigDecimal(0);
		for (int i = 0; i < 20; i++) {
			if(RequestUtils.getParam(request, "pay_"+i, 0) == 0){
				continue;
			}
						
			BigDecimal amount = new BigDecimal(RequestUtils.getParam(request, "amount_"+i, "0"));
			BigDecimal exRate = new BigDecimal(RequestUtils.getParam(request, "exRate_"+i, "1"));
			BigDecimal actualEnteredAmt = amount;
			/*String sUsd = RequestUtils.getParam(request, "USD_"+i, "0");
			BigDecimal usd = Constants.BIGDECIMAL_ZERO;
			if(sUsd != null) usd = new BigDecimal(sUsd);
			if(amount.equals(0)) continue;	*/
			
			/*if(!currency.equalsIgnoreCase("USD")) {
				amount = amount.divide(exRate, 2, RoundingMode.HALF_UP);
				//sUsd  = 
				
			}*/
			
			
			String accountCode = RequestUtils.getParam(request, "accountCode_"+i, "");
			String dsc = RequestUtils.getParam(request, "dsc_"+i, "");
			String bank = RequestUtils.getParam(request, "bank_"+i, "");
			String bankAccNo = RequestUtils.getParam(request, "bankAccNo_"+i, "");
			String chequeNo = RequestUtils.getParam(request, "chequeNo_"+i, "");
			String chequeDate = RequestUtils.getParam(request, "chequeDate_"+i, "");
			Integer clearStatus = RequestUtils.getParam(request, "clear_"+i, 0);
			Integer invId = RequestUtils.getParam(request, "invPay_"+i, -1);
			PaymentDetails pd =  new PaymentDetails();
			pd.setAmount(amount);
			//pd.setUSD();
			pd.setDsc(dsc);
			pd.setAccountCode(accountCode);
			pd.setBank(bank);
			pd.setBankAccNo(bankAccNo);
			pd.setChequeNo(chequeNo);
			pd.setChequeDate(chequeDate);
			pd.setClearStatus(clearStatus);
			pd.setInvId(invId);
			pd.setExRate(exRate);
			pd.setActualEnteredAmt(actualEnteredAmt);
			if(invId == -1){
				//TODO
					/*for (int j = 0; j < invArrList.size(); j++) {
						QueryDescription invObj = invArrList.get(j);
						String desc = invObj.getDescription();
						Double pendingAmount = Double.valueOf(desc.substring(desc.indexOf(":")+1));
						if()
					}*/
			}else{
				QueryDescription q = new QueryDescription();
				q.setId(invId);
				QueryDescription invObj = invArrList.get(invArrList.indexOf(q));
				String desc = invObj.getDescription();
				BigDecimal pendingAmount = new BigDecimal(desc.substring(desc.indexOf(":")+1));
				
				if(NumberUtils.isGreaterThanBigDecimal(pendingAmount, amount)){
					pd.setPayType("PART");
					//this.getMemoService().updateInvoiceStatus(pd.getInvId(), pd.getAmount(), "UNPAID");
				}else if(pendingAmount.equals(amount)){
					pd.setPayType("FULL");
					//this.getMemoService().updateInvoiceStatus(pd.getInvId(), pd.getAmount(), "PAID");
				}			
			}
			totalAmount.add(amount);
			pDtl.add(pd);
		}
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String localCurr = RequestUtils.getUserSession(request).getCurrency();
		
		int z = this.getMemoService().addDepositTrf(fromPartyType, toAccType, fromPartyAccId, vendorAccId, fromPartyName,
				toBankAccId, toAngadiaId,  paymentDate, totalAmount, mode, ref, dscMain,  userId, pDtl,paymentTerm	, currency,  exRate2, partyAccId, localCurr, checkBoxVal);
		
		if(z == 0){
			request.setAttribute("msg", "More amount paid then original Invoice, hence process skipped");
		}
		
		return new ModelAndView(new RedirectView("deposit.html"));
	}
	public ModelAndView journal(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		request.setAttribute("GL_ACC",this.getMemoService().getGLAccData(1));
		HttpSession session =request.getSession(true);
		if((List<Object> )session.getAttribute("JOURNAL_LIST")!=null) session.removeAttribute("JOURNAL_LIST");
		return new ModelAndView("acc/journal");
	}
	
	public ModelAndView journalAJAX(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		String accCode = RequestUtils.getParam(request, "accountCode", "");
		String accDesc = RequestUtils.getParam(request, "accDesc", "");
		Double debitAmount = Double.parseDouble(RequestUtils.getParam(request, "debitAmt", "0"));
		Double creditAmount = Double.parseDouble(RequestUtils.getParam(request, "creditAmt", "0"));
		String dsc = RequestUtils.getParam(request, "dsc", "");
		int count = RequestUtils.getParam(request, "count", 0);
		int remove = RequestUtils.getParam(request, "remove", 0);
		HttpSession session =request.getSession(true);
		List<Object>  paramList ;
		if((List<Object> )session.getAttribute("JOURNAL_LIST")!=null)
			paramList = (List<Object> )session.getAttribute("JOURNAL_LIST");
		else{
			paramList = new Vector<Object>();
		}
		String html="";
		if(remove > 0){
			if(remove <paramList.size())
				paramList.remove(remove);
		}else{
			html= "<tr id=\""+(count+1)+"\"><td>"+ accDesc+"</td><td class='amount' id='db_"+(count+1)+"'>"+debitAmount+"</td><td class='amount' id='cr_"+(count+1)+"'>"+creditAmount+"</td><td>"+dsc+"</td>"+
			"<td><input type=\"button\" value=\"Remove item\" onclick=\"removeItem(this)\"/></td></tr>";
			
			Object[] journalRow = new Object[4];
			journalRow[0] = accCode;
			journalRow[1] = debitAmount;
			journalRow[2] = creditAmount;
			journalRow[3] = dsc;
			paramList.add(journalRow);
		}
		session.setAttribute("JOURNAL_LIST", paramList);
		//String json = JSONUtil.convertToJSON(html);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
		response.getWriter().flush();
		return null;
	}

	public ModelAndView journalEntry(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		HttpSession session =request.getSession(true);
		int userId = RequestUtils.getUserSession(request).getUserId();
		List<Object>  paramList = (List<Object> )session.getAttribute("JOURNAL_LIST");
		String ref = RequestUtils.getParam(request, "ref", "");
		String date = RequestUtils.getParam(request, "paymentDate", "");
		Integer partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		String localCurr = RequestUtils.getUserSession(request).getCurrency();
		this.getMemoService().addJournalEntry(paramList, date, userId, ref,partyAccId, localCurr);
		
		return new ModelAndView(new RedirectView("journal.html"));
	}
	
	public ModelAndView reports(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("acc/reports");
	}
	
	public ModelAndView glRep(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		request.setAttribute("glAccList",this.getMemoService().getGLAccData(1));
		request.setAttribute("show", 0);		
		request.setAttribute("accCode", null);
		return new ModelAndView("acc/glReport");
	}

	public ModelAndView glRepSubmit(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		request.setAttribute("show", 1);		
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		request.setAttribute("glAccList",this.getMemoService().getGLAccData(1));
	//	SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		String fromDate = RequestUtils.getParam(request, "fromDate", sdf.format(new Date()));
		String toDate = RequestUtils.getParam(request, "toDate", sdf.format(new Date()));
		//String accCode = RequestUtils.getParam(request, "accCode", 1060);
		String[] accCodeArray = request.getParameterValues("accCode");
		String accCodes = StringUtils.toString(accCodeArray, ",");
		request.setAttribute("accCodes", accCodeArray);
		
		List<ThreeStrings> opBalList = this.repService.getGlAccOpGroupBal(fromDate, accCodes);
		List<ThreeStrings> clBalList = this.repService.getGlAccClGroupBal(toDate, accCodes);
		request.setAttribute("glOpBalList", opBalList);
		request.setAttribute("glClBalList", clBalList);
		List<GlRepData> glList =   this.getRepService().getGlGroupRepData(fromDate, toDate, accCodes);
		request.setAttribute("glList", glList);
		request.setAttribute("TO_DATE", toDate);
		request.setAttribute("FROM_DATE",fromDate);
		return new ModelAndView("acc/glReport");
	}
	
	public ModelAndView saleReport(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -90);
		String fromDate = RequestUtils.getParam(request, "fromDate", smf.format(cal.getTime()));
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		
		String sort = RequestUtils.getParam(request, "sort", "");
		String sortType = RequestUtils.getParam(request, "sortType", "");
		UserSession u =  RequestUtils.getUserSession(request);
		int partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		PageList pageList =  this.getRepService().getSaleReportData(fromDate, toDate, pktCode, sort, sortType, partyAccId, -1, 1, -1);
		request.setAttribute("pageList", pageList);
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF,partyAccid));
		request.setAttribute("msg", "From date "+ fromDate +"  to "+toDate +" sort by "+sort+" "+sortType );
		request.setAttribute("fromDate", fromDate  );
		request.setAttribute("toDate", toDate );
		return new ModelAndView("acc/saleRep");
	}
	public ModelAndView saleReportMain(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF,partyAccid));
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		
		return new ModelAndView("acc/saleRep");
	}
	public ModelAndView saleReportPartyWise(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -90);
		String fromDate = RequestUtils.getParam(request, "fromDate", smf.format(cal.getTime()));
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		String sort = "pm.companyName";
		String sortType = "asc";
		UserSession u =  RequestUtils.getUserSession(request);
		int partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		PageList pageList =  this.getRepService().getSaleReportData(fromDate, toDate, pktCode, sort, sortType, partyAccId, -1, 1, -1);
		request.setAttribute("pageList", pageList);
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("msg", "From date "+ fromDate +"  to "+toDate +" sort by "+sort+" "+sortType );
		request.setAttribute("fromDate", fromDate  );
		request.setAttribute("toDate", toDate );
		return new ModelAndView("acc/saleRepPartyWise");
	}
	
	public ModelAndView loadPriceHistory(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		String pktCode = RequestUtils.getParam(request, "pktCode", "");

		//TODO
		List<PacketHistory> pktHistory =  this.getPriceService().getPriceHistory(pktCode, -1);
		List<PacketDetails> pktMemoHistory =  this.getPriceService().getPktMemoHistory(pktCode, -1);
		request.setAttribute("PKT_HISTORY", pktHistory);
		request.setAttribute("PKT_MEMO_HISTORY", pktMemoHistory);
		return new ModelAndView("acc/priceHistory");
	}
	
	public ModelAndView partyOSReport(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		//TODO
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -60);
		String fromDate = RequestUtils.getParam(request, "fromDate", smf.format(cal.getTime()));
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		String pktCode = RequestUtils.getParam(request, "pktCode", "");
		
		String sort = "pm.companyName";
		String sortType = "asc";
		UserSession u =  RequestUtils.getUserSession(request);
		int partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		PageList pageList =  this.getRepService().getPartyOSData(fromDate, toDate, pktCode, sort, sortType, partyAccId, -1, 1, -1);
		request.setAttribute("pageList", pageList);
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("msg", "From date "+ fromDate +"  to "+toDate +" sort by "+sort+" "+sortType );

		request.setAttribute("partyOs", pageList);
		return new ModelAndView("acc/priceHistory");
	}
	
	public ModelAndView partyOSReportDetails(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

	/*	//TODO
		request.setAttribute("PKT_HISTORY", pktHistory);
		request.setAttribute("PKT_MEMO_HISTORY", pktMemoHistory);
		return new ModelAndView("acc/priceHistory");*/
		return new ModelAndView("acc/priceHistory");
	}
	//bankStatementReport_kri(TODO)
	public ModelAndView bankStatementReport(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -7);
		
		String fromDate = RequestUtils.getParam(request, "fromDate", smf.format(cal.getTime()));
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		int bankAccId = RequestUtils.getParam(request, "bankAccId", -1);
		
		if(bankAccId > -1){
		double opBal = this.repService.getBankStatementOpBal(fromDate, bankAccId);
		//HashMap<Integer,Double > bnkAcIdOpBl=new HashMap<Integer, Double>();
		//bnkAcIdOpBl.put(bankAccId, value)
		double totbal = opBal;  
		request.setAttribute("bnkStmOpBal",opBal);
		request.setAttribute("bankStClBal",this.repService.getBankClBal(toDate, bankAccId));
		
		// getBankAcc(Integer bankAccId)   request.setAttribute("glAcc",this.memoService.getGlAcc(accCode));
		//request.setAttribute("bankstmAccCode",this.memoService.getBankAccCode(accCode));
		
		List<GlRepData> bankTransList =   this.getRepService().getbankAccTrans(fromDate, toDate, bankAccId);
		
		
		
		for (int i = 0; i < bankTransList.size(); i++) {
			//BankAccountsRepData bl = bankList.get(i);
			GlRepData bl = bankTransList.get(i);
			totbal = totbal - bl.getDebit() +bl.getCredit();
			bl.setBalance(totbal);
		}
		
		request.setAttribute("BANK_ACC_LIST",this.getMemoService().getBankAccList(1,-1));
		request.setAttribute("bList",bankTransList);
		//to getting bankAccountName
// logger.debug("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"+bankAccName);
		/*for (int i = 0; i < bankAccName.size(); i++) {
			//BankAccountsRepData bl = bankList.get(i);
			GlRepData bacnm = bankAccName.get(i);
			
			bl.setBalance(totbal);
		}
		*/
		}
		return new ModelAndView("acc/bankStatementReport");
	}
	//angadiaReport_kri
	public ModelAndView angadiaReport(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -7);
		
		String fromDate = RequestUtils.getParam(request, "fromDate", smf.format(cal.getTime()));
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		int angdAccId = RequestUtils.getParam(request, "angdAccId", -1);
		
		
		// getBankAcc(Integer bankAccId)   request.setAttribute("glAcc",this.memoService.getGlAcc(accCode));
		//request.setAttribute("bankstmAccCode",this.memoService.getBankAccCode(accCode));
		
		if(angdAccId >  -1){
				double opBal =this.repService.getAngadiaOpBal(fromDate,angdAccId);
				double totbal = opBal;  
				request.setAttribute("angdOpBal",opBal);
				request.setAttribute("angdClBal",this.repService.getAngadiaClBal(toDate,angdAccId));
			List<GlRepData> bankList =   this.getRepService().getAngadiaTran(fromDate, toDate, angdAccId);
			
			for (int i = 0; i < bankList.size(); i++) {
				//BankAccountsRepData bl = bankList.get(i);
				GlRepData bl = bankList.get(i);
				totbal = totbal - bl.getDebit() +bl.getCredit();
				bl.setBalance(totbal);
			}
			request.setAttribute("angList",bankList);
		}
		request.setAttribute("Angd_ACC_LIST",this.getRepService().getAngadiaAccList(1));
		return new ModelAndView("acc/angadiaReport");
	}
	//krishna 14/1/12 for to stockChecking Report
	public ModelAndView stockCheckingReport(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
			Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
			if(partyAccid==0){	
				 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
			}
			else{
				partyAccid=null;
			}
			request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
			request.setAttribute("STATUS", this.getMemoService().getStatus(1));
			request.setAttribute("PRP_LOV",this.getCommonService().getPrpLOV());
			request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF,partyAccid));

			return  new ModelAndView("acc/stockCheckingReport");
	}
	//krishna 17/1/12 for search stockCheking Report
	public ModelAndView searchStkChekRep(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		request.setAttribute("STATUS", this.getMemoService().getStatus(1));
		request.setAttribute("PRP_LOV",this.getCommonService().getPrpLOV());
		int partyAccId = RequestUtils.getParam(request, "partyAccId", 0);
		String[] sh =request.getParameterValues("sh"); 
		String[] c = request.getParameterValues("c"); 
		String[] pu =request.getParameterValues("pu"); 
		String[] status = request.getParameterValues("status");//request.getParameterValues("status");
		String[] size=request.getParameterValues("sz");
		String pktCode=RequestUtils.getParam(request, "pktCode", "");

		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("STKSRCH_LIST",this.getRepService().getStockChekRepData(size, status,sh,pu, c,pktCode,partyAccid)); 

		return  new ModelAndView("acc/stockCheckingReport");
	}
	// krishna 9/1/12 (to know the partyOutstanding detail) Start
	public ModelAndView partyOutStanding(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
		//String pktNo = RequestUtils.getParam(request, "pktCode", "");
		Integer  refParmVal=RequestUtils.getParam(request,"refNo",-1);//(request,"paramRefNo",-1);
		logger.debug("sksksksksks"+refParmVal);
		if(refParmVal>0){
			//request.setAttribute("PRTLIST_BY_REF", this.getPartyService().getp)
			request.setAttribute("PRTLT_BY_REF", this.getPartyService()
					.getPartyOutStandingByRefList(refParmVal));
			
		}
		
	//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null

		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		request.setAttribute("PARTY_LIST",
			this.getPartyService().getPartyAcc(1, 1, null, null, partyAccid));
		request.setAttribute("BROKER_LIST", this.getCommonService()
				.getPartyByType(1, Constants.PARTY_TYPE_BROKER));
			
		return new ModelAndView("acc/partyOutStanding");
	}

	public ModelAndView submitPartyOutStanding(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        
		PartyOutStandingDO prtOtStDO = new PartyOutStandingDO();
		prtOtStDO.setReferneceNo(RequestUtils.getParam(request, "refrno", -1));
		prtOtStDO.setPartyID(RequestUtils.getParam(request, "prtyId", 0));
		prtOtStDO.setFdate(DateUtil.getDate(
				RequestUtils.getParam(request, "date", "-1"), "yyyy-MM-dd"));
		prtOtStDO.setDueDate(DateUtil.getDate(
				RequestUtils.getParam(request, "duDate", "-1"), "yyyy-MM-dd"));
		prtOtStDO.setBrokerID(RequestUtils.getParam(request, "brokId", 0));
		prtOtStDO.setAmmount(Double.parseDouble(RequestUtils.getParam(request,
				"amt", "0")));
		prtOtStDO.setTax(Double.parseDouble(RequestUtils.getParam(request,
				"tax", "0")));
		prtOtStDO.setDiscount(Double.parseDouble(RequestUtils.getParam(request,
				"discnt", "0")));
		prtOtStDO.setExpence(Double.parseDouble(RequestUtils.getParam(request,
				"expnce", "0")));
		prtOtStDO.setFinalAmmount(Double.parseDouble(RequestUtils.getParam(
				request, "fnlamt", "0")));
		prtOtStDO.setDescription(RequestUtils.getParam(request, "descrb", ""));
		logger.debug("ooooooooooooooooooo" + prtOtStDO.getReferneceNo() + ","
				+ prtOtStDO.getPartyID() + "," + prtOtStDO.getDueDate() + ","
				+ prtOtStDO.getFdate() + "," + prtOtStDO.getBrokerID() + ","
				+ prtOtStDO.getAmmount() + "," + prtOtStDO.getTax() + ","
				+ prtOtStDO.getDiscount() + "," + prtOtStDO.getExpence() + ","
				+ prtOtStDO.getFinalAmmount() + ","
				+ prtOtStDO.getDescription());
		int comprefno = this.getPartyService().getPartyOtStdRefNo(prtOtStDO);
		if(comprefno>=1){
		//this.getPartyService().insertPartyOutStding(prtOtStDO);
			//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
			request.setAttribute("PARTY_LIST",
					this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));
			request.setAttribute("BROKER_LIST", this.getCommonService()
					.getPartyByType(1, Constants.PARTY_TYPE_BROKER));
			this.getPartyService().editPartyOutStanding(prtOtStDO);

		return new ModelAndView("acc/partyOutStanding");
		}
		else
		{
		this.getPartyService().insertPartyOutStding(prtOtStDO);
		//this.getPartyService().editPartyOutStanding(prtOtStDO);
		//if we want to redirect another html page then we use new RedirectView("showPartyOutStanding.html")
		//return new ModelAndView(new RedirectView("showPartyOutStanding.html"));
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		request.setAttribute("PARTY_LIST",
				this.getPartyService().getPartyAcc(1, 1, null, null, partyAccid));
		request.setAttribute("BROKER_LIST", this.getCommonService()
				.getPartyByType(1, Constants.PARTY_TYPE_BROKER));
		return new ModelAndView("acc/partyOutStanding");
	}
	}
	public ModelAndView showPartyOutStanding(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute("PRT_OST_LIST", this.getPartyService()
				.getPartyOutStandingList());

		return new ModelAndView("acc/showPartyOutStanding");
	}
	
	public ModelAndView paymentList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String srtIndex = RequestUtils.getParam(request, "srtIndex", "p.paymentDate");
		String srtType = RequestUtils.getParam(request, "srtType", "desc");
		
		Integer selfPartyAccId = RequestUtils.getParam(request, "selfPartyAccId", -1);
		int paymentId = RequestUtils.getParam(request, "paymentId", -1);
		String partyName = RequestUtils.getParam(request, "partyName", "");
		String mode = RequestUtils.getParam(request, "mode", "");
		String frDate = RequestUtils.getParam(request, "frDate", "");
		String toDate = RequestUtils.getParam(request, "toDate", "");
		Integer userId = RequestUtils.getUserSession(request).getUserId();
		
		request.setAttribute("PAYMENT_LIST", this.getPartyService().getPaymentDetailReportList(srtIndex, srtType, userId, selfPartyAccId, paymentId, partyName, mode, frDate, toDate));

		return new ModelAndView("acc/paymentList");
	}
	
	public ModelAndView clearPayment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int paymentDetailId = RequestUtils.getParam(request, "pdId", -1);
		int z = this.getMemoService().clearPayment(paymentDetailId);

		return new ModelAndView(new RedirectView("paymentList.html"));
	}
	
	
	public ModelAndView partyLedger(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -180);
		
		Integer partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		String fromDate = RequestUtils.getParam(request, "fromDate", smf.format(cal.getTime()));
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));

		
		request.setAttribute("opBal",this.getRepService().getPartyBal(fromDate, partyAccId));
		request.setAttribute("LIST",this.getRepService().getPartyGL(fromDate,toDate,partyAccId));
		request.setAttribute("PARTY_DETAIL",this.getPartyService().getPartyName(partyAccId));
		request.setAttribute("clBal",this.getRepService().getPartyBal(toDate, partyAccId));
		request.setAttribute("msg", "From date "+ fromDate +"  to "+toDate );

		return new ModelAndView ("acc/partyLedger");
	}
	
	
	public ModelAndView getPartyBankDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		Integer partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		Map map =  this.getPartyService().getBankDetails(partyAccId);
		String json = "";
		json =  JSONUtil.convertToJSON(map);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	
	public ModelAndView profitLoss(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		return new ModelAndView("acc/profitLoss");
	}
	
	public ModelAndView submitProfitLoss(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fromDate=  RequestUtils.getParam(request, "fromDate", "");
		String toDate=  RequestUtils.getParam(request, "toDate", "");
		Double salesRevenue=this.getMemoService().getSalesData(fromDate,toDate);
		PurchaseMaster goodCost=this.getMemoService().getGoodCostData(fromDate,toDate);
		request.setAttribute("AMOUNT", salesRevenue);
		request.setAttribute("GOOD_COST", goodCost);
		return new ModelAndView("acc/profitLoss");
	}
	
	public ModelAndView perReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		
		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, partyAccid));
		request.setAttribute("submitpage", 0);
		return new ModelAndView("acc/perReport");
	}
	
	public ModelAndView getClosingBalBankAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Integer bankAccId = RequestUtils.getParam(request, "toBankAccId", -1);
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		
		Double d =  this.getRepService().getBankClBal(toDate, bankAccId);
		String json =  JSONUtil.convertToJSON(d);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	
	public ModelAndView getClosingBalAngadiaAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Integer angadiaId = RequestUtils.getParam(request, "angadiaId", -1);
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		
		Double d =  this.getRepService().getAngadiaClBal(toDate, angadiaId);
		String json =  JSONUtil.convertToJSON(d);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	
	public ModelAndView getPartyOSAJAX(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Integer partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		String curr = RequestUtils.getUserSession(request).getCurrency();		
		
		PartyAccData pd = this.getPartyService().findPartyAccByPartyAccId(partyAccId);
		
		
		Map<String, BigDecimal> osmap = this.getRepService().getPartyOS(toDate, partyAccId);
		BigDecimal os = osmap.get("total");
		BigDecimal dueOS = this.getRepService().getPartyOSDue(toDate, partyAccId);
		BigDecimal pay = this.getRepService().getPartyPayment(toDate, partyAccId);
		BigDecimal pendingPay = this.getRepService().getPartyPendingPayment(toDate, partyAccId);
		
		String data ="";
		
		String sAmount = "1";
		BigDecimal amount = Constants.BIGDECIMAL_ZERO;
		Integer checkBox = RequestUtils.getParam(request, "currType", -1);
		String sExRate = RequestUtils.getParam(request, "exRate", null);
		String userCurrency = RequestUtils.getUserSession(request).getCurrency();
		if(pd.getCurrency() != null && !"USD".equals(pd.getCurrency())){
			//if(checkBox == 0 || !userCurrency.equals(pd.getCurrency()))	
			sAmount =  RequestUtils.getExRateJson("USD", pd.getCurrency());
			//else if (sExRate !=null)sAmount = sExRate;
			amount = new BigDecimal(sAmount);
			
			data =  "<table class='memoTable'><tr><th></th><th>USD</th><th>"+pd.getCurrency()+"</th></tr> <tr><th>Total O/S. </th> <td>"+os +"<span style='width:50px;color:red;'>( DUE - "+dueOS.setScale(2, RoundingMode.HALF_UP)+")</span> </td> <td>"+(amount.multiply(os)).setScale(2, RoundingMode.HALF_UP)+"<span style='width:50px;color:red;'>( DUE - "+(amount.multiply(dueOS)).setScale(2, RoundingMode.HALF_UP)+")</span> </td> </tr> " +
			"<tr><th>Total Paid </th> <td> "+pay +" <span style='width:50px'>PDC</span> ("+ pendingPay.setScale(2, RoundingMode.HALF_UP) +") </td><td> "+(amount.multiply(pay)).setScale(2, RoundingMode.HALF_UP) +" <span style='width:50px'>PDC</span> ("+(amount.multiply(pendingPay)).setScale(2, RoundingMode.HALF_UP) +") </td> </tr>" +
			"<tr><th>Balance</th><td>"+ (os.subtract(pay)).setScale(2, RoundingMode.HALF_UP)  +" </td>" +
			"<td>"+((amount.multiply(os)).subtract(amount.multiply(pay))).setScale(2, RoundingMode.HALF_UP)+"</td></tr></table>" ;
			
		}else{
			sAmount = RequestUtils.getExRateJson("USD", pd.getCurrency());
			if(!StringUtils.isEmpty(sAmount)) amount =  new BigDecimal(sAmount);
			data =  "<table class='memoTable'><tr><th></th><th>USD</th></tr> <tr><th>Total O/S </th> <td>"+os.setScale(2, RoundingMode.HALF_UP) +"<span style='width:50px;color:red;'>( DUE - "+dueOS.setScale(2, RoundingMode.HALF_UP)+")</span> </td> </tr> " +
			"<tr><th>Total Paid </th> <td> "+pay.setScale(2, RoundingMode.HALF_UP) +" <span style='width:50px'>PDC</span> ("+pendingPay.setScale(2, RoundingMode.HALF_UP) +") </td></tr>" +
			"<tr><th>Balance</th><td>"+ (os.subtract(pay)).setScale(2, RoundingMode.HALF_UP)  +" </td></tr></table>" ;
		}
		HashMap<String, String> newMap =  new HashMap<String, String>();
		newMap.put("data", data);
		newMap.put("exRate", amount.toString());
		newMap.put("curr", pd.getCurrency());
		List<Currency> currency =  this.getMemoService().getCurrencyList(-1);
		String c = pd.getCurrency();
		for (Currency currency2 : currency) {
			 String currAbrev = currency2.getCurrAbrev(); 
			 if(currAbrev != null && c != null && c.equals(currAbrev)) {
				 newMap.put("currName", currency2.getCurrency());
				 break;
			 }
		}
		
		String json =  JSONUtil.convertToJSON(newMap);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	
	public ModelAndView getPartyOSAJAXWithExrate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Integer partyAccId = RequestUtils.getParam(request, "partyAccId", -1);
		String toDate = RequestUtils.getParam(request, "toDate", smf.format(new Date()));
		String curr = RequestUtils.getUserSession(request).getCurrency();		
		
		PartyAccData pd = this.getPartyService().findPartyAccByPartyAccId(partyAccId);
		
		
		Map<String, BigDecimal> osmap = this.getRepService().getPartyOS(toDate, partyAccId);
		BigDecimal os = osmap.get("total");
		BigDecimal dueOS = this.getRepService().getPartyOSDue(toDate, partyAccId);
		BigDecimal pay = this.getRepService().getPartyPayment(toDate, partyAccId);
		BigDecimal pendingPay = this.getRepService().getPartyPendingPayment(toDate, partyAccId);
		
		String data ="";
		
		String sAmount = "1";
		BigDecimal amount = Constants.BIGDECIMAL_ZERO;
		String sExRate = RequestUtils.getParam(request, "exRate", "");
		if(pd.getCurrency() != null && !"USD".equals(pd.getCurrency())){
			//if(checkBox == 0 || !userCurrency.equals(pd.getCurrency()))	sAmount =  RequestUtils.getExRateJson("USD", pd.getCurrency());
			if(!StringUtils.isEmpty(sExRate))sAmount = sExRate;
			amount = new BigDecimal(sAmount);
			
			data =  "<table class='memoTable'><tr><th></th><th>USD</th><th>"+pd.getCurrency()+"</th></tr> <tr><th>Total O/S. </th> <td>"+os +"<span style='width:50px;color:red;'>( DUE - "+dueOS.setScale(2, RoundingMode.HALF_UP)+")</span> </td> <td>"+(amount.multiply(os)).setScale(2, RoundingMode.HALF_UP)+"<span style='width:50px;color:red;'>( DUE - "+(amount.multiply(dueOS)).setScale(2, RoundingMode.HALF_UP)+")</span> </td> </tr> " +
			"<tr><th>Total Paid </th> <td> "+pay +" <span style='width:50px'>PDC</span> ("+ pendingPay.setScale(2, RoundingMode.HALF_UP) +") </td><td> "+(amount.multiply(pay)).setScale(2, RoundingMode.HALF_UP) +" <span style='width:50px'>PDC</span> ("+(amount.multiply(pendingPay)).setScale(2, RoundingMode.HALF_UP) +") </td> </tr>" +
			"<tr><th>Balance</th><td>"+ (os.subtract(pay)).setScale(2, RoundingMode.HALF_UP)  +" </td>" +
			"<td>"+((amount.multiply(os)).subtract(amount.multiply(pay))).setScale(2, RoundingMode.HALF_UP)+"</td></tr></table>" ;
			
		}else{
			sAmount = RequestUtils.getExRateJson("USD", pd.getCurrency());
			if(!StringUtils.isEmpty(sAmount)) amount =  new BigDecimal(sAmount);
			data =  "<table class='memoTable'><tr><th></th><th>USD</th></tr> <tr><th>Total O/S </th> <td>"+os.setScale(2, RoundingMode.HALF_UP) +"<span style='width:50px;color:red;'>( DUE - "+dueOS.setScale(2, RoundingMode.HALF_UP)+")</span> </td> </tr> " +
			"<tr><th>Total Paid </th> <td> "+pay.setScale(2, RoundingMode.HALF_UP) +" <span style='width:50px'>PDC</span> ("+pendingPay.setScale(2, RoundingMode.HALF_UP) +") </td></tr>" +
			"<tr><th>Balance</th><td>"+ (os.subtract(pay)).setScale(2, RoundingMode.HALF_UP)  +" </td></tr></table>" ;
		}
		HashMap<String, String> newMap =  new HashMap<String, String>();
		newMap.put("data", data);
		newMap.put("exRate", amount.toString());
		newMap.put("curr", pd.getCurrency());
		List<Currency> currency =  this.getMemoService().getCurrencyList(-1);
		String c = pd.getCurrency();
		for (Currency currency2 : currency) {
			 String currAbrev = currency2.getCurrAbrev(); 
			 if(currAbrev != null && c != null && c.equals(currAbrev)) {
				 newMap.put("currName", currency2.getCurrency());
				 break;
			 }
		}
		
		String json =  JSONUtil.convertToJSON(newMap);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
		

	public ModelAndView submitPerReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String partyName =RequestUtils.getParam(request, "partyName", "");
		List<ProfitLossData> perData = this.getMemoService().getPerReportData(fromDate,toDate, partyName);
		request.setAttribute("submitpage", 1);
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1,1,null,null, partyAccid));
		request.setAttribute("PER_DATA", perData);
		request.setAttribute("FROM_DATE", fromDate);
		request.setAttribute("TO_DATE", toDate);
		return new ModelAndView("acc/perReport");
	}

	public ModelAndView getVendorInvList(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Integer vendorId = RequestUtils.getParam(request, "vendorId", 0);
		List<QueryDescription> lst = this.getMemoService().getPurchaseInvoiceList(vendorId, 0);
		response.setContentType("text/plain; charset=UTF-8");
		String json =  JSONUtil.convertToJSON(lst);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		return null;
	}
	public ModelAndView addPurchaseTrf(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//	this.getMemoService().addPurchaseTrf(0,102,"5200", "22-11-1985", new BigDecimal(1500),"","", new BigDecimal(-1), 0, 450, 0);
		return new ModelAndView("acc/perReport");
	}
	public ModelAndView addSaleTrf(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			this.getMemoService().addSaleTrf(0, 0, 0, 0, 0, 1050, "Suma", "Arun", "Mumbai", "1025", "22-11-1985", new BigDecimal(1000), "", "", "", new BigDecimal(-1), 0,new BigDecimal(3), new BigDecimal(10), 0);
		return new ModelAndView("acc/perReport");
	}
	public ModelAndView salesRevenue(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		/*String json =  JSONUtil.convertToJSON(lst);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();*/
		return null;
		
	}
	public ModelAndView otherRevenue(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		/*String json =  JSONUtil.convertToJSON(lst);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();*/
		return null;
	}
	public ModelAndView goodSolds(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		/*String json =  JSONUtil.convertToJSON(lst);
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();*/
		return null;
	}
	// Added by Arvind on 12-06-2012
	/**
	 * This method is used to get customer List.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView custBalance(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_BUYER, partyAccid));
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		return new ModelAndView("acc/custBalance");
	}
	/**
	 * This method used to get the list of all customer balances.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView custBalanceSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String custName=RequestUtils.getParam(request, "customerName", "");
		String branch=RequestUtils.getParam(request, "branch", "");
		List<CustomerInfo> custList=this.getMemoService().getCustomerBalanceList(fromDate,toDate,custName,branch,partyAccid);
		BigDecimal totbal = Constants.BIGDECIMAL_ZERO;
		request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_BUYER, partyAccid));
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("custList", custList);
		request.setAttribute("TO_DATE", toDate);
		request.setAttribute("FROM_DATE", fromDate);
		if(custName!="")
			request.setAttribute("custName", Integer.parseInt(custName));
		if(branch !="")
			request.setAttribute("branch", Integer.parseInt(branch));
		return new ModelAndView("acc/custBalance");
	}
	/**
	 * This method is used to get the supplier list.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView supplierBalance(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		List<QueryDescription> vendorlist =this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR);
		List<QueryDescription> buyerlist=this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BUYER);
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		vendorlist.addAll(buyerlist);
		request.setAttribute("VENDOR_LIST", vendorlist);
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		return new ModelAndView("acc/supplierBalance");
	}
	/**
	 *  This method is used to get the details of supplier.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView supplierBalanceSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String custName=RequestUtils.getParam(request, "suppName", "");
		String branch=RequestUtils.getParam(request, "branch", "");
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		List<QueryDescription> vendorlist =this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR);
		List<QueryDescription> buyerlist=this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BUYER);
		List<CustomerInfo> suppList=this.getMemoService().getSupplierBalanceList(fromDate,toDate,custName,branch,partyAccid);
		vendorlist.addAll(buyerlist);
		request.setAttribute("VENDOR_LIST", vendorlist);
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("suppList", suppList);
		request.setAttribute("TO_DATE", toDate);
		request.setAttribute("FROM_DATE", fromDate);
		if(custName!="")
			request.setAttribute("custName", Integer.parseInt(custName));
		if(branch !="")
			request.setAttribute("branch", Integer.parseInt(branch));
		return new ModelAndView("acc/supplierBalance");
	}
	/**
	 * This method is used to get the vendor list.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView paymentReportForAccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		List<QueryDescription> vendorlist =this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR);
		List<QueryDescription> buyerlist=this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BUYER);
		vendorlist.addAll(buyerlist);
		request.setAttribute("VENDOR_LIST", vendorlist);
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		return new ModelAndView("acc/paymentReport");
	}
	/**
	 * This method is used to get the detail of payment.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView paymentReportSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String custName=RequestUtils.getParam(request, "suppName", "");
		String branch=RequestUtils.getParam(request, "branch", "");
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}

		List<QueryDescription> vendorlist =this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_VENDOR);
		List<QueryDescription> buyerlist=this.getCommonService().getPartyByType(1, Constants.PARTY_TYPE_BUYER);
		vendorlist.addAll(buyerlist);
		request.setAttribute("VENDOR_LIST", vendorlist);
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("PAYMENT_REPORT_LIST", this.getMemoService().getPaymentReport(fromDate,toDate,custName,branch,partyAccid));
		request.setAttribute("TO_DATE", toDate);
		request.setAttribute("FROM_DATE", fromDate);
		if(custName!="")
			request.setAttribute("custName", Integer.parseInt(custName));
		if(branch !="")
			request.setAttribute("branch", Integer.parseInt(branch));
		return new ModelAndView("acc/paymentReport");
	}
	/**
	 * This method is used for populating current date in jsp for tax report.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		return new ModelAndView("acc/taxReport");
	}
	/**
	 * This method is used to get the detail of tax.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxReportSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String branch=RequestUtils.getParam(request, "branch", "");
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("TAX_REPORT", this.getMemoService().getTaxReport(fromDate,toDate,branch));
		request.setAttribute("TO_DATE", toDate);
		request.setAttribute("FROM_DATE", fromDate);
		if(branch !="")
			request.setAttribute("branch", Integer.parseInt(branch));
		return new ModelAndView("acc/taxReport");
	}
	/**
	 * This method is used for populating current date in jsp for balance sheet.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView balanceSheet(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		return new ModelAndView("acc/balanceSheet");
	}
	/**
	 * This method is used to get the detail of balance sheet.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView balanceSheetSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String branch=RequestUtils.getParam(request, "branch", "");
		String accCodes=" 1060,1065,1200,1510,2100,2150,4010 ";
		List<QueryCodeDescription> opBalList = this.repService.getBlAccOpGroupBal(fromDate, accCodes,branch);
		List<QueryCodeDescription> clBalList = this.repService.getBlAccClGroupBal(toDate, accCodes,branch);
		request.setAttribute("TO_DATE", toDate);
		request.setAttribute("FROM_DATE", fromDate);
		request.setAttribute("BAL_REPORT_OPEN", opBalList);
		request.setAttribute("BAL_REPORT_CLOSE", clBalList);
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		if(branch !="")
			request.setAttribute("branch", Integer.parseInt(branch));
		return new ModelAndView("acc/balanceSheet");
	}


	public ModelAndView projectile(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return new ModelAndView("projectile");
	}


	/**
	 * This method is used get the detail of checking account for drilldown balancesheet report
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView checkingAccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String accCode= RequestUtils.getParam(request,"accountCode","");
		String branch = RequestUtils.getParam(request,"branch","");
		List<CustomerInfo> opBalList = this.getMemoService().getCheckingAccountOpen(fromDate,toDate, accCode,branch);
		List<CustomerInfo> clBalList = this.getMemoService().getCheckingAccountClose(fromDate,toDate, accCode,branch);
		request.setAttribute("BAL_REPORT_OPEN", opBalList);
		request.setAttribute("BAL_REPORT_CLOSE", clBalList);
		return new ModelAndView("acc/balAcc");
	}
	/**
	 * This method is used to get the detail of petty cash account for drilldown balance sheet report.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView pettyCashAccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String accCode= RequestUtils.getParam(request,"accountCode","");
		String branch=RequestUtils.getParam(request,"branch","");
		List<CustomerInfo> opBalList = this.getMemoService().getCheckingAccountOpen(fromDate,toDate, accCode,branch);
		List<CustomerInfo> clBalList = this.getMemoService().getCheckingAccountClose(fromDate,toDate, accCode,branch);
		request.setAttribute("BAL_REPORT_OPEN", opBalList);
		request.setAttribute("BAL_REPORT_CLOSE", clBalList);
		return new ModelAndView("acc/balAcc");
	}
	/**
	 * This method is used to get the detail of account receivables for drilldown balance sheet report.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView accountReceivables(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String branch =  RequestUtils.getParam(request, "branch", "");
		List<CustomerInfo> invList = this.getMemoService().getInvoiceDetails(fromDate,toDate,branch);
		List<CustomerInfo> payList = this.getMemoService().getPayDetails(fromDate,toDate,branch);
		request.setAttribute("INVOICE_LIST", invList);
		request.setAttribute("PAY_LIST", payList);
		return new ModelAndView("acc/accReceivable");
	}
	/**
	 * This method is used to get the detail of account payable for drilldown balance sheet report.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView accountPayable(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String branch =  RequestUtils.getParam(request, "branch", "");
		List<CustomerInfo> payableList = this.getMemoService().getPurchaseDetailsReport(fromDate,toDate,branch);
		List<CustomerInfo> payList = this.getMemoService().getPayDetails(fromDate,toDate,branch);
		request.setAttribute("INVOICE_LIST", payableList);
		request.setAttribute("PAY_LIST", payList);
		
		return new ModelAndView("acc/accReceivable");
	}
	/**
	 * This method is used to populate broker jsp page with current date.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView brokerageReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("TO_DATE", sdf.format(new Date()));
		request.setAttribute("FROM_DATE", sdf.format(new Date()));
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			 partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		return new ModelAndView("acc/brokerageReport");
	}
	/**
	 * This method is used to get the detail for brokerage report.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView brokerageReportSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
		request.setAttribute("CURR_DATE", sdf.format(new Date()));
		String  fromDate =  RequestUtils.getParam(request, "fromDate", "");
		String toDate =  RequestUtils.getParam(request, "toDate", "");
		String branch=RequestUtils.getParam(request, "branch", "");
		Integer partyAccid=Integer.parseInt(RequestUtils.getUserSession(request).getUserActivityMap().get(Constants.VIEW_ALL_REPORTS));
		if(partyAccid==0){	
			partyAccid = RequestUtils.getUserSession(request).getPartyAccId();
		}
		else{
			partyAccid=null;
		}
		List<CustomerInfo> brokerList=this.getMemoService().getBrokerageList(fromDate,toDate,partyAccid, branch);
		for(int i=0; i<brokerList.size();i++){
			CustomerInfo ci=brokerList.get(i);
			BigDecimal totalAmount=ci.getAmount();
			BigDecimal brokPer=ci.getBrokPer();
			BigDecimal brokAmount=((brokPer.divide(new BigDecimal(100)).multiply(totalAmount)));
			ci.setBrokAmount(brokAmount);
		}
		request.setAttribute("SELF_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, partyAccid));
		request.setAttribute("BROKERAGE_LIST", brokerList);
		request.setAttribute("TO_DATE", toDate);
		request.setAttribute("FROM_DATE", fromDate);
		if(branch !="")
			request.setAttribute("branch", Integer.parseInt(branch));
		return new ModelAndView("acc/brokerageReport");
	}
	
	/**
	 * This method is used to display the rapnet user creation.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView rapNetCreation(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return new ModelAndView("acc/rapNetCreation");
	}
	/**
	 * This method is used to save the rapnet user creation data.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView rapNetCreationSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String rapUserName=RequestUtils.getParam(request, "rapUsername", "kunal");
		String rapPassword=RequestUtils.getParam(request, "rapPassword", "kunal");
		this.getGenericService().insertRapNetUser(rapUserName,rapPassword);
		return new ModelAndView("acc/rapNetCreation");
	}
	

}
