package spring.adobe;

import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;  
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(scanBasePackages={
		"spring.adobe", "spring.adobe.model"})
@EntityScan(basePackages = "spring.adobe.model")
@ComponentScan ({"spring.adobe.model", "spring.adobe"})
public class AdobeReg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SpringApplication.run(AdobeReg.class, args);  
	}
}
