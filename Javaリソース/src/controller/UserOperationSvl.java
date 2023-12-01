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
import model.Menu;
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
			} else if (mode == INSERT || mode == UPDATE) {
				request_user.setUsrName(request.getParameter("usrName"));
				request_user.setAddress(request.getParameter("address"));
				request_user.setPhone(request.getParameter("phone"));
				request_user.setMail(request.getParameter("mail"));
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
					// TODO: handle exception
					
				}
				break;
			case UPDATE:
				request_user = User.update(request_user);
				session.setAttribute("userInfo", request_user);
				rd = request.getRequestDispatcher("../userInsertCompletion.jsp");
				break;
			case DELETE:
				break;
			}
			//			if (session.getAttribute("adminInfo") == null) {
			//				rd = request.getRequestDispatcher("/ideal/home.jsp");
			//			} else {
			//				String menuName = request.getParameter("menuName");
			//				String detail = request.getParameter("detail");
			//				int orderFlg = 0;
			//				int price = 0;
			//				int typeId = 0;
			//				try {
			//					orderFlg = Integer.parseInt(request.getParameter("orderFlg"));
			//				} catch (NumberFormatException e) {
			//				}
			//				try {
			//					price = Integer.parseInt(request.getParameter("price"));
			//				} catch (NumberFormatException e) {
			//				}
			//				try {
			//					typeId = Integer.parseInt(request.getParameter("mode"));
			//				} catch (NumberFormatException e) {
			//				}
			//				
			//				menu = new Menu();
			//				menu.setMenuName(menuName);
			//				menu.setDetail(detail);
			//				menu.setOrderFlg(orderFlg);
			//				menu.setPrice(price);
			//				menu.setTypeId(typeId);
			//				request.setAttribute("typeId", typeId);
			//				Menu.updateMenu(menu, mode);
			//				rd = request.getRequestDispatcher("/ideal/controller/MenuMaintenanceSvl");
			//			}

		} catch (IdealException e) {
			session.setAttribute("msg", e.getMsg());
			//			session.setAttribute("oneMenu", menu);
			//
			//			switch (mode) {
			//			case INSERT:
			//				rd = request.getRequestDispatcher("/ideal/controller/MenuInsertSvl");
			//				break;
			//			case UPDATE:
			//				rd = request.getRequestDispatcher("/ideal/controller/MenuUpdateSvl");
			//				break;
			//			case DELETE:
			//				rd = request.getRequestDispatcher("/ideal/controller/MenuDeleteSvl");
			//				break;
		} finally {
			rd.forward(request, response);
		}
	}

}
