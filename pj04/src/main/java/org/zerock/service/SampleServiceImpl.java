package org.zerock.service;

import org.aspectj.lang.annotation.AfterThrowing;
import org.junit.Test;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

// Target
@Service
@Log4j
public class SampleServiceImpl implements SampleService {
	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(str1)+Integer.parseInt(str2);
	}
	
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		log.info("Exception....!!!!");
		log.info("exception: "+exception);
	}
}
