package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import model.IdealException;
import model.Reserve;
import model.User;

@WebServlet("/controller/ReserveInsertSvl")
public class ReserveInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @param courseId 
	 * @param person 
	 * @param usrId 
	 * @param reservationDateTime 
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveInsertSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		int rsvYy = 0;
		int rsvMm = 0;
		int rsvDd = 0;
		int rsvHh = 0;
		int rsvMi = 0;
		int usrId = 0;
		int person = 0;
		int courseId = 0;
		String usrName = null;

		if (userInfo == null) {
			// セッション情報がない場合、ホームページに遷移
			String url = "../home.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			try {
				try {
					// リクエストパラメータから予約情報を生成
					courseId = Integer.parseInt(request.getParameter("courseId"));
					rsvYy = Integer.parseInt(request.getParameter("rsvYy"));
					rsvMm = Integer.parseInt(request.getParameter("rsvMm"));
					rsvDd = Integer.parseInt(request.getParameter("rsvDd"));
					rsvHh = Integer.parseInt(request.getParameter("rsvHh"));
					rsvMi = Integer.parseInt(request.getParameter("rsvMi"));
					usrId = Integer.parseInt(request.getParameter("usrId"));
					usrName = userInfo.getUsrName();
					person = Integer.parseInt(request.getParameter("person"));
				} catch (NumberFormatException e) {
					// 数値変換例外が発生した場合、0を設定
					//既に設定してあるので次へ
				}

					// 予約情報を生成
					Reserve reserve = new Reserve();
					Course course = Course.getCourse(courseId);
					reserve.setRsvYy(rsvYy);
					reserve.setRsvMm(rsvMm);
					reserve.setRsvDd(rsvDd);
					reserve.setRsvHh(rsvHh);
					reserve.setRsvMi(rsvMi);
					reserve.setUsrId(usrId);
					reserve.setUsrName(usrName);
					reserve.setPerson(person);
					reserve.setCourseId(courseId);
					reserve.setCourseName(course.getCourseName());

					// リクエストオブジェクトに予約情報を設定
					request.setAttribute("reserve", reserve);

					// オーダー可能なコースの一覧情報を取得
					ArrayList<Course> courseList = getOneCourseList(); //クラス完成待ち
					request.setAttribute("courseList", courseList);

					// 新規予約画面に遷移
					String url = "../reserveInsert.jsp";
					request.getRequestDispatcher(url).forward(request, response);
			} catch (IdealException e) {
				// 独自例外が発生した場合、エラーメッセージを取得し設定
				String errorMessage = e.getMsg();
				request.setAttribute("msg", errorMessage);

				// エラーが発生した場合、予約一覧表示処理に遷移
				String url = "../reserveList.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
	}

}