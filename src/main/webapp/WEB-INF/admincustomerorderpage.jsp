<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Admin customer order page
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="pb-4">
        <h1 class="pb-2">Orders from ${requestScope.customerEmail}</h1>
        <a href="${pageContext.request.contextPath}/fc/admincustomer" class="btn btn-primary btn-lg">Back to customer list</a>
        </div>





        <table class="table">
            <thead class="table-info">
            <th>Order Id</th>
            <th>Total Price</th>
            <th>Date</th>
            </thead>
            <c:forEach var="customerorder" items="${requestScope.customerOrderList}">
                <tr>
                    <td>${customerorder.order_id}</td>
                    <td>${customerorder.total_price}</td>
                    <td>${customerorder.timestamp} kr.</td>
                </tr>
            </c:forEach>
        </table>


        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>
    </jsp:body>
</t:genericpage>
