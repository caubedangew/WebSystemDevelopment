<%-- 
    Document   : home
    Created on : Aug 16, 2024, 8:16:17 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="my-3 text-center">
    <c:choose>
        <c:when test="${deniedPage == 1}" >
            Bạn không được quyền thao tác trên đây.
        </c:when>
        <c:otherwise>
            <s:authorize access="isAuthenticated()">
        <s:authorize access="hasRole('ROLE_ADMIN')">
            Chào mừng, <s:authentication property="principal.username"/>!
        </s:authorize>
        <s:authorize access="hasRole('ROLE_USER')">
            Trang này chỉ dành cho quản trị viên
        </s:authorize>
    </s:authorize>
    <s:authorize access="!isAuthenticated()">
        Vui lòng <a href="<c:url value="/login" /> "> đăng nhập </a> tài khoản quản trị để được quản lý
    </s:authorize>
        </c:otherwise>
    </c:choose>
</h3>
