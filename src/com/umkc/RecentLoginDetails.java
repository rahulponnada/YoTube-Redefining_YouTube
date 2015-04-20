package com.umkc;

public class RecentLoginDetails {
	private String name ;
	private String emailID;
	private String Logintime;
	
	public RecentLoginDetails(String name, String emailID, String Logintime )
	{
		this.name = name ;
		this.emailID = emailID;
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
	public String getLogintime()
	{
		return Logintime;
	}
	
	public void printDeatils(){
		System.out.println( "name: " +name + " emailID: "+ emailID + " Logintime: " + Logintime );
	}
}
