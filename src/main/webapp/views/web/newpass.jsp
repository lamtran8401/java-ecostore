<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-web-user"/>
<c:url var="HomeURL" value="/trang-chu"/>
<c:url var="ForgotPassURL" value="/quen-mat-khau"/>
<c:url var="LoginURL" value="/dang-nhap"/>
<c:url var="NewPassURL" value="/mat-khau-moi"/>



<html>
<head>
    <title>Mật khẩu mới</title>
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
                    <li>Mật Khẩu Mới</li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //page -->

    <!--forgot pass-->
    <div class="container">
        <div class="row justify-content-center">
            <div class="modal-body col-sm-6">
                <form id="formSubmit">
                    <c:if test="${not empty message}">
                        <div class="text-center alert alert-${alert}">${message}</div>
                    </c:if>
                    <div class="form-group">
                        <label for="password1" class="col-form-label">Mật khẩu mới</label>
                        <input type="password" class="form-control" name="password" id="password1"
                               required oninvalid="this.setCustomValidity('Mật khẩu từ 6 ký tự!')"
                               oninput="this.setCustomValidity('')" autofocus minlength="6">
                    </div>
                    <div class="form-group">
                        <label for="password2" class="col-form-label">Nhập lại mật khẩu mới</label>
                        <input type="password" class="form-control"
                               id="password2" required oninvalid="this.setCustomValidity('Mật khẩu không khớp!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <div class="right-w3l">
                        <button id="newPassBtn" type="submit" class="form-control">Lưu thay đổi</button>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <!--forgot pass-->
    <script>
        window.onload = function () {
            document.getElementById("password1").onchange = validatePassword;
            document.getElementById("password2").onchange = validatePassword;
        }

        function validatePassword() {
            var pass2 = document.getElementById("password2").value;
            var pass1 = document.getElementById("password1").value;
            if (pass1 != pass2)
                document.getElementById("password2").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("password2").setCustomValidity('');
        }
    </script>
    <script>
        $('#newPassBtn').click(function (e) {
            if($('#formSubmit')[0].checkValidity()) {
                e.preventDefault();
                let data = {};
                let formData = $('#formSubmit').serializeArray();
                $.each(formData, function (i, v) {
                    data['' + v.name] = v.value
                });
                data['id'] = ${user.id};
                newPass(data);
            }
        })

        function newPass(data) {
            $('.load').show();
            $.ajax({
                url: '${APIurl}',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    $('.load').hide();
                    if(result !== null)
                        window.location.href = "${LoginURL}?message=change_pass_success&alert=success";
                    else
                        window.location.href = "${ForgotPassURL}?message=username_email_exist&alert=danger";
                },
                error: function (error) {
                    $('.load').hide();
                    window.location.href = "${NewPassURL}?message=system_error&alert=danger";
                }
            })
        }
    </script>
</body>
</html>
