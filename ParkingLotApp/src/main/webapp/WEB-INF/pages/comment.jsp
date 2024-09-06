<%-- 
    Document   : comment
    Created on : Aug 27, 2024, 4:57:53 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="text-primary text-center mt-3">Quản lý bình luận</h2>
<table class="table">
    <a class="mt-3 btn btn-primary" href="<c:url value="/comment/add"/>">Thêm bình luận</a>
    <thead>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Parking Lot</th>
            <th>Content</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${comments}" var="c">
            <tr id="comment${c.id}">
                <td>${c.id}</td>
                <td>${c.userId.username}</td>
                <td>${c.parkinglotId.address}</td>
                <td>${c.content}</td>
                <td>${c.createdDate}</td>
                <td>${c.updatedDate}</td>
                <c:url value="/api/comment/${c.id}" var="url"/>
                <td><a class="btn btn-success" href="<c:url value="/comment/${c.id}" />" >&orarr;</a></td>
                <td><button class="btn btn-danger" onclick="deleteItem('${url}', ${c.id}, 'comment')">&times;</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>


