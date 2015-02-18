<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources.resources">
    <jsp:include page="../includes/header.jsp" />
    <jsp:include page="../includes/column_left_home.jsp" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- start the middle column -->
    <div id="main">

        <h1><fmt:message key="drinks.refill"/></h1>
        <br>
        <form name="drinksForm" method="POST" action="controller">
            <input type="hidden" name="command" value="refill_drinks" />
            <table id="drinks">
                <tr>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="available.quantity"/></th>
                    <th><fmt:message key="quantity.to.refill"/></th>
                </tr>
                <c:forEach var="drink" items="${drinks}">
                    <tr>
                        <td>${drink.name}</td>
                        <td>${drink.quantity}</td>
                        <td><input type="text" name="${drink.code}" value="0" size="1" /></td>
                    </tr>
                </c:forEach>
            </table>
            <br /><br />
            <input type="submit" value="<fmt:message key="refill.drinks"/>"/>
        </form>
        <br>
        <form name="adminForm" method="POST" action="controller">
            <input type="hidden" name="command" value="show_drinks" />
            <input type="submit" value="<fmt:message key="refresh.data"/>"/>
        </form>
        <br/>
        ${errorDrinksMessage}
        <br/>
        <br/>
        ${refilledDrinksMessage}
        <br/>
    </div>


    <!-- end the middle column -->

    <jsp:include page="../includes/footer.jsp" />
</fmt:bundle>
