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
        <h1>Kundeliste </h1>

        <form action="${pageContext.request.contextPath}/fc/admincustomer" method="post">


        <table class="table">
            <thead>
            <th>Kunde ID</th>
            <th>Email</th>
            <th>Balance</th>
            <th></th>
            </thead>
            <c:forEach var="customer" items="${requestScope.customerList}">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.email}</td>
                    <td>${customer.balance} kr.</td>
                    <td>
                    <div class="input-group mb-3">
                        <input name="amount" type="text" class="form-control">
                        <button name="updatebalance" class="btn btn-outline-primary" type="submit" value="${customer.id}" id="updatebalance" >Indsæt</button>
                    </div>
                    </td>
                </tr>
            </c:forEach>
        </table>

        </form>
        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>
    </jsp:body>
</t:genericpage>
