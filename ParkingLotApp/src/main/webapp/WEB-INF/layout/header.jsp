<%-- 
    Document   : header
    Created on : Aug 16, 2024, 8:05:56 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/" />" >Parking Lot Management</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <s:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/parkinglot"/>">Địa điểm giữ xe</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/parkingspace"/>">Vị trí đậu xe</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/receipt"/>">Hóa đơn</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/comment"/>">Bình luận</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Thống kê</a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/stats/revenue" />">Doanh thu</a></li>
                            <li><a href="<c:url value="/stats/parking" />">Các bãi đỗ</a></li>
                        </ul>
                    </li>
                </s:authorize>
                <s:authorize access="isAuthenticated()" >
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/logout" />">Đăng xuất</a>
                    </li>
                </s:authorize>
            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="text" placeholder="Search">
                <button class="btn btn-primary" type="button">Search</button>
            </form>
        </div>
    </div>
</nav>
