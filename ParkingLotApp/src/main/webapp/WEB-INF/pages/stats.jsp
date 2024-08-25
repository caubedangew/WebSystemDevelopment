<%-- 
    Document   : stats
    Created on : Aug 24, 2024, 10:04:56 PM
    Author     : ThienVu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1">THỐNG KÊ DOANH THU</h1>

<div class="row">
    <div class="col-md-5 col-12">
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Tên sản phẩm</th>
                <th>Tổng doanh thu</th>
            </tr>
            <c:forEach items="${revenues}" var="r">
                <tr>
                    <td>${r[0]}</td>
                    <td>${r[1]}</td>
                    <td>${String.format("%,d", r[2])} VNĐ</td>
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
        labels.push('${r[1]}');
        data.push(${r[2]});
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
