<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"%>
<html>
<head>
</head>
<body>

<div class="section-qna">
	<h1 class="hidden">QnA</h1>
	<slipp:header type="1"/>
	<div class="row-fluid">
		<div class="span1"></div>
		<div class="span10 qna-list">
			<h2 class="hidden">list</h2>
			<ul class="list">
			<c:forEach items="${boards.content}" var="each">
				<li>
					<div class="wrap">
						<div class="main">
							<strong class="subject">
								<a href="/boards/${boardType}/${each.boardId}">${sf:h(each.title)}</a>
							</strong>
						</div>
						<div class="sub">
							<div class="reply">
								<i class="symbol" title="댓글">R</i>
								<span class="point">${each.answerCount}</span>
							</div>
							<div class="auth-info">
								${each.name}
								<span class="time">
									<fmt:formatDate value="${each.createdDate}" pattern="yyyy-MM-dd HH:mm" />  
								</span>
							</div>
						</div>
					</div>					
				</li>
			</c:forEach>
			</ul>
			<div class="pagination pagination-centered">
				<ul>
					<sl:pager page="${boards}" prefixUri="/boards/${boardType}"/>
				</ul>
				<a id="questionBtn" href="/boards/${boardType}/form" class="btn btn-primary pull-right">글쓰기</a>
			</div>
		</div>
		<div class="span1"></div>
	</div>
</div>

</body>
</html>