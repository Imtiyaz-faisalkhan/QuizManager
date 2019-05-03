
package fr.epita.quiz.tests;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.MCQ_Questions;
import fr.epita.quiz.datamodel.TypeOFQuestions;
import fr.epita.quiz.datamodel.TypeOfRoles;
import fr.epita.quiz.datamodel.Users;
import fr.epita.quiz.services.CreateQuestion_DAO;
import fr.epita.quiz.services.Users_DAO;

/**
 * @author Faisal
 *
 *         
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestMCQAndQuestion {

	@Inject
	CreateQuestion_DAO questDAO;
	@Inject
	Users_DAO userDAO;
	@Inject
	SessionFactory factory;

	// @Test
	public void testSaveOrUpdateQuestion() {
		final Session session = factory.openSession();
		final Transaction tx = session.beginTransaction();
		final MCQ_Questions question = new MCQ_Questions();
		question.setQuestion("How to configure Hibernate?");
		question.setType(TypeOFQuestions.MCQ);
		question.setCorrectanswer(
				"add dependency and create a bean of session factory with data source properties and hibernate poperties injecting to Spring and propertis of java packages need to be scanned by hibenate ");
		questDAO.create(question);

		questDAO.create(question);
		tx.commit();
		session.close();
	}


	public void testGetQuizType() {
		final MCQ_Questions question = new MCQ_Questions();
		question.setType(TypeOFQuestions.MCQ);
		List<MCQ_Questions> stri = questDAO.getQuizName(question);
		System.out.println(stri.size());
	}

	@Test
	public void testGetByUserName() {
		Users user = new Users();
		user = userDAO.getUsersByUserName("Faisal");
		System.out.println(user.getUser_name());
	}

	
	public void testGetQuestionsByQuizType() {
		final MCQ_Questions question = new MCQ_Questions();
		question.setQuizName("test");
		List<MCQ_Questions> stri = questDAO.getQuestions(question);
		System.out.println(stri.size());
	}

	
	public void testGetAllQuestions() {
		final MCQ_Questions question = new MCQ_Questions();
		List<MCQ_Questions> questions = questDAO.getQuestions(question);
		System.out.println(questions.size());
	}

	
	public void testCreateOrUpdateUser() {
		final Session session = factory.openSession();
		final Transaction tx = session.beginTransaction();
		final Users user = new Users();
		user.setUser_name("tetuser");
		user.setMail("testmail@test.com");
		user.setPsswd("testing123");
		user.setRole(TypeOfRoles.Admin);
		user.setEnable(true);
		userDAO.create(user);
		tx.commit();
		session.close();
	}

	
	public void testGetAllUsers() {
		final Users user = new Users();
		List<Users> users = (List<Users>) userDAO.searchUsers(user);
		System.out.println(users.size());
	}

	
	public void testDeleteAllQuestion() {
		final List<MCQ_Questions> question = new ArrayList<MCQ_Questions>();
		questDAO.deleteAll(question);
	}

	
	public void testDeleteQuestionById() {
		final MCQ_Questions ques = new MCQ_Questions();
		ques.setId(101);
		questDAO.delete(ques);
	}
}
