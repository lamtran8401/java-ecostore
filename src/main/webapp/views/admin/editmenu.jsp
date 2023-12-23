<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-menu"/>
<c:url var="MenuUrl" value="/quan-tri/menu"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa danh mục</title>
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
                        <strong class="card-title">Chỉnh sửa danh mục</strong>
                    </div>
                    <div class="card-body card-block">
                        <form id="formSubmit" action="" method="post" enctype="multipart/form-data" class="form-horizontal">
                            <div class="row form-group">

                                <div class="col col-md-3"><label class=" form-control-label">Tên</label></div>
                                <div class="col-12 col-md-9"><input type="text"
                                                                    name="name" value="${menuModel.name}" class="form-control"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">Đường dẫn danh mục</label></div>
                                <div class="col-12 col-md-9"><input type="text"
                                                                    name="link" value="${menuModel.link}" class="form-control"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">Loại danh mục</label></div>
                                <div class="col-12 col-md-9">
                                    <select name="menuTypeId" class="form-control">
                                        <c:forEach var="item" items="${menuTypeModels}">
                                            <c:if test="${item.id == menuModel.menuTypeId}">
                                                <option value="${item.id}" selected>${item.name}</option>
                                            </c:if>
                                            <c:if test="${item.id != menuModel.menuTypeId}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:if>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">Trạng thái
                                </label></div>
                                <div class="col-12 col-md-9">
                                    <select name="status" class="form-control">
                                        <c:if test="${menuModel.status == 1}">
                                            <option value="1" selected>Hoạt động</option>
                                            <option value="0">Tạm ngưng</option>
                                        </c:if>
                                        <c:if test="${menuModel.status == 0}">
                                            <option value="1">Hoạt động</option>
                                            <option value="0" selected>Tạm ngưng</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <button id="updateMenu" type="submit" class="btn btn-primary">
                                    <i class="fa fa-dot-circle-o"></i> Lưu
                                </button>
                            </div>
                            <input type="hidden" value="${menuModel.id}" id="id" name="id" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->
<script>
    $('#updateMenu').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        let formData = $('#formSubmit').serializeArray();
        $.each(formData, function(i,v) {
            data[''+v.name] = v.value
        });
        console.log(data)
        updateMenu(data);
    })

    function updateMenu(data) {
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
                    window.location.href = "${MenuUrl}?id="+result.id+"&message=update_success&alert=success";
                else
                    window.location.href = "${MenuUrl}?id="+result.id+"&message=update_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${MenuUrl}?message=system_error&alert=danger";
            }
        })
    }
</script>
</body>
</html>
