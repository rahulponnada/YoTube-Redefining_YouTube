package edu.umkc.ranking;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.umkc.db.MongoDB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Login {
	private static MongoDB dbConnection;
	private static DBCollection LoginTable;
	private static String emailID;
	private static String pswd = "";
	private static String result = "";
	
	public Login(String emailID, String pswd) {
		this.emailID = emailID;
		this.pswd = pswd;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		 emailID = "rahul.ponnada@gmail.com";
		pswd = "rahul@umkc";
	
	BasicDBObject searchusername = new BasicDBObject();
	dbConnection = new MongoDB();
	LoginTable = dbConnection.getDb().getCollection("LoginDetails");
	searchusername.put("emailID", emailID);
	
	DBCursor cursor = LoginTable.find(searchusername);
	while (cursor.hasNext()) {
		System.out.println(cursor.next());
	}
	
	if(cursor.count()== 0){
		result ="invalid username";
		System.out.println(result);
	}
	else
	{
		BasicDBObject password = new BasicDBObject();
		password.put("password", pswd);
		DBObject userobject = cursor.curr();
		
		System.out.println(userobject.get("password").toString());
		
	if( userobject.get("password").toString().equals(pswd)) {
			result ="sucessfull login";
			System.out.println(result);
			BasicDBObject queryuser = new BasicDBObject();
			queryuser.put("emailID", emailID);
			
			BasicDBObject updatelogintime = new BasicDBObject();
			
			updatelogintime.append("$set", new BasicDBObject().append("lastLogintime",  dateFormat.format(date)));
			LoginTable.update(queryuser , updatelogintime);
			
		}
		else
		{
			result ="invalid password";
			System.out.println(result);
		}
			
	}
	
/* DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	BasicDBObject doc = new BasicDBObject();
	doc.put("name", "Sumanth Gunda");
	doc.put("emailID", "sumanth@gmail.com");
	doc.put("password", "sumanth@umkc");
	doc.put("lastLogintime", dateFormat.format(date));
	dbConnection = new MongoDB();
	LoginTable = dbConnection.getDb().getCollection("LoginDetails");
	//dbConnection.delete(LoginTable);
	dbConnection.insert(doc, LoginTable); 
	
	doc = new BasicDBObject();
	doc.put("name", "Rajesh Jonnalagadda");
	doc.put("emailID", "rajeshjonnala@gmail.com");
	doc.put("password", "rajesh@umkc");
	doc.put("lastLogintime", dateFormat.format(date));
	dbConnection = new MongoDB();
	LoginTable = dbConnection.getDb().getCollection("LoginDetails");
	dbConnection.insert(doc, LoginTable); 
	
	doc = new BasicDBObject();
	doc.put("name", "Karthik Gaddipati");
	doc.put("emailID", "karthik@gmail.com");
	doc.put("password", "karthik@umkc");
	doc.put("lastLogintime", dateFormat.format(date));
	dbConnection = new MongoDB();
	LoginTable = dbConnection.getDb().getCollection("LoginDetails");
	dbConnection.insert(doc, LoginTable); 
	*/
	
	dbConnection.fetch("emailID", LoginTable);
	}
}
