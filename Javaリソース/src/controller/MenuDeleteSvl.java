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
import model.Reserve;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/MenuDeleteSvl")
public class MenuDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuDeleteSvl() {
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
				rd = request.getRequestDispatcher("/ideal/home.jsp");
			} else {
				int typeId = Integer.parseInt(request.getParameter("typeId"));
				int menuId = Integer.parseInt(request.getParameter("menuId"));
				if (typeId == 100) {
					Reserve.reservCourseChk(menuId);
					ArrayList<ArrayList<Menu>> typeMenuList = new ArrayList<>();
					ArrayList<MenuType> menuTypes = MenuType.getAllType();
					for(MenuType mt:menuTypes) {
						if(mt.getTypeId() != 100) {
							typeMenuList.add(Menu.getMenu(mt.getTypeId()));
						}
					}
					request.setAttribute("typeMenuList", menuTypes);
					request.setAttribute("oneCourse", Course.getOneCourseList(menuId));
					rd = request.getRequestDispatcher("/ideal/courseUpdate.jsp");

				} else {
					CourseCtl.CourseMenuChk(menuId);
					request.setAttribute("oneMenu", Menu.getMenu(menuId));
					
					rd = request.getRequestDispatcher("/ideal/controller/ShowMenuSvl");
				}
			}

		} catch (IdealException e) {
			String msg = e.getMsg();
			request.setAttribute("msg", msg);
			rd = request.getRequestDispatcher("/ideal/controller/MenuMaintenanceSvl");
		} finally {
			rd.forward(request, response);
		}
	}

}
