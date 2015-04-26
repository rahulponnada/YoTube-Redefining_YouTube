<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YoTub | Redefining YouTube</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script> -->
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
<script type= "text/javascript">
$(document).ready(function() {
	if (localStorage.chkbx && localStorage.chkbx != '' && localStorage.chkbx != null) {
		$('#remember_me').attr('checked', 'checked');
        $('#inputEmail').val(localStorage.usrname);
        $('#inputPassword').val(localStorage.pass);
    } 
	else{
		$('#remember_me').removeAttr('checked');
        $('#inputEmail').val('');
        $('#inputPassword').val('');
	}
	$('#remember_me').click(function() {
		 
		if ($('#remember_me').is(':checked')) {
             // save username and password
             localStorage.usrname = $('#inputEmail').val();
             localStorage.pass = $('#inputPassword').val();
             localStorage.chkbx = $('#remember_me').val();
         } else {
             localStorage.usrname = '';
             localStorage.pass = '';
             localStorage.chkbx = '';
         }
     });
	$('#login').click(function() {
		if(($.trim($('#inputEmail').val()) != null && $.trim($('#inputEmail').val()) != "") && $.trim($('#inputPassword').val()) != null && $.trim($('#inputPassword').val()) != "")
		{	
		$.ajax({
            url : "loginServlet",
        	data : {"userName" : user=$('#inputEmail').val(),"userPass" : $('#inputPassword').val()},
            success : function (responseText) {
            	console.log("hellololloo  "+responseText);
            	//document.location='welcome.jsp?user=satish';
            	//alert(typeof responseText);
            	//alert(responseText);
            	//var message = responseText;
            	if($.trim(responseText)=="success"){
            		console.log("success");
            		window.location='homepage.jsp';
            	}            	
            	else if($.trim(responseText)=="pass"){
            		console.log("else if");
            		alert("Invalid Password");
            		//document.location='welcome.jsp?user=satish';
            	}
            	else{
            		console.log("else");
            		alert("Invalid username");
            		//document.location='welcome.jsp?user=satish';
            	}
            }
        });}
		else{
			alert("Enter Login detials");
			
		}
    });

});

</script>

</head>

<body>

<header class="intro">
        <div class="intro-body">
			<div class="row">
				<div class="col-sm-8">
				  <!--div class="panel panel-default">
					<div class="panel-heading">
					  <h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">
						<form>
						Username:<input type="text"/></br>
						Password:<input type="password"/></br>
						<a href="https://www.google.com" class="btn btn-danger">Login</a>
						</form>
					</div>
				  </div>-->
					
					<div class="form-signin" style="padding-left:500px">
					  
					<table>
					<tr><td></td><td><p style="padding-left:70px">Please Sign In</p></td><td></td></tr>
					<tr>
        			<td></td>
					<td><input type="email" id="inputEmail" style="width:150%" class="form-control" placeholder="Email address" autofocus ></td>
					<td></td>
					</tr>
					<tr style="height:10px"></tr>
										<tr>
					<td></td>
					<td><input type="password" id="inputPassword" style="width:150%" class="form-control" placeholder="Password"></td>
					<td></td>
					</tr>
					<tr>
					<td></td>
					<td><div class="checkbox" style="text-align:left;padding-left:20px"><input type="checkbox" value="remember-me" id="remember_me"> Remember me</div></td>
					<td></td>
					</tr>
					<tr>
						<td></td>
						<td><div style="width:150%"><input type="submit" value="login" id="login" class="btn btn-small btn-primary btn-block"/></div></td>
						<td></td>
						</tr>
						<tr>
						<tr style="height:10px"></tr>
						<td></td>
						<td><div style="text-align:left"><a href="ForgotPassword.jsp?message=welcome">Forgot Password</a></td>
						</tr>
						</table>
						</div>
					
							</div> 
			</div>
		</div>		
	</div>
<!--	<div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
              
	<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> 
	
    </div></div>
<form method="post">  
        <fieldset style="width: 300px">  
            <legend> Login to App </legend>  
            <table>  
                <tr>  
                    <td>User ID</td>  
                    <td><input type="text" id="userName" name="username" required="required" /></td>  
                </tr>  
                <tr>  
                    <td>Password</td>  
                    <td><input id ="userPass" type="password" name="userpass" required="required" /></td>  
                </tr>
                  
                <tr>  
                    <td><input type="submit" value="Login" id="login"/></td>  
                    <td><input type="checkbox" value="Remember" id="remember_me">Remember Me</td>
                    <td><a href="ForgotPassword.jsp">Forgot Password</a></td>
                </tr>  
            </table>  
        </fieldset>  
    </form>-->
</body>
</html>