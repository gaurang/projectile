package com.basync.b2b.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.dao.OrderDAOImpl;
import com.basync.b2b.util.RequestUtils;

public class OrderController extends AbstractController implements IExceptionHandler {
	
	/*public UserController() {
		setCommandClass(UserDO.class);
		setCommandName("userDO");
	}*/
	
	private OrderDAOImpl orderDAO =null;
	
	public void setOrderDAO(OrderDAOImpl orderDAO) {
		this.orderDAO = orderDAO;
	}

	
	public ModelAndView Orders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int roleId= RequestUtils.getUserSession(request).getRoleId();
		
		int status = RequestUtils.getParam(request, "status", 1);
		int days = RequestUtils.getParam(request, "days", 7);
		request.setAttribute("orderList",this.orderDAO.getOrders(days, status));
		return new ModelAndView("Orders");
	}
	
	public ModelAndView OrdersEditSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int roleId= RequestUtils.getUserSession(request).getRoleId();
		
		String[] pkts = null;
		int status = RequestUtils.getParam(request, "status", 0);
		Set<Integer> orderset = new HashSet<Integer>();
		List<String> msg = new ArrayList<String>();
		
		if(status ==2 ||status==3){
			if(request.getParameterValues("orderPkts") !=null){
				pkts = request.getParameterValues("orderPkts");
				logger.debug(" ttttttttt  "+pkts.length);
			
				for (int i = 0; i < pkts.length; i++) {
					logger.debug(" $$$$$$$$$$$$$$"+pkts[i]);
					String [] pktOrder = new String[2];
					pktOrder = pkts[i].split("-");
					int orderId = Integer.parseInt(pktOrder[0]);
					int pktNo = Integer.parseInt(pktOrder[1]);
					int z = this.orderDAO.updateOrderPkts(pktNo,orderId, status);
					if(z == -1)
						msg.add("order ID == "+ orderId + " pktCode == "+this.orderDAO.getPktCode(pktNo)+" Already Approved");
					
					//orderset.add(orderId);
				}
			}
			for (Iterator iterator = orderset.iterator(); iterator.hasNext();) {
				Integer orderId = (Integer) iterator.next();
				this.orderDAO.updateOrder(orderId, status);
			}
			request.setAttribute("msg", msg);
			
		}
		return new ModelAndView( new RedirectView("Orders.html"));
	}
	
	public ModelAndView dealException(HttpServletRequest request,
			HttpServletResponse response, Exception ex, String str)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
