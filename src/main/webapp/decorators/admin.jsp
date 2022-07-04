
<%--
  Created by IntelliJ IDEA.
  User: Truon
  Date: 6/27/2022
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>HomeAdmin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="<c:url value="/template/admin/css/styles.css"/>" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
  </head>
  <body class="sb-nav-fixed">

  <%@include file="/common/admin/header.jsp"%>

  <dec:body />

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
  <script src="<c:url value="/template/admin/js/scripts.js"/>"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
  <script src="<c:url value="/template/admin/assets/demo/chart-area-demo.js"/>"></script>
  <script src="<c:url value="/template/admin/assets/demo/chart-bar-demo.js"/>"></script>
  <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
  <script src="<c:url value="/template/admin/js/datatables-simple-demo.js"/>"></script>
  </body>
</html>
