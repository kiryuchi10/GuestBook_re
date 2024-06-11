<%@page import="himedia.vo.BookListVo"%>
<%@page import="himedia.dao.GuestBookDaoOracleImpl"%>
<%@page import="himedia.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String password = request.getParameter("password");

	ServletContext servletContext = getServletContext();
	String dbuser = servletContext.getInitParameter("dbuser");
	String dbpass = servletContext.getInitParameter("dbpass");

	GuestbookDao dao = new GuestBookDaoOracleImpl(dbuser, dbpass);
	
	Long no = Long.parseLong(request.getParameter("no"));
	BookListVo vo = dao.get(no);

	if (password.equals(vo.getPassword())) {
		dao.delete(vo);
		response.sendRedirect(request.getContextPath());
	} else {
		
		%>
		<h1>비밀번호가 일치하지 않습니다.</h1>
		<a href="<%= request.getContextPath() %>">메인으로 돌아가기</a>
		<%
	}


%>