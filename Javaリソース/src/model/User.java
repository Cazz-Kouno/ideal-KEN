package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int usrId;
    private String usrName;
    private String password;
    private String address;
    private String phone;
    private String mail;
    private String exp;

    // コンストラクタ、setter、getterは省略
    public int getUsrId() {
        return usrId;
    }
    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }
    public String getUsrName() {
        return usrName;
    }
    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getExp() {
        return exp;
    }
    public void setExp(String exp) {
        this.exp = exp;
    }

    public static User login(int usrId, String password) throws IdealException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // データベースに接続
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal","root","root");
            // ログイン情報を取得するSQL文
            String selectSql = "SELECT * FROM user WHERE usr_id = ? AND user.password = ?";
            // PreparedStatementを使用してSQLを実行
            preparedStatement = connection.prepareStatement(selectSql);
            // プレースホルダに値を設定
            preparedStatement.setInt(1, usrId);
            preparedStatement.setString(2, password);
            // SQLを実行
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            if (resultSet.next()) {
                // Userオブジェクトに取得した情報を設定
                User user = new User();
                user.setUsrId(resultSet.getInt("usr_id"));
                user.setUsrName(resultSet.getString("usr_name"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setMail(resultSet.getString("mail"));
                user.setExp(resultSet.getString("exp"));
                // 該当レコードを呼び出し元に戻す
                return user;
            } else {
                // 該当レコードが存在しない場合はnullを返す
            	System.out.println("7");
            	return null;
            }
        } catch (SQLException e) {
            // データベース関連の例外が捕捉された場合、IdealExceptionに変換してスロー
        	IdealException idex = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
//			idex.getMsg();
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

    public static User getUser(int usrId) throws IdealException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // データベースに接続
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal","root","root");
            // ユーザ情報を取得するSQL文
            String selectSql = "SELECT * FROM user WHERE usr_id = ?";
            // PreparedStatementを使用してSQLを実行
            preparedStatement = connection.prepareStatement(selectSql);
            // プレースホルダに値を設定
            preparedStatement.setInt(1, usrId);
            // SQLを実行
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Userオブジェクトに取得した情報を設定
                User user = new User();
                user.setUsrId(resultSet.getInt("usr_id"));
                user.setUsrName(resultSet.getString("usr_name"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setMail(resultSet.getString("mail"));
                user.setExp(resultSet.getString("exp"));
                // 該当レコードを呼び出し元に戻す
                return user;
            } else {
                // 該当レコードが存在しない場合はnullを返す
                return null;
            }
        } catch (SQLException e) {
            // データベース関連の例外が捕捉された場合、IdealExceptionに変換してスロー
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
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

    public static User insert(User user) throws IdealException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // データベースに接続
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal","root","root");
            // 顧客情報を登録するSQL文
            String insertSql = "INSERT INTO user (usr_name, password, address, phone, mail, exp) VALUES (?, ?, ?, ?, ?, ?)";
            // PreparedStatementを使用してSQLを実行
            preparedStatement = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            // プレースホルダに値を設定
            preparedStatement.setString(1, user.getUsrName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getMail());
            preparedStatement.setString(6, user.getExp());
            // SQLを実行
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                // 登録が失敗した場合はIdealExceptionをスロー
                throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            }
            // 登録した顧客情報の顧客IDを取得
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUsrId(resultSet.getInt(1));
            } else {
                // 顧客IDの取得ができない場合もIdealExceptionをスロー
                throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            }
            // 登録した顧客情報を呼び出し元に戻す
            return user;
        } catch (SQLException e) {
            // データベース関連の例外が捕捉された場合、IdealExceptionに変換してスロー
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
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

    public static User update(User user) throws IdealException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // データベースに接続
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal","root","root");

            // 顧客情報を更新するSQL文
            String updateSql = "UPDATE user SET usr_name=?, password=?, address=?, phone=?, mail=?, exp=? WHERE usr_id=?";

            // PreparedStatementを使用してSQLを実行
            preparedStatement = connection.prepareStatement(updateSql);
            // プレースホルダに値を設定
            preparedStatement.setString(1, user.getUsrName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getMail());
            preparedStatement.setString(6, user.getExp());
            preparedStatement.setInt(7, user.getUsrId());

            // SQLを実行
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                // 更新が失敗した場合はIdealExceptionをスロー
                throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            }

            // 更新した顧客情報を呼び出し元に戻す
            return user;
        } catch (SQLException e) {
            // データベース関連の例外が捕捉された場合、IdealExceptionに変換してスロー
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        } finally {
            try {
                // クローズ処理
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

    public static void delete(User user) throws IdealException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // データベースに接続
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal","root","root");

            // トランザクション開始
            connection.setAutoCommit(false);

            // 顧客情報を削除するSQL文（例：予約テーブルと顧客テーブルからの削除）
            String deleteReserveSql = "DELETE FROM reserve WHERE usr_id=?";
            String deleteUserSql = "DELETE FROM user WHERE usr_id=?";

            // PreparedStatementを使用してSQLを実行（予約テーブルからの削除）
            preparedStatement = connection.prepareStatement(deleteReserveSql);
            // プレースホルダに値を設定
            preparedStatement.setInt(1, user.getUsrId());
            // SQLを実行
            preparedStatement.executeUpdate();

            // PreparedStatementを使用してSQLを実行（顧客テーブルからの削除）
            preparedStatement = connection.prepareStatement(deleteUserSql);
            // プレースホルダに値を設定
            preparedStatement.setInt(1, user.getUsrId());
            // SQLを実行
            preparedStatement.executeUpdate();

            // トランザクションのコミット
            connection.commit();
        } catch (SQLException e) {
            try {
                // ロールバック
                if (connection != null) {
                    connection.rollback();
                }

                // データベース関連の例外が捕捉された場合、IdealExceptionに変換してスロー
                throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
            } finally {
                try {
                    // クローズ処理
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // 例外が発生した場合もログに出力し、無視する
                }
            }
        }
    }
	public static User getlogin() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}