package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.one")
public class FrontController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri=req.getRequestURI();
		int lastSlash=uri.lastIndexOf("/");
		String commandStr=uri.substring(lastSlash);
	
		if(commandStr.equals("/regist.one")) {
			req.setAttribute("resultValue", "<h4>회원가입</h4>");
		}else if(commandStr.equals("/login.one")) {
			req.setAttribute("resultValue", "<h4>로그인</h4>");
		}else {
			req.setAttribute("resultValue", "<h4>자유게시판</h4>");
		}
		
		req.setAttribute("uri", uri);
		req.setAttribute("commandStr", commandStr);
		req.getRequestDispatcher("/12Servlet/FrontController.jsp").forward(req, resp);	
	}
	
	
	

}
