package edu.umkc.test;

import edu.umkc.ranking.Login;
import edu.umkc.ranking.Search;
import edu.umkc.ranking.SearchPojo;
import edu.umkc.ranking.VideoInfo;

public class TestSearch {

	public static void main(String[] args) {
		
		SearchPojo searchList = new SearchPojo();
		Search search = new Search();
		search.searchQuery(searchList,"apple iphone");
	   // System.out.println(searchList.topics.get(0));
	   // System.out.println(searchList.topics.get(5));
	   // System.out.println(searchList.topics.get(12));
	    //for(int i=0;i<=searchList.topics.size();i++){
	    //System.out.println(searchList.topics.get(i)+" ("+searchList.topicCategory.get(i)+")");
//	    	System.out.println(searchList.topicCategory.get(i));
	    //}
		for(int i=0;i<10;i++){
		VideoInfo sample = searchList.getVideos().get(i);	    
		}
	   // System.out.println(searchList.topicCategory.get(0));
	   // System.out.println(searchList.topicCategory.get(5));
	  //  System.out.println(searchList.topicCategory.get(12));
	  //  System.out.println(searchList.videos.get(0).getTitle());
		//Login login = new Login("anvesh525@gmail.com", "qwerty");

	}

}
