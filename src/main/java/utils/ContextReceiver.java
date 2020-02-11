package utils;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author Roman_Amanov
 *
 * Simple Spring application context getter
 */

public class ContextReceiver {
    private static ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring.xml");;

    private ContextReceiver() {
    }

    public static ConfigurableApplicationContext getContext() {
        return appCtx;
    }
}
