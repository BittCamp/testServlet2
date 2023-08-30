package com.dbtest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.DBTestDTO;
import com.dao.DBTestDAO;

@WebServlet("/DBListServlet")
public class DBListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터 - X
		//DB에서 데이터 다끌고오고
		DBTestDAO dBTestDAO = new DBTestDAO();
		List<DBTestDTO> list = dBTestDAO.select(); // generic Object로 하면 가리지 않고 다받는다.
		
		//응답 으로 하게 하면 된다.
		response.setContentType("text/html;charset=UTF-8"); // 톰켓은외국꺼라 한국말모름. 이렇게 해줘야함. 순서를 먼저 생성해야제대로 됨.
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		if(list != null) {
			
			out.println("<table border='1'>");
			
			//for(int i=0; i < list.size(); i++){
			for(DBTestDTO dBTestDTO :list) {
				out.println("<tr>");
				out.println("<td width='100' align='center'>"+ dBTestDTO.getName() + "</td>");
				out.println("<td width='50' align='center'>"+ dBTestDTO.getAge() + "</td>");
				out.println("<td width='100' align='center'>"+ dBTestDTO.getHeight() + "</td>");
				out.println("<td width='150' align='center'>"+ dBTestDTO.getLogtime() + "</td>");
				out.println("</tr>"); //경계선.
			}//for
			
			out.println("</table>");
			out.println("<img src='/testServlet2/image/spongebob.png' alt='스폰지밥' width='50' height='50' "+ 
						" onclick=location.href='/testServlet2/exam/input.html' style='cursor: pointer;'>"); //style='cursor: pointer;손가락 모양나옴.
		}//if
		
		out.println("</html>");
		out.println("</body>");
		
	}


}
