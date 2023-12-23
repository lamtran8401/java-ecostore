<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Điều khoản sử dụng</title>
</head>
<body>
    <!-- page -->
    <div class="services-breadcrumb">
        <div class="agile_inner_breadcrumb">
            <div class="container">
                <ul class="w3_short">
                    <li>
                        <a href="<c:url value='/trang-chu'/>">Trang chủ</a>
                        <i>|</i>
                    </li>
                    <li>Điều Khoản Sử Dụng </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //page -->

    <!-- terms -->
    <div class="terms py-sm-5 py-4">
        <div class="container py-xl-4 py-lg-2">
            <!-- tittle heading -->
            <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                <span>Đ</span>iều
                <span>K</span>hoản
                <span>S</span>ử
                <span>D</span>ụng </h3>
            <!-- //tittle heading -->
           <div class="content">
               ${model.content}
           </div>
        </div>
    </div>
    <!-- //terms -->
</body>
</html>
