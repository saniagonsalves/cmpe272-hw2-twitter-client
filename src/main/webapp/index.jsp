<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
    h1 {text-align: center;}
    form {text-align: center;}
    body{ background: #2bc0e4; /* fallback for old browsers */
        background: -webkit-linear-gradient(to right, #2bc0e4, #eaecc6); /* Chrome 10-25, Safari 5.1-6 */
        background: linear-gradient(to right, #2bc0e4, #eaecc6); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */}
</style>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Twitter Example</title>
</head>
<body>
<h1>Welcome to TechnoSpartan Twitter</h1>
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

<br><br>
<form action="GetFavTweets" method="post">
    Get Fav Tweets
    <input type="submit" value="get fav tweets"/>
</form>

<br><br>
<form action="GetFollowers" method="post">
    Get Followers
    <input type="submit" value="get Followers"/>
</form>
<br><br>
<form action="UnFollow" method="post">
    TweetId
    <input type="text" name="tweetId"/>
    <input type="submit" value="un-follow User"/>
</form>
<br><br>
<form action="SearchTweets" method="post">
    TweetId
    <input type="text" name="tweetId"/>
    <input type="submit" value="search tweets by Id"/>
</form>

</body>
</html>