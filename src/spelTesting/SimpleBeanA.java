package spelTesting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleBeanA {
	
	@Value("HELLO FROM SPRING EXPRESSION LANGUAGE!!")
	private String simpleValue;
    
	/*
	 * regular dependency injection to this bean through the property called SimpleValue
	 */
	public String getSimpleValue() {
		System.out.println("Calling the getter: getSimpleValue of bean simpleBeanA::");
		return simpleValue;
	}

	public void setSimpleValue(String simpleValue) {
		this.simpleValue = simpleValue;
	}
	
	

}
