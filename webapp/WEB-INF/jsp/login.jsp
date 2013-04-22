<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%>
<html>
<head>
<title>로그인 :: SLiPP</title>
<link href="${url:resource('/stylesheets/main.css')}" rel="stylesheet">
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<div class="row-fluid">
				<div class="span4">
				</div>
				<div class="span4">
					<h2>iminbak</h2>
					<form action="/signin/facebook" method="POST">
						<input class="btn btn-primary btn-large" type="submit" value="페이스북 계정으로 로그인" />
					</form>
				</div>
				<div class="span4">
				</div>		
			</div>			
		</div>
	</div>
</body>
</html>