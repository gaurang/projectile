package com.basync.b2b.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.basync.b2b.dao.UserDAOImpl;
import com.basync.b2b.dataobjects.UserControlDO;

public class UserController extends AbstractController {
	
	/*public UserController() {
		setCommandClass(UserDO.class);
		setCommandName("userDO");
	}*/
	
	private UserDAOImpl userDAO =null;
	 
	public void setUserDAO(UserDAOImpl userDaoImpl) {
		this.userDAO = userDaoImpl;
	}
	/*	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		List<UserControlDO> userList = userDaoImpl.getUsers();
		request.setAttribute("userList", userList);
		return super.formBackingObject(request);
	}*/
/*	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		return super.onSubmit(request, response, command, errors);
	}*/

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String mode = request.getParameter("mode");
		ModelAndView modelAndView =null;
		if ("fetch".equals(mode)){
			UserControlDO userControlDO = userDAO.getUser(request.getParameter("userName"));
			modelAndView =new ModelAndView("User","userControlDO",userControlDO);
		}
		else if ("process".equals(mode)){
			UserControlDO userControlDO = getDataFromControls(request);
			userControlDO = userDAO.saveUser(userControlDO);
			modelAndView =new ModelAndView("User","userControlDO",userControlDO);
		}
		else{
			UserControlDO userControlDO= new UserControlDO();
			List<UserControlDO> userList = userDAO.getUsers();
			userList.add(userControlDO);
			modelAndView = new ModelAndView("Users","userList",userList);
		}
		return modelAndView;
	}

	private UserControlDO getDataFromControls(HttpServletRequest request) {
		UserControlDO userControlDO =new UserControlDO();
		userControlDO.setUserName(request.getParameter("userName"));
		userControlDO.setCName(request.getParameter("partyName"));
		userControlDO.setTermId(request.getParameter("termId"));
		userControlDO.setStatus(request.getParameter("status"));
		return userControlDO;
	}
}
