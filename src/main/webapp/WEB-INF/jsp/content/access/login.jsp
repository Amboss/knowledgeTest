<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="modal span3" commandName="user" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel" aria-hidden="true" >
    <form class="modal-body" method="POST" action="<c:url value='/j_spring_security_check'/>" autocomplete="off" >
        <center>
            <h3>Please login</h3>
            <hr>
            <div class="control-group">
                <div class="controls">
                    <input class="input-large" id="j_username" name="j_username" type="text"
                            placeholder="Username" autofocus/>
                    <form:errors cssClass="errors"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input class="input-large" id="j_password" name="j_password"
                            type="password" placeholder="Password" />
                    <form:errors cssClass="errors"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input class='btn btn-primary' type='submit' name='save'
                          value="Sign in" style="width:150px"/>
                    <input class="btn" data-dismiss='modal' type='submit'
                          aria-hidden='true' name='cancel' value='Cancel' />
                </div>
            </div>
        </center>
    </form>
</div>