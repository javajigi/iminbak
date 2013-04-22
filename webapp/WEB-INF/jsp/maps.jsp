<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@include file="/WEB-INF/jsp/include/tags.jspf"
%><html>
<head>
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
</head>
<body>
	<iminbak:navbar main="false" />

	<div id="in_pricing">
		<div class="container">
			<div class="row charts_wrapp">
				<div class="span2">
				</div>
				<div class="span8">
					<img src="${url:resource('/images/maps/sub4_01.gif')}" /><br/>
					<img src="${url:resource('/images/maps/sub4_02.gif')}" /><br/>
					<img src="${url:resource('/images/maps/sub4_03.gif')}" /><br/>
					<img src="${url:resource('/images/maps/sub4_08.gif')}" /><br/>
					
				</div>
				<div class="span2">
				</div>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/jsp/include/footer.jspf"%>
</body>
</html>