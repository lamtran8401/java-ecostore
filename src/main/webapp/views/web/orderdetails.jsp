<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-web-order"/>
<c:url var="OrderUrl" value="/theo-doi-don-hang"/>
<html>
<head>
    <title>Chi tiết đơn hàng</title>
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
                    <li>Chi tiết đơn hàng</li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container product-sec1 px-sm-4 px-3 py-sm-5 py-3 mb-4 mt-5">
        <article class="card">
            <header class="card-header"> Đơn hàng của tôi / Theo dõi </header>

                    <c:if test="${not empty message}">
                        <div class="text-center alert alert-${alert} mr-auto ml-auto mt-5" style="width: 50%">${message}</div>
                    </c:if>

<%--                    <div class="row justify-content-center mb-4">--%>
<%--                        <a class="btn btn-info pt-1 pb-1 pr-4 pl-4" href="<c:url value="/trang-chu"/>">Tiếp tục mua sắm</a>--%>
<%--                    </div>--%>


                <div class="card-body">
                <h6><strong>Mã đơn hàng: </strong><span>${order.id}</span></h6>
                <article class="card mt-3">
                    <div class="card-body row">
                        <div class="col"> <strong>Địa chỉ:</strong> <br>${order.address}</div>
                        <div class="col"> <strong>Tên khách hàng:</strong> <br>${order.customerName}</div>

                        <c:choose>
                            <c:when test="${order.status == 0}">
                                <div class="col"> <strong>Trạng thái:</strong> <br> Đang chờ xác nhận </div>
                            </c:when>
                            <c:when test="${order.status == 1}">
                                <div class="col"> <strong>Trạng thái:</strong> <br> Đã xác nhận </div>
                            </c:when>
                            <c:when test="${order.status == 2}">
                                <div class="col"> <strong>Trạng thái:</strong> <br> Đang giao </div>
                            </c:when>
                            <c:when test="${order.status == 3}">
                                <div class="col"> <strong>Trạng thái:</strong> <br> Đã giao hàng </div>
                            </c:when>
                            <c:when test="${order.status == 4}">
                                <div class="col"> <strong>Trạng thái:</strong> <br> Đã hủy </div>
                            </c:when>
                        </c:choose>
                        <div class="col"> <strong>Dự kiến nhận vào:</strong> <br><span class="date">${order.createdDate}</span> </div>
                    </div>
                </article>
                    <c:choose>
                    <c:when test="${order.status == 0}">
                    <div class="track">
                        <div class="step active"> <span class="icon"> <i class="fa fa-clock fa-stack"></i> </span> <span
                                class="text">Đang chờ xác nhận</span> </div>
                        <div class="step"> <span class="icon"> <i class="fa fa-check fa-stack"></i> </span> <span
                                class="text">Đã xác nhận</span> </div>
                        <div class="step"> <span class="icon"> <i class="fa fa-truck fa-stack"></i> </span> <span
                                class="text"> Đang giao </span> </div>
                        <div class="step"> <span class="icon"> <i class="fa fa-box fa-stack"></i> </span> <span class="text">Đã giao hàng</span> </div>
                    </div>
                    <hr>
                    </c:when>
                    <c:when test="${order.status == 1}">
                    <div class="track">
                        <div class="step active"> <span class="icon"> <i class="fa fa-clock fa-stack"></i> </span> <span
                                class="text">Đang chờ xác nhận</span> </div>
                        <div class="step active"> <span class="icon"> <i class="fa fa-check fa-stack"></i> </span> <span
                                class="text">Đã xác nhận</span> </div>
                        <div class="step"> <span class="icon"> <i class="fa fa-truck fa-stack"></i> </span> <span
                                class="text"> Đang giao </span> </div>
                        <div class="step"> <span class="icon"> <i class="fa fa-box fa-stack"></i> </span> <span class="text">Đã giao hàng</span> </div>
                    </div>
                    <hr>
                    </c:when>
                    <c:when test="${order.status == 2}">
                    <div class="track">
                        <div class="step active"> <span class="icon"> <i class="fa fa-clock fa-stack"></i> </span> <span
                                class="text">Đang chờ xác nhận</span> </div>
                        <div class="step active"> <span class="icon"> <i class="fa fa-check fa-stack"></i> </span> <span
                                class="text">Đã xác nhận</span> </div>
                        <div class="step active"> <span class="icon"> <i class="fa fa-truck fa-stack"></i> </span> <span
                                class="text"> Đang giao </span> </div>
                        <div class="step"> <span class="icon"> <i class="fa fa-box fa-stack"></i> </span> <span class="text">Đã giao hàng</span></div>
                        </div>
                        <hr>
                    </c:when>
                    <c:when test="${order.status == 3}">
                        <div class="track">
                            <div class="step active"> <span class="icon"> <i class="fa fa-clock fa-stack"></i> </span> <span
                                    class="text">Đang chờ xác nhận</span> </div>
                            <div class="step active"> <span class="icon"> <i class="fa fa-check fa-stack"></i> </span> <span
                                    class="text">Đã xác nhận</span> </div>
                            <div class="step active"> <span class="icon"> <i class="fa fa-truck fa-stack"></i> </span> <span
                                    class="text"> Đang giao </span> </div>
                            <div class="step active"> <span class="icon"> <i class="fa fa-box fa-stack"></i> </span> <span class="text">Đã giao hàng</span> </div>
                        </div>
                        <hr>
                    </c:when>
                    <c:when test="${order.status == 4}">
                            <div class="track">
                                <div class="step active"> <span class="icon"> <i class="fa fa-clock fa-stack"></i> </span> <span
                                        class="text">Đơn hàng đã đặt</span> </div>
                                <div class="step active"></div>
                                <div class="step active"></div>
                                <div class="step active"> <span class="icon"> <i class="fa fa-times fa-stack"></i> </span> <span class="text">Đã hủy</span> </div>
                            </div>
                            <hr>
                    </c:when>
                </c:choose>

                <ul class="row">
                            <c:forEach var="itemdetail" items="${order.list}">
                                <li class="col-md-4">
                                    <figure class="itemside mb-3">
                                        <div class="aside"><img src="<c:url value='${itemdetail.product.images.get(0)}'/>" class="td-img"></div>
                                        <figcaption class="info align-self-center">
                                            <a href="<c:url value='/chi-tiet-san-pham?id=${itemdetail.product.id}'/>"><p class="title">${itemdetail.product.name}</p></a>
                                            <span class="text-muted item_price">${itemdetail.unitPrice}</span>
                                            <p class="quantity">Số lượng: <span>${itemdetail.quantity}</span></p>
                                        </figcaption>
                                    </figure>
                                </li>
                            </c:forEach>
                    </ul>
                <hr>
                <c:if test="${order.status == 4}">
                        <div class="text-left">Bạn đã hủy đơn hàng này!</div>
                        <div class="row justify-content-between">
                            <a class="btn btn-info pr-5 pl-5 m-3" href="<c:url value="/trang-chu"/>">Tiếp tục mua sắm</a>
                        </div>
                        <hr>
                </c:if>
                <div class="col"> <strong>Tổng tiền: </strong><span class="text-muted item_price">${order.totalPrice}</span> </div>
                <hr>
                <c:if test="${order.status == 0}">
                        <div class="row justify-content-between">
                            <a href="#updateOrder" class="btn btn-danger pr-5 pl-5 m-3" data-toggle="modal"><i
                                    class="fa fa-trash mr-2" aria-hidden="true"></i>Hủy đơn hàng</a>
                        </div>
                        <hr>
                        <div id="updateOrder" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form id="formSubmit" >
                                        <div class="modal-header">
                                            <h4 class="modal-title">Hủy đơn hàng </h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Bạn chắc chắn muốn hủy đơn hàng này?</p>
                                            <p class="text-warning"><small>Hành động này sẽ không thể khôi phục lại.</small></p>
                                        </div>
                                        <div class="modal-footer">
                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Quay lại">
                                            <input type="hidden" value="${order.id}" id="id" name="id" />
                                            <button id="updateStatus" type="submit" class="btn btn-danger">Hủy đơn hàng</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                </c:if>
                </div>

                <div class="card-body">
                    <div class="row justify-content-between">
                        <a href="<c:url value='/theo-doi-don-hang?page=1'/>" class="btn btn-warning m-3" data-abc="true"> <i
                                class="fa fa-chevron-left"></i> Quay lại lịch sử đơn hàng</a>
                    </div>
                </div>
        </article>

    </div>
<script>
    function formatDate(element) {
        let d = new Date(element)
        // cong them 5 ngay
        // 5*86400000 = 432000000
        let dayEnd = d.valueOf() + 432000000;
        var date = new Date(dayEnd);
        return date.toLocaleDateString('vi-VN','UTC+7');
    }

    let arrayDate = document.getElementsByClassName("date");
    for (let i = 0; i < arrayDate.length; i++)
        arrayDate[i].innerHTML = formatDate(arrayDate[i].innerHTML)




    $('#updateStatus').click(function (e) {
        if($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {};
            let formData = $('#formSubmit').serializeArray();
            $.each(formData, function (i, v) {
                data['' + v.name] = v.value
            });
            <%--data['id'] = ${user.id};--%>
            console.log(data);
            updateStatus(data);
        }
    })

    function updateStatus(data) {
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
                    window.location.href = "${OrderUrl}?id=${order.id}&message=cancel_order_success&alert=success";
                else
                    window.location.href = "${OrderUrl}?id=${order.id}&message=cancel_order_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${OrderUrl}?id=${order.id}&message=system_error&alert=danger";
            }
        })
    }
</script>
</body>

</html>
