<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Twitter Example</title>
</head>
<body>
<form action="PostTweet" method="post">
    Tweet
    <input type="text" name="tweet"/>
    <input type="submit" value="post tweet"/>
</form>

<br><br>

<form action="DeleteTweet" method="post">
    TweetId
    <input type="text" name="tweetId"/>
    <input type="submit" value="delete tweet"/>
</form>

<br><br>

<form action="GetHomeTimeline" method="post">
    GetHomeTimeline
    <input type="submit" value="get home timeline"/>
</form>

<br><br>

<form action="GetUserTimeline" method="post">
    GetUserTimeline
    <input type="submit" value="get user timeline"/>
</form>

</body>
</html>