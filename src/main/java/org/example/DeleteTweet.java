package org.example;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTweet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String tweetIdStr = req.getParameter("tweetId");
    long tweetId = Long.parseLong(tweetIdStr);
    Status result = null;

    try {
      result = TwitterFactory.getSingleton().destroyStatus(tweetId);
      System.out.println(result.toString());
    } catch (TwitterException e) {
      e.printStackTrace();
    }

    if (result != null) {
      RequestDispatcher requestDispatcher = req.getRequestDispatcher("deleted.jsp");
      resp.setHeader("tid", Long.toString(result.getId()));
      requestDispatcher.include(req, resp);
    }
  }
}
