package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Course {

	private int crsId;
	private String crsName;
	private String detail;
	private int orderFlg;
	private int price;
	private int typeId;
	private String typeName;
	private int menuId;
	private String menuName;

	public static int[] COURSE_MENU_TYPE_ID = { 200, 210, 220, 300, 310, 400 };

	static InitialContext ic = null;
	static DataSource ds = null;
	static Connection con = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;

	static Course crs = null;
	static String sql = "";

	public Course() {
		super();
	}

	public void setCourseId(int crsId) {
		this.crsId = crsId;
	}

	public void setCourseName(String crsName) {
		this.crsName = crsName;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setOrderFlg(int orderFlg) {
		this.orderFlg = orderFlg;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/////////////////////////////////////////////////////////////////////////

	public int getCourseId() {
		return crsId;
	}

	public String getCourseName() {
		return crsName;
	}

	public String getDetail() {
		return detail;
	}

	public int getOrderFlg() {
		return orderFlg;
	}

	public int getPrice() {
		return price;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getMenuId() {
		return menuId;
	}

	public String getmenuName() {
		return menuName;
	}

	/////////////////////////////////////////////////////////////////////////

	public static Course getCourse(int c_Id) throws IdealException {

		//		InitialContext ic = null;
		//		DataSource ds = null;
		//		Connection con = null;
		//		PreparedStatement pst = null;
		//		ResultSet rs = null;
		//
		//		Course crs = null;
		//		String sql = "";

		try {
			//			ic = new InitialContext();
			//			ds = (DataSource)ic.lookup("java:comp/env/mysql"); 
			//			ds = (DataSource)ic.lookup("java:comp/env/mariadb");
			//			con = ds.getConnection();
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal", "root", "root");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_heidi", "admin", "5290MySQLadmin1791");

			sql = "SELECT c_id, c_name, detail, orderFlg, price, t_id FROM course WHERE c_id = ?";
		
			pst = con.prepareStatement(sql);
			pst.setInt(1, c_Id);
		
			rs = pst.executeQuery();
	
			if (rs.next()) {

				crs = new Course();
				crs.setCourseId(rs.getInt("c_id"));
				crs.setCourseName(rs.getString("c_name"));
				crs.setDetail(rs.getString("detail"));
				crs.setOrderFlg(rs.getInt("orderFlg"));
				crs.setPrice(rs.getInt("price"));
				crs.setTypeId(rs.getInt("t_id"));

				return crs;

			} else {

				throw new IdealException(IdealException.ERR_NO_EXCEPTION);
			}

		} catch (SQLException e) {

			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}

    /////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<Course> getOneCourse(int c_Id) throws IdealException {

		//		InitialContext ic = null;
		//		DataSource ds = null;
		//		Connection con = null;
		//		PreparedStatement pst = null;
		//		ResultSet rs = null;
		//
		//		Course crs = null;
		//		String sql = "";

		try {
			//			ic = new InitialContext();
			//			ds = (DataSource)ic.lookup("java:comp/env/mysql"); 
			//			ds = (DataSource)ic.lookup("java:comp/env/mariadb"); 		
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal", "root", "root");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_heidi", "admin", "5290MySQLadmin1791");

			sql = "SELECT c.c_id, c.c_name, c.detail, c.orderFlg, c.price, c.t_id, "
					+ "m.t_id, m.m_id, m.m_Name "
					+ "FROM course c "
					+ "JOIN coursectl cc USING(c_id) "
					+ "JOIN menu m USING(m_id) "
					+ "WHERE c.c_id = ?";
			
System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.setInt(1, c_Id);
			rs = pst.executeQuery();

			ArrayList<Course> alCrs = new ArrayList<>();
			while (rs.next()) {
				crs = new Course();
				crs.setCourseId(rs.getInt("c_id"));
				crs.setCourseName(rs.getString("c_name"));
				crs.setDetail(rs.getString("detail"));
				crs.setOrderFlg(rs.getInt("orderFlg"));
				crs.setPrice(rs.getInt("price"));
				crs.setTypeId(rs.getInt("t_id"));
				crs.setMenuId(rs.getInt("m_id"));
				crs.setMenuName(rs.getString("m_name"));

				alCrs.add(crs);
			}

			return alCrs;

		} catch (SQLException e) {

			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}

	// 比較の為に AutoCloseable の try-with-resources 形式で記述してみる。
	public static ArrayList<Course> getCourseList(int t_Id) throws IdealException{
		ArrayList<Course> alCrsLst = new ArrayList<>();
		
		// 外出しの static con は通用しない？
//        try (Connection con = DriverManager.getConnection(
//        		"jdbc:mysql://localhost:3306/test_heidi", "admin", "5290MySQLadmin1791")){
      try (Connection con = DriverManager.getConnection(
		"jdbc:mariadb://localhost:3306/ideal", "root", "root")){
    	  
          String sql = "SELECT "
          		+ "c.c_id, "
          		+ "c.c_name, "
          		+ "c.detail, "
          		+ "c.orderFlg, "
          		+ "c.price, "
          		+ "c.t_id, "
          		+ "m.t_id, "
          		+ "m.m_id, "
          		+ "m.m_Name "
          		+ "FROM course c JOIN coursectl cc ON c.c_id = cc.c_id "
          		+ "JOIN menu m ON cc.m_id = m.m_id "
          		+ "WHERE c.c_id = ? ";
          
          System.out.println("1");
          
            // ここの pst も static が通用しない！
          try (PreparedStatement pst = con.prepareStatement(sql)) {
              pst.setInt(1, t_Id);

              try (ResultSet rs = pst.executeQuery()) {
                  while (rs.next()) {
                      Course crs = new Course();
                      try {
                          crs.setCourseId(rs.getInt("c.c_id"));
                          crs.setCourseName(rs.getString("c.c_name"));
                          crs.setDetail(rs.getString("c.detail"));
                          crs.setOrderFlg(rs.getInt("c.orderFlg"));
                          crs.setPrice(rs.getInt("c.price"));
                          crs.setTypeId(rs.getInt("c.t_id"));
                          //crs.setTypeName(rs.getString(""));
                      } catch (SQLException e) {
                      
                          throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
                      }

                      alCrsLst.add(crs);
                  }
              }
          }

            return alCrsLst;
        } catch (SQLException e) {
            throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
        }
	}

	public static ArrayList<Course> getTypeCourseList(int t_Id) {
		ArrayList<Course> crs = new ArrayList<>();
		try {

		} catch (Exception e) {

		} finally {

		}
		return crs;
	}

	
	public static ArrayList<Coursectl> updateCourse() {
		ArrayList<Coursectl> crsCtl = new ArrayList<>();
		try {

		} catch (Exception e) {

		} finally {

		}
		return crsCtl;
	}

}