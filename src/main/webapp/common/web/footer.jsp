<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!-- footer -->
<footer>
    <div class="footer-top-first">
        <div class="container py-md-5 py-sm-4 py-3">
            <div class="row w3l-grids-footer border-top border-bottom py-sm-4 py-3">
                <div class="col-md-4 offer-footer">
                    <div class="row">
                        <div class="col-4 icon-fot">
                            <i class="fas fa-dolly"></i>
                        </div>
                        <div class="col-8 text-form-footer">
                            <h3>Miễn phí vận chuyển</h3>
                            <p></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 offer-footer my-md-0 my-4">
                    <div class="row">
                        <div class="col-4 icon-fot">
                            <i class="fas fa-shipping-fast"></i>
                        </div>
                        <div class="col-8 text-form-footer">
                            <h3>Giao hàng nhanh</h3>
                            <p></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 offer-footer">
                    <div class="row">
                        <div class="col-4 icon-fot">
                            <i class="far fa-thumbs-up"></i>
                        </div>
                        <div class="col-8 text-form-footer">
                            <h3>Sản phẩm chính hãng</h3>
                            <p></p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- //footer second section -->
        </div>
    </div>
    <!-- footer third section -->
    <div class="w3l-middlefooter-sec">
        <div class="container py-md-5 py-sm-4 py-3">
            <div class="row footer-info w3-agileits-info">
                <div class="col-md-3 col-sm-6 footer-grids mt-sm-0 mt-4">
                    <h3 class="text-white font-weight-bold mb-3">Hỗ trợ khách hàng</h3>
                    <ul>
                        <c:forEach var="item" items="${menuBottom}">
                            <li class="mb-3">
                                <a href="<c:url value='${item.link}'/>">${item.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- footer categories -->
                <div class="col-md-3 col-sm-6 footer-grids">
                    <h3 class="text-white font-weight-bold mb-3">Thông tin liên hệ</h3>
                    <ul>
                        <li class="mb-3">
                            <i class="fas fa-map-marker"></i>${information.address}
                        </li>
                        <li class="mb-3">
                            <i class="fas fa-phone"></i>${information.phone}
                        </li>
                        <li class="mb-3">
                            <i class="fas fa-envelope-open"></i>
                            <a href="mailto:${information.email}">${information.email}</a>
                        </li>
                    </ul>
                </div>
                <!-- //footer categories -->
                <!--payment-->
                <div class="col-md-3 col-sm-6 footer-grids mt-md-0 mt-4">
                    <h3 class="text-white font-weight-bold mb-3">Phương thức thanh toán</h3>
                    <ul class="row">
                        <li class="col-md-4 col-sm-6 col-12">
                            <img src="<c:url value='/images/pay2.png'/>" alt="">
                        </li>
                        <li class="col-md-4 col-sm-6 col-12">
                            <img src="<c:url value='/images/pay5.png'/>" alt="">
                        </li>
                        <li class="col-md-4 col-sm-6 col-12">
                            <img src="<c:url value='/images/pay1.png'/>" alt="">
                        </li>
                        <li class="col-md-4 col-sm-6 col-12">
                            <img src="<c:url value='/images/pay7.png'/>" alt="">
                        </li>
                        <li class="col-md-4 col-sm-6 col-12">
                            <img src="<c:url value='/images/pay8.png'/>" alt="">
                        </li>
                        <li class="col-md-4 col-sm-6 col-12">
                            <img src="<c:url value='/images/pay9.png'/>" alt="">
                        </li>
                    </ul>
                    <!-- social icons -->
                    <div class="footer-grids  w3l-socialmk mt-3">
                        <h3 class="text-white font-weight-bold mb-3">Mạng xã hội</h3>
                        <div class="social">
                            <ul>
                                <li>
                                    <a class="icon fb" href="<c:url value='${information.facebookLink}'/>" target="_blank">
                                        <i class="fab fa-facebook-f"></i>
                                    </a>
                                </li>
                                <li>
                                    <a class="icon tw" href="<c:url value='${information.twitterLink}'/>" target="_blank">
                                        <i class="fab fa-twitter"></i>
                                    </a>
                                </li>
                                <li>
                                    <a class="icon gp" href="<c:url value='${information.instagramLink}'/>" target="_blank">
                                        <i class="fab fa-instagram"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- //social icons -->

                </div>
                <div class="col-md-3 col-sm-6 footer-grids w3l-agileits mt-md-0 mt-4">
                    <!-- newsletter -->
                    <h3 class="text-white font-weight-bold mb-3">Đăng ký tư vấn</h3>
                    <p class="mb-3">Tư vấn miễn phí</p>
                    <form action="#" method="post">
                        <div class="form-group">
                            <input type="email" class="form-control float-left" placeholder="Email" name="email"
                                   required="">
                            <input type="submit" value="Gửi">
                        </div>
                    </form>
                    <!-- //newsletter -->
                </div>
                <!--contact us-->
                <div class="col-md-3 col-sm-6 footer-grids w3l-agileits mt-md-0 mt-4">
                    <!-- newsletter -->

                </div>

            </div>

        </div>
    </div>
    <!-- //footer third section -->
</footer>
<!-- //footer -->