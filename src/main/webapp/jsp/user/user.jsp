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

        <h1><fmt:message key="hello"/>${user.firstName} ${user.lastName}!</h1>


        <form name="userForm" method="POST" action="controller">
            <input type="hidden" name="command" value="drinks_catalogue" />
            <fmt:message key="drinks.catalogue"/> <br /><br />
            <input type="submit" value="<fmt:message key="goto.drinks.catalogue"/>"/>
        </form>
        <br /><br />
        <form name="userForm" method="POST" action="controller">
            <input type="hidden" name="command" value="show_user_orders" />
            <fmt:message key="show.processed.orders"/><br /><br />
            <input type="submit" value="<fmt:message key="show.orders"/>"/>
        </form>

        <br>


        <!-- end the middle column -->
    </div>

    <jsp:include page="../includes/footer.jsp" />
</fmt:bundle>