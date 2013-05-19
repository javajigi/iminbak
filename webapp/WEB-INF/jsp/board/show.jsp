<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"%>
<html>
<head>
<title>${sf:h(board.title)}</title>
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
		<div class="span12 qna-view">
			<iminbak:show board="${board}"/>
		
			<div class="qna-comment">
				<p class="count"><strong>${board.answerCount}</strong>개의 답변</p>
				<ul class="list">
					<c:forEach items="${board.answers}" var="each">
						<slipp:answer each="${each}" isBest="false"/>
					</c:forEach>
				</ul>
				<form id="deleteAnswerForm" action="/boards/${boardType}/${board.boardId}/answers/" method="POST" class="flyaway">
					<input type="hidden" name="_method" value="DELETE" />
				</form>
				<form id="likeAnswerForm" action="/boards/${boardType}/${board.boardId}/answers" method="POST" class="flyaway">
				</form>
				<sec:authorize access="hasRole('ROLE_USER')">
					<form:form modelAttribute="answer" action="/boards/${boardType}/${board.boardId}/answers" method="POST" cssClass="form-horizontal">
						<fieldset>
							<form:textarea path="contents"  cols="80" rows="5"/>
							<div class="pull-right">
								<button id="answerBtn" type="submit" class="btn btn-success">답변하기</button>
							</div>
						</fieldset>
					</form:form>
				</sec:authorize>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${url:resource('/javascripts/jquery.markitup.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/qna-set.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/image.upload.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/tagparser.js')}"></script>
<script type="text/javascript" src="${url:resource('/javascripts/qna/show.js')}"></script>
</body>
</html>