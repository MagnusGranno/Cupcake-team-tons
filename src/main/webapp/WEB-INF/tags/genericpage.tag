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
    <title>
        <jsp:invoke fragment="header"/>
    </title>

    <!-- Bootstrap JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/scss/custom.css">

</head>

<body class="container">
<div class="mt-2"><img src="${pageContext.request.contextPath}/images/olskercupcakes.png" class="img-fluid w-100  mx-auto mb-4"></div>
<header class="container d-flex flex-column flex-md-row  p-3 pb-0 px-md-4 mb-4 bg-white border-bottom shadow-sm">
    <div>
        <nav class="my-2 my-md-0 me-md-3 navbar pb-3 pt-0">
            <c:if test="${addHomeLink == null }">
                <a class="btn btn-light p-2" href="<%=request.getContextPath()%>">Home</a>
            </c:if>
            <c:if test="${sessionScope.role != null}">
                <c:if test="${sessionScope.role == 'customer'}">
                    <a class="btn btn-light p-2" href="${pageContext.request.contextPath}/fc/orderpageCustomer">Orders</a>
                    <a class="btn btn-light p-2" href="${pageContext.request.contextPath}/fc/cart">Cart</a>
                    <form method="post" action="${pageContext.request.contextPath}/fc/profile">
                        <button type="submit" class="btn btn-light p-2" name="userprofile" value="${sessionScope.user.id}">Profile</button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role == 'employee'}">
                    <a class="p-2 btn btn-light" href="${pageContext.request.contextPath}/fc/orderpageEmployee">Order Cupcakes</a>
                    <a class="p-2 btn btn-light" href="${pageContext.request.contextPath}/fc/cart">Cart</a>
                    <%--<a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/profile">Profile</a>--%>
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
                <a type="button" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/fc/logoutcommand">Logout</a>
            </c:if>
            <c:if test="${sessionScope.user == null }">
                <a type="button" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/fc/loginpage">Login</a>
                <a type="button" class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
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