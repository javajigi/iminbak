<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@include file="/WEB-INF/jsp/include/tags.jspf"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Styles -->
    <link href="${url:resource('/stylesheets/bootstrap.css')}" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/theme.css')}">
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" type="text/css" href="${url:resource('/stylesheets/lib/animate.css')}" media="screen, projection">
	<link rel="stylesheet" href="${url:resource('/stylesheets/portfolio-item.css')}" type="text/css" media="screen" />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
	<iminbak:navbar main="false"/>
    
    <div id="portfolio_tem">
        <div class="container">
            <%@ include file="/WEB-INF/jsp/include/room_submenu.jspf" %>
            
            <div class="section_header">
                <h3><a href="/rooms/${type}">${type.name}</a></h3>
            </div>

            <div class="span7 left_box">
                <div class="big">
                	<c:forEach var="each" items="${images}">
                	<img src="${each}"/>
                	</c:forEach>
                </div>
                <div class="thumbs">
                	<c:forEach var="each" items="${thumbnailImages}">
                    <div class="thumb">
                        <img src="${each}">
                        <a href="#" class="mask">
                            <div class="more">+</div>
                        </a>
                    </div>                	
                	</c:forEach>
                </div>
            </div>

            <div class="span5 right_box">
                <h2>객실정보</h2>
                <table class="table table-hover table-striped">
				  <thead>
				    <tr>
				      <th>평형</th>
				      <th>위치</th>
				      <th>기준인원</th>
				      <th>최대인원</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>9평</td>
				      <td>${type.floor}층</td>
				      <td>2명</td>
				      <td>5명</td>
				    </tr>
				    <tr>
				      <td colspan="4">TV, 냉장고, 에어콘, 가스레인지, 밥솥, 드라이기</td>
				    </tr>
				  </tbody>
                </table>
                              
                <h2>객실요금</h2>
                <table class="table table-hover table-striped">
				  <thead>
				    <tr>
				      <th></th>
				      <th>주중</th>
				      <th>주말</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>비수기</td>
				      <td>70,000원</td>
				      <td>70,000원</td>
				    </tr>
				    <tr>
				      <td>성수기</td>
				      <td>90,000원</td>
				      <td>90,000원</td>
				    </tr>
				  </tbody>
                </table>
                
                <a href="javascript:RealReservation()" class="btn btn-primary btn-large btn-question pull-right">실시간 예약하기</a>
            </div>
        </div>
    </div>
    
    <%@include file="/WEB-INF/jsp/include/footer.jspf"%>
    
    <!-- Scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="${url:resource('/javascripts/bootstrap.min.js')}"></script>
    <script src="${url:resource('/javascripts/theme.js')}"></script>
	<script language="javascript"> 
	<!-- 
	function RealReservation() { 
	  var url="http://real.pentour.com/index.php?pid=2542"; 
	  open(url,'펜션예약하기','width=620,height=600,menubar=no,resizable=no,location=no,status=no,scrollbars=yes,toolbar=no'); 
	} 
	// --> 
	</script>
</body>
</html>