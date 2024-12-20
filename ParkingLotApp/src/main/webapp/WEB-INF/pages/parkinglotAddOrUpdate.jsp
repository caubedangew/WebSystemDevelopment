<%-- 
    Document   : parkinglotAddOrUpdate
    Created on : Aug 19, 2024, 11:42:59 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<c:url value="/parkinglot" var="action" />

<form:form method="post" enctype="multipart/form-data" action="${action}" modelAttribute="parkinglot">
    <div class="mb-3 mt-3">
        <label for="address" class="form-label">Address: </label>
        <form:input path="address" type="text" class="form-control" id="address" placeholder="" name="address" />
        <form:errors path="address" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="quantity" class="form-label">Quantity:</label>
        <form:input path="quantity" type="number" class="form-control" id="quantity" placeholder="" name="quantity" />
        <form:errors path="quantity" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="price" class="form-label">Price:</label>
        <form:input path="price" type="number" class="form-control" id="price" placeholder="" name="price" />
        <form:errors path="price" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="file" class="form-label">Thumbnail:</label>
        <form:input path="file" type="file" accept=".jpg,.png" class="form-control" id="file" name="file" />
        <c:if test="${parkinglot.thumbnail != null}">
            <img class="mt-1" src="${parkinglot.thumbnail}" alt="${parkinglot.thumbnail}" width="120" />
        </c:if>
    </div>
    <button type="submit" class="mb-3 btn btn-primary">
        <form:hidden path="id" />
        <form:hidden path="thumbnail" />
        <c:choose>
            <c:when test="${parkinglot.id != null}" >
                Cập nhật
            </c:when>
            <c:otherwise>
                Thêm
            </c:otherwise>
        </c:choose>
    </button>
</form:form>

