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
        <form method="post" action="${pageContext.request.contextPath}/fc/shoppingcart">
            <div class="row">
                <div class="col-lg-3 pb-2">
                    <select class="form-select " name="bottom" id="bottom">
                    <option selected>Choose your bottom</option>
                        <c:forEach var="bottom" items="${applicationScope.bottomList}">
                            <option value="${bottom.id}">${bottom.name} - ${bottom.price} kr.</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-lg-3 pb-2">
                    <select class="form-select" name="top" id="top">
                        <option selected>Choose your topping</option>
                        <c:forEach var="topping" items="${applicationScope.toppingList}">
                            <option value="${topping.id}">${topping.name} - ${topping.price} kr.</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-lg-3 pb-2">
                    <select class="form-select" name="amount" id="amount">
                        <option selected>How many cupcakes</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                    </select>
                </div>
                <div class="col-lg-3 pb-2">
                    <button type="submit" class="btn btn-primary">Læg i kurv</button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:genericpage>