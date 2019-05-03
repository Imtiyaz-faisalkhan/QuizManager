package fr.epita.quiz.web.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.MCQ_Questions;
import fr.epita.quiz.services.CreateQuestion_DAO;
import fr.epita.quiz.web.actions.Spring_Servlet;

/**
 * 
 * @author Faisal
 * This is class for displaying list of question
 *
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/questionList")
public class Question_List extends Spring_Servlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(Question_List.class);

	@Autowired
	private CreateQuestion_DAO repository;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<MCQ_Questions> questionsList = (List<MCQ_Questions>) repository.searchAll(new MCQ_Questions());
			request.getSession().setAttribute("questionsList", questionsList);
			LOGGER.info("List retrieved Sucessfully");
			response.sendRedirect("questionList.jsp");
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
	}

}
