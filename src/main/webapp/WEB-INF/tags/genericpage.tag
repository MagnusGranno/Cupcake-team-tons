<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <meta name="theme-color" content="#7952b3">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>

<body class="container">
<div class="mt-2"><img src="${pageContext.request.contextPath}/images/olskercupcakes.png" class="img-fluid w-100  mx-auto mb-4"></div>

    <!--
        This header is inspired by this bootstrap
        example: https://getbootstrap.com/docs/5.0/examples/pricing/
    -->
<header class="container d-flex flex-column flex-md-row  p-3 pb-0 px-md-4 mb-4 bg-white border-bottom shadow-sm">


    <div>
    <nav class="my-2 my-md-0 me-md-3">
        <c:if test="${addHomeLink == null }">
            <a class="p-2 text-dark" href="<%=request.getContextPath()%>">Home</a>
        </c:if>

        <c:if test="${sessionScope.role != null}">

            <c:if test="${sessionScope.role == 'customer'}">
             <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/orderpageCustomer">Orders</a>
                <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/cart">Cart</a>
                <a class="p-2 text-dark" href="#">Profile</a>
             </c:if>
            <c:if test="${sessionScope.role == 'employee'}">
                <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/orderpageEmployee">Orders</a>
                <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/cart">Cart</a>
                <a class="p-2 text-dark" href="#">Profile</a>
            </c:if>
        </c:if>



    </nav>

    </div>
<div class="h5 my-0 me-md-auto fw-normal">

</div>
    <div class="mb-2 pb-2">


            <c:if test="${sessionScope.user != null }">
                ${sessionScope.user.email}
            </c:if>
        <c:if test="${sessionScope.role == 'employee' }">
            <a href="${pageContext.request.contextPath}/fc/employeepage" type="button" class="btn btn-sm btn-primary">Admin</a>
        </c:if>

        <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
        <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
        <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

        <c:if test="${isNotLoginPage && isNotRegisterPage}">
            <c:if test="${sessionScope.user != null }">
                <a type="button" class="btn btn-sm btn-primary"
                href="${pageContext.request.contextPath}/fc/logoutcommand">Logout</a>
            </c:if>
            <c:if test="${sessionScope.user == null }">
                <a type="button" class="btn btn-sm btn-primary"
                   href="${pageContext.request.contextPath}/fc/loginpage">Login</a>
                <a type="button" class="btn btn-sm btn-primary"
                   href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
            </c:if>
    </div>
    </c:if>
</header>

<div id="body" class="w-100" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="w-100">
    <br>
    <hr>
    <br>
    <jsp:invoke fragment="footer"/>
</div>

</body>
</html>