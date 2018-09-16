package services.mail;
import java.io.IOException;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MailServices extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Logger log=Logger.getLogger(MailServices.class.getCanonicalName());
	public static String fromAddress="gmjjatin@gmail.com";

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
      
	  String toAddress=(String)request.getAttribute("email");
	  String body=(String)request.getAttribute("body");
	  String subject="Your Gym's One Day VIP pass";
	  //send mail
	  sendWelcomeMail(toAddress, subject, body);
	  System.out.println("mail success");
	  //setting the status to ok for jsp to respond
	  
	  request.setAttribute("status","ok");
	  
	  
		//send to page below
		RequestDispatcher rd =request.getRequestDispatcher("/demo-gym-contact-us.jsp");
		
		rd.forward(request, response);

    
    
  }
  void sendWelcomeMail(String toAddress,String mailSubject,String mailBody) {
	  Properties props=new Properties();
	  Session session=Session.getDefaultInstance(props,null);
	  try {
		  	Message msg= new MimeMessage(session);		
		  	msg.setFrom(new InternetAddress(fromAddress));
		  	InternetAddress to=new InternetAddress(toAddress);
		  	msg.addRecipient(Message.RecipientType.TO, to);
		  	msg.setSubject(mailSubject);
		  	msg.setText(mailBody);
		  	Transport.send(msg);
		  	
	} catch (AddressException addressException) {
	}catch (MessagingException messagingException) {
	}
	  
  }
}