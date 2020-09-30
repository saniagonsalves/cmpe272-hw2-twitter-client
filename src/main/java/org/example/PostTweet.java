package org.example;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
// Sania Gonsalves
public class PostTweet extends HttpServlet {
	
	private Twitter twitter;
	public PostTweet() {
		twitter = TwitterFactory.getSingleton();
	}
	
	public PostTweet(Twitter twitter) {
		this.twitter = twitter;
	}
	

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String tweet = req.getParameter("tweet");
    Status result = null;
    String errorMessage =null;

    try {
      result = twitter.updateStatus(tweet);
      //System.out.println(result.toString());
    } catch (TwitterException e) {
      e.printStackTrace();
      errorMessage = e.getErrorMessage();
    }

    if (result != null) {
      RequestDispatcher requestDispatcher = req.getRequestDispatcher("posted.jsp");
      resp.setHeader("tid", Long.toString(result.getId()));
      requestDispatcher.include(req, resp);
    }
    else {
    	RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
        resp.setHeader("error", errorMessage);
        requestDispatcher.include(req, resp);
    }
  }
}
