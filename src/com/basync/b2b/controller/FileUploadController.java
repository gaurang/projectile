package com.basync.b2b.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.basync.b2b.conf.Constants;
import com.basync.b2b.dao.StockUploadDAOImpl;
import com.basync.b2b.data.FileUploadBean;
import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.RequestUtils;
import com.basync.b2b.util.StringUtils;


public class FileUploadController extends SimpleFormController {

	private StockUploadDAOImpl stockUploadDAO;
	
	public void setStockUploadDAO(StockUploadDAOImpl stockUploadDAO) {
		this.stockUploadDAO = stockUploadDAO;
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	 protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
	            Object command, BindException errors) throws ServletException, IOException {

	         // cast the bean
	        FileUploadBean bean = (FileUploadBean) command;
	        CommonUtil.getInstance();
	        String destinationDir = "";
			try {
				destinationDir = CommonUtil.getPropertiesByName("b2b.upload.path");
			} catch (Exception e) {
				logger.debug("No Dir/property found with name b2b.upload.path ");
				e.printStackTrace();
			}
	         
	        MultipartFile file = bean.getFile();
	        System.out.println("\n loc = "+destinationDir + file.getOriginalFilename());
	        
	        File destination = new File(destinationDir + file.getOriginalFilename());
	        file.transferTo(destination);
	        request.getSession().setAttribute("FILEPATH", destinationDir + file.getOriginalFilename());
	        
	        String pktType = RequestUtils.getParam(request, "pktType", ""); 
	        
	        
	       
	        String path = destination.getAbsolutePath();
	        if (file == null) {
	             // hmm, that's strange, the user did not upload anything
	        }
	         // well, let's do nothing with the bean for now and return
			int fileId = RequestUtils.getParam(request, "fileId1", 0); 
			String type =  stockUploadDAO.getFileType(fileId);
			
			 if(!StringUtils.isEmpty(pktType) && pktType.equals("mixed")) {
				 if(!type.equals(Constants.FILE_TYPE_EXCEL)){
						request.setAttribute("fileType", "CSV");
						request.getRequestDispatcher("uploadStockFileMixedCSV.html?p="+path).forward(request, response);
					}else{
						request.getRequestDispatcher("uploadStockFileMixed.html?p="+path).forward(request, response);
					}
		    }else if(!StringUtils.isEmpty(pktType) && pktType.equals("single")){
			        if(!type.equals(Constants.FILE_TYPE_EXCEL)){
						request.setAttribute("fileType", "CSV");
						request.getRequestDispatcher("uploadStockFileCSV.html?p="+path).forward(request, response);
					}else{
						request.getRequestDispatcher("uploadStockFile.html?p="+path).forward(request, response);
					}
		    }
	        return null;
	    }
	
   

}