<%-- 
    Document   : list
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Care Logs" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Care Logs</h2>
<div class="panel-box">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Plant</th>
                <th>Date</th>
                <th>Action</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="log" items="${careLogs}">
                <tr>
                    <td class="cell-center">${log.logID}</td>
                    <td>${log.plant.plantName}</td>
                    <td class="cell-center"><fmt:formatDate value="${log.actionDate}" pattern="dd-MM-yyyy" timeZone="Asia/Ho_Chi_Minh" /></td>
                    <td class="cell-center">${log.actionType}</td>
                    <td>${log.description}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty careLogs}">
                <tr><td colspan="5" class="text-center text-muted-note">No logs yet.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="/footer.jsp" %>

