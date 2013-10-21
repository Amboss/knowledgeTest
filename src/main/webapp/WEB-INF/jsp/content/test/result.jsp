<%-- RESULT page --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="row-fluid">
    <div class="span6">
        <h3>Result and statistics</h3>
        <p>User name: <c:out value="${user.userName}"/></p>
        <p>Total number of questions: 5</p>
        <p>Number of answered questions: <c:out value="${rating.score}"/></p>
        <p>Number of unanswered questions: 	 <c:out value=" ${5 - rating.score}"/></p>
    </div>
    </hr>
</div>