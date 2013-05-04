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
		<div class="span12 qna-view">
			<div class="content">
				<article class="article">
					<div class="wrap">
						<div class="auth-info">
							<div class="author-thumb">
								<img src='${sf:stripHttp(blog.writer.imageUrl)}' class="user-thumb" alt="" />
							</div>
							<div class="author-text">
								<a href="${sf:stripHttp(blog.writer.profileUrl)}" class="author-name">${blog.writer.userId}</a>
								<span class="time">
									<fmt:formatDate value="${blog.createdDate}" pattern="yyyy-MM-dd HH:mm" />
								</span>
							</div>
						</div>
						<div class="doc">
							<h1 class="subject">${sf:h(blog.title)}</h1>
							<div class="text">${sf:wiki(blog.contents)}</div>
							<div class="share">
								<div class="facebook sns">
									<div id="fb-root"></div>
									<script src="https://connect.facebook.net/en_US/all.js#xfbml=1"></script>
									<fb:like href="${slippUrl}/questions/${question.questionId}"
									git	send="true" layout="button_count" width="100" show_faces="true"
										font=""></fb:like>
								</div>
								<div class="googleplus sns">
									<g:plusone></g:plusone>
								</div>
								<div class="twitter sns">
									<a href="https://twitter.com/share" class="twitter-share-button"
										data-count="horizontal">Tweet</a>
									<script type="text/javascript"
										src="https://platform.twitter.com/widgets.js"></script>
								</div>
							</div>
							<div class="util">
								<a id="updateBlogBtn" href="/blogs/${blog.blogId}/form" class="btn btn-primary">수정</a>
								<a href="/blogs"><button class="btn">목록으로</button></a>
							</div>
						</div>
					</div>
				</article>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${url:resource('/javascripts/qna/show.js')}"></script>
</body>
</html>