<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Styles -->
    <link href="${url:resource('/stylesheets/bootstrap.css')}" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/theme.css')}">
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/lib/animate.css')}" media="screen, projection">
	<link rel="stylesheet" href="${url:resource('/stylesheets/portfolio-item.css')}" type="text/css" media="screen" />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
	<iminbak:navbar main="false"/>
    
    <div id="portfolio_tem">
        <div class="container">
            <div class="section_header">
                <h3>그린티</h3>
            </div>

            <div class="span7 left_box">
                <div class="big">
                	<c:forEach var="each" items="${images}">
                	<img src="${each}"/>
                	</c:forEach>
                </div>
                <div class="thumbs">
                	<c:forEach var="each" items="${thumbnailImages}">
                    <div class="thumb">
                        <img src="${each}">
                        <a href="#" class="mask">
                            <div class="more">+</div>
                        </a>
                    </div>                	
                	</c:forEach>
                </div>
            </div>

            <div class="span5 right_box">
                <h2>Awesome portfolio item</h2>
                <p>
                    There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected ones humour, or these random words which don’t look even slightly for believable.</p>

                <p>
                    There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected ones humour, or these random words which don’t look even slightly for believable. If you are going to use a passage of Lorem Ipsum, you needs to been sure there isn’t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined. There majority have suffered. If you are going to use a passage of Lorem Ipsum, you needs to been sure there isn’t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined.</p>
                <p>
                    There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected ones humour, or these random words which don’t look even slightly for believable.</p>
            </div>
        </div>
    </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="${url:resource('/javascripts/bootstrap.min.js')}"></script>
    <script src="${url:resource('/javascripts/theme.js')}"></script>
</body>
</html>