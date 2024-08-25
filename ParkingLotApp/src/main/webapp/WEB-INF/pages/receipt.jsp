<%-- 
    Document   : receipt
    Created on : Aug 24, 2024, 4:10:48 PM
    Author     : ThienVu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2 class="text-primary text-center mt-3">Quản lý hóa đơn</h2>
<table class="table">
    <a class="mt-3 btn btn-primary" href="<c:url value="/receipt/add"/>">Thêm hóa đơn</a>
    <thead>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Parking Space</th>
            <th>Time interval</th>
            <th>Created Date</th>
            <th>Total amount</th>
            <th>Updated Date</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${receipt}" var="r">
            <tr id="receipt${r.id}">
                <td>${r.id}</td>
                <td>${r.userId.username}</td>
                <td>Số ${r.parkingspaceId.stt} ${r.parkingspaceId.parkinglotId.address}</td>
                <td>${r.timeInterval}</td>
                <td>${r.createdDate}</td>
                <td>${String.format("%,.0f", r.totalAmount)} VND</td>
                <td>${r.createdDate}</td>
                <c:url value="/api/receipt/${r.id}" var="url"/>
                <td><a class="btn btn-success" href="<c:url value="/receipt/${r.id}" />" >&orarr;</a></td>
                <td><button class="btn btn-danger" onclick="deleteItem('${url}', ${r.id}, 'receipt')">&times;</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
