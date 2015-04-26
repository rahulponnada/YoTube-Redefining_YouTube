package edu.umkc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.umkc.ranking.Search;
import edu.umkc.ranking.SearchPojo;


public class FetchVideos extends HttpServlet{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("Hello! World!");
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		try{
	    response.setContentType("text/html;charset=UTF-8");
	    System.out.println("Fetch video servlet");
	    SearchPojo searchList = new SearchPojo();
	    System.out.println(request.getParameter("query"));
	    (new Search()).searchQuery(searchList,request.getParameter("query"));
	    request.setAttribute("topics", searchList.getTopics());
	    request.setAttribute("topicCategory", searchList.getTopicsCategory());
	    //if(!(searchList.getVideos().isEmpty())){
	    //for(int i=0;i<27;i++){
	    	//request.setAttribute("videoInfo"+i, searchList.getVideos().get(i));	
	   // }	    
	    //}
	    System.out.println("size of videoslist:"+ searchList.getVideos().size());
	    request.setAttribute("videoInfo",searchList.getVideos() );
	    
	    request.setAttribute("query", request.getParameter("query"));
	    request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		catch(ArrayIndexOutOfBoundsException e){
		System.out.println("Array out of bound Exception ");	
		}
		catch(Exception e){
		System.out.println("Exception in fetch videos");
		}
	}
}