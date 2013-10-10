<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function(){
        /* creating array of right answers  */
        var tasks = new Array();



        /* retrieving value of radioButton */
        $("input:radio[name=theme]").click(function() {
            var value = $(this).val();
        });

        /*  */
    });
</script>
<div class="row-fluid">
    <div class="span6">
        <h3>Note:</h3>
        <ul>
            <li>Click the 'Next' button given in the bottom of this page to Submit your answer.</li>
            <li> Test will be submitted automatically if the time expired.</li>
            <li>Don't refresh the page.</li>
        </ul>
    </div>
</div>
</hr>
<div class="row-fluid">
    <div class="span6 alert alert-block">
        <span>Test will be submitted automatically if the time expired. ${task.question}</span>
    </div>

    <form class="span6" method="post" name="radioButton" commandName="radioButton">
        <p><form:radiobutton path="radioButton.btn" value="1" label="value=1" /></p>
        <p><form:radiobutton path="radioButton.btn" value="2" label="value=2" /></p>
        <p><form:radiobutton path="radioButton.btn" value="3" label="value=3" /></p>
        <p><form:radiobutton path="radioButton.btn" value="4" label="value=4" /></p>
        <input class"btn btn-primary" type='submit' name='next' value='Next' />
    </form>
</div>


<div class="row-fluid">
    <table cellpadding="0"
          cellspacing="0"
          border="0"
          class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>question</th>
                <th>answer1</th>
                <th>answer2</th>
                <th>answer3</th>
                <th>answer4</th>
                <th>correct</th>
            </tr>
        <thead>
        <c:if test="${!empty tasks}">
            <tbody>
                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td><c:out value="${task.question}"/></td>
                        <td><c:out value="${task.answer1}"/></td>
                        <td><c:out value="${task.answer2}"/></td>
                        <td><c:out value="${task.answer3}"/></td>
                        <td><c:out value="${task.answer4}"/></td>
                        <td><c:out value="${task.correct}"/></td>
                    </tr>
                </c:forEach>
            <tbody>
        </c:if>
    </table>
</div>
