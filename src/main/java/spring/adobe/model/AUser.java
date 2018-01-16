package spring.adobe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import spring.adobe.GlobalProperties;
import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@NamedStoredProcedureQuery (
		name = "addAUser_md5", 
		procedureName = "adept_userEntry", 
		parameters = {
			@StoredProcedureParameter(name = "password", mode = ParameterMode.IN, type = String.class),
			@StoredProcedureParameter(name = "username", mode = ParameterMode.IN, type = String.class),
			@StoredProcedureParameter(name = "uuid", mode = ParameterMode.IN, type = String.class), 
			@StoredProcedureParameter(name = "rcount", mode = ParameterMode.OUT, type = Integer.class), 
		}
)

public class AUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private String uuid;

	public long getId() {
		return id;
	}
	
    public void setId(long id)
    {
        this.id = id;
    }
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
//	public void setup() {
//        this.setUuid();
//	}
}
