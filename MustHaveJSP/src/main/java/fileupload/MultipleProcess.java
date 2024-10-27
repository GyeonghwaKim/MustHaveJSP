package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/13FileUpload/MultipleProcess.do")

@MultipartConfig(
		maxFileSize = 1024 * 1024 *1,
		maxRequestSize=1024 * 1024 * 10
		)
public class MultipleProcess extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L
			;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//이미지가 저장될 경로
			String saveDirectory = getServletContext().getRealPath("/Uploads");
			
			//이미지 저장하고 oname 들고옴
			ArrayList<String> listFileName=FileUtil.multipleFile(req, saveDirectory);
			
			//오리지널로 저장된 이미지를 다시 이미지명 바꾸고 DB에 넣기
			for(String oFileName:listFileName) {
				String savedFileName = FileUtil.renameFile(saveDirectory, oFileName);
				insertMyFile(req,oFileName,savedFileName);
			}
			resp.sendRedirect("FileList.jsp");
			
		}catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("MultiUploadMain.jsp").forward(req, resp);
			
		}
	}


	//DB에 저장
	private void insertMyFile(HttpServletRequest req, String originalFileName, String savedFileName) {
		// TODO Auto-generated method stub
		String title = req.getParameter("title");
		String[] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if(cateArray ==null) {
			cateBuf.append("선택한 항목 없음");
		}
		else {
			for(String s:cateArray) {
				cateBuf.append(s+", ");
			}
		}
		
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(originalFileName);
		dto.setSfile(savedFileName);
		
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto)
		;
		dao.close();
	}
	

}
