package com.epam.spring.hometask.application;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.shell.jline.PromptProvider;

/**
 * @author Roman_Amanov
 *
 *
 */

@SpringBootApplication
public class MovieTheater {

    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring.xml");
        System.err.println(
                "\n\n\nUse following commands (without comment part) to mainly fill DB or do it automatic by passing 'prepare' command\n" +
                        "Use `help` to see all available commands\n" +
                        "------------------------------------------------------\n" +
                        "user add Vasiliy Pupkin vasya_p@yaplakal.ru 2 (creating new visitor user)\n" +
                        "user birth 1 2010-01-01 (setting birthday to 1st user)\n" +
                        "user add Admin Adminych megapyhar@admins.com 1 (creating new admin user)\n" +
                        "user add Sasha Greyburger ne-tvoya@mail.ru 2 (creating new visitor user)\n" +
                        "event bind 2 3 2020-01-01\\ 20:10 1.5 2 (schedule event)\n" +
                        "ticket buy_r 1 1 --seats 1 3 5 7 9 0 0 0 0 0 (buy tickets for 1st user)"
        );
        SpringApplication.run(MovieTheater.class, args);
    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("MovTheater:> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
