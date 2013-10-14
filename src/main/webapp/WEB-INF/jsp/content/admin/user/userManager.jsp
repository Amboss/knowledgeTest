<%-- USER MANAGER page --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
    $(document).ready(function() {
        jQuery("table").dataTable( {
            "iDisplayLength": -1,
            "aaSorting": [[ 0, "asc" ]]
        });
    });
</script>

<div class="row-fluid">
    <h2>List of tested users</h1>
    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Rating Date</th>
                <th>Score</th>
            </tr>
        <thead>
        <c:if test="${!empty userList}">
            <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td><c:out value="${user.userId}"/></td>
                        <td><c:out value="${user.userName}"/></td>
                        <td><c:out value="${user.rating.ratingDate}"/></td>
                        <td><c:out value="${user.rating.score}"/></td>
                    </tr>
                </c:forEach>
            <tbody>
        </c:if>
    </table>
</div>