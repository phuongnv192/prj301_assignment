<%-- 
    Document   : form
    Created on : Aug 24, 2026, 1:16:21 AM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Plant Form" />
<%@ include file="/header.jsp" %>

<h2 class="page-title">
    <c:choose>
        <c:when test="${mode eq 'edit'}">Edit Plant</c:when>
        <c:otherwise>Create Plant</c:otherwise>
    </c:choose>
</h2>

<div class="panel-box">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <c:choose>
        <c:when test="${mode eq 'edit'}">
            <c:url var="formAction" value="/plants/edit"/>
        </c:when>
        <c:otherwise>
            <c:url var="formAction" value="/plants/create"/>
        </c:otherwise>
    </c:choose>
    <form method="post" action="${formAction}" id="plantForm">
        <c:if test="${mode eq 'edit'}">
            <input type="hidden" name="plantID" value="${plant.plantID}">
        </c:if>

        <div class="plant-form-layout">
            <div class="plant-form-main">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="plantName" value="${plant.plantName}" required>
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <select name="categoryID">
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryID}" ${plant.category.categoryID == category.categoryID ? 'selected' : ''}>
                                ${category.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Image URL</label>
                    <input type="text" name="imageUrl" id="imageUrlInput" value="${plant.imageUrl}" placeholder="https://...">
                </div>
                <div class="form-group">
                    <label>Health Status</label>
                    <input type="text" name="healthStatus" value="${plant.healthStatus}">
                </div>
                <div class="form-group">
                    <label>Note</label>
                    <textarea name="note" rows="4">${plant.note}</textarea>
                </div>
                <div class="action-row">
                    <button type="submit" class="btn btn-primary btn-primary-custom">Save</button>
                    <a class="btn btn-default btn-secondary-custom" href="<c:url value='/plants'/>">Back</a>
                </div>
            </div>

            <div class="plant-form-preview">
                <div class="image-preview-box">
                    <div class="image-preview-title">Preview</div>
                    <img id="plantImagePreview" src="${plant.imageUrl}" alt="Plant preview" onerror="this.style.display='none'; this.parentNode.classList.add('preview-empty');" />
                    <div id="imagePreviewPlaceholder" class="image-preview-placeholder" ${empty plant.imageUrl ? '' : 'style="display:none;"'}>
                        Image will appear here
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    (function () {
        var input = document.getElementById('imageUrlInput');
        var preview = document.getElementById('plantImagePreview');
        var placeholder = document.getElementById('imagePreviewPlaceholder');

        function updatePreview() {
            var url = input.value && input.value.trim();
            if (!url) {
                preview.src = '';
                preview.style.display = 'none';
                placeholder.style.display = 'flex';
                preview.parentNode.classList.add('preview-empty');
                return;
            }

            preview.parentNode.classList.remove('preview-empty');
            preview.src = url;
            preview.style.display = 'block';
            placeholder.style.display = 'none';
        }

        if (input) {
            input.addEventListener('input', updatePreview);
            input.addEventListener('paste', function () {
                setTimeout(updatePreview, 50);
            });
            updatePreview();
        }
    })();
</script>

<%@ include file="/footer.jsp" %>

