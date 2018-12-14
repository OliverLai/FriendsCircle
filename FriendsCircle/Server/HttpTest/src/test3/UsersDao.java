package test3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDao {
	
	public static boolean findUser(String name, String pwd) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			String sql = "select * from users where user = '" + name + "' and password = '" + pwd + "'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, st, conn);
		}
		return false;
	}
	
	public static boolean findUserByName(String name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			String sql = "select * from users where user = '" + name + "'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, st, conn);
		}
		return false;
	}
	
	public static String findIcon(String user) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			String sql = "select icon from users where user = '" + user + "'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return rs.getString("icon");
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Users> showContent() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Users> list = new ArrayList<Users>();
		try {
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			String sql = "select user, text, pic, date_format(date, '%Y年%m月%d日  %H:%i:%s') as date from data order by date desc";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Users c = new Users();
				c.setIconUrl(findIcon(rs.getString("user")));
				c.setUser(rs.getString("user"));
				c.setText(rs.getString("text"));
				c.setPicUrl(rs.getString("pic"));
				c.setDate(rs.getString("date"));
				list.add(c);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, st, conn);
		}
		return null;
	}
	
	public static boolean pub(Users user) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			String sql = "insert into data(user, text, pic, date) values('" +
			user.getUser() + "','" +user.getText() + "','" + user.getPicUrl() + "', str_to_date('" + user.getDate() + "', '%Y-%m-%d-%H-%i-%s'))";
			int num = st.executeUpdate(sql);
			System.out.println(sql);
			if(num > 0) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, st, conn);
		}
		return false;
	}
	
	public static boolean reg(Users user) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			String sql = "insert into users(icon, user, password, phone) values('" +
			user.getIconUrl() + "', '" +user.getUser() + "', '" + user.getPassword() + "', '" + user.getPhone() + "')";
			int num = st.executeUpdate(sql);
			System.out.println(sql);
			if(num > 0) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, st, conn);
		}
		return false;
	}
	
	public static void main(String[] args) {
		if(UsersDao.findUserByName("ljl")) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}
}
