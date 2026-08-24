<%-- 
    Document   : report-reply
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="pageTitle" value="Reply Report" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Reply Report</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<div class="panel-box">
    <ul class="info-list">
        <li><strong>Report ID:</strong> ${report.reportID}</li>
        <li><strong>User:</strong> ${report.user.fullName} (${report.user.username})</li>
        <li><strong>Plant:</strong> ${report.plant.plantName}</li>
        <li><strong>Title:</strong> ${report.title}</li>
        <li><strong>Description:</strong> ${report.description}</li>
        <li>
            <strong>Current status:</strong>
            <c:choose>
                <c:when test="${report.reportStatus eq 'PENDING'}">
                    <span class="label-status label-pending">Đang chờ duyệt</span>
                </c:when>
                <c:when test="${report.reportStatus eq 'RESOLVED'}">
                    <span class="label-status label-resolved">Đã duyệt</span>
                </c:when>
                <c:otherwise>
                    <span class="label-status label-rejected">Đã từ chối</span>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>

    <c:if test="${report.reportStatus ne 'PENDING'}">
        <p class="text-muted-note">Report solved, can not be edited.</p>
    </c:if>
</div>

<div class="panel-box">
    <form method="post" action="<c:url value='/admin/reports/reply'/>">
        <input type="hidden" name="reportID" value="${report.reportID}">
        <div class="form-group">
            <label for="reportStatus"><strong>Status</strong></label>
            <select id="reportStatus" name="reportStatus" class="form-control" required ${report.reportStatus ne 'PENDING' ? 'disabled' : ''}>
                <c:choose>
                    <c:when test="${report.reportStatus eq 'RESOLVED'}">
                        <option value="RESOLVED" selected>Đã duyệt</option>
                        <option value="REJECTED">Đã từ chối</option>
                    </c:when>
                    <c:when test="${report.reportStatus eq 'REJECTED'}">
                        <option value="RESOLVED">Đã duyệt</option>
                        <option value="REJECTED" selected>Đã từ chối</option>
                    </c:when>
                    <c:otherwise>
                        <option value="RESOLVED" selected>Đã duyệt</option>
                        <option value="REJECTED">Đã từ chối</option>
                    </c:otherwise>
                </c:choose>
            </select>
            <c:if test="${report.reportStatus ne 'PENDING'}">
                <input type="hidden" name="reportStatus" value="${report.reportStatus}">
            </c:if>
        </div>

        <div class="form-group">
            <label for="adminReply"><strong>Admin Reply</strong></label>
            <textarea id="adminReply" name="adminReply" class="form-control" rows="5" ${report.reportStatus ne 'PENDING' ? 'readonly' : ''}>${report.adminReply}</textarea>
        </div>

        <div class="form-group">
            <c:if test="${report.reportStatus eq 'PENDING'}">
                <button type="submit" class="btn btn-primary btn-primary-custom">Save reply</button>
            </c:if>
            <a href="<c:url value='/admin/reports'/>" class="btn btn-default btn-secondary-custom">Back to list</a>
        </div>
    </form>
</div>

<%@ include file="/footer.jsp" %>

