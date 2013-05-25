<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"%>
<html>
<head>
<link href="${url:resource('/stylesheets/wiki-style.css')}" rel="stylesheet">
<link href="${url:resource('/stylesheets/wiki-textile-style.css')}" rel="stylesheet">
<link href="${url:resource('/stylesheets/wiki-imageupload-plugins.css')}" rel="stylesheet">
</head>
<body>

<div class="section-qna">
	<slipp:header type="1"/>
	<div class="row-fluid">
		<div class="span1"></div>
		<div class="span10 qna-form">
			<form:form modelAttribute="answer" cssClass="form-horizontal" action="/boards/${boardType}/${board.boardId}/answers/${answer.answerId}" method="DELETE">
				<form:hidden path="answerId"/>
				<fieldset>
					<div class="control-group">
						<form:password path="rawPassword" size="40" placeholder="비밀번호" />
						<c:if test="${not empty errorMessage}">
						<label for="password" generated="true" class="error" style="">${errorMessage}</label>
						</c:if>
					</div>
					<div class="pull-right">
						<button id="confirmBtn" type="submit" class="btn btn-success">삭제하기</button>
					</div>
				</fieldset>				
			</form:form>
		</div>
		<div class="span1"></div>
	</div>
</div>

<script src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
</body>
</html>