<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!-- navigation -->
<div class="navbar-inner">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light ">
            <button class="navbar-toggler m-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse row justify-content-center " id="navbarSupportedContent">
                <ul class="navbar-nav text-center">
                    <c:forEach var="item" items="${menuTop}">
                        <c:if test="${item.name == 'Sản phẩm'}">
                            <li class="nav-item dropdown mr-lg-2 mb-lg-0 mb-2">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    ${item.name}
                                </a>
                                <div class="dropdown-menu">
                                    <div class="agile_inner_drop_nav_info p-4">
                                        <h5 class="mb-3">Sản phẩm</h5>
                                        <div class="row">
                                            <div class="col-sm-4 multi-gd-img">
                                                <ul class="multi-column-dropdown">
                                                    <c:forEach var="item2" items="${categories}" begin="0" end="${categories.size()/3}">
                                                        <li>
                                                            <a href="<c:url value='/san-pham?code=${item2.code}'/>">${item2.name}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <div class="col-sm-4 multi-gd-img">
                                                <ul class="multi-column-dropdown">
                                                    <c:forEach var="item2" items="${categories}" begin="${categories.size()/3+1}" end="${categories.size()/3*2}">
                                                        <li>
                                                            <a href="<c:url value='/san-pham?code=${item2.code}'/>">${item2.name}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <div class="col-sm-4 multi-gd-img">
                                                <ul class="multi-column-dropdown">
                                                    <c:forEach var="item2" items="${categories}" begin="${categories.size()/3*2+1}">
                                                        <li>
                                                            <a href="<c:url value='/san-pham?code=${item2.code}'/>">${item2.name}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${not (item.name == 'Sản phẩm')}">
                            <li class="nav-item mr-lg-2 mb-lg-0 mb-2">
                                <a class="nav-link" href="<c:url value='${item.link}'/>">${item.name}
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </nav>
    </div>
</div>
<!-- //navigation -->