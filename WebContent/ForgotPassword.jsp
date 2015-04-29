<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
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
<script>
<%	//String message =  request.getAttribute("message").toString();

if(request.getParameter("message").toString()!=null && !request.getParameter("message").toString().isEmpty())
	{
		String message =  request.getParameter("message").toString();
		if(message.equalsIgnoreCase("success")){%>
			alert("Mail sent successfully!!");
			document.location ='Login.jsp'<%}
		else if(message.equalsIgnoreCase("error")){ %>
			alert("Please enter correct Mail ID");
		<%}else
		{}	
		
	}%>
	</script>
<header class="intro">
        <div class="intro-body">
			<div class="row">
				<div class="col-sm-8">
<form action="pass" class="form-signin" method="post" style="padding-left:500px">
		<p style="padding-left:0px">Please Enter your mail id for password recovery</p>  
        <table> 
         		<tr>  
                    <td></td>
                    <td  style="padding-left:50px"><input type="text" id="email" name="email" style="width:150%" required="required" class="form-control" placeholder="Email address" required autofocus/></td>  
                </tr>  
                <tr style="height:10px"></tr>
                <tr>  
                    <td></td>
                    <td  style="padding-left:50px"><input style="width:150%"class="btn btn-small btn-primary btn-block" type="submit" value="Submit"/></td>  
                </tr>  
            </table>  
          
</form>
</div></div></div></header>
</body>
</html>