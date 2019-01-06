<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources">
    <jsp:include page="/jsp/includes/header.jsp" />
    <jsp:include page="/jsp/includes/column_left_home.jsp" />



    <!-- start the middle column -->

    <div id="main">
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login" />
            <fmt:message key="login"/>:<br/>
            <input type="text" name="login" value=""/>
            <br/><fmt:message key="password"/>:<br/>
            <input type="password" name="password" value=""/>
            <br/>
            ${errorLoginPassMessage}
            <br/>
            <input type="submit" value="<fmt:message key="login.button"/>"/>
        </form>
        <!-- end the middle column -->
    </div>

    <jsp:include page="/jsp/includes/footer.jsp" />
</fmt:bundle>

