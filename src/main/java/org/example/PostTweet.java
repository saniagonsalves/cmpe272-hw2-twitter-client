package org.example;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class PostTweet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String tweet = req.getParameter("tweet");
    Status result = null;

    try {
      result = TwitterFactory.getSingleton().updateStatus(tweet);
      System.out.println(result.toString());
    } catch (TwitterException e) {
      e.printStackTrace();
    }

    if (result != null) {
      RequestDispatcher requestDispatcher = req.getRequestDispatcher("posted.jsp");
      resp.setHeader("tid", Long.toString(result.getId()));
      requestDispatcher.include(req, resp);
    }
  }
}
