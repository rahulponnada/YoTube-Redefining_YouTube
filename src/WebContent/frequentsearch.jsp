<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.umkc.SearchKeywords"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YoTub | Redefining YouTube</title>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/grayscale.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<header class="intro">
        <div class="intro-body">
			<div class="row">
				<div class="col-sm-9" style="padding-left:280px">
		<p style="padding-left:0px">Most Frequent Keywords</p>  
        <table class="table table-striped">
        		<th style="text-align:center; Color:#000">S No</th>
        		
        		<th style="text-align:center; Color:#000">Keyword</th>
        		
        		<th style="text-align:center; Color:#000">Count</th>
        		
        		<%
        		//RecentLoginDetails recent = new RecentLoginDetails();
        		int i=1;
        		List<SearchKeywords> keywordList = (ArrayList<SearchKeywords>)request.getAttribute("List"); 
        		for(SearchKeywords tag:keywordList){
				//for(int i=0;i<=10;i++){	
				//}	%>
         		<tr>  
                    <td><%= i%></td>
                    <td><%= tag.getKeyword() %></td>
                    <td><%= tag.getCount() %></td>
                    
                </tr>
                <%i++; } %>     </table>  
          		

</div></div></div></header>
</body>
</html>