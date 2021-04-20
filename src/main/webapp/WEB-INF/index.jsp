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




                <p class="text-center">Please <a type="button" class="btn btn-sm btn-outline-primary"
                             href="${pageContext.request.contextPath}/fc/registerpage">Sign up</a>
                or <a type="button" class="btn btn-sm btn-outline-primary"
                   href="${pageContext.request.contextPath}/fc/loginpage">Login</a> to begin</p>


        </div>
             <c:if test="${sessionScope.role == 'customer' }">
                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>
            </c:if>

        </div>
    <div class="col-sm-3"></div>
</div>
    </jsp:body>
</t:genericpage>