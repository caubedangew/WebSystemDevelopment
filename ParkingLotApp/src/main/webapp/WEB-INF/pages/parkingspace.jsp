<%-- 
    Document   : parkingspace
    Created on : Aug 23, 2024, 4:26:17 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="text-primary text-center mt-3">Quản lý vị trí đỗ xe</h2>
<table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>No. </th>
            <th>Status</th>
            <th>Parking Lot ID</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${parkingspace}" var="p">
            <tr id="parkingspace${p.id}">
                <td>${p.id}</td>
                <td>${p.stt}</td>
                <td>${p.status}</td>
                <td>${p.parkinglotId.id}</td>
                <c:url value="/api/parkingspace/${p.id}" var="url"/>
                <td><a class="btn btn-success" href="<c:url value="/parkingspace/${p.id}" />" >&orarr;</a></td>
                <td><button class="btn btn-danger" onclick="deleteItem('${url}', ${p.id}, 'parkingspace')">&times;</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

