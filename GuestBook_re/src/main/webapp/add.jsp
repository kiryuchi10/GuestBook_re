<%@ page import="himedia.vo.BookListVo" %>
<%@ page import="himedia.dao.GuestBookDaoOracleImpl" %>
<%@ page import="himedia.dao.GuestbookDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String name = request.getParameter("name");
    if (name == null || name.equals("")) {
        name = "익명";
    }

    String pass = request.getParameter("pass");
    String content = request.getParameter("content");

    ServletContext servletContext = getServletContext();
    String dbuser = servletContext.getInitParameter("dbuser");
    String dbpass = servletContext.getInitParameter("dbpass");

    GuestbookDao dao = new GuestBookDaoOracleImpl(dbuser, dbpass);
    BookListVo vo = new BookListVo(name, pass, content);

    boolean success = dao.add(vo);

    if (success) {
        response.sendRedirect(request.getContextPath());
    } else {
%>
        <h1>Error</h1>
        <p>데이터 입력 중 오류가 발생했습니다</p>
<%
    }
%>