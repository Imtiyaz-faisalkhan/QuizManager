package fr.epita.quiz.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.epita.quiz.datamodel.MCQ_Questions;
import fr.epita.quiz.datamodel.Users;

/**
 * 
 * @author Faisal
 *
 * 
 */
public abstract class Generic_DAO<T> {

	@Inject
	SessionFactory sFactory;

	
	public final void create(T entity) {
		if (!beforeCreate(entity)) {
			return;
		}

		final Session session = sFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
		session.close();
	}

	
	protected boolean beforeCreate(T entity) {
		return entity != null;
	}

	
	public final void delete(T entity) {
		final Session session = sFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}

	
	public final void deleteAll(List<T> entity) {
		final Session session = sFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}

	
	@SuppressWarnings("unchecked")
	public final List<T> getQuizName(T entity) {
		final Session session = sFactory.openSession();
		final Clause_Builder<T> wcb = getWhereClauseBuilder(entity);
		final Query getTypeQuery = session.createQuery(wcb.getQueryString()).setProperties(wcb.getParameters());
		for (final Entry<String, Object> parameterEntry : wcb.getParameters().entrySet()) {
			getTypeQuery.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
		}

		return getTypeQuery.list();
	}

	
	public final List<MCQ_Questions> getQuestions(MCQ_Questions entity) {
		final Session session = sFactory.openSession();
		String hql = "from Questions s where s.quizName = :quizName";
		Query<MCQ_Questions> query = session.createQuery(hql);
		query.setParameter("quizName", entity.getQuizName());
		List<MCQ_Questions> result = query.getResultList();
		return result;
	}

	
	public Collection<T> searchAll(T entity) {
		List<T> list = new ArrayList<>();
		final Session session = sFactory.openSession();
		list = (List<T>) session.createQuery("from Questions", MCQ_Questions.class).list();
		return list;
	}

	
	public Collection<T> searchUsers(T entity) {
		final Session session = sFactory.openSession();
		return (List<T>) session.createQuery("from Users", Users.class).list();
	}

	
	public Users getUsersById(int id) {
		final Session session = sFactory.openSession();
		return session.get(Users.class, id);
	}

	
	public Users getUsersByUserName(String username) {
		final Session session = sFactory.openSession();
		String hql = "from Users s where s.username = :username";
		Query<Users> query = session.createQuery(hql);
		query.setParameter("username", username);
		List<Users> use = query.getResultList();
		return use.get(0);
	}

	
	public MCQ_Questions getById(int id) {
		final Session session = sFactory.openSession();
		return session.get(MCQ_Questions.class, id);
	}

	
	public final List<T> search(T entity) {
		final Session session = sFactory.openSession();
		final Clause_Builder<T> wcb = getWhereClauseBuilder(entity);
		final Query searchQuery = session.createQuery(wcb.getQueryString());
		for (final Entry<String, Object> parameterEntry : wcb.getParameters().entrySet()) {
			searchQuery.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
		}

		return searchQuery.list();
	}

	
	protected abstract Clause_Builder getWhereClauseBuilder(T entity);

	// Old conception
	// protected abstract String getSearchQuery(T entity);
	//
	// protected abstract void completeQuery(T entity, Query toBeCompleted);

}
