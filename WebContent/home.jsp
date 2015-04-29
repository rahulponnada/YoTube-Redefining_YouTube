<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->

<head>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>YoTube | Redefining YouTube</title>   

<meta name="description" content="Insert Your Site Description" /> 

<!-- Mobile Specifics -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="true"/>
<meta name="MobileOptimized" content="320"/>   

<!-- Mobile Internet Explorer ClearType Technology -->
<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->

<!-- Bootstrap -->
<link href="_include/css/bootstrap.min.css" rel="stylesheet">

<!-- Main Style -->
<link href="_include/css/main.css" rel="stylesheet">

<!-- Supersized -->
<link href="_include/css/supersized.css" rel="stylesheet">
<link href="_include/css/supersized.shutter.css" rel="stylesheet">

<!-- FancyBox -->
<link href="_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">

<!-- Font Icons -->
<link href="_include/css/fonts.css" rel="stylesheet">

<!-- Shortcodes -->
<link href="_include/css/shortcodes.css" rel="stylesheet">

<!-- Responsive -->
<link href="_include/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="_include/css/responsive.css" rel="stylesheet">

<!-- Supersized -->
<link href="_include/css/supersized.css" rel="stylesheet">
<link href="_include/css/supersized.shutter.css" rel="stylesheet">

<!-- Google Font -->
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>

<!-- Fav Icon -->
<link rel="shortcut icon" href="#">

<link rel="apple-touch-icon" href="#">
<link rel="apple-touch-icon" sizes="114x114" href="#">
<link rel="apple-touch-icon" sizes="72x72" href="#">
<link rel="apple-touch-icon" sizes="144x144" href="#">

<!-- Modernizr -->
<script src="_include/js/modernizr.js"></script>

<!-- Analytics -->
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'Insert Your Code']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();     
</script>
<!-- End Analytics -->
<style>
::-webkit-scrollbar {
    width: 12px;
}
 
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0); 
    border-radius: 10px;
background-color:#D0D0D0 ;
}
 
::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
background-color:#FF6666;
}
</style>
</head>


<body>

<!-- This section is for Splash Screen -->

<!-- End of Splash Screen -->

<!-- Homepage Slider -->

<!-- End Homepage Slider -->

<!-- Header -->
<header>
    <div class="sticky-nav">
    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
        
        <div id="logo">

    	</div>
        <nav id="menu">
        	<ul id="menu-nav">
            	<li class="current"><a href="#" onClick="window.location='/yotube/homepage.jsp'">Home</a></li>
            	<li><input type="text" id="search" style="height:25px;width:460px"></input>
        		<button onClick="searching()" type="button" class="btn btn-danger" style="height:36px; width:100px; text-align:center; padding: 5px 5px 5px 5px; margin-bottom: 8.5px">Ranking</button></li>
        		<li><a href="#" onClick="window.location='/yotube/recent'">Frequent Users</a></li>
        		<li><a href="#" onClick="window.location='/yotube/frequent'">Frequent Searches</a></li>
                <li><a href="#" onClick="window.location='Login.jsp'">Logout</a></li>
            </ul>
        </nav>
        
    </div>
</header>
<!-- End Header -->

<!-- Our Work Section -->
<div id="work" class="page">
	<div class="container">
    	<!-- Title Page -->
     	  <div class="span12" style="height:140px">
                <div class="title-page">
                   <h5 class="title">YoTube</h5>
                   <!--  <h6 class="title-description">A new way to search YouTube.</h6>--></div>
            </div>
        
        <!-- End Title Page -->
        
        <!-- Portfolio Projects -->
		
        <div class="row">
        	<div class="span3">
            	<!-- Filter -->
                <nav id="options" class="work-nav">
                <ul class="type-work">Categories</ul>
                <ul id="filters" class="option-set" data-option-key="filter" style="width: 250px; height: 700px; overflow: auto; border:2px">
                    <!-- <ul id="filters" class="option-set" data-option-key="filter"> -->
                        <li><a href="#filter" data-option-value="*" class="selected">All results</a></li>
                        <c:choose>
    					<c:when test="${topics.size() eq 0}">
       						<li>No results to display</li>
    					</c:when>
    					<c:otherwise>
    					<c:forEach var="i" begin="0" end="${topics.size()-1}">
    					<li><a href="#filter" data-option-value=".design" onClick="Search('${topics.get(i)}')">${topics.get(i)}</a>${topicCategory.get(i)}</li>
                        </c:forEach>
    					</c:otherwise>
						</c:choose>       	
                        
                </ul>
                </nav>
                <!-- End Filter -->
            </div>
            
            <div class="span9">
            <h3> Search results for "<span style="font-style: italic; color:red;">${query}</span>"</h3>
            	<div class="row">
                	<section id="projects">
                    	<ul id="thumbs">
                        				
                        	<!-- Video Info -->
                        	        
								<c:forEach var="i" begin="0" end="${videoInfo.size()-1}">
                                <li class="item-thumbs span3">
                                <!-- Fancybox - Gallery Enabled - Title - Full Image -->
                                <a class="hover-wrap fancybox-media" data-fancybox-group="video" title="${videoInfo.get(i).getTitle()}" href="${videoInfo.get(i).getUrl()}">
                                <span class="overlay-img" style="text-align:center;color:white;font-weight: 150;font-size:20px;vertical-align: middle;">
                                <ul>
                                </br>
                                <li>View % - ${videoInfo.get(i).getViewpercent()}</li>
                                </br>
                                <li>Like/Views % - ${videoInfo.get(i).getLikeviewCount()}</li>
                                </br>   
                                <li>Dislike/Like % - ${videoInfo.get(i).getDislikelikeCount()}</li>
                    <!--        <li>Views - ${videoInfo.get(i).getViewCount()}</li>
                                <li>Likes - ${videoInfo.get(i).getLikeCount()}</li>
                                <li>Dislikes - ${videoInfo.get(i).getDislikeCount()}</li>   
                                <li>Comments - ${videoInfo.get(i).getCommentCount()}</li> -->
                                </ul>
                                </span></a>
                                <!-- Thumb Image and Description -->
                                <img src="${videoInfo.get(i).getThumbnail()}" alt="">
                            </li>
                            </c:forEach>
                        </ul>
                    </section>
                    
            	</div>
            </div>
        </div>
        <!-- End Portfolio Projects -->
    </div>
</div>
<!-- End Our Work Section -->

<!-- Socialize -->
<div id="social-area" class="page">
	<div class="container">
    	<div class="row">
            <div class="span12">
                <nav id="social">
                    <ul>
                        <li><a href="" title="Follow Me on Twitter" target="_blank"><span class="font-icon-social-twitter"></span></a></li>
                        <li><a href="" title="Follow Me on Facebook" target="_blank"><span class="font-icon-social-facebook"></span></a></li>
                        <li><a href="" title="Follow Me on Google Plus" target="_blank"><span class="font-icon-social-google-plus"></span></a></li>
                        <li><a href="" title="Follow Me on LinkedIn" target="_blank"><span class="font-icon-social-linkedin"></span></a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- End Socialize -->

<!-- Footer -->
<footer>
	<p class="credits">&copy;2015  <a href="" title="">made with lots of smile :) :)</a> by CodeGeeks<a href="" title=""></a></p>
</footer>
<!-- End Footer -->

<!-- Back To Top -->
<a id="back-to-top" href="#">
	<i class="font-icon-arrow-simple-up"></i>
</a>
<!-- End Back to Top -->


<!-- Js -->
<script>
function Search(query){
	window.location = '/yotube/searchpage?query='+query;
	document.getElementById("search").value=query;
}
function searching(){
	var query= document.getElementById("search").value;
	window.location = '/yotube/searchpage?query='+query;
}
</script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
<script src="_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
<script src="_include/js/supersized.3.2.7.min.js"></script> <!-- Slider -->
<script src="_include/js/waypoints.js"></script> <!-- WayPoints -->
<script src="_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
<script src="_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
<script src="_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
<script src="_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
<script src="_include/js/jquery.tweet.js"></script> <!-- Tweet -->
<script src="_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
<script src="_include/js/main.js"></script> <!-- Default JS -->
<!-- End Js -->

</body>
</html>