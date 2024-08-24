<%-- 
    Document   : items
    Created on : Aug 23, 2024, 4:27:27 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<tilesx:useAttribute id="itemName" name="itemName" />
<tilesx:useAttribute id="itemEName" name="itemEName" />
<tilesx:useAttribute id="fields" name="fields" />

<%
    String item = (String) request.getAttribute("itemEName");
    if (item == null) {
        item = (String) session.getAttribute("itemEName");
    }

    // Lấy đối tượng động dựa trên itemEName
    Object object = request.getAttribute(item);
    if (object == null) {
        object = session.getAttribute(item);
    }
%>

<c:set var="itemObject" value="${object}" />

<h2 class="text-primary text-center mt-3">Quản lý ${itemName}</h2>
<table class="table">
    <a class="mt-3 btn btn-primary" href="<c:url value="/parkinglot/add"/>">Thêm ${itemName}</a>
    <thead>
        <tr>
            <th>ID</th>
                <c:forEach items="${fields}" var="item" >
                <th class="text-capitalize">${item}</th>
                </c:forEach>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${itemObject}" var="i">
            <tr id="${itemEName}${i.id}">
                <td>${i.id}</td>
                <c:forEach items="${fields}" var="field">
                    <td>${i[field]}</td>
                </c:forEach>
                <td><a class="btn btn-success" href="<c:url value="/${itemEName}/${i.id}" />" >&orarr;</a></td>
                <td><button class="btn btn-danger" onclick="deleteItem('${url}', ${i.id}, '${itemEName}')">&times;</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

