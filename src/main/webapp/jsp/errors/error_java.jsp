<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<jsp:include page="../includes/column_left_home.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!-- start the middle column -->

<div id="main">

    <h1>Java Error</h1>
    <p>Java has thrown an exception.</p>
    <p>To continue, click the back button or select a link from this page.</p>
    <br>

    <h2>Details</h2>
    <p>
        ${pageContext.errorData.servletName} threw a <br>
        ${pageContext.exception}<br>
        <c:forEach var="line" items="${pageContext.errorData.throwable.stackTrace}">
            &nbsp;&nbsp;&nbsp;&nbsp;at ${line}<br>
        </c:forEach>
    </p>

    <!-- end the middle column -->
</div>

<jsp:include page="../includes/footer.jsp" />
