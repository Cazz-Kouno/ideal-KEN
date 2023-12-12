package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public static final int INSERT = 21;
	public static final int UPDATE = 22;
	public static final int DELETE = 23;

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

	public String getMenuName() {
		return menuName;
	}

	/////////////////////////////////////////////////////////////////////////

	public static Course getCourse(int c_Id) throws IdealException {

		//		InitialContext ic = null;
		//		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		//
		Course crs = null;
		String sql = "";

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

	//--------------------------------------------------------------------------------------------//

	public static ArrayList<Course> getOneCourse(int c_Id) throws IdealException {

		//		InitialContext ic = null;
		//		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Course crs = null;
		String sql = "";

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

			//			System.out.println("getOneCourse の: " + sql);
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

	//--------------------------------------------------------------------------------------------//

	public static ArrayList<Course> getCourseList() throws IdealException {
		ArrayList<Course> alCrsLst = new ArrayList<>();

		String sql = "SELECT "
				+ "c.c_id, "
				+ "c.c_Name, "
				+ "c.detail, "
				+ "c.orderFlg, "
				+ "c.price, "
				+ "c.t_id, "
				+ "m.t_id, "
				+ "m.m_id, "
				+ "m.m_Name "
				+ "FROM course c JOIN coursectl cc ON c.c_id = cc.c_id "
				+ "JOIN menu m ON cc.m_id = m.m_id ";

		// 上記 sqlで c.t_id と m.t_id のカラム名表示の重複を避けたい場合は m.t_id as mt_id, m.m_id, m.m_Name を採用予定

		//		System.out.println("getCourseList の：" + sql);

		// 比較の為に AutoCloseable の try-with-resources 形式で記述してみる。
		// 外出しの static con は通用しない？
		//		try (Connection con = DriverManager.getConnection(
		//				"jdbc:mysql://localhost:3306/test_heidi", "admin", "5290MySQLadmin1791");
		//				PreparedStatement pst = con.prepareStatement(sql);) {

		try (Connection con = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/ideal", "root", "root");
				PreparedStatement pst = con.prepareStatement(sql);) {

			// ここの pst も static が通用しない！
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Course crs = new Course();
					try {
						crs.setCourseId(rs.getInt("c.c_id"));
						crs.setCourseName(rs.getString("c.c_Name"));
						crs.setDetail(rs.getString("c.detail"));
						crs.setOrderFlg(rs.getInt("c.orderFlg"));
						crs.setPrice(rs.getInt("c.price"));
						crs.setTypeId(rs.getInt("c.t_id"));
						crs.setMenuId(rs.getInt("m.t_id"));
						crs.setMenuName(rs.getString("m.m_Name"));
						System.out.println(rs.getString("m.m_Name"));
					} catch (SQLDataException e) {
						e.printStackTrace();
						throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
					}

					alCrsLst.add(crs);
				}
			}
			return alCrsLst;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
	}

	//--------------------------------------------------------------------------------------------//

	public static ArrayList<Course> getOneCourseList() throws IdealException {
		ArrayList<Course> alCrsLst = new ArrayList<>();

		String sql = "SELECT * "
				+ "FROM course "
				+ "WHERE orderFlg = 1 ";

		// 上記 sqlで c.t_id と m.t_id のカラム名表示の重複を避けたい場合は m.t_id as mt_id, m.m_id, m.m_Name を採用予定

		//		System.out.println("getCourseList の：" + sql);

		// 比較の為に AutoCloseable の try-with-resources 形式で記述してみる。
		// 外出しの static con は通用しない？
		//		try (Connection con = DriverManager.getConnection(
		//				"jdbc:mysql://localhost:3306/test_heidi", "admin", "5290MySQLadmin1791");
		//				PreparedStatement pst = con.prepareStatement(sql);) {

		try (Connection con = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/ideal", "root", "root");
				PreparedStatement pst = con.prepareStatement(sql);) {

			// ここの pst も static が通用しない！
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Course crs = new Course();
					try {
						crs.setCourseId(rs.getInt("c_id"));
						crs.setCourseName(rs.getString("c_Name"));
						crs.setDetail(rs.getString("detail"));
						crs.setOrderFlg(rs.getInt("orderFlg"));
						crs.setPrice(rs.getInt("price"));
						crs.setTypeId(rs.getInt("t_id"));
					} catch (SQLDataException e) {
						e.printStackTrace();
						throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
					}

					alCrsLst.add(crs);
				}
			}

			return alCrsLst;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
	}

	//--------------------------------------------------------------------------------------------//

	public static ArrayList<Course> getTypeCourseList(int t_Id) throws IdealException {
		ArrayList<Course> alTpCrsLst = new ArrayList<>();

		String sql = "SELECT c.c_id, "
				+ "c.c_Name, "
				+ "c.detail, "
				+ "c.orderFlg, "
				+ "c.price, "
				+ "m.t_name  "
				+ "FROM course c JOIN menutype m using(t_id) WHERE t_id = ?";
		//		System.out.println("getTypeCourseList: " + sql);

		try (Connection con = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/ideal", "root", "root");
				PreparedStatement pst = con.prepareStatement(sql);) {

			//	    try (Connection con = DriverManager.getConnection(
			//	            "jdbc:mysql://localhost:3306/test_heidi", "admin", "5290MySQLadmin1791");
			//	         PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, t_Id);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					Course crs = new Course();
					try {
						crs.setCourseId(rs.getInt("c.c_id"));
						crs.setCourseName(rs.getString("c.c_Name"));
						crs.setDetail(rs.getString("c.detail"));
						crs.setOrderFlg(rs.getInt("c.orderFlg"));
						crs.setPrice(rs.getInt("c.price"));
						crs.setTypeName(rs.getString("m.t_name"));
					} catch (SQLDataException e) {
						e.printStackTrace();
						throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
					}

					alTpCrsLst.add(crs);
				}
			}

			return alTpCrsLst;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
	}

	//--------------------------------------------------------------------------------------------//	

	public static int updateCourse(Course crs, int mode, ArrayList<Coursectl> alCctl)
			throws IdealException, SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "";
		try {
			//	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_heidi", "admin", "52909MySQLadmin1791")) {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal", "root", "root");
			System.out.print("C" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
			
			if (mode == UPDATE || mode == DELETE) { //cousectlの処理の前準備（c_idのレコード削除）
				System.out.print("C" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
				sql = "DELETE FROM coursectl WHERE c_id = ?";
				pst = con.prepareStatement(sql);
				pst.setInt(1, crs.getCourseId());
				rs = pst.executeQuery();
				// 直前の挿入操作で生成された主キーの情報を持つ ResultSet を取得 rs にセット
				System.out.print("C" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
				if (rs.next()) {
					crs.setCourseId(rs.getInt(1));
					System.out.println("NewId=" + rs.getInt(1));
				}
			}

			switch (mode) {
			case INSERT:
				sql = "INSERT INTO course VALUES (DEFAULT,?, ?, ?, ?, ?)";
				break;
			case UPDATE:
				sql = "UPDATE course SET c_name=?, detail=?, orderFlg=?, price=?, t_id=? WHERE c_id=?";
				break;
			case DELETE:
				sql = "DELETE FROM course WHERE c_id=?";
				break;
			default:
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
			System.out.print("C" + new Throwable().getStackTrace()[0].getLineNumber() + ":");

			// mode で振り分けられた何れかのクエリを pst に代入
			pst = con.prepareStatement(sql);
			System.out.println("Before:" + pst);
			switch (mode) {
			case INSERT:
				pst.setString(1, crs.getCourseName());
				pst.setString(2, crs.getDetail());
				pst.setInt(3, crs.getOrderFlg());
				pst.setInt(4, crs.getPrice());
				pst.setInt(5, crs.getTypeId());
				break;
			case UPDATE:
				pst.setString(1, crs.getCourseName());
				pst.setString(2, crs.getDetail());
				pst.setInt(3, crs.getOrderFlg());
				pst.setInt(4, crs.getPrice());
				pst.setInt(5, crs.getTypeId());
				pst.setInt(6, crs.getCourseId());
				break;
			case DELETE:
				pst.setInt(1, crs.getCourseId());
				break;
			default:
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
			System.out.println("After:" + pst);
			// Update されたレコード数を戻り値として result にセット、ほぼ1?
			result = pst.executeUpdate();
			System.out.println(result);
			pst.close();
			System.out.print("C" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
			if (mode == INSERT) { //cousectlの処理の前準備（最終レコードの取得）
				System.out.print("C" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
				sql = "SELECT MAX(c_id) FROM course";
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				// 直前の挿入操作で生成された主キーの情報を持つ ResultSet を取得 rs にセット
				System.out.print("C" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
				if (rs.next()) {
					crs.setCourseId(rs.getInt(1));
					System.out.println("NewId=" + rs.getInt(1));
				}
			}

			if (mode == UPDATE || mode == INSERT) { //cousectlの追加処理
				for (Coursectl cctl : alCctl) {
					String cctlSql = "INSERT INTO coursectl VALUES (?, ?)";
					try (PreparedStatement cctlPst = con.prepareStatement(cctlSql)) {
						cctlPst.setInt(1, crs.getCourseId());
						cctlPst.setInt(2, cctl.getM_Id());
						cctlPst.executeUpdate();
						cctlPst.close();
					}
				}
			}

			return result; // 戻り値はこれで正解？
		} catch (SQLException e) {
			System.out.print("C Err" + new Throwable().getStackTrace()[0].getLineNumber() + ":");
			
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		}

	}
}
