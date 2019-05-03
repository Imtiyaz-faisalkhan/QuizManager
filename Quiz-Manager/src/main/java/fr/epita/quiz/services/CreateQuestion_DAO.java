package fr.epita.quiz.services;

import java.util.LinkedHashMap;
import java.util.Map;
import fr.epita.quiz.datamodel.MCQ_Questions;

import javax.inject.Inject;
import javax.inject.Named;



/**
 * 
 * @author Faisal
 *
 */
public class CreateQuestion_DAO extends Generic_DAO<MCQ_Questions> {
	@Inject
	@Named("questionQuery")
	String query;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.epita.quiz.services.GenericORMDao#getWhereClauseBuilder(java.lang.Object)
	 */
	@Override
	protected Clause_Builder getWhereClauseBuilder(MCQ_Questions entity) {
		// TODO Auto-generated method stub
		final Clause_Builder<MCQ_Questions> wcb = new Clause_Builder<>();
		wcb.setQueryString(query);
		final Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("type", entity.getType());
		wcb.setParameters(parameters);
		return wcb;
	}

}
