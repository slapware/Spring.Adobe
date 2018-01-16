package spring.adobe;

//import java.awt.PageAttributes.MediaType;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.adobe.model.SignInLogin;


// AES imports
import java.util.Base64;

@Controller
@RestController
public class RegisterController {
	  private String IV;
	  @Autowired
	  private AdeptParse pAdobe;
	  @Autowired
	  private SignIn signIn;
	  @Autowired
	  private GlobalProperties gProp;
	  @Autowired
	  private SlapCrypt sCrypt;
	  
public String getIV() {
		return IV;
	}

	public void setIV(String iV) {
		IV = iV;
	}

@ResponseBody @RequestMapping(value = "/register",
            method = RequestMethod.POST)
public String samplePostBody(@RequestBody String request) throws IOException {
	String rawData = "na";
	this.setIV(gProp.getCipher());
    byte[] cipher = Base64.getDecoder().decode(new String(request).getBytes("UTF-8"));
    	
	try {
		rawData = sCrypt.decrypt(cipher, this.getIV(), this.getIV());
		pAdobe.setRawXml(rawData);
		pAdobe.ParseData();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rawData;
   }	

@ResponseBody @RequestMapping(value = "/test",
method = RequestMethod.POST)
public String testPostBody(@RequestBody String request) throws IOException {
    	
	try {
		pAdobe.setRawXml(request);
		pAdobe.ParseData();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return request;
   }	

@ResponseBody @RequestMapping(value = "/signin",
method = RequestMethod.POST,
headers = {"Accept=application/vnd.adobe.adept+xml","application/xml"})
public String InPostBody(@RequestBody SignInLogin signInLogin, @RequestHeader(value="Content-type") String userContent) throws IOException {
    	String reqHeader = "application/vnd.adobe.adept+xml";
    	
    	if(!reqHeader.equalsIgnoreCase(userContent)) {
    		// return wrong content-type provided
    		String error = "<errpr xmlns=\"http://ns.adobe.com/adept\" data=\"E_HCVID_AUTH bad content-type\" />";
    		return error;
    	}
    	else {
    		try {
    			signIn.CheckUser(signInLogin.getMyuser(), signInLogin.getMypass());
    			} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
	return reqHeader;
   }	

}
