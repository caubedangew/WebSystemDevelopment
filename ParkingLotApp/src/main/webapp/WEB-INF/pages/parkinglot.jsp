<%-- 
    Document   : parkinglot
    Created on : Aug 19, 2024, 11:03:58 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table">
    <a class="mt-3 btn btn-primary" href="<c:url value="/parkinglot/add"/>">Thêm địa điểm giữ xe</a>
    <thead>
        <tr>
            <th>ID</th>
            <th>Address</th>
            <th>Quantity</th>
            <th>Price</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${parkinglot}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.address}</td>
                <td>${p.quantity}</td>
                <td>${p.price}</td>
                <td><a class="btn btn-success" href="<c:url value="/parkinglot/${p.id}" />" >&orarr;</a></td>
                <td><a class="btn btn-danger" onclick="">&times;</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

