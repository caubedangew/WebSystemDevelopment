<%-- 
    Document   : stats
    Created on : Aug 24, 2024, 10:04:56 PM
    Author     : ThienVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1">Thống kê doanh thu theo từng
    <c:choose >
        <c:when test="${periodValue == null}"> tháng (hoặc quý)</c:when>
        <c:otherwise> bãi đỗ xe</c:otherwise>
    </c:choose>
</h1>

<form method="get">
    <div class="input-group">
        <span class="input-group-text">Năm</span>
        <input type="number" min="1990" max="2024" value="${year}" class="form-control" placeholder="Nhập năm..." name="year">
        <span class="input-group-text">Tiêu chí lọc</span>
        <select class="form-select" name="period" id="period">
            <c:choose>
                <c:when test="${period == 'MONTH'}" >
                    <option value="MONTH" selected>Tháng</option>
                    <option value="QUARTER">Quý</option>
                </c:when>
                <c:otherwise>
                    <option value="MONTH">Tháng</option>
                    <option value="QUARTER" selected>Quý</option>
                </c:otherwise>
            </c:choose>
        </select>
        <c:if test="${periodValue != null}">
            <input type="number" min="1" max="${period == 'QUARTER'? 4: 12}" value="${periodValue}" 
                   class="form-control" placeholder="${period == "QUARTER"? 'Nhập quý...' : 'Nhập tháng...'}" name="periodValue">
        </c:if>
        <button type="submit" class="btn btn-secondary">Lọc</button>
    </div>

</form>

<div class="row">
    <div class="col-md-5 col-12">
        <table class="table">
            <tr>
                <c:choose>
                    <c:when test="${periodValue == null}">
                        <th>STT</th>
                        <th>
                            <c:choose>
                                <c:when test="${period == 'MONTH'}" >Tháng</c:when>
                                <c:otherwise>Quý</c:otherwise>
                            </c:choose>
                        </th>
                    </c:when> 
                    <c:otherwise>
                        <th>ID</th>
                        <th>Địa chỉ</th>
                        </c:otherwise>
                    </c:choose>
                <th>Tổng doanh thu</th>
            </tr>
            <c:forEach items="${revenues}" var="r" varStatus="loop">
                <tr>
                    <c:choose>
                        <c:when test="${periodValue == null}">
                            <td>${loop.count}</td>
                            <td>${r[0]}</td>
                        </c:when> 
                        <c:otherwise>
                            <td>${r[0]}</td>
                            <td>${r[2]}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${String.format("%,.0f", r[1])} VNĐ</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-7 col-12">
        <canvas id="myChart"></canvas>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    let labels = [];
    let data = [];
    <c:forEach items="${revenues}" var="r">
    labels.push('${r[0]}');
    data.push(${r[1]});
    </c:forEach>

    window.onload = function () {
        const ctx = document.getElementById('myChart');

        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                        label: '# Doanh thu',
                        data: data,
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

    }
</script>
