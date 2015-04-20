
import java.io.*;
import java.net.*;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class SendMail
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

    	final String err = "/welcome.jsp";
    	final String succ = "/ForgotPassword.jsp";
    	final MongoDB dbConnection;
    	final DBCollection LoginTable;
    	//private static String emailID;
    	String pswd = "";
    	String result = "";
    	
    	//String from = request.getParameter("email");
    	String to = request.getParameter("email");
    	//String subject = request.getParameter("subject");
    	//String message = request.getParameter("message");
    	//String login = request.getParameter("login");
    	//String password = request.getParameter("password");

    	try {
    		
    		BasicDBObject searchusername = new BasicDBObject();
    		dbConnection = new MongoDB();
    		LoginTable = dbConnection.getDb().getCollection("LoginDetails");
    		searchusername.put("emailID", to);
    		
    		DBCursor cursor = LoginTable.find(searchusername);

    		if(cursor.count()== 0){
    			result ="error";
    			System.out.println(result);
    			//request.setAttribute("message", "error");
        		//request.getRequestDispatcher("/ForgotPassword.jsp?message=error").forward(request, response);
    		}
    		else
    		{
    			DBObject userobject = cursor.next();
    			System.out.println(userobject.toString());
    			pswd = userobject.get("password").toString();
    			
    			System.out.println(result);
    	
    	
    		Properties props = new Properties();
    		props.setProperty("mail.host", "smtp.gmail.com");
    		props.setProperty("mail.smtp.port", "587");
    		props.setProperty("mail.smtp.auth", "true");
    		props.setProperty("mail.smtp.starttls.enable", "true");
    		props.setProperty("mail.smtp.starttls.required", "true"); 
    		props.setProperty("mail.smtp.EnableSSL.enable","true");
    		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
    		props.setProperty("mail.smtp.socketFactory.fallback", "false");   
    		//props.setProperty("mail.smtp.port", "465");   
    		props.setProperty("mail.smtp.socketFactory.port", "465"); 


    		Authenticator auth = new SMTPAuthenticator("hackbattle123@gmail.com", "H@ck123ASE");

    		Session session = Session.getInstance(props, auth);

    		MimeMessage msg = new MimeMessage(session);
    		msg.setText("Your recovered password is "+pswd);
    		msg.setSubject("YoTube Password Recovery");
    		msg.setFrom(new InternetAddress("hackbattle123@gmail.com"));
    		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		Transport.send(msg);
    		result="success";
    		}

    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	//request.setAttribute("message","success");
    	request.getRequestDispatcher("/ForgotPassword.jsp?message="+result).forward(request, response);
    }
    private class SMTPAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\
		 processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}

}
