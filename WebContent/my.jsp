<%@page import="java.util.Comparator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.hhs.xgn.taskboard.type.Status"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.hhs.xgn.taskboard.util.Getter"%>
<%@page import="com.hhs.xgn.taskboard.type.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>Index - CP Taskboard</title>
</head>
<body>
	<jsp:include page="topbar.jsp"></jsp:include>
	<div class="container">
		<h1>My CP Taskboard</h1>
		<%
			if(session.getAttribute("username")==null){
				out.println("Please login");
				return;
			}
		
			User u=Getter.getUser((String)session.getAttribute("username"));
			HashMap<String,Status> hash=u.status;
			ArrayList<Entry<String,Status>> arr=new ArrayList<>();
			int ac=0,nonac=0;
			for(Entry<String,Status> entry:hash.entrySet()){
				if(entry.getValue().ac){
					ac++;
				}else{
					nonac++;
				}
				arr.add(entry);
			}
		%>
		
		<span class="badge badge-success">AC Problem <%=ac %></span>
		<span class="badge badge-danger">NonAC Problem <%=nonac %></span>
		<br/>
		
		<table width="80%" class="table table-striped table-bordered table-hover">
			<tr>
				<th>OJ</th>
				<th>UID</th>
				<th>Title</th>
				<th>Tag</th>
				<th>AC</th>
				<th>Last Update</th>
			</tr>
			<%
			
			
			arr.sort(new Comparator<Entry<String,Status>>(){
				public int compare(Entry<String,Status> o1,Entry<String,Status> o2){
					return -Long.compare(o1.getValue().lastUpdateTime, o2.getValue().lastUpdateTime);
				}
			});
			
			for(Entry<String,Status> entry:arr){ 
					String[] str=entry.getValue().tag.split(",");
					Arrays.sort(str);
					String big="";
					for(String x:str){
						big+="<span class=\"badge badge-secondary\">"+x+"</span> ";
					}
						
			%>
				<tr>
					<td><img width=16px height=16px src="https://github.com/XiaoGeNintendo/CP-Taskboard-Resource/raw/master/<%=entry.getValue().oj %>.png"></td>
					<td><a href="<%=entry.getValue().link%>"><%=entry.getKey() %></a></td>
					<td><a href="javascript:alert('My note:<%=entry.getValue().note.replaceAll("'", "\\\\'")%>')"><%=entry.getValue().title %></a></td>
					<td><%=big %></td>
					<td><span class="badge badge-<%=(entry.getValue().ac?"success":"danger")%>"><%=(entry.getValue().ac?"Yes":"No")%></span> </td>
					<td><%=new Date(entry.getValue().lastUpdateTime) %></td>
				</tr>
			<%} %>
		</table>
		
		
	</div>
</body>
</html>