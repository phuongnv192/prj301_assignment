<%-- 
    Document   : my-list
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="pageTitle" value="My Reports" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">My Reports</h2>
<div class="panel-box">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Plant</th>
                <th>Title</th>
                <th>Status</th>
                <th>Admin Reply</th>
                <th>Created</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="report" items="${reports}">
                <tr>
                    <td class="cell-center">${report.reportID}</td>
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
                    <td>${report.adminReply}</td>
                    <td class="cell-center"><fmt:formatDate value="${report.createdAt}" pattern="dd-MM-yyyy" timeZone="Asia/Ho_Chi_Minh" /></td>
                </tr>
            </c:forEach>
            <c:if test="${empty reports}">
                <tr><td colspan="6" class="text-center text-muted-note">No reports found.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="/footer.jsp" %>

