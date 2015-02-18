<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources.resources">
    <jsp:include page="/jsp//includes/header.jsp" />
    <jsp:include page="/jsp/includes/column_left_home.jsp" />

    <!-- start the middle column -->
    <div id="main">

        <h1><fmt:message key="additional.ingredients"/></h1>
        <p><fmt:message key="additional.ingredients.text"/></p>

    </div>


    <!-- end the middle column -->

    <jsp:include page="/jsp/includes/footer.jsp" />
</fmt:bundle>
