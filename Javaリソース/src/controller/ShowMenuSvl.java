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

import model.IdealException;
import model.Menu;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/ShowMenuSvl")
public class ShowMenuSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMenuSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		RequestDispatcher rd = null;
		String name = String.valueOf(session.getAttribute("userInfo"));
		try {
//			ArrayList<Course> courseList = Course.getCourseList();//Courseが出来たら解放
//			session.setAttribute("courseList", courseList);
			
			ArrayList<Menu> menuList = Menu.getMenuList();
			session.setAttribute("menuList", menuList);
			
			rd = request.getRequestDispatcher("../showMenu.jsp");
//			rd = request.getRequestDispatcher("../home.jsp");

		} catch (IdealException e) {
			session.setAttribute("msg", e.getMsg());
			if(name == null) {
				rd = request.getRequestDispatcher("../home.jsp");
			}else {
				rd = request.getRequestDispatcher("../userIndex.jsp");
			}

		} finally {
			rd.forward(request, response);
		}

	}

}
