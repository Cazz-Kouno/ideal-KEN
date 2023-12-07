package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;
import model.User;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/UserOperationSvl")
public class UserOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOperationSvl() {
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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		RequestDispatcher rd = null;
		User session_user = (User) (session.getAttribute("userInfo"));
		User request_user = new User();
		int mode = Integer.parseInt(request.getParameter("mode"));
			if (mode != INSERT && session_user == null) {
				rd = request.getRequestDispatcher("/ideal/home.jsp");
			} else if(mode == INSERT || mode == UPDATE){
				request_user.setUsrName(request.getParameter("usrName"));
//				System.out.println("UO 57");
				request_user.setAddress(request.getParameter("address"));
//				System.out.println("UO 59");
				request_user.setPhone(request.getParameter("phone"));
//				System.out.println("UO 61");
				request_user.setMail(request.getParameter("mail"));
				request_user.setExp(request.getParameter("exp"));

				request_user.setPassword(request.getParameter("password"));

			} else {
				request_user = session_user;
			}
			switch (mode) {
			case INSERT:
				try {
				request_user = User.insert(request_user);
				session.setAttribute("userInfo", request_user);
				rd = request.getRequestDispatcher("../userInsertCompletion.jsp");
				}catch (IdealException e) {
					request.setAttribute("msg", e.getMsg());
					request.setAttribute("user", request_user);
					rd = request.getRequestDispatcher("../userInsert.jsp");

				}
				break;
			case UPDATE:
				try {
//					System.out.println("UO 83");
				request_user.setUsrId(session_user.getUsrId());
				
				request_user = User.update(request_user);
//				System.out.println("UO 86");
				session.setAttribute("userInfo", request_user);
				rd = request.getRequestDispatcher("../userIndex.jsp");
				}catch (IdealException e) {
					request.setAttribute("msg", e.getMsg());
					rd = request.getRequestDispatcher("UserUpdateSvl");

				}
				break;
			case DELETE:
				try {
				User.delete(request_user);
				session = request.getSession(true);
				rd = request.getRequestDispatcher("../home.jsp");
				}catch (IdealException e) {
					request.setAttribute("msg", e.getMsg());
					rd = request.getRequestDispatcher("UserDeleteSvl");

				}

				break;
			}
			rd.forward(request, response);
	}

}
