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
import model.Coursectl;
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
				rd = request.getRequestDispatcher("../home.jsp");
			} else {
				int typeId = Integer.parseInt(request.getParameter("typeId"));
				int menuId = Integer.parseInt(request.getParameter("menuId"));
				System.out.print("MD" + new Throwable().getStackTrace()[0].getLineNumber() + ":");

				if (typeId == 100) {
					Reserve.reservCourseChk(menuId);
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
					rd = request.getRequestDispatcher("../courseDelete.jsp");

				} else {
					Coursectl.courseMenuChk(menuId);
					request.setAttribute("oneMenu", Menu.getOneMenu(menuId,typeId)); // 修正
					
					rd = request.getRequestDispatcher("../menuDelete.jsp"); // 修正　/ideal/controller/ShowMenuSvl 
				}
			}

		} catch (IdealException e) {
			System.out.print("MD ERR " + new Throwable().getStackTrace()[0].getLineNumber() + ":");

			String msg = e.getMsg();
			request.setAttribute("msg", msg);
			rd = request.getRequestDispatcher("./MenuMaintenanceSvl");
		} finally {
			rd.forward(request, response);
		}
	}

}
