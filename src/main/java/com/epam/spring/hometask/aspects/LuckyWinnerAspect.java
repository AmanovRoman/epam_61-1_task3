package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.domain.strategies.discount.LuckyWinnerStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Roman_Amanov
 *
 * Aspect intercepts returned data from DiscountServiceImpl.getDicount method
 * and calculate 100% discount based on randomness value in properties
 * If test passed - change discount type LuckyWinnerDiscount, else pass normal discount
 */

@Aspect
@Component
@Order(1)
public class LuckyWinnerAspect {

    @Value("${luck.modifier.percent}")
    private int luckPercent;

    @Around("(execution( * *..DiscountService*.getDiscount(..)))")
    public Object checkLuck(ProceedingJoinPoint joinPoint) throws Throwable {
        return (isLucky()) ? LuckyWinnerStrategy.getInstance() : joinPoint.proceed();
    }

    private boolean isLucky() {
        Random random = new Random();
        Set<Integer> distribution = random.ints(1, 100).limit(luckPercent).boxed().collect(Collectors.toSet());
        Integer number = random.nextInt(100);
        return distribution.contains(number);
    }
}
