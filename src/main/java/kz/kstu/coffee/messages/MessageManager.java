package kz.kstu.coffee.messages;

/**
 *
 * @author Kirll
 */
import java.util.ResourceBundle;

public class MessageManager {

    private static ResourceBundle rb = ResourceBundle.getBundle("messages");
// класс извлекает информацию из файла messages. properties

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return rb.getString(key);
    }
}
