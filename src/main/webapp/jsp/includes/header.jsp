<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${userLocale}" />
<fmt:bundle basename="resources">
    <!DOCTYPE html>

    <html>
        <head>
            <title><fmt:message key="title"/></title>
            <link rel="stylesheet" href="/CoffeeMaker/jsp/style/coffee.css">
            <link rel="icon" type="image/x-icon" href="/CoffeeMaker/jsp/style/favicon.ico" />
        </head>
        <body>
            <div id="header">
                <p><fmt:message key="title"/></p>
            </div>
        </fmt:bundle>

