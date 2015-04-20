

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCursor;
import com.umkc.RecentLoginDetails;


/**
 * Servlet implementation class RecentUsers
 */
@WebServlet("/RecentUsers")
public class RecentUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MongoDB dbConnection;
	private static DBCollection LoginTable;
	private static String result = "";
	private static List<RecentLoginDetails> yourList;
	private static BasicDBObject temp;
	private static RecentLoginDetails detailsStruct;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecentUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World!Hello World!Hellow World!");
		BasicDBObject searchLatestname = new BasicDBObject();
		searchLatestname.put("lastLogintime", -1);
		dbConnection = new MongoDB();
		LoginTable = dbConnection.getDb().getCollection("LoginDetails");
				
		DBCursor cursor = LoginTable.find().sort(searchLatestname).limit(10);
		
		if(cursor.count()== 0){
			result ="no user deatils in DB";
			System.out.println(result);
		}
		
		else
		{
			
			yourList = new ArrayList<RecentLoginDetails>();
			while (cursor.hasNext()) {
				temp =  (BasicDBObject) cursor.next();
				String f1 = temp.get("name").toString();
				String f2 = temp.get("emailID").toString();
				String f3 = temp.get("lastLogintime").toString();			
				detailsStruct = new  RecentLoginDetails(f1,f2,f3);	
				yourList.add(detailsStruct);
								
			}
			
			request.setAttribute("List",yourList);
			System.out.println("Nasty Nasty NAsty Nasty NAsty");
			request.getRequestDispatcher("/recent.jsp").forward(request, response);
				
				//for(RecentLoginDetails tag:yourList){
					//tag.printDeatils();
				//}			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
