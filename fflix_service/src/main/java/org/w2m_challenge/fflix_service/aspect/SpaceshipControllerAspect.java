package org.w2m_challenge.fflix_service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.w2m_challenge.fflix_service.service.LoggerService;

@Aspect
@Component
public class SpaceshipControllerAspect {

    private final LoggerService logger;

    public SpaceshipControllerAspect(LoggerService logger) {
        this.logger = logger;
    }

    @Pointcut("execution(* org.w2m_challenge.fflix_service.web.controller.SpaceshipController.getSpaceshipById(..))")
    public void getSpaceshipByIdMethod() {
    }

    @Before("getSpaceshipByIdMethod()")
    public void getSpaceshipByIdMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            int id = Integer.parseInt(args[0].toString());
            if (id < 0)
                logger.warn(String.format("getSpaceshipById: parameter received (id): %s", args[0]));
        }
    }
}
