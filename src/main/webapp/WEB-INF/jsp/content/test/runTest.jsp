<%-- RUN TEST page --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="row-fluid">
    <div class="span7">
        <h3>Note:</h3>
        <ul>
            <li>Click the 'Next' button given in the bottom of this page to Submit your answer.</li>
            <li> Test will be submitted automatically if the time expired.</li>
            <li>Don't refresh the page.</li>
        </ul>
        <input class="btn" id="startBtn" type='button' value='Start test' onclick="start();" />
    </div>
</div>
<hr>
<div class="row-fluid" id="runTestArea">
    <script src="<c:url value='/static/js/testUtil.js'/>" type="text/javascript" ></script>

    <script type="text/javascript">

        $(document).ready(function() {

            var id = ${user.userId};

            $('#taskForm').submit(function(event) {

                /* get inputs */
                var json = { 'taskNum': taskNum, 'userId': userId, 'question': question,
                    'answer1': answer1, 'answer2': answer2, 'answer3': answer3, 'answer4': answer4 };

                $.ajax({
                    url: "/test/runTest/" + id + "/" + answer,
                    data: JSON.stringify(json),
                    type: "POST",

                    beforeSend: function(xhr) {
                        xhr.setRequestHeader("Accept", "application/json");
                        xhr.setRequestHeader("Content-Type", "application/json");
                    },
                    success: function(runTest) {

                    }
                });
                event.preventDefault();
            });

            function buildForm(){
                $.each(data, function(i, item) {

                     var questionDiv = "<div class='span6 alert alert-block'></div>";
                     $(newDiv).appendTo('#runTestArea');

                     var questionSpan = "<span>" + question + "</span>";
                     $(questionSpan).appendTo(questionDiv);

                     var taskForm = "<form class='span6' id='taskForm' method='post'" +
                        + "name='radioButton' commandName='radioButton'></form>";
                     $(taskForm).appendTo('#runTestArea');

                     for (var i = 1; i <= 4; i++) {
                         var formAnswer = "<input type='radio' path='answer' value=" + [i] +
                            ">" + answerArray[i] + " </input>";
                         $(formAnswer).appendTo(taskForm);
                     });
                     var formBtn = "<input class='btn btn-primary' type='submit'" +
                        "name='next' value='Next' />";
                     $(formBtn).appendTo(taskForm);
                }
             }
        });
    </script>
</div>

