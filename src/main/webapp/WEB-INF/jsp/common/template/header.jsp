<%-- HEADER --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<security:authorize access="isAuthenticated()">
     <div class="navbar">
         <div class="navbar-inner">
             <ul class="nav">
                 <li><a href="/knowledgeTest/admin/userManager">Users</a></li>
                 <li><a href="/knowledgeTest/admin/taskManager">Tasks</a></li>
                 <li><a href="/knowledgeTest/admin/create_task">CreateTask</a></li>
             </ul>
             <ul class="nav pull-right">
                 <li><a class="brand" href="<c:url value="/j_spring_security_logout" />" >Sign out</a></li>
             </ul>
         </div>
     </div>
</security:authorize>
<security:authorize access="isAnonymous()">
    <div class="navbar-form pull-left">
        <a href="/knowledgeTest/access/login"><i class="icon-wrench"></i>&nbsp;Login</a>
    </div>
    <hr>
</security:authorize>



