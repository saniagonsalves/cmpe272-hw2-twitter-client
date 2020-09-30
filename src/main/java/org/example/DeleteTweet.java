package org.example;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Sania Gonsalves
public class DeleteTweet extends HttpServlet {

	private Twitter twitter;
	public DeleteTweet() {
		twitter = TwitterFactory.getSingleton();
	}
	
	public DeleteTweet(Twitter twitter) {
		this.twitter = twitter;
	}

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String tweetIdStr = req.getParameter("tweetId");
    long tweetId = Long.parseLong(tweetIdStr);
    Status result = null;
    String errorMessage = null;

    try {
      result = twitter.destroyStatus(tweetId);
      //System.out.println(result.toString());
    } catch (TwitterException e) {
      e.printStackTrace();
      errorMessage = e.getErrorMessage();
    }

    if (result != null) {
      RequestDispatcher requestDispatcher = req.getRequestDispatcher("deleted.jsp");
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
