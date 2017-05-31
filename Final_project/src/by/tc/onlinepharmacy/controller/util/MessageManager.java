package by.tc.onlinepharmacy.controller.util;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * It's utility class for getting the desired message and sending it to the client.
 */
public final class MessageManager {

    private final static String PROPERTIES_FILE = "localization.local";
    private final static String ENCODING = "UTF-8";

    private MessageManager() {
    }

    /**
     * Returns the corresponding message from properties file by key in the target language.
     *
     * @param language it is a string with language name
     * @param key      it is a string with key name in localization properties file
     * @return a corresponding message by key
     */
    public static String getMessage(String language, String key) {
        Locale locale = new Locale(language);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE, locale);
        return resourceBundle.getString(key);
    }

    /**
     * Sends a message to the client.
     *
     * @param message  it is a string string to send to the client
     * @param response object that contains the response the servlet sends to the client
     * @throws IOException
     */
    public static void sendMessage(String message, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(ENCODING);
        response.getWriter().print(message);
    }
}