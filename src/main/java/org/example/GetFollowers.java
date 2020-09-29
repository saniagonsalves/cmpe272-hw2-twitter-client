package org.example;

import twitter4j.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFollowers extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        ArrayList<Long> friendsid = new ArrayList<Long>();
        Twitter twitter = new TwitterFactory().getInstance();
        long cursor = -1;
        IDs ids = null;
        System.out.println("List of Ids");
        do {
            try {
                ids = twitter.getFriendsIDs(cursor);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            for (long id : ids.getIDs()) {
                friendsid.add(id);
            }
        } while ((cursor = ids.getNextCursor()) != 0);
        if (friendsid != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("getFollowers.jsp");
            StringBuilder sb = new StringBuilder();
            for(Long id : friendsid) {
                sb.append(id).append("\n");
            }

            res.setHeader("tweetStr", sb.toString());
            requestDispatcher.include(req, res);
        }
    }
}
