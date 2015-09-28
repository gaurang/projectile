package com.basync.b2b.crm.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.Payment;
import com.basync.b2b.crm.data.PaymentDetails;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.service.IPartyService;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.JSONUtil;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.NumberUtils;

public class PaymentController extends SimpleFormController{

		protected Logger logger = Logger.getLogger(getClass());
		public PaymentController() {
			setCommandClass(Payment.class);
			setCommandName("payment");
		}
		private Payment payment;
		
		private IMemoService memoService;
		
		private IPartyService partyService;
		
		private ICommonService commonService;
		
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
		 * @param payment the payment to set
		 */
		public void setPayment(Payment payment) {
			this.payment = payment;
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



		@Override
		protected ModelAndView onSubmit(HttpServletRequest request,
				HttpServletResponse response, Object command, BindException errors)
				throws Exception {
			super.onSubmit(request, response, command, errors);
			
			
			SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
			SimpleDateFormat smformat = new SimpleDateFormat(Constants.DATE_FORMAT);
			Payment payment =(Payment) command;
			List<String> resultMsg = new ArrayList<String>();
			payment.setUserId(RequestUtils.getUserSession(request).getUserId());
			
			List<PaymentDetails> paymentDetails = new ArrayList<PaymentDetails>();
			Integer paymentId = RequestUtils.getParam(request, "paymentId", -1);
			//partyAddMaster.setPartyId(partyId);
			//Payment pmd = this.getPartyService().findByPartyMasterById(partyId);
			List<QueryDescription> invList = this.getMemoService().getInvoiceList(payment.getPartyAccId());
			boolean validFlag = true;
			
			
			for(int i =0 ;i <invList.size();i++ ){
				PaymentDetails pd = new PaymentDetails();
				pd.setAmount(new BigDecimal(RequestUtils.getParam(request, "amount_"+invList.get(i).getId(), 0)));
				if(pd.getAmount().equals(0)){
					continue;
				}
				pd.setInvId(invList.get(i).getId());
				String desc = invList.get(i).getDescription();
				BigDecimal pendingAmount = new BigDecimal(desc.substring(desc.indexOf(":")+1));
				if(NumberUtils.isGreaterThanBigDecimal(pendingAmount ,pd.getAmount())){
					pd.setPayType("PART");
					paymentDetails.add(pd);
					//this.getMemoService().updateInvoiceStatus(pd.getInvId(), pd.getAmount(), "UNPAID");
				}else if(pendingAmount.equals(pd.getAmount())){
					pd.setPayType("FULL");
					paymentDetails.add(pd);
					//this.getMemoService().updateInvoiceStatus(pd.getInvId(), pd.getAmount(), "PAID");
				}else{
					validFlag =false;
					resultMsg.add(String.valueOf(validFlag));
					resultMsg.add("Cant Accept payment for Invoice Amount was more then Invoice Amount");
				}
			}
			if(paymentDetails.size() > 0){
				payment.setPaymentDetails(paymentDetails);
				payment.setInvId(1);
			}
			else if(NumberUtils.isGreaterThanBigDecimal(payment.getAmount(), Constants.BIGDECIMAL_ZERO)){
				payment.setInvId(0);
			}
			if(payment.getPaymentDate()!=null){
				payment.setPaymentDate(smformat.format(smf.parse(payment.getPaymentDate())));
			}else{
				payment.setPaymentDate(smformat.format(new Date()));
			}
			if(validFlag){
					if(paymentId == -1){
						this.memoService.save(payment);
					}else{
						this.memoService.update(payment);
					}
				
					PaymentDetails pd = new PaymentDetails();
					for (int i = 0; i < paymentDetails.size(); i++) {
						pd = new PaymentDetails();
						pd = paymentDetails.get(i);
						if(pd.getPayType().equals("FULL")){
							this.getMemoService().updateInvoiceStatus(pd.getInvId(), pd.getAmount(), "PAID");
						}else{
							this.getMemoService().updateInvoiceStatus(pd.getInvId(), pd.getAmount(), "UNPAID");
						}
					}
					resultMsg.add(String.valueOf(validFlag));
					resultMsg.add("Successfully done");
			}
			//paryMasterObj.put(arg0, arg1)
			
			String json = JSONUtil.convertToJSON(resultMsg);
			
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			return null;		
		}
			
		protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
			//List<RegistrationViewDO> list = this.partyService.findByPartyMasterById();
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW);
			
			request.setAttribute("CURR_DATE", sdf.format(new Date()));
			//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
			request.setAttribute("PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, null, RequestUtils.getUserSession(request).getPartyAccId()));
			//TODO for SuperAdmin, the fifth parameter of getPartyAcc will be null
			request.setAttribute("SELF_PARTY_LIST", this.getPartyService().getPartyAcc(1, 1, null, Constants.PARTY_TYPE_SELF, RequestUtils.getUserSession(request).getPartyAccId()));
			return new Payment();
		}
	}
