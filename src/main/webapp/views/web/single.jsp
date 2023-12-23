<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-web-comment"/>
<c:url var="ProductDetail" value="/chi-tiet-san-pham"/>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
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
                <li>Chi Tiết Sản Phẩm</li>
            </ul>
        </div>
    </div>
</div>
<!-- //page -->

<!-- Single Page -->
<div class="banner-bootom-w3-agileits py-5">
    <div class="container py-xl-4 py-lg-2">
        <!-- tittle heading -->
        <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
            <span>T</span>hông
            <span>T</span>in
            <span>S</span>ản
            <span>P</span>hẩm</h3>
        <!-- //tittle heading -->
        <div class="row">
            <div class="col-lg-5 col-md-8 single-right-left ">
                <div class="grid images_3_of_2">
                    <div class="flexslider">
                        <ul class="slides">
                            <c:forEach var="item" items="${product.images}">
                                <li data-thumb="<c:url value='${item}'/>">
                                    <div class="thumb-image">
                                        <img src="<c:url value='${item}'/>" data-imagezoom="true"
                                             class="img-fluid-detail" alt="">
                                    </div>
                                </li>
                            </c:forEach>

                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-7 single-right-left simpleCart_shelfItem">
                <h3 class="mb-3">${product.name}</h3>
                <p class="mb-3">
                    <span class="item_price">${product.price - product.price*(product.discount/100)}</span>
                    <del class="mx-2 font-weight-light item_discount">${product.price}</del>
                    <label>Miễn phí vận chuyển</label>
                </p>
                <div class="single-infoagile">
                    <ul>
                        <li class="mb-3">
                            Trả tiền khi đã kiểm tra hàng.
                        </li>
                        <li class="mb-3">
                            Giao hàng trong thời gian ngắn.
                        </li>
                    </ul>
                </div>
                <div class="product-single-w3l">
                    <p class="my-3">
                        <i class="far fa-hand-point-right mr-2"></i>
                        Giao hàng trong
                        <label>48 giờ</label></p>
                    ${product.description}
                    <p class="my-sm-4 my-3">
                        <i class="fas fa-retweet mr-3"></i>Ngân hàng trực tuyến & Thẻ tín dụng / Ghi nợ / ATM</p>
                </div>
                <div class="occasion-cart">
                    <div class="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
                        <form>
                            <fieldset>
                                <input type="hidden" name="productId" value="${product.id}"/>
                                <input type="hidden" name="productImage"
                                       value="${product.images.get(0)}"/>
                                <input type="hidden" name="unitPrice"
                                       value="${product.price - product.price*(product.discount/100)}"/>
                                <input type="hidden" name="productName"
                                       value="${product.name}"/>
                                <button class="btn button addToCart">Thêm vào giỏ hàng
                                </button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //Single Page -->

<div class="container product-sec1 mb-5 pt-5 pb-5" id="slider-product">
    <div class="row">
        <div class="col-md-12">
            <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                <span>S</span>ản
                <span>P</span>hẩm
                <span>T</span>ương
                <span>T</span>ự</h3>
            <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="0">
                <!-- Carousel indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                    <li data-target="#myCarousel" data-slide-to="4"></li>
                </ol>
                <!-- Wrapper for carousel items -->
                <div class="carousel-inner">
                    <c:if test="${product.list.size() > 0}">
                        <div class="carousel-item active">
                            <div class="row justify-content-center">
                                <c:forEach var="item" items="${product.list}" begin="0" end="3">
                                    <div class="col-sm-3">
                                        <div class="thumb-wrapper">
                                            <div class="img-box">
                                                <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>"><img
                                                        src="<c:url value='${item.images.get(0)}'/>" class="img-fluid"
                                                        alt=""></a>
                                            </div>
                                            <div class="thumb-content">
                                                <div class="item-info-product text-center border-top mt-4">
                                                    <h4 class="pt-1">
                                                        <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>">${item.name}</a>
                                                    </h4>
                                                    <div class="info-product-price my-2">
                                                        <span class="item_price">${item.price - item.price*(item.discount/100)}</span>
                                                        <br>
                                                        <del class="ml-0 item_discount">${item.price}</del>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${product.list.size() > 4}">
                        <div class="carousel-item">
                            <div class="row justify-content-center">
                                <c:forEach var="item" items="${product.list}" begin="4" end="7">
                                    <div class="col-sm-3">
                                        <div class="thumb-wrapper">
                                            <div class="img-box">
                                                <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>"><img
                                                        src="<c:url value='${item.images.get(0)}'/>" class="img-fluid"
                                                        alt=""></a>
                                            </div>
                                            <div class="thumb-content">
                                                <div class="item-info-product text-center border-top mt-4">
                                                    <h4 class="pt-1">
                                                        <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>">${item.name}</a>
                                                    </h4>
                                                    <div class="info-product-price my-2">
                                                        <span class="item_price">${item.price - item.price*(item.discount/100)}</span>
                                                        <br>
                                                        <del class="ml-0 item_discount">${item.price}</del>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${product.list.size() > 8}">
                        <div class="carousel-item">
                            <div class="row justify-content-center">
                                <c:forEach var="item" items="${product.list}" begin="8" end="11">
                                    <div class="col-sm-3">
                                        <div class="thumb-wrapper">
                                            <div class="img-box">
                                                <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>"><img
                                                        src="<c:url value='${item.images.get(0)}'/>" class="img-fluid"
                                                        alt=""></a>
                                            </div>
                                            <div class="thumb-content">
                                                <div class="item-info-product text-center border-top mt-4">
                                                    <h4 class="pt-1">
                                                        <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>">${item.name}</a>
                                                    </h4>
                                                    <div class="info-product-price my-2">
                                                        <span class="item_price">${item.price - item.price*(item.discount/100)}</span>
                                                        <br>
                                                        <del class="ml-0 item_discount">${item.price}</del>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${product.list.size() > 12}">
                        <div class="carousel-item">
                            <div class="row justify-content-center">
                                <c:forEach var="item" items="${product.list}" begin="12" end="15">
                                    <div class="col-sm-3">
                                        <div class="thumb-wrapper">
                                            <div class="img-box">
                                                <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>"><img
                                                        src="<c:url value='${item.images.get(0)}'/>" class="img-fluid"
                                                        alt=""></a>
                                            </div>
                                            <div class="thumb-content">
                                                <div class="item-info-product text-center border-top mt-4">
                                                    <h4 class="pt-1">
                                                        <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>">${item.name}</a>
                                                    </h4>
                                                    <div class="info-product-price my-2">
                                                        <span class="item_price">${item.price - item.price*(item.discount/100)}</span>
                                                        <br>
                                                        <del class="ml-0 item_discount">${item.price}</del>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${product.list.size() > 16}">
                        <div class="carousel-item">
                            <div class="row justify-content-center">
                                <c:forEach var="item" items="${product.list}" begin="16" end="19">
                                    <div class="col-sm-3">
                                        <div class="thumb-wrapper">
                                            <div class="img-box">
                                                <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>"><img
                                                        src="<c:url value='${item.images.get(0)}'/>" class="img-fluid"
                                                        alt=""></a>
                                            </div>
                                            <div class="thumb-content">
                                                <div class="item-info-product text-center border-top mt-4">
                                                    <h4 class="pt-1">
                                                        <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>">${item.name}</a>
                                                    </h4>
                                                    <div class="info-product-price my-2">
                                                        <span class="item_price">${item.price - item.price*(item.discount/100)}</span>
                                                        <br>
                                                        <del class="ml-0 item_discount">${item.price}</del>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <!-- Carousel controls -->
                </div>
                <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a class="carousel-control-next" href="#myCarousel" data-slide="next">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
</div>

<!-- Comment -->
<div class="container product-sec1 mb-5 pt-5 pb-5">
    <div class="row bootstrap snippets bootdeys">
        <div class="col-sm-12">
            <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                <span>K</span>hách
                <span>H</span>àng
                <span>N</span>hận
                <span>X</span>ét</h3>
            <div class="comment-wrapper mt-5">
                <form id="formSubmit" class="mb-5">
                            <textarea class="form-control" id="contentComment" name="content" rows="3" required
                                      oninvalid="this.setCustomValidity('Hãy nhập bình luận của bạn!')"
                                      oninput="this.setCustomValidity('')"
                                      placeholder="Bình luận tại đây..."></textarea>
                    <br>
                    <c:if test="${empty USERMODEL}">
                        <i>Vui lòng đăng nhập trước khi để lại bình luận.</i>
                        <a href="<c:url value='/dang-nhap'/>"
                           class="btn btn-primary pull-right ml-1 pl-3 pr-3 pt-2 pb-2">
                            Đăng nhập ngay </a>
                    </c:if>
                    <c:if test="${not empty USERMODEL}">
                        <button type="submit"
                                class="btn btn-info pull-right ml-1 float-left pl-3 pr-3 pt-2 pb-2"
                                id="addComment">Gửi
                        </button>
                    </c:if>
                    <input type="hidden" value="${USERMODEL.id}" name="userId">
                    <input type="hidden" value="${product.id}" name="productId">
                </form>
                <br>
                <hr>
                <div class="panel panel-info mt-5">
                    <div class="panel-body">
                        <div class="clearfix"></div>

                        <ul class="media-list" id="listComment">
                            <c:forEach var="item" items="${comments}">
                                <li class="media">
                                    <img src="<c:url value='${item.user.avatar}'/>" alt="" class="img-circle">
                                    <div class="media-body ml-3">
                                        <strong class="text-success mr-2">${item.user.fullname}</strong>
                                        <span class="text-muted pull-right">
											<small class="text-muted date">${item.createdDate}</small>
										</span>
                                        <p>${item.content}</p>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End comment -->
<script>
    function formatDate(element) {
        return new Date(element).toLocaleString('vi-VN','UTC+7');
    }

    let arrayDate = document.getElementsByClassName("date");
    for (let i = 0; i < arrayDate.length; i++)
        arrayDate[i].innerHTML = formatDate(arrayDate[i].innerHTML)

    function loadComment(data) {
        console.log(data.createdDate)
        var comment = [];
        comment.push(
            '<li class="media"><img src="',
            '' + data.user.avatar,
            '" class="img-circle"><div class="media-body ml-3"><strong class="text-success mr-2">',
            data.user.fullname,
            '</strong><span class="text-muted pull-right"><small class="text-muted">',
            formatDate(data.createdDate),
            '</small></span><p>',
            data.content,
            '</p></div></li>'
        )
        $('#contentComment').val("");
        $('#listComment').prepend(comment.join(""));
    }

    $('#addComment').click(function (e) {
        if ($('#formSubmit')[0].checkValidity()) {
            e.preventDefault();
            let data = {};
            let formData = $('#formSubmit').serializeArray();
            // vong lap
            $.each(formData, function (i, v) {
                data['' + v.name] = v.value
            });
            addComment(data);
        }
    })

    function addComment(data) {
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
                    loadComment(result)
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "/trang-chu";
            }
        })
    }
</script>
</body>
</html>
