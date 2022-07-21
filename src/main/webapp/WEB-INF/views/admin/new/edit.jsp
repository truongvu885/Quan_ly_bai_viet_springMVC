<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="newsListURL" value="/admin/new/list" />
<c:url value="/api/new" var="newsApi" />
<c:url var="editUrl" value="/admin/new/edit"/>
<html lang="">
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
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
            <main>
                <div class="main-content-inner">
                    <div class="breadcrumbs" id="breadcrumbs">
                        <script type="text/javascript">
                            try {
                                ace.settings.check('breadcrumbs', 'fixed')
                            } catch (e) {
                            }
                        </script>
                        <ul class="breadcrumb">
                            <li class="active">Chỉnh sửa bài viết</li>
                        </ul><!-- /.breadcrumb -->
                    </div>
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <c:if test="${not empty message}">
                                    <div class="alert alert-${alert}">
                                        <strong>${message}</strong>
                                    </div>
                                </c:if>
                                <form:form id="formSubmit" enctype="multipart/form-data" modelAttribute="model">
                                    <div class="form-group">
                                        <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                        <div class="col-sm-9">
                                            <form:select path="categoryCode" class="form-control" >
                                                <form:option value="" label="--Chọn thể loại--" />
                                                <form:options items="${categories}" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <br/>
                                    <br/>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" path="title"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <br/>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                        <div class="col-sm-9">
                                            <form:input path="shortDescription" class="form-control" />
                                        </div>
                                    </div>
                                    <br/>
                                    <br/>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                        <div class="col-sm-9">
                                            <form:textarea cssClass="form-control" cols="10" rows="7" path="content" />
                                        </div>
                                    </div>
                                    <form:hidden path="id" id="newId" />
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <c:if test="${not empty model.id}">
                                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="Cập nhập" id="btnAddOrUpdateNew"/>
                                            </c:if>
                                            <c:if test="${empty model.id}">
                                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="Thêm" id="btnAddOrUpdateNew"/>
                                            </c:if>
                                            <input type="reset" class="btn btn-white btn-warning btn-bold" value="reset" id="btnReset"/>
                                        </div>
                                    </div>
                                </form:form>
                                <form action="<c:url value="/admin/new/img"/>" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                        <div class="col-sm-9">
                                            <input class="form-control" type="file" id="img_input" name="img" accept=" image/png, img/jpg "/>
                                        </div>
                                        <div class="form-control">
                                            <div id="display_img" class="form-control align-items-center"  style=" width: 375px;
                                                                                    height: 211px;
                                                                                    border: 1px solid black;
                                                                                    background-position: center;
                                                                                    background-size: cover;">

                                            </div>
                                        </div>
                                        <div class="form-control">
                                            <input type="submit" id="btnUpimg" name="btnUpimg" value="Tải lên">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
</div>
<script>

    const img_input = document.querySelector("#img_input");
    let upload_img = "";

    img_input.addEventListener("change",function(){
        const reader = new FileReader();
        reader.addEventListener("load",() => {
            upload_img = reader.result;
            document.getElementById("display_img").style.backgroundImage = "url("+upload_img+")";
        });
        reader.readAsDataURL(this.files[0]);
    });

    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });

        console.log(data);
        var id = $('#newId').val();
        if (id === "") {
            addNews(data);
        } else {
            updateNews(data);
        }
    });

    function addNews(data) {
        $.ajax({
           url:'${newsApi}',
           type:'POST',
            data:JSON.stringify(data),
            contentType:'application/json',
            processData: false,
            enctype: 'multipart/form-data',
            success: function (result) {
                window.location.href = "${editUrl}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${newsListURL}?page=1&limit=2&message=error_system";
            }
        });
    }
    function updateNews(data) {
        $.ajax({
            url: '${newsApi}',
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editUrl}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
                window.location.href = "${newsListURL}?page=1&limit=2&message=error_system";
            }
        });
    }
</script>
</body>
</html>
