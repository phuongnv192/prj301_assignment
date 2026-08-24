<%-- 
    Document   : home
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Admin Dashboard" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Admin Dashboard</h2>
<div class="panel-box">
    <div class="row">
        <div class="col-md-4">
            <div class="stat-box">
                <span class="small-muted">Total users</span>
                <strong>${totalUsers}</strong>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-box">
                <span class="small-muted">Total plants</span>
                <strong>${totalPlants}</strong>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-box">
                <span class="small-muted">Pending reports</span>
                <strong>${pendingReports}</strong>
            </div>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>

