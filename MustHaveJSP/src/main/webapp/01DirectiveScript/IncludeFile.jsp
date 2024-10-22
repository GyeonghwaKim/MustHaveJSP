<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.LocalDate"%>
<% 
LocalDate today=LocalDate.now();
LocalDateTime tomorrow=LocalDateTime.now().plusDays(1);
%>