package services;

import com.googlecode.objectify.Objectify;

import com.googlecode.objectify.ObjectifyService;


public class OfyService {

	static{
		ObjectifyService.register(entity.UserDetailsEntity.class);
		//ObjectifyService.register(CourseDetailsEntity.class);
		
	}
	public  static Objectify ofy(){
		return ObjectifyService.ofy();
	}
}
