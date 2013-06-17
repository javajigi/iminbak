<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Styles -->
    <link href="${url:resource('/stylesheets/bootstrap.css')}" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/theme.css')}">
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/lib/animate.css')}" media="screen, projection">
	<link rel="stylesheet" href="${url:resource('/stylesheets/blogpost.css')}" type="text/css" media="screen" />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
    <div id="blog_post">
        <div class="container">
        	<c:forEach items="${blogs.content}" var="each">
            <div class="row">
            	<div class="span2"></div>
                <div class="span8">
                    <div class="post_content">
                        <h2>${sf:h(each.title)}</h2>
                        <span class="date"><fmt:formatDate value="${each.createdDate}" pattern="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;from 정이담긴집</span>
                        ${sf:wiki(each.contents)}
                    </div>
                </div>
            	<div class="span2"></div>                
            </div>
            </c:forEach>
			<div class="pagination pagination-centered">
				<ul>
					<sl:pager page="${blogs}" prefixUri="/blogs"/>
				</ul>
			</div>
        </div>
	</div>
</body>
</html>