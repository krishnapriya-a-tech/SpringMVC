package com.luv2code.springboot.thymeleafdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {

	//setup Logger
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
	private void forControllerPackage() {}
		
		//do the same for service / dao
	
	@Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage()||forServicePackage()||forDAOPackage()")
	private void forAppFlow() {}
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		//display the method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>in @Before calling Method" + theMethod);
		
		//display the argument to the method
		
		//get the arguments
		Object[] Arg = theJoinPoint.getArgs();	
		
		//loop thur and display  arg
		for(Object tempArg : Arg) {
			myLogger.info("=====> The Argument : " + tempArg);
			
		}
	}
	
	
	//Add @After Returning Advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")	
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>in @AfterRreturning from Method" + theMethod);
		
		//Display the data Result
		myLogger.info("=====> The After Returning :"+ theResult);
	}
		
	
}

























