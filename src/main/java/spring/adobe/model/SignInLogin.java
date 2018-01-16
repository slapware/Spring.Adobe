package spring.adobe.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;

@Service
@XmlRootElement (name = "signInResponce")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SignInLogin implements Serializable {
	private static final long serialVersionUID = 5088863992478607917L;
	@XmlElement
	private String myuser;
	@XmlElement
	private String mypass;
	
	public String getMyuser() {
		return myuser;
	}
	public void setMyuser(String myuser) {
		this.myuser = myuser;
	}
	public String getMypass() {
		return mypass;
	}
	public void setMypass(String mypass) {
		this.mypass = mypass;
	}
	public static long getSerialversionuid() {
		 return serialVersionUID;
		 }

}
