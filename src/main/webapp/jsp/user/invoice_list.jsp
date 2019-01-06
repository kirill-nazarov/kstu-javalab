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
        <h1><fmt:message key="processed.orders"/></h1>
        <c:if test="${empty  invoiceList}">
            <h3><fmt:message key="no.orders"/></h3>
        </c:if>
        <c:forEach var="invoice" items="${invoiceList}" varStatus="status">
            <br />
            <p><strong><fmt:message key="order.number"/> ${invoice.id}</strong></p>
            <p><strong> <fmt:message key="date"/> : </strong>${invoice.invoiceDateDefaultFormat}</p>
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
            <hr />
        </c:forEach>


    </div>
    <!-- end the middle column -->

    <jsp:include page="../includes/footer.jsp" />
</fmt:bundle>
