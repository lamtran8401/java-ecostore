<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-admin-products"/>
<c:url var="ProductURL" value="/quan-tri/san-pham"/>
<html>
<head>
    <title>Quản lý sản phẩm</title>
</head>
<body>
<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">Danh sách sản phẩm</strong>
                    </div>
                    <div class="card-header">
                        <c:if test="${not empty message}">
                            <div class="text-center float-left alert alert-${alert}">${message}</div>
                        </c:if>
                        <div class="float-right">
                            <a href="#addProductModal" class="btn btn-success" data-toggle="modal"><i
                                    class="fa fa-plus-circle" aria-hidden="true"></i> <span>Thêm</span></a>
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
                                <th class="text-center">Tên SP</th>
                                <th class="text-center">Hình ảnh</th>
                                <th class="text-center">Giá</th>
                                <th class="text-center">Giảm giá</th>
                                <th class="text-center">Thể loại</th>
                                <th class="text-center">Hãng</th>
                                <th class="text-center">Trạng thái</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${products}">
                                <tr>
                                    <td class="text-center">
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="checkbox_${item.id}" name="options[]"
                                                           value="${item.id}">
                                                    <label for="checkbox_${item.id}"></label>
                                                </span>
                                    </td>
                                    <td>${item.name}</td>
                                    <td class="text-center"><img src="<c:url value='${item.images.get(0)}'/>"
                                                                 class="td-img"
                                                                 alt="Not found">
                                    </td>
                                    <td class="text-center">${item.price}</td>
                                    <td class="text-center">${item.discount}%</td>
                                    <td class="text-center">${item.category.name}</td>
                                    <td class="text-center">${item.supplierModel.name}</td>
                                    <c:if test="${item.status == 1}">
                                        <td class="text-center"><span class="status text-success">&bull;</span>Hoạt động
                                        </td>
                                    </c:if>
                                    <c:if test="${item.status == 0}">
                                        <td class="text-center"><span class="status text-danger">&bull;</span>Tạm ngưng
                                        </td>
                                    </c:if>
                                    <td class="text-center">
                                        <a href="<c:url value='/quan-tri/san-pham?id=${item.id}'/>" class="edit"><i
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
<div id="addProductModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="formSubmit">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm sản phẩm</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Tên sản phẩm</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>
                    <div class="form-group">
                        <label>Giá</label>
                        <input type="number" class="form-control" name="price" required min="0">
                    </div>
                    <div class="form-group">
                        <label>Giảm giá (%)</label>
                        <input type="number" class="form-control" name="discount" required min="0" max="100">
                    </div>

                    <div class="form-group">
                        <label>Mô tả</label>
                        <textarea rows="30" id="description" name="description"
                                  class="form-control"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Thể loại</label>
                        <select name="categoryId" class="form-control">
                            <c:forEach var="item" items="${categories}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Hãng</label>
                        <select name="supplierId" class="form-control">
                            <c:forEach var="item" items="${suppliers}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="images" class="col-sm-3 form-control-label">Hình
                            ảnh</label>
                        <input type="file" id="images" name="images" multiple="" class="form-control"
                               accept="image/png, image/jpeg" required>
                    </div>
                    <div class="form-group">
                        <label>Trạng thái</label>
                        <select name="status" class="form-control">
                            <option value="1">Hoạt động</option>
                            <option value="0">Tạm dừng</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <input type="submit" id="addProduct" class="btn btn-success" value="Thêm">
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    let editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('description', {
            language: "vi"
        });
    });

    function fileBase64(file) {
        return new Promise((resolve, reject) => {
            let reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function() {
                resolve(reader.result);
            };
            reader.onerror = function(error) {
                reject(error);
            };
        });
    }

    $('#addProduct').click(async function (e) {
        if ($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {}; // mang object name: value
            let formData = $('#formSubmit').serializeArray();
            // vong lap
            $.each(formData, function (i, v) {
                data['' + v.name] = v.value
            });
            data['description'] = editor.getData();

            const files = $('#images')[0].files;
            data['uploadFiles'] = [];
            if (files.length != 0) {
                for (let i = 0; i < files.length; i++) {
                    const base64 = await fileBase64(files[i]);
                    data['uploadFiles'].push({'base64': base64, 'name': files[i].name})
                }
                addProduct(data);
            }
        }
    })

    function addProduct(data) {
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
                    window.location.href = "${ProductURL}?id=" + result.id + "&message=insert_success&alert=success";
                else
                    window.location.href = "${ProductURL}?message=insert_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${ProductURL}?message=system_error&alert=danger";
            }
        })
    }
</script>
</body>
</html>
