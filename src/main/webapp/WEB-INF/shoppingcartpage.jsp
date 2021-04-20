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



        <p>Tester</p>

        <c:forEach var="cupcake" items="${sessionScope.cupcakeList}">
            <p> ${cupcake.}</p>
            <table class="table">
                <thead>
                <th>Order id</th>
                <th></th>
                </thead>
                <tr>
                    <td>${}</td>
                </tr>

            </table>
        </c:forEach>




    </jsp:body>
</t:genericpage>