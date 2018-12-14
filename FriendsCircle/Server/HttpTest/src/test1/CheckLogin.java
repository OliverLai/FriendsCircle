package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckLogin {
	public static boolean checklogin(String user, String pwd) throws Exception {
		boolean flag = false;
		String sql = "select * from user where username = '" + user + "' and password = '" + pwd + "'";
		String className = "com.mysql.jdbc.Driver";
		Class.forName(className);
		String url = "jdbc:mysql://localhost:3306/test";
		Connection conn = DriverManager.getConnection(url, "root", "root");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()) {
			flag = true;
		}
		rs.close();
		st.close();
		conn.close();
		return flag;
	}
}
