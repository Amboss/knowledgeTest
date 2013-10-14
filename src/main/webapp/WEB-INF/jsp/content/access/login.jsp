<%-- LOGIN page --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript" language="javascript">
    function redirect() {
        window.location.href="/knowledgeTest/";
    }
</script>

<div class="modal span3" commandName="user" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel" aria-hidden="true" >
    <form class="modal-body" method="POST" action="<c:url value='/j_spring_security_check'/>" autocomplete="off" >
        <center>
            <h3>Please login</h3>
            <hr>
            <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                <div class="control-group text-error">
                    <span>Your login attempt was not successful, try again.</br>
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
                </div>
            </c:if>
            <div class="control-group">
                <div class="controls">
                    <input class="input-large" id="j_username" name="j_username" type="text"
                            placeholder="Username" autofocus/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input class="input-large" id="j_password" name="j_password"
                            type="password" placeholder="Password" />
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input class='btn btn-primary' type='submit' name='save'
                          value="Sign in" style="width:150px"/>
                    <input class="btn" data-dismiss='modal' type='button'
                          aria-hidden='true' value='Cancel' onclick="redirect();" />
                </div>
            </div>
        </center>
    </form>
</div>