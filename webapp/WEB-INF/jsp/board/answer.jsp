<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"%>
<html>
<head>
<title>${sf:h(question.title)}</title>
<link href="${url:resource('/stylesheets/wiki-style.css')}" rel="stylesheet">
<link href="${url:resource('/stylesheets/wiki-textile-style.css')}" rel="stylesheet">
<link href="${url:resource('/stylesheets/wiki-imageupload-plugins.css')}" rel="stylesheet">
<link href="${url:resource('/stylesheets/sh/shCoreDefault.css')}" rel="stylesheet">
<link href="${url:resource('/stylesheets/sh/shThemeEclipse.css')}" rel="stylesheet">
</head>
<body>

<div class="section-qna">
	<slipp:header type="1"/>
	<div class="row-fluid">
		<div class="span1"></div>
		<div class="span10 qna-view">
			<iminbak:show board="${board}"/>
			
			<div class="qna-comment">
				<form:form modelAttribute="answer" cssClass="form-horizontal" action="/boards/${boardType}/${board.boardId}/answers/${answer.answerId}" method="PUT">
					<fieldset>
						<div class="control-group">
							<form:textarea path="contents" cols="80" rows="5"/>
						</div>
						<div class="control-group">
							<form:input path="name" size="40" placeholder="이름" />
						</div>
						<div class="control-group">
							<form:password path="rawPassword" size="40" placeholder="비밀번호" />
							<c:if test="${not empty errorMessage}">
							<label for="password" generated="true" class="error" style="">${errorMessage}</label>
							</c:if>
						</div>
						<div class="pull-right">
							<button id="answerBtn" type="submit" class="btn btn-success">답변하기</button>
						</div>
					</fieldset>				
				</form:form>
			</div>
		</div>
		<div class="span1"></div>
	</div>
</div>
<script src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
<script type="text/javascript" src="${url:resource('/javascripts/jquery.markitup.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/image.upload.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/qna-set.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/show.js')}"></script>
</body>
</html>