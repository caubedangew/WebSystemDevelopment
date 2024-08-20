<%-- 
    Document   : login
    Created on : Aug 17, 2024, 10:53:04 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="<c:url value="/login"/>" method="post">
  <div class="mb-3 mt-3">
    <label for="username" class="form-label">Username: </label>
    <input type="username" class="form-control" id="username" placeholder="Tên tài khoản" name="username">
  </div>
  <div class="mb-3">
    <label for="pwd" class="form-label">Password:</label>
    <input type="password" class="form-control" id="pwd" placeholder="Mật khẩu" name="password">
  </div>
  <button type="submit" class="btn btn-primary mb-3">Đăng nhập</button>
</form>
