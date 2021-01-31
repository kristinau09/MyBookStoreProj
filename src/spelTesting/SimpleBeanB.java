package spelTesting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleBeanB {
	
	/* passing whatever the value of simpleBeanA was */
	@Value("#{simpleBeanA.simpleValue}")
	private String secondValue;
	
	/*
	 * when Spring instantiate this bean, it will set default value as being
	 * whatever this expression resolve to
	 */	
	@Value("#{(T(java.lang.Math).random() * 10) + 1}")
	private int randomValue;

	public String getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}

	public int getRandomValue() {
		return randomValue;
	}

	public void setRandomValue(int randomValue) {
		this.randomValue = randomValue;
	}
}
