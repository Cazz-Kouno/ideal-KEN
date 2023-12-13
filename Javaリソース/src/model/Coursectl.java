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
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet resultSet = null;
	static String sql = null;

	
	//コンストラクター
	public Coursectl() {
		super();
	}
	
	//Setters and Getters
	
	public int getC_Id() {
		return c_id;
	}
	
	public void setC_Id(int c_id) {
		this.c_id=c_id;
		
	}
	
	public int getM_Id() {
		return m_id;

}
	
	public void setM_Id(int m_id) {
		this.m_id=m_id;
		
	}
	
	public static void courseMenuChk(int m_Id)throws IdealException{
			
			try {
				// 引数のメニューIDがSQLのコースコントロールテーブルに存在するかの検索
				con = DriverManager.getConnection
						("jdbc:mariadb://localhost:3306/ideal","root","root");

				sql = "SELECT * FROM coursectl "
						+ "WHERE m_id = ?";

				ps = con.prepareStatement(sql);
				ps.setInt(1, m_Id);
				resultSet = ps.executeQuery();
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
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
//					System.out.println(ps);
				}

			}
	}
}