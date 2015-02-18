<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources.resources">
    <div id="column_left">

        <form>
            <select name="lang" onchange='this.form.submit()'>
                <c:choose>
                    <c:when test="${userLocale.language =='ru'}">
                        <option value='ru' selected>Русский
                        <option value='en'>English
                        </c:when>
                        <c:otherwise>
                        <option value='ru'>Русский
                        <option value='en' selected>English
                        </c:otherwise>
                    </c:choose>
            </select>
        </form>
        <br><br>
        <a href="/CoffeeMaker/index.jsp"><fmt:message key="main.page"/></a><br><br>
        <a href="/CoffeeMaker/jsp/static/drinks.jsp"><fmt:message key="drinks"/></a><br><br>
        <a href="/CoffeeMaker/jsp/static/ingredients.jsp"><fmt:message key="additional.ingredients"/></a><br><br>
        <c:if test="${!empty  user}">
            <p><strong><fmt:message key="logged.in.as"/>: ${user.firstName} ${user.lastName} ID:${user.id}</strong></p>
            <a href="controller?command=logout"><fmt:message key="logout"/></a>
        </c:if>

    </div>
</fmt:bundle>
