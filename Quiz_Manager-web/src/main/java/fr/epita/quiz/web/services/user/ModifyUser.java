package fr.epita.quiz.web.services.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.TypeOfRoles;
import fr.epita.quiz.datamodel.Users;
import fr.epita.quiz.services.Users_DAO;
import fr.epita.quiz.web.actions.Spring_Servlet;

/**
 * 
 * @author Faisal
 * This is class to modify the user
 *
 */
@Service
@Transactional
@WebServlet(urlPatterns = "/modifyUser")
public class ModifyUser extends Spring_Servlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ModifyUser.class);

	@Autowired
	private Users_DAO repository;

	public ModifyUser() {
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			final Users user = new Users();
			user.setMail(request.getParameter("mail"));
			user.setUser_name(request.getParameter("user_name"));
			user.setPsswd(request.getParameter("psswd"));
			user.setEnable(true);
			user.setUserRoleId(Integer.parseInt(request.getParameter("id")));
			user.setRole(TypeOfRoles.valueOf(request.getParameter("role")));
			try {
				repository.create(user);
				LOGGER.info("User updated Sucessfully");
				response.sendRedirect("usersList.jsp");
			} catch (DataException e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		} else if (request.getParameter("delete") != null) {
			try {
				Users deleteUser = repository.getUsersById(Integer.parseInt(request.getParameter("selection")));
				repository.delete(deleteUser);
				LOGGER.info("User deleted Sucessfully");
				response.sendRedirect("usersList.jsp");
			} catch (DataException e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		} else if (request.getParameter("modify") != null) {

			try {
				Users editUser = repository.getUsersById(Integer.parseInt(request.getParameter("selection")));
				request.getSession().setAttribute("Users", editUser);
				LOGGER.info("Page RedirectedSucessfully");
				response.sendRedirect("updateUser.jsp");
			} catch (NumberFormatException e) {
				LOGGER.error(e);
				e.printStackTrace();
			}
		}
	}
}
