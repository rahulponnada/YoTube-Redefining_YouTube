import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MongoDB dbConnection;
	private static DBCollection LoginTable;
	String result;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("stupidkdhskhsk");
		String emailID = request.getParameter("userName");
		String pswd = request.getParameter("userPass");
		System.out.println("Hello Worldmnbdshfsbj");
        //if(userName == null || "".equals(userName)){
          //  userName = "Guest";
        //}
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
	//emailID = "anvesh525@gmail.com";
		//pswd = "anvesh@umkc";
	
		BasicDBObject searchusername = new BasicDBObject();
		dbConnection = new MongoDB();
		LoginTable = dbConnection.getDb().getCollection("LoginDetails");
		searchusername.put("emailID", emailID);
	
		DBCursor cursor = LoginTable.find(searchusername);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	
	if(cursor.count()== 0){
		result ="username hjkhgjgfghfhgf";
		//System.out.println(result);
	}
	else
	{
		BasicDBObject password = new BasicDBObject();
		password.put("password", pswd);
		DBObject userobject = cursor.curr();
		
		System.out.println(userobject.get("password").toString());
		
		if( userobject.get("password").toString().equals(pswd)) {
			result ="success";
			System.out.println(result);
			BasicDBObject queryuser = new BasicDBObject();
			queryuser.put("emailID", emailID);
			
			int tempCount = (int) userobject.get("noOfLogins") ;
			tempCount++;
			BasicDBObject updatelogintimeCount = new BasicDBObject();
			
			updatelogintimeCount.append("$set", new BasicDBObject().append("lastLogintime",  dateFormat.format(date)));
			LoginTable.update(queryuser , updatelogintimeCount);
			
			updatelogintimeCount.append("$set", new BasicDBObject().append("noOfLogins", tempCount));
			
			LoginTable.update(queryuser , updatelogintimeCount);
						
		}
		else
		{
			result ="pass";
			System.out.println(result);
		}
			
	}
		dbConnection.fetch("emailID", LoginTable);
 
        response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8"); 
		System.out.println(result);
		response.getWriter().write(result);
		//System.out.println(result);
		//System.out.println("requwst"+request.getAttribute("username"));
        //request.getRequestDispatcher("/welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
