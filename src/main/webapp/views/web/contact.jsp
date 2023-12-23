<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-web-contact"/>
<c:url var="ContactURL" value="/lien-he"/>
<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liên hệ</title>
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
                <li>Liên hệ</li>
            </ul>
        </div>
    </div>
</div>
<!-- //page -->

<!-- contact -->
<div class="contact py-sm-5 py-4">
    <div class="container py-xl-4 py-lg-2">
        <!-- tittle heading -->
        <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
            <span>L</span>iên
            <span>H</span>ệ
        </h3>
        <!-- //tittle heading -->
        <div class="row contact-grids agile-1 mb-5">
            <div class="col-sm-4 contact-grid agileinfo-6 mt-sm-0 mt-2">
                <div class="contact-grid1 text-center">
                    <div class="con-ic">
                        <i class="fas fa-map-marker-alt rounded-circle"></i>
                    </div>
                    <h4 class="font-weight-bold mt-sm-4 mt-3 mb-3">Địa chỉ</h4>
                    <div class="address">
                        ${information.address}
                    </div>
                </div>
            </div>
            <div class="col-sm-4 contact-grid agileinfo-6 my-sm-0 my-4">
                <div class="contact-grid1 text-center">
                    <div class="con-ic">
                        <i class="fas fa-phone rounded-circle"></i>
                    </div>
                    <h4 class="font-weight-bold mt-sm-4 mt-3 mb-3">Hotline</h4>
                    <div class="phone">
                        ${information.phone}
                    </div>
                </div>
            </div>
            <div class="col-sm-4 contact-grid agileinfo-6">
                <div class="contact-grid1 text-center">
                    <div class="con-ic">
                        <i class="fas fa-envelope-open rounded-circle"></i>
                    </div>
                    <h4 class="font-weight-bold mt-sm-4 mt-3 mb-3">Email</h4>
                    <div class="email">
                        <a href="mailto:${information.email}" class="text-dark">${information.email}</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- form -->
        <form id="formSubmit" action="#" method="post">
            <c:if test="${not empty message}">
                <div class="text-center alert alert-${alert}">${message}</div>
            </c:if>
            <div class="contact-grids1 w3agile-6">
                <div class="row">
                    <div class="col-md-6 col-sm-6 contact-form1 form-group">
                        <label class="col-form-label">Họ tên</label>
                        <input type="text"  class="form-control" name="fullname" required
                               oninvalid="this.setCustomValidity('Hãy nhập họ tên!')"
                               oninput="this.setCustomValidity('')" autofocus>
                    </div>
                    <div class="col-md-6 col-sm-6 contact-form1 form-group">
                        <label class="col-form-label">Email</label>
                        <input type="email"  class="form-control" name="email" required
                               oninvalid="this.setCustomValidity('Hãy nhập địa chỉ email!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                </div>
                <div class="contact-me animated wow slideInUp form-group">
                    <label class="col-form-label">Bạn đang cần gì?</label>
                    <textarea name="content"  class="form-control" required oninvalid="this.setCustomValidity('Hãy nhập nội dung yêu cầu!')"
                              oninput="this.setCustomValidity('')"></textarea>
                </div>
                <div  class="contact-form right-w3l">
                    <button id="addContact" type="submit" class="form-control">Gửi</button>
                </div>
            </div>
        </form>
        <!-- //form -->
    </div>
</div>
<!-- //contact -->

<!-- map -->
<div class="map mt-sm-0 mt-4">
    <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.2588533443673!2d106.78566704968605!3d10.867905960433896!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175276398969f7b%3A0x9672b7efd0893fc4!2zxJDhuqFpIEjhu41jIE7DtG5nIEzDom0!5e0!3m2!1svi!2s!4v1605144120671!5m2!1svi!2s"
            width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false"
            tabindex="0"></iframe>
</div>
<!-- //map -->
<script>
    $('#addContact').click(function (e) {
        if($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {}; // mang object name: value
            let formData = $('#formSubmit').serializeArray();
            // vong lap
            $.each(formData, function (i, v) {
                data['' + v.name] = v.value
            });
            addContact(data);
        }
    })
    function addContact(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if(result !== null)
                    window.location.href = "${ContactURL}?message=contact_sent_success&alert=success";
                else
                    window.location.href = "${ContactURL}?message=contact_sent_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${ContactURL}?message=system_error&alert=danger";
            }
        })
    }
</script>
</body>
</html>
