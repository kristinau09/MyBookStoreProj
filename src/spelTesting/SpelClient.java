package spelTesting;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpelClient {

	public static void main(String[] args) {
	
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("application-spel.xml");
		
		SimpleBeanB beanB = factory.getBean(SimpleBeanB.class);
		System.out.println(beanB.getSecondValue());
		System.out.println(beanB.getRandomValue());
		
		factory.close();

	}

}
