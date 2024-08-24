<%-- 
    Document   : itemsAddOrUpdate
    Created on : Aug 23, 2024, 4:27:36 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<c:url value="/${itemEName}" var="action" />

<form:form method="post" action="${action}" modelAttribute="${itemEName}">
    <c:forEach items="${fields}" var="item" >
        <div class="mb-3 mt-3">
            <label for="${field}" class="form-label text-capitalize">${field}: </label>
            <form:input path="${field}" type="text" class="form-control" id="${field}" placeholder="Enter ${field}" name="${field}" />
            <form:errors path="${field}" cssClass="alert alert-danger" element="div" />
        </div>
    </c:forEach>

    <button type="submit" class="mb-3 btn btn-primary">
        <form:hidden path="id" />
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

