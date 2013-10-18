<%-- RUN TEST page --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="row-fluid" >
    <div class="span7">

        <h3>Note:</h3>
        <ul>
            <li>Click the 'Next' button given in the bottom of this page to Submit your answer.</li>
            <li> Test will be submitted automatically if the time expired.</li>
            <li>Don't refresh the page.</li>
        </ul>
        <input class="btn" id="tmp" type='button' value='Start test' />
    </div>
</div>
<hr>
<div class="row-fluid" id="runTestArea">
    <script type="text/javascript">
        $(document).ready(function() {

            var id = ${user.userId};
            var task = 0;
            var answer = 0;

            $('.btn').click(function(event) {

                answer = $('input[name=answer]:checked', '#taskForm').val()

                /* fire off the request to OrderController */
                var request = $.ajax({
                    url: id + "/" + task + "/" + answer,
                    dataType : "json",
                    contentType : 'application/json',
                    type:'GET',
                    $.getJSON('${pageContext.request.contextPath}/' + id + "/" + task + "/" + answer, function(person) {

                            id = item.userId;
                            task = item.taskNum;
                            var answerArray = new Array(item.answer1, item.answer2, item.answer3, item.answer4);

                            var questionDiv = "<div class='span6 alert alert-block'></div>";
                            $(questionDiv).appendTo('#runTestArea');

                            var questionSpan = "<span>" + jsonTaskModel.question + "</span>";
                            $(questionSpan).appendTo(questionDiv);

                            var taskForm = "<form class='span6' id='taskForm' method='post' name='radioButton' commandName='radioButton'></form>";
                            $(taskForm).appendTo('#runTestArea');

                            for (var i = 1; i <= answerArray.length; i++) {
                                var formAnswer = "<input type='radio' path='answer' value=" + [i] + ">" + answerArray[i] + " </input>";
                                $(formAnswer).appendTo(taskForm);
                            }
                            var formBtn = "<input class='btn btn-primary' type='submit' name='next' value='Next' />";
                            $(formBtn).appendTo(taskForm);
                        });
                    }
                });
            });
        });
    </script>
</div>

