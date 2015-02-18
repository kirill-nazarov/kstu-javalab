package kz.kstu.coffee.commands.user;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.DrinkDAO;
import kz.kstu.coffee.entities.Drink;

public class DrinksCatalogue implements Command {

    private static final String AVAILABLE_DRINKS = "availableDrinks";
    private static final String DRINKS_CATALOGUE_PATH = "/jsp/user/drinks_catalogue.jsp";

    public String execute(HttpServletRequest request) {

        String page = null;
        //Создаем коллекцию доступных напитков
        List<Drink> availableDrinks = new ArrayList<Drink>();
        //Получаем доступные напитки из базы данных
        availableDrinks = new DrinkDAO().findAll();
        //Устанавливае аттрибут сессии для хранения доступных напитков
        HttpSession session = request.getSession(true);
        session.setAttribute(AVAILABLE_DRINKS, availableDrinks);

        //Переадресуем на страницу каталога напитков
        page = DRINKS_CATALOGUE_PATH;

        return page;
    }
}
