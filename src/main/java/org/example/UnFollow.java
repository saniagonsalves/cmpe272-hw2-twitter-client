package org.example;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class UnFollow extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tweetIdStr = req.getParameter("tweetId");
        long tweetId = Long.parseLong(tweetIdStr);
        User result = null;

        try {
            result = TwitterFactory.getSingleton().destroyFriendship(tweetId);
            System.out.println(result.toString());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if (result != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("unfollow.jsp");
            resp.setHeader("tid", Long.toString(result.getId()));
            requestDispatcher.include(req, resp);
        }
    }
}
