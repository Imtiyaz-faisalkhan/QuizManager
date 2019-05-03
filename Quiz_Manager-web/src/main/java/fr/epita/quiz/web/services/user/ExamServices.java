package fr.epita.quiz.web.services.user;

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
import fr.epita.quiz.datamodel.TypeOFQuestions;
import fr.epita.quiz.services.CreateQuestion_DAO;
import fr.epita.quiz.web.actions.Spring_Servlet;

/**
 * 
 * @author Faisal
 *This is class to providing exam service
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/examServices")
public class ExamServices extends Spring_Servlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ExamServices.class);

	@Autowired
	CreateQuestion_DAO repository;

	public ExamServices() {
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("mcq") != null) {
			try {
				final MCQ_Questions question = new MCQ_Questions();
				question.setType(TypeOFQuestions.MCQ);
				List<MCQ_Questions> ques = repository.getQuizName(question);
				request.getSession().setAttribute("ques", ques);
				LOGGER.info("Redirected Sucessfully");
				response.sendRedirect("selectQuizName.jsp");
			} catch (Exception e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		} else if (request.getParameter("quizName") != null) {
			try {
				final MCQ_Questions question = new MCQ_Questions();
				question.setQuizName(request.getParameter("selection"));
				List<MCQ_Questions> ques = repository.getQuestions(question);
				request.getSession().setAttribute("ques", ques);
				LOGGER.info("Redirected Sucessfully");
				response.sendRedirect("populateExam.jsp");
			} catch (Exception e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		}
	}
}
