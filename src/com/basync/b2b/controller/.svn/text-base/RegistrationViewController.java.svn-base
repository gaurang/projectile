package com.basync.b2b.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.basync.b2b.dao.RegistrationDAOImpl;
import com.basync.b2b.dataobjects.RegistrationDO;
import com.basync.b2b.dataobjects.RegistrationViewDO;

public class RegistrationViewController extends SimpleFormController{

	public RegistrationViewController() {
		setCommandClass(RegistrationViewDO.class);
		setCommandName("registrationViewDO");
	}
	private RegistrationDAOImpl registrationDAO;
	
	public void setRegistrationDAO(RegistrationDAOImpl registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		super.onSubmit(request, response, command, errors);
		RegistrationDO registrationDO =(RegistrationDO) command;
		registrationDAO.updateUser(registrationDO);
		return null;		
		
	}
		
	protected Object formBackingObject(HttpServletRequest request)
		throws Exception {
		List<RegistrationViewDO> list = registrationDAO.getUserView();
		return new RegistrationDO();
		
	}

}
