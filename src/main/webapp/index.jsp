<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息录入</title>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<a href="javascript:void(0)" onClick="add1()">增加</a>
	<table border="1">
	<tbody>
		<c:forEach var="stu" items="${stu}">
			<tr>
				<td>${stu.id}</td>
				<td>${stu.name}</td> 
				<td>${stu.birthday}</td> 
				<td>${stu.description}</td> 
				<td>${stu.avgscore}</td>  
				<td><a href="javascript:void(0)" onClick="update('${stu.id}','${stu.name}','${stu.birthday}','${stu.description}','${stu.avgscore}')">修改</a><a href="javascript:void(0)" onClick="deleteStu('${stu.id}')">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
		<thead>
		<tr>
		<td>id</td>
		<td>name</td>
		<td>birthday</td>
		<td>description</td>
		<td>avgscore</td>
		<td>operation</td>
		</tr>
		</thead>
	</table>
	<c:forEach var="i" begin="1" end="${totalPage}" step="1">   
	<a href="StudentServlet?method=listByDesc&pageNum=${i}">${i}</a>  
</c:forEach>
	<div id="operation" style="width: 80em; height: 200px; text-align: center;">
	</div>
</body>
<script type="text/javascript">
	function update(id,name,birthday,description,avgScore){
		var str="";
		str+="<form action='StudentServlet?method=update' method='post'>";
		str+="<label for='id'>id</label> <input id='id' type='text' value='"+id+"' disabled='disabled'  placeholder='请输入姓名' size='50' maxlength='40'><br>";
		str+="<label for='name'>姓名</label> <input id='name' type='text' value='"+name+"' placeholder='请输入姓名' size='50' maxlength='40'><br>";
		str+="<label for='birkthday'>出生日期</label><input id='birthday' value='"+birthday+"' type='datetime'><br>";
		str+="<label for='description'>备注</label><textarea rows='3' cols='30' id='description' maxlength='40'>"+description+"</textarea><br>";
		str+="<label for='avgscore'>平均分</label><input value='"+avgScore+"' id='avgscore'>";
		str+="<input type='button' onclick='updateStu()' value='修改'>";
		str+="</form>";
		window.document.getElementById("operation").innerHTML = str;
	}
	function add1(){
		
		var str="";
		str+="<form action='StudentServlet?method=update' method='post'>";
		str+="<label for='name'>姓名</label> <input id='name' type='text' value='' placeholder='请输入姓名' size='50' maxlength='40'><br>";
		str+="<label for='birkthday'>出生日期</label><input id='birthday' value='' type='datetime'><br>";
		str+="<label for='description'>备注</label><textarea rows='3' cols='30' id='description' maxlength='40'></textarea><br>";
		str+="<label for='avgscore'>平均分</label><input value='' id='avgscore'>";
		str+="<input type='button' onclick='addStu()' value='添加'>";
		str+="</form>";
		window.document.getElementById("operation").innerHTML = str;
	}
	function deleteStu(id){
		$.ajax({
			url:"StudentServlet",
			type:"POST",
			data:{"id":id,"method":"delete"},
			success: function(data){
				window.location.reload()
				},async:false
			})
			
	}
	function updateStu(){
		$.ajax({
			url:"StudentServlet",
			type:"get",
			data:{
				"id":$("#id").val(),
				"name":$("#name").val(),
				"birthday":$("#birthday").val(),
				"description":$("#description").val(),
				"avgscore":$("#avgscore").val(),
				"method":"update"
				},
			success: function(data){
				window.location.reload()
				},async:false
			})
			
	}
	function addStu(){
		$.ajax({
			url:"StudentServlet",
			type:"get",
			data:{
				"id":$("#id").val(),
				"name":$("#name").val(),
				"birthday":$("#birthday").val(),
				"description":$("#description").val(),
				"avgscore":$("#avgscore").val(),
				"method":"add"
				},
			success: function(data){
				window.location.reload()
				},async:false
			})
			
	}
</script>
</html>
