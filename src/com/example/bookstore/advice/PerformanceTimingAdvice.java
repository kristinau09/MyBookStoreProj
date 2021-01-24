package com.example.bookstore.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Aspect
public class PerformanceTimingAdvice {
	
	@Pointcut("execution ( * com.example.bookstore.services.*.*(..) )")
	public void allServiceMethod() {}
	
	@Around("allServiceMethod()")
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
	@Before("allServiceMethod()")
	public void beforeAdviceTesting(JoinPoint jp) {
		System.out.println("Now entering a method......" + jp.getSignature().getName());
	}

}
