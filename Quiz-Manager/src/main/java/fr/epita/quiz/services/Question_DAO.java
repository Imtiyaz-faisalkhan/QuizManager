package fr.epita.quiz.services;
import fr.epita.quiz.datamodel.MCQ_Questions;
import java.util.LinkedHashMap;
import java.util.Map;


import javax.inject.Inject;
import javax.inject.Named;



/**
 * 
 * @author Faisal
 *
 */
public class Question_DAO {

	@Inject
	@Named("questionQuery")
	String query;

	/**
	 * 
	 * @param entity
	 * @return
	 */
	protected Clause_Builder<MCQ_Questions> getWhereClauseBuilder(MCQ_Questions entity) {
		final Clause_Builder<MCQ_Questions> wcb = new Clause_Builder<>();
		wcb.setQueryString(query);

		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("question", entity.getQuestion());
		wcb.setParameters(parameters);
		return wcb;

	}

	

}
