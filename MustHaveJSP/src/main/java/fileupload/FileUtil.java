package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	
	//파일 업로드
	public static String uploadFile(HttpServletRequest req,String sDirectory) throws IOException, ServletException {
		Part part = req.getPart("ofile");
		//content-disposition : 파일에 대한 처분!!
		String partHeader = part.getHeader("content-disposition");
		
	String[] phArr = partHeader.split("filename=");
	System.out.println("phArr[0] = "+phArr[0]);
	System.out.println("phArr[1] = "+phArr[1]);
	String originalFileName = phArr[1].trim().replace("\"", "");
	System.out.println("originalFileName = "+originalFileName);
	
	
	if(!originalFileName.isEmpty()) {
		part.write(sDirectory+File.separator+originalFileName);
	}
	
	return originalFileName;
	}
	
	//파일명 변경
	public static String renameFile(String sDirectory,String fileName) {
		String ext=fileName.substring(fileName.lastIndexOf("."));
		String now
		= new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName=now+ext;
		//파일명 변경
		File oldFile = new File(sDirectory+File.separator+fileName);
		File newFile = new File(sDirectory+File.separator+newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
	
	//multiple 속성 추가로 2개 이상의 파일 업로드
	public static ArrayList<String> multipleFile(HttpServletRequest req,String sDirectory)throws ServletException,IOException{
		ArrayList<String> listFileName = new ArrayList<>();
		Collection<Part> parts=req.getParts();
		for(Part part:parts) {
			if(!part.getName().equals("ofile")) continue;
	
			String partHeader = part.getHeader("content-disposition");
			String[] phArr=partHeader.split("filename=");
			String originalFileName=phArr[1].trim().replace("\"", "");
			if(!originalFileName.isEmpty()) {
				part.write(sDirectory+File.separator+originalFileName);
				
			}
			listFileName.add(originalFileName);
 }
		
		return listFileName;
	}
	

}
