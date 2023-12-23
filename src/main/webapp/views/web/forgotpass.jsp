<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-web-user"/>
<c:url var="ForgotPassUrl" value="/quen-mat-khau"/>
<html>
<head>
    <title>Quên mật khẩu</title>
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
                    <li>Quên Mật Khẩu</li>
                </ul>
            </div>
        </div>
    </div>
    <!-- page -->
    <!--login-->
    <div class="container">
        <div class="row justify-content-center">
            <div class="modal-body col-sm-6">
                <c:if test="${not empty message}">
                    <div class="text-center alert alert-${alert}">${message}</div>
                </c:if>
                <form id="formSubmit" action="" method="get">
                    <div class="form-group">
                        <label for="email" class="col-form-label">Email</label>
                        <input type="email" class="form-control" id="email" placeholder=" " name="email" required
                               oninvalid="this.setCustomValidity('Hãy nhập email của bạn!')"
                               oninput="this.setCustomValidity('')" autofocus>
                    </div>
                    <div class="right-w3l">
                        <button id="forgotPassBtn" type="submit" class="form-control">Gửi</button>
                    </div>
                    <p class="text-center dont-do mt-3">Bạn chưa có tài khoản?
                        <a href="<c:url value='/dang-ky'/>">
                            Đăng ký ngay</a>
                    </p>
                </form>
            </div>
        </div>

    </div>
    <!--login-->
    <script>
        $('#forgotPassBtn').click(function (e) {
            if($('#formSubmit')[0].checkValidity()) {
                e.preventDefault();
                forgotPass()
            }
        })

        function forgotPass() {
            $('.load').show();
            let data = $('#email').val();
            $.ajax({
                url: '${APIurl}'+ '?email=' + data,   //đường dẫn kèm email =? trong jsp
                type: 'GET',
                // contentType: 'application/json',
                // data: JSON.stringify(data),
                // dataType: 'json',
                success: function (result) {
                    $('.load').hide();
                    if(result !== null)
                        window.location.href = "${ForgotPassUrl}?message=forgotpass_sent_email_success&alert=success";
                    else
                        window.location.href = "${ForgotPassUrl}?message=no_found_email&alert=danger";
                },
                error: function (error) {
                    $('.load').hide();
                    window.location.href = "${ForgotPassUrl}?message=system_error&alert=danger";
                }
            })
        }
    </script>

</body>
</html>
