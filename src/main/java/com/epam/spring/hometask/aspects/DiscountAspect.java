package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.domain.utils.DiscountInformation;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Roman_Amanov
 */

@Aspect
@Component
@Order(2)
public class DiscountAspect {
    @Autowired
    ApplicationContext context;
    @Autowired
    DiscountInfoServiceDao discountInfoService;

    @Around("(execution( * *..*.setDiscount(..)))")
    public Object countDiscountInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        DiscountStrategy strategy = (DiscountStrategy)joinPoint.getArgs()[0];
        User user = strategy.getLastUser();
        DiscountInformation info = null;
        Map<Integer, DiscountInformation> table = discountInfoService.findByStrategyName(strategy.getDiscountTitle());
        if (table != null)
            info = table.get((user==null)?0:user.getId());

        if (info == null) {
            info = (DiscountInformation)context.getBean("discountInformation");
            info.setUserId((user==null)?0:user.getId());
            info.setStrategyName(strategy.getDiscountTitle());
        }

        this.discountInfoService.increaseCounter(info);
        return joinPoint.proceed();
    }

}
