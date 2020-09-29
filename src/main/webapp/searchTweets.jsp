<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Followers</title>
</head>
<body>
<big>
    <% String[] tweetStr = response.getHeader("tweetStr").split("\n"); %>
    Congrats User!!!! The followers Ids are : <br><br>
    <% for (String tweet : tweetStr) {
        out.println(tweet + "<br>");
    }%>
</big>
<a href="index.jsp"><b>Back</b></a>
</body>
</html>