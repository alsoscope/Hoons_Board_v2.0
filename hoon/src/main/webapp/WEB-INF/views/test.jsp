<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<button id="button">dddd</button>
<ul id="aaa">
	ssssss
</ul>

<script id="template" type="text/x-handlebars-template">
	{{#each .}}
	<li>{{rno}} 00 {{replytext}}</li>
	{{/each}}
</script>
<script>
	function getReplyList(page) {
		var bno = "25";
		
		$.ajax({
			type:"GET",
			url:"/boards/" + bno + "/replies?page=" + page,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"GET"
			},
			dataType:"json",
			success:function(result) {
				var replyList = result.replyList;
				var pageMaker = result.pageMaker;
				var source = $("#template").html();
				var template = Handlebars.compile(source);
				
				$("#aaa").html(template(replyList));
			}
		});
	}

	$(document).ready(getReplyList(1));
</script>
</body>
</html>