<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-admin-order"/>
<c:url var="OrderUrl" value="/quan-tri/don-hang"/>

<html>
<head>
    <title>Chỉnh sửa đơn hàng</title>
</head>
<body>
            <div class="content mt-3">
                <div class="animated fadeIn">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Chỉnh sửa đơn hàng</strong>
                                </div>
                                <c:if test="${not empty message}">
                                    <div class="text-center float-left alert alert-${alert}">${message}</div>
                                </c:if>
                                <div class="card-body card-block">
                                    <form id="formSubmit" action="" method="post" enctype="" class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label  class=" form-control-label">Mã
                                                đơn hàng</label></div>
                                            <div class="col-12 col-md-9"><input type="text"
                                                                                name="id" class="form-control" value="${order.id}" disabled></div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label">Ngày tạo</label></div>
                                            <div class="col-12 col-md-9"><input type="text"
                                                                                name="createdDate" class="form-control" value="${order.createdDate}" disabled></div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label">Tên khách hàng</label></div>
                                            <div class="col-12 col-md-9"><input type="text"
                                                                                name="customerName" class="form-control" value="${order.customerName}"></div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label">Số điện thoại</label></div>
                                            <div class="col-12 col-md-9"><input type="text"
                                                                                name="phone" class="form-control" value="${order.phone}"></div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label">Địa chỉ</label></div>
                                            <div class="col-12 col-md-9"><input type="text"
                                                                                name="address" class="form-control" value="${order.address}"></div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label">Lời nhắn</label></div>
                                            <div class="col-12 col-md-9"><input type="text"
                                                                                name="message" class="form-control" value="${order.message}"></div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label">Tổng giá trị</label></div>
                                            <div class="col-12 col-md-9 "><input type="text"
                                                                                name="totalPrice" class="form-control" value="${order.totalPrice}"></div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label"> Phương thức thanh toán</label></div>
                                            <div class="col-12 col-md-9">
                                                <select name="paymentId" class="form-control">
                                                    <c:forEach var="item" items="${payments}">
                                                        <c:if test="${item.id == order.paymentId}">
                                                            <option value="${item.id}" selected>${item.name}</option>
                                                        </c:if>
                                                        <c:if test="${item.id != order.paymentId}">
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:if>
                                                    </c:forEach>

                                                </select>
                                            </div>

                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label class=" form-control-label">Trạng
                                                thái</label></div>
                                            <div class="col-12 col-md-9">
                                                <select name="status" class="form-control">
                                                    <c:if test="${order.status == 0}">
                                                        <option value="0" selected>Đang chờ xác nhận</option>
                                                        <option value="1">Đã xác nhận</option>
                                                        <option value="2">Đang giao</option>
                                                        <option value="3">Thành công</option>
                                                        <option value="4">Đã hủy</option>
                                                    </c:if>
                                                    <c:if test="${order.status == 1}">
                                                        <option value="0">Đang chờ xác nhận</option>
                                                        <option value="1"selected>Đã xác nhận</option>
                                                        <option value="2">Đang giao</option>
                                                        <option value="3">Thành công</option>
                                                        <option value="4">Đã hủy</option>
                                                    </c:if>
                                                    <c:if test="${order.status == 2}">
                                                        <option value="0">Đang chờ xác nhận</option>
                                                        <option value="1">Đã xác nhận</option>
                                                        <option value="2" selected>Đang giao</option>
                                                        <option value="3">Thành công</option>
                                                        <option value="4">Đã hủy</option>
                                                    </c:if>
                                                    <c:if test="${order.status == 3}">
                                                        <option value="0">Đang chờ xác nhận</option>
                                                        <option value="1">Đã xác nhận</option>
                                                        <option value="2">Đang giao</option>
                                                        <option value="3" selected>Thành công</option>
                                                        <option value="4">Đã hủy</option>
                                                    </c:if>
                                                    <c:if test="${order.status == 4}">
                                                        <option value="0">Đang chờ xác nhận</option>
                                                        <option value="1">Đã xác nhận</option>
                                                        <option value="2">Đang giao</option>
                                                        <option value="3">Thành công</option>
                                                        <option value="4" selected>Đã hủy</option>
                                                    </c:if>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="row justify-content-center">
                                            <button type="submit" id="updateOrder" class="btn btn-primary btn-sm mr-2">
                                                <i class="fa fa-dot-circle-o"></i> Lưu
                                            </button>
                                        </div>
                                        <input type="hidden" value="${order.id}" id="id" name="id" />

                                    </form>
                                </div>



                            </div>
                        </div>
                    </div>
                </div><!-- .animated -->
            </div><!-- .content -->
            <script>
                $('#updateOrder').click(function (e) {
                    e.preventDefault();
                    let data = {}; // mang object name: value
                    let formData = $('#formSubmit').serializeArray();
                    $.each(formData, function(i,v) {
                        data[''+v.name] = v.value
                    });
                    console.log(data)
                    updateOrder(data);
                })

                function updateOrder(data) {
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
                                window.location.href = "${OrderUrl}?idorder=${order.id}&message=update_success&alert=success";
                            else
                                window.location.href = "${OrderUrl}?idorder=${order.id}&message=update_fail&alert=danger";
                        },
                        error: function (error) {
                            $('.load').hide();
                            window.location.href = "${OrderUrl}?idorder=${order.id}&message=system_error&alert=danger";
                        }
                    })
                }
            </script>
</body>
</html>
