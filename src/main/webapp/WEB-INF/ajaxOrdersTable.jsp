<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>

<tOrder:ordersTable ordersPaginatedList="${ordersPaginatedList}"/>