<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@include file="/WEB-INF/jsp/include/tags.jspf"
%><html>
<head>
</head>
<body>
   <iminbak:navbar main="true"/>

   <section id="feature_slider" class="">
        <article class="slide" id="tour" style="background: url('${url:resource('/images/backgrounds/main4.jpg')}') repeat-x top center;">
            <div class="info">
                <h2></h2>
            </div>
        </article>
        <article class="slide" id="showcasing" style="background: url('${url:resource('/images/backgrounds/main3.jpg')}') repeat-x top center;">
            <div class="info">
                <h2></h2>
            </div>
        </article>
    </section>
    
    <div id="in_pricing">
        <div class="container">
            <div class="start">
                <p>정이 담긴 집에서 추억을 만들어 보세요.</p>
                <a href="javascript:RealReservation();">실시간 예약하기</a>
            </div>
        </div>
    </div>

    <!-- starts footer -->
    <div>
        <div class="container">
            <div class="row sections">
                <div class="span6 contact">
                    <h3 class="footer_header">
                        연락처 및 계좌정보
                    </h3>
                    전화번호 : 010-4190-1571<br/>
                    계좌번호 : 단위농협&nbsp;&nbsp;351-0610-4319-03&nbsp;&nbsp;최연정
                </div>
                <div class="span6 recent_posts">
                    <h3 class="footer_header">
                        최근 블로그 글
                    </h3>
                    <c:forEach items="${blogs.content}" var="each" varStatus="status">
                    <div class="post">
                    	<a href="/blogs?page=${status.count}" class="title">${each.title}</a><br/>
                        ${sf:cut(each.contents, 100, '...')}&nbsp;&nbsp;<fmt:formatDate value="${each.createdDate}" pattern="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;from 정이담긴집
                    </div>
                    <br/>                 
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div id="showcase">
        <div class="container">
            <div class="section_header">
                <h3>정이 담긴 집, 객실 정보</h3>
            </div>            
            <div class="row feature_wrapper">
                <!-- Features Row -->
                <div class="features_op1_row">
                    <!-- Feature -->
                    <div class="span4 feature first">
                        <div class="img_box">
                            <a href="/rooms/greentea">
                                <img src="${url:resource('/images/room/room1/image1.jpg')}">
                                <span class="circle"> 
                                    <span class="plus">&#43;</span>
                                </span>
                            </a>
                        </div>
                        <div class="text">
                            <h6>그린티</h6>
                        </div>
                    </div>
                    <!-- Feature -->
                    <div class="span4 feature">
                        <div class="img_box">
                            <a href="/rooms/lavender">
                                <img src="${url:resource('/images/room/room2/image1.jpg')}">
                                <span class="circle"> 
                                    <span class="plus">&#43;</span>
                                </span>
                            </a>
                        </div>
                        <div class="text">
                            <h6>라벤더</h6>
                        </div>
                    </div>
                    <!-- Feature -->
                    <div class="span4 feature last">
                        <div class="img_box">
                            <a href="/rooms/rosemary">
                                <img src="${url:resource('/images/room/room3/image1.jpg')}">
                                <span class="circle"> 
                                    <span class="plus">&#43;</span>
                                </span>
                            </a>
                        </div>
                        <div class="text">
                            <h6>로즈마리</h6>
                        </div>
                    </div>
                </div>
                <div class="features_op1_row">
                    <!-- Feature -->
                    <div class="span4 feature first">
                        <div class="img_box">
                            <a href="/rooms/sweetgreen">
                                <img src="${url:resource('/images/room/room4/image1.jpg')}">
                                <span class="circle"> 
                                    <span class="plus">&#43;</span>
                                </span>
                            </a>
                        </div>
                        <div class="text">
                            <h6>스위트그린</h6>
                        </div>
                    </div>
                    <!-- Feature -->
                    <div class="span4 feature">
                        <div class="img_box">
                            <a href="/rooms/jasmine">
                                <img src="${url:resource('/images/room/room5/image1.jpg')}">
                                <span class="circle"> 
                                    <span class="plus">&#43;</span>
                                </span>
                            </a>
                        </div>
                        <div class="text">
                            <h6>자스민</h6>
                        </div>
                    </div>
                    <!-- Feature -->
                    <div class="span4 feature last">
                        <div class="img_box">
                            <a href="/rooms/out">
                                <img src="${url:resource('/images/room/room0/image2.jpg')}">
                                <span class="circle"> 
                                    <span class="plus">&#43;</span>
                                </span>
                            </a>
                        </div>
                        <div class="text">
                            <h6>야외</h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<%@include file="/WEB-INF/jsp/include/footer.jspf"%>
</body>
</html>