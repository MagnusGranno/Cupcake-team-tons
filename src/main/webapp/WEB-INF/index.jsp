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

        <div class="w-100 bg-white border-bottom shadow-sm mx-auto">
            <h1>Velkommen til Olsker Cupcakes</h1>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                Main page for this 2. semester start project used at cphbusiness.dk
            </div>
            <div>
                <table class="table">
                    <thead>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    </thead>
                    <c:forEach var="bottom" items="${applicationScope.bottomList}">
                    <tr>
                        <td>${bottom.id}</td>
                        <td>${bottom.name}</td>
                        <td>${bottom.price} kr.</td>
                    </tr>
                    </c:forEach>
                </table>

            </div>


            <c:if test="${sessionScope.role == 'employee' }">
                <p style="font-size: larger">This is what you can do,
                    since your are logged in as an employee</p>
                 <p><a href="fc/employeepage">Employee Page</a>
             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">
                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>
            </c:if>

        </div>

    </jsp:body>
</t:genericpage>