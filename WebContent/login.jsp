<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>Login - CP Taskboard</title>
</head>
<body>
	<jsp:include page="topbar.jsp"></jsp:include>
	<div class="container">
		<h1 class="title">Login/Register</h1>
		<i class="title-right"><br/>@XGN can u make two seperate pages?? --Zzzyt</i>
		<hr/>
		<div class="card center-form"><div class="card-body">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Username" id="user" maxlength="50" />
			</div>
			
			<div class="input-group">
				<input type="password" class="form-control" placeholder="Password" id="pass" type="password" maxlength="50" />
			</div>
			
			<div class="input-group">
				<button class="form-control btn btn-primary" style="margin-left:0px;" onclick="login()">Login</button>
				<button class="form-control btn btn-primary" style="margin-right:0px;" onclick="reg()">Register</button>
			</div>
		</div></div>

		<script>
			function login() {
				$.post("loginS", {
					"username" : document.getElementById("user").value,
					"password" : document.getElementById("pass").value,
				}, function(data, status) {
					if (data == "OK") {
						window.location = "index.jsp";
					} else {
						alert(data);
					}
				})
				.fail(function(response){
					alert("Failed!\n"+response.responseText);
				});
				
			}
			function reg() {
				$.post("regS", {
					"username" : document.getElementById("user").value,
					"password" : document.getElementById("pass").value,
				}, function(data, status) {
					alert(data);
				})
				.fail(function(response){
					alert("Failed!\n"+response.responseText);
				});
			}
		</script>
	</div>
</body>
</html>