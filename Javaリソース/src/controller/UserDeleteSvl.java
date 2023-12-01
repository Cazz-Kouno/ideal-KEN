package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class AdminLogoffSvl
 */
@WebServlet("/controller/UserDeleteSvl")
public class UserDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		RequestDispatcher rd = null;
		try {
			User session_user = (User) (session.getAttribute("userInfo"));
			if (session_user == null) {
				request.getSession(true);
				rd = request.getRequestDispatcher("../home.jsp");
			} else {
				rd = request.getRequestDispatcher("../userDelete.jsp");
			}
		} catch (Exception e) {
			rd = request.getRequestDispatcher("../userIndex.jsp");
			e.printStackTrace();
		} finally {
			rd.forward(request, response);
		}

	}

}
