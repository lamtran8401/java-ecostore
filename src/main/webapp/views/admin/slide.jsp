<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-banner"/>
<c:url var="BannerURL" value="/quan-tri/banner"/>
<html>
<head>
    <title>Quản lý banner</title>
</head>
<body>
<!-- Header-->

<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">Danh sách banner</strong>
                    </div>
                    <div class="card-header">
                        <c:if test="${not empty message}">
                            <div class="text-center float-left alert alert-${alert}">${message}</div>
                        </c:if>
                        <div class="float-right">
                            <a href="#addSupplierModal" class="btn btn-success" data-toggle="modal"><i
                                    class="fa fa-plus-circle" aria-hidden="true"></i> <span>Thêm</span></a>
                            <a href="#deletePaymentModal" class="btn btn-danger" data-toggle="modal"><i
                                    class="fa fa-trash-o" aria-hidden="true"></i> <span>Xóa</span></a>
                        </div>
                    </div>
                    <div class="card-body">
                        <table id="bootstrap-data-table-export" class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th class="text-center">
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="selectAll">
                                                    <label for="selectAll"></label>
                                                </span>
                                </th>
                                <th class="text-center">Tựa đề</th>
                                <th class="text-center">Mô tả</th>
                                <th class="text-center">Hình ảnh    </th>
                                <th class="text-center">Thứ tự hiển thị</th>
                                <th class="text-center">Trạng thái</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${slides}">
                                <tr>
                                    <td class="text-center">
                                                  <span class="custom-checkbox">
                                                    <input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                    <label for="checkbox_${item.id}"></label>
                                                </span>
                                    </td>
                                    <td>${item.title}</td>
                                    <td>${item.description}</td>
                                    <td class="text-center"><img src="<c:url value='${item.imageLink}'/>" class="td-img"
                                                                 alt="Not found">
                                    </td>
                                    <td class="text-center">${item.displayorder}</td>
                                    <c:if test="${item.status == 0}">
                                        <td class="text-center"><span class="status text-danger">&bull;</span>Không hoạt động
                                        </td>
                                    </c:if>
                                    <c:if test="${item.status == 1}">
                                        <td class="text-center"><span class="status text-success">&bull;</span>Hoạt động
                                        </td>
                                    </c:if>
                                    <td class="text-center">
                                        <a href="<c:url value='/quan-tri/banner?id=${item.id}'/>" class="edit"><i class="fa fa-pencil"
                                                                                 aria-hidden="true" data-toggle="tooltip"
                                                                                 title="Chỉnh sửa"></i></a>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->


<!-- Add Modal HTML -->
<div id="addSupplierModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="formSubmit">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm banner</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class=" row form-group">
                        <label>Tiêu đề</label>
                        <input type="text" class="form-control" name="title" required>
                    </div>
                    <div class="row form-group">
                        <label>Mô tả</label>
                        <input type="text" class="form-control" name="description" required>
                    </div>
                    <div class="row form-group">
                        <label for="imageLink">Hình ảnh</label>
                            <input type="file" class="form-control"
                                   id="imageLink" name="imageLink"
                                   accept="image/png, image/jpeg" required>
                    </div>
                    <div class="row form-group">
                        <label>Thứ tự</label>
                        <input type="text" class="form-control" name="displayorder" required>
                    </div>

                    <div class="row form-group">
                        <label>Trạng thái</label>
                        <select name="status" id="" class="form-control">
                            <option value="1">Hoạt động</option>
                            <option value="0">Tạm khóa</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <button id="addSlide" class="btn btn-success">Thêm</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deletePaymentModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form >
                <div class="modal-header">
                    <h4 class="modal-title">Xóa banner </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Bạn chắc chắn muốn xóa những banner này?</p>
                    <p class="text-warning"><small>Hành động này sẽ không thể khôi phục lại.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <button id="deleteSlide" type="submit" class="btn btn-danger">Xóa</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $('#addSlide').click(function (e) {
        if($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {}; // mang object name: value
            let formData = $('#formSubmit').serializeArray();
            // vong lap
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
                    addSlide(data);
                };
                reader.readAsDataURL(files);
            } else
                addSlide(data);
        }
    })

    function addSlide(data) {
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
                    window.location.href = "${BannerURL}?id="+result.id + "&message=insert_success&alert=success";
                else
                    window.location.href = "${BannerURL}?message=insert_slide_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${BannerURL}?message=system_error&alert=danger";
            }
        })
    }

    $('#deleteSlide').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        // lay data khi check vao cac checkbox
        let dataArray = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val(); // lay value cua input checked
        }).get();
        if (dataArray.length != 0) {
            data['ids'] = dataArray;
            deleteSlide(data);
        }
    })

    function deleteSlide(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if(result)
                    window.location.href = "${BannerURL}?message=delete_success&alert=success";
                else
                    window.location.href = "${BannerURL}?message=delete_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${BannerURL}?message=system_error&alert=danger";
            }
        })
    }


</script>
</body>
</html>
