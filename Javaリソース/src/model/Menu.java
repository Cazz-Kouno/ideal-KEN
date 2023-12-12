// 河野
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//import javax.naming.InitialContext;
//import javax.sql.DataSource;

import controller.MenuOperationSvl;

public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int menuId;
	private String menuName;
	private String detail;
	private int orderFlg;
	private int price;
	private int typeId;
	private String typeName;
	
	public static int[] MENU_TYPE_ID = { 200, 210, 220, 300, 310, 400 };

	public Menu() {
		super();
	}


	public int getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
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

	////////////////////////////////////////////////////////////
	
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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

	////////////////////////////////////////////////////////////

	public static Menu getOneMenu(int menuId, int typeId) throws IdealException{
		
//		InitialContext ic = null;
//		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Menu m = null;
		String sql = "";
		
		try{
//			System.out.println("1");
//			ic = new InitialContext();
//			System.out.println("2");
			
			//ds = (DataSource)ic.lookup("java:comp/env/mysql"); //データソースをリース
//			ds = (DataSource)ic.lookup("java:comp/env/mariadb"); //データソースをリース
//			System.out.println("3");

//			con = ds.getConnection();
			con = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");
			
//			System.out.println("4");

			if(typeId == 100){
				sql = "SELECT "
					+ " c_id,"
					+ " c_name,"
					+ " detail,"
					+ " orderFlg,"
					+ " price,"
					+ " menuType.t_id,"
					+ " menuType.t_name"
					+ " FROM course"
					+ " JOIN"
					+ " menuType"
					+ " USING(t_id)"
					+ " WHERE "
					+ " c_id = ?"
					+ " ORDER BY c_id";
			}else{
				sql = "SELECT"
					+ " m_id,"
					+ " m_name,"
					+ " detail,"
					+ " orderFlg,"
					+ " price,"
					+ " menuType.t_id,"
					+ " menuType.t_name"
					+ " FROM menu"
					+ " JOIN"
					+ " menuType"
					+ " USING(t_id)"
					+ " WHERE "
					+ " m_id  = ?"
					+ " ORDER BY m_id";
			}
			pst = con.prepareStatement(sql);
			pst.setInt(1,menuId);
			rs = pst.executeQuery();
			if(rs.next()){
				m = new Menu();
				m.setMenuId(rs.getInt(1));
				m.setMenuName(rs.getString(2));
				m.setDetail(rs.getString(3));
				m.setOrderFlg(rs.getInt(4));
				m.setPrice(rs.getInt(5));
				m.setTypeId(rs.getInt(6));
				m.setTypeName(rs.getString(7));
			}
			

			
		}catch(Exception e){
//			System.out.println("err");
			e.printStackTrace();
//			System.out.println(pst);
			IdealException idex = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
//			idex.getMsg();
			throw idex;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return m;
	}

	public static ArrayList<Menu> getMenuList() throws IdealException{

		ArrayList<Menu> al = new ArrayList<Menu>();
	
//		InitialContext ic = null;
//		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
	
		String sql = "";
		try{
//			ic = new InitialContext();
			//ds = (DataSource)ic.lookup("java:comp/env/mysql"); //データソースをリース
//			ds = (DataSource)ic.lookup("java:comp/env/mariadb");
//			con = ds.getConnection();
			
			con = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");
		
				sql = "SELECT "
					+ " m_id,"
					+ " m_name,"
					+ " detail,"
					+ " orderFlg,"
					+ " price,"
					+ " menuType.t_name,"
					+ " menu.t_id"
					+ " FROM menu"
					+ " JOIN"
					+ " menuType"
					+ " USING(t_id)"
					+ " ORDER BY m_id";
				
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(); 
			while(rs.next()){
				Menu m = new Menu();
				m.setMenuId(rs.getInt(1));
				m.setMenuName(rs.getString(2));
				m.setDetail(rs.getString(3));
				m.setOrderFlg(rs.getInt(4));
				m.setPrice(rs.getInt(5));
				m.setTypeName(rs.getString(6));
				m.setTypeId(rs.getInt(7));
				al.add(m);
			}
			
			
		}catch(Exception e){

			e.printStackTrace();
			IdealException idex = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
//			idex.getMsg();
			
			throw idex;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return al;
	}

	public static ArrayList<Menu> getMenu(int typeId) throws IdealException{
		ArrayList<Menu> al = new ArrayList<Menu>();
	
//		InitialContext ic = null;
//		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
	
		String sql = "";
		try{
//			ic = new InitialContext();
			//ds = (DataSource)ic.lookup("java:comp/env/mysql"); //データソースをリース
//			ds = (DataSource)ic.lookup("java:comp/env/mariadb");
//			con = ds.getConnection();
			
			con = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");
		
			if(typeId == 100){
				sql = "SELECT "
					+ " c_id,"
					+ " c_name,"
					+ " detail,"
					+ " orderFlg,"
					+ " price,"
					+ " menuType.t_name"
					+ " FROM course"
					+ " JOIN"
					+ " menuType"
					+ " USING(t_id)"
					+ " WHERE "
					+ " course.t_id = ?"
					+ " ORDER BY c_id";
			}else{
				sql = "SELECT "
					+ " m_id,"
					+ " m_name,"
					+ " detail,"
					+ " orderFlg,"
					+ " price,"
					+ " menuType.t_name"
					+ " FROM menu"
					+ " JOIN"
					+ " menuType"
					+ " USING(t_id)"
					+ " WHERE "
					+ " menu.t_id = ?"
					+ " ORDER BY m_id";
			}
			pst = con.prepareStatement(sql);
			pst.setInt(1,typeId);
			rs = pst.executeQuery();
			while(rs.next()){
				Menu m = new Menu();
				m.setMenuId(rs.getInt(1));
				m.setMenuName(rs.getString(2));
				m.setDetail(rs.getString(3));
				m.setOrderFlg(rs.getInt(4));
				m.setPrice(rs.getInt(5));
				m.setTypeName(rs.getString(6));
				al.add(m);
			}
	
			 	
		}catch(Exception e){
			IdealException idex = new IdealException(1);
//			idex.getMsg();
			throw idex;
			
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return al;
	}
	
	public static int updateMenu(Menu m, int mode) throws IdealException{
	
//				InitialContext ic = null;
//				DataSource ds = null;
				Connection con = null;
				PreparedStatement pst = null;
				//変数の宣言と初期化
				String sql = "";
				int num = 0;

				try{
//					ic = new InitialContext();
					//ds = (DataSource)ic.lookup("java:comp/env/mysql"); //データソースをリース
//					ds = (DataSource)ic.lookup("java:comp/env/mariadb");
//					con = ds.getConnection();
					
					con = DriverManager.getConnection
							("jdbc:mariadb://localhost:3306/ideal","root","root");
					
					switch(mode) {
						case MenuOperationSvl.INSERT:
								sql = "INSERT INTO menu VALUES(DEFAULT,?,?,?,?,?)";
								pst = con.prepareStatement(sql); 
								pst.setString(1, m.getMenuName()); 
								pst.setString(2, m.getDetail());
								pst.setInt(3, m.getOrderFlg());
								pst.setInt(4, m.getPrice());
								pst.setInt(5, m.getTypeId());
						
							break;
						case MenuOperationSvl.UPDATE:		
							//SQL文の生成
//							if(m.getTypeId() == 100) {
//								sql = "UPDATE course SET "
//									+ " c_name = ?, detail = ?, orderFlg = ?, price = ?, t_id = ?"
//									+ " WHERE c_id = ?";
//								pst = con.prepareStatement(sql); 
//								pst.setString(1, m.getMenuName()); 
//								pst.setString(2, m.getDetail());
//								pst.setInt(3, m.getOrderFlg());
//								pst.setInt(4, m.getPrice());
//								pst.setInt(5, m.getTypeId());
//								pst.setInt(6,m.getMenuId());
//							
//							}else {					
								sql = "UPDATE menu SET "
									+ " m_name = ?,detail = ?,orderFlg = ?, price = ?, t_id = ?"
									+ " WHERE m_id = ?";	
								System.out.println(sql);
								System.out.println(pst);
								pst = con.prepareStatement(sql); 
								pst.setString(1, m.getMenuName()); 
								pst.setString(2, m.getDetail());
								pst.setInt(3, m.getOrderFlg());
								pst.setInt(4, m.getPrice());
								pst.setInt(5, m.getTypeId());
								pst.setInt(6,m.getMenuId());
								System.out.println(pst);
//							}
							break;
						case MenuOperationSvl.DELETE: 
							//SQL文の生成
//							if(m.getTypeId() == 100) {
//								sql = "DELETE FROM course WHERE c_id = ?";
//								pst = con.prepareStatement(sql);
//								pst.setInt(1, m.getMenuId());
//							}else {
								sql = "DELETE FROM menu WHERE m_id = ?";
								pst = con.prepareStatement(sql);
								pst.setInt(1, m.getMenuId());
//								}
							break;
						default:
							break;
					}

					num = pst.executeUpdate();
							
				}catch(Exception e){
					IdealException idex = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
					throw idex;
					}finally{
						try{
							if(pst != null) pst.close();
							if(con != null) con.close();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				return num; 
	}

}
