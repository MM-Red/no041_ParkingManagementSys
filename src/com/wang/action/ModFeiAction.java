package com.wang.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.db.DBManager;

import java.sql.*;
 
public class ModFeiAction extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
 
		String price = request.getParameter("price");
 
	 
		DBManager dbm = new DBManager();
		//修改收费标准
		String sql = "update fei set price='"+price+"'";
		System.out.println(sql);

		Statement stat = null;
		Connection conn=null;
		try {
			conn=dbm.getConnection();
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stat!=null)
					stat.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("fei", price);
		out.println("<script>alert('修改收费标准成功！');window.location.href='fei/modFei.jsp'</script>");
		out.flush();
		out.close();
	}
 
}
