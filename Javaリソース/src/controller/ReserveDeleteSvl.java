package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;

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
                // リクエストパラメーターから予約IDを取得
                int rsvId = Integer.parseInt(request.getParameter("rsvId"));

                // リクエストオブジェクトに予約IDをセット
                request.setAttribute("rsvId", rsvId);

                // 予約情報変更画面に遷移
                String url = "/reserveDelete.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            } catch (NumberFormatException e) {
                // 数値変換例外が発生した場合、遷移先をエラー画面に設定
                String url = "/error.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            } catch (IdealException e) {
                // 独自例外が発生した場合、エラーメッセージを取得し設定
                String errorMessage = e.getMessage();
                request.setAttribute("msg", errorMessage);

                // エラーが発生した場合、予約一覧表示処理に遷移
                String url = "/ReserveListSvl";
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }
}