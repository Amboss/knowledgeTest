<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="modal" commandName="user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <form class="modal-body" method="post">
        <center>
            <h3>Please specify your name</h3>
            <hr>
            <spring:bind path="user.userName">
                <c:forEach items="${status.errorMessages}" var="error">
                    <span class="text-error"><c:out value="${error}"/></span>
                </c:forEach>
            </spring:bind></br></br>
            <input type="text" name="userName" placeholder="Username" autofocus>
            <div class="control-group">
                <div class="controls">
                    <input class='btn btn-primary'
                            type='submit'
                            name='save'
                            value='Save'
                            style="width:150px"/>
                    <input class="btn"
                            data-dismiss='modal'
                            type='submit'
                            aria-hidden='true'
                            name='cancel'
                            value='Cancel' />
                </div>
            </div>
        </center>
     </form>
</div>