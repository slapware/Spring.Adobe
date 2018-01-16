package spring.adobe;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GlobalProperties {
	private String vendorid;
	private String cipher;
	
	public GlobalProperties() {
	}
	
    public void readConfig() {
    	ResourceBundle bundle = ResourceBundle.getBundle("global"); //creates bundle from file `config.properties`
    	vendorid = bundle.getString("vendorid");  //gets the value for the key `mykey`
    	cipher = bundle.getString("cipher");  //gets the value for the key `mykey`
}

	public String getVendorid() {
		return vendorid;
	}
	public String getCipher() {
		return cipher;
	}
	
    @PostConstruct
    public void init() {
		readConfig();
    }
}
