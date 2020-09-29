package org.example;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetFavTweets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Status> result = null;

        try {
            result = TwitterFactory.getSingleton().getFavorites();
            System.out.println(result.toString());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if (result != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("getFavTweets.jsp");
            StringBuilder sb = new StringBuilder();
            for (Status status : result) {
                sb.append(status.getText()).append(" ").append(status.getId()).append("\n");
            }

            resp.setHeader("tweetStr", sb.toString());
            requestDispatcher.include(req, resp);
        }

    }
}
