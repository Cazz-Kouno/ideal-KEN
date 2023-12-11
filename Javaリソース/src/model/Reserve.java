package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reserve implements Serializable {

	private static final long serialVersionUID = 1L;
	private int rsvId;//予約ＩＤ
	private int usrId;//顧客ＩＤ
	private String usrName;//顧客名
	private int rsvYy;// 予約日時(年)
	private int rsvMm;// 予約日時(月)
	private int rsvDd;// 予約日時(日)
	private int rsvHh;// 予約日時(時)
	private int rsvMi;// 予約日時(分)
	private int person;//人数
	private int tableId;//テーブルＩＤ
	private String tableName;//テーブル名
	private int courseId;//コースＩＤ
	private String courseName;//コース名
	private String appDate;//登録日時
	private int appYy;//
	private int appMm;//
	private int appDd;//
	private int appHh;//
	private int appMi;//

//	static Connection connection = null;
//	static PreparedStatement preparedStatement = null;
//	static ResultSet resultSet = null;
//	static String sql = null;
	
	//コンストラクター
	public Reserve() {
		super();
	}
	
	//Getters and Setters

	public int getRsvId() {
		return rsvId;
	}

	public void setRsvId(int rsvId) {
		this.rsvId = rsvId;
	}

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

	public int getRsvYy() {
		return rsvYy;
	}

	public void setRsvYy(int rsvYy) {
		this.rsvYy = rsvYy;
	}

	public int getRsvMm() {
		return rsvMm;
	}

	public void setRsvMm(int rsvMm) {
		this.rsvMm = rsvMm;
	}

	public int getRsvDd() {
		return rsvDd;
	}

	public void setRsvDd(int rsvDd) {
		this.rsvDd = rsvDd;
	}

	public int getRsvHh() {
		return rsvHh;
	}

	public void setRsvHh(int rsvHh) {
		this.rsvHh = rsvHh;
	}

	public int getRsvMi() {
		return rsvMi;
	}

	public void setRsvMi(int rsvMi) {
		this.rsvMi = rsvMi;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public int getAppYy() {
		return appYy;
	}

	public void setAppYy(int appYy) {
		this.appYy = appYy;
	}

	public int getAppMm() {
		return appMm;
	}

	public void setAppMm(int appMm) {
		this.appMm = appMm;
	}

	public int getAppDd() {
		return appDd;
	}

	public void setAppDd(int appDd) {
		this.appDd = appDd;
	}

	public int getAppHh() {
		return appHh;
	}

	public void setAppHh(int appHh) {
		this.appHh = appHh;
	}

	public int getAppMi() {
		return appMi;
	}

	public void setAppMi(int appMi) {
		this.appMi = appMi;
	}

	// 顧客IDで予約情報一覧を取得する
	public static ArrayList<Reserve> getReserveList(int usrId) throws IdealException {

		ArrayList<Reserve> listOfReservations = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		
		try {
			// データベースから顧客IDに基づいて予約情報を取得する処理
			//initialContext = new InitialContext();
			//dataSource = (DataSource) initialContext.lookup("java:comp/env/mariadb");
			
			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");

			sql = "SELECT * FROM reserve LEFT JOIN user USING (usr_id) "
					+ "LEFT JOIN table_loc USING (table_id) "
					+ "LEFT JOIN course USING (c_id) "
					+ "where usr_id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, usrId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Reserve reserve = new Reserve();
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				// 日付文字列をDateオブジェクトに変換
				Date rsvDate = inputFormat.parse(resultSet.getString("rsv_date"));
				Date appDate = inputFormat.parse(resultSet.getString("app_date"));

				SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
				SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
				SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");

				reserve.setRsvId(resultSet.getInt("rsv_id"));
				reserve.setUsrId(resultSet.getInt("usr_id"));
				reserve.setUsrName(resultSet.getString("usr_name"));
				reserve.setPerson(resultSet.getInt("person"));

				//rsvDate
				String rsvYyString = yearFormat.format(rsvDate);
				String rsvMmString = monthFormat.format(rsvDate);
				String rsvDdString = dayFormat.format(rsvDate);
				String rsvHhString = hourFormat.format(rsvDate);
				String rsvMiString = minuteFormat.format(rsvDate);
				int rsvYy = Integer.parseInt(rsvYyString);
				int rsvMm = Integer.parseInt(rsvMmString);
				int rsvDd = Integer.parseInt(rsvDdString);
				int rsvHh = Integer.parseInt(rsvHhString);
				int rsvMi = Integer.parseInt(rsvMiString);
				reserve.setRsvYy(rsvYy);
				reserve.setRsvMm(rsvMm);
				reserve.setRsvDd(rsvDd);
				reserve.setRsvHh(rsvHh);
				reserve.setRsvMi(rsvMi);

				reserve.setTableId(resultSet.getInt("table_id"));
				reserve.setTableName(resultSet.getString("table_name"));
				reserve.setCourseId(resultSet.getInt("c_id"));
				reserve.setCourseName(resultSet.getString("c_name"));
				reserve.setAppDate(resultSet.getString("app_date"));

				//appDate
				String appYyString = yearFormat.format(appDate);
				String appMmString = monthFormat.format(appDate);
				String appDdString = dayFormat.format(appDate);
				String appHhString = hourFormat.format(appDate);
				String appMiString = minuteFormat.format(appDate);
				int appYy = Integer.parseInt(appYyString);
				int appMm = Integer.parseInt(appMmString);
				int appDd = Integer.parseInt(appDdString);
				int appHh = Integer.parseInt(appHhString);
				int appMi = Integer.parseInt(appMiString);
				reserve.setAppYy(appYy);
				reserve.setAppMm(appMm);
				reserve.setAppDd(appDd);
				reserve.setAppHh(appHh);
				reserve.setAppMi(appMi);

				listOfReservations.add(reserve);

			}

		} catch (Exception e) {
//			System.out.println(sql);
//			System.out.println(preparedStatement);
			
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
//				System.out.println(preparedStatement);
			}

		}
		return listOfReservations; // データ
	}

	// 予約IDで予約情報を取得する
	public static Reserve getReserve(int rsvId) throws IdealException {

		Reserve reserve = new Reserve();
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 String sql = null;
		 
		try {
			// データベースからReservation IDに基づいて予約情報を取得する処理
			

			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");

			sql = "SELECT * FROM reserve LEFT JOIN user USING (usr_id) "
					+ "LEFT JOIN table_loc USING (table_id) "
					+ "LEFT JOIN course USING (c_id) "
					+ "where rsvId=?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, rsvId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				// 日付文字列をDateオブジェクトに変換
				Date rsvDate = inputFormat.parse(resultSet.getString("rsv_date"));
				Date appDate = inputFormat.parse(resultSet.getString("app_date"));

				SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
				SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
				SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");

				reserve.setRsvId(resultSet.getInt("rsv_id"));
				reserve.setUsrId(resultSet.getInt("usr_id"));
				reserve.setUsrName(resultSet.getString("usr_name"));
				reserve.setPerson(resultSet.getInt("person"));

				//rsvDate
				String rsvYyString = yearFormat.format(rsvDate);
				String rsvMmString = monthFormat.format(rsvDate);
				String rsvDdString = dayFormat.format(rsvDate);
				String rsvHhString = hourFormat.format(rsvDate);
				String rsvMiString = minuteFormat.format(rsvDate);
				int rsvYy = Integer.parseInt(rsvYyString);
				int rsvMm = Integer.parseInt(rsvMmString);
				int rsvDd = Integer.parseInt(rsvDdString);
				int rsvHh = Integer.parseInt(rsvHhString);
				int rsvMi = Integer.parseInt(rsvMiString);
				reserve.setRsvYy(rsvYy);
				reserve.setRsvMm(rsvMm);
				reserve.setRsvDd(rsvDd);
				reserve.setRsvHh(rsvHh);
				reserve.setRsvMi(rsvMi);

				reserve.setTableId(resultSet.getInt("table_id"));
				reserve.setTableName(resultSet.getString("table_name"));
				reserve.setCourseId(resultSet.getInt("c_id"));
				reserve.setCourseName(resultSet.getString("c_name"));
				reserve.setAppDate(resultSet.getString("app_date"));

				//appDate
				String appYyString = yearFormat.format(appDate);
				String appMmString = monthFormat.format(appDate);
				String appDdString = dayFormat.format(appDate);
				String appHhString = hourFormat.format(appDate);
				String appMiString = minuteFormat.format(appDate);
				int appYy = Integer.parseInt(appYyString);
				int appMm = Integer.parseInt(appMmString);
				int appDd = Integer.parseInt(appDdString);
				int appHh = Integer.parseInt(appHhString);
				int appMi = Integer.parseInt(appMiString);
				reserve.setAppYy(appYy);
				reserve.setAppMm(appMm);
				reserve.setAppDd(appDd);
				reserve.setAppHh(appHh);
				reserve.setAppMi(appMi);
			}

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
//				System.out.println(preparedStatement);
			}

		}
		return reserve;
	}

	// コースの予約を確認する

	public static void reservCourseChk(int c_Id) throws IdealException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 String sql = null;
		
		try {
			// データベースからReservation IDに基づいて予約情報を取得する処理
			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");

			sql = "SELECT * FROM reserve LEFT JOIN user USING (usr_id) "
					+ "LEFT JOIN table_loc USING (table_id) "
					+ "LEFT JOIN course USING (c_id) "
					+ "WHERE c_id=? AND rsv_date > current_timestamp()";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, c_Id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				throw new IdealException(IdealException.ERR_NO_NOT_RESERV_DELETE);
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
//				System.out.println(preparedStatement);
			}
		}
	}
	

	public static TableLoc insertChk(String dateStr, int personNum) throws IdealException {

		 
		TableLoc ｔableLoc =new TableLoc();
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 String sql = null;
		 
		try {
			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");


			sql="SELECT table_loc.table_id "
					+ "FROM table_loc "
					+ "WHERE table_loc.max_capacity >= ? "
					+ "AND table_loc.table_id NOT IN ("
					+ "SELECT r.table_id "
					+ "FROM Reserve r "
					+ "WHERE ("
					+ " r.rsv_date <= ? + INTERVAL 3 HOUR AND "
					+ " r.rsv_date + INTERVAL (r.person * 15) MINUTE >= ? "
					+ ") OR ("
					+ " ? <= r.rsv_date + INTERVAL (r.person * 15) MINUTE AND "
					+ " ? + INTERVAL 3 HOUR >= r.rsv_date "
					+ ")"
					+ ")"
					+ "ORDER BY table_loc.table_id;";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, personNum);
			preparedStatement.setString(2, dateStr);
			preparedStatement.setString(2, dateStr);
			preparedStatement.setString(2, dateStr);
			preparedStatement.setString(2, dateStr);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				ｔableLoc.setTableId(resultSet.getInt("table_id"));
				ｔableLoc.setTableName(resultSet.getString("table_name"));
				ｔableLoc.setMaxCapacity(resultSet.getInt("max_capacity"));
				
				return ｔableLoc;
			}else {
				return null;
			}

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
//				System.out.println(preparedStatement);
			}
		}
	}

	//
	// 予約を変更するための確認処理
	public static TableLoc updateChk(int rsvID, String dateStr, int personNum) throws IdealException {

		TableLoc ｔableLoc =new TableLoc();
		
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 String sql = null;
		try {
			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");


			sql="SELECT table_loc.table_id "
					+ "FROM table_loc "
					+ "WHERE table_loc.max_capacity >= ? "
					+ "AND table_loc.table_id NOT IN ("
					+ "SELECT r.table_id "
					+ "FROM Reserve r "
					+ "WHERE NOT rsv_id= ? ("
					+ " r.rsv_date <= ? + INTERVAL 3 HOUR AND "
					+ " r.rsv_date + INTERVAL (r.person * 15) MINUTE >= ? "
					+ ") OR ("
					+ " ? <= r.rsv_date + INTERVAL (r.person * 15) MINUTE AND "
					+ " ? + INTERVAL 3 HOUR >= r.rsv_date "
					+ ")"
					+ ")"
					+ "ORDER BY table_loc.table_id;";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, personNum);
			preparedStatement.setInt(3, rsvID);
			preparedStatement.setString(2, dateStr);
			preparedStatement.setString(2, dateStr);
			preparedStatement.setString(2, dateStr);
			preparedStatement.setString(2, dateStr);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return ｔableLoc;
			}else {
				return null;
			}

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
//				System.out.println(preparedStatement);
			}
		}
	}
	
	// 予約をデータベースに登録する
	public static Reserve insert(Reserve reserve) throws IdealException {
		
		Reserve lastRsv = null;
		
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 String sql = null;
		try {
			// 予約情報をデータベースに登録する処理
			//Reserve reserve = new Reserve();
			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");

			sql = "INSERT INTO reserve "
					+ "VALUES (DEFAULT,?,?,?,?,?,DEFAULT)";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reserve.getUsrId());
			
			// 日時情報からyyyy-MM-dd HH:mm:ss形式の文字列を作成する
			int year = reserve.getRsvYy();
			int month = reserve.getRsvMm();
			int day = reserve.getRsvDd();
			int hour = reserve.getRsvHh();
			int minute = reserve.getRsvMi();

			String formattedDateTime = String.format("%04d-%02d-%02d %02d:%02d:00", year, month, day, hour, minute);

			preparedStatement.setString(2, formattedDateTime);
			preparedStatement.setInt(3, reserve.getPerson());
			preparedStatement.setInt(4, reserve.getTableId());
			preparedStatement.setInt(5, reserve.getCourseId());
			preparedStatement.executeUpdate();
			
			sql = "SELECT LAST_INSERT_ID()";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int lastRsvID=0;
			if (resultSet.next()) {
				lastRsvID = resultSet.getInt(1);
			}
			
			lastRsv = getReserve(lastRsvID);
			
			// ...
		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return lastRsv; // 登録した予約情報を返す
	}

	// 予約を変更する
	public static Reserve update(Reserve reserve) throws IdealException {
		Reserve updatedReserve = null;
		
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 //ResultSet resultSet = null;
		 String sql = null;
		 
		try {
			
			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");
			// 予約情報を変更する処理
			sql = "UPDATE reserve SET "
					+ "rsv_date = ?, "
					+ "person = ?, "
					+ "table_id = ?, "
					+ "c_id = ?, "
					+ "app_date = DEFAULT "
					+ "WHERE rsv_id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			int year = reserve.getRsvYy();
			int month = reserve.getRsvMm();
			int day = reserve.getRsvDd();
			int hour = reserve.getRsvHh();
			int minute = reserve.getRsvMi();

			String formattedDateTime = String.format("%04d-%02d-%02d %02d:%02d:00", year, month, day, hour, minute);

			preparedStatement.setString(1, formattedDateTime);
			preparedStatement.setInt(2, reserve.getPerson());
			preparedStatement.setInt(3, reserve.getTableId());
			preparedStatement.setInt(4, reserve.getCourseId());
			preparedStatement.setInt(5, reserve.getRsvId());
			preparedStatement.executeUpdate();
			
			updatedReserve = getReserve(reserve.getRsvId());
			
		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return updatedReserve; // 変更した予約情報を返す
	}

	// 予約を削除する
	public static void delete(Reserve reserve) throws IdealException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 //ResultSet resultSet = null;
		 String sql = null;
		try {
			
			connection = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/ideal","root","root");
			// 予約情報を削除する処理
			
			sql="DELETE FROM reserve "
					+ "WHERE rsv_id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reserve.getRsvId());
			preparedStatement.executeUpdate();
			

		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
	}
}
