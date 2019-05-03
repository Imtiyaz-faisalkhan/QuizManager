package fr.epita.quiz.web.services;

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
import fr.epita.quiz.datamodel.TypeOFQuestions;
import fr.epita.quiz.services.CreateQuestion_DAO;
import fr.epita.quiz.web.actions.Spring_Servlet;

/**
 * 
 * @author Faisal
 * This is class for creating a question
 *
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/questionAction")
public class Question_Action extends Spring_Servlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(Question_Action.class);

	@Autowired
	private CreateQuestion_DAO repository;

	public Question_Action() {
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("mcq") != null) {
			try {
				final MCQ_Questions addQuestion = prepareQuestion(request);
				repository.create(addQuestion);
				LOGGER.info("Question created Sucessfully");
				response.sendRedirect("questionList");
			} catch (Exception e) {
				LOGGER.error(e);
				LOGGER.info("Question creation Failed");
				e.printStackTrace();
			}
		} else if (request.getParameter("open") != null) {
			try {
				final MCQ_Questions addQuestion = prepareOpenQuestion(request);
				repository.create(addQuestion);
				LOGGER.info("Question created Sucessfully");
				response.sendRedirect("questionList");
			} catch (Exception e) {
				LOGGER.error(e);
				LOGGER.info("Question creation Failed");
				e.printStackTrace();
			}

		} else if (request.getParameter("assoc") != null) {
			LOGGER.info("THIS FUNTIONALITY NOT PROVIDED YET");
		}

	}

	
	private MCQ_Questions prepareQuestion(HttpServletRequest request) {
		final MCQ_Questions addQuestion = new MCQ_Questions();
		addQuestion.setQuestion(request.getParameter("question"));
		addQuestion.setAnswer1(request.getParameter("answer1"));
		addQuestion.setAnswer2(request.getParameter("answer2"));
		addQuestion.setAnswer3(request.getParameter("answer3"));
		addQuestion.setAnswer4(request.getParameter("answer4"));
		String correctAnswer = request.getParameter("correctanswer");
		addQuestion.setCorrectanswer(request.getParameter(correctAnswer));
		addQuestion.setQuizName(request.getParameter("quizName"));
		addQuestion.setType(TypeOFQuestions.MCQ);
		return addQuestion;
	}

	
	private MCQ_Questions prepareOpenQuestion(HttpServletRequest request) {
		final MCQ_Questions addQuestion = new MCQ_Questions();
		addQuestion.setQuestion(request.getParameter("question"));
		addQuestion.setQuizName(request.getParameter("quizName"));
		addQuestion.setAnswer1("N/A");
		addQuestion.setAnswer2("N/A");
		addQuestion.setAnswer3("N/A");
		addQuestion.setAnswer4("N/A");
		addQuestion.setType(TypeOFQuestions.OPN);
		return addQuestion;
	}

}
