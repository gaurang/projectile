package com.basync.b2b.dao;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import javax.servlet.ServletException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.basync.b2b.util.CommonUtil;
import com.basync.b2b.util.JSONUtil;


public class FileUploadServlet extends HttpServlet implements Servlet {

    private static final long serialVersionUID = 2740693677625051632L;

    public FileUploadServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request,response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        FileUploadListener listener = new FileUploadListener();
        HttpSession session = request.getSession();
        session.setAttribute("LISTENER", listener);
        upload.setProgressListener(listener);
        List uploadedItems = null;
        FileItem fileItem = null;
        Properties prop = new Properties();
		String filePath="";
		try {
				CommonUtil.getInstance();
				filePath=CommonUtil.getPropertiesByName("b2b.certUpload.path");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
            uploadedItems = upload.parseRequest(request);
            Iterator i = uploadedItems.iterator();
            while (i.hasNext()) {
                fileItem = (FileItem) i.next();
                if (fileItem.isFormField() == false) {
                    if (fileItem.getSize() > 0) {
                        File uploadedFile = null;
                        String myFullFileName = fileItem.getName(), myFileName = "", slashType = (myFullFileName.lastIndexOf("\\") > 0) ? "\\" : "/";
                        int startIndex = myFullFileName.lastIndexOf(slashType);
                        myFileName = myFullFileName.substring(startIndex + 1, myFullFileName.length());
                        uploadedFile = new File(filePath, myFileName);
                        fileItem.write(uploadedFile);
                        String delete_type="POST";
                        String Common_URL="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"";
                        String delete_URL=Common_URL+"/deleteFile.html?fileName="+myFileName;
                        String url = Common_URL+"/getImageThumb.html?path="+filePath+"/"+myFileName;
                        String finaldata="[{"+"\"url"+"\":"+"\""+url+"\","+"\"thumbnail_url"+"\":"+"\""+url+"\","+"\"name"+"\":"+"\""+myFileName+"\","+"\"type"+"\":"+"\""+fileItem.getContentType()+"\","+"\"size"+"\":"+""+fileItem.getSize()+","+"\"delete_url"+"\":"+"\""+delete_URL+"\","+"\"delete_type"+"\":"+"\""+delete_type+"\"}]";
                        response.setContentType("application/json");
                		response.getWriter().write(finaldata);
                		response.getWriter().flush();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}