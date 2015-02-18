package kz.kstu.coffee.commands.admin;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.DrinkDAO;
import kz.kstu.coffee.entities.Drink;

/**
 *
 * @author Kirill
 */
public class RefillDrinks implements Command {

    private static final String DRINKS = "drinks";
    private static final String DRINKS_PATH = "/jsp/admin/drinks.jsp";
    private static final String NULL_DRINK_MESSAGE = "Значения должны быть больше 0";
    private static final String ONLY_NUMERICAL_MESSAGE = "Вводите только цифровые значения";
    private static final String ERROR_ATTRIBUTE = "errorDrinksMessage";
    private static final String REFILLED_DRINK_MESSAGE = "refilledDrinksMessage";
    private static final String REFILLED_DRINKS = "Напитки обновлены";

    public String execute(HttpServletRequest request) {
        String page = null;

        //Получаем коллекцию напитков из аттрибута сессии
        HttpSession session = request.getSession(true);
        List<Drink> drinks = (List<Drink>) session.getAttribute(DRINKS);

        //Получаeм список переданных параметров формы
        Map parameters = request.getParameterMap();
        //Проверяем параметры формы и создаем итератор  для полученных напитков
        Iterator<Drink> drinksIterator = drinks.iterator();
        //Обходим коллекцию напитков и сверяем с параметрами формы
        //В случае нахождения нужного параметра обновляем кол-во напитков в базе
        while (drinksIterator.hasNext()) {
            Drink drink = drinksIterator.next();
            String drinkCode = drink.getCode();
            if (parameters.containsKey(drinkCode)) {
                int quantity = 0;
                //Обрабатываем ошибки ввода
                try {
                    quantity = Integer.parseInt(request.getParameter(drinkCode));
                    //Если вводимое знание меньше 0
                    if (quantity < 0) {
                        request.setAttribute(ERROR_ATTRIBUTE, NULL_DRINK_MESSAGE);
                        return page = DRINKS_PATH;
                    }
                    //Если введено не число
                } catch (NumberFormatException nfe) {
                    request.setAttribute(ERROR_ATTRIBUTE, ONLY_NUMERICAL_MESSAGE);
                    return page = DRINKS_PATH;
                }
                DrinkDAO drinkDAO = new DrinkDAO();
                drinkDAO.update(drink, quantity);
            }

        }

        request.setAttribute(REFILLED_DRINK_MESSAGE, REFILLED_DRINKS);

        page = DRINKS_PATH;

        return page;
    }
}
