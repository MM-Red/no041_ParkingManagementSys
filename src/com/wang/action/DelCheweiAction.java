package com.wang.action;	
	
import java.io.IOException;	
import java.io.PrintWriter;	
import java.sql.Connection;	
import java.sql.SQLException;	
import java.sql.Statement;	
	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServlet;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;

import com.wang.db.DBManager;	
 
public class DelCheweiAction extends HttpServlet {	
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)	
			throws ServletException, IOException {	
	
		response.setContentType("text/html");	
		PrintWriter out = response.getWriter();	
		String id = request.getParameter("id");	
	
		DBManager dbm = new DBManager();	
		//删除车位
		String sql = "delete from chewei where id="+id;	
	
		Statement stat = null;	
		Connection conn = null;	
		try {	
			conn = dbm.getConnection();	
			stat = conn.createStatement();	
			stat.execute(sql);	
		 	
		} catch (SQLException e) {	
			// TODO Auto-generated catch block	
			e.printStackTrace();	
		} finally {	
			try {	
				if (stat != null)	
					stat.close();	
				if (conn != null)	
					conn.close();	
			} catch (SQLException e) {	
				// TODO Auto-generated catch block	
				e.printStackTrace();	
			}	
		}	
		response.sendRedirect("chewei/list.jsp");	
		out.flush();	
		out.close();	
	}	
  
}	
