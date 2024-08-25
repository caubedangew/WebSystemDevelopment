<%-- 
    Document   : receiptAddOrUpdate
    Created on : Aug 24, 2024, 4:11:01 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<c:url value="/receipt" var="action" />

<form:form method="post" action="${action}" modelAttribute="receipt">
    <form:errors path="*" cssClass="alert alert-danger" element="div" />
    <div class="mb-3 mt-3">
        <label for="userId" class="form-label">UserId: </label>
        <form:select path="userId" class="form-select">
            <c:forEach items="${user}" var="u">
                <c:choose>
                    <c:when test="${receipt.userId.id == u.id}">
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
        <label for="parkingspaceId" class="form-label">Parking Space:</label>
        <form:select path="parkingspaceId" class="form-select">
            <c:forEach items="${parkingspace}" var="p">
                <c:choose>
                    <c:when test="${receipt.parkingspaceId.id == p.id}">
                        <option selected value="${p.id}">No. ${p.stt} ${p.parkinglotId.address}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${p.id}">No. ${p.stt} ${p.parkinglotId.address}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="parkingspaceId" cssClass="alert alert-danger" element="div" />
    </div>
    <div class="mb-3">
        <label for="timeInterval" class="form-label">Time Interval:</label>
        <form:input path="timeInterval" class="form-control" type="number" name="timeInterval" id="timeInterval" placeholder="Enter time interval" />
        <form:errors path="timeInterval" cssClass="alert alert-danger" element="div" />
    </div>
    <button type="submit" class="mb-3 btn btn-primary">
        <form:hidden path="id" />
        <form:hidden path="createdDate" />
        <form:hidden path="updatedDate" />
        <form:hidden path="totalAmount" />
        <c:choose>
            <c:when test="${receipt.id != null}" >
                Cập nhật
            </c:when>
            <c:otherwise>
                Thêm
            </c:otherwise>
        </c:choose>
    </button>
</form:form>
