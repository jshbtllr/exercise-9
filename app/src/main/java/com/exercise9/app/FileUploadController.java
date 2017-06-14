package com.exercise9.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import com.exercise9.core.model.Roles;
import com.exercise9.core.model.FileModel;
import com.exercise9.core.service.RoleCrudServiceImpl;
import com.exercise9.core.service.CreateEmployeeFromFileImpl;
import org.apache.log4j.Logger;

public class FileUploadController extends SimpleFormController{
	private CreateEmployeeFromFileImpl createEmployeeFromFile;
	private Logger logger = Logger.getLogger(FileUploadController.class);
	public void setCreateEmployeeFromFile(CreateEmployeeFromFileImpl createEmployeeFromFile) {
		this.createEmployeeFromFile = createEmployeeFromFile;
	}

	public FileUploadController() {
		setCommandClass(FileModel.class);
		setCommandName("fileModel");
	}

	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) {
		logger.info("File Upload showForm()");
		ModelAndView modelAndView = new ModelAndView("uploadform");
		return modelAndView;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) {
		logger.info("File Upload onSubmit()");
		FileModel fileUploaded = (FileModel) command;
		MultipartFile multipartFile = fileUploaded.getFile();
		String message = null;

		if(!multipartFile.isEmpty()) {
			message = createEmployeeFromFile.parseFile(multipartFile).toString();
		} else {
			message = "No File Chosen";
		}

		ModelAndView modelAndView = new ModelAndView("message");
		modelAndView.addObject("message", message);
		modelAndView.addObject("redirectView", "http://localhost:8080/employee");		

		return modelAndView;
	}

}