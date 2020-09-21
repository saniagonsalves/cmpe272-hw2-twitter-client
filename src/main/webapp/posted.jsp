<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tweeted</title>
</head>
<body>
<big>
    <% String tweet = request.getParameter("tweet");  String tid = response.getHeader("tid"); %>
    Congrats User!!!! You have tweeted. Tweet : <% out.println(tweet); %> TweetId : <% out.println(tid); %>
</big>
<a href="index.jsp"><b>Back</b></a>
</body>
</html>