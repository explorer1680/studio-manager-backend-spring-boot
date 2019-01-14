package personal.studio.manager.backend.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLogger {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionLogger.class);

    @AfterThrowing(pointcut = "execution(* personal.studio.manager.backend.app.App.*(..))", throwing = "t")
    public void logException(Throwable t){
        LOGGER.warn("Exception thrown from system: ", t);
    }
}
