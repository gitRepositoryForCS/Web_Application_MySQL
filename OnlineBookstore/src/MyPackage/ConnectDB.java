package MyPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class ConnectDB {


		public static Connection getConnection(){
			Connection conn = null;
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://mysql.cis.ksu.edu/huichen";
				conn = DriverManager.getConnection(url, "huichen", "root");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public static void closeConnection(Connection conn){

			if(conn != null){
				try {
					conn.close();	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
