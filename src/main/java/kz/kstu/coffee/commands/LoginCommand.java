package kz.kstu.coffee.commands;

/**
 *
 * @author Kirill Nazarov
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.config.ConfigManager;
import kz.kstu.coffee.constants.Constants;
import kz.kstu.coffee.dao.UserDAO;
import kz.kstu.coffee.entities.User;
import kz.kstu.coffee.logic.LoginLogic;
import kz.kstu.coffee.messages.MessageManager;

public class LoginCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String ERROR_LOGIN_PASS_MSG = "errorLoginPassMessage";
    private static final String MESSAGE_LOGIN_ERROR = "message.loginerror";
    private static final String PATH_PAGE_LOGIN = "path.page.login";
    
    public String execute(HttpServletRequest request) {

        String page = null;
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // проверка логина и пароля
        if (LoginLogic.authentification(login, pass)) {
            HttpSession session = request.getSession(true);
            User user = null;
            UserDAO userDAO = new UserDAO();
            user = userDAO.findUserByLoginAndPassword(login, pass);
            if (user.getRole().equals(Constants.ADMINISTRATOR_ROLE)) {
                page = Constants.ADMIN_MAIN;
            } else if (user.getRole().equals(Constants.USER_ROLE)) {
                page = Constants.USER_MAIN;
            }
            session.setAttribute(Constants.USER, user);

        } else {
            request.setAttribute(ERROR_LOGIN_PASS_MSG,
                    MessageManager.getProperty(MESSAGE_LOGIN_ERROR));
            page = ConfigManager.getProperty(PATH_PAGE_LOGIN);

        }
        return page;
    }
}
