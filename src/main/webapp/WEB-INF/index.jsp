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

<div class="row">
        <div class="col-sm-3"></div>
    <div class="col-lg-6">
        <h1 class="pb-3 text-center">Welcome to Olsker Cupcakes</h1>



        <c:if test="${sessionScope.role != 'customer' }">
                <p class="text-center">Please <a type="button" class="btn btn-sm btn-outline-primary"
                             href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
                or <a type="button" class="btn btn-sm btn-outline-primary"
                   href="${pageContext.request.contextPath}/fc/loginpage">Login</a> to begin</p>

        </c:if>
        </div>
             <c:if test="${sessionScope.role == 'customer' }">
                <table class="table">
                    <thead>
                    <th></th>
                    <th></th>
                    <th></th>
                    </thead>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/fc/orderpageCustomer" class="btn btn-lg btn-primary">Order</a></td>
                        <td><a href="${pageContext.request.contextPath}/fc/cart" class="btn btn-lg btn-primary">Cart</a></td>

                        <td><form method="post" action="${pageContext.request.contextPath}/fc/profile">
                            <button type="submit" class="btn btn-lg btn-primary" name="userprofile" value="${sessionScope.user.id}">Profile</button>
                        </form></td>
                    </tr>
                </table>

            </c:if>

        </div>
    <div class="col-sm-3"></div>
</div>
    </jsp:body>
</t:genericpage>