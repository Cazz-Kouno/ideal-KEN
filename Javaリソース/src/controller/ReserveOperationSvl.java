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
import model.Reserve;
import model.TableLoc;
import model.User;

/**
 * Servlet implementation class ReserveOperationSvl
 */
@WebServlet("/controller/ReserveOperationSvl")
public class ReserveOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 11; // 「登録処理」
	public static final int UPDATE = 12; // 「変更処理」
	public static final int DELETE = 13; // 「削除処理」

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveOperationSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	// ... 他のメンバ変数やメソッド

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		int rsvId = 0;
		int rsvYy = 0;
		int rsvMm = 0;
		int rsvDd = 0;
		int rsvHh = 0;
		int rsvMi = 0;
		int usrId = 0;
		int person = 0;
		int courseId = 0;
//		int tableId = 0;
		int mode = 0;
		String formattedDateTime = null;
		TableLoc tableLoc = null;
		RequestDispatcher rd = null;

		if (userInfo == null) {
			// セッション情報がない場合、ホームページに遷移
			String url = "../home.jsp";
			rd = request.getRequestDispatcher(url);
		} else {
			try {
				try {
					// リクエストパラメーターから予約操作モードを取得
					mode = Integer.parseInt(request.getParameter("mode"));

					// 予約情報を取得
					rsvId = Integer.parseInt(request.getParameter("rsvId"));
					rsvYy = Integer.parseInt(request.getParameter("rsvYy"));
					rsvMm = Integer.parseInt(request.getParameter("rsvMm"));
					rsvDd = Integer.parseInt(request.getParameter("rsvDd"));
					rsvHh = Integer.parseInt(request.getParameter("rsvHh"));
					rsvMi = Integer.parseInt(request.getParameter("rsvMi"));
					usrId = Integer.parseInt(request.getParameter("usrId"));
					person = Integer.parseInt(request.getParameter("person"));
					courseId = Integer.parseInt(request.getParameter("courseId"));
					//					tableId = Integer.parseInt(request.getParameter("tableId"));
					formattedDateTime = String.format("%04d-%02d-%02d %02d:%02d:00", rsvYy, rsvMm, rsvDd, rsvHh, rsvMi);

				} catch (NumberFormatException e) {
					// 数値変換例外が発生した場合、0に設定（先にしてある)
				}

				// 予約情報の生成
				Reserve reserve = new Reserve();
				reserve.setRsvId(rsvId);
				reserve.setRsvYy(rsvYy);
				reserve.setRsvMm(rsvMm);
				reserve.setRsvDd(rsvDd);
				reserve.setRsvHh(rsvHh);
				reserve.setRsvMi(rsvMi);
				reserve.setUsrId(usrId);
				reserve.setPerson(person);
				reserve.setCourseId(courseId);
				//				reserve.setTableId(tableId);

				// 予約操作モードにより処理を分岐
				switch (mode) {
				case INSERT:
					// 登録処理
					// 予約可能か確認
					tableLoc = Reserve.insertChk(formattedDateTime, person);
					if (tableLoc != null) {
						System.out.println(1);
						// 予約可能な場合、予約を登録
						reserve.setTableId(tableLoc.getTableId());
						Reserve registeredReserve = Reserve.insert(reserve);
						System.out.println(2);
						// 登録した予約IDをセット
						request.setAttribute("rsvId", registeredReserve.getRsvId());
						System.out.println(3);
						// 遷移先を予約完了画面に設定
						System.out.println(4);
						String url = "../reserveComplete.jsp";
						rd = request.getRequestDispatcher(url);
					} else {
						// 予約不可能な場合、メッセージをセットして遷移
						System.out.println(5);
						request.setAttribute("reserve", reserve);
						String url = "ReserveInsertSvl";
						System.out.println(6);
						System.out.println(url);
						rd = request.getRequestDispatcher(url);
						throw new IdealException(IdealException.ERR_NO_NOT_VACANCY);
					}
					break;
				case UPDATE:
					// 変更処理
					// 予約情報の座席情報を設定
					tableLoc = Reserve.updateChk(rsvId, formattedDateTime, person);

					// 予約を変更
					if (tableLoc != null) {
						reserve.setTableId(tableLoc.getTableId());
						reserve.setTableName(tableLoc.getTableName());
						reserve = Reserve.update(reserve);
						request.setAttribute("reserve", reserve);

						// 遷移先を予約登録画面表示処理に設定
						String url = "../reserveCompletion.jsp";
						rd = request.getRequestDispatcher(url);
					} else {
						// 予約不可能な場合、メッセージをセットして遷移
						request.setAttribute("reserve", reserve);
						String url = "ReserveUpdateSvl";
						rd = request.getRequestDispatcher(url);
						throw new IdealException(IdealException.ERR_NO_NOT_VACANCY);

					}
					break;
				case DELETE:
					try {
						// 削除処理
						// 予約を削除
						Reserve.delete(reserve);
						// 遷移先を予約一覧表示処理に設定
						String url = "ReserveListSvl";
						rd = request.getRequestDispatcher(url);
						break;
					} catch (IdealException e) {
						String url = "ReserveDeleteSvl";
						rd = request.getRequestDispatcher(url);

						throw e;
					}
				default:
					// 不正なモードの場合、ホーム画面に遷移
					// 不正なモードの場合、ホーム画面に遷移
				    String url = "../home.jsp";
				    rd = request.getRequestDispatcher(url);
				    break;
				}
			} catch (IdealException e) {
				// 独自例外が発生した場合、エラーメッセージをセットして適切な画面に遷移
				String errorMessage = e.getMsg();
				request.setAttribute("msg", errorMessage);
			} finally {
			    if (rd != null) {
			        rd.forward(request, response);
			    } else {
			        // 適切なエラー処理を行うか、ログにメッセージを記録するなど
			        System.err.println("RequestDispatcher is null.");
			    }
			}
		}
	}
}