<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-web-user"/>
<c:url var="HomeURL" value="/trang-chu"/>
<c:url var="ForgotPassURL" value="/quen-mat-khau"/>
<c:url var="LoginURL" value="/dang-nhap"/>
<c:url var="NewPassURL" value="/mat-khau-moi"/>
<html>
<head>
    <title>Đổi mật khẩu</title>
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
                <li>Đổi Mật Khẩu</li>
            </ul>
        </div>
    </div>
</div>
<!-- //page -->

<!--forgot pass-->
<div class="container">
    <div class="row justify-content-center">
        <div class="modal-body col-sm-6">
            <form action="doi-mat-khau" method="post">
                <c:if test="${not empty message}">
                    <div class="text-center alert alert-${alert}">${message}</div>
                </c:if>
                <div class="form-group">
                    <label for="currentpassword" class="col-form-label">Mật khẩu cũ</label>
                    <input type="password" class="form-control" name="currentpassword" id="currentpassword"
                           required oninvalid="this.setCustomValidity('Hãy nhập mật khẩu hiện tại của bạn!')"
                           oninput="this.setCustomValidity('')" autofocus>
                </div>
                <div class="form-group">
                    <label for="password" class="col-form-label">Mật khẩu mới</label>
                    <input type="password" class="form-control" name="password" id="password"
                           required oninvalid="this.setCustomValidity('Mật khẩu từ 6 ký tự!')"
                           oninput="this.setCustomValidity('')" minlength="6">
                </div>
                <div class="form-group">
                    <label for="password2" class="col-form-label">Nhập lại mật khẩu mới</label>
                    <input type="password" class="form-control" name="password2"
                           id="password2" required oninvalid="this.setCustomValidity('Mật khẩu không khớp!')"
                           oninput="this.setCustomValidity('')">
                </div>
                <div class="right-w3l">
                    <button type="submit" class="form-control">Lưu thay đổi</button>
                </div>
                <input type="hidden" name="userid" value="${userid}">
            </form>
        </div>
    </div>

</div>
<!--forgot pass-->
<script>
    window.onload = function () {
        document.getElementById("password").onchange = validatePassword;
        document.getElementById("password2").onchange = validatePassword;
    }

    function validatePassword() {
        var pass2 = document.getElementById("password2").value;
        var pass1 = document.getElementById("password").value;
        if (pass1 != pass2)
            document.getElementById("password2").setCustomValidity("Passwords Don't Match");
        else
            document.getElementById("password2").setCustomValidity('');
    }
</script>
</body>
</html>
