package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.utils.CommonInformation;
import com.epam.spring.hometask.service.CommonInfoService;
import com.epam.spring.hometask.service.EventService;
import com.epam.spring.hometask.service.ScheduledService;
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
    ScheduledService scheduledService;

    @Autowired
    EventService eventService;

    @Autowired
    CommonInfoService commonInfoService;

    // count how many times each event was accessed by name
    @AfterReturning(value = "execution(* *.getEventByName(..))", returning = "retVal")
    public void countEventAccess(Event retVal) {
        if (retVal != null) {
            CommonInformation info = getInfo(retVal);
            this.commonInfoService.increaseAccessedByName(info);
        }
    }

    // how many times its prices were queried
    @Around(value = "execution(* *.getBasePrice(..))")
    public Object countPriceQuery(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        Event event = (Event) joinPoint.getThis();
        CommonInformation info = getInfo(event);
        this.commonInfoService.increasePriceQueried(info);
        return o;
    }

    // how many times its tickets were booked
    @AfterReturning(value = "execution(* *.bookTickets(..)))", returning = "retVal")
    public void countTicketBooking(List<Ticket> retVal) {
        retVal.forEach((ticket) -> {
            Event event = this.eventService.getEventById(
                    scheduledService.getScheduledById(
                            ticket.getScheduledEventId()).getEventId());
            if (event != null) {
                CommonInformation info = getInfo(event);
                this.commonInfoService.increaseTicketBooked(info);
            }
        });
    }

    private CommonInformation getInfo(Event event) {
        CommonInformation info = commonInfoService.findByEventId(event.getId());
        if (info == null) {
            info = (CommonInformation) context.getBean("commonInformation");
            info.setEventId(event.getId());
            this.commonInfoService.saveInfo(info);
        }
        return info;
    }
}
