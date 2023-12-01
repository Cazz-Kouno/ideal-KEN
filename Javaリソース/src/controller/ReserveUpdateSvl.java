package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;
import model.Reserve;

@WebServlet("/controller/ReserveUpdateSvl")
public class ReserveUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveUpdateSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

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

                // オーダー可能なコースの一覧情報を取得
                ArrayList<Course> courseList = getAvailableCourseList();
                request.setAttribute("courseList", courseList);

                // リクエストオブジェクトに予約情報をセット
                Reserve reserve = getReserveInfo(rsvId);
                request.setAttribute("reserve", reserve);

                // 予約情報変更画面に遷移
                String url = "/reserveUpdate.jsp";
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

    // オーダー可能なコースの一覧情報を取得するメソッド（仮の例）
    private ArrayList<Course> getAvailableCourseList() {
        // 実際の処理を適切に実装する
        ArrayList<Course> courseList = new ArrayList<>();
        // ...
        return courseList;
    }

    // 予約IDに対応する予約情報を取得するメソッド（仮の例）
    private Reserve getReserveInfo(int rsvId) {
        // 実際の処理を適切に実装する
        Reserve reserve = new Reserve();
        // ...
        return reserve;
    }
}