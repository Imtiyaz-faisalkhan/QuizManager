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

import fr.epita.quiz.datamodel.Users;
import fr.epita.quiz.services.Users_DAO;
import fr.epita.quiz.web.actions.Spring_Servlet;
import fr.epita.quiz.web.services.Question_List;

/**
 * 
 * @author Faisal
 *
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/usersService")
public class UsersService extends Spring_Servlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(Question_List.class);

	@Autowired
	Users_DAO repository;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Users> usersList = (List<Users>) repository.searchUsers(new Users());
			request.getSession().setAttribute("usersList", usersList);
			LOGGER.info("Redirected Sucessfully");
			response.sendRedirect("usersList.jsp");
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
	}
}
