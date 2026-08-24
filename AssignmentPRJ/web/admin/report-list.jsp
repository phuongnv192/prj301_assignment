<%-- 
    Document   : report-list
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<h2 class="page-title">Manage Reports</h2>

<div class="panel-box">
    <form method="get" action="<c:url value='/admin/reports'/>" class="form-inline">
        <label for="status" style="margin-right: 10px; font-weight: 600;">Filter status</label>
        <select id="status" name="status" class="form-control" onchange="this.form.submit()">
            <option value="">All</option>
            <option value="PENDING" ${param.status == 'PENDING' ? 'selected' : ''}>Đang chờ duyệt</option>
            <option value="RESOLVED" ${param.status == 'RESOLVED' ? 'selected' : ''}>Đã duyệt</option>
            <option value="REJECTED" ${param.status == 'REJECTED' ? 'selected' : ''}>Đã từ chối</option>
        </select>
    </form>
</div>

<div class="panel-box">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>User</th>
                <th>Plant</th>
                <th>Title</th>
                <th>Status</th>
                <th>Created</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="report" items="${reports}">
                <tr>
                    <td class="cell-center">${report.reportID}</td>
                    <td>${report.user.fullName} (${report.user.username})</td>
                    <td>${report.plant.plantName}</td>
                    <td>${report.title}</td>
                    <td class="cell-center">
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
                    </td>
                    <td class="cell-center"><fmt:formatDate value="${report.createdAt}" pattern="dd-MM-yyyy" timeZone="Asia/Ho_Chi_Minh" /></td>
                    <td class="cell-center">
                        <c:choose>
                            <c:when test="${report.reportStatus eq 'PENDING'}">
                                <a class="btn btn-primary btn-xs btn-primary-custom report-action-link" href="<c:url value='/admin/reports/reply?id=${report.reportID}'/>">Reply</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-default btn-xs report-action-link" href="<c:url value='/admin/reports/reply?id=${report.reportID}'/>">View</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty reports}">
                <tr><td colspan="7" class="text-center text-muted-note">No reports.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="/footer.jsp" %>

