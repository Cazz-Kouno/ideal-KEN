package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;
import model.Reserve;
import model.User;

/**
 * Servlet implementation class ReserveDeleteSvl
 */
@WebServlet("/controller/ReserveDeleteSvl")
public class ReserveDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveDeleteSvl() {
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

		if (userInfo == null) {
			// セッション情報がない場合、ホームページに遷移
			String url = "../home.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			try {
				// リクエストパラメーターから予約IDを取得
				try {
					int rsvId = Integer.parseInt(request.getParameter("rsvId"));
					Reserve reserve = Reserve.getReserve(rsvId);
					// リクエストオブジェクトに予約IDの予約情報ををセット
					request.setAttribute("reserve", reserve);

					// 予約情報変更画面に遷移
					String url = "../reserveDelete.jsp";
					request.getRequestDispatcher(url).forward(request, response);
				} catch (NumberFormatException e) {
					// 数値変換例外が発生した場合、独自例外をスロー
					IdealException ie = new IdealException(IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
					throw ie;
				}
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