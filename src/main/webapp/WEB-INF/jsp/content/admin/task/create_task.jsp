<%-- CREATE TASK page --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="row-fluid">
    <h3>Edit Task</h3>
    <hr>
    <form class="form-horizontal" method="post" commandName="task" autocomplete="off" >
        <div class="control-group info">
            <label class="control-label" for="question">Question</label>
            <div class="controls">
                <input id="question" name="question" type="text" autofocus value="${task.question}"/>
                <spring:bind path="task.question">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="text-error"><c:out value="${error}"/></span>
                    </c:forEach>
                </spring:bind>
            </div>
        </div>
        <div class="control-group info">
            <label class="control-label" for="answer1">Answer1</label>
            <div class="controls">
                <input id="answer1" name="answer1" type="text" value="${task.answer1}"/>
                <spring:bind path="task.answer1">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="text-error"><c:out value="${error}"/></span>
                    </c:forEach>
                </spring:bind>
            </div>
        </div>
        <div class="control-group info">
            <label class="control-label" for="answer2">Answer2</label>
            <div class="controls">
               <input id="answer2" name="answer2" type="text" value="${task.answer2}"/>
                <spring:bind path="task.answer2">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="text-error"><c:out value="${error}"/></span>
                    </c:forEach>
                </spring:bind>
            </div>
        </div>
        <div class="control-group info">
            <label class="control-label" for="answer3">Answer3</label>
            <div class="controls">
                <input id="answer3" name="answer3" type="text" value="${task.answer3}"/>
                <spring:bind path="task.answer3">
                    <c:forEach items="${status.errorMessages}" var="error">
                        <span class="text-error"><c:out value="${error}"/></span>
                    </c:forEach>
                </spring:bind>
            </div>
        </div>
        <div class="control-group info">
            <label class="control-label" for="answer4">Answer4</label>
            <div class="controls">
                <input id="answer4" name="answer4" type="text" value="${task.answer4}"/>
                <form:errors path="answer4" class="text-error"/>
                <spring:bind path="task.answer4">
                    <c:forEach items="${status.errorMessages}" var="error">
                       <span class="text-error"><c:out value="${error}"/></span>
                    </c:forEach>
                </spring:bind>
            </div>
        </div>
        <div class="control-group info">
            <label class="control-label" for="correct">Correct</label>
            <div class="controls">
               <select name="correct">
                   <option value=""> - - select - - </option>
                   <option value="1" >1</option>
                   <option value="2" >2</option>
                   <option value="3" >3</option>
                   <option value="4" >4</option>
               <select>
               <spring:bind path="task.correct">
                   <c:forEach items="${status.errorMessages}" var="error">
                      <span class="text-error"><c:out value="${error}"/></span>
                   </c:forEach>
               </spring:bind>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input class='btn btn-primary' type='submit' name='save'
                      value="Save" style="width:150px"/>
                <input class="btn"  type='submit' name='cancel' value='Cancel' />
            </div>
        </div>
    </form>
</div>
