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
			<c:set var="method" value="POST" />
			<c:if test="${not empty board.boardId}">
			<c:set var="method" value="PUT" />
			</c:if>
			<form:form modelAttribute="board" cssClass="form-horizontal" action="/boards/${boardType}" method="${method}">
				<form:hidden path="boardId"/>
				<fieldset>
					<div class="control-group">
						제목 : <form:input path="title" cssClass="input-block-level" placeholder="제목" />
					</div>
					<div class="control-group">
						<form:textarea path="contents" cols="80" rows="15"/>
					</div>
					<div class="control-group">
						이름 : <form:input path="name" size="40" placeholder="이름" />
					</div>
					<div class="control-group">
						비밀번호 : <form:password path="password" size="40" placeholder="비밀번호" />
						<c:if test="${not empty errorMessage}">
						<label for="password" generated="true" class="error" style="">${errorMessage}</label>
						</c:if>
					</div>
					<div class="pull-right">
						<button id="confirmBtn" type="submit" class="btn btn-success">글쓰기</button>
					</div>
				</fieldset>				
			</form:form>
		</div>
		<div class="span1"></div>
	</div>
</div>

<script src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
<script type="text/javascript" src="${url:resource('/javascripts/jquery.markitup.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/image.upload.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/qna-set.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/form.js')}"></script>	
</body>
</html>