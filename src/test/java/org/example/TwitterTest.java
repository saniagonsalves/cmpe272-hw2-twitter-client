package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import twitter4j.*;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




/**
 * @author Shreya Ghotankar
 *
 */
@RunWith(PowerMockRunner.class)
public class TwitterTest {
@Mock
HttpServletRequest request;
@Mock
HttpServletResponse response;
@Mock
RequestDispatcher reqdisp;
	
   @Before
   public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


   
	@Test
	public void testPostTweetSuccess() throws Exception{

		String outStatus = "{\"createdAt\" : \"Wed Sep 23 22:23:52 PDT 2020\", \"id\" :\"1309000576240410627\", \"text\":\"Hello\"}";
		TwitterFactory mockfac = Mockito.mock(TwitterFactory.class);
		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockfac);
		
		Twitter mockTwit = mock(Twitter.class);
		when(mockTwit.updateStatus("Hello")).thenReturn(TwitterObjectFactory.createStatus(outStatus));
		
		when(request.getParameter("tweet")).thenReturn("Hello");
		when(request.getRequestDispatcher("posted.jsp")).thenReturn(reqdisp);
		
		new PostTweet(mockTwit).doPost(request, response);
		
		verify(reqdisp).include(request, response);
		verify(response, times(1)).setHeader("tid", "1309000576240410627");
		
	}
	
	@Test
	public void testPostTweetFail() throws Exception{
		TwitterException exception = new TwitterException("{\"errors\":[{\"code\":215,\"message\":\"Bad Authentication data\"}]}");
		
		TwitterFactory mockfac = mock(TwitterFactory.class);
		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockfac);
		
		Twitter mockTwit = Mockito.mock(Twitter.class);
		when(mockTwit.updateStatus("Hello")).thenThrow(exception);
		
		when(request.getParameter("tweet")).thenReturn("Hello");
		when(request.getRequestDispatcher("error.jsp")).thenReturn(reqdisp);
		
		new PostTweet(mockTwit).doPost(request, response);
		
		verify(reqdisp).include(request, response);
		verify(response, times(1)).setHeader("error", "Bad Authentication data");
		
	}
	
	@Test
	public void testDeleteTweetSuccess() throws Exception{
		String delStatus = "{\"createdAt\" : \"Thu Sep 24 14:31:16 PDT 2020\", \"id\" :\"1309244033835835392\", \"text\":\"Hi\"}";
		TwitterFactory mockfac = Mockito.mock(TwitterFactory.class);
		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockfac);
		
		Twitter mockTwit = mock(Twitter.class);
		when(mockTwit.destroyStatus(1309244033835835392L)).thenReturn(TwitterObjectFactory.createStatus(delStatus));
		
		when(request.getParameter("tweetId")).thenReturn("1309244033835835392");
		when(request.getRequestDispatcher("deleted.jsp")).thenReturn(reqdisp);
		
		new DeleteTweet(mockTwit).doPost(request, response);
		
		verify(reqdisp).include(request, response);
		verify(response, times(1)).setHeader("tid", "1309244033835835392");	
		
		
	}
	
	@Test
	public void testDeleteTweetFail() throws Exception{
		TwitterException exception = new TwitterException("{\"errors\":[{\"code\":183,\"message\":\"You may not delete another user's status.\"}]}");
		
		TwitterFactory mockfac = mock(TwitterFactory.class);
		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockfac);
		
		Twitter mockTwit = Mockito.mock(Twitter.class);
		when(mockTwit.destroyStatus(1234L)).thenThrow(exception);
		
		when(request.getParameter("tweetId")).thenReturn("1234");
		when(request.getRequestDispatcher("error.jsp")).thenReturn(reqdisp);
		
		new DeleteTweet(mockTwit).doPost(request, response);
		
		verify(reqdisp).include(request, response);
		verify(response, times(1)).setHeader("error", "You may not delete another user's status.");
		
	}

	@Test
	public void testGetUserTimeline() throws Exception{
		String status1 = "{\"createdAt\" : \"Wed Sep 23 22:23:52 PDT 2020\", \"id\" :\"1309000576240410627\", \"text\":\"Hello\"}";
		String status2 = "{\"createdAt\" : \"Thu Sep 24 14:31:16 PDT 2020\", \"id\" :\"1309244033835835392\", \"text\":\"Hi\"}";
		TwitterFactory mockfac = Mockito.mock(TwitterFactory.class);
		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockfac);
		
		Twitter mockTwit = mock(Twitter.class);
		when(mockTwit.getUserTimeline()).thenReturn(userTimelineResponseList(Arrays.asList(status1, status2)));
		
		when(request.getRequestDispatcher("gettimeline.jsp")).thenReturn(reqdisp);
		
		new GetUserTimeline(mockTwit).doPost(request, response);
		
		verify(reqdisp).include(request, response);
		verify(response, times(1)).setHeader("tweetStr", "Hello 1309000576240410627\nHi 1309244033835835392\n");
	}
	
	@Test
	public void testGetHomeTimeline() throws Exception{
		String status1 = "{\"createdAt\" : \"Wed Sep 23 22:23:52 PDT 2020\", \"id\" :\"1309000576240410627\", \"text\":\"Hello\"}";
		String status2 = "{\"createdAt\" : \"Thu Sep 24 14:31:16 PDT 2020\", \"id\" :\"1309244033835835392\", \"text\":\"Hi\"}";
		String status3 = "{\"createdAt\" : \"Thu Sep 24 15:25:00 PDT 2020\", \"id\" :\"1309257555475005440\", \"text\":\"United Airlines will begin offering COVID-19 rapid testing for passengers flying from SFO to Hawaii next month. https://t.co/GFlD2FRRpS\"}";
		TwitterFactory mockfac = Mockito.mock(TwitterFactory.class);
		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockfac);
		
		Twitter mockTwit = mock(Twitter.class);
		when(mockTwit.getHomeTimeline()).thenReturn(userTimelineResponseList(Arrays.asList(status1, status2, status3)));
		
		when(request.getRequestDispatcher("gettimeline.jsp")).thenReturn(reqdisp);
		
		new GetHomeTimeline(mockTwit).doPost(request, response);
		
		verify(reqdisp).include(request, response);
		verify(response, times(1)).setHeader("tweetStr", "Hello 1309000576240410627\nHi 1309244033835835392\nUnited Airlines will begin offering COVID-19 rapid testing for passengers flying from SFO to Hawaii next month. https://t.co/GFlD2FRRpS 1309257555475005440\n");
	}
	
	//method to for list of statuses on timeline
	private static ResponseList<Status> userTimelineResponseList(List<String> stats) throws Exception{
		final List<Status> statlist = new ArrayList();
		for(String s : stats) {
			statlist.add(TwitterObjectFactory.createStatus(s));
		}
				
		ResponseList<Status> resplist = EasyMock.createMock(ResponseList.class);
		EasyMock.expect(resplist.iterator()).andDelegateTo(statlist).anyTimes();
		EasyMock.replay(resplist);
		return resplist;
	}

		// Test for get favourite tweets
		@Test
		public void testGetFavTweets() throws Exception{
			String status1 = "{\"createdAt\" : \"Wed Sep 23 22:23:52 PDT 2020\", \"id\" :\"1309000576240410627\", \"text\":\"Hello\"}";
			String status2 = "{\"createdAt\" : \"Thu Sep 24 14:31:16 PDT 2020\", \"id\" :\"1309244033835835392\", \"text\":\"Hi\"}";
			String status3 = "{\"createdAt\" : \"Thu Sep 24 15:25:00 PDT 2020\", \"id\" :\"1309257555475005440\", \"text\":\"United Airlines will begin offering COVID-19 rapid testing for passengers flying from SFO to Hawaii next month. https://t.co/GFlD2FRRpS\"}";
			TwitterFactory mockfac = Mockito.mock(TwitterFactory.class);
			PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockfac);

			Twitter mockTwit = mock(Twitter.class);
			when(mockTwit.getFavorites()).thenReturn(userTimelineResponseList(Arrays.asList(status1, status2, status3)));

			when(request.getRequestDispatcher("getFavTweets.jsp")).thenReturn(reqdisp);

			new GetFavTweets(mockTwit).doPost(request, response);

			verify(reqdisp).include(request, response);
			verify(response, times(1)).setHeader("tweetStr", "Hello 1309000576240410627\nHi 1309244033835835392\nUnited Airlines will begin offering COVID-19 rapid testing for passengers flying from SFO to Hawaii next month. https://t.co/GFlD2FRRpS 1309257555475005440\n");
		}

}

