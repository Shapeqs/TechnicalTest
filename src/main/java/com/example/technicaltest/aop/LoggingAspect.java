package com.example.technicaltest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.example.technicaltest..*)" +
            " || within(com.example.technicaltest.service..*)" +
            " || within(com.example.technicaltest.controller..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param throwable the exception thrown
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        String declaratingName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String signatureName = joinPoint.getSignature().getName();
        String errorMessage = throwable.getMessage();
        log.error("Exception in {}.{}() with message error {}", declaratingName, signatureName, errorMessage);
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String declaratingName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String signatureName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        long startTime = System.currentTimeMillis();
        log.info("Enter: {}.{}() with args = {}", declaratingName, signatureName , args);
        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            log.info("Exit: {}.{}() in {}ms with result {}", declaratingName, signatureName , time, result);
            return result;
        } catch (IllegalArgumentException exception) {
            log.error("Illegal argument: {} in {}.{}()", args, declaratingName, signatureName);
            throw exception;
        }
    }
}
