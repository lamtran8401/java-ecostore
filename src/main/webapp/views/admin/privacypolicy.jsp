<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-privacy-policy"/>
<c:url var="PrivacyPolicyUrl" value="/quan-tri/chinh-sach-bao-mat"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý chính sách và bảo mật</title>
</head>
<body>
<!-- Header-->

<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">Danh sách chính sách và bảo mật</strong>
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
                                <th class="text-center">Nội dung</th>
                                <th class="text-center">Trạng thái</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${privacyPolicyModels}">
                                <tr>
                                    <td class="text-center">
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                    <label for="checkbox_${item.id}"></label>
                                                </span>
                                    </td>
                                    <td><span class="show-less">${item.content}</span></td>
                                    <c:if test="${item.status == 1}">
                                        <td class="text-center"><span class="status text-success">&bull;</span>Hoạt động
                                        </td>
                                    </c:if>
                                    <c:if test="${item.status == 0}">
                                        <td class="text-center"><span class="status text-danger">&bull;</span>Tạm ngưng
                                        </td>
                                    </c:if>
                                    </td>
                                    <td class="text-center">
                                        <a href="<c:url value='/quan-tri/chinh-sach-bao-mat?id=${item.id}'/>" class="edit"><i class="fa fa-pencil"
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
                    <h4 class="modal-title">Thêm chính sách và bảo mật</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Nội dung</label>
                        <textarea rows="10" id="content" name="content" class="form-control"></textarea>
                    </div>

                    <div class="form-group">
                        <label>Trạng thái</label>
                        <select name="status" class="form-control">
                            <option value="1">Hoạt động</option>
                            <option value="0">Tạm khóa</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <button id="addPrivacyPolicy" type="submit" class="btn btn-success">Thêm</button>
                </div>

            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deletePaymentModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Xóa chính sách và bảo mật </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Bạn chắc chắn muốn xóa những chính sách và bảo mật này?</p>
                    <p class="text-warning"><small>Hành động này sẽ không thể khôi phục lại.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <button id="deletePrivacyPolicy" type="submit" class="btn btn-danger" >Xóa</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    let editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('content', {
            language: "vi"
        });
    });

    $('#addPrivacyPolicy').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        let formData = $('#formSubmit').serializeArray();
        // vong lap
        $.each(formData, function (i, v) {
            data['' + v.name] = v.value
        });
        data['content'] = editor.getData();
        addPrivacyPolicy(data);
    })

    function addPrivacyPolicy(data) {
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
                    window.location.href = "${PrivacyPolicyUrl}?id="+result.id+"&message=insert_success&alert=success";
                else
                    window.location.href = "${PrivacyPolicyUrl}?message=insert_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${PrivacyPolicyUrl}?message=system_error&alert=danger";
            }
        })
    }

    $('#deletePrivacyPolicy').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        // lay data khi check vao cac checkbox
        let dataArray = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val(); // lay value cua input checked
        }).get();
        if (dataArray.length != 0) {
            data['ids'] = dataArray;
            deletePrivacyPolicy(data);
        }
    })

    function deletePrivacyPolicy(data) {
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
                    window.location.href = "${PrivacyPolicyUrl}?message=delete_success&alert=success";
                else
                    window.location.href = "${PrivacyPolicyUrl}?message=delete_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${PrivacyPolicyUrl}?message=system_error&alert=danger";
            }
        })
    }


</script>
</body>
</html>
