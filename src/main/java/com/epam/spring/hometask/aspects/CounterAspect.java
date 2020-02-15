package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.data.DBconnector;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.utils.CommonInformation;
import com.epam.spring.hometask.service.business.EventServiceDao;
import com.epam.spring.hometask.service.business.ScheduledServiceDao;
import com.epam.spring.hometask.service.business.TicketServiceDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Roman_Amanov
 */

@Component
@Aspect
public class CounterAspect {
    @Autowired
    ApplicationContext context;

    @Autowired
    ScheduledServiceDao scheduledService;

    @Autowired
    EventServiceDao eventService;

    // count how many times each event was accessed by name
    @AfterReturning(value = "execution(* *.getEventByName(..))", returning = "retVal")
    public void countEventAccess(Event retVal) {
        if (retVal == null) return;
        CommonInformation info = getInfo(retVal);
        info.increaseAccessedByName();
        DBconnector.getConnection().getCommonInfo().put(retVal, info);
    }

    // how many times its prices were queried
    @Around(value = "execution(* *.getBasePrice(..))")
    public Object countPriceQuery(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        Event event = (Event) joinPoint.getThis();
        CommonInformation info = getInfo(event);
        info.increasePriceQueried();
        DBconnector.getConnection().getCommonInfo().put(event, info);
        return o;
    }

    // how many times its tickets were booked
    @AfterReturning(value = "execution(* *.bookTickets(..)))", returning = "retVal")
    public void countTicketBooking(List<Ticket> retVal) {
        retVal.forEach(ticket -> {
            Event event = eventService.getEventById(
                    scheduledService.getScheduledById(ticket.getScheduledEventId()).getEventId()
            );
            if (event == null) return;
            CommonInformation info = getInfo(event);
            info.increaseTicketBooked();
            DBconnector.getConnection().getCommonInfo().put(event, info);
        });
    }

    private CommonInformation getInfo(Event event) {
        CommonInformation info = DBconnector.getConnection().getCommonInfo().get(event);
        if (info == null) {
            info = (CommonInformation) context.getBean("commonInformation");
            info.setEvent(event);
        }
        return info;
    }
}
