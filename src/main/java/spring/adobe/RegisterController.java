package spring.adobe;

//import java.awt.PageAttributes.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import spring.adobe.model.SignInLogin;


// AES imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.Base64;

@Controller
@RestController
public class RegisterController {
      private static final Logger LOGGER = LoggerFactory.getLogger(AdeptParse.class);
            
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

@RequestMapping(value = "/signin",
		method = RequestMethod.POST)
@ResponseBody
	public String InPostBody(@RequestBody String request, @RequestHeader(value="Content-type") String userContent) throws IOException {
		String reqHeader = "application/vnd.adobe.adept+xml";

		if(!reqHeader.equalsIgnoreCase(userContent)) {
			// return wrong content-type provided
			String error = "<errpr xmlns=\"http://ns.adobe.com/adept\" data=\"E_HCVID_AUTH bad content-type\" />";
			return error;
		}
		else {
			try {
                Document xmlDocument = this.parseXmlFromString(request);
                String expression = "//username";
                try {
                    XPath xPath =  XPathFactory.newInstance().newXPath();
                    NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
                    String user = (nodeList.item(0).getFirstChild().getNodeValue());
                    expression = "//password";
                    nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
                    String pswd = (nodeList.item(0).getFirstChild().getNodeValue());
				String retMessage = signIn.CheckUser(user, pswd);
				return retMessage;
                } catch (XPathExpressionException e) {
                    String theError = e.getMessage();
                }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reqHeader;
	}

//	@ResponseBody @RequestMapping(value = "/SignIn",
//			method = RequestMethod.POST,
//			headers = {"Accept=application/vnd.adobe.adept+xml","application/xml"})
//	public String InPostBody(@RequestBody SignInLogin signInLogin, @RequestHeader(value="Content-type") String userContent) throws IOException {
//		String reqHeader = "application/vnd.adobe.adept+xml";
//
//		if(!reqHeader.equalsIgnoreCase(userContent)) {
//			// return wrong content-type provided
//			String error = "<errpr xmlns=\"http://ns.adobe.com/adept\" data=\"E_HCVID_AUTH bad content-type\" />";
//			return error;
//		}
//		else {
//			try {
//				signIn.CheckUser(signInLogin.getMyuser(), signInLogin.getMypass());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return reqHeader;
//	}

    public Document parseXmlFromString(String xmlString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
            org.w3c.dom.Document document;
            document = builder.parse(inputStream);
            return document;
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            String theError = e.getMessage();
            LOGGER.error("parse XML file encountered an error with value - {}", theError);
        }
        catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            String theError = e.getMessage();
            LOGGER.error("parse XML file encountered an error with value - {}", theError);
        }
        Document document = null;
        return document;
    } // parseXmlFromString

}
