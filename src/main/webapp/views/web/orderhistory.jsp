<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lịch sử mua hàng</title>
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
                    <li>Lịch sử đơn hàng</li>
                </ul>
            </div>
        </div>
    </div>
<!-- Order history -->
    <c:if test="${empty model.list || model.list.size() == 0 }">
        <c:if test="${not empty message}">
            <div class="text-center alert alert-${alert} mr-auto ml-auto mt-5" style="width: 50%">${message}</div>
        </c:if>
        <c:if test="${empty message}">
            <div class="text-center alert alert-warning mr-auto ml-auto mt-5" style="width: 50%">Bạn chưa có đơn hàng nào.</div>
        </c:if>

        <div class="row justify-content-center">
            <a class="btn btn-info pt-1 pb-1 pr-4 pl-4" href="<c:url value="/trang-chu"/>">Tiếp tục mua sắm</a>
        </div>
    </c:if>
    <c:if test="${model.list.size() > 0 }">
        <div class="container product-sec1 px-sm-4 px-3 py-sm-5 py-3 mb-4 mt-5">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th scope="col" class="text-center">Mã đơn hàng</th>
                    <th scope="col" class="text-center">Ngày đặt hàng</th>
                    <th scope="col" class="text-center">Tổng trị giá</th>
                    <th scope="col" class="text-center">Trạng thái</th>
                    <th scope="col" class="text-center">Thao tác<table></table>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${model.list}">
                    <tr>
                        <th scope="row" class="text-center">${item.id}</th>
                        <td class="text-center date">${item.createdDate}</td>
                        <td class="text-center item_price">${item.totalPrice}</td>
                        <c:if test="${item.status == 0}">
                            <td class="text-center"><span class="status text-warning">&bull;</span>Đang chờ xác nhận
                            </td>
                        </c:if>
                        <c:if test="${item.status == 1}">
                            <td class="text-center"><span class="status text-info">&bull;</span>Đã xác nhận
                            </td>
                        </c:if>
                        <c:if test="${item.status == 2}">
                            <td class="text-center"><span class="status text-primary">&bull;</span>Đang giao
                            </td>
                        </c:if>
                        <c:if test="${item.status == 3}">
                            <td class="text-center"><span class="status text-success">&bull;</span>Thành công
                            </td>
                        </c:if>
                        <c:if test="${item.status == 4}">
                            <td class="text-center"><span class="status text-danger">&bull;</span>Đã hủy
                            </td>
                        </c:if>
                        <td class="text-center">
                            <a href="<c:url value='/theo-doi-don-hang?id=${item.id}'/>" class="detail-icon"><i class="fa fa-eye" aria-hidden="true"
                                                                                                 data-toggle="tooltip" title="Xem chi tiết"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form action="<c:url value='/theo-doi-don-hang'/>" id="formSubmit">
                <div class="row justify-content-center">
                    <ul class="pagination" id="pagination"></ul>
                </div>
                <input type="hidden" value="" id="page" name="page"/>
<%--                <input type="hidden" value="${code}" id="code" name="code"/>--%>
            </form>
        </div>
    </c:if>
<script>
    function formatDate(element) {
        let date = new Date(element)

        return date.toLocaleDateString('vi-VN','UTC+7');
    }

    let arrayDate = document.getElementsByClassName("date");
    for (let i = 0; i < arrayDate.length; i++)
        arrayDate[i].innerHTML = formatDate(arrayDate[i].innerHTML)


    let totalPages = ${model.totalPage};
    let currentPage = ${model.page};
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
</script>

    <!-- end order history -->
</body>
</html>
