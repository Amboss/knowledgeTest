<%-- ================================================================================
                                Default layout
=================================================================================--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
    <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
    <!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <tiles:insertAttribute name="meta" />
        <title><tiles:insertAttribute name="title" flush="true"/></title>
    </head>
    <body>
        <div class="container">
            <tiles:insertAttribute name="header" />
        </div>
        <div class="container">
            <tiles:insertAttribute name="body" />
        </div>
        <div class="container">
            <tiles:insertAttribute name="footer" />
        </div>
    </body>
</html>
