package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
// Sania Gonsalves
public class GetUserTimeline extends HttpServlet {

	private Twitter twitter;
	public GetUserTimeline() {
		twitter = TwitterFactory.getSingleton();
	}
	
	public GetUserTimeline(Twitter twitter) {
		this.twitter = twitter;
	}
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        List<Status> result = null;

        try {
            result = twitter.getUserTimeline();
            //System.out.println(result.toString());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if (result != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("gettimeline.jsp");
            StringBuilder sb = new StringBuilder();
            for(Status status : result) {
                sb.append(status.getText()).append(" ").append(status.getId()).append("\n");
            }

            resp.setHeader("tweetStr", sb.toString());
            requestDispatcher.include(req, resp);
        }
    }
}
