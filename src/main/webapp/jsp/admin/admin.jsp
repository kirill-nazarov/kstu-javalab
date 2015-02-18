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

        <h1><fmt:message key="admin.interface"/></h1>

        <h3><fmt:message key="admin"/>: ${user.firstName} ${user.lastName}</h3>


        <form name="adminForm" method="POST" action="controller">
            <input type="hidden" name="command" value="show_drinks" />
            <fmt:message key="go.to.drink.refill"/> <br /><br /><input type="submit" value="<fmt:message key="refill.drinks"/>"/>
        </form>
        <br /><br />
        <form name="adminForm" method="POST" action="controller">
            <input type="hidden" name="command" value="show_ingredients" />
            <fmt:message key="go.to.ingredient.refill"/> <br /><br /><input type="submit" value="<fmt:message key="refill.ingredients"/>"/>
        </form>
        <!-- end the middle column -->
    </div>

    <jsp:include page="../includes/footer.jsp" />
</fmt:bundle>
