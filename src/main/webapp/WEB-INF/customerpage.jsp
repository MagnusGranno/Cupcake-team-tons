<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Profile
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>

        <div class="container">
            <div class="row">
                <div class="col-sm-4"><h1 class="pb-3">${sessionScope.email} </h1></div>
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <h2 class="pb-2">Your Balance: </h2>
                    <h4>${requestScope.balance} kr.</h4></div>

            </div>
                <h2 class="pb-2">Your orders:</h2>
                <table class="table">
                    <thead class="table-info">
                    <th>Order Id</th>
                    <th>Price</th>
                    <th>Date</th>
                    </thead>
                    <c:forEach var="customerorder" items="${requestScope.profileOrders}">
                        <tr>
                            <td>${customerorder.order_id}</td>
                            <td>${customerorder.total_price} kr.</td>
                            <td>${customerorder.timestamp}</td>
                        </tr>
                    </c:forEach>


                </table>











        </div>

    </jsp:body>

</t:genericpage>

