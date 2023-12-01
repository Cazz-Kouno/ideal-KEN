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
import model.User;

@WebServlet("/controller/ReserveListSvl")
public class ReserveListSvl extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveListSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute("userInfo");

        if (userInfo == null) {
            // セッション情報がない場合、ホームページに遷移
            String url = "../home.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            // セッション情報から予約情報一覧を取得
            try {
            	System.out.println("start:" + userInfo.getUsrId());
                ArrayList<Reserve> reserveList = Reserve.getReserveList(userInfo.getUsrId());
                System.out.println(reserveList);
                session.setAttribute("reserveList", reserveList);
            } catch (IdealException e) {
                // 独自例外が発生した場合、エラーメッセージを取得し設定
                String errorMessage = e.getMsg();
                request.setAttribute("msg", errorMessage);

                // エラーが発生した場合、顧客処理選択画面に遷移
                String url = "/customerProcessingSelection.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                return; // エラーが発生した場合、これ以降の処理はスキップ
            }

            // 予約一覧画面に遷移
            String url = "../reserveList.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}