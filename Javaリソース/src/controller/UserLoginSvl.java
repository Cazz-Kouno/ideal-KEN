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
import model.User;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/controller/UserLoginSvl")
public class UserLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginSvl() {
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
		int id = Integer.parseInt(request.getParameter("usrId"));
		String pass = request.getParameter("password");
		String name = request.getParameter("usrName");
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		RequestDispatcher rd = null;
		
		try {
			System.out.println("1");
			User user = User.login(id,pass);
			System.out.println("2");
			System.out.println(user);
			if(user == null) {
				IdealException ie = new IdealException(IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
				throw ie;
			}else {
				session.setAttribute("userInfo", user);
				session.setAttribute("usrName", user);
				rd = request.getRequestDispatcher("../userIndex.jsp");
			}
		} catch(IdealException e) {
			session.setAttribute("msg", e.getMsg());
			rd = request.getRequestDispatcher("../userLogin.jsp");
			e.printStackTrace();

		} catch(Exception e2) {
			session.setAttribute("msg", e2.getMessage());
			rd = request.getRequestDispatcher("../userLogin.jsp");
			e2.printStackTrace();


		}finally {
			rd.forward(request, response);

		}



	}

}
