package com.dbtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DBTestDTO;
import com.dao.DBTestDAO;

@WebServlet("/DBTestServlet")
public class DBTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String height = request.getParameter("height");
		
		DBTestDTO dBTestDTO = new DBTestDTO();
		dBTestDTO.setName(name);
		dBTestDTO.setAge(age);
		dBTestDTO.setHeight(height);
			
		//DB
		DBTestDAO dbTestDAO = new DBTestDAO();
		int su = dbTestDAO.insert(dBTestDTO); // dbTestDAO.insert(name, age ,height); 대신에 DTO모음으로 한번에 넘김.
			
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		if(su == 1) {
			out.println("<h3>등록 완료</h3> ");
			out.println("<br/><br/>");
			out.println("<input type='button' value='목록' onclick=location.href='/testServlet2/DBListServlet'>");
		}else {
			out.println("<h3>등록 실패</h3> ");
			out.println("<br/><br/>");
			out.println("<input type='button' value='뒤로' onclick='history.go(-1)'>");
		}
		
		out.println("</body>");
		out.println("</html>");
		
	}

}
