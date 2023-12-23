<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-web-feedback"/>
<c:url var="FeedbackURL" value="/danh-gia"/>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Phản hồi</title>
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
                <li>Phản Hồi</li>
            </ul>
        </div>
    </div>
</div>
<!-- //page -->

<!-- help -->
<div class="faqs-w3l py-sm-5 py-4">
    <div class="container py-xl-4 py-lg-2">
        <!-- tittle heading -->
        <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
            <span>Đ</span>ánh
            <span>G</span>iá
        </h3>
        <!-- //tittle heading -->
        <!-- feedback content -->
        <div class="wthree-help mb-sm-5 mb-4">
            <div class="agile-left-help">
                <c:if test="${not empty message}">
                    <div class="text-center alert alert-${alert}">${message}</div>
                </c:if>
                <h3 class="w3-head">Đánh giá của bạn về chúng tôi</h3>
                <form id="formSubmit">
                     <textarea name="content" required
                               oninvalid="this.setCustomValidity('Hãy nhập đánh giá của bạn!')"
                               oninput="this.setCustomValidity('')" placeholder="Đánh giá tại đây..."
                               autofocus></textarea>
                    <c:if test="${empty USERMODEL}">
                        <br>
                        <div class="mt-3">
                            <i>Vui lòng đăng nhập trước khi để lại đánh giá.</i>
                            <a href="<c:url value='/dang-nhap'/>" class="btn btn-primary  ml-1 pl-3 pr-3">
                                Đăng nhập ngay </a>
                        </div>
                    </c:if>
                    <c:if test="${not empty USERMODEL}">
                        <button id="addFeedback" type="submit" class="right-w3l">Gửi</button>
                    </c:if>

                </form>

            </div>
        </div>

        <!-- //feedback content -->
    </div>
    <!-- testimonials -->
    <c:if test="${feedbacks.size() > 0}">
        <div class="testimonials py-sm-5 py-4">
            <div class="container py-xl-4 py-lg-2">
                <!-- tittle heading -->
                <h3 class="tittle-w3l text-center text-white mb-lg-5 mb-sm-4 mb-3">
                    <span>P</span>hản
                    <span>H</span>ồi
                    <span>K</span>hách
                    <span>H</span>àng
                </h3>
                <!-- tittle heading -->
                <div class="row gallery-index">
                    <c:forEach var="item" items="${feedbacks}">

                        <div class="col-sm-6 med-testi-grid">
                            <div class="med-testi test-tooltip rounded p-4">
                                <p>${item.content}</p>
                            </div>
                            <div class="row med-testi-left my-5">
                                <div class="col-lg-2 col-3 w3ls-med-testi-img">
                                    <img src="<c:url value="${item.user.avatar}"/>" alt=" "
                                         class="img-fluid rounded-circle"/>
                                </div>
                                <div class="col-lg-10 col-9 med-testi-txt">
                                    <h4 class="font-weight-bold mt-3">${item.user.fullname}</h4>
                                    <!-- <p>fames ac turpis</p> -->
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
</div>
<!-- //testimonials -->
<script>
    $('#addFeedback').click(function (e) {
        if ($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {};
            let formData = $('#formSubmit').serializeArray();
            // vong lap
            $.each(formData, function (i, v) {
                data['' + v.name] = v.value
            });
            addFeedBack(data);
        }
    })

    function addFeedBack(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result === "")
                    window.location.href = "/dang-nhap?message=not_login&alert=danger";
                else if (result !== null)
                    window.location.href = "${FeedbackURL}?message=feedback_success&alert=success";
                else
                    window.location.href = "${FeedbackURL}?message=feedback_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${FeedbackURL}?message=system_error&alert=danger";
            }
        })
    }
</script>
</body>
</html>
