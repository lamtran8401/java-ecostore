<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-banner"/>
<c:url var="BannerURL" value="/quan-tri/banner"/>
<html>
<head>
    <title>Chỉnh sửa banner</title>
</head>
<body>
<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">Chỉnh sửa banner</strong>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="text-center float-left alert alert-${alert}">${message}</div>
                    </c:if>
                    <div class="card-body card-block">
                        <form id="formSubmit" class="form-horizontal">
                            <div class="row form-group">

                                <div class="col col-md-3"><label
                                                                 class=" form-control-label">Tiêu đề</label></div>
                                <div class="col-12 col-md-9"><input type="text"
                                                                    name="title" class="form-control" value="${slide.title}"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label
                                                                 class=" form-control-label">Mô tả</label></div>
                                <div class="col-12 col-md-9"><input type="text"
                                                                    name="description" class="form-control" value="${slide.description}"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label
                                        class=" form-control-label">Hình ảnh</label></div>
                                <div class="col-12 col-md-2">
                                    <img src="<c:url value='${slide.imageLink}'/>" class=" mb-1" style=" max-width: 130px; max-height: 70px;">

                                </div>
                                <div class="col-12 col-md-5"><div class="custom-input-file">
                                    <input type="file"
                                           id="imageLink" name="imageLink"
                                           accept="image/png, image/jpeg" value="${slide.imageLink}">
                                </div></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label
                                                                 class=" form-control-label">Thứ tự hiện thị</label></div>
                                <div class="col-12 col-md-9"><input type="text"
                                                                    name="displayorder" class="form-control" value="${slide.displayorder}"></div>
                            </div>


                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">Trạng thái
                                </label></div>
                                <div class="col-12 col-md-9">
                                    <select name="status" class="form-control">
                                        <c:if test="${slide.status == 1}">
                                            <option value="1" selected>Hoạt động</option>
                                            <option value="0">Tạm ngưng</option>
                                        </c:if>
                                        <c:if test="${slide.status == 0}">
                                            <option value="1">Hoạt động</option>
                                            <option value="0" selected>Tạm ngưng</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <button id="updateSlide" type="submit" class="btn btn-primary">
                                    <i class="fa fa-dot-circle-o"></i> Lưu
                                </button>
                                <input type="hidden" value="${slide.id}" id="id" name="id" />
                            </div>
                        </form>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('#updateSlide').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        let formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data['' + v.name] = v.value
        });

        data['uploadFile'] = {}
        var files = $('#imageLink')[0].files[0];
        if(files != undefined) {
            var reader = new FileReader();
            reader.onload = function (e) {
                data['uploadFile']['base64'] = e.target.result;
                data['uploadFile']['name'] = files.name;
                updateSlide(data);
            };
            reader.readAsDataURL(files);
        } else
            updateSlide(data);
    })

    function updateSlide(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null)
                    window.location.href = "${BannerURL}?id=${slide.id}&message=update_success&alert=success";
                else
                    window.location.href = "${BannerURL}?id=${slide.id}&message=insert_slide_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${BannerURL}?id=${slide.id}&message=system_error&alert=danger";
            }
        })
    }
</script>

</body>
</html>
