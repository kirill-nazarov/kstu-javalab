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

        <h1><fmt:message key="add.ingredients"/></h1>
        <br />
        <form name="ingredientsForm" method="POST" action="controller">
            <input type="hidden" name="command" value="confirm_order" />
            <table id="ordered_drinks_catalogue">
                <tr>
                    <th>№</th>
                    <th><fmt:message key="drink.name"/></th>
                    <th colspan="6"><fmt:message key="ingredients"/></th>
                </tr>
                <c:forEach var="drink" items="${orderedDrinks}" varStatus="status">
                    <tr>
                        <td>${status.index +1}</td>
                        <td>${drink.name}</td>
                        <c:forEach var="ingredient" items="${availableIngredients}">
                            <td>${ingredient.name}</td>
                            <td><input type="checkbox" name="ingredients" value="${status.index}.${drink.code}.${ingredient.code}" /></td>
                            </c:forEach>
                    </tr>
                </c:forEach>
            </table>
            <br />
            <input type="submit" value="<fmt:message key="order.drinks.ingredients"/>"/>
        </form>
        <br />
        <h1><fmt:message key="drinks.info"/></h1>
        <br />
        <table>
            <tr>
                <th><fmt:message key="drink.name"/></th>
                <th><fmt:message key="price"/></th>
                <th><fmt:message key="available.quantity"/></th>
            </tr>
            <c:forEach var="drink" items="${availableDrinks}">
                <tr>
                    <td>${drink.name}</td>
                    <td>${drink.price}</td>
                    <td>${drink.quantity}</td>
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
                <th><fmt:message key="available.quantity"/></th>
            </tr>
            <c:forEach var="ingredient" items="${availableIngredients}">
                <tr>
                    <td>${ingredient.name}</td>
                    <td>${ingredient.price}</td>
                    <td>${ingredient.quantity}</td>
                </tr>
            </c:forEach>
        </table>
    </div>


    <!-- end the middle column -->

    <jsp:include page="../includes/footer.jsp" />
</fmt:bundle>