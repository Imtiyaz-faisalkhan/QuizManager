package fr.epita.quiz.web.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author Faisal
 *
 * This is Servel class
 */
public class Spring_Servlet extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}
