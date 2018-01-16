package spring.adobe;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import spring.adobe.model.AUser;
import spring.adobe.model.UserRepository;

@Service
public class SignIn {
    @Autowired
	private UserRepository userRepository;

    public String CheckUser(String user, String pass) throws ParserConfigurationException {
    	String returnMessage = "";
    	
    	AUser auser = new AUser();
    	List<AUser> mysource = (ArrayList<AUser>) userRepository.findByLogin(user, pass);
    	if(mysource.isEmpty())
    	{
    		String error = "xmlns=\"http://ns.adobe.com/adept\" data=\"E_HCVID_AUTH";
    		// user not found
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            // root element
            Element root = document.createElement("signInResponce");
            document.appendChild(root);
            // data element
            Element myerror = document.createElement("error");
            myerror.appendChild(document.createTextNode(error));
            root.appendChild(myerror);
            // create the xml file
            //transform the DOM Object to an XML File
            returnMessage = convertDocumentToString(document);    		
    	} else {
    		// user found
    		auser = mysource.get(0);
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root element
        Element root = document.createElement("signInResponce");
        document.appendChild(root);
        // data element
        Element myuser = document.createElement("user");
        myuser.appendChild(document.createTextNode(auser.getUuid()));
        root.appendChild(myuser);
        // label element
        Element label = document.createElement("label");
        label.appendChild(document.createTextNode(auser.getUsername()));
        root.appendChild(label);
        // create the xml file
        //transform the DOM Object to an XML File
        returnMessage = convertDocumentToString(document);    		
    	}
    		return returnMessage;
    } // CheckUser
    
    private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
