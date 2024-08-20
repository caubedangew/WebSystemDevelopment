<%-- 
    Document   : parkinglotAddOrUpdate
    Created on : Aug 19, 2024, 11:42:59 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" action="/action_page.php" modelAttribute="parkinglot">
    <div class="mb-3 mt-3">
        <label for="address" class="form-label">Address: </label>
        <input type="text" class="form-control" id="address" placeholder="" name="address">
    </div>
    <div class="mb-3">
        <label for="quantity" class="form-label">Quantity:</label>
        <input type="number" class="form-control" id="quantity" placeholder="" name="quantity">
    </div>
    <div class="mb-3">
        <label for="price" class="form-label">Price:</label>
        <input type="number" class="form-control" id="price" placeholder="" name="price">
    </div>
    <button type="submit" class="mb-3 btn btn-primary">
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

