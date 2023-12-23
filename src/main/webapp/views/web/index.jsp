<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<!-- banner -->
<c:if test="${slides.size() > 0}">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <!-- Indicators-->
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <c:forEach var="i" begin="1" end="${slides.size()-1}">
                <li data-target="#carouselExampleIndicators" data-slide-to="${i}"></li>
            </c:forEach>

        </ol>
        <div class="carousel-inner">
            <div class="carousel-item item1 active" style="background: url(${slides.get(0).imageLink}) no-repeat center;">
                <div class="container">
                    <div class="w3l-space-banner">
                        <div class="carousel-caption p-lg-5 p-sm-4 p-3">
                            <p>${slides.get(0).description}</p>
                            <h3 class="font-weight-bold pt-2 pb-lg-5 pb-4">
                                <c:forEach var="item" items="${fn:split(slides.get(0).title, ' ')}">
                                    <span>${fn:substring(item, 0, 1)}</span>${fn:substring(item, 1, fn:length(item))}
                                </c:forEach>
                            </h3>
                            <a class="button2" href="<c:url value="/trang-chu"/>">Mua ngay </a>
                        </div>
                    </div>
                </div>
            </div>
            <c:forEach var="item" items="${slides}" begin="1">
                <div class="carousel-item item1" style="background: url(${item.imageLink}) no-repeat center;">
                    <div class="container">
                        <div class="w3l-space-banner">
                            <div class="carousel-caption p-lg-5 p-sm-4 p-3">
                                <p>${item.description}</p>
                                <h3 class="font-weight-bold pt-2 pb-lg-5 pb-4">
                                    <c:forEach var="it" items="${fn:split(item.title, ' ')}">
                                        <span>${fn:substring(it, 0, 1)}</span>${fn:substring(it, 1, fn:length(it))}
                                    </c:forEach>
                                </h3>
                                <a class="button2" href="<c:url value="/trang-chu"/>">Mua ngay </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</c:if>
<!-- //banner -->

<!-- top Products -->
<div class="ads-grid py-sm-5 py-4">
    <div class="container py-xl-4 py-lg-2">
        <div class="row justify-content-center">
            <div class="agileinfo-ads-display col-lg-12">
                <div class="wrapper">
                    <!-- first section -->
                    <div class="product-sec1 px-sm-4 px-3 py-sm-5  py-3">
                        <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                            <span>S</span>ản

                            <span>P</span>hẩm
                            <span>M</span>ới</h3>
                        <div class="row mt-5 mb-5">
                            <c:forEach var="item" items="${productNewList}" begin="0" end="19">
                                <div class="col-lg-3 col-md-4 col-sm-6 col-12 product-men mt-5">
                                    <div class="men-pro-item simpleCart_shelfItem">
                                        <div class="men-thumb-item text-center">
                                            <p class="product-item-img">
                                                <img src="<c:url value='${item.images.get(0)}'/>" alt="" class="img-fluid product-img">
                                            </p>
                                            <div class="men-cart-pro">
                                                <div class="inner-men-cart-pro">
                                                    <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>" class="link-product-add-cart">Chi tiết</a>
                                                </div>
                                            </div>
                                            <span class="product-new-top">Mới</span>
                                        </div>
                                        <div class="item-info-product text-center border-top mt-4">
                                            <h4 class="pt-1 crop">
                                                <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>">${item.name}</a>
                                            </h4>
                                            <div class="info-product-price my-2">
                                                <span class="item_price">${item.price - item.price*(item.discount/100)}</span>
                                                <br>
                                                <del class="item_discount">${item.price}</del>
                                            </div>
                                            <div
                                                    class="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
                                                <form>
                                                        <input type="hidden" name="productId" value="${item.id}"/>
                                                        <input type="hidden" name="productImage" value="${item.images.get(0)}"/>
                                                        <input type="hidden" name="unitPrice" value="${item.price - item.price*(item.discount/100)}"/>
                                                        <input type="hidden" name="productName"
                                                               value="${item.name}"/>
                                                        <button class="btn button addToCart">Thêm vào giỏ hàng</button>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>


                        </div>
                    </div>
                    <!-- //first section -->

                    <!-- third section -->
                    <div class="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mt-4">
                        <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                            <span>G</span>iảm

                            <span>G</span>iá
                            <span>S</span>ốc</h3>
                        <div class="row mt-5 mb-5">
                            <c:forEach var="item" items="${productDiscountList}" begin="0" end="19">
                                <div class="col-lg-3 col-md-4 col-sm-6 col-12 product-men mt-5">
                                    <div class="men-pro-item simpleCart_shelfItem">
                                        <div class="men-thumb-item text-center">
                                            <p class="product-item-img">
                                                <img src="<c:url value='${item.images.get(0)}'/>" alt="" class="img-fluid product-img">
                                            </p>
                                            <div class="men-cart-pro">
                                                <div class="inner-men-cart-pro">
                                                    <a href="<c:url value='/chi-tiet-san-pham?id=${item.id}'/>" class="link-product-add-cart">Chi tiết</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item-info-product text-center border-top mt-4">
                                            <h4 class="pt-1 crop">
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
                                                        <input type="hidden" name="productImage" value="${item.images.get(0)}"/>
                                                        <input type="hidden" name="unitPrice" value="${item.price - item.price*(item.discount/100)}"/>
                                                        <input type="hidden" name="productName"
                                                               value="${item.name}"/>
                                                        <button class="btn button addToCart">Thêm vào giỏ hàng</button>
                                                    </fieldset>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- //third section -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- //top products -->
</body>
</html>
