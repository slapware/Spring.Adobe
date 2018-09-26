package spring.adobe.model;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Service;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
//@Service
@XmlType(name = "", namespace="http://ns.adobe.com/adept", propOrder = {
		"myuser",
		"mypass"
})
@XmlRootElement(name = "signInRequest")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SignInLogin implements Serializable {
//	@XmlElement
	private String myuser;
//	@XmlElement
	private String mypass;
	
	public String getMyuser() {
		return this.myuser;
	}
	public void setMyuser(String myuser) {
		this.myuser = myuser;
	}
	public String getMypass() {
		return this.mypass;
	}
	public void setMypass(String mypass) {
		this.mypass = mypass;
	}

}
