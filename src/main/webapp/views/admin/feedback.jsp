<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-feedback"/>
<c:url var="FeedbackURL" value="/quan-tri/phan-hoi"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý phản hồi</title>
</head>
<body>
<div class="content mt-3">
    <div class="animated fadeIn">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">Danh sách phản hồi</strong>
                    </div>
                    <div class="card-header">
                        <c:if test="${not empty message}">
                            <div class="float-left alert alert-${alert}">${message}</div>
                        </c:if>
                        <div class="float-right">
                            <a href="#deleteFeedbackModal" class="btn btn-danger" data-toggle="modal"><i
                                    class="fa fa-trash-o" aria-hidden="true"></i> <span>Xóa</span></a>
                        </div>
                    </div>
                    <div class="card-body">
                        <table id="bootstrap-data-table-export" class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th class="text-center">
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="selectAll">
                                                    <label for="selectAll"></label>
                                                </span>
                                </th>
                                <th class="text-center">Tên người dùng</th>
                                <th class="text-center">Nội dung</th>
                                <th class="text-center">Ngày phản hồi</th>
                                <th class="text-center">Trạng thái</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${feedbacks}">
                                <tr>
                                    <td class="text-center">
                                                <span class="custom-checkbox">
                                                     <input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                    <label for="checkbox_${item.id}"></label>
                                                </span>
                                    </td>
                                    <td>${item.user.fullname}</td>
                                    <td>${item.content}</td>
                                    <td>${item.createdDate}</td>
                                    <c:if test="${item.status == 0}">
                                        <td class="text-center"><span class="status text-danger">&bull;</span>Chưa hoạt
                                            động
                                        </td>
                                    </c:if>
                                    <c:if test="${item.status == 1}">
                                        <td class="text-center"><span class="status text-success">&bull;</span>Hoạt
                                            động
                                        </td>
                                    </c:if>

                                    <td class="text-center">
                                        <a href="<c:url value='/quan-tri/phan-hoi?id=${item.id}'/>" class="edit"><i
                                                class="fa fa-pencil"
                                                aria-hidden="true" data-toggle="tooltip"
                                                title="Chỉnh sửa"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->

<div id="deleteFeedbackModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Xóa phản hồi </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Bạn chắc chắn muốn xóa những phản hồi này?</p>
                    <p class="text-warning"><small>Hành động này sẽ không thể khôi phục lại.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                    <button id="deleteFeedback" type="submit" class="btn btn-danger" >Xoá</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $('#deleteFeedback').click(function (e) {
        e.preventDefault();
        let data = {}; // mang object name: value
        // lay data khi check vao cac checkbox
        let dataArray = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val(); // lay value cua input checked
        }).get();
        if (dataArray.length != 0) {
            data['ids'] = dataArray;
            deleteFeedback(data);
        }
    })
    function deleteFeedback(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if(result)
                    window.location.href = "${FeedbackURL}?message=delete_success&alert=success";
                else
                    window.location.href = "${FeedbackURL}?message=delete_fail&alert=danger";
            },
            error: function (error) {
                $('.load').hide();
                window.location.href = "${FeedbackURL}?message=system_error&alert=danger";
            }
        })
    }
</script>
</body>
</html>
