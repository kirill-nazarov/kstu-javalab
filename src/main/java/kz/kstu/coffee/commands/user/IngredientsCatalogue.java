package kz.kstu.coffee.commands.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.DrinkDAO;
import kz.kstu.coffee.dao.IngredientDAO;
import kz.kstu.coffee.entities.Drink;
import kz.kstu.coffee.entities.Ingredient;

/**
 *
 * @author Kirill
 */
public class IngredientsCatalogue implements Command {

    private static final String AVAILABLE_DRINKS = "availableDrinks";
    private static final String AVAILABLE_INGREDIENTS = "availableIngredients";
    private static final String ORDERED_DRINKS = "orderedDrinks";
    private static final String ERROR_DRINK_MESSAGE = "errorDrinksMessage";
    private static final String EMPTY_ORDER_MESSAGE = "errorDrinksMessage";
    private static final String NULL_DRINK_MESSAGE = "Значения должны быть больше 0";
    private static final String ONLY_NUMERICAL_MESSAGE = "Вводите только цифровые значения";
    private static final String DRINKS_CATALOGUE_PATH = "/jsp/user/drinks_catalogue.jsp";
    private static final String INGREDIENTS_CATALOGUE_PATH = "/jsp/user/ingredients_catalogue.jsp";

    public String execute(HttpServletRequest request) {

        String page = null;
        //Коллекция имеющихся ингредиентов
        List<Ingredient> availableIngredients = new IngredientDAO().findAll();
        //Коллекция имеющихся в базе напитков - availableDrinks
        HttpSession session = request.getSession(true);
        List<Drink> availableDrinks = (List<Drink>) session.getAttribute(AVAILABLE_DRINKS);
        //Создаем коллекцию для заказываемых напитков
        List<Drink> orderedDrinks = new ArrayList<Drink>();

        //Получаeм список переданных параметров формы
        Map parameters = request.getParameterMap();
        //Проверяем параметры формы и создаем необходимые напитки
        Iterator<Drink> drinksIterator = availableDrinks.iterator();
        //Обходим коллекцию напитков и добавляем напитки к коллекции заказа
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
                        request.setAttribute(ERROR_DRINK_MESSAGE, NULL_DRINK_MESSAGE);
                        return page = DRINKS_CATALOGUE_PATH;
                    }
                    //Если введено не число
                } catch (NumberFormatException nfe) {
                    request.setAttribute(ERROR_DRINK_MESSAGE, ONLY_NUMERICAL_MESSAGE);
                    return page = DRINKS_CATALOGUE_PATH;
                }
                for (int i = 0; i < quantity; i++) {
                    Drink addDrink = new DrinkDAO().findDrinkByCode(drinkCode);
                    orderedDrinks.add(addDrink);
                }
            }
        }

        //Обработка ошибки если не было заказано ни одного напитка
        if (orderedDrinks.isEmpty()) {
            request.setAttribute(ERROR_DRINK_MESSAGE, EMPTY_ORDER_MESSAGE);
            return page = DRINKS_CATALOGUE_PATH;
        }

        //Устанавливаем аттрибут сессии для имеющихся ингредиентов
        session.setAttribute(AVAILABLE_INGREDIENTS, availableIngredients);
        //Устанавливаем аттрибут сессия для заказанных напитков
        session.setAttribute(ORDERED_DRINKS, orderedDrinks);
        //Переадресуем на страницу подтверждения заказа
        page = INGREDIENTS_CATALOGUE_PATH;

        return page;
    }
}
