
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
import com.umkc.SearchKeywords;


/**
 * Servlet implementation class RecentUsers
 */
@WebServlet("/FrequentSearchTerms")
public class FrequentSearchTerms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MongoDB dbConnection;
	private static DBCollection userHistoryTable;
	private static String result = "";
	private static ArrayList<SearchKeywords> yourList;
	private static BasicDBObject temp;
	private static SearchKeywords frequentsearches;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrequentSearchTerms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BasicDBObject searchLatestname = new BasicDBObject();
		searchLatestname.put("count", -1);		
		dbConnection = new MongoDB();
		userHistoryTable = dbConnection.getDb().getCollection("searcHistory");
				
		DBCursor cursor = userHistoryTable.find().sort(searchLatestname).limit(10);
		
		
		if(cursor.count()== 0){
			result ="no user deatils in DB";
			System.out.println(result);
		}
		
		else
		{
			
			yourList = new ArrayList<SearchKeywords>();
			while (cursor.hasNext()) {
				temp =  (BasicDBObject) cursor.next();
				String f1 = temp.get("searchterm").toString();
				int f2 = temp.getInt("count");		
				frequentsearches = new  SearchKeywords(f1,f2);	
				yourList.add(frequentsearches);
				
								
			}
			
			request.setAttribute("List",yourList);
			request.getRequestDispatcher("/frequentsearch.jsp").forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
