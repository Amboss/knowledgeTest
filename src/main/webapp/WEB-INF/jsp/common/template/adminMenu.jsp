<%-- ADMIN menu --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="navbar">
    <div class="navbar-inner">
        <ul class="nav">
            <li><a class="brand" href="/knowledgeTest/">KnowledgeTest</a></li>
            <li class="divider-vertical"></li>
            <li><a href="/knowledgeTest/admin/userManager">Users</a></li>
            <li><a href="/knowledgeTest/admin/taskManager">Tasks</a></li>
            <li><a href="/knowledgeTest/admin/create_task">CreateTask</a></li>
        </ul>
        <ul class="nav pull-right">
            <li><a class="brand" href="<c:url value="/j_spring_security_logout" />" >Sign out</a></li>
        </ul>
    </div>
</div>

