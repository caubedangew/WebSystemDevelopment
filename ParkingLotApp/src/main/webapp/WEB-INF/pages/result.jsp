<%-- 
    Document   : result
    Created on : Aug 29, 2024, 8:08:18 AM
    Author     : ThienVu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 th:if='${errMsg}' th:text='${errMsg}' style='color: red;'></h3>
<div th:unless='${errMsg}'>
    <h3 style='color: green;'>Success!</h3>
    <div>Id.: <span th:text='${id}' /></div>
    <div>Status: <span th:text='${status}' /></div>
    <div>Charge id.: <span th:text='${chargeId}' /></div>
    <div>Balance transaction id.: <span th:text='${balance_transaction}' /></div>
</div>
<a href='/checkout.html'>Checkout again</a>
