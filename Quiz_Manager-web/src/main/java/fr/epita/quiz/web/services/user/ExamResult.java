package fr.epita.quiz.web.services.user;

import java.io.IOException;

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
 *This is class to calculate and display the exam result
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/examResult")
public class ExamResult extends Spring_Servlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ExamResult.class);

	@Autowired
	CreateQuestion_DAO repository;

	public ExamResult() {
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] question = request.getParameterValues("question");
		int rightAnswer = 0;
		try {
			for (int i = 0; i < question.length; i++) {
				if (request.getParameter("quesNum[" + i + "]") != null
						&& request.getParameter("option[" + i + "]") != null) {
					int questionId = Integer.parseInt(request.getParameter("quesNum[" + i + "]"));
					String optionMarked = request.getParameter("option[" + i + "]");
					MCQ_Questions questionGet = (MCQ_Questions) repository.getById(questionId);
					if (questionGet != null) {
						int comp = optionMarked.compareTo(questionGet.getCorrectanswer());
						if (comp == 0) {
							rightAnswer++;
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
		int wrongAnswers = ((question.length) - rightAnswer);
		request.getSession().setAttribute("wrongAnswers", wrongAnswers);
		request.getSession().setAttribute("totalQuestions", question.length);
		request.getSession().setAttribute("rightAnswer", rightAnswer);
		LOGGER.info("Redirected to Result Page");
		response.sendRedirect("paperResult.jsp");
	}
}
