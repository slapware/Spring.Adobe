package spring.adobe.model;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GuidUtil {
	private String returnValue;
	
	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public GuidUtil() {
	}

    public String changeCharInPosition(int position, char ch, String str){
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }
    
    public String addVendorid(int position, String vid) {
    		String newuuid = "urn:uuid:" + getReturnValue().substring(0, 23) + vid;
    		return newuuid;
    }
    
    @PostConstruct
    public void init() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        setReturnValue(changeCharInPosition(0, '0', randomUUIDString) );
        setReturnValue(changeCharInPosition(14, '1', getReturnValue()) );
    }

}
