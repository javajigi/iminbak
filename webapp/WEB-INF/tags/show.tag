<%@ tag language="java" pageEncoding="UTF-8"%><%@ tag body-content="empty" %><%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@
taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@
taglib prefix="sf" uri="http://slipp.net/functions"%><%@
attribute name="board" required="true" rtexprvalue="true" type="net.slipp.domain.board.Board" description="질문"%>

			<div class="content">
				<article class="article">
					<div class="wrap">
						<div class="auth-info">
							<div class="author-text">
								${board.name}
								<span class="time">
									<fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd HH:mm" />
								</span>
							</div>
						</div>
						<div class="doc">
							<h1 class="subject">${sf:h(board.title)}</h1>
							<div class="text">${sf:wiki(board.contents)}</div>
							<div class="share">
							</div>
							<div class="util">
								<a id="updateQuestionBtn" href="/boards/${boardType}/${board.boardId}/form" class="btn btn-primary">수정</a>
								<a id="deleteQuestionBtn" href="/boards/${boardType}/${board.boardId}/deleteForm" class="btn btn-danger">삭제</a>
								<a href="/boards/${boardType}"><button class="btn">목록으로</button></a>
							</div>
						</div>
					</div>
				</article>
				<form id="deleteQuestionForm" action="/questions/${board.boardId}" method="POST" class="flyaway">
					<input type="hidden" name="_method" value="DELETE" />
				</form>
			</div>
