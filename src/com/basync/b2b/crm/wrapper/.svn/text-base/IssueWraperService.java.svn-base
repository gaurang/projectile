package com.basync.b2b.crm.wrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.dataobjects.StockMasterDO;
import com.basync.b2b.dataobjects.StockPRPDO;
import com.basync.b2b.service.ICommonService;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;


public class IssueWraperService {
	
	protected ICommonService commonService;
	public ICommonService getCommonService() {
		return commonService;
	}
	public List<Object> stockEntryWraper(HttpServletRequest request,
			HttpServletResponse response,int mxStckCount, Map<String, String> hMap)
	throws Exception { 
		
		List<Object>  list= new ArrayList<Object>();
		int partyAccId = RequestUtils.getUserSession(request).getPartyAccId();
		int userId = RequestUtils.getUserSession(request).getUserId();
		StockMasterDO stcMstr=new StockMasterDO();
		StockPRPDO  stcPrpo=new StockPRPDO();
		List<StockMasterDO> stockMasterList = new ArrayList<StockMasterDO>();
		List<StockPRPDO> stockPrpList = new ArrayList<StockPRPDO>(); 
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		
		for(int p=0;p<mxStckCount;p++)
		{
			stcMstr=new StockMasterDO();
			stcPrpo=new StockPRPDO();
			stcMstr.setStatus(1L);
			stcMstr.setPartyAccId(partyAccId);
			stcMstr.setUserId(userId);
			stcMstr.setIssueDate(sdf.format(new Date()));
			
			//check pkt number
			String pktNo = RequestUtils.getParam(request, "Pkt_"+p, "");
			Double avgcts = Double.parseDouble(RequestUtils.getParam(request, "cts_"+p, "0"));
			Double cts = Double.parseDouble(RequestUtils.getParam(request, "totcts_"+p, "0"));
			
			if(StringUtils.isEmpty(pktNo) && cts <= 0){
				continue;
			}
			String puFr=RequestUtils.getParam(request,"puFr_"+p,"");
			String puTo=RequestUtils.getParam(request,"puTo_"+p,"");
			String cFr=RequestUtils.getParam(request,"cFr_"+p,"");
			String cTo=RequestUtils.getParam(request,"cTo_"+p,"");
			String shFr=RequestUtils.getParam(request,"shFr_"+p,"");
			String shTo=RequestUtils.getParam(request,"shTo_"+p,"");
			String sieve = RequestUtils.getParam(request, "sieve_"+p, "0");
			String ptyp = RequestUtils.getParam(request, "pTyp_"+p, "");
			
			stcMstr.setPktCode(pktNo);
			stcMstr.setParcelNum(RequestUtils.getParam(request, "parcelNum_"+p, ""));
			stcPrpo.setCTS(cts);
			stcPrpo.setAVGCTS(avgcts);
			stcMstr.setPcs(Double.parseDouble(RequestUtils.getParam(request, "pcs_"+p, "0")));
			stcMstr.setRate(Double.parseDouble(RequestUtils.getParam(request, "rate_"+p, "0")));
     		stcMstr.setRootPkt(RequestUtils.getParam(request, "rootpkt_"+p, ""));
     		stcMstr.setBaseRate(Double.parseDouble(RequestUtils.getParam(request, "baseRate_"+p, "0")));
     		Double ppc = 0d;
     		if(stcMstr.getPcs() != null && stcMstr.getPcs() > 0){
     			ppc = stcMstr.getPcs()/cts;
     		}else if(stcPrpo.getAVGCTS() != null){
     			ppc = cts/stcPrpo.getAVGCTS();
     		}
     		
     		stcPrpo.setPPC(ppc);
     		
     		stcPrpo.setShFr_so(Double.parseDouble(shFr));
			stcPrpo.setShTo_so(Double.parseDouble(shTo));
			stcPrpo.setPuFr_so(Double.parseDouble(puFr));
			stcPrpo.setPuTo_so(Double.parseDouble(puTo));
			stcPrpo.setcFr_so(Double.parseDouble(cFr));
			stcPrpo.setcTo_so(Double.parseDouble(cTo));
			stcPrpo.setSIEVE_so(Double.parseDouble(sieve));
			if(!StringUtils.isEmpty(ptyp)) stcPrpo.setPTYP_so(Double.parseDouble(ptyp));
			stcPrpo.setShFr(hMap.containsKey("SH_"+shFr)==true?hMap.get("SH_"+shFr):"");
			stcPrpo.setShTo(hMap.containsKey("SH_"+shTo)==true?hMap.get("SH_"+shTo):"");
			stcPrpo.setPuFr(hMap.containsKey("PU_"+puFr)==true?hMap.get("PU_"+puFr):"");
			stcPrpo.setPuTo(hMap.containsKey("PU_"+puTo)==true?hMap.get("PU_"+puTo):"");
			stcPrpo.setcFr(hMap.containsKey("COL_"+cFr)==true?hMap.get("COL_"+cFr):"");
			stcPrpo.setcTo(hMap.containsKey("COL_"+cTo)==true?hMap.get("COL_"+cTo):"");
			stcPrpo.setSIEVE(hMap.containsKey("SIEVE_"+cTo)==true?hMap.get("SIEVE_"+cTo):"");
			stcPrpo.setPTYP(hMap.containsKey("PTYP_"+cTo)==true?hMap.get("PTYP_"+cTo):"");
			
		    stcMstr.setStatus(1l);
		    stcMstr.setTotalCts(cts); 		  
		    
		    stockMasterList.add(stcMstr);
		    stockPrpList.add(stcPrpo);

		}
		
		list.add(stockMasterList);
		list.add(stockPrpList);
		return list;
	}
	

		
		
}
