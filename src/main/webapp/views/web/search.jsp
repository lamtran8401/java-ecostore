<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tìm kiếm</title>
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
                <li>Tìm kiếm</li>
            </ul>
        </div>
    </div>
</div>
<!-- //page -->
<!-- top Products -->
<div class="ads-grid py-sm-5 py-4">
    <div class="container py-xl-4 py-lg-2">
        <div class="row">
            <div class="agileinfo-ads-display col-lg-12">
                <div class="wrapper">
                    <c:if test="${model.totalItems == 0}">
                        <div class="alert alert-warning">Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của
                            bạn
                        </div>
                    </c:if>
                    <c:if test="${model.totalItems != 0}">
                        <div class="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4">
                            <div class="row ml-2 mb-5">
                                <h3>Kết quả tìm kiếm cho `${keyword}`: <span class="text-muted">${model.totalItems} kết quả</span></h3>
                            </div>
                            <div class="row ml-3">
                                <span class="card-title">Ưu tiên xem:</span>
                                <ul class="list-inline ml-3">
                                    <li class="list-inline-item"><a class="text-uppercase priority sortCreateddate"
                                                                    href="<c:url value='/tim-kiem?keyword=${keyword}&sortName=createddate&sortBy=desc'/>">Hàng
                                        mới</a></li>
                                    <li class="list-inline-item priority"><a class="text-uppercase priority sortDiscount"
                                                                             href="<c:url value='/tim-kiem?keyword=${keyword}&sortName=discount&sortBy=desc'/>">Giảm
                                        giá nhiều</a></li>
                                    <li class="list-inline-item priority"><a class="text-uppercase priority sortPriceA"
                                                                             href="<c:url value='/tim-kiem?keyword=${keyword}&sortName=price&sortBy=asc'/>">Giá
                                        thấp</a></li>
                                    <li class="list-inline-item priority"><a class="text-uppercase priority sortPriceD"
                                                                             href="<c:url value='/tim-kiem?keyword=${keyword}&sortName=price&sortBy=desc'/>">Giá
                                        cao</a></li>
                                </ul>
                            </div>
                            <hr>
                            <div class="row mt-5 mb-5">
                                <c:forEach var="item" items="${model.list}">
                                    <div class="col-md-3 col-12 product-men mt-5">
                                        <div class="men-pro-item simpleCart_shelfItem">
                                            <div class="men-thumb-item text-center">
                                                <p class="product-item-img">
                                                    <img src="<c:url value='${item.images.get(0)}'/>" alt=""
                                                         class="img-fluid">
                                                </p>
                                                <div class="men-cart-pro">
                                                    <div class="inner-men-cart-pro">
                                                        <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>"
                                                           class="link-product-add-cart">Chi tiết</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item-info-product text-center border-top mt-4">
                                                <h4 class="pt-1">
                                                    <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>">${item.name}</a>
                                                </h4>
                                                <div class="info-product-price my-2">
                                                    <span class="item_price">${item.price - item.price*(item.discount/100)}</span>
                                                    <br>
                                                    <del class="item_discount">${item.price}</del>
                                                </div>
                                                <div class="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
                                                    <form>
                                                        <fieldset>
                                                            <input type="hidden" name="productId" value="${item.id}"/>
                                                            <input type="hidden" name="productImage"
                                                                   value="${item.images.get(0)}"/>
                                                            <input type="hidden" name="unitPrice"
                                                                   value="${item.price - item.price*(item.discount/100)}"/>
                                                            <input type="hidden" name="productName"
                                                                   value="${item.name}"/>
                                                            <button class="btn button addToCart">Thêm vào giỏ hàng
                                                            </button>
                                                        </fieldset>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <form action="<c:url value='/tim-kiem'/>" id="formSubmit">
                                <div class="row justify-content-center">
                                    <ul class="pagination" id="pagination"></ul>
                                </div>
                                <input type="hidden" value="${keyword}" name="keyword"/>
                                <c:if test="${not empty model.sortName}">
                                    <input type="hidden" value="${model.sortName}" id="sortName" name="sortName"/>
                                    <input type="hidden" value="${model.sortBy}" id="sortBy" name="sortBy"/>
                                </c:if>
                                <input type="hidden" id="page" name="page"/>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //top products -->
<script>
    const sortName = '${model.sortName}';
    const sortBy = '${model.sortBy}';
    if(sortName == "createddate") {
        $(".sortCreateddate").addClass("btn btn-primary")
    } else if (sortName == "discount")
        $(".sortDiscount").addClass("btn btn-primary")
    else if(sortName == "price" && sortBy == "asc")
        $(".sortPriceA").addClass("btn btn-primary")
    else if(sortName == "price" && sortBy == "desc")
        $(".sortPriceD").addClass("btn btn-primary")

    let totalPages = ${model.totalPage};
    let currentPage = ${model.page};
    if (totalPages != 0) {
        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: 5,
                startPage: currentPage,
                onPageClick: function (event, page) {
                    if (page != currentPage) {
                        $('#page').val(page);
                        $('#formSubmit').submit();
                    }
                }
            });
        });
    }
</script>
</body>
</html>
