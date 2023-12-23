<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-admin-products"/>
<c:url var="ProductURL" value="/quan-tri/san-pham"/>
<html>
<head>
    <title>Chỉnh sửa sản phẩm</title>
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
                        <strong class="card-title">Chỉnh sửa sản phẩm</strong>
                    </div>
                    <div class="card-body card-block">
                        <form id="formSubmit" class="form-horizontal">
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="name"
                                                                 class=" form-control-label">Tên sản phẩm</label></div>
                                <div class="col-12 col-md-9"><input type="text" id="name"
                                                                    name="name" class="form-control"
                                                                    value="${product.name}"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="price"
                                                                 class=" form-control-label">Giá (VNĐ)</label></div>
                                <div class="col-12 col-md-9"><input type="number" id="price"
                                                                    name="price" class="form-control"
                                                                    value="${product.price}"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="discount"
                                                                 class=" form-control-label">Giảm giá (%)</label></div>
                                <div class="col-12 col-md-9"><input type="number" id="discount"
                                                                    name="discount" class="form-control"
                                                                    value="${product.discount}"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="description"
                                                                 class=" form-control-label">Mô tả</label></div>
                                <div class="col-12 col-md-9"> <textarea rows="30" id="description" name="description"
                                                                        class="form-control">${product.description}</textarea>
                                </div>

                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">Thể
                                    loại</label></div>
                                <div class="col-12 col-md-9">
                                    <select name="categoryId" class="form-control">
                                        <c:forEach var="item" items="${categories}">
                                            <c:if test="${item.id == product.categoryId}">
                                                <option value="${item.id}" selected>${item.name}</option>
                                            </c:if>
                                            <c:if test="${item.id != product.categoryId}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">Nhà
                                    cung cấp</label></div>
                                <div class="col-12 col-md-9">
                                    <select name="supplierId" class="form-control">
                                        <c:forEach var="item" items="${suppliers}">
                                            <c:if test="${item.id == product.supplierId}">
                                                <option value="${item.id}" selected>${item.name}</option>
                                            </c:if>
                                            <c:if test="${item.id != product.supplierId}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label
                                        class="form-control-label">Trạng thái</label></div>
                                <div class="col-12 col-md-9"><select name="status" class="form-control">
                                    <c:if test="${product.status == 1}">
                                        <option value="1" selected>Hoạt động</option>
                                        <option value="0">Tạm ngưng</option>
                                    </c:if>
                                    <c:if test="${product.status == 0}">
                                        <option value="1">Hoạt động</option>
                                        <option value="0" selected>Tạm ngưng</option>
                                    </c:if>
                                </select></div>
                            </div>
                            <div class="row form-group justify-content-start">
                                <div class="col col-md-3"><label class="form-control-label">Hình
                                    ảnh</label></div>
                                <div class="col-sm-12 col-md-2">
                                    <input type="file" id="images" name="images" multiple=""
                                           accept="image/png, image/jpeg">
                                </div>
                            </div>
                            <div class="row form-group">
                                <c:forEach var="item" items="${product.images}">
                                    <div class="col-3 mt-2">
                                        <img src="<c:url value='${item}'/>" class="td-img"/>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="mt-5 row justify-content-center">
                                <button type="submit" class="btn btn-primary" id="updateProduct">
                                    <i class="fa fa-dot-circle-o"></i> Lưu
                                </button>
                            </div>
                            <input type="hidden" name="id" value="${product.id}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->
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

    $('#updateProduct').click(async function (e) {
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
                updateProduct(data);
            } else updateProduct(data);
        }
    })

    function updateProduct(data) {
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
                    window.location.href = "${ProductURL}?id=" + result.id + "&message=update_success&alert=success";
                else
                    window.location.href = "${ProductURL}?id=" + result.id + "&message=update_fail&alert=danger";
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
