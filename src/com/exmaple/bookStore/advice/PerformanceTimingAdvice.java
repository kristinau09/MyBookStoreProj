package com.exmaple.bookStore.advice;



import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformanceTimingAdvice implements MethodInterceptor {	



	@Override
	public Object invoke(MethodInvocation method) throws Throwable {

		//before
		long startTime = System.nanoTime();

		try {	

			//proceed to the target
			Object returnValue = method.proceed();
			return returnValue;
		} finally {
			//after
			long endTime = System.nanoTime();

			long timeTaken = endTime - startTime;

			//getMethod() return as Java Reflection method instance which has the getName method inside it
			System.out.println("The method " + method.getMethod().getName() + " took " + timeTaken + " nanoseconds");
		}

	}

}
