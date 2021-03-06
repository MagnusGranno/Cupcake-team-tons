<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Cart
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <h2 class="pb-2">Cart for ${sessionScope.email}</h2>

        <table class="table table-responsive-lg">
            <thead class="table-primary">
                <th>Remove</th>
                <th>Topping</th>
                <th>Bottom</th>
                <th>Amount</th>
                <th>Price</th>
            </thead>

            <c:forEach var="cartList" items="${sessionScope.cartList}">
                <tr>
                    <form method="post" action="${pageContext.request.contextPath}/fc/removeitem">
                        <td><button class="btn btn-primary" type="submit" name="removeItem" value="${cartList.id}">X</button></td>
                    </form>
                    <td>${cartList.topping.name}</td>
                    <td>${cartList.bottom.name}</td>
                    <td>${cartList.amount}</td>
                    <td>${cartList.price} kr.</td>
                </tr>
            </c:forEach>

            <c:if test="${sessionScope.total !=null}">
            <thead class="table-primary">
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th>Total: ${sessionScope.total} kr.</th>
            </thead>
            </c:if>
        </table>
        <div class="row">
            <div class="col-10"></div>
            <div class="col">
                <c:if test="${sessionScope.total !=null}">
                    <form method="post" action="${pageContext.request.contextPath}/fc/addOrder">
                        <button class="btn btn-primary" type="submit">Bestil cupcakes</button>
                    </form>
                </c:if>
            </div>
        </div>


    </jsp:body>
</t:genericpage>