package pl.bal.konrad.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* *(..))")
	public void allMethods() {
	}

	@Before("allMethods()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Executing method: " + joinPoint.getSignature().getName());
	}

	// tutaj rownowazne z value = execution(* *(..))
	@After("execution(* *(..))") // tu mozna podobnie allMethods() ale dla
									// przykladu zostawiam
	public void logAfter(JoinPoint joinPoint) {
		logger.info("After method: " + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "allMethods()", returning = "returnedValue")
	public void logAfterReturning(Object returnedValue) {
		logger.info("Returned value: " + returnedValue);
	}

	@AfterThrowing(pointcut = "allMethods()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.info("Mehod " + joinPoint.getSignature().getName() + " has thrown " + exception);
	}

	@Around("allMethods()")
	public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("Execution time: " + (end-start) + "ms");
		return proceed;
	}

}
