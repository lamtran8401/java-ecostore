<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-information"/>
<c:url var="InformationURL" value="/quan-tri/thong-tin"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý thông tin cửa hàng</title>
</head>
<body>
<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">Danh sách thông tin cửa hàng</strong>
                    </div>
                    <div class="card-header">
                        <c:if test="${not empty message}">
                            <div class="text-center float-left alert alert-${alert}">${message}</div>
                        </c:if>
                        <div class="float-right">
                            <a href="#addInformationModal" class="btn btn-success" data-toggle="modal"><i
                                    class="fa fa-plus-circle" aria-hidden="true"></i> <span>Thêm</span></a>
                            <a href="#deleteInformationModal" class="btn btn-danger" data-toggle="modal"><i
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
                                <th class="text-center">Địa chỉ</th>
                                <th class="text-center">Số điện thoại</th>
                                <th class="text-center">Email</th>
                                <th class="text-center">Facebook</th>
                                <th class="text-center">Instagram</th>
                                <th class="text-center">Twitter</th>
                                <th class="text-center">Trạng thái</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${information}">
                                <tr>
                                    <td class="text-center">
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                    <label for="checkbox_${item.id}"></label>
                                                </span>
                                    </td>
                                    <td>${item.address}</td>
                                    <td>${item.email}</td>
                                    <td>${item.phone}</td>
                                    <td>${item.facebookLink}</td>
                                    <td>${item.instagramLink}</td>
                                    <td>${item.twitterLink}</td>

                                    <c:if test="${item.status == 1}">
                                        <td class="text-center"><span class="status text-success">&bull;</span>Hoạt động
                                        </td>
                                    </c:if>
                                    <c:if test="${item.status == 0}">
                                        <td class="text-center"><span class="status text-danger">&bull;</span>Tạm ngưng
                                        </td>
                                    </c:if>
                                    <td class="text-center">
                                        <a href="<c:url value='/quan-tri/thong-tin?id=${item.id}'/>" class="edit"><i
                                                class="fa fa-pencil"
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
<div id="addInformationModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="formSubmit">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm thông tin cửa hàng</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="row form-group">
                        <label>Địa chỉ</label>
                        <input type="text" class="form-control" name="address" required oninvalid="this.setCustomValidity('Hãy nhập địa chỉ!')"
                               oninput="this.setCustomValidity('')" autofocus>
                    </div>
                    <div class="row form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="email" required oninvalid="this.setCustomValidity('Hãy nhập email!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <div class="row form-group">
                        <label>Số điện thoại</label>
                        <input type="text" class="form-control" name="phone" required oninvalid="this.setCustomValidity('Hãy nhập số điện thoại!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <div class="row form-group">
                        <label>Facebook</label>
                        <input type="text" class="form-control" name="facebookLink" required oninvalid="this.setCustomValidity('Hãy nhập link FB!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <div class="row form-group">
                        <label>Instagram</label>
                        <input type="text" class="form-control" name="instagramLink" required oninvalid="this.setCustomValidity('Hãy nhập link Instagram!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <div class="row form-group">
                        <label>Twitter</label>
                        <input type="text" class="form-control" name="twitterLink" required oninvalid="this.setCustomValidity('Hãy nhập link Twitter!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                    <div class="row form-group">
                        <label>Trạng thái</label>
                        <select id="status" name="status" class="form-control">
                            <option value="1">Hoạt động</option>
                            <option value="0">Tạm ngưng</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <button id="addInformation" type="submit" class="btn btn-success">Thêm</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteInformationModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Xóa thông tin cửa hàng </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Bạn chắc chắn muốn xóa những thông tin này?</p>
                    <p class="text-warning"><small>Hành động này sẽ không thể khôi phục lại.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <button id="deleteInformation" type="submit" class="btn btn-danger">Xóa</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $('#addInformation').click(function (e) {
        if ($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {}; // mang object name: value
            let formData = $('#formSubmit').serializeArray();
            // vong lap
            $.each(formData, function (i, v) {
                data['' + v.name] = v.value
            });
            addInformation(data);
        }
    })

    $('#deleteInformation').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        // lay data khi check vao cac checkbox
        let dataArray = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val(); // lay value cua input checked
        }).get();
        if (dataArray.length != 0) {
            data['ids'] = dataArray;
            deleteInformation(data);
        }
    })

    function deleteInformation(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result)
                    window.location.href = "${InformationURL}?message=delete_success&alert=success";
                else
                    window.location.href = "${InformationURL}?message=delete_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${InformationURL}?message=system_error&alert=danger";
            }
        })
    }

    function addInformation(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null)
                    window.location.href = "${InformationURL}?id="+result.id+"&message=insert_success&alert=success";
                else
                    window.location.href = "${InformationURL}?message=insert_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${InformationURL}?message=system_error&alert=danger";
            }
        })
    }
</script>
</body>
</html>
