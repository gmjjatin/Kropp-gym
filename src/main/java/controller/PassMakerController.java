package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmailVerifierAndPassMaker;
import entity.UserDetailsEntity;

public class PassMakerController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String phone=req.getParameter("phone");
	
	EmailVerifierAndPassMaker obj=new EmailVerifierAndPassMaker();
	String status=obj.emailVerifierAndPassMaker(email,phone,name);
	if(status.equals("ok")) {
		//setting the status so that the jsp page can check and respond
		req.setAttribute("status","ok");
		
		
		//getting the user details
		UserDetailsEntity user=obj.user1;
		
		//setting the expiry date of passcode
		Date date=user.date;
		
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DATE,7);
			date=cal.getTime();
		
		//setting Body , recipient for mail services
		String body="VIP Passcode\n\nHello "+user.name+"\nWe are happy to notice your interest in us.Here is your exclusive one day VIP passcode: \u001B[1m"+user.name+"."+user.email+"."+user.phone+"\nEager to see you soon as the passcode is only valid uptill \u001B[1m"+date.toString()+".Show the passcode while entering at any of our branche.\nWith Regards\nYour Best Gym (Paschim Vihar ,Delhi)";
		
		req.setAttribute("body", body);
		req.setAttribute("email", email);
		
		//mailing services to send VIP pass
		RequestDispatcher rd1 =req.getRequestDispatcher("/MailServices");
		//sends control to mailservices
		rd1.forward(req, resp);
		
		
		
	}
	else {
		String date=obj.emailVerifierAndPassMaker(email,phone,name);
		req.setAttribute("status","notOk");
		req.setAttribute("date",date);
		RequestDispatcher rd =req.getRequestDispatcher("/demo-gym-contact-us.jsp");
		rd.forward(req, resp);
		
	}
	}
}
