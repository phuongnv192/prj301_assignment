<%-- 
    Document   : 403
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="403 Forbidden" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">403 - Forbidden</h2>
<div class="panel-box">
    <p>You do not have permission to access this page.</p>
    <p><a class="btn btn-primary btn-primary-custom" href="<c:url value='/login'/>">Back to login</a></p>
</div>

<%@ include file="/footer.jsp" %>

