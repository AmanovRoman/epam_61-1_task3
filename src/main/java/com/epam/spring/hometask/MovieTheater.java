package com.epam.spring.hometask;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

/**
 * @author Roman_Amanov
 */

/**
 * @author Roman_Amanov
 */

@SpringBootApplication
public class MovieTheater {

    public static void main(String[] args) {
        SpringApplication.run(MovieTheater.class, args);

    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("MovTheater:> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }

}
