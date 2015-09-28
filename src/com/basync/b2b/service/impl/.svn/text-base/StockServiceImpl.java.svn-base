package com.basync.b2b.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.crm.data.FileMap;
import com.basync.b2b.crm.data.ParcelMaster;
import com.basync.b2b.crm.data.PurchaseDetails;
import com.basync.b2b.crm.data.PurchaseMaster;
import com.basync.b2b.crm.data.PurchaseParcel;
import com.basync.b2b.crm.data.QueryCodeDescription;
import com.basync.b2b.crm.data.rowExtract.PurchaseParcelRowExtract;
import com.basync.b2b.crm.data.rowExtract.QueryCodeDescriptionRowExtract;
import com.basync.b2b.crm.data.rowExtract.SaleReportExtract;
import com.basync.b2b.crm.service.IMemoService;
import com.basync.b2b.crm.service.IPriceService;
import com.basync.b2b.data.OrderMasterData;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.data.PrpData;
import com.basync.b2b.data.QueryDescription;
import com.basync.b2b.data.UserSession;
import com.basync.b2b.data.rowExtract.JQGridRowMapper;
import com.basync.b2b.data.rowExtract.MixStockMinRowMapper;
import com.basync.b2b.data.rowExtract.OrderMasterRowMapper;
import com.basync.b2b.data.rowExtract.OrderPacketsRowMapper;
import com.basync.b2b.data.rowExtract.OrderPktsRowMapper;
import com.basync.b2b.data.rowExtract.PacketDetailsBarCodeRowMapper;
import com.basync.b2b.data.rowExtract.PacketDetailsRowMapper;
import com.basync.b2b.data.rowExtract.PacketMapRowExtract;
import com.basync.b2b.data.rowExtract.PendingPktDetailsRowMapper;
import com.basync.b2b.data.rowExtract.QueryDescriptionExtract;
import com.basync.b2b.dataobjects.PriceMasterPrpDO;
import com.basync.b2b.dataobjects.StockMasterDO;
import com.basync.b2b.dataobjects.StockPRPDO;
import com.basync.b2b.service.IStockService;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JQGridContainer;
import com.basync.b2b.util.PageList;
import com.basync.b2b.util.StringUtils;
import com.basync.crm.webservice.RapUpload;
import com.sun.xml.internal.ws.api.message.Packet;

public class StockServiceImpl extends BaseService implements IStockService {

	private HibernateTemplate hibernateTemplate;
	
	private IPriceService priceService;
	
	private IMemoService memoService;
	
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getPrpStr(java.util.List)
	 */
	public String getPrpStr(List<PrpData> list, Double term) throws Exception {
		StringBuilder prpStr = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			String prp = list.get(i).getPrp();
			if (list.get(i).getPrp().equalsIgnoreCase("Rate")) {
				prp = "s." + list.get(i).getPrp() + " * " + term + " ";
			} else if (list.get(i).getPrp().equalsIgnoreCase("Rap")) {
				prp = "(100 - (" + term + " * (100 + s." + list.get(i).getPrp()
						+ "))) as " + list.get(i).getPrp();
			} else if (list.get(i).getPrp().equalsIgnoreCase("total")) {
				prp = "  (s.rate*" + term + "*sp.cts) total ";
			} else if (list.get(i).getPrp().equalsIgnoreCase("pktCode")) {
				prp = " s.pktCode ";
			} else if (StringUtils.isContainsSpecialChar(prp)) {
				prp = " CONCAT("
						+ StringUtils.toString(
								StringUtils.splitSpecialChar(prp), ",'*',")
						+ ") as "
						+ StringUtils.toString(
								StringUtils.splitSpecialChar(prp), "");
			}

			if (i == 0) {
				prpStr.append(prp);
			} else {
				prpStr.append(", ");
				prpStr.append(prp);
			}
		}
		logger.debug("PRPSTRING ==" + prpStr.toString());
		return prpStr.toString();

	}

	public String getPrpStr(List<PrpData> list, Double term, Double factor)
			throws Exception {
		StringBuilder prpStr = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			String prp = list.get(i).getPrp();
			if (list.get(i).getPrp().equalsIgnoreCase("Rate")) {
				prp = "s." + list.get(i).getPrp() + " * " + term + " ";
				prp += ", s." + list.get(i).getPrp() + " * " + term + " * "
						+ String.valueOf(factor) + " ";
			} else if (list.get(i).getPrp().equalsIgnoreCase("Rap")) {
				prp = "(100 - (" + term + " * (100 + s." + list.get(i).getPrp()
						+ "))) as " + list.get(i).getPrp();
			} else if (list.get(i).getPrp().equalsIgnoreCase("total")) {
				prp = "  (s.rate*" + term + "*sp.cts) total ";
			} else if (list.get(i).getPrp().equalsIgnoreCase("pktCode")) {
				prp = " s.pktCode ";
			} else if (StringUtils.isContainsSpecialChar(prp)) {
				prp = " CONCAT("
						+ StringUtils.toString(
								StringUtils.splitSpecialChar(prp), ",'*',")
						+ ") as "
						+ StringUtils.toString(
								StringUtils.splitSpecialChar(prp), "");
			}
			if (i == 0) {
				prpStr.append(prp);
			} else {
				prpStr.append(", ");
				prpStr.append(prp);
			}
		}
		logger.debug("PRPSTRING ==" + prpStr.toString());
		return prpStr.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getStockData(java.util.List)
	 */
	public JQGridContainer getStockData(Double term, List<PrpData> prpLst,
			String whereClause, int userId, int page, int pageSize,
			String srtIndex, String srtType, String currency, Double factor)
			throws Exception {

		String prpString = "";
		if (!StringUtils.isEmpty(currency))
			prpString = getPrpStr(prpLst, term, factor);
		else
			prpString = getPrpStr(prpLst, term);
		StringBuilder sql = new StringBuilder(
				" select s.id, s.certURL certId, s.status, " + prpString
						+ ", IFNULL(s.pairStock,'-') pairStock ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" left join tb_myfavorites tm on  tm.pktId = s.id and tm.userId = "
						+ userId + "  and tm.removeDateTime is null ")
				.append(" left join tb_partyAcc pa on pa.id = s.partyAccId ")
				.append(" left join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
				.append(" where s.Status in (1, 2, 3) ").append(whereClause);

		StringBuilder countSql = new StringBuilder(" select count(*) ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on ")
				.append(" s.id = sp.pktId  and grpid = 1 where s.Status in (1, 2, 3) ")
				.append(whereClause);
		logger.debug(" clause" + whereClause);
		logger.debug(sql.toString());
		Object[] param = null;
		if (!StringUtils.isEmpty(srtIndex)) {
			sql.append(" order by ").append(srtIndex).append(" ")
					.append(srtType + " ");
		}
		JQGridContainer container = this.getJdbcDao().getGridList(
				sql.toString(), countSql.toString(), page, pageSize, param,
				new RowMapperResultSetExtractor(new JQGridRowMapper()));

		StringBuilder sqlStr = new StringBuilder(
				" select count(*) pktCode, avg(s.rap) RAP, avg(s.rate*" + term
						+ ") RATE, sum(sp.cts) CTS, sum(s.rate*" + term
						+ "*sp.cts) total ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" left join tb_myfavorites tm on  tm.pktId = s.id and tm.userId = "
						+ userId + "  and tm.removeDateTime is null ")
				.append(" where s.Status in (1, 2, 3) ").append(whereClause);
		Map userDate = new HashMap();
		userDate = getTotals(term, null, sqlStr.toString());
		container.setUserdata(userDate);

		// sql.append("LIMIT ").append(Integer.toString(pageSize*(page-1))).append(", ").append(Integer.toString(pageSize*(page-1)+pageSize));
		return container;
	}

	public Map getTotals(Double term, Object[] param, String sql)
			throws Exception {
		return this.getJdbcDao().queryForMap(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#addToFavorite(java.util.List)
	 */
	public int[] addToFavorite(List<String> list, int userID) throws Exception {

		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT);
		String sql = "insert into tb_myfavorites (pktID, PktCode, AddDateTime, UserId, Rate) "
				+ " select ID, pktcode, ?, ?, Rate from tb_stockmaster where Id =? ";
		Object[] param;
		List<Object[]> paramList = new ArrayList<Object[]>();
		String sqlQ = "";
		Object[] o;
		for (int i = 0; i < list.size(); i++) {
			o = new Object[2];
			o[0] = list.get(i);
			o[1] = userID;
			sqlQ = " select count(*) from tb_myfavorites mf where mf.pktId = ? and userId = ? and mf.RemoveDateTime is null ";
			int x = this.getJdbcDao().queryForInt(sqlQ, o);
			if (x <= 0) {
				param = new Object[3];
				param[0] = smf.format(new Date());
				param[1] = userID;
				param[2] = list.get(i);
				paramList.add(param);
			}
		}
		int z[] = this.getJdbcDao().batchUpdate(sql, paramList);
		return z;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#removeFavorite(java.util.List,
	 * int)
	 */
	public int[] removeFavorite(List<String> list, int userID) throws Exception {
		SimpleDateFormat smf = new SimpleDateFormat(Constants.DATE_FORMAT);
		String sql = "update tb_myfavorites set RemoveDateTime = ? "
				+ " where userId=? and PktId =?";
		Object[] param;
		List<Object[]> paramList = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			param = new Object[3];
			param[0] = smf.format(new Date());
			param[1] = userID;
			param[2] = list.get(i);
			paramList.add(param);
		}
		int z[] = this.getJdbcDao().batchUpdate(sql, paramList);
		return z;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getFavorite(int)
	 */
	public JQGridContainer getFavorites(Double term, List<PrpData> prpLst,
			int userID, int page, int pageSize, String srtIndex, String srtType)
			throws Exception {

		StringBuilder sql = new StringBuilder(
				" select s.id, s.certId,s.status,   " + getPrpStr(prpLst, term)
						+ ", (s.rate*" + term + "*sp.cts) total ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" inner join tb_myfavorites mf on s.id = mf.pktId and mf.RemoveDateTime is null and mf.userId =? ")
				.append(" left join tb_partyAcc pa on pa.id = s.partyAccId ")
				.append(" left join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
				.append(" where s.Status in (1, 2, 3) ");

		StringBuilder countSql = new StringBuilder(" select count(*) ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" inner join tb_myfavorites mf on s.id = mf.pktId and mf.RemoveDateTime is null and mf.userId =? ")
				.append("  where s.Status in (1, 2, 3) ");

		Object[] param = new Object[1];
		param[0] = userID;

		if (!StringUtils.isEmpty(srtIndex)) {
			sql.append(" order by ").append(srtIndex).append(" ")
					.append(srtType + " ");
		}

		JQGridContainer container = this.getJdbcDao().getGridList(
				sql.toString(), countSql.toString(), page, pageSize, param,
				new RowMapperResultSetExtractor(new JQGridRowMapper()));

		// sql.append("LIMIT ").append(Integer.toString(pageSize*(page-1))).append(", ").append(Integer.toString(pageSize*(page-1)+pageSize));
		StringBuilder sqlStr = new StringBuilder(
				" select count(*) pktCode, avg(s.rap) RAP, avg(s.rate*" + term
						+ ") RATE, sum(sp.cts) CTS, sum(s.rate*" + term
						+ "*sp.cts) total ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" inner join tb_myfavorites mf on s.id = mf.pktId and mf.RemoveDateTime is null and mf.userId =? ")
				.append(" left join tb_partyAcc pa on pa.id = s.partyAccId ")
				.append(" left join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
				.append("  where s.Status in (1, 2, 3) ");
		Map userDate = new HashMap();
		userDate = getTotals(term, param, sqlStr.toString());
		container.setUserdata(userDate);
		return container;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#getJQGridColModel(java.util.List,
	 * int)
	 */
	public String[] getJQGridColModel(List<PrpData> prpLst, int stage) {

		StringBuilder headers = new StringBuilder();
		StringBuilder colModel = new StringBuilder();
		// colModel.append("{name:'pktCode',index:'pktCode', width:65, formatter:getDGCLink}");
		// headers.append( "'Packet No.' ");

		for (int i = 0; i < prpLst.size(); i++) {
			PrpData pd = prpLst.get(i);
			if (i > 0) {
				colModel.append(", ");
				headers.append(", ");
			}
			headers.append("'").append(pd.getWebhdr() + "'");
			if (pd.getPrp().equalsIgnoreCase("lab")) {
				colModel.append(" {name:'lab',index:'lab', width:"
						+ pd.getWidth() + ", formatter:getCertLink}");
			} else if (pd.getPrp().equalsIgnoreCase("rate")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getRate }");
			} else if (pd.getPrp().equalsIgnoreCase("rap")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getRap}");
			} else if (pd.getPrp().equalsIgnoreCase("pktCode")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getDGCLink}");
			} else if (pd.getPrp().equalsIgnoreCase("total")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getRate}");
			} else {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth() + "}");
				// colModel.append(", {name:'total',index:'total', width:70, formatter:getRate }");
			}
		}
		headers.append(", 'pairStock'");
		colModel.append(", {name:'pairStock',index:'pairStock',  hidden: true  }");
		String[] gridData = new String[2];
		gridData[0] = headers.toString();
		gridData[1] = colModel.toString();

		return gridData;
	}

	public String[] getJQGridColModel(List<PrpData> prpLst, String currency,
			Double factor) {

		StringBuilder headers = new StringBuilder();
		StringBuilder colModel = new StringBuilder();
		// colModel.append("{name:'pktCode',index:'pktCode', width:65, formatter:getCertLink}");
		// headers.append( "'Packet No.' ");

		for (int i = 0; i < prpLst.size(); i++) {
			PrpData pd = prpLst.get(i);
			if (i > 0) {
				colModel.append(", ");
				headers.append(", ");
			}
			headers.append("'").append(pd.getWebhdr() + "'");
			if (pd.getPrp().equalsIgnoreCase("lab")) {
				colModel.append(" {name:'lab',index:'lab', width:"
						+ pd.getWidth() + ", formatter:getCertLink}");
			} else if (pd.getPrp().equalsIgnoreCase("rate")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getRate }");
				headers.append(", '").append("Rate(" + currency + ")'");
				colModel.append(", {name:'rate',index:'rate', width:70,sortable:false, formatter:getRate }");
			} else if (pd.getPrp().equalsIgnoreCase("rap")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getRap}");
			} else if (pd.getPrp().equalsIgnoreCase("pktCode")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getDGCLink}");
			} else if (pd.getPrp().equalsIgnoreCase("total")) {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth()
						+ ", formatter:getRate}");
			} else {
				colModel.append(" {name:'" + pd.getPrp() + "',index:'"
						+ pd.getPrp() + "', width:" + pd.getWidth() + "}");
			}
		}
		// headers.append(", 'Total'");
		// colModel.append(", {name:'total',index:'total', width:70, formatter:getRate }");
		String[] gridData = new String[2];
		gridData[0] = headers.toString();
		gridData[1] = colModel.toString();

		return gridData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getSessionCart(java.util.List,
	 * java.util.List, int, int, int, java.lang.String, java.lang.String)
	 */
	public JQGridContainer getStockByPktNoOrderId(Double term,
			List<PrpData> prpLst, List<String> pktCode, List<String> orderIds,
			int userID, int page, int pageSize, String srtIndex, String srtType)
			throws Exception {
		String whereClause = "";

		if (pktCode.size() > 0) {
			String[] strings = null;
			whereClause += "s.id in ("
					+ StringUtils.toString((String[]) pktCode.toArray(strings),
							",") + ")";
		}
		if (orderIds.size() > 0) {
			String[] strings = null;
			whereClause += "o.orderId in ("
					+ StringUtils.toString(
							(String[]) orderIds.toArray(strings), ",") + ")";
		}

		StringBuilder sql = new StringBuilder(
				" select s.id, s.certId, if(tm.id,'Fav','-') myFav ,  "
						+ getPrpStr(prpLst, term) + ", (s.rate*" + term
						+ "*sp.cts) Total ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" left join tb_myfavorites tm on  tm.pktId = s.id ")
				.append(" left join tb_partyAcc pa on pa.id = s.partyAccId ")
				.append(" left join tb_partyAddMaster pam on pam.id = pa.partyAddId ")
				.append(" where s.Status in (1, 2 , 3) and tm.removeDateTime is null ")
				.append(whereClause);

		StringBuilder countSql = new StringBuilder(" select count(*) ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on  and grpid = 1 ")
				.append(" s.id = sp.pktId where s.Status in (1, 2, 3) ")
				.append(whereClause);

		Object[] param = new Object[1];
		param[0] = userID;

		if (!StringUtils.isEmpty(srtIndex)) {
			sql.append(" order by ").append(srtIndex).append(" ")
					.append(srtType + " ");
		}
		JQGridContainer container = this.getJdbcDao().getGridList(
				sql.toString(), countSql.toString(), page, pageSize, param,
				new RowMapperResultSetExtractor(new JQGridRowMapper()));
		// sql.append("LIMIT ").append(Integer.toString(pageSize*(page-1))).append(", ").append(Integer.toString(pageSize*(page-1)+pageSize));

		StringBuilder sqlStr = new StringBuilder(
				" select count(*) pktCode, avg(s.rap) RAP, avg(s.rate*" + term
						+ ") RATE, sum(sp.cts) CTS, sum(s.rate*" + term
						+ "*sp.cts) total ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" left join tb_myfavorites tm on  tm.pktId = s.id ")
				.append(" where s.Status in (1, 2, 3) and tm.removeDateTime is null ")
				.append(whereClause);

		HashMap userDate = new HashMap();
		userDate.put("total", getTotals(term, param, sqlStr.toString()) + "");

		container.setUserdata(userDate);
		return container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#insertOrder(com.basync.b2b.data.
	 * OrderMasterData)
	 */
	public int insertOrder(OrderMasterData omd, Double term) throws Exception {
		Object[] params = new Object[6];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql = "insert into tb_ordermaster (userId, orderDate, contactPerson, comments, status, orderType)"
				+ " values (?, ?, ?, ?, ?, ? )";
		params[0] = omd.getUserId();
		params[1] = sdf.format(new Date());
		params[2] = omd.getContactPerson();
		params[3] = omd.getComments();
		params[4] = omd.getStatus();
		params[5] = omd.getOrderType();

		int result = this.getJdbcDao().update(sql, params);

		int orderId = 0;
		sql = "select LAST_INSERT_ID()";
		if (result > 0) {
			orderId = this.getJdbcDao().queryForInt(sql, null);
			sql = "insert into tb_orderdetail (orderId, pktId, baseRate, Rate, issueDateTIme, rap, pcs,cts ) select  ?, ID, baseRate, ? , curDate(),(100 - ("
					+ term
					+ " * (100 + s.rap))) , pcs , cts from tb_stockmaster s inner join tb_stockprp sp on sp.prpid =s.id and sp.grpid =1 where id = ?";
			List<Object[]> paramList = new ArrayList<Object[]>();
			for (int i = 0; i < omd.getPacketList().size(); i++) {
				params = new Object[3];
				params[0] = orderId;
				params[1] = term;
				params[2] = omd.getPacketList().get(i).getPktId();
				logger.debug("++++++++in updating "
						+ omd.getPacketList().get(i).getPktId() + "  "
						+ orderId);
				paramList.add(params);
			}
			int[] res = this.getJdbcDao().batchUpdate(sql, paramList);
			if (res.length == 0)
				return -1;
		}

		return orderId;
	}

	public int insertOrderMemo(OrderMasterData omd, Double term, Integer status, Boolean salesMix)
			throws Exception {
		Object[] params = new Object[18];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatIn = new SimpleDateFormat("dd-MM-yyyy");

		String sql = "insert into tb_ordermaster (userId, orderDate, contactPerson, comments, status, orderType, partyAccId, brokerId, brokrage, refPartyId, memo, termId, dueDate, exRate,shipCharges, accType, lab, Discount)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?)";
		params[0] = omd.getUserId();
		params[1] = sdf.format(!omd.getOrderDate().equals("") ? formatIn
				.parse(omd.getOrderDate()) : new Date());
		params[2] = omd.getContactPerson();
		params[3] = omd.getComments();
		params[4] = omd.getStatus();
		params[5] = omd.getOrderType();
		params[6] = omd.getPartyAccId();
		params[7] = omd.getBrokerId();
		params[8] = omd.getBrokrage();
		params[9] = omd.getRefPartyId();
		params[10] = omd.getMemo();
		params[11] = omd.getTermId();
		params[12] = sdf.format(!omd.getDueDate().equals("") ? formatIn
				.parse(omd.getDueDate()) : new Date());
		params[13] = omd.getExrate();
		params[14] = omd.getShipCharges();
		params[15] = omd.getAccType();
		params[16] = omd.getLab();
		params[17] = omd.getDiscount();
		int result = this.getJdbcDao().update(sql, params);

		List<String> pktIds = new ArrayList<String>();

		int orderId = 0;
		sql = "select max(id) from tb_ordermaster";
		if (result > 0) {
			orderId = this.getJdbcDao().queryForInt(sql, null);
			sql = "insert into tb_orderdetail (orderId, pktId, baseRate, Rate, issueDateTIme,status ,rap, pcs,cts, rejCts) select  ?, ID, baseRate, ? , curDate(), ?, ?,  IFNULL( ?, pcs),IFNULL( ?, cts), (cts -IFNULL(?,cts)) from tb_stockmaster s inner join tb_stockprp sp on sp.pktid =s.id and sp.grpid =1 where s.id = ? and s.status =1 ";
			List<Object[]> paramList = new ArrayList<Object[]>();
			for (int i = 0; i < omd.getPacketList().size(); i++) {
				PacketDetails pd = omd.getPacketList().get(i);
				params = new Object[8];
				params[0] = orderId;
				params[1] = pd.getRate();
				params[2] = omd.getStatus();
				params[3] = pd.getRap();
				params[4] = pd.getPcs();
				params[5] = pd.getCts();
				params[6] = pd.getCts();
				params[7] = pd.getPktId();

				pktIds.add(String
						.valueOf(omd.getPacketList().get(i).getPktId()));// for
																			// updating
				logger.debug("++++++++in updating "
						+ omd.getPacketList().get(i).getPktId() + "  "
						+ orderId);
				paramList.add(params);
			}
			int[] res = this.getJdbcDao().batchUpdate(sql, paramList);
			// if(res.length == 0)
			// return -1;
		}
		if(salesMix){
			updateStoneStatusParcel(omd.getPacketList(), status);
		}else{
			updateStoneStatus(omd.getPacketList(), status);
		}
		
		return orderId;
	}

	/*
	 * To be used for single pkts (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#updateStoneStatus(java.util.List,
	 * int)
	 */
	public int updateStoneStatus(List<PacketDetails> pkts, int status)
			throws Exception {
		Object[] params = new Object[4];

		String sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid= s.id  and grpid = 1 set s.status = ?  where Id = ?  ";
		List<Object[]> paramList = new ArrayList<Object[]>();
		for (int i = 0; i < pkts.size(); i++) {
			PacketDetails pd = pkts.get(i);
			params = new Object[2];
			params[0] = status;
			params[1] = pd.getPktId();
			paramList.add(params);
		}
		int[] res = this.getJdbcDao().batchUpdate(sql, paramList);
		return res.length;
		/*
		 * Object[] param = new Object[1]; param[0] =status; return
		 * this.getJdbcDao().update(sql, param);
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getOrderMasterDetail(int, int)
	 */
	public OrderMasterData getOrderMasterDetail(int userId, int orderId)
			throws Exception {
		String sql = "select userName,  DATE_FORMAT( o.OrderDate, '%Y:%m:%d')OrderDate, contactPerson, comments, status , orderType, TotalAmount,tax, discount,expences, finalAmount, termId, brokerId, partyAccId, o.exRate, pa.accType from tb_ordermaster o where Id= ? and userId =?";
		Object[] param = new Object[2];
		param[0] = orderId;
		param[1] = userId;

		return (OrderMasterData) this.getJdbcDao().queryForObject(sql, param,
				new OrderMasterRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getOrderMasterDetail(int)
	 */
	public OrderMasterData getOrderMasterDetail(int orderId) throws Exception {
		StringBuilder sql = new StringBuilder(
				"select o.userId, userName, DATE_FORMAT( o.OrderDate, '%d-%m-%Y') OrderDate, o.contactPerson, o.comments, status , orderType, TotalAmount,tax, discount,expences, finalAmount, pm.companyName, pam.branchCode, bpm.companyName brokerName, o.brokrage, o.termId, o.brokerId, o.partyAccId, o.exRate, o.accType, DATE_FORMAT( o.dueDate, '%d-%m-%Y') DueDate from tb_ordermaster o ")
				.append(" inner join tb_partyAcc pa on  pa.id = o.partyAccId ")
				.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
				.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = o.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
				.append(" where o.Id= ? ");
		Object[] param = new Object[1];
		param[0] = orderId;

		return (OrderMasterData) this.getJdbcDao().queryForObject(
				sql.toString(), param, new OrderMasterRowMapper());
	}

	@SuppressWarnings("unchecked")
	public List<PacketDetails> getPktDetailsByOrderId(int status,
			String orderIdString, int orderPktStatus) throws Exception {

		StringBuilder sql = new StringBuilder(
				" select s.id, s.pktCode, od.rate, if(s.pcs =1, sp.sh, concat(sp.shFr,'-', sp.shTo)) sh, if(s.pcs =1, sp.c, concat(sp.cFr,'-', sp.cTo)) c,if(s.pcs =1,  sp.pu, concat(sp.puFr,'-', sp.puTo)) pu, sp.ct, sp.lab, od.rap, od.pcs, od.cts, od.status,s.remark, s.rapPrice, od.addDisc, od.rejCts ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and grpid = 1 ")
				.append(" join tb_orderdetail od on s.Id = od.pktId ")
				.append(" where  od.orderId in  ")
				.append(" (" + orderIdString + ") ");
		List<Object> params = new ArrayList<Object>();
		if (status > 0) {
			sql.append(" and s.Status = ? ");
			params.add(status);
		}
		if (orderPktStatus > 0) {
			sql.append(" and od.Status = ? ");
			params.add(orderPktStatus);
		}
		// sql.append("LIMIT ").append(Integer.toString(pageSize*(page-1))).append(", ").append(Integer.toString(pageSize*(page-1)+pageSize));
		return this.getJdbcDao().query(sql.toString(), params.toArray(),
				new RowMapperResultSetExtractor(new OrderPktsRowMapper()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#upDateOrderDetails(com.basync.b2b
	 * .data.OrderMasterData)
	 */
	public int upDateOrderDetails(OrderMasterData omd) throws Exception {

		String sql = "update tb_ordermaster set totalAmount= ? , tax = ? , Expences=? ,discount=?, finalAmount =?  where ID = ? ";
		Object[] params = new Object[6];
		params[0] = omd.getTotalAmount();
		params[1] = omd.getTax();
		params[2] = omd.getExpences();
		params[3] = omd.getDiscount();

		Double d = (omd.getTotalAmount()
				+ (omd.getTotalAmount() * omd.getTax()) + omd.getExpences())
				- (omd.getTotalAmount() * omd.getDiscount());
		params[4] = d;
		params[5] = omd.getId();
		return this.getJdbcDao().update(sql, params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getOrderMasterData(int, int,
	 * String java.util.Date, int, int)
	 */
	public PageList getOrderMasterData(int userId, int status, int pktStatus,
			String orderType, Date fromDate, String srtIndex, String srtType,
			int pageNo, int pageSize) throws Exception {

		StringBuilder sql = new StringBuilder(
				"select o.id, DATE_FORMAT( o.OrderDate, '%Y:%m:%d')OrderDate, o.status, od.status pktStatus, od.pktId, od.Rate, sm.pktCode, sp.cts, sp.SH,sp.pu,sp.c,sp.ct from tb_ordermaster o ")
				.append("LEFT JOIN tb_orderdetail od on od.orderID = o.id  ")
				.append("LEFT JOIN tb_stockprp sp on sp.pktId = od.PktId  and grpid = 1 ")
				.append("LEFT JOIN tb_stockmaster sm on sp.pktId = sm.Id ")
				.append("where 1 = 1 ");

		StringBuilder countSql = new StringBuilder(
				"select count(*)  from tb_ordermaster o  ")
				.append("LEFT JOIN tb_orderdetail od on od.orderID = o.id  ")
				.append("LEFT JOIN tb_stockprp sp on sp.pktId = od.PktId  and grpid = 1 ")
				.append("LEFT JOIN tb_stockmaster sm on sp.pktId = sm.Id ")
				.append("where 1 = 1 ");
		List<Object> param = new ArrayList<Object>();

		if (userId > -1) {
			sql.append(" and o.userId = ? ");
			countSql.append(" and o.userId = ? ");
			param.add(userId);
		}
		if (status != -1) {
			sql.append(" and o.status = ? ");
			countSql.append(" and o.status = ? ");
			param.add(status);
		}
		if (!StringUtils.isEmpty(orderType)) {
			sql.append(" and o.orderType = ? ");
			countSql.append(" and o.orderType = ? ");
			param.add(orderType);
		}
		if (fromDate != null) {
			sql.append(" and o.orderDate >= ? ");
			countSql.append(" and o.orderDate >= ? ");
			param.add(fromDate);
		}
		if (pktStatus != -1) {
			sql.append(" and od.status = ? ");
			countSql.append(" and od.status = ? ");
			param.add(pktStatus);
		}

		sql.append("order by " + srtIndex + " " + srtType);
		return this.getJdbcDao().getPageList(sql.toString(),
				countSql.toString(), pageNo, pageSize, param.toArray(),
				new RowMapperResultSetExtractor(new OrderPacketsRowMapper()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getOrdersList(int,
	 * java.lang.String, int)
	 */
	public List<QueryDescription> getOrdersList(int userId, String orderType,
			int status, int periodDys) throws Exception {
		StringBuilder sql = new StringBuilder(
				"select id, DATE_FORMAT( o.OrderDate, '%Y:%m:%d') description from tb_ordermaster o where 1=1");
		List<Object> param = new ArrayList<Object>();

		if (userId > -1) {
			sql.append(" and o.userId = ? ");
			param.add(userId);
		}
		if (status > -1) {
			sql.append(" and o.status = ? ");
			param.add(status);
		}
		if (!StringUtils.isEmpty(orderType)) {
			sql.append(" and o.orderType = ? ");
			param.add(orderType);
		}
		if (periodDys > -1) {
			sql.append(" and DATEDIFF(curdate(), o.orderDate) < ? ");
			param.add(periodDys);
		}

		sql.append(" order by orderDate desc ");
		return this.getJdbcDao().query(sql.toString(), param.toArray(),
				new RowMapperResultSetExtractor(new QueryDescriptionExtract()));

	}

	public Map<String, Object> findStockByPktCode(String pktCode,
			List<String> columnNames) throws Exception {
		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		// session.beginTransaction();

		// List list
		// =hibernateTemplate.find(" from StockMasterDO as sm inner join sm.stockPRPDOs as sp where sp.GRPID =1 and sm.pktCode = ? ",pktCode);
		// return (StockMasterDO)list.get(0);
		Field[] fields = StockPRPDO.class.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for (Field f : fields) {
			fieldList.add(f.getName());
		}

		String sqlColnames = "pktCode";
		for (String string : columnNames) {
			if (!StringUtils.isEmpty(string) && string != "pktCode") {
				if (string.equals("MD-XD-D")) {
					sqlColnames += ", concat(MD,'*',XD,'*',D) MDXDD ";
				} else if (string.equalsIgnoreCase("total")) {
					sqlColnames += ",(sm.rate*sp.cts) total";
				} else if (!string.equals("CTS") && !string.equals("certLabId")
						&& !string.equals("certLabUrl")
						&& fieldList.contains(string)) {
					
					if(fieldList.contains(string+ "_so")) {  //For those properties which does not have sort field
						sqlColnames += ", " + string + "_so " + string;
					}

					if(fieldList.contains(string+"_so")){
						sqlColnames += ", " + string + "_so " + string;
					}else{
						sqlColnames += ", " + string + " " + string;
					}

					sqlColnames += ", " + string + " " + string + "_val";
				} else {
					sqlColnames += ", " + string;
				}
			}
		}
		sqlColnames += ", rapPrice, grpId, partyAccId, sm.status   ";
		logger.debug("SIZE OF LIST " + columnNames.size());

		String sql = "select "
				+ sqlColnames
				+ " from tb_stockmaster sm inner join tb_stockprp sp on sm.id = sp.PKTID and sp.GRPID=1 where sm.pktCode = '"
				+ pktCode + "' ";//manage from controller to show status Msg removed in query//"and sm.status in (0, 1)" 

		return this.getJdbcDao().queryForMap(sql, null);
	}
	
	public Map<String, Object> findStockByPktId(Integer pktId, Integer grpId,
			List<String> columnNames) throws Exception {
		Field[] fields = StockPRPDO.class.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for (Field f : fields) {
			fieldList.add(f.getName());
		}

		String sqlColnames = "pktCode";
		for (String string : columnNames) {
			if (!StringUtils.isEmpty(string) && string != "pktCode") {
				if (string.equals("MD-XD-D")) {
					sqlColnames += ", concat(MD,'*',XD,'*',D) MDXDD ";
				} else if (string.equalsIgnoreCase("total")) {
					sqlColnames += ",(sm.rate*sp.cts) total";
				} else if (!string.equals("CTS") && !string.equals("certLabId")
						&& !string.equals("certLabUrl") &&  !string.equals("AVGCTS") 
						&& fieldList.contains(string) && fieldList.contains(string+"_so") ) {
					sqlColnames += ", " + string + "_so " + string;
					sqlColnames += ", " + string + " " + string + "_val";
				} else {
					sqlColnames += ", " + string;
				}
			}
		}
		sqlColnames += ", rapPrice, grpId, partyAccId, sm.status  ";
		logger.debug("SIZE OF LIST " + columnNames.size());

		String sql = "select "
				+ sqlColnames
				+ " from tb_stockmaster sm inner join tb_stockprp sp on sm.id = sp.PKTID and sp.GRPID = "+grpId+" where sm.pktCode = '"
				+ pktId + "' ";//manage from controller to show status Msg removed in query//"and sm.status in (0, 1)"

		return this.getJdbcDao().queryForMap(sql, null);
		
	}
	public List<Map<String, Object>> findStockByPktCodeList(String pktCode,
			List<String> columnNames) throws Exception {
		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		// session.beginTransaction();

		// List list
		// =hibernateTemplate.find(" from StockMasterDO as sm inner join sm.stockPRPDOs as sp where sp.GRPID =1 and sm.pktCode = ? ",pktCode);
		// return (StockMasterDO)list.get(0);
		Field[] fields = StockPRPDO.class.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for (Field f : fields) {
			fieldList.add(f.getName());
		}

		String sqlColnames = "pktCode";
		for (String string : columnNames) {
			if (!StringUtils.isEmpty(string) && string != "pktCode") {
				if (string.equalsIgnoreCase("total")) {
					sqlColnames += ",(sm.rate*sp.cts) total";
				} else if (!string.equalsIgnoreCase("CTS")
						&& fieldList.contains(string)) {
					sqlColnames += ", " + string + "_so " + string;
					sqlColnames += ", " + string + " " + string + "_val";
				} else {
					sqlColnames += ", " + string;
				}
			}
		}
		sqlColnames += ", rapPrice ";
		logger.debug("SIZE OF LIST " + columnNames.size());

		String sql = "select "
				+ sqlColnames
				+ ", grpid from tb_stockmaster sm inner join tb_stockprp sp on sm.id = sp.PKTID where sm.pktCode = '"
				+ pktCode + "' order By grpId";

		return this.getJdbcDao().query(sql, null,
				new RowMapperResultSetExtractor(new PacketMapRowExtract()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#clearStock(java.lang.Integer)
	 */
	public Integer clearStock(Integer status, Integer partyAccId)
			throws Exception {

		String sql = " delete from tb_stockprp where pktid in (select id from tb_stockmaster where status =? and partyAccId =?) ";
		Object[] param = new Object[2];
		param[0] = status;
		param[1] = partyAccId;
		this.getJdbcDao().update(sql, param);

		sql = " delete from tb_stockmaster where status = ? and partyAccId =? ";
		return this.getJdbcDao().update(sql, param);

		// TODO
		// add gl entry for stock inventory remove stock
		// add gl entry for stock tax remove tax
	}

	public int editMemoExRate(OrderMasterData omd) throws Exception {
		// TODO Auto-generated method stub
		Object param[] = new Object[2];
		// SimpleDateFormat smf = new SimpleDateFormat("yy-MM");
		// String invCd = smf.format(new Date())

		String sql = " update tb_ordermaster om set exRate = ? where om.id  = ?  ";

		param[0] = omd.getExrate();
		param[1] = omd.getId();

		int i = this.getJdbcDao().update(sql, param);
		return i;
	}

	public int editMemoType(OrderMasterData omd) throws Exception {
		// TODO Auto-generated method stub
		Object param[] = new Object[2];
		// SimpleDateFormat smf = new SimpleDateFormat("yy-MM");
		// String invCd = smf.format(new Date())

		String sql = " update tb_ordermaster om set orderType = ?, modifiedOn = now() where om.id  = ?  ";

		param[0] = omd.getOrderType();
		param[1] = omd.getId();

		int i = this.getJdbcDao().update(sql, param);

		List<PacketDetails> pktDtls = getPktDetailsByOrderId(
				Constants.STATUS_TYPE_MI, String.valueOf(omd.getId()), 2);
		updateStoneStatus(pktDtls, Constants.STATUS_TYPE_SL);

		return i;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#getPacketDetails(com.basync.b2b.
	 * data.OrderMasterData)
	 */
	public PacketDetails getPacketDetails(int pktId) throws Exception {
		String sql = "select s.pktCode, sp.cts , s.remark, s.comment, sp.sh, sp.pu, sp.c, sp.ct, sp.fnc, sp.fnco, sp.fnci, sp.lab from tb_stockmaster s inner join tb_stockprp sp "
				+ " on s.id = sp.pktId  and grpid = 1 and s.id  = ? ";
		Object param[] = new Object[1];
		param[0] = pktId;

		return (PacketDetails) this.getJdbcDao().queryForObject(sql, param,
				new PacketDetailsRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#deletePkt(java.lang.String)
	 */
	public int deletePkt(String pktCode) throws Exception {
		Object param[] = new Object[1];

		String sql = " delete from tb_stockprp where PKTID = (select id from tb_stockmaster sm where sm.pktCode = ?) ";

		param[0] = pktCode;

		int i = this.getJdbcDao().update(sql, param);

		sql = " delete from tb_stockmaster where pktCode = ?";

		return this.getJdbcDao().update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#approveWebMemo(com.basync.b2b.data
	 * .OrderMasterData)
	 */
	public int approveWebMemo(OrderMasterData omd) throws Exception {
		// TODO Auto-generated method stub
		Object param[] = new Object[2];

		String sql = " update tb_orderdetail od inner join tb_stockmaster s on s.id= od.pktId and s.status = "
				+ Constants.STATUS_TYPE_MA
				+ " set od.status = ? where orderId = ? ";

		param[0] = omd.getStatus();
		param[1] = omd.getId();

		int i = this.getJdbcDao().update(sql, param);
		// Approve Stones
		if (i > 0) {
			sql = " update tb_ordermaster om set status = ?, modifiedOn = now() where om.id  = ?  ";
			param[0] = omd.getStatus();
			param[1] = omd.getId();

			i = this.getJdbcDao().update(sql, param);

			List<PacketDetails> pktDtls = getPktDetailsByOrderId(
					Constants.STATUS_TYPE_MA, String.valueOf(omd.getId()), 2);
			updateStoneStatus(pktDtls, Constants.STATUS_TYPE_SL);
		}
		return i;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#approveWebMemo(com.basync.b2b.data
	 * .OrderMasterData)
	 */
	public int editMemoDetails(OrderMasterData omd) throws Exception {
		// TODO Auto-generated method stub
		Object param[] = new Object[3];

		String sql = " update tb_ordermaster om set brokrage = ?, brokerId = ?, modifiedOn = now() where om.id  = ?  ";
		param[0] = omd.getBrokrage();
		param[1] = omd.getBrokerId();
		param[2] = omd.getId();

		int i = this.getJdbcDao().update(sql, param);

		List<PacketDetails> pktDtls = getPktDetailsByOrderId(
				Constants.STATUS_TYPE_MA, String.valueOf(omd.getId()), 2);
		return i;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getPendingStock(int, int, int)
	 */
	public List<PacketDetails> getPendingStock(int status, int grpId,
			int priceNull, int partyAccid, String whereClause) throws Exception {
		String statusStr = " and s.status = ? ";
		if (status == -1) {
			statusStr = " and s.status in (0, 1) ";
		}
		/*
		 * String sql =
		 * " select s.pktCode, sp.cts , s.remark, s.comment, sp.sh, sp.pu, sp.c, sp.ct, sp.fnc, sp.fnco, sp.fnci, sp.lab, (1 - s.rate/rp.price)*-100 rap, s.rate, rp.price rapPrice, s.rootPkt, s.baseRate, sp.grpid, s.status, s.rapnetFlag, s.websiteFlag,s.issueDate, sp.sh_so, sp.pu_so, sp.c_so  from tb_stockmaster s inner join tb_stockprp sp "
		 * + " on s.id = sp.pktId and s.partyAccId =  ? " +statusStr
		 * +whereClause+
		 * " left outer join tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "
		 * + " rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
		 * " and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.lowsize and rp.highsize  "
		 * ;
		 */
		String sql = " select s.pktCode, sp.cts , s.remark, s.comment, sp.sh, sp.pu, sp.c, sp.ct, sp.fnc, sp.fnco, sp.fnci, sp.lab, if(sp.grpId = 1, s.rap, sp.rapLab) as rap, if(sp.grpId = 1, s.rate, sp.rateLab) as rate, rp.price rapPrice, s.rootPkt, s.baseRate, sp.grpid, s.status, s.rapnetFlag, s.websiteFlag,s.issueDate, sp.sh_so, sp.pu_so, sp.c_so,sp.ct,sp.po,sp.sy,sp.flc,sp.fl,sp.t,sp.dp,sp.md,sp.xd,sp.d,sp.lab_so  from tb_stockmaster s inner join tb_stockprp sp "
				+ " on s.id = sp.pktId and s.partyAccId =  ? and s.pcs = 1 "
				+ statusStr
				+ whereClause
				+ " left outer join tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "
				+ " rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "
				+ " and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.lowsize and rp.highsize  ";

		List<Object> param = new ArrayList<Object>();
		param.add(partyAccid);
		if (status != -1) {
			param.add(status);
		}
		if (grpId > 0) {
			sql += " and sp.grpId = ? ";
			param.add(grpId);
		}
		if (priceNull > 0) {
			sql += " and (rate is null or rate = 0 or rate = '')";
		}
		// sql+= whereClause;
		sql += " order by sp.cts desc, s.pktCode, grpId ";
		return this.getJdbcDao().query(
				sql,
				param.toArray(),
				new RowMapperResultSetExtractor(
						new PendingPktDetailsRowMapper()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getSimilarStock(int,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<PacketDetails> getSimilarStock(int grpId, String pktCode,
			double range, int partyAccid, String lab) throws Exception {

		String sql = "  select sp.pu, sp.cts, sp.c, sp.sh from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and grpId = 1 and s.pktCode = ? ";

		Object[] params = new Object[1];
		params[0] = pktCode;
		Map<String, Object> paramMap = this.getJdbcDao().queryForMap(sql,
				params);

		sql = "  select s.pktCode, sp.cts , s.remark, s.comment, sp.sh, sp.pu, sp.c, sp.ct, sp.fnc, sp.fnco, sp.fnci, sp.lab, s.rap, s.rate, s.rapPrice, s.rootPkt, s.baseRate, sp.grpid, s.status, s.rapnetFlag, s.websiteFlag,s.issueDate, sp.sh_so, sp.pu_so, sp.c_so,sp.ct,sp.po,sp.sy,sp.flc,sp.fls,sp.t,sp.dp,sp.md,sp.xd,sp.d from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and "
				+ " sp.cts >=  ? - "
				+ range
				+ " and sp.cts <=  ? +"
				+ range
				+ " and sp.c = ? and sp.pu = ? and sp.sh = ? "
				+ " where s.partyAccId =  ?  and s.status in (1, 2) ";

		List<Object> param = new ArrayList<Object>();
		param.add(Double.parseDouble(paramMap.get("cts").toString()));
		param.add(Double.parseDouble(paramMap.get("cts").toString()));
		param.add(paramMap.get("c"));
		param.add(paramMap.get("pu"));
		param.add(paramMap.get("sh"));
		param.add(partyAccid);

		if (grpId > 0) {
			sql += " and sp.grpId = ? ";
			param.add(grpId);
		}
		if (!StringUtils.isEmpty(lab)) {
			sql += " and sp.lab = ? ";
			param.add(lab);
		}
		sql += " order by sp.cts desc ";
		return this.getJdbcDao().query(sql,param.toArray(),new RowMapperResultSetExtractor(	new PendingPktDetailsRowMapper()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getSimilarPriceStock(int,
	 * java.lang.String, double)
	 */
	public List<PacketDetails> getSimilarPriceStock(int grpId, String pktCode,
			int partyAccid, String lab) throws Exception {

		String sql = "  select sp.pu, sp.sz, sp.c, sp.sh from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and grpId = 1 and s.pktCode = ? ";

		Object[] params = new Object[1];
		params[0] = pktCode;
		Map<String, Object> paramMap = this.getJdbcDao().queryForMap(sql,
				params);

		sql = "  select s.pktCode, sp.cts , s.remark, s.comment, sp.sh, sp.pu, sp.c, sp.ct, sp.fnc, sp.fnco, sp.fnci, sp.lab, s.rap, s.rate, s.rapPrice, s.rootPkt, s.baseRate, sp.grpid, s.status, s.rapnetFlag, s.websiteFlag,s.issueDate, sp.sh_so, sp.pu_so, sp.c_so,sp.ct,sp.po,sp.sy,sp.flc,sp.fls,sp.t,sp.dp,sp.md,sp.xd,sp.d from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId  and "
				+ " sp.sz = ? "
				+ " and sp.c = ? and sp.pu = ? and sp.sh = ?"
				+ " where s.partyAccId =  ? and s.status in (1, 2) ";

		List<Object> param = new ArrayList<Object>();
		param.add(paramMap.get("sz"));
		param.add(paramMap.get("c"));
		param.add(paramMap.get("pu"));
		param.add(paramMap.get("sh"));
		param.add(partyAccid);
		if (grpId > 0) {
			sql += " and sp.grpId = ? ";
			param.add(grpId);
		}
		if (!StringUtils.isEmpty(lab)) {
			sql += " and sp.lab = ? ";
			param.add(lab);
		}
		sql += " order by sp.cts desc ";
		return this.getJdbcDao().query(
				sql,
				param.toArray(),
				new RowMapperResultSetExtractor(
						new PendingPktDetailsRowMapper()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#getSoldPkt(int,
	 * java.lang.String)
	 */
	public List<PacketDetails> getSoldPkt(int grpId, String pktCode,
			int partyAccid, int days, String lab) throws Exception {
		// TODO Auto-generated method stub

		String sqlStr = "  select sp.pu, sp.sz, sp.c, sp.sh from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and grpId = 1 and s.pktCode = ? ";

		Object[] params = new Object[1];
		params[0] = pktCode;
		Map<String, Object> paramMap = this.getJdbcDao().queryForMap(sqlStr,
				params);

		StringBuilder sql = new StringBuilder(
				" select s.id, s.pktCode, odt.rap, s.rate, odt.rate sellRate, om.exRate, s.rootPkt roughPkt, om.id memoNo, sp.cts, sp.sh, sp.c, sp.pu, sp.ct, ROUND(odt.cts* IFNULL(odt.rate,odt.rate),2) totalRate, sm.statusCode, IFNULL(s.baseRate,0) baseRate , pm.companyName, bpm.companyName brokerName, om.brokrage, om.orderDate ")
				.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and s.status in (4,9,10)   ")
				.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
				.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in ('"
						+ Constants.ORDER_TYPE_CONFRIM + "', 'INV' ) ")
				.append(" inner join tb_statusMaster sm on sm.id = s.status ")
				.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
				.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
				.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
				.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  pam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
				.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
				.append(" where sp.sz = ? ")
				.append(" and sp.c = ? and sp.pu = ? and sp.sh = ? ");
		List<Object> param = new ArrayList<Object>();
		param.add(partyAccid);
		param.add(paramMap.get("sz"));
		param.add(paramMap.get("c"));
		param.add(paramMap.get("pu"));
		param.add(paramMap.get("sh"));
		if (grpId > 0) {
			sql.append(" and sp.grpId = ? ");
			param.add(grpId);
		}
		if (days > 0) {
			sql.append(" and om.orderDate >= DATE_SUB(CURDATE(), INTERVAL ? DAY) and om.orderDate <=  CURDATE() ");
			param.add(days);
		}
		if (!StringUtils.isEmpty(lab)) {
			sql.append(" and sp.lab = ? ");
			param.add(lab);
		}
		sql.append(" order by sp.cts desc ");
		return this.getJdbcDao().query(sql.toString(), param.toArray(),
				new RowMapperResultSetExtractor(new SaleReportExtract()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#transferStock(java.util.List)
	 */
	public List<QueryCodeDescription> transferStock(List<PacketDetails> pList,
			UserSession us) throws Exception {
		List<QueryCodeDescription> qdList = new ArrayList<QueryCodeDescription>();
		String pktCode ="";
		
		for (int i = 0; i < pList.size(); i++) {
			boolean flag = true;
			PacketDetails pd = pList.get(i);
			
			// price History
			if(!pktCode.equals(pd.getPktCode())){
				String insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate, ?, now(),? , s.rapPrice "
					+ "from tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id inner join "
					+ " tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "
					+ " rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "
					+ " and sp.CTS between rp.lowsize and rp.highsize  where s.status in (1) and s.Rap is not null and s.pktCode = ? and sp.grpId = 1 ";
				Object[] params = new Object[3];
				params[0] = pd.getRate();
				params[1] = us.getUserId();
				params[2] = pd.getPktCode();
				this.getJdbcDao().update(insertQ, params);
				int z = this.getJdbcDao().update("update tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId set sp.grpid = ifnull(lab_so,0) where s.pktCode = '"+pd.getPktCode()+"' and sp.grpid =1 ",null);
				logger.debug(z+"update count &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			}
			pktCode = pd.getPktCode();
			logger.debug(pktCode+"pktCode count &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			// check validity of stock
			
			if(pd.getGrpId()!= null && pd.getGrpId() == 1 ){
				if (pd.getRate() != null && pd.getRate() > 0) {
					String sql = " select count(*) from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and sp.lab = ? and sp.cts > 0 and sp.pu_so is not null && sp.sh_so is not null and (sp.c is null or (sp.c is not null && sp.c_so is not null)) and pktCode = ? ";
					Object[] params = new Object[2];
					params[0] = pd.getLab();
					params[1] = pd.getPktCode();
	
					int z = this.getJdbcDao().queryForInt(sql, params);
					if (z == 0) {
						flag = false;
					}
	
				} else {
					flag = false;
				}
				if(!flag){
					QueryCodeDescription qcd = new QueryCodeDescription();
					qcd.setId(pd.getPktCode());
					qcd.setDescription("Packet has got invalid details.Please Edit min required details to upload");
					qdList.add(qcd);
				}
			}
			
				/*String sql = " update tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId set sp.grpid = lab_so where pktCode = ? and sp.lab <> ? ";
				Object[] params = new Object[2];
				params[0] = pd.getPktCode();
				params[1] = pd.getLab();
				int z = this.getJdbcDao().update(sql, params);*/

			    
				// update rate
				StringBuilder sql = new StringBuilder(" update tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId set sp.grpid = ?, s.status = 1, ");
				
				List<Object> params = new ArrayList<Object>();
				if(pd.getGrpId()==null){
					int grpId = this.getJdbcDao().queryForInt("select max(grpId)  from tb_stockprp sp inner join tb_stockmaster sm on sm.id = sp.pktId  where pktId = '"+pd.getPktCode()+"'" ,null);
					pd.setGrpId(grpId+1);
				}
				
				params.add(pd.getGrpId());

				if(pd.getGrpId()!= null && pd.getGrpId() == 1 ){
					sql.append("s.rate = ?,s.rap=?, s.rapPrice = sp.rapPriceLab, ");
					params.add(pd.getRate());
					params.add(pd.getRap());
					
				}
				
				sql.append(" sp.rateLab = ?, sp.rapLab = ? where pktCode = ? and sp.lab = ? ");
				params.add(pd.getRate());
				params.add(pd.getRap());
				params.add(pd.getPktCode());
				params.add(pd.getLab());
				
				int z = this.getJdbcDao().update(sql.toString(), params.toArray());

				/*sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id inner join "
						+ " tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "
						+ " rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "
						+ " and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.lowsize and rp.highsize set s.RapPrice  = rp.price , "
						+ "s.Rap = ROUND((((s.Rate*100)/rp.price)-100),2) where s.pktCode  = ? and s.Rate is not null and sp.grpid = 1";
				params = new Object[1];
				params[0] = pd.getPktCode();
				z = this.getJdbcDao().update(sql, params);*/
		}

		return qdList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#transferStockSingle(com.basync.b2b
	 * .data.PacketDetails)
	 */
	public QueryCodeDescription transferStockSingle(PacketDetails pd,
			UserSession us) throws Exception {
		QueryCodeDescription qcd = new QueryCodeDescription();
		boolean flag = true;
		// check validity of stock
		if (pd.getRate() != null && pd.getRate() > 0) {
			String sql = " select count(*) from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and sp.lab = ? and sp.cts > 0 and sp.pu_so is not null && sp.sh_so is not null and (sp.c is null or (sp.c is not null && sp.c_so is not null)) and pktCode = ? ";
			Object[] params = new Object[2];
			params[0] = pd.getLab();
			params[1] = pd.getPktCode();

			int z = this.getJdbcDao().queryForInt(sql, params);
			if (z == 0) {
				flag = false;
			}

		} else {
			flag = false;
		}
		if (flag) {
			String sql = " update tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId set sp.grpid = lab_so where pktCode = ? and sp.lab <> ? ";
			Object[] params = new Object[2];
			params[0] = pd.getPktCode();
			params[1] = pd.getLab();
			int z = this.getJdbcDao().update(sql, params);

			// price History
			String insertQ = "insert into tb_priceHistory(pktId,oldPrice,newPrice,updateDate,userId, rapPrice) select id,rate, ?, now(),? , s.rapPrice "
					+ "from tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id and GRPID = 1 inner join "
					+ " tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "
					+ " rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "
					+ " and sp.CTS between rp.lowsize and rp.highsize  where s.status in (1) and s.Rap is not null and s.pktCode = ? ";
			params = new Object[3];
			params[0] = pd.getRate();
			params[1] = us.getUserId();
			params[2] = pd.getPktCode();
			this.getJdbcDao().update(insertQ, params);
			// update rate
			sql = " update tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId set sp.grpid = 1, s.status = 1, s.rate = ? where pktCode = ? and sp.lab <> ? ";
			params = new Object[3];
			params[0] = pd.getRate();
			params[1] = pd.getPktCode();
			params[2] = pd.getLab();
			z = this.getJdbcDao().update(sql, params);

			sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid = s.id inner join "
					+ " tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "
					+ " rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "
					+ " and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.lowsize and rp.highsize set s.RapPrice  = rp.price , "
					+ "s.Rap = ROUND((((s.Rate*100)/rp.price)-100),2) where s.pktCode  = ? and s.Rate is not null and sp.grpid = 1";
			params = new Object[1];
			params[0] = pd.getPktCode();
			z = this.getJdbcDao().update(sql, params);

		} else {
			qcd.setId(pd.getPktCode());
			qcd.setDescription("packet has got invalid details. that refers to either cts, clarity, shape, color, rate  is not properly filled so no actipn performed .please go to purchase and edit. ");
		}

		return qcd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#transferStockSingle(com.basync.b2b
	 * .data.PacketDetails)
	 */
	public boolean validatePkt(PacketDetails pd) throws Exception {
		boolean flag = true;
		// check validity of stock
		String sql = " select count(*) from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and sp.lab = ? and sp.cts > 0 and sp.pu_so is not null && sp.sh_so is not null and (sp.c is null or (sp.c is not null && sp.c_so is not null)) and pktCode = ? and s.rate is not null && s.rate  > 0";
		Object[] params = new Object[2];
		params[0] = pd.getLab();
		params[1] = pd.getPktCode();

		int z = this.getJdbcDao().queryForInt(sql, params);
		if (z == 0) {
			flag = false;
		}

		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.basync.b2b.service.IStockService#findpartyEmails(com.basync.b2b.data
	 * .PacketDetails, int)
	 */
	public List<String> findpartyEmails(PacketDetails pd, int partyAccid,
			int days) throws Exception {
		// TODO Auto-generated method stub

		String sqlStr = "  select sp.pu, sp.sz, sp.c, sp.sh from tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId and grpId = 1 and s.pktCode = ? ";

		Object[] params = new Object[1];
		params[0] = pd.getPktCode();
		Map<String, Object> paramMap = this.getJdbcDao().queryForMap(sqlStr,
				params);

		StringBuilder sql = new StringBuilder(
				" select distinct(pm.email) email")
				.append(" from tb_stockmaster s inner join tb_stockprp sp  on s.id = sp.pktId and s.status in (4,9,10)   ")
				.append(" left outer join tb_orderdetail odt on s.Id = odt.pktId and odt.status  = 2 ")
				.append(" left outer join tb_ordermaster om on om.Id = odt.orderId and om.status  = 2 and om.orderType  in ('"
						+ Constants.ORDER_TYPE_CONFRIM + "', 'INV' ) ")
				.append(" inner join tb_statusMaster sm on sm.id = s.status ")
				.append(" inner join tb_termmaster tm on  tm.id = om.termId ")
				.append(" inner join tb_partyAcc pa on  pa.id = om.partyAccId ")
				.append(" inner join tb_partyAddMaster  pam on  pam.id = pa.partyAddId ")
				.append(" inner join tb_partyMaster pm on  pm.id = pam.partyId ")
		.append(" left outer join tb_partyAcc bpa on  bpa.id = om.brokerId ")
		.append(" left outer join tb_partyAddMaster  bpam on  bpam.id = bpa.partyAddId ")
		.append(" left outer join tb_partyMaster bpm on  bpm.id = bpam.partyId ")
				.append(" inner join tb_users u on  u.id = om.userId and u.partyAccId = ? ")
				.append(" where sp.sz = ? ")
				.append(" and sp.c = ? and sp.pu = ? and sp.sh = ? ");
		List<Object> param = new ArrayList<Object>();
		param.add(partyAccid);
		param.add(paramMap.get("sz"));
		param.add(paramMap.get("c"));
		param.add(paramMap.get("pu"));
		param.add(paramMap.get("sh"));
		if (pd.getGrpId() > 0) {
			sql.append(" and sp.grpId = ? ");
			param.add(pd.getGrpId());
		}
		if (days > 0) {
			sql.append(" and om.orderDate >= DATE_SUB(CURDATE(), INTERVAL ? DAY) and om.orderDate <=  CURDATE() ");
			param.add(days);
		}
		if (!StringUtils.isEmpty(pd.getLab())) {
			sql.append(" and sp.lab = ? ");
			param.add(pd.getLab());
		}
		sql.append(" order by sp.cts desc ");
		return this.getJdbcDao().queryForList(sql.toString(), param.toArray(),
				String.class);

	}

	public Map<String, Object> findStockForMailPktCode(String pktCode,
			List<FileMap> fileMapList, String lab) throws Exception {

		Field[] fields = StockPRPDO.class.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for (Field f : fields) {
			fieldList.add(f.getName());
		}

		String sqlColnames = "pktCode";
		for (int i = 0; i < fileMapList.size(); i++) {
			String string = fileMapList.get(i).getColumnName();
			if (!StringUtils.isEmpty(string) && string != "pktCode") {
				if (string.equals("MD-XD-D")) {
					sqlColnames += ", concat(MD,'*',XD,'*',D) MDXDD ";
				} else if (string.equalsIgnoreCase("total")) {
					sqlColnames += ",(sm.rate*sp.cts) total";
				} else {
					sqlColnames += ", " + string;
				}
			}
		}
		// sqlColnames += ", rapPrice ";
		logger.debug("SIZE OF LIST " + fileMapList.size());

		String sql = "select "
				+ sqlColnames
				+ " from tb_stockmaster sm inner join tb_stockprp sp on sm.id = sp.PKTID and sp.lab = '"
				+ lab + "' where sm.pktCode = '" + pktCode
				+ "' and sm.status in (0, 1) ";

		return this.getJdbcDao().queryForMap(sql, null);
	}

	public List<String> getColumns(int fileId, int sortId) throws Exception {
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		String sql = "SELECT columnName FROM tb_excelmap e,tb_excelfile ef where e.fileId=ef.id and ef.id = "
				+ fileId + " order by colIndex asc";
		if (fileId == -1) {
			sql = "SELECT columnName FROM tb_excelmap e,tb_excelfile ef where e.fileId=ef.id and ef.sort = "
					+ sortId + " order by colIndex asc";
		}

		list = this.getJdbcDao().queryForList(sql, null, String.class);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.basync.b2b.service.IStockService#uploadRap(java.lang.String,
	 * java.util.List, java.lang.String)
	 */
	public int uploadRap(String pktCode, List<FileMap> fileMapList, String lab)
			throws Exception {
		Map<String, Object> stkDtl = findStockForMailPktCode(pktCode,
				fileMapList, lab);
		StringBuilder html = new StringBuilder();
		for (int i = 0; i < fileMapList.size(); i++) {
			String string = fileMapList.get(i).getExcelColumnName();
			if (i == 0)
				html.append(string);
			else
				html.append("," + string);

		}
		html.append("\n");
		for (int i = 0; i < fileMapList.size(); i++) {
			String string = fileMapList.get(i).getColumnName();
			if (i == 0)
				html.append((stkDtl.get(string) != null ? stkDtl.get(string)
						: ""));
			else
				html.append(", "
						+ (stkDtl.get(string) != null ? stkDtl.get(string) : ""));
		}
		RapUpload r = new RapUpload();
		logger.debug(html.toString());
		String msg = r.uploadRapStock(html.toString());
		logger.debug(msg);
		if (msg.indexOf("success") > 1) {
			String sql = " update tb_stockmaster s set rapnetFlag = 1 where pktCode = ? ";
			Object[] params = new Object[1];
			params[0] = pktCode;
			int z = this.getJdbcDao().update(sql, params);
			return 1;
		} else {
			return 0;
		}

	}

	// kri_28
	public int uploadParcelStock(List<StockMasterDO> stockMasterList,
			List<StockPRPDO> stockPRPList, UserSession userSession,
			PurchaseMaster pm, int fileId) throws Exception {

		for (int i = 0; i < stockMasterList.size(); i++) {
			Integer id;
			// extract stockmasterobject
			StockMasterDO sMasterDO = new StockMasterDO();
			sMasterDO = stockMasterList.get(i);

			StockPRPDO sPrpDO = new StockPRPDO();
			sPrpDO = stockPRPList.get(i);
			// String sqlpkt="select PktCode from tb_stockmaster";
			// Integer.parseInt("sqlpkt");
			String sql = "select id from tb_stockmaster where pktCode = ? ";
			Object[] param = new Object[1];
			param[0] = sMasterDO.getPktCode();
			Integer pktId = this.getJdbcDao().queryForInt(sql, param);
			// if(pktId != null && pktId > 0 && sqlpkt.equals(param[0])){

			if (pktId != null && pktId > 0) {
				updateParcelStock(sMasterDO, sPrpDO,userSession);
			} else {
				insertParcelStock(sMasterDO, sPrpDO, userSession,0);
			}
			insertPurchaseEntry(stockMasterList, stockPRPList, pm, userSession);
			logger.debug("*************************the data is showneda");
		}

		return 0;
	}

	public int insertParcelStock(StockMasterDO sMasterDO, StockPRPDO sPrpDO, UserSession us, Integer fixedFlag)
			throws Exception {
		if(StringUtils.isEmpty(sMasterDO.getPktCode())){
			// Auto generated numbers 
			String sql = "select max(id) from tb_stockmaster ";
			CommonUtil.getInstance();
			
			Integer pktCode = 10000;
			if(CommonUtil.getPropertiesByName("b2b.pktcode.seq")!=null){
				pktCode = Integer.parseInt(CommonUtil.getPropertiesByName("b2b.pktcode.seq"));
			} 
			
			pktCode = (this.getJdbcDao().queryForInt(sql, null))+1;
			sMasterDO.setPktCode(pktCode.toString());
		}
		String sql = "select count(*) from tb_stockmaster where pktCode = ? ";
		Object[] params = new Object[1];
		params[0] = sMasterDO.getPktCode();
		Integer count = this.getJdbcDao().queryForInt(sql, params);
		if(count > 0){
			return -1;
		}
		
		sql = "insert into tb_stockmaster (pktCode ,parcelNum, pcs, rootPkt,totalCts, rate ,baseRate, issueDate, updateBy, partyAccId, status)  values ( ?,?,?,?,?,?,?, now(),?, ?,1 )";

		params = new Object[9];
		params[0] = sMasterDO.getPktCode();
		params[1] = sMasterDO.getParcelNum();
		params[2] = sMasterDO.getPcs();
		params[3] = sMasterDO.getRootPkt();
		params[4] = sMasterDO.getTotalCts();
		params[5] = sMasterDO.getRate();
		params[6] = sMasterDO.getBaseRate();
		params[7] = us.getUserId();
		params[8] = us.getPartyAccId();
		this.getJdbcDao().update(sql, params);

		Integer id = getMaxStockMasterID();

		sPrpDO.setPKTID(id.longValue());
		sql = "insert into tb_stockprp (pktId, grpId,CTS,  shFr, shFr_so, shTo, shTo_so, puFr, puFr_so , puTo, puTo_so ,cFr, cFr_so, cTo, cTo_so )  values (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
		Object[] parm = new Object[15];
		parm[0] = sPrpDO.getPKTID();
		parm[1] = sPrpDO.getGRPID();
		parm[2] = sPrpDO.getCTS();
		parm[3] = sPrpDO.getShFr();
		parm[4] = sPrpDO.getShFr_so();
		parm[5] = sPrpDO.getShTo();
		parm[6] = sPrpDO.getShTo_so();
		parm[7] = sPrpDO.getPuFr();
		parm[8] = sPrpDO.getPuFr_so();
		parm[9] = sPrpDO.getPuTo();
		parm[10] = sPrpDO.getPuTo_so();
		parm[11] = sPrpDO.getcFr();
		parm[12] = sPrpDO.getcFr_so();
		parm[13] = sPrpDO.getcTo();
		parm[14] = sPrpDO.getcTo_so();
		this.getJdbcDao().update(sql, parm);

		
		
		return insertFixedStockPkt(id, 0, fixedFlag);
		/*
		 * this.hibernateTemplate.save(sMasterDO);
		 * sPrpDO.setPKTID(sMasterDO.getID());
		 * this.hibernateTemplate.saveOrUpdate(sPrpDO);
		 */

	}

	public void updateParcelStock(StockMasterDO sMasterDO, StockPRPDO sPrpDO, UserSession us)
			throws Exception {
		//PKT EDIT HISTORY
		this.getPriceService().insertPriceLog(sMasterDO.getRate(), null, -1,sMasterDO.getPktCode(), us.getPartyAccId(), us.getUserId(), Constants.SECTION_PURCHASE_MIX_EDIT);
		// update
		// sql =
		// " update tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId set sp.grpid = 1, s.status = 1,s.Pcs = ?, s.rate = ?,s.baseRate = ? where  sp.pkId= ? ";
		// sql =
		// " update tb_stockprp sp inner join tb_stockmaster s on sp.pktId = s.id set sp.grpid = 1, sp.status = 1,sp.CTS = ?, sp.SH = ?,sp.PU = ?,sp.C= ?, where  pkId= ? ";
		// Update stockmaster
		String sql = " update tb_stockmaster s set rate = ? , rootPkt = ?, pcs = ? , totalCts = ?, baseRate = ?, parcelNum = ?, updateBy = ?, lastUpdateDate = now() where  pktCode = ? ";
		Object[] params = new Object[8];
		params[0] = sMasterDO.getRate();
		params[1] = sMasterDO.getRootPkt();
		params[2] = sMasterDO.getPcs();
		params[3] = sMasterDO.getTotalCts();
		params[4] = sMasterDO.getBaseRate();
		params[5] = sMasterDO.getParcelNum();
		params[6] = sMasterDO.getPktCode();
		params[7] = us.getUserId();
		
		
		// Update stockprp
		sql = " update tb_stockprp sp inner join tb_stockmaster s on s.id = sp.pktId set sp.CTS = ?,  shFr=?, shFr_so=?, shTo=?, shTo_so=?, puFr=?, puFr_so=? , puTo=?, puTo_so=? ,cFr=?, cFr_so=?, cTo=?, cTo_so=?, PTYP = ? , PTYP_so = ?, sieve = ?, sieve_so = ?,avgCts=?,sp.lastUpdateDate =now(), sp.updateBy = ?  where  s.pktCode = ? ";
		Object[] prm = new Object[20];
		prm[0] = sPrpDO.getCTS();
		prm[1] = sPrpDO.getShFr();
		prm[2] = sPrpDO.getShFr_so();
		prm[3] = sPrpDO.getShTo();
		prm[4] = sPrpDO.getShTo_so();
		prm[5] = sPrpDO.getPuFr();
		prm[6] = sPrpDO.getPuFr_so();
		prm[7] = sPrpDO.getPuTo();
		prm[8] = sPrpDO.getPuTo_so();
		prm[9] = sPrpDO.getcFr();
		prm[10] = sPrpDO.getcFr_so();
		prm[11] = sPrpDO.getcTo();
		prm[12] = sPrpDO.getcTo_so();
		prm[13] = sPrpDO.getPTYP();
		prm[14] = sPrpDO.getPTYP_so();
		prm[15] = sPrpDO.getSIEVE();
		prm[16] = sPrpDO.getSIEVE_so();
		prm[17] = sPrpDO.getAVGCTS();
		
		prm[18] = us.getUserId();
		prm[19] = sMasterDO.getPktCode();
		this.getJdbcDao().update(sql, prm);

		/*
		 * sMasterDO.setID(pktId.longValue());
		 * sPrpDO.setPKTID(pktId.longValue());
		 * this.hibernateTemplate.update(sMasterDO);
		 * this.hibernateTemplate.saveOrUpdate(sPrpDO);
		 */

	}

	public void insertPurchaseEntry(List<StockMasterDO> sm,
			List<StockPRPDO> sp, PurchaseMaster pm, UserSession us)
			throws Exception {

		int i = 0;

		/*
		 * String sql =
		 * "insert into tb_purchasemaster (vendorId, comments, userId, billNo, purchaseDate, dueDate, status, amount, tax) "
		 * + "values (?, ?, ?, ?, ?, CURDATE(), ?, ?, ?)";
		 */

		String sql = "insert into tb_purchasemaster (vendorId, comments, billNo,purchaseDate, status) "
				+ "values (?, ?, ?, CURDATE(), ?)";
		/*
		 * Object[] param = new Object[8]; param[0] = pm.getVendorId(); param[1]
		 * = pm.getComments(); param[2] = us.getUserId();//2 param[3] =
		 * pm.getBillNo(); param[4] = pm.getPurchaseDate(); param[5] = 1;
		 * param[6] = pm.getAmount(); param[7] = pm.getTax();
		 */
		Object[] param = new Object[4];
		param[0] = pm.getVendorId();
		param[1] = pm.getComments();
		param[2] = pm.getBillNo();
		param[3] = 1;
		this.getJdbcDao().update(sql, param);

		int purId = 0;
		purId = this.getJdbcDao().queryForInt(
				"select max(id) from tb_purchasemaster", null);

		sql = "insert into tb_purchaseDetails (purchaseId, pktCode, rate,  wt) "
				+ "values (?, ?, ?, ?)";
		StockMasterDO smo;
		StockPRPDO spo;
		List<Object[]> paramList = new ArrayList<Object[]>();
		for (int j = 0; j < sm.size(); j++) {
			smo = new StockMasterDO();
			spo = new StockPRPDO();
			smo = sm.get(j);
			spo = sp.get(j);
			param = new Object[4];
			param[0] = purId;
			param[1] = smo.getPktCode();
			param[2] = smo.getBaseRate() != null ? smo.getBaseRate() : 0;
			param[3] = spo.getCTS();
			paramList.add(param);
		}
		// int arr[] =this.getJdbcDao().batchUpdate(sql, paramList);
		this.getJdbcDao().batchUpdate(sql, paramList);

		// TODO
		// add gl entry for stock inventory
		// this.getMemoService().addSuppTran(type, transNo, vendorId, vendorRef,
		// ref, dsc, date, amount, userId, exRate)
		// add gl entry for stock tax
	}

	public Map<String, Object> findMixPktCode(String pktCode) throws Exception {
		/*
		 * StringBuilder sql=new StringBuilder (
		 * "SELECT stm.pktCode,stm.Pcs,stm.rate,stm.rootPkt,stm.baseRate,sprp.c,sprp.pu FROM tb_stockmaster stm "
		 * )
		 * .append("left outer join tb_stockprp sprp on  sprp.pktid = stm.id ")
		 * .append("where stm.pktCode = ?" );
		 */

		String sql = "SELECT stm.pktcode,stm.parcelNum, sprp.shFr, sprp.shFr_so, sprp.shTo, sprp.shTo_so ,sprp.cts,stm.pcs,stm.rate,stm.rootpkt,sprp.puFr,sprp.puTo,sprp.puFr_so,sprp.puTo_so,sprp.cFr,sprp.cFr_so,sprp.cTo,sprp.cTo_so,stm.baseRate  FROM tb_stockmaster stm left outer join tb_stockprp sprp on  sprp.pktid = stm.id where stm.pktCode = ? and pcs <> 1";

		Object[] param = new Object[1];
		param[0] = pktCode;
		// param[1]=cts;
		Map<String, Object> paramMap = this.getJdbcDao()
				.queryForMap(sql, param);
		return paramMap;
	}

	
	// stockSearchBarCode searching by pktno
	// public List<String> searchBarCodebyPktno(String pktCode) throws Exception
	// {

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IStockService#findMixPktId(java.lang.String)
	 */
	public Map<String, Object> findMixPktId(String pktCode) throws Exception {

		String sql = "SELECT stm.pktcode,stm.parcelNum, sprp.shFr, sprp.shFr_so, sprp.shTo, sprp.shTo_so ,sprp.cts,stm.pcs,stm.rate,stm.rootpkt,sprp.puFr,sprp.puTo,sprp.puFr_so,sprp.puTo_so,sprp.cFr,sprp.cFr_so,sprp.cTo,sprp.cTo_so,stm.baseRate,sprp.PTYP,sprp.AVGCTS  FROM tb_stockmaster stm left outer join tb_stockprp sprp on  sprp.pktid = stm.id where stm.id = ? and pcs <> 1";

		Object[] param = new Object[1];
		param[0] = pktCode;
		// param[1]=cts;
		Map<String, Object> paramMap = this.getJdbcDao()
				.queryForMap(sql, param);
		return paramMap;
	}

	public PacketDetails getBarCodePktPrint(String pktCode) throws Exception {
		String sql = "select s.pktCode, sp.cts , sp.sh, sp.pu, sp.c, sp.ct, sp.lab, sp.grpid,s.askingPriceDisc, s.status,sp.ct,sp.po,sp.sy,sp.flc,sp.fl,sp.fls,sp.t,sp.dp,sp.md,sp.xd,sp.d,s.rap,sp.sd, sp.certLabId,sp.ca,sp.lh,sp.sl,sp.pa,sp.cu,sp.gd,sp.pd,sp.REPORT_COMM,sp.cc,sp.gd_per,sp.ch,sp.li,sp.comment1, sp.comment2, sp.comment3,s.remark,sp.rateLab, s.comment,sp.fnc,sp.fnci,sp.fnco   from tb_stockmaster s inner join tb_stockprp sp "
				+ " on s.id = sp.pktId and grpid = 1 and s.pktCode  = ?";

		// " on s.id = sp.pktId  and grpid = 1 and s.pktCode  = ? ";
		Object param[] = new Object[1];
		param[0] = pktCode;

		return (PacketDetails) this.getJdbcDao().queryForObject(sql, param,
				new PacketDetailsBarCodeRowMapper());
	}
	
	public List<PacketDetails> getPktPrintLab(String pktCode) throws Exception {
		String sql = "select s.pktCode, sp.cts , sp.sh, sp.pu, sp.c, sp.ct, sp.lab, sp.grpid,s.askingPriceDisc, s.status,sp.ct,sp.po,sp.sy,sp.flc,sp.fl,sp.fls,sp.t,sp.dp,sp.md,sp.xd,sp.d,s.rap,sp.sd, sp.certLabId,sp.ca,sp.lh,sp.sl,sp.pa,sp.cu,sp.gd,sp.pd,sp.REPORT_COMM,sp.cc,sp.gd_per,sp.ch,sp.li,sp.comment1, sp.comment2, sp.comment3,s.remark,sp.rateLab, s.comment,sp.fnc,sp.fnci,sp.fnco   from tb_stockmaster s inner join tb_stockprp sp "
				+ " on s.id = sp.pktId and s.pktCode  = ? ";

		// " on s.id = sp.pktId  and grpid = 1 and s.pktCode  = ? ";
		Object param[] = new Object[1];
		param[0] = pktCode;

		return  this.getJdbcDao().query(sql, param,
				new RowMapperResultSetExtractor(new PacketDetailsBarCodeRowMapper()));
	}
	/*
	 * String sql =
	 * " select s.pktCode, sp.cts , s.remark, s.comment, sp.sh, sp.pu, sp.c, sp.ct, sp.fnc, sp.fnco, sp.fnci, sp.lab, (1 - s.rate/rp.price)*-100 rap, s.rate, rp.price rapPrice, s.rootPkt, s.baseRate, sp.grpid, s.status, s.rapnetFlag, s.websiteFlag,s.issueDate, sp.sh_so, sp.pu_so, sp.c_so,sp.ct,sp.po,sp.sy,sp.flc,sp.fls,sp.t,sp.dp,sp.md,sp.xd,sp.d  from tb_stockmaster s inner join tb_stockprp sp "
	 * +
	 * " on s.id = sp.pktId and  left outer join tb_rapPrices rp on  rp.shape = if(sp.SH_so =  10 ,'ROUND','PEAR') and rp.color = if(sp.C > 'M' ,'M',sp.C) and "
	 * + " rp.Clarity =  if(sp.PU = 'FL','IF',sp.PU) "+
	 * " and if(sp.CTS > 5.99 and sp.CTS <10, 5.99,sp.CTS) between rp.lowsize and rp.highsize  "
	 * ;
	 * 
	 * 
	 * 
	 * 
	 * //sql+= whereClause; sql+=" order by sp.cts desc, s.pktCode, grpId ";
	 * return this.getJdbcDao().query(sql,param.toArray(), new
	 * RowMapperResultSetExtractor(new PendingPktDetailsRowMapper()));
	 */

	public int updateParcelStock(List<StockMasterDO> stockMasterList,
			List<StockPRPDO> stockPRPList, UserSession userSession, int fileId)
			throws Exception {

		for (int i = 0; i < stockMasterList.size(); i++) {
			Integer id;
			// extract stockmasterobject
			StockMasterDO sMasterDO = new StockMasterDO();
			sMasterDO = stockMasterList.get(i);

			StockPRPDO sPrpDO = new StockPRPDO();
			sPrpDO = stockPRPList.get(i);
			String sqlpkt = "select PktCode from tb_stockmaster";
			// Integer.parseInt("sqlpkt");

			String sql = "select id from tb_stockmaster where pktCode = ? ";
			Object[] param = new Object[1];
			param[0] = sMasterDO.getPktCode();
			Integer pktId = this.getJdbcDao().queryForInt(sql, param);
			if (pktId != null && pktId > 0) {

				// update
				// sql =
				// " update tb_stockmaster s inner join tb_stockprp sp on s.id = sp.pktId set sp.grpid = 1, s.status = 1,s.Pcs = ?, s.rate = ?,s.baseRate = ? where  sp.pkId= ? ";
				// sql =
				// " update tb_stockprp sp inner join tb_stockmaster s on sp.pktId = s.id set sp.grpid = 1, sp.status = 1,sp.CTS = ?, sp.SH = ?,sp.PU = ?,sp.C= ?, where  pkId= ? ";
				// Update stockmaster
				sql = " update tb_stockmaster s set rate = ? , rootPkt = ?, pcs = ? , totalCts = ?, baseRate = ? ,parcelNum = ? where  pktCode = ? ";
				Object[] params = new Object[7];
				params[0] = sMasterDO.getRate();
				params[1] = sMasterDO.getRootPkt();
				params[2] = sMasterDO.getPcs();
				params[3] = sMasterDO.getTotalCts();
				params[4] = sMasterDO.getBaseRate();
				params[5] = sMasterDO.getParcelNum();
				params[6] = sMasterDO.getPktCode();

				// 1
				this.getJdbcDao().update(sql, params);
				sql = "select max(id) from tb_stockmaster";
				// Integer id = this.getJdbcDao().queryForInt(sql, params);
				id = this.getJdbcDao().queryForInt(sql, null);
				sPrpDO.setPKTID(id.longValue());
				// Update stockprp
				sql = " update tb_stockprp sp set CTS = ?, SH = ?, PU = ? , C= ? where  pktId = ? ";
				Object[] prm = new Object[5];
				prm[0] = sPrpDO.getCTS();
				prm[1] = sPrpDO.getSH();
				prm[2] = sPrpDO.getPU();
				prm[3] = sPrpDO.getC();
				prm[4] = sPrpDO.getPKTID();

				this.getJdbcDao().update(sql, prm);

				/*
				 * sMasterDO.setID(pktId.longValue());
				 * sPrpDO.setPKTID(pktId.longValue());
				 * this.hibernateTemplate.update(sMasterDO);
				 * this.hibernateTemplate.saveOrUpdate(sPrpDO);
				 */
			}

		}
		return 0;
	}

	// kri_7
	public Map<String, Object> getpktCode() throws Exception {
		String sql = "select PktCode from tb_stockmaster";
		return this.getJdbcDao().queryForMap(sql, null);

	}

	// kri_8 update splt
	public int updateSplitData(Integer pktId, double totCts)
			throws Exception {
		Integer id;
		// StockMasterDO stmDo=new StockMasterDO();
		String sql = " update tb_stockprp  set  cts = cts - ? "
				+ "   where pktId = ?  ";
		Object[] param = new Object[2];
		param[0] = totCts;
		param[1] = pktId;
		return this.getJdbcDao().update(sql, param);

	}

	/*public int uploadParcelInsertStock(List<StockMasterDO> stockMasterList,
			List<StockPRPDO> stockPRPList, UserSession userSession,
			PurchaseMaster pm, int fileId) throws Exception {

		for (int i = 0; i < stockMasterList.size(); i++) {
			Integer id;
			// extract stockmasterobject
			StockMasterDO sMasterDO = new StockMasterDO();
			sMasterDO = stockMasterList.get(i);

			StockPRPDO sPrpDO = new StockPRPDO();
			sPrpDO = stockPRPList.get(i);
			// String sqlpkt="select PktCode from tb_stockmaster";
			// Integer.parseInt("sqlpkt");

			String sql = "select id from tb_stockmaster where pktCode = ? ";
			Object[] param = new Object[1];
			param[0] = sMasterDO.getPktCode();
			// Integer pktId = this.getJdbcDao().queryForInt(sql, param);

			sql = "insert into tb_stockmaster (pktCode ,parcelNum, pcs, rootPkt,totalCts, rate ,baseRate)  values ( ?,?,?,?,?,?,? )";

			Object[] params = new Object[7];
			params[0] = sMasterDO.getPktCode();
			params[1] = sMasterDO.getParcelNum();
			params[2] = sMasterDO.getPcs();
			params[3] = sMasterDO.getRootPkt();
			params[4] = sMasterDO.getTotalCts();
			params[5] = sMasterDO.getRate();
			params[6] = sMasterDO.getBaseRate();
			this.getJdbcDao().update(sql, params);

			sql = "select max(id) from tb_stockmaster";
			id = this.getJdbcDao().queryForInt(sql, null);
			sPrpDO.setPKTID(id.longValue());
			sql = "insert into tb_stockprp (pktId, grpId,  SH, PU,CTS,C )  values ( ?,?,?,?,?,? )";
			Object[] parm = new Object[6];
			parm[0] = sPrpDO.getPKTID();
			parm[1] = sPrpDO.getGRPID();
			parm[2] = sPrpDO.getSH();
			parm[3] = sPrpDO.getPU();
			parm[4] = sPrpDO.getCTS();
			parm[5] = sPrpDO.getC();
			this.getJdbcDao().update(sql, parm);

			
			 * this.hibernateTemplate.save(sMasterDO);
			 * sPrpDO.setPKTID(sMasterDO.getID());
			 * this.hibernateTemplate.saveOrUpdate(sPrpDO);
			 
		}
		insertPurchaseEntry(stockMasterList, stockPRPList, pm, userSession);
		logger.debug("*************************the data is showneda");

		return 0;
	}*/

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IStockService#saveParcelMaster(java.util.List)
	 */
	public void saveParcelMaster(List<ParcelMaster> pmList) throws Exception {
		for (int i = 0; i < pmList.size(); i++) {
			ParcelMaster pm = new ParcelMaster();
			pm = pmList.get(i);
			this.hibernateTemplate.saveOrUpdate(pm);
		}
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IStockService#getParcelMaster(int)
	 */
	public ParcelMaster getParcelMaster(String id) throws Exception {
		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		// session.beginTransaction();

		 List list
		 	=hibernateTemplate.find(" from ParcelMaster where code = ? ",id);
		 return (ParcelMaster)list.get(0);
		
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IStockService#getParcelMasterList(String)
	 */
	public List<ParcelMaster> getParcelMasterList(String whereClause) throws Exception {
		 List list
		 	=hibernateTemplate.find(" from ParcelMaster "+whereClause,null);
		 return list;
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IStockService#getParcelMasterQD()
	 */
	public List<QueryCodeDescription> getParcelMasterQD() throws Exception {
		String sql = "select code id, code description from tb_parcelMaster ";
		return this.getJdbcDao().query(sql, null, new RowMapperResultSetExtractor(new QueryCodeDescriptionRowExtract()));
	}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IStockService#updateStoneStatusParcel(java.util.List, int)
	 */
	public int updateStoneStatusParcel(List<PacketDetails> pkts, int status)
			throws Exception {
			Object[] params = new Object[4];

			String sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktid= s.id set sp.cts = sp.cts - ?  where Id = ?  ";
			List<Object[]> paramList = new ArrayList<Object[]>();
			for (int i = 0; i < pkts.size(); i++) {
				PacketDetails pd = pkts.get(i);
				params = new Object[2];
				params[0] = pd.getCts();
				params[1] = pd.getPktId();
				paramList.add(params);
			}
			int[] res = this.getJdbcDao().batchUpdate(sql, paramList);
			return res.length;
			/*
			 * Object[] param = new Object[1]; param[0] =status; return
			 * this.getJdbcDao().update(sql, param);
			 */
		}

	/* (non-Javadoc)
	 * @see com.basync.b2b.service.IStockService#getMixStock(int)
	 */
		public List<PacketDetails> getMixStock(int status, int fixedFlag) throws Exception {
			String sql = "select id, pktCode, s.rate, sp.shFr,sp.shTo,sp.puFr,sp.puTo,sp.cFr,sp.cTo,sp.cts ,sp.avgCts,s.parcelNum, sp.PTYP,sp.sieve from " +
					"tb_stockmaster s inner join tb_stockprp sp on sp.pktId = s.id " +
					"inner join tb_fixedStockPkt f on f.pktId = s.id and f.fixedflag = ? " +
					"where s.status =  ? and pcs <> 1 ";
			if(fixedFlag != 1) {
				sql += " and sp.cts > 0 ";
			}
			Object[] param = new Object[2];
			param[0] = fixedFlag;
			param[1] = status;
			
			return  this.getJdbcDao().query(sql, param,
					new RowMapperResultSetExtractor(new MixStockMinRowMapper()));
		}

		/* (non-Javadoc)
		 * @see com.basync.b2b.service.IStockService#mergePackets(java.util.List, java.lang.Double, java.lang.Integer)
		 */
		public int mergePackets(List<Integer> pktList, Double rate,
				Integer toPktId, String pktCode, UserSession us) throws Exception {
			int z=0;
			Object[] params;
			Integer pktCodeInt= 10000;
			CommonUtil.getInstance();
			if(!StringUtils.isEmpty(CommonUtil.getPropertiesByName("b2b.pktcode.seq"))){
				pktCodeInt = Integer.parseInt(CommonUtil.getPropertiesByName("b2b.pktcode.seq"));
			} 
			if(toPktId == -1){
				//insert new Record
				String sql = "select max(id) from tb_stockmaster ";
				pktCodeInt = (this.getJdbcDao().queryForInt(sql, null))+1;
				if(StringUtils.isEmpty(pktCode)){
					// Auto generated numbers 
					
					
					pktCode = pktCodeInt.toString();
				}
			//	this.getJdbcDao().getJdbcTemplate().execute(sql);
				//master   
				
				//TO DO CREATED PROCEDURE 17-1-2012
				/**
				 * From here 
				 */
				sql = "insert into tb_stockmaster (pktCode ,parcelNum, pcs, totalCts, rate ,baseRate,issueDate,partyAccId, mixType, status)  " +
						"select ?, max(parcelNum),-1,sum(sp.cts),sum(pcs),?,now(),?,'M',1  from tb_stockmaster s inner join tb_stockprp sp on sp.pktId = s.id where pktId in("+StringUtils.toString(pktList, ",")+") and sp.grpId = 1  ";
				params =new Object[3];
				params[0] = pktCode;
				params[1] = rate;
				params[2] = us.getPartyAccId();
				z = this.getJdbcDao().update(sql, params);
				
				pktCodeInt = this.getJdbcDao().queryForInt("SELECT LAST_INSERT_ID()",null);
				//prp
				sql = "insert into tb_stockprp (pktId, grpId,CTS, shFr_so, shTo_so, puFr_so , puTo_so , cFr_so, cTo_so )  " +
				"select ?, 1,sum(sp.cts), min(shFr_so), max(shTo_so)," +
				" min(puFr_so), max(puTo_so)," +
				"  min(cFr_so), max(cTo_so) from " +
				" tb_stockmaster s inner join tb_stockprp sp on sp.pktId = s.id where pktId in("+StringUtils.toString(pktList, ",")+") and grpId = 1  ";
		
				params =new Object[1];
				params[0] = pktCodeInt;
				
				z = this.getJdbcDao().update(sql, params);
				
				sql = "update tb_stockprp s set shFr = (SELECT prpValue FROM tb_prpdetail t where sortId = s.shFr_so and prpId=1 and validFlag =1)," +
						" shTo = (SELECT prpValue FROM tb_prpdetail t where sortId = s.shTo_so and prpId=1 and validFlag =1)," +
						"puFr = (SELECT prpValue FROM tb_prpdetail t where sortId = s.puFr_so and prpId=3 and validFlag =1)," +
						"puTo = (SELECT prpValue FROM tb_prpdetail t where sortId = s.puTo_so and prpId=3 and validFlag =1)," +
						"cFr = (SELECT prpValue FROM tb_prpdetail t where sortId = s.cFr_so and prpId=5 and validFlag =1)," +
						"cTo = (SELECT prpValue FROM tb_prpdetail t where sortId = s.cTo_so and prpId=5 and validFlag =1) where s.pktId = ? and grpId = 1"; 
	
				params =new Object[1];
				params[0] = pktCodeInt;
				
				z = this.getJdbcDao().update(sql, params);
				
				/**
				 * TO here
				 */
			}
			
			//history
			String sql = "insert into tb_pktProcess (processName, status, refPktId, updateDateTime, updatedBy) values ('"+Constants.MIX_TYPE_MERGE+"',1, ?, now(),?) ";
				params = new Object[2];
				params[0] = toPktId>-1?toPktId:pktCodeInt;
				params[1] = us.getUserId();
			z = this.getJdbcDao().update(sql, params);
			
			int processId = this.getJdbcDao().queryForInt("SELECT LAST_INSERT_ID()",null);
				
			sql = "insert into tb_pktProcessHistory (processId,pktId, status, cts, rate, process, updateDateTime, updatedBy, refPktId) select ?,s.id, s.status, sp.cts,s.rate, '"+Constants.MIX_TYPE_MERGE+"', now(),?,? from tb_stockmaster s inner join tb_stockprp sp on sp.pktId = s.id  and s.id = ? and grpId = 1 ";
			List<Object[]> paramList = new ArrayList<Object[]>();
			for (int i = 0; i < pktList.size(); i++) {
				Integer pktId = pktList.get(i);
				params = new Object[4];
				params[0] = processId;
				params[1] = us.getUserId();
				params[2] = pktId;
				params[3] = toPktId>-1?toPktId:pktCodeInt;
				paramList.add(params);
			}
			int[] res = this.getJdbcDao().batchUpdate(sql, paramList);

			
			if(toPktId > -1){
				//update total cts
				sql = "update tb_stockprp sp inner join tb_stockmaster s on s.id = sp.pktId set sp.cts = (select cts from (select IFNULL(sum(cts),0) cts from tb_stockprp where pktId in("+StringUtils.toString(pktList, ",")+") and grpId = 1) as table2)  ,  sp.lastUpdateDate = now(), sp.updateBy = ?, s.rate = ?  where sp.pktId = ? and grpId = 1";
				params = new Object[3];
				params[0] = us.getUserId();
				params[1] = rate;
				params[2] = toPktId;
				paramList.add(params);
				z = this.getJdbcDao().update(sql, params);
			}
			//update pktc to merge with cts = 0
			sql = "update tb_stockmaster s inner join tb_stockprp sp on sp.pktId = s.id and grpid = 1 set sp.cts = 0 ,  sp.lastUpdateDate = now(), sp.updateBy = ?, s.status = '"+Constants.STATUS_TYPE_MERGE+"' where pktId = ? and grpId = 1 ";
			paramList = new ArrayList<Object[]>();
			for (int i = 0; i < pktList.size(); i++) {
					
				Integer pktId = pktList.get(i);
				if(toPktId.equals(pktId)){
					continue;
				}
				params = new Object[2];
				params[0] = us.getUserId();
				params[1] = pktId;
				paramList.add(params);
			}
			res = this.getJdbcDao().batchUpdate(sql, paramList);
			return z;
		}

	//for priceMasterSearch findStockBypktcode 28/12 kri
	public Map<String, Object> findStockByPKTMSTID(String PKTMSTID,
			List<String> columnNames) throws Exception {
		   String sqlColnames = "PKTMSTID";
		for (String string : columnNames) {
			if (!StringUtils.isEmpty(string) && string != "PKTMSTID") {
				
				if (!string.equals("CTS")) {
					sqlColnames += ", " + string + "_so " + string;
					sqlColnames += ", " + string + " " + string + "_val";
				} else {
					sqlColnames += ", " + string;
				}
			}
		}
		sqlColnames += ", rapPrice ";
		logger.debug("SIZE OF LIST " + columnNames.size());

		String sql = "select "
				+ sqlColnames
				+ " from tb_priceMaster  where PKTMSTID = '"
				+ PKTMSTID + "' ";

		return this.getJdbcDao().queryForMap(sql, null);
	}
	
	public void addPriceMasterData(List<PriceMasterPrpDO> priceMstList) throws Exception {
		for (int i = 0; i < priceMstList.size(); i++) {
			this.hibernateTemplate.save(priceMstList.get(i));
		}
	}
	
	public int insertParcelMaster(ParcelMaster pm) throws Exception{
		String Code = pm.getCode();
		String sql = "select max(ID) from tb_parcelMaster";
		Integer id =  this.getJdbcDao().queryForInt(sql, null);
		id += 1;
		if (Code == null || StringUtils.isEmpty(Code)) Code = id.toString();		
		sql = "insert into tb_parcelMaster (code, cts, totalCts, status, deleteflag, PurchaseParcel) values(?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[6];
		params[0] = Code;
		params[1] = pm.getCts();
		params[2] = pm.getTotalCts();
		params[3] = pm.getStatus();
		params[4] = pm.getDeleteFlag();
		params[5] = pm.getPurchaseParcel();
		int i = this.getJdbcDao().update(sql, params);
		if(i > 0) return id;
		else return 0;
	}	
	
	public int insertPurchaseAndGL(PurchaseMaster pm, PurchaseDetails pd, String accountCode, Integer partyAccId, String localCurr, String currency) throws Exception{
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT_MINI);
		Date pDate = sdf.parse(pm.getPurchaseDate());
		SimpleDateFormat sdfSlash = new SimpleDateFormat(Constants.DATE_FORMAT_VIEW_WITH_FRONTSLASH);
		String paymentDate = sdfSlash.format(pDate);
		int glTransNo = 0;
		try {
			glTransNo = this.getMemoService().addPurchaseTrf(Constants.PARTY_TYPE_VENDOR, pm.getVendorId(), accountCode, paymentDate,
					Constants.BIGDECIMAL_ZERO, "", new BigDecimal(pm.getExRate()), partyAccId, pm.getBillNo(), Constants.STATUS_PERFORMED_INSERT, -1, localCurr, currency);
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.debug("Error in inserting GL");
			e.printStackTrace();
			throw new  Exception("ERROR in GL entry");
		}
		
		String sql = "insert into tb_purchasemaster (vendorId, comments, userId, billNo, purchaseDate," +
							"dueDate, status, amount, tax, expenses, paymentTerm,currency,exrate,  glTransNo) " +
							"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[14]; 
		params[0] = pm.getVendorId();
		params[1] = pm.getComments();
		params[2] = pm.getUserId();
		params[3] = pm.getBillNo();
		params[4] = pm.getPurchaseDate();
		params[5] = pm.getDueDate();
		params[6] = pm.getStatus();
		params[7] = pm.getAmount();
		params[8] = pm.getTax();
		params[9] = pm.getExpenses();
		params[10] = pm.getPaymentTerm();
		params[11] = pm.getCurrency();
		params[12] = pm.getExRate();
		params[13] = glTransNo;
		int i = this.getJdbcDao().update(sql, params);
		
		if(i > 0) {
			//get ID from purchaseMaster
			sql = "select max(id) from tb_purchasemaster";
			i = this.getJdbcDao().queryForInt(sql, null);
			
			sql = "insert into tb_purchaseDetails(purchaseId, pktcode, parcelId, rate, status, wt, totalRate,finalRate) values(?, ?, ?, ?, ? , ?, ?, ?)";
			Object[] param = new Object[8];
			param[0] = i;
			param[1] = pd.getPktCode();
			param[2] = pd.getParcelId();
			param[3] = pd.getRate(); //TODO error total going in rate
			param[4] = pd.getStatus();
			param[5] = pd.getWt();
			param[6] = pd.getTotalRate();
			param[7] = pd.getFinalRate();
			return this.getJdbcDao().update(sql, param);
		}		
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getfixedpkts() throws Exception{
		String sql = "Select sm.pktcode from tb_fixedStockPkt f inner join tb_stockmaster sm on sm.id = f.pktId where deleteFlag <> 1 and fixedFlag = 1 ";
		return  this.getJdbcDao().queryForList(sql, null, String.class);
	}	
	
	//Gets the Parcel details of the parcels which have not been distributed
	@SuppressWarnings("unchecked")
	public List<PurchaseParcel> getPurchaseParcel() throws Exception{
		String sql = "select ptm.companyName, pm.billNo, dateformat(pm.purchaseDate) as purchaseDate, pc.totalcts , pc.ID , pd.purchaseId "+
					 "from  tb_parcelMaster pc "+
					 "inner join tb_purchaseDetails pd ON pd.parcelId = pc.ID "+
					 "inner join tb_purchasemaster pm ON pm.ID = pd.purchaseId "+
					 "inner join tb_partyAcc pa ON pa.id = pm.vendorId "+
					 "inner join tb_partyAddMaster pam ON pam.id = pa.partyAddId "+
					 "inner join tb_partyMaster ptm ON ptm.id = pam.partyId "+
					 "where pc.purchaseparcel= 1 and pc.deleteflag = 0 and pc.totalcts>0 " +
					 "order by pm.purchasedate desc";
		return this.getJdbcDao().query(sql.toString(), null,
				new RowMapperResultSetExtractor(new PurchaseParcelRowExtract()));
	}

	public int updateStockMaster(String codes, String cts) throws Exception{
		Object[] params = new Object[3];
		params[0] = cts;
		params[1] = cts;
		params[2] = codes;
		String sql = "update tb_stockmaster sm inner join tb_stockprp sp " +
			  "on sp.pktId = sm.id set sm.totalcts = ifnull(sm.totalcts, 0) + ?, " +
			  "sp.cts = ifnull(sp.cts, 0) + ?  where sm.pcs <> 1 and sm.pktcode = ?";
		return this.getJdbcDao().update(sql, params);		
	}
//insert new table

	public int updatePkts(Integer iParcelID, Double dTotalCts) throws Exception{
		String sql = "update tb_parcelMaster set totalcts = totalcts - ?, status = if(totalcts - ? = 0,1,0) where ID = ?";
		Object[] params = new Object[3];
		params[0] = dTotalCts;
		params[1] = dTotalCts;
		params[2] = iParcelID;
		return this.getJdbcDao().update(sql, params);	
	}
	
	public int insertParcelHistory(Integer iParcelID, String sCts, String sCodes, Integer iPurchaseId) throws Exception {		
		String sql = "insert Into tb_parcelhistory(Code, pktcode, cts, purchaseId) values(?,?,?,?)";
		Object[] params = new Object[4];
		params[0] = iParcelID;
		params[1] = sCodes;
		params[2] = sCts;
		params[3] = iPurchaseId;
		return this.getJdbcDao().update(sql, params);	
	} 

	public int  insertFixedStockPkt(Integer pktId,Integer  deleteFlag, Integer fixedFlag)
			throws Exception{
		String sql = "Insert into tb_fixedStockPkt (pktId, deleteFlag, fixedFlag) values(?, ?, ?)";
		if(pktId == null)pktId =  getMaxStockMasterID();
		Object[] pa = new Object[3];
		pa[0] = pktId; //To change db column name
		pa[1] = 0;
		pa[2] = fixedFlag; 
		return this.getJdbcDao().update(sql, pa);
		
	}
	
	public int getMaxStockMasterID()	throws Exception{	return  this.getJdbcDao().queryForInt("select max(id) from tb_stockmaster", null);}
}	