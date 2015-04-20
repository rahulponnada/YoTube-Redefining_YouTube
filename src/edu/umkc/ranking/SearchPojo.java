package edu.umkc.ranking;

import java.util.ArrayList;

public class SearchPojo {
	
	public ArrayList<String> topics;
	public ArrayList<String> topicCategory;
	public ArrayList<VideoInfo> videos;
	
	public SearchPojo(){
		topics = new ArrayList<String>();
		topicCategory = new ArrayList<String>();
		videos = new ArrayList<VideoInfo>();
	}
	public ArrayList<String> getTopicsCategory() {
		return topicCategory;
	}
	public void setTopicsCategory(ArrayList<String> topicsCategory) {
		this.topicCategory = topicsCategory;
	}
	public ArrayList<String> getTopics() {
		return topics;
	}
	public void setTopics(ArrayList<String> topics) {
		this.topics = topics;
	}
	public ArrayList<VideoInfo> getVideos() {
		return videos;
	}
	public void setVideos(ArrayList<VideoInfo> videos) {
		this.videos = videos;
	}
			

}
