package model2.mvcboard;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		MVCBoardDAO dao=new MVCBoardDAO();
		String idx=req.getParameter("idx");
		dao.updateVisitCount(idx);
		
		MVCBoardDTO dto = dao.selectView(idx);
		dao.close();
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		String filename=dto.getSfile();
		String ext=getExtension(filename);
		
		String[] mimeStr= {"png","jpg","gif"};
		
		List<String> mineList=Arrays.asList(mimeStr);
		
		boolean isImage=false;
		if(mineList.contains(ext)) {
			isImage=true;
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("isImage", isImage);
		req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp);
		
	}

	private String getExtension(String filename) {
		if(filename==null || !filename.contains(".")) {
			return "";
		}
		return filename.substring(filename.lastIndexOf(".")+1);
	}
	
	

}
