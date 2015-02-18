package kz.kstu.coffee.commands.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.DrinkDAO;
import kz.kstu.coffee.entities.Drink;

/**
 *
 * @author Kirill
 */
public class ShowDrinks implements Command {

    private static final String DRINKS = "drinks";
    private static final String DRINKS_PATH = "/jsp/admin/drinks.jsp";

    public String execute(HttpServletRequest request) {
        String page = null;

        //Создаем коллекцию для хранения напитков
        List<Drink> drinks = new ArrayList<Drink>();

        //Получаем из базы доступные напитки
        drinks = new DrinkDAO().findAll();

        //Устанавливаем аттрибут для отображения напитков
        HttpSession session = request.getSession(true);
        session.setAttribute(DRINKS, drinks);

        page = DRINKS_PATH;

        return page;
    }
}
