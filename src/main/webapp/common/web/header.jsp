<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!-- top-header -->
<div class="agile-main-top">
    <div class="container-fluid">
        <div class="row justify-content-end main-top-w3l py-2">

            <div class="col-sm-8 header-right mt-lg-0 mt-2">
                <!-- header lists -->
                <ul class="row justify-content-end">
                    <li class="text-center border-right text-white mt-auto mb-auto">
                        <a href="<c:url value='/lien-he'/>" class="text-white">
                            <i class="fas fa-phone mr-2"></i> ${information.phone} </a>
                    </li>
                    <c:if test="${empty USERMODEL}">
                        <li class="text-center border-right text-white">
                            <a href="<c:url value='/dang-nhap'/>" class="text-white">
                                <i class="fas fa-sign-in-alt mr-2"></i> Đăng nhập </a>
                        </li>
                        <li class="text-center text-white">
                            <a href="<c:url value='/dang-ky'/>" class="text-white">
                                <i class="fas fa-user-plus mr-2"></i> Đăng ký </a>
                        </li>
                    </c:if>

                    <c:if test="${not empty USERMODEL}">
                        <li class="text-center border-right text-white pr-3 pl-3" style="width: auto">
                            <div class="user-area dropdown">
                                <a href="#" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">
                                    <img class="user-avatar rounded-circle" src="<c:url value='${USERMODEL.avatar}'/>">
                                </a>
                                <span class="mr-3">Xin chào, ${USERMODEL.fullname}</span>

                                <div class="user-menu dropdown-menu">
                                    <a class="nav-link text-dark" href="<c:url value='/chinh-sua-thong-tin'/>"><i
                                            class="fa fa-user"></i> Thông tin cá nhân</a>

                                    <a class="nav-link text-dark" href="<c:url value="/theo-doi-don-hang"/>"><i class="fa fa-table"
                                                                              aria-hidden="true"></i>
                                        Theo dõi đơn hàng</a>

                                    <a class="nav-link text-dark" href="<c:url value="/doi-mat-khau"/>"><i
                                            class="fa fa-key" aria-hidden="true"></i>
                                        Đổi
                                        mật khẩu</a>

                                    <a class="nav-link text-dark" href="<c:url value='/thoat?action=logout'/>"><i
                                            class="fa fa-power-off"></i> Thoát</a>
                                </div>
                            </div>
                        </li>
                    </c:if>

                </ul>
                <!-- //header lists -->
            </div>
        </div>
    </div>
</div>
<!-- //top-header -->

<!-- header-bottom-->
<div class="header-bot">
    <div class="container">
        <div class="row header-bot_inner_wthreeinfo_header_mid">
            <!-- logo -->
            <div class="col-md-3 logo_agile">
                <h1 class="text-center">
                    <a href="<c:url value='/trang-chu'/>" class="font-weight-bold font-italic">
                        <img src="<c:url value='/images/logo.png'/>" alt=" " class="img-fluid">
                        Eco Store
                    </a>
                </h1>
            </div>
            <!-- //logo -->
            <!-- header-bot -->
            <div class="col-md-9 header mt-4 mb-md-0 mb-4">
                <div class="row">
                    <!-- search -->
                    <div class="col-10 agileits_search">
                        <form class="form-inline" action="tim-kiem">
                            <input class="form-control mr-sm-0" type="search" placeholder="Tìm kiếm"
                                   aria-label="Search" required
                                   oninvalid="this.setCustomValidity('Vui lòng nhập từ khóa bạn muốn tìm kiếm.')"
                                   oninput="this.setCustomValidity('')" name="keyword">
                            <button class="btn my-2 my-sm-0" type="submit"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                    <!-- //search -->
                    <!-- cart details -->
                    <div class="col-2 top_nav_right text-center mt-sm-0 mt-2 mb-3">
                        <button type="button" class="btn btn-outline-info p-3" data-toggle="modal"
                                data-target="#cartModal">
                            <c:if test="${not empty CART}">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> <span
                                    class="cart-size">${CART.size()}</span>
                            </c:if>
                            <c:if test="${empty CART}">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> <span class="cart-size"></span>
                            </c:if>
                        </button>
                    </div>

                    <div class="modal fade" id="cartModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header border-bottom-0">
                                    <h5 class="modal-title" id="exampleModalLabel">
                                        Giỏ hàng
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body" id="cart-modal">
                                    <table class="table table-image" id="mycart">
                                        <thead>
                                        <tr>
                                            <th scope="col"></th>
                                            <th scope="col">Sản phẩm</th>
                                            <th scope="col">Giá</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Thành tiền</th>
                                            <th scope="col">Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${CART.data()}">
                                            <tr>
                                                <td class="w-15">
                                                    <a href="<c:url value='/chi-tiet-san-pham?id=${item.productId}'/>"><img
                                                            src="<c:url value='${item.productImage}'/>"
                                                            class="img-fluid img-thumbnail"></a>
                                                </td>
                                                <td><a href="<c:url value='/chi-tiet-san-pham?id=${item.productId}'/>"
                                                       class="text-secondary">${item.productName}</a></td>
                                                <td class="item_discount">${item.unitPrice}</td>
                                                <td class="qty"><form>
                                                    <input type="hidden" name="productId"
                                                           value="${item.productId}"/>
                                                    <input type="number" class="form-control quantity-cart" name="quantity"
                                                                             value="${item.quantity}" min="1">
                                                </form></td>
                                                <td class="item_discount">${item.unitPrice * item.quantity}</td>
                                                <td>
                                                    <form>
                                                            <button class="btn btn-danger btn-sm remove-item mt-2"><i
                                                                    class="fa fa-times"></i></button>
                                                            <input type="hidden" name="productId"
                                                                   value="${item.productId}"/>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <div class="d-flex justify-content-end total-price">
                                        <c:if test="${not empty CART}">
                                            <h5>Tổng: <span
                                                    class="price text-success item_discount">${CART.total()}</span></h5>
                                        </c:if>
                                        <c:if test="${empty CART}">
                                            <h5>Tổng: <span class="price text-success item_discount">0</span></h5>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="modal-footer border-top-0 d-flex justify-content-between">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
                                    <a href="<c:url value="/thanh-toan"/>" class="btn btn-success">Thanh toán</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //cart details -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- shop locator (popup) -->