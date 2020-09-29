package org.example;

import twitter4j.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchTweets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tweetIdStr = req.getParameter("tweetId");
        long tweetId = Long.parseLong(tweetIdStr);
        Status result = null;

        try {
            result = TwitterFactory.getSingleton().showStatus(tweetId);
            System.out.println(result.toString());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        if (result != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("searchTweets.jsp");
            StringBuilder sb = new StringBuilder();
            sb.append(result.getText()).append(" ").append(result.getId()).append("\n");
            resp.setHeader("tweetStr", sb.toString());
            requestDispatcher.include(req, resp);
        }
    }
}
