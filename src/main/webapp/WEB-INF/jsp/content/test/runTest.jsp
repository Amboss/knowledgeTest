<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script>

<script>
<div class="">
    <div class="">
        <h3>Note:</h3>
        <ul>
            <li>Click the 'Next' button given in the bottom of this page to Submit your answer.</li>
            <li> Test will be submitted automatically if the time expired.</li>
            <li>Don't refresh the page.</li>
        </ul>
    </div>
    <div class="" >
        <form method="post" commandName="task">
            <form:radiobutton path="answer1" value="answer1" label="answer1" />
            <form:radiobutton path="answer2" value="answer2" label="answer2" />
            <form:radiobutton path="answer3" value="answer3" label="answer3" />
            <form:radiobutton path="answer4" value="answer4" label="answer4" />

            <input class="btn"
                type='submit'
                name='next'
                value='Next' />
        </form>
    </div>
</div>