
package fr.epita.quiz.tests;
import org.h2.tools.RunScript;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.hibernate.jdbc.Work;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.MCQ_Questions;
import fr.epita.quiz.datamodel.TypeOFQuestions;

/**
 * @author Faisal
 *         
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/ApplicationContext.xml" })

public class TestCase {

	private static final Logger LOGGER = LogManager.getLogger(TestCase.class);

	//private static final String  = null;

	@Inject
	SessionFactory sf;

	@Test
	public void TestMethod() {
		// given
		Assert.assertNotNull(sf);
		final MCQ_Questions question = new MCQ_Questions();
		question.setQuestion("How to configure Hibernate?");
		question.setType(TypeOFQuestions.MCQ);
		final Session session = sf.openSession();

		// when
		final Transaction tx = session.beginTransaction();
		session.save(question);
		tx.commit();
		session.close();
		// then
		// TODO
		
	}

}
