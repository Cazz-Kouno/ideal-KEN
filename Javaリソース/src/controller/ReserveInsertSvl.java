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
		ArrayList<Integer> userData = new ArrayList<>();
		String[] attributeName = {"rsvYy","rsvMm","rsvDd","rsvHh","rsvMi","person","courseId"};

		if (session.getAttribute("userInfo") == null) {
			// セッション情報がない場合、ホームページに遷移
			String url = "../home.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			try {
				User userInfo = (User) session.getAttribute("userInfo");
					// リクエストパラメータから予約情報を生成
					for(int i = 0;i < attributeName.length;i++) {
						try {
							userData.add(Integer.parseInt(request.getParameter(attributeName[i])));
						}catch (NumberFormatException e) {
							userData.add(0);
							// 数値変換例外が発生した場合、0を設定
						}
					}

					// 予約情報を生成
					Reserve reserve = new Reserve();
					Course course = null;
					if(userData.get(6) != 0) {
						course = Course.getCourse(userData.get(6));
						reserve.setCourseName(course.getCourseName());
					}
					reserve.setRsvYy(userData.get(0));
					reserve.setRsvMm(userData.get(1));
					reserve.setRsvDd(userData.get(2));
					reserve.setRsvHh(userData.get(3));
					reserve.setRsvMi(userData.get(4));
					reserve.setUsrId(userInfo.getUsrId());
					reserve.setUsrName(userInfo.getUsrName());
					reserve.setPerson(userData.get(5));
					reserve.setCourseId(userData.get(6));

					// リクエストオブジェクトに予約情報を設定
					request.setAttribute("reserve", reserve);

					// オーダー可能なコースの一覧情報を取得
					ArrayList<Course> courseList = Course.getOneCourseList(); //クラス完成待ち
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