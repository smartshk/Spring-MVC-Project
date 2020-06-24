package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

// Advice
@Aspect // 이 클래스가 Aspect를 구현
@Component // 스프링에서 빈으로 인식하기 위해
@Log4j
public class LogAdvice {
	
	// PointCut
	// target을 SampleServiceImpl로 하고 모든 메서드 실행 전 logBefore()을 실행하도록
	@Before("execution(* org.zerock.service.SampleService*.*(..))") 
	public void logBefore () { // advice 1
		log.info("========================");
	}
	
	// doAdd() 실행 전에 이 메소드에 전달되는 파라미터의 값을 로그하는 advice
	@Before("execution(* org.zerock.service.SampleService*.doAdd(String,String)) && args(str1, str2)") // PointCut
	public void logBeforeWithParam (String str1, String str2) { // advice 1
		log.info("str1: "+str1);
		log.info("str2: "+str2);
	}
	
	// ProceedingJointPoint는 @Around와 같이 결합해서 파라미터나 예외 등을 처리할 수 있음 (직접 실행 결정)
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime( ProceedingJoinPoint pjp ) {
		long start = System.currentTimeMillis (); // 시작 시각
		log.info("Target: " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		//invoke method
		Object result = null;
		try {
			result = pjp.proceed(); //target 의 현재 jointpoint 를 실행해라
		} catch (Throwable e) { 
			e.printStackTrace ();
		}
		
		long end = System.currentTimeMillis (); // 종료 시각	
		log.info("TIME: " + (end -start));
		
		return result; // 객체 반환 (결과를 직접 반환하는 형태로 작성해야만 한다)
	}
	
}
