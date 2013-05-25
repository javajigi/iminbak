<%@ tag language="java" pageEncoding="UTF-8"%><%@ tag body-content="empty" %><%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@
taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@
taglib prefix="sec" uri="http://www.springframework.org/security/tags"%><%@
taglib prefix="sf" uri="http://slipp.net/functions"%><%@
taglib prefix="sl" uri="http://www.slipp.net/tags"%><%@
attribute name="each" required="true" rtexprvalue="true" type="net.slipp.domain.board.Answer" description="답변"%>

<li id="answer-${each.answerId}">
	<a href="#answer-${each.answerId}" class="permalink">#answer-${each.answerId}</a>
	<div class="auth-info">
		<div class="author-text">
			${each.name}
			<span class="time">
				<fmt:formatDate value="${each.createdDate}" pattern="yyyy-MM-dd HH:mm" />
			</span>
		</div>
	</div>
	<div class="doc">
		<div class="text">${sf:wiki(each.contents)}</div>
		<div>
			<a class="updateAnswerBtn btn btn-primary" href="/boards/${boardType}/${board.boardId}/answers/${each.answerId}/form">수정</a>
			<a class="deleteAnswerBtn btn btn-danger" href="/boards/${boardType}/${board.boardId}/answers/${each.answerId}/deleteForm">삭제</a>
		</div>	
	</div>
</li>


