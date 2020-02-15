package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Roman_Amanov
 */

@Aspect
@Component
@Order(2)
public class DiscountAspect {
    @Autowired
    ApplicationContext context;

    @Around("(execution( * *..*.setDiscount(..)))")
    public Object countDiscountInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        DiscountStrategy strategy = (DiscountStrategy)joinPoint.getArgs()[0];
        User user = strategy.getLastUser();

        DiscountInformation info = DBconnector.getConnection().getDiscountInfo().get(strategy);

        if (info == null)
            info = (DiscountInformation) context.getBean("discountInformation");

        info.increaseUserCounter(user);
        DBconnector.getConnection().getDiscountInfo().put(strategy, info);

        return joinPoint.proceed();
    }

}
