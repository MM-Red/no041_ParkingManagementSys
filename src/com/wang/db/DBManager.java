package com.wang.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 数据库管理类
 * 
 * 
 */
public class DBManager {
	public static final String DEFAULT_DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/tcc?useUnicode=true&characterEncoding=utf8";

	public static String DB_URL = null;

	public static Properties prop = null;

	// 获取数据连接
	public Connection getConnection() {

		Connection coon = null;
		try {
			Class.forName(DEFAULT_DRIVER_NAME);
			coon = DriverManager.getConnection(DEFAULT_DB_URL, "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coon;
	}

	public static void main(String[] args) {
		DBManager manager = new DBManager();
		manager.getConnection();
	}

	public boolean login(String username, String pwd) {

		Connection coon = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = coon
					.prepareStatement("select * from admin where userName='" + username + "' and userPw='" + pwd + "'");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (coon != null)
					coon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public int loginYH(String username, String pwd) {

		Connection coon = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from userinfo where name='" + username + "' and pwd='" + pwd + "'";
			pstmt = coon.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (coon != null)
					coon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public String getSF() {

		Connection coon = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = coon.prepareStatement("select * from fei ");

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("price");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (coon != null)
					coon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "0";
	}

	public String getChe(int uid) {

		Connection coon = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = coon.prepareStatement("select * from che where uid= " + uid);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("hao");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (coon != null)
					coon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String isDing(String hao) {

		Connection coon = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = coon.prepareStatement("select * from chewei where chepai='" + hao + "'");

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("hao");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (coon != null)
					coon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getYue(String hao) {

		Connection coon = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = coon.prepareStatement(
					"select ui.jine as jine from che ch,userinfo ui where ch.uid=ui.id and ch.hao='" + hao + "'");

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("jine");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (coon != null)
					coon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public long calcHour(String bdate, String edate) {
		String dateStart = bdate;
		String dateStop = edate;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// 毫秒ms
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print("两个时间相差：");
			System.out.print(diffDays + " 天, ");
			System.out.print(diffHours + " 小时, ");
			System.out.print(diffMinutes + " 分钟, ");
			System.out.print(diffSeconds + " 秒.");
			return diffHours;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static String TextToHtml(String sourcestr) {
		int strlen;
		String restring = "", destr = "";
		strlen = sourcestr.length();
		for (int i = 0; i < strlen; i++) {
			char ch = sourcestr.charAt(i);
			switch (ch) {
			case '<':
				destr = "<";
				break;
			case '>':
				destr = ">";
				break;
			case '\"':
				destr = "\"";
				break;
			case '&':
				destr = "&";
				break;
			case 13:
				destr = "<br>";
				break;
			case 32:
				destr = "&nbsp;";
				break;
			default:
				destr = "" + ch;
				break;
			}
			restring = restring + destr;
		}
		return "" + restring;
	}
}
