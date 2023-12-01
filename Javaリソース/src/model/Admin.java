package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    private String admName;
    private String password;
    private String exp;

    /* コンストラクタ */
    public Admin() {
        super();
    }

    /* 各フィールドに対するgetter() */
    /* 各フィールドに対するsetter() */
    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public static Admin login(String admName, String password) throws IdealException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // データベースに接続
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal","root","root");

            // 管理者情報を取得するSQL文
            String selectSql = "SELECT * FROM admin WHERE adm_name = ? AND password = ?";

            // PreparedStatementを使用してSQLを実行
            preparedStatement = connection.prepareStatement(selectSql);
            // プレースホルダに値を設定
            preparedStatement.setString(1, admName);
            preparedStatement.setString(2, password);

            // SQLを実行
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Adminオブジェクトに取得した情報を設定
                Admin admin = new Admin();
                admin.setAdmName(resultSet.getString("adm_name"));
                admin.setPassword(resultSet.getString("password"));
                admin.setExp(resultSet.getString("exp"));

                // 該当レコードを呼び出し元に戻す
                return admin;
            } else {
                // 該当レコードが存在しない場合はnullを返す
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            IdealException idex = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            throw idex;
        } finally {
            try {
                // クローズ処理
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // 例外が発生した場合もログに出力し、無視する
            }
        }
    }
}