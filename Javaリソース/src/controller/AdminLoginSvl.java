package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.IdealException;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/AdminLoginSvl")
public class AdminLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginSvl() {
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
		String name = request.getParameter("admName");
		String pass = request.getParameter("password");
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		RequestDispatcher rd = null;
		
		try {
			Admin admin = Admin.login(name,pass);
			if(admin == null) {
				IdealException ie = new IdealException(IdealException.ERR_NO_ADMIN_EXCEPTION);
				throw ie;
			}else {
				session.setAttribute("adminInfo", admin);
				rd = request.getRequestDispatcher("../adminIndex.jsp");				System.out.println("1");
			}
		} catch(IdealException e) {
			request.setAttribute("msg", e.getMsg());
			rd = request.getRequestDispatcher("../adminLogin.jsp");
			e.printStackTrace();

		} catch(Exception e2) {
			request.setAttribute("msg", e2.getMessage());
			rd = request.getRequestDispatcher("../adminLogin.jsp");
			e2.printStackTrace();


		}finally {
			rd.forward(request, response);

		}



	}

}
