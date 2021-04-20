<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Admin customer page
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="pb-4">
            <h1 class="pb-2">Customers</h1>
            <a href="${pageContext.request.contextPath}/fc/employeepage" class="btn btn-primary btn-lg">Back to admin page</a>
        </div>



        <table class="table">
            <thead class="table-info">
            <th>Customer ID</th>
            <th>Email</th>
            <th>Created</th>
            <th>Balance</th>
            <th></th>
            </thead>
            <c:forEach var="customer" items="${requestScope.customerList}">
                <tr>
                    <td>${customer.id}</td>
                    <td><form action="${pageContext.request.contextPath}/fc/admincustomerorder" method="post">
                        <div class="input-group mb-3">
                        <button name="customerid" class="btn btn-outline-primary" type="submit" value="${customer.id}">${customer.email}</button>
                        </div>
                    </form>
                    </td>
                    <td>${customer.timestamp}</td>
                    <td>${customer.balance} kr.</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/fc/admincustomer" method="post">
                    <div class="input-group mb-3">
                        <input name="amount" type="text" class="form-control">
                        <button name="updatebalance" class="btn btn-primary" type="submit" value="${customer.id}">Update</button>
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
