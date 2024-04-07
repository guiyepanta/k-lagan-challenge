package com.klagan.challenge.application.advice;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("true")
public class ExecutionTimeAdvice {

    @Value("${file.log.path}")
    private String fileLogPath;

    @Around("@annotation(com.klagan.challenge.common.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
	long startTime = System.currentTimeMillis();
	Object object = point.proceed();
	long endtime = System.currentTimeMillis();
	long enlapseTime = (endtime - startTime);
	String logMessage = String.format("Source name: %s.%s - Enlapse time : %s ms",
		point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), enlapseTime);

	this.writeLog(logMessage);
	return object;
    }

    private void writeLog(String message) throws SecurityException, IOException {

	Logger logger = Logger.getLogger("custom-log");
	FileHandler fh = new FileHandler(fileLogPath, true);
	logger.addHandler(fh);

	SimpleFormatter formatter = new SimpleFormatter();
	fh.setFormatter(formatter);

	logger.info(message);

	fh.close();

    }
}
