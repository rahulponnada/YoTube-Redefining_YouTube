package com.umkc;

public class RecentLoginDetails {
	private String name ;
	private String emailID;
	private int noOfLogins;
	private String Logintime;
	
	public RecentLoginDetails(String name, String emailID, int noOfLogins, String Logintime )
	{
		this.name = name ;
		this.emailID = emailID;
		this.noOfLogins = noOfLogins;
		this.Logintime = Logintime;
	}
	public String getName()
	{
		return name;
	}
	
	public String getEmailID()
	{
		return emailID;
	}
	
	public int getNoOfLogins()
	{
		return noOfLogins;
	}
	
	public String getLogintime()
	{
		return Logintime;
	}
	
	public void printDeatils(){
		System.out.println( "name: " +name + " emailID: "+ emailID + " Logintime: " + Logintime );
	}
}
