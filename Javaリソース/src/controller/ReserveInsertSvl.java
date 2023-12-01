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
                // リクエストパラメータから予約情報を生成
                int rsvYy = Integer.parseInt(request.getParameter("rsvYy"));
                int rsvMm = Integer.parseInt(request.getParameter("rsvMm"));
                int rsvDd = Integer.parseInt(request.getParameter("rsvDd"));
                int rsvHh = Integer.parseInt(request.getParameter("rsvHh"));
                int rsvMi = Integer.parseInt(request.getParameter("rsvMi"));
                int usrId = Integer.parseInt(request.getParameter("usrId"));
                int person = Integer.parseInt(request.getParameter("person"));
                int courseId = Integer.parseInt(request.getParameter("courseId"));

                // 予約情報を生成
                Reserve reserve = new Reserve();
                reserve.setRsvYy(rsvYy);
                reserve.setRsvMm(rsvMm);
                reserve.setRsvDd(rsvDd);
                reserve.setRsvHh(rsvHh);
                reserve.setRsvMi(rsvMi);
                reserve.setUsrId(usrId);
                reserve.setPerson(person);
                reserve.setCourseId(courseId);

                // リクエストオブジェクトに予約情報を設定
                request.setAttribute("reserve", reserve);

                // オーダー可能なコースの一覧情報を取得
                ArrayList<Course> courseList = getAvailableCourseList();
                request.setAttribute("courseList", courseList);

                // 新規予約画面に遷移
                String url = "/reserveInsert.jsp";
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
}