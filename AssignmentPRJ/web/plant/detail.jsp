<%-- 
    Document   : detail
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Plant Detail" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Plant Detail</h2>
<div class="panel-box plant-detail-layout">
    <div class="plant-detail-image">
        <img src="${not empty plant.imageUrl ? plant.imageUrl : 'https://placehold.co/600x400/EEF4FF/1F3C88?text=Plant'}" alt="${plant.plantName}" />
    </div>

    <div class="plant-detail-info">
        <ul class="info-list">
            <li><strong>ID:</strong> ${plant.plantID}</li>
            <li><strong>Name:</strong> ${plant.plantName}</li>
            <li><strong>Category:</strong> ${plant.category.categoryName}</li>
            <li><strong>Owner:</strong> ${plant.owner.fullName} (${plant.owner.username})</li>
            <li><strong>Health:</strong> ${plant.healthStatus}</li>
            <li><strong>Note:</strong> ${plant.note}</li>
            <li><strong>Created At:</strong> <fmt:formatDate value="${plant.createdAt}" pattern="dd-MM-yyyy" timeZone="Asia/Ho_Chi_Minh" /></li>
        </ul>

        <div class="action-row">
            <a class="btn btn-primary btn-primary-custom" href="<c:url value='/plants/edit?id=${plant.plantID}'/>">Edit</a>
            <a class="btn btn-primary btn-primary-custom" href="<c:url value='/reports/create?plantId=${plant.plantID}'/>">Create report</a>
            <a class="btn btn-default btn-secondary-custom" href="<c:url value='/plants'/>">Back</a>
        </div>
    </div>
</div>

<h3>Care Logs</h3>
<div class="panel-box">
    <p class="action-row">    <a class="btn btn-default btn-primary-custom" href="<c:url value='/carelogs/create?plantID=${plant.plantID}'/>">+ Add care log</a>
    </p>    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>Date</th>
                <th>Action</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="log" items="${careLogs}">
                <tr>
                    <td><fmt:formatDate value="${log.actionDate}" pattern="dd-MM-yyyy" timeZone="Asia/Ho_Chi_Minh" /></td>
                    <td>${log.actionType}</td>
                    <td>${log.description}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty careLogs}">
                <tr><td colspan="3" class="text-center text-muted-note">No care logs.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="/footer.jsp" %>

