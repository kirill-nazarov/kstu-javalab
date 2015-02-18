<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<jsp:include page="../includes/column_left_home.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start the middle column -->

<div id="main">

<%@ page isErrorPage="true" %>


    <h1>404 Error</h1>
    <p>The server was not able to find the file you requested.</p>
    <p>To continue, click the Back button or select a link from this page.</p>
    <br>

    <h2>Details</h2>
    <p>Requested URI: ${pageContext.errorData.requestURI}</p>



<!-- end the middle column -->
</div>

<jsp:include page="../includes/footer.jsp" />

