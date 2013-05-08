<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css" />
        <title>Business trip reporting</title>
    </head>

    <body>
        <div class="stred">
            <div class="obal-kul-roh">
                <div class="horlev"></div><div class="horprav"></div>
                <div class="obal-vnitrek">
                    <h1>Business Trip Reporting</h1>
                    <br/>
                    <c:choose>
                        <c:when test="${it.user != null}">
                            <p>Jste přihlášen jako <c:out value="${it.user.nickname}" />. <a href="${fn:escapeXml(it.url)}">Odhlásit se.</a></p>
                        </c:when>
                        <c:otherwise>

                            <p>Ještě nejste přihlášen.</p>
                            
                            <p><a href="${fn:escapeXml(it.url)}">Přihlaste se pomocí Google účtu.</a></p>
                            <br/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="dollev"></div><div class="dolprav"></div>
            </div>
        </div>
    </body>
</html>