<%--
  Created by IntelliJ IDEA.
  User: Truon
  Date: 6/27/2022
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.springmvc.util.SecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container px-5">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="<c:url value="/trang-chu"/>">Trang
                    chủ</a></li>
                <security:authorize access="isAnonymous()">
                    <li class="nav-item"><a class="nav-link" href="#!">Đăng nhập</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">Đăng ký</a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item"><a class="nav-link" href="">Welcome <%=SecurityUtils.getPrincipal().getFullName()%> </a></li>
                    <li class="nav-item"><a class="nav-link" href="<c:url value="/logout"/>">Thoát</a></li>
                </security:authorize>

            </ul>
        </div>
    </div>
</nav>