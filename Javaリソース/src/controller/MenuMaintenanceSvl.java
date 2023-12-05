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
import model.MenuType;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/MenuMaintenanceSvl")
public class MenuMaintenanceSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuMaintenanceSvl() {
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
		try {
			if (session.getAttribute("adminInfo") == null) {
				rd = request.getRequestDispatcher("../home.jsp");
			} else {
				int typeId = 100;
				if(request.getParameter("typeId") != null) {
					typeId = Integer.parseInt(request.getParameter("typeId"));
				}
				request.setAttribute("typeId",typeId);
				request.setAttribute("mType",MenuType.getAllType());
				request.setAttribute("menu",Menu.getMenu(typeId));
//				Menu oneMenu = new Menu();
//				request.setAttribute("oneMenu", oneMenu);
				rd = request.getRequestDispatcher("../menuMaintenance.jsp");
			}

		} catch (IdealException e) {
			String msg = e.getMsg();
			request.setAttribute("msg", msg);
			rd = request.getRequestDispatcher("../adminIndex.jsp");
		} finally {
			rd.forward(request, response);
		}
	}

}
