<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"
%><%@include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html lang="ko">
<head>
  	<title>,&middot;&acute;″```&deg;&sup3;о♡::정이담긴집에 오신 것을 환영합니다::♡о&sup3;&deg;```″&acute;&middot;, </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <decorator:head />
</head>

<body class="pull_top">
    <decorator:body/>

    <!-- starts footer -->
    <footer id="footer">
        <div class="container">
            <div class="row credits">
                <div class="span12">
                    <div class="row social">
                        <div class="span12">
                            <a href="#" class="facebook">
                                <span class="socialicons ico1"></span>
                                <span class="socialicons_h ico1h"></span>
                            </a>
                            <a href="#" class="twitter">
                                <span class="socialicons ico2"></span>
                                <span class="socialicons_h ico2h"></span>
                            </a>
                            <a href="#" class="gplus">
                                <span class="socialicons ico3"></span>
                                <span class="socialicons_h ico3h"></span>
                            </a>
                            <a href="#" class="flickr">
                                <span class="socialicons ico4"></span>
                                <span class="socialicons_h ico4h"></span>
                            </a>
                            <a href="#" class="pinterest">
                                <span class="socialicons ico5"></span>
                                <span class="socialicons_h ico5h"></span>
                            </a>
                            <a href="#" class="dribble">
                                <span class="socialicons ico6"></span>
                                <span class="socialicons_h ico6h"></span>
                            </a>
                            <a href="#" class="behance">
                                <span class="socialicons ico7"></span>
                                <span class="socialicons_h ico7h"></span>
                            </a>
                        </div>
                    </div>
                    <div class="row copyright">
                        <div class="span12">
                            주소 : 강원도 양양군 현북면 원일전리 3반 정이 담긴 집
                        </div>
                    </div>
                </div>            
            </div>
        </div>
    </footer>
   
    <!-- Scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="${url:resource('/javascripts/bootstrap.min.js')}"></script>
    <script src="${url:resource('/javascripts/theme.js')}"></script>

    <script type="text/javascript" src="${url:resource('/javascripts/index-slider.js')}"></script>
	<script language="javascript"> 
	<!-- 
	function RealReservation() { 
	  var url="http://real.pentour.com/index.php?pid=2542"; 
	  open(url,'펜션예약하기','width=620,height=600,menubar=no,resizable=no,location=no,status=no,scrollbars=yes,toolbar=no'); 
	} 
	// --> 
	</script> 
</body>
</html>