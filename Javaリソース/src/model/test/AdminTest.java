package model.test;

import model.Admin;
import model.IdealException;

public class AdminTest {
    public static void main(String[] args) {
        try {
            // ログイン処理のテスト
            testLogin();
        } catch (IdealException e) {
            e.printStackTrace();
        }
    }

    private static void testLogin() throws IdealException {
        Admin admin = new Admin();
        String admName = "admin"; // テスト用の管理者名
        String password = "*4ACFE3202A5FF5CF467898FC58AAB1D615029441"; // テスト用のパスワード

        try {
            // ログイン処理を呼び出す
            Admin loggedInAdmin = admin.login(admName, password);

            if (loggedInAdmin != null) {
                System.out.println("ログイン成功");
                System.out.println("管理者名: " + loggedInAdmin.getAdmName());
                System.out.println("パスワード: " + loggedInAdmin.getPassword());
                System.out.println("備考: " + loggedInAdmin.getExp());
            } else {
                System.out.println("ログイン失敗");
            }
        } catch (IdealException e) {
            // ログイン時に例外が発生した場合の処理
            e.printStackTrace();
        }
    }
}