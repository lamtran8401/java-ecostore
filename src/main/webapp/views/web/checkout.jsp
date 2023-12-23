<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-cart"/>
<c:url var="APIOrderurl" value="/api-web-order"/>
<c:url var="CheckoutURL" value="/thanh-toan"/>
<html>
<head>
    <title>Thanh toán</title>
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
                    <li>Giỏ hàng</li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //page -->
    <!-- checkout page -->
    <c:if test="${empty CART || CART.size() == 0 }">
        <c:if test="${not empty message}">
            <div class="text-center alert alert-${alert} mr-auto ml-auto mt-5" style="width: 50%">${message}</div>
        </c:if>
        <c:if test="${empty message}">
            <div class="text-center alert alert-warning mr-auto ml-auto mt-5" style="width: 50%">Không có sản phẩm nào trong giỏ hàng của bạn.</div>
        </c:if>

        <div class="row justify-content-center">
            <a class="btn btn-info pt-1 pb-1 pr-4 pl-4" href="<c:url value="/trang-chu"/>">Tiếp tục mua sắm</a>
        </div>
    </c:if>
    <c:if test="${CART.size() > 0 }">
        <div class="privacy py-sm-5 py-4">
            <div class="container py-xl-4 py-lg-2">
                <!-- tittle heading -->
                <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                    <span>G</span>iỏ
                    <span>H</span>àng
                </h3>
                <!-- //tittle heading -->
                <div class="checkout-right">
                    <h4 class="mb-sm-4 mb-3">Giỏ hàng bạn có
                        <span><span class="size-checkout">${CART.size()}</span> sản phẩm</span>
                    </h4>
                    <div class="table-responsive">
                        <table class="timetable_sub" id="mycheckout">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                                <th>Thành tiền</th>
                                <th>Xóa</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${CART.data()}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td class="invert-image">
                                        <a href="<c:url value='/chi-tiet-san-pham?id=${item.productId}'/>">
                                            <img src="<c:url value='${item.productImage}'/>" class="td-img">
                                        </a>
                                    </td>
                                    <td><a href="<c:url value='/chi-tiet-san-pham?id=${item.productId}'/>"
                                           class="text-secondary">${item.productName}</a></td>
                                    <td>
                                        <form>
                                            <input type="hidden" name="productId"
                                                   value="${item.productId}"/>
                                        <input type="number" class="form-control quantity-checkout text-center m-auto" name="quantity" style="width: 50%"
                                               value="${item.quantity}" min="1">
                                        </form>
                                    </td>
                                    <td class="item_discount">${item.unitPrice}</td>
                                    <td class="item_discount">${item.unitPrice * item.quantity}</td>
                                    <td>
                                        <form>
                                            <button class="btn btn-danger btn-sm remove-item-checkout mt-2"><i
                                                    class="fa fa-times"></i></button>
                                            <input type="hidden" name="productId"
                                                   value="${item.productId}"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <h4 class="mt-3 mb-3 text-right">Tổng:
                        <span><span class="total-checkout item_discount">${CART.total()}</span></span>
                    </h4>
                </div>
                <div class="checkout-left">
                    <div class="address_form_agile mt-sm-5 mt-4">
                        <h4 class="mb-sm-4 mb-3">Thông tin giao hàng</h4>
                        <form id="formSubmit" class="creditly-card-form agileinfo_form">
                            <div class="creditly-wrapper wthree, w3_agileits_wrapper">
                                <div class="information-wrapper">
                                    <div class="first-row">
                                        <div class="controls form-group">
                                            <input class="billing-address-name form-control" type="text" name="customerName"
                                                   placeholder="Họ tên" required oninvalid="this.setCustomValidity('Hãy nhập họ tên của bạn.')"
                                                   oninput="this.setCustomValidity('')" autofocus>
                                        </div>
                                        <div class="controls form-group">
                                            <input type="tel" class="form-control" placeholder="Só điện thoại"  pattern="(\+84|0)\d{9,10}"
                                                   name="phone" required oninvalid="this.setCustomValidity('Hãy nhập số điện thoại (10 - 11 chữ , bắt đầu từ 0 hoặc 84)!')"
                                                   oninput="this.setCustomValidity('')">
                                        </div>
                                        <div class="controls form-group">
                                            <input type="text" class="form-control" placeholder="Địa chỉ" name="address"
                                                   required oninvalid="this.setCustomValidity('Hãy nhập địa chỉ của bạn.')"
                                                   oninput="this.setCustomValidity('')" autofocus>
                                        </div>
                                        <div class="controls form-group">
                                            <input type="text" class="form-control" placeholder="Lời nhắn" name="message">
                                        </div>
                                    </div>
                                    <div class="payment container mb-4">
                                        <c:forEach var="item" items="${payments}">
                                            <div class="custom-control custom-radio mb-1">
                                                <input type="radio" class="custom-control-input" id="radio_${item.id}"
                                                       name="paymentId" value="${item.id}" required>
                                                <label class="custom-control-label" for="radio_${item.id}">${item.name}</label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <button id="addOrder" class="submit check_out btn">Giao hàng tới địa chỉ này</button>
                                </div>
                            </div>
                            <input type="hidden" name="userId" value="${USERMODEL.id}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <!-- //checkout page -->
<script>
    function loadCheckout(data) {
        $('#mycheckout tbody').html('');
        let totalPrice = 0;
        let size = 0;
        for (let i = 0; i < data.length; i++) {
            var row = [];
            row.push(
                '<tr><td>',
                i+1,
                '</td><td class="invert-image"><a href="',
                '/chi-tiet-san-pham?id=',
                data[i].productId,
                '"><img src="',
                '' + data[i].productImage,
                '" class="td-img"></a></td><td><a href="',
                '/chi-tiet-san-pham?id=',
                data[i].productId,
                '" class="text-secondary">',
                data[i].productName,
                '</a></td><td><form><input type="hidden" name="productId" value="',
                data[i].productId,
                '"/><input type="number" class="form-control quantity-checkout text-center m-auto" name="quantity" style="width: 50%" value="',
                data[i].quantity,
                '" min="1"></form></td><td class="item_discount">',
                data[i].unitPrice,
                '</td><td class="item_discount">',
                data[i].unitPrice * data[i].quantity,
                '</td><td><form><button class="btn btn-danger btn-sm remove-item-checkout mt-2"><i class="fa fa-times"></i></button> <input type="hidden" name="productId" value="',
                data[i].productId,
                '"/></form></td></tr>'
            )
            $('#mycheckout tbody').append(row.join(""));
            totalPrice += data[i].unitPrice * data[i].quantity;
            size += data[i].quantity;
        }
        if(size == 0) {
            location.reload();
            return;
        }
        $('.total-checkout').text(totalPrice)
        $('.size-checkout').text(size);
        let arraydiscount = document.getElementsByClassName("item_discount");
        for (let i = 0; i < arraydiscount.length; i++) {
            if (arraydiscount[i].innerHTML.includes("₫")) continue;
            arraydiscount[i].innerHTML = Number.parseFloat(arraydiscount[i].innerHTML).toFixed(0);
            arraydiscount[i].innerHTML = formatVND(arraydiscount[i].innerHTML)
        }
    }

    $('#mycheckout').on('change','.quantity-checkout', function (e) {
        e.preventDefault();
        let data = {}
        let formElement = this.form;
        let formData = $(formElement).serializeArray();
        $.each(formData, function (i, v) {
            data['' + v.name] = v.value
        });
        updateQuantityCheckout(data);
    })

    function updateQuantityCheckout(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null) {
                    loadCart(result);
                    loadCheckout(result);
                }
            },
            error: function (error) {
                $('.load').hide();
            }
        })
    }

    $('#mycheckout').on('click', '.remove-item-checkout', function (e) {
        e.preventDefault();
        let data = {}
        let formElement = this.form;
        let formData = $(formElement).serializeArray();
        $.each(formData, function (i, v) {
            data['' + v.name] = v.value
        });
        removeFromCheckout(data);
    })

    function removeFromCheckout(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null) {
                    loadCart(result);
                    loadCheckout(result);
                }
            },
            error: function (error) {
                $('.load').hide();
            }
        })
    }

    $('#addOrder').click(function (e) {
        if ($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {};
            let formData = $('#formSubmit').serializeArray();
            // vong lap
            $.each(formData, function (i, v) {
                data['' + v.name] = v.value
            });
            addOrder(data);
        }
    })

    function addOrder(data) {
        $('.load').show();
        $.ajax({
            url: '${APIOrderurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null) {
                    window.location.href = "${CheckoutURL}?message=order_success&alert=success";
                }
                else {
                    window.location.href = "${CheckoutURL}?message=order_fail&alert=danger";
                }

            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${CheckoutURL}?message=system_error&alert=danger";
            }
        })
    }
</script>
</body>
</html>
