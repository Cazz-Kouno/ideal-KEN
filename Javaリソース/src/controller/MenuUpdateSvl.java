package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import model.IdealException;
import model.Menu;
import model.MenuType;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/MenuUpdateSvl")
public class MenuUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuUpdateSvl() {
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
				int typeId = Integer.parseInt(request.getParameter("typeId"));
				int menuId = Integer.parseInt(request.getParameter("menuId"));
				if (typeId == 100) {
					Course course = Course.getCourse(menuId);
					ArrayList<ArrayList<Menu>> typeMenuList = new ArrayList<>();
					ArrayList<MenuType> menuTypes = MenuType.getAllType();
					for(MenuType mt:menuTypes) {
						if(mt.getTypeId() != 100) {
							typeMenuList.add(Menu.getMenu(mt.getTypeId()));
						}
					}
					request.setAttribute("course", course);
					request.setAttribute("typeMenuList", typeMenuList);
					request.setAttribute("menuTypes", menuTypes);
					request.setAttribute("oneCourse", Course.getOneCourse(menuId));	//クラス完成待ち
					
					rd = request.getRequestDispatcher("../courseUpdate.jsp");

				} else {
					request.setAttribute("mType", MenuType.getAllType());
					request.setAttribute("menuId", menuId); // 追記
					request.setAttribute("typeId", typeId);
					request.setAttribute("oneMenu", Menu.getOneMenu(menuId,typeId));
					System.out.println(1);
					rd = request.getRequestDispatcher("../menuUpdate.jsp");
				}
			}

		} catch (IdealException e) {
			System.out.println("MU:err");
			String msg = e.getMsg();
			request.setAttribute("msg", msg);
			rd = request.getRequestDispatcher("./MenuMaintenanceSvl");
		} finally {
			rd.forward(request, response);
		}
	}

}
