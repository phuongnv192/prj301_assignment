<%-- 
    Document   : list
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="My Plants" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">My Plants</h2>
<div class="panel-box">
    <p class="action-row"><a class="btn btn-primary btn-primary-custom" href="<c:url value='/plants/create'/>">+ Add new plant</a></p>

    <div class="plant-grid">
        <c:forEach var="plant" items="${plants}">
            <div class="plant-card" onclick="window.location.href = '<c:url value='/plants/detail?id=${plant.plantID}'/>'">
                <div class="plant-card-image">
                    <img src="${not empty plant.imageUrl ? plant.imageUrl : 'https://placehold.co/600x400/EEF4FF/1F3C88?text=Plant'}" alt="${plant.plantName}" />
                    <div class="plant-card-hover">
                        <a class="btn btn-default btn-secondary-custom" href="<c:url value='/plants/detail?id=${plant.plantID}'/>" onclick="event.stopPropagation();">Detail</a>
                        <a class="btn btn-default btn-secondary-custom" href="<c:url value='/plants/edit?id=${plant.plantID}'/>" onclick="event.stopPropagation();">Edit</a>
                        <a class="btn btn-danger btn-secondary-custom" href="<c:url value='/plants/delete?id=${plant.plantID}'/>" onclick="event.stopPropagation(); return confirm('Delete this plant?');">Delete</a>
                    </div>
                </div>
                <div class="plant-card-body">
                    <h4>${plant.plantName}</h4>
                    <p>${plant.category.categoryName}</p>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty plants}">
        <p class="text-center text-muted-note">No plants found.</p>
    </c:if>
</div>

<%@ include file="/footer.jsp" %>

