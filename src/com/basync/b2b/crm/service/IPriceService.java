package com.basync.b2b.crm.service;

import java.util.Date;
import java.util.List;

import com.basync.b2b.crm.data.PacketHistory;
import com.basync.b2b.crm.data.RapPriceData;
import com.basync.b2b.data.PacketDetails;

public interface IPriceService {

	/**
	 * 
	 * @param rapPriceList
	 * @return
	 * @throws Exception
	 */
	public int[] insertRapPrices(List<RapPriceData> rapPriceList) throws Exception;
	
	/**
	 * 
	 * @param shape
	 * @param size
	 * @param color
	 * @param clarity
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Double getLocalRap(String shape, Double size,String color,String clarity )
	throws Exception;
	
	/**
	 * 
	 * @param shape
	 * @param size
	 * @param color
	 * @param clarity
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Double getLocalRapHistory(String shape, Double size,String color,String clarity,Date date )
	throws Exception;
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int updateRapStockPricingBulk() throws Exception ;

	/**
	 * 
	 * @param pktID
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updateRapStockPrice(Integer pktID, Integer userId) throws Exception ;
	
	/**
	 * 
	 * @param rate
	 * @param rap
	 * @param pktID
	 * @param partyAccId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updateStockPrice(Double rate, Double rap , Integer pktID,Integer partyAccId, Integer userId) throws Exception ;

	/**
	 * 
	 * @param wherClause
	 * @param rapChange
	 * @param partyAccId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updateStockPriceBulk(String whereClause,Double rapChange, Integer partyAccId, Integer userId) throws Exception ;
	
	/**
	 * 
	 * @param rate
	 * @param rap
	 * @param pktID
	 * @param partyAccId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updateStockPriceHistory(Double rate, Double rap , Integer pktID, Integer userId) throws Exception ;
	/**
	 * 
	 * @param pktcCode
	 * @return
	 * @throws Exception
	 */
	public List<PacketHistory> getPriceHistory(String pktCode,int days) throws Exception ;

	/**
	 * 
	 * @param pktCode
	 * @param days
	 * @return
	 * @throws Exception
	 */
	public List<PacketDetails> getPktMemoHistory(String pktCode, int days) throws Exception ;
		
	public int insertPriceLog(Double rate, Double rap, Integer pktID,String pktCode,
			Integer partyAccId, Integer userId,String section) throws Exception ;
}
