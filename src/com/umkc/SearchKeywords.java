package com.umkc;

public class SearchKeywords {
	private String keyword ;
	private int count;
	
	
	public SearchKeywords(String keyword, int count)
	{
		this.keyword = keyword;
		this.count = count;
		
	}
	public String getKeyword()
	{
		return keyword;
	}
	
	public int getCount()
	{
		return count;
	}
	
	
	
	public void printDeatils(){
		System.out.println( "keyword: " +keyword + " count: "+ count );
	}
}
