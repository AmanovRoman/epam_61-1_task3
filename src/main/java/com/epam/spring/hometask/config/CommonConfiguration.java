package com.epam.spring.hometask.config;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.business.AuditoriumServiceDao;
import com.epam.spring.hometask.service.business.EventServiceDao;
import com.epam.spring.hometask.service.business.UserServiceDao;
import com.epam.spring.hometask.service.business.impl.AuditoriumServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Roman_Amanov
 */

@Configuration
@ComponentScan("com.epam.spring.hometask")
@PropertySource("classpath:auditorium.properties")
@EnableAspectJAutoProxy
@Primary
public class CommonConfiguration {

    @Value("${auditory.first.Name}")
    String fAudName;
    @Value("${auditory.second.Name}")
    String sAudName;
    @Value("${auditory.first.Seats}")
    Integer fAudSeats;
    @Value("${auditory.second.Seats}")
    Integer sAudSeats;
    @Value("#{'${auditory.first.Vips}'.split(',')}")
    Set<Integer> fAudVips; //
    @Value("#{'${auditory.second.Vips}'.split(',')}")
    Set<Integer> sAudVips;
    @Value("${auditory.first.vipMult}")
    double fAudMult;
    @Value("${auditory.second.vipMult}")
    double sAudMult;

    @Value("${event.name}")
    String eventName;
    @Value("${event.basePrice}")
    double eventPrice;
    @Value("${event.rating}")
    int eventRating;

    @Bean
    @Primary
    Auditorium firstAuditorium() {
        return new Auditorium(fAudName, fAudSeats, fAudVips, fAudMult);
    }

    @Bean
    @Primary
    Auditorium secondAuditorium() {
        return new Auditorium(sAudName, sAudSeats, sAudVips, sAudMult);
    }

    @Bean
    boolean preReadyLoader(@Autowired AuditoriumServiceDao auditoriumService, @Autowired UserServiceDao userService) {
        auditoriumService.addNewAuditorium(firstAuditorium());
        auditoriumService.addNewAuditorium(secondAuditorium());
        userService.addNewUser("Admin", "Adminych", "Jena@Jizni.net", 1);
        return true;
    }
}
