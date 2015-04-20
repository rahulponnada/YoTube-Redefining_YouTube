<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<script type= "text/javascript">
$(document).ready(function() {
	if (localStorage.chkbx && localStorage.chkbx != '' && localStorage.chkbx != null) {
		alert("Hello");
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
		 
		alert("Hello2"); 
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
	/*$('#login').click(function() {
		
		alert("Hello3");
		var user=$('#inputEmail').val();
        var pwd=$('#inputPassword').val();
        $.ajax({
            type: "POST",
        	url : "loginServlet",
        	dataType: "text",
            data : {"userName" : user,"userPass" : pwd},
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            success : function (data) {
            	alert("hellololloo");
            	//document.location='welcome.jsp?user=satish';
            	alert(typeof data);
            	alert(data);
            	//var message = responseText;
            	if(data=="success"){
            		alert("Login sucessfull");
            		document.location='welcome.jsp?user=satish';
            	}            	
            	else if(data=="user"){
            		alert("Invalid Username");
            		//document.location='welcome.jsp?user=satish';
            	}
            	else{
            		alert("Invalid password");
            		//document.location='welcome.jsp?user=satish';
            	}
            	alert(responseText);
            	//var message = responseText;
            	//test(responseText);
            	//alert("repsosne"+responseText);
            	//String message = (String)responseText;
            	//if(message=="invalid password")%>
            	//var successUrl = "welcome.jsp"; // might be a good idea to return this URL in the successful AJAX call
    			//window.location.href = 'welcome.jsp?user='+responseText;
    			//document.location='welcome.jsp?user='+responseText;
                //$('#ajaxGetUserServletResponse').innerHtml =responseText;
            }
        });*/
    });
	/*function test(responseText){
		if(responseText=="invalid password"){
			alert("Invalid Password");
			//document.location="Login.jsp"
		}
		else if(responseText=="invalid username")
		{
			alert("Invalid Username");
			//document,location="Login.jsp"
		}
		else{
			document.location="welcome.jsp?user="+responseText;
		}
	}*/

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
					
					<form method="post" action="homepage.jsp" class="form-signin" style="padding-left:500px">
					  
					<table>
					<tr><td></td><td><p style="padding-left:70px">Please Sign In</p></td><td></td></tr>
					<tr>
        			<td></td>
					<td><input type="email" id="inputEmail" style="width:150%" class="form-control" placeholder="Email address" required autofocus ></td>
					<td></td>
					</tr>
					<tr style="height:10px"></tr>
										<tr>
					<td></td>
					<td><input type="password" id="inputPassword" style="width:150%" class="form-control" placeholder="Password" required></td>
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
						<td><div style="text-align:left"><a href="/yotube/recent">Recent Users</a></td>
						</tr>
						</table>
						</form>
					
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