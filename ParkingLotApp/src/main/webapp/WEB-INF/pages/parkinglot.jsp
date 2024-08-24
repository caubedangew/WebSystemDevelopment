<%-- 
    Document   : parkinglot
    Created on : Aug 19, 2024, 11:03:58 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="text-primary text-center mt-3">Quản lý địa điểm đỗ xe</h2>
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
            <tr id="parkinglot${p.id}">
                <td>${p.id}</td>
                <td>${p.address}</td>
                <td>${p.quantity}</td>
                <td>${String.format("%,.0f", p.price)} VND</td>
                <c:url value="/api/parkinglot/${p.id}" var="url"/>
                <td><a class="btn btn-success" href="<c:url value="/parkinglot/${p.id}" />" >&orarr;</a></td>
                <td><button class="btn btn-danger" onclick="deleteItem('${url}', ${p.id}, 'parkinglot')">&times;</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

