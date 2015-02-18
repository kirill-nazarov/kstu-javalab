package kz.kstu.coffee.commands.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.IngredientDAO;
import kz.kstu.coffee.entities.Drink;
import kz.kstu.coffee.entities.Ingredient;

/**
 *
 * @author Kirill
 */
public class ConfirmOrder implements Command {

    private static final String ORDERED_DRINKS = "orderedDrinks";
    private static final String INGREDIENTS = "ingredients";
    private static final String CONFIRM_ORDER_PATH = "/jsp/user/confirm_order.jsp";
    private static final String SPLIT_BY_DOT_PATTERN = "\\.";
    private static final String TOTAL_PRICE = "totalPrice";

    public String execute(HttpServletRequest request) {
        String page = null;

        //Получаем заказанные напитки из аттрибута сессии
        HttpSession session = request.getSession(true);
        List<Drink> orderedDrinks = (List<Drink>) session.getAttribute(ORDERED_DRINKS);

        //Убираем добавленные ранее ингредиенты - нужно при возвращении к странице
        for (Drink drink : orderedDrinks) {
            drink.removeIngredients();
        }

        //Проверяем параметры формы
        //На основе данных из установленных checkbox добавляем ингредиенты

        //Получаем все выбранные параметры с именем - ingredients
        String[] ingredientsValues = request.getParameterValues(INGREDIENTS);

        //Обходим коллекцию заказанных напитков и добавляем ингредиенты из формы
        for (int i = 0; i < orderedDrinks.size(); i++) {
            if (ingredientsValues != null) {
                for (int j = 0; j < ingredientsValues.length; j++) {
                    String value = ingredientsValues[j];
                    //Разбиваем строку параметра на 3 части
                    //1)Номер напитка 2)Код напитка 3) Код ингредиента
                    String[] result = value.split(SPLIT_BY_DOT_PATTERN);
                    //result[0] - drink number, result[1] - drink code, result[2] - ingredient code
                    int formDrinkNumber = Integer.parseInt(result[0]);
                    String formDrinkCode = result[1];
                    String formIngredientCode = result[2];
                    String orderedDrinkCode = orderedDrinks.get(i).getCode();
                    if (orderedDrinkCode.equals(formDrinkCode) && i == formDrinkNumber) {
                        Ingredient ingredient = new IngredientDAO().findIngredientByCode(formIngredientCode);
                        //Добавляем ингредиенты к напитку
                        orderedDrinks.get(i).addIngredient(ingredient);
                    }
                }
            }
        }


        //Рассчет общей стоимости заказа
        double totalPrice = 0;
        for (Drink drink : orderedDrinks) {
            totalPrice += drink.getPrice();
            List<Ingredient> orderedIngredients = drink.getIngredients();
            for (Ingredient ingredient : orderedIngredients) {
                totalPrice += ingredient.getPrice();
            }
        }

        //Устанавливаем аттрибут общей стоимости заказа
        session.setAttribute(TOTAL_PRICE, totalPrice);


        //Устанавливаем аттрибут сессии для заказанных напитков
        session.setAttribute(ORDERED_DRINKS, orderedDrinks);

        page = CONFIRM_ORDER_PATH;

        return page;
    }
}
