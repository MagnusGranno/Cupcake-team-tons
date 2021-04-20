<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Employee page
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="container-fluid">
            <div class="row">
                <h1 class="text-center pb-4">Admin Page</h1>

                <div class="col-lg-6">
                    <a href="${pageContext.request.contextPath}/fc/adminorder" type="button"
                       class="btn btn-primary btn-lg w-100 mh-100">Orderlist</a>
                </div>

                <div class="col-lg-6">
                    <a href="${pageContext.request.contextPath}/fc/admincustomer" type="button" class="btn btn-primary btn-lg w-100 h-100">Kunder</a>
                </div>


            </div>
        </div>

    </jsp:body>
</t:genericpage>
