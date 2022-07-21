<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="newsListUrl" value="/admin/new/list"/>
<c:url var="deleteUrl" value="/api/new"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách bài viết</title>
</head>

<body>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading">Core</div>
                    <a class="nav-link" href="<c:url value="/admin/new/list?page=1&limit=2"/>">
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        Quản lý bài viết
                    </a>
                </div>
            </div>
            <div class="sb-sidenav-footer">
                <div class="small">Logged in as:</div>
                Start Bootstrap
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <div class="main-content">
            <form action="<c:url value="/admin/new/list"/>" id="formSubmit" method="get">
                <div class="main-content-inner">
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="table-btn-controls">
                                        <div class="pull-right tableTools-container">
                                            <div class="dt-buttons btn-overlap btn-group">
                                                <a flag="info"
                                                   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                                   data-toggle="tooltip"
                                                   title='Thêm bài viết' href='<c:url value="/admin/new/edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                                </a>
                                                <button id="btnDelete" type="button" onclick="warningBeforeDelete()"
                                                        class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                        data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa-solid fa-trash"></i>
																</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="table-responsive">
                                            <c:if test="${not empty message}">
                                                <div class="alert alert-${alert}">
                                                    <strong>${message}</strong>
                                                </div>
                                            </c:if>
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>Tiêu đề</th>
                                                    <th>Mô tả ngắn</th>
                                                    <th>Nội dung</th>
                                                    <th>Thao tác</th>
                                                    <th> <input type="checkbox" id="checkAll"> </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="item" items="${model.listResult}">
                                                    <tr>
                                                        <td>${item.title}</td>
                                                        <td>${item.shortDescription}</td>
                                                        <td>${item.content}</td>
                                                        <td>
                                                            <c:url var="updateNewsURL" value="/admin/new/edit">
                                                                <c:param name="id" value="${item.id}"/>
                                                            </c:url>
                                                            <a class="btn btn-sm btn-primary btn-edit"
                                                               data-toggle="tooltip"
                                                               title="Cập nhật bài viết" href="${updateNewsURL}"><i
                                                                    class="fa-solid fa-pen-to-square"
                                                                    aria-hidden="true"></i>
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                            <div class="container">
                                                <nav aria-label="Page navigation">
                                                    <ul class="pagination" id="pagination"></ul>
                                                    <input type="hidden" value="" id="page" name="page"/>
                                                    <input type="hidden" value="" id="limit" name="limit"/>
                                                </nav>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- /.main-content -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript">
    var currentPage = ${model.page};
    var toltalPage = ${model.totalPage};
        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: toltalPage,
                visiblePages: 10,
                startPage: currentPage,
                onPageClick: function (event, page) {
                    if (currentPage != page) {
                        $('#limit').val(2);
                        $('#page').val(page);
                        $('#formSubmit').submit();
                    }
                }
            });
        });

    $("#btnDelete").click(function(e){
            e.preventDefault();
            Swal.fire({
                title: 'Bạn có chắc chắn muốn xóa không ?',
                text: "Dữ liệu bị xóa sẽ không thể khôi phục lại",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Đồng ý',
                cancelButtonText: 'Không'
            }).then((result) => {
                if (result.isConfirmed) {
                    var ids = $('tbody input[type=checkbox]:checked').map(function (){
                        return $(this).val();
                    }).get();
                    deleteNew(ids);
                }
            })
        });
    function deleteNew(data) {
        $.ajax({
            url: '${deleteUrl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${newsListUrl}?page=1&limit=2&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${newsListUrl}?page=1&limit=2&message=error_system";
            }
        });
    }
</script>
</body>

</html>