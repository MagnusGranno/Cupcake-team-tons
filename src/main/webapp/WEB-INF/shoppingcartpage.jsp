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
        <h2 class="pb-2">Cart for ${sessionScope.email}</h2>


        <table class="table">
            <thead class="table-info">
            <th>Topping</th>
            <th>Bottom</th>
            <th>Amount</th>
            <th>Price</th>
            <th>Total</th>

            </thead>
            <c:forEach var="cartList" items="${sessionScope.cartList}">
                <tr>
                    <td>${cartList.topping.name}</td>
                    <td>${cartList.bottom.name}</td>
                    <td>${cartList.amount}</td>
                    <td>${cartList.price} kr.</td>
                    <td></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <%--TODO: få det til at se pænere ud på hjemmesiden--%>
                <td>${sessionScope.total} kr.</td>
            </tr>
        </table>

        <div class="row">
            <div class="col-10"></div>
            <div class="col">
                <form method="post" action="${pageContext.request.contextPath}/fc/addOrder">
                    <button class="btn btn-primary" type="submit">Bestil cupcakes</button>
                </form>
            </div>
        </div>



    </jsp:body>
</t:genericpage>