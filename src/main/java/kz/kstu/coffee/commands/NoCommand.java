package kz.kstu.coffee.commands;

/**
 *
 * @author Kirill
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.config.ConfigManager;
import kz.kstu.coffee.entities.User;

public class NoCommand implements Command {

    private static final String USER = "user";
    private static final String ADMIN = "administrator";
    private static final String USER_PAGE = "/jsp/user/user.jsp";
    private static final String ADMIN_PAGE = "/jsp/admin/admin.jsp";
    private static final String PATH_PAGE_LOGIN = "path.page.login";

    public String execute(HttpServletRequest request) {

        /*
         * Проверка существования пользователя и переадресация на страницу
         * логина в случае отсутствия пользователя
         */
        String page = null;
        User user = null;
        HttpSession session = request.getSession(true);
        //Получение пользователя из сессии
        user = (User) session.getAttribute(USER);

        //Проверка роли пользователся и переадресация на соот. страницу
        if (user != null) {
            if (user.getRole().equals(USER)) {
                page = USER_PAGE;
            } else if (user.getRole().equals(ADMIN)) {
                page = ADMIN_PAGE;
            }
        } else {
            page = ConfigManager.getProperty(PATH_PAGE_LOGIN);
        }

        return page;
    }
}
