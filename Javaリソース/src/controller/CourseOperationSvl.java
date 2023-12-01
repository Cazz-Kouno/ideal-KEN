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
@WebServlet("/controller/CourseOperationSvl")
public class CourseOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 21;
	public static final int UPDATE = 22;
	public static final int DELETE = 23;
	public static final int[] COURSE_MENU_TYPE_ID = {200,210,220,300,310,400};

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseOperationSvl() {
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
		String[] menuType = {"appetizerId","soupId","pastaId","meatId","fishId","dessertId"};
		
		int mode = 100;
		int typeId = 100;
		if(request.getParameter("mode") != null) {
			mode = Integer.parseInt(request.getParameter("mode"));
		}
		try {
			if (session.getAttribute("adminInfo") == null) {
				rd = request.getRequestDispatcher("home.jsp");
			} else {
				Course course = null;
				switch(mode) {
				case INSERT:
					course = new Course();
					course.setCourseName(request.getParameter("courseName"));
					course.setPrice(Integer.parseInt(request.getParameter("price")));
					course.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
					course.setDetail(request.getParameter("detail"));
					course.setTypeId(100);
					
					ArrayList<Coursectl> coursectl = new ArrayList<>();
					Coursectl menu = null;
					for(String type:menuType) {
						if(request.getParameter(type) != null) {
							menu = new Coursectl();
							menu.setM_Id(request.getParameter(type));
							coursectl.add(menu);
						}
					}
					Coursectl.updateCourse(course,mode,coursectl);
					rd = request.getRequestDispatcher("/ideal/controller/MenuMaintenanceSvl");
					break;
				case UPDATE:
					course = new Course();
					course.setCourseId(Integer.parseInt(request.getParameter("courseId")));
					course.setCourseName(request.getParameter("courseName"));
					course.setPrice(Integer.parseInt(request.getParameter("price")));
					course.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
					course.setDetail(request.getParameter("detail"));
					course.setTypeId(100);
					
					ArrayList<Coursectl> coursectl = new ArrayList<>();
					Coursectl menu = null;
					for(String type:menuType) {
						if(request.getParameter(type) != null) {
							menu = new Coursectl();
							menu.setM_Id(request.getParameter(type));
							coursectl.add(menu);
						}
					}

					Coursectl.updateCourse(course,mode,coursectl);
					rd = request.getRequestDispatcher("/ideal/controller/MenuMaintenanceSvl");
					break;
				case DELETE:
					ArrayList<Coursectl> coursectl = new ArrayList<>();

					Coursectl.updateCourse(course,mode,coursectl);
					rd = request.getRequestDispatcher("/ideal/controller/MenuMaintenanceSvl");

					break;
				default:
				}
				request.setAttribute("typeId", typeId);
				

			}

		} catch (IdealException e) {
			session.setAttribute("msg", e.getMsg());

			switch(mode) {
			case INSERT:
				rd = request.getRequestDispatcher("/ideal/controller/MenuInsertSvl");
				break;
			case UPDATE:
				rd = request.getRequestDispatcher("/ideal/controller/MenuUpdateSvl");
				break;
			case DELETE:
				rd = request.getRequestDispatcher("/ideal/controller/MenuDeleteSvl");
				break;
			}
		} finally {
			rd.forward(request, response);
		}
	}

}
