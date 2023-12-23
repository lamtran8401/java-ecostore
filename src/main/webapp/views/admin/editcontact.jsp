<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-contact"/>
<c:url var="ContactURL" value="/quan-tri/lien-he"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa trang liên hệ</title>
</head>
<body>
<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <c:if test="${not empty message}">
                        <div class="text-center float-left alert alert-${alert}">${message}</div>
                    </c:if>
                    <div class="card-header">
                        <strong class="card-title">Chỉnh sửa liên hệ</strong>
                    </div>
                    <div class="card-body card-block">
                        <form id="formSubmit" action="" method="post" enctype="multipart/form-data" class="form-horizontal">
                            <div class="row form-group">
                                <div class="col col-md-3"><label
                                        class=" form-control-label">Họ và tên</label></div>
                                <div class="col-12 col-md-9"><input type="text"
                                                                    name="fullname" value="${contactModel.fullname}" class="form-control"></div>

                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label
                                        class=" form-control-label">Email</label></div>
                                <div class="col-12 col-md-9"><input type="text"
                                                                    name="email" value="${contactModel.email}" class="form-control"></div>

                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label
                                        class=" form-control-label">Nội dung</label></div>
                                <div class="col-12 col-md-9">
                                    <textarea rows="10" id="content" name="content" class="form-control">${contactModel.content}</textarea>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">Trạng thái</label></div>
                                <div class="col-12 col-md-9">
                                    <select id="status" name="status" class="form-control">
                                        <c:if test="${contactModel.status == 0}">
                                            <option value="0" selected>Chờ xử lý</option>
                                            <option value="1">Đang xử lý</option>
                                            <option value="2">Đã xử lý</option>
                                        </c:if>
                                        <c:if test="${contactModel.status == 1}">
                                            <option value="0" >Chờ xử lý</option>
                                            <option value="1" selected>Đang xử lý</option>
                                            <option value="2">Đã xử lý</option>
                                        </c:if>
                                        <c:if test="${contactModel.status == 2}">
                                            <option value="0" >Chờ xử lý</option>
                                            <option value="1" >Đang xử lý</option>
                                            <option value="2" selected>Đã xử lý</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <button id="updateContact" type="button" class="btn btn-primary">
                                    <i class="fa fa-dot-circle-o"></i> Lưu
                                </button>
                            </div>
                            <input type="hidden" value="${contactModel.id}" id="id" name="id" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->
<script>
    $('#updateContact').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        let formData = $('#formSubmit').serializeArray();
        // vong lap
        $.each(formData, function(i,v) {
            data[''+v.name] = v.value
        });
        updateContact(data);
    })

    function updateContact(data) {
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
                    window.location.href = "${ContactURL}?id="+result.id+"&message=update_success&alert=success";
                else
                    window.location.href = "${ContactURL}?id="+result.id+"&message=update_fail&alert=danger";
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
