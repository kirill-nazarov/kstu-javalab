<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources.resources">
    <jsp:include page="/jsp/includes/header.jsp" />
    <jsp:include page="/jsp/includes/column_left_home.jsp" />

    <!-- start the middle column -->
    <div id="main">
        <h1><a name="espresso"><fmt:message key="espresso"/></a></h1> 
        <p><fmt:message key="espresso.content"/></p><br>  

        <h1><a name="americano"><fmt:message key="americano"/></a></h1> 
        <p><fmt:message key="americano.content"/></p><br>   

        <h1><a name="latte"><fmt:message key="latte"/></a></h1> 
        <p><fmt:message key="latte.content"/></p><br>   

        <h1><a name="cappuccino"><fmt:message key="cappuccino"/></a></h1>  
        <p><fmt:message key="cappuccino.content"/></p><br> 


    </div>


    <!-- end the middle column -->

    <jsp:include page="/jsp/includes/footer.jsp" />
</fmt:bundle>