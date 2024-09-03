package com.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(public java.util.List<com.dto.UsersPojo> com.controller.ProController.fetchEmpList())")
	public void logB4() {
		LOGGER.info(" fetchEmpList() method called before execution ");
	}

	@AfterThrowing("execution(public java.util.List<com.dto.UsersPojo> com.controller.ProController.fetchEmpList())")
	public void logException() {
		LOGGER.info(" exception thrown in fetchEmpList() method ");
	}

	@AfterReturning("execution(public java.util.List<com.dto.UsersPojo> com.controller.ProController.fetchEmpList())")
	public void logReturn() {
		LOGGER.info(" fetchEmpList() method called after executed ");
	}

	@After("execution(public java.util.List<com.dto.UsersPojo> com.controller.ProController.fetchEmpList())")
	public void logAfter() {
		LOGGER.info(" fetchEmpList() method executed ");
	}

}
