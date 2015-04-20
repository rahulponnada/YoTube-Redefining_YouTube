package com.ibm.cloudoe.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umkc.ranking.Search;
import edu.umkc.ranking.SearchPojo;
import edu.umkc.test.TestSearch;


public class HelloResource extends HttpServlet{
	
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("Hello! World!");
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
	
	    response.setContentType("text/html;charset=UTF-8");
	    System.out.println("hello");
	    SearchPojo searchList = new SearchPojo();
	    (new Search()).searchQuery(searchList,"apple iphone");
	    request.setAttribute("topics", searchList.getTopics());
	    request.setAttribute("topicCategory", searchList.getTopicsCategory());
	    for(int i=0;i<30;i++){
	    	request.setAttribute("videoInfo"+i, searchList.getVideos().get(i));	
	    }	    
	    request.getRequestDispatcher("/home.jsp").forward(request, response);
	    
	}
	}