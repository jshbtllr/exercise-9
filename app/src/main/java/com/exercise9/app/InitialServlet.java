package com.exercise9.app;

import com.exercise9.util.HibernateUtil;
import org.hibernate.SessionFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

public class InitialServlet extends HttpServlet {
	
    public void init() throws ServletException{
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }
}