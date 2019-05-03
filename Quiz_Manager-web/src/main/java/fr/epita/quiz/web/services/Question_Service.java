package fr.epita.quiz.web.services;
/*
 * @author Faisal
 * 
 */
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQ_Questions;
import fr.epita.quiz.services.CreateQuestion_DAO;
import fr.epita.quiz.web.services.messages.QuestionMessage;

@Repository
public class Question_Service {

	@Inject
	private CreateQuestion_DAO question;

	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON_VALUE })
	public List<QuestionMessage> findAllQuestions() {
		final List<MCQ_Questions> questions = question.search(new MCQ_Questions());
		final List<QuestionMessage> messages = new ArrayList<>();
		for (final MCQ_Questions question : questions) {
			final QuestionMessage message = new QuestionMessage();
			message.setTitle(question.getQuestion());
		}
		return messages;
	}

}