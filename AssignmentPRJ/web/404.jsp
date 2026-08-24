<%-- 
    Document   : 404
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="404 Not Found" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">404 - Not Found</h2>
<div class="panel-box">
    <p>The page you requested does not exist.</p>
    <p><a class="btn btn-primary btn-primary-custom" href="<c:url value='/customer/home'/>">Back to home</a></p>
</div>

<%@ include file="/footer.jsp" %>

