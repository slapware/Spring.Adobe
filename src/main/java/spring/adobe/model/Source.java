package spring.adobe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import spring.adobe.AdeptParse;

@Entity // This tells Hibernate to make a table out of this class
public class Source {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String orderid;
    private String submissionDate;
    private String type;
    private String city;
    private String address1;
    private String site_name1;
    private String site_name2;
    private String site_phone;
    private String postalCode;
    private String state;
    private Double amount;
    private Double subtotal;
    private Double tax;
    
	public Long getId() {
		return id;
	}
	
    public void setId(Long id)
    {
        this.id = id;
    }
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getSite_name1() {
		return site_name1;
	}
	public void setSite_name1(String site_name1) {
		this.site_name1 = site_name1;
	}
	public String getSite_name2() {
		return site_name2;
	}
	public void setSite_name2(String site_name2) {
		this.site_name2 = site_name2;
	}
	public String getSite_phone() {
		return site_phone;
	}
	public void setSite_phone(String site_phone) {
		this.site_phone = site_phone;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
