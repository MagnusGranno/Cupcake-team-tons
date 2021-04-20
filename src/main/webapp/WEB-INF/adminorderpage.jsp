<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Order page for employees
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        Here is a list of all orders.
        <table class="table">
            <thead>
            <th>OrderID</th>
            <th>UserID</th>
            <th>Total Price</th>
            <th>Date</th>
            </thead>
            <c:forEach var="order" items="${requestScope.orderWEmail}">
                <tr>
                    <td>${order.order_id}</td>
                    <td>${order.email}</td>
                    <td>${order.total_price} kr.</td>
                    <td>${order.timestamp}</td>
                </tr>
            </c:forEach>
        </table>

    </jsp:body>
</t:genericpage>
