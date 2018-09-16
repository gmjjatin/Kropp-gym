package entity;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserDetailsEntity {
	@Index
	@Id
	public String email;
	@Index
	public String name;
	@Index
	public String phone;
	public Date date;
	public UserDetailsEntity(){
		
	}
	public UserDetailsEntity(String email,String name,String phone, Date date){
		this.email=email;
		this.name=name;
		this.phone=phone;
		this.date=date;
	}

}

