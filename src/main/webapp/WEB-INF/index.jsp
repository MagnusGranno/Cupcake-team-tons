<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.role != 'customer' }">
            <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-lg-6">
            <h1 class="pb-3 text-center">Welcome to Olsker Cupcakes</h1>
            <p class="text-center">Please <a type="button" class="btn btn-sm btn-outline-primary"
                                             href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
                or <a type="button" class="btn btn-sm btn-outline-primary"
                      href="${pageContext.request.contextPath}/fc/loginpage">Login</a> to begin</p>
            </div>
            </div>
            <div class="col-sm-3"></div>


        </c:if>
             <c:if test="${sessionScope.role == 'customer' }">
                 <div class="row text-center">
                     <div class="col-sm-4">
                        <a href="${pageContext.request.contextPath}/fc/orderpageCustomer" class="btn btn-lg btn-primary">Order</a>
                     </div>
                 <div class="col-sm-4">
                        <a href="${pageContext.request.contextPath}/fc/cart" class="btn btn-lg btn-block btn-primary">Cart</a>
                 </div>
                 <div class="col-sm-4">
                        <form method="post" action="${pageContext.request.contextPath}/fc/profile">
                            <button type="submit" class="btn btn-lg btn-block btn-primary" name="userprofile" value="${sessionScope.user.id}">Profile</button>
                        </form>
                 </div>
                 </div>
            </c:if>




    </jsp:body>
</t:genericpage>