package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.IdealException;
import model.Reserve;

@WebServlet("/controller/AdminReserveListSvl")
public class AdminReserveListSvl extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReserveListSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
//        Reserve allReserve = (Reserve) session.getAttribute("allReserve");
        Admin adminInfo = (Admin)session.getAttribute("adminInfo");
        if (adminInfo == null) {
        	System.out.print("AR" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
            // セッション情報がない場合、ホームページに遷移
            String url = "../home.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            // セッション情報から予約情報一覧を取得
            try {
//            	System.out.println("start:" + userInfo.getUsrId());
            	//System.out.print("AR" + new Throwable().getStackTrace()[0].getLineNumber() + ":");

                ArrayList<Reserve> allReserveList = Reserve.getAllReserveList();
            	//System.out.print("AR" + new Throwable().getStackTrace()[0].getLineNumber() + ":");

//                System.out.println(reserveList);
                request.setAttribute("allReserveList", allReserveList);
            } catch (IdealException e) {
                // 独自例外が発生した場合、エラーメッセージを取得し設定
                String errorMessage = e.getMsg();
                request.setAttribute("msg", errorMessage);
                
                // エラーが発生した場合、顧客処理選択画面に遷移
                String url = "../userIndex.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                return; // エラーが発生した場合、これ以降の処理はスキップ
            }

            // 全体予約一覧画面に遷移
            String url = "../AdminReserveList.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}