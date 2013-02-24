<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"
%><%@include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>,&middot;&acute;″```&deg;&sup3;о♡::정이담긴집에 오신 것을 환영합니다::♡о&sup3;&deg;```″&acute;&middot;, </title>
<link rel="shortcut icon" type="image/x-icon" href="${url:resource('/images/favicon.ico')}">
<link href="${url:resource('/stylesheets/bootstrap.css')}" rel="stylesheet">
<style>
  body { padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */ }
</style>
<link href="${url:resource('/stylesheets/bootstrap-responsive.css')}" rel="stylesheet">
<link href="${url:resource('/stylesheets/slipp.css')}" rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
<script src="${url:resource('/javascripts/bootstrap.min.js')}"></script>
<decorator:head />
</head>
<body>

  <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a class="brand" href="/"><abbr title="정이 담긴 집">정이 담긴 집</abbr></a>
        <div class="nav-collapse">
          <ul class="nav nav-pills pull-right">
            <li><a href="/rooms">객실안내</a></li>
            <li><a href="/questions">문의게시판</a></li>
            <li><a href="/reservations">예약안내</a></li>
            <li><a href="/travels">찾아오시는길</a></li>
            <sec:authorize access="!hasRole('ROLE_USER')">
            <li class="active loginBtn"><a href="/login">로그인</a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
            <li class="active logoutBtn"><a href="/logout">로그아웃</a></li>
            </sec:authorize>              
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <decorator:body/>
  </div>

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try{
var pageTracker = _gat._getTracker("UA-22853131-1");
pageTracker._trackPageview();
} catch(err) {}</script>
</body>
</html>