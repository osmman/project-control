<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="/assets/css/bootstrap.css" rel="stylesheet" media="screen"/>
		<link href="/assets/css/bootstrap-responsive.css" rel="stylesheet" media="screen"/>
		<style>
	      body {
	        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
	      }
	    </style>
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	   	<!--[if lt IE 9]>
	    	<script src="../assets/js/html5shiv.js"></script>
	   	<![endif]-->
	
	
		<title>ProjectControl - ${param.title}</title>
	</head>
	<body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="/">ProjectControl</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
            	<c:if test="${param.activePage == 1}">
            		<li class="active"><a href="/">Home</a></li> 
            	</c:if>
            	<c:if test="${param.activePage != 1}">
            		<li><a href="/">Home</a></li> 
            	</c:if>
            	
            	<c:if test="${param.activePage == 2}">
            		<li class="active"><a href="/users">Users</a></li> 
            	</c:if>
            	<c:if test="${param.activePage != 2}">
            		<li><a href="/users">Users</a></li> 
            	</c:if>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">

      <jsp:include page="${param.body}" />

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/assets/js/jquery.js"></script>
    <script src="/assets/js/bootstrap-transition.js"></script>
    <script src="/assets/js/bootstrap-alert.js"></script>
    <script src="/assets/js/bootstrap-modal.js"></script>
    <script src="/assets/js/bootstrap-dropdown.js"></script>
    <script src="/assets/js/bootstrap-scrollspy.js"></script>
    <script src="/assets/js/bootstrap-tab.js"></script>
    <script src="/assets/js/bootstrap-tooltip.js"></script>
    <script src="/assets/js/bootstrap-popover.js"></script>
    <script src="/assets/js/bootstrap-button.js"></script>
    <script src="/assets/js/bootstrap-collapse.js"></script>
    <script src="/assets/js/bootstrap-carousel.js"></script>
    <script src="/assets/js/bootstrap-typeahead.js"></script>

  </body>
</html>