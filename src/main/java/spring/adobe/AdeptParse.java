package spring.adobe;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
//import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Transient;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
// xPath
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.springframework.transaction.annotation.Transactional;

import spring.adobe.model.AUser;
import spring.adobe.model.GuidUtil;
import spring.adobe.model.UserRepository;
//import spring.adobe.model.UserRepositoryCustom;
//import spring.adobe.model.UserRepositoryCustomImpl;
import spring.adobe.model.Source;
import spring.adobe.model.SourceRepository;
import spring.adobe.model.AOrder;
import spring.adobe.model.OrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdeptParse {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdeptParse.class);
	
    private String loginID;
    private String Password;
    private String customerLastName;
    private String customerFirstName;
    private String line1;
    private String name1;
    private String name2;
    private String phoneNumber;
    private String addressID;
    private String city;
    private String country;
    private String postalCode;
    private String state;
    private String customerEmail;
    private String rawXml;
    private String orderID;
    private String title;
    private String submissionDate;
    private String currencyCode;
    private double totalamount;
    private double subtotal;
    private double unitPrice;
    private double tax;
    private int quantity;
    private String paymentMethodName;
    private String testorder;
    private String billpostalCode;
    private String billcity;
    private String lineItemID;
    private String productID;
    private String externalReferenceID;
    private String store;
    private String mfrPartNumber;
    private String downUrl;
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private SourceRepository sourceRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GuidUtil uid;
    @Autowired
    private GlobalProperties gProp;
    
	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getRawXml() {
		return rawXml;
	}
	
	public void setRawXml(String rawData) {
	       this.rawXml = rawData;
	       this.rawXml = this.rawXml.substring(0,this.rawXml.lastIndexOf(">") + 1);
	}

	public double getTotalamount() {
		return totalamount;
	}

	private double getSubtotal() {
		return subtotal;
	}

	private double getUnitPrice() {
		return unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public String getTestorder() {
		return testorder;
	}

	public String getBillpostalCode() {
		return billpostalCode;
	}

	public String getBillcity() {
		return billcity;
	}

	private void setBillcity(String billcity) {
		this.billcity = billcity;
	}

	private void setBillpostalCode(String billpostalCode) {
		this.billpostalCode = billpostalCode;
	}

	public void setTestorder(String testorder) {
		this.testorder = testorder;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	private void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	private void setCustomerLastName(String customerLastName) {
		this.customerLastName = this.MysqlRealScapeString(customerLastName);
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	private void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = this.MysqlRealScapeString(customerFirstName);
	}

	private void setCustomerEmail(String customerEmail) {
		this.customerEmail = this.MysqlRealScapeString(customerEmail);
	}

	private void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	private void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	private String getState() {
		return state;
	}

	private void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	private void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	private String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	private void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName2() {
		return name2;
	}

	private void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName1() {
		return name1;
	}

	private void setName1(String name1) {
		this.name1 = name1;
	}

	public String getLine1() {
		return line1;
	}

	private void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getCountry() {
		return country;
	}

	private void setCountry(String country) {
		this.country = this.MysqlRealScapeString(country);
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) {
		this.city = this.MysqlRealScapeString(city);
	}

	public String getAddressID() {
		return addressID;
	}

	private void setAddressID(String addressID) {
		this.addressID = this.MysqlRealScapeString(addressID);
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	private void setSubmissionDate(String submissionDate) {
		this.submissionDate = this.MysqlRealScapeString(submissionDate);
	}

	public String getOrderID() {
		return orderID;
	}

	public String getStore() {
		return store;
	}

	public String getDownUrl() {
		return downUrl;
	}

	private void setDownUrl(String downUrl) {
		this.downUrl = this.MysqlRealScapeString(downUrl);
	}

	private void setStore(String store) {
		this.store = store;
	}

	public String getLineItemID() {
		return lineItemID;
	}

	public String getProductID() {
		return productID;
	}

	private void setProductID(String productID) {
		this.productID = productID;
	}

	private void setLineItemID(String lineItemID) {
		this.lineItemID = this.MysqlRealScapeString(lineItemID);
	}

	public String getMfrPartNumber() {
		return mfrPartNumber;
	}

	private void setMfrPartNumber(String mfrPartNumber) {
		this.mfrPartNumber = mfrPartNumber;
	}

	public String getExternalReferenceID() {
		return externalReferenceID;
	}

	private void setExternalReferenceID(String externalReferenceID) {
		this.externalReferenceID = externalReferenceID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = this.MysqlRealScapeString(title);
	}

	private void setOrderID(String orderID) {
		this.orderID = orderID;
	}

    public String getLoginID() {
		return loginID;
	}

	private void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return Password;
	}

	private void setPassword(String password) {
		Password = password;
	}
	public AdeptParse() {
//		this.userRepository = ctx.getBean("UserRepository");
    }

	@Transactional
	public Boolean ParseData()  {
	Document xmlDocument = this.parseXmlFromString(this.getRawXml());
    	String expression = "//orderID";
    	try {
    	XPath xPath =  XPathFactory.newInstance().newXPath();
    	NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
    	this.setOrderID(nodeList.item(0).getFirstChild().getNodeValue());
    	expression = "//title";
    	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
    this.setTitle(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//submissionDate";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setSubmissionDate(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/addressID";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setAddressID(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/city";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setCity(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/country";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setCountry(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/line1";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setLine1(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/name1";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setName1(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/name2";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setName2(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/phoneNumber";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setPhoneNumber(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//pricing/total/currencyCode";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setCurrencyCode(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/postalCode";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setPostalCode(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//site/siteAddress/state";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setState(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//loginID";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setLoginID(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//password";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setPassword(nodeList.item(0).getFirstChild().getNodeValue());
	// NOTE: double as total amount currency.
	expression = "//pricing/total/amount";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	String temp = nodeList.item(0).getFirstChild().getNodeValue();
	if (temp != null && !temp.isEmpty()) {
		this.setTotalamount(Double.parseDouble(temp));
	}
	// NOTE: double as sub amount currency.
	expression = "//pricing/subtotal/amount";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	temp = nodeList.item(0).getFirstChild().getNodeValue();
	if (temp != null && !temp.isEmpty()) {
		this.setSubtotal(Double.parseDouble(temp));
	}
	// NOTE: double as tax amount currency.
	expression = "//pricing/tax/amount";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	temp = nodeList.item(0).getFirstChild().getNodeValue();
	if (temp != null && !temp.isEmpty()) {
		this.setTax(Double.parseDouble(temp));
	}
	
	expression = "//paymentInfos/paymentInfo/customerEmail";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setCustomerEmail(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//paymentInfos/paymentInfo/customerLastName";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	if (nodeList != null) {
		this.setCustomerLastName(nodeList.item(0).getFirstChild().getNodeValue());
	}
	expression = "//paymentInfos/paymentInfo/customerFirstName";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setCustomerFirstName(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//paymentInfos/paymentInfo/paymentMethodName";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setPaymentMethodName(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//testOrder";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setTestorder(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//paymentInfos/paymentInfo/billingAddress/postalCode";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setBillpostalCode(nodeList.item(0).getFirstChild().getNodeValue());
	expression = "//paymentInfos/paymentInfo/billingAddress/city";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setBillcity(nodeList.item(0).getFirstChild().getNodeValue());
	
	// The database save is performed here.
	AUser auser = new AUser();
	auser.setUsername(this.getLoginID());
	auser.setPassword(this.getPassword());
	auser.setUuid(uid.addVendorid(23, gProp.getVendorid()) );

	// NOTE: call stored procedure for insert
	Integer res = userRepository.adept_userEntry(this.getPassword(), this.getLoginID(), auser.getUuid());

	List<Source> mysource = (ArrayList<Source>) sourceRepository.findByOrder(this.getOrderID());
	Source source = new Source();
	if(mysource.isEmpty())
	{
		source.setUserId(auser.getId());
		source.setOrderid(this.getOrderID());
		source.setSubmissionDate(this.getSubmissionDate());
		source.setType(this.getTitle());
		source.setCity(this.getCity());
		source.setAddress1(this.getAddressID());
		source.setSite_name1(this.getName1());
		source.setSite_name2(this.getName2());
		source.setSite_phone(this.getPhoneNumber());
		source.setPostalCode(this.getPostalCode());
		source.setState(this.getState());
		source.setSubtotal(this.getSubtotal());
		source.setAmount(this.getTotalamount());
		source.setTax(this.getTax());
		sourceRepository.save(source);
	}
	else
	{
		source = mysource.get(0);
	}
	
	// NOTE: The lineItems loop for purchases *******************
	expression = "//lineItems/item";
	NodeList lineItemList = null;

	lineItemList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	int lineList = lineItemList.getLength();
	for(int i=0; i<lineList; i++) {
		  // Do something with childNode...
	Node item = lineItemList.item(i);
	// NOTE: int as quantity
	expression = ".//quantity";
	nodeList = (NodeList) xPath.compile(expression).evaluate(item, XPathConstants.NODESET);
	temp = nodeList.item(0).getFirstChild().getNodeValue();
	if (temp != null && !temp.isEmpty()) {
		this.setQuantity(Integer.parseInt(temp));
	}
	// NOTE: double as unit price
	expression = ".//pricing/unitPrice/amount";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	temp = nodeList.item(0).getFirstChild().getNodeValue();
	if (temp != null && !temp.isEmpty()) {
		this.setUnitPrice(Double.parseDouble(temp));
	}
	expression = ".//lineItemID";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setLineItemID(nodeList.item(0).getFirstChild().getNodeValue());
	expression = ".//product/productID";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setProductID(nodeList.item(0).getFirstChild().getNodeValue());
	expression = ".//product/externalReferenceID";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	if (nodeList != null) {
		this.setExternalReferenceID(nodeList.item(0).getFirstChild().getNodeValue());
	}
	expression = ".//productInfo/mfrPartNumber";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	this.setMfrPartNumber(nodeList.item(0).getFirstChild().getNodeValue());
	// NOTE: read extendedAttributes values
	expression = ".//extendedAttributes/item";
	nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	int lenList = nodeList.getLength();
	String soreid = "hcstoreID";
	String downrl = "DGPDownloadURL";
	Element eElement=null;
	
	for(int idx=0; idx < lenList; idx++) {
		  // Do something with childNode...
		  if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
			    eElement = (Element) nodeList.item(idx).getChildNodes();
//			    System.out.println("\nCurrent Element :" + nodeList.item(0).getTextContent());
			    if(eElement.getElementsByTagName("name").item(0).getTextContent().equals(soreid) ) {
			    	setStore(eElement.getElementsByTagName("value").item(0).getTextContent());
			    }
			    if(eElement.getElementsByTagName("name").item(0).getTextContent().equals(downrl) ) {
			    	setDownUrl(eElement.getElementsByTagName("value").item(0).getTextContent());
			    }			    
			  }		  
		} // for(integer i=0; i<lenList; i++)
	
		// The database order save is performed here.
		AOrder order = new AOrder();
		order.setSourceid(source.getId());
		order.setUserId(auser.getId());
		order.setCurrencyCode(this.getCurrencyCode());
		order.setQuantity(this.getQuantity());
		order.setCustemail(this.getCustomerEmail());
		order.setCustlastname(this.getCustomerLastName());
		order.setCustfirstname(this.getCustomerFirstName());
		order.setPaymethod(this.getPaymentMethodName());
		order.setTestorder(this.getTestorder());
		order.setPostalcode(this.getPostalCode());
		order.setCity(this.getCity());
		order.setLineitem(this.getLineItemID());
		order.setProductid(this.getProductID());
		order.setIsbn(this.getExternalReferenceID());
		order.setUnitprice(this.getUnitPrice());
		order.setUrl(this.getDownUrl());
		order.setMfrPartNumber(this.getMfrPartNumber());
		orderRepository.save(order);
		LOGGER.info("order added - {}", this.getOrderID());
		// NOTE: Autowired does not work on @Entity clases.
		cleanItem();
	} // for(integer i=0; i<lineList; i++)
	
    	} catch (XPathExpressionException e) {
		String theError = e.getMessage();
		LOGGER.error("doStuff encountered an error with value - {}", theError);
	}		
    		return true;
    } // ParseData
   
   public Document parseXmlFromString(String xmlString) {
		try {
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder;
				builder = factory.newDocumentBuilder();
		    InputStream inputStream = new    ByteArrayInputStream(xmlString.getBytes());
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
   
   public void cleanItem() {
	   String smepty = "";
	   double dzero = 0.0;
	   int izero = 0;
	   
	   this.setQuantity(izero);
	   this.setUnitPrice(dzero);
	   this.setSubtotal(dzero);
	   this.setLineItemID(smepty);
	   this.setProductID(smepty);
	   this.setExternalReferenceID(smepty);
	   this.setMfrPartNumber(smepty);
	   this.setStore(smepty);
	   this.setDownUrl(smepty);
	   this.setQuantity(0);
   } // cleanItem

   public String MysqlRealScapeString(String str) {
	   String data = null;
	   if (str != null && str.length() > 0) {
	     str = str.replace("\\", "\\\\");
	     str = str.replace("'", "\\'");
	     str = str.replace("\0", "\\0");
	     str = str.replace("\n", "\\n");
	     str = str.replace("\r", "\\r");
	     str = str.replace("\"", "\\\"");
	     str = str.replace("\\x1a", "\\Z");
	     data = str;
	   }
	 return data;
   }
}
