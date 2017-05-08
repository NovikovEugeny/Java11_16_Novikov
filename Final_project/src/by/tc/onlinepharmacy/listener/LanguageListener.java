package by.tc.onlinepharmacy.listener;


import by.tc.onlinepharmacy.resource.AttributeName;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Set start locale.
 *
 * @see HttpSessionListener
 */
public class LanguageListener implements HttpSessionListener {

    private final static String RU = "ru";

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(AttributeName.LOCAL, RU);
    }
}