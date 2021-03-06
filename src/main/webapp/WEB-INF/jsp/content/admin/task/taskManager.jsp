<%-- TASK MANAGER page --%>

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
    <h2>List of all Tasks</h1>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>TaskId</th>
                <th>Question</th>
                <th>Answer1</th>
                <th>Answer2</th>
                <th>Answer3</th>
                <th>Answer4</th>
                <th>Correct</th>
                <th>Options</th>
            </tr>
        <thead>
        <c:if test="${!empty taskList}">
            <tbody>
                <c:forEach items="${taskList}" var="task">
                    <tr>
                        <td><c:out value="${task.taskId}"/></td>
                        <td><c:out value="${task.question}"/></td>
                        <td><c:out value="${task.answer1}"/></td>
                        <td><c:out value="${task.answer2}"/></td>
                        <td><c:out value="${task.answer3}"/></td>
                        <td><c:out value="${task.answer4}"/></td>
                        <td><c:out value="${task.correct}"/></td>
                        <td><a href="<c:url value="/admin/edit_task/${task.taskId}" />" >
                                <i class="icon-pencil" ></i>&nbsp;Edit</a></td>
                    </tr>
                </c:forEach>
            <tbody>
        </c:if>
    </table>
</div>
