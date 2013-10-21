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
            <li>Test will be submitted automatically if 3 minutes will expire.</li>
            <li>Don't refresh the page.</li>
        </ul>
        <input class="btn btn-primary" id="tmp" type='button' value='Start test' />
    </div>
</div>
<hr>
<div class="row-fluid" id="runTestArea">
    <script type="text/javascript">

        var id = ${user.userId};
        var taskNum = null;
        var answerNum = null;
        var task = null;

        /*
         * main method
         */
        $(document).ready(function() {
            $('.btn').click(function(event) {
                 /* Remove any divs added by the last request */
                 $('#tmp').remove();

                 /* fire off the request to OrderController */
                 sendAjax();
            });
        });

        /*
         * sending POST request with user info
         */
        function sendAjax() {

            answerNum = $('input[name="answer"]:checked', '#taskForm').val();
            task = { "userId": id, "taskNum": taskNum, "answerNum": answerNum };

            $.ajax({
                "type": "POST",
                "contentType": "application/json; charset=utf-8",
                "url": "taskModel",
                "data": JSON.stringify(task),
                "dataType": "json",
                "success":
                    function(data) {
                        if (data.redirectURL != null) {
                            window.location.href = "/knowledgeTest" + data.redirectURL;
                        }
                        else {
                            taskNum = data.taskNum;

                            /* Remove any divs added by the last request */
                            $('#runTestArea').empty();
                            answerArray = null;
                            var answerArray = new Array(data.answer1, data.answer2, data.answer3, data.answer4);

                            var questionDiv = $('<div>').attr({
                                                    'class' : 'span5 alert alert-block',
                                                    'style' : 'min-height:150px' });
                            $(questionDiv).appendTo('#runTestArea');

                            var questionSpan = $('<span>').text(data.question);
                            $(questionSpan).appendTo(questionDiv);

                            var formDiv = $('<div>').attr({'class' : 'span5 form-horizontal'});
                                $(formDiv).appendTo('#runTestArea');

                            var form = $('<form>').attr({
                                                'class': 'control-group',
                                                'id' : 'taskForm',
                                                'method' : 'post',
                                                'name' : 'radioButton',
                                                'commandName' : 'radioButton' });
                            $(form).appendTo(formDiv);

                            for (var i = 1; i <= answerArray.length; i++) {
                                $('form.control-group')
                                    .append("<label class='radio'><input type='radio' path='answer' name='answer'" +
                                        "value=" + [i] + " />" + answerArray[i - 1] + "</label>");
                            }

                            var formBtn = $('<input>').attr({
                                                'class':'btn btn-primary',
                                                'type':'button',
                                                'value':'Next',
                                                'style' : 'width:150px',
                                                'onsubmit':'sendAjax()'});
                            $(formBtn).appendTo(formDiv);

                            setInterval(sendAjax, 180000);

                            $('.btn').click(function(event) {
                                 /* fire off the request to OrderController */
                                 sendAjax();
                            });
                        }
                    },
                "error":
                    function(data, status, er) {
                        alert("error: " + data + " status: " + status + " er:" + er);
                    }
            });
        }
    </script>
</div>

