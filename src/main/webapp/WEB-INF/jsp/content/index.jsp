<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="hero-unit">
    <h1><tiles:insertAttribute name="title" flush="true"/></h1>
</div>
<form class="control-group" method="post" autocomplete="off">
    <center>
        <h3>press to start test</h3>
        <input class="btn btn-large btn-primary" style="width:300px" type='submit' name='start' value='start'>
    </center>
</form>

