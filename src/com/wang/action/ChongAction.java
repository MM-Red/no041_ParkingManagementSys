package com.wang.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.db.DBManager;

import java.sql.*;

/**
 * 充值
*/
public class ChongAction extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
 
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String jine = request.getParameter("jine");
	 
	 
		DBManager dbm = new DBManager();
		//充值
		String sql = "update userinfo set jine=jine+"+jine+"  where id="+id;
		System.out.println(sql);

		Statement stat = null;
		Connection conn=null;
		try {
			conn=dbm.getConnection();
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			 
			e.printStackTrace();
		} finally {
			try {
				if(stat!=null)
					stat.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		out.println("<script>alert('充值成功！');window.location.href='userinfo/list.jsp'</script>");
		out.flush();
		out.close();
	}
 
}
