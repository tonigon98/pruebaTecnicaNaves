package com.capitolConsulting.gestionNaves.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NaveEspacialLoggingAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(NaveEspacialLoggingAspect.class);

  // Define un pointcut que coincida con cualquier método que tome un Long como primer parámetro
  @Pointcut("execution(* com.capitolConsulting.gestionNaves.service.*.*(..)) && args(id,..)")
  public void serviceMethodsWithLongArgument(Long id) {}

  // Define un advice antes de los métodos que coincidan con el pointcut y tengan un id negativo
  @Before("serviceMethodsWithLongArgument(id)")
  public void logBeforeServiceMethodsWithNegativeId(JoinPoint joinPoint, Long id) {
    if (id != null && id < 0) {
      LOGGER.warn("Se intentó acceder a una nave con un ID negativo: {}", id);
    }
  }
}
