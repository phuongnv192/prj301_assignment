<%-- 
    Document   : category
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Manage Categories" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">Manage Categories</h2>
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>
<c:if test="${param.added eq '1'}">
    <div class="alert alert-success">Category added successfully.</div>
</c:if>

<div class="panel-box">
    <h3>Add Category</h3>
    <form method="post" action="<c:url value='/admin/categories'/>">
        <input type="hidden" name="action" value="create">
        <div class="form-group">
            <label>Category name</label>
            <input type="text" name="categoryName" required>
        </div>
        <div class="action-row">
            <button type="submit" class="btn btn-primary btn-primary-custom">Add</button>
        </div>
    </form>
</div>

<h3>Category List</h3>
<div class="panel-box">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td class="cell-center">${category.categoryID}</td>
                    <td>${category.categoryName}</td>
                    <td class="cell-center">
                        <form method="post" action="<c:url value='/admin/categories'/>" style="display:inline;">
                            <input type="hidden" name="action" value="delete" />
                            <input type="hidden" name="categoryID" value="${category.categoryID}" />
                            <button type="submit" class="btn-link" onclick="return confirm('Delete this category?');">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty categories}">
                <tr><td colspan="3" class="text-center text-muted-note">No categories.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<%@ include file="/footer.jsp" %>

