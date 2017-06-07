package by.tc.testapp.listener;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;


public class LanguageListener implements HttpSessionListener {

    private final static String RU = "ru_RU";
    private final static String EN = "en_EN";
    private final static String LOCAL = "language";

    private static final Logger LOGGER = Logger.getLogger(LanguageListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(LOCAL, RU);

        LOGGER.info("start");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }
}