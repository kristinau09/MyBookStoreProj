package com.exmaple.bookStore.advice;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.AfterReturningAdvice;

public class SimpleLoggingAdvice implements AfterReturningAdvice {

	
	
	/*
	 * Before Advice
	 * first parameter: details of method that is being called 
	 * second parameter: list of all arguments that are being passed to the method 
	 * third parameter: reference to the target objects
	 */
	/*
	 * @Override
	 * public void before(Method method, Object[] arguments, Object targetObject)
	 * throws Throwable { System.out.println("Now about to call the " +
	 * method.getName() + " method");
	 * 
	 * }
	 */

	@Override
	/*
	 * After Advice
	 * first parameter into the list is an object and it's return value that has been passed back from the target object	 
	 */
	public void afterReturning(Object returnValue, Method method, Object[] arguments, Object targetObject) throws Throwable {
		
		//payload
		 System.out.println("Now finished calling the " + method.getName() + " method");
		 //this will change the outcomes of opposite what we want
		 if(returnValue instanceof java.util.List) {
			 List returnList = (List)returnValue;
			 returnList.clear();
		 }
		 System.out.println("The target method returned the value " + returnValue);
		
	}

}
