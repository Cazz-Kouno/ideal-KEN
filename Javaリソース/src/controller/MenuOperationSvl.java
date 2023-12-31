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

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/MenuOperationSvl")
public class MenuOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuOperationSvl() {
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
		int mode = Integer.parseInt(request.getParameter("mode"));
		Menu menu = null;
		try {
			if (session.getAttribute("adminInfo") == null) {
				rd = request.getRequestDispatcher("../home.jsp");
			} else {
				String menuName = request.getParameter("menuName");
				String detail = request.getParameter("detail");
				int orderFlg = 0;
				int price = 0;
				int typeId = 0;
				int menuId = 0;
				
				try {
					orderFlg = Integer.parseInt(request.getParameter("orderFlg"));
				} catch (NumberFormatException e) {
				}
				try {
					price = Integer.parseInt(request.getParameter("price"));
				} catch (NumberFormatException e) {
				}
				try {
					typeId = Integer.parseInt(request.getParameter("typeId"));
				} catch (NumberFormatException e) {
				}
				try {
					menuId = Integer.parseInt(request.getParameter("menuId"));
				} catch (NumberFormatException e) {
				}

				menu = new Menu();
				menu.setMenuName(menuName);
				menu.setDetail(detail);
				menu.setOrderFlg(orderFlg);
				menu.setPrice(price);
				menu.setTypeId(typeId);
				menu.setMenuId(menuId);
				request.setAttribute("typeId", typeId);
				System.out.print("MO" + new Throwable().getStackTrace()[0].getLineNumber() + ":");

				if (Menu.updateMenu(menu, mode) == 0) {
								throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
				}
				;
				rd = request.getRequestDispatcher("./MenuMaintenanceSvl");
			}

		} catch (IdealException e) {
			request.setAttribute("msg", e.getMsg());
			request.setAttribute("oneMenu", menu);

			switch (mode) {
			case INSERT:
				rd = request.getRequestDispatcher("./MenuInsertSvl");
				break;
			case UPDATE:
				rd = request.getRequestDispatcher("./MenuUpdateSvl");
				break;
			case DELETE:
				rd = request.getRequestDispatcher("./MenuDeleteSvl");
				break;
			default:
				rd = request.getRequestDispatcher("./errpage");

			}
		} finally {
			rd.forward(request, response);
		}
	}

}
