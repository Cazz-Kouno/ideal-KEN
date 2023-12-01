package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Coursectl implements Serializable {

	private static final long serialVersionUID = 1L;
	private int c_id;
	private int m_id;
	
	//Connection related fields
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	static String sql = null;

	
	//コンストラクター
	public Coursectl() {
		super();
	}
	
	//Setters and Getters
	
	public int getC_id() {
		return c_id;
	}
	
	public void setC_id(int c_id) {
		this.c_id=c_id;
		
	}
	
	public int getM_id() {
		return m_id;

}
	
	public void setM_id(int m_id) {
		this.m_id=m_id;
		
	}
	
	public static void courseMenuChk(int m_Id)throws IdealException{
			
			try {
				// 引数のメニューIDがSQLのコースコントロールテーブルに存在するかの検索
				connection = DriverManager.getConnection
						("jdbc:mariadb://localhost:3306/ideal","root","root");

				sql = "SELECT * FROM coursectl "
						+ "WHERE m_id = ?";

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, m_Id);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					throw new IdealException(IdealException.ERR_NO_NOT_MENU_DELETE);
				}
			}catch(IdealException ie) {
				throw ie;
			} catch (Exception e) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			} finally {

				try {
					if (resultSet != null)
						resultSet.close();
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println(preparedStatement);
				}

			}
	}
}