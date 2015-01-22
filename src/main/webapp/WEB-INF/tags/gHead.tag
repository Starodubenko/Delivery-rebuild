<%@ tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <head>
        <title><fmt:message key="farm.name"/></title>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
        <jsp:doBody/>
    </head>
</fmt:bundle>