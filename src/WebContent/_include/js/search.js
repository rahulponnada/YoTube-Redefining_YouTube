$("#search").click(function(){
	window.location.href = "http://localhost:8080/yotube/page";
	var milliSeconds = 4000;
	var startTime = new Date().getTime(); // get the current time
	while (new Date().getTime() < startTime + milliSeconds);
});

$("#ios").click(function(){
	window.location.href = "http://localhost:8080/yotube/ios.jsp";
	var milliSeconds = 4000;
	var startTime = new Date().getTime(); // get the current time
	while (new Date().getTime() < startTime + milliSeconds);
});

$("#ios8").click(function(){
	window.location.href = "http://localhost:8080/yotube/ios8.jsp";
	var milliSeconds = 4000;
	var startTime = new Date().getTime(); // get the current time
	while (new Date().getTime() < startTime + milliSeconds);
});
