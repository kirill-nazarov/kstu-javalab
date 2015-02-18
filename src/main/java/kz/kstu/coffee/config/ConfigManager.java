package kz.kstu.coffee.config;

/**
 *
 * @author Kirill
 */
import java.util.ResourceBundle;

public class ConfigManager {

    private static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("resources.config");
// класс извлекает информацию из файла config.properties

    private ConfigManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
