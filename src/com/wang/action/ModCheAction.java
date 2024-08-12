package com.wang.action;	
import java.io.IOException;	
import java.io.PrintWriter;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServlet;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;

import com.wang.db.DBManager;

import java.sql.*;	
public class ModCheAction extends HttpServlet {	
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)	
			throws ServletException, IOException {	
	
		response.setContentType("text/html");	
		PrintWriter out = response.getWriter();	
		String id = request.getParameter("id");	
		String hao=request.getParameter("hao");
		String leixing=request.getParameter("leixing");
		String pic=request.getParameter("fujian");
		String info=request.getParameter("info");
	 	
		DBManager dbm = new DBManager();	
		//修改车辆
		String sql = "update che set hao='"+hao+"',leixing='"+leixing+"',pic='"+pic+"',info='"+info+"' where id="+id;	
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
		response.sendRedirect("che/list.jsp");	
		out.flush();	
		out.close();	
	}	
 
}	
