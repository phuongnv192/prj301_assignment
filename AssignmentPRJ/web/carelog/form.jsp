<%-- 
    Document   : form
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Create Care Log" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Create Care Log</h2>
<div class="panel-box">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form method="post" action="<c:url value='/carelogs/create'/>">
        <input type="hidden" name="plantID" value="${plantID}">
        <div class="form-group">
            <label>Action Type</label>
            <input type="text" name="actionType" required>
        </div>
        <div class="form-group">
            <label>Description</label>
            <textarea name="description" rows="4"></textarea>
        </div>
        <div class="action-row">
            <button type="submit" class="btn btn-primary btn-primary-custom">Save</button>
            <a class="btn btn-default btn-secondary-custom" href="<c:url value='/plants/detail?id=${plantID}'/>">Back to Plant Detail</a>
        </div>
    </form>
</div>

<%@ include file="/footer.jsp" %>

