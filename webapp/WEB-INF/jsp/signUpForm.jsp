<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"%>
<html>
<head>
<title>로그인 :: SLiPP</title>
<link href="${url:resource('/stylesheets/main.css')}" rel="stylesheet">
</head>
<body>
<div class="jumbotron">
	<div class="container">
		<div class="span6 offset2">
			<form:form action="" method="post" modelAttribute="signUpForm" cssClass="form-horizontal">
				<fieldset>
					<div class="control-group">
						<div class="controls">
							정이 담긴 집에서 활동할 계정 : <form:input path="username" cssClass="input-xlarge focused span2"/>
							<form:errors path="*" />
							<input class="btn btn-success" type="submit" value="로그인" />
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>		
	</div>
</div>
<script src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
<script type="text/javascript"	src="${url:resource('/javascripts/user/form.js')}"></script>	
</body>
</html>