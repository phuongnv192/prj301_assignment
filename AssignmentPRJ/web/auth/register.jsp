<%-- 
    Document   : register
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Register" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Register</h2>
<div class="panel-box">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <form method="post" action="<c:url value='/register'/>">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" type="text" name="username" value="${param.username}" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" type="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="fullName">Full name</label>
            <input id="fullName" type="text" name="fullName" value="${param.fullName}" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" type="email" name="email" value="${param.email}">
        </div>
        <div class="action-row">
            <button type="submit" class="btn btn-primary btn-primary-custom">Create account</button>
        </div>
    </form>

    <p class="action-row"><a href="<c:url value='/login'/>">Back to Login</a></p>
</div>

<%@ include file="/footer.jsp" %>

