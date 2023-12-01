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
import model.TableLoc;

/**
 * Servlet implementation class ReserveOperationSvl
 */
@WebServlet("/controller/ReserveOperationSvl")
public class ReserveOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		private static int INSERT = 11; // 「登録処理」
	    private static int UPDATE = 12; // 「変更処理」
	    private static int DELETE = 13; // 「削除処理」

	    // ... 他のメンバ変数やメソッド

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

	        if (userInfo == null) {
	            // セッション情報がない場合、ホームページに遷移
	            String url = "/home.jsp";
	            request.getRequestDispatcher(url).forward(request, response);
	        } else {
	            try {
	                // リクエストパラメーターから予約操作モードを取得
	                int mode = Integer.parseInt(request.getParameter("mode"));

	                // 予約情報を取得
	                int rsvId = Integer.parseInt(request.getParameter("rsvId"));
	                int rsvYy = Integer.parseInt(request.getParameter("rsvYy"));
	                int rsvMm = Integer.parseInt(request.getParameter("rsvMm"));
	                int rsvDd = Integer.parseInt(request.getParameter("rsvDd"));
	                int rsvHh = Integer.parseInt(request.getParameter("rsvHh"));
	                int rsvMi = Integer.parseInt(request.getParameter("rsvMi"));
	                int usrId = Integer.parseInt(request.getParameter("usrId"));
	                int person = Integer.parseInt(request.getParameter("person"));
	                int courseId = Integer.parseInt(request.getParameter("courseId"));
	                int tableId = Integer.parseInt(request.getParameter("tableId"));

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
	                reserve.setTableId(tableId);

	                // 予約操作モードにより処理を分岐
	                switch (mode) {
	                    case INSERT:
	                        // 登録処理
	                        // 予約可能か確認
	                        TableLoc tableLoc = checkVacancy(reserve);
	                        if (tableLoc != null) {
	                            // 予約可能な場合、予約を登録
	                            reserve.setTableId(tableLoc.getTableId());
	                            Reserve registeredReserve = insertReserve(reserve);
	                            // 登録した予約IDをセット
	                            request.setAttribute("rsvId", registeredReserve.getRsvId());
	                            // 遷移先を予約完了画面に設定
	                            String url = "/reserveComplete.jsp";
	                            request.getRequestDispatcher(url).forward(request, response);
	                        } else {
	                            // 予約不可能な場合、メッセージをセットして遷移
	                            request.setAttribute("reserve", reserve);
	                            throw new IdealException(IdealException.ERR_NO_NOT_VACANCY);
	                        }
	                        break;
	                    case UPDATE:
	                        // 変更処理
	                        // 予約情報の座席情報を設定
	                        setTableInfoForReserve(reserve);
	                        // 予約を変更
	                        updateReserve(reserve);
	                        // 遷移先を予約登録画面表示処理に設定
	                        String url = "/ReserveInsertSvl";
	                        request.getRequestDispatcher(url).forward(request, response);
	                        break;
	                    case DELETE:
	                        // 削除処理
	                        // 予約を削除
	                        deleteReserve(reserve);
	                        // 遷移先を予約一覧表示処理に設定
	                        url = "/ReserveListSvl";
	                        request.getRequestDispatcher(url).forward(request, response);
	                        break;
	                    default:
	                        // 不正なモードの場合、エラー画面に遷移
	                        url = "/error.jsp";
	                        request.getRequestDispatcher(url).forward(request, response);
	                        break;
	                }
	            } catch (NumberFormatException e) {
	                // 数値変換例外が発生した場合、エラー画面に遷移
	                String url = "/error.jsp";
	                request.getRequestDispatcher(url).forward(request, response);
	            } catch (IdealException e) {
	                // 独自例外が発生した場合、エラーメッセージをセットして適切な画面に遷移
	                String errorMessage = e.getMessage();
	                request.setAttribute("msg", errorMessage);
	                String url = determineForwardUrl(mode);
	                request.getRequestDispatcher(url).forward(request, response);
	            }
	        }
	    }

	    private TableLoc checkVacancy(Reserve reserve) throws IdealException {
	        // 予約可能か確認するロジックを実装する
	        // 予約可能な場合、座席情報（TableLoc）を返す
	        // 予約不可能な場合、nullを返す
	    }

	    private void setTableInfoForReserve(Reserve reserve) throws IdealException {
	        // 予約情報に座席情報を設定するロジックを実装する
	    }

	    private Reserve insertReserve(Reserve reserve) throws IdealException {
	        // 予約を登録するロジックを実装する
	        // 登録した予約情報（予約IDを含む）を返す
	    }

	    private void updateReserve(Reserve reserve) throws IdealException {
	        // 予約を変更するロジックを実装する
	    }

	    private void deleteReserve(Reserve reserve) throws IdealException {
	        // 予約を削除するロジックを実装する
	    }

	    private String determineForwardUrl(int mode) {
	        // モードにより遷移先のURLを決定するロジックを実装する
	        // 登録処理の場合、予約登録画面表示処理に遷移
	        // 変更処理の場合、予約変更画面表示処理に遷移
	        // 削除処理の場合、予約一覧表示処理に遷移
	        // その他の場合、エラー画面に遷移
	    }
	}