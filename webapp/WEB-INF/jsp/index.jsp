<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@include file="/WEB-INF/jsp/include/tags.jspf"
%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
    <!-- Styles -->
    <link href="${url:resource('/stylesheets/bootstrap.css')}" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/theme.css')}">

    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/lib/animate.css')}" media="screen, projection">    

	<link rel="stylesheet" href="${url:resource('/stylesheets/index.css')}" type="text/css" media="screen" />
	
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
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
                        Contact
                    </h3>
                    전화번호 : 017-515-1571<br/>
                    <!-- 계좌번호 : 농협&nbsp;&nbsp;123-3455-789 &nbsp;&nbsp;박재성 -->
                </div>
                <div class="span6 recent_posts">
                    <h3 class="footer_header">
                        최근 블로그 글
                    </h3>
                   	<!-- 
                    <div class="post">
                        <a href="blogpost.html">
                            <img src="${url:resource('/images/recent_post1.png')}" class="img-circle" />
                        </a>
                        <div class="date">
                            Wed, 12 Dec
                        </div>
                        <a href="blogpost.html" class="title">
                            Randomised words which don't look embarrasing hidden.
                        </a>
                    </div>
                    <div class="post">
                        <a href="blogpost.html">
                            <img src="${url:resource('/images/recent_post2.png')}" class="img-circle" />
                        </a>
                        <div class="date">
                            Mon, 12 Dec
                        </div>
                        <a href="blogpost.html" class="title">
                            Randomised words which don't look embarrasing hidden.
                        </a>
                    </div>
                    -->
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