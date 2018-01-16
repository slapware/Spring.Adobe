package spring.adobe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import spring.adobe.AdeptParse;

@Entity // This tells Hibernate to make a table out of this class
public class AOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long sourceid;
    private Long userId;
    private String currencyCode;
    private String custemail;
    private String custlastname;
    private String custfirstname;
    private String paymethod;
    private String testorder;
    private String postalcode;
    private String city;
    private String lineitem;
    private String productid;
    private String isbn;
    private Double unitprice;
    private Integer quantity;
    private String storeid;
    private String url;
    private String mfrPartNumber;
    
	public Long getId() {
		return id;
	}
	
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getSourceid() {
		return sourceid;
	}
	public void setSourceid(Long sourceid) {
		this.sourceid = sourceid;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCustemail() {
		return custemail;
	}
	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}
	public String getCustlastname() {
		return custlastname;
	}
	public void setCustlastname(String custlastname) {
		this.custlastname = custlastname;
	}
	public String getCustfirstname() {
		return custfirstname;
	}
	public void setCustfirstname(String custfirstname) {
		this.custfirstname = custfirstname;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getTestorder() {
		return testorder;
	}
	public void setTestorder(String testorder) {
		this.testorder = testorder;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLineitem() {
		return lineitem;
	}
	public void setLineitem(String lineitem) {
		this.lineitem = lineitem;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMfrPartNumber() {
		return mfrPartNumber;
	}
	public void setMfrPartNumber(String mfrPartNumber) {
		this.mfrPartNumber = mfrPartNumber;
	}

}
