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

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/CourseOperationSvl2")
public class CourseOperationSvl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 21;
	public static final int UPDATE = 22;
	public static final int DELETE = 23;
	public static final int[] COURSE_MENU_TYPE_ID = { 200, 210, 220, 300, 310, 400 };
	public static final String[] COURSE_MENU_TYPE_NAME = { "appetizerId", "soupId", "pastaId", "meatId", "fishId",
			"dessertId" };

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseOperationSvl2() {
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
		System.out.println("CO52");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		RequestDispatcher rd = request.getRequestDispatcher("../ex27.jsp"); //null防止　後でhome.jspにする？

		int mode = 100;
		int typeId = 100;
		System.out.println("CO59");
		System.out.println(request.getParameter("mode"));
		if (request.getParameter("mode") != null) {
			mode = Integer.parseInt(request.getParameter("mode"));
		}
		try {
			if (session.getAttribute("adminInfo") == null) {
				rd = request.getRequestDispatcher("../home.jsp");
			} else {
				Course course = null;
				ArrayList<Coursectl> coursectl = null;
				switch (mode) {
				case INSERT:
					course = new Course();
					course.setCourseName(request.getParameter("courseName"));
					course.setPrice(Integer.parseInt(request.getParameter("price")));
					course.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
					course.setDetail(request.getParameter("detail"));
					course.setTypeId(100);

					coursectl = new ArrayList<>();
					Coursectl menu = null;
					for (String type : COURSE_MENU_TYPE_NAME) {
						if (request.getParameter(type) != null || !(request.getParameter(type).isEmpty())) {
							menu = new Coursectl();
							menu.setM_Id(Integer.parseInt(request.getParameter(type)));
							coursectl.add(menu);
						}
					}
					Course.updateCourse(course, mode, coursectl);
					rd = request.getRequestDispatcher("./MenuMaintenanceSvl");
					break;
				case UPDATE:
					System.out.println("CO90");
					course = new Course();
					course.setCourseId(Integer.parseInt(request.getParameter("courseId")));
					course.setCourseName(request.getParameter("courseName"));
					course.setPrice(Integer.parseInt(request.getParameter("price")));
					course.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
					course.setDetail(request.getParameter("detail"));
					course.setTypeId(100);

					coursectl = new ArrayList<>();
					menu = null;
					for (String type : COURSE_MENU_TYPE_NAME) {
						if (request.getParameter(type) != null) {
							menu = new Coursectl();
							menu.setM_Id(Integer.parseInt(request.getParameter(type)));
							coursectl.add(menu);
						}
					}
					System.out.println("CO108");
					Course.updateCourse(course, mode, coursectl);
					System.out.println("CO110");
					rd = request.getRequestDispatcher("./MenuMaintenanceSvl");
					break;
				case DELETE:
					coursectl = new ArrayList<>();

					Course.updateCourse(course, mode, coursectl);
					rd = request.getRequestDispatcher("./MenuMaintenanceSvl");

					break;
				default:
					System.out.println("CO121");
					rd = request.getRequestDispatcher("../ex27.jsp"); //テスト用
				}
				request.setAttribute("typeId", typeId);

			}
		} catch (IdealException e) { //Courseクラス完成時に、スローされませんエラーがきえるはず
			request.setAttribute("msg", e.getMsg());
			rd = request.getRequestDispatcher("./MenuMaintenanceSvl");
			System.out.println("CO130");
		}catch(Exception ee) {
			ee.printStackTrace();
			System.out.println("err");
		} finally {
			System.out.println("CO132");
			System.out.println(rd);
			rd.forward(request, response);
		}
	}

}
