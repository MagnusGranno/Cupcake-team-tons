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
        <div class="pb-4">
            <h1 class="pb-2 pl-lg-5">Orders</h1>
            <a href="${pageContext.request.contextPath}/fc/employeepage" class="btn btn-primary btn-lg">Back to admin page</a>
        </div>
        <table class="table">
            <thead class="table-info">
            <th>OrderID</th>
            <th>UserID</th>
            <th>Total Price</th>
            <th>Date</th>
            <th></th>
            </thead>
            <c:forEach var="order" items="${requestScope.orderWEmail}">
                <tr>
                    <td>${order.order_id}</td>
                    <td>${order.email}</td>
                    <td>${order.total_price} kr.</td>
                    <td>${order.timestamp}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/fc/adminorder" method="post">
                        <div class="input-group mb-3">
                            <button name="remove" class="btn btn-primary" type="submit" value="${order.order_id}">Remove</button>
                        </div>
                    </form>
                    </td>
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
