<%-- 
    Document   : commentAddOrUpdate
    Created on : Aug 27, 2024, 4:58:07 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<c:url value="/comment" var="action" />

<form:errors path="*" cssClass="alert alert-danger" element="div" />
<form:form method="post" action="${action}" modelAttribute="comment">
    <div class="mb-3 mt-3">
        <label for="userId" class="form-label">User: </label>
        <form:select path="userId" class="form-select">
            <c:forEach items="${user}" var="u">
                <c:choose>
                    <c:when test="${u.id == comment.userId.id}">
                        <option selected value="${u.id}">${u.username}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${u.id}">${u.username}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="userId" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="parkinglotId" class="form-label">Parking Lot:</label>
        <form:select path="parkinglotId" class="form-select">
            <c:forEach items="${parkinglot}" var="p">
                <c:choose>
                    <c:when test="${p.id == comment.parkinglotId.id}">
                        <option selected value="${p.id}">${p.address}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${p.id}">${p.address}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="parkinglotId" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="content" class="form-label">Content:</label>
        <form:input path="content" type="text" class="form-control" id="content" name="content" />
        <form:errors path="content" cssClass="alert alert-danger" element="div" />
    </div>
    <button type="submit" class="mb-3 btn btn-primary">
        <form:hidden path="id" />
        <c:choose>
            <c:when test="${comment.id != null}" >
                Cập nhật
            </c:when>
            <c:otherwise>
                Thêm
            </c:otherwise>
        </c:choose>
    </button>
</form:form>

