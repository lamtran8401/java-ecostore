<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Không tìm thấy trang yêu cầu</title>
    <link rel="shortcut icon" href="<c:url value='/images/logo.png'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/fontawesome-all.css'/>">
</head>
<style>
    body {
        background-color: #95c2de;
    }

    .mainbox {
        background-color: #95c2de;
        margin: auto;
        height: 600px;
        width: 800px;
        position: relative;
    }

    .err {
        color: #ffffff;
        font-family: 'Nunito Sans', sans-serif;
        font-size: 11rem;
        position:absolute;
        left: 20%;
        top: 8%;
    }

    .far {
        position: absolute;
        font-size: 8.5rem;
        left: 42%;
        top: 15%;
        color: #ffffff;
    }

    .err2 {
        color: #ffffff;
        font-family: 'Nunito Sans', sans-serif;
        font-size: 11rem;
        position:absolute;
        left: 68%;
        top: 8%;
    }

    .msg {
        text-align: center;
        font-family: 'Nunito Sans', sans-serif;
        font-size: 1.6rem;
        position:absolute;
        left: 16%;
        top: 45%;
        width: 75%;
    }

    a {
        text-decoration: none;
        color: white;
    }

    a:hover {
        text-decoration: underline;
    }
</style>
<body>
<div class="mainbox">
    <div class="err">4</div>
    <i class="far fa-question-circle fa-spin"></i>
    <div class="err2">4</div>
    <div class="msg">Xin lỗi, trang bạn đang tìm kiếm
        không tồn tại!<p>Quay lại <a href="<c:url value="/trang-chu"/>">trang chủ</a> nhé!</p></div>
</div>
</body>
</html>
