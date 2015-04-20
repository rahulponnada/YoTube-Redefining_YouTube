package edu.umkc.ranking;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.GeoPoint;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import edu.umkc.db.MongoDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Print a list of videos matching a search term.
 **/
public class Search {

	private MongoDB dbConnection;
	private DBCollection searchTable;
	private DBCollection userHistoryTable;
	ArrayList<VideoInfo> videoListInfo = null;
	public ArrayList<String> topics = null;
	public ArrayList<String> topicCategory = null;
	//public SearchPojo searchList = new SearchPojo();
	/**
	 * Define a global variable that identifies the name of a file that contains
	 * the developer's API key.
	 */
	private static final String PROPERTIES_FILENAME = "youtube.properties";
	private static final long NUMBER_OF_VIDEOS_RETURNED = 30;
	 /**
     * Define a global variable that specifies the maximum numbers of topics
     * that an API response can contain.
     */
    private static final long NUMBER_OF_TOPICS_RETURNED = 20;

	/**
	 * Define a global instance of a Youtube object, which will be used to make
	 * YouTube Data API requests.
	 */
	private static YouTube youtube;

	/**
	 * Initialize a YouTube object to search for videos on YouTube. Then display
	 * the name and thumbnail image of each video in the result set.
	 *
	 * @param args
	 *            command line args.
	 */
	public void searchQuery(SearchPojo searchList,String query) {
		// Read the developer's key from the properties file.
		Properties properties = new Properties();
		
		dbConnection = new MongoDB();
		dbConnection.delete(dbConnection.getDb().getCollection("searchDetails"));
		searchTable = dbConnection.getDb().getCollection("searchDetails");
		userHistoryTable = dbConnection.getDb().getCollection("users");
		//topicCategory = null;
		try {
			InputStream in = Search.class
					.getResourceAsStream(PROPERTIES_FILENAME);
			properties.load(in);
		} catch (IOException e) {
			System.err.println("There was an error reading "
					+ PROPERTIES_FILENAME + ": " + e.getCause() + " : "
					+ e.getMessage());
			System.exit(1);
		}

		try {
			// This object is used to make YouTube Data API requests. The last
			// argument is required, but since we don't need anything
			// initialized when the HttpRequest is initialized, we override
			// the interface and provide a no-op function.
			youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT,
					Auth.JSON_FACTORY, new HttpRequestInitializer() {
						public void initialize(HttpRequest request)
								throws IOException {
						}
					}).setApplicationName("youtube-cmdline-search-sample")
					.build();

			// Prompt the user to enter a query term.
			String queryTerm = query; 
					//getInputQuery();
					
			ArrayNode freebaseResults = getTopicArray(queryTerm);
			topics = searchList.getTopics();
			topicCategory = searchList.getTopicsCategory();
			 for (int i = 0; i < freebaseResults.size(); i++) {
		            JsonNode node = freebaseResults.get(i);
		            String topicName = node.get("name").asText();
		            System.out.print(" " + i + " = " + topicName +"      \n");
		            topics.add(topicName);
		            
		            if (node.get("notable") != null) {
		                System.out.print(" (" + node.get("notable").get("name").asText() + ")");
		                topicCategory.add(node.get("notable").get("name").asText());
		            }else{
		            topicCategory.add("");
		            }
		            System.out.println("");
		        }
			
			// Insert userID along with the details of the system
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//get current date time with Date()
			Date date = new Date();
			BasicDBObject doc = new BasicDBObject();
			doc.put("userid", "1");
			doc.put("time", dateFormat.format(date));
			doc.put("searchterm", queryTerm);
			dbConnection.insert(doc, userHistoryTable);
			
			// Define the API request for retrieving search results.
			YouTube.Search.List search = youtube.search().list("id,snippet");

			// Set your developer key from the {{ Google Cloud Console }} for
			// non-authenticated requests. See:
			// {{ https://cloud.google.com/console }}
			String apiKey = properties.getProperty("youtube.apikey");
			search.setKey(apiKey);
			search.setQ(queryTerm);

			System.out.println("API" + apiKey);

			// Restrict the search results to only include videos. See:
			// https://developers.google.com/youtube/v3/docs/search/list#type
			search.setType("video");

			// To increase efficiency, only retrieve the fields that the
			// application uses.
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

			// Call the API and print results.
			SearchListResponse searchResponse = search.execute();
			List<SearchResult> searchResultList = searchResponse.getItems();
			if (searchResultList != null) {
				prettyPrint(searchResultList.iterator(), queryTerm);
			}
			System.out.println("After Prettyprint");
			BasicDBObject document = new BasicDBObject();
			int rank = 0;
			document.put("viewpercent", -1);
			document.put("like/viewCount", -1);
			document.put("dislike/likeCount", 1);
			document.put("commentcount", -1);
			
			videoListInfo = searchList.getVideos();
			DBCursor cursor = searchTable.find().sort(document).limit(30);
			DBObject userObj = null;
			while (cursor.hasNext()) {
				VideoInfo vidInfo = new VideoInfo();
				++rank;
				userObj = cursor.next();
				vidInfo.setRanking(rank);
				System.out.println(rank);
				System.out.println(vidInfo.getRanking());
				vidInfo.setVideoId(userObj.get("videoid").toString());
				vidInfo.setTitle(userObj.get("title").toString());
				vidInfo.setThumbnail(userObj.get("thumbnail").toString());
				vidInfo.setViewCount(userObj.get("viewcount").toString());
				vidInfo.setLikeCount(userObj.get("likecount").toString());
				vidInfo.setDislikeCount(userObj.get("dislikecount").toString());
				vidInfo.setCommentCount(userObj.get("commentcount").toString());
				vidInfo.setLikeCount(userObj.get("like/viewCount").toString());
				vidInfo.setDislikeCount(userObj.get("dislike/likeCount").toString());
				vidInfo.setViewpercent(userObj.get("viewpercent").toString());
				videoListInfo.add(vidInfo);
				//System.out.println(videoListInfo.
				//System.out.println(cursor.next());
			}
			
			/* BasicDBObject document = new BasicDBObject();
			
			DBCursor top10Cursor = cursor.sort(new BasicDBObject("viewcount", -1)).limit(10);
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			*/			
			//searchList.setTopics(topics);
			//searchList.setTopicsCategory(topicCategory);
			//searchList.setVideos(videoListInfo);
			
		} catch (GoogleJsonResponseException e) {
			System.err.println("There was a service error: "
					+ e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : "
					+ e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	}

	/*
	 * Prompt the user to enter a query term and return the user-specified term.
	 */
	private String getInputQuery() throws IOException {

		String inputQuery = "";

		System.out.print("Please enter a search term: ");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		inputQuery = bReader.readLine();

		if (inputQuery.length() < 1) {
			// Use the string "YouTube Developers Live" as a default.
			inputQuery = "YouTube Developers Live";
		}
		return inputQuery;
	}

	/*
	 * Prints out all results in the Iterator. For each result, print the title,
	 * video ID, and thumbnail.
	 * 
	 * @param iteratorSearchResults Iterator of SearchResults to print
	 * 
	 * @param query Search query (String)
	 */
	private void prettyPrint(Iterator<SearchResult> iteratorSearchResults,
			String query) throws IOException {
		ArrayList<VideoInfo> videosInfo = null;
		System.out
				.println("\n=============================================================");
		System.out.println("   First " + NUMBER_OF_VIDEOS_RETURNED
				+ " videos for search on \"" + query + "\".");
		System.out
				.println("=============================================================\n");

		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();
			
			// Confirm that the result represents a video. Otherwise, the
			// item will not contain a video ID.

			if (rId.getKind().equals("youtube#video")) {
				Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails()
						.getDefault();

				System.out.println(" Video Id" + rId.getVideoId());
				Properties properties = new Properties();
				try {
					InputStream in = Search.class
							.getResourceAsStream(PROPERTIES_FILENAME);
					properties.load(in);

				} catch (IOException e) {
					System.err.println("There was an error reading "
							+ PROPERTIES_FILENAME + ": " + e.getCause() + " : "
							+ e.getMessage());
					System.exit(1);
				}

				YouTube.Videos.List listVideosRequest = youtube.videos()
						.list("snippet, recordingDetails, statistics, player")
						.setId(rId.getVideoId());
				String apiKey = properties.getProperty("youtube.apikey");

				listVideosRequest.setKey(apiKey);
				VideoListResponse listResponse = listVideosRequest.execute();

				List<Video> videoList = listResponse.getItems();

				if (videoList != null) {
					 prettyPrint2(videoList.iterator(), query);
					// Video searching from mongoDB
					// System.out.println("VideoID: ");

					// dbConnection.fetch(query, searchTable);
				} else
					System.out
							.println("No Details of Statistics for this video");

			}
		}
	}
	
	
	private static ArrayNode getTopicArray(String topicQuery) throws IOException {

        // The application will return an empty string if no matching topic ID
        // is found or no results are available.
        String topicsId = "";
        ArrayNode arrayNodeResults = null;
        
	 HttpClient httpclient = new DefaultHttpClient();
     List<NameValuePair> params = new ArrayList<NameValuePair>();
     params.add(new BasicNameValuePair("query", topicQuery));
     params.add(new BasicNameValuePair("limit", Long.toString(NUMBER_OF_TOPICS_RETURNED)));

     String serviceURL = "https://www.googleapis.com/freebase/v1/search";
     String url = serviceURL + "?" + URLEncodedUtils.format(params, "UTF-8");

     HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
     HttpEntity entity = httpResponse.getEntity();

     if (entity != null) {
         InputStream instream = entity.getContent();
         try {

             // Convert the JSON to an object. This code does not do an
             // exact map from JSON to POJO (Plain Old Java object), but
             // you could create additional classes and use them with the
             // mapper.readValue() function to get that exact mapping.
             ObjectMapper mapper = new ObjectMapper();
             JsonNode rootNode = mapper.readValue(instream, JsonNode.class);

             // Confirm that the HTTP request was handled successfully by
             // checking the API response's HTTP response code.
             if (rootNode.get("status").asText().equals("200 OK")) {
                 // In the API response, the "result" field contains the
                 // list of needed results.
                  arrayNodeResults = (ArrayNode) rootNode.get("result");

             }
         } finally {
             instream.close();
         }
     }
     return arrayNodeResults;
 }

	private void prettyPrint2(Iterator<Video> iteratorVideoResults, String query) {
		ArrayList<VideoInfo> videoList = new ArrayList<VideoInfo>();
		SearchPojo searchInfo = new SearchPojo();
		if (!iteratorVideoResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		while (iteratorVideoResults.hasNext()) {
			
			Video singleVideo = iteratorVideoResults.next();
			Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getMedium();
			BasicDBObject document = new BasicDBObject();
		
			document.put("videoid", singleVideo.getId());
			document.put("title", singleVideo.getSnippet().getTitle());
			document.put("thumbnail", thumbnail.getUrl());
			document.put("EmbedHtml", singleVideo.getPlayer().getEmbedHtml());
			
			long viewCount = singleVideo.getStatistics().getViewCount().longValue();
			document.put("viewcount", viewCount );
			
			long likeCount = singleVideo.getStatistics().getLikeCount().longValue();
			document.put("likecount", likeCount);
			
			long disLikeCount = singleVideo.getStatistics().getDislikeCount().longValue();
			document.put("dislikecount",disLikeCount);
			
			long commentCount = singleVideo.getStatistics().getCommentCount().longValue();
			document.put("commentcount", commentCount);
			
			long likebyviewCount = (likeCount*10000)/viewCount;
			
			int temp = 1; 
			for(int i = (String.valueOf(likebyviewCount).length()); i>1 ; i--)
			{
				temp = temp *10;
			}
			document.put("like/viewCount", (round(likebyviewCount / temp)) * temp );
			
			long dislikebylikeCount;
			if(likeCount ==0)
			{
				dislikebylikeCount = 1000;
			}
			else 
			{
				dislikebylikeCount = (disLikeCount *1000)/likeCount;
			}
			
			temp = 1; 
			for(int i = (String.valueOf(dislikebylikeCount).length()); i>1 ; i--)
			{
				temp = temp *10;
			}
			
			document.put("dislike/likeCount", (round(dislikebylikeCount / temp)) * temp);
			
			long viewpercents = viewCount/1000;
			 temp = 1; 
			for(int i = (String.valueOf(viewpercents).length()); i>1 ; i--)
			{
				temp = temp *10;
			}
			document.put("viewpercent", (round((viewpercents) / temp)) * temp );
			
			dbConnection.insert(document, searchTable);
			System.out
					.println("\n-------------------------------------------------------------\n");
			System.out
					.println(" Title: " + singleVideo.getSnippet().getTitle());
			System.out.println(" Video Id" + singleVideo.getId());
			System.out.println(" Thumbnail: " + thumbnail.getUrl());
			System.out.println("ViewCount"
					+ singleVideo.getStatistics().getViewCount());
			System.out.println("Like Count"
					+ singleVideo.getStatistics().getLikeCount());
			System.out.println("DisLike Count"
					+ singleVideo.getStatistics().getDislikeCount());
			System.out.println("Comment Count"
					+ singleVideo.getStatistics().getCommentCount());
			System.out.println("URL: " + singleVideo.getPlayer().getEmbedHtml());
			
			System.out.println("Like/ViewCount: "
					+ likebyviewCount);
			System.out.println("DisLike/LikePercentage: "
					+ dislikebylikeCount);
		}
	}
	
	long round(long n)
	{
		return (n+4)/5 *5;
	}

}