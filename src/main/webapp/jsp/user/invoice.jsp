<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources">
    <jsp:include page="../includes/header.jsp" />
    <jsp:include page="../includes/column_left_home.jsp" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- start the middle column -->
    <div id="main">
        <h1><fmt:message key="order.confirmed"/></h1>
        <br />
        <h3><fmt:message key="order.description"/></h3>
        <br />
        <p><strong><fmt:message key="date"/>:</strong> ${invoice.invoiceDateDefaultFormat}</p>
        <p><strong><fmt:message key="total.price"/>:</strong> ${invoice.invoiceTotalCurrencyFormat}</p>
        <p><strong><fmt:message key="order.content"/>:</strong></p>
        <br />
        <c:forEach var="drink" items="${invoice.orderedDrinks}" varStatus="status">
            <table>
                <tr>
                    <th>№</th>
                    <th><fmt:message key="drink.name"/></th>
                    <c:if test="${!empty  drink.ingredients}">
                        <th colspan="3"><fmt:message key="ingredients"/></th>
                    </c:if>
                </tr>
                <tr>
                    <td>${status.index +1}</td>
                    <td>${drink.name}</td>
                    <c:forEach var="ingredient" items="${drink.ingredients}">
                        <td>${ingredient.name}</td>
                    </c:forEach>
                </tr>
            </table>
            <br />
        </c:forEach>
        <br />
        <br />
        <h1><fmt:message key="drinks.info"/></h1>
        <br />
        <table>
            <tr>
                <th><fmt:message key="drink.name"/></th>
                <th><fmt:message key="price"/></th>
            </tr>
            <c:forEach var="drink" items="${availableDrinks}">
                <tr>
                    <td>${drink.name}</td>
                    <td>${drink.price}</td>
                </tr>
            </c:forEach>
        </table>
        <br />
        <h1><fmt:message key="ingredients.info"/></h1>
        <br />
        <table>
            <tr>
                <th><fmt:message key="ingredient.name"/></th>
                <th><fmt:message key="price"/></th>
            </tr>
            <c:forEach var="ingredient" items="${availableIngredients}">
                <tr>
                    <td>${ingredient.name}</td>
                    <td>${ingredient.price}</td>
                </tr>
            </c:forEach>
        </table>



    </div>
    <!-- end the middle column -->

    <jsp:include page="../includes/footer.jsp" />
</fmt:bundle>