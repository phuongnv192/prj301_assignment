<%-- 
    Document   : home
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="pageTitle" value="Customer Dashboard" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Customer Dashboard</h2>
<div class="panel-box">
    <div class="row">
        <div class="col-md-6">
            <div class="stat-box">
                <span class="small-muted">Total plants</span>
                <strong>${plantCount}</strong>
            </div>
        </div>
        <div class="col-md-6">
            <div class="stat-box">
                <span class="small-muted">Pending reports</span>
                <strong>${pendingReportCount}</strong>
            </div>
        </div>
    </div>
</div>

<h3>Care Logs</h3>
<div class="panel-box">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>Plant</th>
                <th>Date</th>
                <th>Action</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="log" items="${recentCareLogs}">
                <tr>
                    <td class="cell-left">${log.plant.plantName}</td>
                    <td class="cell-center"><fmt:formatDate value="${log.actionDate}" pattern="dd-MM-yyyy" timeZone="Asia/Ho_Chi_Minh" /></td>
                    <td class="cell-left">${log.actionType}</td>
                    <td>${log.description}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty recentCareLogs}">
                <tr><td colspan="4" class="text-center text-muted-note">No data.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="/footer.jsp" %>

