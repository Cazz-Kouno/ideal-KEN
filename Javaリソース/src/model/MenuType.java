// 河野
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int typeId ;
	private String typeName = null;
	
	public MenuType(){
		super();
	}
	
	public void setTypeId (int typeId) {
		this.typeId = typeId;
	}
	
	public void setTypeName (String typeName) {
		this.typeName = typeName;
	}
	
/////////////////////////////////////////////////////////////////////////////////
	
	public int getTypeId () {
		
		return typeId; 
	}
	
	public String getTypeName () {
		
		return typeName;
	}
	
/////////////////////////////////////////////////////////////////////////////////
	
	public static ArrayList<MenuType> getAllType() throws IdealException{
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "";
		
		ArrayList<MenuType> al = new ArrayList<>();
		
		try {
			
			con = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");
			
			sql = "SELECT * FROM menutype ORDER BY t_id ASC";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				MenuType mt = new MenuType();
				mt.setTypeId(rs.getInt("t_id"));
				mt.setTypeName(rs.getString("t_name"));
				al.add(mt);
			}
	
			
		}catch(Exception e) {
			e.printStackTrace();
			IdealException idex = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			throw idex;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
				}catch(Exception e){
				
				}
			}
		
		return al;
	}


}
