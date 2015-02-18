package kz.kstu.coffee.commands.admin;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.IngredientDAO;
import kz.kstu.coffee.entities.Ingredient;

/**
 *
 * @author Kirill
 */
public class RefillIngredients implements Command {

    private static final String INGREDIENTS = "ingredients";
    private static final String INGREDIENTS_PATH = "/jsp/admin/ingredients.jsp";
    private static final String NULL_MESSAGE = "Значения должны быть больше 0";
    private static final String ONLY_NUMERICAL_MESSAGE = "Вводите только цифровые значения";
    private static final String ERROR_ATTRIBUTE = "errorIngredientsMessage";
    private static final String REFILLED_INGREDIENT_MESSAGE = "refilledIngredientsMessage";
    private static final String REFILLED_INGREDIENTS = "Ингредиенты обновлены";

    public String execute(HttpServletRequest request) {
        String page = null;

        //Получаем коллекцию ингредиентов из аттрибута сессии
        HttpSession session = request.getSession(true);
        List<Ingredient> ingredients = (List<Ingredient>) session.getAttribute(INGREDIENTS);

        //Получаeм список переданных параметров формы
        Map parameters = request.getParameterMap();
        //Проверяем параметры формы и создаем итератор для полученных ингредиентов
        Iterator<Ingredient> ingredientsIterator = ingredients.iterator();
        //Обходим коллекцию ингредиенто и сверяем с параметрами формы
        //В случае нахождения нужного параметра обновляем кол-во ингредиентов в базе
        while (ingredientsIterator.hasNext()) {
            Ingredient ingredient = ingredientsIterator.next();
            String ingredientCode = ingredient.getCode();
            if (parameters.containsKey(ingredientCode)) {
                int quantity = 0;
                //Обрабатываем ошибки ввода
                try {
                    quantity = Integer.parseInt(request.getParameter(ingredientCode));
                    //Если вводимое знание меньше 0
                    if (quantity < 0) {
                        request.setAttribute(ERROR_ATTRIBUTE, NULL_MESSAGE);
                        return page = INGREDIENTS_PATH;
                    }
                    //Если введено не число, установить 0
                } catch (NumberFormatException nfe) {
                    request.setAttribute(ERROR_ATTRIBUTE, ONLY_NUMERICAL_MESSAGE);
                    return page = INGREDIENTS_PATH;
                }
                IngredientDAO ingredientDAO = new IngredientDAO();
                ingredientDAO.update(ingredient, quantity);
            }

        }

        request.setAttribute(REFILLED_INGREDIENT_MESSAGE, REFILLED_INGREDIENTS);

        page = INGREDIENTS_PATH;
        return page;
    }
}
