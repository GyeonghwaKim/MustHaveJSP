<%@page import="java.util.stream.IntStream"%>
<%@ page import="utils.JSFunction"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String saveDirectory=application.getRealPath("/Uploads");
String saveFilename=request.getParameter("sName");
String originalFilename=request.getParameter("oName");

try{
	
	//파일을 찾아 입력 스트림 생성
	//inputstream은 데이터를 읽어들이는데 사용
	File file=new File(saveDirectory,saveFilename);
	InputStream inStream=new FileInputStream(file);
	
	
	String client=request.getHeader("User-Agent");
	
	//웹브라우저 마다 한글 처리 방식이 다름
	if(client.indexOf("WOW64")==-1){
		originalFilename=new String(originalFilename.getBytes("UTF-8"),"ISO-8859-1");
	}else{
		originalFilename=new String(originalFilename.getBytes("KSC5601"),"ISO-8859-1");
	}
	
	//응답 데이터 초기화
	response.reset();
	//파일 데이터 다운을 위한 콘텐츠 타입을 지정
	response.setContentType("application/octet-stream");
	//다운로드할때 원본이름 생성
	response.setHeader("Content-Disposition", "attachment; filename=\""+
	originalFilename+"\"");
	response.setHeader("Content-Length",""+file.length());
	
	//새로운 출력 스트림을 생성하기 위해 초기화
	out.clear();
	
	//내장 객체로부터 새로운 출력 스트림을 생성
	//데이터를 출력하는데 사용, 저장
	OutputStream outStream=response.getOutputStream();
	
	//읽은 내용(inStream) 출력(outStream)
	byte b[] = new byte[(int)file.length()];
	int readBuffer=0;
	while( (readBuffer = inStream.read(b))> 0){
		outStream.write(b,0,readBuffer);
	}
	inStream.close();
	outStream.close();	
	
}catch(FileNotFoundException e){
	JSFunction.alertBack("파일을 찾을 수 없습니다.",out);
	
}catch(Exception e){
	 JSFunction.alertBack("예외가 발생하였습니다.", out);
}

%>