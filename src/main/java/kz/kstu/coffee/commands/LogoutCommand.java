package kz.kstu.coffee.commands;

/**
 *
 * @author Kirill
 */
import javax.servlet.http.HttpServletRequest;
import kz.kstu.coffee.config.ConfigManager;

public class LogoutCommand implements Command {

    private static final String PATH_PAGE_INDEX = "path.page.index";

    public String execute(HttpServletRequest request) {
        //Перевод на главную страницу
        String page = ConfigManager.getProperty(PATH_PAGE_INDEX);
        // уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}
