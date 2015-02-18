<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources.resources">
    <jsp:include page="../includes/header.jsp" />
    <jsp:include page="../includes/column_left_home.jsp" />
    <!-- start the middle column -->
    <div id="main">

        <h1><fmt:message key="drinks.catalogue"/></h1>
        <br>
        <form name="drinksForm" method="POST" action="controller">
            <input type="hidden" name="command" value="ingredients_catalogue" />
            <table id="drinks_catalogue">
                <tr>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="available.quantity"/></th>
                    <th><fmt:message key="ordered.quantity"/></th>
                </tr>
                <c:forEach var="drink" items="${availableDrinks}">
                    <tr>
                        <td>${drink.name}</td>
                        <td>${drink.price}</td>
                        <td>${drink.quantity}</td>
                        <td><input type="text" name="${drink.code}" value="0" size="1" /></td>
                    </tr>
                </c:forEach>
            </table>
            <br /><br />
            <input type="submit" value="<fmt:message key="order.drinks"/>"/>
        </form>
        <br/>
        ${errorDrinksMessage}
        <br/>
    </div>


    <!-- end the middle column -->

    <jsp:include page="../includes/footer.jsp" />
</fmt:bundle>

