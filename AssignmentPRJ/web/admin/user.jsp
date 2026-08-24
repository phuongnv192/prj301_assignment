<%-- 
    Document   : user
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="pageTitle" value="Manage Users" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Manage Users</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>
<c:if test="${param.created eq '1'}">
    <div class="alert alert-success">Account created successfully.</div>
</c:if>

<div class="panel-box">
    <h3>Create User Account</h3>
    <form method="post" action="<c:url value='/admin/users'/>">
        <input type="hidden" name="action" value="create">
        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" required>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required>
        </div>
        <div class="form-group">
            <label>Full name</label>
            <input type="text" name="fullName" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email">
        </div>
        <div class="form-group">
            <label>Role</label>
            <select name="role" required>
                <option value="CUSTOMER">Customer</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        <div class="action-row">
            <button type="submit" class="btn btn-primary btn-primary-custom">Create</button>
        </div>
    </form>
</div>

<h3>User List</h3>
<div class="panel-box">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Full name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td class="cell-center">${u.userID}</td>
                    <td class="cell-left">${u.username}</td>
                    <td>${u.fullName}</td>
                    <td>${u.email}</td>
                    <td class="cell-center">
                        <c:choose>
                            <c:when test="${u.role eq 'CUSTOMER'}">Customer</c:when>
                            <c:otherwise>Admin</c:otherwise>
                        </c:choose>
                    </td>
                    <td class="cell-center">
                        <c:choose>
                            <c:when test="${u.status eq true}">
                                <span class="label-status label-resolved">Đang hoạt động</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label-status label-rejected">Ngừng hoạt động</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="cell-center">
                        <c:url var="toggleUrl" value="/admin/users">
                            <c:param name="action" value="toggle" />
                            <c:param name="userID" value="${u.userID}" />
                            <c:param name="status" value="${u.status ? 'false' : 'true'}" />
                        </c:url>
                        <a href="${toggleUrl}" onclick="return confirm('Change this account status?');">${u.status ? 'Lock' : 'Unlock'}</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty users}">
                <tr><td colspan="7" class="text-center text-muted-note">No users.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="/footer.jsp" %>

