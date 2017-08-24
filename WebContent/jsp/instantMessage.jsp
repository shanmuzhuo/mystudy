<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-3.0.0.min.js"></script>
<title>即时消息</title>
</head>
<body>
您好，现在的消息数目是：<span id="num">${num }</span>条
<button id="btn" value="fsd">按钮</button>
</body>

<script type="text/javascript">

$(function(){
	
// ajax 普通轮询，设置定时器，这样会耗费网络宽带资源，且由于异步，ajax的返回结果顺序得不到保障
/* 	window.setInterval(function(){
		$.ajax({
			url : "/mystudy/instant/ajax",
			type : "get",						
			dataType : "json",
			success : function(data){
				var num = parseInt($("#num").html());
				$("#num").html(data);
			},
			error : function(){
				console.log("error");
			}
		});
	},1000); */
	
// ajax 长轮询，long-polling,这个要等服务器做出回应了才能继续，避免了大量的不必要的请求，
	function longPolling(){
		$.ajax({
			url : "/mystudy/instant/longAjax",
			type : "get",						
			dataType : "json",
			success : function(data){
				$("#num").html(data);
				longPolling();
			},
			error : function(){
				console.log("error");
			}
		});
	}
	longPolling();
});
</script>
</html>