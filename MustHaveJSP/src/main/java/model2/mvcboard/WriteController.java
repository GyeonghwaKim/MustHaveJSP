package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

public class WriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(req, resp);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		
		ServletContext application = getServletContext();
		
		String oFileName="";
		try {
			oFileName=FileUtil.uploadFile(req, saveDirectory);
		} catch (Exception e) {

			JSFunction.alertLocation(resp, "파일 업로드 오류입니다", "../mvcboard/write.do");
			return;
		}
		
		MVCBoardDTO dto = new MVCBoardDTO(); 
        dto.setName(req.getParameter("name"));
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));
        dto.setPass(req.getParameter("pass"));
        
        if(!oFileName.equals("")) {
        	String sFileName=FileUtil.renameFile(saveDirectory, oFileName);
        	dto.setOfile(oFileName);
        	dto.setSfile(sFileName);
                }
        
        
        MVCBoardDAO dao=new MVCBoardDAO();
        int result=dao.insertWrite(dto);
        dao.close();
        
     // 성공 or 실패?
        if (result == 1) {  // 글쓰기 성공
            resp.sendRedirect("../mvcboard/list.do");
        }
        else {  // 글쓰기 실패
            resp.sendRedirect("../mvcboard/write.do");
        }
		
		}


}
