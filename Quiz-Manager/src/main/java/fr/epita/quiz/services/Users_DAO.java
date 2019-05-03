package fr.epita.quiz.services;


import fr.epita.quiz.datamodel.Users;

/**
 * 
 * @author Faisal
 *
 */
public class Users_DAO extends Generic_DAO<Users> {

	@Override
	protected Clause_Builder getWhereClauseBuilder(Users entity) {
		return null;
	}

}
