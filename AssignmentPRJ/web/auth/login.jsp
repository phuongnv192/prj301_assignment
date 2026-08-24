<%-- 
    Document   : login
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Login" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Login</h2>
<div class="panel-box">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form method="post" action="<c:url value='/login'/>">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" type="text" name="username" value="${param.username}" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" type="password" name="password" required>
        </div>
        <div class="action-row">
            <button type="submit" class="btn btn-primary btn-primary-custom">Login</button>
            <button type="reset" class="btn btn-default btn-secondary-custom">Reset</button>
        </div>
    </form>

    <p class="action-row"><a href="<c:url value='/register'/>">Register new account</a></p>
</div>

<%@ include file="/footer.jsp" %>

