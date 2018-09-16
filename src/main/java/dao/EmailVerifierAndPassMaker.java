package dao;

import entity.UserDetailsEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailVerifierAndPassMaker {
	//setting the user in request so as to make Body , recepient mail in Controller
	public UserDetailsEntity user1;

	public String emailVerifierAndPassMaker(String email,String phone  ,String name) {
		UserDetailsEntity user=services.OfyService.ofy().load().type(entity.UserDetailsEntity.class).id(email).now();
		if(user!=null) {
			 
			DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
			String strDate = dateFormat.format(user.date);

			return strDate;
		}
		else{
			//note date /time of dispatch of ViP pass to user 
			Date date=new Date();
			//register user, make database
			user=new UserDetailsEntity(email,name, phone,date);
			
			services.OfyService.ofy().save().entity(user).now();
			//loading the user in user1 var so that it can be accessed inn the controller
			user1=services.OfyService.ofy().load().type(entity.UserDetailsEntity.class).id(email).now();
			
		
			
			//return ok to indicate success in pass dispatch
			return "ok";
		}
	}

}
