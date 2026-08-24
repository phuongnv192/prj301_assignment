<%-- 
    Document   : header
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="<c:url value='/styles/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/styles/custom.css'/>">

<div class="container page-shell">
    <nav class="navbar navbar-default navbar-custom" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand"/>PRJ301</a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <li><a>Welcome <b>${sessionScope.account.fullName}</b></a></li>
                            <c:if test="${sessionScope.account.role eq 'ADMIN'}">
                            <li><a href="<c:url value='/admin/home'/>">Admin Home</a></li>
                            <li><a href="<c:url value='/admin/users'/>">Manage Users</a></li>
                            <li><a href="<c:url value='/admin/categories'/>">Manage Categories</a></li>
                            <li><a href="<c:url value='/admin/reports'/>">Manage Reports</a></li>
                            </c:if>
                            <c:if test="${sessionScope.account.role eq 'CUSTOMER'}">
                            <li><a href="<c:url value='/customer/home'/>">Home</a></li>
                            <li><a href="<c:url value='/plants'/>">My Plants</a></li>
                            <li><a href="<c:url value='/reports/my'/>">My Reports</a></li>
                            </c:if>
                        <li><a href="<c:url value='/logout'/>">Logout</a></li>
                        </c:when>
                        <c:otherwise>
                        <li><a href="<c:url value='/login'/>">Login</a></li>
                        <li><a href="<c:url value='/register'/>">Register</a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </div>
    </nav>

