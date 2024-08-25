<%-- 
    Document   : parkingspaceAddOrUpdate
    Created on : Aug 19, 2024, 11:42:59 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<c:url value="/parkingspace" var="action" />

<form:form method="post" action="${action}" modelAttribute="parkingspace">
    <div class="mb-3 mt-3">
        <label for="stt" class="form-label">STT: </label>
        <form:input path="stt" type="number" class="form-control" id="stt" placeholder="" name="stt" />
        <form:errors path="stt" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="status" class="form-label">Quantity:</label>
        <form:select path="status" class="form-select">
            <c:forEach items="${status}" var="s">
                <c:choose>
                    <c:when test="${parkingspace.status == s}">
                        <option selected value="${s}">${s}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${s}">${s}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="status" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="parkinglotId" class="form-label">Parking Lot Address:</label>
        <form:select path="parkinglotId" class="form-select" >
            <c:forEach items="${parkinglot}" var="p">
                <c:choose>
                    <c:when test="${parkingspace.parkinglotId.id == p.id}">
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
    <button type="submit" class="mb-3 btn btn-primary">
        <form:hidden path="id" />
        <c:choose>
            <c:when test="${parkingspace.id != null}" >
                Cập nhật
            </c:when>
            <c:otherwise>
                Thêm
            </c:otherwise>
        </c:choose>
    </button>
</form:form>