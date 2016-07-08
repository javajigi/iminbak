<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"
%><%@include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html lang="ko">
<head>
  	<title>,&middot;&acute;″```&deg;&sup3;о♡::정이담긴집에 오신 것을 환영합니다::♡о&sup3;&deg;```″&acute;&middot;, </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
    <!-- Styles -->
    <link href="${url:resource('/stylesheets/bootstrap.css')}" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/theme.css')}">

    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/lib/animate.css')}" media="screen, projection">    

	<link rel="stylesheet" href="${url:resource('/stylesheets/index.css')}" type="text/css" media="screen" />
	
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
    <decorator:head />
</head>

<body class="pull_top">
    <decorator:body/>

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script src="${url:resource('/javascripts/bootstrap.min.js')}"></script>
    <script src="${url:resource('/javascripts/theme.js')}"></script>

    <script type="text/javascript" src="${url:resource('/javascripts/index-slider.js')}"></script>
	<script language="javascript"> 
	<!-- 
	function RealReservation() { 
	  var url="http://real.pentour.com/index.php?pid=3171"; 
	  open(url,'펜션예약하기','width=620,height=600,menubar=no,resizable=no,location=no,status=no,scrollbars=yes,toolbar=no'); 
	} 
	// --> 
	</script>
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-40303281-1', 'iminbak.pe.kr');
	  ga('send', 'pageview');
	
	</script> 
</body>
</html>
