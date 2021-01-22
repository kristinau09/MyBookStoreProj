package com.exmaple.bookStore.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceTimingAdvice {	
	
	public Object performTimimgMeasurement(ProceedingJoinPoint method) throws Throwable {

		//before
		long startTime = System.currentTimeMillis();

		try {	

			//proceed to the target
			Object returnValue = method.proceed();
			return returnValue;
		} finally {
			//after
			long endTime = System.currentTimeMillis();

			long timeTaken = endTime - startTime;

			//getMethod() return as Java Reflection method instance which has the getName method inside it
			System.out.println("The method " + method.getSignature().getName() + " took " + timeTaken + " milliseconds");
		}

	}
	
	public void beforeAdviceTesting(JoinPoint jp) {
		System.out.println("Now entering a method......" + jp.getSignature().getName());
	}

}
