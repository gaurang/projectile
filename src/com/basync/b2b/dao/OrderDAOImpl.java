package com.basync.b2b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.basync.b2b.data.OrderMasterData;
import com.basync.b2b.data.PacketDetails;
import com.basync.b2b.util.ConnectionUtil;

public class OrderDAOImpl {
	private ConnectionUtil dataSource =null;
	protected Logger logger = Logger.getLogger(getClass());
	
	public void setDataSource(ConnectionUtil dataSource) {
		this.dataSource = dataSource;
	}

	public List<OrderMasterData> getOrders(int days,int status){
		Connection conn =dataSource.getConnection();
		ResultSet rs =null;
		PreparedStatement pst =null;
		List<OrderMasterData> orderList = new ArrayList<OrderMasterData>();
		try {
			pst = conn.prepareStatement("SELECT om.ID,om.orderDate, om.status, ut.companyName FROM tb_ordermaster om inner join tb_usertable ut on om.userId= ut.id where DATEDIFF( curdate(),orderDate) < ?  and om.status = ? ");
			pst.setInt(1, days);
			pst.setInt(2, status);
			rs = pst.executeQuery(); 
			while (rs.next()) {
				OrderMasterData  od= new OrderMasterData();
				od.setId(rs.getInt(1));
				od.setOrderDate(rs.getString(2));
				od.setStatus(rs.getInt(3));
				od.setCompanyName(rs.getString(4));
				List<PacketDetails> oList = getOrdersDetails(rs.getInt(1),status);
				if(oList.size() > 0){
					od.setPacketList(oList);
					orderList.add(od);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public List<PacketDetails> getOrdersDetails(int orderId, int status){
		Connection conn =dataSource.getConnection();
		ResultSet rs =null;
		PreparedStatement pst =null;
		List<PacketDetails> orderPktList = new ArrayList<PacketDetails>();
		try {
			pst = conn.prepareStatement("SELECT sm.id, sm.pktCode, sp.cts, od.rate, sp.sh, sp.pu, sp.ct, sp.c, od.status  FROM tb_ordermaster om inner join tb_orderdetail od on om.Id= od.orderid inner join tb_stockprp sp on sp.pktid = od.pktid inner join tb_stockmaster sm on sp.pktId = sm.Id where om.id = ? and od.status = ?");
			pst.setInt(1, orderId);
			pst.setInt(2, status);
			rs = pst.executeQuery(); 
			
			while (rs.next()) {
				PacketDetails  pd= new PacketDetails();
				pd.setPktId(rs.getInt(1));
				pd.setPktCode(rs.getString(2));
				pd.setCts(rs.getDouble(3));
				pd.setRate(rs.getDouble(4));
				pd.setSh(rs.getString(5));
				pd.setPu(rs.getString(6));
				pd.setCt(rs.getString(7));
				pd.setC(rs.getString(8));
				pd.setStatus(rs.getInt(9));

				orderPktList.add(pd);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderPktList;
	}
	public int updateOrderPkts(int pktId, int orderId , int status){
		Connection conn =dataSource.getConnection();
		ResultSet rs =null;
		PreparedStatement pst =null;
		int z=0;
		try {
			int cnt=0;
			if(status==3){
				pst = conn.prepareStatement(" select count(*) from tb_orderdetail where status =? and pktID= ? and orderId=?   ");
				pst.setInt(1, status);
				pst.setInt(2, pktId);
				pst.setInt(3, orderId);
				rs = pst.executeQuery();
				if(rs.next()){
					cnt = rs.getInt(1);
				}
				logger.debug("########################"+cnt);
				
			}
		if(cnt ==0)	{
			pst = conn.prepareStatement(" update tb_orderdetail set status = ? where pktID= ? and orderId=? ");
			pst.setInt(1, status);
			pst.setInt(2, pktId);
			pst.setInt(3, orderId);
			z = pst.executeUpdate(); 
			logger.debug("########################"+pktId	 );
			if(status ==3){
				pst = conn.prepareStatement(" update tb_stockmaster set status = 4 where id = ? ");
				pst.setInt(1, pktId);
				z = pst.executeUpdate(); 
			}
			
		}
		else{
			logger.debug("ALREADY APPROVED  pkt " +pktId +"   and status"+orderId);
			return -1;
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return z;
	}
	public int updateOrder(int orderId , int status){
		Connection conn =dataSource.getConnection();
		ResultSet rs =null;
		PreparedStatement pst =null;
		int z=0;
		try {
			pst = conn.prepareStatement(" update tb_ordermaster set status = ? where  Id=? ");
			pst.setInt(1, status);
			pst.setInt(2, orderId);
			z = pst.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return z;
	}
	public int getPktCode(int pktId){
		Connection conn =dataSource.getConnection();
		ResultSet rs =null;
		PreparedStatement pst =null;
		int z=0;
		try {
			pst = conn.prepareStatement(" select pktCode from tb_stockmaster where id = ? ");
			pst.setInt(1, pktId);
			
			rs = pst.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return z;
	}
	
	

}
