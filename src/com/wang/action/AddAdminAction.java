package com.wang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.db.DBManager;

/**
 * 添加管理员信息
 */
public class AddAdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");

		// 1.连接数据库
		DBManager dbm = new DBManager();
		// 2.插入管理员信息表
		String sql = "insert into admin(userName,userPw)  values('" + name + "','" + pwd + "')";

		Statement stat = null;
		Connection conn = null;
		try {
			conn = dbm.getConnection();
			stat = conn.createStatement();
			System.out.println(sql);
			stat.execute(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		// 3.成功向数据库中插入元素后，重新跳转到admin/list.jsp页面
		response.sendRedirect("admin/list.jsp");
		out.flush();
		out.close();
	}

}
