<%@ tag language="java" pageEncoding="UTF-8"%><%@ tag body-content="empty" %><%@
attribute name="main" required="true" rtexprvalue="false" type="java.lang.Boolean" description="메인 유무"%><%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <c:choose>
	<c:when test="${main}">
	<div class="navbar transparent navbar-inverse navbar-fixed-top">
   	</c:when>
   	<c:otherwise>
    <div class="navbar navbar-inverse navbar-static-top">
   	</c:otherwise>
   </c:choose>
      <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="/">
                <strong>정이 담긴 집</strong>
            </a>
            <div class="nav-collapse collapse">
                <ul class="nav pull-right">
                    <li><a href="/" class="active">HOME</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            객실정보
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/rooms/room1">그린티</a></li>
                            <li><a href="/rooms/room2">라벤더</a></li>
                            <li><a href="/rooms/room3">로즈마리</a></li>
                            <li><a href="/rooms/room4">스위트그린</a></li>
                            <li><a href="/rooms/room5">자스민</a></li>
                        </ul>
                    </li>
                    <li><a href="/reservations">예약정보</a></li>
                    <li><a href="blog.html">찾아오시는길</a></li>
                    <li><a href="pricing.html">문의게시판</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            PAGES
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="features.html">Features</a></li>
                            <li><a href="services.html">Services</a></li>
                            <li><a href="portfolio.html">Portfolio</a></li>
                            <li><a href="portfolio-item.html">Portfolio Item</a></li>
                            <li><a href="coming-soon.html">Coming Soon</a></li>
                            <li><a href="sign-in.html">Sign in</a></li>
                            <li><a href="sign-up.html">Sign up</a></li>
                            <li><a href="backgrounds.html">Backgrounds</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
      </div>
    </div>
